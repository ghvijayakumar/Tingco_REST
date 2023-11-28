/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.restUtil;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.pojo.City;
import se.info24.pojo.CountryStates;
import se.info24.pojo.Currency;
import se.info24.pojo.GroupWeekdays;
import se.info24.pojo.Ismnews;
import se.info24.pojo.News;
import se.info24.pojo.NewsCategories;
import se.info24.pojo.NewsCategoryTranslations;
import se.info24.pojo.NewsVisibleInGroups;
import se.info24.pojo.ObjectUsageUnits;
import se.info24.pojo.TimeZones;
import se.info24.pojo.UserLog;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.WeekdayTranslations;
import se.info24.util.HibernateUtil;
import se.info24.util.TCMUtil;

/**
 *
 * @author Sekhar
 */
public class RestUtilDAO {

    public GroupWeekdays getGroupWeekDays(String weekdayId, String groupId, Session session) {
        return (GroupWeekdays) session.getNamedQuery("getGroupWeekDaysByIds").setString("weekDayId", weekdayId).setString("groupId", groupId).uniqueResult();
    }

    // Currency CRUD Methods.
    public boolean saveCurrencies(Currency currency, Session session) {
        Transaction tx = null;
        try {
            if (currency != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(currency);
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

    public ObjectUsageUnits getObjectUsageUnitsById(String usageUnitId, Session session) {
        try {
            return (ObjectUsageUnits) session.getNamedQuery("getObjectUsageUnitsById").setString("usageUnitId", usageUnitId).uniqueResult();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public Currency getCurrencyById(String currencyId, Session session) {
        try {
            return (Currency) session.getNamedQuery("getCurrencyById").setString("currencyId", currencyId).uniqueResult();
        } catch (IndexOutOfBoundsException ae) {
            return null;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public boolean removeCurrency(Currency currency, Session session) {
        Transaction tx = null;
        try {
            if (currency != null) {
                tx = session.beginTransaction();
                session.delete(currency);
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

    public List<Currency> getAllCurrency(Session session) {
        try {
            return session.getNamedQuery("getAllCurrency").list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }

    }

    //Timezone CRUD methods
    public boolean saveTimezone(TimeZones timezone, Session session) {
        Transaction tx = null;
        try {
            if (timezone != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(timezone);
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

    public static Date convertGMTtoUserLocalTime(String timeZoneGMToffset, Date GMTDate) {
        String hour = null;
        String min = null;
        GregorianCalendar gc = new GregorianCalendar();
        try {
            gc.setTime(GMTDate);
            if (timeZoneGMToffset.startsWith("+")) {
                hour = timeZoneGMToffset.substring(1, 3);
                gc.add(GregorianCalendar.HOUR, Integer.valueOf(hour));
                if (!timeZoneGMToffset.startsWith("0", 4)) {
                    min = timeZoneGMToffset.substring(4, 6);
                    gc.add(GregorianCalendar.MINUTE, Integer.valueOf(min));
                }
            } else if (timeZoneGMToffset.startsWith("-")) {
                hour = timeZoneGMToffset.substring(1, 3);
                gc.add(GregorianCalendar.HOUR, -Integer.valueOf(hour));
                if (!timeZoneGMToffset.startsWith("0", 4)) {
                    min = timeZoneGMToffset.substring(4, 6);
                    gc.add(GregorianCalendar.MINUTE, -Integer.valueOf(min));
                }
            }
            return gc.getTime();
        } catch (Exception e) {
            return gc.getTime();
        } finally {
//            System.gc();
        }

    }

    public static Date convertUserLocalTimetoGMT(String timeZoneGMToffset, Date localDate) {
        String hour = null;
        String min = null;
        GregorianCalendar gc = new GregorianCalendar();
        try {
            gc.setTime(localDate);
            if (timeZoneGMToffset.startsWith("+")) {
                hour = timeZoneGMToffset.substring(1, 3);
                gc.add(GregorianCalendar.HOUR, -Integer.valueOf(hour));
                if (!timeZoneGMToffset.startsWith("0", 4)) {
                    min = timeZoneGMToffset.substring(4, 6);
                    gc.add(GregorianCalendar.MINUTE, -Integer.valueOf(min));
                }
            } else if (timeZoneGMToffset.startsWith("-")) {
                hour = timeZoneGMToffset.substring(1, 3);
                gc.add(GregorianCalendar.HOUR, Integer.valueOf(hour));
                if (!timeZoneGMToffset.startsWith("0", 4)) {
                    min = timeZoneGMToffset.substring(4, 6);
                    gc.add(GregorianCalendar.MINUTE, Integer.valueOf(min));
                }
            }
            return gc.getTime();
        } catch (Exception e) {
            return gc.getTime();
        } finally {
//            System.gc();
        }

    }

    public static long convertGMTtoUserLocalTimeInLongFormat(String timeZoneGMToffset, Date GMTDate) {
        String hour = null;
        String min = null;
        GregorianCalendar gc = new GregorianCalendar();
        try {
            gc.setTime(GMTDate);
            if (timeZoneGMToffset.startsWith("+")) {
                hour = timeZoneGMToffset.substring(1, 3);
                gc.add(GregorianCalendar.HOUR, Integer.valueOf(hour));
                if (!timeZoneGMToffset.startsWith("0", 4)) {
                    min = timeZoneGMToffset.substring(4, 6);
                    gc.add(GregorianCalendar.MINUTE, Integer.valueOf(min));
                }
            } else if (timeZoneGMToffset.startsWith("-")) {
                hour = timeZoneGMToffset.substring(1, 3);
                gc.add(GregorianCalendar.HOUR, -Integer.valueOf(hour));
                if (!timeZoneGMToffset.startsWith("0", 4)) {
                    min = timeZoneGMToffset.substring(4, 6);
                    gc.add(GregorianCalendar.MINUTE, -Integer.valueOf(min));
                }
            }
            return gc.getTimeInMillis();
        } catch (Exception e) {
            return gc.getTimeInMillis();
        } finally {
//            System.gc();
        }

    }

    public static long convertUserLocalTimetoGMTInLongFormat(String timeZoneGMToffset, Date localDate) {
        String hour = null;
        String min = null;
        GregorianCalendar gc = new GregorianCalendar();
        try {
            gc.setTime(localDate);
            if (timeZoneGMToffset.startsWith("+")) {
                hour = timeZoneGMToffset.substring(1, 3);
                gc.add(GregorianCalendar.HOUR, -Integer.valueOf(hour));
                if (!timeZoneGMToffset.startsWith("0", 4)) {
                    min = timeZoneGMToffset.substring(4, 6);
                    gc.add(GregorianCalendar.MINUTE, -Integer.valueOf(min));
                }
            } else if (timeZoneGMToffset.startsWith("-")) {
                hour = timeZoneGMToffset.substring(1, 3);
                gc.add(GregorianCalendar.HOUR, Integer.valueOf(hour));
                if (!timeZoneGMToffset.startsWith("0", 4)) {
                    min = timeZoneGMToffset.substring(4, 6);
                    gc.add(GregorianCalendar.MINUTE, Integer.valueOf(min));
                }
            }
            return gc.getTimeInMillis();
        } catch (Exception e) {
            return gc.getTimeInMillis();
        } finally {
//            System.gc();
        }

    }

    public static TimeZones getTimezoneById(String timezoneId, Session session) {
        try {
            return (TimeZones) session.getNamedQuery("getTimezoneById").setString("timezoneId", timezoneId).uniqueResult();
        } catch (IndexOutOfBoundsException ae) {
            return null;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog("se.info24.restUtil.RestUtilDAO", he);
            return null;
        }
    }

    public boolean removeTimeZone(TimeZones timezone, Session session) {
        Transaction tx = null;
        try {
            if (timezone != null) {
                tx = session.beginTransaction();
                session.delete(timezone);
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

    // CRUD methods for ISMNews.
    public Ismnews getNewsById(String newsId, Session session) {
        try {
            return (Ismnews) session.getNamedQuery("getNewsById").setString("newsId", newsId).list().get(0);
        } catch (IndexOutOfBoundsException ae) {
            return null;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public boolean removeNews(Ismnews news, Session session) {
        Transaction tx = null;
        try {
            if (news != null) {
                tx = session.beginTransaction();
                session.delete(news);
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

    public List<Ismnews> getAllNews(Session session) {
        try {
            return session.getNamedQuery("getAllNews").setMaxResults(10).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public boolean saveNews(Ismnews ismnews, Session session) {
        Transaction tx = null;
        try {
            if (ismnews != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(ismnews);
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

    //City CRUD Methods.
    public boolean saveCity(City city, Session session) {
        Transaction tx = null;
        try {
            if (city != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(city);
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

    public City getCityById(String cityId, Session session) {
        try {
            return (City) session.getNamedQuery("getCityById").setString("cityId", cityId).uniqueResult();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }

    }

    public boolean removeCity(City city, Session session) {
        Transaction tx = null;
        try {
            if (city != null) {
                tx = session.beginTransaction();
                session.delete(city);
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

    // CRUD Methods For CountryStates.
    public List<CountryStates> getCountryStatesByCountryId(int countryId, Session session) {
        try {
            return session.getNamedQuery("getCountryStatesByCountryId").setInteger("countryId", countryId).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public List<WeekdayTranslations> getWeekDays(Session session, int countryId) {
        return session.getNamedQuery("getWeekDays").setInteger("countryId", countryId).list();
    }

    public static UserTimeZones2 getUserTimeZones2byUserId(String userId, Session session) {
        return (UserTimeZones2) session.getNamedQuery("getTimeZoneByUserID").setString("userID", userId).uniqueResult();
    }

    boolean updateUserTimeZones(String userID, String timezoneID, String useDayLightSaving, Session session) {
        UserTimeZones2 utz2 = getUserTimeZones2byUserId(userID, session);
        Transaction tx = null;
        boolean result = false;
        if (utz2 != null) {
            utz2.setTimeZoneId(timezoneID);
            utz2.setUseDaylightSaving(Integer.valueOf(useDayLightSaving));
            utz2.setCreatedDate(utz2.getCreatedDate());
            GregorianCalendar gc = new GregorianCalendar();
            utz2.setUpdatedDate(gc.getTime());
            try {
                tx = session.beginTransaction();
                session.saveOrUpdate(utz2);
                tx.commit();
                session.flush();
                session.clear();
                result = true;
            } catch (Exception e) {
                TCMUtil.exceptionLog(getClass().getName(), e);
                result = false;
            }
        } else {
            utz2 = new UserTimeZones2();
            utz2.setUserId(userID);
            utz2.setTimeZoneId(timezoneID);
            utz2.setUseDaylightSaving(Integer.valueOf(useDayLightSaving));
            GregorianCalendar gc = new GregorianCalendar();
            utz2.setCreatedDate(gc.getTime());
            utz2.setUpdatedDate(gc.getTime());
            try {
                tx = session.beginTransaction();
                session.saveOrUpdate(utz2);
                tx.commit();
                session.flush();
                session.clear();
                result = true;
            } catch (Exception e) {
                TCMUtil.exceptionLog(getClass().getName(), e);
                result = false;
            }
        }
        return result;
    }

    public boolean saveUserLog(UserLog userLog, Session session) {
        Transaction tx = null;
        try {
            if (userLog != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(userLog);
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

    List<NewsCategories> getAllNewsCategories(Session session) {
        return session.getNamedQuery("getAllNewsCategories").list();
    }

    List<NewsCategoryTranslations> getNewsCategoryTranslationsByNewsCategoryIDs(Session session, int coutryId,  Set<String> newsCategoryList, int maxResults) {
        return session.getNamedQuery("getNewsCategoryTranslationsByIDs").setInteger("countryId", coutryId).setParameterList("newsCategoryId", newsCategoryList).setMaxResults(maxResults).list();
    }

    List<NewsCategoryTranslations> getNewsCategoryTranslationsByNewsCategoryIDs(Session session, int coutryId,  Set<String> newsCategoryList) {
        return session.getNamedQuery("getNewsCategoryTranslationsByIDs").setInteger("countryId", coutryId).setParameterList("newsCategoryId", newsCategoryList).list();
    }

    List<NewsCategoryTranslations> getNewsCategoryTranslationsBySearchString(Session session, int coutryId, String categoryName) {
        return session.getNamedQuery("getNewsCategoryTranslationsBySearchString").setInteger("countryId", coutryId).setString("categoryName", categoryName).list();
    }

    boolean addNewNews(News newNews, List<NewsVisibleInGroups> newsVisibleInGroupsList, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(newNews);
            for(NewsVisibleInGroups nvig : newsVisibleInGroupsList){
                session.save(nvig);
            }
            tx.commit();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return true;
    }

    Object getNewsCategoriesByNewsCategoryID(Session session, String newsCategoryID) {
        return session.getNamedQuery("getNewsCategoriesByNewsCategoryID").setString("newsCategoryId", newsCategoryID).uniqueResult();
    }

//    public List<NewsVisibleInGroups> getNewsVisibleInGroupsByGroupID(List<String> groupId, Session session) {
//        return session.getNamedQuery("getNewsVisibleInGroupsByGroupID").setParameterList("groupId", groupId).list();
//    }

    List<NewsVisibleInGroups> getNewsVisibleInGroupsByGroupID(String groupId, Session session) {
        return session.getNamedQuery("getNewsVisibleInGroupsByGroupID").setString("groupId", groupId).list();
    }


//    List<News> getNewsByGroupIDAndDate(Session session, List<String> newsIDs, String dateTimeNow,String groupId) {
//        return session.getNamedQuery("getNewsByGroupIDAndDate").setParameterList("newsId", newsIDs).setString("timenow", dateTimeNow).setString("groupId", groupId).list();
//    }

    List<News> getNewsByIsVisibleToAllGroupsAndDate(Session session, String dateTimeNow) {
        return session.getNamedQuery("getNewsByIsVisibleToAllGroupsAndDate").setString("timenow", dateTimeNow).list();
    }

    Object getNewsByNewsID(String newsIDTCMV3, Session session) {
        return session.getNamedQuery("getNewsByNewsID").setString("newsId", newsIDTCMV3).uniqueResult();
    }

    List<NewsVisibleInGroups> getNewsVisibleInGroupsByNewsID(String newsid, Session session) {
        return session.getNamedQuery("getNewsVisibleInGroupsByNewsID").setString("newsId", newsid).list();
    }

    boolean deleteNews(News news, List<NewsVisibleInGroups> newsVisibleInGroupses, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for(NewsVisibleInGroups nvig : newsVisibleInGroupses){
                session.delete(nvig);
            }
            session.delete(news);
            tx.commit();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return true;
    }

    List<News> getNewsByGroupIDAndDate(Session session, List<String> newsIDs, String dateTimeNow) {
        return session.getNamedQuery("getNewsByGroupIDAndDate").setParameterList("newsId", newsIDs).setString("timenow", dateTimeNow).list();
    }

}
