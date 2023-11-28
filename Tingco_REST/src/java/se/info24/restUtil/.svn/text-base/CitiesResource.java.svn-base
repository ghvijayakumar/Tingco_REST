/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.restUtil;

import java.util.GregorianCalendar;
import java.util.Set;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.jaxbUtil.TingcoUtilsXML;
import se.info24.pojo.City;
import se.info24.pojo.CityTranslations;
import se.info24.pojo.CityTranslationsId;
import se.info24.pojo.Country;
import se.info24.pojo.UserSessions2;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;
import se.info24.utiljaxb.TingcoUtils;

/**
 * REST Web Service
 *
 * @author Sekhar
 */
@Path("/city")
public class CitiesResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoUtilsXML tuXML = new TingcoUtilsXML();
    RestUtilDAO restUtilDAO = new RestUtilDAO();

    /** Creates a new instance of CitiesResource */
    public CitiesResource() {
    }

    /**
     * City_Add
     * @param cityName
     * @param countryID
     * @param cityDesc
     * @param displayOrder
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/cityname/{cityname}/countryid/{countryid}{citydesc:(/citydesc/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}")
    @RESTDoc(methodName = "City_Add", desc = "Used to create a new City", req_Params = {"CityName;String", "CountryID;int"}, opt_Params = {"CityDesc;String", "DisplayOrder;int"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_Add(@PathParam("cityname") String cityName, @PathParam("countryid") int countryID, @PathParam("citydesc") String cityDesc, @PathParam("displayorder") String displayOrder) {
        return createCity(cityName, countryID, cityDesc, displayOrder);
    }

    /**
     * City_Add
     * @param cityName
     * @param countryID
     * @param cityDesc
     * @param displayOrder
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/add/rest/{rest}/cityname/{cityname}/countryid/{countryid}{citydesc:(/citydesc/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}")
    public TingcoUtils postXml_Add(@PathParam("cityname") String cityName, @PathParam("countryid") int countryID, @PathParam("citydesc") String cityDesc, @PathParam("displayorder") String displayOrder) {
        return createCity(cityName, countryID, cityDesc, displayOrder);
    }

    /**
     * City_Add
     * @param cityName
     * @param countryID
     * @param cityDesc
     * @param displayOrder
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/cityname/{cityname}/countryid/{countryid}{citydesc:(/citydesc/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}")
    public TingcoUtils getJson_Add(@PathParam("cityname") String cityName, @PathParam("countryid") int countryID, @PathParam("citydesc") String cityDesc, @PathParam("displayorder") String displayOrder) {
        return createCity(cityName, countryID, cityDesc, displayOrder);
    }

    /**
     * City_Add
     * @param cityName
     * @param countryID
     * @param cityDesc
     * @param displayOrder
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/add/json/{json}/cityname/{cityname}/countryid/{countryid}{citydesc:(/citydesc/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}")
    public TingcoUtils postJson_Add(@PathParam("cityname") String cityName, @PathParam("countryid") int countryID, @PathParam("citydesc") String cityDesc, @PathParam("displayorder") String displayOrder) {
        return createCity(cityName, countryID, cityDesc, displayOrder);
    }

    /**
     * City_Delete
     * @param cityID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/cityid/{cityid}")
    @RESTDoc(methodName = "City_Delete", desc = "To Delete a City", req_Params = {"CityID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_Delete(@PathParam("cityid") String cityID) {
        return deleteCity(cityID);
    }

    /**
     * City_Delete
     * @param cityID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/cityid/{cityid}")
    public TingcoUtils postXml_Delete(@PathParam("cityid") String cityID) {
        return deleteCity(cityID);
    }

    /**
     * City_Delete
     * @param cityID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/cityid/{cityid}")
    public TingcoUtils getJson_Delete(@PathParam("cityid") String cityID) {
        return deleteCity(cityID);
    }

    /**
     * City_Delete
     * @param cityID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/delete/json/{json}/cityid/{cityid}")
    public TingcoUtils postJson_Delete(@PathParam("cityid") String cityID) {
        return deleteCity(cityID);
    }

    /**
     * City_Update
     * @param cityID
     * @param cityName
     * @param countryID
     * @param cityDesc
     * @param displayOrder
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/update/rest/{rest}/cityid/{cityid}{cityname:(/cityname/[^/]+?)?}{countryid:(/countryid/[^/]+?)?}{citydesc:(/citydesc/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}")
    @RESTDoc(methodName = "City_Update", desc = "Used to Update a new City", req_Params = {"CityID;int"}, opt_Params = {"CityName;String", "CountryID;int", "CityDesc;String", "DisplayOrder;int"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_Update(@PathParam("cityid") String cityID, @PathParam("cityname") String cityName, @PathParam("countryid") String countryID, @PathParam("citydesc") String cityDesc, @PathParam("displayorder") String displayOrder) {
        return updateCity(cityID, cityName, countryID, cityDesc, displayOrder);
    }

    /**
     * City_Update
     * @param cityID
     * @param cityName
     * @param countryID
     * @param cityDesc
     * @param displayOrder
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/update/rest/{rest}/cityid/{cityid}{cityname:(/cityname/[^/]+?)?}{countryid:(/countryid/[^/]+?)?}{citydesc:(/citydesc/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}")
    public TingcoUtils postXml_Update(@PathParam("cityid") String cityID, @PathParam("cityname") String cityName, @PathParam("countryid") String countryID, @PathParam("citydesc") String cityDesc, @PathParam("displayorder") String displayOrder) {
        return updateCity(cityID, cityName, countryID, cityDesc, displayOrder);
    }

    /**
     * City_Update
     * @param cityID
     * @param cityName
     * @param countryID
     * @param cityDesc
     * @param displayOrder
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/update/json/{json}/cityid/{cityid}{cityname:(/cityname/[^/]+?)?}{countryid:(/countryid/[^/]+?)?}{citydesc:(/citydesc/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}")
    public TingcoUtils getJson_Update(@PathParam("cityid") String cityID, @PathParam("cityname") String cityName, @PathParam("countryid") String countryID, @PathParam("citydesc") String cityDesc, @PathParam("displayorder") String displayOrder) {
        return updateCity(cityID, cityName, countryID, cityDesc, displayOrder);
    }

    /**
     * City_Update
     * @param cityID
     * @param cityName
     * @param countryID
     * @param cityDesc
     * @param displayOrder
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/update/json/{json}/cityid/{cityid}{cityname:(/cityname/[^/]+?)?}{countryid:(/countryid/[^/]+?)?}{citydesc:(/citydesc/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}")
    public TingcoUtils postJson_Update(@PathParam("cityid") String cityID, @PathParam("cityname") String cityName, @PathParam("countryid") String countryID, @PathParam("citydesc") String cityDesc, @PathParam("displayorder") String displayOrder) {
        return updateCity(cityID, cityName, countryID, cityDesc, displayOrder);
    }

    private TingcoUtils createCity(String cityName, int countryID, String cityDesc, String displayOrder) {
        TingcoUtils tingcoUtils = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        if (request.getAttribute("USERSESSION") != null) {
            if (cityName == null) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("CityName is should not be empty");
                return tingcoUtils;
            } else if (countryID <= 0) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("CountryID is should not be empty");
                return tingcoUtils;
            }

            if (cityDesc.equals("")) {
                cityDesc = null;
            } else {
                cityDesc = cityDesc.split("/")[2];
            }

            if (displayOrder.equals("")) {
                displayOrder = null;
            } else {
                displayOrder = displayOrder.split("/")[2];
            }
            UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
            City city = new City();
            Country country = new Country(Integer.valueOf(countryID));
            city.setCountry(country);
            if (displayOrder != null) {
                city.setDisplayOrder(Integer.valueOf(displayOrder));
            }
            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            city.setCreatedDate(gc.getTime());
            city.setUpdatedDate(gc.getTime());

            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                Transaction tx = session.beginTransaction();
                session.saveOrUpdate(city);
                tx.commit();

                CityTranslations ct = new CityTranslations();
                ct.setCity(city);
                ct.setCityName(cityName);
                if (cityDesc != null) {
                    ct.setCityDescription(cityDesc);
                }
                ct.setId(new CityTranslationsId(city.getCity(), countryID));
                ct.setCountry(country);
                ct.setUserId(sessions2.getUserId());
                ct.setCreatedDate(gc.getTime());
                ct.setUpdatedDate(gc.getTime());
                city.getCityTranslationses().add(ct);



                if (restUtilDAO.saveCity(city, session)) {
                    return tingcoUtils;
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("Error Occured while Saving City");
                    return tingcoUtils;
                }
            } finally {
                session.close();
            }
        } else {
            tingcoUtils.getMsgStatus().setResponseCode(-3);
            tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoUtils;
        }

    }

    private TingcoUtils deleteCity(String cityID) {
        TingcoUtils tingcoUtils = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
        } catch (DatatypeConfigurationException ex) {
           TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        if (request.getAttribute("USERSESSION") != null) {
            if (cityID == null) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("CityID should not be empty");
                return tingcoUtils;
            }
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                City city = restUtilDAO.getCityById(cityID, session);

                if (city != null) {

                    if (restUtilDAO.removeCity(city, session)) {
                        return tingcoUtils;
                    } else {
                        tingcoUtils.getMsgStatus().setResponseCode(-1);
                        tingcoUtils.getMsgStatus().setResponseText("Error Occured while Deleting CityID");
                        return tingcoUtils;
                    }

                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("CityID Not Found");
                    return tingcoUtils;
                }
            } finally {
                session.close();
            }
        } else {
            tingcoUtils.getMsgStatus().setResponseCode(-3);
            tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoUtils;
        }

    }

    private TingcoUtils updateCity(String cityId, String cityName, String countryId, String cityDesc, String displayOrder) {
        TingcoUtils tingcoUtils = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
        } catch (DatatypeConfigurationException ex) {
           TCMUtil.exceptionLog(getClass().getName(), ex);
        }

        if (request.getAttribute("USERSESSION") != null) {
            if (cityId == null) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("CityID is should not be empty");
                return tingcoUtils;
            }
            if (cityName.equals("")) {
                cityName = null;
            } else {
                cityName = cityName.split("/")[2];
            }
            if (countryId.equals("")) {
                countryId = null;
            } else {
                countryId = countryId.split("/")[2];
            }
            if (cityDesc.equals("")) {
                cityDesc = null;
            } else {
                cityDesc = cityDesc.split("/")[2];
            }

            if (displayOrder.equals("")) {
                displayOrder = null;
            } else {
                displayOrder = displayOrder.split("/")[2];
            }
            if (cityName == null && countryId == null && cityDesc == null && displayOrder == null) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("Alleast One Value Should Present");
                return tingcoUtils;
            }
            Session session = HibernateUtil.getSessionFactory().openSession();
            UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
            try {
                City city = restUtilDAO.getCityById(cityId, session);
                if (city != null) {
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    Set<CityTranslations> cityTransSet = city.getCityTranslationses();
                    boolean flag = true;
                    for (CityTranslations ct : cityTransSet) {
                        if (ct.getCountry().getCountryId() == Integer.parseInt(countryId)) { // Existing Translation
                            flag = false;
                            if (cityName != null) {
                                ct.setCityName(cityName);
                            }
                            if (cityDesc != null) {
                                ct.setCityDescription(cityDesc);
                            }
                            ct.setUpdatedDate(gc.getTime());
                            ct.setUserId(sessions2.getUserId());
                            break;
                        }
                    }

                    if (flag) { // New Translation.
                        CityTranslations ct = new CityTranslations();
                        ct.setCity(city);
                        ct.setCityName(cityName);
                        if (cityDesc != null) {
                            ct.setCityDescription(cityDesc);
                        }
                        ct.setId(new CityTranslationsId(city.getCity(), Integer.valueOf(countryId)));
                        ct.setCountry(new Country(Integer.valueOf(countryId)));
                        ct.setUserId(sessions2.getUserId());
                        ct.setCreatedDate(gc.getTime());
                        ct.setUpdatedDate(gc.getTime());
                        city.getCityTranslationses().add(ct);
                    }

                    if (displayOrder != null) {
                        city.setDisplayOrder(Integer.valueOf(displayOrder));
                    }

                    city.setUpdatedDate(gc.getTime());

                    if (restUtilDAO.saveCity(city, session)) {
                        return tingcoUtils;
                    } else {
                        tingcoUtils.getMsgStatus().setResponseCode(-1);
                        tingcoUtils.getMsgStatus().setResponseText("Error Occured while Saving Country");
                        return tingcoUtils;
                    }

                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("CountryID Not Found");
                    return tingcoUtils;
                }
            } catch (Exception e) {
                TCMUtil.exceptionLog(getClass().getName(), e);
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("Eroor Occured While Updating the Country");
                return tingcoUtils;
            } finally {
                session.close();
            }
        } else {
            tingcoUtils.getMsgStatus().setResponseCode(-3);
            tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoUtils;
        }

    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public CityResource getCity_AddResource() {
        return new CityResource();
    }
}
