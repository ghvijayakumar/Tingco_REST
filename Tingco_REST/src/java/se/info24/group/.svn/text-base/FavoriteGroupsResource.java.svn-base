/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.group;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.hibernate.Session;
import se.info24.groupsjaxb.Group;
import se.info24.groupsjaxb.TingcoGroups;
import se.info24.jaxbUtil.TingcoGroupXML;
import se.info24.pojo.UserFavoriteGroups;
import se.info24.pojo.UserSessions2;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Ravikant
 */
@Path("/favoritegroups")
public class FavoriteGroupsResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of FavoriteGroupsResource */
    public FavoriteGroupsResource() {
    }

    /**
     * FavoriteGroups_Delete
     * @return
     */
    @GET
    @Path("/delete/rest/{rest}")
    @Produces("application/xml")
    @RESTDoc(methodName = "FavoriteGroups_Delete", desc = "Delete's user favorite groups", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml_Delete() {
        return isDeletedFavoriteGroups();
    }

    /**
     * FavoriteGroups_Delete
     * @return
     */
    @GET
    @Path("/delete/json/{json}")
    @Produces("application/json")
    public TingcoGroups getJSON_Delete() {
        return isDeletedFavoriteGroups();
    }

    /**
     * FilteredGroups
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/filteredgroups/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "FilteredGroups", desc = "Return's favorite Groups", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoGroups getXml(@PathParam("countryid") int countryID) {
        return filteredGroups(countryID);
    }

    /**
     * FilteredGroups
     * @param countryID
     * @return
     */
    @GET
    @Path("/filteredgroups/json/{json}/countryid/{countryid}")
    @Produces("application/json")
    public TingcoGroups getJSON(@PathParam("countryid") int countryID) {
        return filteredGroups(countryID);
    }

    /**
     * AddFavoriteGroups
     * @param content
     * @return
     */
    @POST
    @Path("/add/rest/{rest}")
    @Consumes("application/xml")
    @Produces("application/xml")
    @RESTDoc(methodName = "AddFavoriteGroups", desc = "Add's user favorite Groups", req_Params = {"GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoGroups postXml_Add(String content) {
        return isAddedfavoriteGroups(content);
    }

    /**
     * AddFavoriteGroups
     * @param content
     * @return
     */
    @POST
    @Path("/add/json/{json}")
    @Consumes("application/xml")
    @Produces("application/json")
    public TingcoGroups postJson_Add(String content) {
        return isAddedfavoriteGroups(content);
    }

    private TingcoGroups isAddedfavoriteGroups(String content) {
        long requestedTime = System.currentTimeMillis();
        TingcoGroupXML groupXML = new TingcoGroupXML();
        GroupDAO dao = new GroupDAO();
        Session session = null;
        TingcoGroups group = null;
        try {
            group = groupXML.buildTingcoGroupTemplate();
            TingcoGroups input = (TingcoGroups) JAXBManager.getInstance().unMarshall(content, TingcoGroups.class);
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                UserSessions2 sess = (UserSessions2) request.getAttribute("USERSESSION");
                List<Group> list = input.getGroups().getGroup();
                boolean added = dao.addfavoriteGroups(sess.getUserId(), list, session);
                if (added) {
                    return group;
                } else {
                    group.getMsgStatus().setResponseCode(-1);
                    group.getMsgStatus().setResponseText("Error");
                }
            } else {
                group.getMsgStatus().setResponseCode(-3);
                group.getMsgStatus().setResponseText("Session Expired");
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            group.getMsgStatus().setResponseCode(-1);
            group.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return group;
    }

    private TingcoGroups isDeletedFavoriteGroups() {
        long requestedTime = System.currentTimeMillis();
        TingcoGroups group = null;
        Session session = null;
        try {
            GroupDAO dao = new GroupDAO();
            group = dao.buildGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 userSess = (UserSessions2) request.getAttribute("USERSESSION");
                session = HibernateUtil.getSessionFactory().openSession();
                boolean deleted = dao.deleteuserfavoriteGroups(userSess.getUserId(), session);
                if (deleted) {
                    return group;
                } else {
                    group.getMsgStatus().setResponseCode(-1);
                    group.getMsgStatus().setResponseText("Error");
                }

            } else {
                group.getMsgStatus().setResponseCode(-3);
                group.getMsgStatus().setResponseText("Session Expired");
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            group.getMsgStatus().setResponseCode(-1);
            group.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return group;
    }

    private TingcoGroups filteredGroups(int countryID) {
        long requestedTime = System.currentTimeMillis();
        TingcoGroups group = null;
        TingcoGroupXML groupXML = new TingcoGroupXML();
        Session session = null;
        try {
            GroupDAO dao = new GroupDAO();
            group = dao.buildGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                UserSessions2 sess = (UserSessions2) request.getAttribute("USERSESSION");
                List<UserFavoriteGroups> favGroups = dao.getUserFavouriteGroups(sess.getUserId(), session);
                group = groupXML.buildFavoriteGroups(group, favGroups, countryID);

            } else {
                group.getMsgStatus().setResponseCode(-3);
                group.getMsgStatus().setResponseText("Session Expired");
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            group.getMsgStatus().setResponseCode(-1);
            group.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return group;
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}
