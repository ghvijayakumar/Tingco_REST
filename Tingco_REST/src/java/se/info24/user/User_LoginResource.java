/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.info24.user;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;


/**
 * REST Web Service
 *
 * @author Sridhar Dasari
 */

public class User_LoginResource {
    private String id;
    /** Creates a new instance of User_LoginResource */
    private User_LoginResource(String id) {
        this.id=id;
    }

    /** Get instance of the User_LoginResource */
    public static User_LoginResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of User_LoginResource class.
        return new User_LoginResource(id);
    }

    /**
     * Retrieves representation of an instance of se.info24.user.User_LoginResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of User_LoginResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }

    /**
     * DELETE method for resource User_LoginResource
     */
    @DELETE
    public void delete() {
    }
}
