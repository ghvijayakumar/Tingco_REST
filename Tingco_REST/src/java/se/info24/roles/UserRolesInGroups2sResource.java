/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.roles;

import java.util.GregorianCalendar;
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
import se.info24.groupsjaxb.TingcoGroups;
import se.info24.jaxbUtil.TingcoGroupXML;
import se.info24.jaxbUtil.TingcoUtilsXML;
import se.info24.pojo.UserRolesInGroups2;
import se.info24.pojo.UserRolesInGroups2Id;
import se.info24.pojo.UserSessions2;
import se.info24.user.UserManager;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;
import se.info24.utiljaxb.TingcoUtils;

/**
 * REST Web Service
 *
 * @author Sekhar
 */
@Path("/userrolesingroups2")
public class UserRolesInGroups2sResource {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;
    TingcoGroupXML tingcoGroupXML = new TingcoGroupXML();
    TingcoUtilsXML tuXML = new TingcoUtilsXML();
    RoleDAO roleDAO = new RoleDAO();
    /** Creates a new instance of UserRolesInGroups2sResource */
    public UserRolesInGroups2sResource() {
    }

    /**
     * AddRolesToOrganization
     * @param userRoleID
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/add/rest/{rest}/userroleid/{userroleid}/groupid/{groupid}")
    @RESTDoc(methodName = "AddRolesToOrganization", desc = "Mapping Role and Organization (Group)", req_Params = {"UserRoleID;UUID", "GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoGroups getXml_Add(@PathParam("userroleid") String userRoleID, @PathParam("groupid") String groupId) {
        return addRoleToOrgnization(userRoleID, groupId);
    }

    /**
     * AddRolesToOrganization
     * @param userRoleID
     * @param groupId
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/add/rest/{rest}/userroleid/{userroleid}/groupid/{groupid}")
    public TingcoGroups postXml_Add(@PathParam("userroleid") String userRoleID, @PathParam("groupid") String groupId) {
        return addRoleToOrgnization(userRoleID, groupId);
    }

    /**
     * AddRolesToOrganization
     * @param userRoleID
     * @param groupId
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/add/json/{json}/userroleid/{userroleid}/groupid/{groupid}")
    public TingcoGroups getJson_Add(@PathParam("userroleid") String userRoleID, @PathParam("groupid") String groupId) {
        return addRoleToOrgnization(userRoleID, groupId);
    }

    /**
     * AddRolesToOrganization
     * @param userRoleID
     * @param groupId
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/add/json/{json}/userroleid/{userroleid}/groupid/{groupid}")
    public TingcoGroups postJson_Add(@PathParam("userroleid") String userRoleID, @PathParam("groupid") String groupId) {
        return addRoleToOrgnization(userRoleID, groupId);
    }

    /**
     * OrganizationRole_Update
     * @param request
     * @param oldroleID
     * @param newroleID
     * @param groupID
     * @return
     */
    @GET
    @Path("/update/rest/{rest}/olduserroleid/{olduserroleid}/newuserroleid/{newuserroleid}/groupid/{groupid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "OrganizationRole_Update", desc = "Updates the Role of an Organization", req_Params = {"OldUserRoleID;UUID", "NewUserRoleID;UUID", "GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_Update(@Context HttpServletRequest request, @PathParam("olduserroleid") String oldroleID, @PathParam("newuserroleid") String newroleID,
            @PathParam("groupid") String groupID) {
        return updatedOrganizationRole(oldroleID, newroleID, groupID, request);
    }

    /**
     * OrganizationRole_Update
     * @param request
     * @param oldroleID
     * @param newroleID
     * @param groupID
     * @return
     */
    @GET
    @Path("/update/json/{json}/olduserroleid/{olduserroleid}/newuserroleid/{newuserroleid}/groupid/{groupid}")
    @Produces("application/json")
    public TingcoUsers getJson_Update(@Context HttpServletRequest request, @PathParam("olduserroleid") String oldroleID, @PathParam("newuserroleid") String newroleID,
            @PathParam("groupid") String groupID) {
        return updatedOrganizationRole(oldroleID, newroleID, groupID, request);
    }

    /**
     * IsOrganizationRoleExists
     * @param userRoleID
     * @param groupID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/isexists/rest/{rest}/userroleid/{userroleid}/groupid/{groupid}")
    @RESTDoc(methodName = "IsOrganizationRoleExists", desc = "Check the UserRoleID and GroupID Mapped or Not", req_Params = {"UserRoleID;UUID", "GroupID;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_IsExists(@PathParam("userroleid") String userRoleID, @PathParam("groupid") String groupID) {
        return isOrganizationalRolesExists(userRoleID, groupID);
    }

    /**
     * IsOrganizationRoleExists
     * @param userRoleID
     * @param groupID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/isexists/rest/{rest}/userroleid/{userroleid}/groupid/{groupid}")
    public TingcoUtils postXml_IsExists(@PathParam("userroleid") String userRoleID, @PathParam("groupid") String groupID) {
        return isOrganizationalRolesExists(userRoleID, groupID);
    }

    /**
     * IsOrganizationRoleExists
     * @param userRoleID
     * @param groupID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/isexists/json/{json}/userroleid/{userroleid}/groupid/{groupid}")
    public TingcoUtils getJson_IsExists(@PathParam("userroleid") String userRoleID, @PathParam("groupid") String groupID) {
        return isOrganizationalRolesExists(userRoleID, groupID);
    }

    /**
     * IsOrganizationRoleExists
     * @param userRoleID
     * @param groupID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/isexists/json/{json}/userroleid/{userroleid}/groupid/{groupid}")
    public TingcoUtils postJson_IsExists(@PathParam("userroleid") String userRoleID, @PathParam("groupid") String groupID) {
        return isOrganizationalRolesExists(userRoleID, groupID);
    }

    /**
     * GetAllRolesandOrgByOrgID
     * @param request
     * @param groupID
     * @param countryID
     * @return
     */
    @GET
    @Path("/rest/{rest}/groupid/{groupid}/countryid/{countryid}")
    @Produces("application/xml")
    @RESTDoc(methodName = "GetAllRolesandOrgByOrgID", desc = "Returns all Roles and Organisations for the specified OrganizationID", req_Params = {"GroupID;UUID", "CountryId;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoUsers getXml_All(@Context HttpServletRequest request, @PathParam("groupid") String groupID, @PathParam("countryid") int countryID) {
        return allRolesandOrg(groupID, countryID, request);
    }

    /**
     * GetAllRolesandOrgByOrgID
     * @param request
     * @param groupID
     * @param countryID
     * @return
     */
    @GET
    @Path("/json/{json}/groupid/{groupid}/countryid/{countryid}")
    @Produces("application/json")
    public TingcoUsers getJson_All(@Context HttpServletRequest request, @PathParam("groupid") String groupID, @PathParam("countryid") int countryID) {
        return allRolesandOrg(groupID, countryID, request);
    }

    private TingcoGroups addRoleToOrgnization(String userRoleId, String groupId) {
        TingcoGroups tingcoGroups = null;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoGroups = tingcoGroupXML.buildTingcoGroupTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (userRoleId == null) {
                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                    tingcoGroups.getMsgStatus().setResponseText("UserRoleID is should not be empty");
                    return tingcoGroups;
                } else if (groupId == null) {
                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                    tingcoGroups.getMsgStatus().setResponseText("GroupID is should not be empty");
                    return tingcoGroups;
                }
                UserSessions2 userSessions2 = (UserSessions2) request.getAttribute("USERSESSION");

                UserRolesInGroups2 userRoleInGroups2 = new UserRolesInGroups2();
                UserRolesInGroups2Id id = new UserRolesInGroups2Id(userRoleId, groupId);
                userRoleInGroups2.setId(id);
                userRoleInGroups2.setLastUpdatedByUserId(userRoleId);
                userRoleInGroups2.setLastUpdatedByUserId(userSessions2.getUserId());
                GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                userRoleInGroups2.setCreatedDate(gc.getTime());
                userRoleInGroups2.setUpdatedDate(gc.getTime());

                session = HibernateUtil.getSessionFactory().openSession();

                if (!roleDAO.saveRoleOrganizationMapping(userRoleInGroups2, session)) {
                    tingcoGroups.getMsgStatus().setResponseCode(-1);
                    tingcoGroups.getMsgStatus().setResponseText("Error Occured While Saving Info");
                }

            } else {
                tingcoGroups.getMsgStatus().setResponseCode(-3);
                tingcoGroups.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoGroups;
            }
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("Error Occured While Saving Info");
            return tingcoGroups;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoGroups;
    }

    private TingcoUsers updatedOrganizationRole(String oldroleID, String newroleID, String groupID, HttpServletRequest request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        long requestedTime = System.currentTimeMillis();
        RoleManager manager = new RoleManager();
        TCMUtil tcm = new TCMUtil();
        TingcoUsers user = null;
        if (request.getAttribute("USERSESSION") != null) {
            try {
                boolean updated = manager.updateOrganizationRole(oldroleID, newroleID, groupID, session);
                if (updated) {
                    user = manager.buildUserTemplate();
                } else {
                    user = manager.buildUserTemplate();
                    user.getMsgStatus().setResponseCode(-1);
                    user.getMsgStatus().setResponseText("Error");
                }
            } catch (Exception e) {
                TCMUtil.exceptionLog(getClass().getName(), e);
            } finally {
                session.close();
                delayLog(requestedTime);
            }
        } else {
            try {
                user = tcm.sessionExpired();
            } catch (DatatypeConfigurationException ex) {
                TCMUtil.exceptionLog(getClass().getName(), ex);
            }
        }
        return user;
    }

    private TingcoUtils isOrganizationalRolesExists(String userRoleID, String groupID) {
        TingcoUtils tingcoUtils = null;
        Session session = null;
        long requestedTime = System.currentTimeMillis();
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();

            if (request.getAttribute("USERSESSION") != null) {
                if (userRoleID == null) {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("UserRoleID is should not be empty");
                    return tingcoUtils;
                } else if (groupID == null) {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("GroupID is should not be empty");
                    return tingcoUtils;
                }

                session = HibernateUtil.getSessionFactory().openSession();
                tingcoUtils.setOrganizationRoleExists(roleDAO.isOrganizationRoleExists(userRoleID, groupID, session));
                return tingcoUtils;
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUtils;
            }
        } catch (DatatypeConfigurationException dce) {
            TCMUtil.exceptionLog(getClass().getName(), dce);
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error Occured While Getting Info");
            return tingcoUtils;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUtils;
    }

    private TingcoUsers allRolesandOrg(String groupID, int countryID, HttpServletRequest request) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserManager manager = new UserManager();
        TingcoUsers user = null;
        long requestedTime = System.currentTimeMillis();
        try {
            user = manager.buildUserTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                user = manager.getAllRolesandOrgByGroupID(groupID, user, countryID, session);
            } else {
                TCMUtil tcm = new TCMUtil();
                user = tcm.sessionExpired();
            }
        } catch (DatatypeConfigurationException e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        } finally {
            session.close();
            delayLog(requestedTime);
        }
        return user;
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public UserRolesInGroup2Resource getAddRolesToOrganizationResource() {
        return new UserRolesInGroup2Resource();
    }
    public void delayLog(long requestedTime){
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took "+(System.currentTimeMillis()- requestedTime)+"ms]", "Info");
	}
}
