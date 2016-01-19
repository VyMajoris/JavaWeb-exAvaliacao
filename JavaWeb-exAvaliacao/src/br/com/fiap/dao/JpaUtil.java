package br.com.fiap.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class JpaUtil {


	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("jpaPU");

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();


	}

	public static Session getHibSession() {
		return  (Session) getEntityManager().getDelegate();
	}




}
