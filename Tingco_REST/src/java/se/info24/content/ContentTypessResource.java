/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.content;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
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
import se.info24.contentjaxb.TingcoContent;
import se.info24.jaxbUtil.TingcoContentXML;
import se.info24.pojo.ContentTypeTranslations;
import se.info24.pojo.ContentTypeTranslationsId;
import se.info24.pojo.ContentTypes;
import se.info24.pojo.Country;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.Users2;
import se.info24.user.CountryDAO;
import se.info24.user.UserDAO;
import se.info24.user.User_LoginsResource;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/contenttypes")
public class ContentTypessResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoContentXML tingcoContentXML = new TingcoContentXML();
    ContentDAO contentDAO = new ContentDAO();
    CountryDAO countryDAO = new CountryDAO();
    UserDAO userDAO = new UserDAO();
//    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of ContentTypessResource */
    public ContentTypessResource() {
    }

    /**
     * ContentType_Add
     * @param contentTypeName
     * @param contentTypeDesc
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/contenttypename/{contenttypename}{contenttypedesc:(/contenttypedesc/[^/]+?)?}{countryid:(/countryid/[^/]+?)?}")
    @RESTDoc(methodName = "ContentType_Add", desc = "Used to create a new ContentType", req_Params = {"ContentTypeName;String"}, opt_Params = {"ContentTypeDesc;String", "CountryID;int"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoContent getXml_Add(@PathParam("contenttypename") String contentTypeName, @PathParam("contenttypedesc") String contentTypeDesc, @PathParam("countryid") String countryId) {
        return createContentType(contentTypeName, contentTypeDesc, countryId);
    }

    /**
     * ContentType_Add
     * @param contentTypeName
     * @param contentTypeDesc
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/add/rest/{rest}/contenttypename/{contenttypename}{contenttypedesc:(/contenttypedesc/[^/]+?)?}{countryid:(/countryid/[^/]+?)?}")
    public TingcoContent postXml_Add(@PathParam("contenttypename") String contentTypeName, @PathParam("contenttypedesc") String contentTypeDesc, @PathParam("countryid") String countryId) {
        return createContentType(contentTypeName, contentTypeDesc, countryId);
    }

    /**
     * ContentType_Add
     * @param contentTypeName
     * @param contentTypeDesc
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/contenttypename/{contenttypename}{contenttypedesc:(/contenttypedesc/[^/]+?)?}{countryid:(/countryid/[^/]+?)?}")
    public TingcoContent getJson_Add(@PathParam("contenttypename") String contentTypeName, @PathParam("contenttypedesc") String contentTypeDesc, @PathParam("countryid") String countryId) {
        return createContentType(contentTypeName, contentTypeDesc, countryId);
    }

    /**
     * ContentType_Add
     * @param contentTypeName
     * @param contentTypeDesc
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/add/json/{json}/contenttypename/{contenttypename}{contenttypedesc:(/contenttypedesc/[^/]+?)?}{countryid:(/countryid/[^/]+?)?}")
    public TingcoContent postJson_Add(@PathParam("contenttypename") String contentTypeName, @PathParam("contenttypedesc") String contentTypeDesc, @PathParam("countryid") String countryId) {
        return createContentType(contentTypeName, contentTypeDesc, countryId);
    }

    /**
     * ContentType_Delete
     * @param contentTypeID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/contenttypeid/{contenttypeid}")
    @RESTDoc(methodName = "ContentType_Delete", desc = "To Delete a ContentType", req_Params = {"ContentTypeID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoContent getXml_Delete(@PathParam("contenttypeid") String contentTypeID) {
        return deleteContentType(contentTypeID);
    }

    /**
     * ContentType_Delete
     * @param contentTypeID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/contenttypeid/{contenttypeid}")
    public TingcoContent postXml_Delete(@PathParam("contenttypeid") String contentTypeID) {
        return deleteContentType(contentTypeID);
    }

    /**
     * ContentType_Delete
     * @param contentTypeID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/contenttypeid/{contenttypeid}")
    public TingcoContent getJson_Delete(@PathParam("contenttypeid") String contentTypeID) {
        return deleteContentType(contentTypeID);
    }

    /**
     * ContentType_Delete
     * @param contentTypeID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/delete/json/{json}/contenttypeid/{contenttypeid}")
    public TingcoContent postJson_Delete(@PathParam("contenttypeid") String contentTypeID) {
        return deleteContentType(contentTypeID);
    }

    /**
     * ContentType_Update
     * @param contentTypeID
     * @param contentTypeName
     * @param contentTypeDesc
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/update/rest/{rest}/contenttypeid/{contenttypeid}{contenttypename:(/contenttypename/[^/]+?)?}{contenttypedesc:(/contenttypedesc/[^/]+?)?}{countryid:(/countryid/[^/]+?)?}")
    @RESTDoc(methodName = "ContentType_Update", desc = "To Update ContentType", req_Params = {"ContentTypeID;UUID"}, opt_Params = {"ContentTypeName;String", "ContentTypeDesc;String", "CountryID;int"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoContent getXml_Update(@PathParam("contenttypeid") String contentTypeID, @PathParam("contenttypename") String contentTypeName, @PathParam("contenttypedesc") String contentTypeDesc, @PathParam("countryid") String countryID) {
        return updateContentType(contentTypeID, contentTypeName, contentTypeDesc, countryID);
    }

    /**
     * ContentType_Update
     * @param contentTypeID
     * @param contentTypeName
     * @param contentTypeDesc
     * @param countryID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/update/rest/{rest}/contenttypeid/{contenttypeid}{contenttypename:(/contenttypename/[^/]+?)?}{contenttypedesc:(/contenttypedesc/[^/]+?)?}{countryid:(/countryid/[^/]+?)?}")
    public TingcoContent postXml_Update(@PathParam("contenttypeid") String contentTypeID, @PathParam("contenttypename") String contentTypeName, @PathParam("contenttypedesc") String contentTypeDesc, @PathParam("countryid") String countryID) {
        return updateContentType(contentTypeID, contentTypeName, contentTypeDesc, countryID);
    }

    /**
     * ContentType_Update
     * @param contentTypeID
     * @param contentTypeName
     * @param contentTypeDesc
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/update/json/{json}/contenttypeid/{contenttypeid}{contenttypename:(/contenttypename/[^/]+?)?}{contenttypedesc:(/contenttypedesc/[^/]+?)?}{countryid:(/countryid/[^/]+?)?}")
    public TingcoContent getJson_Update(@PathParam("contenttypeid") String contentTypeID, @PathParam("contenttypename") String contentTypeName, @PathParam("contenttypedesc") String contentTypeDesc, @PathParam("countryid") String countryID) {
        return updateContentType(contentTypeID, contentTypeName, contentTypeDesc, countryID);
    }

    /**
     * ContentType_Update
     * @param contentTypeID
     * @param contentTypeName
     * @param contentTypeDesc
     * @param countryID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/update/json/{json}/contenttypeid/{contenttypeid}{contenttypename:(/contenttypename/[^/]+?)?}{contenttypedesc:(/contenttypedesc/[^/]+?)?}{countryid:(/countryid/[^/]+?)?}")
    public TingcoContent postJson_Update(@PathParam("contenttypeid") String contentTypeID, @PathParam("contenttypename") String contentTypeName, @PathParam("contenttypedesc") String contentTypeDesc, @PathParam("countryid") String countryID) {
        return updateContentType(contentTypeID, contentTypeName, contentTypeDesc, countryID);
    }

    /**
     * ContentType_GetInfo
     * @param contentTypeID
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/contenttypeid/{contenttypeid}/countryid/{countryid}")
    @RESTDoc(methodName = "ContentType_GetInfo", desc = "To Get a ContentType", req_Params = {"ContentTypeID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoContent getXml(@PathParam("contenttypeid") String contentTypeID, @PathParam("countryid") int countryId) {
        return getContentType(contentTypeID, countryId);
    }

    /**
     * ContentType_GetInfo
     * @param contentTypeID
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/get/rest/{rest}/contenttypeid/{contenttypeid}/countryid/{countryid}")
    public TingcoContent postXml(@PathParam("contenttypeid") String contentTypeID, @PathParam("countryid") int countryId) {
        return getContentType(contentTypeID, countryId);
    }

    /**
     * ContentType_GetInfo
     * @param contentTypeID
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/contenttypeid/{contenttypeid}/countryid/{countryid}")
    public TingcoContent getJson(@PathParam("contenttypeid") String contentTypeID, @PathParam("countryid") int countryId) {
        return getContentType(contentTypeID, countryId);
    }

    /**
     * ContentType_GetInfo
     * @param contentTypeID
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/get/json/{json}/contenttypeid/{contenttypeid}/countryid/{countryid}")
    public TingcoContent postJson(@PathParam("contenttypeid") String contentTypeID, @PathParam("countryid") int countryId) {
        return getContentType(contentTypeID, countryId);
    }

    /**
     * GetAllContentTypes
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetAllContentTypes", desc = "To Get All ContentType", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoContent getXml_All(@PathParam("countryid") int countryId) {
        return getAllContentTypes(countryId);
    }

    /**
     * GetAllContentTypes
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/rest/{rest}/countryid/{countryid}")
    public TingcoContent postXml_All(@PathParam("countryid") int countryId) {
        return getAllContentTypes(countryId);
    }

    /**
     * GetAllContentTypes
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/json/{json}/countryid/{countryid}")
    public TingcoContent getJson_All(@PathParam("countryid") int countryId) {
        return getAllContentTypes(countryId);
    }

    /**
     * GetAllContentTypes
     * @param countryId
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/json/{json}/countryid/{countryid}")
    public TingcoContent postJson_All(@PathParam("countryid") int countryId) {
        return getAllContentTypes(countryId);
    }
    /**
     * GetAllContentTypes
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getallcontenttypesbycountryid/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetAllContentTypes", desc = "To Get All ContentType", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoContent getXml_GetAllContentTypesByCountryId(@PathParam("countryid") int countryId) {
        return getAllContentTypesByCountryId(countryId);
    }
    /**
     * GetAllContentTypes
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getallcontenttypesbycountryid/json/{json}/countryid/{countryid}")
    @RESTDoc(methodName = "GetAllContentTypes", desc = "To Get All ContentType", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoContent getJson_GetAllContentTypesByCountryId(@PathParam("countryid") int countryId) {
        return getAllContentTypesByCountryId(countryId);
    }
    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public ContentTypesResource getContentTypesResource() {
        return new ContentTypesResource();
    }

    private TingcoContent getAllContentTypesByCountryId(int countryId) {
        long requestedTime = System.currentTimeMillis();
        TingcoContent tingcoContent = null;
        Session session = null;
        boolean hasPermission = false;
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (countryId <= 0) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("CountryID is should not be empty");
                    return tingcoContent;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if(hasPermission){
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ContentTypeTranslations> contentTypeTranslationsList = contentDAO.getContentTypeTranslationsByCountryId(session,countryId);
                    tingcoContent = tingcoContentXML.buildgetAllContentTypesByCountryId(tingcoContent, contentTypeTranslationsList);                    
                }else{
                    tingcoContent.getMsgStatus().setResponseCode(-10);
                    tingcoContent.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoContent;
                }          
            } else {
                tingcoContent.getMsgStatus().setResponseCode(-3);
                tingcoContent.getMsgStatus().setResponseText("User Session is Not Valid");
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoContent.getMsgStatus().setResponseCode(-1);
            tingcoContent.getMsgStatus().setResponseText("Error Occured While Saving ContentTypes");
            return tingcoContent;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContent;
    }
    
    private TingcoContent createContentType(String contentTypeName, String contentTypeDesc, String countryId) {
        long requestedTime = System.currentTimeMillis();
        TingcoContent tingcoContent = null;
        Session session = null;
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (contentTypeName == null) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("ContentTypeName is should not be empty");
                    return tingcoContent;
                }
                if (contentTypeDesc.equals("")) {
                    contentTypeDesc = null;
                } else {
                    contentTypeDesc = contentTypeDesc.split("/")[2];
                }
                if (countryId.equals("")) {
                    countryId = null;
                } else {
                    countryId = countryId.split("/")[2];
                }
                UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                session = HibernateUtil.getSessionFactory().openSession();
                GregorianCalendar gc = new GregorianCalendar();
                ContentTypes contentTypes = new ContentTypes();
                String contentTypeId = UUID.randomUUID().toString();
                contentTypes.setContentTypeId(contentTypeId);
                contentTypes.setUserId(userSessions2.getUserId());
                contentTypes.setCreatedDate(gc.getTime());
                contentTypes.setUpdatedDate(gc.getTime());
                ContentTypeTranslations ctt;

                if (countryId != null) {
                    int cid = Integer.parseInt(countryId);
                    ctt = new ContentTypeTranslations();
                    ctt.setCountry(new Country(cid));
                    ctt.setContentTypeName(contentTypeName);
                    ctt.setId(new ContentTypeTranslationsId(contentTypeId, cid));
                    if (contentTypeDesc != null) {
                        ctt.setContentTypeDescription(contentTypeDesc);
                    }
                    ctt.setContentTypes(contentTypes);
                    ctt.setUserId(userSessions2.getUserId());
                    ctt.setCreatedDate(gc.getTime());
                    ctt.setUpdatedDate(gc.getTime());
                    contentTypes.getContentTypeTranslationses().add(ctt);
                } else {
                    for (Country country : countryDAO.getAllCountries(session)) {
                        ctt = new ContentTypeTranslations();
                        ctt.setCountry(country);
                        ctt.setContentTypeName(contentTypeName);
                        ctt.setId(new ContentTypeTranslationsId(contentTypeId, country.getCountryId()));
                        if (contentTypeDesc != null) {
                            ctt.setContentTypeDescription(contentTypeDesc);
                        }
                        ctt.setContentTypes(contentTypes);
                        ctt.setUserId(userSessions2.getUserId());
                        ctt.setCreatedDate(gc.getTime());
                        ctt.setUpdatedDate(gc.getTime());
                        contentTypes.getContentTypeTranslationses().add(ctt);
                    }
                }
                if (!contentDAO.saveContentTypes(contentTypes, session)) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("Eroor Occured While Saving the ContentTypes");
                    return tingcoContent;
                }
            } else {
                tingcoContent.getMsgStatus().setResponseCode(-3);
                tingcoContent.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContent;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoContent.getMsgStatus().setResponseCode(-1);
            tingcoContent.getMsgStatus().setResponseText("Error Occured While Saving ContentTypes");
            return tingcoContent;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContent;
    }

    private TingcoContent deleteContentType(String contentTypeId) {
        long requestedTime = System.currentTimeMillis();
        TingcoContent tingcoContent = null;
        Session session = null;
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (contentTypeId == null) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("ContentTypeID is should not be empty");
                    return tingcoContent;
                }
                session = HibernateUtil.getSessionFactory().openSession();
                ContentTypes ct = contentDAO.getDeviceTypesById(contentTypeId, session);
                if (ct != null) {
                    if (!contentDAO.deleteContentType(ct, session)) {
                        tingcoContent.getMsgStatus().setResponseCode(-1);
                        tingcoContent.getMsgStatus().setResponseText("Error Occured while Deleting ContentTypes");
                        return tingcoContent;
                    }
                }
            } else {
                tingcoContent.getMsgStatus().setResponseCode(-3);
                tingcoContent.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContent;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoContent.getMsgStatus().setResponseCode(-1);
            tingcoContent.getMsgStatus().setResponseText("Error Occured While Saving ContentTypes");
            return tingcoContent;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContent;
    }

    private TingcoContent updateContentType(String contentTypeID, String contentTypeName, String contentTypeDesc, String countryID) {
        long requestedTime = System.currentTimeMillis();
        TingcoContent tingcoContent = null;
        Session session = null;
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (contentTypeID == null) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("ContentTypeID is should not be empty");
                    return tingcoContent;
                }

                if (contentTypeName.equals("")) {
                    contentTypeName = null;
                } else {
                    contentTypeName = contentTypeName.split("/")[2];
                }

                if (contentTypeDesc.equals("")) {
                    contentTypeDesc = null;
                } else {
                    contentTypeDesc = contentTypeDesc.split("/")[2];
                }

                if (countryID.equals("")) {
                    countryID = null;
                } else {
                    countryID = countryID.split("/")[2];
                }

                if (contentTypeName == null && contentTypeDesc == null && countryID == null) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("Alleast One Value Should Present");
                    return tingcoContent;
                }

                session = HibernateUtil.getSessionFactory().openSession();
                ContentTypes ct = contentDAO.getDeviceTypesById(contentTypeID, session);
                if (ct != null) {
                    UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                    ct.setUserId(userSessions2.getUserId());
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    ct.setUpdatedDate(gc.getTime());
                    Set<ContentTypeTranslations> cttSet = ct.getContentTypeTranslationses();
                    if (countryID != null) {
                        int cid = Integer.parseInt(countryID);
                        boolean flag = true;
                        for (ContentTypeTranslations ctt : cttSet) {
                            if (ctt.getCountry().getCountryId() == cid) {
                                if (contentTypeName != null) {
                                    ctt.setContentTypeName(contentTypeName);
                                }
                                if (contentTypeDesc != null) {
                                    ctt.setContentTypeDescription(contentTypeDesc);
                                }
                                ctt.setUpdatedDate(gc.getTime());
                                flag = false;
                            }
                        }
                        if (flag && contentTypeName != null) { // The Provided CountryID Not Found in Translations and TypeName shuold not be null-> It is a New Translation.
                            ContentTypeTranslations ctt = new ContentTypeTranslations();
                            ctt.setCountry(new Country(cid));
                            ctt.setContentTypeName(contentTypeName);
                            ctt.setId(new ContentTypeTranslationsId(ct.getContentTypeId(), cid));
                            if (contentTypeDesc != null) {
                                ctt.setContentTypeDescription(contentTypeDesc);
                            }
                            ctt.setContentTypes(ct);
                            ctt.setUserId(userSessions2.getUserId());
                            ctt.setCreatedDate(gc.getTime());
                            ctt.setUpdatedDate(gc.getTime());
                            ct.getContentTypeTranslationses().add(ctt);
                        }
                    } else { // If CountryID is not presented and ContentTypeName shuold present then we will update ContentTypeName for all Translation records.
                        if (contentTypeName != null) {
                            for (ContentTypeTranslations ctt : cttSet) {
                                ctt.setContentTypeName(contentTypeName);
                                if (contentTypeDesc != null) {
                                    ctt.setContentTypeDescription(contentTypeDesc);
                                }
                                ctt.setUpdatedDate(gc.getTime());
                            }
                        }
                    }
                    if (!contentDAO.saveContentTypes(ct, session)) {
                        tingcoContent.getMsgStatus().setResponseCode(-1);
                        tingcoContent.getMsgStatus().setResponseText("Error Occured While Updating the ContentTypes");
                        return tingcoContent;
                    }
                } else {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("ContentTypeID Not Found");
                    return tingcoContent;
                }
            } else {
                tingcoContent.getMsgStatus().setResponseCode(-3);
                tingcoContent.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContent;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoContent.getMsgStatus().setResponseCode(-1);
            tingcoContent.getMsgStatus().setResponseText("Error Occured While Saving ContentTypes");
            return tingcoContent;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContent;
    }

    private TingcoContent getContentType(String contentTypeID, int countryId) {
        long requestedTime = System.currentTimeMillis();
        TingcoContent tingcoContent = null;
        Session session = null;
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (contentTypeID == null) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("ContentTypeID is should not be empty");
                    return tingcoContent;
                } else if (countryId <= 0) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("CountryID is should not be empty");
                    return tingcoContent;
                }

                session = HibernateUtil.getSessionFactory().openSession();
                ContentTypes ct = contentDAO.getDeviceTypesById(contentTypeID, session);
                if (ct != null) {
                    ArrayList<ContentTypes> typesList = new ArrayList<ContentTypes>();
                    typesList.add(ct);
                    Users2 user2 = userDAO.getUserById(ct.getUserId(), session);
                    HashMap<String, Users2> userMap = new HashMap<String, Users2>();
                    userMap.put(ct.getUserId(), user2);
                    tingcoContent = tingcoContentXML.buildContentTypes(tingcoContent, typesList, userMap, countryId);
                } else {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("ContentTypeID Not Found");
                    return tingcoContent;
                }
            } else {
                tingcoContent.getMsgStatus().setResponseCode(-3);
                tingcoContent.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoContent;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoContent.getMsgStatus().setResponseCode(-1);
            tingcoContent.getMsgStatus().setResponseText("Error Occured While Saving ContentTypes");
            return tingcoContent;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContent;
    }

    private TingcoContent getAllContentTypes(int countryId) {
        long requestedTime = System.currentTimeMillis();
        TingcoContent tingcoContent = null;
        Session session = null;
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (countryId <= 0) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("CountryID is should not be empty");
                    return tingcoContent;
                }
                session = HibernateUtil.getSessionFactory().openSession();
                List<ContentTypes> ctList = contentDAO.getAllContentTypes(session);
                if (ctList != null) {
                    HashMap<String, Users2> userMap = new HashMap<String, Users2>();
                    for (ContentTypes contentTypes : ctList) {
                        if (!userMap.containsKey(contentTypes.getUserId())) {
                            Users2 user2 = userDAO.getUserById(contentTypes.getUserId(), session);
                            userMap.put(contentTypes.getUserId(), user2);
                        }
                    }
                    tingcoContent = tingcoContentXML.buildContentTypes(tingcoContent, ctList, userMap, countryId);
                    return tingcoContent;
                } else {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("Error Occured while Loading the ContentTypes");
                    return tingcoContent;
                }
            } else {
                tingcoContent.getMsgStatus().setResponseCode(-3);
                tingcoContent.getMsgStatus().setResponseText("User Session is Not Valid");
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoContent.getMsgStatus().setResponseCode(-1);
            tingcoContent.getMsgStatus().setResponseText("Error Occured While Saving ContentTypes");
            return tingcoContent;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContent;
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}
