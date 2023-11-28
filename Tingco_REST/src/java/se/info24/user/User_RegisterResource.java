/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.info24.user;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import se.info24.usersjaxb.TingcoUsers;

/**
 * REST Web Service
 *
 * @author Sridhar
 */

public class User_RegisterResource {
    @Context
    private UriInfo context;

    /** Creates a new instance of User_RegisterResource */
    public User_RegisterResource() {
    }

    /**
     * Retrieves representation of an instance of se.info24.user.User_RegisterResource
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
     * PUT method for updating or creating an instance of User_RegisterResource
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
     * DELETE method for resource User_RegisterResource
     * @param id resource URI parameter
     */
    @DELETE
    public void delete(@PathParam("id")
    String id) {
    }

   
}
