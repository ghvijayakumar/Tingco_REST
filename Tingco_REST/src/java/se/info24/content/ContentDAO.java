/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.content;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
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
import org.hibernate.criterion.Restrictions;
import se.info24.ismOperationsPojo.ContentGroupItems;
import se.info24.ismOperationsPojo.ContentGroups;
import se.info24.ismOperationsPojo.ContentItemCommands;
import se.info24.ismOperationsPojo.ContentItemFeedback;
import se.info24.ismOperationsPojo.ContentItems;
import se.info24.contentjaxb.ContentItem;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.ContentItemAttributes;
import se.info24.ismOperationsPojo.ContentItemLinks;
import se.info24.ismOperationsPojo.ContentItemsToDevice;
import se.info24.pojo.ContentAttributeTranslations;
import se.info24.pojo.ContentAttributes;
import se.info24.pojo.ContentCategories;
import se.info24.pojo.ContentCategoriesInGroups;
import se.info24.pojo.ContentCategoryAttributes;
import se.info24.pojo.ContentCategoryTranslations;
import se.info24.pojo.ContentTypeTranslations;
import se.info24.pojo.ContentTypes;
import se.info24.pojo.DeviceCommandSchedules;
import se.info24.pojo.DeviceTypeCommandTranslations;
import se.info24.pojo.DeviceTypeContentAttributes;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.ObjectContentCategorySubscriptions;
import se.info24.pojo.UserFavoriteContentCategories;
import se.info24.pojo.UserFavoriteContentCategoriesId;
import se.info24.restUtil.RestUtilDAO;
import se.info24.util.TCMUtil;

/**
 *
 * @author Sekhar
 */
public class ContentDAO {

    // CRUD Methods for ContentTypes
    public boolean saveContentTypes(ContentTypes contentTypes, Session session) {
        Transaction tx = null;
        try {
            if (contentTypes != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(contentTypes);
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

    public ContentTypes getDeviceTypesById(String contentTypeId, Session session) {
        try {
            return (ContentTypes) session.getNamedQuery("getContentTypesById").setString("contentTypeId", contentTypeId).list().get(0);
        } catch (IndexOutOfBoundsException ae) {
            return null;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public boolean deleteContentType(ContentTypes contentTypes, Session session) {
        Transaction tx = null;
        try {
            if (contentTypes != null) {
                tx = session.beginTransaction();
                session.delete(contentTypes);
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

    public List<ContentTypes> getAllContentTypes(Session session) {
        try {
            return session.getNamedQuery("getAllContentTypes").list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public List<ContentAttributes> getAllContentAttributes(int maxResult, Session session) {
        try {
            return session.getNamedQuery("getAllContentAttributes").setMaxResults(2000).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public boolean addContentCategory(ContentCategories cc, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(cc);
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

    public Object getContentCategoryById(String contentCategoryId, Session session) {
        try {
            return session.getNamedQuery("getContentCategoryById").setString("contentCategoryId", contentCategoryId).uniqueResult();
        } catch (IndexOutOfBoundsException ae) {
            return null;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public boolean saveContentCategory(ContentCategories contentCategory, Session session) {
        Transaction tx = null;
        try {
            if (contentCategory != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(contentCategory);
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

    public boolean addNewContentAttribute(ContentAttributes ca, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(ca);
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

    public boolean updateContentAttribute(String id, String dataFieldName, String dataGroupName, String userID, String unit, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            GregorianCalendar gc = new GregorianCalendar();
            Object obj = getContentAttributes(id, session);
            ContentAttributes ca = (ContentAttributes) obj;
            ca.setCdmfieldName(dataFieldName);
            ca.setCdmdataGroupName(dataGroupName);
            ca.setUserId(userID);
            if (!unit.equals("")) {
                ca.setUnit(unit.split("/")[2]);
            }
            ca.setUpdatedDate(gc.getTime());
            session.saveOrUpdate(ca);
            tx.commit();
            return true;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
        return false;
    }

    public boolean deleteContentAttribute(String id, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Object ca = getContentAttributes(id, session);
            session.delete(ca);
            tx.commit();
            return true;
        } catch (IndexOutOfBoundsException ae) {
            return false;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
        return false;
    }

    public Object getContentAttributes(String contentAttributeId, Session session) {
        return session.getNamedQuery("getContentAttributesById").setString("id", contentAttributeId).uniqueResult();
    }

    public List<ContentAttributes> getContentAttributesByIds(List<String> contentAttributeId, Session session) {
        return session.getNamedQuery("getContentAttributesByIds").setParameterList("id", contentAttributeId).list();
    }

    public List<ContentAttributeTranslations> getContentAttributeTranslationsByAttributeId(String contentAttributeId, Session session) {
        return session.getNamedQuery("getContentAttributeTransByContentAttributeId").setString("contentAttributeId", contentAttributeId).list();
    }

    public List<ContentAttributeTranslations> getContentAttributeTranslationsByCountryId(int countryId, Session session) {
        return session.getNamedQuery("getContentAttributeTransByCountryId").setInteger("countryId", countryId).list();
    }

    // CRUD Methods for ContentCategoryAttributes
    public boolean saveContentCategoryAttributes(ContentCategoryAttributes attributes, Session session) {
        Transaction tx = null;
        try {
            if (attributes != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(attributes);
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

    public Object getContentCategoryAttributes(String contentCategoryId, String contentAttributeId, Session session) {
        try {
            return session.getNamedQuery("getContentCategoryAttributesById").setString("contentCategoryId", contentCategoryId).setString("contentAttributeId", contentAttributeId).uniqueResult();
        } catch (IndexOutOfBoundsException ae) {
            return null;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public boolean deleteContentCategoryAttributes(Object attributes, Session session) {
        Transaction tx = null;
        try {
            if (attributes != null) {
                tx = session.beginTransaction();
                session.delete(attributes);
                tx.commit();
                return true;
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
        return false;
    }

    public List<UserFavoriteContentCategories> getUserFavouriteContentCategories(String userID, Session session) {
        List<UserFavoriteContentCategories> ufcg = session.getNamedQuery("getUserFavouriteContentCategories").setString("userID", userID).list();
        return ufcg;
    }

    public Object getContentItemsById(String contentItemID, Session session) {
        return session.getNamedQuery("getContentItemsById").setString("contentItemID", contentItemID).uniqueResult();
    }

    public List<ContentItems> getContentItemsByIds(List<String> contentItemId, Session session) {
        return session.getNamedQuery("getContentItemsByIds").setParameterList("contentItemId", contentItemId).list();
    }

    public List<ContentItems> getContentItemsByIds(List<String> contentItemId, Session session, int maxResults) {
        return session.getNamedQuery("getContentItemsByIds").setParameterList("contentItemId", contentItemId).setMaxResults(maxResults).list();
    }

    public List<ContentItems> getContentItemDetailsByContentCategoryID(String conCatID, Session session) {
        return session.getNamedQuery("getContentItemsByContentCategoryId").setString("conCatID", conCatID).list();
    }

    public Object getContentItemLinkByIds(String contentItemIdLeft, String contentItemIdRight, Session oprsession) {
        return oprsession.getNamedQuery("getContentItemLinksByIds").setString("contentItemIdleft", contentItemIdLeft).setString("contentItemIdright", contentItemIdRight).uniqueResult();
    }

    public List<ObjectContentCategorySubscriptions> getObjectContentCategories(String groupId, Session session) {
        try {
            return session.getNamedQuery("getObjectContentCategoriesById").setString("groupId", groupId).list();
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return null;
        }
    }

    public List<ContentItemAttributes> getContentItemAttributesByContentItemId(String contentItemId, Session ismoperationssession) {
        return ismoperationssession.getNamedQuery("getContentItemAttributesByContentItemId").setString("contentItemId", contentItemId).list();
    }

    public Object getContentItemAttributesByIds(String contentItemId, String contentAttributeId, Session session) {
        return session.getNamedQuery("getContentItemAttributesByIds").setString("contentItemId", contentItemId).setString("contentAttributeId", contentAttributeId).uniqueResult();
    }

    public List<ContentCategories> getAllContentCategories(Session session) {
        try {
            return session.getNamedQuery("getAllContentCategories").list();
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return null;
        }
    }

    public boolean addFavoriteContent(String userId, List<ContentItem> ci, Session session) {
        UserFavoriteContentCategories userFav = null;
        UserFavoriteContentCategoriesId userFavCon = null;
        GregorianCalendar gc = new GregorianCalendar();
        Transaction tx = null;
        try {
            for (ContentItem data : ci) {
                userFav = new UserFavoriteContentCategories();
                userFavCon = new UserFavoriteContentCategoriesId();
                userFavCon.setContentCategoryId(data.getContentCategoryID());
                userFavCon.setUserId(userId);
                userFav.setCreatedDate(gc.getTime());
                userFav.setUpdatedDate(gc.getTime());
                userFav.setId(userFavCon);
                tx = session.beginTransaction();
                session.save(userFav);
                tx.commit();
            }

            return true;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    boolean deleteFavoriteContentCategoriesByUserId(String userId, Session session) {
        Transaction tx = null;
        try {
            Query query = session.getNamedQuery("deleteFavoriteContentCategoriesByUserId").setString("userid", userId);
            query.executeUpdate();
            tx = session.beginTransaction();
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

    public List<ContentCategoryTranslations> getContentCategoryTranslationsByCountryId(int countryId, String searchString, Session session) {
        if (searchString == null) {
            return session.getNamedQuery("getContentCategoriesByCountryId").setInteger("countryId", countryId).list();
        } else {
            if (TCMUtil.isValidUUID(searchString)) {
                return session.getNamedQuery("getContentCategoryTransByIds").setInteger("countryId", countryId).setString("contentCategoryId", searchString).list();
            } else {
                return session.getNamedQuery("getContentCategoryTransByCountryIdAndsearchString").setInteger("countryId", countryId).setString("contentCategoryName", searchString).list();
            }
        }
    }

    List<Object> getContentItemAttributesAndTranslations(String contentItemId, int countryId, Session session) {
        SQLQuery query = session.createSQLQuery("select cia.contentattributeid as contentattributeid, cat.attributename as attributename, cia.contentattributevalue as contentattributevalue from ISMOperations.dbo.ContentItemAttributes as cia inner join contentAttributeTranslations as cat on cia.contentattributeid=cat.contentattributeid and cia.contentitemid='" + contentItemId + "' and cat.countryid = " + countryId + " and cia.isEnabled = 1 order by cat.attributename");
        query.addScalar("contentattributeid", Hibernate.STRING);
        query.addScalar("attributename", Hibernate.STRING);
        query.addScalar("contentattributevalue", Hibernate.STRING);
        List<Object> objectList = query.setMaxResults(200).list();
        return objectList;
    }

    Object getContentItemCommandsById(String contentItemCommandId, Session ismOperationsSession) {
        return ismOperationsSession.getNamedQuery("getContentItemCommandsById").setString("contentItemCommandId", contentItemCommandId).uniqueResult();
    }

    List<String> getContentItemsByContentCategoryid(String contentCategoryId, Session oprSession) {
        Criteria criteria = oprSession.createCriteria(ContentItems.class);
        criteria.add(Restrictions.eq("contentCategoryId", contentCategoryId));
        criteria.addOrder(Order.desc("createdDate"));
        criteria.setProjection(Projections.property("contentItemId"));
        return criteria.setMaxResults(200).list();
    }

    List<ContentItems> getContentItemsByDeviceId(Session ismoperationssession, String deviceId) {
        Criteria criteria = ismoperationssession.createCriteria(ContentItems.class, "ci");
        criteria.createAlias("ci.contentItemsToDevices", "citd", CriteriaSpecification.INNER_JOIN);
        criteria.add(Restrictions.eq("citd.id.deviceId", deviceId));
        criteria.addOrder(Order.asc("ci.contentItemSubject"));
        return criteria.list();
    }

    public ContentItemsToDevice getContentItemsToDeviceById(Session session, String contentItemId, String deviceId) {
        List<ContentItemsToDevice> list = session.getNamedQuery("getContentItemsToDeviceById").setParameter("contentItemId", contentItemId).setParameter("deviceId", deviceId).list();
        return list.isEmpty() ? null : list.get(0);
    }

    public List<ContentItemsToDevice> getContentItemsToDeviceByContentItemID(Session session, String contentItemId) {
       return session.getNamedQuery("getContentItemsToDeviceByContentItemID").setString("contentItemId", contentItemId).list();
    }

    public void deleteContentItemsToDevice(Session session, ContentItemsToDevice contentItemsToDevice) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(contentItemsToDevice);
            tx.commit();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public List<ContentItems> getContentItemsBySearchString(Session session, String searchString, int maxresult) {
        return session.getNamedQuery("getContentItemsBySearchString").setParameter("contentItemSubject", searchString).setMaxResults(maxresult).list();
    }

    public void saveContentItemsToDevice(Session session, ContentItemsToDevice contentItemsToDevice) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(contentItemsToDevice);
            tx.commit();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public void saveContentItemAttribute(Session oprsession, ContentItemAttributes contentItemAttributes) {
        Transaction tx = null;
        try {
            tx = oprsession.beginTransaction();
            oprsession.saveOrUpdate(contentItemAttributes);
            tx.commit();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public void deleteObject(Session oprsession, Object obj) {
        try {
            oprsession.delete(obj);
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
        }
    }

    List<ContentItems> getContentItemsByGroupIds(Session oprSession, Set<String> groupidsset, String contentItemSubject, int maxResults) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ContentItems.class, "ci");
        detachedCriteria.add(Restrictions.in("ci.groupId", groupidsset));
        if (contentItemSubject != null) {
            detachedCriteria.add(Restrictions.ilike("ci.contentItemSubject", contentItemSubject, MatchMode.ANYWHERE));
        }
        detachedCriteria.addOrder(Order.desc("ci.createdDate"));
        detachedCriteria.setProjection(Projections.property("ci.contentItemId"));
        List<String> contentItemIdsList = detachedCriteria.getExecutableCriteria(oprSession).setMaxResults(maxResults).list();
        if (!contentItemIdsList.isEmpty()) {
            return getContentItemsFromContentItemIds(contentItemIdsList, maxResults, oprSession);
        } else {
            return null;
        }

//        Criteria criteria = oprSession.createCriteria(ContentItems.class, "ci");
//        criteria.add(Subqueries.in("ci.contentItemId", detachedCriteria));
//        criteria.addOrder(Order.asc("ci.contentItemSubject"));
//        return criteria.setMaxResults(1000).list();
    }

    public List<ContentItems> getContentItemsFromContentItemIds(List<String> contentItemIdsList, int maxResults, Session ismOperationsSession) {
        Criteria criteria = ismOperationsSession.createCriteria(ContentItems.class, "ci");
        criteria.add(Restrictions.in("ci.contentItemId", contentItemIdsList));
        criteria.addOrder(Order.asc("ci.contentItemSubject"));
        return criteria.setMaxResults(maxResults).list();
    }

    public List<ContentItems> getContentItemsList(Set<String> groupIdsset, ContentItem contentItem, int maxResults, Session ismOperationsSession, Session session) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ContentItems.class, "ci");
        detachedCriteria.add(Restrictions.in("ci.groupId", groupIdsset));
        if (contentItem.getBody() != null) {
            detachedCriteria.add(Restrictions.ilike("ci.contentItemSubject", contentItem.getBody(), MatchMode.ANYWHERE));
        }
        if (contentItem.getContentCategoryID() != null) {
            if (TCMUtil.isValidUUID(contentItem.getContentCategoryID())) {
                detachedCriteria.add(Restrictions.eq("ci.contentCategoryId", contentItem.getContentCategoryID()));
            } else {
                ContentDAO contentDAO = new ContentDAO();

                List<ContentCategoryTranslations> contentCategoryTranslationses = contentDAO.getContentCategoryTranslationsByCountryId(contentItem.getCountryID().getValue(), contentItem.getContentCategoryID(), session);

//                List<ObjectContentCategorySubscriptions> objectCategoryList = contentDAO.getObjectContentCategories(contentItem.getGroupID().getValue(), session);
                List<String> ContentCategoryId = new ArrayList();
//                for (ObjectContentCategorySubscriptions string : objectCategoryList) {
//                    ContentCategoryId.add(string.getId().getContentCategoryId());
//                }
//                if (ContentCategoryId.isEmpty()) {
//                    return null;
//                }
//                List<ContentCategoryTranslations> contentCategoryTranslationses = getContentCategoryTransByIdListAndCountryIdsearch(session, ContentCategoryId, contentItem.getCountryID().getValue(), contentItem.getContentCategoryID());
                if (contentCategoryTranslationses.isEmpty()) {
                    return null;
                }
                ContentCategoryId.clear();
                for (ContentCategoryTranslations contentCategoryTranslations : contentCategoryTranslationses) {
                    ContentCategoryId.add(contentCategoryTranslations.getId().getContentCategoryId());
                }
                detachedCriteria.add(Restrictions.in("ci.contentCategoryId", ContentCategoryId));
            }
        }
        if (contentItem.getContentTypeID() != null) {
            detachedCriteria.add(Restrictions.eq("ci.contentTypeId", contentItem.getContentTypeID()));
        }
        if (contentItem.getSelectedGroupID() != null) {
            GroupDAO groupDAO = new GroupDAO();
            List<GroupTranslations> groupTransList = groupDAO.getGroupTransBySearchStringCriteria(groupIdsset, contentItem.getSelectedGroupID(), contentItem.getCountryID().getValue(), maxResults, session);
            if (!groupTransList.isEmpty()) {
                Set<String> groupIdsList = groupDAO.getGroupTransIdsList(groupTransList);
                detachedCriteria.add(Restrictions.in("ci.groupId", groupIdsList));
            } else {
                return null;
            }
        }
        detachedCriteria.addOrder(Order.desc("ci.createdDate"));
        detachedCriteria.setProjection(Projections.property("ci.contentItemId"));
        List<String> contentItemIdsList = detachedCriteria.getExecutableCriteria(ismOperationsSession).setMaxResults(maxResults).list();
        if (!contentItemIdsList.isEmpty()) {
            return getContentItemsFromContentItemIds(contentItemIdsList, maxResults, ismOperationsSession);
        } else {
            return null;
        }
    }

    public List<ContentCategoryTranslations> getContentCategoryTransByIdListAndCountryIdsearch(Session session, List<String> contentCategoryId, int countryId, String contentCategoryName) {
        return session.getNamedQuery("getContentCategoryTransByIdListAndCountryIdsearch").setParameterList("contentCategoryId", contentCategoryId).setInteger("countryId", countryId).setString("contentCategoryName", contentCategoryName).list();
    }

    List<DeviceTypeContentAttributes> getDeviceTypeContentAttributesByDeviceTypeId(Session session, String deviceTypeId) {
        return session.getNamedQuery("getDeviceTypeContentAttributesByDeviceTypeId").setParameter("deviceTypeId", deviceTypeId).list();
    }

    List<String> getLinkedContentItems(String contentItemId, Session oprSession) {
        Criteria criteria = oprSession.createCriteria(ContentItemLinks.class, "cil");
        criteria.add(Restrictions.eq("cil.id.contentItemIdleft", contentItemId));
        criteria.addOrder(Order.desc("createdDate"));
        criteria.setProjection(Projections.property("cil.id.contentItemIdright"));
        return criteria.setMaxResults(200).list();
    }

    List<Object> getNonLinkedContentItemAttributesAndTranslations(List<String> contentAttributeIdsList, String contentCategoryId, int countryId, Session session, int maxResult) {
        SQLQuery query = session.createSQLQuery("select cat.contentattributeid as contentattributeid, cat.attributename as attributename, cca.attributedefaultvalue as contentattributevalue from contentattributetranslations as cat inner join contentcategoryattributes as cca on cat.contentattributeid=cca.contentattributeid and cca.contentattributeid not in(:contentAttributeIdsList) and cca.contentcategoryid='" + contentCategoryId + "' and cat.countryid = '" + countryId + "' order by cat.attributename");
        query.addScalar("contentattributeid", Hibernate.STRING);
        query.addScalar("attributename", Hibernate.STRING);
        query.addScalar("contentattributevalue", Hibernate.STRING);
        query.setParameterList("contentAttributeIdsList", contentAttributeIdsList);
        List<Object> objectList = query.setMaxResults(maxResult).list();
        return objectList;
    }

    List<Object> getContentItemAttributesAndTranslations(String contentCategoryId, int countryId, Session session, int maxResult) {
        SQLQuery query = session.createSQLQuery("select cat.contentattributeid as contentattributeid, cat.attributename as attributename, cca.attributedefaultvalue as contentattributevalue from contentattributetranslations as cat inner join contentcategoryattributes as cca on cat.contentattributeid=cca.contentattributeid and cca.contentcategoryid='" + contentCategoryId + "' and cat.countryid = '" + countryId + "' order by cat.attributename");
        query.addScalar("contentattributeid", Hibernate.STRING);
        query.addScalar("attributename", Hibernate.STRING);
        query.addScalar("contentattributevalue", Hibernate.STRING);
        List<Object> objectList = query.setMaxResults(maxResult).list();
        return objectList;
    }

    List<String> getcontentAttributeIdsList(List<ContentItemAttributes> contentItemAttributesList) {
        List<String> list = new ArrayList<String>();
        for (ContentItemAttributes cia : contentItemAttributesList) {
            list.add(cia.getId().getContentAttributeId());
        }
        return list;
    }

    boolean saveUpdateContentItems(Session session, ContentItems contentItems) {
        Transaction tx = null;
//        boolean result = false;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(contentItems);
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

    void saveContentItemAttributes(Session session, List<ContentItemAttributes> contentItemAttributesList) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (ContentItemAttributes contentItemAttributes : contentItemAttributesList) {
                session.saveOrUpdate(contentItemAttributes);
            }
            tx.commit();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    List<ContentGroupItems> getContentGroupItemsByContentItemId(Session session, String contentItemId) {
        return session.getNamedQuery("getContentGroupItemsByContentItemId").setParameter("contentItemId", contentItemId).list();
    }

    public List<ContentGroups> getContentGroupsByContentGroupId(Session session, List<String> ids) {
        return session.getNamedQuery("getContentGroupsByContentGroupId").setParameterList("contentGroupId", ids).list();
    }

    Object getContentGroupItemsById(Session session, String contentItemId, String contentGroupId) {
        return session.getNamedQuery("getContentGroupItemsById").setParameter("contentItemId", contentItemId).setParameter("contentGroupId", contentGroupId).uniqueResult();
    }

    Object getDeviceTypeDefaultCategoryByDeviceTypeId(Session session, String deviceTypeId) {
        return session.getNamedQuery("getDeviceTypeDefaultCategoryByDeviceTypeId").setParameter("deviceTypeId", deviceTypeId).uniqueResult();
    }

    void deleteContentGroupItems(Session session, ContentGroupItems contentGroupItems) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(contentGroupItems);
            tx.commit();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    List<ContentGroups> getAllContentGroups(Session session, int maxResult) {
        return session.getNamedQuery("getAllContentGroups").setMaxResults(maxResult).list();
    }

    void saveContentGroupItems(Session session, List<ContentGroupItems> contentGroupItemsList) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (ContentGroupItems cgi : contentGroupItemsList) {
                session.saveOrUpdate(cgi);
            }
            tx.commit();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    boolean saveContentGroupItemsAndContentGroups(Session session, ContentGroups contentGroups, ContentGroupItems contentGroupItems) {
        Transaction tx = null;
        boolean result = false;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(contentGroups);
            session.saveOrUpdate(contentGroupItems);
            tx.commit();
            result = true;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            result = false;
        }
        return result;
    }

    Object getContentGroupsById(Session session, String contentGroupId) {
        return session.getNamedQuery("getContentGroupsById").setParameter("contentGroupId", contentGroupId).uniqueResult();
    }

    List<ContentItems> getContentItemsBySearchStringAndContentCategoryId(Session session, String contentItemSubject, String contentCategoryId, int maxResult) {
        return session.getNamedQuery("getContentItemsBySearchStringAndContentCategoryId").setParameter("contentCategoryId", contentCategoryId).setParameter("contentItemSubject", contentItemSubject).setMaxResults(maxResult).list();
    }

    Object getContentItemsByIdAndContentCategoryId(String contentItemID, String contentCategoryId, Session session) {
        return session.getNamedQuery("getContentItemsByIdAndContentCategoryId").setParameter("contentItemID", contentItemID).setParameter("contentCategoryId", contentCategoryId).uniqueResult();
    }

    List<ContentItems> getContentItemsByCCID(Session session, String contentCategoryId, int maxResult) {
        return session.getNamedQuery("getContentItemsByCCID").setParameter("contentCategoryId", contentCategoryId).setMaxResults(maxResult).list();
    }

    List<ContentTypeTranslations> getContentTypeTranslationsByIdsAndCountryId(Session session, Set<String> contentTypeIds, int countryId) {
        return session.getNamedQuery("getContentTypeTranslationsByIdsAndCountryId").setParameterList("contentTypeId", contentTypeIds).setParameter("countryId", countryId).list();
    }

    List<ContentTypeTranslations> getContentTypeTranslationsByCountryId(Session session, int countryId) {
        return session.getNamedQuery("getContentTypeTranslationsByCountryId").setParameter("countryId", countryId).list();
    }

    void saveContentItems(ContentItem contentItemJaxb, String newContentItemId, String timeZoneGMToffset, GregorianCalendar gc, Session oprSession) throws ParseException {
        ContentItems contentItems = new ContentItems();
        contentItems.setContentItemId(newContentItemId);

        contentItems.setContentItemSubject(contentItemJaxb.getBody());

        if (contentItemJaxb.getContentCategoryID() != null) {
            contentItems.setContentCategoryId(contentItemJaxb.getContentCategoryID());
        }
        if (contentItemJaxb.getContentTypeID() != null) {
            contentItems.setContentTypeId(contentItemJaxb.getContentTypeID());
        }
        if (contentItemJaxb.getGroupID() != null) {
            if (contentItemJaxb.getGroupID().getValue() != null) {
                contentItems.setGroupId(contentItemJaxb.getGroupID().getValue());
            }
        }

        if (contentItemJaxb.getSourceName() != null) {
            contentItems.setSourceName(contentItemJaxb.getSourceName());
        }
        if (contentItemJaxb.getSourceReferenceID() != null) {
            contentItems.setSourceReferenceId(contentItemJaxb.getSourceReferenceID());
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        contentItems.setIsEnabled(contentItemJaxb.getIsEnabled());
        if (contentItemJaxb.getActiveFromDate() != null) {
            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, dateFormat.parse(contentItemJaxb.getActiveFromDate())));
            contentItems.setStartTime(gc.getTime());
        }
        if (contentItemJaxb.getActiveToDate() != null) {
            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, dateFormat.parse(contentItemJaxb.getActiveToDate())));
            contentItems.setStopTime(gc.getTime());
        }
        if (contentItemJaxb.getExpiryDate() != null) {
            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, dateFormat.parse(contentItemJaxb.getExpiryDate())));
            contentItems.setExpiryTime(gc.getTime());
        } else {
            gc.add(GregorianCalendar.HOUR, 24); //ActiveToDate + 1 day
            contentItems.setExpiryTime(gc.getTime());
        }
        if (contentItemJaxb.getGeometry() != null) {
            se.info24.contentjaxb.Point point = contentItemJaxb.getGeometry().getPoint();
            if (point != null) {
                if (point.getPosLatitude() != null) {
                    contentItems.setPosLatitude(Double.valueOf(point.getPosLatitude()));
                }
                if (point.getPosLongitude() != null) {
                    contentItems.setPosLongitude(Double.valueOf(point.getPosLongitude()));
                }
                if (point.getPosDepth() != null) {
                    contentItems.setPosDepth(Double.valueOf(point.getPosDepth()));
                }
            }
        }

        gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        contentItems.setCreatedDate(gc.getTime());
        contentItems.setUpdatedDate(gc.getTime());

        oprSession.saveOrUpdate(contentItems);
    }

    void deleteContentAttributeTranslations(List<ContentAttributeTranslations> attributeTranslationses, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (ContentAttributeTranslations cat : attributeTranslationses) {
                session.delete(cat);
            }
            tx.commit();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public List<ContentCategoryAttributes> getContentCategoryAttributesBycontentAttributeId(String contentAttributeId, Session session) {
        return session.getNamedQuery("getContentCategoryAttributesBycontentAttributeId").setString("contentAttributeId", contentAttributeId).list();

    }

    void deleteContentCategoryAttributes(List<ContentCategoryAttributes> contentCategoryAttributeses, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (ContentCategoryAttributes cat : contentCategoryAttributeses) {
                session.delete(cat);
            }
            tx.commit();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public List<ContentCategoriesInGroups> getContentCategoriesInGroupsByGroupId(String groupId, Session session) {
        return session.getNamedQuery("getContentCategoriesInGroupsByGroupId").setString("groupId", groupId).setMaxResults(500).list();
    }

    public Set<String> getContentCategoryIds(List<ContentCategoriesInGroups> ccigList) {
        Set<String> contentCategoryIds = new HashSet<String>();
        for (ContentCategoriesInGroups ccig : ccigList) {
            contentCategoryIds.add(ccig.getId().getContentCategoryId());
        }
        return contentCategoryIds;
    }

    public List<ContentItemCommands> getContentItemCommandsByContentItemId(String contentitemid, Session oprSession) {
        return oprSession.getNamedQuery("getContentItemCommandsByContentItemId").setString("contentItemId", contentitemid).list();
    }

    public List<ContentItems> getContentItemsByGroupIDs(HashSet<String> trustedGroupIDs, Session oprSession) {
        return oprSession.getNamedQuery("getContentItemsByGroupIDs").setParameterList("groupId", trustedGroupIDs).list();
    }

    public List<ContentItems> getContentItemsByGroupIDsAndSearchString(HashSet<String> trustedGroupIDs, String searchstring, Session oprSession) {
        return oprSession.getNamedQuery("getContentItemsByGroupIDsAndSearchString").setParameterList("groupId", trustedGroupIDs).setString("contentItemSubject", searchstring).list();
    }

    public Object getContentCategoryTransByIds(String contentCategoryId, int countryid, Session session) {
        return session.getNamedQuery("getContentCategoryTransByIds").setInteger("countryId", countryid).setString("contentCategoryId", contentCategoryId).uniqueResult();
    }

    public Object getContentItemStatisticsById(String contentItemId, Session oprSession) {
        return oprSession.getNamedQuery("getContentItemStatisticsById").setString("contentItemID", contentItemId).uniqueResult();
    }

    public List<ContentItemFeedback> getContentItemFeedbackByContentItemId(String contentItemId, Session oprSession, int maxResult) {
        return oprSession.getNamedQuery("getContentItemFeedbackByContentItemId").setString("contentItemID", contentItemId).list();
    }

    public List<DeviceTypeCommandTranslations> getDeviceTypeCommandTranslationsByDeviceTypeCommandID(String deviceTypeCommandId, Session session) {
        return session.getNamedQuery("getDeviceTypeCommandTranslationsByDeviceTypeCommandID").setString("deviceTypeCommandId", deviceTypeCommandId).list();
    }
    public List<DeviceCommandSchedules> getDeviceCommandSchedulesByTypeId(Session session, String deviceTypeCommandId){
        return session.getNamedQuery("getDeviceCommandSchedulesByTypeId").setString("deviceTypeCommandId", deviceTypeCommandId).list();
    }

}
