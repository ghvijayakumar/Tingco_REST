/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import se.info24.ismOperationsPojo.BundleFiles;
import se.info24.pojo.ApplicationModuleServices;
import se.info24.pojo.Bundle;
import se.info24.pojo.BundleLog;
import se.info24.pojo.BundleCategoryTranslations;
import se.info24.pojo.BundleTypeTranslations;
import se.info24.pojo.BundleVersions;
import se.info24.pojo.Channels;
import se.info24.pojo.DeviceServices;
import se.info24.pojo.DeviceServicesActiveBundles;
import se.info24.pojo.DeviceServicesAvailableBundles;
import se.info24.pojo.DeviceServicesId;
import se.info24.pojo.DeviceTypeServices;
import se.info24.pojo.Groups;
import se.info24.pojo.ServiceClientLogins;
import se.info24.pojo.ServiceDeviceSettings;
import se.info24.pojo.ServiceDeviceSubscriptions;
import se.info24.pojo.ServiceSettings;
import se.info24.pojo.BundleDetails;
import se.info24.pojo.ContentCategoriesInGroups;
import se.info24.pojo.DeviceServicesAllowedBundles;
import se.info24.pojo.ServiceContentCategories;
import se.info24.pojo.ServiceContentSubscriptions;
import se.info24.objectpojo.ServiceStatusDetails;
import se.info24.pojo.ServiceTypes;
import se.info24.pojo.ServiceTypesInGroups;
import se.info24.pojo.Services;
import se.info24.pojo.ServicesChannels;
import se.info24.pojo.ServicesVisibleToGroup;
import se.info24.pojo.SettingDataType;
import se.info24.servicejaxb.ObjectFactory;
import se.info24.servicejaxb.Service;
import se.info24.servicejaxb.ServiceDeviceSubscription;
import se.info24.servicejaxb.TingcoService;
import se.info24.util.TCMUtil;

/**
 *
 * @author Sridhar Dasari | Sekhar Babu
 */
public class ServiceDAO {

    // CRUD methods for ServiceTypes.
    public boolean saveServiceTypes(ServiceTypes serviceTypes, Session session) {
        Transaction tx = null;
        try {
            if (serviceTypes != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(serviceTypes);
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

    public ServiceTypes getServiceTypesById(String serviceTypeId, Session session) {
        try {
            return (ServiceTypes) session.getNamedQuery("getServiceTypesById").setString("serviceTypeId", serviceTypeId).list().get(0);
        } catch (IndexOutOfBoundsException ae) {
            return null;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public boolean deleteServiceTypes(ServiceTypes serviceTypes, Session session) {
        Transaction tx = null;
        try {
            if (serviceTypes != null) {
                tx = session.beginTransaction();
                session.delete(serviceTypes);
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

    public List<ServiceTypes> getAllServiceTypes(Session session) {
        try {
            return session.getNamedQuery("getAllServiceTypes").list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    // CRUD Methods for Channels.
    public boolean saveChannels(Channels channels, Session session) {
        Transaction tx = null;
        try {
            if (channels != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(channels);
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

    public Channels getChannelById(String channelId, Session session) {
        try {
            return (Channels) session.getNamedQuery("getChannelById").setString("channelId", channelId).list().get(0);
        } catch (IndexOutOfBoundsException ae) {
            return null;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public boolean deleteChannel(Channels channels, Session session) {
        Transaction tx = null;
        try {
            if (channels != null) {
                tx = session.beginTransaction();
                session.delete(channels);
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

    public List<Channels> getAllChannels(Session session) {
        try {
            return session.getNamedQuery("getAllChannels").list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public List<ServiceTypesInGroups> getServiceTypesByGroupId(String groupId, Session session) {
        try {
            return session.getNamedQuery("getServiceTypesByGroupId").setString("groupId", groupId).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public ArrayList<String> getserviceIDList(Session session, List<ServicesVisibleToGroup> servicesVisibleToGroupList) {
        ArrayList<String> serviceIDList = new ArrayList<String>();
        for (ServicesVisibleToGroup svtg : servicesVisibleToGroupList) {
            serviceIDList.add(svtg.getId().getServiceId());
        }
        return serviceIDList;
    }

    public Set<String> getserviceIDsList(Session session, List<ApplicationModuleServices> applicationModuleServicesList) {
        Set<String> serviceIDList = new HashSet();
        for (ApplicationModuleServices ams : applicationModuleServicesList) {
            serviceIDList.add(ams.getId().getServiceId());
        }
        return serviceIDList;
    }

    public List<Services> getServices(Session session, List<String> serviceIDList) {
        return session.getNamedQuery("getServicesByID").setParameterList("serviceId", serviceIDList).list();
    }

    public List<Services> getAllServices(Session session, int maxResult) {
        return session.getNamedQuery("getAllServices").setMaxResults(maxResult).list();
    }

    public Object getServicesbyServiceId(Session session, String serviceId) {
        return session.getNamedQuery("getServicesByServiceId").setString("serviceId", serviceId).uniqueResult();
    }

    public List<ServicesVisibleToGroup> getServicesVisibleToGroups(Session session, String groupId) {
        return session.getNamedQuery("getServicesVisibleToGroupByGroupId").setString("groupId", groupId).list();
    }

    public List<Services> getServicesByGroupId(Session session, String groupId) {
        return session.getNamedQuery("getServicesByGroupId").setString("groupId", groupId).list();
    }

//    public List<ServiceClientLogins>
    public TingcoService getDeviceServiceSubscriptions(List<ServiceDeviceSubscriptions> subscriptionList, Session session, TingcoService tingcoService) {
//        TingcoService tingcoService = new TingcoService();
        ObjectFactory objectFactory = new ObjectFactory();
        se.info24.servicejaxb.Services servicess = new se.info24.servicejaxb.Services();
        List<String> subscriptionIdList = new ArrayList<String>();
        boolean flag = false;
        if (!subscriptionList.isEmpty()) {
            for (ServiceDeviceSubscriptions sds : subscriptionList) {
                subscriptionIdList.add(sds.getServiceDeviceSubscriptionId());
            }
            List<ServiceDeviceSubscriptions> subscriptionsList = getServiceDeviceSubscriptions(session, subscriptionIdList);
            if (!subscriptionsList.isEmpty()) {
                for (ServiceDeviceSubscriptions subscription : subscriptionsList) {
                    flag = true;
                    Service service = new Service();
                    ServiceDeviceSubscription serviceDeviceSubscription = new ServiceDeviceSubscription();
                    serviceDeviceSubscription.setServiceDeviceSubscriptionID(subscription.getServiceDeviceSubscriptionId());
                    service.getServiceDeviceSubscription().add(serviceDeviceSubscription);
                    String serviceId = subscription.getServices().getServiceId();
                    Object obj = getServicesbyServiceId(session, serviceId);
                    if (obj != null) {
                        Services s = (Services) obj;
                        service.setServiceID(s.getServiceId());
                        service.setServiceName(s.getServiceName());
                        if (s.getServiceDescription() != null) {
                            service.setServiceDescription(s.getServiceDescription());
                        }
                        servicess.getService().add(service);
                    }
                    String serviceClientLoginID = subscription.getServiceClientLogins().getServiceClientLoginId();
                    ServiceClientLogins scl = (ServiceClientLogins) session.getNamedQuery("getServiceClientLoginsById").setString("serviceClientLoginId", serviceClientLoginID).uniqueResult();
                    if (scl != null) {
                        se.info24.servicejaxb.ServiceClientLogins serviceClientLogins = new se.info24.servicejaxb.ServiceClientLogins();
                        serviceClientLogins.setServiceClientLoginID(scl.getServiceClientLoginId());
                        serviceClientLogins.setServiceClientLoginName(scl.getServiceClientLoginName());
                        if (scl.getServiceClientLoginDescription() != null) {
                            serviceClientLogins.setServiceClientLoginDescription(scl.getServiceClientLoginDescription());
                        }
                        service.getServiceClientLogins().add(serviceClientLogins);
                    }
                }
            }
        }
        if (!flag) {
            tingcoService.getMsgStatus().setResponseCode(-1);
            tingcoService.getMsgStatus().setResponseText("No Data Not Found");
            return tingcoService;
        }
        tingcoService.setServices(servicess);
        return tingcoService;
    }

    public List<ServiceDeviceSettings> getServiceDeviceSettingsBySettingIdList(List<String> subscriptionIdList, Session session) {
        return session.getNamedQuery("getServiceDeviceSettingsBySettingIdList").setParameterList("serviceDeviceSubscriptionId", subscriptionIdList).list();
    }

    Object getBundleByBundleId(String bundleId, Session session) {
        return session.getNamedQuery("getBundleById").setString("bundleId", bundleId).uniqueResult();
    }

    public List<Bundle> getBundleByIds(List<String> bundleId, Session session) {
        return session.getNamedQuery("getBundleByIds").setParameterList("bundleId", bundleId).list();
    }

    List<BundleDetails> getBundleDetailsByBundleCategoryAndSearchString(String groupId, int countryId, String searchString, String bundleCategoryId, Session session) {
        StringBuffer queryString = new StringBuffer("select b.bundleid as bundleId, b.bundlename as bundleName, bc.bundlecategoryid as bundleCategoryId, " +
                "bc.bundlecategoryname as bundleCategoryName, gt.groupid as groupId, gt.groupname as groupName from bundle as b inner join bundleCategoryTranslations as bc " +
                "on b.bundlecategoryid = bc.bundlecategoryid inner join groups as g on b.groupid = g.groupid inner join groupTranslations as gt on g.groupid = gt.groupid " +
                "where g.groupid in (" + TCMUtil.getGroupTrust(groupId) + ")  " +
                "and gt.countryid = " + countryId + " and bc.countryid= " + countryId + " ");

        if (searchString != null) {
            if (TCMUtil.isValidUUID(searchString)) {
                queryString.append(" and b.bundleId = '" + searchString + "' ");
            } else {
                queryString.append(" and b.bundleName like '%" + searchString + "%' ");
            }
        }
        if (bundleCategoryId != null) {
            queryString.append(" and b.bundleCategoryId = '" + bundleCategoryId + "' ");
        }

        queryString.append("order by b.createddate desc ");
        SQLQuery query = session.createSQLQuery(queryString.toString());
        query.addScalar("bundleId", Hibernate.STRING);
        query.addScalar("bundleName", Hibernate.STRING);
        query.addScalar("bundleCategoryId", Hibernate.STRING);
        query.addScalar("bundleCategoryName", Hibernate.STRING);
        query.addScalar("groupId", Hibernate.STRING);
        query.addScalar("groupName", Hibernate.STRING);
        query.setResultTransformer(Transformers.aliasToBean(BundleDetails.class));
        return query.setMaxResults(500).list();
    }

    Object getDeviceServices(String serviceID, String deviceID, Session session) {
        return session.getNamedQuery("getDeviceServicesByIds").setString("deviceId", deviceID).setString("serviceId", serviceID).uniqueResult();
    }

    List<DeviceServicesActiveBundles> getDeviceServicesActiveBundlesByDeviceId(String deviceId, Session session) {
        return session.getNamedQuery("getDeviceServicesActiveBundlesByDeviceId").setString("deviceId", deviceId).setMaxResults(2000).list();
    }

    List<Services> getDeviceServicesActiveBundlesByDeviceIdByServiceNameOrder(String deviceId, Session session) {
        Query query = session.getNamedQuery("getDeviceServicesActiveBundlesByDeviceIdServiceNameOrder").setString("deviceId", deviceId);
        return query.setResultTransformer(Transformers.aliasToBean(Services.class)).list();
    }

    Object getDeviceServicesActiveBundlesByIds(String deviceId, String serviceId, Session session) {
        return session.getNamedQuery("getDeviceServicesActiveBundlesByIds").setString("deviceId", deviceId).setString("serviceId", serviceId).uniqueResult();
    }

    List<DeviceServicesAllowedBundles> getDeviceServicesAllowedBundlesByDeviceId(String deviceId, Session session) {
        return session.getNamedQuery("getDeviceServicesAllowedBundlesByDeviceId").setString("deviceId", deviceId).list();
    }

    List<DeviceServicesAllowedBundles> getDeviceServicesAllowedBundlesByDeviceIdServiceId(String deviceId, String serviceId, Session session) {
        return session.getNamedQuery("getDeviceServicesAllowedBundlesByIds").setString("deviceId", deviceId).setString("serviceId", serviceId).list();
    }

    List<DeviceServicesAvailableBundles> getDeviceServicesAvailableBundlesNotInAllowedBundlesByDeviceId(String deviceId, Session session) {
        return session.getNamedQuery("getDeviceAvailableBundlesNotInAllowedBundlesByDeviceId").setString("deviceId", deviceId).list();
    }

    List<DeviceServicesAvailableBundles> getDeviceServicesAvailableBundlesNotInAllowedBundlesByDeviceIdServiceId(String deviceId, String serviceId, Session session) {
        return session.getNamedQuery("getDeviceAvailableBundlesNotInAllowedBundlesByDeviceIdServiceId").setString("deviceId", deviceId).setString("serviceId", serviceId).list();
    }

    List<Services> getDeviceServicesAvailableBundlesByDeviceIdByServiceNameOrder(String deviceId, Session session) {
        Query query = session.getNamedQuery("getDeviceServicesAvailableBundlesByDeviceIdServiceNameOrder").setString("deviceId", deviceId);
        return query.setResultTransformer(Transformers.aliasToBean(Services.class)).list();
    }

    List<DeviceServices> getDeviceServicesByServiceId(String serviceID, Session session) {
        return session.getNamedQuery("getDeviceServicesByServiceId").setString("serviceId", serviceID).list();
    }

    List<DeviceTypeServices> getDeviceTypeServices(Set<String> serviceIdsList, String deviceTypeId, Session session) {
        return session.getNamedQuery("getDeviceTypeServicesbyIds").setParameterList("serviceId", serviceIdsList).setString("deviceTypeId", deviceTypeId).list();
    }

    public List<ServiceDeviceSubscriptions> getServiceDeviceSubscriptionsByDeviceId(Session session, String deviceId) {
        return session.getNamedQuery("getServiceDeviceSubscriptionsByDeviceId").setString("deviceId", deviceId).setMaxResults(100).list();
    }

    List<String> getServiceContentCategories(String contentCategoryId, Session session) {
        Criteria criteria = session.createCriteria(ServiceContentCategories.class, "scc");
        criteria.add(Restrictions.eq("scc.id.contentCategoryId", contentCategoryId));
        criteria.setProjection(Projections.property("scc.id.serviceId"));
        return criteria.list();
    }

    Object getServiceContentSubsscriptions(String serviceContentSubscriptionId, Session session) {
        return session.getNamedQuery("getServiceContentSubscriptionsById").setString("serviceContentSubscriptionId", serviceContentSubscriptionId).uniqueResult();
    }

    List<ServiceContentSubscriptions> getServiceContentSubscriptionsByVariousIds(String serviceId, String contentCategoryId, String serviceClientLoginId, Session session) {
        return session.getNamedQuery("getServiceContentSubscriptionsByVariousIds").setString("serviceId", serviceId).setString("contentCategoryId", contentCategoryId).setString("serviceClientLoginId", serviceClientLoginId).list();
    }

    List<ServiceSettings> getServiceSettingsByServiceId(String serviceId, Session session) {
        return session.getNamedQuery("getServiceSettingsByServiceId").setString("serviceId", serviceId).list();
    }

    Object getServiceSettingsByServiceSettingId(String serviceSettingId, Session session) {
        return session.getNamedQuery("getServiceSettingsById").setString("serviceSettingId", serviceSettingId).uniqueResult();
    }

    List<ServiceStatusDetails> getServiceStatusDetails(Service serviceXML, Session session) {
        List<ServiceStatusDetails> deviceList = new ArrayList<ServiceStatusDetails>();
        StringBuffer queryString = new StringBuffer();
        if (serviceXML.getGroupID().getGroupName() != null) {
            queryString.append("select distinct ss.serviceid as serviceId, ss.deviceid as deviceId, s.servicename as serviceName, d.devicename as deviceName, " +
                    "dt.devicetypeid as deviceTypeId, dt.devicetypename as deviceTypeName, gt.groupId as groupId, gt.groupname as groupName, ss.isonline as isOnline, ss.objectstatecode as objectStateCode, " +
                    "ss.isonlinechangeddate as isOnlineChangedDate, ss.objectstatechangeddate as objectStateChangedDate, ss.createdDate from ISMOperations.dbo.serviceStatus as ss inner join services as s on ss.serviceid = s.serviceid " +
                    "inner join device as d on ss.deviceid = d.deviceid inner join devicetypes as dt on d.devicetypeid = dt.devicetypeid inner join Groups as g on d.groupId " +
                    "= g.groupId and g.groupid in (" + TCMUtil.getGroupTrust(serviceXML.getGroupID().getValue()) + ") inner join " +
                    "grouptranslations as gt on g.groupid = gt.groupid and gt.countryid= " + serviceXML.getCountryID() + " ");

            if (TCMUtil.isValidUUID(serviceXML.getGroupID().getGroupName())) {
                queryString.append(" and gt.groupId = '" + serviceXML.getGroupID().getGroupName() + "' ");
            } else {
                queryString.append(" and gt.groupName like '%" + serviceXML.getGroupID().getGroupName() + "%' ");
            }
        } else {
            queryString.append("select distinct ss.serviceid as serviceId, ss.deviceid as deviceId, s.servicename as serviceName, d.devicename as deviceName, " +
                    "dt.devicetypeid as deviceTypeId, dt.devicetypename as deviceTypeName, gt.groupId as groupId, gt.groupname as groupName, ss.isonline as isOnline, ss.objectstatecode as objectStateCode , " +
                    "ss.isonlinechangeddate as isOnlineChangedDate, ss.objectstatechangeddate as objectStateChangedDate, ss.createdDate from ismoperations.dbo.servicestatus as ss inner join services as s on ss.serviceid = s.serviceid " +
                    "inner join device as d on ss.deviceid = d.deviceid inner join devicetypes as dt on d.devicetypeid = dt.devicetypeid inner join Groups as g on d.groupId " +
                    "= g.groupId and g.groupid in (" + TCMUtil.getGroupTrust(serviceXML.getGroupID().getValue()) + ") inner join " +
                    "grouptranslations as gt on d.groupid = gt.groupid and gt.countryid = " + serviceXML.getCountryID() + " ");
        }

        if (serviceXML.getServiceName() != null) {
            if (TCMUtil.isValidUUID(serviceXML.getServiceName())) {
                queryString.append(" and s.serviceId = '" + serviceXML.getServiceName() + "' ");
            } else {
                queryString.append("and (s.serviceName like '%" + serviceXML.getServiceName() + "%' or s.serviceDescription like '%" + serviceXML.getServiceName() + "%') ");
            }
        }

        if (serviceXML.getOptionalCountryID() != null) {
            queryString.append("left join Addresses as addr on d.addressId = addr.addressId inner join country as cou on addr.countryId = cou.countryId ");
            if (serviceXML.getOptionalCountryID().matches("[0-9]+")) {
                queryString.append(" and cou.countryId = " + Integer.valueOf(serviceXML.getOptionalCountryID()) + " ");
            } else {
                queryString.append(" and cou.countryName like '%" + serviceXML.getOptionalCountryID() + "%' ");
            }
        }

        if (serviceXML.getObjectGroupName() != null) {
            queryString.append(" left join Ogmdevice as ogm on d.deviceId = ogm.deviceId left join ObjectGroups as og on ogm.objectGroupId = og.objectGroupId " +
                    " inner join ObjectGroupTranslations as ogt on og.objectGroupId = ogt.objectGroupId and ogt.countryId = " + serviceXML.getCountryID() + " ");
            if (TCMUtil.isValidUUID(serviceXML.getObjectGroupName())) {
                queryString.append("and ogt.objectGroupId = '" + serviceXML.getObjectGroupName() + "' ");
            } else {
                queryString.append("and ogt.objectGroupName like '%" + serviceXML.getObjectGroupName() + "%' ");
            }
        }

        if (!serviceXML.getDevice().isEmpty()) {
            if (serviceXML.getDevice().get(0).getDeviceName() != null) {
                if (TCMUtil.isValidUUID(serviceXML.getDevice().get(0).getDeviceName())) {
                    queryString.append(" and d.deviceId = '" + serviceXML.getDevice().get(0).getDeviceName() + "' ");
                } else {
                    queryString.append(" and (d.deviceName like '%" + serviceXML.getDevice().get(0).getDeviceName() + "%' or d.deviceDescription like '%" + serviceXML.getDevice().get(0).getDeviceName() + "%') ");
                }
            }
        }

        if (!serviceXML.getDevice().isEmpty()) {
            if (serviceXML.getDevice().get(0).getDeviceTypes() != null) {
                if (TCMUtil.isValidUUID(serviceXML.getDevice().get(0).getDeviceTypes().getDeviceTypeName())) {
                    queryString.append(" and dt.deviceTypeId = '" + serviceXML.getDevice().get(0).getDeviceTypes().getDeviceTypeName() + "' ");
                } else {
                    queryString.append(" and (dt.deviceTypeName like '%" + serviceXML.getDevice().get(0).getDeviceTypes().getDeviceTypeName() + "%' or dt.deviceTypeDescription like '%" + serviceXML.getDevice().get(0).getDeviceTypes().getDeviceTypeName() + "%' ) ");
                }
            }
        }
        if (serviceXML.getServiceStatus().get(0).getIsOnline() != 2) {
            queryString.append(" and ss.isOnline = " + serviceXML.getServiceStatus().get(0).getIsOnline() + " ");
        }

        queryString.append(" order by ss.createdDate desc ");
        SQLQuery query = session.createSQLQuery(queryString.toString());

        TCMUtil.loger(getClass().getName(), query.getQueryString(), "info");
        query.addScalar(
                "serviceId", Hibernate.STRING);
        query.addScalar(
                "deviceId", Hibernate.STRING);
        query.addScalar(
                "serviceName", Hibernate.STRING);
        query.addScalar(
                "deviceName", Hibernate.STRING);
        query.addScalar(
                "deviceTypeId", Hibernate.STRING);
        query.addScalar(
                "deviceTypeName", Hibernate.STRING);
        query.addScalar(
                "groupId", Hibernate.STRING);
        query.addScalar(
                "groupName", Hibernate.STRING);
        query.addScalar(
                "isOnline", Hibernate.INTEGER);
        query.addScalar(
                "objectStateCode", Hibernate.STRING);
        query.addScalar(
                "isOnlineChangedDate", Hibernate.TIMESTAMP);
        query.addScalar(
                "objectStateChangedDate", Hibernate.TIMESTAMP);
        query.setResultTransformer(Transformers.aliasToBean(ServiceStatusDetails.class));
        deviceList = query.setMaxResults(500).list();
        return deviceList;
    }

    Object getServiceTypeBundlesByIds(String serviceTypeId, String bundleId, Session session) {
        return session.getNamedQuery("getServiceTypeBundlesByIds").setString("serviceTypeId", serviceTypeId).setString("bundleId", bundleId).uniqueResult();
    }

    List<BundleDetails> getServiceTypeBundlesByServiceTypeId(String serviceTypeId, int countryId, Session session) {
        String queryString = new String("select b.bundleId as bundleId, b.bundleName as bundleName, bc.bundleCategoryId as bundleCategoryId, bc.bundleCategoryName as bundleCategoryName, " +
                " bt.bundleTypeId as bundleTypeId, bt.bundleTypeName as bundleTypeName, gt.groupId as groupId, gt.groupName as groupName, b.createdDate as bundleCreatedDate " +
                "from serviceTypeBundles as stb inner join bundle as b on stb.bundleid = b.bundleid and stb.servicetypeid='" + serviceTypeId + "' " +
                "inner join bundleCategoryTranslations as bc on b.bundlecategoryid = bc.bundlecategoryid inner join bundleTypeTranslations as bt on " +
                "b.bundletypeid = bt.bundletypeid inner join groupTranslations as gt on b.groupid = gt.groupid and gt.countryid = " + countryId + " " +
                "and bc.countryid=" + countryId + " and bt.countryId = " + countryId + " order by b.createddate desc");
        SQLQuery query = session.createSQLQuery(queryString);
        query.addScalar("bundleId", Hibernate.STRING);
        query.addScalar("bundleName", Hibernate.STRING);
        query.addScalar("bundleCategoryId", Hibernate.STRING);
        query.addScalar("bundleCategoryName", Hibernate.STRING);
        query.addScalar("bundleTypeId", Hibernate.STRING);
        query.addScalar("bundleTypeName", Hibernate.STRING);
        query.addScalar("groupId", Hibernate.STRING);
        query.addScalar("groupName", Hibernate.STRING);
        query.addScalar("bundleCreatedDate", Hibernate.TIMESTAMP);
        query.setResultTransformer(Transformers.aliasToBean(BundleDetails.class));
        return query.setMaxResults(500).list();
    }

    List<Services> getServicesByServiceName(String searchString, int maxResult, Session session) {
        return session.getNamedQuery("getServicesByServiceName").setString("searchString", searchString).setMaxResults(maxResult).list();
    }

    List<String> getservicesIDsList(Session session, List<Services> servicesList) {
        ArrayList<String> serviceIDList = new ArrayList<String>();
        for (Services s : servicesList) {
            serviceIDList.add(s.getServiceId());
        }
        return serviceIDList;
    }

    public List<ServiceDeviceSubscriptions> getServiceDeviceSubscriptions(Session session, List<String> subscriptionIdList) {
        return session.getNamedQuery("getServiceDeviceSubscriptionsByIdList").setParameterList("serviceDeviceSubscriptionId", subscriptionIdList).list();
    }

    public boolean saveServices(Services services, String newServiceId, String serviceName, String serviceDesc, String groupId, String serviceTypeId, Integer serviceEnabled, Date activeFromDate, String replicates, String userId, Date createdDate, Date updatedDate, Session session) {

        services.setServiceId(newServiceId);

        services.setServiceName(serviceName);
        if (serviceDesc != null) {
            services.setServiceDescription(serviceDesc);
        } else {
            services.setServiceDescription(null);
        }

        services.setGroups(new Groups(groupId));
        services.setServiceTypes(new ServiceTypes(serviceTypeId));

        if (serviceEnabled != null) {
            services.setServiceEnabled(serviceEnabled);
        }

        if (replicates != null) {
            services.setReplicates(replicates);
        } else {
            services.setReplicates(null);
        }
        if (activeFromDate != null) {
            services.setActiveFromDate(activeFromDate);
        }
        services.setUserId(userId);
        if (createdDate != null) {
            services.setCreatedDate(createdDate);
        }
        services.setUpdatedDate(updatedDate);
        session.saveOrUpdate(services);

        return true;
    }

    boolean saveBundle(Bundle bundle, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(bundle);
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

    boolean saveBundleFiles(BundleFiles bundleFiles, Session ismOperationssession) {
        try {
            ismOperationssession.saveOrUpdate(bundleFiles);
            return true;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return false;
        }
    }

    boolean saveBundleVersions(BundleVersions bundleVersions, Session session) {
        try {
            session.saveOrUpdate(bundleVersions);
            return true;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return false;
        }
    }

    boolean saveDeviceServices(DeviceServices deviceServices, String deviceID, String serviceID, String userId, Date createdDate, Date updatedDate, Session session) {
        if (deviceID != null && serviceID != null) {
            deviceServices.setId(new DeviceServicesId(deviceID, serviceID));
        }
        if (userId != null) {
            deviceServices.setLastUpdatedByUserId(userId);
        }
        if (createdDate != null) {
            deviceServices.setCreatedDate(createdDate);
        }
        if (updatedDate != null) {
            deviceServices.setUpdatedDate(updatedDate);
        }
        session.saveOrUpdate(deviceServices);
        return true;
    }

    boolean saveServiceSettings(ServiceSettings serviceSettings, String serviceSettingId, String serviceSettingParentId, String serviceId, String serviceSettingName, String serviceSettingValue, SettingDataType settingDataTypeId, Date activeFromDate, String userId, Date createdDate, Date updatedDate, Session session) {
        serviceSettings.setServiceSettingId(serviceSettingId);
        ServiceSettings servicesettingsParent;
        if (serviceSettingParentId != null) {
            servicesettingsParent = new ServiceSettings();
            servicesettingsParent.setServiceSettings(new ServiceSettings(serviceSettingParentId));
        } else {
            servicesettingsParent = null;
        }
        serviceSettings.setServices(new Services(serviceId));
        serviceSettings.setServiceSettingName(serviceSettingName);
        serviceSettings.setServiceSettingValue(serviceSettingValue);
        if (settingDataTypeId != null) {
            serviceSettings.setSettingDataType(new SettingDataType(settingDataTypeId.getSettingDataTypeId(), null));
        } else {
            serviceSettings.setSettingDataType(null);
        }
        serviceSettings.setActiveFromDate(activeFromDate);
        serviceSettings.setUserId(userId);
        serviceSettings.setCreatedDate(createdDate);
        serviceSettings.setUpdatedDate(updatedDate);
        session.saveOrUpdate(serviceSettings);
        return true;
    }

    boolean addServiceSettings(ServiceSettings serviceSettings, String serviceSettingId, String serviceSettingParentId, String serviceId, String serviceSettingName, String serviceSettingValue, SettingDataType settingDataType, Date activeFromDate, String userId, Date createdDate, Date updatedDate, Session session) {
        serviceSettings.setServiceSettingId(serviceSettingId);
        ServiceSettings servicesettingsParent = null;
        if (serviceSettingParentId != null) {
            Object object = getServiceSettingsByServiceSettingId(serviceSettingParentId, session);
            if (object != null) {
                servicesettingsParent = (ServiceSettings) object;
                serviceSettings.setServiceSettings(servicesettingsParent);
            }
        //servicesettingsParent.setServiceSettings(new ServiceSettings(serviceSettingParentId));
        }
//        else {
//            servicesettingsParent = null;
//        }
        serviceSettings.setServices(new Services(serviceId));
        serviceSettings.setServiceSettingName(serviceSettingName);
        serviceSettings.setServiceSettingValue(serviceSettingValue);
        if (settingDataType != null) {
            serviceSettings.setSettingDataType(settingDataType);
        }
//        else {
//            serviceSettings.setSettingDataType(null);
//        }
        serviceSettings.setActiveFromDate(activeFromDate);
        serviceSettings.setUserId(userId);
        serviceSettings.setCreatedDate(createdDate);
        serviceSettings.setUpdatedDate(updatedDate);
        session.saveOrUpdate(serviceSettings);
        return true;
    }

    public boolean addNewChannelToService(ServicesChannels servicesChannels, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(servicesChannels);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    public boolean updateChannelToService(ServicesChannels servicesChannels, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(servicesChannels);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    public ServicesChannels getServicesChannelsById(Session session, int serviceChannelId) {
        Object sch = session.get(ServicesChannels.class, serviceChannelId);
        if (sch != null) {
            return (ServicesChannels) sch;
        } else {
            return null;
        }
    }

    public ServicesChannels getServicesChannelsByIdNative(Session session, int serviceChannelId) {
        List<ServicesChannels> sch = session.getNamedQuery("getServicesChannelsById").setParameter("serviceChannelId", serviceChannelId).list();
        if (!sch.isEmpty()) {
            return sch.get(0);
        } else {
            return null;
        }
    }

    boolean deleteServiceChannelDetailsById(ServicesChannels sch, Session session) {
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(sch);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    public List<ServicesChannels> getServicesChannelsByServiceId(Session session, String serviceId) {
        return session.getNamedQuery("getServicesChannelsByServiceId").setString("serviceId", serviceId).list();
    }

    public List<Channels> getChannelByIds(Session session, List<String> channelID) {
        return session.getNamedQuery("getChannelByIds").setParameterList("channelId", channelID).setMaxResults(200).list();
    }

    public List<BundleLog> getBundleLogByBundleID(String bundleId, Session session, int maxResult) {
        return session.getNamedQuery("getBundleLogByBundleID").setString("bundleId", bundleId).setMaxResults(maxResult).list();
    }

    public List<BundleLog> getBundleLog(Session session, int maxResult) {
        return session.getNamedQuery("getBundleLog").setMaxResults(maxResult).list();
    }

    public List<BundleLog> getBundleLogByCreatedDate(Session session, String gc_diff_time, String gc_time, int maxResult) {
        return session.getNamedQuery("getBundleLogByCreatedDate").setString("fromDate", gc_diff_time).setString("toDate", gc_time).setMaxResults(maxResult).list();
    }

    public List<BundleVersions> getBundleVersionByBundleVersionID(Session session, Set<String> bundleVersionIDs) {
        return session.getNamedQuery("getBundleVersionByBundleVersionID").setParameterList("bundleVersionId", bundleVersionIDs).list();
    }

    public List<BundleTypeTranslations> getBundalTypes(Session session, int countryID, int limit) {
        return session.getNamedQuery("getBundalTypes").setInteger("countryId", countryID).setMaxResults(limit).list();
    }

    public List<BundleTypeTranslations> getBundalTypesByName(Session session, int countryID, String searchString) {
        return session.getNamedQuery("getBundalTypesByName").setInteger("countryId", countryID).setString("bundleTypeName", searchString).list();
    }

    public List<BundleCategoryTranslations> getBundleCategoryTranslations(Session session, int countryID, int limit) {
        return session.getNamedQuery("getBundleCategoryTranslations").setInteger("countryId", countryID).setMaxResults(limit).list();
    }

    public List<BundleCategoryTranslations> getBundleCategoryTranslationsByName(Session session, int countryID, String searchString) {
        return session.getNamedQuery("getBundleCategoryTranslationsByName").setInteger("countryId", countryID).setString("bundleCategoryName", searchString).list();
    }

    public List<Bundle> getBundleByCountryID(Session session, List<String> groupId, int limit) {
        return session.getNamedQuery("getBundleByCountryID").setParameterList("groupId", groupId).setMaxResults(limit).list();
    }

    public List<Bundle> getBundleTranslationsByName(Session session, List<String> groupId, String searchString) {
        return session.getNamedQuery("getBundleTranslationsByName").setParameterList("groupId", groupId).setString("bundleName", searchString).list();
    }

    public Object getBundleCategoryTranslationsByID(String bundleCategoryId, int countryid, Session session) {
        return session.getNamedQuery("getBundleCategoryTranslationsByID").setString("bundleCategoryId", bundleCategoryId).setInteger("countryId", countryid).uniqueResult();
    }

    public Object getBundalTypeTranslationsByID(String bundleTypeId, int countryid, Session session) {
        return session.getNamedQuery("getBundalTypeTranslationsByID").setString("bundleTypeId", bundleTypeId).setInteger("countryId", countryid).uniqueResult();
    }

    public List<BundleVersions> getBundleVersionByBundleID(String bundleId, Session session, int maxResult) {
        return session.getNamedQuery("getBundleVersionByBundleID").setString("bundleId", bundleId).setMaxResults(maxResult).list();
    }

    public List<BundleFiles> getBundleFilesByBundleVersionID(String bundleVersionId, Session session) {
        return session.getNamedQuery("getBundleFilesByBundleVersionID").setString("bundleVersionId", bundleVersionId).list();
    }

    public List<ServiceTypes> getAllServiceTypesBySearchCreteria(String serviceName, Session session) {
        return session.getNamedQuery("getAllServiceTypesBySearchCreteria").setString("serviceTypeName", serviceName).list();
    }

    public boolean saveServiceTypes(Session session, ServiceTypes serviceTypes) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(serviceTypes);
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

    public List<ContentCategoriesInGroups> getContentCategoriesInGroupsByGroupId(Session session, String groupId) {
        return session.getNamedQuery("getContentCategoriesInGroupsByGroupId").setString("groupId", groupId).list();
    }

    public List<ServiceContentSubscriptions> getServiceContentSubscriptionsByContentCategoryId(Session session, String contentCategoryId) {
        return session.getNamedQuery("getServiceContentSubscriptionsByContentCategoryId").setString("contentCategoryId", contentCategoryId).list();
    }

    public List<ServiceContentSubscriptions> getServiceContentSubscriptionsByVariousIdsForDetail(List<String> contentCategoryId, String serviceClientLoginId, Session session) {
        return session.getNamedQuery("getServiceContentSubscriptionsByVariousIdsForDetail").setParameterList("contentCategoryId", contentCategoryId).setString("serviceClientLoginId", serviceClientLoginId).list();
    }

    public List<ServiceClientLogins> getServiceClientLoginsByIdAndGroupID(String groupid, String serviceClientLoginId, Session session) {
        return session.getNamedQuery("getServiceClientLoginsByIdAndGroupID").setString("groupId", groupid).setString("serviceClientLoginId", serviceClientLoginId).list();
    }

    public List<ServiceContentSubscriptions> getServiceContentSubscriptionsBySCLID(List<String> serviceClientLoginId, Session session) {
        return session.getNamedQuery("getServiceContentSubscriptionsBySCLID").setParameterList("serviceClientLoginId", serviceClientLoginId).list();
    }

    List<DeviceTypeServices> getDeviceTypeServicesByDeviceTypeID(String deviceTypeId, Session session) {
        return session.getNamedQuery("getDeviceTypeServicesByDeviceTypeID").setString("deviceTypeId", deviceTypeId).list();
    }

    List<Services> getServicesByServiceIDAndGroupIDs(Set<String> serviceIds, List<String> list, Session session) {
        return session.getNamedQuery("getServicesByServiceIDAndGroupIDs").setParameterList("serviceId", serviceIds).setParameterList("groupId", list).list();
    }

    List<Services> getServicesByServiceIDsOrderByCreatedDate(Set<String> serviceIds, Session session, int maxResult) {
        return session.getNamedQuery("getServicesByServiceIDsOrderByCreatedDate").setParameterList("serviceId", serviceIds).setMaxResults(maxResult).list();
    }

    List<Services> getServicesBySearchStringAndGroupIDs(String searchString, List<String> list, Session session) {
        return session.getNamedQuery("getServicesBySearchStringAndGroupIDs").setString("searchString", searchString).setParameterList("groupId", list).list();
    }

    List<Services> getServices(List<String> serviceIds, Session session, int maxResult) {
        return session.getNamedQuery("getServicesByID").setParameterList("serviceId", serviceIds).setMaxResults(maxResult).list();
    }

    List<Services> getServicesByTrustedGroupIDs(List<String> list, Session session) {
        return session.getNamedQuery("getServicesByTrustedGroupIDs").setParameterList("groupId", list).list();
    }

    List<Services> getServicesByServiceIdAndTrustedGroupIDS(String searchString, List<String> list, Session session) {
        return session.getNamedQuery("getServicesByServiceIdAndTrustedGroupIDS").setString("serviceId", searchString).setParameterList("groupId", list).list();
    }

    boolean saveDeviceTypeServices(List<DeviceTypeServices> deviceTypeServiceses, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (DeviceTypeServices dts : deviceTypeServiceses) {
                session.save(dts);
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

    Object getDeviceTypeServicesByDeviceTypeIDAndServiceID(String deviceTypeId, String serviceId, Session session) {
        return session.getNamedQuery("getDeviceTypeServicesByDeviceTypeIDAndServiceID").setString("deviceTypeId", deviceTypeId).setString("serviceId", serviceId).uniqueResult();
    }

    boolean deleteDeviceTypeServices(DeviceTypeServices dts, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(dts);
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

    Object getGroupDefaultServiceClientLoginByGroupID(String groupId, Session session) {
        return session.getNamedQuery("getGroupDefaultServiceClientLoginByGroupID").setString("groupId", groupId).uniqueResult();
    }
}
