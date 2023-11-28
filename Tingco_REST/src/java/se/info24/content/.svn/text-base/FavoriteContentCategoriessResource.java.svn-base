/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.content;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import org.hibernate.Session;
import se.info24.contentjaxb.ContentItem;
import se.info24.contentjaxb.TingcoContent;
import se.info24.jaxbUtil.TingcoContentXML;
import se.info24.pojo.ContentCategories;
import se.info24.pojo.UserFavoriteContentCategories;
import se.info24.pojo.UserSessions2;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/favoritecontentcategories")
public class FavoriteContentCategoriessResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoContentXML tingcoContentXML = new TingcoContentXML();
    ContentDAO contentDAO = new ContentDAO();
//    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of FavoriteContentCategoriessResource */
    public FavoriteContentCategoriessResource() {
    }

    /**
     * FavoriteContentCategories_Delete
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/")
    @RESTDoc(methodName = "FavoriteContentCategories_Delete", desc = "Deletes UserFavoriteContentCategories ", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContent getXml_Delete() {
        return deleteFavoriteContentCategoriesByUserId();
    }

    /**
     * FavoriteContentCategories_Delete
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/json/{json}/")
    public TingcoContent getJSON_Delete() {
        return deleteFavoriteContentCategoriesByUserId();
    }

    /**
     * FavouriteContents_Add
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/add/rest/{rest}")
    @RESTDoc(methodName = "FavouriteContents_Add", desc = "Adds user favorite contents ", req_Params = {"ContentCategoryID;UUID"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoContent postXml_Add(String content) {
        JAXBManager jaxbManage = JAXBManager.getInstance();
        TingcoContent tc = (TingcoContent) jaxbManage.unMarshall(content, TingcoContent.class);
        return addfavoriteContents(tc);
    }

    /**
     * FavouriteContents_Add
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/add/json/{json}")
    @RESTDoc(methodName = "FavouriteContents_Add", desc = "Adds user favorite contents ", req_Params = {"ContentCategoryID;UUID"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoContent postJson_Add(String content) {
        JAXBManager jaxbManage = JAXBManager.getInstance();
        TingcoContent tc = (TingcoContent) jaxbManage.unMarshall(content, TingcoContent.class);
        return addfavoriteContents(tc);
    }

    /**
     * FilteredContentCategories
     * @param countryID
     * @return
     */
    @GET
    @Path("/filteredcontentcategories/rest/{rest}/countryid/{countryid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "FilteredContentCategories", desc = "Return's favorite contentcategories", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoContent getXml(@PathParam("countryid") int countryID) {
        return filteredContentCategories(countryID);
    }

    /**
     * FilteredContentCategories
     * @param countryID
     * @return
     */
    @GET
    @Path("/filteredcontentcategories/json/{json}/countryid/{countryid}")
    @Produces("application/json")
    public TingcoContent getJSON(@PathParam("countryid") int countryID) {
        return filteredContentCategories(countryID);
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public FavoriteContentCategoriesResource getFavoriteContentCategoriesResource() {
        return new FavoriteContentCategoriesResource();
    }

    private TingcoContent addfavoriteContents(TingcoContent tc) {
        long requestedTime = System.currentTimeMillis();
        TingcoContent tingcoContent = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 user_sess = (UserSessions2) request.getAttribute("USERSESSION");
                List<ContentItem> ci = tc.getContent().getContentItems().getContentItem();
                boolean added = contentDAO.addFavoriteContent(user_sess.getUserId(), ci, session);
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
            tingcoContent.getMsgStatus().setResponseText("Error");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContent;
    }

    private TingcoContent deleteFavoriteContentCategoriesByUserId() {
        long requestedTime = System.currentTimeMillis();
        TingcoContent tingcoContent = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tingcoContent = tingcoContentXML.buildTingcoContentTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 user_sess = (UserSessions2) request.getAttribute("USERSESSION");
                boolean deleted = contentDAO.deleteFavoriteContentCategoriesByUserId(user_sess.getUserId(), session);
                if (deleted) {
                    return tingcoContent;
                } else {
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
            tingcoContent.getMsgStatus().setResponseText("Error");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoContent;
    }

    private TingcoContent filteredContentCategories(int countryID) {
        long requestedTime = System.currentTimeMillis();
        TingcoContent tc = new TingcoContent();
        Session session = null;
        try {
            TingcoContentXML conXML = new TingcoContentXML();
            tc = conXML.buildTingcoContentTemplate();
            session = HibernateUtil.getSessionFactory().openSession();
            if (request.getAttribute("USERSESSION") != null) {
                HashMap<String, ContentCategories> hm = new HashMap<String, ContentCategories>();
                UserSessions2 sess = (UserSessions2) request.getAttribute("USERSESSION");
                List<UserFavoriteContentCategories> ufcg = contentDAO.getUserFavouriteContentCategories(sess.getUserId(), session);
                List<ContentCategories> categories = contentDAO.getAllContentCategories(session);
                for (ContentCategories cc : categories) {
                    hm.put(cc.getContentCategoryId(), cc);
                }
                conXML.getfavoriteContentCategories(tc, ufcg, hm, countryID);

            } else {
                tc.getMsgStatus().setResponseCode(-3);
                tc.getMsgStatus().setResponseText("Session Expired");
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tc.getMsgStatus().setResponseCode(-1);
            tc.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tc;
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}
