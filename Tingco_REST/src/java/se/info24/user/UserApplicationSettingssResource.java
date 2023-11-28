/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.info24.user;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.hibernate.Session;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */

@Path("/userapplicationsettings")
public class UserApplicationSettingssResource {
    @Context
    private UriInfo context;
//    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /** Creates a new instance of UserApplicationSettingssResource */
    public UserApplicationSettingssResource() {
    }

    /**
     * AddUserApplicationSettings
     * @param request
     * @param userID
     * @param appID
     * @param key
     * @param value
     * @return
     */
    @GET
    @Path("/add/rest/{rest}/userid/{userid}/applicationid/{applicationid}/applicationsettingname/{applicationsettingname}/applicationsettingvalue/{applicationsettingvalue}")
    @Produces("application/xml")
    @RESTDoc(methodName = "UserApplicationSettings_Add", desc = "Adds a new userapplicationsetting", req_Params = {"UserID;UUID", "ApplicationID;UUID", "ApplicationSettingName;String", "ApplicationSettingValue;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_Add(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("applicationid") String appID,
            @PathParam("applicationsettingname") String key, @PathParam("applicationsettingvalue") String value) {
        return isAddedUserApplicationSetting(userID, appID, key, value, request);
    }

    /**
     * AddUserApplicationSettings
     * @param request
     * @param userID
     * @param appID
     * @param key
     * @param value
     * @return
     */
    @GET
    @Path("/add/json/{json}/userid/{userid}/applicationid/{applicationid}/applicationsettingname/{applicationsettingname}/applicationsettingvalue/{applicationsettingvalue}")
    @Produces("application/json")
    public TingcoUsers getJson_Add(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("applicationid") String appID,
            @PathParam("applicationsettingname") String key, @PathParam("applicationsettingvalue") String value) {
        return isAddedUserApplicationSetting(userID, appID, key, value, request);
    }

    /**
     * UpdateUserApplicationSettings
     * @param request
     * @param userID
     * @param appID
     * @param key
     * @param value
     * @return
     */
    @GET
    @Path("/update/rest/{rest}/userid/{userid}/applicationid/{applicationid}/applicationsettingname/{applicationsettingname}/applicationsettingvalue/{applicationsettingvalue}")
    @Produces("application/xml")
    @RESTDoc(methodName = "UserApplicationSettings_Update", desc = "Updates user application settings", req_Params = {"UserID;UUID", "ApplicationID;UUID", "ApplicationSettingName;String", "ApplicationSettingValue;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_Update(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("applicationid") String appID,
            @PathParam("applicationsettingname") String key, @PathParam("applicationsettingvalue") String value) {
        return isUpdatedUserAppSetting(userID, appID, key, value, request);
    }

    /**
     * UpdateUserApplicationSettings
     * @param request
     * @param userID
     * @param appID
     * @param key
     * @param value
     * @return
     */
    @GET
    @Path("/update/json/{json}/userid/{userid}/applicationid/{applicationid}/applicationsettingname/{applicationsettingname}/applicationsettingvalue/{applicationsettingvalue}")
    @Produces("application/json")
    public TingcoUsers getJson_Update(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("applicationid") String appID,
            @PathParam("applicationsettingname") String key, @PathParam("applicationsettingvalue") String value) {
        return isUpdatedUserAppSetting(userID, appID, key, value, request);
    }

    /**
     * GetUserApplicationSettings
     * @param request
     * @param userID
     * @param appID
     * @return
     */
    @GET
    @Path("/get/rest/{rest}/userid/{userid}/applicationid/{applicationid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "Get_UserApplicationSettings", desc = "Reteieves user application settings", req_Params = {"UserID;UUID", "ApplicationID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("applicationid") String appID) {
        return userAppSettings(userID, appID, request);
    }

    /**
     * GetUserApplicationSettings
     * @param request
     * @param userID
     * @param appID
     * @return
     */
    @GET
    @Path("/get/json/{json}/userid/{userid}/applicationid/{applicationid}")
    @Produces("application/json")
    public TingcoUsers getJson(@Context HttpServletRequest request, @PathParam("userid") String userID, @PathParam("applicationid") String appID) {
        return userAppSettings(userID, appID, request);
    }

    /**
     * POST method for creating an instance of UserApplicationSettingssResource
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

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public UserApplicationSettingsResource getUserApplicationSettingsResource() {
        return new UserApplicationSettingsResource();
    }

    private TingcoUsers isAddedUserApplicationSetting(String userID, String appID, String key, String value, HttpServletRequest request) {
        long requestedTime = System.currentTimeMillis();
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserManager manager = new UserManager();
        TingcoUsers user = null;
        boolean hasPermission = false;

        try {
            user = manager.buildUserTemplate();
//            Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//                if (ht.containsKey(TCMUtil.USERS)) {
//                    ArrayList<String> operations = ht.get(TCMUtil.USERS);
//                    for (int i = 0; i < operations.size(); i++) {
//                        if (operations.get(i).equalsIgnoreCase(TCMUtil.ADD)) {
                            hasPermission = true;
//                        }
//                    }
//                }

            if(hasPermission){
                manager.addUserAppSetting(userID, appID, key, value, session);
            }else{
                user.getMsgStatus().setResponseCode(-10);
                user.getMsgStatus().setResponseText("User Permission is not given");
                return user;
            }

        }  catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            user.getMsgStatus().setResponseCode(-1);
            user.getMsgStatus().setResponseText("Error");
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return user;
    }

    private TingcoUsers isUpdatedUserAppSetting(String userID, String appID, String key, String value, HttpServletRequest request) {
        long requestedTime = System.currentTimeMillis();
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserManager manager = new UserManager();
        TingcoUsers user = null;
        boolean hasPermission = false;

        try {
            user = manager.buildUserTemplate();
//            Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//                if (ht.containsKey(TCMUtil.USERS)) {
//                    ArrayList<String> operations = ht.get(TCMUtil.USERS);
//                    for (int i = 0; i < operations.size(); i++) {
//                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
//                        }
//                    }
//                }
            if(hasPermission){
                manager.updateUserAppSetting(userID, appID, key, value, session);
            }else{
                user.getMsgStatus().setResponseCode(-10);
                user.getMsgStatus().setResponseText("User Permission is not given");
                return user;
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            user.getMsgStatus().setResponseCode(-1);
            user.getMsgStatus().setResponseText("Error");
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return user;
    }

    private TingcoUsers userAppSettings(String userID, String appID, HttpServletRequest request) {
        boolean hasPermission = false;
        long requestedTime = System.currentTimeMillis();
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserManager manager = new UserManager();
        TCMUtil tcm = new TCMUtil();
        TingcoUsers user = null;
        try {
            user = manager.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
//                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//                if (ht.containsKey(TCMUtil.USERS)) {
//                    ArrayList<String> operations = ht.get(TCMUtil.USERS);
//                    for (int i = 0; i < operations.size(); i++) {
//                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
//                        }
//                    }
//                }
                if(hasPermission){
                    user = manager.getUserAppSettings(userID, appID, user, session);
                }else{
                    user.getMsgStatus().setResponseCode(-10);
                    user.getMsgStatus().setResponseText("User Permission is not given");
                    return user;
                }
            } else {
                user = tcm.sessionExpired();
            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            user.getMsgStatus().setResponseCode(-1);
            user.getMsgStatus().setResponseText("Invalid UserID");
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return user;
    }
    public void delayLog(long requestedTime){
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took "+(System.currentTimeMillis()- requestedTime)+"ms]", "Info");
	}
}
