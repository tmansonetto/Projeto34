package db;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {

    private static Connection connection = null;

    public static Connection getConnection() throws IOException {
        if (connection == null) {
            try {
                Properties prop = loadProperties();
                String url = prop.getProperty("dburl");
                connection = DriverManager.getConnection(url, prop);
            } catch (SQLException ex) {
                throw new DbException(ex.getMessage());
            }
        }
        return connection;
    }
    public static void closeConnection() throws SQLException {
        if (connection != null) {
            try {
            connection.close();
        }
            catch (SQLException ex) {
            throw new DbException(ex.getMessage());}
        }
    }
    private static Properties  loadProperties() throws IOException {
        InputStream input = null;
        try (FileInputStream fis = new FileInputStream("db.properties")) {
           Properties props = new Properties();
           props.load(fis);
           return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }
}
