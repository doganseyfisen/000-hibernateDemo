package com.hibernatedemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(City.class)
				.buildSessionFactory();
		// Unit of Work
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			// HQL -> Hibernate Query Language
			// Select * from city -->List<City> cities... for icinded de city olacak
			// from City c where c.countryCode='TUR' AND c.district='Ankara' -> AND/OR 
			// from City c where c.name LIKE '%kara%' -> icinde 'kara' olan sehirleri arar
			// from City c ORDER BY c.name -> A'dan Z'ye sehirler siralanir
			// ASC (en bastan en sona) siralama ve DESC (en sondan en basa) siralama var
			
				List<String> countryCodes =
				session.createQuery("select c.countryCode from City c GROUP BY c.countryCode"
				).getResultList(); for(String countryCode:countryCodes) {
				System.out.println(countryCode); }
			// >Insert kodu<
			/*
			 * City city = new City();
			 * city.setName("Fatsa"); 
			 * city.setDistrict("Karadeniz");
			 * city.setPopulation(95000); 
			 * city.setCountryCode("TUR"); 
			 * session.save(city);
			 */
			// >Update kodu<
			/*
			 * City city = session.get(City.class, 4080); 
			 * city.setPopulation(12500);
			 * session.save(city);
			 */
			// ---CRUD Create, Read, Update, Delete---
			// >Delete kodu<
			//City city = session.get(City.class, 4082); 
			//session.delete(city);
			
			
			//session.getTransaction().commit();
			//System.out.println("City deleted.");
		} finally {
			factory.close();
		}
	}
}
