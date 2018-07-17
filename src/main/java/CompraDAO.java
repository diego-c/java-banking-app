package src.main.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CompraDAO {
    private Connection connection;

    public CompraDAO() {
        this.connection = DB.connectToDB();
    }

    public boolean insertCompra(CompraBean compra) {
        Timestamp ts = Timestamp.valueOf(compra.getHorario());
        if (connection != null) {
            String sql = "INSERT INTO compras (HORARIO, VALOR, ESTABELECIMENTO) VALUES (?, ?, ?)";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(sql);
                statement.setTimestamp(1, ts);
                statement.setObject(2, compra.getValor().getNumber());
                statement.setString(3, compra.getEstabelecimento());

                statement.execute();
                System.out.println("Compra adicionada ao BD");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (statement != null) {
                    try {
                        statement.close();
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }
}
