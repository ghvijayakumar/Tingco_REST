/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.containers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import se.info24.containersjaxb.Container;
import se.info24.containersjaxb.ContainerDevicesPost;
import se.info24.containersjaxb.ObjectFieldData;
import se.info24.containersjaxb.TingcoContainers;
import se.info24.device.DeviceDAO;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.ContainerData;
import se.info24.ismOperationsPojo.ContainerFillings;
import se.info24.ismOperationsPojo.ContainerTransactions;
import se.info24.ismOperationsPojo.ContainerUsageData;
import se.info24.pojo.AddressType;
import se.info24.pojo.Addresses;
import se.info24.pojo.Agreements;
import se.info24.pojo.ContainerDevices;
import se.info24.pojo.ContainerDevicesId;
import se.info24.pojo.ContainerShapes;
import se.info24.pojo.ContainerTypes;
import se.info24.pojo.Containers;
import se.info24.pojo.Country;
import se.info24.pojo.Device;
import se.info24.pojo.DeviceBillingCategories;
import se.info24.pojo.DeviceDataitemTranslations;
import se.info24.pojo.DeviceTypeBillingCategories;
import se.info24.pojo.DeviceTypeServices;
import se.info24.pojo.DeviceTypes;
import se.info24.pojo.GroupDefaultServiceClientLogin;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.Groups;
import se.info24.pojo.ObjectContactMemberships;
import se.info24.pojo.ObjectContactMembershipsId;
import se.info24.pojo.ObjectContactTypeTranslations;
import se.info24.pojo.ObjectContactTypes;
import se.info24.pojo.ObjectContacts;
import se.info24.pojo.ObjectCostCenters;
import se.info24.pojo.ObjectFieldDataId;
import se.info24.pojo.ObjectStateCodeTranslations;
import se.info24.pojo.Ogmcontainers;
import se.info24.pojo.Schedules;
import se.info24.pojo.SensorTypes;
import se.info24.pojo.ServiceClientLogins;
import se.info24.pojo.ServiceDeviceSubscriptions;
import se.info24.pojo.Services;
import se.info24.pojo.SupportCaseDeviceLinks;
import se.info24.pojo.SupportCaseStatuses;
import se.info24.pojo.UnitTranslations;
import se.info24.pojo.Users2;
import se.info24.user.UserDAO;
import se.info24.util.TCMUtil;

/**
 *
 * @author Vijayakumar
 */
public class ContainerDAO {

    public List<ContainerDevices> getContainerDevicesByContainerId(String containerId, Session session) {
        return session.getNamedQuery("getContainerDevicesByContainerId").setString("containerId", containerId).list();
    }

    List<SensorTypes> getAllSensorTypes(Session session) {
        return session.getNamedQuery("getAllSensorTypes").setMaxResults(100).list();
    }

    Set<String> getContainerIds(List<Containers> containersList) {
        Set<String> containerIdsList = new HashSet<String>();
        for (Containers containers : containersList) {
            containerIdsList.add(containers.getContainerId());
        }
        return containerIdsList;
    }

    List<Containers> getContainersByAssetId(Set<String> groupIdsList, String assetId, Session session) {
        return session.getNamedQuery("getContainersByGroupIDListAndAssetID").setParameterList("groupId", groupIdsList).setString("assetId", assetId).list();
    }

    List<Containers> getContainersForTankMonitor(String fieldId, String fieldValue, int countryId, Session session) {
        return session.getNamedQuery("getContainersForMyTankMonitor").setString("fieldId", fieldId).setString("fieldValue", fieldValue).list();
    }

    List<Containers> getContainersListReport(Container containerXML, Set<String> groupIdsset, Session session) {

        GroupDAO groupDAO = new GroupDAO();
        StringBuffer queryString = new StringBuffer("from Containers where groupId in(:groupidset)"); //groupId ='"+ loggedInUserGroup +"' and

        List<Containers> containersList = new ArrayList<Containers>();

        List<String> containerIdsfromDevice = new ArrayList<String>();

        if (containerXML.getContainerID() != null) {
            if (TCMUtil.isValidUUID(containerXML.getContainerID())) {
                queryString.append(" and containerId = '" + containerXML.getContainerID() + "')");
            } else {
                queryString.append(" and (containerName like '%" + containerXML.getContainerID() + "%' or containerNumber like '%" + containerXML.getContainerID() + "%')");
            }
        }

        if (containerXML.getAgreementID() != null) {
            queryString.append(" and agreements.agreementId  = '" + containerXML.getAgreementID() + "' ");
        }

        List<String> searchGroupIdsList = new ArrayList<String>();
        if (containerXML.getSelectedGroupID() != null) {
            if (TCMUtil.isValidUUID(containerXML.getSelectedGroupID())) {
                GroupTranslations gt = groupDAO.getGroupTranslationsByIds(containerXML.getSelectedGroupID(), containerXML.getCountry().getCountryID(), session);
                queryString.append(" and groupId = '" + gt.getId().getGroupId() + "' ");
            } else {
                List<GroupTranslations> groupTranslationsList = groupDAO.getGroupTransByGroupIdandSearchString(groupIdsset, containerXML.getSelectedGroupID(), containerXML.getCountry().getCountryID(), session);
                if (!groupTranslationsList.isEmpty()) {
                    searchGroupIdsList = groupDAO.getGroupidsListFromGroupTranslations(groupTranslationsList);
                    queryString.append(" and groupId in (:searchGroupIdsList)");
                } else {
                    return containersList;
                }
            }
        }
        if (!containerXML.getContainerDevices().isEmpty()) {
            Criteria criteria = session.createCriteria(Containers.class, "c");
            criteria.createAlias("c.containerDeviceses", "cd", CriteriaSpecification.INNER_JOIN);
            criteria.createAlias("cd.device", "d", CriteriaSpecification.INNER_JOIN);
            if (TCMUtil.isValidUUID(containerXML.getContainerDevices().get(0).getDeviceID())) {
                criteria.add(Restrictions.eq("d.deviceId", containerXML.getContainerDevices().get(0).getDeviceID()));
            } else {
                criteria.add(Restrictions.or(Restrictions.ilike("d.deviceName", containerXML.getContainerDevices().get(0).getDeviceID(), MatchMode.ANYWHERE), Restrictions.ilike("d.deviceName2", containerXML.getContainerDevices().get(0).getDeviceID(), MatchMode.ANYWHERE)));
            }
            criteria.setProjection(Projections.property("cd.id.containerId"));
            containerIdsfromDevice = criteria.setMaxResults(1000).list();

            if (!containerIdsfromDevice.isEmpty()) {
                queryString.append(" and containerId in (:containerIds)");
            } else {
                return containersList;
            }
        }

        List<String> objectContainerIdList = new ArrayList<String>();
        if (!containerXML.getObjectGroupTranslations().isEmpty()) {
            Criteria criteria = session.createCriteria(Ogmcontainers.class, "ogm");
            criteria.createAlias("ogm.objectGroups", "og", CriteriaSpecification.INNER_JOIN);
            criteria.add(Restrictions.in("og.groupId", groupIdsset));
            criteria.createAlias("og.objectGroupTranslationses", "ogt", CriteriaSpecification.INNER_JOIN);
            if (TCMUtil.isValidUUID(containerXML.getObjectGroupTranslations().get(0).getObjectGroupID())) {
                criteria.add(Restrictions.eq("og.objectGroupId", containerXML.getObjectGroupTranslations().get(0).getObjectGroupID()));
            } else {
                criteria.add(Restrictions.ilike("ogt.objectGroupName", containerXML.getObjectGroupTranslations().get(0).getObjectGroupID(), MatchMode.ANYWHERE));
            }
            criteria.add(Restrictions.eq("ogt.id.countryId", containerXML.getCountry().getCountryID()));
            criteria.setProjection(Projections.distinct(Projections.property("ogm.id.containerId")));
            objectContainerIdList = criteria.list();
            if (!objectContainerIdList.isEmpty()) {
                queryString.append(" and containerId in (:objectContainerIdList)");
            } else {
                return containersList;
            }
        }

        queryString.append(" order by createdDate DESC");

        Query query = session.createQuery(queryString.toString());
        query.setParameterList("groupidset", groupIdsset);

        if (!searchGroupIdsList.isEmpty()) {
            query.setParameterList("searchGroupIdsList", searchGroupIdsList);
        }
        if (!containerIdsfromDevice.isEmpty()) {
            query.setParameterList("containerIds", containerIdsfromDevice);
        }
        if (!objectContainerIdList.isEmpty()) {
            query.setParameterList("objectContainerIdList", objectContainerIdList);
        }
        query.setMaxResults(1000);
        containersList = query.list();
        return containersList;
    }

//    List<Object> getContainersListReport1(Container containerXML, Set<String> groupIdsset, Session session) {
//    GroupDAO groupDAO = new GroupDAO();
//
//         boolean objectGroups = false;
//        List<String> containerIds = new ArrayList<String>();
//        StringBuffer queryString = new StringBuffer("from Containers where groupId in(:groupidset)");//groupId ='"+ loggedInUserGroup +"' and
//
//        Criteria criteria = session.createCriteria(Containers.class, "c");
//        criteria.createAlias("c.groups", "g", CriteriaSpecification.INNER_JOIN);
//        criteria.createAlias("c.containerDeviceses", "cd", CriteriaSpecification.LEFT_JOIN);
//        criteria.createAlias("cd.device", "d", CriteriaSpecification.INNER_JOIN);
//        criteria.createAlias("d.deviceTypes", "dt", CriteriaSpecification.INNER_JOIN);
//        criteria.createAlias("g.groupTranslationses", "gt", CriteriaSpecification.INNER_JOIN);
//        criteria.createAlias("c.agreements", "a", CriteriaSpecification.INNER_JOIN);
//        criteria.createAlias("c.costCenters", "cc", CriteriaSpecification.INNER_JOIN);
//
//        criteria.add(Restrictions.in("c.groups.groupId", groupIdsset));
//
//        if (containerXML.getContainerID() != null) {
//            criteria.add(Restrictions.eq("c.containerId", containerXML.getContainerID()));
//        }
//
//        if (containerXML.getAgreementID() != null) {
//            criteria.add(Restrictions.eq("a.agreementId", containerXML.getAgreementID()));
//        }
//
//        if (containerXML.getSelectedGroupID() != null) {
//            criteria.add(Restrictions.eq("c.groups.groupId", containerXML.getSelectedGroupID()));
//        }
//
//        if (!containerXML.getContainerDevices().isEmpty()) {
//            criteria.add(Restrictions.eq("d.deviceId", containerXML.getContainerDevices().get(0).getDeviceID()));
//        }
//
//        if (!containerXML.getObjectGroupTranslations().isEmpty()) {
//            Object object = groupDAO.getObjectGroupTranslationsByIds(containerXML.getCountry().getCountryID(), containerXML.getObjectGroupTranslations().get(0).getObjectGroupID(), session);
//            if (object != null) {
//                ObjectGroupTranslations ogt = (ObjectGroupTranslations) object;
//                Criteria c = session.createCriteria(Ogmcontainers.class, "ogm").add(Restrictions.eq("ogm.id.objectGroupId", ogt.getId().getObjectGroupId()));
//                c.setProjection(Projections.distinct(Projections.property("ogm.id.containerId")));
//                containerIds = c.list();
//            }
//
//            if (!containerIds.isEmpty()) {
//                queryString.append(" and containerId in (:containerIds)");
//                objectGroups = true;
//            } else {
//                return containersList;
//            }
//        }
//
//        criteria.addOrder(Order.asc("c.containerName"));
//        criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
//        return criteria.setMaxResults(200).list();
//}
    Ogmcontainers getOGMContainersByContainerIdAndObjectGroupId(String containerId, String objectGroupId, Session session) {
        List<Ogmcontainers> ogmContainersList = session.getNamedQuery("getOgmContainersByContainerIdAndObjectGroupId").setString("containerId", containerId).setString("objectGroupId", objectGroupId).list();
        return !ogmContainersList.isEmpty() ? ogmContainersList.get(0) : null;
    }

    List<ContainerShapes> getContainerShapes(Session session) {
        DetachedCriteria containerTypesCriteria = DetachedCriteria.forClass(ContainerShapes.class, "cs");
        containerTypesCriteria.addOrder(Order.desc("cs.createdDate"));
        containerTypesCriteria.setProjection(Projections.property("cs.containerShapeId"));
        List<String> containerShapeids = containerTypesCriteria.getExecutableCriteria(session).setMaxResults(100).list();

        Criteria criteria = session.createCriteria(ContainerShapes.class);
        criteria.add(Property.forName("containerShapeId").in(containerShapeids));
        criteria.addOrder(Order.asc("containerShapeName"));
        return criteria.list();
    }

    List<ContainerTypes> getContainerTypes(Session session) {
        DetachedCriteria containerTypesCriteria = DetachedCriteria.forClass(ContainerTypes.class, "ct");
        containerTypesCriteria.addOrder(Order.desc("ct.createdDate"));
        containerTypesCriteria.setProjection(Projections.property("ct.containerTypeId"));
        List<String> containerTypeids = containerTypesCriteria.getExecutableCriteria(session).setMaxResults(100).list();

        Criteria criteria = session.createCriteria(ContainerTypes.class);
        criteria.add(Property.forName("containerTypeId").in(containerTypeids));
        criteria.addOrder(Order.asc("containerTypeName"));
        return criteria.list();
    }

    public Containers getContainersByContainerId(String containerId, Session session) {
        List<Containers> containersList = session.getNamedQuery("getContainersById").setString("containerId", containerId).list();
        return !containersList.isEmpty() ? containersList.get(0) : null;
    }

 public List<ContainerDevices> getContainerDevicesByDeviceIdByDeviceDateOrder(String containerId, Session session) {
        return session.getNamedQuery("getContainerDevicesByDeviceIdByDeviceDate").setString("containerId", containerId).list();
    }

    public List<Addresses> getAddressByAddressCity(String searchField, Session session) {
        return session.getNamedQuery("getAddressByAddressCity").setString("searchField", searchField).list();
    }

    SensorTypes getSensorTypesBySensorTypeId(String sensorTypeId, Session session) {
        return (SensorTypes) session.getNamedQuery("getSensorTypesById").setString("sensorTypeId", sensorTypeId).uniqueResult();
    }

    List<UnitTranslations> getUnitTranslationsByCountryId(Session session, int countryId) {
        return session.getNamedQuery("getUnitTranslations").setInteger("countryId", countryId).list();
    }

    List<Object> getVisitorsList(String groupId, String containerId, String userAlias, String searchGroupId, String deviceId, int countryId, Session session) {
        List<Object> visitorsList = new ArrayList<Object>();
        StringBuffer queryString = new StringBuffer("select ua.useraliasid as userAliasId, ua.firstname as firstName, ua.lastname as lastName, ua.usermobilephone as userMobilePhone, ua.useralias as userAlias, " +
                "c.containername as containerName, cua.checkindate as checkInDate, cua.containerId as containerId, cua.checkInDeviceId as checkInDeviceId from containeruseralias as cua inner join " +
                "useralias as ua on cua.useraliasid = ua.useraliasid inner join containers as c on cua.containerid = c.containerid and ua.groupId in " +
                "(" + TCMUtil.getGroupTrust(groupId) + ") ");

        if (containerId != null) {
            if (TCMUtil.isValidUUID(containerId)) {
                queryString.append(" and c.containerId='" + containerId + "' ");
            } else {
                queryString.append(" and c.containerName like '%" + containerId + "%' ");
            }
        }

        if (searchGroupId != null) {
            if (TCMUtil.isValidUUID(searchGroupId)) {
                queryString.append(" and cua.checkindeviceid in(select deviceId from device as d where d.groupId in(Select groupId from GroupTranslations where groupId in (" + TCMUtil.getGroupTrust(groupId) + ")  and countryId ='" + countryId + "' and GroupID = '" + searchGroupId + "'))");
            } else {
                queryString.append(" and cua.checkindeviceid in(select deviceId from device as d where d.groupId in (Select groupId from GroupTranslations where groupId in (" + TCMUtil.getGroupTrust(groupId) + ")  and countryId =" + countryId + " and groupName LIKE '%" + searchGroupId + "%'))");
            }
        }

        if (userAlias != null) {
            if (TCMUtil.isValidUUID(userAlias)) {
                queryString.append(" and cua.userAliasId = '" + userAlias + "' ");
            } else {
                queryString.append(" and cua.userAlias like '%" + userAlias + "%' ");
            }
        }

        if (deviceId != null) {
            if (TCMUtil.isValidUUID(deviceId)) {
                queryString.append(" and cua.checkInDeviceId in(select deviceId from device where deviceId = '" + deviceId + "') ");
            } else {
                queryString.append(" and cua.checkInDeviceId in(select deviceId from device where deviceName like '%" + deviceId + "%') ");
            }

        }

        queryString.append(" order by cua.createdDate DESC");

        System.out.println(queryString.toString());
        SQLQuery query = session.createSQLQuery(queryString.toString());

        query.addScalar("userAliasId", Hibernate.STRING);
        query.addScalar("firstName", Hibernate.STRING);
        query.addScalar("lastName", Hibernate.STRING);
        query.addScalar("userMobilePhone", Hibernate.STRING);
        query.addScalar("userAlias", Hibernate.STRING);
        query.addScalar("containerName", Hibernate.STRING);
        query.addScalar("checkInDate", Hibernate.STRING);
        query.addScalar("containerId", Hibernate.STRING);
        query.addScalar("checkInDeviceId", Hibernate.STRING);

        visitorsList = query.setMaxResults(2000).list();
//        TCMUtil.loger(getClass().getName(), "Size :: " + containersList.size(), "Info");
        return visitorsList;
    }

    public Set<String> getDeviceIdsListObject(List<Object> deviceList) {
        Set<String> deviceIdSet = new HashSet<String>();
        for (Iterator itr = deviceList.iterator(); itr.hasNext();) {

            Object[] row = (Object[]) itr.next();
            for (int i = 0; i < row.length; i++) {
                if (i % 2 == 0) {
                    if (row[i] != null) {
                        deviceIdSet.add(row[i].toString());

                    }
                    i += 6;
                }

            }
        }
        return deviceIdSet;
    }

    List<ContainerData> getcontainerData(String containerId, String fromDate, String toDate, Session ismOperationsSession) throws ParseException {
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String gc_diff_time = null;
        String gc_time = null;
        if (fromDate != null && toDate != null) {
            gc.setTime(lFormat.parse(fromDate));
            gc_diff_time = lFormat.format(gc.getTime());

            gc.setTime(lFormat.parse(toDate));
            gc_time = lFormat.format(gc.getTime());
        } else {
            gc_time = lFormat.format(gc.getTime());
            gc.add(GregorianCalendar.DATE, -7);
            gc_diff_time = lFormat.format(gc.getTime());
        }
        List<ContainerData> containerDataList = ismOperationsSession.getNamedQuery("getContainerDataByContainerIdandDataItemTime").setString("containerId", containerId).setString("fromDate", gc_diff_time).setString("toDate", gc_time).setMaxResults(5000).list();
        return containerDataList;
    }

    public ContainerTypes getContainerTypesById(String containerId, Session session) {
        List<ContainerTypes> ctList = session.getNamedQuery("getContainerTypesById").setString("containerTypeId", containerId).list();
        return ctList.isEmpty() ? null : ctList.get(0);
    }

    public ContainerShapes getContainerShapesById(String containerShapeId, Session session) {
        List<ContainerShapes> csList = session.getNamedQuery("getContainerShapesById").setString("containerShapeId", containerShapeId).list();
        return csList.isEmpty() ? null : csList.get(0);
    }

    public Agreements getAgreements(List<String> agreementIdList, Session session) {
        List<Agreements> aList = session.getNamedQuery("getAgreements").setParameterList("agreementId", agreementIdList).list();
        return aList.isEmpty() ? null : aList.get(0);
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

    public Schedules getSchedulesByID(String scheduleId, Session session) {
        List<Schedules> schedulesList = session.getNamedQuery("getSchedulesById").setString("scheduleId", scheduleId).list();
        return !schedulesList.isEmpty() ? schedulesList.get(0) : null;
    }

    public boolean addContainerDetails(Addresses address, Containers container, ObjectContactMemberships ocm, Session session, ObjectCostCenters costCenters) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            if (address != null) {
                session.saveOrUpdate(address);
            }
            session.save(container);
            if (ocm != null) {
                session.saveOrUpdate(ocm);
            }
            if (costCenters != null) {
                session.saveOrUpdate(costCenters);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    public boolean updateContainerDetails(Addresses address, Containers container, ObjectContactMemberships ocm, Session session, ObjectCostCenters objectCostCenters, boolean isDelete) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(container);
            if (ocm != null) {
                session.update(ocm);
            }
            if (address != null) {
                session.saveOrUpdate(address);

            }
            if (isDelete) {
                session.delete(objectCostCenters);
            } else {
                if (objectCostCenters != null) {
                    session.saveOrUpdate(objectCostCenters);
                }
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    List<Containers> getContainersGroupID(String groupId, Session session) {
        return session.getNamedQuery("getContainersGroupID").setString("groupId", groupId).list();
    }

    public List<Containers> getContainersByGroupIDList(Set<String> groupId, int maxSize, Session session) {
        return session.getNamedQuery("getContainersByGroupIDList").setParameterList("groupId", groupId).setMaxResults(maxSize).list();
    }

    public List<Containers> getContainersOrderByNumber(List<String> containerId, Session session) {
        return session.getNamedQuery("getContainersOrderByNumber").setParameterList("containerId", containerId).list();
    }

    public List<Containers> getContainersOrderByName(List<String> groupId, Session session) {
        return session.getNamedQuery("getContainersByGroupIDListOrderByName").setParameterList("groupId", groupId).setMaxResults(100).list();
    }

    public List<Containers> getContainersOrderByName(Set<String> containerId, Session session) {
        return session.getNamedQuery("getContainersOrderByName").setParameterList("containerId", containerId).list();
    }

    public List<SupportCaseDeviceLinks> getSupportCaseDeviceLinksByDeviceID(List<String> deviceId, Session session) {
        return session.getNamedQuery("getSupportCaseDeviceLinksByDeviceID").setParameterList("deviceId", deviceId).list();
    }

    public SupportCaseStatuses getSupportCaseStatusesById(String supportCaseStatusId, Session session) {
        List<SupportCaseStatuses> list = session.getNamedQuery("getSupportCaseStatusesById").setString("supportCaseStatusId", supportCaseStatusId).list();
        return list.isEmpty() ? null : list.get(0);
    }

    public List<Containers> searchContainersByNameOrNumber(String groupId, String searchField, Session session) {
        return session.getNamedQuery("searchContainersByNameOrNumber").setString("groupId", groupId).setString("searchField", "%" + searchField + "%").list();
    }

    public Set<String> getsearchContainersListObject(List<Object> listContainersObject) {
        Set<String> containerIDsSet = new HashSet<String>();
        for (Iterator itr = listContainersObject.iterator(); itr.hasNext();) {
            containerIDsSet.add(itr.next().toString());
        }
        return containerIDsSet;
    }

    List<Object> searchContainers(String containerName, String organizationName, String productVariantID, Integer currentFillLevelPercent, int countryID, String objectGroupName, String addressCity, String loggedInGroupId, Session session) {

        StringBuffer queryString = new StringBuffer("select containerId as containerId from Containers where groupId in" +
                "(" + TCMUtil.getGroupTrust(loggedInGroupId) + ")  ");

        List<Object> containersList = new ArrayList<Object>();

        if (containerName != null) {
            if (TCMUtil.isValidUUID(containerName)) {
                queryString.append(" and containerId='" + containerName + "'");
            } else {
                queryString.append(" and (containerName like '%" + containerName + "%' or containerNumber like '%" + containerName + "%')");
            }
        }

        if (organizationName != null) {
            if (TCMUtil.isValidUUID(organizationName)) {
                queryString.append(" and groupId in (Select groupId from GroupTranslations where groupId in (" + TCMUtil.getGroupTrust(loggedInGroupId) + ")  and countryId ='" + countryID + "' and GroupID = '" + organizationName + "')");
            } else {
                queryString.append(" and groupId in (Select groupId from GroupTranslations where groupId in(" + TCMUtil.getGroupTrust(loggedInGroupId) + ")  and countryId ='" + countryID + "' and groupName LIKE '%" + organizationName + "%')");
            }

        }

        if (productVariantID != null) {
            queryString.append(" and productVariantId like '%" + productVariantID + "%'");
        }

        if (currentFillLevelPercent != null) {
            queryString.append(" and currentFillLevelPercent < '" + currentFillLevelPercent + "'");
        }

        if (objectGroupName != null) {
            queryString.append("and containerId in (SELECT containerId from Ogmcontainers where objectGroupId in (SELECT objectGroupId from ObjectGroupTranslations where countryId ='" + countryID + "' and objectGroupName like '%" + objectGroupName + "%'))");
        }

        if (addressCity != null) {
            queryString.append(" and addressId in (SELECT addressId from Addresses  where addressCity like '%" + addressCity + "%')");
        }
        queryString.append(" order by createdDate DESC");
        TCMUtil.loger(getClass().getName(), queryString.toString(), "Info");
        SQLQuery query = session.createSQLQuery(queryString.toString());

        query.addScalar("containerId", Hibernate.STRING);

        containersList = query.setMaxResults(500).list();
        TCMUtil.loger(getClass().getName(), "Size :: " + containersList.size(), "Info");
        return containersList;
    }

    public List<ObjectContacts> getObjectContactsByGroupId(Session session, Set<String> groupId) {
        return session.getNamedQuery("getObjectContactsByGroupId").setParameterList("groupId", groupId).setMaxResults(100).list();
    }

    public ObjectContactMemberships getObjectContactMembershipsByIdandContactId(Session session, String objectID, String objectContactID) {
        return (ObjectContactMemberships) session.getNamedQuery("getObjectContactMembershipsByIdandContactId").setString("objectId", objectID).setString("objectContactId", objectContactID).uniqueResult();
    }

    public boolean addExistingContactToContainer(Session session, ObjectContactMemberships objectContactMemberships) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(objectContactMemberships);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public List<ObjectContactTypeTranslations> getObjectContactTypeTranslationsByCountryId(Session session, String CountryID) {
        return session.getNamedQuery("getObjectContactTypeTranslationsByCountryId").setString("countryId", CountryID).list();
    }

    public ObjectContactTypes getObjectContactTypesById(Session session, String objectContactTypeId) {
        return (ObjectContactTypes) session.getNamedQuery("getObjectContactTypesById").setString("objectContactTypeId", objectContactTypeId).uniqueResult();
    }

    public boolean addNewContactToContainer(Session session, ObjectContacts oc, ObjectContactMemberships ocm) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(oc);
            session.saveOrUpdate(ocm);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            if (tx != null) {
                tx.rollback();

            }
            return false;
        }
    }

    public boolean updateObjectContactInfo(Session session, ObjectContacts oc) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(oc);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            if (tx != null) {
                tx.rollback();

            }
            return false;
        }
    }

    public boolean deleteContact(Session session, ObjectContactMemberships ocm) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(ocm);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            if (tx != null) {
                tx.rollback();

            }
            return false;
        }
    }

    public List<ContainerData> getContainerDataByContainerId(Session session, String containerId) {
        return session.getNamedQuery("getContainerDataByContainerId").setString("containerId", containerId).list();
    }

    public boolean deleteContainer(Session session, Session sessionoper, Containers container, List<ObjectContactMemberships> ocmList, List<ContainerData> ContainerdataList, List<ContainerDevices> containerdeviceList, List<ObjectCostCenters> costCenterses) {
        Transaction tx = null;
        Transaction txoper = null;
        try {
            tx = session.beginTransaction();
            txoper = sessionoper.beginTransaction();

            for (ContainerData containerData : ContainerdataList) {
                sessionoper.delete(containerData);
            }
            for (ObjectContactMemberships ocm : ocmList) {
                session.delete(ocm);
            }
            for (ContainerDevices containerDevices : containerdeviceList) {
                session.delete(containerDevices);
            }
            if (!costCenterses.isEmpty()) {
                for (ObjectCostCenters occ : costCenterses) {
                    session.delete(occ);
                }
            }
            session.delete(container);
            if (container.getAddresses() != null) {
                session.delete(container.getAddresses());
            }
            txoper.commit();
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            if (txoper != null) {
                txoper.rollback();
            }
            if (tx != null) {
                tx.rollback();

            }
            return false;
        }
    }

    public List<Containers> searchContainersByNameOrNumbers(Session session, String searchString, Set<String> groupidset) {
        String ContainerId = null;
        if (TCMUtil.isValidUUID(searchString)) {
            ContainerId = searchString;
        }
        return session.getNamedQuery("searchContainersByNameOrNumbers").setParameterList("groupId", groupidset).setString("containerId", ContainerId).setString("searchField", searchString).setMaxResults(100).list();
    }

    public ContainerDevices getContainerDevicesByContainerIdandDeviceId(Session session, String containerId, String deviceId) {
        return (ContainerDevices) session.getNamedQuery("getContainerDevicesByContainerIdandDeviceId").setString("deviceId", deviceId).setString("containerId", containerId).uniqueResult();
    }

    public boolean addNewDeviceToContainer(Session session, ContainerDevices cd) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(cd);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            if (tx != null) {
                tx.rollback();

            }
            return false;
        }
    }

    public boolean deleteDeviceLinkedToContainer(Session session, ContainerDevices cd) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(cd);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            if (tx != null) {
                tx.rollback();

            }
            return false;
        }
    }

    public boolean UpdateContainerNotificationDetails(Session session, Containers container) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(container);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            if (tx != null) {
                tx.rollback();

            }
            return false;
        }
    }

    public ObjectStateCodeTranslations getObjectStateCodeTranslationsByCountryId(Session session, String countryId, String objectStateCodeId) {
        return (ObjectStateCodeTranslations) session.getNamedQuery("getObjectStateCodeTranslationsByCountryId").setString("objectStateCodeId", objectStateCodeId).setInteger("countryId", Integer.parseInt(countryId)).uniqueResult();
    }

    public List<ObjectContactTypeTranslations> getObjectContactTypeTranslationsByIds(Session session, String CountryID, List<String> objectContactTypeId) {
        return session.getNamedQuery("getObjectContactTypeTranslationsByIds").setString("countryId", CountryID).setParameterList("objectContactTypeId", objectContactTypeId).list();
    }

    public List<Device> getNonLinkedDeviceForContainer(Session session, List<String> deviceId, List<String> ContainerDeviceList) {
        Criteria c = session.createCriteria(Device.class);
        c.add(Restrictions.in("deviceId", deviceId));
        if (!ContainerDeviceList.isEmpty()) {
            c.add(Restrictions.not(Restrictions.in("deviceId", ContainerDeviceList)));
        }
        c.addOrder(Order.asc("deviceName"));
        return c.list();

    }

    public List<Ogmcontainers> getOgmcontainersById(Session session, String containerId) {
        return session.getNamedQuery("getOgmcontainersById").setString("containerId", containerId).list();
    }

    public List<ContainerFillings> getContainerFillingsBydataItemTime(Session session, String containerId, String fromDate, String toDate) {
        return session.getNamedQuery("getContainerFillingsBydataItemTime").setString("containerId", containerId).setString("fromDate", fromDate).setString("toDate", toDate).list();
    }

    public List<ContainerUsageData> getContainerUsageDataBydataItemTime(Session session, String containerId, String fromDate, String toDate) {
        return session.getNamedQuery("getContainerUsageDataBydataItemTime").setString("containerId", containerId).setString("fromDate", fromDate).setString("toDate", toDate).list();
    }

    public TingcoContainers addContainerWizard(Session session, String userId, Container con, TingcoContainers tingcoContainers) {
        try {
            Transaction tx = session.beginTransaction();
            GregorianCalendar gc = new GregorianCalendar();
            String containerId = UUID.randomUUID().toString();
            String deviceId = UUID.randomUUID().toString();
            String addressId = null;
            Addresses addresses = null;
            if (!con.getAddress().isEmpty()) {
                addressId = UUID.randomUUID().toString();
                addresses = getAddressPojo(con, addressId, userId, session);
                session.saveOrUpdate(addresses);
            }
//          Device
            Device device = new Device();
            device.setDeviceId(deviceId);
            DeviceDAO deviceDAO = new DeviceDAO();
            device.setDeviceTypes(new DeviceTypes(con.getDeviceTypeID()));
            device.setDeviceName2(con.getDeviceName());


            List<se.info24.pojo.Device> list = deviceDAO.getDeviceByGroupIdNDeviceName2(con.getGroupID().getValue(), con.getDeviceName(), session);
            if (!list.isEmpty()) {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("You cannot have two Devices with the same Unit ID in the same Organization");
                return tingcoContainers;
            }
            device.setDeviceEnabled(1);
            device.setInvoiceDevice(1);
            device.setActiveFromDate(gc.getTime());
            device.setInstalledDate(gc.getTime());
            device.setUserId(userId);
            device.setUpdatedDate(gc.getTime());
            device.setCreatedDate(gc.getTime());


//            Container
            se.info24.pojo.Containers containerPojo = new Containers();
            containerPojo.setContainerId(containerId);
            containerPojo.setContainerName(con.getContainerName());
            device.setDeviceName(con.getContainerName());

            ContainerTypes containerTypes = getContainerTypesById(con.getContainerTypeID(), session);
            if (containerTypes == null) {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("ContainerTypeID is not valid");
                return tingcoContainers;
            }
            containerPojo.setContainerTypes(containerTypes);
            ContainerShapes containerShapes = getContainerShapesById(con.getContainerShapeID(), session);
            if (containerShapes == null) {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("ContainerShapeID is not Valid");
                return tingcoContainers;
            }
            containerPojo.setContainerShapes(containerShapes);
            containerPojo.setContainerNumber(con.getContainerNumber());

            if (con.getAssetID() != null) {
                containerPojo.setAssetId(con.getAssetID());
                device.setAssetId(con.getAssetID());
            }
            GroupDAO groupDAO = new GroupDAO();
            Groups groups = groupDAO.getGroupByGroupId(con.getGroupID().getValue(), session);
            containerPojo.setGroups(groups);
            device.setGroups(groups);

            List<String> aidList = new ArrayList<String>();
            aidList.add(con.getAgreementID());
            Agreements agreements = getAgreements(aidList, session);
            if (agreements != null) {
                containerPojo.setAgreements(agreements);
                device.setAgreements(agreements);
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("AgreementID is not Valid");
                return tingcoContainers;
            }
            if (con.getContainerDescription() != null) {
                containerPojo.setContainerDescription(con.getContainerDescription());
                device.setDeviceDescription(con.getContainerDescription());
            }

            containerPojo.setContainerMinLevel(con.getContainerMinLevel());
            containerPojo.setContainerMaxLevel(con.getContainerMaxLevel());
            containerPojo.setContainerEmptyLevel(con.getContainerEmptyLevel());
            containerPojo.setContainerFullLevel(con.getContainerFullLevel());

            if (con.getContainerLength() != null) {
                containerPojo.setContainerLength(con.getContainerLength());
            }
            if (con.getContainerWidth() != null) {
                containerPojo.setContainerWidth(con.getContainerWidth());
            }
            if (con.getContainerHeight() != null) {
                containerPojo.setContainerHeight(con.getContainerHeight());
            }
            if (con.getProductVariantID() != null) {
                containerPojo.setProductVariantId(con.getProductVariantID());
            }
            if (con.getProductVariantUnit() != null) {
                containerPojo.setProductVariantUnit(con.getProductVariantUnit());
            }
            if (con.getSalesNumber() != null) {
                containerPojo.setSalesNumber(con.getSalesNumber());
            }
            if (con.getDepot() != null) {
                containerPojo.setDepot(con.getDepot());
            }
            if (con.getWarningLowValue() != null) {
                containerPojo.setWarningLowValue(con.getWarningLowValue());
            }
            if (con.getWarningHighValue() != null) {
                containerPojo.setWarningHighValue(con.getWarningHighValue());
            }
            if (con.getWarningLowEventTypeID() != null) {
                containerPojo.setWarningLowEventTypeId(con.getWarningLowEventTypeID());
            }
            if (con.getWarningHighEventTypeID() != null) {
                containerPojo.setWarningHighEventTypeId(con.getWarningHighEventTypeID());
            }

            if (con.getAlarmLowValue() != null) {
                containerPojo.setAlarmLowValue(con.getAlarmLowValue());
            }
            if (con.getAlarmHighValue() != null) {
                containerPojo.setAlarmHighValue(con.getAlarmHighValue());
            }
            if (con.getAlarmHighEventTypeID() != null) {
                containerPojo.setAlarmHighEventTypeId(con.getAlarmHighEventTypeID());
            }
            if (con.getAlarmLowEventTypeID() != null) {
                containerPojo.setAlarmLowEventTypeId(con.getAlarmLowEventTypeID());
            }

            if (con.getOrderMoreBelowThisValue() != null) {
                containerPojo.setOrderMoreBelowThisValue(con.getOrderMoreBelowThisValue());
            }
            if (con.getOrderMoreEventTypeID() != null) {
                containerPojo.setOrderMoreEventTypeId(con.getOrderMoreEventTypeID());
            }
            if (addressId != null) {
                containerPojo.setAddresses(addresses);
                device.setAddresses(addresses);
            }
            containerPojo.setFlagSetValueToMaxIfAboveMax(1);
            containerPojo.setFlagSetValueToMinIfBelowMin(1);
            containerPojo.setEmptyPublicText("Empty");
            containerPojo.setFullPublicText("Full");
            containerPojo.setEventSendingDeviceId(deviceId);

            containerPojo.setCurrentFillLevel(new BigDecimal(0));
            containerPojo.setObjectStateCodeId("65FB3668-47EF-4DE0-AF2F-711118AA7615");
            containerPojo.setIsEnabled(1);
            containerPojo.setIsMobileContainer(0);
            containerPojo.setActiveFromDate(gc.getTime());
            containerPojo.setLastUpdatedByUserId(userId);
            containerPojo.setUpdatedDate(gc.getTime());
            containerPojo.setCreatedDate(gc.getTime());
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 15);
            containerPojo.setActiveToDate(cal.getTime());
            session.saveOrUpdate(device);
            session.saveOrUpdate(containerPojo);

            List<DeviceTypeBillingCategories> billingCategorieses = deviceDAO.getDeviceTypeBillingCategoriesByDeviceTypeID(con.getDeviceTypeID(), session);
            if (!billingCategorieses.isEmpty()) {
                DeviceBillingCategories deviceBillingCategories = new DeviceBillingCategories();
                deviceBillingCategories.setDeviceId(deviceId);
                deviceBillingCategories.setBillingCategories(billingCategorieses.get(0).getBillingCategories());
                deviceBillingCategories.setLastUpdatedByUserId(userId);
                deviceBillingCategories.setUpdatedDate(gc.getTime());
                deviceBillingCategories.setCreatedDate(gc.getTime());
                session.saveOrUpdate(deviceBillingCategories);
            }
//          ContaintainerDevice
            ContainerDevicesPost cdp = con.getContainerDevicesPost();
            for (se.info24.containersjaxb.ContainerDevices ContainerDeviceJaxb : cdp.getContainerDevices()) {
                ContainerDevices cd = new ContainerDevices();
                ContainerDevicesId iddevice = new ContainerDevicesId();
                iddevice.setContainerId(containerId);
                iddevice.setDeviceId(deviceId);
                cd.setId(iddevice);
                cd.setDeviceDataItemId(ContainerDeviceJaxb.getDeviceDataItemID());
                cd.setFillDirection(1);
                if (ContainerDeviceJaxb.getRawMaxValue() != null) {
                    cd.setRawMaxValue(ContainerDeviceJaxb.getRawMaxValue());
                }
                if (ContainerDeviceJaxb.getRawMinValue() != null) {
                    cd.setRawMinValue(ContainerDeviceJaxb.getRawMinValue());
                }
                if (ContainerDeviceJaxb.getScaledMaxValue() != null) {
                    cd.setScaledMaxValue(ContainerDeviceJaxb.getScaledMaxValue());
                }
                if (ContainerDeviceJaxb.getScaledMinValue() != null) {
                    cd.setScaledMinValue(ContainerDeviceJaxb.getScaledMinValue());
                }
                cd.setFlagScaleValue(0);
                if (ContainerDeviceJaxb.getScalingOperand() != null) {
                    cd.setScalingOperand(ContainerDeviceJaxb.getScalingOperand());
                }
                if (ContainerDeviceJaxb.getScalingFactor() != null) {
                    cd.setScalingFactor(ContainerDeviceJaxb.getScalingFactor());
                }
                if (ContainerDeviceJaxb.getSensorTypeID() != null) {
                    cd.setSensorTypeId(ContainerDeviceJaxb.getSensorTypeID());
                }
                cd.setIsEnabled(1);
                cd.setLastUpdatedByUserId(userId);
                cd.setCreatedDate(gc.getTime());
                cd.setUpdatedDate(gc.getTime());
                session.saveOrUpdate(cd);
            }

//          ServiceDeviceSubscriptions
            GroupDefaultServiceClientLogin defaultServiceClientLogins = (GroupDefaultServiceClientLogin) session.get(GroupDefaultServiceClientLogin.class, con.getGroupID().getValue());
            if (defaultServiceClientLogins == null) {
                ///Users2 user = (Users2) session.get(Users2.class, userId);
                UserDAO userDAO = new UserDAO();
                Users2 user = userDAO.getUserById(userId, session);
                defaultServiceClientLogins = (GroupDefaultServiceClientLogin) session.get(GroupDefaultServiceClientLogin.class, user.getGroupId());
            }
            List<DeviceTypeServices> deviceTypeServiceses = getDeviceTypeServicesbyIdsisdefault(session, con.getDeviceTypeID());

            for (DeviceTypeServices deviceTypeServices : deviceTypeServiceses) {
                ServiceDeviceSubscriptions serviceDeviceSubscriptions = new ServiceDeviceSubscriptions();
                serviceDeviceSubscriptions.setServiceDeviceSubscriptionId(UUID.randomUUID().toString());
                Services service = (Services) session.get(se.info24.pojo.Services.class, deviceTypeServices.getId().getServiceId());
                serviceDeviceSubscriptions.setServices(service);
                serviceDeviceSubscriptions.setDevice(device);
                if (agreements != null) {
                    serviceDeviceSubscriptions.setAgreements(agreements);
                }
                if (defaultServiceClientLogins != null) {
                    serviceDeviceSubscriptions.setServiceClientLogins(defaultServiceClientLogins.getServiceClientLogins());
                } else {
                    ServiceClientLogins scl = (ServiceClientLogins) session.get(ServiceClientLogins.class, "B1B1ACD0-514E-4596-987B-7FE1F563782D");
                    serviceDeviceSubscriptions.setServiceClientLogins(scl);
                }
                serviceDeviceSubscriptions.setSubscriptionEnabled(1);
                serviceDeviceSubscriptions.setUserId(userId);
                serviceDeviceSubscriptions.setUpdatedDate(gc.getTime());
                serviceDeviceSubscriptions.setCreatedDate(gc.getTime());
                session.saveOrUpdate(serviceDeviceSubscriptions);

            }

            if (!con.getObjectContacts().isEmpty()) {
//      ObjectContactMemberships
                ObjectContactMemberships objectContactMemberships = new ObjectContactMemberships();
                ObjectContactMembershipsId id = new ObjectContactMembershipsId();
                id.setObjectId(containerId);
                id.setObjectContactId(con.getObjectContacts().get(0).getObjectContactID());
                objectContactMemberships.setId(id);
                objectContactMemberships.setLastUpdatedByUserId(userId);
                objectContactMemberships.setCreatedDate(gc.getTime());
                objectContactMemberships.setUpdatedDate(gc.getTime());
                session.saveOrUpdate(objectContactMemberships);
                ObjectContactMemberships objectContactMembershipsDevice = new ObjectContactMemberships();
                id = new ObjectContactMembershipsId();
                id.setObjectId(deviceId);
                id.setObjectContactId(con.getObjectContacts().get(0).getObjectContactID());
                objectContactMembershipsDevice.setId(id);
                objectContactMembershipsDevice.setLastUpdatedByUserId(userId);
                objectContactMembershipsDevice.setCreatedDate(gc.getTime());
                objectContactMembershipsDevice.setUpdatedDate(gc.getTime());
                session.saveOrUpdate(objectContactMembershipsDevice);
            }
//          ObjectFieldData
            if (con.getObjectFieldDatas() != null) {
                for (ObjectFieldData ofd : con.getObjectFieldDatas().getObjectFieldData()) {
                    if (deviceDAO.getObjectFieldDataByID(session, userId, ofd.getFieldID()) == null) {
                        se.info24.pojo.ObjectFieldData ofdpojo = new se.info24.pojo.ObjectFieldData();
                        ObjectFieldDataId iddata = new ObjectFieldDataId();
                        iddata.setFieldId(ofd.getFieldID());
                        iddata.setObjectId(userId);
                        ofdpojo.setId(iddata);
                        ofdpojo.setFieldValue(TCMUtil.convertHexToString(ofd.getFieldValue()));
                        ofdpojo.setLastUpdatedByUserId(userId);
                        ofdpojo.setUpdatedDate(gc.getTime());
                        ofdpojo.setCreatedDate(gc.getTime());
                        session.saveOrUpdate(ofdpojo);
                    }
                    if (deviceDAO.getObjectFieldDataByID(session, con.getGroupID().getValue(), ofd.getFieldID()) == null) {
                        se.info24.pojo.ObjectFieldData ofdpojo = new se.info24.pojo.ObjectFieldData();
                        ObjectFieldDataId iddata = new ObjectFieldDataId();
                        iddata.setFieldId(ofd.getFieldID());
                        iddata.setObjectId(con.getGroupID().getValue());
                        ofdpojo.setId(iddata);
                        ofdpojo.setFieldValue(TCMUtil.convertHexToString(ofd.getFieldValue()));
                        ofdpojo.setLastUpdatedByUserId(userId);
                        ofdpojo.setUpdatedDate(gc.getTime());
                        ofdpojo.setCreatedDate(gc.getTime());
                        session.saveOrUpdate(ofdpojo);

                    }
                    se.info24.pojo.ObjectFieldData ofdpojo = new se.info24.pojo.ObjectFieldData();
                    ObjectFieldDataId iddata = new ObjectFieldDataId();
                    iddata.setFieldId(ofd.getFieldID());
                    iddata.setObjectId(deviceId);
                    ofdpojo.setId(iddata);
                    ofdpojo.setFieldValue(TCMUtil.convertHexToString(ofd.getFieldValue()));
                    ofdpojo.setLastUpdatedByUserId(userId);
                    ofdpojo.setUpdatedDate(gc.getTime());
                    ofdpojo.setCreatedDate(gc.getTime());
                    session.saveOrUpdate(ofdpojo);

                    ofdpojo = new se.info24.pojo.ObjectFieldData();
                    iddata = new ObjectFieldDataId();
                    iddata.setFieldId(ofd.getFieldID());
                    iddata.setObjectId(containerId);
                    ofdpojo.setId(iddata);
                    ofdpojo.setFieldValue(TCMUtil.convertHexToString(ofd.getFieldValue()));
                    ofdpojo.setLastUpdatedByUserId(userId);
                    ofdpojo.setUpdatedDate(gc.getTime());
                    ofdpojo.setCreatedDate(gc.getTime());
                    session.saveOrUpdate(ofdpojo);

                }
            }

            tx.commit();
            return tingcoContainers;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error Occured");
            return tingcoContainers;
        }
    }

    public Addresses getAddressPojo(Container con, String addressId, String userID, Session session) {
        GregorianCalendar gc = new GregorianCalendar();
        Addresses addresses = new Addresses();
        addresses.setAddressId(addressId);
        if (con.getAddress().get(0).getAddressStreet() != null) {
            addresses.setAddressStreet(con.getAddress().get(0).getAddressStreet());
        }
        if (con.getAddress().get(0).getAddressSuite() != null) {
            addresses.setAddressSuite(con.getAddress().get(0).getAddressSuite());
        }
        if (con.getAddress().get(0).getAddressZip() != null) {
            addresses.setAddressZip(con.getAddress().get(0).getAddressZip());
        }
        if (con.getAddress().get(0).getAddressCity() != null) {
            addresses.setAddressCity(con.getAddress().get(0).getAddressCity());
        }
        AddressType addressType = new AddressType();
        addressType.setAddressTypeId(1);
        addresses.setAddressType(addressType);
        Country country = getCountryById(con.getAddress().get(0).getCountryID(), session);
        addresses.setCountry(country);
        addresses.setUserId(userID);
        addresses.setUpdatedDate(gc.getTime());
        addresses.setCreatedDate(gc.getTime());
        return addresses;

    }

    public List<DeviceTypeServices> getDeviceTypeServicesbyIdsisdefault(Session session, String deviceTypeId) {
        return session.getNamedQuery("getDeviceTypeServicesbyIdsisdefault").setString("deviceTypeId", deviceTypeId).list();
    }

    TingcoContainers updateContainerInformation(Session session, String userId, Container con, TingcoContainers tingcoContainers) {
        try {
            se.info24.pojo.Containers containerPojo = getContainersByContainerId(con.getContainerID(), session);
            if (containerPojo == null) {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("ContainerID is not Valid");
                return tingcoContainers;
            }
            String nullString = null;
//          Device
            DeviceDAO deviceDAO = new DeviceDAO();
//            Device device = deviceDAO.getDeviceById(con.getDeviceID(), session);
//
//            if (device == null) {
//                tingcoContainers.getMsgStatus().setResponseCode(-1);
//                tingcoContainers.getMsgStatus().setResponseText("Device is not valid");
//                return tingcoContainers;
//            }

            Transaction tx = session.beginTransaction();
            GregorianCalendar gc = new GregorianCalendar();

            String addressId = null;
            Addresses address = null;
            if (!con.getAddress().isEmpty()) {
                if (containerPojo.getAddresses() != null) {
                    address = containerPojo.getAddresses();
                } else {
                    addressId = UUID.randomUUID().toString();
                    address = new Addresses();
                    address.setAddressId(addressId);
                    AddressType addressType = new AddressType();
                    addressType.setAddressTypeId(1);
                    address.setAddressType(addressType);

                }
                if (con.getAddress().get(0).getAddressStreet() != null) {
                    address.setAddressStreet(con.getAddress().get(0).getAddressStreet());
                } else {
                    address.setAddressStreet(nullString);
                }
                if (con.getAddress().get(0).getAddressSuite() != null) {
                    address.setAddressSuite(con.getAddress().get(0).getAddressSuite());
                } else {
                    address.setAddressSuite(nullString);
                }
                if (con.getAddress().get(0).getAddressZip() != null) {
                    address.setAddressZip(con.getAddress().get(0).getAddressZip());
                } else {
                    address.setAddressZip(nullString);
                }
                if (con.getAddress().get(0).getAddressCity() != null) {
                    address.setAddressCity(con.getAddress().get(0).getAddressCity());
                } else {
                    address.setAddressCity(nullString);
                }
                Country country = getCountryById(con.getAddress().get(0).getCountryID(), session);
                address.setCountry(country);

                address.setUserId(userId);
                address.setUpdatedDate(gc.getTime());
                address.setCreatedDate(gc.getTime());
                if (containerPojo.getAddresses() == null) {
                    containerPojo.setAddresses(address);
                }
                session.saveOrUpdate(address);
            } else {
                containerPojo.setAddresses(null);
            }

//            Container

            containerPojo.setContainerName(con.getContainerName());

            ContainerTypes containerTypes = getContainerTypesById(con.getContainerTypeID(), session);
            if (containerTypes == null) {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("ContainerTypeID is not valid");
                return tingcoContainers;
            }
            containerPojo.setContainerTypes(containerTypes);
            ContainerShapes containerShapes = getContainerShapesById(con.getContainerShapeID(), session);
            if (containerShapes == null) {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("ContainerShapeID is not Valid");
                return tingcoContainers;
            }
            containerPojo.setContainerShapes(containerShapes);
            containerPojo.setContainerNumber(con.getContainerNumber());

            if (con.getAssetID() != null) {
                containerPojo.setAssetId(con.getAssetID());
            } else {
                containerPojo.setAssetId(nullString);
            }
            GroupDAO groupDAO = new GroupDAO();
            Groups groups = groupDAO.getGroupByGroupId(con.getGroupID().getValue(), session);
            containerPojo.setGroups(groups);

            List<String> aidList = new ArrayList<String>();
            aidList.add(con.getAgreementID());
            Agreements agreements = getAgreements(aidList, session);
            if (agreements != null) {
                containerPojo.setAgreements(agreements);
            } else {
                tingcoContainers.getMsgStatus().setResponseCode(-1);
                tingcoContainers.getMsgStatus().setResponseText("AgreementID is not Valid");
                return tingcoContainers;
            }
            if (con.getContainerDescription() != null) {
                containerPojo.setContainerDescription(con.getContainerDescription());
            } else {
                containerPojo.setContainerDescription(nullString);
            }

            containerPojo.setContainerMinLevel(con.getContainerMinLevel());
            containerPojo.setContainerMaxLevel(con.getContainerMaxLevel());
            containerPojo.setContainerEmptyLevel(con.getContainerEmptyLevel());
            containerPojo.setContainerFullLevel(con.getContainerFullLevel());

            if (con.getContainerLength() != null) {
                containerPojo.setContainerLength(con.getContainerLength());
            } else {
                containerPojo.setContainerLength(null);
            }
            if (con.getContainerWidth() != null) {
                containerPojo.setContainerWidth(con.getContainerWidth());
            } else {
                containerPojo.setContainerWidth(null);
            }
            if (con.getContainerHeight() != null) {
                containerPojo.setContainerHeight(con.getContainerHeight());
            } else {
                containerPojo.setContainerHeight(null);
            }
            if (con.getProductVariantID() != null) {
                containerPojo.setProductVariantId(con.getProductVariantID());
            } else {
                containerPojo.setProductVariantId(null);
            }
            if (con.getProductVariantUnit() != null) {
                containerPojo.setProductVariantUnit(con.getProductVariantUnit());
            } else {
                containerPojo.setProductVariantUnit(null);
            }
            if (con.getSalesNumber() != null) {
                containerPojo.setSalesNumber(con.getSalesNumber());
            } else {
                containerPojo.setSalesNumber(null);
            }
            if (con.getDepot() != null) {
                containerPojo.setDepot(con.getDepot());
            } else {
                containerPojo.setDepot(null);
            }
            if (con.getWarningLowValue() != null) {
                containerPojo.setWarningLowValue(con.getWarningLowValue());
            } else {
                containerPojo.setWarningLowValue(null);
            }
            if (con.getWarningHighValue() != null) {
                containerPojo.setWarningHighValue(con.getWarningHighValue());
            } else {
                containerPojo.setWarningHighValue(null);
            }
            if (con.getWarningLowEventTypeID() != null) {
                containerPojo.setWarningLowEventTypeId(con.getWarningLowEventTypeID());
            } else {
                containerPojo.setWarningLowEventTypeId(nullString);
            }
            if (con.getWarningHighEventTypeID() != null) {
                containerPojo.setWarningHighEventTypeId(con.getWarningHighEventTypeID());
            } else {
                containerPojo.setWarningHighEventTypeId(nullString);
            }

            if (con.getAlarmLowValue() != null) {
                containerPojo.setAlarmLowValue(con.getAlarmLowValue());
            } else {
                containerPojo.setAlarmLowValue(null);
            }
            if (con.getAlarmHighValue() != null) {
                containerPojo.setAlarmHighValue(con.getAlarmHighValue());
            } else {
                containerPojo.setAlarmHighValue(null);
            }
            if (con.getAlarmHighEventTypeID() != null) {
                containerPojo.setAlarmHighEventTypeId(con.getAlarmHighEventTypeID());
            } else {
                containerPojo.setAlarmHighEventTypeId(nullString);
            }
            if (con.getAlarmLowEventTypeID() != null) {
                containerPojo.setAlarmLowEventTypeId(con.getAlarmLowEventTypeID());
            } else {
                containerPojo.setAlarmLowEventTypeId(nullString);
            }

            if (con.getOrderMoreBelowThisValue() != null) {
                containerPojo.setOrderMoreBelowThisValue(con.getOrderMoreBelowThisValue());
            } else {
                containerPojo.setOrderMoreBelowThisValue(null);
            }
            if (con.getOrderMoreEventTypeID() != null) {
                containerPojo.setOrderMoreEventTypeId(con.getOrderMoreEventTypeID());
            } else {
                containerPojo.setOrderMoreEventTypeId(nullString);
            }

            containerPojo.setFlagSetValueToMaxIfAboveMax(1);
            containerPojo.setFlagSetValueToMinIfBelowMin(1);
            containerPojo.setEmptyPublicText("Empty");
            containerPojo.setFullPublicText("Full");

//            containerPojo.setEventSendingDeviceId(con.getDeviceID());

            containerPojo.setCurrentFillLevel(new BigDecimal(0));
            containerPojo.setObjectStateCodeId("65FB3668-47EF-4DE0-AF2F-711118AA7615");
            containerPojo.setIsEnabled(1);
            containerPojo.setIsMobileContainer(0);
            containerPojo.setActiveFromDate(gc.getTime());
            containerPojo.setLastUpdatedByUserId(userId);
            containerPojo.setUpdatedDate(gc.getTime());
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 15);
            containerPojo.setActiveToDate(cal.getTime());

            session.saveOrUpdate(containerPojo);
//          ContaintainerDevice
            ContainerDevicesPost cdp = con.getContainerDevicesPost();
            if (cdp != null) {
                for (se.info24.containersjaxb.ContainerDevices ContainerDeviceJaxb : cdp.getContainerDevices()) {
                    ContainerDevices cd = getContainerDevicesByContainerIdandDeviceId(session, containerPojo.getContainerId(), ContainerDeviceJaxb.getDeviceID());
                    if (cd == null) {
                        cd = new ContainerDevices();
                        ContainerDevicesId iddevice = new ContainerDevicesId();
                        iddevice.setContainerId(containerPojo.getContainerId());
                        iddevice.setDeviceId(ContainerDeviceJaxb.getDeviceID());
                        cd.setId(iddevice);
                        cd.setDeviceDataItemId(ContainerDeviceJaxb.getDeviceDataItemID());

                        cd.setFillDirection(1);
                        if (ContainerDeviceJaxb.getRawMaxValue() != null) {
                            cd.setRawMaxValue(ContainerDeviceJaxb.getRawMaxValue());
                        }
                        if (ContainerDeviceJaxb.getRawMinValue() != null) {
                            cd.setRawMinValue(ContainerDeviceJaxb.getRawMinValue());
                        }
                        if (ContainerDeviceJaxb.getScaledMaxValue() != null) {
                            cd.setScaledMaxValue(ContainerDeviceJaxb.getScaledMaxValue());
                        }
                        if (ContainerDeviceJaxb.getScaledMinValue() != null) {
                            cd.setScaledMinValue(ContainerDeviceJaxb.getScaledMinValue());
                        }
                        cd.setFlagScaleValue(0);
                        if (ContainerDeviceJaxb.getScalingOperand() != null) {
                            cd.setScalingOperand(ContainerDeviceJaxb.getScalingOperand());
                        }
                        if (ContainerDeviceJaxb.getScalingFactor() != null) {
                            cd.setScalingFactor(ContainerDeviceJaxb.getScalingFactor());
                        }
                        if (ContainerDeviceJaxb.getSensorTypeID() != null) {
                            cd.setSensorTypeId(ContainerDeviceJaxb.getSensorTypeID());
                        }
                        cd.setIsEnabled(1);
                        cd.setLastUpdatedByUserId(userId);
                        cd.setCreatedDate(gc.getTime());
                        cd.setUpdatedDate(gc.getTime());
                        session.saveOrUpdate(cd);
                    } else {

                        cd.setDeviceDataItemId(ContainerDeviceJaxb.getDeviceDataItemID());

                        cd.setFillDirection(1);
                        if (ContainerDeviceJaxb.getRawMaxValue() != null) {
                            cd.setRawMaxValue(ContainerDeviceJaxb.getRawMaxValue());
                        } else {
                            cd.setRawMaxValue(null);
                        }
                        if (ContainerDeviceJaxb.getRawMinValue() != null) {
                            cd.setRawMinValue(ContainerDeviceJaxb.getRawMinValue());
                        } else {
                            cd.setRawMinValue(null);
                        }
                        if (ContainerDeviceJaxb.getScaledMaxValue() != null) {
                            cd.setScaledMaxValue(ContainerDeviceJaxb.getScaledMaxValue());
                        } else {
                            cd.setScaledMaxValue(null);
                        }
                        if (ContainerDeviceJaxb.getScaledMinValue() != null) {
                            cd.setScaledMinValue(ContainerDeviceJaxb.getScaledMinValue());
                        } else {
                            cd.setScaledMinValue(null);
                        }
                        cd.setFlagScaleValue(0);
                        if (ContainerDeviceJaxb.getScalingOperand() != null) {
                            cd.setScalingOperand(ContainerDeviceJaxb.getScalingOperand());
                        } else {
                            cd.setScalingOperand(null);
                        }
                        if (ContainerDeviceJaxb.getScalingFactor() != null) {
                            cd.setScalingFactor(ContainerDeviceJaxb.getScalingFactor());
                        } else {
                            cd.setScalingFactor(null);
                        }
                        if (ContainerDeviceJaxb.getSensorTypeID() != null) {
                            cd.setSensorTypeId(ContainerDeviceJaxb.getSensorTypeID());
                        } else {
                            cd.setSensorTypeId(null);
                        }
                        cd.setIsEnabled(1);
                        cd.setLastUpdatedByUserId(userId);
                        cd.setUpdatedDate(gc.getTime());
                        session.saveOrUpdate(cd);
                    }

                }

            }


            if (!con.getObjectContacts().isEmpty()) {
//      ObjectContactMemberships
                List<ObjectContactMemberships> objectContactMembershipsList = deviceDAO.getObjectContactMemberships(containerPojo.getContainerId(), session);
//                List<ObjectContactMemberships> objectContactMembershipsDeviceList = deviceDAO.getObjectContactMemberships(con.getDeviceID(), session);
                ObjectContactMemberships objectContactMembershipsDevice = null;
//                if (objectContactMembershipsDeviceList.isEmpty()) {
//                    objectContactMembershipsDevice = new ObjectContactMemberships();
//                    ObjectContactMembershipsId id = new ObjectContactMembershipsId();
//                    id = new ObjectContactMembershipsId();
//                    id.setObjectId(con.getDeviceID());
//                    id.setObjectContactId(con.getObjectContacts().get(0).getObjectContactID());
//                    objectContactMembershipsDevice.setId(id);
//                    objectContactMembershipsDevice.setLastUpdatedByUserId(userId);
//                    objectContactMembershipsDevice.setCreatedDate(gc.getTime());
//                    objectContactMembershipsDevice.setUpdatedDate(gc.getTime());
//                    session.saveOrUpdate(objectContactMembershipsDevice);
//                } else {
//                    objectContactMembershipsDevice = getObjectContactMembershipsByIdandContactId(session, con.getDeviceID(), con.getObjectContacts().get(0).getObjectContactID());
//                    if (objectContactMembershipsDevice == null) {
//                        objectContactMembershipsDevice = objectContactMembershipsDeviceList.get(0);
//                        ObjectContactMembershipsId id = objectContactMembershipsDevice.getId();
//                        id.setObjectContactId(con.getObjectContacts().get(0).getObjectContactID());
//                        objectContactMembershipsDevice.setId(id);
//                        objectContactMembershipsDevice.setLastUpdatedByUserId(userId);
//                        objectContactMembershipsDevice.setUpdatedDate(gc.getTime());
//                        session.saveOrUpdate(objectContactMembershipsDevice);
//                    }
//                }
                if (objectContactMembershipsList.isEmpty()) {
                    objectContactMembershipsDevice = new ObjectContactMemberships();
                    ObjectContactMembershipsId id = new ObjectContactMembershipsId();
                    id = new ObjectContactMembershipsId();
                    id.setObjectId(con.getContainerID());
                    id.setObjectContactId(con.getObjectContacts().get(0).getObjectContactID());
                    objectContactMembershipsDevice.setId(id);
                    objectContactMembershipsDevice.setLastUpdatedByUserId(userId);
                    objectContactMembershipsDevice.setCreatedDate(gc.getTime());
                    objectContactMembershipsDevice.setUpdatedDate(gc.getTime());
                    session.saveOrUpdate(objectContactMembershipsDevice);
                } else {
                    objectContactMembershipsDevice = getObjectContactMembershipsByIdandContactId(session, con.getContainerID(), con.getObjectContacts().get(0).getObjectContactID());
                    if (objectContactMembershipsDevice == null) {
                        objectContactMembershipsDevice = objectContactMembershipsList.get(0);
                        ObjectContactMembershipsId id = objectContactMembershipsDevice.getId();
                        id.setObjectContactId(con.getObjectContacts().get(0).getObjectContactID());
                        objectContactMembershipsDevice.setId(id);
                        objectContactMembershipsDevice.setLastUpdatedByUserId(userId);
                        objectContactMembershipsDevice.setUpdatedDate(gc.getTime());
                        session.saveOrUpdate(objectContactMembershipsDevice);
                    }
                }
            }
//          ObjectFieldData
            if (con.getObjectFieldDatas() != null) {
                for (ObjectFieldData ofd : con.getObjectFieldDatas().getObjectFieldData()) {
                    se.info24.pojo.ObjectFieldData ofdpojo = deviceDAO.getObjectFieldDataByID(session, userId, ofd.getFieldID());
                    if (ofdpojo == null) {
                        ofdpojo = new se.info24.pojo.ObjectFieldData();
                        ObjectFieldDataId iddata = new ObjectFieldDataId();
                        iddata.setFieldId(ofd.getFieldID());
                        iddata.setObjectId(userId);
                        ofdpojo.setId(iddata);
                        ofdpojo.setFieldValue(TCMUtil.convertHexToString(ofd.getFieldValue()));
                        ofdpojo.setLastUpdatedByUserId(userId);
                        ofdpojo.setUpdatedDate(gc.getTime());
                        ofdpojo.setCreatedDate(gc.getTime());
                        session.saveOrUpdate(ofdpojo);
                    } else {
                        ofdpojo.setFieldValue(TCMUtil.convertHexToString(ofd.getFieldValue()));
                        ofdpojo.setLastUpdatedByUserId(userId);
                        ofdpojo.setUpdatedDate(gc.getTime());
                        session.saveOrUpdate(ofdpojo);
                    }
                    ofdpojo = deviceDAO.getObjectFieldDataByID(session, con.getGroupID().getValue(), ofd.getFieldID());
                    if (ofdpojo == null) {
                        ofdpojo = new se.info24.pojo.ObjectFieldData();
                        ObjectFieldDataId iddata = new ObjectFieldDataId();
                        iddata.setFieldId(ofd.getFieldID());
                        iddata.setObjectId(con.getGroupID().getValue());
                        ofdpojo.setId(iddata);
                        ofdpojo.setFieldValue(TCMUtil.convertHexToString(ofd.getFieldValue()));
                        ofdpojo.setLastUpdatedByUserId(userId);
                        ofdpojo.setUpdatedDate(gc.getTime());
                        ofdpojo.setCreatedDate(gc.getTime());
                        session.saveOrUpdate(ofdpojo);

                    } else {
                        ofdpojo.setFieldValue(TCMUtil.convertHexToString(ofd.getFieldValue()));
                        ofdpojo.setLastUpdatedByUserId(userId);
                        ofdpojo.setUpdatedDate(gc.getTime());
                        session.saveOrUpdate(ofdpojo);
                    }

//                    ofdpojo = deviceDAO.getObjectFieldDataByID(session, con.getDeviceID(), ofd.getFieldID());

//                    if (ofdpojo == null) {
//                        ofdpojo = new se.info24.pojo.ObjectFieldData();
//                        ObjectFieldDataId iddata = new ObjectFieldDataId();
//                        iddata.setFieldId(ofd.getFieldID());
//                        iddata.setObjectId(con.getDeviceID());
//                        ofdpojo.setId(iddata);
//                        ofdpojo.setFieldValue(ofd.getFieldValue());
//                        ofdpojo.setLastUpdatedByUserId(userId);
//                        ofdpojo.setUpdatedDate(gc.getTime());
//                        ofdpojo.setCreatedDate(gc.getTime());
//                        session.saveOrUpdate(ofdpojo);
//                    } else {
//                        ofdpojo.setFieldValue(ofd.getFieldValue());
//                        ofdpojo.setLastUpdatedByUserId(userId);
//                        ofdpojo.setUpdatedDate(gc.getTime());
//                        session.saveOrUpdate(ofdpojo);
//                    }

                    ofdpojo = deviceDAO.getObjectFieldDataByID(session, con.getContainerID(), ofd.getFieldID());

                    if (ofdpojo == null) {
                        ofdpojo = new se.info24.pojo.ObjectFieldData();
                        ObjectFieldDataId iddata = new ObjectFieldDataId();
                        iddata.setFieldId(ofd.getFieldID());
                        iddata.setObjectId(con.getContainerID());
                        ofdpojo.setId(iddata);
                        ofdpojo.setFieldValue(TCMUtil.convertHexToString(ofd.getFieldValue()));
                        ofdpojo.setLastUpdatedByUserId(userId);
                        ofdpojo.setUpdatedDate(gc.getTime());
                        ofdpojo.setCreatedDate(gc.getTime());
                        session.saveOrUpdate(ofdpojo);
                    } else {
                        ofdpojo.setFieldValue(TCMUtil.convertHexToString(ofd.getFieldValue()));
                        ofdpojo.setLastUpdatedByUserId(userId);
                        ofdpojo.setUpdatedDate(gc.getTime());
                        session.saveOrUpdate(ofdpojo);
                    }
                }
            }

            tx.commit();
            return tingcoContainers;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("Error Occured");
            return tingcoContainers;
        }
    }

    public List<DeviceDataitemTranslations> getDeviceDataItemTranslationsByDataItemNameandCountryId(Session session, int countryId) {
        return session.getNamedQuery("getDeviceDataItemTranslationsByDataItemNameandCountryId").setInteger("countryId", countryId).list();
    }

    public List<ContainerDevices> getContainerDevicesByContainerIdandDeviceDataItemId(Session session, String containerId, List<String> devicedataItemId) {
        return session.getNamedQuery("getContainerDevicesByContainerIdandDeviceDataItemId").setString("containerId", containerId).setParameterList("deviceDataItemId", devicedataItemId).list();

    }

    public List<DeviceDataitemTranslations> getDeviceDataItemTranslationsByIds(Session session, List<String> deviceDataItemId, int countryId) {
        return session.getNamedQuery("getDeviceDataItemTranslationsByIds").setParameterList("deviceDataItemId", deviceDataItemId).setInteger("countryId", countryId).list();
    }

    public List<ServiceDeviceSubscriptions> getServiceDeviceSubscriptionsByDeviceIds(Session session, List<String> deviceid) {
        return session.getNamedQuery("getServiceDeviceSubscriptionsByDeviceIds").setParameterList("deviceId", deviceid).list();
    }

    public boolean updateCurrentFillLevel(ContainerTransactions ct, Containers containersPojo, Session ismOperationSession, Session session) {
        Transaction tx = null;
        Transaction txOperation = null;
        try {
            tx = session.beginTransaction();
            txOperation = ismOperationSession.beginTransaction();
            session.saveOrUpdate(containersPojo);
            ismOperationSession.saveOrUpdate(ct);
            tx.commit();
            txOperation.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            if (txOperation != null) {
                txOperation.rollback();
            }
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    List<Containers> getContainersByGroupIDAndProductVariantID(String groupId, String productvariantid, Session session) {
        return session.getNamedQuery("GetContainersByGroupIDAndProductVariantID").setString("groupId", groupId).setString("productVariantId", productvariantid).list();
    }
}
