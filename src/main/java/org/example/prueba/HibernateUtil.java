package org.example.prueba;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory sessionFactory;

    static{
        sessionFactory = new Configuration()
                .configure()
                .setProperty("hibernate.connection.username",System.getenv("hibernate_username"))
                .setProperty("hibernate.connection.password",System.getenv("hibernate_password"))
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}

