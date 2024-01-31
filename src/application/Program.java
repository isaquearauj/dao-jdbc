package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

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

        System.out.println("\n--- TEST 4: seller insert ---");
        Seller newSeller = new Seller(null, "Greg Brown", "greg@gmail.com", LocalDate.parse("2000-07-10"), 4000.0, seller.getDepartment());
        sellerDao.insert(newSeller);
        if (newSeller.getId() != null) {
            System.out.println("Inserted! New id = " + newSeller.getId());
        }

        System.out.println("\n--- TEST 5: seller update ---");
        seller = sellerDao.findById(1);
        seller.setName("Martha Grimes");
        sellerDao.update(seller);
        if (Objects.equals(sellerDao.findById(1).getName(), "Martha Grimes")) {
            System.out.println("Update completed!");
        }

        System.out.println("\n--- TEST 6: seller delete ---");
        System.out.print("Enter id for delete test: ");
        int id = sc.nextInt();
        if (sellerDao.findById(id) != null) {
            sellerDao.deleteById(id);
            System.out.println("Delete completed!");
        } else {
            System.out.println("Id not found!");
        }

        DB.closeConnection();
    }
}
