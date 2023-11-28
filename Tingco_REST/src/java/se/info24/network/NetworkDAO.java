/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.network;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import se.info24.networkjaxb.NetworkSubscription;
import se.info24.pojo.Agreements;
import se.info24.pojo.ContactGroups;
import se.info24.pojo.ContactGroupsInContacts;
import se.info24.pojo.Device;
import se.info24.pojo.EventActionSchedules;
import se.info24.pojo.NetworkSettingsTemplate;
import se.info24.pojo.NetworkSubscriptionSettings;
import se.info24.pojo.NetworkSubscriptionStatuses;
import se.info24.pojo.NetworkSubscriptions;
import se.info24.pojo.NetworkSubscriptionsPendingDelete;
import se.info24.pojo.NetworkTypes;
import se.info24.pojo.Networks;
import se.info24.pojo.ObjectCostCenters;
import se.info24.util.TCMUtil;

/**
 *
 * @author Sekhar
 */
public class NetworkDAO {
//    GroupDAO groupDAO;


    // NetworkTypes CRUD methods.
    public boolean saveNetworkTypes(NetworkTypes networkTypes, Session session) {
        Transaction tx = null;
        try {
            if (networkTypes != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(networkTypes);
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

    public NetworkTypes getNetworkTypesById(String networkTypeId, Session session) {
        try {
            return (NetworkTypes) session.getNamedQuery("getNetworkTypesById").setString("networkTypeId", networkTypeId).uniqueResult();
        } catch (IndexOutOfBoundsException ae) {
            return null;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public boolean removeNetworkTypes(NetworkTypes networkTypes, Session session) {
        Transaction tx = null;
        try {
            if (networkTypes != null) {
                tx = session.beginTransaction();
                session.delete(networkTypes);
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

    public List<NetworkTypes> getAllDeviceTypes(Session session) {
        try {
            return session.getNamedQuery("getAllNetworkTypes").list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    // Network CRUD methods.
    public boolean saveNetwork(Networks networks, Session session) {
        Transaction tx = null;
        try {
            if (networks != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(networks);
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

    public Networks getNetworksById(String networkId, Session session) {
        try {
            return (Networks) session.getNamedQuery("getNetworksById").setString("networkId", networkId).uniqueResult();
        } catch (IndexOutOfBoundsException ae) {
            return null;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public boolean removeNetwork(Networks networks, Session session) {
        Transaction tx = null;
        try {
            if (networks != null) {
                tx = session.beginTransaction();
                session.delete(networks);
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

    public List<NetworkSubscriptions> getNetworkSubscriptionsBydeviceId(Session session, String deviceId, int maxresult) {
        if (maxresult == 0) {
            return session.getNamedQuery("getNetworkSubscriptionsBydeviceId").setString("deviceId", deviceId).list();
        } else {
            return session.getNamedQuery("getNetworkSubscriptionsBydeviceId").setString("deviceId", deviceId).setMaxResults(maxresult).list();
        }
    }

    public List<NetworkSubscriptions> getNetworkSubscriptionsByIds(Session session, Set<String> networkSubscriptionId) {
        return session.getNamedQuery("getNetworkSubscriptionsByIds").setParameterList("networkSubscriptionId", networkSubscriptionId).list();
    }

    public List<NetworkSubscriptionSettings> getNetworkSubscriptionSettingsBySubscriptionID(Session session, String networkSubscriptionId) {
        return session.getNamedQuery("getNetworkSubscriptionSettingsBySubscriptionID").setString("networkSubscriptionId", networkSubscriptionId).list();
    }

    public List<NetworkSubscriptions> getNetworkSubscriptionsByGroupIdAnddDeviceId(Session session, Set<String> groupId) {
        return session.getNamedQuery("getNetworkSubscriptionsByGroupIdAnddDeviceId").setParameterList("groupId", groupId).list();
    }

    public List<NetworkSubscriptions> getNetworkSubscriptionsByGroupIdAndDeviceId(Session session, String groupId, String networkSubscriptionName, String subscriptionNumber, String iccId) {
    List<NetworkSubscriptions> networkSubscriptionsList = new ArrayList<NetworkSubscriptions>();
    StringBuffer queryString = new StringBuffer();

    queryString.append("select ns.networkSubscriptionId as networkSubscriptionId, ns.subscriptionNumber as subscriptionNumber, ns.networkSubscriptionName as networkSubscriptionName, " +
            "ns.groupId as groupId from NetworkSubscriptions as ns " +
            "inner join Groups as g on ns.groupId = g.groupId and g.groupid in (" + TCMUtil.getGroupTrust(groupId) + ") " +
            "and ns.deviceId is null and SubscriptionNumber is not null ");

//    queryString.append("from NetworkSubscriptions ns " +
//            "inner join Groups g with g.groupid in (" + TCMUtil.getGroupTrust(groupId) + ") " +
//            "and device.deviceId = null and SubscriptionNumber != null ");


    if(networkSubscriptionName != null) {
    queryString.append(" and ns.networkSubscriptionName like '%"+networkSubscriptionName+"%' ");
    }

    if(subscriptionNumber != null) {
    queryString.append("and ns.subscriptionNumber like '%"+subscriptionNumber+"%' ");
    }

    if(iccId != null) {
    queryString.append("and  ns.iccid like '%"+iccId+"%' ");
    }

    queryString.append(" order by ns.subscriptionNumber, ns.networkSubscriptionName ");
    
    SQLQuery query = session.createSQLQuery(queryString.toString());//.addEntity("ns", NetworkSubscriptions.class).addJoin("device", "ns.device");
    query.addScalar("networkSubscriptionId", Hibernate.STRING);
    query.addScalar("subscriptionNumber", Hibernate.STRING);
    query.addScalar("networkSubscriptionName", Hibernate.STRING);
    query.addScalar("groupId", Hibernate.STRING);
    query.setResultTransformer(Transformers.aliasToBean(NetworkSubscriptions.class));
        System.out.println(query.getQueryString());

    networkSubscriptionsList = query.setMaxResults(2000).list();

    return networkSubscriptionsList;
    }

    public List<Networks> getNetworksAll(Session session) {
        return session.getNamedQuery("getNetworksAll").list();
    }

    public List<NetworkSubscriptionStatuses> getNetworkSubscriptionStatusesAll(Session session) {
        return session.getNamedQuery("getNetworkSubscriptionStatusesAll").list();
    }

    public Object getNetworkSubscriptionsByIdAnddDeviceIds(Session session, String networkSubscriptionId, String deviceId) {
        return session.getNamedQuery("getNetworkSubscriptionsByIdAnddDeviceIds").setString("networkSubscriptionId", networkSubscriptionId).setString("deviceId", deviceId).uniqueResult();
    }

    public boolean removeNetworkSubscription(Session session, NetworkSubscriptions networkSubscriptions, List<ObjectCostCenters> costCenterses) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            if (!costCenterses.isEmpty()) {
                session.delete(costCenterses.get(0));
            }
            session.update(networkSubscriptions);

            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public List<NetworkSettingsTemplate> getNetworkSettingsTemplateByNetworkTypes(Session session, String networkTypeId) {
        return session.getNamedQuery("getNetworkSettingsTemplateByNetworkTypes").setString("networkTypeId", networkTypeId).list();
    }

    public boolean addNewContactGroup(Session session, ContactGroups cg) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(cg);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    boolean addExistingSubscription(Session session, NetworkSubscriptions networkSubscriptions) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
//            session.merge(device);
            session.update(networkSubscriptions);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    boolean addNewNetworkSubscriptionSetting(Session session, NetworkSubscriptionSettings nss) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(nss);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    boolean deleteContactGroup(Session session, ContactGroups get, List<EventActionSchedules> easList, List<ContactGroupsInContacts> cgicList) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (ContactGroupsInContacts contactGroupsInContacts : cgicList) {
                session.delete(contactGroupsInContacts);
            }
            for (EventActionSchedules eventActionSchedules : easList) {
                session.delete(eventActionSchedules);
            }
            session.delete(get);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    boolean deleteNetworkSubscriptionSetting(Session session, NetworkSubscriptionSettings get) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(get);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public NetworkSubscriptionSettings getNetworkSubscriptionSettingsByID(Session session, String networkSubscriptionSettingId) {
        return (NetworkSubscriptionSettings) session.getNamedQuery("getNetworkSubscriptionSettingsByID").setString("networkSubscriptionSettingId", networkSubscriptionSettingId).uniqueResult();
    }

    public List<EventActionSchedules> getEventActionSchedulesBycontactGroupId(Session session, String contactGroupId) {
        return session.getNamedQuery("getEventActionSchedulesBycontactGroupId").setString("contactGroupId", contactGroupId).list();
    }

    public List<ContactGroupsInContacts> getContactGroupsInContactsBycontactGroupId(Session session, String contactGroupId) {
        return session.getNamedQuery("getContactGroupsInContactsBycontactGroupId").setString("contactGroupId", contactGroupId).list();
    }

    public List<ContactGroups> getContactGroupBySearchCreteria(Session session, Set<String> groupidset, String searchString) {
        return session.getNamedQuery("getContactGroupBySearchCreteria").setParameterList("GroupID", groupidset).setString("searchString", searchString).list();
    }

    public List<ContactGroups> getContactGroupByContactGroupIdandGroupIdsList(Session session, Set<String> groupidset, String searchString) {
        return session.getNamedQuery("getContactGroupByContactGroupId").setParameterList("GroupID", groupidset).setString("searchString", searchString).list();
    }

    public List<ContactGroups> getContactGroupBySearchCreterias(Session session, Set<String> groupidset, String searchString) {
        return session.getNamedQuery("getContactGroupBySearchCreterias").setParameterList("GroupID", groupidset).setString("searchString", searchString).list();
    }

    public Agreements getAgreementsById(Session session, String agreementId) {
        return (Agreements) session.getNamedQuery("getAgreementsById").setString("agreementId", agreementId).uniqueResult();
    }

    public NetworkSubscriptionStatuses getNetworkSubscriptionStatusesById(Session session, String networkSubscriptionStatusId) {
        return (NetworkSubscriptionStatuses) session.getNamedQuery("getNetworkSubscriptionStatusesById").setString("networkSubscriptionStatusId", networkSubscriptionStatusId).uniqueResult();
    }

    public boolean addNewSubscription(Session session, NetworkSubscriptions ns, List<NetworkSubscriptionSettings> nsst, ObjectCostCenters objectCostCenters, boolean isDelete) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(ns);
            for (NetworkSubscriptionSettings networkSubscriptionSettings : nsst) {
                session.saveOrUpdate(networkSubscriptionSettings);
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
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public NetworkSubscriptionsPendingDelete getNetworkSubscriptionsPendingDeleteBySubscriptionId(Session session, String networkSubscriptionId) {
        return (NetworkSubscriptionsPendingDelete) session.getNamedQuery("getNetworkSubscriptionsPendingDeleteBySubscriptionId").setString("networkSubscriptionId", networkSubscriptionId).uniqueResult();
    }

    public Set<String> getDeviceIdsListObject(List<Object> networkSubscriptionIdsObjectList) {
        Set<String> networkSubscriptionIdSet = new HashSet<String>();
        for (Iterator itr = networkSubscriptionIdsObjectList.iterator(); itr.hasNext();) {
            networkSubscriptionIdSet.add(itr.next().toString());
//            Object[] row = (Object[]) itr.next();
//            for (int i = 0; i < row.length; i++) {
//                if (i % 2 == 0) {
//                    if (row[i] != null) {
//                        networkSubscriptionIdSet.add(row[i].toString());
//
//                    }
//                    i += 1;
//                }
//
//            }
        }
        return networkSubscriptionIdSet;
    }

    List<Object> getNetworkSubscriptionsBySearchCriteria(NetworkSubscription networkSubscription, Session session) {

        StringBuffer queryString = new StringBuffer("Select ns.networkSubscriptionId from NetworkSubscriptions as ns inner join networks as n on ns.networkId = n.networkId inner join agreements as ag on ns.agreementId = ag.agreementId and ns.groupId in " +
                "(" + TCMUtil.getGroupTrust(networkSubscription.getGroupID().getValue()) + ")  ");

        if (networkSubscription.getSubscriptionNumber() != null) {
            if (TCMUtil.isValidUUID(networkSubscription.getSubscriptionNumber())) {
                queryString.append(" and ns.networkSubscriptionId ='" + networkSubscription.getSubscriptionNumber() + "' ");
            } else {
                queryString.append(" and (ns.networkSubscriptionName like '%" + networkSubscription.getSubscriptionNumber() + "%' or ns.subscriptionNumber like '%" + networkSubscription.getSubscriptionNumber() + "%') ");
            }
        }

        if (networkSubscription.getDeviceID() != null) {
            if (TCMUtil.isValidUUID(networkSubscription.getDeviceID())) {
                queryString.append(" and ns.deviceId in (Select d.deviceId from Device as d where d.deviceId='" + networkSubscription.getDeviceID() + "' and d.groupId in (" + TCMUtil.getGroupTrust(networkSubscription.getGroupID().getValue()) + ") )");
            } else {
                queryString.append(" and ns.deviceId in (Select d.deviceId from Device as d where (d.deviceName like '%" + networkSubscription.getDeviceID() + "%' or d.deviceName2 like '%" + networkSubscription.getDeviceID() + "%') and d.groupId in (" + TCMUtil.getGroupTrust(networkSubscription.getGroupID().getValue()) + ") )");
            }
        }

        if (networkSubscription.getGroupID().getGroupName() != null) {
            queryString.append(" and ns.groupId in (Select groupId from GroupTranslations where groupId in (" + TCMUtil.getGroupTrust(networkSubscription.getGroupID().getValue()) + ")  and countryId ='" + networkSubscription.getCountryID() + "' and groupName LIKE '%" + networkSubscription.getGroupID().getGroupName() + "%' )");
        }

        if (networkSubscription.getAgreementID() != null) {
            if (TCMUtil.isValidUUID(networkSubscription.getAgreementID().getValue())) {
                queryString.append(" and ag.agreementId='" + networkSubscription.getAgreementID().getValue() + "' ");
            } else {
                queryString.append(" and ag.agreementName like '%" + networkSubscription.getAgreementID().getValue() + "%' ");
            }
        }

        if (networkSubscription.getNetworkID() != null) {
            if (TCMUtil.isValidUUID(networkSubscription.getNetworkID())) {
                queryString.append(" and n.networkId='" + networkSubscription.getNetworkID() + "' ");
            } else {
                queryString.append(" and n.networkName like '%" + networkSubscription.getNetworkID() + "%' ");
            }
        }

        if (networkSubscription.getIPV4() != null) {
            queryString.append(" and (ns.ipv4 like '%" + networkSubscription.getIPV4() + "%' OR ns.ipv6 like '%" + networkSubscription.getIPV4() + "%')");
        }

        if (networkSubscription.getICCID() != null) {
            queryString.append(" and ns.iccid like '%" + networkSubscription.getICCID() + "%'");
        }
        if (networkSubscription.getAPN() != null) {
            queryString.append(" and ns.apn like '%" + networkSubscription.getAPN() + "%'");
        }
        queryString.append(" order by ns.createdDate desc");
        SQLQuery query = session.createSQLQuery(queryString.toString());
        query.addScalar("networkSubscriptionId", Hibernate.STRING);
        List<Object> deviceList = query.setMaxResults(1000).list();
        return deviceList;
    }

    public List<Networks> getNetworksByName(Session session, String searchString) {
        return session.getNamedQuery("getNetworksByName").setString("networkName", searchString).list();
    }

    List<NetworkSubscriptions> getNetworkSubscriptionsBySearchString(String searchString, List<String> list, Session session) {
        return session.getNamedQuery("getNetworkSubscriptionsBySearchString").setParameterList("groupId", list).setString("SearchString", searchString).list();
    }

    List<NetworkSubscriptions> getNetworkSubscriptionsOrderByNetworkSubscriptionName(Set<String> networkSubIDs, Session session, int maxResult) {
        return session.getNamedQuery("getNetworkSubscriptionsOrderByNetworkSubscriptionName").setParameterList("networkSubscriptionId", networkSubIDs).setMaxResults(maxResult).list();
    }

    List<NetworkSubscriptions> getNetworkSubscriptionsByGroupIDs(List<String> list, Session session) {
        return session.getNamedQuery("getNetworkSubscriptionsByGroupIDs").setParameterList("groupId", list).list();
    }

    boolean removeNetworkSubscriptions(Session session, List<NetworkSubscriptions> networkSubscriptionsList, List<NetworkSubscriptionSettings> networkSubscriptionSettingses, List<ObjectCostCenters> costCenterses) {
        Transaction tx = null;
        try {
            GregorianCalendar gc = new GregorianCalendar();
            tx = session.beginTransaction();
            for (NetworkSubscriptionSettings networkSubscriptionSettings : networkSubscriptionSettingses) {
                session.delete(networkSubscriptionSettings);
            }

            for (NetworkSubscriptions networkSubscriptions : networkSubscriptionsList) {
                session.delete(networkSubscriptions);
            }
            if (!costCenterses.isEmpty()) {
                session.delete(costCenterses.get(0));
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public List<NetworkSubscriptions> getNetworkSubscription(Session session, Set<String> networkSubId, String networkSubscriptionName, String subscriptionNumber, String iccid) {
        String str = "from NetworkSubscriptions where networkSubscriptionId in (:id)";

        if (networkSubscriptionName != null) {
            str += " and networkSubscriptionName like '%" + networkSubscriptionName + "%' ";
        }
        if (subscriptionNumber != null) {
            str += " and subscriptionNumber like '%" + subscriptionNumber + "%' ";
        }
        if (iccid != null) {
            str += " and iccid like '%" + iccid + "%' ";
        }
        str += " order by createdDate desc";
        Query query = session.createQuery(str);
        query.setParameterList("id", networkSubId);
        return query.setMaxResults(200).list();
    }

    public List<ObjectCostCenters> getObjectCostCentersByGroupIdandObjectId(Session session, String objectId, String loginGroupId) {
        return session.getNamedQuery("getObjectCostCentersByGroupIdandObjectId").setString("objectId", objectId).setString("groupId", loginGroupId).list();
    }
}

