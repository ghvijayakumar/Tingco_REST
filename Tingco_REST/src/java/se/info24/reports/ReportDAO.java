/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.reports;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import se.info24.devicejaxbPost.Device;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.ObjectUsageRecords;
import se.info24.ismOperationsPojo.TransactionResult;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.GroupWeekdays;
import se.info24.pojo.MeasurementTypes;
import se.info24.pojo.ObjectProductVariantCounters;
import se.info24.objectpojo.ObjectUsageSummaryReport;
import se.info24.pojo.ObjectUsageTypes;
import se.info24.pojo.ObjectUsageUnits;
import se.info24.pojo.Ogmdevice;
import se.info24.pojo.ReportSendScheduleSettings;
import se.info24.pojo.ReportSendSchedules;
import se.info24.pojo.ReportTranslations;
import se.info24.pojo.Reports;
import se.info24.pojo.WeekdayTranslations;
import se.info24.reportsjaxb.InstantMessageLog;
import se.info24.restUtil.RestUtilDAO;
import se.info24.util.TCMUtil;

/**
 *
 * @author Hitha
 */
public class ReportDAO {

//    GroupDAO groupDAO;
//
//    public ReportDAO() {
//        groupDAO = new GroupDAO();
//    }
    public List<ReportSendSchedules> getReportSendSchedules(String reportCode, String groupId, String measurementTypeId, Session session) {
        List<Reports> reports = session.getNamedQuery("getReports").setString("reportCode", reportCode).list();
        List<ReportSendSchedules> reportList = new ArrayList<ReportSendSchedules>();
        GroupDAO groupDAO = new GroupDAO();
        List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
        ArrayList<String> groupIdList = new ArrayList<String>();
        groupIdList.add(groupId);
        for (GroupTrusts gtr : groupTrustsList) {
            groupIdList.add(gtr.getId().getGroupId());
        }
        if (!reports.isEmpty()) {
            /**
             * SPLIT LIST SIZE 30 MAR 2014
             */
            List<List<String>> listsplit = TCMUtil.splitStringList(groupIdList, 2000);
            for (List<String> list : listsplit) {
                List<ReportSendSchedules> reportListTemp = session.getNamedQuery("getReportSendSchedules").setString("reportId", reports.get(0).getReportId()).setParameterList("groupId", list).setString("measurementTypeId", measurementTypeId).list();
                reportList.addAll(reportListTemp);
            }
        //reportList = session.getNamedQuery("getReportSendSchedules").setString("reportId", reports.get(0).getReportId()).setParameterList("groupId", groupIdList).setString("measurementTypeId", measurementTypeId).list();
        }
        return reportList;
    }

    public List<ReportSendSchedules> getReportSendSchedules(Session session) {
        return session.getNamedQuery("getAllReportSendSchedules").list();
    }

    public Reports getReports(Session session, String reportId) {
        return (Reports) session.getNamedQuery("getReportsByReportId").setString("reportId", reportId).uniqueResult();
    }

    public List<ReportSendScheduleSettings> getReportSendSchedulesSettings(Session session, String reportSendScheduleId) {
        return session.getNamedQuery("getReportSendScheduleSettings").setString("reportSendScheduleId", reportSendScheduleId).list();
    }

    public List<ReportTranslations> getReportTranslations(Session session, String reportId) {
        return session.getNamedQuery("getReportTranslationsByReportId").setString("reportId", reportId).list();
    }

    public boolean addReports(ReportSendSchedules rss, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(rss);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public List<GroupWeekdays> getGroupWeekdays(String weekdayID, Session session) {
        return session.getNamedQuery("getGroupWeekDaysById").setString("weekDayId", weekdayID).list();
    }

    public int getReportTemplateSettings(String reportId, String settingName, Session session) {
        return session.getNamedQuery("getReportTemplateSettings").setString("reportId", reportId).setString("settingName", settingName).list().size();
    }

    public boolean addReportsSendScheduleSettings(ReportSendScheduleSettings rsss, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(rsss);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public boolean deleteReportSendSchedules(Session session, String reportSendScheduleId) {
        Transaction tx = null;
        boolean result = false;
        try {
            List<ReportSendScheduleSettings> reportSettingsList = session.getNamedQuery("getReportSendScheduleSettings").setString("reportSendScheduleId", reportSendScheduleId).list();
            if (!reportSettingsList.isEmpty()) {
                for (ReportSendScheduleSettings rsss : reportSettingsList) {
                    tx = session.beginTransaction();
                    session.delete(rsss);
                    tx.commit();
                }
                result = true;
            }
            List<ReportSendSchedules> reportList = session.getNamedQuery("getReportSendSchedulesById").setString("reportSendScheduleId", reportSendScheduleId).list();
            if (!reportList.isEmpty()) {
                for (ReportSendSchedules r : reportList) {
                    tx = session.beginTransaction();
                    session.delete(r);
                    tx.commit();
                }
                result = true;
            }
            return result;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tx.rollback();
            return false;
        }
    }

    public WeekdayTranslations getWeekDaysById(int countryId, String weekdayId, Session session) {
        try {
            return (WeekdayTranslations) session.getNamedQuery("getWeekDaysById").setInteger("countryId", countryId).setString("weekdayId", weekdayId).uniqueResult();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public GroupWeekdays getGroupWeekDaysByValue(Session session, int weekdayValue, String groupId) {
        try {
            return (GroupWeekdays) session.getNamedQuery("getGroupWeekDaysByValue").setString("groupId", groupId).setInteger("weekdayValue", weekdayValue).uniqueResult();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }

    }

    public List<Reports> getReportsByGroupId(Session session, String groupId) {
        GroupDAO groupDAO = new GroupDAO();
        List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
        ArrayList<String> groupIdList = new ArrayList<String>();
        groupIdList.add(groupId);
        for (GroupTrusts gtr : groupTrustsList) {
            groupIdList.add(gtr.getId().getGroupId());
        }
        List<Reports> reportsList = new ArrayList<Reports>();
        /**
         * SPLIT LIST SIZE 30 MAR 2014
         */
        List<List<String>> listsplit = TCMUtil.splitStringList(groupIdList, 2000);
        for (List<String> list : listsplit) {
            List<Reports> reportsListTemp = session.getNamedQuery("getReportsByGroupId").setParameterList("groupId", list).list();
            reportsList.addAll(reportsListTemp);
        }
        //return session.getNamedQuery("getReportsByGroupId").setParameterList("groupId", groupIdList).list();
        return reportsList;
    }

    public ReportTranslations getReportTranslations(Session session, String reportId, int countryId) {
        try {
            return (ReportTranslations) session.getNamedQuery("getReportTranslationsById").setString("reportId", reportId).setInteger("countryId", countryId).uniqueResult();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }

    }

    public List<ObjectUsageUnits> getAllObjectUsageUnits(Session session) {
        return session.getNamedQuery("getAllObjectUsageUnits").list();
    }

    public ObjectUsageRecords getUsageLogDetailsById(Session session, String usageRecordId) {
        return (ObjectUsageRecords) session.getNamedQuery("getUsageLogDetailsById").setString("usageRecordId", usageRecordId).uniqueResult();
    }

    public boolean saveObjectUsageRecords(Session session, ObjectUsageRecords objectUsageRecords) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(objectUsageRecords);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public boolean deleteObjectUsageRecords(Session session, ObjectUsageRecords objectUsageRecords) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(objectUsageRecords);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    List<Object> getTransactionResultBySearch(String searchString, String loggedinUserGroupId, String fromdate, String todate, Integer countryid, Session ismOperationsSession, Session session, String timeZoneGMToffset) throws ParseException {
        GroupDAO groupDAO = new GroupDAO();
        List<Object> transactionResultList = new ArrayList<Object>();
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        String gc_time = lFormat.format(gc.getTime());
        String gc_diff_time = null;

        gc_diff_time = lFormat.format(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, lFormat.parse(fromdate)));
        gc_time = lFormat.format(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, lFormat.parse(todate)));

        List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(loggedinUserGroupId, session);
        Set<String> groupIdsList = groupDAO.getGroupIdsList(loggedinUserGroupId, groupTrustsList);
        StringBuffer queryString = new StringBuffer("select providerId, providerName, count(amount), sum(amount), currencyId from TransactionResult where deviceGroupId in (:devicegroupIdsList) and isPurchase = 1");

        List<String> searchGroupIdsList = new ArrayList<String>();
        if (searchString != null) {
            List<GroupTranslations> groupTranslationsList = groupDAO.getGroupTransByGroupIdandSearchString(groupIdsList, searchString, countryid, session);
            if (!groupTranslationsList.isEmpty()) {
                searchGroupIdsList = groupDAO.getGroupidsListFromGroupTranslations(groupTranslationsList);
                queryString.append(" and deviceGroupId in (:searchGroupIdsList)");
            } else {
                return transactionResultList;
            }
        }

//        List<String> userGroupIdsList = new ArrayList<String>();
//        if (deviceXML.getUserGroupName() != null) {
//            List<GroupTranslations> groupTranslationsList = groupDAO.getGroupTransByGroupIdandSearchString(groupIdsList, deviceXML.getUserGroupName(), deviceXML.getCountryID().getValue(), session);
//            if (!groupTranslationsList.isEmpty()) {
//                userGroupIdsList = groupDAO.getGroupidsListFromGroupTranslations(groupTranslationsList);
//                queryString.append(" and userGroupId in (:userGroupIdsList)");
//            } else {
//                return transactionResultList;
//            }
//        }

//        List<String> deviceIdsList = new ArrayList<String>();
//        if (deviceXML.getObjectGroupName() != null) {
//            Criteria criteria = session.createCriteria(Ogmdevice.class, "ogm");
//            criteria.createAlias("ogm.objectGroups", "og", CriteriaSpecification.INNER_JOIN);
//            criteria.add(Restrictions.in("og.groupId", groupIdsList));
//            criteria.createAlias("og.objectGroupTranslationses", "ogt", CriteriaSpecification.INNER_JOIN);
//            criteria.add(Restrictions.ilike("ogt.objectGroupName", deviceXML.getObjectGroupName(), MatchMode.ANYWHERE));
//            criteria.add(Restrictions.eq("ogt.id.countryId", deviceXML.getCountryID().getValue()));
//            criteria.setProjection(Projections.distinct(Projections.property("ogm.id.deviceId")));
//            deviceIdsList = criteria.list();
//            if (!deviceIdsList.isEmpty()) {
//                queryString.append(" and deviceId in (:deviceIdsList)");
//            } else {
//                return transactionResultList;
//            }
//        }

//        if (deviceXML.getDeviceName() != null) {
//            if (deviceXML.getDeviceName().length() == 36) {
//                queryString.append(" and deviceId = '" + deviceXML.getDeviceName() + "' ");
//            } else {
//                queryString.append(" and deviceName like '%" + deviceXML.getDeviceName() + "%' ");
//            }
//        }

        queryString.append(" and transactionStartTime between '" + gc_diff_time + "' and '" + gc_time + "' group by providerId, providerName, currencyId order by providerName ");
        Query query = ismOperationsSession.createQuery(queryString.toString());
        query.setParameterList("devicegroupIdsList", groupIdsList);
        if (!searchGroupIdsList.isEmpty()) {
            query.setParameterList("searchGroupIdsList", searchGroupIdsList);
        }
//        if (!userGroupIdsList.isEmpty()) {
//            query.setParameterList("userGroupIdsList", userGroupIdsList);
//        }
//        if (!deviceIdsList.isEmpty()) {
//            query.setParameterList("deviceIdsList", deviceIdsList);
//        }
        transactionResultList = query.list();
        return transactionResultList;
    }

    List<ObjectProductVariantCounters> getObjectProductVariantCountersByObjectID(String deviceId, Session session, int maxResult) {
        return session.getNamedQuery("getObjectProductVariantCountersByObjectID").setString("objectId", deviceId).setMaxResults(maxResult).list();
    }

    public List<ObjectProductVariantCounters> getObjectProductVariantCountersByObjectID(String deviceId, Session session) {
        return session.getNamedQuery("getObjectProductVariantCountersByObjectID").setString("objectId", deviceId).list();
    }

    List<ObjectUsageTypes> getAllObjectUsageTypesOrderByUsageTypeName(Session session) {
        return session.getNamedQuery("getAllObjectUsageTypesOrderByUsageTypeName").list();
    }

    public List<ObjectUsageSummaryReport> getUsageChartReport(se.info24.devicejaxbPost.Device deviceXML, String groupingBy, Session session) {
        List<ObjectUsageSummaryReport> usageChartReportList = new ArrayList<ObjectUsageSummaryReport>();
        StringBuffer queryString = new StringBuffer();
        se.info24.devicejaxbPost.ObjectUsageRecords objectUsageRecordsjaxb = deviceXML.getObjectUsageRecords().get(0);
        if (groupingBy.equalsIgnoreCase("Month")) {
            queryString.append("SELECT DATENAME(MONTH, dataItemTime) as groupedBy, SUM(measurementvalue) as measurementValue ");
        } else if (groupingBy.equalsIgnoreCase("Week")) {
            queryString.append("SELECT DATEPART(WK, dataItemTime) as groupedBy, SUM(measurementvalue) as measurementValue ");
        } else if (groupingBy.equalsIgnoreCase("Day")) {
            queryString.append("SELECT cast(DATEPART(DD, dataitemtime) as varchar)+' '+cast(DATENAME(MONTH, DataItemTime) as varchar) as groupedBy, SUM(measurementvalue) as measurementValue ");
        } else if (groupingBy.equalsIgnoreCase("Hour")) {
            queryString.append("SELECT DATEPART(HH, dataitemtime) as groupedBy, SUM(measurementvalue) as measurementValue ");
        }

        queryString.append(" from ISMOperations.dbo.MeasurementData as md inner join device as d on md.objectId = d.deviceId inner join Groups as g on d.groupId " +
                "= g.groupId and g.groupid in (" + TCMUtil.getGroupTrust(deviceXML.getGroupID().getValue()) + ") " +
                "inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.countryId = " + deviceXML.getCountryID().getValue() + " " +
                "inner join Addresses as addr on d.addressId = addr.addressId inner join country as cou on addr.countryId = cou.countryId " +
                "and md.measurementTypeId = '" + objectUsageRecordsjaxb.getObjectTypeID() + "' ");
        queryString.append("and md.dataItemTime between '" + objectUsageRecordsjaxb.getUsageStartTimeTCMV3() + "' and '" + objectUsageRecordsjaxb.getUsageStopTimeTCMV3() + "' ");

        if (deviceXML.getGroupID().getGroupName() != null) {
            if (TCMUtil.isValidUUID(deviceXML.getGroupID().getGroupName())) {
                queryString.append("and gts.groupId = '" + deviceXML.getGroupID().getGroupName() + "' ");
            } else {
                queryString.append("and gts.groupName like '%" + deviceXML.getGroupID().getGroupName() + "%' ");
            }
        }

        if (deviceXML.getOptionalCountryID() != null) {
            if (deviceXML.getOptionalCountryID().matches("[0-9]+")) {
                queryString.append(" and cou.countryId = " + Integer.valueOf(deviceXML.getOptionalCountryID()) + " ");
            } else {
                queryString.append(" and cou.countryName like '%" + deviceXML.getOptionalCountryID() + "%' ");
            }
        }

        if (deviceXML.getObjectGroupName() != null) {
            queryString.append(" inner join Ogmdevice as ogm on d.deviceId = ogm.deviceId inner join ObjectGroups as og on ogm.objectGroupId = og.objectGroupId " +
                    " inner join ObjectGroupTranslations as ogt on og.objectGroupId = ogt.objectGroupId and ogt.countryId = " + deviceXML.getCountryID().getValue() + " ");
            if (TCMUtil.isValidUUID(deviceXML.getObjectGroupName())) {
                queryString.append("and ogt.objectGroupId = '" + deviceXML.getObjectGroupName() + "' ");
            } else {
                queryString.append("and ogt.objectGroupName like '%" + deviceXML.getObjectGroupName() + "%' ");
            }
        }

        if (deviceXML.getDeviceName() != null) {
            if (TCMUtil.isValidUUID(deviceXML.getDeviceName())) {
                queryString.append(" and d.deviceId = '" + deviceXML.getDeviceName() + "' ");
            } else {
                queryString.append(" and (d.deviceName like '%" + deviceXML.getDeviceName() + "%' or d.deviceName2 like '%" + deviceXML.getDeviceName() + "%') ");
            }
        }

        if (groupingBy.equalsIgnoreCase("Month")) {
            queryString.append("GROUP BY year(dataitemtime), month(dataitemtime), DATENAME(MONTH, dataitemtime) ");
            queryString.append("ORDER BY year(dataitemtime), month(dataitemtime) ");
        } else if (groupingBy.equalsIgnoreCase("Week")) {
            queryString.append("GROUP BY year(dataitemtime), DATEPART(WK, dataitemtime) ");
            queryString.append("ORDER BY year(dataitemtime), DATEPART(WK, dataitemtime) ");
        } else if (groupingBy.equalsIgnoreCase("Day")) {
            queryString.append("GROUP BY year(dataitemtime), month(dataitemtime), DATENAME(MONTH, DataItemTime), DATEPART(DD, dataitemtime) ");
            queryString.append("ORDER BY year(dataitemtime), month(dataitemtime), DATEPART(DD, dataitemtime), DATENAME(MONTH, DataItemTime) ");
        } else if (groupingBy.equalsIgnoreCase("Hour")) {
            queryString.append("GROUP BY year(dataitemtime), month(dataitemtime), DATENAME(MONTH, DataItemTime), DATEPART(HH, dataitemtime)  ");
            queryString.append("ORDER BY year(dataitemtime), month(dataitemtime), DATENAME(MONTH, DataItemTime), DATEPART(HH, dataitemtime) ");
        }
        SQLQuery query = session.createSQLQuery(queryString.toString());

        query.addScalar("groupedBy", Hibernate.STRING);
        query.addScalar("measurementValue", Hibernate.STRING);
        query.setResultTransformer(Transformers.aliasToBean(ObjectUsageSummaryReport.class));
        System.out.println(queryString);
        usageChartReportList = query.list();
        return usageChartReportList;
    }

    public List<ObjectUsageSummaryReport> getChargingStatisticsReport(se.info24.devicejaxbPost.Device deviceXML, String groupingBy, Session session) {
        List<ObjectUsageSummaryReport> usageChartReportList = new ArrayList<ObjectUsageSummaryReport>();
        StringBuffer queryString = new StringBuffer();
        se.info24.devicejaxbPost.ObjectUsageRecords objectUsageRecordsjaxb = deviceXML.getObjectUsageRecords().get(0);
        if (groupingBy.equalsIgnoreCase("Month")) {
            queryString.append("SELECT DATENAME(MONTH, usageStartTime) as groupedBy, SUM(CASE WHEN ISNUMERIC(usageVolume) = 1 THEN CAST(usageVolume AS FLOAT) ELSE 0 END) as measurementValue, usageUnitName ");
        } else if (groupingBy.equalsIgnoreCase("Week")) {
            queryString.append("SELECT DATEPART(WK, usageStartTime) as groupedBy, SUM(CASE WHEN ISNUMERIC(usageVolume) = 1 THEN CAST(usageVolume AS FLOAT) ELSE 0 END) as measurementValue, usageUnitName ");
        } else if (groupingBy.equalsIgnoreCase("Day")) {
            queryString.append("SELECT cast(DATEPART(DD, usageStartTime) as varchar)+' '+cast(DATENAME(MONTH, usageStartTime) as varchar) as groupedBy, SUM(CASE WHEN ISNUMERIC(usageVolume) = 1 THEN CAST(usageVolume AS FLOAT) ELSE 0 END) as measurementValue, usageUnitName ");
        } else if (groupingBy.equalsIgnoreCase("Hour")) {
            queryString.append("SELECT DATEPART(HH, usageStartTime) as groupedBy, SUM(CASE WHEN ISNUMERIC(usageVolume) = 1 THEN CAST(usageVolume AS FLOAT) ELSE 0 END) as measurementValue, usageUnitName ");
        }

        queryString.append(" from ISMOperations.dbo.ObjectUsageRecords as our where our.usedByUserAlias = '"+deviceXML.getObjectUsageRecords().get(0).getUsedByUserAlias()+"' ");
               queryString.append(" and our.usageTypeId in ('7E596193-4755-4016-A174-FDF7D9EEC7D3', 'FE003EFA-F370-4FFB-9423-0269426800BA', 'CDEE17C1-D5FD-442D-A964-8DBA5F524AE3') ");
        queryString.append("and our.usageStartTime between '" + objectUsageRecordsjaxb.getUsageStartTimeTCMV3() + "' and '" + objectUsageRecordsjaxb.getUsageStopTimeTCMV3() + "' ");

        if (groupingBy.equalsIgnoreCase("Month")) {
            queryString.append("GROUP BY year(usageStartTime), month(usageStartTime), DATENAME(MONTH, usageStartTime), usageUnitName ");
            queryString.append("ORDER BY year(usageStartTime), month(usageStartTime) ");
        } else if (groupingBy.equalsIgnoreCase("Week")) {
            queryString.append("GROUP BY year(usageStartTime), DATEPART(WK, usageStartTime), usageUnitName ");
            queryString.append("ORDER BY year(usageStartTime), DATEPART(WK, usageStartTime) ");
        } else if (groupingBy.equalsIgnoreCase("Day")) {
            queryString.append("GROUP BY year(usageStartTime), month(usageStartTime), DATENAME(MONTH, usageStartTime), DATEPART(DD, usageStartTime), usageUnitName ");
            queryString.append("ORDER BY year(usageStartTime), month(usageStartTime), DATEPART(DD, usageStartTime), DATENAME(MONTH, usageStartTime) ");
        } else if (groupingBy.equalsIgnoreCase("Hour")) {
            queryString.append("GROUP BY year(usageStartTime), month(usageStartTime), DATENAME(MONTH, usageStartTime), DATEPART(HH, usageStartTime), usageUnitName  ");
            queryString.append("ORDER BY year(usageStartTime), month(usageStartTime), DATENAME(MONTH, usageStartTime), DATEPART(HH, usageStartTime) ");
        }
        SQLQuery query = session.createSQLQuery(queryString.toString());

        query.addScalar("groupedBy", Hibernate.STRING);
        query.addScalar("measurementValue", Hibernate.STRING);
        query.addScalar("usageUnitName", Hibernate.STRING);
        query.setResultTransformer(Transformers.aliasToBean(ObjectUsageSummaryReport.class));
        System.out.println(queryString);
        usageChartReportList = query.list();
        return usageChartReportList;
    }

    List<Object> getgetSMSLogDetails(InstantMessageLog instantMessageLog, String timeZoneGMToffset, Session mOperationSession) {
        List<Object> objects = new ArrayList<Object>();
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        String gc_time = lFormat.format(gc.getTime());
        GregorianCalendar gc_diff = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        String gc_diff_time = null;
        try {
            if (instantMessageLog.getTimePeriod() != null) {
                if (instantMessageLog.getTimePeriod().equalsIgnoreCase("hour")) {
                    gc_diff.add(GregorianCalendar.HOUR, -1);
                    gc_diff_time = lFormat.format(gc_diff.getTime());
                } else if (instantMessageLog.getTimePeriod().equalsIgnoreCase("day")) {
                    gc_diff.add(GregorianCalendar.DATE, -1);
                    gc_diff_time = lFormat.format(gc_diff.getTime());
                } else if (instantMessageLog.getTimePeriod().equalsIgnoreCase("week")) {
                    gc_diff.add(GregorianCalendar.DATE, -7);
                    gc_diff_time = lFormat.format(gc_diff.getTime());
                } else if (instantMessageLog.getTimePeriod().equalsIgnoreCase("month")) {
                    gc_diff.add(GregorianCalendar.MONTH, -1);
                    gc_diff_time = lFormat.format(gc_diff.getTime());
                }
            } else if (instantMessageLog.getFromDate() != null && instantMessageLog.getToDate() != null) {

                gc_diff.setTime(lFormat.parse(instantMessageLog.getFromDate()));
                gc_diff.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc_diff.getTime()));
                gc_diff_time = lFormat.format(gc_diff.getTime());

                gc.setTime(lFormat.parse(instantMessageLog.getToDate()));
                gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
                gc_time = lFormat.format(gc.getTime());
            }
            StringBuffer queryString = new StringBuffer("select IMLogID from ISMOperations.dbo.InstantMessageLog where IMLogID is not null ");
            boolean flag = false;
            boolean flagdevice = false;
            if (instantMessageLog.getDeviceID() != null) {
//                if (!flag) {
//                    flag = true;
//                    queryString.append(" where ");
//                } else {
//                    queryString.append(" and ");
//                }
                if (TCMUtil.isValidUUID(instantMessageLog.getDeviceID())) {
//                    instantMessageLog.getGroupId()
                    queryString.append(" and DeviceID in (select DeviceID from ISM.dbo.Device where DeviceID = '" + instantMessageLog.getDeviceID() + "' and GroupID in (" + TCMUtil.getGroupTrust(instantMessageLog.getGroupId()) + ")  )");
                } else {
                    queryString.append(" and DeviceID in (select DeviceID from ISM.dbo.Device where (DeviceName  like '%" + instantMessageLog.getDeviceID() + "%' or DeviceDescription like '%" + instantMessageLog.getDeviceID() + "%') and GroupID in (" + TCMUtil.getGroupTrust(instantMessageLog.getGroupId()) + ")  )");
                }
                flagdevice = true;
            }
            if (instantMessageLog.getIMText() != null) {
//                if (!flag) {
//                    flag = true;
//                    queryString.append(" where ");
//                } else {
//                    queryString.append(" and ");
//                }
                queryString.append(" and IMText like '%" + instantMessageLog.getIMText() + "%'");
            }
            if (instantMessageLog.getIMReceiver() != null) {
//                if (!flag) {
//                    flag = true;
//                    queryString.append(" where ");
//                } else {
//                    queryString.append(" and ");
//                }
                queryString.append(" and IMReceiver like '%" + instantMessageLog.getIMReceiver() + "%' ");
            }
            if (instantMessageLog.getIMSenderOrIMReceiver() != null) {
//                if (!flag) {
//                    flag = true;
//                    queryString.append(" where ");
//                } else {
//                    queryString.append(" and ");
//                }
                queryString.append(" and (IMSender like '%" + instantMessageLog.getIMSenderOrIMReceiver() + "%' or IMReceiver like '%" + instantMessageLog.getIMSenderOrIMReceiver() + "%' )");
            }
            if (instantMessageLog.getIMSender() != null) {
//                if (!flag) {
//                    flag = true;
//                    queryString.append(" where ");
//                } else {
//                    queryString.append(" and ");
//                }
                queryString.append(" and IMSender like '%" + instantMessageLog.getIMSender() + "%' ");
            }

//            if (!flag) {
            //on request of.Net Team(Devi) we have given the trusted group on the startup.
            queryString.append(" and DeviceID in (select DeviceID from ISM.dbo.Device where GroupID in  (" + TCMUtil.getGroupTrust(instantMessageLog.getGroupId()) + ") )");
//            } else if (!flagdevice) {
//                queryString.append(" and DeviceID in (select DeviceID from ISM.dbo.Device where GroupID in  ("+TCMUtil.getGroupTrust( instantMessageLog.getGroupId())+") )");
//            }
            if (instantMessageLog.getTimePeriod() != null) {
                if (instantMessageLog.getTimePeriod().equalsIgnoreCase("100")) {
                    queryString.append(" order by CreatedDate desc");
                    TCMUtil.loger(getClass().getName(), queryString.toString(), "Info");
                    SQLQuery query = mOperationSession.createSQLQuery(queryString.toString());
                    query.addScalar("IMLogID", Hibernate.STRING);
                    objects = query.setMaxResults(100).list();
                } else {
                    queryString.append(" and CreatedDate between '" + gc_diff_time + "' and '" + gc_time + "' order by CreatedDate desc");
                    TCMUtil.loger(getClass().getName(), queryString.toString(), "Info");
                    SQLQuery query = mOperationSession.createSQLQuery(queryString.toString());
                    query.addScalar("IMLogID", Hibernate.STRING);
                    objects = query.setMaxResults(500).list();
                }
            } else {
                queryString.append(" and CreatedDate between '" + gc_diff_time + "' and '" + gc_time + "' order by CreatedDate desc");
                TCMUtil.loger(getClass().getName(), queryString.toString(), "Info");
                SQLQuery query = mOperationSession.createSQLQuery(queryString.toString());
                query.addScalar("IMLogID", Hibernate.STRING);
                objects = query.setMaxResults(500).list();
            }
        } catch (ParseException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objects;
    }

    public List<String> converObjectToString(List<Object> deviceList) {
        List<String> devicessSet = new ArrayList<String>();
        for (Iterator itr = deviceList.iterator(); itr.hasNext();) {
            devicessSet.add(itr.next().toString());
        }
        return devicessSet;
    }

    public List<se.info24.ismOperationsPojo.InstantMessageLog> getInstantMessageLogByIds(Session session, List<String> imlogId) {
        return session.getNamedQuery("getInstantMessageLogByIds").setParameterList("imlogId", imlogId).list();
    }
    
    public Object getMeasurementTypesById(Session session, String measurementTypeId) {
        return session.getNamedQuery("getMeasurementTypesById").setString("measurementTypeId", measurementTypeId).uniqueResult();
    }

}
