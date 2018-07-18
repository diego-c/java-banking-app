package src.main.java;

import java.sql.*;
import java.util.UUID;

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

    public void findClientePorNome(String nome) {
        if (connection != null) {
            String sql = "SELECT * FROM usuarios WHERE LOWER(nome) LIKE ?";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, nome);

                ResultSet rs = statement.executeQuery();
                System.out.println("Usuários encontrados:\n");
                while (rs.next()) {
                    UUID ID = (UUID) rs.getObject("id");
                    String nomeEncontrado = rs.getString("nome");
                    String email = rs.getString("email");
                    String telefone = rs.getString("telefone");
                    String senha = rs.getString("senha");

                    System.out.println("ID: " + ID.toString());
                    System.out.println("Nome: " + nomeEncontrado);
                    System.out.println("Email: " + email);
                    System.out.println("Telefone: " + telefone);
                    System.out.println("Senha: " + senha + "\n");
                }
                rs.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Não foi possível estabelecer uma conexão com o BD");
        }
    }
}
