/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.info24.group;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.Consumes;

/**
 * REST Web Service
 *
 * @author Ravikant
 */

public class FavoriteGroupResource {
    @Context
    private UriInfo context;

    /** Creates a new instance of FavoriteGroupResource */
    public FavoriteGroupResource() {
    }

    /**
     * Retrieves representation of an instance of se.info24.group.FavoriteGroupResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of FavoriteGroupResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }

    /**
     * DELETE method for resource FavoriteGroupResource
     */
    @DELETE
    public void delete() {
    }
}
