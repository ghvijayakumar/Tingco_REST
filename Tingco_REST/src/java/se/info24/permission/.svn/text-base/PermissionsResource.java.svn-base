/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.info24.permission;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;


/**
 * REST Web Service
 *
 * @author Sridhar
 */

public class PermissionsResource {
    private String id;
    /** Creates a new instance of PermissionsResource */
    private PermissionsResource(String id) {
        this.id=id;
    }

    /** Get instance of the PermissionsResource */
    public static PermissionsResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of PermissionsResource class.
        return new PermissionsResource(id);
    }

    /**
     * Retrieves representation of an instance of se.info24.permission.PermissionsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of PermissionsResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }

    /**
     * DELETE method for resource PermissionsResource
     */
    @DELETE
    public void delete() {
    }
}
