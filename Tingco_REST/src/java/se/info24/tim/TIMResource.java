/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.info24.tim;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;

/**
 * REST Web Service
 *
 * @author Vijayakumar
 */

public class TIMResource {
    @Context
    private UriInfo context;

    /** Creates a new instance of TIMResource */
    public TIMResource() {
    }

    /**
     * Retrieves representation of an instance of se.info24.tim.TIMResource
     * @param id resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml(@PathParam("id")
    String id) {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of TIMResource
     * @param id resource URI parameter
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(@PathParam("id")
    String id, String content) {
    }

    /**
     * DELETE method for resource TIMResource
     * @param id resource URI parameter
     */
    @DELETE
    public void delete(@PathParam("id")
    String id) {
    }
}
