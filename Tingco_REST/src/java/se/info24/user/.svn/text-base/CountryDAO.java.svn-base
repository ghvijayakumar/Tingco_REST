/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.user;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.pojo.Country;
import se.info24.pojo.CountryPhoneCodes;
import se.info24.pojo.TimeZones;
import se.info24.util.TCMUtil;

/**
 * Utility class to load / save / delete records from Country table.
 * @author Sekhar
 */
public class CountryDAO {

    public List<Country> getAllCountries(Session session) {
        List<Country> countryList = null;
        try {
            countryList = session.getNamedQuery("getAllCountry").list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
        }
        return countryList;
    }

    public List<Country> getCountryByLanguage(Session session) {
        return session.getNamedQuery("getCountryByLanguage").list();
    }

    public List<CountryPhoneCodes> getCountryPhoneCodes(Session session) {
        return session.getNamedQuery("getCountryPhoneCodes").list();
    }

    public boolean saveCountry(Country country, Session session) {
        Transaction tx = null;
        try {
            if (country != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(country);
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

    public Country getCountryById(int countryId, Session session) {
        try {
            return (Country) session.getNamedQuery("getCountryById").setInteger("countryID", countryId).uniqueResult();
        } catch (IndexOutOfBoundsException ae) {
            return null;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public List<Country> getCountriesBySearchString(String searchString, Session session) {
        return session.getNamedQuery("getCountriesByseachString").setString("searchString", searchString).list();
    }

    public boolean removeCountry(Country country, Session session) {
        Transaction tx = null;
        try {
            if (country != null) {
                tx = session.beginTransaction();
                session.delete(country);
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

    public List<TimeZones> getAllTimezones(Session session) {
        List<TimeZones> timeZoneList = null;
        try {
            timeZoneList = session.getNamedQuery("getAllTimeZones").list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
        }
        return timeZoneList;
    }

    public List<Country> getCountryByIdList(Session session, List<Integer> list) {
        return session.getNamedQuery("getCountryByIdList").setParameterList("countryId", list).list();
    }
}
