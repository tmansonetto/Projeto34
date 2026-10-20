package application;
import db.DB;
import db.DbException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Program {
    public static void main(String[] args) throws IOException, SQLException {
        Connection connection = DB.getConnection();
        System.out.println("Conexão aberta com sucesso!");
        DB.closeConnection();
    }
}
