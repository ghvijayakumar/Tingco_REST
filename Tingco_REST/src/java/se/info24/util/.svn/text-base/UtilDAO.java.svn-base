/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import se.info24.ismOperationsPojo.ObjectLog;
import se.info24.restUtil.RestUtilDAO;
import se.info24.utiljaxb.ObjectLogs;
import se.info24.utiljaxb.TingcoUtils;

/**
 *
 * @author Vijayakumar
 */
public class UtilDAO {

    List<ObjectLog> getObjectLogs(TingcoUtils tingcoUtils, ObjectLogs objectLogs, String timeZoneGMToffset, Session session) throws ParseException {
        StringBuffer queryString = new StringBuffer();

        List<ObjectLog> objectLogsList = new ArrayList<ObjectLog>();
        String timePeriod = objectLogs.getTimePeriod();
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        String gc_time = lFormat.format(gc.getTime());
        GregorianCalendar gc_diff = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        String gc_diff_time = null;
        if (timePeriod != null) {
            if (timePeriod.equalsIgnoreCase("hour")) {
                gc_diff.add(GregorianCalendar.HOUR, -1);
                gc_diff_time = lFormat.format(gc_diff.getTime());
            } else if (timePeriod.equalsIgnoreCase("day")) {
                gc_diff.add(GregorianCalendar.DATE, -1);
                gc_diff_time = lFormat.format(gc_diff.getTime());
            } else if (timePeriod.equalsIgnoreCase("week")) {
                gc_diff.add(GregorianCalendar.DATE, -7);
                gc_diff_time = lFormat.format(gc_diff.getTime());
            } else if (timePeriod.equalsIgnoreCase("month")) {
                gc_diff.add(GregorianCalendar.MONTH, -1);
                gc_diff_time = lFormat.format(gc_diff.getTime());
            }
        } else if (objectLogs.getFromDate() != null && objectLogs.getToDate() != null) {

            gc_diff.setTime(lFormat.parse(objectLogs.getFromDate()));
            gc_diff.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc_diff.getTime()));
            gc_diff_time = lFormat.format(gc_diff.getTime());

            gc.setTime(lFormat.parse(objectLogs.getToDate()));
            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
            gc_time = lFormat.format(gc.getTime());
        }

        queryString.append("select objectId, substring(message, 1, 50) as message, messageCode, createdDate from objectLog where objectId = '" + objectLogs.getObjectId() + "' ");
        if (objectLogs.getMessage() != null) {
            queryString.append(" and message like '%" + objectLogs.getMessage() + "%' ");
        }
        if (objectLogs.getMessageCode() != null) {
            queryString.append(" and messageCode like '%" + objectLogs.getMessageCode() + "%' ");
        }

        if (timePeriod != null) {
            if (timePeriod.equalsIgnoreCase("100")) {
                queryString.append(" order by createdDate desc");
                SQLQuery query = session.createSQLQuery(queryString.toString());
                System.out.println(queryString.toString());
                query.addScalar("objectId", Hibernate.STRING);
                query.addScalar("message", Hibernate.STRING);
                query.addScalar("messageCode", Hibernate.STRING);
                query.addScalar("createdDate", Hibernate.TIMESTAMP);
                query.setResultTransformer(Transformers.aliasToBean(ObjectLog.class));
                objectLogsList = query.setMaxResults(100).list();
            } else {
                queryString.append(" and createdDate between '" + gc_diff_time + "' and '" + gc_time + "' order by createdDate desc");
                SQLQuery query = session.createSQLQuery(queryString.toString());
                System.out.println(queryString.toString());
                query.addScalar("objectId", Hibernate.STRING);
                query.addScalar("message", Hibernate.STRING);
                query.addScalar("messageCode", Hibernate.STRING);
                query.addScalar("createdDate", Hibernate.TIMESTAMP);
                query.setResultTransformer(Transformers.aliasToBean(ObjectLog.class));
                objectLogsList = query.setMaxResults(2000).list();
            }
        } else {
            queryString.append(" and createdDate between '" + gc_diff_time + "' and '" + gc_time + "' order by createdDate desc");
            SQLQuery query = session.createSQLQuery(queryString.toString());
            System.out.println(queryString.toString());
            query.addScalar("objectId", Hibernate.STRING);
            query.addScalar("message", Hibernate.STRING);
            query.addScalar("messageCode", Hibernate.STRING);
            query.addScalar("createdDate", Hibernate.TIMESTAMP);
            query.setResultTransformer(Transformers.aliasToBean(ObjectLog.class));
            objectLogsList = query.setMaxResults(2000).list();
        }
        return objectLogsList;
    }

    Object getObjectLogsByObjectId(String objectId, Session session) {
        return session.getNamedQuery("getObjectLogByObjectId").setString("objectId", objectId).uniqueResult();
    }
}
