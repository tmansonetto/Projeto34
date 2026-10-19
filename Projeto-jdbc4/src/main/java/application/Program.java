package application;
import db.DB;
import db.DbException;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) throws IOException, SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            connection = DB.getConnection();
            preparedStatement = connection.prepareStatement(
                    "UPDATE seller "
                            + "SET BaseSalary = BaseSalary + ? "
                            + "WHERE "
                            + "(DepartmentId = ?)");


                    preparedStatement.setDouble(1,200.0);
                    preparedStatement.setInt(2,2);

                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println("Feito! Registros atualizados " + rowsAffected);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(preparedStatement);
            DB.closeConnection();

        }
    }
}



