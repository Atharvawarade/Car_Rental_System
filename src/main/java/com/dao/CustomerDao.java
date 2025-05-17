package com.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.entity.Car;
import com.entity.Customer;
import com.util.DbConnection;

public class CustomerDao {
	public static void addCustomer() {
		EntityManager em = DbConnection.getEntityManager();
		EntityTransaction et = DbConnection.getTransaction(em);
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter customer name: ");
		String name = sc.nextLine();

		System.out.print("Enter customer email: ");
		String email = sc.nextLine();

		System.out.print("Enter customer phone: ");
		String phone = sc.nextLine();

		System.out.print("Enter customer address: ");
		String address = sc.nextLine();

		Customer customer = new Customer();
		customer.setName(name);
		customer.setEmail(email);
		customer.setPhone(phone);
		customer.setAddress(address);

		et.begin();
		em.persist(customer);
		et.commit();

	}

	public static void removeCustomer(Scanner sc) {
		EntityManager em = DbConnection.getEntityManager();
		EntityTransaction et = DbConnection.getTransaction(em);
		System.out.print("Enter customer ID to remove: ");
		int c_id = sc.nextInt();
		Customer customer = em.find(Customer.class, c_id);

		if (customer == null) {
			System.out.println("Customer with ID " + c_id + " not found!");
			return;
		}

		et.begin();
		em.remove(customer);
		et.commit();

	}

	public static void findCustomerById(Scanner sc) {
		EntityManager em = DbConnection.getEntityManager();
		System.out.print("Enter customer ID to find: ");
		int c_id = sc.nextInt();
		String jpql = "SELECT c FROM Customer c WHERE c.id = :id";
		Query query = em.createQuery(jpql);
		query.setParameter("id", c_id);
//		List<Customer> li = query.getResultList();
//		li.forEach(al -> System.out.println(al.getId()));
//		
		Customer customer = (Customer) query.getSingleResult();

		// Display customer details
		System.out.println("\nCustomer Details:");
		System.out.println("ID: " + customer.getId());
		System.out.println("Name: " + customer.getName());
		System.out.println("Email: " + customer.getEmail());
		System.out.println("Phone: " + customer.getPhone());
		System.out.println("Address: " + customer.getAddress());

	}
	
	
	public static void buyCar(Scanner sc) {
		EntityManager em = DbConnection.getEntityManager();
		EntityTransaction et = DbConnection.getTransaction(em);
		
	    System.out.println("Enter customer_id of customer who has to buy a car:");
	    int c_id = sc.nextInt();
	    
	    System.out.println("Enter car_id of the car to be purchased:");
	    int car_id = sc.nextInt();
	    
	    // Find the customer and car
	    Customer customer = em.find(Customer.class, c_id);
	    Car car = em.find(Car.class, car_id);
	    
	    if (customer == null) {
	        System.out.println("Customer with ID " + c_id + " not found!");
	        return;
	    }
	    
	    if (car == null) {
	        System.out.println("Car with ID " + car_id + " not found!");
	        return;
	    }
	    
	    if (car.getCustomer() != null) {
	        System.out.println("This car is already owned by another customer!");
	        return;
	    }
	    
	    et.begin();
        // Set the relationship both ways
        customer.setCar(car);
        car.setCustomer(customer);
        car.setRegisterDate(LocalDate.now()); // Set registration date to today
        em.merge(customer);
        em.merge(car);
        et.commit();
        
        System.out.println("Car purchased successfully!");
        System.out.println("Customer: " + customer.getName() + " now owns " + car.getBrand() + " " + car.getModel());
		
	}

	public static void findCustomersByRegistrationDate(LocalDate now) {
		EntityManager em = DbConnection.getEntityManager();
		String jpql = "SELECT c FROM Customer c JOIN c.car car WHERE car.registerDate = :date";
		Query query = em.createQuery(jpql, Customer.class);
		query.setParameter("date", now);
		List<Customer> customers = query.getResultList();
		System.out.println("ID\tName\t\tCar Brand\tCar Model\tRegistration Date");
		System.out.println("--------------------------------------------------------------");

		for (Customer customer : customers) {
			System.out.printf("%-5d\t%-15s\t%-10s\t%-10s\t%s\n", customer.getId(), customer.getName(),
					customer.getCar().getBrand(), customer.getCar().getModel(), customer.getCar().getRegisterDate());
		}
	}

	

}

