/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.supportcase;


import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;

/**
 * REST Web Service
 *
 * @author Ravikant
 */
public class SupportCaseResource {

    

    /** Creates a new instance of SupportCaseResource */
    public SupportCaseResource() {
    }

    /**
     * Retrieves representation of an instance of se.info24.supportcase.SupportCaseResource
     * @param id resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml(@PathParam("id") String id) {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of SupportCaseResource
     * @param id resource URI parameter
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(@PathParam("id") String id, String content) {
    }

    /**
     * DELETE method for resource SupportCaseResource
     * @param id resource URI parameter
     */
    @DELETE
    public void delete(@PathParam("id") String id) {
    }
}
