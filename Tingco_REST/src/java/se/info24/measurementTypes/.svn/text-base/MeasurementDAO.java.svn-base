/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.measurementTypes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import se.info24.device.DeviceDAO;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.MeasurementData;
import se.info24.measurementjaxb.Data;
import se.info24.measurementjaxb.DataSeries;
import se.info24.measurementjaxb.DeviceMeasurementData;
import se.info24.measurementjaxb.MeasurementTypeTranslation;
import se.info24.measurementjaxb.TingcoMeasurementTypes;
import se.info24.pojo.Device;
import se.info24.pojo.DeviceTypeMeasurementTypes;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.MeasurementTypeTranslations;
import se.info24.pojo.MeasurementTypes;
import se.info24.pojo.MeasurementTypesInGroups;
import se.info24.pojo.ObjectUsageUnits;
import se.info24.restUtil.RestUtilDAO;
import se.info24.user.CountryDAO;
import se.info24.util.TCMUtil;

/**
 *
 * @author Hitha
 */
public class MeasurementDAO {

    public List<MeasurementTypeTranslations> getMeasurementTypeTranslations(String groupId, int countryId, Session session) {

        Criteria criteria = session.createCriteria(MeasurementTypeTranslations.class, "m");
        criteria.createAlias("m.measurementTypes", "mt", CriteriaSpecification.INNER_JOIN);
        criteria.add(Restrictions.eq("m.id.countryId", countryId));
        criteria.createAlias("mt.measurementTypesInGroupses", "g", CriteriaSpecification.INNER_JOIN);
        criteria.add(Restrictions.eq("g.id.groupId", groupId));
        criteria.addOrder(Order.asc("m.measurementTypeName"));
        return criteria.list();
    }

    public List<MeasurementTypesInGroups> getMeasurementTypesInGroups(String groupId, Session session) {
        return session.getNamedQuery("getMeasurementTypesInGroupsByGroupId").setString("groupId", groupId).list();
    }

    public List<Object> getMeasurementData(String dataCal, String reportId, String groupId, String fromDate, String toDate, String deviceId, Session ISMOperationsession) {
        ArrayList<String> groupIdList = new ArrayList<String>();
        groupIdList.add(groupId);
        return ISMOperationsession.getNamedQuery("getMeasurementData").setString("dataCal", dataCal).setString("measurementTypeId", reportId).setParameterList("groupId", groupIdList).setString("fromDate", fromDate).setString("toDate", toDate).setString("deviceId", deviceId).list();
    }

    public List<Device> getDeviceList(Object objectId, String groupId, Session session) {
        GroupDAO groupDAO = new GroupDAO();
        DeviceDAO deviceDAO = new DeviceDAO();
        List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
        Set<String> groupIdSet = groupDAO.getGroupIdsList(groupId, groupTrustsList);
        /**
         * SPLIT LIST SIZE 30 MAR 2014
         */
        List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupIdSet), 2000);
        List<Device> devicesList = new ArrayList<Device>();
        for (List<String> list : listsplit) {
            List<Device> devicesListTemp = deviceDAO.getDeviceByDeviceAndGroupIDList(new HashSet<String>(list), objectId, session);
            devicesList.addAll(devicesListTemp);
        }
        //return deviceDAO.getDeviceByDeviceAndGroupIDList(groupIdSet, objectId, session);
        return devicesList;
    }

    public List<Object> getMeasurementData(String dataCal, String reportId, String groupId, String fromDate, String toDate, String deviceId, String groupBy, Session session, Session ismSession) {
        List<Object> mdList = new ArrayList<Object>();
        ArrayList<String> groupIdList = new ArrayList<String>();
        groupIdList.add(groupId);
        if (groupBy.equalsIgnoreCase("year")) {
            mdList = session.getNamedQuery("getMeasurementDataByYear").setString("dataCal", dataCal).setString("measurementTypeId", reportId).setParameterList("groupId", groupIdList).setString("fromDate", fromDate).setString("toDate", toDate).setString("deviceId", deviceId).setMaxResults(5).list();
        } else if (groupBy.equalsIgnoreCase("month")) {
            mdList = session.getNamedQuery("getMeasurementDataByMonth").setString("dataCal", dataCal).setString("measurementTypeId", reportId).setParameterList("groupId", groupIdList).setString("fromDate", fromDate).setString("toDate", toDate).setString("deviceId", deviceId).setMaxResults(12).list();
        } else if (groupBy.equalsIgnoreCase("day")) {
            mdList = session.getNamedQuery("getMeasurementDataByDay").setString("dataCal", dataCal).setString("measurementTypeId", reportId).setParameterList("groupId", groupIdList).setString("fromDate", fromDate).setString("toDate", toDate).setString("deviceId", deviceId).setMaxResults(100).list();
        } else if (groupBy.equalsIgnoreCase("hour")) {
            mdList = session.getNamedQuery("getMeasurementDataByHour").setString("dataCal", dataCal).setString("measurementTypeId", reportId).setParameterList("groupId", groupIdList).setString("fromDate", fromDate).setString("toDate", toDate).setString("deviceId", deviceId).setMaxResults(24).list();
        }
        return mdList;
    }

    public List<MeasurementData> getMeasurementDatademo(String dataCal, String reportId, String groupId, String fromDate, String toDate, String deviceId, String groupBy, Session session, Session ismSession) {
        List<MeasurementData> mdList = new ArrayList<MeasurementData>();

        if (groupBy.equalsIgnoreCase("year")) {
            mdList = session.getNamedQuery("getMeasurementDataByYearDemo").setString("measurementTypeId", reportId).setString("groupId", groupId).setString("fromDate", fromDate).setString("toDate", toDate).setString("deviceId", deviceId).list();
        } else if (groupBy.equalsIgnoreCase("month")) {
            mdList = session.getNamedQuery("getMeasurementDataByMonthDemo").setString("measurementTypeId", reportId).setString("groupId", groupId).setString("fromDate", fromDate).setString("toDate", toDate).setString("deviceId", deviceId).list();
        } else if (groupBy.equalsIgnoreCase("day")) {
            mdList = session.getNamedQuery("getMeasurementDataByDayDemo").setString("measurementTypeId", reportId).setString("groupId", groupId).setString("fromDate", fromDate).setString("toDate", toDate).setString("deviceId", deviceId).list();
        } else if (groupBy.equalsIgnoreCase("hour")) {
            mdList = session.getNamedQuery("getMeasurementDataByHourDemo").setString("measurementTypeId", reportId).setString("groupId", groupId).setString("fromDate", fromDate).setString("toDate", toDate).setString("deviceId", deviceId).list();
        }
        return mdList;
    }

    public MeasurementTypeTranslations getMeasurementTypeTranslationsByCountryId(Session session, String measurementTypeId, int countryId) {
        return (MeasurementTypeTranslations) session.getNamedQuery("getMeasurementTypeTranslationByID").setString("measurementTypeId", measurementTypeId).setInteger("countryId", countryId).uniqueResult();
    }

    TingcoMeasurementTypes buildGetMeasurementDataDetailsForDevice(TingcoMeasurementTypes measurementTypes, TingcoMeasurementTypes tingcoMeasurement, Session session, Session ismSession) throws DatatypeConfigurationException, ParseException {
        CountryDAO countryDAO = new CountryDAO();
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        RestUtilDAO utilDAO = new RestUtilDAO();
        String deviceId = measurementTypes.getDeviceId();
        int countryId = Integer.valueOf(measurementTypes.getCountryId());
        String timePeriod = measurementTypes.getMeasurementDatas().getTimePeriod();
        String userId = measurementTypes.getUserId();
        List<String> measurementTypeIdList = new ArrayList<String>();
        GregorianCalendar gc = new GregorianCalendar();
        se.info24.measurementjaxb.MeasurementTypes measurementtype = measurementTypes.getMeasurementTypes();

        for (se.info24.measurementjaxb.MeasurementTypeTranslations mtts : measurementtype.getMeasurementTypeTranslations()) {
            for (MeasurementTypeTranslation mtt : mtts.getMeasurementTypeTranslation()) {
                measurementTypeIdList.add(mtt.getMeasurementTypeID());
            }
        }
        List<MeasurementTypes> measurementTypesList = getMeasurementTypes(measurementTypeIdList, session);
        if (!measurementTypesList.isEmpty()) {
            List<MeasurementData> measurementDataList = getMeasurementDataByIdsList(measurementTypeIdList, deviceId, timePeriod, measurementTypes, ismSession);
            String timeZoneId = RestUtilDAO.getUserTimeZones2byUserId(userId, session).getTimeZoneId();
            String timeZoneGMToffset = RestUtilDAO.getTimezoneById(timeZoneId, session).getTimeZoneGmtoffset();
            DeviceDAO deviceDAO = new DeviceDAO();
            String deviceName = deviceDAO.getDeviceById(deviceId, session).getDeviceName();
            int seriesId = 0;
            DeviceMeasurementData dmd = new DeviceMeasurementData();
            dmd.setDeviceID(deviceId);
            dmd.setDeviceName(deviceName);
            String language = null;
            ObjectUsageUnits unit = null;
            for (MeasurementTypes mt : measurementTypesList) {
                Set<MeasurementTypeTranslations> mttset = mt.getMeasurementTypeTranslationses();
                for (MeasurementTypeTranslations mtt : mttset) {
                    if (mtt.getId().getCountryId() == countryId) {
                        language = countryDAO.getCountryById(countryId, session).getLanguageCode();
                        unit = utilDAO.getObjectUsageUnitsById(mt.getObjectUsageUnits().getUsageUnitId(), session);
                        seriesId++;
                        DataSeries dataSeries = new DataSeries();
                        dataSeries.setSeriesID(seriesId);
                        dataSeries.setSeriesName(mtt.getMeasurementTypeName());
                        dataSeries.setLanguage(language);
                        dataSeries.setUnit(unit.getUsageUnitName());
                        int dataId = 1;
                        for (MeasurementData md : measurementDataList) {
                            if (md.getId().getMeasurementTypeId().equalsIgnoreCase(mt.getMeasurementTypeId())) {
                                Data data = new Data();
                                data.setID(dataId);
                                gc.setTimeInMillis(RestUtilDAO.convertGMTtoUserLocalTimeInLongFormat(timeZoneGMToffset, md.getDataItemTime()));
                                data.setTime(df.format(gc.getTime()));
                                data.setValue(String.valueOf(md.getMeasurementValue()));
                                dataSeries.getData().add(data);
                                dataId++;
                            }
                        }
                        dmd.getDataSeries().add(dataSeries);
                    }
                }
            }
            tingcoMeasurement.setDeviceMeasurementData(dmd);
        }
        return tingcoMeasurement;
    }



    TingcoMeasurementTypes buildMeasurementGraphforDevice(TingcoMeasurementTypes measurementTypes, TingcoMeasurementTypes tingcoMeasurement, Session session, Session ismSession) throws DatatypeConfigurationException, ParseException {
        CountryDAO countryDAO = new CountryDAO();
        RestUtilDAO utilDAO = new RestUtilDAO();
        String deviceId = measurementTypes.getDeviceId();
        int countryId = Integer.valueOf(measurementTypes.getCountryId());
        String timePeriod = measurementTypes.getMeasurementDatas().getTimePeriod();
        String userId = measurementTypes.getUserId();
        List<String> measurementTypeIdList = new ArrayList<String>();
        GregorianCalendar gc = new GregorianCalendar();
        se.info24.measurementjaxb.MeasurementTypes measurementtype = measurementTypes.getMeasurementTypes();

        for (se.info24.measurementjaxb.MeasurementTypeTranslations mtts : measurementtype.getMeasurementTypeTranslations()) {
            for (MeasurementTypeTranslation mtt : mtts.getMeasurementTypeTranslation()) {
                measurementTypeIdList.add(mtt.getMeasurementTypeID());
            }
        }
        List<MeasurementTypes> measurementTypesList = getMeasurementTypes(measurementTypeIdList, session);
        if (!measurementTypesList.isEmpty()) {
            List<MeasurementData> measurementDataList = getMeasurementDataByIdsList(measurementTypeIdList, deviceId, timePeriod, measurementTypes, ismSession);
            String timeZoneId = RestUtilDAO.getUserTimeZones2byUserId(userId, session).getTimeZoneId();
            String timeZoneGMToffset = RestUtilDAO.getTimezoneById(timeZoneId, session).getTimeZoneGmtoffset();
            DeviceDAO deviceDAO = new DeviceDAO();
            String deviceName = deviceDAO.getDeviceById(deviceId, session).getDeviceName();
            int seriesId = 0;
            DeviceMeasurementData dmd = new DeviceMeasurementData();
            dmd.setDeviceID(deviceId);
            dmd.setDeviceName(deviceName);
            Collections.reverse(measurementDataList);
            String language = null;
            ObjectUsageUnits unit = null;
            for (MeasurementTypes mt : measurementTypesList) {
                Set<MeasurementTypeTranslations> mttset = mt.getMeasurementTypeTranslationses();
                for (MeasurementTypeTranslations mtt : mttset) {
                    if (mtt.getId().getCountryId() == countryId) {
                        language = countryDAO.getCountryById(countryId, session).getLanguageCode();
                        unit = utilDAO.getObjectUsageUnitsById(mt.getObjectUsageUnits().getUsageUnitId(), session);
                        seriesId++;
                        DataSeries dataSeries = new DataSeries();
                        dataSeries.setSeriesID(seriesId);
                        dataSeries.setSeriesName(mtt.getMeasurementTypeName());
                        dataSeries.setLanguage(language);
                        dataSeries.setUnit(unit.getUsageUnitName());
                        int dataId = 1;
                        for (MeasurementData md : measurementDataList) {
                            if (md.getId().getMeasurementTypeId().equalsIgnoreCase(mt.getMeasurementTypeId())) {
                                Data data = new Data();
                                data.setID(dataId);
                                gc.setTimeInMillis(RestUtilDAO.convertGMTtoUserLocalTimeInLongFormat(timeZoneGMToffset, md.getDataItemTime()));
                                data.setTime(String.valueOf(gc.getTimeInMillis()));
                                data.setValue(String.valueOf(md.getMeasurementValue()));
                                dataSeries.getData().add(data);
                                dataId++;
                            }
                        }
                        dmd.getDataSeries().add(dataSeries);
                    }
                }
            }
            tingcoMeasurement.setDeviceMeasurementData(dmd);
        }
        return tingcoMeasurement;
    }


    List<MeasurementData> getMeasurementDataByIdsList(List<String> measurementTypeIdList, String deviceId, String timePeriod, TingcoMeasurementTypes measurementTypes, Session ismSession) throws DatatypeConfigurationException, ParseException {
        List<MeasurementData> mdlist = new ArrayList<MeasurementData>();
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        GregorianCalendar gc_diff = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
        long lMillis = gc.getTimeInMillis();
        Date lDate = new Date(lMillis);
        String gc_time = lFormat.format(lDate);
        if (timePeriod != null) {
            if (timePeriod.equalsIgnoreCase("100")) {
                mdlist = ismSession.getNamedQuery("getMeasurementDatabyIdandDeviceIdTop100").setParameterList("measurementTypeId", measurementTypeIdList).setString("deviceId", deviceId).setMaxResults(100).list();
            } else if (timePeriod.equalsIgnoreCase("hour")) {
                gc_diff.add(GregorianCalendar.HOUR, -1);
                long gc_diff_Millis = gc_diff.getTimeInMillis();
                Date gc_diff_lDate = new Date(gc_diff_Millis);
                String gc_diff_time = lFormat.format(gc_diff_lDate);
                mdlist = ismSession.getNamedQuery("getMeasurementDatabyIdandDeviceId").setParameterList("measurementTypeId", measurementTypeIdList).setString("deviceId", deviceId).setString("fromDate", gc_diff_time).setString("toDate", gc_time).setMaxResults(5000).list();
            } else if (timePeriod.equalsIgnoreCase("day")) {
                gc_diff.add(GregorianCalendar.DATE, -1);
                long gc_diff_Millis = gc_diff.getTimeInMillis();
                Date gc_diff_lDate = new Date(gc_diff_Millis);
                String gc_diff_time = lFormat.format(gc_diff_lDate);
                mdlist = ismSession.getNamedQuery("getMeasurementDatabyIdandDeviceId").setParameterList("measurementTypeId", measurementTypeIdList).setString("deviceId", deviceId).setString("fromDate", gc_diff_time).setString("toDate", gc_time).setMaxResults(5000).list();
            } else if (timePeriod.equalsIgnoreCase("week")) {
                gc_diff.add(GregorianCalendar.DATE, -7);
                long gc_diff_Millis = gc_diff.getTimeInMillis();
                Date gc_diff_lDate = new Date(gc_diff_Millis);
                String gc_diff_time = lFormat.format(gc_diff_lDate);
                mdlist = ismSession.getNamedQuery("getMeasurementDatabyIdandDeviceId").setParameterList("measurementTypeId", measurementTypeIdList).setString("deviceId", deviceId).setString("fromDate", gc_diff_time).setString("toDate", gc_time).setMaxResults(5000).list();
            } else if (timePeriod.equalsIgnoreCase("month")) {
                gc_diff.add(GregorianCalendar.MONTH, -1);
                long gc_diff_Millis = gc_diff.getTimeInMillis();
                Date gc_diff_lDate = new Date(gc_diff_Millis);
                String gc_diff_time = lFormat.format(gc_diff_lDate);
                mdlist = ismSession.getNamedQuery("getMeasurementDatabyIdandDeviceId").setParameterList("measurementTypeId", measurementTypeIdList).setString("deviceId", deviceId).setString("fromDate", gc_diff_time).setString("toDate", gc_time).setMaxResults(5000).list();
            }
        } else if (measurementTypes.getMeasurementDatas().getFromDate() != null && measurementTypes.getMeasurementDatas().getToDate() != null) {
            String gc_diff_time = measurementTypes.getMeasurementDatas().getFromDate();
            gc_time = measurementTypes.getMeasurementDatas().getToDate();
            mdlist = ismSession.getNamedQuery("getMeasurementDatabyIdandDeviceId").setParameterList("measurementTypeId", measurementTypeIdList).setString("deviceId", deviceId).setString("fromDate", gc_diff_time).setString("toDate", gc_time).setMaxResults(5000).list();
        }
        ismSession.flush();
        ismSession.clear();
        return mdlist;
    }

    List<String> getMeasurementTypeIdsList(List<DeviceTypeMeasurementTypes> deviceTypemeasurementTypesList) {
        List<String> measurementTypeIdsList = new ArrayList<String>();
        for (DeviceTypeMeasurementTypes dtmt : deviceTypemeasurementTypesList) {
            measurementTypeIdsList.add(dtmt.getId().getMeasurementTypeId());
        }
        return measurementTypeIdsList;
    }

    List<MeasurementTypeTranslations> getMeasurementTypeTranslationsbyIds(List<String> measurementTypeIdsList, int countryId, Session session) {
        return session.getNamedQuery("getMeasurementTypeTranslationsbyIdsList").setParameterList("measurementTypeId", measurementTypeIdsList).setInteger("countryId", countryId).list();
    }

    List<MeasurementTypes> getMeasurementTypes(List<String> measurementTypeIdList, Session session) {
        return session.getNamedQuery("getMeasurementTypesbyIdsList").setParameterList("measurementTypeId", measurementTypeIdList).list();
    }

    public List<DeviceTypeMeasurementTypes> getDeviceTypeMeasurementTypes(String deviceTypeId, Session session) {
        return session.getNamedQuery("getDeviceTypeMeasurementTypesbyDeviceTypeId").setString("deviceTypeId", deviceTypeId).list();
    }

    public MeasurementTypesInGroups getMeasurementTypesInGroupsByID(Session session, String groupId, String measurementTypeId) {
        return (MeasurementTypesInGroups) session.getNamedQuery("getMeasurementTypesInGroupsByID").setParameter("groupId", groupId).setParameter("measurementTypeId", measurementTypeId).uniqueResult();
    }

    Object getDeviceTypeMeasurementTypesByID(String deviceTypeId, String measurementTypeId, Session session) {
        return session.getNamedQuery("getDeviceTypeMeasurementTypesByID").setString("deviceTypeId", deviceTypeId).setString("measurementTypeId", measurementTypeId).uniqueResult();
    }

    void saveDeviceTypeMeasurementTypes(DeviceTypeMeasurementTypes dtmt, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            tx.begin();
            session.saveOrUpdate(dtmt);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    void deleteDeviceTypeMeasurementTypes(DeviceTypeMeasurementTypes dtmt, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            tx.begin();
            session.delete(dtmt);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }
}
