/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.service;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
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
import se.info24.jaxbUtil.TingcoServiceXML;
import se.info24.pojo.ServiceTypes;
import se.info24.pojo.ServiceTypesInGroups;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.Users2;
import se.info24.servicejaxb.TingcoService;
import se.info24.user.UserDAO;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Sekhar
 */
@Path("/servicetypes")
public class ServiceTypesResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    ServiceDAO serviceDAO = new ServiceDAO();
    TingcoServiceXML tingcoServiceXML = new TingcoServiceXML();
    UserDAO userDAO = new UserDAO();

    /** Creates a new instance of ServiceTypesResource */
    public ServiceTypesResource() {
    }

    /**
     * ServiceTypes_Add
     * @param serviceTypeName
     * @param serviceTypeDesc
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/servicetypename/{servicetypename}{servicetypedesc:(/servicetypedesc/[^/]+?)?}")
    @RESTDoc(methodName = "ServiceTypes_Add", desc = "Used to create a new ServiceType", req_Params = {"ServiceTypeName;String"}, opt_Params = {"ServiceTypeDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoService getXml_Add(@PathParam("servicetypename") String serviceTypeName, @PathParam("servicetypedesc") String serviceTypeDesc) {
        return createServiceType(serviceTypeName, serviceTypeDesc);
    }

    /**
     * ServiceTypes_Add
     * @param serviceTypeName
     * @param serviceTypeDesc
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/servicetypename/{servicetypename}{servicetypedesc:(/servicetypedesc/[^/]+?)?}")
    public TingcoService getJson_Add(@PathParam("servicetypename") String serviceTypeName, @PathParam("servicetypedesc") String serviceTypeDesc) {
        return createServiceType(serviceTypeName, serviceTypeDesc);
    }

    /**
     * ServiceTypes_Add
     * @param serviceTypeName
     * @param serviceTypeDesc
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/add/rest/{rest}/servicetypename/{servicetypename}{servicetypedesc:(/servicetypedesc/[^/]+?)?}")
    public TingcoService postXml_Add(@PathParam("servicetypename") String serviceTypeName, @PathParam("servicetypedesc") String serviceTypeDesc) {
        return createServiceType(serviceTypeName, serviceTypeDesc);
    }

    /**
     * ServiceTypes_Add
     * @param serviceTypeName
     * @param serviceTypeDesc
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/add/json/{json}/servicetypename/{servicetypename}{servicetypedesc:(/servicetypedesc/[^/]+?)?}")
    public TingcoService postJson_Add(@PathParam("servicetypename") String serviceTypeName, @PathParam("servicetypedesc") String serviceTypeDesc) {
        return createServiceType(serviceTypeName, serviceTypeDesc);
    }

    /**
     * ServiceTypes_Delete
     * @param serviceTypeID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/servicetypeid/{servicetypeid}")
    @RESTDoc(methodName = "ServiceTypes_Delete", desc = "To Delete a ServiceTypes", req_Params = {"ServiceTypeID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoService getXml_Delete(@PathParam("servicetypeid") String serviceTypeID) {
        return deleteServiceType(serviceTypeID);
    }

    /**
     * ServiceTypes_Delete
     * @param serviceTypeID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/servicetypeid/{servicetypeid}")
    public TingcoService getJson_Delete(@PathParam("servicetypeid") String serviceTypeID) {
        return deleteServiceType(serviceTypeID);
    }

    /**
     * ServiceTypes_Delete
     * @param serviceTypeID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/servicetypeid/{servicetypeid}")
    public TingcoService postXml_Delete(@PathParam("servicetypeid") String serviceTypeID) {
        return deleteServiceType(serviceTypeID);
    }

    /**
     * ServiceTypes_Delete
     * @param serviceTypeID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/delete/json/{json}/servicetypeid/{servicetypeid}")
    public TingcoService postJson_Delete(@PathParam("servicetypeid") String serviceTypeID) {
        return deleteServiceType(serviceTypeID);
    }

    /**
     * ServiceTypes_GetInfo
     * @param serviceTypeID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/servicetypeid/{servicetypeid}")
    @RESTDoc(methodName = "ServiceTypes_GetInfo", desc = "To Get a ServiceTypes", req_Params = {"ServiceTypeID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoService getXml(@PathParam("servicetypeid") String serviceTypeID) {
        return getServiceType(serviceTypeID);
    }

    /**
     * ServiceTypes_GetInfo
     * @param serviceTypeID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/servicetypeid/{servicetypeid}")
    public TingcoService getJson(@PathParam("servicetypeid") String serviceTypeID) {
        return getServiceType(serviceTypeID);
    }

    /**
     * ServiceTypes_GetInfo
     * @param serviceTypeID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/get/rest/{rest}/servicetypeid/{servicetypeid}")
    public TingcoService postXml(@PathParam("servicetypeid") String serviceTypeID) {
        return getServiceType(serviceTypeID);
    }

    /**
     * ServiceTypes_GetInfo
     * @param serviceTypeID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/get/json/{json}/servicetypeid/{servicetypeid}")
    public TingcoService postJson(@PathParam("servicetypeid") String serviceTypeID) {
        return getServiceType(serviceTypeID);
    }

    /**
     * ServiceTypes_Update
     * @param serviceTypeID
     * @param serviceTypeName
     * @param serviceTypeDesc
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/update/rest/{rest}/servicetypeid/{servicetypeid}{servicetypename:(/servicetypename/[^/]+?)?}{servicetypedesc:(/servicetypedesc/[^/]+?)?}")
    @RESTDoc(methodName = "ServiceTypes_Update", desc = "To Update ServiceTypes", req_Params = {"ServiceTypeID;UUID"}, opt_Params = {"ServiceTypeName;String", "ServiceTypeDesc;String"}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoService getXml_Update(@PathParam("servicetypeid") String serviceTypeID, @PathParam("servicetypename") String serviceTypeName, @PathParam("servicetypedesc") String serviceTypeDesc) {
        return updateServiceType(serviceTypeID, serviceTypeName, serviceTypeDesc);
    }

    /**
     * ServiceTypes_Update
     * @param serviceTypeID
     * @param serviceTypeName
     * @param serviceTypeDesc
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/update/json/{json}/servicetypeid/{servicetypeid}{servicetypename:(/servicetypename/[^/]+?)?}{servicetypedesc:(/servicetypedesc/[^/]+?)?}")
    public TingcoService getJson_Update(@PathParam("servicetypeid") String serviceTypeID, @PathParam("servicetypename") String serviceTypeName, @PathParam("servicetypedesc") String serviceTypeDesc) {
        return updateServiceType(serviceTypeID, serviceTypeName, serviceTypeDesc);
    }

    /**
     * ServiceTypes_Update
     * @param serviceTypeID
     * @param serviceTypeName
     * @param serviceTypeDesc
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/update/rest/{rest}/servicetypeid/{servicetypeid}{servicetypename:(/servicetypename/[^/]+?)?}{servicetypedesc:(/servicetypedesc/[^/]+?)?}")
    public TingcoService postXml_Update(@PathParam("servicetypeid") String serviceTypeID, @PathParam("servicetypename") String serviceTypeName, @PathParam("servicetypedesc") String serviceTypeDesc) {
        return updateServiceType(serviceTypeID, serviceTypeName, serviceTypeDesc);
    }

    /**
     * ServiceTypes_Update
     * @param serviceTypeID
     * @param serviceTypeName
     * @param serviceTypeDesc
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/update/json/{json}/servicetypeid/{servicetypeid}{servicetypename:(/servicetypename/[^/]+?)?}{servicetypedesc:(/servicetypedesc/[^/]+?)?}")
    public TingcoService postJson_Update(@PathParam("servicetypeid") String serviceTypeID, @PathParam("servicetypename") String serviceTypeName, @PathParam("servicetypedesc") String serviceTypeDesc) {
        return updateServiceType(serviceTypeID, serviceTypeName, serviceTypeDesc);
    }

    /**
     * GetAllServiceTypes
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/rest/{rest}{servicetyepename:(/servicetyepename/[^/]+?)?}")
    @RESTDoc(methodName = "GetAllServiceTypes", desc = "To Get All ServiceTypes", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoService getXml_All(@PathParam("servicetyepename") String serviceTypeName) {
        return getAllServiceTypes(serviceTypeName);
    }

    /**
     * GetAllServiceTypes
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/json/{json}{servicetyepename:(/servicetyepename/[^/]+?)?}")
    public TingcoService getJson_All(@PathParam("servicetyepename") String serviceTypeName) {
        return getAllServiceTypes(serviceTypeName);
    }

    /**
     * GetAllServiceTypes
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/rest/{rest}{servicetyepename:(/servicetyepename/[^/]+?)?}")
    public TingcoService postXml_All(@PathParam("servicetyepename") String serviceTypeName) {
        return getAllServiceTypes(serviceTypeName);
    }

    /**
     * GetAllServiceTypes
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/json/{json}{servicetyepename:(/servicetyepename/[^/]+?)?}")
    public TingcoService postJson_All(@PathParam("servicetyepename") String serviceTypeName) {
        return getAllServiceTypes(serviceTypeName);
    }

    /**
     * ServiceTypesByGroupId
     * @param groupID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/get/rest/{rest}/groupid/{groupid}")
    @RESTDoc(methodName = "ServiceTypesByGroupId", desc = "To Get a ServiceTypesName and ServiceId", req_Params = {"ServiceTypeID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoService getXmlServiceTypesByGroupId(@PathParam("groupid") String groupID) {
        return getServiceTypeByGroupId(groupID);
    }

    /**
     * ServiceTypesByGroupId
     * @param groupID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/get/json/{json}/groupid/{groupid}")
    @RESTDoc(methodName = "ServiceTypesByGroupId", desc = "To Get a ServiceTypesName and ServiceId", req_Params = {"ServiceTypeID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoService getjsonServiceTypesByGroupId(@PathParam("groupid") String groupID) {
        return getServiceTypeByGroupId(groupID);
    }

    private TingcoService createServiceType(String serviceTypeName, String serviceTypeDesc) {
        TingcoService tingcoService = null;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoService = tingcoServiceXML.buildTingcoServiceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (serviceTypeName == null) {
                    tingcoService.getMsgStatus().setResponseCode(-1);
                    tingcoService.getMsgStatus().setResponseText("ServiceTypeName is should not be empty");
                    return tingcoService;
                }

                if (serviceTypeDesc.equals("")) {
                    serviceTypeDesc = null;
                } else {
                    serviceTypeDesc = serviceTypeDesc.split("/")[2];
                }

                UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                session = HibernateUtil.getSessionFactory().openSession();

                GregorianCalendar gc = new GregorianCalendar();
                ServiceTypes st = new ServiceTypes();
                st.setServiceTypeId(UUID.randomUUID().toString());
                st.setServiceTypeName(serviceTypeName);
                if (serviceTypeDesc != null) {
                    st.setServiceTypeDescription(serviceTypeDesc);
                }
                st.setUserId(userSessions2.getUserId());
                st.setCreatedDate(gc.getTime());
                st.setUpdatedDate(gc.getTime());

                if (serviceDAO.saveServiceTypes(st, session)) {
                    return tingcoService;
                } else {
                    tingcoService.getMsgStatus().setResponseCode(-1);
                    tingcoService.getMsgStatus().setResponseText("Eroor Occured While Saving the ServiceTypes");
                    return tingcoService;
                }


            } else {
                tingcoService.getMsgStatus().setResponseCode(-3);
                tingcoService.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoService;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoService.getMsgStatus().setResponseCode(-1);
            tingcoService.getMsgStatus().setResponseText("Error Occured While Saving ServiceTypes");
            return tingcoService;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoService deleteServiceType(String serviceTypeID) {
        TingcoService tingcoService = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoService = tingcoServiceXML.buildTingcoServiceTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }

        if (request.getAttribute("USERSESSION") != null) {
            if (serviceTypeID == null) {
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("servicetypeid is should not be empty");
                return tingcoService;
            }

            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                ServiceTypes serviceTypes = serviceDAO.getServiceTypesById(serviceTypeID, session);
                if (serviceTypes != null) {
                    if (serviceDAO.deleteServiceTypes(serviceTypes, session)) {
                        return tingcoService;
                    } else {
                        tingcoService.getMsgStatus().setResponseCode(-1);
                        tingcoService.getMsgStatus().setResponseText("Eroor Occured While Deleting the ServiceTypes");
                        return tingcoService;
                    }

                } else {
                    tingcoService.getMsgStatus().setResponseCode(-1);
                    tingcoService.getMsgStatus().setResponseText("ServiceTypeID Not Found");
                    return tingcoService;
                }

            } catch (Exception e) {
                TCMUtil.exceptionLog(getClass().getName(), e);
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("Eroor Occured While Deleting the ServiceTypes");
                return tingcoService;
            } finally {
                session.close();
                delayLog(requestedTime);
            }

        } else {
            tingcoService.getMsgStatus().setResponseCode(-3);
            tingcoService.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoService;
        }

    }

    private TingcoService getServiceType(String serviceTypeID) {
        TingcoService tingcoService = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoService = tingcoServiceXML.buildTingcoServiceTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }

        if (request.getAttribute("USERSESSION") != null) {
            if (serviceTypeID == null) {
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("ServiceTypeID is should not be empty");
                return tingcoService;
            }

            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                ServiceTypes serviceTypes = serviceDAO.getServiceTypesById(serviceTypeID, session);
                if (serviceTypes != null) {
                    ArrayList<ServiceTypes> typesList = new ArrayList<ServiceTypes>();
                    typesList.add(serviceTypes);
                    Users2 user2 = userDAO.getUserById(serviceTypes.getUserId(), session);
                    HashMap<String, Users2> userMap = new HashMap<String, Users2>();
                    userMap.put(serviceTypes.getUserId(), user2);
                    tingcoService =
                            tingcoServiceXML.buildServiceTypes(tingcoService, typesList, userMap);
                    return tingcoService;
                } else {
                    tingcoService.getMsgStatus().setResponseCode(-1);
                    tingcoService.getMsgStatus().setResponseText("ServiceTypeID Not Found");
                    return tingcoService;
                }

            } catch (Exception e) {
                TCMUtil.exceptionLog(getClass().getName(), e);
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("Eroor Occured While Loading the ServiceType");
                return tingcoService;
            } finally {
                session.close();
                delayLog(requestedTime);
            }

        } else {
            tingcoService.getMsgStatus().setResponseCode(-3);
            tingcoService.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoService;
        }

    }

    private TingcoService updateServiceType(String serviceTypeID, String serviceTypeName, String serviceTypeDesc) {
        TingcoService tingcoService = null;
        try {
            tingcoService = tingcoServiceXML.buildTingcoServiceTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        long requestedTime = System.currentTimeMillis();

        if (request.getAttribute("USERSESSION") != null) {
            if (serviceTypeID == null) {
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("ServiceTypeID is should not be empty");
                return tingcoService;
            }

            if (serviceTypeName.equals("")) {
                serviceTypeName = null;
            } else {
                serviceTypeName = serviceTypeName.split("/")[2];
            }

            if (serviceTypeDesc.equals("")) {
                serviceTypeDesc = null;
            } else {
                serviceTypeDesc = serviceTypeDesc.split("/")[2];
            }

            if (serviceTypeName == null && serviceTypeDesc == null) {
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("Alleast One Value Should Present");
                return tingcoService;
            }

            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                ServiceTypes serviceTypes = serviceDAO.getServiceTypesById(serviceTypeID, session);
                if (serviceTypes != null) {
                    UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    if (serviceTypeName != null) {
                        serviceTypes.setServiceTypeName(serviceTypeName);
                    }

                    if (serviceTypeDesc != null) {
                        serviceTypes.setServiceTypeDescription(serviceTypeDesc);
                    }

                    serviceTypes.setUserId(userSessions2.getUserId());
                    serviceTypes.setUpdatedDate(gc.getTime());
                    if (serviceDAO.saveServiceTypes(serviceTypes, session)) {
                        return tingcoService;
                    } else {
                        tingcoService.getMsgStatus().setResponseCode(-1);
                        tingcoService.getMsgStatus().setResponseText("Eroor Occured While Updating the ServiceTypes");
                        return tingcoService;
                    }

                } else {
                    tingcoService.getMsgStatus().setResponseCode(-1);
                    tingcoService.getMsgStatus().setResponseText("ServiceTypeID Not Found");
                    return tingcoService;
                }

            } catch (Exception e) {
                TCMUtil.exceptionLog(getClass().getName(), e);
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("Eroor Occured While Updating the ServiceTypes");
                return tingcoService;
            } finally {
                session.close();
                delayLog(requestedTime);
            }

        } else {
            tingcoService.getMsgStatus().setResponseCode(-3);
            tingcoService.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoService;
        }

    }

    private TingcoService getAllServiceTypes(String serviceTypeName) {
        TingcoService tingcoService = null;

        if (!serviceTypeName.equals("")) {
            serviceTypeName = serviceTypeName.split("/")[2];
        } else {
            serviceTypeName = null;
        }

        try {
            tingcoService = tingcoServiceXML.buildTingcoServiceTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        long requestedTime = System.currentTimeMillis();
        if (request.getAttribute("USERSESSION") != null) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                 List<ServiceTypes> stList = new ArrayList<ServiceTypes>();
                if(serviceTypeName != null){
                    stList = serviceDAO.getAllServiceTypesBySearchCreteria(serviceTypeName, session);
                }else{
                    stList = serviceDAO.getAllServiceTypes(session);
                }
                
                if (!stList.isEmpty()) {
                    HashMap<String, Users2> userMap = new HashMap<String, Users2>();
                    for (ServiceTypes serviceTypes : stList) {
                        if (!userMap.containsKey(serviceTypes.getUserId())) {
                            Users2 user2 = userDAO.getUserById(serviceTypes.getUserId(), session);
                            userMap.put(serviceTypes.getUserId(), user2);
                        }

                    }
                    tingcoService = tingcoServiceXML.buildServiceTypes(tingcoService, stList, userMap);
                    return tingcoService;
                } else {
                    tingcoService.getMsgStatus().setResponseCode(-1);
                    tingcoService.getMsgStatus().setResponseText("Data Not Found");
                    return tingcoService;
                }

            } catch (Exception e) {
                TCMUtil.exceptionLog(getClass().getName(), e);
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("Eroor Occured While loading ServiceTypes");
                return tingcoService;
            } finally {
                session.close();
                delayLog(requestedTime);
            }

        } else {
            tingcoService.getMsgStatus().setResponseCode(-3);
            tingcoService.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoService;
        }

    }

    public TingcoService getServiceTypeByGroupId(String groupID) {
        TingcoService tingcoService = null;
        List<ServiceTypes> servicetypelist = new ArrayList<ServiceTypes>();
        try {
            tingcoService = tingcoServiceXML.buildTingcoServiceTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        long requestedTime = System.currentTimeMillis();
        if (request.getAttribute("USERSESSION") != null) {
//            Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//            if (ht.containsKey(TCMUtil.READ)) {
//                ArrayList<String> operations = ht.get(TCMUtil.READ);
//                for (int i = 0; i < operations.size(); i++) {
//                    if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
//                        hasPermission = true;
//                    }
//                }
//            }
//            if (hasPermission) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                List<ServiceTypesInGroups> serviceTypesInGroupsList = serviceDAO.getServiceTypesByGroupId(groupID, session);
                if (serviceTypesInGroupsList.size() > 0) {
                    for (ServiceTypesInGroups serviceTypesInGroups : serviceTypesInGroupsList) {
                        ServiceTypes servicetype = serviceDAO.getServiceTypesById(serviceTypesInGroups.getId().getServiceTypeId(), session);
                        servicetypelist.add(servicetype);
                    }

                    tingcoService = tingcoServiceXML.buildServiceType(tingcoService, servicetypelist);
                } else {
                    tingcoService.getMsgStatus().setResponseCode(-1);
                    tingcoService.getMsgStatus().setResponseText("GroupId not Exist");
                    return tingcoService;
                }

            } catch (Exception e) {
                TCMUtil.exceptionLog(getClass().getName(), e);
                tingcoService.getMsgStatus().setResponseCode(-1);
                tingcoService.getMsgStatus().setResponseText("Eroor Occured While loading ServiceTypes");
                return tingcoService;
            } finally {
                session.close();
                delayLog(requestedTime);
            }

//            } else {
//                tingcoService.getMsgStatus().setResponseCode(-10);
//                tingcoService.getMsgStatus().setResponseText("User Permission is not given");
//                return tingcoService;
//            }
            return tingcoService;
        } else {
            tingcoService.getMsgStatus().setResponseCode(-3);
            tingcoService.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoService;
        }

    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public ServiceTypeResource getServiceTypes_AddResource() {
        return new ServiceTypeResource();
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}
