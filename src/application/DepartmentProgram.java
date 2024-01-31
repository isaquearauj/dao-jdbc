package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DepartmentProgram {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("--- TEST 1: department findById ---");
        Department department = departmentDao.findById(2);
        System.out.println(department);

        System.out.println("\n--- TEST 2: department findAll ---");
        List<Department> departments = departmentDao.findAll();
        departments.forEach(System.out::println);

        System.out.println("\n--- TEST 3: department insert ---");
        Department newDepartment = new Department(null, "Design");
        departmentDao.insert(newDepartment);
        if (newDepartment.getId() != null) {
            System.out.println("Inserted! New id = " + newDepartment.getId());
        }

        System.out.println("\n--- TEST 4: department update ---");
        department = departmentDao.findById(3);
        department.setName("Cyber security");
        departmentDao.update(department);
        if (Objects.equals(departmentDao.findById(3).getName(), "Cyber security")) {
            System.out.println("Update completed!");
        }

        System.out.println("\n--- TEST 5: department delete ---");
        System.out.print("Enter id for delete test: ");
        int id = sc.nextInt();
        if (departmentDao.findById(id) != null) {
            departmentDao.deleteById(id);
            System.out.println("Delete completed!");
        } else {
            System.out.println("Id not found!");
        }

        DB.closeConnection();
    }
}
