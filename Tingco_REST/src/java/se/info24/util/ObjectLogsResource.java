/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.util;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.hibernate.Session;
import se.info24.ismOperationsPojo.ObjectLog;
import se.info24.jaxbUtil.TingcoUtilsXML;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.restUtil.RestUtilDAO;
import se.info24.user.User_LoginsResource;
import se.info24.utiljaxb.ObjectLogs;
import se.info24.utiljaxb.TingcoUtils;

/**
 * REST Web Service
 *
 * @author Vijayakumar
 */
@Path("/objectlogs")
public class ObjectLogsResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoUtilsXML tingcoUtilsXML = new TingcoUtilsXML();
    GregorianCalendar currentDateTime = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

    /** Creates a new instance of ObjectLogsResource */
    public ObjectLogsResource() {
    }

    /**
     * GetObjectLogDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/getobjectlogdetails/rest/{rest}")
    @RESTDoc(methodName = "GetObjectLogDetails", desc = "Returns Object Log", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUtils postXml_getObjectLogDetails(String content) {
        return getobjectLogDetails(content);
    }

    /**
     * GetObjectLogDetails
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/getobjectlogdetails/json/{json}")
    public TingcoUtils postJson_getObjectLogDetails(String content) {
        return getobjectLogDetails(content);
    }

    /**
     * GetObjectLogMessage
     * @param objectId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getobjectlogmessagebyobjectid/rest/{rest}/objectid/{objectid}")
    @RESTDoc(methodName = "GetObjectLogMessage", desc = "Used to Get ObjectLog Message", req_Params = {"ObjectID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUtils getXml_getObjectLogMessageByObjectId(@PathParam("objectid") String objectId) {
        return getObjectLogMessageByObjectId(objectId);
    }

    /**
     * GetObjectLogMessage
     * @param objectId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getobjectsettingtemplates/json/{json}/deviceid/{deviceid}")
    public TingcoUtils getJson_getObjectLogMessageByObjectId(@PathParam("objectid") String objectId) {
        return getObjectLogMessageByObjectId(objectId);
    }

    /**
     * POST method for creating an instance of ObjectLogsResource
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
    public ObjectLogResource getObjectLogResource() {
        return new ObjectLogResource();
    }

    private TingcoUtils getObjectLogMessageByObjectId(String objectId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session ismOperationsession = null;
        TingcoUtils tingcoUtils = null;
        UtilDAO utilDAO = new UtilDAO();
        try {
            tingcoUtils = tingcoUtilsXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    ismOperationsession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    ObjectLog objectLog = (ObjectLog) utilDAO.getObjectLogsByObjectId(objectId, ismOperationsession);
                    tingcoUtils = tingcoUtilsXML.buildObjectLogsByObjectId(tingcoUtils, objectLog);
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-10);
                    tingcoUtils.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUtils;
                }
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUtils;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (ismOperationsession != null) {
                ismOperationsession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUtils;
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

    private TingcoUtils getobjectLogDetails(String content) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        Session ismOperationsession = null;
        Session session = null;
        TingcoUtils tingcoUtils = null;
        UtilDAO utilDAO = new UtilDAO();
        try {
            tingcoUtils = tingcoUtilsXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.DEVICE, TCMUtil.READ);
                if (hasPermission) {
                    ismOperationsession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    session = HibernateUtil.getSessionFactory().openSession();
                    TingcoUtils tingcoUtilsjaxb = (TingcoUtils) JAXBManager.getInstance().unMarshall(content, TingcoUtils.class);
                    ObjectLogs objectLogsjaxb = tingcoUtilsjaxb.getObjectLogs();
                    
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                    
                    List<ObjectLog> objectLogs = utilDAO.getObjectLogs(tingcoUtils, objectLogsjaxb, timeZoneGMToffset, ismOperationsession);
                    if (!objectLogs.isEmpty()) {
                        tingcoUtils = tingcoUtilsXML.buildObjectLogs(tingcoUtils, objectLogs, timeZoneGMToffset);
                    } else {
                        tingcoUtils.getMsgStatus().setResponseCode(-1);
                        tingcoUtils.getMsgStatus().setResponseText("No logs found for the given input");
                        return tingcoUtils;
                    }
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-10);
                    tingcoUtils.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUtils;
                }
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUtils;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (ismOperationsession != null) {
                ismOperationsession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUtils;
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}
