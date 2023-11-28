/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.info24.restUtil;

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

public class CountryStatesResource {
    private String id;
    /** Creates a new instance of CountryStatesResource */
    private CountryStatesResource(String id) {
        this.id=id;
    }

    /** Get instance of the CountryStatesResource */
    public static CountryStatesResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of CountryStatesResource class.
        return new CountryStatesResource(id);
    }

    /**
     * Retrieves representation of an instance of se.info24.restUtil.CountryStatesResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of CountryStatesResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }

    /**
     * DELETE method for resource CountryStatesResource
     */
    @DELETE
    public void delete() {
    }
}
