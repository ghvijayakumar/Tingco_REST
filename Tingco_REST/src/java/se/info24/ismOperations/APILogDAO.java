/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.ismOperations;

import se.info24.util.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.ismOperationsPojo.Apilog;

/**
 *
 * @author Sekhar Babu
 */
public class APILogDAO {

    public void saveAPILog(Apilog apilog) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getISMOperationsSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(apilog);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
        } finally {
            session.close();
        }
    }
}
