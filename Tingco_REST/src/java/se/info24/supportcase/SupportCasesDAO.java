/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.supportcase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.group.GroupDAO;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.ObjectCostCenters;
import se.info24.pojo.SupportCaseDeviceLinks;
import se.info24.pojo.SupportCaseImpacts;
import se.info24.pojo.SupportCasePriorities;
import se.info24.pojo.SupportCaseResolutionTemplates;
import se.info24.pojo.SupportCaseStatuses;
import se.info24.pojo.SupportCaseSubjectTemplates;
import se.info24.pojo.SupportCaseTypes;
import se.info24.pojo.SupportCases;
import se.info24.pojo.SupportImpactTranslations;
import se.info24.pojo.SupportOrgVisibleToGroups;
import se.info24.pojo.SupportOrganizationUsers;
import se.info24.pojo.SupportOrganizations;
import se.info24.pojo.SupportPriorityTranslations;
import se.info24.pojo.SupportStatusTranslations;
import se.info24.pojo.SupportTypeTranslations;
import se.info24.pojo.Users2;
import se.info24.supportcasejaxb.SupportCase;
import se.info24.user.UserDAO;
import se.info24.util.TCMUtil;

/**
 *
 * @author Ravikant
 */
public class SupportCasesDAO {
//    GroupDAO groupDAO = new GroupDAO();
//    ContainerDAO containerDAO = new ContainerDAO();
//    DeviceDAO deviceDAO = new DeviceDAO();
//    UserDAO userDAO = new UserDAO();

    public SupportCasePriorities getSupportCasePrioritiesByPriorityId(String supportCasePriorityId, Session session) {
        return (SupportCasePriorities) session.getNamedQuery("getSupportCasePrioritiesById").setString("supportCasePriorityId", supportCasePriorityId).uniqueResult();
    }

    public List<SupportCases> getSupportCasesByGroupId(String groupId, Session session) {
        GroupDAO groupDAO = new GroupDAO();
        List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
        Set<String> groupIdsList = groupDAO.getGroupIdsList(groupId, groupTrustsList);
        List<SupportCases> supportCasesList = new ArrayList<SupportCases>();
        /**
         * SPLIT LIST SIZE 30 MAR 2014
         */
        List<List<String>> listsplit = TCMUtil.splitStringList(new ArrayList(groupIdsList), 2000);
        for (List<String> list : listsplit) {
            List<SupportCases> supportCasesListTemp = session.getNamedQuery("getSupportCasesByGroupIdList").setParameterList("groupId", list).setMaxResults(200).list();
            supportCasesList.addAll(supportCasesListTemp);
            if (supportCasesList.size() > 200) {
                supportCasesList = supportCasesList.subList(0, 200);
                break;
            }
        }
        //return session.getNamedQuery("getSupportCasesByGroupIdList").setParameterList("groupId", groupIdsList).setMaxResults(200).list();
        return supportCasesList;
    }

//    List<SupportCases> getSupportCasesBySearchCriteria(List<SupportCases> supportcases, SupportCase supportCaseXML, String loggedinuserId, Session session) {
//        supportcases.clear();
//        List<Device> deviceList = new ArrayList<Device>();
//        Set<String> deviceIdsList = new HashSet<String>();
//        List<SupportCaseDeviceLinks> scdlList = new ArrayList<SupportCaseDeviceLinks>();
//        UserDAO userDAO = new UserDAO();
//        GroupDAO groupDAO = new GroupDAO();
//        Users2 users2 = userDAO.getUserById(loggedinuserId, session);
//        List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(users2.getGroupId(), session);
//        Set<String> groupIdsList = groupDAO.getGroupIdsList(users2.getGroupId(), groupTrustsList);
//        StringBuffer queryString = new StringBuffer("from SupportCases where groups.groupId in (:groupIdsList)");
//
//        if (supportCaseXML.getSupportCaseID() != null) {
//            if (supportCaseXML.getSupportCaseID().length() == 36) {
//                queryString.append(" and supportCaseId = '" + supportCaseXML.getSupportCaseID() + "' ");
//            } else {
//                queryString.append(" and supportCaseNumber like '" + supportCaseXML.getSupportCaseID() + "' ");
//            }
//        }
//
//        if (supportCaseXML.getAssignedToUserID() != null) {
//            queryString.append(" and assignedToUserId = '" + supportCaseXML.getAssignedToUserID() + "' ");
//        }
//
////        List<String> searchGroupIdsList = new ArrayList<String>();
////        if (supportCaseXML.getGroupID().getGroupName() != null) {
////            List<GroupTranslations> groupTranslationsList = groupDAO.getGroupTransByGroupIdandSearchString(groupIdsList, supportCaseXML.getGroupID().getGroupName(), supportCaseXML.getCountryID().intValue(), session);
////            if (!groupTranslationsList.isEmpty()) {
////                searchGroupIdsList = groupDAO.getGroupidsListFromGroupTranslations(groupTranslationsList);
////                queryString.append(" and groups.groupId in (:searchGroupIdsList)");
////            } else {
////                return supportcases;
////            }
////        }
//
//        List<String> searchGroupIdsList = new ArrayList<String>();
//        if (supportCaseXML.getGroupID().getGroupName() != null) {
//            if (supportCaseXML.getGroupID().getGroupName().length() == 36) {
//                if (groupIdsList.contains(supportCaseXML.getGroupID().getGroupName())) {
//                    searchGroupIdsList.add(supportCaseXML.getGroupID().getGroupName());
////                    queryString.append("from Device where groups.groupId in (:searchGroupIdsList)");
//                } else {
//                    return supportcases;
//                }
//            } else {
//                List<GroupTranslations> groupTranslationsList = groupDAO.getGroupTransByGroupIdandSearchString(groupIdsList, supportCaseXML.getGroupID().getGroupName(), supportCaseXML.getCountryID().intValue(), session);
//                if (!groupTranslationsList.isEmpty()) {
//                    searchGroupIdsList = groupDAO.getGroupidsListFromGroupTranslations(groupTranslationsList);
////                queryString.append(" and groups.groupId in (:searchGroupIdsList)");
//                } else {
//                    return supportcases;
//                }
//            }
//            queryString.append(" and groups.groupId in (:searchGroupIdsList)");
//        } else {
//            queryString.append(" and groups.groupId in (:groupIdsList)");
//        }
//
//        List<String> deviceSupportCaseIdList = new ArrayList<String>();
//        deviceList.clear();
//        deviceIdsList.clear();
//        scdlList.clear();
//        DeviceDAO deviceDAO = new DeviceDAO();
//        ContainerDAO containerDAO = new ContainerDAO();
//
//        if (supportCaseXML.getDeviceID() != null) {
//            if (supportCaseXML.getDeviceID().length() == 36) {
//                deviceList = deviceDAO.getDeviceByDeviceAndGroupIDList(groupIdsList, supportCaseXML.getDeviceID(), session);
//            } else {
//                deviceList = deviceDAO.getDeviceBySearchdeviceNameonly(session, supportCaseXML.getDeviceID(), groupIdsList);
//            }
//            if (!deviceList.isEmpty()) {
//                deviceIdsList = deviceDAO.getDeviceIdsList(deviceList);
//                scdlList = containerDAO.getSupportCaseDeviceLinksByDeviceID(new ArrayList<String>(deviceIdsList), session);
//                if (!scdlList.isEmpty()) {
//                    deviceSupportCaseIdList = getSupportCaseIdList(scdlList);
//                    queryString.append(" and supportCaseId in (:deviceSupportCaseIdList)");
//                } else {
//                    return supportcases;
//                }
//            } else {
//                return supportcases;
//            }
//        }
//
//        List<String> deviceTypeSupportCaseIdList = new ArrayList<String>();
//        deviceList.clear();
//        deviceIdsList.clear();
//        scdlList.clear();
//        if (supportCaseXML.getDeviceTypeID() != null) {
////            if(supportCaseXML.getDeviceTypeID().length() == 36) {
////            deviceList = deviceDAO.getDeviceByDeviceTypeId(supportCaseXML.getDeviceTypeID(), session);
////            } else {
////                deviceList = deviceDAO.getDeviceByDeviceTypeId(supportCaseXML.getDeviceTypeID(), session);
////            }
//            deviceList = deviceDAO.getDeviceTypesDeviceIdsList(supportCaseXML.getDeviceTypeID(), session);
//            if (!deviceList.isEmpty()) {
//                scdlList = containerDAO.getSupportCaseDeviceLinksByDeviceID(new ArrayList<String>(deviceIdsList), session);
//                if (!scdlList.isEmpty()) {
//                    deviceTypeSupportCaseIdList = getSupportCaseIdList(scdlList);
//                    queryString.append(" and supportCaseId in (:deviceTypeSupportCaseIdList)");
//                } else {
//                    return supportcases;
//                }
//            } else {
//                return supportcases;
//            }
//        }
//
//        List<String> objectDeviceIdsList = new ArrayList<String>();
//        deviceList.clear();
//        deviceIdsList.clear();
//        scdlList.clear();
//        if (supportCaseXML.getObjectGroupName() != null) {
//            deviceIdsList = new HashSet<String>(deviceDAO.getObjectDeviceIdsList(groupIdsList, supportCaseXML.getObjectGroupName(), supportCaseXML.getCountryID().intValue(), session));
//            if (!deviceIdsList.isEmpty()) {
//                scdlList = containerDAO.getSupportCaseDeviceLinksByDeviceID(new ArrayList<String>(deviceIdsList), session);
//                if (!scdlList.isEmpty()) {
//                    objectDeviceIdsList = getSupportCaseIdList(scdlList);
//                    queryString.append(" and supportCaseId in (:objectDeviceIdsList)");
//                } else {
//                    return supportcases;
//                }
//            } else {
//                return supportcases;
//            }
//        }
//
//        List<String> assetDeviceIdsList = new ArrayList<String>();
//        deviceList.clear();
//        deviceIdsList.clear();
//        scdlList.clear();
//        if (supportCaseXML.getAssetID() != null) {
//            deviceList = deviceDAO.getDeviceByAssetId(supportCaseXML.getAssetID(), session);
//            if (!deviceList.isEmpty()) {
//                deviceIdsList = deviceDAO.getDeviceIdsList(deviceList);
//                scdlList = containerDAO.getSupportCaseDeviceLinksByDeviceID(new ArrayList<String>(deviceIdsList), session);
//                if (!scdlList.isEmpty()) {
//                    assetDeviceIdsList = getSupportCaseIdList(scdlList);
//                    queryString.append(" and supportCaseId in (:assetDeviceIdsList)");
//                } else {
//                    return supportcases;
//                }
//            } else {
//                return supportcases;
//            }
//        }
//
//        List<String> countryDeviceIdsList = new ArrayList<String>();
//        deviceList.clear();
//        deviceIdsList.clear();
//        scdlList.clear();
//        if (supportCaseXML.getOptionalCountryID() != null) {
//            deviceIdsList = new HashSet(deviceDAO.getCountryDeviceIdsList(supportCaseXML.getOptionalCountryID().intValue(), session));
//            if (!deviceIdsList.isEmpty()) {
//                scdlList = containerDAO.getSupportCaseDeviceLinksByDeviceID(new ArrayList<String>(deviceIdsList), session);
//                if (!scdlList.isEmpty()) {
//                    countryDeviceIdsList = getSupportCaseIdList(scdlList);
//                    queryString.append(" and supportCaseId in (:countryDeviceIdsList)");
//                } else {
//                    return supportcases;
//                }
//            } else {
//                return supportcases;
//            }
//        }
//
//        List<String> supportCaseStatusIdList = new ArrayList<String>();
//        if (!supportCaseXML.getSupportCaseStatuses().isEmpty()) {
//            supportCaseStatusIdList = supportCaseXML.getSupportCaseStatuses().get(0).getSupportCaseStatusID();
//            queryString.append(" and supportCaseStatuses.supportCaseStatusId in (:supportCaseStatusIdList)");
//        }
//
//        List<String> supportCaseTypesIdList = new ArrayList<String>();
//        if (!supportCaseXML.getSupportCaseTypes().isEmpty()) {
//            supportCaseTypesIdList = supportCaseXML.getSupportCaseTypes().get(0).getSupportCaseTypeID();
//            queryString.append(" and supportCaseTypes.supportCaseTypeId in (:supportCaseTypesIdList)");
//        }
//
//        List<String> supportCasePriorityIdList = new ArrayList<String>();
//        if (!supportCaseXML.getSupportCasePriorities().isEmpty()) {
//            supportCasePriorityIdList = supportCaseXML.getSupportCasePriorities().get(0).getSupportCasePriorityID();
//            queryString.append(" and supportCasePriorities.supportCasePriorityId in (:supportCasePriorityIdList)");
//        }
//
//
//        queryString.append(" order by supportCaseNumber desc ");
//        Query query = session.createQuery(queryString.toString());
//        query.setParameterList("groupIdsList", groupIdsList);
//
//        if (!searchGroupIdsList.isEmpty()) {
//            query.setParameterList("searchGroupIdsList", searchGroupIdsList);
//        }
//        if (!deviceSupportCaseIdList.isEmpty()) {
//            query.setParameterList("deviceSupportCaseIdList", deviceSupportCaseIdList);
//        }
//        if (!deviceTypeSupportCaseIdList.isEmpty()) {
//            query.setParameterList("deviceTypeSupportCaseIdList", deviceTypeSupportCaseIdList);
//        }
//        if (!objectDeviceIdsList.isEmpty()) {
//            query.setParameterList("objectDeviceIdsList", objectDeviceIdsList);
//        }
//        if (!assetDeviceIdsList.isEmpty()) {
//            query.setParameterList("assetDeviceIdsList", assetDeviceIdsList);
//        }
//        if (!countryDeviceIdsList.isEmpty()) {
//            query.setParameterList("countryDeviceIdsList", countryDeviceIdsList);
//        }
//        if (!supportCaseStatusIdList.isEmpty()) {
//            query.setParameterList("supportCaseStatusIdList", supportCaseStatusIdList);
//        }
//        if (!supportCaseTypesIdList.isEmpty()) {
//            query.setParameterList("supportCaseTypesIdList", supportCaseTypesIdList);
//        }
//        if (!supportCasePriorityIdList.isEmpty()) {
//            query.setParameterList("supportCasePriorityIdList", supportCasePriorityIdList);
//        }
//        supportcases = query.setMaxResults(200).list();
//        return supportcases;
//    }
    List<String> getSupportCasesBySearchCriteria(List<SupportCases> supportcases, SupportCase supportCaseXML, String loggedinuserId, Session session) {
        supportcases.clear();
        List<String> list = new ArrayList<String>();
        UserDAO userDAO = new UserDAO();
        Users2 users2 = userDAO.getUserById(loggedinuserId, session);
//        StringBuffer queryString = new StringBuffer("Select supportCaseId, supportCaseNumber, supportCaseSubject, durationActual, reportedDate, supportCasePriorityID, " +
//                "supportCaseStatusID, supportCaseTypeID, impactsGroupId, costCenterId "+
//                "from SupportCases where impactsGroupId in (Select GroupID from GroupTrusts where ITrustGroupID = '" + users2.getGroupId() + "' or GroupID = '" + users2.getGroupId() + "') ");

        StringBuffer queryString = new StringBuffer("Select supportCaseId from SupportCases where impactsGroupId in (" + TCMUtil.getGroupTrust(users2.getGroupId()) + ")  ");

        if (supportCaseXML.getSupportCaseID() != null) {
            if (TCMUtil.isValidUUID(supportCaseXML.getSupportCaseID())) {
                queryString.append(" and supportCaseId = '" + supportCaseXML.getSupportCaseID() + "' ");
            } else {
                queryString.append(" and supportCaseNumber like '" + supportCaseXML.getSupportCaseID() + "' ");
            }
        }

        if (supportCaseXML.getAssignedToUserID() != null) {
            queryString.append(" and assignedToUserId = '" + supportCaseXML.getAssignedToUserID() + "' ");
        }

        if (supportCaseXML.getGroupID().getGroupName() != null) {
            if (TCMUtil.isValidUUID(supportCaseXML.getGroupID().getGroupName())) {
                queryString.append(" and impactsGroupId in (" + TCMUtil.getGroupTrust(users2.getGroupId()) + ") and impactsGroupId = '" + supportCaseXML.getGroupID().getGroupName() + "' ");
            } else {
                queryString.append(" and impactsGroupId in (Select GroupID from GroupTranslations where groupId in (" + TCMUtil.getGroupTrust(users2.getGroupId()) + ") and countryId = '" + supportCaseXML.getCountryID().intValue() + "' and groupName like '%" + supportCaseXML.getGroupID().getGroupName() + "%')");
            }
        }

        if (supportCaseXML.getDeviceID() != null) {
            if (TCMUtil.isValidUUID(supportCaseXML.getDeviceID())) {
                queryString.append(" and supportCaseId in (Select SupportCaseID from SupportCaseDeviceLinks where DeviceID in (Select DeviceID from Device where deviceId='" + supportCaseXML.getDeviceID() + "' and groupId in (" + TCMUtil.getGroupTrust(users2.getGroupId()) + ")))");
            } else {
                queryString.append(" and supportCaseId in (Select SupportCaseID from SupportCaseDeviceLinks where DeviceID in (Select DeviceID from Device where groupId in (" + TCMUtil.getGroupTrust(users2.getGroupId()) + ") and deviceName like '%" + supportCaseXML.getDeviceID() + "%'))");
            }
        }

        if (supportCaseXML.getDeviceTypeID() != null) {
            if (TCMUtil.isValidUUID(supportCaseXML.getDeviceTypeID())) {
                queryString.append(" and supportCaseId in (Select SupportCaseID from SupportCaseDeviceLinks where DeviceID in (Select DeviceID from Device where DeviceTypeID in (Select DeviceTypeID from DeviceTypes where DeviceTypeID  = '" + supportCaseXML.getDeviceTypeID() + "')))");
            } else {
                queryString.append(" and supportCaseId in (Select SupportCaseID from SupportCaseDeviceLinks where DeviceID in (Select DeviceID from Device where DeviceTypeID in (Select DeviceTypeID from DeviceTypes where DeviceTypeName  LIKE '%" + supportCaseXML.getDeviceTypeID() + "%')))");
            }
        }

        if (supportCaseXML.getObjectGroupName() != null) {
            if (TCMUtil.isValidUUID(supportCaseXML.getObjectGroupName())) {
                queryString.append(" and supportCaseId in (Select SupportCaseID from SupportCaseDeviceLinks where DeviceID in(Select DeviceID from Ogmdevice where objectGroupId in(Select ObjectGroupID from ObjectGroups where GroupID in (" + TCMUtil.getGroupTrust(users2.getGroupId()) + ") and ObjectGroupID = '" + supportCaseXML.getObjectGroupName() + "')))");
            } else {
                queryString.append(" and supportCaseId in (Select SupportCaseID from SupportCaseDeviceLinks where DeviceID in(Select DeviceID from Ogmdevice where objectGroupId in (Select ObjectGroupID from ObjectGroupTranslations where ObjectGroupID in (Select ObjectGroupID from ObjectGroups where GroupID in (" + TCMUtil.getGroupTrust(users2.getGroupId()) + "))and ObjectGroupName LIKE '%" + supportCaseXML.getObjectGroupName() + "%')))");
            }
        }

        if (supportCaseXML.getAssetID() != null) {
            queryString.append(" and supportCaseId in (Select SupportCaseID from SupportCaseDeviceLinks where DeviceID in (Select DeviceID from Device where AssetID = '" + supportCaseXML.getAssetID() + "'))");
        }

        if (supportCaseXML.getOptionalCountryID() != null) {
            if (String.valueOf(supportCaseXML.getOptionalCountryID()).matches("[0-9]+")) {
                queryString.append(" and supportCaseId in (Select SupportCaseID from SupportCaseDeviceLinks where DeviceID in (Select DeviceID from Device where AddressID in (Select AddressID from Addresses where CountryID in(Select CountryID from Country where CountryID = '" + supportCaseXML.getOptionalCountryID() + "'))))");
            } else {
                queryString.append(" and supportCaseId in (Select SupportCaseID from SupportCaseDeviceLinks where DeviceID in (Select DeviceID from Device where AddressID in (Select AddressID from Addresses where CountryID in(Select CountryID from Country where CountryName LIKE '%" + supportCaseXML.getOptionalCountryID() + "%'))))");
            }
        }

        List<String> supportCaseStatusIdList = new ArrayList<String>();
        if (!supportCaseXML.getSupportCaseStatuses().isEmpty()) {
            supportCaseStatusIdList = supportCaseXML.getSupportCaseStatuses().get(0).getSupportCaseStatusID();
            queryString.append(" and supportCaseStatusId in (:supportCaseStatusIdList)");
        }

        List<String> supportCaseTypesIdList = new ArrayList<String>();
        if (!supportCaseXML.getSupportCaseTypes().isEmpty()) {
            supportCaseTypesIdList = supportCaseXML.getSupportCaseTypes().get(0).getSupportCaseTypeID();
            queryString.append(" and supportCaseTypeId in (:supportCaseTypesIdList)");
        }

        List<String> supportCasePriorityIdList = new ArrayList<String>();
        if (!supportCaseXML.getSupportCasePriorities().isEmpty()) {
            supportCasePriorityIdList = supportCaseXML.getSupportCasePriorities().get(0).getSupportCasePriorityID();
            queryString.append(" and supportCasePriorityId in (:supportCasePriorityIdList)");
        }

        queryString.append(" order by supportCaseNumber desc ");

        SQLQuery query = session.createSQLQuery(queryString.toString());
        query.addScalar("supportCaseId", Hibernate.STRING);
//        query.addScalar("supportCaseNumber", Hibernate.LONG);
//        query.addScalar("supportCaseSubject", Hibernate.STRING);
//        query.addScalar("durationActual", Hibernate.INTEGER);
//        query.addScalar("reportedDate", Hibernate.TIMESTAMP);
//        query.addScalar("supportCasePriorityID", Hibernate.STRING);
//        query.addScalar("supportCaseStatusID", Hibernate.STRING);
//        query.addScalar("supportCaseTypeID", Hibernate.STRING);
//        query.addScalar("impactsGroupId", Hibernate.STRING);
//        query.addScalar("costCenterId", Hibernate.STRING);
        if (!supportCaseStatusIdList.isEmpty()) {
            query.setParameterList("supportCaseStatusIdList", supportCaseStatusIdList);
        }
        if (!supportCaseTypesIdList.isEmpty()) {
            query.setParameterList("supportCaseTypesIdList", supportCaseTypesIdList);
        }
        if (!supportCasePriorityIdList.isEmpty()) {
            query.setParameterList("supportCasePriorityIdList", supportCasePriorityIdList);
        }
//        query.setResultTransformer(Transformers.aliasToBean(SupportCases.class));
        list = query.setMaxResults(200).list();
        return list;
    }

    public List<SupportCaseSubjectTemplates> getSupportCaseSubjectTemplatesByGroupId(Session session, String groupId, String countryId) {
        return session.getNamedQuery("getSupportCaseSubjectTemplatesByGroupId").setString("groupId", groupId).setString("countryId", countryId).setMaxResults(200).list();
    }

    public List<SupportCaseSubjectTemplates> getSupportCaseSubjectTemplatesByGroupIdSearch(Session session, String groupId, String countryId, String searchString) {
        return session.getNamedQuery("getSupportCaseSubjectTemplatesByGroupIdSearch").setString("groupId", groupId).setString("countryId", countryId).setString("supportCaseSubject", searchString).setMaxResults(200).list();
    }

    public List<SupportCaseDeviceLinks> getSupportCaseDeviceLinks(String supportCaseId, Session session) {
        return session.getNamedQuery("getSupportCaseDeviceLinksBySupportCaseId").setString("supportCaseId", supportCaseId).list();
    }

    public List<SupportCaseStatuses> getSupportCaseStatusesByIsClosed(Session session, int isClosed) {
        return session.getNamedQuery("getSupportCaseStatusByIsClosed").setInteger("isClosed", isClosed).list();
    }

    public SupportImpactTranslations getSupportImpactTranslationsByIds(String supportCaseImpactId, Integer countryId, Session session) {
        return (SupportImpactTranslations) session.getNamedQuery("getSupportImpactTranslationsByIds").setParameter("supportCaseImpactId", supportCaseImpactId).setInteger("countryId", countryId).uniqueResult();
    }

    public List<SupportOrganizations> getSupportOrganizationsByManagedByGroupId(String groupId, Session session) {
        return session.getNamedQuery("getSupportOrganizationsByManagedByGroupId").setString("managedByGroupId", groupId).list();
    }

    public SupportOrganizations getSupportOrganizationsById(String supportOrganizationId, Session session) {
        return (SupportOrganizations) session.getNamedQuery("getSupportOrganizationsById").setString("supportOrganizationId", supportOrganizationId).uniqueResult();
    }

    public SupportPriorityTranslations getSupportPriorityTranslationsByIds(String supportCasePriorityId, Integer countryId, Session session) {
        return (SupportPriorityTranslations) session.getNamedQuery("getSupportPriorityTranslationsByIds").setParameter("supportCasePriorityId", supportCasePriorityId).setInteger("countryId", countryId).uniqueResult();
    }

    public SupportStatusTranslations getSupportStatusTranslationsByIds(String supportCaseStatusId, Integer countryId, Session session) {
        return (SupportStatusTranslations) session.getNamedQuery("getSupportStatusTranslationsByIds").setParameter("supportCaseStatusId", supportCaseStatusId).setInteger("countryId", countryId).uniqueResult();
    }

    public SupportTypeTranslations getSupportTypeTranslationsByIds(String supportCaseTypeId, Integer countryId, Session session) {
        return (SupportTypeTranslations) session.getNamedQuery("getSupportTypeTranslationsByIds").setParameter("supportCaseTypeId", supportCaseTypeId).setInteger("countryId", countryId).uniqueResult();
    }

    public boolean saveSupportCases(SupportCases supportCases, Transaction tx, Session session) {
        try {
            session.saveOrUpdate(supportCases);
            tx.commit();
            return true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx.wasCommitted()) {
                tx.rollback();
            }
            return false;
        }
    }

//    public List<SupportCaseSubjectTemplates> getSupportCaseSubjectTemplatesByGroupId(Session session, String groupId) {
//        return session.getNamedQuery("getSupportCaseSubjectTemplatesByGroupId").setString("groupId", groupId).list();
//
//    }
    public List<SupportTypeTranslations> getSupportTypeTranslationsByCountryId(Session session, String countryId) {
        return session.getNamedQuery("getSupportTypeTranslationsByCountryId").setString("countryId", countryId).setMaxResults(100).list();
    }

    public List<SupportCaseTypes> getSupportCaseTypesByIds(Session session, List<String> supportCaseTypeId) {
        return session.getNamedQuery("getSupportCaseTypesByIds").setParameterList("supportCaseTypeId", supportCaseTypeId).list();
    }

    public List<SupportImpactTranslations> getSupportImpactTranslationsByCountryId(Session session, String countryId) {
        return session.getNamedQuery("getSupportImpactTranslationsByCountryId").setString("countryId", countryId).setMaxResults(100).list();
    }

    public List<SupportCaseImpacts> getSupportCaseImpactsByIDs(Session session, List<String> supportCaseImpactId) {
        return session.getNamedQuery("getSupportCaseImpactsByIDs").setParameterList("supportCaseImpactId", supportCaseImpactId).list();
    }

    public List<SupportPriorityTranslations> getSupportPriorityTranslationsByCountryId(Session session, String countryId) {
        return session.getNamedQuery("getSupportPriorityTranslationsByCountryId").setString("countryId", countryId).setMaxResults(100).list();
    }

    public List<SupportCasePriorities> getSupportCasePrioritiesByIds(Session session, List<String> supportCasePriorityId) {
        return session.getNamedQuery("getSupportCasePrioritiesByIds").setParameterList("supportCasePriorityId", supportCasePriorityId).list();
    }

    public List<SupportStatusTranslations> getSupportStatusTranslationsByCountryId(Session session, String countryId, int maxResult) {
        if (maxResult == 0) {
            return session.getNamedQuery("getSupportStatusTranslationsByCountryId").setString("countryId", countryId).list();
        }
        return session.getNamedQuery("getSupportStatusTranslationsByCountryId").setString("countryId", countryId).setMaxResults(maxResult).list();
    }

    public List<SupportStatusTranslations> getSupportStatusTranslationsByCountryIdOrderBy(Session session, String countryId, int maxResult) {
        if (maxResult == 0) {
            return session.getNamedQuery("getSupportStatusTranslationsByCountryIdOrderBy").setString("countryId", countryId).list();
        }
        return session.getNamedQuery("getSupportStatusTranslationsByCountryIdOrderBy").setString("countryId", countryId).setMaxResults(maxResult).list();
    }

    public List<SupportCaseStatuses> getSupportCaseStatusesByIds(Session session, List<String> supportCaseStatusId) {
        return session.getNamedQuery("getSupportCaseStatusesByIds").setParameterList("supportCaseStatusId", supportCaseStatusId).list();
    }

    public Object getSupportCasesBySupportCaseId(Session session, String supportCaseId) {
        return session.getNamedQuery("getSupportCasesById").setParameter("supportCaseId", supportCaseId).uniqueResult();
    }

    public List<SupportCaseResolutionTemplates> getSupportCaseResolutionTemplatesByGroupId(Session session, String groupId, String countryId) {
        return session.getNamedQuery("getSupportCaseResolutionTemplatesByGroupId").setString("groupId", groupId).setString("countryId", countryId).setMaxResults(200).list();
//        Criteria c = session.createCriteria(SupportCaseResolutionTemplates.class);
//        c.add(Restrictions.eq("groupId", groupId));
//        c.add(Restrictions.eq("countryId", Integer.parseInt(countryId)));
//        c.addOrder(Order.asc("createdDate")).setMaxResults(200);
//        c.addOrder(Order.asc("resolutionSubject"));
//        return c.list();
    }

    public List<SupportCaseResolutionTemplates> getSupportCaseResolutionTemplatesByIds(Session session, List<String> resolutionTemplateId) {
        return session.getNamedQuery("getSupportCaseResolutionTemplatesByIds").setParameterList("resolutionTemplateId", resolutionTemplateId).list();
    }

    public List<SupportCaseResolutionTemplates> getSupportCaseResolutionTemplatesByIdsAnsSearch(Session session, List<String> resolutionTemplateId, String SearchString) {
        return session.getNamedQuery("getSupportCaseResolutionTemplatesByIdsAnsSearch").setParameterList("resolutionTemplateId", resolutionTemplateId).setString("resolutionSubject", SearchString).list();
    }

    public List<SupportOrgVisibleToGroups> getSupportOrgVisibleToGroupsByGroupId(Session session, String groupId) {
        return session.getNamedQuery("getSupportOrgVisibleToGroupsByGroupId").setString("groupId", groupId).list();
    }

    public Object getSupportOrgVisibleToGroupsByIds(String supportOrganizationId, String groupId, Session session) {
        return session.getNamedQuery("getSupportOrgVisibleToGroupsByIds").setString("supportOrganizationId", supportOrganizationId).setString("groupId", groupId).uniqueResult();
    }

    public List<SupportOrganizations> getSupportOrganizationsByIdsAndSearchString(Session session, List<String> supportOrganizationId, String searchString, int maxResult) {
        return session.getNamedQuery("getSupportOrganizationsByIdsAndSearchString").setParameterList("supportOrganizationId", supportOrganizationId).setString("organizationName", searchString).setMaxResults(maxResult).list();
    }

    public SupportCaseSubjectTemplates getSupportCaseSubjectTemplatesById(Session session, String supportCaseSubjectId) {
        return (SupportCaseSubjectTemplates) session.getNamedQuery("getSupportCaseSubjectTemplatesById").setString("supportCaseSubjectId", supportCaseSubjectId).uniqueResult();
    }

    public List<SupportCaseDeviceLinks> getSupportCaseDeviceLinksBySupportCaseIdDeviceId(Session session, String deviceid, String supportCaseId) {
        return session.getNamedQuery("getSupportCaseDeviceLinksBySupportCaseIdDeviceId").setString("supportCaseId", supportCaseId).setString("deviceId", deviceid).list();
    }

    public List<SupportCases> getSupportCasesByUserId(String loggedInUserId, Session session) {
        return session.getNamedQuery("getSupportCasesByAssignedToUserId").setString("assignedToUserId", loggedInUserId).list();
    }

    Object getSupportOrganizationUserByIds(String supportOrganizationId, String userId, Session session) {
        return session.getNamedQuery("getSupportOrganizationUsersByIds").setString("supportOrganizationId", supportOrganizationId).setString("userId", userId).uniqueResult();
    }

    Set<String> getsupportOrgGroupIdsList(List<SupportOrgVisibleToGroups> sovtgList) {
        Set<String> supportOrgGroupIdsList = new HashSet<String>();
        for (SupportOrgVisibleToGroups sovtg : sovtgList) {
            supportOrgGroupIdsList.add(sovtg.getId().getGroupId());
        }
        return supportOrgGroupIdsList;
    }

//    private List<String> getSupportCaseIdList(List<SupportCaseDeviceLinks> supportCaseDeviceLinksList) {
//        List<String> supportCaseIdList = new ArrayList<String>();
//        for (SupportCaseDeviceLinks scdl : supportCaseDeviceLinksList) {
//            supportCaseIdList.add(scdl.getId().getSupportCaseId());
//        }
//        return supportCaseIdList;
//    }
    public boolean updateSupportCaseDetails(Session session, SupportCases sc, SupportCaseDeviceLinks scdl, ObjectCostCenters costCenters, boolean isDeleted) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            if (scdl != null) {
                session.saveOrUpdate(scdl);
            }
            session.update(sc);
            if(costCenters != null){
                if(isDeleted){
                    session.delete(costCenters);
                }else{
                    session.saveOrUpdate(costCenters);
                }
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    boolean addSupportCase(Session session, SupportCases sc, SupportCaseDeviceLinks scdl, SupportCaseSubjectTemplates scst, ObjectCostCenters costCenters) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(sc);
            session.flush();
            session.clear();
            if (scdl != null) {
                session.saveOrUpdate(scdl);
            }
            if (scst != null) {
                session.save(scst);
            }
            if(costCenters != null){
                session.saveOrUpdate(costCenters);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public List<SupportCases> getSupportCasesBySupportCaseIds(Session session, List<String> supportCaseId) {
        return session.getNamedQuery("getSupportCasesByIds").setParameterList("supportCaseId", supportCaseId).list();
    }

    public List<SupportOrganizations> getSupportOrganizationsByOrgNamemanagedByGroupId(Session session, List<String> managedByGroupId, String organizationName) {
        return session.getNamedQuery("getSupportOrganizationsByOrgNamemanagedByGroupId").setParameterList("managedByGroupId", managedByGroupId).setString("organizationName", organizationName).setMaxResults(200).list();
    }

    public List<SupportOrganizations> getSupportOrganizationsBymanagedByGroupId(Session session, List<String> managedByGroupId) {
        return session.getNamedQuery("getSupportOrganizationsBymanagedByGroupId").setParameterList("managedByGroupId", managedByGroupId).setMaxResults(200).list();
    }

    public List<SupportOrgVisibleToGroups> getSupportOrgVisibleToGroupsById(Session session, String supportOrganizationId) {
        return session.getNamedQuery("getSupportOrgVisibleToGroupsById").setString("supportOrganizationId", supportOrganizationId).list();
    }

    public List<SupportOrgVisibleToGroups> getSupportOrgVisibleToGroupsByGroupIds(Session session, String supportOrganizationId, List<String> groupIds, int maxResult) {
        if (maxResult == 0) {
            return session.getNamedQuery("getSupportOrgVisibleToGroupsByGroupIds").setString("supportOrganizationId", supportOrganizationId).setParameterList("groupId", groupIds).list();
        } else {
            return session.getNamedQuery("getSupportOrgVisibleToGroupsByGroupIds").setString("supportOrganizationId", supportOrganizationId).setParameterList("groupId", groupIds).setMaxResults(maxResult).list();
        }
    }

    List<SupportOrganizations> getSupportOrganizationsByIdAndTrustedGroupIDs(String searchString, List<String> list, Session session) {
        return session.getNamedQuery("getSupportOrganizationsByIdAndTrustedGroupIDs").setString("supportOrganizationId", searchString).setParameterList("managedByGroupId", list).list();
    }
    public List<SupportOrganizations> getSupportOrganizationsByIds(Session session, List<String> supportOrganizationId, int maxResult) {
        return session.getNamedQuery("getSupportOrganizationsByIds").setParameterList("supportOrganizationId", supportOrganizationId).setMaxResults(maxResult).list();
    }

    boolean saveSupportOrganizationUsers(SupportOrganizationUsers sou, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(sou);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    Object getSupportOrganizationUsersByUserAndSupportOrganization(String userId, String supportOrganizationId, Session session) {
        return session.getNamedQuery("getSupportOrganizationUsersByUserAndSupportOrganization").setString("userId", userId).setString("supportOrganizationId", supportOrganizationId).uniqueResult();
    }

}
