/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.user;

import java.util.Calendar;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import se.info24.pojo.ApplicationEmailTemplates;
import se.info24.pojo.ApplicationSettings;
import se.info24.pojo.UserLoginResetRequests;
import se.info24.pojo.UserSessions2;
import se.info24.util.TCMUtil;

/**
 *
 * @author Sekhar
 */
public class UserSession2DAO {

    public List<UserLoginResetRequests> getAllUserLoginPassResetReq(Session session) {
        return session.getNamedQuery("getAlluserpasswordresetReq").list();
    }

    public List<ApplicationEmailTemplates> getApplicationEmailTemplates(int countryId, Session session) {
        return session.getNamedQuery("getAppEmailTemplates").setInteger("countryId", countryId).list();
    }

    public List<ApplicationSettings> getApplicationSettings(String applicationid, Session session) {
        return session.getNamedQuery("getAppSettingByAppID").setString("appID", applicationid).list();
    }

    public List<ApplicationSettings> getAllApplicationSettings(Session session) {
        return session.getNamedQuery("getAllAppSettings").list();
    }

    public UserSessions2 getUserSession2ByAuthToken(String authToken, Session session) {

        UserSessions2 userSessions2 = null;
        List<UserSessions2> sessionList = null;
        try {

            sessionList = session.getNamedQuery("getUserSessionByToken").setString("authenticationToken", authToken).list();
            if (sessionList != null && sessionList.size() > 0) {
                userSessions2 = sessionList.get(0);
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
        }

        return userSessions2;
    }

    public void saveUserSession2(UserSessions2 userSessions2, Session session) {

        Transaction tx = null;
        try {
            if (userSessions2 != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(userSessions2);
                tx.commit();
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public void removeUserSession2(UserSessions2 userSession2, Session session) {

        Transaction tx = null;
        try {
            if (userSession2 != null) {
                tx = session.beginTransaction();
                session.delete(userSession2);
                tx.commit();
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public List<UserSessions2> getAllUserSessions2(Session session) {
        List<UserSessions2> sessionList = null;
        try {
            sessionList = session.getNamedQuery("getAllUserSessions2").list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
        }
        return sessionList;
    }

    public List<UserLoginResetRequests> getAllUserLoginPassResetReqByDate(Session session) {
        try {
            Calendar cal = Calendar.getInstance();
            return session.createCriteria(UserLoginResetRequests.class).add(Restrictions.le("requestExpiryDate", cal.getTime())).list();
        } catch (Exception e) {
            TCMUtil.exceptionLog(UserSession2DAO.class.getName(), e);
            return null;
        }
    }

    public void setIsEmailSent(UserLoginResetRequests ulrr, Session session) {
        Transaction tx = null;
        try {
            if (ulrr != null) {
                tx = session.beginTransaction();
                ulrr.setIsMailSent(1);
                session.saveOrUpdate(ulrr);
                tx.commit();
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(UserSession2DAO.class.getName(), ex);
            if (tx != null) {
                tx.rollback();
            }
        }
    }
}
