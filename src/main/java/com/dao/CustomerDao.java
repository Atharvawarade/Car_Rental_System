package com.dao;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.entity.Customer;
import com.util.DbConnection;

public class CustomerDao {
	public static void addCustomer() {
		EntityManager em = DbConnection.getEntityManager();
        EntityTransaction et = DbConnection.getTransaction(em);
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- Add New Customer ---");
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

	public static void removeCustomer(Scanner scanner) {
		// TODO Auto-generated method stub
		
	}

	public static void findCustomerById(Scanner scanner) {
		// TODO Auto-generated method stub
		
	}

	public static void updateCustomer(Scanner scanner) {
		// TODO Auto-generated method stub
		
	}

	public static void listAllCustomers() {
		// TODO Auto-generated method stub
		
	}

	public static void findCustomerByName(Scanner scanner) {
		// TODO Auto-generated method stub
		
	}

	
}
