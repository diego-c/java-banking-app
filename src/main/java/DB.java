package src.main.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
    public static void connectToDB() {
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
            }
        } catch (SQLException e) {
            System.out.println("Connection exception");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                System.out.println("Closing connection...");
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Could not close the connection!");
                    e.printStackTrace();
                }
            } else {
                System.out.println("No connection was established to the DB");
            }
        }
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
