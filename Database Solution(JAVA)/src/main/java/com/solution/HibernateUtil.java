package com.solution;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = initFactory();

    private static SessionFactory initFactory() {
        try {
            // retrieve config for hibernate on conguration file
            return new Configuration().configure()
                            .addAnnotatedClass(Record.class)
                            .buildSessionFactory();
                            
        } catch (Throwable ex) {
            // set logging here
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // destroy session
        sessionFactory.close();
    }
}
