package com.app;
import java.time.LocalDate;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import com.dao.CarDao;
import com.dao.CustomerDao;
import com.dao.EngineDao;
import com.entity.Car;
import com.entity.Customer;
import com.entity.Engine;
import com.util.DbConnection;

public class Driver {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;
		
		System.out.println("Welcome to Car Rental System!!!!!");
		
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
					CustomerDao.buyCar(scanner);
					break;
					
				case 5:
					CustomerDao.findCustomersByRegistrationDate(LocalDate.now());
					break;

				
				case 6:
					CarDao.addCar(scanner);
					break;			
				case 7:
					CarDao.removeCar(scanner);
					break;
				case 8:
					CarDao.findCarById(scanner);
					break;
				case 9:
					CarDao.findCarbyRegisterDate(LocalDate.now());
					break;
				case 10:
					CarDao.allocateEngine(scanner);
					break;
				case 11:
					CarDao.deallocateEngine(scanner);
					break;
					
				
				case 12:
					EngineDao.addEngine(scanner);
					break;
				case 13:
					EngineDao.removeEngine(scanner);
					break;
				case 14:
					EngineDao.findAllEngine(scanner);
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
		
		scanner.close();  
	}
	
	private static void displayMenu() {
	    System.out.println("\n=== Car Rental System Menu ===");
	    System.out.println("--- Customer Operations ---");
	    System.out.println("1 : Add Customer");
	    System.out.println("2 : Remove Customer");
	    System.out.println("3 : Find Customer by ID");
	    System.out.println("4 : Buy Car");
	    System.out.println("5 : Find Customer by registerDate");
	    

	    System.out.println("\n--- Car Operations ---");
	    System.out.println("6 : Add Car");
	    System.out.println("7 : Remove Car");
	    System.out.println("8 : Find Car by ID");
	    System.out.println("9 : Find Cars by RegisterDate");
	    System.out.println("10 : Allocate Engine");
	    System.out.println("11 : Deallocate Engine");
	    
	    System.out.println("\n--- Engine Operations ---");
	    System.out.println("12 : Add Engine");
	    System.out.println("13 : Remove Engine");
	    System.out.println("14 : Find All Engine");
	    System.out.println("\n0 : Exit");
	}
}