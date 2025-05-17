package com.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import javax.persistence.Query;

import com.entity.Car;
import com.entity.Engine;
import com.util.DbConnection;

public class CarDao {

	public static void addCar(Scanner sc) {
		EntityManager em = DbConnection.getEntityManager();
		EntityTransaction et = DbConnection.getTransaction(em);
		System.out.println("Enter the Brand of Car:");
		String Brand = sc.nextLine();
		System.out.print("Enter car model: ");
		String model = sc.nextLine();
		Car car = new Car();
		car.setBrand(Brand);
		car.setModel(model);
		car.setRegisterDate(LocalDate.now());

		et.begin();
		em.persist(car);
		et.commit();

	}

	public static void removeCar(Scanner sc) {
		EntityManager em = DbConnection.getEntityManager();
		EntityTransaction et = DbConnection.getTransaction(em);
		System.out.println("Enter the Id of the Car which you want to remove:");
		int id = sc.nextInt();
		String jpql = "DELETE FROM Car c WHERE c.id = :carId";
		Query query = em.createQuery(jpql);
		query.setParameter("carId", id);
		int deletedCount = query.executeUpdate();

		if (deletedCount > 0) {
			System.out.println("Car with ID " + id + " removed successfully.");
		} else {
			System.out.println("Car with ID " + id + " not found!");
		}
		et.commit();

	}

	public static void findCarById(Scanner sc) {
		EntityManager em = DbConnection.getEntityManager();
		System.out.println("Enter the Id:");
		int id = sc.nextInt();
		String jpql = "SELECT c FROM Car c WHERE c.id = :carId";
		Query query = em.createQuery(jpql);
		query.setParameter("carId", id);
		Car car = (Car) query.getSingleResult();
		System.out.println("\nCar Details:");
		System.out.println("ID: " + car.getId());
		System.out.println("Brand: " + car.getBrand());
		System.out.println("Model: " + car.getModel());
		System.out.println("Registration Date: " + car.getRegisterDate());

	}

	public static void findCarbyRegisterDate(LocalDate date) {
		EntityManager em = DbConnection.getEntityManager();
		String jpql = "Select c from Car c where c.registerDate = :date";
		Query query = em.createQuery(jpql);
		query.setParameter("date", date);
		List<Car> cars = query.getResultList();
		if (cars.isEmpty()) {
			System.out.println("No cars registered on " + date);
			return;
		}
		System.out.println("\nCars registered on " + date + ":");
		cars.forEach(c -> System.out.println(c.getId() + ": " + c.getBrand() + " " + c.getModel()));

	}

	public static void allocateEngine(Scanner sc) {
		EntityManager em = DbConnection.getEntityManager();
        EntityTransaction et = DbConnection.getTransaction(em);
        System.out.print("Enter car ID: ");
        int carId = sc.nextInt();
        System.out.print("Enter engine ID: ");
        int engineId = sc.nextInt();
        
        Car car = em.find(Car.class, carId);
        Engine engine = em.find(Engine.class, engineId);
        
        if (car == null || engine == null) {
            System.out.println("Invalid car or engine ID!");
            return;
        }

        Query query = em.createQuery("SELECT c FROM Car c WHERE c.engine = :engine");
        query.setParameter("engine", engine);
        if (!query.getResultList().isEmpty()) {
            System.out.println("Engine already allocated to another car!");
            return;
        }
        
        et.begin();
        car.setEngine(engine);
        em.merge(car);
        et.commit();
        
        System.out.println("Engine with EngineId:"+ engineId + " successfully allocated to car with CarId:"+carId);
	}

	public static void deallocateEngine(Scanner sc) {
		EntityManager em = DbConnection.getEntityManager();
        EntityTransaction et = DbConnection.getTransaction(em);
        System.out.print("Enter car ID: ");
        int carId = sc.nextInt();
        Car car = em.find(Car.class, carId);
        if (car == null) {
            System.out.println("Car not found!");
            return;
        }
        
        if (car.getEngine() == null) {
            System.out.println("Car has no engine allocated!");
            return;
        }
        
        et.begin();
        car.setEngine(null);
        em.merge(car);
        et.commit();
        System.out.println("Engine deallocated successfully!");
	}

}
