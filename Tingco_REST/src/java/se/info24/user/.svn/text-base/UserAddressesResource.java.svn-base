/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.user;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import se.info24.pojo.AddressTypeTranslations;
import se.info24.pojo.Addresses;
import se.info24.pojo.Country;
import se.info24.pojo.ObjectAddresses;
import se.info24.pojo.UserAddresses;
import se.info24.pojo.UserSessions2;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/useraddress")
public class UserAddressesResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    UserDAO userDao = new UserDAO();
    TingcoUsers tingcoUsers = new TingcoUsers();
    TingcoUserXML tingcoUsersXML = new TingcoUserXML();
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of UserAddressesResource */
    public UserAddressesResource() {
    }

    /**
     * GetAddressDetails
     * @return
     */
    @GET
    @Path("/get/rest/{rest}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetAddressDetails", desc = "Get the Address Information", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml() {
        return userAddressDetails();
    }

    /**
     * GetAddressDetails
     * @return
     */
    @GET
    @Path("/get/json/{json}")
    @Produces("application/json")
    @RESTDoc(methodName = "GetAddressDetails", desc = "Get the Address Information", req_Params = {"json;string"}, opt_Params = {}, method_formats = {"JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getJson() {
        return userAddressDetails();
    }

    /**
     * InsertUserAddress
     * @param content
     * @param userId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/add/rest/{rest}/userid/{userid}")
    @RESTDoc(methodName = "InsertUserAddress", desc = "Add New User Address", req_Params = {"UserID;UUID"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoUsers getXml_Add(String content, @PathParam("userid") String userId) throws DatatypeConfigurationException {
        return insertUserAddress(content, userId);
    }

    /**
     * InsertUserAddress
     * @param content
     * @param userId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/add/json/{json}/userid/{userid}")
    public TingcoUsers getJson_Add(String content, @PathParam("userid") String userId) throws DatatypeConfigurationException {
        return insertUserAddress(content, userId);
    }

    /**
     * UpdateUserAddress
     * @param content
     * @param addressId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/update/rest/{rest}/addressid/{addressid}")
    @RESTDoc(methodName = "UpdateUserAddress", desc = "Update Users Address Details", req_Params = {"AddressID;UUID"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoUsers postXml_Update(String content, @PathParam("addressid") String addressId) throws DatatypeConfigurationException {
        return updateUserAddresses(content, addressId);
    }

    @GET
    @Path("/getaddresstypes/rest/{rest}/countryid/{countryid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetAddressTypes", desc = "Get the Address Types Information", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_GetAddressTypes(@PathParam("countryid") String countryId) {
        return getAddressTypes(countryId);
    }

    @GET
    @Path("/getaddresstypes/json/{json}/countryid/{countryid}")
    @Produces("application/json")
    @RESTDoc(methodName = "GetAddressTypes", desc = "Get the Address Types Information", req_Params = {"countryid;int"}, opt_Params = {}, method_formats = {"JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getJson_GetAddressTypes(@PathParam("countryid") String countryId) {
        return getAddressTypes(countryId);
    }

    @GET
    @Path("/getaddressforobject/rest/{rest}/objectid/{objectid}/addresstypeid/{addresstypeid}/objecttype/{objecttype}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetAddressForObject", desc = "Get Address For Object", req_Params = {"objecttype;UUID"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_GetAddressForObject(@PathParam("objectid") String objectId, @PathParam("addresstypeid") String addressTypeId, @PathParam("objecttype") String objectType) {
        return getAddressForObject(objectId, addressTypeId, objectType);
    }

    @GET
    @Path("/getaddressforobject/json/{json}/objectid/{objectid}/addresstypeid/{addresstypeid}/objecttype/{objecttype}")
    @Produces("application/json")
    @RESTDoc(methodName = "GetAddressForObject", desc = "Get Address For Object", req_Params = {"objecttype;UUID"}, opt_Params = {}, method_formats = {"JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getJson_GetAddressForObject(@PathParam("objectid") String objectId, @PathParam("addresstypeid") String addressTypeId, @PathParam("objecttype") String objectType) {
        return getAddressForObject(objectId, addressTypeId, objectType);
    }

    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updateaddressforobject/rest/{rest}/objecttype/{objecttype}")
    @RESTDoc(methodName = "UpdateAddressForObject", desc = "UpdateAddressForObject", req_Params = {"objecttype;UUID"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoUsers postXml_UpdateAddressForObject(String content, @PathParam("objecttype") String objecttype) throws DatatypeConfigurationException {
        return updateAddressForObject(content, objecttype);
    }

    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updateaddressforobject/json/{json}/objecttype/{objecttype}")
    @RESTDoc(methodName = "UpdateAddressForObject", desc = "UpdateAddressForObject", req_Params = {"objecttype;UUID"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoUsers postJson_UpdateAddressForObject(String content, @PathParam("objecttype") String objecttype) throws DatatypeConfigurationException {
        return updateAddressForObject(content, objecttype);
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public UserAddressResource getUserAddressResource() {
        return new UserAddressResource();
    }

    public TingcoUsers updateAddressForObject(String content, String objectType) {
        UserManager manager = new UserManager();
        TingcoUsers user = manager.buildUserTemplate();
        boolean hasPermission = false;
        TingcoUsers tUser = (TingcoUsers) JAXBManager.getInstance().unMarshall(content, TingcoUsers.class);
        long requestedTime = System.currentTimeMillis();
        try {
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(objectType.toUpperCase())) {
                    ArrayList<String> operations = ht.get(objectType.toUpperCase());
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    try {
                        if (userDao.updateUserAddressesForObject(tUser.getUsers().getUser().get(0), tUser.getUsers().getUser().get(0).getAddress().getAddressID(), session, sessions2.getUserId())) {
                            return user;
                        } else {
                            user.getMsgStatus().setResponseCode(-1);
                            user.getMsgStatus().setResponseText("Error Occured while Updating UserAddress");
                            return user;
                        }
                    } finally {
                        session.close();
                        delayLog(requestedTime);
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                user.getMsgStatus().setResponseCode(-3);
                user.getMsgStatus().setResponseText("User Session is Not Valid");
                return user;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        }
        return user;
    }

    public TingcoUsers getAddressForObject(String objectId, String addressTypeId, String objectType) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoUsers = tingcoUsersXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (objectId.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("objectId cannot be empty");
                    return tingcoUsers;
                }
                if (addressTypeId.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("addressTypeId cannot be empty");
                    return tingcoUsers;
                }
                if (objectType.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("objectType cannot be empty");
                    return tingcoUsers;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(objectType.toUpperCase())) {
                    ArrayList<String> operations = ht.get(objectType.toUpperCase());
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ObjectAddresses objectAddress = userDao.getObjectAddressesByID(session, objectId, addressTypeId);
                    if (objectAddress == null) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("No Data Found");
                        return tingcoUsers;
                    } else {
                        Addresses address = (Addresses) userDao.getAddress(objectAddress.getAddressId(), session);
                        if (address == null) {
                            tingcoUsers.getMsgStatus().setResponseCode(-1);
                            tingcoUsers.getMsgStatus().setResponseText("No Address Data Found");
                            return tingcoUsers;
                        } else {
                            se.info24.usersjaxb.Addresses addresses = new se.info24.usersjaxb.Addresses();
                            se.info24.usersjaxb.Address addressJaxb = new se.info24.usersjaxb.Address();
                            Country country = address.getCountry();
                            addressJaxb.setAddressID(address.getAddressId());
                            addressJaxb.setCountryID(country.getCountryId());
                            if (address.getAddressStreet() != null && address.getAddressStreet().length() > 0) {
                                addressJaxb.setAddressStreet(address.getAddressStreet());
                            }
                            if (address.getAddressStreet2() != null && address.getAddressStreet2().length() > 0) {
                                addressJaxb.setAddressStreet2(address.getAddressStreet2());
                            }
                            if (address.getAddressSuite() != null && address.getAddressSuite().length() > 0) {
                                addressJaxb.setAddressSuite(address.getAddressSuite());
                            }
                            if (address.getAddressZip() != null && address.getAddressZip().length() > 0) {
                                addressJaxb.setAddressZip(address.getAddressZip());
                            }
                            if (address.getAddressCity() != null && address.getAddressCity().length() > 0) {
                                addressJaxb.setAddressCity(address.getAddressCity());
                            }
                            if (address.getAddressRegion() != null && address.getAddressRegion().length() > 0) {
                                addressJaxb.setAddressRegion(address.getAddressRegion());
                            }
                            if (address.getAddressDescription() != null && address.getAddressDescription().length() > 0) {
                                addressJaxb.setAddressDesc(address.getAddressDescription());
                            }
                            if (address.getAddressPhone() != null && address.getAddressPhone().length() > 0) {
                                addressJaxb.setAddressPhone(address.getAddressPhone());
                            }
                            if (address.getAddressMobile() != null && address.getAddressMobile().length() > 0) {
                                addressJaxb.setAddressMobile(address.getAddressMobile());
                            }
                            if (address.getAddressFax() != null && address.getAddressFax().length() > 0) {
                                addressJaxb.setAddressFax(address.getAddressFax());
                            }
                            if (address.getAddressEmail() != null && address.getAddressEmail().length() > 0) {
                                addressJaxb.setAddressEmail(address.getAddressEmail());
                            }
                            if (address.getAddressLatitude() != null) {
                                addressJaxb.setAddressLatitude(address.getAddressLatitude().floatValue());
                            }
                            if (address.getAddressLongitude() != null) {
                                addressJaxb.setAddressLongitude(address.getAddressLongitude().floatValue());
                            }
                            if (address.getAddressDepth() != null) {
                                addressJaxb.setAddressDepth(address.getAddressDepth().floatValue());
                            }
                            se.info24.usersjaxb.Country countryJaxb = new se.info24.usersjaxb.Country();
                            countryJaxb.setCountryID(country.getCountryId());
                            countryJaxb.setCountryName(country.getCountryName());
                            addressJaxb.getCountry().add(countryJaxb);
                            addresses.getAddress().add(addressJaxb);
                            tingcoUsers.setAddresses(addresses);
                        }
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured While Reading Data");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    public TingcoUsers getAddressTypes(String countryId) {
        boolean hasPermission = false;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoUsers = tingcoUsersXML.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (countryId.equals("")) {
                    tingcoUsers.getMsgStatus().setResponseCode(-1);
                    tingcoUsers.getMsgStatus().setResponseText("countryid cannot be empty");
                    return tingcoUsers;
                }
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
                    List<AddressTypeTranslations> list = userDao.getAddressTypeTranslationsByCountryId(session, countryId);
                    if (list.isEmpty()) {
                        tingcoUsers.getMsgStatus().setResponseCode(-1);
                        tingcoUsers.getMsgStatus().setResponseText("No Data Found");
                        return tingcoUsers;
                    } else {
                        se.info24.usersjaxb.Addresses addresses = new se.info24.usersjaxb.Addresses();
                        se.info24.usersjaxb.Address address = new se.info24.usersjaxb.Address();
                        se.info24.usersjaxb.AddressTypeTranslations transJaxb = null;
                        for (AddressTypeTranslations trans : list) {
                            transJaxb = new se.info24.usersjaxb.AddressTypeTranslations();
                            transJaxb.setAddressTypeID(trans.getId().getAddressTypeId());
                            transJaxb.setAddressTypeName(trans.getAddressTypeName());
                            transJaxb.setCountryID(trans.getCountry().getCountryId());
                            address.getAddressTypeTranslations().add(transJaxb);
                        }
                        addresses.getAddress().add(address);
                        tingcoUsers.setAddresses(addresses);
                    }
                } else {
                    tingcoUsers.getMsgStatus().setResponseCode(-10);
                    tingcoUsers.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUsers;
                }
            } else {
                tingcoUsers.getMsgStatus().setResponseCode(-3);
                tingcoUsers.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUsers;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUsers.getMsgStatus().setResponseCode(-1);
            tingcoUsers.getMsgStatus().setResponseText("Error Occured While Reading Data");
            return tingcoUsers;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUsers;
    }

    private TingcoUsers userAddressDetails() {
        UserManager manager = new UserManager();
        TingcoUsers user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        long requestedTime = System.currentTimeMillis();
        try {

            user = manager.buildUserTemplate();
            UserSessions2 sess = (UserSessions2) request.getAttribute("USERSESSION");
            if (sess != null) {
                TingcoUserXML tUser = new TingcoUserXML();
                List<UserAddresses> addresses = userDao.getUserAddressDetails(sess.getUserId(), session);
                tUser.buildUserAddressDetails(addresses, user);

            }

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            user.getMsgStatus().setResponseCode(-1);
            user.getMsgStatus().setResponseText("Error");
            return user;
        } finally {
            session.close();
            delayLog(requestedTime);
        }

        return user;
    }

    private TingcoUsers insertUserAddress(String content, String userId) throws DatatypeConfigurationException {
        UserManager manager = new UserManager();
        TingcoUsers user = manager.buildUserTemplate();
        long requestedTime = System.currentTimeMillis();
        TingcoUsers tUser = (TingcoUsers) JAXBManager.getInstance().unMarshall(content, TingcoUsers.class);
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                String addressId = UUID.randomUUID().toString();
                if (userDao.insertUserAddress(tUser.getUsers().getUser().get(0), addressId, userId, session)) {
                    return user;
                } else {
                    user.getMsgStatus().setResponseCode(-1);
                    user.getMsgStatus().setResponseText("Error Occured while Inserting UserAddress");
                    return user;
                }
            } finally {
                session.close();
                delayLog(requestedTime);
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        }
        return user;
    }

    private TingcoUsers updateUserAddresses(String content, String addressId) throws DatatypeConfigurationException {
        UserManager manager = new UserManager();
        TingcoUsers user = manager.buildUserTemplate();
        long requestedTime = System.currentTimeMillis();
        TingcoUsers tUser = (TingcoUsers) JAXBManager.getInstance().unMarshall(content, TingcoUsers.class);
        try {
            if (request.getAttribute("USERSESSION") != null) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                try {
                    if (userDao.updateUserAddresses(tUser.getUsers().getUser().get(0), addressId, session)) {
                        return user;
                    } else {
                        user.getMsgStatus().setResponseCode(-1);
                        user.getMsgStatus().setResponseText("Error Occured while Updating UserAddress");
                        return user;
                    }
                } finally {
                    session.close();
                    delayLog(requestedTime);
                }
            } else {
                user.getMsgStatus().setResponseCode(-3);
                user.getMsgStatus().setResponseText("User Session is Not Valid");
                return user;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        }
        return user;
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}
