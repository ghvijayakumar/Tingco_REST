/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.content;

import java.util.ArrayList;
import java.util.GregorianCalendar;
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
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.contentjaxb.Attribute;
import se.info24.contentjaxb.ContentAttribute;
import se.info24.contentjaxb.TingcoContent;
import se.info24.jaxbUtil.TingcoContentXML;
import se.info24.pojo.ContentAttributeTranslations;
import se.info24.pojo.ContentAttributeTranslationsId;
import se.info24.pojo.ContentAttributes;
import se.info24.pojo.ContentCategoryAttributes;
import se.info24.pojo.Country;
import se.info24.pojo.UserSessions2;
import se.info24.user.CountryDAO;
import se.info24.user.User_LoginsResource;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/contentattributes")
public class ContentAttributesResource {

    @Context
    private UriInfo context;
    TingcoContentXML tingcoContentXML = new TingcoContentXML();
    ContentDAO contentDAO = new ContentDAO();
    @Context
    private HttpServletRequest request;
    GregorianCalendar currentDateTime = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
//    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of ContentAttributesResource */
    public ContentAttributesResource() {
    }

    /**
     * ContentAttribute_Add
     * @param dataFieldName
     * @param dataGroupName
     * @param unit
     * @return
     */
    @GET
    @Path("/add/rest/{rest}/cdmfieldname/{cdmfieldname}/cdmgroupname/{cdmgroupname}{unit:(/unit/[^/]+?)?}")
    @Produces("application/xml")
    @RESTDoc(methodName = "ContentAttribute_Add", desc = "Add a  new ContentAttribute", req_Params = {"CDMFieldName;String", "CDMGroupName;String"}, opt_Params = {"Unit;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContent getXml_Add(@PathParam("cdmfieldname") String dataFieldName, @PathParam("cdmgroupname") String dataGroupName, @PathParam("unit") String unit) throws DatatypeConfigurationException {
        return isAddedContentAttribute(dataFieldName, dataGroupName, unit);
    }

    /**
     * ContentAttribute_Add
     * @param dataFieldName
     * @param dataGroupName
     * @param unit
     * @return
     */
    @GET
    @Path("/add/json/{json}/cdmfieldname/{cdmfieldname}/cdmgroupname/{cdmgroupname}{unit:(/unit/[^/]+?)?}")
    @Produces("application/json")
    public TingcoContent getJson_Add(@PathParam("cdmfieldname") String dataFieldName, @PathParam("cdmgroupname") String dataGroupName, @PathParam("unit") String unit) throws DatatypeConfigurationException {
        return isAddedContentAttribute(dataFieldName, dataGroupName, unit);
    }

    /**
     * ContentAttribute_Delete
     * @param id
     * @return
     */
    @GET
    @Path("/delete/rest/{rest}/contentattributeid/{contentattributeid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "ContentAttribute_Delete", desc = "Delete a  new ContentAttribute", req_Params = {"ContentAttributeID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContent getXml_Delete(@PathParam("contentattributeid") String id) {
        return isDeletedContentAttribute(id);
    }

    /**
     * GetAllContentAttributes
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getallcontentattributes/rest/{rest}")
    @RESTDoc(methodName = "GetAllContentAttributes", desc = "Used to Get All ContentAttributes", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContent getXml_getAllContentAttributes() throws DatatypeConfigurationException {
        return getAllContentAttributes();
    }

    /**
     * GetAllContentAttributes
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getallcontentattributes/json/{json}")
    public TingcoContent getJson_getAllContentAttributes() throws DatatypeConfigurationException {
        return getAllContentAttributes();
    }

    /**
     * ContentAttribute_Delete
     * @param id
     * @return
     */
    @GET
    @Path("/delete/json/{json}/contentattributeid/{contentattributeid}")
    @Produces("application/json")
    public TingcoContent getJson_Delete(@PathParam("contentattributeid") String id) {
        return isDeletedContentAttribute(id);
    }

    /**
     * ContentAttribute_Update
     * @param id
     * @param dataFieldName
     * @param dataGroupName
     * @param unit
     * @return
     */
    @GET
    @Path("/update/rest/{rest}/contentattributeid/{contentattributeid}/cdmfieldname/{cdmfieldname}/cdmgroupname/{cdmgroupname}{unit:(/unit/[^/]+?)?}")
    @Produces("application/xml")
    @RESTDoc(methodName = "ContentAttribute_Update", desc = "Update a  new ContentAttribute", req_Params = {"CDMFieldName;String", "CDMGroupName;String"}, opt_Params = {"Unit;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContent getXml_Update(@PathParam("ContentAttributeID") String id, @PathParam("CDMFieldName") String dataFieldName, @PathParam("CDMGroupName") String dataGroupName, @PathParam("Unit") String unit) {
        return isUpdatedContentAttribute(id, dataFieldName, dataGroupName, unit);
    }

    /**
     * ContentAttribute_Update
     * @param id
     * @param dataFieldName
     * @param dataGroupName
     * @param unit
     * @return
     */
    @GET
    @Path("/update/json/{json}/contentattributeid/{contentattributeid}/cdmfieldname/{cdmfieldname}/cdmgroupname/{cdmgroupname}{unit:(/unit/[^/]+?)?}")
    @Produces("application/json")
    public TingcoContent getJson_Update(@PathParam("ContentAttributeID") String id, @PathParam("CDMFieldName") String dataFieldName, @PathParam("CDMGroupName") String dataGroupName, @PathParam("Unit") String unit) {
        return isUpdatedContentAttribute(id, dataFieldName, dataGroupName, unit);
    }

    /**
     * AddUpdateContentAttribute
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addupdatecontentattribute/rest/{rest}")
    @RESTDoc(methodName = "AddUpdateContentAttribute", desc = "Used to Add or Update ContentAttribute", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoContent postXml_addUpdateContentAttribute(String content) {
        return addUpdateContentAttribute(content);
    }

    /**
     * AddUpdateContentAttribute
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addupdatecontentattribute/json/{json}")
    public TingcoContent postJson_addUpdateContentAttribute(String content) {
        return addUpdateContentAttribute(content);
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public ContentAttributeResource getContentAttributeResource() {
        return new ContentAttributeResource();
    }

    /**
     * GetContentAttributebyContentAttributeID
     * @param contentAttributeId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontentattributebycontentattributeid/rest/{rest}/contentattributeid/{contentattributeid}")
    @RESTDoc(methodName = "GetContentAttributebyContentAttributeID", desc = "Get ContentAttribute by ContentAttributeID", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContent getXml_GetContentAttributebyContentAttributeID(@PathParam("contentattributeid") String contentAttributeId) throws DatatypeConfigurationException {
        return getContentAttributebyContentAttributeID(contentAttributeId);
    }

    /**
     * GetContentAttributebyContentAttributeID
     * @param contentAttributeId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getcontentattributebycontentattributeid/json/{json}/contentattributeid/{contentattributeid}")
    public TingcoContent getJson_GetContentAttributebyContentAttributeID(@PathParam("contentattributeid") String contentAttributeId) throws DatatypeConfigurationException {
        return getContentAttributebyContentAttributeID(contentAttributeId);
    }

    /**
     * GetContentAttributebyCountryId
     * @param countryId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getcontentattributesbycountryid/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetContentAttributebyCountryId", desc = "Get ContentAttribute by CountryID", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContent getXml_getContentAttributebyCountryId(@PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getContentAttributesbyCountryId(countryId);
    }

    /**
     * GetContentAttributebyCountryId
     * @param countryId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getcontentattributesbycountryid/json/{json}/countryid/{countryid}")
    public TingcoContent getJson_getContentAttributebyCountryId(@PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getContentAttributesbyCountryId(countryId);
    }

    @GET
    @Produces("application/xml")
    @Path("/deletecontentattribute/rest/{rest}/contentattributeid/{contentattributeid}")
    @RESTDoc(methodName = "DeleteContentAttribute", desc = "DeleteContentAttribute", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContent getXml_DeleteContentAttribute(@PathParam("contentattributeid") String contentAttributeId) throws DatatypeConfigurationException {
        return deleteContentAttribute(contentAttributeId);
    }

    @GET
    @Produces("application/json")
    @Path("/deletecontentattribute/json/{json}/contentattributeid/{contentattributeid}")
    public TingcoContent getJson_DeleteContentAttribute(@PathParam("contentattributeid") String contentAttributeId) throws DatatypeConfigurationException {
        return deleteContentAttribute(contentAttributeId);
    }

    private TingcoContent deleteContentAttribute(String contentAttributeId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        TingcoContent tingcoContent = null;
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTENT)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTENT);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.DELETE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object object = contentDAO.getContentAttributes(contentAttributeId, session);
                    if (object == null) {
                        tingcoContent.getMsgStatus().setResponseCode(-1);
                        tingcoContent.getMsgStatus().setResponseText("No Data Found");
                        return tingcoContent;
                    } else {
                        List<ContentAttributeTranslations> attributeTranslationses = contentDAO.getContentAttributeTranslationsByAttributeId(contentAttributeId, session);
                        if (!attributeTranslationses.isEmpty()) {
                            contentDAO.deleteContentAttributeTranslations(attributeTranslationses, session);
                        }
                        List<ContentCategoryAttributes> contentCategoryAttributeses = contentDAO.getContentCategoryAttributesBycontentAttributeId(contentAttributeId, session);
                        if (!contentCategoryAttributeses.isEmpty()) {
                            contentDAO.deleteContentCategoryAttributes(contentCategoryAttributeses, session);
                        }
                        contentDAO.deleteContentAttribute(contentAttributeId, session);

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

    private TingcoContent getContentAttributebyContentAttributeID(String contentAttributeId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session session = null;
        TingcoContent tingcoContent = null;
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTENT)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTENT);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object object = contentDAO.getContentAttributes(contentAttributeId, session);
                    if (object == null) {
                        tingcoContent.getMsgStatus().setResponseCode(-1);
                        tingcoContent.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoContent;
                    } else {
                        ContentAttributes contentAttributes = (ContentAttributes) object;
                        List<ContentAttributeTranslations> attributeTranslationses = contentDAO.getContentAttributeTranslationsByAttributeId(contentAttributeId, session);
                        CountryDAO countryDAO = new CountryDAO();
                        List<Country> countries = countryDAO.getAllCountries(session);
                        tingcoContent = tingcoContentXML.buildGetContentAttributebyContentAttributeID(tingcoContent, contentAttributes, attributeTranslationses, countries);
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

    private TingcoContent addUpdateContentAttribute(String content) {
        long requestedTime = System.currentTimeMillis();
        TingcoContent tingcoContent = null;
        Session session = null;
        Transaction tx = null;
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                if (getPermission(sessions2.getUserId(), TCMUtil.CONTENT, TCMUtil.ADD)) {
                    se.info24.contentjaxb.TingcoContent contentXML = (se.info24.contentjaxb.TingcoContent) JAXBManager.getInstance().unMarshall(content, se.info24.contentjaxb.TingcoContent.class);
                    session = HibernateUtil.getSessionFactory().openSession();
                    tx = session.beginTransaction();
                    Attribute attribute = contentXML.getContent().getContentItems().getContentItem().get(0).getAttributes().getAttribute().get(0);
                    ContentAttributes contentAttributes = null;
                    Set<ContentAttributeTranslations> catSet = new HashSet<ContentAttributeTranslations>();
                    if (attribute.getContentAttributeId() == null) {
                        String contentAttributeId = UUID.randomUUID().toString();
                        contentAttributes = new ContentAttributes();
                        contentAttributes.setContentAttributeId(contentAttributeId);
                        contentAttributes.setCdmfieldName(attribute.getCDMFieldName());
                        contentAttributes.setCdmdataGroupName(attribute.getCDMDataGroupName());
                        contentAttributes.setUnit(attribute.getUnit());
                        contentAttributes.setUserId(sessions2.getUserId());
                        contentAttributes.setCreatedDate(currentDateTime.getTime());
                        contentAttributes.setUpdatedDate(currentDateTime.getTime());

                        for (ContentAttribute cat : attribute.getContentAttribute()) {
                            ContentAttributeTranslations contentAttributeTrans = new ContentAttributeTranslations();
                            contentAttributeTrans.setId(new ContentAttributeTranslationsId(contentAttributeId, cat.getCountryId()));
                            contentAttributeTrans.setAttributeName(cat.getAttributeName());
                            contentAttributeTrans.setUserId(sessions2.getUserId());
                            contentAttributeTrans.setCreatedDate(currentDateTime.getTime());
                            contentAttributeTrans.setUpdatedDate(currentDateTime.getTime());
                            catSet.add(contentAttributeTrans);
                        }
                        contentAttributes.setContentAttributeTranslationses(catSet);
                        session.saveOrUpdate(contentAttributes);
                    } else {
                        List<ContentAttributeTranslations> contentAttributeTransList = contentDAO.getContentAttributeTranslationsByAttributeId(attribute.getContentAttributeId(), session);
                        if (!contentAttributeTransList.isEmpty()) {
                            for (ContentAttributeTranslations cat : contentAttributeTransList) {
                                session.delete(cat);
                            }
                        }
                        tx.commit();
                        tx = session.beginTransaction();

                        contentAttributes = (ContentAttributes) contentDAO.getContentAttributes(attribute.getContentAttributeId(), session);
                        contentAttributes.setCdmfieldName(attribute.getCDMFieldName());
                        contentAttributes.setCdmdataGroupName(attribute.getCDMDataGroupName());
                        contentAttributes.setUnit(attribute.getUnit());
                        contentAttributes.setUserId(sessions2.getUserId());
                        contentAttributes.setUpdatedDate(currentDateTime.getTime());

                        for (ContentAttribute cat : attribute.getContentAttribute()) {
                            ContentAttributeTranslations contentAttributeTrans = new ContentAttributeTranslations();
                            contentAttributeTrans.setId(new ContentAttributeTranslationsId(attribute.getContentAttributeId(), cat.getCountryId()));
                            contentAttributeTrans.setAttributeName(cat.getAttributeName());
                            contentAttributeTrans.setUserId(sessions2.getUserId());
                            contentAttributeTrans.setCreatedDate(currentDateTime.getTime());
                            contentAttributeTrans.setUpdatedDate(currentDateTime.getTime());
                            catSet.add(contentAttributeTrans);
                        }
                        contentAttributes.setContentAttributeTranslationses(catSet);
                        session.saveOrUpdate(contentAttributes);
                    }
                    tx.commit();
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
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
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

    private TingcoContent getContentAttributesbyCountryId(int countryId) {
        boolean hasPermission = false;
        Session session = null;
        TingcoContent tingcoContent = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.CONTENT, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ContentAttributeTranslations> catList = contentDAO.getContentAttributeTranslationsByCountryId(countryId, session);
                    if(!catList.isEmpty()) {
                        tingcoContent = tingcoContentXML.buildContentAttributesByCountryId(tingcoContent, catList);
                    } else {
                        tingcoContent.getMsgStatus().setResponseCode(-1);
                        tingcoContent.getMsgStatus().setResponseText("Data Not Found");
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
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContent.getMsgStatus().setResponseCode(-1);
            tingcoContent.getMsgStatus().setResponseText("Error Occured");
            return tingcoContent;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContent;
    }

    private TingcoContent isAddedContentAttribute(String dataFieldName, String dataGroupName, String unit) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        TingcoContent tingcoContent = null;
        Session session = null;
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                UserSessions2 user_sess = (UserSessions2) request.getAttribute("USERSESSION");
                ContentAttributes ca = new ContentAttributes();
                ca.setUserId(user_sess.getUserId());
                ca.setContentAttributeId(UUID.randomUUID().toString());
                ca.setCdmfieldName(dataFieldName);
                ca.setCdmdataGroupName(dataGroupName);
                if (!unit.equals("")) {
                    unit = unit.split("/")[2];
                    ca.setUnit(unit);
                }
                GregorianCalendar gc = new GregorianCalendar();
                ca.setCreatedDate(gc.getTime());
                ca.setUpdatedDate(gc.getTime());

                boolean added = contentDAO.addNewContentAttribute(ca, session);
                if (!added) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("Error");
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

    private TingcoContent isDeletedContentAttribute(String id) {
        long requestedTime = System.currentTimeMillis();
        TingcoContent tingcoContent = null;
        Session session = null;
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (id == null || id.equals("")) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("DeviceDataItemID should not be empty");
                    return tingcoContent;
                }

                session = HibernateUtil.getSessionFactory().openSession();
                boolean deleted = contentDAO.deleteContentAttribute(id, session);
                if (!deleted) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("DeviceDataItemID Not Found");
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

    private TingcoContent isUpdatedContentAttribute(String id, String dataFieldName, String dataGroupName, String unit) {
        long requestedTime = System.currentTimeMillis();
        TingcoContent tingcoContent = null;
        Session session = null;
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {

                if (id == null || id.equals("")) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("ContentAttributeID should not be empty");
                    return tingcoContent;
                }
                UserSessions2 user_sess = (UserSessions2) request.getAttribute("USERSESSION");
                session = HibernateUtil.getSessionFactory().openSession();
                boolean added = contentDAO.updateContentAttribute(id, dataFieldName, dataGroupName, user_sess.getUserId(), unit, session);
                if (!added) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("Error");
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

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }

    private TingcoContent getAllContentAttributes() {
        boolean hasPermission = false;
        Session session = null;
        TingcoContent tingcoContent = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.CONTENT, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ContentAttributes> contentAttributes = contentDAO.getAllContentAttributes(2000, session);
                    if (!contentAttributes.isEmpty()) {

                        List<String> contentAttributeID = new ArrayList<String>();
                        for (ContentAttributes contentAttributes1 : contentAttributes) {
                            contentAttributeID.add(contentAttributes1.getContentAttributeId());
                        }
                       contentAttributes =  contentDAO.getContentAttributesByIds(contentAttributeID, session);
//                        Collections.sort(contentAttributes, new Comparator<ContentAttributes>() {
//
//                            public int compare(ContentAttributes o1, ContentAttributes o2) {
//                                return o1.getCdmfieldName().compareToIgnoreCase(o2.getCdmfieldName());
//                            }
//                        });
                        tingcoContent = tingcoContentXML.buildAllContentAttributes(tingcoContent, contentAttributes);
                    } else {
                        tingcoContent.getMsgStatus().setResponseCode(-1);
                        tingcoContent.getMsgStatus().setResponseText("Data Not Found");
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
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoContent.getMsgStatus().setResponseCode(-1);
            tingcoContent.getMsgStatus().setResponseText("Error Occured");
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
}
