/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.restUtil;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import se.info24.jaxbUtil.TingcoUtilsXML;
import se.info24.pojo.Country;
import se.info24.pojo.CountryPhoneCodes;
import se.info24.pojo.Currency;
import se.info24.pojo.UserSessions2;
import se.info24.user.CountryDAO;
import se.info24.user.User_LoginsResource;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;
import se.info24.utiljaxb.TingcoUtils;

/**
 * REST Web Service
 *
 * @author Sekhar
 */
@Path("/country")
public class CountriesResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoUtilsXML tuXML = new TingcoUtilsXML();
    CountryDAO countryDAO = new CountryDAO();

    /** Creates a new instance of CountriesResource */
    public CountriesResource() {
    }

    /**
     * Country_Add
     * @param countryName
     * @param currencyID
     * @param countryDesc
     * @param languageCode
     * @param language
     * @param displayOrder
     * @param functionID
     * @param timeZoneID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/countryname/{countryname}{currencyid:(/currencyid/[^/]+?)?}{countrydesc:(/countrydesc/[^/]+?)?}{languagecode:(/languagecode/[^/]+?)?}{language:(/language/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}{functionid:(/functionid/[^/]+?)?}{timezoneid:(/timezoneid/[^/]+?)?}")
    @RESTDoc(methodName = "Country_Add", desc = "Used to create a new Country", req_Params = {"CountryName;String"}, opt_Params = {"CurrencyID;int", "CountryDesc;String", "LanguageCode;String", "Language;String", "DisplayOrder;int", "FunctionID;int", "TimeZoneID;UUID"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_Add(@PathParam("countryname") String countryName, @PathParam("currencyid") String currencyID, @PathParam("countrydesc") String countryDesc, @PathParam("languagecode") String languageCode, @PathParam("language") String language, @PathParam("displayorder") String displayOrder, @PathParam("functionid") String functionID, @PathParam("timezoneid") String timeZoneID) {
        return createCoutnry(countryName, currencyID, countryDesc, languageCode, language, displayOrder, functionID, timeZoneID);
    }

    /**
     * Country_Add
     * @param countryName
     * @param currencyID
     * @param countryDesc
     * @param languageCode
     * @param language
     * @param displayOrder
     * @param functionID
     * @param timeZoneID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/add/rest/{rest}/countryname/{countryname}{currencyid:(/currencyid/[^/]+?)?}{countrydesc:(/countrydesc/[^/]+?)?}{languagecode:(/languagecode/[^/]+?)?}{language:(/language/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}{functionid:(/functionid/[^/]+?)?}{timezoneid:(/timezoneid/[^/]+?)?}")
    public TingcoUtils postXml_Add(@PathParam("countryname") String countryName, @PathParam("currencyid") String currencyID, @PathParam("countrydesc") String countryDesc, @PathParam("languagecode") String languageCode, @PathParam("language") String language, @PathParam("displayorder") String displayOrder, @PathParam("functionid") String functionID, @PathParam("timezoneid") String timeZoneID) {
        return createCoutnry(countryName, currencyID, countryDesc, languageCode, language, displayOrder, functionID, timeZoneID);
    }

    /**
     * Country_Add
     * @param countryName
     * @param currencyID
     * @param countryDesc
     * @param languageCode
     * @param language
     * @param displayOrder
     * @param functionID
     * @param timeZoneID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/countryname/{countryname}{currencyid:(/currencyid/[^/]+?)?}{countrydesc:(/countrydesc/[^/]+?)?}{languagecode:(/languagecode/[^/]+?)?}{language:(/language/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}{functionid:(/functionid/[^/]+?)?}{timezoneid:(/timezoneid/[^/]+?)?}")
    public TingcoUtils getJson_Add(@PathParam("countryname") String countryName, @PathParam("currencyid") String currencyID, @PathParam("countrydesc") String countryDesc, @PathParam("languagecode") String languageCode, @PathParam("language") String language, @PathParam("displayorder") String displayOrder, @PathParam("functionid") String functionID, @PathParam("timezoneid") String timeZoneID) {
        return createCoutnry(countryName, currencyID, countryDesc, languageCode, language, displayOrder, functionID, timeZoneID);
    }

    /**
     * Country_Add
     * @param countryName
     * @param currencyID
     * @param countryDesc
     * @param languageCode
     * @param language
     * @param displayOrder
     * @param functionID
     * @param timeZoneID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/add/json/{json}/countryname/{countryname}{currencyid:(/currencyid/[^/]+?)?}{countrydesc:(/countrydesc/[^/]+?)?}{languagecode:(/languagecode/[^/]+?)?}{language:(/language/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}{functionid:(/functionid/[^/]+?)?}{timezoneid:(/timezoneid/[^/]+?)?}")
    public TingcoUtils postJson_Add(@PathParam("countryname") String countryName, @PathParam("currencyid") String currencyID, @PathParam("countrydesc") String countryDesc, @PathParam("languagecode") String languageCode, @PathParam("language") String language, @PathParam("displayorder") String displayOrder, @PathParam("functionid") String functionID, @PathParam("timezoneid") String timeZoneID) {
        return createCoutnry(countryName, currencyID, countryDesc, languageCode, language, displayOrder, functionID, timeZoneID);
    }

    /**
     * Country_Delete
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "Country_Delete", desc = "To Delete a Country", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_Delete(@PathParam("countryid") int countryID) {
        return deleteCountry(countryID);
    }

    /**
     * Country_Delete
     * @param countryID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/countryid/{countryid}")
    public TingcoUtils postXml_Delete(@PathParam("countryid") int countryID) {
        return deleteCountry(countryID);
    }

    /**
     * Country_Delete
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/countryid/{countryid}")
    public TingcoUtils getJson_Delete(@PathParam("countryid") int countryID) {
        return deleteCountry(countryID);
    }

    /**
     * Country_Delete
     * @param countryID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/delete/json/{json}/countryid/{countryid}")
    public TingcoUtils postJson_Delete(@PathParam("countryid") int countryID) {
        return deleteCountry(countryID);
    }

    /**
     * Country_Update
     * @param countryID
     * @param countryName
     * @param currencyID
     * @param countryDesc
     * @param languageCode
     * @param language
     * @param displayOrder
     * @param functionID
     * @param timeZoneID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/update/rest/{rest}/countryid/{countryid}{countryname:(/countryname/[^/]+?)?}{currencyid:(/currencyid/[^/]+?)?}{countrydesc:(/countrydesc/[^/]+?)?}{languagecode:(/languagecode/[^/]+?)?}{language:(/language/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}{functionid:(/functionid/[^/]+?)?}{timezoneid:(/timezoneid/[^/]+?)?}")
    @RESTDoc(methodName = "Country_Update", desc = "Used to Update a new Country", req_Params = {"CountryID;int"}, opt_Params = {"CountryName;String", "CurrencyID;int", "CountryDesc;String", "LanguageCode;String", "Language;String", "DisplayOrder;int", "FunctionID;int", "TimeZoneID;UUID"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_Update(@PathParam("countryid") int countryID, @PathParam("countryname") String countryName, @PathParam("currencyid") String currencyID, @PathParam("countrydesc") String countryDesc, @PathParam("languagecode") String languageCode, @PathParam("language") String language, @PathParam("displayorder") String displayOrder, @PathParam("functionid") String functionID, @PathParam("timezoneid") String timeZoneID) {
        return updateCountry(countryID, countryName, currencyID, countryDesc, languageCode, language, displayOrder, functionID, timeZoneID);
    }

    /**
     * Country_Update
     * @param countryID
     * @param countryName
     * @param currencyID
     * @param countryDesc
     * @param languageCode
     * @param language
     * @param displayOrder
     * @param functionID
     * @param timeZoneID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/update/rest/{rest}/countryid/{countryid}{countryname:(/countryname/[^/]+?)?}{currencyid:(/currencyid/[^/]+?)?}{countrydesc:(/countrydesc/[^/]+?)?}{languagecode:(/languagecode/[^/]+?)?}{language:(/language/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}{functionid:(/functionid/[^/]+?)?}{timezoneid:(/timezoneid/[^/]+?)?}")
    public TingcoUtils postXml_Update(@PathParam("countryid") int countryID, @PathParam("countryname") String countryName, @PathParam("currencyid") String currencyID, @PathParam("countrydesc") String countryDesc, @PathParam("languagecode") String languageCode, @PathParam("language") String language, @PathParam("displayorder") String displayOrder, @PathParam("functionid") String functionID, @PathParam("timezoneid") String timeZoneID) {
        return updateCountry(countryID, countryName, currencyID, countryDesc, languageCode, language, displayOrder, functionID, timeZoneID);
    }

    /**
     * Country_Update
     * @param countryID
     * @param countryName
     * @param currencyID
     * @param countryDesc
     * @param languageCode
     * @param language
     * @param displayOrder
     * @param functionID
     * @param timeZoneID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/update/json/{json}/countryid/{countryid}{countryname:(/countryname/[^/]+?)?}{currencyid:(/currencyid/[^/]+?)?}{countrydesc:(/countrydesc/[^/]+?)?}{languagecode:(/languagecode/[^/]+?)?}{language:(/language/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}{functionid:(/functionid/[^/]+?)?}{timezoneid:(/timezoneid/[^/]+?)?}")
    public TingcoUtils getJson_Update(@PathParam("countryid") int countryID, @PathParam("countryname") String countryName, @PathParam("currencyid") String currencyID, @PathParam("countrydesc") String countryDesc, @PathParam("languagecode") String languageCode, @PathParam("language") String language, @PathParam("displayorder") String displayOrder, @PathParam("functionid") String functionID, @PathParam("timezoneid") String timeZoneID) {
        return updateCountry(countryID, countryName, currencyID, countryDesc, languageCode, language, displayOrder, functionID, timeZoneID);
    }

    /**
     * Country_Update
     * @param countryID
     * @param countryName
     * @param currencyID
     * @param countryDesc
     * @param languageCode
     * @param language
     * @param displayOrder
     * @param functionID
     * @param timeZoneID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/update/json/{json}/countryid/{countryid}{countryname:(/countryname/[^/]+?)?}{currencyid:(/currencyid/[^/]+?)?}{countrydesc:(/countrydesc/[^/]+?)?}{languagecode:(/languagecode/[^/]+?)?}{language:(/language/[^/]+?)?}{displayorder:(/displayorder/[^/]+?)?}{functionid:(/functionid/[^/]+?)?}{timezoneid:(/timezoneid/[^/]+?)?}")
    public TingcoUtils postJson_Update(@PathParam("countryid") int countryID, @PathParam("countryname") String countryName, @PathParam("currencyid") String currencyID, @PathParam("countrydesc") String countryDesc, @PathParam("languagecode") String languageCode, @PathParam("language") String language, @PathParam("displayorder") String displayOrder, @PathParam("functionid") String functionID, @PathParam("timezoneid") String timeZoneID) {
        return updateCountry(countryID, countryName, currencyID, countryDesc, languageCode, language, displayOrder, functionID, timeZoneID);
    }

    /**
     * GetAllLanguages
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/rest/{rest}{searchstring:(/searchstring/[^/]+?)?}{isloacalized:(/isloacalized/[^/]+?)?}")
    @RESTDoc(methodName = "GetAllLanguages", desc = "Get All Languages or Country Info", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_All(@PathParam("searchstring") String searchString, @PathParam("isloacalized") String isLoacalized) {
        return getLangInfo(searchString,isLoacalized);
    }

    /**
     * GetAllLanguages
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/json/{json}{searchstring:(/searchstring/[^/]+?)?}{isloacalized:(/isloacalized/[^/]+?)?}")
    public TingcoUtils getJson_All(@PathParam("searchstring") String searchString, @PathParam("isloacalized") String isLoacalized) {
        return getLangInfo(searchString,isLoacalized);
    }

//    /**
//     * GetAllLanguages
//     * @return
//     */
//    @POST
//    @Produces("application/xml")
//    @Path("/rest/{rest}{searchstring:(/searchstring/[^/]+?)?}")
//    public TingcoUtils postXml_All() {
//        return getLangInfo(searchString);
//    }
//
//    /**
//     * GetAllLanguages
//     * @return
//     */
//    @POST
//    @Produces("application/json")
//    @Path("/json/{json}{searchstring:(/searchstring/[^/]+?)?}")
//    public TingcoUtils postJson_All() {
//        return getLangInfo(searchString);
//    }
    /**
     * GetCountryPhoneCodes
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getphonecodes/rest/{rest}")
    public TingcoUtils getXml_PhoneCodes() throws DatatypeConfigurationException {
        return getCountryPhoneCodes();
    }

    /**
     * GetCountryPhoneCodes
     * @param countryId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getphonecodes/json/{json}")
    public TingcoUtils getJson_PhoneCodes() throws DatatypeConfigurationException {
        return getCountryPhoneCodes();
    }

    /**
     * GetCountryDetails
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetCountryDetails", desc = "Get Country details", req_Params = {"CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_GetCountry(@PathParam("countryid") int countryId) {
        return getCountryDetails(countryId);
    }

    /**
     * GetCountryDetails
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/countryid/{countryid}")
    @RESTDoc(methodName = "GetCountryDetails", desc = "Get Country details", req_Params = {"CountryID;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getJson_GetCountry(@PathParam("countryid") int countryId) {
        return getCountryDetails(countryId);
    }

    private TingcoUtils getLangInfo(String searchString,String isLoacalized) {
        TingcoUtils tingcoUtils = null;
        Session session = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                 if (!isLoacalized.equals("")) {
                    isLoacalized = isLoacalized.split("/")[2];
                 }else{
                    isLoacalized = null;
                 }
                if (!searchString.equals("")) { //Implemented for TCM V3 sprint2 (3.0.0)
                    searchString = searchString.split("/")[2];
                    List<Country> countries = new ArrayList<Country>();
                    if(searchString.matches("[0-9]+")) { //checking contains countryId 
                        Country country = countryDAO.getCountryById(Integer.valueOf(searchString), session);
                        countries.add(country);
                    } else {
                        countries = countryDAO.getCountriesBySearchString(searchString, session);
                    }
                    if(!countries.isEmpty()) {
                        for(Country co: countries) {
                            System.out.println(co.getCountryName());
                        }
                    }
                    tingcoUtils = tuXML.buildAllLanguages(tingcoUtils, countries,isLoacalized);
                } else {
                    tingcoUtils = tuXML.buildAllLanguages(tingcoUtils, countryDAO.getAllCountries(session),isLoacalized);
                }
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
            }
        } catch (Exception dce) {
            TCMUtil.exceptionLog(getClass().getName(), dce);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return tingcoUtils;
    }

    private TingcoUtils createCoutnry(String countryName, String currencyID, String countryDesc, String languageCode, String language, String displayOrder, String functionId, String timezoneId) {
        TingcoUtils tingcoUtils = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        if (request.getAttribute("USERSESSION") != null) {
            if (countryName == null) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("CountryName is should not be empty");
                return tingcoUtils;
            }

            if (currencyID.equals("")) {
                currencyID = null;
            } else {
                currencyID = currencyID.split("/")[2];
            }

            if (countryDesc.equals("")) {
                countryDesc = null;
            } else {
                countryDesc = countryDesc.split("/")[2];
            }

            if (languageCode.equals("")) {
                languageCode = null;
            } else {
                languageCode = languageCode.split("/")[2];
            }
            if (language.equals("")) {
                language = null;
            } else {
                language = language.split("/")[2];
            }
            if (displayOrder.equals("")) {
                displayOrder = null;
            } else {
                displayOrder = displayOrder.split("/")[2];
            }

            if (functionId.equals("")) {
                functionId = null;
            } else {
                functionId = functionId.split("/")[2];
            }

            if (timezoneId.equals("")) {
                timezoneId = null;
            } else {
                timezoneId = timezoneId.split("/")[2];
            }

            Country country = new Country();
            country.setCountryName(countryName);
            if (currencyID != null) {
                country.setCurrency(new Currency(Integer.valueOf(currencyID)));
            }
            if (countryDesc != null) {
                country.setCountryDescription(countryDesc);
            }
            if (languageCode != null) {
                country.setLanguageCode(languageCode);
            }
            if (language != null) {
                country.setLanguage(language);
            }
            if (displayOrder != null) {
                country.setDisplayOrder(Integer.valueOf(displayOrder));
            }
            if (functionId != null) {
                country.setFunctionId(Integer.valueOf(functionId));
            }
            if (timezoneId != null) {
                country.setTimeZoneId(timezoneId);
            }

            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            country.setCreatedDate(gc.getTime());
            country.setUpdatedDate(gc.getTime());
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                if (!countryDAO.saveCountry(country, session)) {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("Error Occured while Saving Country");

                }
            } finally {
                session.close();
            }

        } else {
            tingcoUtils.getMsgStatus().setResponseCode(-3);
            tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoUtils;
        }
        return tingcoUtils;

    }

    private TingcoUtils deleteCountry(int countryID) {
        TingcoUtils tingcoUtils = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        if (request.getAttribute("USERSESSION") != null) {
            if (countryID <= 0) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("CountryID should not be empty");
                return tingcoUtils;
            }
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                Country country = countryDAO.getCountryById(countryID, session);

                if (country != null) {

                    if (countryDAO.removeCountry(country, session)) {
                        return tingcoUtils;
                    } else {
                        tingcoUtils.getMsgStatus().setResponseCode(-1);
                        tingcoUtils.getMsgStatus().setResponseText("Error Occured while Deleting Country");
                        return tingcoUtils;
                    }

                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("CountryID Not Found");
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

    private TingcoUtils getCountryDetails(int countryId) {
        boolean hasPermission = false;
        TingcoUtils tingcoUtils = null;
        Session session = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.USERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.USERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Country country = countryDAO.getCountryById(countryId, session);
                    if (country != null) {
                        tingcoUtils = tuXML.buildCountryDetails(tingcoUtils, country);
                    } else {
                        tingcoUtils.getMsgStatus().setResponseCode(-1);
                        tingcoUtils.getMsgStatus().setResponseText("No Record Found");
                    }
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-10);
                    tingcoUtils.getMsgStatus().setResponseText("User Permission is not given");
                }
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
            }
        } catch (Exception dce) {
            TCMUtil.exceptionLog(getClass().getName(), dce);
        } finally {
            if (session != null) {
                session.close();
            }
//            System.gc();
        }
        return tingcoUtils;
    }

    private TingcoUtils getCountryPhoneCodes() throws DatatypeConfigurationException {
        boolean hasPermission = false;
        TingcoUtils tingcoUtils = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
        } catch (DatatypeConfigurationException dce) {
            TCMUtil.exceptionLog(getClass().getName(), dce);
        }
        if (request.getAttribute("USERSESSION") != null) {

//            UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
//            Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
//            if (ht.containsKey(TCMUtil.DEVICE)) {
//                ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
//                for (int i = 0; i < operations.size(); i++) {
//                    if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
            hasPermission = true;
//                    }
//                }
//            }
            if (hasPermission) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                List<CountryPhoneCodes> phoneCodesList = countryDAO.getCountryPhoneCodes(session);
                tingcoUtils = tuXML.buildCountryPhoneCodes(tingcoUtils, phoneCodesList);
                session.close();
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-10);
                tingcoUtils.getMsgStatus().setResponseText("User Permission is not given");
            }
        } else {
            tingcoUtils.getMsgStatus().setResponseCode(-3);
            tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
        }
        return tingcoUtils;
    }

    private TingcoUtils updateCountry(int countryId, String countryName, String currencyID, String countryDesc, String languageCode, String language, String displayOrder, String functionId, String timezoneId) {
        TingcoUtils tingcoUtils = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        if (request.getAttribute("USERSESSION") != null) {
            if (countryId <= 0) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("CountryID is should not be empty");
                return tingcoUtils;
            }
            if (countryName.equals("")) {
                countryName = null;
            } else {
                countryName = countryName.split("/")[2];
            }
            if (currencyID.equals("")) {
                currencyID = null;
            } else {
                currencyID = currencyID.split("/")[2];
            }
            if (countryDesc.equals("")) {
                countryDesc = null;
            } else {
                countryDesc = countryDesc.split("/")[2];
            }
            if (languageCode.equals("")) {
                languageCode = null;
            } else {
                languageCode = languageCode.split("/")[2];
            }
            if (language.equals("")) {
                language = null;
            } else {
                language = language.split("/")[2];
            }
            if (displayOrder.equals("")) {
                displayOrder = null;
            } else {
                displayOrder = displayOrder.split("/")[2];
            }

            if (functionId.equals("")) {
                functionId = null;
            } else {
                functionId = functionId.split("/")[2];
            }

            if (timezoneId.equals("")) {
                timezoneId = null;
            } else {
                timezoneId = timezoneId.split("/")[2];
            }

            if (countryName == null && currencyID == null && countryDesc == null && languageCode == null && language == null && displayOrder == null && functionId == null && timezoneId == null) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("Alleast One Value Should Present");
                return tingcoUtils;
            }

            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                Country country = countryDAO.getCountryById(countryId, session);
                if (country != null) {
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

                    if (countryName != null) {
                        country.setCountryName(countryName);
                    }
                    if (currencyID != null) {
                        country.setCurrency(new Currency(Integer.valueOf(currencyID)));
                    }
                    if (countryDesc != null) {
                        country.setCountryDescription(countryDesc);
                    }

                    if (languageCode != null) {
                        country.setLanguageCode(languageCode);
                    }
                    if (language != null) {
                        country.setLanguage(language);
                    }
                    if (displayOrder != null) {
                        country.setDisplayOrder(Integer.valueOf(displayOrder));
                    }
                    if (functionId != null) {
                        country.setFunctionId(Integer.valueOf(functionId));
                    }
                    if (timezoneId != null) {
                        country.setTimeZoneId(timezoneId);
                    }
                    country.setUpdatedDate(gc.getTime());

                    if (countryDAO.saveCountry(country, session)) {
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
    public CountryResource getCountry_AddResource() {
        return new CountryResource();
    }
}
