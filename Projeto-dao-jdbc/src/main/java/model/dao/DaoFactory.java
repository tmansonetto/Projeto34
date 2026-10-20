package model.dao;

import db.DB;
import model.impl.SellerDaoJDBC;

import java.io.IOException;

public class DaoFactory {
public static SellerDao createSellerDao() throws IOException {
    return  new SellerDaoJDBC(DB.getConnection());
}
}
