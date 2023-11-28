/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.tim;

import java.util.List;
import java.util.Set;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import se.info24.pojo.SupportCaseStatuses;
import se.info24.pojo.UserRoleMemberships2;
import se.info24.timPojo.SiteMenuTranslations;
import se.info24.util.DbManager;

/**
 *
 * @author Vijayakumar
 */
public class TingcoMobileDAO {

    DbManager dbManager = new DbManager();

    List<Object> getTIMSiteMenuPageDetailsForUserID(Set<String> userRoleIdsList, Set<String> applicationModuleId, int countryId, Session session) {
        SQLQuery query = session.createSQLQuery("SELECT DISTINCT sm.SiteMenuNodeID as sitemenunodeid, smt.SiteMenuNodeName as sitemenunodename, sm.SiteMenuNodeParentID as sitemenunodeparentid, " +
                "sm.PageID as pageid, pt.PageName as pagename, sm.sortingweight as sortingweight, p.pagetitle as pagetitle, " +
                "(case when sm.NodeIconURL IS null then 'none' else  sm.NodeIconURL end) as  NodeIconURL from timweb.dbo.SiteMenus sm " +
                "INNER JOIN timweb.dbo.SiteMenuTranslations smt ON (sm.SiteMenuNodeID = smt.SiteMenuNodeID AND smt.CountryID=:CountryId) " +
                "INNER JOIN timweb.dbo.ApplicationModuleObjects AMO ON sm.SiteMenuNodeID=AMO.ObjectID " +
                "LEFT JOIN timweb.dbo.Pages p on sm.PageID=p.PageID " +
                "LEFT JOIN timweb.dbo.PageTranslations pt on (sm.PageID=pt.PageID  and pt.CountryID=:CountryId) where " +
                "sm.SiteMenuNodeID IN(Select objectid from timweb.dbo.ApplicationModuleObjects where ApplicationModuleID IN (:applicationModuleIdsList))AND sm.SiteMenuNodeID IN " +
                "(select objectid from timweb.dbo.UserRoleObjects where userroleid IN (:userRoleIdsList)) Order By sm.SortingWeight");
        query.addScalar("sitemenunodeid", Hibernate.STRING);
        query.addScalar("sitemenunodename", Hibernate.STRING);
        query.addScalar("sitemenunodeparentid", Hibernate.STRING);
        query.addScalar("pageid", Hibernate.STRING);
        query.addScalar("pagename", Hibernate.STRING);
        query.addScalar("sortingweight", Hibernate.STRING);
        query.addScalar("pagetitle", Hibernate.STRING);
        query.addScalar("NodeIconURL", Hibernate.STRING);
        query.setParameter("CountryId", countryId);
        query.setParameterList("userRoleIdsList", userRoleIdsList);
        query.setParameterList("applicationModuleIdsList", applicationModuleId);
        return query.list();
    }

    Set<String> getUserRoleIds(String loggedInUserId, int countryId, Session session) {
        List<UserRoleMemberships2> userRoleMemberships2 = dbManager.getUserRoleIdByUserId(session, loggedInUserId);
        Set<String> userRoleIdsList = dbManager.getUserRoleIdsList(userRoleMemberships2);

        return userRoleIdsList;
    }

    public Object getSiteMenuTranslationsById(Session session, String siteMenuNodeId, int countryId) {
        return session.getNamedQuery("getSiteMenuTranslationsById").setString("siteMenuNodeId", siteMenuNodeId).setInteger("countryId", countryId).uniqueResult();
    }

    public List<SupportCaseStatuses> getSupportCaseStatusesByIdsAndIsClosed(Session session,List<String> supportCaseStatusId){
        return session.getNamedQuery("getSupportCaseStatusesByIdsAndIsClosed").setString("isClosed", "1").setParameterList("supportCaseStatusId", supportCaseStatusId).list();
    }
}
