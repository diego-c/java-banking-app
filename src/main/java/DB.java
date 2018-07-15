package src.main.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    public static Connection connectToDB() {
        Connection connection = null;
        try {
            String host = loadProperty("db_host");
            String port = loadProperty("db_port");
            String db = loadProperty("db");
            String user = loadProperty("db_user");
            String pass = loadProperty("db_password");
            if (host != null && port != null && db != null && user != null && pass != null) {
                StringBuilder url = new StringBuilder();
                url.append("jdbc:postgresql://");
                url.append(host);
                url.append(":");
                url.append(port);
                url.append("/");
                url.append(db);
                connection = DriverManager.getConnection(url.toString(), user, pass);
                System.out.println("Connected to bank!");
            } else {
                System.out.println("Could not load the properties file");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Connection exception");
            e.printStackTrace();
        }
        return connection;
    }
    public static boolean insertUser(String nome, String email, String telefone, String senha) {
        Connection connection = connectToDB();
        String salt = loadProperty("password_salt");
        StringBuilder passwordHash = new StringBuilder(512);
        if (salt != null) {
            passwordHash.append(Password.hashPassword(senha, salt));

            if (connection != null) {
                String sql = "INSERT INTO usuarios(nome, email, telefone, senha, contas) VALUES (?, ?, ?, ?, ?)";
                Array contasArray = null;
                try {
                    contasArray = connection.createArrayOf("contas", new Contas[] { Contas.BASICA, null });

                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, nome);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, telefone);
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
    private static String loadProperty(String prop) {
        Properties props = new Properties();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader("config.properties"));
            props.load(reader);

            return props.getProperty(prop);
        } catch (FileNotFoundException e) {
            System.err.println("Could not find properties file");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Could not load properties file");
            e.printStackTrace();
        }
        System.out.println("Could not retrieve properties file");
        return null;
    }
}
