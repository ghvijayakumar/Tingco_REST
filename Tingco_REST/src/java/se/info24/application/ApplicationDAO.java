/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.AgreementBillingData;
import se.info24.pojo.AgreementItemTypeTranslations;
import se.info24.pojo.AgreementItems;
import se.info24.pojo.AgreementRoleTypeTranslations;
import se.info24.pojo.AgreementRoles;
import se.info24.pojo.AgreementRolesId;
import se.info24.pojo.AgreementStatusTranslations;
import se.info24.pojo.AgreementTypeTranslations;
import se.info24.pojo.Agreements;
import se.info24.pojo.ApplicationEmailTemplates;
import se.info24.pojo.ApplicationGroupMemberships;
import se.info24.pojo.ApplicationModuleServices;
import se.info24.pojo.ApplicationModuleTranslations;
import se.info24.pojo.ApplicationModules;
import se.info24.pojo.ApplicationPackageModules;
import se.info24.pojo.ApplicationPackageTranslations;
import se.info24.pojo.ApplicationPackages;
import se.info24.pojo.ApplicationSolutionTranslations;
import se.info24.pojo.ApplicationSolutions;
import se.info24.pojo.ApplicationTranslations;
import se.info24.pojo.Applications;
import se.info24.pojo.GroupApplicationPackages;
import se.info24.pojo.GroupApplicationPackagesId;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupVisibleApplicationModules;
import se.info24.pojo.GroupVisibleApplicationPackages;
import se.info24.pojo.GroupVisibleApplicationSolutions;
import se.info24.pojo.SystemMessageTranslations;
import se.info24.pojo.SystemMessages;
import se.info24.pojo.SystemMessagesToApplications;
import se.info24.pojo.UserSessions2;
import se.info24.util.TCMUtil;

/**
 *
 * @author Sridhar Dasari; Sekhar
 */
public class ApplicationDAO {

    public Set<String> getApplicationModuleIds(List<ApplicationPackageModules> appPackageModulesList) {
        Set<String> applicationModuleIdsSet = new HashSet<String>();
        for (ApplicationPackageModules apm : appPackageModulesList) {
            applicationModuleIdsSet.add(apm.getApplicationModules().getApplicationModuleId());
        }
        return applicationModuleIdsSet;
    }

    public List<ApplicationModuleServices> getApplicationModuleServices(Set<String> applicationModuleIdsList, Session session) {
        return session.getNamedQuery("getApplicationModuleServicesbyAppModuleIds").setParameterList("applicationModuleId", applicationModuleIdsList).list();
    }

    public Set<String> getApplicationPackageIds(List<GroupApplicationPackages> groupappPackagesList) {
        Set<String> applicationPackageIdsSet = new HashSet<String>();
        for (GroupApplicationPackages gap : groupappPackagesList) {
            applicationPackageIdsSet.add(gap.getApplicationPackages().getApplicationPackageId());
        }
        return applicationPackageIdsSet;
    }

    public List<GroupApplicationPackages> getGroupApplicationPackagesbyGroupId(String groupId, Session session) {
        return session.getNamedQuery("getGroupAppPackagesByGroupId").setString("groupId", groupId).list();
    }

    public GroupApplicationPackages getGroupApplicationPackagesbyGroupIdAndPackageId(String groupId, String applicationPackageId, Session session) {
        return (GroupApplicationPackages) session.getNamedQuery("getGroupAppPackagesByIds").setString("groupId", groupId).setString("applicationPackageId", applicationPackageId).uniqueResult();
    }

    public void saveApplication(Applications applications, Session session) {
        Transaction tx = null;
        try {
            if (applications != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(applications);
                tx.commit();
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public void removeApplication(Applications applications, Session session) {
        Transaction tx = null;
        try {
            if (applications != null) {
                tx = session.beginTransaction();
                session.delete(applications);
                tx.commit();
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public void removeApplicationByID(String applicationId, Session session) {
        removeApplication(getApplication(applicationId, session), session);
    }

    public Applications getApplication(String applicationId, Session session) {
        return (Applications) session.getNamedQuery("getApplicationsById").setString("applicationId", applicationId).uniqueResult();
    }

    public List<ApplicationTranslations> getApplicationsByCountryId(int countryId, Session session) {
        List<ApplicationTranslations> applicationTransList = null;
        try {
            applicationTransList = session.getNamedQuery("getApplicationTranslationsById").setInteger("countryId", countryId).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
        }
        return applicationTransList;
    }

    public List<ApplicationTranslations> getApplicationTranslationsByIdSearch(int countryId, String searchField, Session session) {
        List<ApplicationTranslations> applicationTransList = null;
        try {
            applicationTransList = session.getNamedQuery("getApplicationTranslationsByIdSearch").setInteger("countryId", countryId).setString("searchField", searchField).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
        }
        return applicationTransList;
    }

    public void saveApplicationTranslations(ApplicationTranslations appTrans, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(appTrans);
            tx.commit();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public void saveAppGroupMembership(ApplicationGroupMemberships appGroupMem, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(appGroupMem);
            tx.commit();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public void removeAppGroupMembership(ApplicationGroupMemberships appGroupMem, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(appGroupMem);
            tx.commit();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public SystemMessageTranslations getSystemMessagesByApplicationId(Session session, String applicationId, int countryId) {
        SystemMessageTranslations sysMsgTrans = null;
        GregorianCalendar gc = new GregorianCalendar();
        Date date = gc.getTime();
        try {
            List<SystemMessages> sysMsgs = session.getNamedQuery("getTopOneActiveSystemMessage").setString("isEnabled", "1").setParameter("activeFromDate", date).setParameter("activeToDate", date).list();
            if (!sysMsgs.isEmpty()) {
                for (SystemMessages sm : sysMsgs) {
                    List<SystemMessagesToApplications> sysMsgApp = session.getNamedQuery("getSystemMessagesToApplicationsById").setString("applicationId", applicationId).setString("systemMessageId", sm.getSystemMessageId()).list();
                    if (!sysMsgApp.isEmpty()) {
                        sysMsgTrans = (SystemMessageTranslations) session.getNamedQuery("getSystemMessageTextByCountryId").setInteger("countryId", countryId).setString("systemMessageId", sm.getSystemMessageId()).list().get(0);
                    }
                    break;
                }
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        return sysMsgTrans;
    }

    public GroupApplicationPackages getGroupApplicationPackagesbyIds(String groupId, String applicationPackageId, String agreementId, Session session) {
        return (GroupApplicationPackages) session.getNamedQuery("getGroupApplicationPackages").setString("groupId", groupId).setString("applicationPackageId", applicationPackageId).setString("agreementId", agreementId).uniqueResult();
    }

    boolean addOrderforSolutionPackage(String groupId, String applicationPackageId, String agreementId, Session session, UserSessions2 sessions2) {
        boolean result = false;
        try {
            GroupApplicationPackages groupApplicationPackages = getGroupApplicationPackagesbyIds(groupId, applicationPackageId, agreementId, session);
            if (groupApplicationPackages != null) {
                GregorianCalendar gc = new GregorianCalendar();
                groupApplicationPackages.setActiveFromDate(gc.getTime());
                gc.add(GregorianCalendar.YEAR, 15);
                groupApplicationPackages.setActiveToDate(gc.getTime());
                groupApplicationPackages.setIsEnabled(1);
                groupApplicationPackages.setIsTrial(0);
                groupApplicationPackages.setOrderedByUserId(sessions2.getUserId());
                groupApplicationPackages.setLastUpdatedByUserId(sessions2.getUserId());
                gc = new GregorianCalendar();
                groupApplicationPackages.setCretedDate(gc.getTime());
                groupApplicationPackages.setUpdatedDate(gc.getTime());
                Transaction tx = session.beginTransaction();
                session.saveOrUpdate(groupApplicationPackages);
                tx.commit();
                result = true;
            } else {
                groupApplicationPackages = new GroupApplicationPackages();
                GroupApplicationPackagesId gapid = new GroupApplicationPackagesId();
                gapid.setGroupId(groupId);
                gapid.setApplicationPackageId(applicationPackageId);
                groupApplicationPackages.setId(gapid);
                Agreements agreements = new Agreements();
                agreements.setAgreementId(agreementId);
                groupApplicationPackages.setAgreements(agreements);
                groupApplicationPackages.setComment(null);
                GregorianCalendar gc = new GregorianCalendar();
                groupApplicationPackages.setActiveFromDate(gc.getTime());
                gc.add(GregorianCalendar.YEAR, 15);
                groupApplicationPackages.setActiveToDate(gc.getTime());
                groupApplicationPackages.setIsEnabled(1);
                groupApplicationPackages.setIsTrial(0);
                groupApplicationPackages.setIsPendingDelete(0);
                groupApplicationPackages.setDeleteAfterThisDate(null);
                groupApplicationPackages.setOrderedByUserId(sessions2.getUserId());
                groupApplicationPackages.setLastUpdatedByUserId(sessions2.getUserId());
                gc = new GregorianCalendar();
                groupApplicationPackages.setCretedDate(gc.getTime());
                groupApplicationPackages.setUpdatedDate(gc.getTime());
                Transaction tx = session.beginTransaction();
                session.saveOrUpdate(groupApplicationPackages);
                tx.commit();
                result = true;
            }
        } catch (Exception e) {
            result = false;
            TCMUtil.exceptionLog(getClass().getName(), e);
        }
        return result;
    }

    boolean addTrialforSolutionPackage(String groupId, String applicationPackageId, String agreementId, Session session, UserSessions2 sessions2) {
        boolean result = false;
        try {
            GroupApplicationPackages groupApplicationPackages = getGroupApplicationPackagesbyIds(groupId, applicationPackageId, agreementId, session);
            if (groupApplicationPackages != null) {
                if (groupApplicationPackages.getIsTrial() == 1) {
                    result = false;
                } else {
                    groupApplicationPackages.setComment(null);
                    GregorianCalendar gc = new GregorianCalendar();
                    groupApplicationPackages.setActiveFromDate(gc.getTime());
                    gc.add(GregorianCalendar.DATE, 30);
                    groupApplicationPackages.setActiveToDate(gc.getTime());
                    groupApplicationPackages.setIsEnabled(1);
                    groupApplicationPackages.setIsTrial(1);
                    groupApplicationPackages.setIsPendingDelete(0);
                    groupApplicationPackages.setDeleteAfterThisDate(null);
                    groupApplicationPackages.setOrderedByUserId(sessions2.getUserId());
                    groupApplicationPackages.setLastUpdatedByUserId(sessions2.getUserId());
                    gc = new GregorianCalendar();
                    groupApplicationPackages.setCretedDate(gc.getTime());
                    groupApplicationPackages.setUpdatedDate(gc.getTime());
                    Transaction tx = session.beginTransaction();
                    session.saveOrUpdate(groupApplicationPackages);
                    tx.commit();
                    result = true;
                }
            } else {
                groupApplicationPackages = new GroupApplicationPackages();
                GroupApplicationPackagesId gapid = new GroupApplicationPackagesId();
                gapid.setGroupId(groupId);
                gapid.setApplicationPackageId(applicationPackageId);
                groupApplicationPackages.setId(gapid);
                Agreements agreements = new Agreements();
                agreements.setAgreementId(agreementId);
                groupApplicationPackages.setAgreements(agreements);
                groupApplicationPackages.setComment(null);
                GregorianCalendar gc = new GregorianCalendar();
                groupApplicationPackages.setActiveFromDate(gc.getTime());
                gc.add(GregorianCalendar.DATE, 30);
                groupApplicationPackages.setActiveToDate(gc.getTime());
                groupApplicationPackages.setIsEnabled(1);
                groupApplicationPackages.setIsTrial(1);
                groupApplicationPackages.setIsPendingDelete(0);
                groupApplicationPackages.setDeleteAfterThisDate(null);
                groupApplicationPackages.setOrderedByUserId(sessions2.getUserId());
                groupApplicationPackages.setLastUpdatedByUserId(sessions2.getUserId());
                gc = new GregorianCalendar();
                groupApplicationPackages.setCretedDate(gc.getTime());
                groupApplicationPackages.setUpdatedDate(gc.getTime());
                Transaction tx = session.beginTransaction();
                session.saveOrUpdate(groupApplicationPackages);
                tx.commit();
                result = true;
            }
        } catch (Exception e) {
            result = false;
            TCMUtil.exceptionLog(getClass().getName(), e);
        }
        return result;
    }

    boolean cancelOrderforSolutionPackage(String groupId, String applicationPackageId, String agreementId, Session session, UserSessions2 sessions2) {
        boolean result = false;
        try {
            GroupApplicationPackages groupApplicationPackages = getGroupApplicationPackagesbyIds(groupId, applicationPackageId, agreementId, session);
            if (groupApplicationPackages != null) {
                GregorianCalendar gc = new GregorianCalendar();
                groupApplicationPackages.setActiveFromDate(gc.getTime());
                gc.add(GregorianCalendar.DATE, 90);
                groupApplicationPackages.setActiveToDate(gc.getTime());
                groupApplicationPackages.setIsEnabled(1);
                groupApplicationPackages.setIsTrial(0);
                groupApplicationPackages.setIsPendingDelete(1);
                gc = new GregorianCalendar();
                gc.add(GregorianCalendar.DATE, 100);
                groupApplicationPackages.setDeleteAfterThisDate(gc.getTime());
                groupApplicationPackages.setOrderedByUserId(sessions2.getUserId());
                groupApplicationPackages.setLastUpdatedByUserId(sessions2.getUserId());
                gc = new GregorianCalendar();
                groupApplicationPackages.setCretedDate(gc.getTime());
                groupApplicationPackages.setUpdatedDate(gc.getTime());
                Transaction tx = session.beginTransaction();
                session.saveOrUpdate(groupApplicationPackages);
                tx.commit();
                result = true;
            }
        } catch (Exception e) {
            result = false;
            TCMUtil.exceptionLog(getClass().getName(), e);
        }
        return result;
    }

    Set<String> getAgreementRolesByGroupIdsList(Set<String> groupIdList, Session session) {
        List<AgreementRoles> agreementRolesList = session.getNamedQuery("getAgreementRoles").setParameterList("groupId", groupIdList).setMaxResults(50).list();
        return getAgreementIds(agreementRolesList);
    }

    List<AgreementRoles> getAgreementRolesByAgreementId(String agreementId, int maxResult, Session session) {
        return session.getNamedQuery("getAgreementRolesByAgreementId").setString("agreementId", agreementId).setMaxResults(200).list();
//        return getAgreementIds(agreementRolesList);
    }

    Object getAgreementRolesByIds(String agreementId, String objectId, Session session) {
        return session.getNamedQuery("getAgreementRolesByIds").setString("agreementId", agreementId).setString("objectId", objectId).uniqueResult();
    }

    Set<String> getAgreementIds(List<AgreementRoles> agreementRolesList) {
        Set<String> agreementidset = new HashSet<String>();
        for (AgreementRoles agr : agreementRolesList) {
            agreementidset.add(agr.getId().getAgreementId());
        }
        return agreementidset;
    }

    List<AgreementRoles> getAgreementRoles(Session session, Set<String> agreementIdSet) {
        return session.getNamedQuery("getAgreementRolesOrderByObjectName").setParameterList("agreementId", agreementIdSet).list();
    }

    List<AgreementRoleTypeTranslations> getAgreementRoleTypeTranslations(Session session, int countryId) {
        return session.getNamedQuery("getAllAgreementRoleTypeTranslations").setInteger("countryId", countryId).list();
    }

    public List<Agreements> getAgreements(Session session, Set<String> agreementIdSet) {
        return session.getNamedQuery("getAgreements").setParameterList("agreementId", agreementIdSet).list();
    }

    List<ApplicationModuleTranslations> getApplicationModuleTranslations(Session session) {
        return session.getNamedQuery("getApplicationModuleTranslations").list();
    }

    List<ApplicationPackageTranslations> getApplicationPackageTranslations(Session session) {
        return session.getNamedQuery("getApplicationPackageTranslations").list();
    }

    List<ApplicationPackages> getApplicationPackages(List<String> appSolutionIdList, Session session) {
        return session.getNamedQuery("getApplicationPackages").setParameterList("applicationSolutionId", appSolutionIdList).list();
    }

    List<ApplicationSolutionTranslations> getApplicationSolutionTranslations(Session session) {
        return session.getNamedQuery("getApplicationSolutionTranslations").list();
    }

    List<ApplicationSolutions> getApplicationSolutions(Session session, String applicationId) {
        return session.getNamedQuery("getApplicationSolutions").setString("applicationId", applicationId).list();
    }

    public List<GroupVisibleApplicationModules> getGroupVisibleAppModules(Session session, String groupId) {
        return session.getNamedQuery("getGroupVisibleApplicationModulesByGroupId").setString("groupId", groupId).list();
    }

    List<GroupVisibleApplicationPackages> getGroupVisibleAppPackages(Session session, String groupId, List<ApplicationPackages> applicationPackagesList) {
        List<String> appPackageId = new ArrayList<String>();
        for (ApplicationPackages ap : applicationPackagesList) {
            appPackageId.add(ap.getApplicationPackageId());
        }
        return session.getNamedQuery("getGroupVisibleApplicationPackages").setString("groupId", groupId).setParameterList("applicationPackageId", appPackageId).list();
    }

    List<GroupVisibleApplicationSolutions> getGroupVisibleApplicationSolutions(Session session, List<String> appSolutionIdList) {
        return session.getNamedQuery("getGroupVisibleApplicationSolutions").setParameterList("applicationSolutionId", appSolutionIdList).list();
    }

    public GroupVisibleApplicationSolutions getGroupVisibleApplicationSolutionsByIds(String groupId, String applicationSolutionId, Session session) {
        return (GroupVisibleApplicationSolutions) session.getNamedQuery("getGroupVisibleApplicationSolutionsbyIds").setString("groupId", groupId).setString("applicationSolutionId", applicationSolutionId).uniqueResult();
    }

    List<ApplicationModules> getapplicationModules(Session session, String applicationId) {
        return session.getNamedQuery("getApplicationModules").setString("applicationId", applicationId).list();
    }

    List<GroupApplicationPackages> getgroupApplicationPackages(List<String> appPackageIdList, String groupId, Session session) {
        return session.getNamedQuery("getGroupAppPackages").setParameterList("applicationPackageId", appPackageIdList).setString("groupId", groupId).list();
    }

    public List<GroupVisibleApplicationSolutions> getGroupVisibleApplicationSolutionsbyGroupid(String groupId, Session session) {
        try {
            List<GroupVisibleApplicationSolutions> gvas = session.getNamedQuery("getGroupVisibleApplicationSolutionsbygroupid").setString("groupId", groupId).list();
            return gvas;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public List<GroupVisibleApplicationPackages> getGroupVisibleApplicationPackagesbyGroupid(String groupId, Session session) {
        try {
            List<GroupVisibleApplicationPackages> gvas = session.getNamedQuery("getGroupVisibleApplicationPackagesByGroupId").setString("groupId", groupId).list();
            return gvas;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public GroupVisibleApplicationPackages getGroupVisibleApplicationPackagesByIds(String groupId, String applicationPackageId, Session session) {
        return (GroupVisibleApplicationPackages) session.getNamedQuery("getGroupVisibleApplicationPackagesbyIds").setString("groupId", groupId).setString("applicationPackageId", applicationPackageId).uniqueResult();
    }

    public List<ApplicationSolutionTranslations> getApplicationSolutionTranslationsByidandCountryid(List<String> applicationSolutionIdsList, int countryId, Session session) {
        return session.getNamedQuery("getApplicationSolutionTranslationsByIds").setParameterList("applicationSolutionId", applicationSolutionIdsList).setInteger("countryId", countryId).list();
    }

    public List<ApplicationPackages> getApplicationPackagesByAppSolId(String applicationSolutionId, Session session) {
        try {
            return session.getNamedQuery("getApplicationPackagesByAppSolId").setString("applicationSolutionId", applicationSolutionId).list();
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }
    }

    public boolean isValidApplicationPackageId(String applicationPackageId, String groupid, Session session) {

        try {
            List<GroupVisibleApplicationPackages> gvap = session.getNamedQuery("getGroupVisibleApplicationPackagesByAppPakgId").setString("applicationPackageId", applicationPackageId).list();
            if (gvap.size() > 0) {
                for (GroupVisibleApplicationPackages groupVisibleApplicationPackages : gvap) {
                    if (groupVisibleApplicationPackages.getId().getGroupId().equalsIgnoreCase(groupid)) {
                        return true;
                    }
                }
                return false;
            } else {
                return false;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public List<ApplicationPackageTranslations> getApplicationPackageTranslationsByAppPackID(String applicationPackageId, int countryId, Session session) {
        try {
            return session.getNamedQuery("getApplicationPackageTranslationsByAppPackID").setString("applicationPackageId", applicationPackageId).setInteger("countryId", countryId).list();
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }
    }

    public List<ApplicationPackageModules> getApplicationPackageModulesbyIdsList(Set<String> applicationPackageIdsList, Session session) {
        return session.getNamedQuery("getApplicationPackageModulesByIdsList").setParameterList("applicationPackageId", applicationPackageIdsList).list();
    }

    public List<ApplicationPackageModules> getApplicationPackageModules(String applicationPackageId, Session session) {
        return session.getNamedQuery("getAllApplicationPackageModules").setString("applicationPackageId", applicationPackageId).list();
    }

    public boolean isValidApplicationModuleId(String applicationModuleId, String groupid, Session session) {

        try {
            List<GroupVisibleApplicationModules> gvam = session.getNamedQuery("getGroupVisibleApplicationModulesByAppModuleId").setString("applicationModuleId", applicationModuleId).list();
            if (gvam.size() > 0) {
                for (GroupVisibleApplicationModules groupVisibleApplicationPackages : gvam) {
                    if (groupVisibleApplicationPackages.getId().getGroupId().equalsIgnoreCase(groupid)) {
                        return true;
                    }
                }
                return false;
            } else {
                return false;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    List<Agreements> getAgreements(Set<String> agreementIdList, String searchString, Session session) {
        return session.getNamedQuery("getAgreementsByIdandSearchString").setParameterList("agreementId", agreementIdList).setString("agreementName", searchString).list();
    }

    List<Agreements> getAgreementsByIdsAndSearchNameAndNumber(Set<String> agreementIdList, String searchString, Session session) {
        return session.getNamedQuery("getAgreementsByIdandSearchNameAndNumber").setParameterList("agreementId", agreementIdList).setString("searchString", searchString).list();
    }

    public ApplicationModuleTranslations getAppModuleTransByAppModuleIdAndCountryId(String applicationModuleId, int countryId, Session session) {
        try {
            return (ApplicationModuleTranslations) session.getNamedQuery("getAppModuleTransByAppModuleIdAndCountryId").setString("applicationModuleId", applicationModuleId).setInteger("countryId", countryId).list().get(0);
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }
    }

    List<GroupVisibleApplicationModules> getgroupVisibleApplicationModules(Session session, List<ApplicationModules> appModulesList, String groupId) {
        List<String> appModuleId = new ArrayList<String>();
        for (ApplicationModules am : appModulesList) {
            appModuleId.add(am.getApplicationModuleId());
        }
        return session.getNamedQuery("getGroupVisibleApplicationModules").setParameterList("applicationModuleId", appModuleId).setString("groupId", groupId).list();
    }

    List<ApplicationModuleTranslations> getApplicationModuleTranslations(Session session, String groupId, String countryId) {
        Criteria criteria = session.createCriteria(ApplicationModuleTranslations.class, "amt");
        criteria.createCriteria("amt.applicationModules", "am", CriteriaSpecification.INNER_JOIN);
        criteria.add(Restrictions.eq("amt.id.countryId", Integer.valueOf(countryId)));
        criteria.createAlias("am.groupVisibleApplicationModuleses", "g", CriteriaSpecification.INNER_JOIN);
        criteria.add(Restrictions.eq("g.id.groupId", groupId));
        criteria.addOrder(Order.asc("amt.applicationModuleName"));
        return criteria.list();
    }

    public List<ApplicationModuleTranslations> getAppModuleTransByAppModuleIdListAndCountryId(Session session, Set<String> applicationModuleIdSet, int countryId) {
        return session.getNamedQuery("getAppModuleTransByAppModuleIdListAndCountryId").setParameterList("applicationModuleId", applicationModuleIdSet).setInteger("countryId", countryId).list();
    }

    public List<ApplicationPackageModules> getApplicationPackageModulesByModuleIdList(Session session, Set<String> applicationModuleIdSet) {
        return session.getNamedQuery("getApplicationPackageModulesByModuleIdList").setParameterList("applicationModuleId", applicationModuleIdSet).list();
    }

    public List<GroupApplicationPackages> getGroupAppPackagesForApplicationModule(Session session, String groupid) {
        return session.getNamedQuery("getGroupAppPackagesForApplicationModule").setString("groupId", groupid).list();
    }

    public Set<String> getApplicationPackageId(List<GroupApplicationPackages> gAPList) {
        GregorianCalendar calender = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        Set<String> applicationPackageId = new HashSet<String>();
        for (GroupApplicationPackages groupApplicationPackages : gAPList) {
            if (groupApplicationPackages.getActiveFromDate().before(calender.getTime()) && groupApplicationPackages.getActiveToDate().after(calender.getTime())) {
                applicationPackageId.add(groupApplicationPackages.getId().getApplicationPackageId());
            }
        }
        return applicationPackageId;
    }

    public Set<String> getApplicationModuleId(List<ApplicationPackageModules> aPMList) {
        Set<String> applicationModuleId = new HashSet<String>();
        for (ApplicationPackageModules applicationPackageModules : aPMList) {
            applicationModuleId.add(applicationPackageModules.getId().getApplicationModuleId());
        }
        return applicationModuleId;
    }

    List<Agreements> getAgreementsByAgreementOwnerGroupID(Session session, String agreementOwnerGroupID, int maxResult) {
        return session.getNamedQuery("getAgreementsByAgreementOwnerGroupID").setParameter("agreementOwnerGroupID", agreementOwnerGroupID).setMaxResults(maxResult).list();
    }

    List<AgreementStatusTranslations> getAgreementStatusTranslationsByStatusIds(Session session, Set<String> ids, Integer countryId) {
        return session.getNamedQuery("getAgreementStatusTranslationsByStatusIds").setParameterList("agreementStatusId", ids).setParameter("countryId", countryId).list();
    }

    public Object getAgreementsById(Session session, String agreementId) {
        return session.getNamedQuery("getAgreementsById").setString("agreementId", agreementId).uniqueResult();
    }

    Object getAgreementStatusTranslationsById(String agreementStatusId, Integer countryid, Session session) {
        return session.getNamedQuery("getAgreementStatusTranslationsById").setParameter("agreementStatusId", agreementStatusId).setParameter("countryId", countryid).uniqueResult();
    }

    AgreementTypeTranslations getAgreementTypeTranslationsById(Session session, String agreementTypeId, Integer countryId) {
        return (AgreementTypeTranslations) session.getNamedQuery("getAgreementTypeTranslationsById").setParameter("agreementTypeId", agreementTypeId).setParameter("countryId", countryId).uniqueResult();
    }

    List<AgreementTypeTranslations> getAgreementTypeTranslationsByCountryId(Session session, Integer countryId) {
        return session.getNamedQuery("getAgreementTypeTranslationsByCountryId").setParameter("countryId", countryId).list();
    }

    List<AgreementStatusTranslations> getAgreementStatusTranslationsByCountryId(Session session, Integer countryId) {
        return session.getNamedQuery("getAgreementStatusTranslationsByCountryId").setParameter("countryId", countryId).list();
    }

    Object getAgreementStatusesById(String agreementStatusID, Session session) {
        return session.getNamedQuery("getAgreementStatusesById").setParameter("agreementStatusId", agreementStatusID).uniqueResult();
    }

    List<Agreements> getAgreementsByAgreementOwnerGroupId(Session session, Set<String> groupsList, String groupId, int maxResult) {
        return session.getNamedQuery("getAgreementsByAgreementOwnerGroupIds").setParameterList("agreementOwnerGroupId", groupsList).setParameter("groupId", groupId).setMaxResults(maxResult).list();
    }

    boolean saveAgreementRoles(String agreementId, String objectId, String objectName, String agreementRoleTypeId, Date activeFromDate, Date activeToDate, String userId, Session session) {
        try {
            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            AgreementRoles agreementRoles = new AgreementRoles();
            agreementRoles.setId(new AgreementRolesId(agreementId, objectId));
//          agreementRoles.setAgreements(new Agreements(agreementId));
            agreementRoles.setObjectName(objectName);
            agreementRoles.setAgreementRolesTypes(new se.info24.pojo.AgreementRolesTypes(agreementRoleTypeId));
            agreementRoles.setActiveFromDate(activeFromDate);
            agreementRoles.setActiveToDate(activeToDate);
            agreementRoles.setLastUpdatedByUserId(userId);
            agreementRoles.setUpdatedDate(gc.getTime());
            agreementRoles.setCreatedDate(gc.getTime());
            session.saveOrUpdate(agreementRoles);
            return true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return false;
        }
    }

    boolean saveUpdateAgreements(Agreements agreements, Session session) {
        try {
            session.saveOrUpdate(agreements);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    List<Agreements> getAllAgreementsBySearch(String agreementSearchString, String organizationSearchString, Set<String> downstreamGroupIdsList, Session session, Integer countryId) {
        StringBuffer queryString = new StringBuffer("from Agreements where");
//        Set<String> setIds = new HashSet<String>();
        List<Agreements> agreementList = new ArrayList<Agreements>();
        if (agreementSearchString != null) {
            queryString.append(" (agreementName like '%" + agreementSearchString + "%' or  agreementNumber like '%" + agreementSearchString + "%') and");
        }
        if (organizationSearchString != null) {
            GroupDAO groupDAO = new GroupDAO();
            List<GroupTranslations> list = groupDAO.getGroupTransByGroupIdandSearchString(downstreamGroupIdsList, organizationSearchString, countryId, session);
            if (!list.isEmpty()) {
                downstreamGroupIdsList.clear();
                for (GroupTranslations gt : list) {
//                for (String id : downstreamGroupIdsList) {
//                    if (id.equalsIgnoreCase(gt.getId().getGroupId())) {
                    downstreamGroupIdsList.add(gt.getId().getGroupId());
//                    }
//                }
                }
//                downstreamGroupIdsList.clear();
//                downstreamGroupIdsList.addAll(setIds);
            } else {
                return agreementList;
            }
        }
        queryString.append(" agreementOwnerGroupId in (:downstreamGroupIdsList) order by agreementName asc");
        Query query = session.createQuery(queryString.toString());
        query.setParameterList("downstreamGroupIdsList", downstreamGroupIdsList);
        query.setMaxResults(200);
        return query.list();
    }

    List<Agreements> getAllAgreementsBySearchString(Set<String> downstreamGroupIdsList, String searchString, Session session) {
        StringBuffer queryString = new StringBuffer("from Agreements where");
        queryString.append(" agreementName like '%" + searchString + "%' or  agreementNumber like '%" + searchString + "%' and");
        queryString.append(" agreementOwnerGroupId in (:downstreamGroupIdsList) order by createdDate desc");
        Query query = session.createQuery(queryString.toString());
        query.setParameterList("downstreamGroupIdsList", downstreamGroupIdsList);
        query.setMaxResults(200);
        return query.list();
    }

    List<AgreementItemTypeTranslations> getAgreementItemTypeTranslationsByCountryId(int countryId, Session session) {
        return session.getNamedQuery("getAgreementItemTypeTranslationsByCountryId").setInteger("countryId", countryId).list();
    }

    public List<AgreementItems> getAgreementItemsByAgreementID(Session session, String agreementId) {
        return session.getNamedQuery("getAgreementItemsByAgreementID").setString("agreementId", agreementId).list();
    }

    public List<AgreementItems> getAgreementItemsByAgreementIDAndItemSectionNumber(Session session, String agreementId, int itemSectionNumber) {
        return session.getNamedQuery("getAgreementItemsByAgreementIDAndItemSectionNumber").setString("agreementId", agreementId).setInteger("itemSectionNumber", itemSectionNumber).list();
    }

    public Set<String> traverseAgreementsDownwards(String agreementItemId, Set<String> agreementIdsList, Session session) {
        List<AgreementItems> agreementsList = session.getNamedQuery("getAgreementItemsByagreementItemParentId").setString("agreementItemParentId", agreementItemId).list();
        if (!agreementsList.isEmpty()) {
            for (AgreementItems agreements : agreementsList) {
                agreementIdsList.add(agreements.getAgreementItemId());
                traverseAgreementsDownwards(agreements.getAgreementItemId(), agreementIdsList, session);
            }
        }
        return agreementIdsList;
    }

    public boolean deleteAgreementItemById(Session session, List<AgreementItems> agreementItems) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (AgreementItems agreementItems1 : agreementItems) {
                session.delete(agreementItems1);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    boolean addAgreementItem(Session session, AgreementItems ai) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(ai);
            tx.commit();

            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    boolean updateAgreementItemById(Session session, AgreementItems ai) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(ai);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    Object getAgreementItemsByAgreementItemId(String agreementItemId, Session session) {
        return session.getNamedQuery("getAgreementItemsByAgreementItemId").setString("agreementItemId", agreementItemId).uniqueResult();
    }

    Object getAgreementItemTypeTranslationsByID(String agreementItemTypeId, int countryId, Session session) {
        return session.getNamedQuery("getAgreementItemTypeTranslationsByID").setString("agreementItemTypeId", agreementItemTypeId).setInteger("countryId", countryId).uniqueResult();
    }

    List<AgreementItems> getAgreementItemsByAgreementId(String agreementId, Session session, int maxResult) {
        return session.getNamedQuery("getAgreementItemsByAgreementId").setString("agreementId", agreementId).setMaxResults(maxResult).list();
    }

    List<AgreementItemTypeTranslations> getAgreementItemTypeTranslationsByIDs(Session session, Set<String> aittIDs, int countryId) {
        return session.getNamedQuery("getAgreementItemTypeTranslationsByIDs").setParameterList("agreementItemTypeId", aittIDs).setInteger("countryId", countryId).list();
    }

    List<AgreementItems> getAgreementItemsOrderByItemSectionNumber(Set<String> agreementItemsIDs, Session session) {
        return session.getNamedQuery("getAgreementItemsOrderByItemSectionNumber").setParameterList("agreementItemId", agreementItemsIDs).list();
    }

    public List<AgreementBillingData> getAgreementBillingDetails(Session session, String agreementId, String month, String year) {
        return session.getNamedQuery("getAgreementBillingDetails").setString("agreementId", agreementId).setString("year", year).setString("month", month).setMaxResults(1000).list();
    }

    public List<ApplicationEmailTemplates> getApplicationEmailTemplatesDetails(Session session, boolean falg, String applicationId, String objectCode, String countryId, int loginCountryId) {
        StringBuffer hql = new StringBuffer("FROM ApplicationEmailTemplates ");
        List<String> applicationIds = new ArrayList<String>();
        boolean check = false;
        if (falg) {
            hql.append(" where");
        }
        if (applicationId != null) {
            if (TCMUtil.isValidUUID(applicationId)) {
                hql.append(" applications.applicationId = '" + applicationId + "'");
            } else {
                List<ApplicationTranslations> applicationTranslationses = getApplicationTranslationsByIdSearch(loginCountryId, applicationId, session);
                if (applicationTranslationses.isEmpty()) {
                    List<ApplicationEmailTemplates> aets = new ArrayList<ApplicationEmailTemplates>();
                    return aets;
                }

                for (ApplicationTranslations string : applicationTranslationses) {
                    applicationIds.add(string.getId().getApplicationId());
                }
                hql.append(" applications.applicationId in (:applicationIds)");
            }
            check = true;
        }
        if (objectCode != null) {
            if (check) {
                hql.append(" and");
            }
            hql.append(" objectCode like '%" + objectCode + "%'");
            check = true;
        }
        if (countryId != null) {
            if (check) {
                hql.append(" and");
            }
            hql.append(" countryId = '" + countryId + "'");
        }
        hql.append(" order by objectCode");

        Query query = session.createQuery(hql.toString());
        if (applicationId != null) {
            if (!TCMUtil.isValidUUID(applicationId)) {
                query.setParameterList("applicationIds", applicationIds);
            }
        }
        return query.list();
    }

    public List<ApplicationTranslations> getApplicationTranslationsByIds(Session session, List<String> applicationId, String countryId) {
        return session.getNamedQuery("getApplicationTranslationsByIds").setString("countryId", countryId).setParameterList("applicationId", applicationId).list();
    }

    public List<ApplicationEmailTemplates> getAppEmailTemplatesCheck(Session session, String countryId, String applicationId, String objectCode) {
        return session.getNamedQuery("getAppEmailTemplatesCheck").setString("countryId", countryId).setString("applicationId", applicationId).setString("objectCode", objectCode).list();
    }
}
