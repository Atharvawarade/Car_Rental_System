package com.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.entity.Engine;
import com.util.DbConnection;

public class EngineDao {
	
	public static void addEngine(Scanner sc) {
		EntityManager em = DbConnection.getEntityManager();
		EntityTransaction et = DbConnection.getTransaction(em);
		System.out.println("Enter the CC of the Engine");
		int cc = sc.nextInt();
		System.out.println("Enter the type of the Engine:");
		String type = sc.next();
		
		Engine engine = new Engine();
		engine.setCc(cc);
		engine.setType(type);
		
		et.begin();
		em.persist(engine);
		et.commit();
		System.out.println("Engine added successfully with ID: " + engine.getId());
	}

	public static void removeEngine(Scanner sc) {
		EntityManager em = DbConnection.getEntityManager();
		EntityTransaction et = DbConnection.getTransaction(em);
		System.out.println("Enter the engine id that you want to remove:");
		int id = sc.nextInt();
		
		Engine engine = em.find(Engine.class, id);
		if(engine==null) {
			System.out.println("Engine with "+ id+ " not found.");
		}
		else
		{
			et.begin();
			em.remove(engine);
			et.commit();
		}
		
	}

	public static void findAllEngine(Scanner scanner) {
		EntityManager em = DbConnection.getEntityManager();
		String jpql = "SELECT e FROM Engine e";
		Query query = em.createQuery(jpql);
		List<Engine> engines = query.getResultList();
		
		if(engines.size() == 0) {
			System.out.println("No engines found!!!");
		}
		
		else {
			engines.forEach(al -> System.out.println(al.getId()));
		}
	}

}
