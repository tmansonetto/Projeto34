package application;
import db.DB;
import db.DbException;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) throws IOException, SQLException {

        Connection connection = DB.getConnection();
        PreparedStatement preparedStatement = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            connection = DB.getConnection();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO seller "
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                            + "VALUES "
                            + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, "Ike Purple");
                    preparedStatement.setString(2,"Ike@gmail.com");
                    preparedStatement.setDate(3, new java.sql.Date(simpleDateFormat.parse("22/04/1985").getTime()));
                    preparedStatement.setDouble(4,3000.0);
                    preparedStatement.setInt(5,4);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        ResultSet resultSet = preparedStatement.getGeneratedKeys();
                        while (resultSet.next()) {
                            int id = resultSet.getInt(1);
                            System.out.println("Feito! registro adicionado número: " + id);
                        }
                    }
                    else {
                        System.out.println("Registro não adicionado!");
                    }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(preparedStatement);
            DB.closeConnection();

        }
    }
}



