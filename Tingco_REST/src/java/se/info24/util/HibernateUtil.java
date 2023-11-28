/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.info24.util;


import org.apache.log4j.Logger;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author Sridhar Dasari
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private static final SessionFactory ismOperationsSessionFactory;
    private static final SessionFactory timSessionFactory;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            timSessionFactory = new AnnotationConfiguration().configure("timHibernate.cfg.xml").buildSessionFactory();
            ismOperationsSessionFactory = new AnnotationConfiguration().configure("ismOperationsHibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception.
            Logger logger = Logger.getLogger(HibernateUtil.class);
            logger.error("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static SessionFactory getISMOperationsSessionFactory() {
        return ismOperationsSessionFactory;
    }
    public static SessionFactory getTimSessionFactory() {
        return timSessionFactory;
    }
}
