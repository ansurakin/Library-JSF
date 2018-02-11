/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.alexander.library.entity;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Tim
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    
    static {
	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
			.configure() // configures settings from hibernate.cfg.xml
			.build();
	try {
		sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
	}
	catch (Exception e) {
		// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
		// so destroy it manually.
		StandardServiceRegistryBuilder.destroy( registry );
	}
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
  
}
