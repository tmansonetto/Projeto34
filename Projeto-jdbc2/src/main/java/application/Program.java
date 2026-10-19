package application;
import db.DB;
import db.DbException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {
    public static void main(String[] args) throws IOException, SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DB.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM department");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + "," + resultSet.getString("name"));
            }
        }
            catch (SQLException e){
                e.printStackTrace();
            }
finally{
            DB.closeResultSet(resultSet);
            DB.closeStatement(statement);
            DB.closeConnection();

        }



        }

    }

