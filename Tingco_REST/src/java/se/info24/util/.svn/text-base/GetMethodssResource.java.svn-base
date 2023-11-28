/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.info24.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Sridhar Dasari
 */

@Path("/getmethods")
public class GetMethodssResource {
    @Context
    private UriInfo context;
    
    /** Creates a new instance of GetMethodssResource */
    public GetMethodssResource() {
    }

    /**
     * Retrieves representation of an instance of se.info24.util.GetMethodssResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/rest/{rest}")
    @Produces("application/xml")
    public String getXml() throws IOException, ClassNotFoundException {
        TCMUtil util = new TCMUtil();
        return util.getMethods();
    }

    @GET
    @Path("/json/{json}")
    @Produces("application/json")
    public String getJSON() throws IOException, ClassNotFoundException {
       TCMUtil util = new TCMUtil();
        return util.getMethods();
    }

    /**
     * POST method for creating an instance of GetMethodsResource
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
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public GetMethodsResource getGetMethodsResource(@PathParam("id")
    String id) {
        return GetMethodsResource.getInstance(id);
    }
}
