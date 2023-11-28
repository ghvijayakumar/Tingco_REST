/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.restUtil;

import java.util.GregorianCalendar;
import java.util.TimeZone;
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
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import se.info24.jaxbUtil.TingcoUtilsXML;
import se.info24.pojo.UserLog;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;
import se.info24.utiljaxb.TingcoUtils;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/userlog")
public class UserLogsResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoUtilsXML tuXML = new TingcoUtilsXML();
    RestUtilDAO restUtilDAO = new RestUtilDAO();

    /** Creates a new instance of UserLogsResource */
    public UserLogsResource() {
    }

    /**
     * UserLog_Add
     * @param userID
     * @param action
     * @param tableName
     * @param actionvalue1
     * @param actionvalue2
     * @param result
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/userid/{userid}/action/{action}/tablename/{tablename}{actionvalue1:(/actionvalue1/[^/]+?)?}{actionvalue2:(/actionvalue2/[^/]+?)?}{results:(/results/[^/]+?)?}")
    @RESTDoc(methodName = "UserLog_Add", desc = "Used to create a new UserLog", req_Params = {"Action;String", "TableName;String"}, opt_Params = {"ActionValue1;String", "ActionValue2;String", "Results;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_Add(@PathParam("userid") String userID, @PathParam("action") String action, @PathParam("tablename") String tableName, @PathParam("actionvalue1") String actionvalue1,
            @PathParam("actionvalue2") String actionvalue2, @PathParam("results") String result) throws DatatypeConfigurationException {
        return createUserLog(userID, action, tableName, actionvalue1, actionvalue2, result);
    }

    /**
     * UserLog_Add
     * @param userID
     * @param action
     * @param tableName
     * @param actionvalue1
     * @param actionvalue2
     * @param result
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/userid/{userid}/action/{action}/tablename/{tablename}{actionvalue1:(/actionvalue1/[^/]+?)?}{actionvalue2:(/actionvalue2/[^/]+?)?}{results:(/results/[^/]+?)?}")
    public TingcoUtils getJson_Add(@PathParam("userid") String userID, @PathParam("action") String action, @PathParam("tablename") String tableName, @PathParam("actionvalue1") String actionvalue1,
            @PathParam("actionvalue2") String actionvalue2, @PathParam("results") String result) throws DatatypeConfigurationException {
        return createUserLog(userID, action, tableName, actionvalue1, actionvalue2, result);
    }

    /**
     * POST method for creating an instance of UserLogsResource
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
    public UserLogResource getUserLog_AddResource() {
        return new UserLogResource();
    }

    private TingcoUtils createUserLog(String userID, String action, String tableName, String actionvalue1, String actionvalue2, String result) throws DatatypeConfigurationException {
        TingcoUtils tingcoUtils = null;
        Session session = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
            if (action == null) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("Action is should not be empty");
                return tingcoUtils;
            } else if (tableName == null) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("TableName is should not be empty");
                return tingcoUtils;
            }

            if (actionvalue1.equals("")) {
                actionvalue1 = null;
            } else {
                actionvalue1 = actionvalue1.split("/")[2];
            }

            if (actionvalue2.equals("")) {
                actionvalue2 = null;
            } else {
                actionvalue2 = actionvalue2.split("/")[2];
            }

            if (result.equals("")) {
                result = null;
            } else {
                result = result.split("/")[2];
            }
            


            if (!TCMUtil.saveUserLog(userID, action, tableName, result, actionvalue1, actionvalue2, request.getRemoteHost())) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("Error Occured while Saving UserLog");
            }
            return tingcoUtils;
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error Occured while Saving UserLog");
            return tingcoUtils;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
