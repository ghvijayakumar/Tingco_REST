/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.util;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.UUID;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import se.info24.apijaxb.TingcoAPI;

/**
 * REST Web Service
 *
 * @author Sridhar Dasari
 */
@Path("/api_ping")
public class API_PingsResource {

    @Context
    private UriInfo context;

    /** Creates a new instance of API_PingsResource */
    public API_PingsResource() {
    }

    /**
     * Retrieves representation of an instance of se.info24.util.API_PingsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/rest/{rest}/")
    @Produces("application/xml")
    @RESTDoc(methodName = "API_Ping", desc = "Used to check Whether the Server is up and Running", req_Params = {"rest;v1"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoAPI getXml() throws DatatypeConfigurationException {
        return TCMUtil.ping();
    }

    @GET
    @Path("/json/{json}/")
    @Produces("application/json")
    public TingcoAPI getJSON() throws DatatypeConfigurationException {
        return TCMUtil.ping();
    }

    /**
     * POST method for creating an instance of API_PingResource
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

    @GET
    @Path("/dashboard/rest/{rest}")
    @Produces("application/xml")
    @RESTDoc(methodName = "API_Ping", desc = "Used to check Whether the Server is up and Running", req_Params = {"rest;v1"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoAPI getXmlDashBoard() throws DatatypeConfigurationException {
        se.info24.apijaxb.ObjectFactory of = new se.info24.apijaxb.ObjectFactory();
        TingcoAPI api = of.createTingcoAPI();
        api.setCreateRef("Info24");
        api.setMsgVer(new BigDecimal(1.0));
        api.setRegionalUnits("Metric");
        api.setTimeZone("UTC");
        api.setMsgID(UUID.randomUUID().toString());
        api.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));
        se.info24.apijaxb.MsgStatus status = new se.info24.apijaxb.MsgStatus();
        status.setResponseCode(0);
        status.setResponseText(TingcoConstants.getUserSessions2DaemonTimer() + "");

        api.setMsgStatus(status);
        return api;
    }

    @GET
    @Path("/dashboard/json/{json}")
    @Produces("application/json")
    @RESTDoc(methodName = "API_Ping", desc = "Used to check Whether the Server is up and Running", req_Params = {"rest;v1"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoAPI getJsonDashBoard() throws DatatypeConfigurationException {
        se.info24.apijaxb.ObjectFactory of = new se.info24.apijaxb.ObjectFactory();
        TingcoAPI api = of.createTingcoAPI();
        api.setCreateRef("Info24");
        api.setMsgVer(new BigDecimal(1.0));
        api.setRegionalUnits("Metric");
        api.setTimeZone("UTC");
        api.setMsgID(UUID.randomUUID().toString());
        api.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));
        se.info24.apijaxb.MsgStatus status = new se.info24.apijaxb.MsgStatus();
        status.setResponseCode(0);
        status.setResponseText(TingcoConstants.getUserSessions2DaemonTimer() + "");

        api.setMsgStatus(status);
        return api;
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public API_PingResource getAPI_PingResource(@PathParam("id") String id) {
        return API_PingResource.getInstance(id);
    }
}
