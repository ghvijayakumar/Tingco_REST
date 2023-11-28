/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.passwords;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import org.hibernate.Session;
import se.info24.pojo.ForgetPasswords;
import se.info24.pojo.UserSessions2;
import se.info24.user.UserManager;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.usersjaxb.User;
import se.info24.usersjaxb.Users;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/forgotpassword")
public class ForgotPasswordsResource {

    @Context
    private UriInfo context;
    SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /** Creates a new instance of ForgotPasswordsResource */
    public ForgotPasswordsResource() {
    }

    /**
     * ForgotPassword_Add
     * @param request
     * @param appID
     * @param domainID
     * @param emailID
     * @return
     */
    @GET
    @Path("/add/rest/{rest}/applicationid/{applicationid}/domainid/{domainid}/emailid/{emailid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "ForgotPassword_Add", desc = "Used to delete a user", req_Params = {"ApplicationID;UUID", "DomainID;UUID", "EmailID;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_Add(@Context HttpServletRequest request, @PathParam("applicationid") String appID, @PathParam("domainid") String domainID,
            @PathParam("emailid") String emailID) {
        return insertRecord(appID, domainID, emailID, request);
    }

    /**
     * ForgotPassword_Add
     * @param request
     * @param appID
     * @param domainID
     * @param emailID
     * @return
     */
    @GET
    @Path("/add/json/{json}/applicationid/{applicationid}/domainid/{domainid}/emailid/{emailid}")
    @Produces("application/json")
    public TingcoUsers getJson_Add(@Context HttpServletRequest request, @PathParam("applicationid") String appID, @PathParam("domainid") String domainID,
            @PathParam("emailid") String emailID) {
        return insertRecord(appID, domainID, emailID, request);
    }

    /**
     * IsValidForgotPasswordLink
     * @param request
     * @param id
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/isvalidlink/rest/{rest}/id/{id}")
    @RESTDoc(methodName = "IsValidForgotPasswordLink", desc = "Check's Whether the ForgotPassword link is Valid or Not", req_Params = {"ID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_IsValidLink(@Context HttpServletRequest request, @PathParam("id") String id) {
        return validForgotPassword(id, request);
    }

    /**
     * IsValidForgotPasswordLink
     * @param request
     * @param id
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/isvalidlink/json/{json}/id/{id}")
    public TingcoUsers getJson_IsValidLink(@Context HttpServletRequest request, @PathParam("id") String id) {
        return validForgotPassword(id, request);
    }

    /**
     * POST method for creating an instance of ForgotPasswordsResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    public Response postXml(String content) {
        //TODO
        return Response.created(context.getAbsolutePath()).build();
    }

    private TingcoUsers insertRecord(String appID, String domainID, String emailID, HttpServletRequest request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserManager manager = new UserManager();
        GregorianCalendar gc = new GregorianCalendar();
        TingcoUsers user = null;
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getHeader("AuthenticationToken") != null) {
                UserSessions2 user_sess = manager.getUserSession(session, request);
                if (user_sess.getActiveToDate().after(gc.getTime())) {
                    user = manager.buildUserTemplate();
                    boolean success = manager.insertForgotPassword(appID, domainID, emailID, user, session, request);
                    if (success) {
                        user.getMsgStatus().setResponseCode(0);
                    } else {
                        user.getMsgStatus().setResponseCode(-1);
                        user.getMsgStatus().setResponseText("Error");
                    }
                }
            } else {
                user = manager.buildUserTemplate();
                boolean success = manager.insertForgotPassword(appID, domainID, emailID, user, session, request);
                if (success) {
                    user.getMsgStatus().setResponseCode(0);
                } else {
                    user.getMsgStatus().setResponseCode(-1);
                    user.getMsgStatus().setResponseText("Error");
                }
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            user.getMsgStatus().setResponseCode(-1);
            user.getMsgStatus().setResponseText("Error");
        } finally {
            delayLog(requestedTime);
            session.close();
        }
        return user;
    }

    private TingcoUsers validForgotPassword(String id, HttpServletRequest request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserManager manager = new UserManager();
        String token = request.getHeader("AuthenticationToken");
        TingcoUsers user = null;
        long requestedTime = System.currentTimeMillis();
        try {
            ForgetPasswords fp = manager.isValidForgotPassword(id, token, session);
            user = manager.buildUserTemplate();
            if (fp != null) {
                user.getMsgStatus().setResponseCode(0);
                user.getMsgStatus().setResponseText("OK");
                Users users = new Users();
                User userJ = new User();
                userJ.setUserID(fp.getId().getUserId());
                users.getUser().add(userJ);
                user.setUsers(users);
            } else {
                user.getMsgStatus().setResponseCode(-1);
                user.getMsgStatus().setResponseText("Invalid Link");
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            delayLog(requestedTime);
            session.close();
        }
        return user;
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public ForgotPasswordResource getForgotPasswordResource() {
        return new ForgotPasswordResource();
    }
     public void delayLog(long requestedTime){
         TCMUtil.loger(getClass().getName(), " [tingco API] [Request took "+(System.currentTimeMillis()- requestedTime)+"ms]", "Info");
	}
}
