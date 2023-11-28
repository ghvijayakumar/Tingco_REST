/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.info24.user;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.hibernate.Session;
import se.info24.usersjaxb.TingcoUsers;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Sridhar
 */
@Path("/registeruser")
public class User_RegistersResource {
    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;

    /** Creates a new instance of User_RegistersResource */
    public User_RegistersResource() {
    }

    /**
     * Retrieves representation of an instance of se.info24.user.User_RegistersResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

   

    /**
     * POST method for creating an instance of User_RegistersResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Path("/rest/{rest}/password/{pwd}")
    @Consumes("application/xml")
    @Produces("application/xml")
    @RESTDoc(methodName = "User_RegistersResource", desc = "Register New User", req_Params = {"password;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoUsers postXml(@PathParam("pwd") String pwd,String content) {
        //TODO
        TingcoUsers tUser = (TingcoUsers) JAXBManager.getInstance().unMarshall(content, TingcoUsers.class);

        return activateNewUser(tUser,pwd);
    }

    /**
     * POST method for creating an instance of User_RegistersResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Path("/json/{json}/password/{pwd}")
    @Consumes("application/xml")
    @Produces("application/json")
    @RESTDoc(methodName = "User_RegistersResource", desc = "Register New User", req_Params = {"Password;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoUsers postJson(@PathParam("pwd") String pwd,String content) {
        //TODO
        TingcoUsers tUser = (TingcoUsers) JAXBManager.getInstance().unMarshall(content, TingcoUsers.class);

        return activateNewUser(tUser,pwd);
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public User_RegisterResource getUser_RegisterResource() {
        return new User_RegisterResource();
    }

      private TingcoUsers activateNewUser(TingcoUsers tUser,String pwd) {
        long requestedTime = System.currentTimeMillis();
          Session session = HibernateUtil.getSessionFactory().openSession();
          UserDAO manager = new UserDAO();
          TCMUtil tcm = new TCMUtil();
          TingcoUsers user = null;
          try {
              user = tcm.buildUserTemplate();
              boolean success = manager.addNewUserToActivate(tUser, pwd,request, session);
              if (!success) {
                  user.getMsgStatus().setResponseCode(-1);
                  user.getMsgStatus().setResponseText("Error");
              }
          } catch (Exception e) {
              TCMUtil.exceptionLog("User_RegisterResource", e);
              user.getMsgStatus().setResponseCode(-1);
          } finally {
              session.close();
              TCMUtil.loger("User_RegisterResource", "[tingco API] [Request took "+(System.currentTimeMillis()- requestedTime)+"ms]", "Info");
          }
          return user;
    }
}
