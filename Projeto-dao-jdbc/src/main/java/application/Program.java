package application;

import db.DB;
import db.DBIntegrityException;
import db.DbException;
import model.entities.Department;
import model.entities.Seller;
import java.util.Date;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args)  {


        System.out.println();

        Department department = new Department(1,"books");
        System.out.println(department);

        System.out.println();

        Seller seller = new Seller(1,"Joao","teste@gmail", new Date(),3000.0, department );
        System.out.println(seller);
    }
}



