/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.device;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.containers.ContainerDAO;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.util.RESTDoc;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.ItemConnectionStatus;
import se.info24.user.UserDAO;
import se.info24.jaxbUtil.TingcoDeviceXML;
import se.info24.pojo.Containers;
import se.info24.pojo.Device;
import se.info24.pojo.DeviceDataitemTranslations;
import se.info24.pojo.DeviceTypeDataitems;
import se.info24.pojo.FieldTranslations;
import se.info24.pojo.FieldTypes;
import se.info24.pojo.ObjectFieldData;
import se.info24.util.TCMUtil;
import se.info24.pojo.UserSessions2;
import se.info24.user.User_LoginsResource;
import se.info24.util.HibernateUtil;
import se.info24.pojo.Fields;
import se.info24.pojo.Groups;
import se.info24.pojo.ListValues;
import se.info24.pojo.ObjectFieldDataId;
import se.info24.pojo.ObjectStateCodeTranslations;
import se.info24.pojo.ObjectStateCodes;
import se.info24.pojo.ObjectStateControl;
import se.info24.pojo.ObjectTypeFields;
import se.info24.pojo.Users2;
import se.info24.util.JAXBManager;

/**
 * REST Web Service
 *
 * @author Sumit
 */
@Path("/deviceproperties")
public class DevicePropertiessResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoDeviceXML tingcoDeviceXML = new TingcoDeviceXML();
    DeviceDAO deviceDAO = new DeviceDAO();
    GroupDAO groupDAO = new GroupDAO();
    UserDAO userDAO = new UserDAO();
    ContainerDAO containerDAO = new ContainerDAO();
//    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * getpropertiesforobject
     * @param objectId
     * @param countryId
     * @param objectType
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getpropertiesforobject/rest/{rest}/objectid/{objectid}/countryid/{countryid}/objecttype/{objecttype}")
    @RESTDoc(methodName = "getpropertiesforobject", desc = "getpropertiesforobject", req_Params = {"objectid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetPropertiesForObject(@PathParam("objectid") String objectId, @PathParam("countryid") String countryId, @PathParam("objecttype") String objectType) throws DatatypeConfigurationException {
        return getPropertiesForObject(objectId, countryId, objectType);
    }

    /**
     *
     * @param objectId
     * @param countryId
     * @param objectType
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getpropertiesforobject/json/{json}/objectid/{objectid}/countryid/{countryid}/objecttype/{objecttype}")
    public TingcoDevice getJson_GetPropertiesForObject(@PathParam("objectid") String objectId, @PathParam("countryid") String countryId, @PathParam("objecttype") String objectType) throws DatatypeConfigurationException {
        return getPropertiesForObject(objectId, countryId, objectType);
    }

    /**
     *
     * @param objectId
     * @param fieldId
     * @param fieldValue
     * @param objectType
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/updatepropertiesforobject/rest/{rest}/objectid/{objectid}/fieldid/{fieldid}/fieldvalue/{fieldvalue}/objecttype/{objecttype}")
    @RESTDoc(methodName = "updatepropertiesforobject", desc = "updatepropertiesforobject", req_Params = {"objectid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_UpdatePropertiesForObject(@PathParam("objectid") String objectId, @PathParam("fieldid") String fieldId, @PathParam("fieldvalue") String fieldValue, @PathParam("objecttype") String objectType) throws DatatypeConfigurationException {
        return updatePropertiesForObject(objectId, fieldId, fieldValue, objectType);
    }

    /**
     *
     * @param objectId
     * @param fieldId
     * @param fieldValue
     * @param objectType
     * @return TingcoDevice
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/updatepropertiesforobject/json/{json}/objectid/{objectid}/fieldid/{fieldid}/fieldvalue/{fieldvalue}/objecttype/{objecttype}")
    public TingcoDevice getJson_UpdatePropertiesForObject(@PathParam("objectid") String objectId, @PathParam("fieldid") String fieldId, @PathParam("fieldvalue") String fieldValue, @PathParam("objecttype") String objectType) throws DatatypeConfigurationException {
        return updatePropertiesForObject(objectId, fieldId, fieldValue, objectType);
    }

    /**
     *
     * @param countryid
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getobjectstatecodes/rest/{rest}/countryid/{countryid}")
    @RESTDoc(methodName = "GetStateCodes", desc = "GetStateCodes", req_Params = {"CountryId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetStateCodes(@PathParam("countryid") String countryid) throws DatatypeConfigurationException {
        return getStateCodes(countryid);
    }

    /**
     *
     * @param countryid
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getobjectstatecodes/json/{json}/countryid/{countryid}")
    public TingcoDevice getJson_GetStateCodes(@PathParam("countryid") String countryid) throws DatatypeConfigurationException {
        return getStateCodes(countryid);
    }

    /**
     *
     * @param deviceId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getdevicestate/rest/{rest}/deviceid/{deviceid}")
    @RESTDoc(methodName = "GetDeviceState", desc = "GetDeviceState", req_Params = {"DeviceId;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetDeviceState(@PathParam("deviceid") String deviceId) throws DatatypeConfigurationException {
        return getDeviceState(deviceId);
    }

    /**
     *
     * @param deviceId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getdevicestate/json/{json}/deviceid/{deviceid}")
    public TingcoDevice getJson_GetDeviceState(@PathParam("deviceid") String deviceId) throws DatatypeConfigurationException {
        return getDeviceState(deviceId);
    }

    /**
     *
     * @param deviceId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/updatedevicestate/rest/{rest}")
    @RESTDoc(methodName = "UpdateDeviceState", desc = "UpdateDeviceState", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoDevice postXml_UpdateDeviceState(String content) throws DatatypeConfigurationException {
        return updateDeviceState(content);
    }

    /**
     * 
     * @param deviceId
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/updatedevicestate/json/{json}")
    public TingcoDevice postJson_UpdateDeviceState(String content) throws DatatypeConfigurationException {
        return updateDeviceState(content);
    }

    /**
     * GetContainerDetails
     * @param objectId
     * @param countryId
     * @return TingcoDevice
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getpropertiesfortype/rest/{rest}/objectid/{objectid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetContainerDetails", desc = "Get Container Details", req_Params = {"ContainerId;UUID", "objectGroupId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetPropertiesForType(@PathParam("objectid") String objectId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getPropertiesForType(objectId, countryId);
    }
    /**
     * GetContainerDetails
     * @param objectId
     * @param countryId
     * @return TingcoDevice
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getpropertiesfortype/json/{json}/objectid/{objectid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetContainerDetails", desc = "Get Container Details", req_Params = {"ContainerId;UUID", "objectGroupId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getJson_GetPropertiesForType(@PathParam("objectid") String objectId, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getPropertiesForType(objectId, countryId);
    }
    /**
     * GetDataItemsForDeviceType
     * @param dviceTypeID
     * @param countryId
     * @return TingcoDevice
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getdataitemsfordevicetype/rest/{rest}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetDataItemsForDeviceType", desc = "Get DataItems For DeviceType", req_Params = {"ContainerId;UUID", "objectGroupId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetDataItemsForDeviceType(@PathParam("devicetypeid") String dviceTypeID, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getDataItemsForDeviceType(dviceTypeID, countryId);
    }
     /**
     * GetDataItemsForDeviceType
     * @param dviceTypeID
     * @param countryId
     * @return TingcoDevice
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getdataitemsfordevicetype/json/{json}/devicetypeid/{devicetypeid}/countryid/{countryid}")
    @RESTDoc(methodName = "GetDataItemsForDeviceType", desc = "Get DataItems For DeviceType", req_Params = {"ContainerId;UUID", "objectGroupId;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getJson_GetDataItemsForDeviceType(@PathParam("devicetypeid") String dviceTypeID, @PathParam("countryid") int countryId) throws DatatypeConfigurationException {
        return getDataItemsForDeviceType(dviceTypeID, countryId);
    }

    /** Creates a new instance of DevicePropertiessResource */
    public DevicePropertiessResource() {
    }

    /**
     * Sub-resource locator method for  deviceproperties
     */
    @Path("deviceproperties")
    public DevicePropertiesResource getDevicePropertiesResource() {
        return new DevicePropertiesResource();
    }


     /**
     * getpropertiesforobject
     * @param objectId
     * @param countryId
     * @param objectType
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/xml")
    @Path("/getpropertiesforobjecttype/rest/{rest}/objecttypeid/{objecttypeid}/countryid/{countryid}")
    @RESTDoc(methodName = "getpropertiesforobjecttype", desc = "getpropertiesforobject", req_Params = {"objectid;UUID"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET"}, versions = {"1"})
    public TingcoDevice getXml_GetPropertiesForObject(@PathParam("objecttypeid") String objectId, @PathParam("countryid") String countryId) throws DatatypeConfigurationException {
        return getPropertiesForObjectType(objectId, countryId);
    }

    /**
     *
     * @param objectId
     * @param countryId
     * @param objectType
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    @GET
    @Produces("application/json")
    @Path("/getpropertiesforobjecttypetype/json/{json}/objecttypeid/{objecttypeid}/countryid/{countryid}")
    public TingcoDevice getJson_GetPropertiesForObject(@PathParam("objecttypeid") String objectId, @PathParam("countryid") String countryId) throws DatatypeConfigurationException {
        return getPropertiesForObjectType(objectId, countryId);
    }

    public TingcoDevice getPropertiesForObjectType(String objectTypeId, String countryId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (objectTypeId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("objectId should not be empty");
                    return tingcoDevice;
                }
                if (countryId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("countryId should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    
                    if (objectTypeId == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Object Not Found");
                        return tingcoDevice;
                    }
                    List<ObjectTypeFields> objectTypeFieldList = deviceDAO.getObjectTypeFieldsByObjectID(session, objectTypeId);
                    if (objectTypeFieldList.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                    List<String> fieldIds = new ArrayList<String>();
                    for (ObjectTypeFields otf : objectTypeFieldList) {
                        Fields fields = otf.getFields();
                        fieldIds.add(fields.getFieldId());
                    }
                    List<FieldTranslations> fieldTransList = deviceDAO.getFieldTranslationsByIdList(session, fieldIds, countryId);

                    se.info24.devicejaxb.Devices devicesJaxb = new se.info24.devicejaxb.Devices();
                    se.info24.devicejaxb.Device deviceJaxb = new se.info24.devicejaxb.Device();

                    for (FieldTranslations ft : fieldTransList) {
                        se.info24.devicejaxb.Fields fieldsJaxb = new se.info24.devicejaxb.Fields();
                        se.info24.devicejaxb.FieldTranslations fieldsTransJaxb = new se.info24.devicejaxb.FieldTranslations();
                        se.info24.devicejaxb.FieldTypes fieldsTypesJaxb = new se.info24.devicejaxb.FieldTypes();

                        Fields field = ft.getFields();
                        ObjectFieldData objectFieldData = deviceDAO.getObjectFieldDataByID(session, objectTypeId, field.getFieldId());

                        fieldsJaxb.setFieldID(field.getFieldId());
                        if (field.getDecimals() != null) {
                            fieldsJaxb.setDecimals(field.getDecimals());
                        }
                        if (field.getValidationRegEx() != null && field.getValidationRegEx().length() > 0) {
                            fieldsJaxb.setValidationRegEx(field.getValidationRegEx());
                        }
                        fieldsJaxb.setLength(field.getLength());
                        if (field.getMinValue() != null) {
                            fieldsJaxb.setMinValue(field.getMinValue().floatValue());
                        }
                        if (field.getMaxValue() != null) {
                            fieldsJaxb.setMaxValue(field.getMaxValue().floatValue());
                        }
                        if (field.getLists() != null) {
                            fieldsJaxb.setListID(field.getLists().getListId());
                        }

                        if (objectFieldData != null) {
                            if (objectFieldData.getFieldValue() != null) {
                                fieldsJaxb.setFieldValue(objectFieldData.getFieldValue());
                                if (field.getFieldTypes().getFieldTypeCode().equalsIgnoreCase("checkbox")) {
                                    if (objectFieldData.getFieldValue().equalsIgnoreCase("1")) {
                                        fieldsJaxb.setFieldValueV3("Enabled");
                                    } else {
                                        fieldsJaxb.setFieldValueV3("Disabled");
                                    }
                                } else if (field.getFieldTypes().getFieldTypeCode().equalsIgnoreCase("boolean")) {
                                    if (objectFieldData.getFieldValue().equalsIgnoreCase("1")) {
                                        fieldsJaxb.setFieldValueV3("True");
                                    } else {
                                        fieldsJaxb.setFieldValueV3("False");
                                    }
                                } else {
                                    fieldsJaxb.setFieldValueV3(objectFieldData.getFieldValue());
                                }

                            }
                        }
                        if (ft.getFieldName() != null) {
                            fieldsTransJaxb.setFieldName(ft.getFieldName());
                        }
                        if (ft.getFieldDescription() != null) {
                            fieldsTransJaxb.setFieldDescription(ft.getFieldDescription());
                        }
                        if (ft.getUnit() != null) {
                            fieldsTransJaxb.setUnit(ft.getUnit());
                        }

                        FieldTypes fieldTypes = field.getFieldTypes();
                        fieldsTypesJaxb.setFieldTypeID(fieldTypes.getFieldTypeId());
                        fieldsTypesJaxb.setFieldTypeCode(fieldTypes.getFieldTypeCode());

                        if (fieldTypes.getFieldTypeCode().equalsIgnoreCase("LIST")) {

                            if (field.getLists() != null) {
                                List<ListValues> listValuesList = deviceDAO.getListValuesByListId(session, field.getLists().getListId());

                                for (ListValues lv : listValuesList) {
                                    se.info24.devicejaxb.ListValues listValueJaxb = new se.info24.devicejaxb.ListValues();
                                    listValueJaxb.setListID(lv.getLists().getListId());
                                    if (lv.getListValue1() != null) {
                                        listValueJaxb.setListValue1(lv.getListValue1());
                                    }
                                    if (lv.getListValue2() != null) {
                                        listValueJaxb.setListValue2(lv.getListValue2());
                                    }
                                    if (lv.getListValue3() != null) {
                                        listValueJaxb.setListValue3(lv.getListValue3());
                                    }
                                    if (lv.getListValue4() != null) {
                                        listValueJaxb.setListValue4(lv.getListValue4());
                                    }
                                    if (lv.getListValue5() != null) {
                                        listValueJaxb.setListValue5(lv.getListValue5());
                                    }
                                    fieldsJaxb.getListValues().add(listValueJaxb);
                                }
                            }
                        }
                        fieldsJaxb.getFieldTranslations().add(fieldsTransJaxb);
                        fieldsJaxb.getFieldTypes().add(fieldsTypesJaxb);
                        deviceJaxb.getContent().add(fieldsJaxb);
                    }
                    devicesJaxb.getDevice().add(deviceJaxb);
                    tingcoDevice.setDevices(devicesJaxb);
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
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }
    private TingcoDevice getDataItemsForDeviceType(String dviceTypeID, int countryId) {
        long requestedTime = System.currentTimeMillis();
        boolean hasPermission = false;
        TingcoDevice tingcoDevice = null;
        Session session = null;
        Transaction tx = null;
        try {
             tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<DeviceTypeDataitems> deviceTypeDataitemses = deviceDAO.getDeviceTypeDataItemsByDeviceTypeID(dviceTypeID, session);
                    if (deviceTypeDataitemses.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                    List<String>  deviceDataItemId = new ArrayList<String>();
                    for (DeviceTypeDataitems deviceTypeDataitems : deviceTypeDataitemses) {
                        deviceDataItemId.add(deviceTypeDataitems.getId().getDeviceDataItemId());
                    }
                    List<DeviceDataitemTranslations> deviceDataitemTranslationses = containerDAO.getDeviceDataItemTranslationsByIds(session, deviceDataItemId, countryId);
                    return tingcoDeviceXML.buildGetDataItemsForDeviceType(tingcoDevice,deviceDataitemTranslationses);
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
        } catch (Exception ex) {
            if (tx != null) {
                if (tx.wasCommitted()) {
                    tx.rollback();
                }
            }
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
    }

    private TingcoDevice getPropertiesForType(String objectTypeId, int countryId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {

                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.CONTAINERS)) {
                    ArrayList<String> operations = ht.get(TCMUtil.CONTAINERS);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();

                    Users2 user = null;
                    Groups group = null;

                    List<ObjectTypeFields> objectTypeFieldList = deviceDAO.getObjectTypeFieldsByObjectID(session, objectTypeId);
                    if (objectTypeFieldList.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                    List<String> fieldIds = new ArrayList<String>();
                    for (ObjectTypeFields otf : objectTypeFieldList) {
                        Fields fields = otf.getFields();
                        fieldIds.add(fields.getFieldId());
                    }
                    List<FieldTranslations> fieldTransList = deviceDAO.getFieldTranslationsByIdList(session, fieldIds, String.valueOf(countryId));

                    se.info24.devicejaxb.Devices devicesJaxb = new se.info24.devicejaxb.Devices();
                    se.info24.devicejaxb.Device deviceJaxb = new se.info24.devicejaxb.Device();

                    for (FieldTranslations ft : fieldTransList) {
                        se.info24.devicejaxb.Fields fieldsJaxb = new se.info24.devicejaxb.Fields();
                        se.info24.devicejaxb.FieldTranslations fieldsTransJaxb = new se.info24.devicejaxb.FieldTranslations();
                        se.info24.devicejaxb.FieldTypes fieldsTypesJaxb = new se.info24.devicejaxb.FieldTypes();

                        Fields field = ft.getFields();
//                        ObjectFieldData objectFieldData = deviceDAO.getObjectFieldDataByID(session, objectId, field.getFieldId());

                        fieldsJaxb.setFieldID(field.getFieldId());
                        if (field.getDecimals() != null) {
                            fieldsJaxb.setDecimals(field.getDecimals());
                        }
                        if (field.getValidationRegEx() != null && field.getValidationRegEx().length() > 0) {
                            fieldsJaxb.setValidationRegEx(field.getValidationRegEx());
                        }
                        fieldsJaxb.setLength(field.getLength());
                        if (field.getMinValue() != null) {
                            fieldsJaxb.setMinValue(field.getMinValue().floatValue());
                        }
                        if (field.getMaxValue() != null) {
                            fieldsJaxb.setMaxValue(field.getMaxValue().floatValue());
                        }
                        if (field.getLists() != null) {
                            fieldsJaxb.setListID(field.getLists().getListId());
                        }

//                        if (objectFieldData != null) {
//                            if (objectFieldData.getFieldValue() != null) {
//                                fieldsJaxb.setFieldValue(objectFieldData.getFieldValue());
//                                if (field.getFieldTypes().getFieldTypeCode().equalsIgnoreCase("checkbox")) {
//                                    if (objectFieldData.getFieldValue().equalsIgnoreCase("1")) {
//                                        fieldsJaxb.setFieldValueV3("Enabled");
//                                    } else {
//                                        fieldsJaxb.setFieldValueV3("Disabled");
//                                    }
//                                } else if (field.getFieldTypes().getFieldTypeCode().equalsIgnoreCase("boolean")) {
//                                    if (objectFieldData.getFieldValue().equalsIgnoreCase("1")) {
//                                        fieldsJaxb.setFieldValueV3("True");
//                                    } else {
//                                        fieldsJaxb.setFieldValueV3("False");
//                                    }
//                                } else {
//                                    fieldsJaxb.setFieldValueV3(objectFieldData.getFieldValue());
//                                }
//
//                            }
//                        }
                        if (ft.getFieldName() != null) {
                            fieldsTransJaxb.setFieldName(ft.getFieldName());
                        }
                        if (ft.getFieldDescription() != null) {
                            fieldsTransJaxb.setFieldDescription(ft.getFieldDescription());
                        }
                        if (ft.getUnit() != null) {
                            fieldsTransJaxb.setUnit(ft.getUnit());
                        }

                        FieldTypes fieldTypes = field.getFieldTypes();
                        fieldsTypesJaxb.setFieldTypeID(fieldTypes.getFieldTypeId());
                        fieldsTypesJaxb.setFieldTypeCode(fieldTypes.getFieldTypeCode());

                        if (fieldTypes.getFieldTypeCode().equalsIgnoreCase("LIST")) {

                            if (field.getLists() != null) {
                                List<ListValues> listValuesList = deviceDAO.getListValuesByListId(session, field.getLists().getListId());

                                for (ListValues lv : listValuesList) {
                                    se.info24.devicejaxb.ListValues listValueJaxb = new se.info24.devicejaxb.ListValues();
                                    listValueJaxb.setListID(lv.getLists().getListId());
                                    if (lv.getListValue1() != null) {
                                        listValueJaxb.setListValue1(lv.getListValue1());
                                    }
                                    if (lv.getListValue2() != null) {
                                        listValueJaxb.setListValue2(lv.getListValue2());
                                    }
                                    if (lv.getListValue3() != null) {
                                        listValueJaxb.setListValue3(lv.getListValue3());
                                    }
                                    if (lv.getListValue4() != null) {
                                        listValueJaxb.setListValue4(lv.getListValue4());
                                    }
                                    if (lv.getListValue5() != null) {
                                        listValueJaxb.setListValue5(lv.getListValue5());
                                    }
                                    fieldsJaxb.getListValues().add(listValueJaxb);
                                }
                            }
                        }
                        fieldsJaxb.getFieldTranslations().add(fieldsTransJaxb);
                        fieldsJaxb.getFieldTypes().add(fieldsTypesJaxb);
                        deviceJaxb.getContent().add(fieldsJaxb);
                    }
                    devicesJaxb.getDevice().add(deviceJaxb);
                    tingcoDevice.setDevices(devicesJaxb);
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
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    public TingcoDevice updateDeviceState(String content) {//DeviceManagement-->Device-->setting--->State Control
        long requestedTime = System.currentTimeMillis();
        Session ismSession = null;
        Session ismOprSession = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        Transaction ismTx = null;
        Transaction oprTx = null;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.UPDATE)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    ismSession = HibernateUtil.getSessionFactory().openSession();
                    ismOprSession = HibernateUtil.getISMOperationsSessionFactory().openSession();
                    se.info24.devicejaxbPost.TingcoDevice tingcoDeviceJaxb = (se.info24.devicejaxbPost.TingcoDevice) JAXBManager.getInstance().unMarshall(content, se.info24.devicejaxbPost.TingcoDevice.class);
                    se.info24.devicejaxbPost.Devices devicesJaxb = tingcoDeviceJaxb.getDevices();

                    ismTx = ismSession.beginTransaction();
                    oprTx = ismOprSession.beginTransaction();

                    for (se.info24.devicejaxbPost.Device deviceJaxb : devicesJaxb.getDevice()) {
                        for (se.info24.devicejaxbPost.ObjectStateControl oscJaxb : deviceJaxb.getObjectStateControl()) {
                            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                            ObjectStateControl objectStateControl = deviceDAO.getObjectStateControlByObjectId(ismSession, oscJaxb.getObjectID());
                            if (objectStateControl == null) {
                                ObjectStateControl objectControl = new ObjectStateControl();
                                objectControl.setObjectId(oscJaxb.getObjectID());
                                objectControl.setStateModeIsAutomatic(oscJaxb.getStateModeIsAutomatic());
                                objectControl.setStateModeIsManual(oscJaxb.getStateModeIsManual());
                                objectControl.setStateModeIsSchedule(oscJaxb.getStateModeIsSchedule());
                                if (oscJaxb.getManualModeObjectStateCodeID() != null && oscJaxb.getStateModeIsManual() == 1) {
                                    objectControl.setManualModeObjectStateCodeId(oscJaxb.getManualModeObjectStateCodeID());
                                } else if (oscJaxb.getStateModeIsAutomatic() == 1 || oscJaxb.getStateModeIsSchedule() == 1 || oscJaxb.getManualModeObjectStateCodeID() == null) {
                                    objectControl.setManualModeObjectStateCodeId(null);
                                }
                                if (oscJaxb.getScheduleModeScheduleID() != null && oscJaxb.getStateModeIsSchedule() == 1) {
                                    objectControl.setScheduleModeScheduleId(oscJaxb.getScheduleModeScheduleID());
                                } else {
                                    objectControl.setScheduleModeScheduleId(null);
                                }
                                if (oscJaxb.getScheduleDuringObjectStateCodeID() != null && oscJaxb.getStateModeIsSchedule() == 1) {
                                    objectControl.setScheduleDuringObjectStateCodeId(oscJaxb.getScheduleDuringObjectStateCodeID());
                                } else {
                                    objectControl.setScheduleDuringObjectStateCodeId(null);
                                }
                                if (oscJaxb.getScheduleOutsideObjectStateCodeID() != null && oscJaxb.getStateModeIsSchedule() == 1) {
                                    objectControl.setScheduleOutsideObjectStateCodeId(oscJaxb.getScheduleOutsideObjectStateCodeID());
                                } else {
                                    objectControl.setScheduleOutsideObjectStateCodeId(null);
                                }
                                if (oscJaxb.getStateCommentInternal() != null) {
                                    objectControl.setStateCommentInternal(oscJaxb.getStateCommentInternal());
                                }
                                if (oscJaxb.getStateCommentPublic() != null) {
                                    objectControl.setStateCommentPublic(oscJaxb.getStateCommentPublic());
                                }
                                objectControl.setCreatedDate(gc.getTime());
                                objectControl.setUpdatedDate(gc.getTime());
                                objectControl.setLastUpdatedByUserId(sessions2.getUserId());

                                ismSession.saveOrUpdate(objectControl);

                                if (oscJaxb.getStateModeIsAutomatic() == 1) {
                                    Object icsObject = deviceDAO.getitemconnectionStatus(oscJaxb.getObjectID(), ismOprSession);
                                    if (icsObject == null) {
                                       ItemConnectionStatus ics = new ItemConnectionStatus();
                                        ics.setItemId(oscJaxb.getObjectID());
                                        ics.setConnected(new Short("0"));
                                        ics.setCreatedDate(gc.getTime());
                                        ics.setUpdatedDate(gc.getTime());
                                        ics.setObjectStateCode("OK");
                                        ics.setStateChangedDate(gc.getTime());//Updated on 18 oct 2013
                                        ismOprSession.saveOrUpdate(ics);
                                    } else {
                                        ItemConnectionStatus ics = (ItemConnectionStatus) icsObject;
//                                        ics.setConnected(new Short("0"));
                                        ics.setObjectStateCode("OK");
                                        ics.setUpdatedDate(gc.getTime());
                                        ics.setStateChangedDate(gc.getTime());//Updated on 18 oct 2013
                                        ismOprSession.saveOrUpdate(ics);
                                    }
                                } else if (oscJaxb.getStateModeIsManual() == 1) {
                                    ObjectStateCodes stateCodes = deviceDAO.getObjectStateCodesById(ismSession, oscJaxb.getManualModeObjectStateCodeID());
                                    if (stateCodes != null) {
                                        Object icsObject = deviceDAO.getitemconnectionStatus(oscJaxb.getObjectID(), ismOprSession);
                                        if (icsObject == null) {
                                            ItemConnectionStatus connStatus = new ItemConnectionStatus();
                                            connStatus.setItemId(oscJaxb.getObjectID());
                                            connStatus.setConnected(new Short("0"));
                                            connStatus.setCreatedDate(gc.getTime());
                                            connStatus.setUpdatedDate(gc.getTime());
                                            connStatus.setStateChangedDate(gc.getTime());
                                            connStatus.setObjectStateCode(stateCodes.getObjectStateCode());
                                            ismOprSession.saveOrUpdate(connStatus);
                                        } else {
                                            ItemConnectionStatus ics = (ItemConnectionStatus) icsObject;
                                            ics.setStateChangedDate(gc.getTime());
                                            ics.setObjectStateCode(stateCodes.getObjectStateCode());
                                            ismOprSession.saveOrUpdate(ics);
                                        }
                                    }
                                }
                            } else {
                                objectStateControl.setStateModeIsAutomatic(oscJaxb.getStateModeIsAutomatic());
                                objectStateControl.setStateModeIsManual(oscJaxb.getStateModeIsManual());
                                objectStateControl.setStateModeIsSchedule(oscJaxb.getStateModeIsSchedule());
                                if (oscJaxb.getManualModeObjectStateCodeID() != null && oscJaxb.getStateModeIsManual() == 1) {
                                    objectStateControl.setManualModeObjectStateCodeId(oscJaxb.getManualModeObjectStateCodeID());
                                } else if (oscJaxb.getStateModeIsAutomatic() == 1 || oscJaxb.getStateModeIsSchedule() == 1 || oscJaxb.getManualModeObjectStateCodeID() == null) {
                                    objectStateControl.setManualModeObjectStateCodeId(null);
                                }
                                if (oscJaxb.getScheduleModeScheduleID() != null && oscJaxb.getStateModeIsSchedule() == 1) {
                                    objectStateControl.setScheduleModeScheduleId(oscJaxb.getScheduleModeScheduleID());
                                } else {
                                    objectStateControl.setScheduleModeScheduleId(null);
                                }
                                if (oscJaxb.getScheduleDuringObjectStateCodeID() != null && oscJaxb.getStateModeIsSchedule() == 1) {
                                    objectStateControl.setScheduleDuringObjectStateCodeId(oscJaxb.getScheduleDuringObjectStateCodeID());
                                } else {
                                    objectStateControl.setScheduleDuringObjectStateCodeId(null);
                                }
                                if (oscJaxb.getScheduleOutsideObjectStateCodeID() != null && oscJaxb.getStateModeIsSchedule() == 1) {
                                    objectStateControl.setScheduleOutsideObjectStateCodeId(oscJaxb.getScheduleOutsideObjectStateCodeID());
                                } else {
                                    objectStateControl.setScheduleOutsideObjectStateCodeId(null);
                                }
                                if (oscJaxb.getStateCommentInternal() != null) {
                                    objectStateControl.setStateCommentInternal(oscJaxb.getStateCommentInternal());
                                }
                                if (oscJaxb.getStateCommentPublic() != null) {
                                    objectStateControl.setStateCommentPublic(oscJaxb.getStateCommentPublic());
                                }
                                objectStateControl.setUpdatedDate(gc.getTime());
                                objectStateControl.setLastUpdatedByUserId(sessions2.getUserId());
                                ismSession.saveOrUpdate(objectStateControl);

                                if (oscJaxb.getStateModeIsAutomatic() == 1) {
                                    Object icsObject = deviceDAO.getitemconnectionStatus(oscJaxb.getObjectID(), ismOprSession);
                                    if (icsObject == null) {
                                        ItemConnectionStatus connStatus = new ItemConnectionStatus();
                                        connStatus.setItemId(oscJaxb.getObjectID());
                                        //connStatus.setConnected(new Short("1"));
                                        connStatus.setConnected(new Short("0"));//Updated on 30 OCT
                                        connStatus.setCreatedDate(gc.getTime());
                                        connStatus.setUpdatedDate(gc.getTime());
                                        connStatus.setObjectStateCode("OK");
                                        connStatus.setStateChangedDate(gc.getTime());//Updated on 30 OCT
                                        ismOprSession.saveOrUpdate(connStatus);
                                    } else {
                                        ItemConnectionStatus ics = (ItemConnectionStatus) icsObject;
//                                        ics.setConnected(new Short("0"));
                                        ics.setObjectStateCode("OK");
                                        ics.setStateChangedDate(gc.getTime());//Updated on 30 OCT
                                        ismOprSession.saveOrUpdate(ics);
                                    }
                                } else if (oscJaxb.getStateModeIsManual() == 1) {
                                    ObjectStateCodes stateCodes = deviceDAO.getObjectStateCodesById(ismSession, oscJaxb.getManualModeObjectStateCodeID());
                                    if (stateCodes != null) {
                                        Object icsObject = deviceDAO.getitemconnectionStatus(oscJaxb.getObjectID(), ismOprSession);
                                        if (icsObject == null) {
                                            ItemConnectionStatus connStatus = new ItemConnectionStatus();
                                            connStatus.setItemId(oscJaxb.getObjectID());
                                            connStatus.setConnected(new Short("0"));
                                            connStatus.setCreatedDate(gc.getTime());
                                            connStatus.setUpdatedDate(gc.getTime());
                                            connStatus.setStateChangedDate(gc.getTime());
                                            connStatus.setObjectStateCode(stateCodes.getObjectStateCode());
                                            ismOprSession.saveOrUpdate(connStatus);
                                        } else {
                                            ItemConnectionStatus ics = (ItemConnectionStatus) icsObject;
                                            ics.setStateChangedDate(gc.getTime());
                                            ics.setObjectStateCode(stateCodes.getObjectStateCode());
                                            ismOprSession.saveOrUpdate(ics);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    ismTx.commit();
                    oprTx.commit();
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
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (ismTx.wasCommitted()) {
                ismTx.rollback();
            }
            if (oprTx.wasCommitted()) {
                oprTx.rollback();
            }
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error Occured while updating");
            return tingcoDevice;
        } finally {
            if (ismSession != null) {
                ismSession.close();
            }
            if (ismOprSession != null) {
                ismOprSession.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    public TingcoDevice getDeviceState(String deviceId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (deviceId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("deviceId should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    ObjectStateControl osc = deviceDAO.getObjectStateControlByObjectId(session, deviceId);
                    if (osc == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found");
                        return tingcoDevice;
                    } else {
                        se.info24.devicejaxb.Devices devicesJaxb = new se.info24.devicejaxb.Devices();
                        se.info24.devicejaxb.Device deviceJaxb = new se.info24.devicejaxb.Device();
                        se.info24.devicejaxb.ObjectStateControl oscJaxb = new se.info24.devicejaxb.ObjectStateControl();
                        oscJaxb.setObjectID(osc.getObjectId());
                        oscJaxb.setStateModeIsAutomatic(osc.getStateModeIsAutomatic());
                        oscJaxb.setStateModeIsManual(osc.getStateModeIsManual());
                        oscJaxb.setStateModeIsSchedule(osc.getStateModeIsSchedule());
                        if (osc.getManualModeObjectStateCodeId() != null) {
                            oscJaxb.setManualModeObjectStateCodeID(osc.getManualModeObjectStateCodeId());
                        }
                        if (osc.getScheduleModeScheduleId() != null) {
                            oscJaxb.setScheduleModeScheduleID(osc.getScheduleModeScheduleId());
                        }
                        if (osc.getScheduleDuringObjectStateCodeId() != null) {
                            oscJaxb.setScheduleDuringObjectStateCodeID(osc.getScheduleDuringObjectStateCodeId());
                        }
                        if (osc.getScheduleOutsideObjectStateCodeId() != null) {
                            oscJaxb.setScheduleOutsideObjectStateCodeID(osc.getScheduleOutsideObjectStateCodeId());
                        }
                        if (osc.getStateCommentInternal() != null) {
                            oscJaxb.setStateCommentInternal(osc.getStateCommentInternal());
                        }
                        if (osc.getStateCommentPublic() != null) {
                            oscJaxb.setStateCommentPublic(osc.getStateCommentPublic());
                        }
                        deviceJaxb.getContent().add(oscJaxb);

                        devicesJaxb.getDevice().add(deviceJaxb);
                        tingcoDevice.setDevices(devicesJaxb);
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
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error Occured while reading");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    public TingcoDevice getStateCodes(String countryId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (countryId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("countryId should not be empty");
                    return tingcoDevice;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(sessions2.getUserId());
                if (ht.containsKey(TCMUtil.DEVICE)) {
                    ArrayList<String> operations = ht.get(TCMUtil.DEVICE);
                    for (int i = 0; i < operations.size(); i++) {
                        if (operations.get(i).equalsIgnoreCase(TCMUtil.READ)) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<ObjectStateCodeTranslations> osctList = deviceDAO.getObjectStateCodeTranslationsByCountryId(session, countryId);
                    if (!osctList.isEmpty()) {
                        tingcoDevice = tingcoDeviceXML.buildObjectStateCodes(tingcoDevice, osctList);
                    } else {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("No Data Found");
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
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error Occured while reading");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    public TingcoDevice updatePropertiesForObject(String objectId, String fieldId, String fieldValue, String objectType) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (objectId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("objectId should not be empty");
                    return tingcoDevice;
                }
                if (fieldId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("fieldId should not be empty");
                    return tingcoDevice;
                }
                if (fieldValue.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("fieldValue should not be empty");
                    return tingcoDevice;
                }
                if (objectType.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("objectType should not be empty");
                    return tingcoDevice;
                }
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
                    session = HibernateUtil.getSessionFactory().openSession();
                    fieldValue = TCMUtil.convertHexToString(fieldValue);
                    ObjectFieldData objectFieldData = deviceDAO.getObjectFieldDataByID(session, objectId, fieldId);
                    if (objectFieldData != null) {
                        objectFieldData.setFieldValue(fieldValue);
                        objectFieldData.setLastUpdatedByUserId(sessions2.getUserId());
                        objectFieldData.setUpdatedDate(new GregorianCalendar(TimeZone.getTimeZone("UTC")).getTime());
                        if (!deviceDAO.saveUpdateObjectFieldData(session, objectFieldData)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while update");
                            return tingcoDevice;
                        }
                    } else {
                        objectFieldData = new ObjectFieldData();
                        ObjectFieldDataId id = new ObjectFieldDataId();
                        id.setFieldId(fieldId);
                        id.setObjectId(objectId);
                        objectFieldData.setId(id);
                        objectFieldData.setFieldValue(fieldValue);
                        objectFieldData.setLastUpdatedByUserId(sessions2.getUserId());
                        objectFieldData.setCreatedDate(new GregorianCalendar(TimeZone.getTimeZone("UTC")).getTime());
                        objectFieldData.setUpdatedDate(new GregorianCalendar(TimeZone.getTimeZone("UTC")).getTime());
                        if (!deviceDAO.saveUpdateObjectFieldData(session, objectFieldData)) {
                            tingcoDevice.getMsgStatus().setResponseCode(-1);
                            tingcoDevice.getMsgStatus().setResponseText("Error Occured while inserting");
                            return tingcoDevice;
                        }
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
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error Occured while updating");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    public TingcoDevice getPropertiesForObject(String objectId, String countryId, String objectType) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoDevice tingcoDevice = null;
        boolean hasPermission = false;
        try {
            tingcoDevice = tingcoDeviceXML.buildTingcoDeviceTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (objectId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("objectId should not be empty");
                    return tingcoDevice;
                }
                if (countryId.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("countryId should not be empty");
                    return tingcoDevice;
                }
                if (objectType.equals("")) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("objectType should not be empty");
                    return tingcoDevice;
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
                    Device device = deviceDAO.getDeviceById(objectId, session);
                    Users2 user = null;
                    Groups group = null;
                    String objectTypeId = null;
                    if (objectType.equalsIgnoreCase("CONTAINERS")) {
                        Containers containers = containerDAO.getContainersByContainerId(objectId, session);
                        objectTypeId = containers.getContainerTypes().getContainerTypeId();
                    } else if (device != null) {
                        objectTypeId = device.getDeviceTypes().getDeviceTypeId();
                    } else {
                        user = userDAO.getUserById(objectId, session);
                        if (user != null) {
                            objectTypeId = user.getUserTypes2().getUserTypeId();
                        } else {
                            group = groupDAO.getGroupByGroupId(objectId, session);
                            if (group != null) {
                                objectTypeId = group.getGroupTypeId();
                            }
                        }
                    }
                    if (objectTypeId == null) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Object Not Found");
                        return tingcoDevice;
                    }
                    List<ObjectTypeFields> objectTypeFieldList = deviceDAO.getObjectTypeFieldsByObjectID(session, objectTypeId);
                    if (objectTypeFieldList.isEmpty()) {
                        tingcoDevice.getMsgStatus().setResponseCode(-1);
                        tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                        return tingcoDevice;
                    }
                    List<String> fieldIds = new ArrayList<String>();
                    for (ObjectTypeFields otf : objectTypeFieldList) {
                        Fields fields = otf.getFields();
                        fieldIds.add(fields.getFieldId());
                    }
                    List<FieldTranslations> fieldTransList = deviceDAO.getFieldTranslationsByIdList(session, fieldIds, countryId);

                    se.info24.devicejaxb.Devices devicesJaxb = new se.info24.devicejaxb.Devices();
                    se.info24.devicejaxb.Device deviceJaxb = new se.info24.devicejaxb.Device();

                    for (FieldTranslations ft : fieldTransList) {
                        se.info24.devicejaxb.Fields fieldsJaxb = new se.info24.devicejaxb.Fields();
                        se.info24.devicejaxb.FieldTranslations fieldsTransJaxb = new se.info24.devicejaxb.FieldTranslations();
                        se.info24.devicejaxb.FieldTypes fieldsTypesJaxb = new se.info24.devicejaxb.FieldTypes();

                        Fields field = ft.getFields();
                        ObjectFieldData objectFieldData = deviceDAO.getObjectFieldDataByID(session, objectId, field.getFieldId());

                        fieldsJaxb.setFieldID(field.getFieldId());
                        if (field.getDecimals() != null) {
                            fieldsJaxb.setDecimals(field.getDecimals());
                        }
                        if (field.getValidationRegEx() != null && field.getValidationRegEx().length() > 0) {
                            fieldsJaxb.setValidationRegEx(field.getValidationRegEx());
                        }
                        fieldsJaxb.setLength(field.getLength());
                        if (field.getMinValue() != null) {
                            fieldsJaxb.setMinValue(field.getMinValue().floatValue());
                        }
                        if (field.getMaxValue() != null) {
                            fieldsJaxb.setMaxValue(field.getMaxValue().floatValue());
                        }
                        if (field.getLists() != null) {
                            fieldsJaxb.setListID(field.getLists().getListId());
                        }

                        if (objectFieldData != null) {
                            if (objectFieldData.getFieldValue() != null) {
                                fieldsJaxb.setFieldValue(objectFieldData.getFieldValue());
                                if (field.getFieldTypes().getFieldTypeCode().equalsIgnoreCase("checkbox")) {
                                    if (objectFieldData.getFieldValue().equalsIgnoreCase("1")) {
                                        fieldsJaxb.setFieldValueV3("Enabled");
                                    } else {
                                        fieldsJaxb.setFieldValueV3("Disabled");
                                    }
                                } else if (field.getFieldTypes().getFieldTypeCode().equalsIgnoreCase("boolean")) {
                                    if (objectFieldData.getFieldValue().equalsIgnoreCase("1")) {
                                        fieldsJaxb.setFieldValueV3("True");
                                    } else {
                                        fieldsJaxb.setFieldValueV3("False");
                                    }
                                } else {
                                    fieldsJaxb.setFieldValueV3(objectFieldData.getFieldValue());
                                }

                            }
                        }
                        if (ft.getFieldName() != null) {
                            fieldsTransJaxb.setFieldName(ft.getFieldName());
                        }
                        if (ft.getFieldDescription() != null) {
                            fieldsTransJaxb.setFieldDescription(ft.getFieldDescription());
                        }
                        if (ft.getUnit() != null) {
                            fieldsTransJaxb.setUnit(ft.getUnit());
                        }

                        FieldTypes fieldTypes = field.getFieldTypes();
                        fieldsTypesJaxb.setFieldTypeID(fieldTypes.getFieldTypeId());
                        fieldsTypesJaxb.setFieldTypeCode(fieldTypes.getFieldTypeCode());

                        if (fieldTypes.getFieldTypeCode().equalsIgnoreCase("LIST")) {

                            if (field.getLists() != null) {
                                List<ListValues> listValuesList = deviceDAO.getListValuesByListId(session, field.getLists().getListId());

                                for (ListValues lv : listValuesList) {
                                    se.info24.devicejaxb.ListValues listValueJaxb = new se.info24.devicejaxb.ListValues();
                                    listValueJaxb.setListID(lv.getLists().getListId());
                                    if (lv.getListValue1() != null) {
                                        listValueJaxb.setListValue1(lv.getListValue1());
                                    }
                                    if (lv.getListValue2() != null) {
                                        listValueJaxb.setListValue2(lv.getListValue2());
                                    }
                                    if (lv.getListValue3() != null) {
                                        listValueJaxb.setListValue3(lv.getListValue3());
                                    }
                                    if (lv.getListValue4() != null) {
                                        listValueJaxb.setListValue4(lv.getListValue4());
                                    }
                                    if (lv.getListValue5() != null) {
                                        listValueJaxb.setListValue5(lv.getListValue5());
                                    }
                                    fieldsJaxb.getListValues().add(listValueJaxb);
                                }
                            }
                        }
                        fieldsJaxb.getFieldTranslations().add(fieldsTransJaxb);
                        fieldsJaxb.getFieldTypes().add(fieldsTypesJaxb);
                        deviceJaxb.getContent().add(fieldsJaxb);
                    }
                    devicesJaxb.getDevice().add(deviceJaxb);
                    tingcoDevice.setDevices(devicesJaxb);
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
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
            return tingcoDevice;
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoDevice;
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }
}
