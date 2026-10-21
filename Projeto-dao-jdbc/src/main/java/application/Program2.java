package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println();
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        System.out.println("Teste número 1: departamento findById ");
        Department department = departmentDao.findById(3);
        System.out.println(department);

        System.out.println("\n=== Teste número 2: seller findAll ");
        List<Department> list = departmentDao.findAll();
        list = departmentDao.findAll();

        for (Department sell : list) {
            System.out.println(sell);
        }

        System.out.println("\n=== Teste número 4: departamento insert ");
        Department newDepartment = new Department(null,"Almoxarifado");
        departmentDao.insert (newDepartment);
        System.out.println("Department inserted successfully id=" + newDepartment.getId() );

        System.out.println("\n=== Teste número 2: seller findAll ");
        list = departmentDao.findAll();

        for (Department sell : list) {
            System.out.println(sell);
        }

        System.out.println("\n=== Teste número 5: department update ");
        newDepartment = departmentDao.findById(3);
        newDepartment.setName("RH");
        departmentDao.update(newDepartment);
        System.out.println("\nUpdate done! ");

        System.out.println("\n=== Teste número 2: seller findAll ");
        list = departmentDao.findAll();

        for (Department sell : list) {
            System.out.println(sell);
        }

        System.out.println("\n=== Teste número 6: department delete ");
        System.out.print("Digite o código do departamento para exclusão: ");
        int id = sc.nextInt();
        departmentDao.deleteById(id);

        System.out.println("\nDelete done! ");

        System.out.println("\n=== Teste número 2: seller findAll ");
        list = departmentDao.findAll();

        for (Department sell : list) {
            System.out.println(sell);
        }


        sc.close();
    }
}
