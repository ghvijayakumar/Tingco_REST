/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.info24.objectcomments;

import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.ismOperationsPojo.ObjectComments;
import se.info24.util.TCMUtil;

/**
 *
 * @author Hitha
 */
public class ObjectCommentsDAO {

    public boolean saveObjectComments(ObjectComments objectComments, Session session) {
        Transaction tx = null;
        try {
            if (objectComments != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(objectComments);
                tx.commit();
                return true;
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return false;
    }


    public  boolean deleteObjectComment(String objectCommentId, Session session) {
        Transaction tx = null;
        try {
            Query query = session.getNamedQuery("deleteObjectCommentByObjectCommentId").setString("objectCommentId",objectCommentId);
            query.executeUpdate();
            tx = session.beginTransaction();
            tx.commit();
            return true;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    public List<ObjectComments> getObjectComments(String deviceId, Session session) {
        return session.getNamedQuery("getObjectCommentByObjectId").setString("objectId",deviceId).list();
    }

    boolean updateObjectComment(String objectCommentId, String subject, String body,String del, Session session) {
        Transaction tx = null;
        try {
            ObjectComments objectComments = (ObjectComments) session.getNamedQuery("getObjectCommentByObjectCommentId").setString("objectCommentId",objectCommentId).list().get(0);
            objectComments.setSubject(TCMUtil.convertHexToString(subject));
            objectComments.setBody(TCMUtil.convertHexToString(body));
            objectComments.setIsDeleted(Integer.parseInt(del));
            GregorianCalendar gc = new GregorianCalendar();
            objectComments.setUpdatedDate(gc.getTime());
            tx = session.beginTransaction();
            session.saveOrUpdate(objectComments);
            tx.commit();
            return true;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

}
