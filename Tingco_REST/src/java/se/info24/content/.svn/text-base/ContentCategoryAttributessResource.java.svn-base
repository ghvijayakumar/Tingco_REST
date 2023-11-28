/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.content;

import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import se.info24.contentjaxb.TingcoContent;
import se.info24.jaxbUtil.TingcoContentXML;
import se.info24.pojo.ContentAttributes;
import se.info24.pojo.ContentCategories;
import se.info24.pojo.ContentCategoryAttributes;
import se.info24.pojo.ContentCategoryAttributesId;
import se.info24.pojo.UserSessions2;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/contentcategoryattributes")
public class ContentCategoryAttributessResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoContentXML tingcoContentXML = new TingcoContentXML();
    ContentDAO contentDAO = new ContentDAO();
//    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of ContentCategoryAttributessResource */
    public ContentCategoryAttributessResource() {
    }

    /**
     * ContentCategoryAttribute_Add
     * @param contentCategoryID
     * @param contentAttributeID
     * @param attributeDefaultValue
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/contentcategoryid/{contentcategoryid}/contentattributeid/{contentattributeid}{attributedefaultvalue:(/attributedefaultvalue/[^/]+?)?}")
    @RESTDoc(methodName = "ContentCategoryAttribute_Add", desc = "Used to create a new Content Category Attribute", req_Params = {"ContentCategoryID;UUID", "ContentAttributeID;UUID"}, opt_Params = {"AttributeDefaultValue;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoContent getXml_Add(@PathParam("contentcategoryid") String contentCategoryID, @PathParam("contentattributeid") String contentAttributeID, @PathParam("attributedefaultvalue") String attributeDefaultValue) throws DatatypeConfigurationException {
        return createContentCategoryAttribute(contentCategoryID, contentAttributeID, attributeDefaultValue);
    }

    /**
     * ContentCategoryAttribute_Add
     * @param contentCategoryID
     * @param contentAttributeID
     * @param attributeDefaultValue
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/add/rest/{rest}/contentcategoryid/{contentcategoryid}/contentattributeid/{contentattributeid}{attributedefaultvalue:(/attributedefaultvalue/[^/]+?)?}")
    public TingcoContent postXml_Add(@PathParam("contentcategoryid") String contentCategoryID, @PathParam("contentattributeid") String contentAttributeID, @PathParam("attributedefaultvalue") String attributeDefaultValue) {
        return null; // return createContentCategoryAttribute(contentCategoryID, contentAttributeID, attributeDefaultValue); // Need to take the XML file consits more entries to save.
    }

    /**
     * ContentCategoryAttribute_Add
     * @param contentCategoryID
     * @param contentAttributeID
     * @param attributeDefaultValue
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/contentcategoryid/{contentcategoryid}/contentattributeid/{contentattributeid}{attributedefaultvalue:(/attributedefaultvalue/[^/]+?)?}")
    public TingcoContent getJson_Add(@PathParam("contentcategoryid") String contentCategoryID, @PathParam("contentattributeid") String contentAttributeID, @PathParam("attributedefaultvalue") String attributeDefaultValue) throws DatatypeConfigurationException {
        return createContentCategoryAttribute(contentCategoryID, contentAttributeID, attributeDefaultValue);
    }

    /**
     * ContentCategoryAttribute_Add
     * @param contentCategoryID
     * @param contentAttributeID
     * @param attributeDefaultValue
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/add/json/{json}/contentcategoryid/{contentcategoryid}/contentattributeid/{contentattributeid}{attributedefaultvalue:(/attributedefaultvalue/[^/]+?)?}")
    public TingcoContent postJson_Add(@PathParam("contentcategoryid") String contentCategoryID, @PathParam("contentattributeid") String contentAttributeID, @PathParam("attributedefaultvalue") String attributeDefaultValue) {
        return null; //return createContentCategoryAttribute(contentCategoryID, contentAttributeID, attributeDefaultValue); // Need to take the JSON file consists more entries to save.
    }

    /**
     * ContentCategoryAttribute_Delete
     * @param contentCategoryID
     * @param contentAttributeID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/contentcategoryid/{contentcategoryid}/contentattributeid/{contentattributeid}")
    @RESTDoc(methodName = "DeleteContentCategoryAttribute", desc = "Used to delete Content Category Attribute", req_Params = {"ContentCategoryID;UUID", "ContentAttributeID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoContent getXml_Delete(@PathParam("contentcategoryid") String contentCategoryID, @PathParam("contentattributeid") String contentAttributeID) {
        return deleteContentCategoryAttribute(contentCategoryID, contentAttributeID);
    }

    /**
     * ContentCategoryAttribute_Delete
     * @param contentCategoryID
     * @param contentAttributeID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/contentcategoryid/{contentcategoryid}/contentattributeid/{contentattributeid}")
    public TingcoContent postXml_Delete(@PathParam("contentcategoryid") String contentCategoryID, @PathParam("contentattributeid") String contentAttributeID) {
        return null; //return deleteContentCategoryAttribute(contentCategoryID, contentAttributeID); // Need to take the XML file consits more entries to delete
    }

    /**
     * ContentCategoryAttribute_Delete
     * @param contentCategoryID
     * @param contentAttributeID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/contentcategoryid/{contentcategoryid}/contentattributeid/{contentattributeid}")
    public TingcoContent getJson_Delete(@PathParam("contentcategoryid") String contentCategoryID, @PathParam("contentattributeid") String contentAttributeID) {
        return deleteContentCategoryAttribute(contentCategoryID, contentAttributeID);
    }

    /**
     * ContentCategoryAttribute_Delete
     * @param contentCategoryID
     * @param contentAttributeID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/delete/json/{json}/contentcategoryid/{contentcategoryid}/contentattributeid/{contentattributeid}")
    public TingcoContent postJson_Delete(@PathParam("contentcategoryid") String contentCategoryID, @PathParam("contentattributeid") String contentAttributeID) {
        return null; //return deleteContentCategoryAttribute(contentCategoryID, contentAttributeID);  // Need to take the JSON file consists more entries to delete.
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public ContentCategoryAttributesResource getContentCategoryAttributesResource() {
        return new ContentCategoryAttributesResource();
    }

    private TingcoContent createContentCategoryAttribute(String contentCategoryId, String contentAttributeId, String attributeDefaultValue) throws DatatypeConfigurationException {
        long requestedTime = System.currentTimeMillis();
        TingcoContent tingcoContent = null;
        Session session = null;
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (contentCategoryId == null) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("ContentCategoryID is should not be empty");
                    return tingcoContent;
                } else if (contentAttributeId == null) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("ContentAttributeID is should not be empty");
                    return tingcoContent;
                }
                if (attributeDefaultValue.equals("")) {
                    attributeDefaultValue = null;
                } else {
                    attributeDefaultValue = attributeDefaultValue.split("/")[2];
                }
                UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                GregorianCalendar gc = new GregorianCalendar();
                session = HibernateUtil.getSessionFactory().openSession();

                Object obj = contentDAO.getContentCategoryAttributes(contentCategoryId, contentAttributeId, session);
                if (obj == null) {
                    Object contentAttributes = contentDAO.getContentAttributes(contentAttributeId, session);
                    Object contentCategories = contentDAO.getContentCategoryById(contentCategoryId, session);
                    if (contentAttributes != null && contentCategories != null) {
                        ContentCategoryAttributes att = new ContentCategoryAttributes();
                        att.setId(new ContentCategoryAttributesId(contentCategoryId, contentAttributeId));
                        att.setContentAttributes(new ContentAttributes(contentAttributeId));
                        att.setContentCategories(new ContentCategories(contentCategoryId));
                        att.setUserId(userSessions2.getUserId());
                        att.setCreatedDate(gc.getTime());
                        att.setUpdatedDate(gc.getTime());
                        if (!contentDAO.saveContentCategoryAttributes(att, session)) {
                            tingcoContent.getMsgStatus().setResponseCode(-1);
                            tingcoContent.getMsgStatus().setResponseText("Error Occured While Saving the ContentCategoryAttribute");
                            return tingcoContent;
                        }
                    } else {
                        tingcoContent.getMsgStatus().setResponseCode(-1);
                        tingcoContent.getMsgStatus().setResponseText("data not found");
                        return tingcoContent;
                    }
                } else {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("Data already exists");
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
            tingcoContent.getMsgStatus().setResponseText("Error Occured While Saving ContentCategoryAttributes");
            return tingcoContent;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContent;
    }

    private TingcoContent deleteContentCategoryAttribute(String contentCategoryId, String contentAttributeId) {
        long requestedTime = System.currentTimeMillis();
        TingcoContent tingcoContent = null;
        Session session = null;
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (contentCategoryId == null) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("ContentCategoryID is should not be empty");
                    return tingcoContent;
                } else if (contentAttributeId == null) {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("ContentAttributeID is should not be empty");
                    return tingcoContent;
                }
                session = HibernateUtil.getSessionFactory().openSession();
                Object att = contentDAO.getContentCategoryAttributes(contentCategoryId, contentAttributeId, session);
                if (att != null) {
                    if (!contentDAO.deleteContentCategoryAttributes(att, session)) {
                        tingcoContent.getMsgStatus().setResponseCode(-1);
                        tingcoContent.getMsgStatus().setResponseText("Error Occured while Deleting ContentCategoryAttributes");
                        return tingcoContent;
                    }
                } else {
                    tingcoContent.getMsgStatus().setResponseCode(-1);
                    tingcoContent.getMsgStatus().setResponseText("ContentCategoryID Not Found");
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
            tingcoContent.getMsgStatus().setResponseText("Error Occured While Saving ContentCategoryAttributes");
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
