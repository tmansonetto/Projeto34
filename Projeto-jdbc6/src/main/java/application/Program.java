package application;

import db.DB;
import db.DBIntegrityException;
import db.DbException;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) throws IOException, SQLException {

        Connection connection = null;
        Statement estatement = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            connection = DB.getConnection();
            connection.setAutoCommit(false);//Todas as transações não serão comitadas automaticamente
            estatement = connection.createStatement();

            int rows1 = estatement.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
            int rows2 = estatement.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            connection.commit();

            System.out.println("rows1 " + rows1);
            System.out.println("rows2 " + rows2);


        } catch (SQLException e) {
          try {
              connection.rollback();
              throw new DbException("Transação desfeita pelo sistema! Causa: " + e.getMessage());
          }catch (SQLException ex) {
                throw new DbException("Erro ao tentar desfazer a transação! Causa: " + ex.getMessage());
          }
        } finally {
            DB.closeStatement(estatement);
            DB.closeConnection();

        }
    }
}



