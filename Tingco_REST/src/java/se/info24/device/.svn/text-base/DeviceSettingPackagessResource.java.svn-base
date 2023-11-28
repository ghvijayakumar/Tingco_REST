/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.device;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.hibernate.Session;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.jaxbUtil.TingcoDeviceXML;
import se.info24.pojo.DeviceSettingsPackages;
import se.info24.util.HibernateUtil;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;

/**
 * REST Web Service
 *
 * @author Hitha
 */
@Path("/devicesettingpackages")
public class DeviceSettingPackagessResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
    DeviceDAO deviceDAO = new DeviceDAO();
//    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /** Creates a new instance of DeviceSettingPackagessResource */
    public DeviceSettingPackagessResource() {
    }

    /**
     * GetDeviceSettingsPackagesByDeviceTypeID
     * @param deviceTypeID
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getbydevicetypeid/rest/{rest}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetDeviceSettingsPackagesByDeviceTypeID", desc = "To Get All DeviceSettingsPackages By DeviceTypeID", req_Params = {"DeviceTypeID;UUID", "CountryID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoDevice getXml(@PathParam("devicetypeid") String deviceTypeID, @PathParam("countryid") int countryID) {
        return getDeviceSettingsPackageByDeviceTypeId(deviceTypeID, countryID);
    }

    /**
     * GetDeviceSettingsPackagesByDeviceTypeID
     * @param deviceTypeID
     * @param countryID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/getbydevicetypeid/rest/{rest}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    public TingcoDevice postXml(@PathParam("devicetypeid") String deviceTypeID, @PathParam("countryid") int countryID) {
        return getDeviceSettingsPackageByDeviceTypeId(deviceTypeID, countryID);
    }

    /**
     * GetDeviceSettingsPackagesByDeviceTypeID
     * @param deviceTypeID
     * @param countryID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getbydevicetypeid/json/{json}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    public TingcoDevice getJson(@PathParam("devicetypeid") String deviceTypeID, @PathParam("countryid") int countryID) {
        return getDeviceSettingsPackageByDeviceTypeId(deviceTypeID, countryID);
    }

    /**
     * GetDeviceSettingsPackagesByDeviceTypeID
     * @param deviceTypeID
     * @param countryID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/getbydevicetypeid/json/{json}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    public TingcoDevice postJson(@PathParam("devicetypeid") String deviceTypeID, @PathParam("countryid") int countryID) {
        return getDeviceSettingsPackageByDeviceTypeId(deviceTypeID, countryID);
    }

    private TingcoDevice getDeviceSettingsPackageByDeviceTypeId(String deviceTypeId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceTypeId == null) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("DeviceTypeID is should not be empty");
                    return tingcoDevice;
                } else if (countryId <= 0) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("CountryID is should not be empty");
                    return tingcoDevice;
                }

//            Hashtable<String,ArrayList> ht = (Hashtable<String, ArrayList>) request.getSession(true).getAttribute(request.getSession(true).getId());
//            if (ht.containsKey(TCMUtil.DEVICE)) {
//                ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
//                for(int i=0;i<operations.size();i++){
//                    if(operations.get(i).equalsIgnoreCase(TCMUtil.READ)){
                hasPermission = true;
//                    }
//                }
//            }

                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceSettingsPackages> dspList = deviceDAO.getDeviceSettingsPackagesByDeviceTypeId(deviceTypeId, session);
                    if (dspList != null) {
                        tingcoDevice = tingcoDeviceXML.buildDeviceSettingsPackages(tingcoDevice, dspList, countryId);
                        return tingcoDevice;
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No DeviceSettingsPackages Found with Given DeviceTypeID");
                        return tingcoDevice;
                    }
                } else {
                    tingcoDevice.getMsgStatus().setResponseCode(-10);
                    tingcoDevice.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoDevice;
                }
            } else {
                tingcoDevice.getMsgStatus().setResponseCode(-3);
                tingcoDevice.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoDevice;
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error Occured while Loading the DeviceSettingPackages");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public DeviceSettingPackagesResource getDeviceSettingPackagesResource() {
        return new DeviceSettingPackagesResource();
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}
