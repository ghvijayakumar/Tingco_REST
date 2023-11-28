/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.restUtil;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import se.info24.jaxbUtil.TingcoUtilsXML;
import se.info24.pojo.Country;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.Currency;
import se.info24.pojo.Users2;
import se.info24.user.CountryDAO;
import se.info24.user.UserDAO;
import se.info24.util.RESTDoc;
import se.info24.utiljaxb.TingcoUtils;
import se.info24.util.HibernateUtil;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Saptashiri
 */
@Path("/currency")
public class CurrenciesResource {

    @Context
    private HttpServletRequest request;
    TingcoUtilsXML tuXML = new TingcoUtilsXML();
    RestUtilDAO restUtilDao = new RestUtilDAO();
    UserDAO userDAO = new UserDAO();

    /** Creates a new instance of CurrenciesResource */
    public CurrenciesResource() {
    }

    /**
     * Currency_Add
     * @param currencyIsocode
     * @param currencyIsocharCode
     * @param currencyName
     * @param currencyDescription
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/currencyisocode/{currencyisocode}/currencyisocharcode/{currencyisocharcode}/currencyname/{currencyname}/currencydescription/{currencydescription}")
    @RESTDoc(methodName = "Currency_Add", desc = "Used to add currency", req_Params = {"CurrencyIsoCode;String", "CurrencyIsoCharCode;String", "CurrencyName;String", "CurrencyDescription;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_Add(@PathParam("currencyisocode") String currencyIsocode, @PathParam("currencyisocharcode") String currencyIsocharCode, @PathParam("currencyname") String currencyName, @PathParam("currencydescription") String currencyDescription) {
        return addCurrency(currencyIsocode, currencyIsocharCode, currencyName, currencyDescription);
    }

    /**
     * Currency_Add
     * @param currencyIsocode
     * @param currencyIsocharCode
     * @param currencyName
     * @param currencyDescription
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/add/rest/{rest}/currencyisocode/{currencyisocode}/currencyisocharcode/{currencyisocharcode}/currencyname/{currencyname}/currencydescription/{currencydescription}")
    public TingcoUtils postXml_Add(@PathParam("currencyisocode") String currencyIsocode, @PathParam("currencyisocharcode") String currencyIsocharCode, @PathParam("currencyname") String currencyName, @PathParam("currencydescription") String currencyDescription) {
        return addCurrency(currencyIsocode, currencyIsocharCode, currencyName, currencyDescription);
    }

    /**
     * Currency_Add
     * @param currencyIsocode
     * @param currencyIsocharCode
     * @param currencyName
     * @param currencyDescription
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/currencyisocode/{currencyisocode}/currencyisocharcode/{currencyisocharcode}/currencyname/{currencyname}/currencydescription/{currencydescription}")
    public TingcoUtils getJson_Add(@PathParam("currencyisocode") String currencyIsocode, @PathParam("currencyisocharcode") String currencyIsocharCode, @PathParam("currencyname") String currencyName, @PathParam("currencydescription") String currencyDescription) {
        return addCurrency(currencyIsocode, currencyIsocharCode, currencyName, currencyDescription);
    }

    /**
     * Currency_Add
     * @param currencyIsocode
     * @param currencyIsocharCode
     * @param currencyName
     * @param currencyDescription
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/add/json/{json}/currencyisocode/{currencyisocode}/currencyisocharcode/{currencyisocharcode}/currencyname/{currencyname}/currencydescription/{currencydescription}")
    public TingcoUtils postJson_Add(@PathParam("currencyisocode") String currencyIsocode, @PathParam("currencyisocharcode") String currencyIsocharCode, @PathParam("currencyname") String currencyName, @PathParam("currencydescription") String currencyDescription) {
        return addCurrency(currencyIsocode, currencyIsocharCode, currencyName, currencyDescription);
    }

    /**
     * Currency_Delete
     * @param currencyID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/currencyid/{currencyid}")
    @RESTDoc(methodName = "Currency_Delete", desc = "To Delete a Currency", req_Params = {"CurrencyID;String"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_Delete(@PathParam("currencyid") String currencyID) {
        return deleteCurrency(currencyID);
    }

    /**
     * Currency_Delete
     * @param currencyID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/currencyid/{currencyid}")
    public TingcoUtils postXml_Delete(@PathParam("currencyid") String currencyID) {
        return deleteCurrency(currencyID);
    }

    /**
     * Currency_Delete
     * @param currencyID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/currencyid/{currencyid}")
    public TingcoUtils getJson_Delete(@PathParam("currencyid") String currencyID) {
        return deleteCurrency(currencyID);
    }

    /**
     * Currency_Delete
     * @param currencyID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/delete/json/{json}/currencyid/{currencyid}")
    public TingcoUtils postJson_Delete(@PathParam("currencyid") String currencyID) {
        return deleteCurrency(currencyID);
    }

    /**
     * Currency_GetInfo
     * @param currencyID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/currencyid/{currencyid}")
    @RESTDoc(methodName = "Currency_GetInfo", desc = "To Get a Currency", req_Params = {"CurrencyID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml(@PathParam("currencyid") String currencyID) {
        return getCurrency(currencyID);
    }

    /**
     * Currency_GetInfo
     * @param currencyID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/get/rest/{rest}/currencyid/{currencyid}")
    public TingcoUtils postXml(@PathParam("currencyid") String currencyID) {
        return getCurrency(currencyID);
    }

    /**
     * Currency_GetInfo
     * @param currencyID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/currencyid/{currencyid}")
    public TingcoUtils getJson(@PathParam("currencyid") String currencyID) {
        return getCurrency(currencyID);
    }

    /**
     * Currency_GetInfo
     * @param currencyID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/get/json/{json}/currencyid/{currencyid}")
    public TingcoUtils postJson(@PathParam("currencyid") String currencyID) {
        return getCurrency(currencyID);
    }

    /**
     * Currency_Update
     * @param currencyID
     * @param currencyIsoCode
     * @param currencyIsoCharCode
     * @param currencyName
     * @param currencyDescription
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/update/rest/{rest}/currencyid/{currencyid}{currencyisocode:(/currencyisocode/[^/]+?)?}{currencyisocharcode:(/currencyisocharcode/[^/]+?)?}{currencyname:(/currencyname/[^/]+?)?}{currencydescription:(/currencydescription/[^/]+?)?}")
    @RESTDoc(methodName = "Currency_Update", desc = "Used to update Currency", req_Params = {"CurrencyID;String"}, opt_Params = {"CurrencyIsoCode;String", "CurrencyIsoCharCode;String", "CurrencyName;String", "CurrencyDescription;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_Update(@PathParam("currencyid") String currencyID, @PathParam("currencyisocode") String currencyIsoCode, @PathParam("currencyisocharcode") String currencyIsoCharCode, @PathParam("currencyname") String currencyName, @PathParam("currencydescription") String currencyDescription) {
        return updateDeviceType(currencyID, currencyIsoCode, currencyIsoCharCode, currencyName, currencyDescription);
    }

    /**
     * Currency_Update
     * @param currencyID
     * @param currencyIsoCode
     * @param currencyIsoCharCode
     * @param currencyName
     * @param currencyDescription
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/update/rest/{rest}/currencyid/{currencyid}{currencyisocode:(/currencyisocode/[^/]+?)?}{currencyisocharcode:(/currencyisocharcode/[^/]+?)?}{currencyname:(/currencyname/[^/]+?)?}{currencydescription:(/currencydescription/[^/]+?)?}")
    public TingcoUtils postXml_Update(@PathParam("currencyid") String currencyID, @PathParam("currencyisocode") String currencyIsoCode, @PathParam("currencyisocharcode") String currencyIsoCharCode, @PathParam("currencyname") String currencyName, @PathParam("currencydescription") String currencyDescription) {
        return updateDeviceType(currencyID, currencyIsoCode, currencyIsoCharCode, currencyName, currencyDescription);
    }

    /**
     * Currency_Update
     * @param currencyID
     * @param currencyIsoCode
     * @param currencyIsoCharCode
     * @param currencyName
     * @param currencyDescription
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/update/json/{json}/currencyid/{currencyid}{currencyisocode:(/currencyisocode/[^/]+?)?}{currencyisocharcode:(/currencyisocharcode/[^/]+?)?}{currencyname:(/currencyname/[^/]+?)?}{currencydescription:(/currencydescription/[^/]+?)?}")
    public TingcoUtils getJSON_Update(@PathParam("currencyid") String currencyID, @PathParam("currencyisocode") String currencyIsoCode, @PathParam("currencyisocharcode") String currencyIsoCharCode, @PathParam("currencyname") String currencyName, @PathParam("currencydescription") String currencyDescription) {
        return updateDeviceType(currencyID, currencyIsoCode, currencyIsoCharCode, currencyName, currencyDescription);
    }

    /**
     * Currency_Update
     * @param currencyID
     * @param currencyIsoCode
     * @param currencyIsoCharCode
     * @param currencyName
     * @param currencyDescription
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/update/json/{json}/currencyid/{currencyid}{currencyisocode:(/currencyisocode/[^/]+?)?}{currencyisocharcode:(/currencyisocharcode/[^/]+?)?}{currencyname:(/currencyname/[^/]+?)?}{currencydescription:(/currencydescription/[^/]+?)?}")
    public TingcoUtils postJSON_Update(@PathParam("currencyid") String currencyID, @PathParam("currencyisocode") String currencyIsoCode, @PathParam("currencyisocharcode") String currencyIsoCharCode, @PathParam("currencyname") String currencyName, @PathParam("currencydescription") String currencyDescription) {
        return updateDeviceType(currencyID, currencyIsoCode, currencyIsoCharCode, currencyName, currencyDescription);
    }

    /**
     * GetAllCurrencies
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/rest/{rest}")
    @RESTDoc(methodName = "GetAllCurrencies", desc = "To Get All DeviceTypes", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_All() {
        return getAllCurrencies();
    }

    /**
     * GetAllCurrencies
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/rest/{rest}")
    public TingcoUtils postXml_All() {
        return getAllCurrencies();
    }

    /**
     * GetAllCurrencies
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/json/{json}")
    public TingcoUtils getJson_All() {
        return getAllCurrencies();
    }

    /**
     * GetAllCurrencies
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/json/{json}")
    public TingcoUtils postJson_All() {
        return getAllCurrencies();
    }

    private TingcoUtils deleteCurrency(String currencyID) {
        TingcoUtils tingcoUtils = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        if (request.getAttribute("USERSESSION") != null) {
            if (currencyID == null) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("CurrencyID should not be empty");
                return tingcoUtils;
            }

            Session session = HibernateUtil.getSessionFactory().openSession();
            Currency currency = restUtilDao.getCurrencyById(currencyID, session);

            if (currency != null) {
                try {
                    if (restUtilDao.removeCurrency(currency, session)) {
                        return tingcoUtils;
                    } else {
                        tingcoUtils.getMsgStatus().setResponseCode(-1);
                        tingcoUtils.getMsgStatus().setResponseText("Error Occured while Deleting Currency");
                        return tingcoUtils;
                    }
                } finally {
                    session.close();
                }
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("CurrencyID Not Found");
                return tingcoUtils;
            }
        } else {
            tingcoUtils.getMsgStatus().setResponseCode(-3);
            tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoUtils;
        }
    }

    private TingcoUtils addCurrency(String currencyIsocode, String currencyIsocharCode, String currencyName, String currencyDescription) {
        TingcoUtils tingcoUtils = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }

        if (request.getAttribute("USERSESSION") != null) {
            if (currencyIsocode == null) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("CurrencyIsocode is should not be empty");
                return tingcoUtils;
            } else if (currencyIsocharCode == null) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("CurrencyIsocharCode is should not be empty");
                return tingcoUtils;
            } else if (currencyName == null) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("CurrencyName is should not be empty");
                return tingcoUtils;
            } else if (currencyDescription == null) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("CurrencyDescription should not be empty");
                return tingcoUtils;
            }
            UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
            Currency currency = new Currency();
            currency.setCurrencyIsocode(currencyIsocode);
            currency.setCurrencyIsocharCode(currencyIsocharCode);
            currency.setCurrencyName(currencyName);
            currency.setCurrencyDescription(currencyDescription);
            currency.setUserId(userSessions2.getUserId());
            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            currency.setCreatedDate(gc.getTime());
            currency.setUpdatedDate(gc.getTime());
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                if (restUtilDao.saveCurrencies(currency, session)) {
                    return tingcoUtils;
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("Error Occured while Saving DeviceTypes");
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

    private TingcoUtils getCurrency(String currencyID) {
        TingcoUtils tingcoUtil = null;
        try {
            tingcoUtil = tuXML.buildTingcoUtilsTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        if (request.getAttribute("USERSESSION") != null) {
            if (currencyID == null) {
                tingcoUtil.getMsgStatus().setResponseCode(-1);
                tingcoUtil.getMsgStatus().setResponseText("CurrencyID is should not be empty");
                return tingcoUtil;
            }

            Session session = HibernateUtil.getSessionFactory().openSession();
            Currency currency = restUtilDao.getCurrencyById(currencyID, session);
            try {
                if (currency != null) {
                    ArrayList<Currency> currencyList = new ArrayList<Currency>();
                    currencyList.add(currency);
                    Users2 user2 = userDAO.getUserById(String.valueOf(currency.getUserId()), session);
                    HashMap<String, Users2> userMap = new HashMap<String, Users2>();
                    userMap.put(currency.getUserId(), user2);
                    tingcoUtil = tuXML.buildCurrency(tingcoUtil, currencyList, userMap);
                } else {
                    tingcoUtil.getMsgStatus().setResponseCode(-1);
                    tingcoUtil.getMsgStatus().setResponseText("No Currency Found with Given CurrencyID");
                }
            } catch (Exception e) {
                TCMUtil.exceptionLog(getClass().getName(), e);
                tingcoUtil.getMsgStatus().setResponseCode(-1);
                tingcoUtil.getMsgStatus().setResponseText("Error Occured");
            } finally {
                session.close();
            }
            return tingcoUtil;

        } else {
            tingcoUtil.getMsgStatus().setResponseCode(-3);
            tingcoUtil.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoUtil;
        }
    }

    private TingcoUtils updateDeviceType(String currencyID, String currencyIsoCode, String currencyIsoCharCode, String currencyName, String currencyDescription) {
        TingcoUtils tingcoUtils = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        if (request.getAttribute("USERSESSION") != null) {
            if (currencyID == null) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("CurrencyID is should not be empty");
                return tingcoUtils;
            }

            if (currencyIsoCode.equals("")) {
                currencyIsoCode = null;
            } else {
                currencyIsoCode = currencyIsoCode.split("/")[2];
            }

            if (currencyIsoCharCode.equals("")) {
                currencyIsoCharCode = null;
            } else {
                currencyIsoCharCode = currencyIsoCharCode.split("/")[2];
            }

            if (currencyName.equals("")) {
                currencyName = null;
            } else {
                currencyName = currencyName.split("/")[2];
            }

            if (currencyDescription.equals("")) {
                currencyDescription = null;
            } else {
                currencyDescription = currencyDescription.split("/")[2];
            }

            if (currencyIsoCode == null && currencyIsoCharCode == null && currencyName == null && currencyDescription == null) {
                return tingcoUtils;
            }

            Session session = HibernateUtil.getSessionFactory().openSession();
            Currency currency = restUtilDao.getCurrencyById(currencyID, session);
            try {
                if (currency != null) {
                    UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                    currency.setUserId(userSessions2.getUserId());
                    if (currencyIsoCode != null) {
                        currency.setCurrencyIsocode(currencyIsoCode);
                    }
                    if (currencyIsoCharCode != null) {
                        currency.setCurrencyIsocharCode(currencyIsoCharCode);
                    }

                    if (currencyName != null) {
                        currency.setCurrencyName(currencyName);
                    }
                    if (currencyDescription != null) {
                        currency.setCurrencyDescription(currencyDescription);
                    }

                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    currency.setUpdatedDate(gc.getTime());

                    if (restUtilDao.saveCurrencies(currency, session)) {
                        return tingcoUtils;
                    } else {
                        tingcoUtils.getMsgStatus().setResponseCode(-1);
                        tingcoUtils.getMsgStatus().setResponseText("Error Occured while Updating Currency");
                        return tingcoUtils;

                    }
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("Currency Not Found");
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

    private TingcoUtils getAllCurrencies() {
        TingcoUtils tingcoUtil = null;
        try {
            tingcoUtil = tuXML.buildTingcoUtilsTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        if (request.getAttribute("USERSESSION") != null) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                List<Currency> currencyList = restUtilDao.getAllCurrency(session);
                if (currencyList != null) {
                    Set<Integer> currencySet = new HashSet<Integer>();
                    HashMap<String, Users2> userMap = new HashMap<String, Users2>();
                    for (Currency currency : currencyList) {
                        if (!userMap.containsKey(currency.getUserId())) {
                            Users2 user2 = userDAO.getUserById(currency.getUserId(), session);
                            userMap.put(currency.getUserId(), user2);
                        }
                        currencySet.add(currency.getCurrencyId());
                    }
                    List<Integer> cList = new ArrayList<Integer>(currencySet);
                    CountryDAO countryDAO = new CountryDAO();
                    List<Country> countryList = countryDAO.getCountryByIdList(session, cList);
                    tingcoUtil = tuXML.buildCurrency(tingcoUtil, currencyList, userMap, countryList);
                    return tingcoUtil;
                } else {
                    tingcoUtil.getMsgStatus().setResponseCode(-1);
                    tingcoUtil.getMsgStatus().setResponseText("Error Occured while Loading the DeviceTypes");
                    return tingcoUtil;
                }
            } finally {
                session.close();
            }
        } else {
            tingcoUtil.getMsgStatus().setResponseCode(-3);
            tingcoUtil.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoUtil;
        }
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public CurrencyResource getCurrency_AddResource() {
        return new CurrencyResource();
    }
}

