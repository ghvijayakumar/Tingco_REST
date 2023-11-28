/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.restUtil;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import se.info24.jaxbUtil.TingcoUtilsXML;
import se.info24.pojo.CountryStates;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;
import se.info24.utiljaxb.TingcoUtils;

/**
 * REST Web Service
 *
 * @author Sridhar | Sekhar
 */
@Path("/countrystates")
public class CountryStatessResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    RestUtilDAO restUtilDao = new RestUtilDAO();
    TingcoUtilsXML tuXML = new TingcoUtilsXML();

    /** Creates a new instance of CountryStatessResource */
    public CountryStatessResource() {
    }

    /**
     * GetAllStates
     * @param countryID
     * @return
     */
    @GET
    @Path("/rest/{rest}/countryid/{countryid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetAllStates", desc = "Get All States from CountryStates for a particular CountryID", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_All(@PathParam("countryid") int countryID) {
        return getAllStates(countryID);
    }

    /**
     * GetAllStates
     * @param countryID
     * @return
     */
    @GET
    @Path("/json/{json}/countryid/{countryid}")
    @Produces("application/json")
    public TingcoUtils getJSON_All(@PathParam("countryid") int countryID) {
        return getAllStates(countryID);
    }

    /**
     * GetAllStates
     * @param countryID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/rest/{rest}/countryid/{countryid}")
    public TingcoUtils postXml_All(@PathParam("countryid") int countryID) {
        return getAllStates(countryID);
    }

    /**
     * GetAllStates
     * @param countryID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/json/{json}/countryid/{countryid}")
    public TingcoUtils postJson_All(@PathParam("countryid") int countryID) {
        return getAllStates(countryID);
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public CountryStatesResource getGetAllStatesResource(@PathParam("id") String id) {
        return CountryStatesResource.getInstance(id);
    }

    private TingcoUtils getAllStates(int countryID) {
        TingcoUtils tingcoUtils = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
        } catch (DatatypeConfigurationException dce) {
            TCMUtil.exceptionLog(getClass().getName(), dce);
        }
        if (request.getAttribute("USERSESSION") != null) {
            if (countryID <= 0) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("CountryID should not be Empty");
                return tingcoUtils;
            }
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                List<CountryStates> countryStatesList = restUtilDao.getCountryStatesByCountryId(countryID, session);
                if (countryStatesList != null) {
                    tingcoUtils = tuXML.buildCountryStates(tingcoUtils, countryStatesList);
                    return tingcoUtils;
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("No States Found with given CountryID");
                    return tingcoUtils;
                }
            } catch (Exception e) {
                TCMUtil.exceptionLog(getClass().getName(), e);
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("Error Occured While Loading CountryStates");
                return tingcoUtils;
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        } else {
            tingcoUtils.getMsgStatus().setResponseCode(-3);
            tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoUtils;
        }
    }
}
