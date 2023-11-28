/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.priceplans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import se.info24.group.GroupDAO;
import se.info24.pojo.ChargeTypeTranslations;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.PricePlanItemTypeTranslations;
import se.info24.pojo.PricePlanItemTypes;
import se.info24.pojo.PricePlanItems;
import se.info24.pojo.PricePlanTypeTranslations;
import se.info24.pojo.PricePlanTypes;
import se.info24.pojo.PricePlanVersions;
import se.info24.pojo.PricePlans;
import se.info24.pojo.ProductCategoriesInGroups;
import se.info24.pojo.ProductCategoryTranslations;
import se.info24.pojo.ProductVariantTranslations;
import se.info24.pojo.ProductVariants;
import se.info24.pojo.ProductsInGroups;
import se.info24.pojo.UserAliasTypes;
import se.info24.pojo.UserAliasTypesInGroups;
import se.info24.pojo.ValidationDataType;
import se.info24.pojo.ValidationExpressions;
import se.info24.pojo.ValidationMemberships;
import se.info24.restUtil.RestUtilDAO;
import se.info24.util.TCMUtil;

/**
 *
 * @author Vijayakumar
 */
public class PricePlanDAO {

    public List<PricePlans> getPricePlansByGroupId(Session session, String groupId, String searchString) {
        GroupDAO groupDAO = new GroupDAO();
        List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
        ArrayList<String> groupIdList = new ArrayList<String>();
        groupIdList.add(groupId);
        for (GroupTrusts gtr : groupTrustsList) {
            groupIdList.add(gtr.getId().getGroupId());
        }
        return session.getNamedQuery("getPricePlansByGroupId").setParameterList("groupId", groupIdList).setString("pricePlanName", "%" + searchString + "%").list();
    }

    public List<PricePlanTypeTranslations> getPricePlanTypeTranslationsByCountryId(Session session, int countryId) {
        return session.getNamedQuery("getPricePlanTypeTranslationsByCountryId").setInteger("countryId", countryId).list();
    }

    public PricePlans getPricePlansByPricePlanId(Session session, String pricePlanId) {
        return (PricePlans) session.getNamedQuery("getPricePlans").setString("pricePlanId", pricePlanId).uniqueResult();
    }

    public List<PricePlanVersions> getPricePlanVersions(Session session, String pricePlanId) {
        return session.getNamedQuery("getPricePlanVersions").setString("pricePlanId", pricePlanId).list();
    }

    public boolean copyPricePlanTables(Session session, PricePlans pps, PricePlanItems ppits, PricePlanVersions ppvs) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(pps);
            session.saveOrUpdate(ppvs);
            session.saveOrUpdate(ppits);
            tx.commit();
            return true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return false;
        }
    }

    boolean deletePricePlan(Session session, String pricePlanId) {
        Transaction tx = session.beginTransaction();
        int i = 1;
        boolean result = false;
        try {
            List<PricePlanVersions> pricePlanVersionList = session.getNamedQuery("getPricePlanVersions").setString("pricePlanId", pricePlanId).list();
            if (!pricePlanVersionList.isEmpty()) {
                for (PricePlanVersions ppv : pricePlanVersionList) {
                    List<PricePlanItems> pricePlanItemList = session.getNamedQuery("getPricePlanItemsByPricePlanVersionId").setString("pricePlanVersionId", ppv.getPricePlanVersionId()).list();
                    if (!pricePlanItemList.isEmpty()) {
                        for (PricePlanItems ppi : pricePlanItemList) {
                            session.delete(ppi);
                            i = i + 1;
                            if (i % 20 == 0) {
                                session.flush();
                                session.clear();
                            }
                        }
                        result = true;
                    }
                    session.delete(ppv);
                    session.flush();
                    session.clear();
                }
                result = true;
            }
            PricePlans pricePlans = getPricePlansByPricePlanId(session, pricePlanId);
            if (pricePlans != null) {
                session.delete(pricePlans);
                tx.commit();
                result = true;
            }
            return result;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tx.rollback();
            return false;
        }
    }

    boolean addPricePlan(PricePlans pricePlans, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(pricePlans);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public boolean updatePricePlan(String pricePlanId, String pricePlanName, String pricePlanDescription, String pricePlanTypeId, String activeFromDate, String activeToDate, String groupId, String enabled, Session session, String timeZoneGMToffset) throws ParseException {
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<PricePlans> pricePlansList = session.getNamedQuery("getPricePlans").setString("pricePlanId", pricePlanId).list();
        Transaction tx = null;
        boolean result = false;
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        for (PricePlans pp : pricePlansList) {
            pp.setPricePlanName(pricePlanName);
            pp.setPricePlanDescription(pricePlanDescription);
            pp.setUpdatedDate(gc.getTime());
            gc.setTime(lFormat.parse(activeFromDate));
            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
            pp.setActiveFromDate(gc.getTime());
            gc.setTime(lFormat.parse(activeToDate));
            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
            pp.setActiveToDate(gc.getTime());
            if (groupId != null) {
                pp.setGroupId(groupId);
            }
            if (enabled != null) {
                pp.setIsEnabled(Integer.parseInt(enabled));
            }
            PricePlanTypes pricePlanTypes = new PricePlanTypes();
            pricePlanTypes.setPricePlanTypeId(pricePlanTypeId);
            pp.setPricePlanTypes(pricePlanTypes);

            try {
                tx = session.beginTransaction();
                session.saveOrUpdate(pp);
                tx.commit();
                result = true;
            } catch (Exception e) {
                TCMUtil.exceptionLog(getClass().getName(), e);
                result = false;
            }
        }
        return result;
    }

    public List<PricePlanItemTypeTranslations> getPricePlanItemTypeTranslations(Session session, String pricePlanItemTypeId, int countryId) {
        return session.getNamedQuery("getPricePlanItemTypeTranslations").setString("pricePlanItemTypeId", pricePlanItemTypeId).setInteger("countryId", countryId).list();
    }

    public ChargeTypeTranslations getChargeTypeTranslations(Session session, String chargeTypeId, int countryId) {
        return (ChargeTypeTranslations) session.getNamedQuery("getChargeTypeTranslations").setString("chargeTypeId", chargeTypeId).setInteger("countryId", countryId).uniqueResult();
    }

    public ValidationDataType getValidationDataType(Session session, int validationDataTypeId) {
        return (ValidationDataType) session.getNamedQuery("getValidationDataTypeById").setInteger("validationDataTypeId", validationDataTypeId).uniqueResult();
    }

    public ValidationExpressions getValidationExpression(Session session, int validationExpressionId) {
        return (ValidationExpressions) session.getNamedQuery("getValidationExpressionById").setInteger("validationExpressionId", validationExpressionId).uniqueResult();
    }

    boolean copyPricePlanRecursion(PricePlans pps, Session session) {
        Transaction tx = null;
        boolean result = false;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(pps);
            tx.commit();
//            session.flush();
//            session.clear();
            result = true;
        } catch (Exception e) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), e);
            result = false;
        }
        return result;
    }

    boolean copyPricePlanVersionRecursion(PricePlanVersions ppvs, Session session) {
        Transaction tx = null;
        boolean result = false;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(ppvs);
            tx.commit();
            session.flush();
            session.clear();
            result = true;
        } catch (Exception e) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), e);
            result = false;
        }
        return result;
    }

    public List<PricePlanItems> getPricePlanItemDetails(Session session, String pricePlanVersionId) {
        return session.getNamedQuery("getPricePlanItemsByPricePlanVersionId").setString("pricePlanVersionId", pricePlanVersionId).list();
    }

    boolean copyPricePlanItemsRecursion(PricePlanItems ppItems, Session session) {
        Transaction tx = null;
        boolean result = false;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(ppItems);
            tx.commit();
            session.flush();
            session.clear();
            result = true;
        } catch (Exception e) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), e);
            result = false;
        }
        return result;
    }

    Object getPricePlanItems(Session session, String pricePlanItemId, String pricePlanVersionId) {
        return session.getNamedQuery("getPricePlanItemsByItemIdAndVersionId").setString("pricePlanItemId", pricePlanItemId).setString("pricePlanVersionId", pricePlanVersionId).uniqueResult();
    }

    boolean deletePricePlanItems(String pricePlanItemId, String pricePlanVersionId, Session session) {
        Transaction tx = null;      
        String parentid = null;
        try {
            tx = session.beginTransaction();
            Object object = getPricePlanItems(session, pricePlanItemId, pricePlanVersionId);
            session.delete(object);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tx.rollback();
            return false;
        }
    }

    public void deleteUpStreamItems(String oldchildid, HashMap<String, String> hm, String pricePlanVersionId, Session session) {
        Set<Map.Entry<String, String>> entryset = hm.entrySet();
        try {
            String newchildId = null;
            for (Map.Entry<String, String> set : entryset) {
                if (set.getValue() != null) {
                    if (set.getValue().equalsIgnoreCase(oldchildid)) {
                        newchildId = set.getKey();
                        deletePricePlanItems(newchildId, pricePlanVersionId, session);
                        oldchildid = set.getKey();
                    } else {
                        deletePricePlanItems(pricePlanVersionId, newchildId, session);
                    }

                }
                //
                recursiveParentId(set.getKey());
            }



        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        }
    }

    public static void recursiveParentId(String id) {

        recursiveParentId(id);
    }

    public void deleteDownStreamItems(String oldparentid, HashMap<String, String> hm, String pricePlanVersionId, Session session) {
        String childid = oldparentid;
        while (childid != null) {
            deletePricePlanItems(childid, pricePlanVersionId, session);
            oldparentid = hm.get(childid);
        }
        deletePricePlanItems(childid, pricePlanVersionId, session);
    }

    List<ChargeTypeTranslations> getChargeTypeTranslations(Session session, int countryId) {
        return session.getNamedQuery("getChargeTypeTranslationsByCountryID").setInteger("countryId", countryId).list();
    }

    List<PricePlanItemTypeTranslations> getPricePlanItemTypeTranslations(Session session, int countryId) {
        return session.getNamedQuery("getPricePlanItemTypeTranslationsByCountryId").setInteger("countryId", countryId).list();
    }

    List<ProductCategoriesInGroups> getProductCategoriesInGroups(Session session, String groupId) {
        return session.getNamedQuery("getProductCategoriesInGroupsByGroupId").setString("groupId", groupId).list();
    }

    List<ProductCategoryTranslations> getProductCategoryTranslations(Session session, List<ProductCategoriesInGroups> productsCategoriesInGroupsList, int countryId) {
        ArrayList<String> pctList = new ArrayList<String>();
        for (ProductCategoriesInGroups pct : productsCategoriesInGroupsList) {
            pctList.add(pct.getId().getProductCategoryId());
        }
        return session.getNamedQuery("getProductCategoryTranslations").setParameterList("productCategoryId", pctList).setInteger("countryId", countryId).list();
    }

    List<ProductVariantTranslations> getProductVariantTranslations(Session session, List<ProductVariants> productVariantsList, int countryId) {
        List<String> productVariantIdList = new ArrayList<String>();
        for (ProductVariants pv : productVariantsList) {
            productVariantIdList.add(pv.getProductVariantId());
        }

        return session.getNamedQuery("getProductVariantTranslations").setParameterList("productVariantId", productVariantIdList).setInteger("countryId", countryId).list();
    }

    List<ProductVariants> getProductVariants(Session session) {
        return session.getNamedQuery("getProductVariants").list();
    }

    List<ProductsInGroups> getProductsInGroups(Session session, String groupId) {
        return session.getNamedQuery("getProductsInGroupsByGroupId").setString("groupId", groupId).list();
    }

    List<UserAliasTypes> getUserAliasTypes(Session session, String groupId, String searchString) {
//        List<UserAliasTypes> uat = new ArrayList<UserAliasTypes>();
        StringBuffer queryString = new StringBuffer();
        queryString.append("select uat.userAliasTypeId as userAliasTypeId, uat.userAliasTypename as userAliasTypeName from userAliasTypes as uat inner join " +
                "userAliasTypesInGroups as uatig on uat.userAliasTypeId = uatig.userAliasTypeId and uatig.groupId = '" + groupId + "' ");
        if (searchString != null) {
            if (TCMUtil.isValidUUID(searchString)) {
                queryString.append(" and uat.userAliasTypeId = '" + searchString + "' ");
            } else {
                queryString.append(" and uat.userAliasTypeName like '%" + searchString + "%' ");
            }
        }
        queryString.append("order by uat.userAliasTypeName asc ");
        SQLQuery query = session.createSQLQuery(queryString.toString());
        query.addScalar("userAliasTypeId", Hibernate.STRING);
        query.addScalar("userAliasTypeName", Hibernate.STRING);
        query.setResultTransformer(Transformers.aliasToBean(UserAliasTypes.class));
        return query.setMaxResults(200).list();
        
    }

    public List<UserAliasTypesInGroups> getUserAliasTypesInGroups(Session session, String groupId) {
        return session.getNamedQuery("getUserAliasTypesInGroups").setString("groupId", groupId).list();
    }
    List<ValidationDataType> getValidationDataType(Session session) {
        return session.getNamedQuery("getValidationDataType").list();
    }

    public PricePlanItemTypes getPricePlanItemTypesById(String priceplanitemtypeid, Session session) {
        try {
            return (PricePlanItemTypes) session.getNamedQuery("getPricePlanItemTypesByPricePlanItemTypeid").setString("pricePlanItemTypeId", priceplanitemtypeid).list().get(0);
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }

    }

    public ValidationDataType getValidationDataTypeById(String validationdatatypeid, Session session) {
        try {
            return (ValidationDataType) session.getNamedQuery("getValidationDataTypeById").setString("validationDataTypeId", validationdatatypeid).list().get(0);
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }

    }

    public ValidationExpressions getValidationExpressionsById(String validationexpressid, Session session) {
        try {
            return (ValidationExpressions) session.getNamedQuery("getValidationExpressionById").setString("validationExpressionId", validationexpressid).list().get(0);
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }

    }

    public PricePlanVersions getPricePlanVersionsById(String priceplanversionid, Session session) {
        try {
            List<PricePlanVersions> pricePlanVersionses = session.getNamedQuery("getPricePlanVersionsById").setString("pricePlanVersionId", priceplanversionid).list();
            return pricePlanVersionses.isEmpty()?null:(PricePlanVersions)pricePlanVersionses.get(0);
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }

    }

    public PricePlanItems getPricePlanItemById(String priceplanitemid, Session session) {
        try {
            return (PricePlanItems) session.getNamedQuery("getPricePlanItemsByPricePlanItemId").setString("pricePlanItemId", priceplanitemid).uniqueResult();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }

    }

    List<ValidationExpressions> getValidationExpressions(List<ValidationMemberships> validationMembershipsList, Session session) {
        ArrayList<Integer> validationExpressionIdList = new ArrayList<Integer>();
        for (ValidationMemberships vm : validationMembershipsList) {
            validationExpressionIdList.add(vm.getId().getValidationExpressionId());
        }

        return session.getNamedQuery("getValidationExpressionsByExpressionId").setParameterList("validationExpressionId", validationExpressionIdList).list();
    }

    List<ValidationMemberships> getValidationMemberships(String validationDataTypeId, Session session) {
        return session.getNamedQuery("getValidationMemberships").setString("validationDataTypeId", validationDataTypeId).list();
    }

    public List<PricePlans> getPricePlansById(Session session, Set<String> groupid) {
        return session.getNamedQuery("getPricePlansById").setParameterList("groupId", groupid).setMaxResults(200).list();
    }

    public List<PricePlans> getPricePlans(Session session, List<String> pricePlaneList) {
        return session.getNamedQuery("getPricePlansByIDS").setParameterList("pricePlanId", pricePlaneList).list();
    }

    public List<PricePlans> getPricePlansByGroupIdAndSearchString(Session session, Set<String> groupIdList, String searchString, int maxResults) {
        return session.getNamedQuery("getPricePlansByGroupIdAndSearchString").setParameterList("groupId", groupIdList).setString("searchField", "%" + searchString + "%").setMaxResults(maxResults).list();
    }

    public List<PricePlans> getPricePlansByGivenGroupId(Session session, Set<String> groupIdsList, int maxResults) {
        return session.getNamedQuery("getPricePlansByGivenGroupId").setParameterList("groupId", groupIdsList).setMaxResults(maxResults).list();
    }

    List<PricePlans> getPricePlansBySearch(Session session, String pricePlanName, String organization, Set<String> groupTrustIdsList, Integer countryId) {
        List<PricePlans> pricePlanses = new ArrayList<PricePlans>();
        List<GroupTranslations> groupTranslationses = new ArrayList<GroupTranslations>();
        if (pricePlanName != null) {
            if (TCMUtil.isValidUUID(pricePlanName)) {
                pricePlanses.add(getPricePlansByPricePlanId(session, pricePlanName));
            } else {
                pricePlanses.addAll(getPricePlansByGroupIdAndSearchString(session, groupTrustIdsList, pricePlanName, 200));
            }
        }
        if (organization != null) {
            GroupDAO groupDAO = new GroupDAO();
            Set<String> groupIds = new HashSet<String>();
            for (PricePlans pp : pricePlanses) {
                groupIds.add(pp.getGroupId());
            }
            if (TCMUtil.isValidUUID(organization)) {
                GroupTranslations gt = groupDAO.getGroupTranslationsByIds(organization, countryId, session);
                if (groupTrustIdsList.contains(gt.getId().getGroupId())) {
                    groupTranslationses.add(gt);
                }
            } else {
                if (groupIds.isEmpty()) {
                    groupTranslationses.addAll(groupDAO.getGroupTransByGroupIdandSearchString(groupTrustIdsList, organization, countryId, session));
                } else {
                    groupTranslationses.addAll(groupDAO.getGroupTransByGroupIdandSearchString(groupIds, organization, countryId, session));
                }
            }
        } else {
            return pricePlanses;
        }

        if (!groupTranslationses.isEmpty()) {
            List<PricePlans> searchedPricePlanses = new ArrayList<PricePlans>();
            if (!pricePlanses.isEmpty()) {
                for (PricePlans plans : pricePlanses) {
                    for (GroupTranslations groupTranslations : groupTranslationses) {
                        if (plans.getGroupId().equalsIgnoreCase(groupTranslations.getId().getGroupId())) {
                            searchedPricePlanses.add(plans);
                            break;
                        }
                    }
                }
            } else {
                Set<String> ppGids = new HashSet<String>();
                for (GroupTranslations gt : groupTranslationses) {
                    ppGids.add(gt.getId().getGroupId());
                }
                if (!ppGids.isEmpty()) {
                    searchedPricePlanses.addAll(getPricePlansByGivenGroupId(session, ppGids, 200));
                }
            }
            return searchedPricePlanses;
        }
        return pricePlanses;
    }

    public List<PricePlanTypeTranslations> getPricePlanTypeTranslationsByID(Set<String> pptypeIds, Integer countryID, Session session) {
        return session.getNamedQuery("getPricePlanTypeTranslationsByID").setParameterList("pricePlanTypeId", pptypeIds).setInteger("countryId", countryID).list();
    }

    public Object getPricePlanTypeTranslationsByCountryAndPricePlanTypeID(String pricePlanTypeId, Integer countryID, Session session) {
        return session.getNamedQuery("getPricePlanTypeTranslationsByCountryAndPricePlanTypeID").setParameter("pricePlanTypeId", pricePlanTypeId).setInteger("countryId", countryID).uniqueResult();
    }

    public List<PricePlanVersions> getPricePlanVersionsByPricePlanId(Session session, String pricePlanId) {
        return session.getNamedQuery("getPricePlanVersionsByPricePlanId").setString("pricePlanId", pricePlanId).list();
    }

    public List<PricePlanVersions> getPricePlanVersionsByIdsOrderByVersionName(Session session, Set<String> ppVersionIds) {
        return session.getNamedQuery("getPricePlanVersionsByIdsOrderByVersionName").setParameterList("pricePlanVersionId", ppVersionIds).list();
    }

    Object getPricePlansByIdAndIsEnabled(Session session, String pricePlanId) {
        return session.getNamedQuery("getPricePlansByIdAndIsEnabled").setString("pricePlanId", pricePlanId).uniqueResult();
    }

    public List<PricePlans> getPricePlansByPricePlanName(Session session, String pricePlanName) {
        return session.getNamedQuery("getPricePlansByPricePlanName").setParameter("pricePlanName", this).list();
    }
}
