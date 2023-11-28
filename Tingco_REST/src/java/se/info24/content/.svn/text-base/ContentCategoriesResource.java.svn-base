/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.content;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.contentjaxb.TingcoContent;
import se.info24.jaxbUtil.TingcoContentXML;
import se.info24.pojo.ContentCategories;
import se.info24.pojo.ContentCategoriesInGroups;
import se.info24.pojo.ContentCategoryTranslations;
import se.info24.pojo.ContentCategoryTranslationsId;
import se.info24.pojo.Country;
import se.info24.pojo.ObjectContentCategorySubscriptions;
import se.info24.pojo.ServiceContentSubscriptions;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.products.ProductsDAO;
import se.info24.restUtil.RestUtilDAO;
import se.info24.service.ServiceDAO;
import se.info24.user.User_LoginsResource;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/contentcategory")
public class ContentCategoriesResource {

    @Context
    private UriInfo context;
    TingcoContentXML tingcoContentXML = new TingcoContentXML();
    ContentDAO contentDAO = new ContentDAO();
    @Context
    private HttpServletRequest request;
    ContentCategoryTranslations contentCategoryTranslation;
//    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of ContentCategoriesResource */
    public ContentCategoriesResource() {
    }

    /**
     * ContentCategory_Add
     * @param countryID
     * @param contentCategoryName
     * @param contentCategoryParentID
     * @param displayOrder
     * @param contentCategoryDesc
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/countryid/{countryid}/contentcategoryname/{contentcategoryname}{contentcategoryparentid:(/contentcategoryparentid/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}{contentcategorydescription:(/contentcategorydescription/[^/]+?)?}")
    @RESTDoc(methodName = "ContentCategory_Add", desc = "Add's a ContentCategory", req_Params = {"CountryID;int", "ContentCategoryName;String"}, opt_Params = {"ContentCategoryParentID;String", "DisplayOrder;String", "ContentCategoryDescription;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoContent getXml_Add(@PathParam("countryid") int countryID, @PathParam("contentcategoryname") String contentCategoryName, @PathParam("contentcategoryparentid") String contentCategoryParentID, @PathParam("displayorder") String displayOrder, @PathParam("contentcategorydescription") String contentCategoryDesc) {
        return isAddedContentCategory(countryID, contentCategoryName, contentCategoryParentID, displayOrder, contentCategoryDesc);
    }

    /**
     * ContentCategory_Add
     * @param countryID
     * @param contentCategoryName
     * @param contentCategoryParentID
     * @param displayOrder
     * @param contentCategoryDesc
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/add/rest/{rest}/countryid/{countryid}/contentcategoryname/{contentcategoryname}{contentcategoryparentid:(/contentcategoryparentid/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}{contentcategorydescription:(/contentcategorydescription/[^/]+?)?}")
    public TingcoContent postXml_Add(@PathParam("countryid") int countryID, @PathParam("contentcategoryname") String contentCategoryName, @PathParam("contentcategoryparentid") String contentCategoryParentID, @PathParam("displayorder") String displayOrder, @PathParam("contentcategorydescription") String contentCategoryDesc) {
        return isAddedContentCategory(countryID, contentCategoryName, contentCategoryParentID, displayOrder, contentCategoryDesc);
    }

    /**
     * ContentCategory_Add
     * @param countryID
     * @param contentCategoryName
     * @param contentCategoryParentID
     * @param displayOrder
     * @param contentCategoryDesc
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/countryid/{countryid}/contentcategoryname/{contentcategoryname}{contentcategoryparentid:(/contentcategoryparentid/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}{contentcategorydescription:(/contentcategorydescription/[^/]+?)?}")
    public TingcoContent getJson_Add(@PathParam("countryid") int countryID, @PathParam("contentcategoryname") String contentCategoryName, @PathParam("contentcategoryparentid") String contentCategoryParentID, @PathParam("displayorder") String displayOrder, @PathParam("contentcategorydescription") String contentCategoryDesc) {
        return isAddedContentCategory(countryID, contentCategoryName, contentCategoryParentID, displayOrder, contentCategoryDesc);
    }

    /**
     * ContentCategory_Add
     * @param countryID
     * @param contentCategoryName
     * @param contentCategoryParentID
     * @param displayOrder
     * @param contentCategoryDesc
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/add/json/{json}/countryid/{countryid}/contentcategoryname/{contentcategoryname}{contentcategoryparentid:(/contentcategoryparentid/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}{contentcategorydescription:(/contentcategorydescription/[^/]+?)?}")
    public TingcoContent postJson_Add(@PathParam("countryid") int countryID, @PathParam("contentcategoryname") String contentCategoryName, @PathParam("contentcategoryparentid") String contentCategoryParentID, @PathParam("displayorder") String displayOrder, @PathParam("contentcategorydescription") String contentCategoryDesc) {
        return isAddedContentCategory(countryID, contentCategoryName, contentCategoryParentID, displayOrder, contentCategoryDesc);
    }

    /**
     * ContentCategory_Delete
     * @param contentCategoryID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/contentcategoryid/{contentcategoryid}")
    @RESTDoc(methodName = "ContentCategory_Delete", desc = "To Delete a ContentCategory", req_Params = {"ContentCategoryID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoContent getXml_Delete(@PathParam("contentcategoryid") String contentCategoryID) {
        return deleteContentCategory(contentCategoryID);
    }

    /**
     * ContentCategory_Delete
     * @param contentCategoryID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/contentcategoryid/{contentcategoryid}")
    public TingcoContent postXml_Delete(@PathParam("contentcategoryid") String contentCategoryID) {
        return deleteContentCategory(contentCategoryID);
    }

    /**
     * ContentCategory_Delete
     * @param contentCategoryID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/contentcategoryid/{contentcategoryid}")
    public TingcoContent getJson_Delete(@PathParam("contentcategoryid") String contentCategoryID) {
        return deleteContentCategory(contentCategoryID);
    }

    /**
     * ContentCategory_Delete
     * @param contentCategoryID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/delete/json/{json}/contentcategoryid/{contentcategoryid}")
    public TingcoContent postJson_Delete(@PathParam("contentcategoryid") String contentCategoryID) {
        return deleteContentCategory(contentCategoryID);
    }

    /**
     * ContentCategory_Update
     * @param catID
     * @param countryID
     * @param catParID
     * @param dOrder
     * @param contentCategoryName
     * @param contentCategoryDescription
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/update/rest/{rest}/contentcategoryid/{contentcategoryid}/countryid/{countryid}{contentcategoryparentid:(/contentcategoryparentid/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}{contentcategoryname:(/contentcategoryname/[^/]+?)?}{contentcategorydescription:(/contentcategorydescription/[^/]+?)?}")
    @RESTDoc(methodName = "ContentCategory_Update", desc = "To Update ContentCategory", req_Params = {"ContentCategoryID;UUID", "CountryID;String"}, opt_Params = {"ContentCategoryParentID;String", "DisplayOrder;String", "ContentCategoryName;String", "ContentCategoryDescription;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoContent getXml_Update(@PathParam("contentcategoryid") String catID, @PathParam("countryid") String countryID, @PathParam("contentcategoryparentid") String catParID, @PathParam("displayorder") String dOrder, @PathParam("contentcategoryname") String contentCategoryName, @PathParam("contentcategorydescription") String contentCategoryDescription) {
        return updateContentCategory(catID, catParID, countryID, dOrder, contentCategoryName, contentCategoryDescription);
    }

    /**
     * ContentCategory_Update
     * @param catID
     * @param countryID
     * @param catParID
     * @param dOrder
     * @param contentCategoryName
     * @param contentCategoryDescription
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/update/rest/{rest}/contentcategoryid/{contentcategoryid}/countryid/{countryid}{contentcategoryparentid:(/contentcategoryparentid/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}{contentcategoryname:(/contentcategoryname/[^/]+?)?}{contentcategorydescription:(/contentcategorydescription/[^/]+?)?}")
    public TingcoContent postXml_Update(@PathParam("contentcategoryid") String catID, @PathParam("countryid") String countryID, @PathParam("contentcategoryparentid") String catParID, @PathParam("displayorder") String dOrder, @PathParam("contentcategoryname") String contentCategoryName, @PathParam("contentcategorydescription") String contentCategoryDescription) {
        return updateContentCategory(catID, catParID, countryID, dOrder, contentCategoryName, contentCategoryDescription);
    }

    /**
     * ContentCategory_Update
     * @param catID
     * @param countryID
     * @param catParID
     * @param dOrder
     * @param contentCategoryName
     * @param contentCategoryDescription
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/update/json/{json}/contentcategoryid/{contentcategoryid}/countryid/{countryid}{contentcategoryparentid:(/contentcategoryparentid/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}{contentcategoryname:(/contentcategoryname/[^/]+?)?}{contentcategorydescription:(/contentcategorydescription/[^/]+?)?}")
    public TingcoContent getJson_Update(@PathParam("contentcategoryid") String catID, @PathParam("countryid") String countryID, @PathParam("contentcategoryparentid") String catParID, @PathParam("displayorder") String dOrder, @PathParam("contentcategoryname") String contentCategoryName, @PathParam("contentcategorydescription") String contentCategoryDescription) {
        return updateContentCategory(catID, catParID, countryID, dOrder, contentCategoryName, contentCategoryDescription);
    }

    /**
     * ContentCategory_Update
     * @param catID
     * @param countryID
     * @param catParID
     * @param dOrder
     * @param contentCategoryName
     * @param contentCategoryDescription
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/update/json/{json}/contentcategoryid/{contentcategoryid}/countryid/{countryid}{contentcategoryparentid:(/contentcategoryparentid/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}{contentcategoryname:(/contentcategoryname/[^/]+?)?}{contentcategorydescription:(/contentcategorydescription/[^/]+?)?}")
    public TingcoContent postJson_Update(@PathParam("contentcategoryid") String catID, @PathParam("countryid") String countryID, @PathParam("contentcategoryparentid") String catParID, @PathParam("displayorder") String dOrder, @PathParam("contentcategoryname") String contentCategoryName, @PathParam("contentcategorydescription") String contentCategoryDescription) {
        return updateContentCategory(catID, catParID, countryID, dOrder, contentCategoryName, contentCategoryDescription);
    }

    /**
     * ContentCategories_Get
     * @param groupId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "ContentCategories_Get", desc = "Return's contentid,contentparentid and contentname", req_Params = {"GroupID;UUID,CountryID;int"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoContent getXml(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        try {
            return getContentCategories(groupId, countryId);
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        return null;
    }

    /**
     * ContentCategories_Get
     * @param groupId
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/groupid/{groupid}/countryid/{countryid}")
    public TingcoContent getJson(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        try {
            return getContentCategories(groupId, countryId);
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        return null;
    }

    /**
     * GetContentCategoriesList
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontentcategorieslist/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetContentCategoriesList", desc = "Return's contentid,contentparentid, contentcategoryname and contentcategoryparentname", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContent getXml_getContentCategoriesList(@PathParam("countryid") int countryId) {
        return getContentCategoriesList(countryId);
    }

    /**
     * GetContentCategoriesList
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getcontentcategorieslist/json/{json}/countryid/{countryid}")
    public TingcoContent getJsongetContentCategoriesList(@PathParam("countryid") int countryId) {
        return getContentCategoriesList(countryId);
    }

    /**
     * GetContentCategoriesByGroupId
     * @param groupid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontentcategoriesbygroupid/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetContentCategoriesByGroupId", desc = "Used to Get ContentCategories By GroupId", req_Params = {"GroupId;UUID", "CountryId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContent getXml_getContentCategoriesByGroupId(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getContentCategoriesByGroupId(groupId, countryId);
    }

    /**
     * GetContentCategoriesByGroupId
     * @param groupid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getcontentcategoriesbygroupid/json/{json}/groupid/{groupid}/countryid/{countryid}")
    public TingcoContent getJson_getContentCategoriesByGroupId(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        return getContentCategoriesByGroupId(groupId, countryId);
    }

    /**
     * ContentCategories_Get
     * @param groupId
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/get/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    public TingcoContent postXml(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        try {
            return getContentCategories(groupId, countryId);
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        return null;
    }

    /**
     * ContentCategories_Get
     * @param groupId
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/get/json/{json}/groupid/{groupid}/countryid/{countryid}")
    public TingcoContent postJson(@PathParam("groupid") String groupId, @PathParam("countryid") int countryId) {
        try {
            return getContentCategories(groupId, countryId);
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        return null;
    }

    /**z
     * ContentCategories_Get
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "ContentCategories_Get", desc = "Return's ContentCategoryid and ContentCategoryName", req_Params = {"CountryID;int"}, opt_Params = {"searchstring;string"}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContent getContentCategoryXml(@PathParam("countryid") int countryid, @PathParam("searchstring") String searchString) {
        try {
            return getContentCategoriesByCountryId(countryid, searchString);
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return null;
        }
    }

    /**
     * ContentCategories_Get
     * @param countryid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoContent getContentCategoryjson(@PathParam("countryid") int countryid, @PathParam("searchstring") String searchString) {
        try {
            return getContentCategoriesByCountryId(countryid, searchString);
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return null;
        }
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public ContentCategoryResource getContentCategoryResource() {
        return new ContentCategoryResource();
    }

    private TingcoContent getContentCategoriesList(int countryId) {
        long requestedTime = System.currentTimeMillis();
        TingcoContent tingcoContent = null;
        Session session = null;
        boolean hasPermission = false;
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.CONTENT, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
//                    HashMap<String, ContentCategories> contentMap = new HashMap<String, ContentCategories>();
//                    for (ContentCategories cc : contentDAO.getAllContentCategories(session)) {
//                        contentMap.put(cc.getContentCategoryId(), cc);
//                    }
                    List<ContentCategoryTranslations> cctList = contentDAO.getContentCategoryTranslationsByCountryId(countryId, null, session);
                    List<ContentCategories> contentCategories = contentDAO.getAllContentCategories(session);
                    if (!cctList.isEmpty()) {
                        UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        tingcoContent = tingcoContentXML.buildContentCategoriesList(tingcoContent, contentCategories, cctList, timeZoneGMToffset);
                        return tingcoContent;
                    } else {
                        tingcoContent.getMsgStatus().setResponseCode(-1);
                        tingcoContent.getMsgStatus().setResponseText("No data found");
                        return tingcoContent;
                    }
                } else {
                    tingcoContent.getMsgStatus().setResponseCode(-10);
                    tingcoContent.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContent;
                }
            } else {
                tingcoContent.getMsgStatus().setResponseCode(-3);
                tingcoContent.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContent;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContent.getMsgStatus().setResponseCode(-1);
            tingcoContent.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContent;
    }

    private TingcoContent isAddedContentCategory(int countryID, String contentCategoryName, String contentCategoryParentID, String displayOrder, String contentCategoryDesc) {
        long requestedTime = System.currentTimeMillis();
        TingcoContent tingcoContent = null;
        Session session = null;
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (countryID <= 0) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("CountryID is should not be empty");
                    return tingcoContent;
                }
                if (contentCategoryName == null) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("ContentCategoryName is should not be empty");
                    return tingcoContent;
                }
                if (contentCategoryParentID.equals("")) {
                    contentCategoryParentID = null;
                } else {
                    contentCategoryParentID = contentCategoryParentID.split("/")[2];
                }
                if (displayOrder.equals("")) {
                    displayOrder = null;
                } else {
                    displayOrder = displayOrder.split("/")[2];
                }
                if (contentCategoryDesc.equals("")) {
                    contentCategoryDesc = null;
                } else {
                    contentCategoryDesc = contentCategoryDesc.split("/")[2];
                }
                UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                session = HibernateUtil.getSessionFactory().openSession();
                GregorianCalendar gc = new GregorianCalendar();
                ContentCategories contentCategories = new ContentCategories();
                String contentCategoryId = UUID.randomUUID().toString();
                contentCategories.setContentCategoryId(contentCategoryId);
                contentCategories.setContentCategoryParentId(contentCategoryParentID);
                if (displayOrder != null) {
                    contentCategories.setDisplayOrder(Integer.parseInt(displayOrder));
                }
                contentCategories.setUserId(userSessions2.getUserId());
                contentCategories.setCreatedDate(gc.getTime());
                contentCategories.setUpdatedDate(gc.getTime());

                contentCategoryTranslation = new ContentCategoryTranslations();
                contentCategoryTranslation.setId(new ContentCategoryTranslationsId(contentCategoryId, countryID));
                contentCategoryTranslation.setCountry(new Country(countryID));
                contentCategoryTranslation.setContentCategoryName(contentCategoryName);
                contentCategoryTranslation.setContentCategoryDescription(contentCategoryDesc);
                contentCategoryTranslation.setUserId(userSessions2.getUserId());
                contentCategoryTranslation.setCreatedDate(gc.getTime());
                contentCategoryTranslation.setUpdatedDate(gc.getTime());
                contentCategories.getContentCategoryTranslationses().add(contentCategoryTranslation);
                boolean added = contentDAO.addContentCategory(contentCategories, session);
                if (!added) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("Error while adding ContentCategory");
                    return tingcoContent;
                }
            } else {
                tingcoContent.getMsgStatus().setResponseCode(-3);
                tingcoContent.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContent;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContent.getMsgStatus().setResponseCode(-1);
            tingcoContent.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContent;
    }

    private TingcoContent deleteContentCategory(String contentCategoryId) {
        long requestedTime = System.currentTimeMillis();
        TingcoContent tingcoContent = null;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (contentCategoryId == null) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("ContentCategoryID is should not be empty");
                    return tingcoContent;
                }
                session = HibernateUtil.getSessionFactory().openSession();
                tx = session.beginTransaction();
                Object ct = session.get(ContentCategories.class, contentCategoryId);
                if (ct != null) {
                    ServiceDAO serviceDAO = new ServiceDAO();
                    int i = 0;
                    List<ServiceContentSubscriptions> scsList = serviceDAO.getServiceContentSubscriptionsByContentCategoryId(session, contentCategoryId);
                    if (!scsList.isEmpty()) {
                        for (ServiceContentSubscriptions scs : scsList) {
                            session.delete(scs);
                            i++;
                            if (i % 20 == 0) {
                                session.flush();
                                session.clear();
                            }
                        }
                    }
                    session.delete(ct);
                    tx.commit();
                } else {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("ContentCategory Not Found");
                    return tingcoContent;
                }
            } else {
                tingcoContent.getMsgStatus().setResponseCode(-3);
                tingcoContent.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContent;
            }
        } catch (Exception ex) {
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContent.getMsgStatus().setResponseCode(-1);
            tingcoContent.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContent;
    }

    private TingcoContent updateContentCategory(String catID, String catParID, String countryID, String dOrder, String contentCategoryName, String contentCategoryDescription) {
        long requestedTime = System.currentTimeMillis();
        TingcoContent tingcoContent = null;
        Session session = null;
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (catID == null) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("ContentCategoryID is should not be empty");
                    return tingcoContent;
                }
                if (countryID == null) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("CountryID is should not be empty");
                    return tingcoContent;
                }
                if (catParID.equals("")) {
                    catParID = null;
                } else {
                    catParID = catParID.split("/")[2];
                }
                if (dOrder.equals("")) {
                    dOrder = null;
                } else {
                    dOrder = dOrder.split("/")[2];
                }
                if (catParID == null && countryID == null && dOrder == null && contentCategoryName == null && contentCategoryDescription == null) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("Alleast One Value Should Present");
                    return tingcoContent;
                }
                session = HibernateUtil.getSessionFactory().openSession();
                Object ccObject = contentDAO.getContentCategoryById(catID, session);
                if (ccObject != null) {
                    ContentCategories cc = (ContentCategories) ccObject;
                    UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    if (contentCategoryDescription.equals("")) {
                        contentCategoryDescription = null;
                    } else {
                        contentCategoryDescription = contentCategoryDescription.split("/")[2];
                    }
                    if (contentCategoryName.equals("")) {
                        contentCategoryName = null;
                    } else {
                        contentCategoryName = contentCategoryName.split("/")[2];
                    }

                    if (contentCategoryDescription != null || contentCategoryName != null) {
                        Set<ContentCategoryTranslations> transSet = cc.getContentCategoryTranslationses();
                        for (ContentCategoryTranslations cct : transSet) {
                            if (cct.getCountry().getCountryId() == Integer.parseInt(countryID)) {
                                if (contentCategoryName != null) {
                                    cct.setContentCategoryName(contentCategoryName);
                                }
                                if (contentCategoryDescription != null) {
                                    cct.setContentCategoryDescription(contentCategoryDescription);
                                }
                                cct.setUserId(userSessions2.getUserId());
                                cct.setUpdatedDate(gc.getTime());

                            }
                        }
                    }

                    if (dOrder != null) {
                        cc.setDisplayOrder(Integer.valueOf(dOrder));
                    }
                    cc.setUserId(userSessions2.getUserId());

                    cc.setUpdatedDate(gc.getTime());

                    if (!contentDAO.saveContentCategory(cc, session)) {
                        tingcoContent.getMsgStatus().setResponseCode(-1);
                        tingcoContent.getMsgStatus().setResponseText("Error Occured While Updating the ContentCategory");
                        return tingcoContent;
                    }
                } else {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("ContentCategory Not Found");
                    return tingcoContent;
                }
            } else {
                tingcoContent.getMsgStatus().setResponseCode(-3);
                tingcoContent.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContent;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContent.getMsgStatus().setResponseCode(-1);
            tingcoContent.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContent;
    }

    private TingcoContent getContentCategories(String groupId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        TingcoContent tingcoContent = null;
        Session session = null;
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (groupId.equals("")) {
                tingcoContent.getMsgStatus().setResponseCode(-1);
                tingcoContent.getMsgStatus().setResponseText("Group ID should not be empty");
                return tingcoContent;
            } else if (countryId <= 0) {
                tingcoContent.getMsgStatus().setResponseCode(-1);
                tingcoContent.getMsgStatus().setResponseText("Country ID should not be empty");
                return tingcoContent;
            }
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                List<ObjectContentCategorySubscriptions> objectCategoryList = contentDAO.getObjectContentCategories(groupId, session);
                HashMap<String, ContentCategories> contentMap = new HashMap<String, ContentCategories>();
                if (!objectCategoryList.isEmpty()) {
                    for (ContentCategories cc : contentDAO.getAllContentCategories(session)) {
                        contentMap.put(cc.getContentCategoryId(), cc);
                    }
                    tingcoContent = tingcoContentXML.buildContentCategories(tingcoContent, objectCategoryList, contentMap, countryId);
                    return tingcoContent;
                } else {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("ContentCategories Not Found");
                    return tingcoContent;
                }
            } else {
                tingcoContent.getMsgStatus().setResponseCode(-3);
                tingcoContent.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContent;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContent.getMsgStatus().setResponseCode(-1);
            tingcoContent.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContent;
    }

    private TingcoContent getContentCategoriesByCountryId(int countryId, String searchString) {
        long requestedTime = System.currentTimeMillis();
        TingcoContent tingcoContent = null;
        boolean hasPermission = false;
        Session session = null;
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.CONTENT, TCMUtil.READ);
                if (hasPermission) {
                    if (countryId <= 0) {
                        tingcoContent.getMsgStatus().setResponseCode(-1);
                        tingcoContent.getMsgStatus().setResponseText("Country ID should not be empty");
                        return tingcoContent;
                    }
                    try {
                        session = HibernateUtil.getSessionFactory().openSession();
                        if (!searchString.equals("")) {
                            searchString = searchString.split("/")[2];
                        } else {
                            searchString = null;
                        }
                        List<ContentCategoryTranslations> cct = contentDAO.getContentCategoryTranslationsByCountryId(countryId, searchString, session);
                        if (!cct.isEmpty()) {
                            tingcoContent = tingcoContentXML.buildContentCategories(tingcoContent, cct);
                        } else {
                            tingcoContent.getMsgStatus().setResponseCode(-1);
                            tingcoContent.getMsgStatus().setResponseText("No data found");
                            return tingcoContent;
                        }
                        return tingcoContent;
                    } catch (Exception e) {
                        tingcoContent.getMsgStatus().setResponseCode(-1);
                        tingcoContent.getMsgStatus().setResponseText("Error Occured While loading ServiceTypes");
                        return tingcoContent;
                    }

                } else {
                    tingcoContent.getMsgStatus().setResponseCode(-10);
                    tingcoContent.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContent;
                }
            } else {
                tingcoContent.getMsgStatus().setResponseCode(-3);
                tingcoContent.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContent;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContent.getMsgStatus().setResponseCode(-1);
            tingcoContent.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
        }
        delayLog(requestedTime);
        return tingcoContent;
    }

    private TingcoContent getContentCategoriesByGroupId(String groupId, int countryId) {
        Session session = null;
        boolean hasPermission = false;
        long requestedTime = System.currentTimeMillis();
        TingcoContent tingcoContent = null;
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.SERVICES, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ContentCategoriesInGroups> ccigList = contentDAO.getContentCategoriesInGroupsByGroupId(groupId, session);
                    if (!ccigList.isEmpty()) {
                        ProductsDAO productsDAO = new ProductsDAO();
                        Set<String> contentCategoryIds = contentDAO.getContentCategoryIds(ccigList);
                        List<ContentCategoryTranslations> cct = productsDAO.getContentCategoryTranslations(contentCategoryIds, countryId, session);
                        tingcoContent = tingcoContentXML.buildContentCategories(tingcoContent, cct);
                    } else {
                        tingcoContent.getMsgStatus().setResponseCode(-1);
                        tingcoContent.getMsgStatus().setResponseText("No data found");
                        return tingcoContent;
                    }
                } else {
                    tingcoContent.getMsgStatus().setResponseCode(-10);
                    tingcoContent.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContent;
                }
            } else {
                tingcoContent.getMsgStatus().setResponseCode(-3);
                tingcoContent.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContent;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContent.getMsgStatus().setResponseCode(-1);
            tingcoContent.getMsgStatus().setResponseText("Error occured");
            return tingcoContent;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContent;
    }

    private boolean getPermission(String userId, String functionarea, String operation) {
        boolean hasPermission = false;
        Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(userId);
        if (ht.containsKey(functionarea)) {
            ArrayList<String> operations = ht.get(functionarea);
            for (int i = 0; i < operations.size(); i++) {
                if (operations.get(i).equalsIgnoreCase(operation)) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}
