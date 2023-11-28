/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.drivers;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.ismOperationsPojo.DriversLogItemHistory;
import se.info24.ismOperationsPojo.DriversLogItems;
import se.info24.pojo.Device;
import se.info24.pojo.DeviceUsers;
import se.info24.pojo.JourneyTypeTranslations;
import se.info24.pojo.JourneyTypesInGroups;
import se.info24.util.TCMUtil;

/**
 *
 * @author Hitha
 */
public class DriversDAO {

    public List<JourneyTypeTranslations> getJourneyTypeTranslations(Session session, String groupId, int countryId) {
        List<JourneyTypesInGroups> jouTypes = session.getNamedQuery("getJourneyTypesInGroups").setString("groupId", groupId).list();
        List<JourneyTypeTranslations> jouTypTrans = new ArrayList<JourneyTypeTranslations>();
        try {
            for (JourneyTypesInGroups jtig : jouTypes) {
                JourneyTypeTranslations jtt = (JourneyTypeTranslations) session.getNamedQuery("getJourneyTypeTranslationsByJourneyTypeID").setString("journeyTypeId", jtig.getId().getJourneyTypeId()).setInteger("countryId", countryId).list().get(0);
                jouTypTrans.add(jtt);
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        return jouTypTrans;
    }

    public List<DriversLogItemHistory> getJourneyHistoryDetails(Session session, String deviceId, String fromDate, String toDate, int countryId, String userId, String filterFlag, String journeyTypeId, String journeyPurpose) {
        List<DriversLogItemHistory> driverLogHis = new ArrayList<DriversLogItemHistory>();
        try {
            if(journeyTypeId != null && journeyPurpose != null){
                if (filterFlag.equalsIgnoreCase("1")) {
                    driverLogHis = session.getNamedQuery("getDriverLogHistoryByJourneyDateAndTypeID").setString("deviceId", deviceId).setString("userId", userId).setString("fromDate", fromDate).setString("toDate", toDate).setParameter("journeyPurpose", journeyPurpose,Hibernate.STRING).setString("journeyTypeId", journeyTypeId).list();
                } else {
                    driverLogHis = session.getNamedQuery("getDriverLogHistoryByExportedDateAndTypeID").setString("deviceId", deviceId).setString("userId", userId).setString("fromDate", fromDate).setString("toDate", toDate).setParameter("journeyPurpose", journeyPurpose,Hibernate.STRING).setString("journeyTypeId", journeyTypeId).list();
                }
            }else if(journeyPurpose != null){
                if (filterFlag.equals("1")) {
                    driverLogHis = session.getNamedQuery("getDriverLogHistoryByJourneyDate").setString("deviceId", deviceId).setString("userId", userId).setString("fromDate", fromDate).setString("toDate", toDate).setParameter("journeyPurpose", journeyPurpose,Hibernate.STRING).list();
                } else {
                    driverLogHis = session.getNamedQuery("getDriverLogHistoryByExportedDate").setString("deviceId", deviceId).setString("userId", userId).setString("fromDate", fromDate).setString("toDate", toDate).setParameter("journeyPurpose", journeyPurpose,Hibernate.STRING).list();
                }
            }else if(journeyTypeId != null){
                if (filterFlag.equalsIgnoreCase("1")) {
                    driverLogHis = session.getNamedQuery("getDriverLogHistoryByJourneyDateAndTypeIDOnly").setString("deviceId", deviceId).setString("userId", userId).setString("fromDate", fromDate).setString("toDate", toDate).setString("journeyTypeId", journeyTypeId).list();
                } else {
                    driverLogHis = session.getNamedQuery("getDriverLogHistoryByExportedDateAndTypeIDOnly").setString("deviceId", deviceId).setString("userId", userId).setString("fromDate", fromDate).setString("toDate", toDate).setString("journeyTypeId", journeyTypeId).list();
                }
            }else{
                if (filterFlag.equalsIgnoreCase("1")) {
                    driverLogHis = session.getNamedQuery("getDriverLogHistoryByJourneyDates").setString("deviceId", deviceId).setString("userId", userId).setString("fromDate", fromDate).setString("toDate", toDate).list();
                } else {
                    driverLogHis = session.getNamedQuery("getDriverLogHistoryByExportedDates").setString("deviceId", deviceId).setString("userId", userId).setString("fromDate", fromDate).setString("toDate", toDate).list();
                }
                
            }
//            if (journeyTypeId==null) {
//                if (filterFlag.equals("1")) {
//                    driverLogHis = session.getNamedQuery("getDriverLogHistoryByJourneyDate").setString("deviceId", deviceId).setString("userId", userId).setString("fromDate", fromDate).setString("toDate", toDate).setParameter("journeyPurpose", journeyPurpose,Hibernate.STRING).list();
//                } else {
//                    driverLogHis = session.getNamedQuery("getDriverLogHistoryByExportedDate").setString("deviceId", deviceId).setString("userId", userId).setString("fromDate", fromDate).setString("toDate", toDate).setParameter("journeyPurpose", journeyPurpose,Hibernate.STRING).list();
//                }
//            } else {
//                if (filterFlag.equalsIgnoreCase("1")) {
//                    driverLogHis = session.getNamedQuery("getDriverLogHistoryByJourneyDateAndTypeID").setString("deviceId", deviceId).setString("userId", userId).setString("fromDate", fromDate).setString("toDate", toDate).setParameter("journeyPurpose", journeyPurpose,Hibernate.STRING).setString("journeyTypeId", journeyTypeId).list();
//                } else {
//                    driverLogHis = session.getNamedQuery("getDriverLogHistoryByExportedDateAndTypeID").setString("deviceId", deviceId).setString("userId", userId).setString("fromDate", fromDate).setString("toDate", toDate).setParameter("journeyPurpose", journeyPurpose,Hibernate.STRING).setString("journeyTypeId", journeyTypeId).list();
//                }
//            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }

        return driverLogHis;
    }

    public DriversLogItems gerLogItemsByDriversLogItemId(Session session, String driversLogItemId) {
        try {
            return (DriversLogItems) session.getNamedQuery("gerLogItemsByDriversLogItemId").setString("driversLogItemId", driversLogItemId).list().get(0);
        } catch (IndexOutOfBoundsException ae) {
            return null;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }

    }

    public boolean updateDriverslog(Session session, DriversLogItems logItem) {
        Transaction tx = session.beginTransaction();
        try {
            session.update(logItem);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public JourneyTypeTranslations getJourneyTypeIDByCountryID(Session session, int countryId, String journeyTypeId) {
        try {
            return (JourneyTypeTranslations) session.getNamedQuery("getJourneyTypeTranslationsByJourneyTypeID").setInteger("countryId", countryId).setString("journeyTypeId", journeyTypeId).uniqueResult();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }
    public List<JourneyTypeTranslations> getJourneyTypeTranslationsByJourneyTypeIDs(Session session, int countryId, List<String> journeyTypeId) {
        try {
            return session.getNamedQuery("getJourneyTypeTranslationsByJourneyTypeIDs").setInteger("countryId", countryId).setParameterList("journeyTypeId", journeyTypeId).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<DriversLogItems> getDriverLog(Session session, String driverUserId, String deviceId) {

        try {
            return session.getNamedQuery("getDriverLogByUserID").setString("driverUserId", driverUserId).setString("deviceId", deviceId).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<DriversLogItems> getDriverLogByUserIDandId(Session session, String driverUserId, String deviceId,String driversLogItemId) {

        try {
            return session.getNamedQuery("getDriverLogByUserIDandId").setString("driverUserId", driverUserId).setString("deviceId", deviceId).setString("driversLogItemId", driversLogItemId).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

//    @SuppressWarnings("unchecked")
    public List<DeviceUsers> getDeviceidByUserId(Session session, String userId) {
        try {
            return session.getNamedQuery("getDeviceIdByUserId").setString("userId", userId).list();
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }

    }

    public Device getdeviceNameByDeviceId(Session session, String deviceId, String userId) {

        try {
            List<Device> deviceList = session.getNamedQuery("getDeviceDetailByUserId").setString("userId", userId).setString("deviceId", deviceId).list();
            return deviceList.size()>0?deviceList.get(0):null;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }
    }
}
