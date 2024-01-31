package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

import java.util.List;

public class Program {

    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("--- TEST 1: seller findById ---");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n--- TEST 2: seller findByDepartment ---");
        List<Seller> sellers = sellerDao.findByDepartment(seller.getDepartment());
        sellers.forEach(System.out::println);

        System.out.println("\n--- TEST 3: seller findAll ---");
        sellers = sellerDao.findAll();
        sellers.forEach(System.out::println);

        DB.closeConnection();

    }
}
