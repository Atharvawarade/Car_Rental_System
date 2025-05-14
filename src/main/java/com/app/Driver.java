package com.app;

import java.time.LocalDate;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.dao.CarDao;
import com.dao.CustomerDao;
import com.entity.Car;
import com.entity.Customer;
import com.entity.Engine;
import com.util.DbConnection;

public class Driver {
	public static void main(String[] args) {
//        EntityManager em = DbConnection.getEntityManager();
//        EntityTransaction et = DbConnection.getTransaction(em);
//        
//        Engine e = new Engine();
//        e.setCc(100);
//        e.setType("petrol");
//        
//        Car c = new Car();
//        c.setBrand("Ferrari");
//        c.setModel("GT");
//        c.setEngine(e);
//        c.setRegisterDate(LocalDate.of(2025, 4, 12));
//        
//        Customer cst = new Customer();
//        cst.setName("Atharva");
//        cst.setPhone("7410767476");
//        cst.setEmail("atharvarwarade@gmail.com");
//        cst.setAddress("Bihar");
//        cst.setCar(c);
//        
//		et.begin();
//		em.persist(e);
//		em.persist(c);
//		em.persist(cst);
//		et.commit();
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;
		while (!exit) {
			displayMenu();
			System.out.print("\nEnter your choice: ");

			try {
				int choice = Integer.parseInt(scanner.nextLine());

				switch (choice) {
				case 1:
					CustomerDao.addCustomer();
					break;
				case 2:
					CustomerDao.removeCustomer(scanner);
					break;
				case 3:
					CustomerDao.findCustomerById(scanner);
					break;
				case 4:
					CustomerDao.updateCustomer(scanner);
					break;
				case 5:
					CustomerDao.listAllCustomers();
					break;
				case 6:
					CustomerDao.findCustomerByName(scanner);
					break;
				case 7:
					CarDao.addCar(scanner);
					break;
				case 8:
					CarDao.removeCar(scanner);
					break;
				case 9:
					CarDao.findCarById(scanner);
					break;
				case 10:
					CarDao.updateCar(scanner);
					break;
				case 11:
					CarDao.listAllCars();
					break;
				case 12:
					CarDao.findCarsByBrand(scanner);
					break;
				case 13:
					CarDao.listAvailableCars();
					break;
				case 0:
					exit = true;
					System.out.println("Thank you for using Car Rental System! Goodbye!");
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
				}

				if (!exit) {
					System.out.println("\nPress Enter to continue...");
					scanner.nextLine();
				}

			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a number.");
			} catch (Exception e) {
				System.out.println("An error occurred: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	 private static void displayMenu() {
	        System.out.println("\n=== Car Rental System Menu ===");
	        System.out.println("--- Customer Operations ---");
	        System.out.println("1 : Add Customer");
	        System.out.println("2 : Remove Customer");
	        System.out.println("3 : Find Customer by ID");
	        System.out.println("4 : Update Customer");
	        System.out.println("5 : List All Customers");
	        System.out.println("6 : Find Customer by Name");
	        
	        System.out.println("\n--- Car Operations ---");
	        System.out.println("7 : Add Car");
	        System.out.println("8 : Remove Car");
	        System.out.println("9 : Find Car by ID");
	        System.out.println("10 : Update Car");
	        System.out.println("11 : List All Cars");
	        System.out.println("12 : Find Cars by Brand");
	        System.out.println("13 : List Available Cars");
	        
	        System.out.println("\n0 : Exit");
	    }
}
