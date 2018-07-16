package src.main.java;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {
    private Connection connection;

    public ClienteDAO() {
        connection = DB.connectToDB();
    }

    public boolean insertUser(ClienteBean cliente) {
        String salt = DB.loadProperty("password_salt");
        StringBuilder passwordHash = new StringBuilder(512);
        if (salt != null) {
            passwordHash.append(Password.hashPassword(cliente.getSenha(), salt));

            if (connection != null) {
                String sql = "INSERT INTO usuarios(nome, email, telefone, senha, contas) VALUES (?, ?, ?, ?, ?)";
                Array contasArray = null;
                try {
                    contasArray = connection.createArrayOf("contas", new Contas[] { Contas.BASICA, null });

                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, cliente.getNome());
                    preparedStatement.setString(2, cliente.getEmail());
                    preparedStatement.setString(3, cliente.getTelefone());
                    preparedStatement.setString(4, passwordHash.toString());
                    preparedStatement.setArray(5, contasArray);

                    preparedStatement.execute();
                    preparedStatement.close();
                    System.out.println("User added to DB!");
                    connection.close();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Could not connect to DB");
            }
        } else {
            System.out.println("Could not load salt");
        }
        return false;
    }
}
