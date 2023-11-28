/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.restUtil;

import java.util.List;
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
import se.info24.util.HibernateUtil;
import se.info24.utiljaxb.TingcoUtils;
import se.info24.pojo.WeekdayTranslations;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/weekdays")
public class WeekDayssResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoUtilsXML tuXML = new TingcoUtilsXML();
    RestUtilDAO dao = new RestUtilDAO();

    /** Creates a new instance of WeekDayssResource */
    public WeekDayssResource() {
    }

    /**
     * GetWeekDays
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/countryid/{countryid}/groupid/{groupid}")
    @RESTDoc(methodName = "GetWeekDays", desc = "Used to get the weekdays", req_Params = {"CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUtils getXml(@PathParam("countryid") int countryId, @PathParam("groupid") String groupId) {
        return getWeekDays(countryId, groupId);
    }

    /**
     * GetWeekDays
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/countryid/{countryid}/groupid/{groupid}")
    public TingcoUtils getJson(@PathParam("countryid") int countryId, @PathParam("groupid") String groupId) {
        return getWeekDays(countryId, groupId);
    }

    /**
     * POST method for creating an instance of WeekDayssResource
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
    public WeekDaysResource getWeekDaysResource() {
        return new WeekDaysResource();
    }

    private TingcoUtils getWeekDays(int countryId, String groupId) {
        TingcoUtils tingcoUtils = null;
        Session session = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                List<WeekdayTranslations> weekDaysList = dao.getWeekDays(session, countryId);
                tingcoUtils = tuXML.buildWeekDays(tingcoUtils, weekDaysList, session, groupId);
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
            }
        } catch (DatatypeConfigurationException dce) {
            TCMUtil.exceptionLog(getClass().getName(), dce);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error Occured");
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return tingcoUtils;
    }
}
