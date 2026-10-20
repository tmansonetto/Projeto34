package application;

import db.DB;
import db.DBIntegrityException;
import db.DbException;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;
import model.impl.SellerDaoJDBC;

import java.util.Date;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Program {
    public static void main(String[] args) throws IOException {


        System.out.println();
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("Teste número 1: seller findById ");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n=== Teste número 2: seller findByDepartment ");
        Department department = new Department(2,null);
        List<Seller> list = sellerDao.findByDepartment(department);

        for (Seller sell : list) {
            System.out.println(sell);
        }

        System.out.println("\n=== Teste número 3: seller findAll ");
        list = sellerDao.findAll();

        for (Seller sell : list) {
            System.out.println(sell);
    }
}
}




