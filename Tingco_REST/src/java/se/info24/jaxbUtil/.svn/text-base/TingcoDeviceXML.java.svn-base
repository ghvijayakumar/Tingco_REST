/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.jaxbUtil;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Session;
import se.info24.commandsjaxb.Command;
import se.info24.commandsjaxb.Commands;
import se.info24.commandsjaxb.TingcoCommands;
import se.info24.device.DeviceDAO;
import se.info24.devicejaxb.AddressTypeID;
import se.info24.devicejaxb.AgreementID;
import se.info24.devicejaxb.AlarmLevels;
import se.info24.devicejaxb.ChargePointStatusWidgetDetails;
import se.info24.devicejaxb.Connector;
import se.info24.devicejaxb.CountryID;
import se.info24.devicejaxb.DataItemTranslation;
import se.info24.devicejaxb.DataItemTranslationsPerDevice;
import se.info24.devicejaxb.DeviceAddress;
import se.info24.devicejaxb.DeviceDataItemScalings;
import se.info24.devicejaxb.DeviceDataitemTranslation;
import se.info24.devicejaxb.DeviceHistoryDataItem;
import se.info24.devicejaxb.DeviceMessage;
import se.info24.devicejaxb.DeviceOperationsStatus;
import se.info24.devicejaxb.DeviceOperationsStatusTranslations;
import se.info24.devicejaxb.DeviceSetting;
import se.info24.devicejaxb.DeviceSettingsPackageID;
import se.info24.devicejaxb.DeviceSettingsPackages;
import se.info24.devicejaxb.DeviceStatusDataItem;
import se.info24.devicejaxb.DeviceTypeID;
import se.info24.devicejaxb.DeviceTypeManufacturerID;
import se.info24.devicejaxb.Devices;
import se.info24.devicejaxb.EventDetail;
import se.info24.devicejaxb.EventTypeTranslation;
import se.info24.devicejaxb.EventTypes;
import se.info24.devicejaxb.GroupID;
import se.info24.devicejaxb.LastUpdatedByUserID;
import se.info24.devicejaxb.MsgStatus;
import se.info24.devicejaxb.ObjectComment;
import se.info24.devicejaxb.ObjectContact;
import se.info24.devicejaxb.ObjectFactory;
import se.info24.devicejaxb.ObjectFieldData;
import se.info24.devicejaxb.ObjectMediaFile;
import se.info24.devicejaxb.ObjectSettingPackages;
import se.info24.devicejaxb.ObjectSettingTemplates;
import se.info24.devicejaxb.ObjectStateCodePercentage;
import se.info24.devicejaxb.ObjectUsageRecords;
import se.info24.devicejaxb.ProductLink;
import se.info24.devicejaxb.Scaling;
import se.info24.devicejaxb.ServiceDeviceSetting;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.devicejaxb.WarningLevels;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.DeviceActivationSchedules;
import se.info24.ismOperationsPojo.DeviceHistory;
import se.info24.ismOperationsPojo.DeviceHistoryDataItems;
import se.info24.ismOperationsPojo.DeviceMessages;
import se.info24.ismOperationsPojo.DeviceStatus;
import se.info24.ismOperationsPojo.DeviceStatusDataItems;
import se.info24.ismOperationsPojo.EventDetails;
import se.info24.ismOperationsPojo.EventLog;
import se.info24.ismOperationsPojo.ItemConnectionStatus;
import se.info24.ismOperationsPojo.MediaFiles;
import se.info24.ismOperationsPojo.ObjectComments;
import se.info24.ismOperationsPojo.TransactionProducts;
import se.info24.ismOperationsPojo.TransactionResult;
import se.info24.ismOperationsPojo.Widgets2;
import se.info24.pojo.AddressTypeTranslations;
import se.info24.pojo.Addresses;


import se.info24.pojo.BillingCategories;
import se.info24.pojo.BillingCategoryTranslations;

import se.info24.pojo.Channels;
import se.info24.pojo.CommandTranslations;
import se.info24.pojo.ConnectorAcdc;
import se.info24.pojo.ConnectorCurrents;
import se.info24.pojo.ConnectorModes;
import se.info24.pojo.ConnectorTypes;
import se.info24.pojo.ConnectorVoltages;
import se.info24.pojo.Connectors;
import se.info24.pojo.ContactGroups;
import se.info24.pojo.ContainerDevices;
import se.info24.pojo.Country;
import se.info24.pojo.Currency;
import se.info24.pojo.Device;
import se.info24.pojo.DeviceCommandSchedules;
import se.info24.pojo.DeviceDataItemScaling;
import se.info24.pojo.DeviceDataItems;
import se.info24.pojo.DeviceDataitemTranslations;
import se.info24.pojo.DeviceManufacturers;
import se.info24.pojo.DeviceOperationsMember;
import se.info24.pojo.DeviceOperationsStatusTranslation;
import se.info24.pojo.DevicePendingDelete;
import se.info24.pojo.DeviceSettings;
import se.info24.pojo.DeviceSettingsPackagesTranslations;
import se.info24.pojo.DeviceTypeChannels;
import se.info24.pojo.DeviceTypeCommandTranslations;
import se.info24.pojo.DeviceTypeCommands;
import se.info24.pojo.DeviceTypes;
import se.info24.pojo.EventActionSchedules;
import se.info24.pojo.EventItemActions;
import se.info24.pojo.EventItems;
import se.info24.pojo.EventTypeTranslations;
import se.info24.pojo.FieldTranslations;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.LoadBalance;
import se.info24.pojo.MediaFileTypes;
import se.info24.pojo.ObjectContactMemberships;
import se.info24.pojo.ObjectContacts;
import se.info24.pojo.ObjectMediaFiles;
import se.info24.pojo.ObjectSettingPackageTranslations;
import se.info24.pojo.ObjectStateCodeTranslations;
import se.info24.pojo.ObjectTags;
import se.info24.objectpojo.ObjectUsageSummaryReport;
import se.info24.pojo.ObjectUsageUnits;
import se.info24.pojo.PermissionTranslations;
import se.info24.pojo.ProductVariantTranslations;
import se.info24.pojo.RecurrenceTypeTranslations;
import se.info24.pojo.RecurrenceTypes;
import se.info24.pojo.RecurringPurchases;
import se.info24.pojo.Schedules;
import se.info24.pojo.ServiceDeviceSettings;
import se.info24.pojo.SettingDataType;
import se.info24.pojo.TimeZones;
import se.info24.pojo.UserAlias;
import se.info24.pojo.UserFavoriteDataItems;
import se.info24.pojo.UserFavoriteDevices;
import se.info24.pojo.UserRoleObjectPermissions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.Users2;
import se.info24.products.ProductsDAO;
import se.info24.restUtil.RestUtilDAO;
import se.info24.user.UserDAO;
import se.info24.util.TCMUtil;

/**
 *
 * @author Sekhar
 */
public class TingcoDeviceXML {

    private TingcoDevice tingcoDevice;
    private TingcoCommands tingcoCommands;
    ObjectFactory objectFactory;
    se.info24.commandsjaxb.ObjectFactory commandsObjectFactory;
    DecimalFormat df;
    RestUtilDAO utilDAO;
    GroupDAO groupDAO;
    DeviceDAO deviceDAO;
    ProductsDAO productsDAO;
    UserDAO userDAO;

    public TingcoDeviceXML() {
        objectFactory = new ObjectFactory();
        commandsObjectFactory = new se.info24.commandsjaxb.ObjectFactory();
        utilDAO = new RestUtilDAO();
        groupDAO = new GroupDAO();
        deviceDAO = new DeviceDAO();
        productsDAO = new ProductsDAO();
        userDAO = new UserDAO();
        df = new DecimalFormat("#.##");
    }

    public TingcoDevice buildChargePointStatusLocationXML(TingcoDevice tingcoDevice, List<DeviceStatus> deviceStatusList) {
        if (!deviceStatusList.isEmpty()) {
            int seq = 0;
            Devices devices = new Devices();

            se.info24.devicejaxb.DeviceStatus deviceStatus = objectFactory.createDeviceStatus();

            for (DeviceStatus ds : deviceStatusList) {
                se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
                try {
                    dev.setSeqNo(++seq);
                    dev.getContent().add(objectFactory.createDeviceID(ds.getDeviceId()));
                    deviceStatus.setDataItemID(ds.getDataItemId());
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    gc.setTime(ds.getDataItemTime());
                    deviceStatus.setDataItemTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    GroupID groupID = objectFactory.createGroupID();
                    groupID.setValue(ds.getGroupId());
                    dev.getContent().add(groupID);
                    deviceStatus.setIsEnabled(String.valueOf(ds.getIsEnabled()));
                    if (ds.getPosLatitude() != null) {
                        deviceStatus.setPosLatitude(String.valueOf(ds.getPosLatitude()));

                    }
                    if (ds.getPosLongitude() != null) {
                        deviceStatus.setPosLongitude(String.valueOf(ds.getPosLongitude()));

                    }
                    if (ds.getPosDepth() != null) {
                        deviceStatus.setPosDepth(String.valueOf(ds.getPosDepth()));

                    }
                    if (ds.getPosDirection() != null) {
                        deviceStatus.setPosDirection(String.valueOf(ds.getPosDirection()));

                    }
                    deviceStatus.setCoordinateSystemID(ds.getCoordinateSystemId());
                    deviceStatus.setMsgID(ds.getMsgId());
                    gc.setTime(ds.getMsgTimeStamp());
                    deviceStatus.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    deviceStatus.setMsgSenderDeviceID(ds.getMsgSenderDeviceId());
                    deviceStatus.setMsgSenderServiceID(ds.getMsgSenderServiceId());
                    gc.setTime(ds.getCreatedDate());
                    deviceStatus.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    gc.setTime(ds.getUpdatedDate());
                    deviceStatus.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                } catch (DatatypeConfigurationException ex) {
                    TCMUtil.exceptionLog(getClass().getName(), ex);
                }
                dev.getContent().add(deviceStatus);
                devices.getDevice().add(dev);
            }
            tingcoDevice.setDevices(devices);
        }
        return tingcoDevice;
    }

    public TingcoDevice buildContainerDevices(TingcoDevice tingcoDevice, List<ContainerDevices> containerDevicesList, List<se.info24.pojo.Device> devicesList, Session session, Session ismOperationsSession, String userId, int countryId) {
        Devices devices = new Devices();
        for (Device d : devicesList) {
            for (ContainerDevices cd : containerDevicesList) {
                if (d.getDeviceId().equalsIgnoreCase(cd.getDevice().getDeviceId())) {
                    se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
                    device.getContent().add(objectFactory.createDeviceID(d.getDeviceId()));
                    device.getContent().add(objectFactory.createDeviceName(d.getDeviceName()));
                    se.info24.devicejaxb.ItemConnectionStatus itemConnStatus = getItemConnectionStatusByDeviceId(d.getDeviceId(), session, ismOperationsSession, objectFactory, userId);
                    device.getContent().add(itemConnStatus);
                    Set<GroupTranslations> groupTrans = d.getGroups().getGroupTranslationses();
                    for (GroupTranslations gt : groupTrans) {
                        if (d.getGroups().getGroupId().equalsIgnoreCase(gt.getId().getGroupId())) {
                            GroupID groupID = new GroupID();
                            groupID.setValue(d.getGroups().getGroupId());
                            groupID.setGroupName(gt.getGroupName());
                            device.getContent().add(groupID);
                            break;
                        }
                    }
                    DeviceTypes dt = d.getDeviceTypes();
                    DeviceTypeID deviceTypeID = new DeviceTypeID();
                    deviceTypeID.setValue(dt.getDeviceTypeId());
                    deviceTypeID.setDeviceTypeName(dt.getDeviceTypeName());
                    device.getContent().add(deviceTypeID);

                    DeviceDataitemTranslations ddit = deviceDAO.getDeviceDataItemTranslationsById(session, cd.getDeviceDataItemId(), countryId);
                    se.info24.devicejaxb.DeviceDataItems deviceDataItems = new se.info24.devicejaxb.DeviceDataItems();
                    se.info24.devicejaxb.DeviceDataitemTranslations deviceDataItemTrans = new se.info24.devicejaxb.DeviceDataitemTranslations();
                    DeviceDataitemTranslation deviceDataItemTran = new DeviceDataitemTranslation();
                    if (ddit != null) {
                        deviceDataItemTran.setDeviceDataItemID(ddit.getId().getDeviceDataItemId());
                        deviceDataItemTran.setDataItemName(ddit.getDataItemName());
                    }
                    deviceDataItemTrans.getDeviceDataitemTranslation().add(deviceDataItemTran);
                    deviceDataItems.setDeviceDataitemTranslations(deviceDataItemTrans);
                    device.getContent().add(deviceDataItems);
                    devices.getDevice().add(device);
                }
            }
        }
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildDataItemTranslationsPerDevice(TingcoDevice tingcoDevice, List<se.info24.pojo.DataItemTranslationsPerDevice> ditpList, List<Country> countryList) {
        DeviceDataItemScalings ddiss = new DeviceDataItemScalings();
        se.info24.devicejaxb.DeviceDataItemScaling ddis = new se.info24.devicejaxb.DeviceDataItemScaling();
        se.info24.devicejaxb.DataItemTranslationsPerDevice dtp = new se.info24.devicejaxb.DataItemTranslationsPerDevice();
        ddis.setSeqNo(1);
        int seqNo = 1;
        for (Country country : countryList) {
            DataItemTranslation dit = new DataItemTranslation();
            dit.setSeqNo(seqNo++);
            dit.setCountryID(country.getCountryId());
            if (country.getLanguage() != null) {
                dit.setLanguage(country.getLanguage());
            }
            if (!ditpList.isEmpty()) {
                for (se.info24.pojo.DataItemTranslationsPerDevice ditp : ditpList) {
                    if (country.getCountryId() == ditp.getId().getCountryId()) {
                        dit.setDataItemName(ditp.getDataItemName());
                        if (ditp.getUnit() != null) {
                            dit.setUnit(ditp.getUnit());
                        }
                    }
                }
            }
            dtp.getDataItemTranslation().add(dit);
        }
        ddis.getContent().add(dtp);
        ddiss.getDeviceDataItemScaling().add(ddis);
        tingcoDevice.setDeviceDataItemScalings(ddiss);
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceActivationSchedules(TingcoDevice tingcoDevice, List<DeviceActivationSchedules> dasList, Device device, String timeZoneGMToffset) {
        if (!dasList.isEmpty()) {
            int seq = 0;
            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Devices devices = new Devices();
            se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
            dev.setSeqNo(++seq);
            dev.getContent().add(objectFactory.createDeviceID(device.getDeviceId()));
            dev.getContent().add(objectFactory.createDeviceName(device.getDeviceName()));
            se.info24.devicejaxb.DeviceStatus deviceStatus = new se.info24.devicejaxb.DeviceStatus();
            for (DeviceActivationSchedules das : dasList) {
                try {
                    se.info24.devicejaxb.DeviceActivationSchedules dass = new se.info24.devicejaxb.DeviceActivationSchedules();
                    dass.setDeviceActivationID(das.getDeviceActivationId());
                    dass.setDataItem(das.getDataItem());
                    gc.setTime(das.getStartTime());
                    dass.setStartTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, das.getStartTime()));
                    dass.setStartTimeTCMV3(lFormat.format(gc.getTime()));

                    gc.setTime(das.getStopTime());
                    dass.setStopTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));

                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, das.getStopTime()));
                    dass.setStopTimeTCMV3(lFormat.format(gc.getTime()));
                    dass.setUserAlias(das.getUserAlias());
                    deviceStatus.getDeviceActivationSchedules().add(dass);

                } catch (DatatypeConfigurationException ex) {
                    TCMUtil.exceptionLog(getClass().getName(), ex);
                }
            }
            dev.getContent().add(deviceStatus);
            devices.getDevice().add(dev);
            tingcoDevice.setDevices(devices);
        }
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceConnectionStatusXML(TingcoDevice tingcoDevice, List<Object> deviceList, Session ismOperationssession, Session session, String userId, String timeZoneGMToffset, String status, String stateCode) {
        if (!deviceList.isEmpty()) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            int seq = 0;
            Devices devices = new Devices();
            int count = 0;
            int numberOfDevicesConnected = 0;
            int numberOfDevicesDisconnected = 0;
            int numberofDevicesCharging = 0;
            boolean isDeviceCharging = false;
            Integer stateCodeCount = 0;
            float stateCodesum = 0;
            boolean flagstatus = true;
            Map<String, Integer> itemConnStatusStateCodes = new HashMap<String, Integer>();
            for (Iterator itr = deviceList.iterator(); itr.hasNext();) {

                se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
                dev.setSeqNo(++seq);
                String groupId = null;
                String deviceId = null;
                Object[] row = (Object[]) itr.next();
                for (int i = 0; i < row.length; i++) {
                    if (i % 2 == 0) {
                        if (row[i] != null) {
                            deviceId = row[i].toString();
                            dev.getContent().add(objectFactory.createDeviceID(deviceId));
                            List<Connectors> connectorses = deviceDAO.getConnectorsLinkedToDevice(deviceId, session);
                            if (!connectorses.isEmpty()) {
                                dev.getContent().add(objectFactory.createOperation("1"));
                            } else {
                                dev.getContent().add(objectFactory.createOperation("0"));
                            }
                        }
                        if (row[i + 1] != null) {
                            dev.getContent().add(objectFactory.createDeviceName(row[i + 1].toString()));
                        }
                        if (row[i + 2] != null) {
                            dev.getContent().add(objectFactory.createDeviceName2(row[i + 2].toString()));

                        }
                        if (row[i + 3] != null) {
                            groupId = row[i + 3].toString();
                        }
                        if (row[i + 4] != null) {
                            String deviceTypeId = row[i + 4].toString();
                            DeviceTypes dt = (DeviceTypes) deviceDAO.getDeviceTypesById(deviceTypeId, session);
                            DeviceTypeID deviceTypeID = objectFactory.createDeviceTypeID();
                            deviceTypeID.setDeviceTypeName(dt.getDeviceTypeName());

                            deviceTypeID.setValue(deviceTypeId);
                            dev.getContent().add(deviceTypeID);
                        }
                        if (row[i + 5] != null) {
                            String assetId = row[i + 5].toString();
                            if (assetId != null) {
                                dev.getContent().add(objectFactory.createAssetID(assetId));
                            }
                        }

                        i += 5;
                    }
                }
                if (deviceId != null ){//&& groupId != null) {
                    Set<String> deviceid  = new HashSet<String>();
                    deviceid.add(deviceId);
                    DeviceStatus devicestatus = null;
                    List<DeviceStatus> devicestatusList = deviceDAO.getDeviceStatusByDeviceIds(ismOperationssession, deviceid);
                    if(devicestatusList.isEmpty()){
                        devicestatus = null;
                    }else{
                        devicestatus = devicestatusList.get(0);
                    }
//                    DeviceStatus devicestatus = deviceDAO.getDeviceStatus(deviceId, groupId, ismOperationssession);
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                    try {
                        se.info24.devicejaxb.DeviceStatus deviceStatus = objectFactory.createDeviceStatus();
                        if (devicestatus != null) {
                            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, devicestatus.getUpdatedDate()));
//                            gc.setTime(devicestatus.getDataItemTime());
                            deviceStatus.setDataItemTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                            deviceStatus.setDataItemTimeTCMV3(dateFormat.format(gc.getTime()));
                            List<DeviceStatusDataItems> dsdi = deviceDAO.getDeviceStatusDataItemsByDeviceID(deviceId, ismOperationssession);
                            se.info24.devicejaxb.DeviceStatus.DeviceStatusDataItems deviceStatusDataItems = new se.info24.devicejaxb.DeviceStatus.DeviceStatusDataItems();
                            boolean flag = false;
                            if (!dsdi.isEmpty()) {
                                List<String> deviceDataItemIDList = new ArrayList<String>();
                                for (DeviceStatusDataItems ds : dsdi) {
                                    deviceDataItemIDList.add(ds.getDeviceDataItemId());
                                }
                                List<se.info24.pojo.DeviceDataItems> ddiList = deviceDAO.getDeviceDataItemsList(session, deviceDataItemIDList, 0);
                                for (DeviceDataItems ddi : ddiList) {
                                    for (DeviceStatusDataItems ds : dsdi) {
                                        if (ddi.getDeviceDataItemId().equalsIgnoreCase(ds.getId().getDeviceDataItemId())) {
                                            if (ds.getDeviceDataValue().equalsIgnoreCase("1")) {
                                                flag = true;
                                            }
                                            se.info24.devicejaxb.DeviceStatusDataItem deviceStatusDataItem = objectFactory.createDeviceStatusDataItem();
                                            deviceStatusDataItem.setValue(ds.getDeviceDataValue());
                                            deviceStatusDataItem.setUnit(ds.getUnit());
                                            gc.setTime(ds.getActiveFromDate());
                                            deviceStatusDataItem.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                                            deviceStatusDataItem.setDeviceStatusDataItemName(ddi.getDeviceDataFieldName());
                                            deviceStatusDataItems.getDeviceStatusDataItem().add(deviceStatusDataItem);
                                        }
                                    }
                                }
                                deviceStatus.setDeviceStatusDataItems(deviceStatusDataItems);
                                if (flag) {
                                    deviceStatus.setCharging("1");
                                    if (status == null) {
                                        numberofDevicesCharging = numberofDevicesCharging + 1;
                                    }
                                    isDeviceCharging = true;
                                } else {
                                    deviceStatus.setCharging("0");
                                }
                            } else {
                                deviceStatus.setCharging("0");
                            }
                            dev.getContent().add(deviceStatus);
                        }

                    } catch (DatatypeConfigurationException ex) {
                        TCMUtil.exceptionLog(getClass().getName(), ex);
                    }
                    se.info24.devicejaxb.ItemConnectionStatus itemConnStatus = getItemConnectionStatusByDeviceIdsss(deviceId, session, ismOperationssession, objectFactory, userId, timeZoneGMToffset, stateCode);
                    if (stateCode != null) {
                        if (itemConnStatus.getObjectStateCode() != null) {
//                            if (itemConnStatus.getObjectStateCode() != null) {
                            if (stateCode.equalsIgnoreCase("all")) {
                                flagstatus = false;
                                if (itemConnStatusStateCodes.containsKey(itemConnStatus.getObjectStateCode().toUpperCase())) {
                                    stateCodeCount = itemConnStatusStateCodes.get(itemConnStatus.getObjectStateCode().toUpperCase());
                                    itemConnStatusStateCodes.put(itemConnStatus.getObjectStateCode().toUpperCase(), stateCodeCount + 1);
                                } else {
                                    itemConnStatusStateCodes.put(itemConnStatus.getObjectStateCode().toUpperCase(), 1);
                                }
                                stateCodesum++;
                            } else {
                                if (!stateCode.equalsIgnoreCase("DISABLE")) {
                                    if (stateCode.equalsIgnoreCase(itemConnStatus.getObjectStateCode())) {
                                        flagstatus = false;
                                        if (itemConnStatusStateCodes.containsKey(itemConnStatus.getObjectStateCode().toUpperCase())) {
                                            stateCodeCount = itemConnStatusStateCodes.get(itemConnStatus.getObjectStateCode().toUpperCase());
                                            itemConnStatusStateCodes.put(itemConnStatus.getObjectStateCode().toUpperCase(), stateCodeCount + 1);
                                        } else {
                                            itemConnStatusStateCodes.put(itemConnStatus.getObjectStateCode().toUpperCase(), 1);
                                        }
                                        stateCodesum++;
                                    }
                                }
                            }
                        } else {
                            if (stateCode.equalsIgnoreCase("all")) {
                                flagstatus = false;
                                if (itemConnStatusStateCodes.containsKey("DISABLE")) {
                                    stateCodeCount = itemConnStatusStateCodes.get("DISABLE");
                                    itemConnStatusStateCodes.put("DISABLE", stateCodeCount + 1);
                                } else {
                                    itemConnStatusStateCodes.put("DISABLE", 1);
                                }
                                stateCodesum++;
                            } else if (stateCode.equalsIgnoreCase("DISABLE")) {
//                               TCMUtil.loger(getClass().getName(), stateCodesum+"", "Info");
                                flagstatus = false;
                                if (itemConnStatusStateCodes.containsKey("DISABLE")) {
                                    stateCodeCount = itemConnStatusStateCodes.get("DISABLE");
                                    itemConnStatusStateCodes.put("DISABLE", stateCodeCount + 1);
                                } else {
                                    itemConnStatusStateCodes.put("DISABLE", 1);
                                }
                                stateCodesum++;
                            }

                        }

                    }

                    if (status != null) {
                        if (status.equalsIgnoreCase("0")) {
                            if (!isDeviceCharging) {
                                if (itemConnStatus.getConnected() != null) {
                                    if (itemConnStatus.getConnected().equalsIgnoreCase("0")) {
                                        numberOfDevicesDisconnected = numberOfDevicesDisconnected + 1;
                                        dev.getContent().add(itemConnStatus);
                                        ++count;
                                        if (stateCode != null) {
                                            if (!flagstatus) {
                                                devices.getDevice().add(dev);
                                                flagstatus = true;
                                            }
                                        } else {
                                            devices.getDevice().add(dev);
                                        }
                                        if (count == 200) {
                                            break;
                                        }
                                    }
                                } else {
                                    numberOfDevicesDisconnected = numberOfDevicesDisconnected + 1;
                                    dev.getContent().add(itemConnStatus);
                                    ++count;
                                    if (stateCode != null) {
                                        if (!flagstatus) {
                                            devices.getDevice().add(dev);
                                            flagstatus = true;
                                        }
                                    } else {
                                        devices.getDevice().add(dev);
                                    }
                                    if (count == 200) {
                                        break;
                                    }
                                }
                            }
                        } else if (status.equalsIgnoreCase("1")) {
                            if (!isDeviceCharging) {
                                if (itemConnStatus.getConnected() != null) {
                                    if (itemConnStatus.getConnected().equalsIgnoreCase("1")) {
                                        numberOfDevicesConnected = numberOfDevicesConnected + 1;
                                        dev.getContent().add(itemConnStatus);
                                        ++count;
                                        if (stateCode != null) {
                                            if (!flagstatus) {
                                                devices.getDevice().add(dev);
                                                flagstatus = true;
                                            }
                                        } else {
                                            devices.getDevice().add(dev);
                                        }
                                        if (count == 200) {
                                            break;
                                        }
                                    }
                                }
                            }
                        } else if (status.equalsIgnoreCase("2")) {
                            if (isDeviceCharging) {
                                if (itemConnStatus.getConnected() != null) {
//                                    if (itemConnStatus.getConnected().equalsIgnoreCase("1")) {
                                    numberofDevicesCharging = numberofDevicesCharging + 1;
                                    dev.getContent().add(itemConnStatus);
                                    ++count;
                                    if (stateCode != null) {
                                        if (!flagstatus) {
                                            devices.getDevice().add(dev);
                                            flagstatus = true;
                                        }
                                    } else {
                                        devices.getDevice().add(dev);
                                    }
                                    if (count == 200) {
                                        break;
                                    }
//                                    }
                                } else {
                                    numberofDevicesCharging = numberofDevicesCharging + 1;
                                    ++count;
                                    if (stateCode != null) {
                                        if (!flagstatus) {
                                            devices.getDevice().add(dev);
                                            flagstatus = true;
                                        }
                                    } else {
                                        devices.getDevice().add(dev);
                                    }
                                    if (count == 200) {
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        if (!isDeviceCharging) {
                            if (itemConnStatus.getConnected() != null) {
                                if (itemConnStatus.getConnected().equalsIgnoreCase("1")) {
                                    numberOfDevicesConnected = numberOfDevicesConnected + 1;
                                } else if (itemConnStatus.getConnected().equalsIgnoreCase("0")) {
                                    numberOfDevicesDisconnected = numberOfDevicesDisconnected + 1;
                                }
                            } else {
                                numberOfDevicesDisconnected = numberOfDevicesDisconnected + 1;
                            }
                        }
                        dev.getContent().add(itemConnStatus);
                        ++count;

                        if (stateCode != null) {
                            if (!flagstatus) {
                                devices.getDevice().add(dev);
                                flagstatus = true;
                            }
                        } else {
                            devices.getDevice().add(dev);
                        }


                        if (count == 200) {
                            break;
                        }
                    }
                    isDeviceCharging = false;
                }
            }
            float sum = numberOfDevicesConnected + numberOfDevicesDisconnected + numberofDevicesCharging;
            float ConnectedDevicesAverage = (numberOfDevicesConnected / sum) * 100;
            float DisConnectedDevicesAverage = (numberOfDevicesDisconnected / sum) * 100;
            float chargingDevicesAverage = (numberofDevicesCharging / sum) * 100;
            devices.setConnectedDevicesAverageTCMV3(Math.round(ConnectedDevicesAverage));
            devices.setDisConnectedDevicesAverageTCMV3(Math.round(DisConnectedDevicesAverage));
            devices.setChargingDevicesAverageTCMV3(Math.round(chargingDevicesAverage));

            if (stateCode != null) {
                float stateCodeCounter = 0.0f;
                float stateCodePercentage = 0.0f;
//                int counts = 0;
//                int total = 0;
                se.info24.devicejaxb.ItemConnectionStatus ics = new se.info24.devicejaxb.ItemConnectionStatus();
                for (String state : itemConnStatusStateCodes.keySet()) {
//                    counts++;
                    ObjectStateCodePercentage objectStateCode = new ObjectStateCodePercentage();
                    objectStateCode.setobjectStateName(state);
                    stateCodeCounter = itemConnStatusStateCodes.get(state);
                    stateCodePercentage = (stateCodeCounter * 100) / stateCodesum;

//                    if(itemConnStatusStateCodes.keySet().size() == counts){
//                        objectStateCode.setValue(100-total);
//                    }else{
//                        total = total+Math.round(stateCodePercentage);
                    objectStateCode.setValue(Math.round(stateCodePercentage));
//                    }
                    ics.getObjectStateCodePercentage().add(objectStateCode);
                    devices.setItemConnectionStatus(ics);

                }
            }
            tingcoDevice.setDevices(devices);

        }
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceConnectionStatussXML(TingcoDevice tingcoDevice, List<Device> deviceList, Session ismOperationssession, Session session, String userId) {
        if (!deviceList.isEmpty()) {
            int seq = 0;
            Devices devices = new Devices();
            int count = 0;
            for (se.info24.pojo.Device d : deviceList) {
                ++count;
                se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
                dev.setSeqNo(++seq);
                dev.getContent().add(objectFactory.createDeviceID(d.getDeviceId()));
                dev.getContent().add(objectFactory.createDeviceName(d.getDeviceName()));
                dev.getContent().add(objectFactory.createDeviceName2(d.getDeviceName2()));
                if (d.getAssetId() != null) {
                    dev.getContent().add(objectFactory.createAssetID(d.getAssetId()));
                }

                DeviceTypeID deviceTypeID = objectFactory.createDeviceTypeID();
                deviceTypeID.setDeviceTypeName(d.getDeviceTypes().getDeviceTypeName());
                deviceTypeID.setValue(d.getDeviceTypes().getDeviceTypeId());
                dev.getContent().add(deviceTypeID);

                DeviceStatus devicestatus = deviceDAO.getDeviceStatus(d.getDeviceId(), d.getGroups().getGroupId(), ismOperationssession);

                GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                try {
                    if (devicestatus != null) {
                        se.info24.devicejaxb.DeviceStatus deviceStatus = objectFactory.createDeviceStatus();
                        gc.setTime(devicestatus.getDataItemTime());
                        deviceStatus.setDataItemTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));

                        se.info24.devicejaxb.DeviceStatus.DeviceStatusDataItems deviceStatusDataItems = new se.info24.devicejaxb.DeviceStatus.DeviceStatusDataItems();
                        List<DeviceStatusDataItems> dsdi = deviceDAO.getDeviceStatusDataItemsByDeviceID(d.getDeviceId(), ismOperationssession);
                        if (!dsdi.isEmpty()) {
                            for (DeviceStatusDataItems ds : dsdi) {
                                se.info24.devicejaxb.DeviceStatusDataItem deviceStatusDataItem = objectFactory.createDeviceStatusDataItem();
                                List<DeviceDataitemTranslations> ddiList = session.getNamedQuery("getDeviceDataItemTranslationsByDataItemName").setString("deviceDataItemId", ds.getId().getDeviceDataItemId()).list();
                                for (DeviceDataitemTranslations ddi : ddiList) {
                                    deviceStatusDataItem.setDeviceStatusDataItemName(ddi.getDataItemName());
                                    deviceStatusDataItem.setValue(ds.getDeviceDataValue());
                                    deviceStatusDataItem.setUnit(ds.getUnit());
                                    gc = new GregorianCalendar();
                                    gc.setTime(ds.getActiveFromDate());
                                    deviceStatusDataItem.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                                    deviceStatusDataItems.getDeviceStatusDataItem().add(deviceStatusDataItem);
                                    break;
                                }
                            }
                        }
                        deviceStatus.setDeviceStatusDataItems(deviceStatusDataItems);
                        dev.getContent().add(deviceStatus);
                    }
                } catch (DatatypeConfigurationException ex) {
                    TCMUtil.exceptionLog(getClass().getName(), ex);
                }
                se.info24.devicejaxb.ItemConnectionStatus itemConnStatus = getItemConnectionStatusByDeviceId(d.getDeviceId(), session, ismOperationssession, objectFactory, userId);
                dev.getContent().add(itemConnStatus);
                devices.getDevice().add(dev);
                if (count == 200) {
                    break;
                }
            }
            tingcoDevice.setDevices(devices);
        }
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceStatusDataItemsAndWidget2(TingcoDevice tingcoDevice, DeviceStatusDataItems dsdi, Widgets2 widgets2) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.Connectors connectors = new se.info24.devicejaxb.Connectors();
        Connector connector = new Connector();
        ChargePointStatusWidgetDetails cpswd = new ChargePointStatusWidgetDetails();
        if (widgets2.getUnit() != null) {
            cpswd.setUnit(widgets2.getUnit());
        }
        if (widgets2.getWarningLowValue() != null) {
            cpswd.setWarningLowValue(widgets2.getWarningLowValue() + "");
        }
        if (widgets2.getWarningHighValue() != null) {
            cpswd.setWarningHighValue(widgets2.getWarningHighValue() + "");
        }
        if (widgets2.getNumberOfDecimals() == null) {
            cpswd.setDeviceDataValue(dsdi.getDeviceDataValue());
        } else {
            StringBuilder sb = new StringBuilder("#.");
            for (int i = 0; i < widgets2.getNumberOfDecimals(); i++) {
                sb.append("0");
            }
            DecimalFormat dformat = new DecimalFormat(sb.toString());
            dformat.setMaximumFractionDigits(widgets2.getNumberOfDecimals());
            cpswd.setDeviceDataValue(dformat.format(Long.valueOf(dsdi.getDeviceDataValue())));
        }
        connector.getChargePointStatusWidgetDetails().add(cpswd);
        connectors.getConnector().add(connector);
        device.getContent().add(connectors);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceStatusDataItemsByDeviceId(TingcoDevice tingcoDevice, List<DeviceStatusDataItems> deviceStatusList, List<se.info24.pojo.DeviceDataItems> ddiList, Session session) {
        if (!deviceStatusList.isEmpty()) {
            int seq = 0;
            Devices devices = new Devices();
            try {
                se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
                dev.setSeqNo(++seq);
                dev.getContent().add(objectFactory.createDeviceID(deviceStatusList.get(0).getId().getDeviceId()));
                for (DeviceDataItems ddi : ddiList) {
                    for (DeviceStatusDataItems ds : deviceStatusList) {
                        if (ddi.getDeviceDataItemId().equalsIgnoreCase(ds.getId().getDeviceDataItemId())) {
                            se.info24.devicejaxb.DeviceStatus deviceStatus = objectFactory.createDeviceStatus();
                            se.info24.devicejaxb.DeviceStatus.DeviceStatusDataItems deviceStatusDataItems = new se.info24.devicejaxb.DeviceStatus.DeviceStatusDataItems();
                            se.info24.devicejaxb.DeviceStatusDataItem deviceStatusDataItem = objectFactory.createDeviceStatusDataItem();
                            deviceStatusDataItem.setValue(ds.getDeviceDataValue());
                            deviceStatusDataItem.setUnit(ds.getUnit());
                            GregorianCalendar gc = new GregorianCalendar();
                            gc.setTime(ds.getActiveFromDate());
                            deviceStatusDataItem.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                            deviceStatusDataItem.setDeviceStatusDataItemName(ddi.getDeviceDataFieldName());
                            deviceStatusDataItem.setDataItemID(ddi.getDeviceDataItemId());
                            deviceStatusDataItems.getDeviceStatusDataItem().add(deviceStatusDataItem);
                            deviceStatus.setDeviceStatusDataItems(deviceStatusDataItems);
                            dev.getContent().add(deviceStatus);
                            break;
                        }
                    }
                }
                devices.getDevice().add(dev);
                tingcoDevice.setDevices(devices);
            } catch (DatatypeConfigurationException ex) {
                TCMUtil.exceptionLog(getClass().getName(), ex);
            }
        }
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceStatusDataItemsByDeviceId(TingcoDevice tingcoDevice, List<Connectors> connectorses, List<DeviceStatusDataItems> deviceStatusList, List<se.info24.pojo.DeviceDataItems> ddiList, int countryId, Session session) {
//        if (!deviceStatusList.isEmpty()) {
        int seq = 0;
        Devices devices = new Devices();
        try {
            se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
            dev.setSeqNo(++seq);
            dev.getContent().add(objectFactory.createDeviceID(connectorses.get(0).getDeviceId()));
            se.info24.devicejaxb.Connectors connectorsJaxb = new se.info24.devicejaxb.Connectors();
            se.info24.devicejaxb.Connector connectorJaxb = null;
            for (DeviceDataItems ddi : ddiList) {
                for (Connectors connectorse : connectorses) {
                    if (connectorse.getDeviceDataItems().getDeviceDataItemId().equalsIgnoreCase(ddi.getDeviceDataItemId())) {
                        connectorJaxb = new se.info24.devicejaxb.Connector();
                        connectorJaxb.setConnectorID(connectorse.getConnectorId());
                        connectorJaxb.setConnectorName(connectorse.getConnectorName());
                        connectorJaxb.setConnectorModeCode(connectorse.getConnectorModes().getConnectorModeName());
                        connectorJaxb.setConnectorTypeCode(connectorse.getConnectorTypes().getConnectorTypeName());
                        connectorJaxb.setConnectorCurrentID(connectorse.getConnectorCurrents().getConnectorCurrentId());
                        connectorJaxb.setConnectorCurrentCode(connectorse.getConnectorCurrents().getConnectorCurrentName());
                        connectorJaxb.setConnectorVoltageID(connectorse.getConnectorVoltages().getConnectorVoltageId());
                        connectorJaxb.setConnectorVoltageCode(connectorse.getConnectorVoltages().getConnectorVoltageName());
                        for (Object object : connectorse.getProductVariants().getProductVariantTranslationses()) {
                            ProductVariantTranslations productVariantTranslations = (ProductVariantTranslations) object;
                            if (productVariantTranslations.getId().getCountryId() == countryId) {
                                connectorJaxb.setProductVariantName(productVariantTranslations.getProductVariantName());
                                break;
                            }
                        }
                        connectorJaxb.setIsEnabled(connectorse.getIsEnabled());
                        if (connectorse.getInternalComment() != null) {
                            connectorJaxb.setInternalComment(connectorse.getInternalComment());
                        }

                        connectorJaxb.setDeviceDataItemID(ddi.getDeviceDataItemId());
                        connectorJaxb.setDeviceDataItemName(ddi.getDeviceDataFieldName());
                        for (DeviceStatusDataItems ds : deviceStatusList) {
                            if (ddi.getDeviceDataItemId().equalsIgnoreCase(ds.getId().getDeviceDataItemId())) {
                                if (ds.getDeviceDataValue().equalsIgnoreCase("0") || ds.getDeviceDataValue().equalsIgnoreCase("1")) {
                                    connectorJaxb.setDeviceDataItemValue(ds.getDeviceDataValue());
                                    break;
                                }
                            }
                        }
                        connectorsJaxb.getConnector().add(connectorJaxb);
                    }
                }
            }
            dev.getContent().add(connectorsJaxb);
            devices.getDevice().add(dev);
            tingcoDevice.setDevices(devices);
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
//        }
        return tingcoDevice;
    }

    public TingcoDevice buildGetAllDeviceDataItems(TingcoDevice tingcoDevice, List<DeviceDataitemTranslations> deviceDataItemTransList, String userId) {
        Set<UserFavoriteDataItems> userFavoriteDataItemsSet = new HashSet<UserFavoriteDataItems>();
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.DeviceDataItems deviceDataItems = new se.info24.devicejaxb.DeviceDataItems();
        se.info24.devicejaxb.DeviceDataitemTranslations deviceDataItemTrans = new se.info24.devicejaxb.DeviceDataitemTranslations();
        for (DeviceDataitemTranslations ddit : deviceDataItemTransList) {
            DeviceDataitemTranslation ddiTrans = new DeviceDataitemTranslation();
            ddiTrans.setDeviceDataItemID(ddit.getId().getDeviceDataItemId());
            ddiTrans.setDataItemName(ddit.getDataItemName());
            userFavoriteDataItemsSet = ddit.getDeviceDataItems().getUserFavoriteDataItemses();
            if (!userFavoriteDataItemsSet.isEmpty()) {
                for (UserFavoriteDataItems ufdi : userFavoriteDataItemsSet) {
                    if (ufdi.getId().getUserId().equalsIgnoreCase(userId)) {
                        ddiTrans.setIsFavorite(1);
                        userFavoriteDataItemsSet.clear();
                        break;
                    }
                }
            } else {
                ddiTrans.setIsFavorite(0);
            }
            deviceDataItemTrans.getDeviceDataitemTranslation().add(ddiTrans);
        }
        deviceDataItems.setDeviceDataitemTranslations(deviceDataItemTrans);
        device.getContent().add(deviceDataItems);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);

        return tingcoDevice;
    }

    public TingcoDevice buildGetCommandDetailsByDeviceId(TingcoDevice tingcoDevice, List<Object> commandDetailsList) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        for (Iterator itr = commandDetailsList.iterator(); itr.hasNext();) {
            Object[] row = (Object[]) itr.next();
            se.info24.devicejaxb.DeviceCommandSchedules deviceCommandSchedules = new se.info24.devicejaxb.DeviceCommandSchedules();
            se.info24.devicejaxb.Commands commands = new se.info24.devicejaxb.Commands();
            se.info24.devicejaxb.CommandTranslations commandTrans = new se.info24.devicejaxb.CommandTranslations();
            se.info24.devicejaxb.DeviceTypeCommandTranslations dtct = new se.info24.devicejaxb.DeviceTypeCommandTranslations();
            for (int i = 0; i < row.length; i++) {
                if (i % 2 == 0) {
                    if (row[i] != null) {
                        commandTrans.setCommandID(row[i].toString());
                    }
                    if (row[i + 1] != null) {
                        commandTrans.setTargetString(row[i + 1].toString());
                    }
                    if (row[i + 2] != null) {
                        commandTrans.setCommandName(row[i + 2].toString());
                    }
                    if (row[i + 3] != null) {
                        dtct.setDeviceTypeCommandID(row[i + 3].toString());
                    }
                    if (row[i + 4] != null) {
                        dtct.setDeviceTypecommandName(row[i + 4].toString());
                    }
                    i += 4;
                }
                commands.getCommandTranslations().add(commandTrans);
                deviceCommandSchedules.getCommands().add(commands);
                deviceCommandSchedules.getDeviceTypeCommandTranslations().add(dtct);
                device.getContent().add(deviceCommandSchedules);
            }
        }
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildItemConnectionStatus(TingcoDevice tingcoDevice, Object itemConnectionStatusObject) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.ItemConnectionStatus ics = new se.info24.devicejaxb.ItemConnectionStatus();
        if (itemConnectionStatusObject != null) {
            ItemConnectionStatus itemConnectionStatus = (ItemConnectionStatus) itemConnectionStatusObject;
            ics.setConnected(itemConnectionStatus.getConnected() + "");
            if (itemConnectionStatus.getQueueSize() != null) {
                ics.setQueueSize(itemConnectionStatus.getQueueSize());
            }
        } else {
            ics.setConnected(0 + "");
        }
        device.getContent().add(ics);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildObjectUsageErrorReport(TingcoDevice tingcoDevice, List<ObjectUsageSummaryReport> usageErrorRecordsList, String groupingBy) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        for (ObjectUsageSummaryReport ouer : usageErrorRecordsList) {
            ObjectUsageRecords objectUsageRecords = new ObjectUsageRecords();
            if (groupingBy.equalsIgnoreCase("UserKey")) {
                objectUsageRecords.setGroupedBy(ouer.getFirstName() + " " + ouer.getLastName());
            } else {
                objectUsageRecords.setGroupedBy(ouer.getGroupedBy());
            }
            objectUsageRecords.setUsageSessions(ouer.getUsageSessions());
            objectUsageRecords.setSuccessfulSessions(ouer.getSuccessfulSessions());
            objectUsageRecords.setFailedSessions(Integer.valueOf(ouer.getUsageSessions()) - Integer.valueOf(ouer.getSuccessfulSessions()) + "");
            objectUsageRecords.setSuccessfulSessionsPercentage((Integer.valueOf(ouer.getSuccessfulSessions()) * 100 / Integer.valueOf(ouer.getUsageSessions())) + "%");
            device.getContent().add(objectUsageRecords);
        }
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildObjectUsageSummaryReport(TingcoDevice tingcoDevice, List<ObjectUsageSummaryReport> usageReportsList, String groupingBy) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        for (ObjectUsageSummaryReport ousr : usageReportsList) {
            ObjectUsageRecords objectUsageRecords = new ObjectUsageRecords();
            if (groupingBy.equalsIgnoreCase("UserKey")) {
                objectUsageRecords.setGroupedBy(ousr.getFirstName() + " " + ousr.getLastName());

            } else {
                objectUsageRecords.setGroupedBy(ousr.getGroupedBy());
            }
            objectUsageRecords.setUsageSessions(ousr.getUsageSessions());
            objectUsageRecords.setVolume(ousr.getVolume());
            objectUsageRecords.setTotalUsageTimeInMinutes(ousr.getTotalUsageTime());
            objectUsageRecords.setAverageUsageTimeInMinutes(ousr.getAverageUsageTime());
            objectUsageRecords.setUniqueUsers(ousr.getUniqueUsers());
            device.getContent().add(objectUsageRecords);
        }
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoCommands buildGetSMSCommands(TingcoCommands tingcoCommands, List<CommandTranslations> commandTransList) {
        Commands commands = new Commands();
        for (CommandTranslations ct : commandTransList) {
            Command command = new Command();
            command.setcommandID(ct.getId().getCommandId());
            command.setCommandName(ct.getCommandName());
            commands.getCommand().add(command);
        }
        tingcoCommands.setCommands(commands);
        return tingcoCommands;
    }

    public TingcoDevice buildGetSensorMonitors(TingcoDevice tingcoDevice, List<Device> deviceList, List<DeviceStatusDataItems> devStatusDataItem, int countryId, String timeZoneGMToffset, Session session) throws DatatypeConfigurationException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Devices devices = new Devices();
        Set<GroupTranslations> gtSet = new HashSet<GroupTranslations>();
        GroupID groupID;
        DeviceDataitemTranslations ddt;
        GregorianCalendar gc;
        for (Device d : deviceList) {
            for (DeviceStatusDataItems dsdi : devStatusDataItem) {
                if (d.getDeviceId().equalsIgnoreCase(dsdi.getDeviceId())) {
                    se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
                    device.getContent().add(objectFactory.createDeviceID(d.getDeviceId()));
                    device.getContent().add(objectFactory.createDeviceName(d.getDeviceName()));
                    gtSet = d.getGroups().getGroupTranslationses();

                    groupID = new GroupID();
                    gtSet = d.getGroups().getGroupTranslationses();
                    if (gtSet != null) {
                        for (GroupTranslations gt : gtSet) {
                            if (gt.getCountry().getCountryId() == countryId) {
                                groupID.setValue(d.getGroups().getGroupId());
                                groupID.setGroupName(gt.getGroupName());
                                break;
                            }
                        }
                    }
                    device.getContent().add(groupID);
                    ddt = deviceDAO.getDeviceDataItemTranslationsById(session, dsdi.getDeviceDataItemId(), countryId);
                    se.info24.devicejaxb.DeviceDataItems deviceDataItems = new se.info24.devicejaxb.DeviceDataItems();
                    se.info24.devicejaxb.DeviceDataitemTranslations deviceDataItemTrans = new se.info24.devicejaxb.DeviceDataitemTranslations();
                    DeviceDataitemTranslation deviceDataItemTran = new DeviceDataitemTranslation();
                    deviceDataItemTran.setDeviceDataItemID(dsdi.getDeviceDataItemId());
                    if (ddt != null) {
                        if (ddt.getDataItemName() != null) {
                            deviceDataItemTran.setDataItemName(ddt.getDataItemName());
                        }
                    }
                    deviceDataItemTrans.getDeviceDataitemTranslation().add(deviceDataItemTran);
                    deviceDataItems.setDeviceDataitemTranslations(deviceDataItemTrans);
                    device.getContent().add(deviceDataItems);

                    se.info24.devicejaxb.DeviceStatus deviceStatus = objectFactory.createDeviceStatus();
                    se.info24.devicejaxb.DeviceStatus.DeviceStatusDataItems deviceStatusDataItems = new se.info24.devicejaxb.DeviceStatus.DeviceStatusDataItems();
                    se.info24.devicejaxb.DeviceStatusDataItem deviceStatusDataItem = objectFactory.createDeviceStatusDataItem();
                    deviceStatusDataItem.setValue(dsdi.getDeviceDataValue());
                    deviceStatusDataItem.setUnit(dsdi.getUnit());
                    gc = new GregorianCalendar();
                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, dsdi.getActiveFromDate()));
                    deviceStatusDataItem.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    deviceStatusDataItem.setActiveFromDateTCMV3(dateFormat.format(gc.getTime()));
                    deviceStatusDataItems.getDeviceStatusDataItem().add(deviceStatusDataItem);
                    deviceStatus.setDeviceStatusDataItems(deviceStatusDataItems);
                    device.getContent().add(deviceStatus);

                    devices.getDevice().add(device);
                }
            }
        }
        tingcoDevice.setDevices(devices);
        return tingcoDevice;

    }

    public TingcoDevice buildMediaFiles(TingcoDevice tingcoDevice, Object mf) throws SQLException {
        Object[] row = (Object[]) mf;
        se.info24.devicejaxb.ObjectMediaFiles objectMediaFiles = new se.info24.devicejaxb.ObjectMediaFiles();
        ObjectMediaFile objectMediaFile = new ObjectMediaFile();
        se.info24.devicejaxb.MediaFiles mediafiles = new se.info24.devicejaxb.MediaFiles();
        for (int i = 0; i < row.length; i++) {
            mediafiles.setMediaFileID(row[i].toString());
            mediafiles.setMediaFileName(row[i + 1].toString());
            Blob blob = (Blob) row[i + 2];
            byte[] bytes = blob.getBytes(1, (int) blob.length());
            StringBuffer sb = new StringBuffer();
            for (byte b : bytes) {
                sb.append(toBinary(b));
            }
            mediafiles.setMediaFileBlob(sb.toString());
            mediafiles.setMediaFileExtension(row[i + 3].toString());
            i += 3;
        }
        objectMediaFile.getMediaFiles().add(mediafiles);
        objectMediaFiles.getObjectMediaFile().add(objectMediaFile);
        tingcoDevice.setObjectMediaFiles(objectMediaFiles);
        return tingcoDevice;
    }

    public TingcoDevice buildRecurrenceTypes(TingcoDevice tingcoDevice, List<RecurrenceTypes> recurrenceTypesList, int countryId) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        Set<RecurrenceTypeTranslations> rttList = new HashSet<RecurrenceTypeTranslations>();
        for (RecurrenceTypes rt : recurrenceTypesList) {
            se.info24.devicejaxb.RecurrenceTypes recurrenceTypes = new se.info24.devicejaxb.RecurrenceTypes();
            recurrenceTypes.setRecurrenceTypeID(rt.getRecurrenceTypeId());
            recurrenceTypes.setObjectCode(rt.getObjectCode());
            rttList = rt.getRecurrenceTypeTranslationses();
            for (RecurrenceTypeTranslations rtt : rttList) {
                if (rtt.getCountry().getCountryId() == countryId) {
                    se.info24.devicejaxb.RecurrenceTypeTranslations recurrenceTypeTrans = new se.info24.devicejaxb.RecurrenceTypeTranslations();
                    recurrenceTypeTrans.setRecurrenceTypeName(rtt.getRecurrenceTypeName());
                    recurrenceTypes.getRecurrenceTypeTranslations().add(recurrenceTypeTrans);
                    break;
                }
            }
            device.getContent().add(recurrenceTypes);
        }
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildSalesTransationReport(TingcoDevice tingcoDevice, List<Object> transactionResultList, String timeZoneGMToffset) throws DatatypeConfigurationException, ParseException {
        Devices devices = new Devices();
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Iterator itr = transactionResultList.iterator(); itr.hasNext();) {
            Object[] row = (Object[]) itr.next();
            se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
            se.info24.devicejaxb.TransactionResult transactionResult = new se.info24.devicejaxb.TransactionResult();
            se.info24.devicejaxb.TransactionProducts transactionProducts = new se.info24.devicejaxb.TransactionProducts();
            for (int i = 0; i < row.length; i++) {
                if (i % 2 == 0) {
                    if (row[i] != null) {
                        transactionResult.setTransactionID(row[i].toString());
                    }
                    if (row[i + 1] != null) {
                        DeviceAddress deviceAddress = new DeviceAddress();
                        CountryID countryID = new CountryID();
                        countryID.setCountryName(row[i + 1].toString());
                        deviceAddress.setCountryID(countryID);
                        device.getContent().add(deviceAddress);
                    }
                    if (row[i + 2] != null) {
                        GroupID groupID = new GroupID();
                        groupID.setGroupName(row[i + 2].toString());
                        device.getContent().add(groupID);
                    }
                    if (row[i + 3] != null) {
                        device.getContent().add(objectFactory.createOrganizationNumber(row[i + 3].toString()));
                    }
                    if (row[i + 4] != null) {
                        ObjectFieldData objectFieldData = new ObjectFieldData();
                        objectFieldData.setFieldValue(row[i + 4].toString());
                        device.getContent().add(objectFieldData);
                    }
                    if (row[i + 5] != null) {
                        device.getContent().add(objectFactory.createDeviceName(row[i + 5].toString()));
                    }

                    if (row[i + 6] != null) {
                        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, df1.parse(row[i + 6].toString())));
                        transactionResult.setTransactionStartTimev3(df1.format(gc.getTime()));
                        transactionResult.setTransactionStartTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    }
                    if (row[i + 7] != null) {
                        transactionResult.setUserAlias(row[i + 7].toString());
                    }
                    if (row[i + 8] != null) {
                        transactionProducts.setPPU(Float.valueOf(row[i + 8].toString().replace(",", ".")).intValue() + "");
                    }
                    if (row[i + 9] != null) {
                        transactionProducts.setVAT(row[i + 9].toString());
                    }
                    if (row[i + 10] != null) {
                        transactionResult.setCurrencyISOCharCode(row[i + 10].toString());
                    }
//                    if (row[i + 11] != null) {
//                        row[i + 11].toString();
//                    }
                    i += 10;
                }
            }
            device.getContent().add(transactionResult);
            device.getContent().add(transactionProducts);
            devices.getDevice().add(device);
        }
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildUserFavoriteDataItems(TingcoDevice tingcoDevice, List<DeviceDataitemTranslations> deviceDataItemTranslations) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.DeviceDataItems deviceDataItems = new se.info24.devicejaxb.DeviceDataItems();
        se.info24.devicejaxb.DeviceDataitemTranslations ddts = new se.info24.devicejaxb.DeviceDataitemTranslations();
        for (DeviceDataitemTranslations ddt : deviceDataItemTranslations) {
            DeviceDataitemTranslation deviceDataItemTrans = new DeviceDataitemTranslation();
            deviceDataItemTrans.setDeviceDataItemID(ddt.getId().getDeviceDataItemId());
            deviceDataItemTrans.setDataItemName(ddt.getDataItemName());
            ddts.getDeviceDataitemTranslation().add(deviceDataItemTrans);
        }
        deviceDataItems.setDeviceDataitemTranslations(ddts);
        device.getContent().add(deviceDataItems);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    private String toBinary(byte b) {
        StringBuilder sb = new StringBuilder("00000000");
        for (int bit = 0; bit < 8; bit++) {
            if (((b >> bit) & 1) > 0) {
                sb.setCharAt(7 - bit, '1');
            }
        }
        return (sb.toString());
    }

    public se.info24.devicejaxb.ItemConnectionStatus getItemConnectionStatusByDeviceIdsss(String deviceId, Session session, Session ismoperationsSession, ObjectFactory objectFactory, String userId, String timeZoneGMToffset, String stateCode) {
        ItemConnectionStatus ics = (ItemConnectionStatus) deviceDAO.getitemconnectionStatus(deviceId, ismoperationsSession);

        try {
            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

            if (stateCode != null) {
                if (ics != null) {
                    if (stateCode.equalsIgnoreCase("ALL")) {
                        se.info24.devicejaxb.ItemConnectionStatus itemConnStatus = objectFactory.createItemConnectionStatus();
                        itemConnStatus.setConnected(String.valueOf(ics.getConnected()));
                        UserTimeZones2 utz = RestUtilDAO.getUserTimeZones2byUserId(userId, session);
                        TimeZones timeZones = userDAO.getTimeZone(utz.getTimeZoneId(), session);
                        itemConnStatus.setObjectStateCode(ics.getObjectStateCode());
                        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZones.getTimeZoneGmtoffset(), ics.getUpdatedDate()));
                        itemConnStatus.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        itemConnStatus.setUpdatedDateTCMV3(lFormat.format(gc.getTime()));
                        return itemConnStatus;
                    } else if (stateCode.equalsIgnoreCase(ics.getObjectStateCode())) {
                        se.info24.devicejaxb.ItemConnectionStatus itemConnStatus = objectFactory.createItemConnectionStatus();
                        itemConnStatus.setConnected(String.valueOf(ics.getConnected()));
                        UserTimeZones2 utz = RestUtilDAO.getUserTimeZones2byUserId(userId, session);
                        TimeZones timeZones = userDAO.getTimeZone(utz.getTimeZoneId(), session);
                        itemConnStatus.setObjectStateCode(ics.getObjectStateCode());
                        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZones.getTimeZoneGmtoffset(), ics.getUpdatedDate()));
                        itemConnStatus.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        itemConnStatus.setUpdatedDateTCMV3(lFormat.format(gc.getTime()));
                        return itemConnStatus;
                    } else if (stateCode.equalsIgnoreCase("DISABLE")) {

                        se.info24.devicejaxb.ItemConnectionStatus itemConnStatus = objectFactory.createItemConnectionStatus();
                        itemConnStatus.setConnected(String.valueOf(ics.getConnected()));
                        UserTimeZones2 utz = RestUtilDAO.getUserTimeZones2byUserId(userId, session);
                        TimeZones timeZones = userDAO.getTimeZone(utz.getTimeZoneId(), session);
                        itemConnStatus.setObjectStateCode(ics.getObjectStateCode());
                        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZones.getTimeZoneGmtoffset(), ics.getUpdatedDate()));
                        itemConnStatus.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        itemConnStatus.setUpdatedDateTCMV3(lFormat.format(gc.getTime()));
                        return itemConnStatus;
                    }
                }
            } else {
                if (ics != null) {
                    se.info24.devicejaxb.ItemConnectionStatus itemConnStatus = objectFactory.createItemConnectionStatus();
                    itemConnStatus.setConnected(String.valueOf(ics.getConnected()));
                    UserTimeZones2 utz = RestUtilDAO.getUserTimeZones2byUserId(userId, session);
                    TimeZones timeZones = userDAO.getTimeZone(utz.getTimeZoneId(), session);
                    itemConnStatus.setObjectStateCode(ics.getObjectStateCode());
                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZones.getTimeZoneGmtoffset(), ics.getUpdatedDate()));
                    itemConnStatus.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    itemConnStatus.setUpdatedDateTCMV3(lFormat.format(gc.getTime()));
                    return itemConnStatus;
                } else {
                    return objectFactory.createItemConnectionStatus();
                }
            }

        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        return objectFactory.createItemConnectionStatus();

    }

    public se.info24.devicejaxb.ItemConnectionStatus getItemConnectionStatusByDeviceId(String deviceId, Session session, Session ismoperationsSession, ObjectFactory objectFactory, String userId, String timeZoneGMToffset) {
        ItemConnectionStatus ics = (ItemConnectionStatus) deviceDAO.getitemconnectionStatus(deviceId, ismoperationsSession);
        se.info24.devicejaxb.ItemConnectionStatus itemConnStatus = objectFactory.createItemConnectionStatus();
        UserTimeZones2 utz = RestUtilDAO.getUserTimeZones2byUserId(userId, session);
        TimeZones timeZones = userDAO.getTimeZone(utz.getTimeZoneId(), session);
        try {
            if (ics != null) {
                GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                itemConnStatus.setConnected(String.valueOf(ics.getConnected()));
                itemConnStatus.setObjectStateCode(ics.getObjectStateCode());
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZones.getTimeZoneGmtoffset(), ics.getUpdatedDate()));
                itemConnStatus.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                itemConnStatus.setUpdatedDateTCMV3(lFormat.format(gc.getTime()));
            }
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        return itemConnStatus;
    }

    public se.info24.devicejaxb.ItemConnectionStatus getItemConnectionStatusByDeviceId(String deviceId, Session session, Session ismoperationsSession, ObjectFactory objectFactory, String userId) {
        ItemConnectionStatus ics = (ItemConnectionStatus) deviceDAO.getitemconnectionStatus(deviceId, ismoperationsSession);
        se.info24.devicejaxb.ItemConnectionStatus itemConnStatus = objectFactory.createItemConnectionStatus();
        UserTimeZones2 utz = RestUtilDAO.getUserTimeZones2byUserId(userId, session);
        TimeZones timeZones = userDAO.getTimeZone(utz.getTimeZoneId(), session);
        try {
            if (ics != null) {
                GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                itemConnStatus.setConnected(String.valueOf(ics.getConnected()));
                itemConnStatus.setObjectStateCode(ics.getObjectStateCode());
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZones.getTimeZoneGmtoffset(), ics.getUpdatedDate()));
                itemConnStatus.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            }
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        return itemConnStatus;
    }

    public TingcoDevice buildDeviceDataItemTranslations(TingcoDevice tingcoDevice, List<DeviceDataitemTranslations> ddiTransList) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        int seqNo = 1;
        for (DeviceDataitemTranslations ddit : ddiTransList) {
            se.info24.devicejaxb.DeviceDataItems ddi = new se.info24.devicejaxb.DeviceDataItems();
            ddi.setSeqNo(seqNo++);
            se.info24.devicejaxb.DeviceDataitemTranslations ddits = new se.info24.devicejaxb.DeviceDataitemTranslations();
            DeviceDataitemTranslation ddiTrans = new DeviceDataitemTranslation();
            ddiTrans.setSeqNo(1);
            ddiTrans.setDeviceDataItemID(ddit.getId().getDeviceDataItemId());
            ddiTrans.setDataItemName(ddit.getDataItemName());
            CountryID id = new CountryID();
            id.setValue(ddit.getId().getCountryId());
            ddiTrans.setCountryID(id);
            ddits.getDeviceDataitemTranslation().add(ddiTrans);
            ddi.setDeviceDataitemTranslations(ddits);
            device.getContent().add(ddi);
        }
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceDetailsbySearchCriteria(TingcoDevice tingcoDevice, List<Device> deviceList, se.info24.devicejaxbPost.Device deviceXML, String timeZoneGMToffset, Session session) throws DatatypeConfigurationException, ParseException {
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        Devices devices = new Devices();
        GroupID groupID = null;
        Set<GroupTranslations> gtSet = null;
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Device device1 : deviceList) {
            se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
            device.getContent().add(objectFactory.createDeviceID(device1.getDeviceId()));
            device.getContent().add(objectFactory.createDeviceName(device1.getDeviceName()));
            device.getContent().add(objectFactory.createDeviceName2(device1.getDeviceName2()));
            DeviceTypeID deviceTypeID = objectFactory.createDeviceTypeID();
            deviceTypeID.setDeviceTypeName(device1.getDeviceTypes().getDeviceTypeName());
            deviceTypeID.setValue(device1.getDeviceTypes().getDeviceTypeId());
            device.getContent().add(deviceTypeID);
            groupID = new GroupID();
            gtSet = device1.getGroups().getGroupTranslationses();
            if (gtSet != null) {
                for (GroupTranslations gt : gtSet) {
                    if (gt.getCountry().getCountryId() == deviceXML.getCountryID().getValue()) {
                        groupID.setValue(device1.getGroups().getGroupId());
                        groupID.setGroupName(gt.getGroupName());
                        break;
                    }
                }
            }
            device.getContent().add(groupID);
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, device1.getCreatedDate()));
            device.getContent().add(objectFactory.createCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc)));
            device.getContent().add(objectFactory.createCreatedDateTCMV3(lFormat.format(gc.getTime())));
            devices.getDevice().add(device);
        }
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }
    public TingcoDevice buildDevices(TingcoDevice tingcoDevice, List<Device> deviceList, int countryId,Session session) {
        Devices devices = new Devices();
        Set<GroupTranslations> groupTrans = new HashSet<GroupTranslations>();
        for (Device d : deviceList) {
            se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
            device.getContent().add(objectFactory.createDeviceID(d.getDeviceId()));
            device.getContent().add(objectFactory.createDeviceName(d.getDeviceName()));
            Device devicePojo = (Device) session.get(Device.class, d.getDeviceId());

            groupTrans = devicePojo.getGroups().getGroupTranslationses();
            for (GroupTranslations gt : groupTrans) {
                if (gt.getId().getCountryId() == countryId) {
                    GroupID groupID = objectFactory.createGroupID();
                    groupID.setValue(devicePojo.getGroups().getGroupId());
                    groupID.setGroupName(gt.getGroupName());
                    device.getContent().add(groupID);
                    break;
                }
            }
            devices.getDevice().add(device);
        }
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildDevices(TingcoDevice tingcoDevice, List<Device> deviceList, int countryId) {
        Devices devices = new Devices();
        Set<GroupTranslations> groupTrans = new HashSet<GroupTranslations>();
        for (Device d : deviceList) {
            se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
            device.getContent().add(objectFactory.createDeviceID(d.getDeviceId()));
            device.getContent().add(objectFactory.createDeviceName(d.getDeviceName()));
            groupTrans = d.getGroups().getGroupTranslationses();
            for (GroupTranslations gt : groupTrans) {
                if (gt.getId().getCountryId() == countryId) {
                    GroupID groupID = objectFactory.createGroupID();
                    groupID.setValue(d.getGroups().getGroupId());
                    groupID.setGroupName(gt.getGroupName());
                    device.getContent().add(groupID);
                    break;
                }
            }
            devices.getDevice().add(device);
        }
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceHistory(TingcoDevice tingcoDevice, List<DeviceHistory> deviceHistoryList, String timeZoneGMToffset) throws DatatypeConfigurationException {
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        DecimalFormat dformat = new DecimalFormat("0.000000");
        dformat.setMaximumFractionDigits(6);
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        for (DeviceHistory dh : deviceHistoryList) {
            se.info24.devicejaxb.DeviceHistory deviceHistory = new se.info24.devicejaxb.DeviceHistory();
            deviceHistory.setDeviceHistoryID(dh.getDeviceHistoryId());
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, dh.getDataItemTime()));
            deviceHistory.setDataItemTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            deviceHistory.setPosLatitude(dformat.format(dh.getPosLatitude()));
            deviceHistory.setPosLongitude(dformat.format(dh.getPosLongitude()));
            deviceHistory.setPosDepth(String.valueOf(dh.getPosDepth()));
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, dh.getUpdatedDate()));
            deviceHistory.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            device.getContent().add(deviceHistory);
        }
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceList(TingcoDevice tingcoDevice, List<Device> deviceList, List<GroupTranslations> groupTransList) {
        int seq = 0;
        Devices devices = new Devices();
        for (Device d : deviceList) {
            String deviceGroupName = "";
            se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
            dev.setSeqNo(++seq);
            dev.getContent().add(objectFactory.createDeviceID(d.getDeviceId()));
            dev.getContent().add(objectFactory.createDeviceName(d.getDeviceName()));
            deviceGroupName = deviceGroupName + d.getDeviceName();
            for (GroupTranslations gt : groupTransList) {
                if (gt.getId().getGroupId().equalsIgnoreCase(d.getGroups().getGroupId())) {
                    GroupID groupID = new GroupID();
                    groupID.setGroupName(gt.getGroupName());
                    deviceGroupName = deviceGroupName + " (" + gt.getGroupName() + ")";
                    groupID.setValue(gt.getId().getGroupId());
                    dev.getContent().add(groupID);
                    break;
                }
            }
            dev.getContent().add(objectFactory.createDeviceDeviceGroupName(deviceGroupName));
            devices.getDevice().add(dev);
        }
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceOperationsStatusTrans(TingcoDevice tingcoDevice, List<DeviceOperationsStatusTranslation> dostList) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        DeviceOperationsStatus dos = new DeviceOperationsStatus();
        for (DeviceOperationsStatusTranslation dost : dostList) {
            DeviceOperationsStatusTranslations dostrans = new DeviceOperationsStatusTranslations();
            dostrans.setDeviceOperationsStatusID(dost.getId().getDeviceOperationsStatusId());
            dostrans.setCountryID(dost.getCountry().getCountryId());
            dostrans.setDeviceOperationsName(dost.getDeviceOperationsName());
            dostrans.setDeviceOperationsDescription(dost.getDeviceOperationsDescription());
            dos.getDeviceOperationsStatusTranslations().add(dostrans);
        }
        device.getContent().add(dos);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceSettingInfo(TingcoDevice tingcoDevice, DeviceSettings dss, Users2 us, se.info24.pojo.ObjectSettingTemplates ost) throws DatatypeConfigurationException {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        DeviceSetting ds = new DeviceSetting();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        se.info24.devicejaxb.ObjectSettingTemplates osts = new se.info24.devicejaxb.ObjectSettingTemplates();
        if (ost != null) {
            osts.setObjectSettingDescription(ost.getObjectSettingDescription());
        }
        LastUpdatedByUserID userId = new LastUpdatedByUserID();
        String userName = "";
        if (us.getFirstName() != null) {
            userName = us.getFirstName();
        }
        if (us.getLastName() != null) {
            userName = userName + " " + us.getLastName();
        }
        userId.setUserFullName(userName);
        ds.setLastUpdatedByUserID(userId);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dss.getActiveFromDate());
        ds.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
        ds.setActiveFromDateTCMV3(dateFormat.format(gc.getTime()));
        if (dss.getSettingDataType() != null) {
            se.info24.devicejaxb.SettingDataType sdt = new se.info24.devicejaxb.SettingDataType();
            sdt.setSettingDataTypeID(dss.getSettingDataType().getSettingDataTypeId());
            sdt.setSettingDataType(dss.getSettingDataType().getSettingDataType());
            osts.getSettingDataType().add(sdt);
        }
        ds.getObjectSettingTemplates().add(osts);
        device.getContent().add(ds);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceSettingsList(TingcoDevice tingcoDevice, List<DeviceSettings> deviceSettingsList) {
        int seqNo = 0;
        int sdtSeqNo = 1;
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.DeviceSettings deviceSettings = new se.info24.devicejaxb.DeviceSettings();
        for (DeviceSettings ds : deviceSettingsList) {
            DeviceSetting deviceSetting = new DeviceSetting();
            deviceSetting.setSeqNo(++seqNo);
            deviceSetting.setDeviceSettingID(ds.getDeviceSettingId());
            if (ds.getDeviceSettings() != null) {
                DeviceSettings dss = new DeviceSettings();
                dss.setDeviceSettingId(ds.getDeviceSettings().getDeviceSettingId());
                deviceSetting.setDeviceSettingParentID(ds.getDeviceSettings().getDeviceSettingId());
            }
            deviceSetting.setDeviceSettingName(ds.getDeviceSettingName());
            deviceSetting.setDeviceSettingValue(ds.getDeviceSettingValue());
            ObjectSettingTemplates ost = new ObjectSettingTemplates();
            ost.setObjectSettingTemplateID(ds.getObjectSettingTemplateId());
            if (ds.getSettingDataType() != null) {
                se.info24.devicejaxb.SettingDataType sdt = new se.info24.devicejaxb.SettingDataType();
                sdt.setSeqNo(sdtSeqNo);
                sdt.setSettingDataTypeID(ds.getSettingDataType().getSettingDataTypeId());
                ost.getSettingDataType().add(sdt);
            }
            deviceSetting.getObjectSettingTemplates().add(ost);
            deviceSettings.getDeviceSetting().add(deviceSetting);
        }
        device.getContent().add(deviceSettings);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceStatusDataItems(TingcoDevice tingcoDevice, List<DeviceStatusDataItems> deviceStatusList, String timeZoneGMToffset, Session session, List<DeviceDataitemTranslations> deviceDataitemTranslationses) {
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        int seq = 0;
        Devices devices = new Devices();
        try {
            se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
            dev.setSeqNo(++seq);
            se.info24.devicejaxb.DeviceStatus deviceStatus = objectFactory.createDeviceStatus();
            dev.getContent().add(objectFactory.createDeviceID(deviceStatusList.get(0).getId().getDeviceId()));
            se.info24.devicejaxb.DeviceStatus.DeviceStatusDataItems deviceStatusDataItems = new se.info24.devicejaxb.DeviceStatus.DeviceStatusDataItems();

            boolean flag = false;
            List<String> devicedataItemVacantId = new ArrayList<String>();
            for (DeviceStatusDataItems ds : deviceStatusList) {
                flag = false;
                for (DeviceDataitemTranslations deviceDataitemTranslations : deviceDataitemTranslationses) {
                    if (deviceDataitemTranslations.getId().getDeviceDataItemId().equalsIgnoreCase(ds.getId().getDeviceDataItemId())) {
                        if (!devicedataItemVacantId.contains(deviceDataitemTranslations.getId().getDeviceDataItemId())) {
                            flag = true;
                            devicedataItemVacantId.add(deviceDataitemTranslations.getId().getDeviceDataItemId());
                        }
                    }
                }
                if (!flag) {
                    se.info24.devicejaxb.DeviceStatusDataItem deviceStatusDataItem = objectFactory.createDeviceStatusDataItem();
                    deviceStatusDataItem.setValue(ds.getDeviceDataValue());
                    deviceStatusDataItem.setUnit(ds.getUnit());
                    GregorianCalendar gc = new GregorianCalendar();
                    gc.setTime(ds.getActiveFromDate());
                    deviceStatusDataItem.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, ds.getActiveFromDate()));
                    deviceStatusDataItem.setActiveFromDateTCMV3(lFormat.format(gc.getTime()));
                    deviceStatusDataItems.getDeviceStatusDataItem().add(deviceStatusDataItem);
                }

            }
            List<String> devicedataItemId = new ArrayList<String>();

            for (DeviceDataitemTranslations deviceDataitemTranslations : deviceDataitemTranslationses) {
                for (DeviceStatusDataItems ds : deviceStatusList) {
                    if (deviceDataitemTranslations.getId().getDeviceDataItemId().equalsIgnoreCase(ds.getId().getDeviceDataItemId())) {
                        if (!devicedataItemId.contains(deviceDataitemTranslations.getId().getDeviceDataItemId())) {
                            se.info24.devicejaxb.DeviceStatusDataItem deviceStatusDataItem = objectFactory.createDeviceStatusDataItem();
//                            List<DeviceDataitemTranslations> ddi = session.getNamedQuery("getDeviceDataItemTranslationsByDataItemId").setString("deviceDataItemId", ds.getId().getDeviceDataItemId()).list();
//                            if (!ddi.isEmpty()) {
                            deviceStatusDataItem.setDeviceStatusDataItemName(deviceDataitemTranslations.getDataItemName());
//                            }
                            deviceStatusDataItem.setValue(ds.getDeviceDataValue());
                            deviceStatusDataItem.setUnit(ds.getUnit());
                            GregorianCalendar gc = new GregorianCalendar();
                            gc.setTime(ds.getActiveFromDate());
                            deviceStatusDataItem.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, ds.getActiveFromDate()));
                            deviceStatusDataItem.setActiveFromDateTCMV3(lFormat.format(gc.getTime()));
                            deviceStatusDataItems.getDeviceStatusDataItem().add(deviceStatusDataItem);
                            devicedataItemId.add(deviceDataitemTranslations.getId().getDeviceDataItemId());
                        }

                    }
                }

            }

            deviceStatus.setDeviceStatusDataItems(deviceStatusDataItems);
            dev.getContent().add(deviceStatus);
            devices.getDevice().add(dev);
            tingcoDevice.setDevices(devices);
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceStatusDataItemsByDeviceId1(TingcoDevice tingcoDevice, List<DeviceStatusDataItems> deviceStatusList, List<se.info24.pojo.DeviceDataItems> ddiList, List<DeviceActivationSchedules> dasList, Device device, Session session) {
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        int seq = 0;
        Devices devices = new Devices();
        GregorianCalendar gc = new GregorianCalendar();
        try {
            se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
            dev.setSeqNo(++seq);

            dev.getContent().add(objectFactory.createDeviceID(device.getDeviceId()));
            dev.getContent().add(objectFactory.createDeviceName(device.getDeviceName()));

//                dev.getContent().add(objectFactory.createDeviceID(deviceStatusList.get(0).getId().getDeviceId()));

            if (!deviceStatusList.isEmpty() && !ddiList.isEmpty()) {
                for (DeviceDataItems ddi : ddiList) {
                    for (DeviceStatusDataItems ds : deviceStatusList) {
                        if (ddi.getDeviceDataItemId().equalsIgnoreCase(ds.getId().getDeviceDataItemId())) {
                            se.info24.devicejaxb.DeviceStatus deviceStatus = objectFactory.createDeviceStatus();
                            se.info24.devicejaxb.DeviceStatus.DeviceStatusDataItems deviceStatusDataItems = new se.info24.devicejaxb.DeviceStatus.DeviceStatusDataItems();
                            se.info24.devicejaxb.DeviceStatusDataItem deviceStatusDataItem = objectFactory.createDeviceStatusDataItem();
                            deviceStatusDataItem.setValue(ds.getDeviceDataValue());
                            deviceStatusDataItem.setUnit(ds.getUnit());
                            gc.setTime(ds.getActiveFromDate());
                            deviceStatusDataItem.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                            deviceStatusDataItem.setDeviceStatusDataItemName(ddi.getDeviceDataFieldName());
                            deviceStatusDataItem.setDataItemID(ddi.getDeviceDataItemId());
                            if (!dasList.isEmpty()) {
                                for (DeviceActivationSchedules das : dasList) {
                                    if (das.getDataItem().equalsIgnoreCase(ddi.getDeviceDataFieldName())) {
                                        se.info24.devicejaxb.DeviceActivationSchedules dass = new se.info24.devicejaxb.DeviceActivationSchedules();
                                        dass.setDataItem(das.getDataItem());
                                        gc.setTime(das.getStartTime());
                                        dass.setStartTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                                        dass.setStartTimeTCMV3(lFormat.format(gc.getTime()));
                                        gc.setTime(das.getStopTime());
                                        dass.setStopTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                                        dass.setStopTimeTCMV3(lFormat.format(gc.getTime()));
                                        dass.setUserAlias(das.getUserAlias());
                                        deviceStatus.getDeviceActivationSchedules().add(dass);
                                        break;
                                    }
                                }
                            }
                            deviceStatusDataItems.getDeviceStatusDataItem().add(deviceStatusDataItem);
                            deviceStatus.setDeviceStatusDataItems(deviceStatusDataItems);
                            dev.getContent().add(deviceStatus);
                            break;
                        }
                    }
                }
            }
            devices.getDevice().add(dev);
            tingcoDevice.setDevices(devices);
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceTypeCommands(TingcoDevice tingcoDevice, List<DeviceTypeCommands> deviceTypeCommandsList, List<DeviceTypeCommandTranslations> deviceTypeCommandTranslationses) throws DatatypeConfigurationException {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        for (DeviceTypeCommandTranslations dtct : deviceTypeCommandTranslationses) {
            for (DeviceTypeCommands dtc : deviceTypeCommandsList) {
                if (dtc.getDeviceTypeCommandId().equalsIgnoreCase(dtct.getId().getDeviceTypeCommandId())) {
                    se.info24.devicejaxb.DeviceTypeCommands deviceTypeCommands = new se.info24.devicejaxb.DeviceTypeCommands();
                    deviceTypeCommands.setDeviceTypeCommandID(dtc.getDeviceTypeCommandId());
                    deviceTypeCommands.setDeviceDataItemID(dtc.getDeviceDataItemId());
                    deviceTypeCommands.setDataItemValue(dtc.getDataItemValue());
                    deviceTypeCommands.setFlagUserCanSetValue(dtc.getFlagUserCanSetValue());
                    deviceTypeCommands.setFlagIsVisible(dtc.getFlagIsVisible());
                    deviceTypeCommands.setDisplayOrder(dtc.getDisplayOrder());
                    GregorianCalendar gc = new GregorianCalendar();
                    gc.setTime(dtc.getCreatedDate());
                    deviceTypeCommands.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    se.info24.devicejaxb.DeviceTypeCommandTranslations deviceTypeCommandTrans = new se.info24.devicejaxb.DeviceTypeCommandTranslations();
                    deviceTypeCommandTrans.setCommandButtonText(dtct.getCommandButtonText());
                    if(dtct.getCommandDescription() != null){
                        deviceTypeCommandTrans.setCommandDescription(dtct.getCommandDescription());
                    }
                    deviceTypeCommands.getDeviceTypeCommandTranslations().add(deviceTypeCommandTrans);
                    device.getContent().add(deviceTypeCommands);
                }
            }
        }

        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildEventDetails(TingcoDevice tingcoDevice, List<EventDetails> eventDetailsList) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.EventDetails eventDetails = new se.info24.devicejaxb.EventDetails();
        int seqNo = 1;
        for (EventDetails ed : eventDetailsList) {
            EventDetail eventDetail = new EventDetail();
            eventDetail.setSeqNo(seqNo++);
            eventDetail.setName(ed.getId().getName());
            eventDetail.setData(ed.getData());
            eventDetails.getEventDetail().add(eventDetail);
        }
        device.getContent().add(eventDetails);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetDeviceDataItemInformation(DeviceDataItemScaling ddis, se.info24.pojo.DataItemTranslationsPerDevice ditpd, String deviceId, String dataItemId, int countryId, Session session) {
        DeviceDataItemScalings deviceDataItemScalings = new DeviceDataItemScalings();
        se.info24.devicejaxb.DeviceDataItemScaling deviceDataItemScaling = new se.info24.devicejaxb.DeviceDataItemScaling();
        deviceDataItemScaling.getContent().add(objectFactory.createDeviceDataItemScalingDeviceID(deviceId));
        deviceDataItemScaling.getContent().add(objectFactory.createDeviceDataItemScalingDeviceDataItemID(dataItemId));
        DataItemTranslationsPerDevice ditransperdevice = new DataItemTranslationsPerDevice();
        DataItemTranslation dit = new DataItemTranslation();
        if (ditpd != null) {
            dit.setSeqNo(1);
            dit.setCountryID(ditpd.getId().getCountryId());
            dit.setDataItemName(ditpd.getDataItemName());
            if (ditpd.getUnit() != null) {
                dit.setUnit(ditpd.getUnit());
            }
            ditransperdevice.getDataItemTranslation().add(dit);
            deviceDataItemScaling.getContent().add(ditransperdevice);
        } else {
            DeviceDataitemTranslations ddit = deviceDAO.getDeviceDataItemTranslationsById(session, dataItemId, countryId);
            dit.setSeqNo(1);
            dit.setCountryID(countryId);
            if (ddit != null) {
                dit.setDataItemName(ddit.getDataItemName());
            }
            ditransperdevice.getDataItemTranslation().add(dit);
            deviceDataItemScaling.getContent().add(ditransperdevice);
        }
        if (ddis != null) {
            Scaling scaling = new Scaling();
            scaling.getContent().add(objectFactory.createScalingFlagScaleValue(ddis.getFlagScaleValue()));
            if (ddis.getRawMinValue() != null) {
                scaling.getContent().add(objectFactory.createScalingRawMinValue(ddis.getRawMinValue()));
            }
            if (ddis.getRawMaxValue() != null) {
                scaling.getContent().add(objectFactory.createScalingRawMaxValue(ddis.getRawMaxValue()));
            }
            if (ddis.getScaledMinValue() != null) {
                scaling.getContent().add(objectFactory.createScalingScaledMinValue(ddis.getScaledMinValue()));
            }
            if (ddis.getScaledMaxValue() != null) {
                scaling.getContent().add(objectFactory.createScalingScaledMaxValue(ddis.getScaledMaxValue()));
            }
            scaling.getContent().add(objectFactory.createScalingFlagSetValueToMinIfBelowMin(ddis.getFlagSetValueToMinIfBelowMin()));
            scaling.getContent().add(objectFactory.createScalingFlagSetValueToMaxIfAboveMax(ddis.getFlagSetValueToMaxIfAboveMax()));
            scaling.getContent().add(objectFactory.createScalingFlagSaveAsFixedValue(ddis.getFlagSaveAsFixedValue()));
            if (ddis.getFixedValue() != null) {
                scaling.getContent().add(objectFactory.createScalingFixedValue(ddis.getFixedValue()));
            }
            deviceDataItemScaling.getContent().add(scaling);

            WarningLevels warningLevels = new WarningLevels();
            if (ddis.getWarningLowValue() != null) {
                warningLevels.getContent().add(objectFactory.createWarningLevelsWarningLowValue(ddis.getWarningLowValue()));
            }
            if (ddis.getWarningLowEventTypeId() != null) {
                warningLevels.getContent().add(objectFactory.createWarningLevelsWarningLowEventTypeID(ddis.getWarningLowEventTypeId()));
                if (deviceDAO.getEventTypeTranslations(ddis.getWarningLowEventTypeId(), countryId, session) != null) {
                    warningLevels.getContent().add(objectFactory.createWarningLevelsWarningLowEventTypeName(deviceDAO.getEventTypeTranslations(ddis.getWarningLowEventTypeId(), countryId, session).getEventTypeName()));
                }
            }
            if (ddis.getWarningHighValue() != null) {
                warningLevels.getContent().add(objectFactory.createWarningLevelsWarningHighValue(ddis.getWarningHighValue()));
            }
            if (ddis.getWarningHighEventTypeId() != null) {
                warningLevels.getContent().add(objectFactory.createWarningLevelsWarningHighEventTypeID(ddis.getWarningHighEventTypeId()));
                if (deviceDAO.getEventTypeTranslations(ddis.getWarningHighEventTypeId(), countryId, session) != null) {
                    warningLevels.getContent().add(objectFactory.createWarningLevelsWarningHighEventTypeName(deviceDAO.getEventTypeTranslations(ddis.getWarningHighEventTypeId(), countryId, session).getEventTypeName()));
                }
            }
            deviceDataItemScaling.getContent().add(warningLevels);

            AlarmLevels alarmLevels = new AlarmLevels();
            if (ddis.getAlarmLowValue() != null) {
                alarmLevels.getContent().add(objectFactory.createAlarmLevelsAlarmLowValue(ddis.getAlarmLowValue()));
            }
            if (ddis.getAlarmLowEventTypeId() != null) {
                alarmLevels.getContent().add(objectFactory.createAlarmLevelsAlarmLowEventTypeID(ddis.getAlarmLowEventTypeId()));
                alarmLevels.getContent().add(objectFactory.createAlarmLevelsAlarmLowEventTypeName(deviceDAO.getEventTypeTranslations(ddis.getAlarmLowEventTypeId(), countryId, session).getEventTypeName()));
            }
            if (ddis.getAlarmHighValue() != null) {
                alarmLevels.getContent().add(objectFactory.createAlarmLevelsAlarmHighValue(ddis.getAlarmHighValue()));
            }
            if (ddis.getAlarmHighEventTypeId() != null) {
                alarmLevels.getContent().add(objectFactory.createAlarmLevelsAlarmHighEventTypeID(ddis.getAlarmHighEventTypeId()));
                alarmLevels.getContent().add(objectFactory.createAlarmLevelsAlarmHighEventTypeName(deviceDAO.getEventTypeTranslations(ddis.getAlarmHighEventTypeId(), countryId, session).getEventTypeName()));
            }
            deviceDataItemScaling.getContent().add(alarmLevels);

            ProductLink productLink = new ProductLink();
            if (ddis.getProductVariantId() != null) {
                productLink.getContent().add(objectFactory.createProductLinkProductVariantID(ddis.getProductVariantId()));
                if (productsDAO.getProductVariantTranslationsByIds(ddis.getProductVariantId(), countryId, session) != null) {
                    productLink.getContent().add(objectFactory.createProductLinkProductVariantName(productsDAO.getProductVariantTranslationsByIds(ddis.getProductVariantId(), countryId, session).getProductVariantName()));
                }
            }
            if (ddis.getOrderMoreBelowThisValue() != null) {
                productLink.getContent().add(objectFactory.createProductLinkOrderMoreBelowThisValue(ddis.getOrderMoreBelowThisValue()));
            }
            if (ddis.getOrderMoreEventTypeId() != null) {
                productLink.getContent().add(objectFactory.createProductLinkOrderMoreEventTypeID(ddis.getOrderMoreEventTypeId()));
                productLink.getContent().add(objectFactory.createProductLinkOrderMoreEventTypeName(deviceDAO.getEventTypeTranslations(ddis.getOrderMoreEventTypeId(), countryId, session).getEventTypeName()));
            }

            deviceDataItemScaling.getContent().add(productLink);
        }
        deviceDataItemScalings.getDeviceDataItemScaling().add(deviceDataItemScaling);
        tingcoDevice.setDeviceDataItemScalings(deviceDataItemScalings);
        return tingcoDevice;
    }

    public TingcoDevice buildEventTypes(TingcoDevice tingcoDevice, List<EventTypeTranslations> eventTrans) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.ServiceDeviceSettings servDevSet = new se.info24.devicejaxb.ServiceDeviceSettings();
        ServiceDeviceSetting sds = new ServiceDeviceSetting();
        EventTypes eTypes = new EventTypes();
        for (EventTypeTranslations trans : eventTrans) {
            EventTypeTranslation eTypeTrans = new EventTypeTranslation();
            eTypeTrans.setEventTypeID(trans.getId().getEventTypeId());
            eTypeTrans.setEventTypeName(trans.getEventTypeName());
            eTypes.getEventTypeTranslation().add(eTypeTrans);
        }
        sds.getEventTypes().add(eTypes);
        servDevSet.getServiceDeviceSetting().add(sds);
        device.getContent().add(servDevSet);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildObjectComments(TingcoDevice tingcoDevice, List<ObjectComments> objectCommentsList, Device device, Session session) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (!objectCommentsList.isEmpty() && device != null) {
            int seq = 0;
            Devices devices = new Devices();
            try {
                se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
                dev.setSeqNo(++seq);
                dev.getContent().add(objectFactory.createDeviceID(device.getDeviceId()));
                dev.getContent().add(objectFactory.createDeviceName(device.getDeviceName()));
                dev.getContent().add(objectFactory.createDeviceName2(device.getDeviceName2()));
                se.info24.devicejaxb.ObjectComments objectComments = new se.info24.devicejaxb.ObjectComments();
                for (ObjectComments oc : objectCommentsList) {
                    se.info24.devicejaxb.ObjectComment objectComment = new ObjectComment();
                    objectComment.setObjectCommentID(oc.getObjectCommentId());
                    objectComment.setSubject(oc.getSubject());
                    objectComment.setBody(oc.getBody());
                    objectComment.setCreatedByUserID(oc.getCreatedByUserId());
                    GregorianCalendar gc = new GregorianCalendar();
                    gc.setTime(oc.getUpdatedDate());
                    objectComment.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    objectComment.setUpdatedDateTCMV3(dateFormat.format(gc.getTime()));
                    List<se.info24.pojo.Users2> u2List = session.getNamedQuery("getUsers2ById").setString("userID", oc.getCreatedByUserId()).list();
                    for (se.info24.pojo.Users2 u2 : u2List) {
                        objectComment.setUserName(u2.getFirstName() + " " + u2.getLastName());
                    }
                    objectComments.getObjectComment().add(objectComment);
                }
                dev.getContent().add(objectComments);
                devices.getDevice().add(dev);
                tingcoDevice.setDevices(devices);
            } catch (Exception ex) {
                TCMUtil.exceptionLog(getClass().getName(), ex);
            }
        }
        return tingcoDevice;
    }

    public TingcoDevice buildObjectContacts(TingcoDevice tingcoDevice, GroupTranslations groupTrans, List<ObjectContacts> objectContactsList) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        for (ObjectContacts oc : objectContactsList) {
            String fullName = null;
            ObjectContact objectContact = new ObjectContact();
            objectContact.setObjectContactID(oc.getObjectContactId());
            objectContact.setContactFirstName(oc.getContactFirstName());
            objectContact.setContactLastName(oc.getContactLastName());
            fullName = oc.getContactFirstName() + " " + oc.getContactLastName();
            if (oc.getContactOrganizationName() != null) {
                objectContact.setContactOrganizationName(oc.getContactOrganizationName());
                fullName = fullName + " (" + oc.getContactOrganizationName() + ")";
            }
            objectContact.setContactFullName(fullName);
            if (oc.getCountryId() != null && groupTrans != null) {
                objectContact.setCountryID(oc.getCountryId());
            }
            if (groupTrans != null) {
                GroupID groupID = objectFactory.createGroupID();
                groupID.setGroupName(groupTrans.getGroupName());
                groupID.setValue(groupTrans.getId().getGroupId());
                device.getContent().add(groupID);
            }
            device.getContent().add(objectContact);
        }
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildObjectSettingPackages(TingcoDevice tingcoDevice, List<ObjectSettingPackageTranslations> osptList) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        int seqNo = 1;
        for (ObjectSettingPackageTranslations ospt : osptList) {
            ObjectSettingPackages osp = new ObjectSettingPackages();
            osp.setSeqNo(seqNo++);
            se.info24.devicejaxb.ObjectSettingPackageTranslations ospts = new se.info24.devicejaxb.ObjectSettingPackageTranslations();
            ospts.setObjectSettingPackageID(ospt.getId().getObjectSettingPackageId());
            ospts.setCountryID(ospt.getId().getCountryId());
            ospts.setPackageName(ospt.getPackageName());
            osp.getObjectSettingPackageTranslations().add(ospts);
            device.getContent().add(osp);
        }
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildObjectSettingTemplatesForDeviceService(TingcoDevice tingcoDevice, List<se.info24.pojo.ObjectSettingTemplates> ostList) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.ServiceDeviceSettings serviceDeviceSettings = new se.info24.devicejaxb.ServiceDeviceSettings();
        ServiceDeviceSetting serviceDeviceSetting = new ServiceDeviceSetting();
        serviceDeviceSetting.setSeqNo(1);
        for (se.info24.pojo.ObjectSettingTemplates osts : ostList) {
            ObjectSettingTemplates ost = new ObjectSettingTemplates();
            ost.setObjectSettingTemplateID(osts.getObjectSettingTemplateId());
            ost.setObjectSettingName(osts.getObjectSettingName());
            if (osts.getObjectSettingDescription() != null) {
                ost.setObjectSettingDescription(osts.getObjectSettingDescription());
            }
            if (osts.getObjectSettingDefaultValue() != null) {
                ost.setObjectSettingDefaultValue(osts.getObjectSettingDefaultValue());
            }
            SettingDataType sdtype = osts.getSettingDataType();
            se.info24.devicejaxb.SettingDataType sdt = new se.info24.devicejaxb.SettingDataType();
            sdt.setSeqNo(1);
            sdt.setSettingDataTypeID(osts.getSettingDataType().getSettingDataTypeId());
            sdt.setSettingDataType(sdtype.getSettingDataType());
            ost.getSettingDataType().add(sdt);
            serviceDeviceSetting.getObjectSettingTemplates().add(ost);
        }
        serviceDeviceSettings.getServiceDeviceSetting().add(serviceDeviceSetting);
        device.getContent().add(serviceDeviceSettings);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildObjectStateCodes(TingcoDevice tingcoDevice, List<ObjectStateCodeTranslations> osctList) {
        se.info24.devicejaxb.Devices devicesJaxb = new se.info24.devicejaxb.Devices();
        se.info24.devicejaxb.Device deviceJaxb = new se.info24.devicejaxb.Device();
        for (ObjectStateCodeTranslations osct : osctList) {
            se.info24.devicejaxb.ObjectStateCodeTranslations osctJaxb = new se.info24.devicejaxb.ObjectStateCodeTranslations();
            osctJaxb.setObjectStateCodeID(osct.getId().getObjectStateCodeId());
            osctJaxb.setStateCodeName(osct.getStateCodeName());
            deviceJaxb.getContent().add(osctJaxb);
        }
        devicesJaxb.getDevice().add(deviceJaxb);
        tingcoDevice.setDevices(devicesJaxb);
        return tingcoDevice;
    }

    public TingcoDevice buildObjectUsageRecordsForUsageLog(TingcoDevice tingcoDevice, List<Object> objectUsageRecordsList, String timeZoneGMToffset, Session session) throws DatatypeConfigurationException, ParseException {
        GregorianCalendar gc_start = new GregorianCalendar();
        GregorianCalendar gc_stop = new GregorianCalendar();
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Iterator itr = objectUsageRecordsList.iterator(); itr.hasNext();) {
            Object[] row = (Object[]) itr.next();
            se.info24.devicejaxb.ObjectUsageRecords objectUsageRecords = new se.info24.devicejaxb.ObjectUsageRecords();
            for (int i = 0; i < row.length; i++) {
                if (i % 2 == 0) {
                    if (row[i] != null) {
                        objectUsageRecords.setUsageRecordID(row[i].toString());
                    }
                    if (row[i + 1] != null) {
                        objectUsageRecords.setObjectID(row[i + 1].toString());
                    }
                    String deviceId = row[i + 1].toString();
                    if (row[i + 2] != null) {
//                        System.out.println("date is "+row[i + 2].toString());
//                        System.out.println("stop is "+row[i + 3].toString());
                        gc_start.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, lFormat.parse(row[i + 2].toString())));
                        objectUsageRecords.setUsageStartTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc_start));
                        objectUsageRecords.setUsageStartTimeTCMV3(lFormat.format(gc_start.getTime()));

//                        System.out.println("gc is " + gc.getTime());
                    }

                    if (row[i + 3] != null) {
                        long longMinutes;
                        gc_stop.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, lFormat.parse(row[i + 3].toString())));
                        if (((gc_stop.getTimeInMillis() - gc_start.getTimeInMillis()) % (60 * 1000)) != 0) {
                            longMinutes = ((gc_stop.getTimeInMillis() - gc_start.getTimeInMillis()) / (60 * 1000));
//                            System.out.println("logn minutes here is "+longMinutes);
                            longMinutes = longMinutes + 1; // This code is used to covert the seconds with the minutes to round of to the next Minute.
//                            System.out.println("logn minutes after adding 1 is here is "+longMinutes);
                        } else {
                            longMinutes = ((gc_stop.getTimeInMillis() - gc_start.getTimeInMillis()) / (60 * 1000));
//                            System.out.println("logn minutes here else is "+longMinutes);
                        }
                        if (longMinutes == 0) {
                            objectUsageRecords.setTime(1l);
                        } else {
                            objectUsageRecords.setTime(longMinutes);
                        }
                    }

//                    if (row[i + 3] != null) {
//                        long longMinutes;
////                        Date date = lFormat.parse(row[i + 3].toString());
//                        gc_stop.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, lFormat.parse(row[i + 3].toString())));
////                        longMinutes = TimeUnit.MILLISECONDS.toMinutes((date.getTime() - gc.getTimeInMillis()));
//                        if (TimeUnit.MILLISECONDS.toMinutes((gc_stop.getTimeInMillis() - gc_start.getTimeInMillis())) != 0) {
//                            longMinutes = TimeUnit.MILLISECONDS.toMinutes((gc_stop.getTimeInMillis() - gc_start.getTimeInMillis()));
////                            System.out.println("stop time is "+gc_stop.getTimeInMillis()+" startime is "+gc_start.getTime()+" long is "+longMinutes);
////                            longMinutes = (date.getTime() - gc.getTimeInMillis()) / (60 * 1000);
//                            longMinutes = longMinutes + 1; // This code is used to covert the seconds with the minutes to round of to the next Minute.
//                        } else {
//                            longMinutes = TimeUnit.MILLISECONDS.toMinutes((gc_stop.getTimeInMillis() - gc_start.getTimeInMillis()));
////                            System.out.println("here stop time is "+gc_stop.getTimeInMillis()+" startime is "+gc_start.getTime()+" long is "+longMinutes);
////                            longMinutes = (date.getTime() - gc.getTimeInMillis()) / (60 * 1000);
//                        }
////                        System.out.println("gc is "+gc.getTime()+" logn is "+longMinutes);
//                        if (longMinutes == 0) {
//                            objectUsageRecords.setTime(1l);
//                        } else {
//                            objectUsageRecords.setTime(longMinutes);
//                        }
//                    }
//                    System.out.println("stop is "+row[i + 3].toString());
//                    
//                    if (row[i + 4] != null) {
                    Device devic = deviceDAO.getDeviceById(deviceId, session);
                    if (devic != null) {
                        objectUsageRecords.setObjectName(devic.getDeviceName());
                    }
//                    }
                    if (row[i + 4] != null) {
                        objectUsageRecords.setDataItemName(row[i + 4].toString());
                    }
                    if (row[i + 5] != null) {
                        objectUsageRecords.setUsedByUserAlias(row[i + 5].toString());
                    }
                    if (row[i + 6] != null) {
                        objectUsageRecords.setUsageVolume(row[i + 6].toString());
                    }
                    if (row[i + 7] != null) {
                        ObjectUsageUnits ouu = utilDAO.getObjectUsageUnitsById(row[i + 7].toString(), session);
                        objectUsageRecords.setUsageUnitName(ouu.getUsageUnitName());
                    }
                    i += 7;
                }
            }
            device.getContent().add(objectUsageRecords);
//                for (ObjectUsageRecords our : objectUsageRecordsList) {
//                    se.info24.devicejaxb.ObjectUsageRecords objectUsageRecords = new se.info24.devicejaxb.ObjectUsageRecords();
//                    objectUsageRecords.setUsageRecordID(our.getUsageRecordId());
//                    objectUsageRecords.setObjectID(our.getObjectId());
//                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, our.getUsageStartTime()));
//                    objectUsageRecords.setUsageStartTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
//                    long longMinutes;
//                    if ((our.getUsageStopTime().getTime() - our.getUsageStartTime().getTime()) % (60 * 1000) != 0) {
//                        longMinutes = (our.getUsageStopTime().getTime() - our.getUsageStartTime().getTime()) / (60 * 1000);
//                        longMinutes = longMinutes + 1; // This code is used to covert the seconds with the minutes to round of to the next Minute.
//                    } else {
//                        longMinutes = (our.getUsageStopTime().getTime() - our.getUsageStartTime().getTime()) / (60 * 1000);
//                    }
//                    if (longMinutes == 0) {
//                        objectUsageRecords.setTime(1l);
//                    } else {
//                        objectUsageRecords.setTime(longMinutes);
//                    }
//                    Device devic = deviceDAO.getDeviceById(our.getObjectId(), session);
//                    if (devic != null) {
//                        objectUsageRecords.setObjectName(devic.getDeviceName());
//                    }
//                    if (our.getDataItemName() != null) {
//                        objectUsageRecords.setDataItemName(our.getDataItemName());
//                    }
//                    if (our.getUsedByUserAlias() != null) {
//                        objectUsageRecords.setUsedByUserAlias(our.getUsedByUserAlias());
//                    }
//                    if (our.getUsageVolume() != null) {
//                        objectUsageRecords.setUsageVolume(our.getUsageVolume());
//                    }
//                    if (our.getUsageUnitId() != null) {
//                        ObjectUsageUnits ouu = utilDAO.getObjectUsageUnitsById(our.getUsageUnitId(), session);
//                        objectUsageRecords.setUsageUnitName(ouu.getUsageUnitName());
//                    }
//                    device.getContent().add(objectUsageRecords);
//                }
        }
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;

    }

//    public TingcoDevice buildObjectUsageRecordsForUsageLog1(TingcoDevice tingcoDevice, List<ObjectUsageRecords> objectUsageRecordsList, String timeZoneGMToffset, Session session) throws DatatypeConfigurationException {
//        GregorianCalendar gc = new GregorianCalendar();
//        Devices devices = new Devices();
//        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
//        for (ObjectUsageRecords our : objectUsageRecordsList) {
//            se.info24.devicejaxb.ObjectUsageRecords objectUsageRecords = new se.info24.devicejaxb.ObjectUsageRecords();
//            objectUsageRecords.setUsageRecordID(our.getUsageRecordId());
//            objectUsageRecords.setObjectID(our.getObjectId());
//            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, our.getUsageStartTime()));
//            objectUsageRecords.setUsageStartTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
//            long longMinutes;
//            if ((our.getUsageStopTime().getTime() - our.getUsageStartTime().getTime()) % (60 * 1000) != 0) {
//                longMinutes = (our.getUsageStopTime().getTime() - our.getUsageStartTime().getTime()) / (60 * 1000);
//                long min = TimeUnit.MILLISECONDS.toMinutes((our.getUsageStopTime().getTime() - our.getUsageStartTime().getTime()));
//                System.out.println("stop time " + our.getUsageStopTime() + " starttime " + our.getUsageStartTime() + " here is " + min + " longMinutes " + longMinutes);
//                longMinutes = longMinutes + 1; // This code is used to covert the seconds with the minutes to round of to the next Minute.
//            } else {
//                longMinutes = (our.getUsageStopTime().getTime() - our.getUsageStartTime().getTime()) / (60 * 1000);
//                long min = TimeUnit.MILLISECONDS.toMinutes((our.getUsageStopTime().getTime() - our.getUsageStartTime().getTime()));
//                System.out.println("here stop time " + our.getUsageStopTime() + " starttime " + our.getUsageStartTime() + " here is " + min + " longMinutes " + longMinutes);
//            }
////            TimeUnit.MILLISECONDS.toMinutes(longMinutes);
//            if (longMinutes == 0) {
//                objectUsageRecords.setTime(1l);
//            } else {
//                objectUsageRecords.setTime(longMinutes);
//            }
//            Device devic = deviceDAO.getDeviceById(our.getObjectId(), session);
//            if (devic != null) {
//                objectUsageRecords.setObjectName(devic.getDeviceName());
//            }
//            if (our.getDataItemName() != null) {
//                objectUsageRecords.setDataItemName(our.getDataItemName());
//            }
//            if (our.getUsedByUserAlias() != null) {
//                objectUsageRecords.setUsedByUserAlias(our.getUsedByUserAlias());
//            }
//            if (our.getUsageVolume() != null) {
//                objectUsageRecords.setUsageVolume(our.getUsageVolume());
//            }
//            if (our.getUsageUnitId() != null) {
//                ObjectUsageUnits ouu = utilDAO.getObjectUsageUnitsById(our.getUsageUnitId(), session);
//                objectUsageRecords.setUsageUnitName(ouu.getUsageUnitName());
//            }
//            device.getContent().add(objectUsageRecords);
//        }
//        devices.getDevice().add(device);
//        tingcoDevice.setDevices(devices);
//        return tingcoDevice;
//
//    }
    public TingcoDevice buildPropertiesForDeviceType(TingcoDevice tingcoDevice, List<FieldTranslations> ftList) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.ServiceDeviceSettings sdss = new se.info24.devicejaxb.ServiceDeviceSettings();
        for (FieldTranslations fieldTranslations : ftList) {
            se.info24.devicejaxb.Fields fieldsJaxb = new se.info24.devicejaxb.Fields();
            se.info24.devicejaxb.FieldTranslations fieldsTransJaxb = new se.info24.devicejaxb.FieldTranslations();
            fieldsTransJaxb.setFieldID(fieldTranslations.getId().getFieldId());
            fieldsTransJaxb.setFieldName(fieldTranslations.getFieldName());
            fieldsJaxb.getFieldTranslations().add(fieldsTransJaxb);
            device.getContent().add(fieldsJaxb);
        }
        device.getContent().add(sdss);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildServiceDeviceSettings(TingcoDevice tingcoDevice, List<ServiceDeviceSettings> sdsList) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.ServiceDeviceSettings sdss = new se.info24.devicejaxb.ServiceDeviceSettings();
        int seqNo = 1;
        for (ServiceDeviceSettings sds : sdsList) {
            if (sds.getServiceDeviceSettingParentID() == null) {
                ServiceDeviceSetting serviceDeviceSetting = new ServiceDeviceSetting();
                serviceDeviceSetting.setSeqNo(seqNo++);
                serviceDeviceSetting.setServiceDeviceSettingID(sds.getServiceDeviceSettingId());
                serviceDeviceSetting.setServiceDeviceSettingName(sds.getServiceDeviceSettingName());
                serviceDeviceSetting.setServiceDeviceSettingValue(sds.getServiceDeviceSettingValue());
                if (sds.getObjectSettingTemplateId() != null) {
                    ObjectSettingTemplates ost = new ObjectSettingTemplates();
                    ost.setObjectSettingTemplateID(sds.getObjectSettingTemplateId());
                    serviceDeviceSetting.getObjectSettingTemplates().add(ost);
                }
                sdss.getServiceDeviceSetting().add(serviceDeviceSetting);
            }
        }
        device.getContent().add(sdss);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildServiceDeviceSettings(TingcoDevice tingcoDevice, ServiceDeviceSettings sds, se.info24.pojo.ObjectSettingTemplates ost, SettingDataType sdt, Users2 us) throws DatatypeConfigurationException {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.ServiceDeviceSettings serviceDeviceSettings = new se.info24.devicejaxb.ServiceDeviceSettings();
        ServiceDeviceSetting serviceDeviceSetting = new ServiceDeviceSetting();
        serviceDeviceSetting.setSeqNo(1);
        ObjectSettingTemplates osts = new ObjectSettingTemplates();
        if (ost != null) {
            if (ost.getObjectSettingDescription() != null) {
                osts.setObjectSettingDescription(ost.getObjectSettingDescription());
            }
        }
        if (sdt != null) {
            se.info24.devicejaxb.SettingDataType sdtype = new se.info24.devicejaxb.SettingDataType();
            sdtype.setSettingDataTypeID(sds.getSettingDataTypeId());
            sdtype.setSettingDataType(sdt.getSettingDataType());
            osts.getSettingDataType().add(sdtype);
        }
        serviceDeviceSetting.getObjectSettingTemplates().add(osts);
        StringBuffer br = new StringBuffer();
        LastUpdatedByUserID userId = new LastUpdatedByUserID();
        if (us.getFirstName() != null) {
            br.append(us.getFirstName());
        }
        if (us.getLastName() != null) {
            br.append(us.getLastName());
        }
        userId.setUserFullName(br.toString());
        serviceDeviceSetting.setLastUpdatedByUserID(userId);
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        gc.setTime(sds.getActiveFromDate());
        serviceDeviceSetting.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
        serviceDeviceSettings.getServiceDeviceSetting().add(serviceDeviceSetting);
        device.getContent().add(serviceDeviceSettings);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildSettingDataType(TingcoDevice tingcoDevice, List<SettingDataType> settingList) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.DeviceSettings deviceSettings = new se.info24.devicejaxb.DeviceSettings();
        DeviceSetting deviceSetting = new DeviceSetting();
        ObjectSettingTemplates objSettingTemplate = new ObjectSettingTemplates();
        int seqNo = 1;
        for (SettingDataType setting : settingList) {
            se.info24.devicejaxb.SettingDataType settingDataType = new se.info24.devicejaxb.SettingDataType();
            settingDataType.setSeqNo(seqNo++);
            settingDataType.setSettingDataTypeID(setting.getSettingDataTypeId());
            settingDataType.setSettingDataType(setting.getSettingDataType());
            settingDataType.setSettingDataTypeDescription(setting.getSettingDataTypeDescription());
            objSettingTemplate.getSettingDataType().add(settingDataType);
        }
        deviceSetting.getObjectSettingTemplates().add(objSettingTemplate);
        deviceSettings.getDeviceSetting().add(deviceSetting);
        dev.getContent().add(deviceSettings);
        devices.getDevice().add(dev);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildTingcoDeviceDetailsXML(TingcoDevice tingcoDevice, List<Device> device, int countryId, Session session) {
        if (!device.isEmpty()) {
            int seq = 0;
            Devices devices = new Devices();
            for (se.info24.pojo.Device d : device) {
                se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
                dev.setSeqNo(++seq);
                dev.getContent().add(objectFactory.createDeviceID(d.getDeviceId()));
                dev.getContent().add(objectFactory.createDeviceName(d.getDeviceName()));
                dev.getContent().add(objectFactory.createDeviceName2(d.getDeviceName2()));

                DeviceTypeID deviceTypeID = objectFactory.createDeviceTypeID();
                deviceTypeID.setDeviceTypeName(d.getDeviceTypes().getDeviceTypeName());
                deviceTypeID.setValue(d.getDeviceTypes().getDeviceTypeId());
                dev.getContent().add(deviceTypeID);

                if (countryId != 0) {
                    GroupID groupID = objectFactory.createGroupID();
                    Set<GroupTranslations> gtSet = d.getGroups().getGroupTranslationses();
                    for (GroupTranslations gt : gtSet) {
                        if (gt.getCountry().getCountryId() == countryId) {
                            groupID.setGroupName(gt.getGroupName());
                            break;
                        }
                    }
                    groupID.setValue(d.getGroups().getGroupId());
                    dev.getContent().add(groupID);
                }
                ItemConnectionStatus ics = (ItemConnectionStatus) deviceDAO.getitemconnectionStatus(d.getDeviceId(), session);
                if (ics != null) {
                    se.info24.devicejaxb.ItemConnectionStatus itemConnStatus = objectFactory.createItemConnectionStatus();
                    itemConnStatus.setConnected(String.valueOf(ics.getConnected()));
                    itemConnStatus.setObjectStateCode(ics.getObjectStateCode());
                    dev.getContent().add(itemConnStatus);
                }
                devices.getDevice().add(dev);
            }
            tingcoDevice.setDevices(devices);
        }
        return tingcoDevice;
    }

    public TingcoDevice buildTingcoDeviceTemplate() throws DatatypeConfigurationException {
        tingcoDevice = objectFactory.createTingcoDevice();
        tingcoDevice.setCreateRef("Info24");
        tingcoDevice.setMsgVer(new BigDecimal("1.0"));
        tingcoDevice.setRegionalUnits("Metric");
        tingcoDevice.setTimeZone("UTC");

        tingcoDevice.setMsgID(UUID.randomUUID().toString());
        tingcoDevice.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        MsgStatus msgStatus = new MsgStatus();
        msgStatus.setResponseCode(0);
        msgStatus.setResponseText("OK");

        tingcoDevice.setMsgStatus(msgStatus);
        return tingcoDevice;
    }

    public TingcoCommands buildTingcoCommandsTemplate() throws DatatypeConfigurationException {
        tingcoCommands = commandsObjectFactory.createTingcoCommands();
        tingcoCommands.setCreateRef("Info24");
        tingcoCommands.setMsgVer(new BigDecimal("1.0"));
        tingcoCommands.setRegionalUnits("Metric");
        tingcoCommands.setTimeZone("UTC");

        tingcoCommands.setMsgID(UUID.randomUUID().toString());
        tingcoCommands.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        se.info24.commandsjaxb.MsgStatus msgStatus = new se.info24.commandsjaxb.MsgStatus();
        msgStatus.setResponseCode(0);
        msgStatus.setResponseText("OK");

        tingcoCommands.setMsgStatus(msgStatus);
        return tingcoCommands;
    }

    public TingcoDevice buildDeviceID(TingcoDevice tingcoDevice, String deviceId) {
        se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
        dev.setSeqNo(1);
        dev.getContent().add(objectFactory.createDeviceID(deviceId));
        Devices devices = new Devices();
        devices.getDevice().add(dev);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildTincoDeviceXML(TingcoDevice deviceXML, List<Device> list_devices, DevicePendingDelete dpd, List<DeviceOperationsStatusTranslation> dostList, List<ObjectContactMemberships> objectContactMem, List<ObjectContacts> objectContactsList, List<DeviceOperationsMember> deviceOperationsMembersList, ItemConnectionStatus itemConnectionStatus, DeviceStatus deviceStatus, int countryId, String timeZoneGMToffset, ObjectTags objectTags, List<BillingCategoryTranslations> billingCategoryTranslationses) throws DatatypeConfigurationException {
        Devices devices = new Devices();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (Device d : list_devices) {
            se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
            dev.setSeqNo(1);
            dev.getContent().add(objectFactory.createDeviceID(d.getDeviceId()));
            if (objectTags != null) {
                if (objectTags.getSearchTags() != null) {
                    dev.getContent().add(objectFactory.createDeviceObjectTag(objectTags.getSearchTags()));
                }
            }

            if (d.getGroups() != null) {
                Set<GroupTranslations> gtSet = d.getGroups().getGroupTranslationses();
                if (!gtSet.isEmpty()) {
                    GroupID groupID = objectFactory.createGroupID();
                    for (GroupTranslations gt : gtSet) {
                        if (gt.getCountry().getCountryId() == countryId) {
                            groupID.setGroupName(gt.getGroupName());
                            break;
                        }
                    }
                    groupID.setValue(d.getGroups().getGroupId());
                    dev.getContent().add(groupID);
                }
            }
            DeviceTypeID deviceTypeID = objectFactory.createDeviceTypeID();
            deviceTypeID.setDeviceTypeName(d.getDeviceTypes().getDeviceTypeName());
            deviceTypeID.setValue(d.getDeviceTypes().getDeviceTypeId());
            dev.getContent().add(deviceTypeID);
            GregorianCalendar gc = new GregorianCalendar();
            dev.getContent().add(objectFactory.createDeviceName(d.getDeviceName()));
            dev.getContent().add(objectFactory.createDeviceName2(d.getDeviceName2()));
            if (d.getDeviceDescription() != null) {
                dev.getContent().add(objectFactory.createDeviceDescription(d.getDeviceDescription()));
            }
            dev.getContent().add(objectFactory.createDeviceEnabled(d.getDeviceEnabled()));
            dev.getContent().add(objectFactory.createInvoiceDevice(d.getInvoiceDevice()));
            if (d.getAddresses() != null) {
                dev.getContent().add(objectFactory.createAddressID(d.getAddresses().getAddressId()));
            }
            if (d.getShortDescription() != null) {
                dev.getContent().add(objectFactory.createShortdescription(d.getShortDescription()));
            }
            if (d.getAgreements() != null) {
                AgreementID aggId = objectFactory.createAgreementID();
                aggId.setAgreementName(d.getAgreements().getAgreementName());
                aggId.setValue(d.getAgreements().getAgreementId());
                dev.getContent().add(aggId);
            }
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, d.getActiveFromDate()));
            dev.getContent().add(objectFactory.createActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc)));
            dev.getContent().add(objectFactory.createActiveFromDateTCMV3(dateFormat.format(gc.getTime())));

            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, d.getInstalledDate()));
            dev.getContent().add(objectFactory.createInstalledDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc)));
            dev.getContent().add(objectFactory.createInstalledDateTCMV3(dateFormat.format(gc.getTime())));
            if (d.getAssetId() != null) {
                dev.getContent().add(objectFactory.createAssetID(d.getAssetId()));
            }
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, d.getCreatedDate()));
            dev.getContent().add(objectFactory.createCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc)));
            dev.getContent().add(objectFactory.createCreatedDateTCMV3(dateFormat.format(gc.getTime())));
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, d.getUpdatedDate()));
            dev.getContent().add(objectFactory.createUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc)));
            dev.getContent().add(objectFactory.createUpdatedDateTCMV3(dateFormat.format(gc.getTime())));
            if (dpd != null) {
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, dpd.getDeleteDeviceAfterThisDate()));
                dev.getContent().add(objectFactory.createDeleteDeviceAfterThisDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc)));
                dev.getContent().add(objectFactory.createDeleteDeviceAfterThisDateTCMV3(dateFormat.format(gc.getTime())));
            }
            if (itemConnectionStatus != null) {
                se.info24.devicejaxb.ItemConnectionStatus itemConnStatus = new se.info24.devicejaxb.ItemConnectionStatus();
                itemConnStatus.setConnected(String.valueOf(itemConnectionStatus.getConnected()));
                itemConnStatus.setObjectStateCode(itemConnectionStatus.getObjectStateCode());
                dev.getContent().add(itemConnStatus);
            }

            DeviceOperationsStatus dos = new DeviceOperationsStatus();
            for (DeviceOperationsMember dom : deviceOperationsMembersList) {
                for (DeviceOperationsStatusTranslation dost : dostList) {
                    if (dom.getDeviceOperationsStatus().getDeviceOperationsStatusId().equalsIgnoreCase(dost.getId().getDeviceOperationsStatusId())) {
                        DeviceOperationsStatusTranslations dostrans = new DeviceOperationsStatusTranslations();
                        dostrans.setDeviceOperationsStatusID(dost.getId().getDeviceOperationsStatusId());
                        dostrans.setCountryID(dost.getCountry().getCountryId());
                        dostrans.setDeviceOperationsName(dost.getDeviceOperationsName());
                        dostrans.setDeviceOperationsDescription(dost.getDeviceOperationsDescription());
                        dos.getDeviceOperationsStatusTranslations().add(dostrans);
                    }
                }
            }
            dev.getContent().add(dos);

            for (ObjectContacts oc : objectContactsList) {
                ObjectContact objectContact = new ObjectContact();
                objectContact.setObjectContactID(oc.getObjectContactId());
                objectContact.setContactFirstName(oc.getContactFirstName());
                objectContact.setContactLastName(oc.getContactLastName());
                objectContact.setContactEmail(oc.getContactEmail());
                objectContact.setContactPhone(oc.getContactPhone());
                objectContact.setContactMobilePhone(oc.getContactMobilePhone());
                if (oc.getCountryId() != null) {
                    objectContact.setCountryID(oc.getCountryId());
                }
                dev.getContent().add(objectContact);
            }

            if (deviceStatus != null) {
                se.info24.devicejaxb.DeviceStatus ds = new se.info24.devicejaxb.DeviceStatus();
                if (deviceStatus.getPosLatitude() != null) {
                    ds.setPosLatitude(String.valueOf(deviceStatus.getPosLatitude()));
                }
                if (deviceStatus.getPosLongitude() != null) {
                    ds.setPosLongitude(String.valueOf(deviceStatus.getPosLongitude()));
                }
                if (deviceStatus.getPosDepth() != null) {
                    ds.setPosDepth(String.valueOf(deviceStatus.getPosDepth()));
                }
                if (deviceStatus.getPosDirection() != null) {
                    ds.setPosDirection(String.valueOf(deviceStatus.getPosDirection()));
                }
                if (deviceStatus.getUpdatedDate() != null) {
                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, deviceStatus.getUpdatedDate()));
                    ds.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    ds.setUpdatedDateTCMV3(dateFormat.format(gc.getTime()));
                }
                dev.getContent().add(ds);
            }

            Addresses add = d.getAddresses();
            if (add != null) {
                DeviceAddress da = objectFactory.createDeviceAddress();
                if (add.getAddressCity() != null) {
                    da.setAddressCity(add.getAddressCity());
                }
                if (add.getAddressDepth() != null) {
                    da.setAddressDepth(add.getAddressDepth().toString());
                }
                if (add.getAddressDescription() != null) {
                    da.setAddressDescription(add.getAddressDescription());
                }
                if (add.getAddressEmail() != null) {
                    da.setAddressEmail(add.getAddressEmail());
                }
                if (add.getAddressFax() != null) {
                    da.setAddressFax(add.getAddressFax());
                }
                da.setAddressID(add.getAddressId());
                if (add.getAddressIm() != null) {
                    da.setAddressIM(add.getAddressIm());
                }
                if (add.getAddressLatitude() != null) {
                    da.setAddressLatitude(add.getAddressLatitude().toString());
                }
                if (add.getAddressLongitude() != null) {
                    da.setAddressLongitude(add.getAddressLongitude().toString());
                }
                if (add.getAddressMobile() != null) {
                    da.setAddressMobile(add.getAddressMobile());
                }
                if (add.getAddressName() != null) {
                    da.setAddressName(add.getAddressName());
                }
                if (add.getAddressPhone() != null) {
                    da.setAddressPhone(add.getAddressPhone());
                }
                if (add.getAddressRegion() != null) {
                    da.setAddressRegion(add.getAddressRegion());
                }
                if (add.getAddressStreet() != null) {
                    da.setAddressStreet(add.getAddressStreet());
                }
                if (add.getAddressStreet2() != null) {
                    da.setAddressStreet2(add.getAddressStreet2());
                }
                if (add.getAddressSuite() != null) {
                    da.setAddressSuite(add.getAddressSuite());
                }

                if (add.getCountryStateId() != null) {
                    da.setCountryStateID(add.getCountryStateId());
                }
                Set<AddressTypeTranslations> attSet = add.getAddressType().getAddressTranslationses();
                AddressTypeID addType = objectFactory.createAddressTypeID();
                for (AddressTypeTranslations att : attSet) {
                    if (att.getCountry().getCountryId() == countryId) {
                        addType.setAddressTypeName(att.getAddressTypeName());
                        addType.setValue(String.valueOf(att.getId().getAddressTypeId()));
                        da.setAddressTypeID(addType);
                        break;
                    }
                }

                CountryID country = objectFactory.createCountryID();
                country.setCountryName(add.getCountry().getCountryName());
                country.setValue(add.getCountry().getCountryId());
                da.setCountryID(country);

                if (add.getAddressWeb() != null) {
                    da.setAddressWeb(add.getAddressWeb());
                }
                if (add.getAddressZip() != null) {
                    da.setAddressZip(add.getAddressZip());
                }

                dev.getContent().add(da);
            }

            for (BillingCategoryTranslations billingCategoryTranslations : billingCategoryTranslationses) {
                if (billingCategoryTranslations != null) {
                    se.info24.devicejaxb.BillingCategoryTranslations bctJaxb = new se.info24.devicejaxb.BillingCategoryTranslations();
                    bctJaxb.setBillingCategoryID(billingCategoryTranslations.getId().getBillingCategoryId());
                    bctJaxb.setCategoryName(billingCategoryTranslations.getCategoryName());
                    dev.getContent().add(bctJaxb);
                }
            }
            devices.getDevice().add(dev);

        }
        deviceXML.setDevices(devices);
        return deviceXML;
    }

    public TingcoDevice buildDeviceTypes(TingcoDevice tingcoDevice, List<DeviceTypes> deviceTypesList, HashMap<String, Users2> userMap, String isTcm) {

        Devices devices = new Devices();
        se.info24.devicejaxb.Device dev;
        int seqNo = 1;
        Users2 user = null;
        for (DeviceTypes deviceTypes : deviceTypesList) {
            dev = new se.info24.devicejaxb.Device();
            dev.setSeqNo(seqNo++);

            DeviceTypeID deviceTypeID = objectFactory.createDeviceTypeID();
            deviceTypeID.setDeviceTypeName(deviceTypes.getDeviceTypeName());
            deviceTypeID.setValue(deviceTypes.getDeviceTypeId());
            dev.getContent().add(deviceTypeID);
            if (isTcm == null) {
                dev.getContent().add(objectFactory.createDeviceDescription(deviceTypes.getDeviceTypeDescription()));
            }
            DeviceTypeManufacturerID dtManfacId = objectFactory.createDeviceTypeManufacturerID();
            dtManfacId.setDeviceTypeManufacturerName(deviceTypes.getDeviceManufacturers().getDeviceManufacturerName());
            dtManfacId.setValue(deviceTypes.getDeviceManufacturers().getDeviceManufacturerId());
            dev.getContent().add(dtManfacId);

            if (userMap.get(deviceTypes.getUserId()) != null) {
                user = userMap.get(deviceTypes.getUserId());
            }

            if (user != null) {
                LastUpdatedByUserID updatedUser = objectFactory.createLastUpdatedByUserID();
                updatedUser.setUserFullName(user.getFirstName() + " " + user.getLastName());
                updatedUser.setValue(user.getUserId());
                dev.getContent().add(updatedUser);
            }
            devices.getDevice().add(dev);
        }

        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceManfacurers(TingcoDevice tingcoDevice, List<DeviceManufacturers> dmList, HashMap<String, Users2> userMap) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device dev = null;
        int seqNo = 1;
        Users2 user = null;

        for (DeviceManufacturers manfacurer : dmList) {
            dev = new se.info24.devicejaxb.Device();
            dev.setSeqNo(seqNo++);

            DeviceTypeManufacturerID dtManfacId = objectFactory.createDeviceTypeManufacturerID();
            dtManfacId.setDeviceTypeManufacturerName(manfacurer.getDeviceManufacturerName());
            dtManfacId.setValue(manfacurer.getDeviceManufacturerId());
            dev.getContent().add(dtManfacId);

            if (userMap.get(manfacurer.getUserId()) != null) {
                user = userMap.get(manfacurer.getUserId());
            }

            if (user != null) {
                LastUpdatedByUserID updatedUser = objectFactory.createLastUpdatedByUserID();
                updatedUser.setUserFullName(user.getFirstName() + " " + user.getLastName());
                updatedUser.setValue(user.getUserId());
                dev.getContent().add(updatedUser);
            }
            devices.getDevice().add(dev);
        }

        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceStatus(TingcoDevice tingcoDevice, List<DeviceStatus> deviceStatusList, Hashtable<String, Device> deviceMap, Hashtable<String, DeviceDataitemTranslations> dataitemTrans, HashMap<String, List<DeviceStatusDataItems>> deviceStatusMap, int countryId) throws DatatypeConfigurationException {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device dev = null;
        int seqNo = 1;
        Device device;
        se.info24.devicejaxb.DeviceStatus status;
        GregorianCalendar gc = new GregorianCalendar();

        for (DeviceStatus dstatus : deviceStatusList) {
            dev = new se.info24.devicejaxb.Device();
            dev.setSeqNo(seqNo++);

            if (deviceMap.get(dstatus.getDeviceId()) != null) {
                device = deviceMap.get(dstatus.getDeviceId());
                dev.getContent().add(objectFactory.createDeviceID(device.getDeviceId()));

                GroupID groupID = objectFactory.createGroupID();
                Set<GroupTranslations> gtSet = device.getGroups().getGroupTranslationses();
                for (GroupTranslations gt : gtSet) {
                    if (gt.getCountry().getCountryId() == countryId) {
                        groupID.setGroupName(gt.getGroupName());
                        break;
                    }
                }
                groupID.setValue(device.getGroups().getGroupId());
                dev.getContent().add(groupID);
                DeviceTypeID deviceTypeID = objectFactory.createDeviceTypeID();
                deviceTypeID.setDeviceTypeName(device.getDeviceTypes().getDeviceTypeName());
                deviceTypeID.setValue(device.getDeviceTypes().getDeviceTypeId());
                dev.getContent().add(deviceTypeID);
                dev.getContent().add(objectFactory.createDeviceName(device.getDeviceName()));
                dev.getContent().add(objectFactory.createDeviceName2(device.getDeviceName2()));
            }

            status = objectFactory.createDeviceStatus();
            status.setDataItemID(dstatus.getDataItemId());
            gc.setTime(dstatus.getDataItemTime());
            status.setDataItemTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            status.setIsEnabled(String.valueOf(dstatus.getIsEnabled()));
            if (dstatus.getPosLatitude() != null) {
                status.setPosLatitude(String.valueOf(dstatus.getPosLatitude()));
            }
            if (dstatus.getPosLongitude() != null) {
                status.setPosLongitude(String.valueOf(dstatus.getPosLongitude()));
            }
            if (dstatus.getPosDepth() != null) {
                status.setPosDepth(String.valueOf(dstatus.getPosDepth()));
            }
            if (dstatus.getPosDirection() != null) {
                status.setPosDirection(String.valueOf(dstatus.getPosDirection()));
            }
            if (dstatus.getCoordinateSystemId() != null) {
                status.setCoordinateSystemID(dstatus.getCoordinateSystemId());
            }
            status.setMsgID(dstatus.getMsgId());
            if (dstatus.getMsgSenderDeviceId() != null) {
                status.setMsgSenderDeviceID(dstatus.getMsgSenderDeviceId());
            }
            if (dstatus.getMsgSenderServiceId() != null) {
                status.setMsgSenderServiceID(dstatus.getMsgSenderServiceId());
            }
            gc.setTime(dstatus.getMsgTimeStamp());
            status.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            if (dstatus.getCreatedDate() != null) {
                gc.setTime(dstatus.getCreatedDate());
                status.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            }
            if (dstatus.getUpdatedDate() != null) {
                gc.setTime(dstatus.getUpdatedDate());
                status.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            }
            if (deviceStatusMap.get(dstatus.getDeviceId()) != null) {
                se.info24.devicejaxb.DeviceStatus.DeviceStatusDataItems dsdi = objectFactory.createDeviceStatusDeviceStatusDataItems();
                for (DeviceStatusDataItems statusDataItem : deviceStatusMap.get(dstatus.getDeviceId())) {
                    DeviceStatusDataItem dsdataitem = objectFactory.createDeviceStatusDataItem();
                    gc.setTime(statusDataItem.getActiveFromDate());
                    dsdataitem.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    dsdataitem.setValue(statusDataItem.getDeviceDataValue());
                    if (dataitemTrans.get(dstatus.getDataItemId()) != null) {
                        dsdataitem.setDeviceStatusDataItemName(dataitemTrans.get(dstatus.getDataItemId()).getDataItemName());
                    }

                    if (statusDataItem.getDataType() != null) {
                        dsdataitem.setDataType(statusDataItem.getDataType());
                    }
                    if (statusDataItem.getQuality() != null) {
                        dsdataitem.setQuality(statusDataItem.getQuality());
                    }
                    if (statusDataItem.getScaling() != null) {
                        dsdataitem.setScaling(new BigDecimal(statusDataItem.getScaling()));
                    }
                    if (statusDataItem.getMd5() != null) {
                        dsdataitem.setMD5(statusDataItem.getMd5());
                    }
                    if (statusDataItem.getIsEncrypted() != null) {
                        dsdataitem.setEncrypted(new Boolean(String.valueOf(statusDataItem.getIsEncrypted())));
                    }
                    if (statusDataItem.getUnit() != null) {
                        dsdataitem.setUnit(statusDataItem.getUnit());
                    }

                    dsdi.getDeviceStatusDataItem().add(dsdataitem);
                }
                status.setDeviceStatusDataItems(dsdi);
            }

            dev.getContent().add(status);
            devices.getDevice().add(dev);
        }
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceHistory(TingcoDevice tingcoDevice, List<DeviceHistory> dhList, HashMap<String, Device> deviceMap, Hashtable<String, DeviceDataitemTranslations> dataitemTrans, HashMap<String, List<DeviceHistoryDataItems>> dhdiMap, int countryId) throws DatatypeConfigurationException {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device dev = null;
        int seqNo = 1;
        Device device;
        se.info24.devicejaxb.DeviceHistory history;
        GregorianCalendar gc = new GregorianCalendar();
        for (DeviceHistory dh : dhList) {
            dev = new se.info24.devicejaxb.Device();
            dev.setSeqNo(seqNo++);
            if (deviceMap.get(dh.getDeviceId()) != null) {
                device = deviceMap.get(dh.getDeviceId());
                GroupID groupID = objectFactory.createGroupID();
                Set<GroupTranslations> gtSet = device.getGroups().getGroupTranslationses();
                for (GroupTranslations gt : gtSet) {
                    if (gt.getCountry().getCountryId() == countryId) {
                        groupID.setGroupName(gt.getGroupName());
                        break;
                    }
                }
                groupID.setValue(device.getGroups().getGroupId());
                dev.getContent().add(groupID);
                if (device.getDeviceTypes() != null) {
                    DeviceTypeID deviceTypeID = objectFactory.createDeviceTypeID();
                    deviceTypeID.setDeviceTypeName(device.getDeviceTypes().getDeviceTypeName());
                    deviceTypeID.setValue(device.getDeviceTypes().getDeviceTypeId());
                    dev.getContent().add(deviceTypeID);
                }

                dev.getContent().add(objectFactory.createDeviceName(device.getDeviceName()));
                dev.getContent().add(objectFactory.createDeviceName2(device.getDeviceName2()));
            }
            history = objectFactory.createDeviceHistory();
            history.setDataItemID(dh.getDataItemId());
            gc.setTime(dh.getDataItemTime());
            history.setDataItemTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            history.setIsEnabled(String.valueOf(dh.getIsEnabled()));
            if (dh.getPosLatitude() != null) {
                history.setPosLatitude(String.valueOf(dh.getPosLatitude()));
            }
            if (dh.getPosLongitude() != null) {
                history.setPosLongitude(String.valueOf(dh.getPosLongitude()));
            }
            if (dh.getPosDepth() != null) {
                history.setPosDepth(String.valueOf(dh.getPosDepth()));
            }
            if (dh.getPosDirection() != null) {
                history.setPosDirection(String.valueOf(dh.getPosDirection()));
            }
            if (dh.getCoordinateSystemId() != null) {
                history.setCoordinateSystemID(dh.getCoordinateSystemId());
            }
            if (dh.getMsgId() != null) {
                history.setMsgID(dh.getMsgId());
            }
            if (dh.getMsgSenderDeviceId() != null) {
                history.setMsgSenderDeviceID(dh.getMsgSenderDeviceId());
            }
            if (dh.getMsgSenderServiceId() != null) {
                history.setMsgSenderServiceID(dh.getMsgSenderServiceId());
            }
            if (dh.getMsgTimeStamp() != null) {
                gc.setTime(dh.getMsgTimeStamp());
                history.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            }
            gc.setTime(dh.getCreatedDate());
            history.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            gc.setTime(dh.getUpdatedDate());
            history.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            dev.getContent().add(history);
            if (dhdiMap.get(dh.getDeviceId()) != null) {
                se.info24.devicejaxb.Device.DeviceHistoryDataItems dhdiItems = objectFactory.createDeviceDeviceHistoryDataItems();
                for (DeviceHistoryDataItems dhdi : dhdiMap.get(dh.getDeviceId())) {
                    DeviceHistoryDataItem dhdiItem = objectFactory.createDeviceHistoryDataItem();
                    gc.setTime(dhdi.getActiveFromDate());
                    dhdiItem.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    dhdiItem.setValue(dhdi.getDeviceDataValue());
                    if (dataitemTrans.get(dhdi.getDeviceDataItemId()) != null) {
                        dhdiItem.setDeviceHistoryDataItemName(dataitemTrans.get(dhdi.getDeviceDataItemId()).getDataItemName());
                    }

                    if (dhdi.getDataType() != null) {
                        dhdiItem.setDataType(dhdi.getDataType());
                    }
                    if (dhdi.getQuality() != null) {
                        dhdiItem.setQuality(dhdi.getQuality());
                    }
                    if (dhdi.getScaling() != null) {
                        dhdiItem.setScaling(new BigDecimal(dhdi.getScaling()));
                    }
                    if (dhdi.getMd5() != null) {
                        dhdiItem.setMD5(dhdi.getMd5());
                    }
                    if (dhdi.getIsEncrypted() != null) {
                        dhdiItem.setEncrypted(new Boolean(String.valueOf(dhdi.getIsEncrypted())));
                    }
                    if (dhdi.getUnit() != null) {
                        dhdiItem.setUnit(dhdi.getUnit());
                    }
                    if (dhdi.getLanguage() != null) {
                        dhdiItem.setLanguage(dhdi.getLanguage());
                    }
                    dhdiItems.getDeviceHistoryDataItem().add(dhdiItem);
                }
                dev.getContent().add(dhdiItems);
            }
            devices.getDevice().add(dev);
        }
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceSettingsPackages(TingcoDevice tingcoDevice, List<se.info24.pojo.DeviceSettingsPackages> dspList, int countryId) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device dev = null;
        DeviceSettingsPackages dspJaxb = null;
        DeviceSettingsPackageID dspId = null;
        int seqNo = 1;

        for (se.info24.pojo.DeviceSettingsPackages dsp : dspList) {
            dev = new se.info24.devicejaxb.Device();
            dev.setSeqNo(seqNo++);
            dspJaxb = objectFactory.createDeviceSettingsPackages();
            dspId = objectFactory.createDeviceSettingsPackageID();
            Set<DeviceSettingsPackagesTranslations> dsptSet = dsp.getDeviceSettingsPackagesTranslationses();
            for (DeviceSettingsPackagesTranslations dspt : dsptSet) {
                if (dspt.getCountry().getCountryId() == countryId) {
                    dspId.setDeviceSettingsPackageName(dspt.getPackageName());
                    dspId.setValue(dspt.getId().getDeviceSettingsPackageId());
                    dspJaxb.getDeviceSettingsPackageID().add(dspId);
                    dev.getContent().add(dspJaxb);
                    devices.getDevice().add(dev);
                }
            }
        }
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceMessages(TingcoDevice tingcoDevice, HashMap<String, List<DeviceMessages>> sortMessages, HashMap<String, Device> deviceMap, int countryId) throws DatatypeConfigurationException {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device dev = null;

        DeviceMessage dm = null;
        Device device = null;
        int seqNo = 1;
        GregorianCalendar gc = new GregorianCalendar();

        Set<Map.Entry<String, List<DeviceMessages>>> set = sortMessages.entrySet();
        for (Map.Entry<String, List<DeviceMessages>> dml : set) {
            dev = new se.info24.devicejaxb.Device();
            dev.setSeqNo(seqNo++);
            int messageSeqNo = 1;
            se.info24.devicejaxb.DeviceMessages dms = new se.info24.devicejaxb.DeviceMessages();
            String deviceId = dml.getKey();
            dev.getContent().add(objectFactory.createDeviceID(deviceId));

            if (deviceMap.get(deviceId) != null) {
                device = deviceMap.get(deviceId);

                GroupID groupID = objectFactory.createGroupID();
                Set<GroupTranslations> gtSet = device.getGroups().getGroupTranslationses();
                for (GroupTranslations gt : gtSet) {
                    if (gt.getCountry().getCountryId() == countryId) {
                        groupID.setGroupName(gt.getGroupName());
                        break;
                    }
                }

                groupID.setValue(device.getGroups().getGroupId());
                dev.getContent().add(groupID);

                DeviceTypeID deviceTypeID = objectFactory.createDeviceTypeID();
                deviceTypeID.setDeviceTypeName(device.getDeviceTypes().getDeviceTypeName());
                deviceTypeID.setValue(device.getDeviceTypes().getDeviceTypeId());
                dev.getContent().add(deviceTypeID);

                dev.getContent().add(objectFactory.createDeviceName(device.getDeviceName()));
                dev.getContent().add(objectFactory.createDeviceName2(device.getDeviceName2()));
            }


            for (DeviceMessages message : dml.getValue()) {
                dm = new DeviceMessage();
                dm.setSeqNo(messageSeqNo++);
                dm.getContent().add(objectFactory.createDeviceMessageID(message.getDeviceMessageId()));
                if (message.getDataItemId() != null) {
                    dm.getContent().add(objectFactory.createDataItemID(message.getDataItemId()));
                }
                if (message.getSourceReferenceId() != null) {
                    dm.getContent().add(objectFactory.createSourceReferenceID(message.getSourceReferenceId()));
                }
                dm.getContent().add(objectFactory.createMessageText(message.getMessageText()));

                if (message.getPriority() != null) {
                    dm.getContent().add(objectFactory.createPriority(message.getPriority()));
                }
                if (message.getSendToDeviceNow() != null) {
                    dm.getContent().add(objectFactory.createSendToDeviceNow(message.getSendToDeviceNow()));
                }
                if (message.getIsNew() != null) {
                    dm.getContent().add(objectFactory.createIsNew(message.getIsNew()));
                }
                if (message.getIsSentToDevice() != null) {
                    dm.getContent().add(objectFactory.createIsSentToDevice(message.getIsSentToDevice()));
                }
                if (message.getIsReceivedFromDevice() != null) {
                    dm.getContent().add(objectFactory.createIsReceivedFromDevice(message.getIsReceivedFromDevice()));
                }
                if (message.getIsDeleted() != null) {
                    dm.getContent().add(objectFactory.createIsDeleted(message.getIsDeleted()));
                }
                if (message.getIsEmergency() != null) {
                    dm.getContent().add(objectFactory.createIsEmergency(message.getIsEmergency()));
                }
                if (message.getIsEmergencyAck() != null) {
                    dm.getContent().add(objectFactory.createIsEmergencyAck(message.getIsEmergencyAck()));
                }
                if (message.getPosLatitude() != null) {
                    dm.getContent().add(objectFactory.createPosLatitude(message.getPosLatitude().toString()));
                }
                if (message.getPosLongitude() != null) {
                    dm.getContent().add(objectFactory.createPosLongitude(message.getPosLongitude().toString()));
                }
                if (message.getPosDepth() != null) {
                    dm.getContent().add(objectFactory.createPosDepth(message.getPosDepth().toString()));
                }
                if (message.getPosDirection() != null) {
                    dm.getContent().add(objectFactory.createPosDirection(message.getPosDirection().toString()));
                }
                if (message.getDeviceStatus() != null) {
                    dm.getContent().add(objectFactory.createDeviceStatusText(message.getDeviceStatus()));
                }

                if (message.getSentToDeviceDate() != null) {
                    gc.setTime(message.getSentToDeviceDate());
                    dm.getContent().add(objectFactory.createActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc)));
                }
                if (message.getReceivedFromDeviceDate() != null) {
                    gc.setTime(message.getReceivedFromDeviceDate());
                    dm.getContent().add(objectFactory.createInstalledDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc)));
                }
                dms.getDeviceMessage().add(dm);
            }
            dev.getContent().add(dms);
            devices.getDevice().add(dev);
        }
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

//    public TingcoDevice buildTransactionResult(TingcoDevice tingcoDevice, List<TransactionResult> transactionResultList, String timeZoneGMToffset, Session session) throws DatatypeConfigurationException {
//        GregorianCalendar gc = new GregorianCalendar();
//        Devices devices = new Devices();
//        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
//        for (TransactionResult tr : transactionResultList) {
//            se.info24.devicejaxb.TransactionResult transactionResult = new se.info24.devicejaxb.TransactionResult();
//            transactionResult.setTransactionID(tr.getTransactionId());
//            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, tr.getTransactionStartTime()));
//            transactionResult.setTransactionStartTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
//            if (tr.getDeviceName() != null) {
//                transactionResult.setDeviceName(tr.getDeviceName());
//            }
//            StringBuffer br = new StringBuffer();
//            if (tr.getFirstName() != null) {
//                br.append(tr.getFirstName());
//            }
//            if (tr.getLastName() != null) {
//                br.append(" ");
//                br.append(tr.getLastName());
//            }
//            if (tr.getUserAlias() != null) {
//                br.append(" (" + tr.getUserAlias() + ")");
//            }
//            transactionResult.setFirstName(br.toString());
//            transactionResult.setAmount(tr.getAmount().floatValue());
//            if (tr.getProviderName() != null) {
//                transactionResult.setProviderName(tr.getProviderName());
//            }
//            if (tr.getCurrencyId() != null) {
//                Currency currency = utilDAO.getCurrencyById(String.valueOf(tr.getCurrencyId()), session);
//                transactionResult.setCurrencyISOCharCode(currency.getCurrencyIsocharCode());
//            }
//            device.getContent().add(transactionResult);
//        }
//        devices.getDevice().add(device);
//        tingcoDevice.setDevices(devices);
//        return tingcoDevice;
//    }
    public TingcoDevice buildTransactionResult(TingcoDevice tingcoDevice, List<Object> transactionResultList, String timeZoneGMToffset, Session session) throws DatatypeConfigurationException, ParseException {
        GregorianCalendar gc = new GregorianCalendar();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuffer sb = new StringBuffer();
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        for (Iterator itr = transactionResultList.iterator(); itr.hasNext();) {
            Object[] row = (Object[]) itr.next();
            se.info24.devicejaxb.TransactionResult transactionResult = new se.info24.devicejaxb.TransactionResult();
            for (int i = 0; i < row.length; i++) {
                if (i % 2 == 0) {
                    if (row[i] != null) {
                        transactionResult.setTransactionID(row[i].toString());
                    }
                    if (row[i + 1] != null) {
                        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, dateFormat.parse(row[i + 1].toString())));
                        transactionResult.setTransactionStartTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                        transactionResult.setTransactionStartTimev3(dateFormat2.format(gc.getTime()));
                    }
                    if (row[i + 2] != null) {
                        transactionResult.setDeviceName(row[i + 2].toString());
                    }
                    if (row[i + 3] != null) {
                        sb.append(row[i + 3].toString());
                    }
                    if (row[i + 4] != null) {
                        sb.append(" ");
                        sb.append(row[i + 4].toString());
                    }
                    if (row[i + 5] != null) {
                        sb.append("(");
                        sb.append(row[i + 5].toString());
                        sb.append(")");

                    }
                    transactionResult.setFirstName(sb.toString());
                    sb.delete(0, sb.length()); //deleting the stringbuffer content in every iteration so new data will be added in the next iteration

                    if (row[i + 6] != null) {
                        transactionResult.setAmount(Float.valueOf(row[i + 6].toString()));
                    }
                    if (row[i + 7] != null) {
                        transactionResult.setProviderName(row[i + 7].toString());
                    }

                    if (row[i + 8] != null) {
                        Currency currency = utilDAO.getCurrencyById(row[i + 8].toString(), session);
                        transactionResult.setCurrencyISOCharCode(currency.getCurrencyIsocharCode());
                    }
                    i += 8;
                }
            }
            device.getContent().add(transactionResult);
        }
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildTransactionResultForDeviceSalesDetails(TingcoDevice tingcoDevice, List<Object> transactionResultList, int countryId, Session session) throws DatatypeConfigurationException {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        for (Iterator itr = transactionResultList.iterator(); itr.hasNext();) {
            Object[] row = (Object[]) itr.next();
            se.info24.devicejaxb.TransactionResult transactionResult = new se.info24.devicejaxb.TransactionResult();
            for (int i = 0; i < row.length; i++) {
                if (i % 2 == 0) {
                    if (row[i] != null) {
                        transactionResult.setDeviceID(row[i].toString());
                    }
                    if (row[i + 1] != null) {
                        transactionResult.setDeviceName(row[i + 1].toString());
                    }
                    if (row[i + 2] != null) {
                        GroupTranslations groupTranslations = groupDAO.getGroupTranslationsByIds(row[i + 2].toString(), countryId, session);
                        if (groupTranslations != null) {
                            transactionResult.setDeviceGroupName(groupTranslations.getGroupName());
                        }
                    }
                    if (row[i + 3] != null) {
                        transactionResult.setCount(Integer.valueOf(row[i + 3].toString()));
                    }
                    if (row[i + 4] != null) {
                        transactionResult.setAmount(Float.valueOf(row[i + 4].toString()));
                    }
                    if (row[i + 5] != null) {
                        Currency currency = utilDAO.getCurrencyById(row[i + 5].toString(), session);
                        if (currency != null) {
                            transactionResult.setCurrencyISOCharCode(currency.getCurrencyIsocharCode());
                        }
                    }
                    i += 5;
                }
            }
            device.getContent().add(transactionResult);
        }
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildTransactionResultForSalesByProvider(TingcoDevice tingcoDevice, List<Object> transactionResultList, int countryId, Session session) throws DatatypeConfigurationException {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        for (Iterator itr = transactionResultList.iterator(); itr.hasNext();) {
            Object[] row = (Object[]) itr.next();
            se.info24.devicejaxb.TransactionResult transactionResult = new se.info24.devicejaxb.TransactionResult();
            for (int i = 0; i < row.length; i++) {
                if (i % 2 == 0) {
                    if (row[i] != null) {
                        transactionResult.setProviderID(row[i].toString());
                    }
                    if (row[i + 1] != null) {
                        transactionResult.setProviderName(row[i + 1].toString());
                    }
                    if (row[i + 2] != null) {
                        transactionResult.setCount(Integer.valueOf(row[i + 2].toString()));
                    }
                    if (row[i + 3] != null) {
                        transactionResult.setAmount(Float.valueOf(row[i + 3].toString()));
                    }
                    if (row[i + 4] != null) {
                        Currency currency = utilDAO.getCurrencyById(row[i + 4].toString(), session);
                        if (currency != null) {
                            transactionResult.setCurrencyISOCharCode(currency.getCurrencyIsocharCode());
                        }
                    }
                    i += 4;
                }
            }
            device.getContent().add(transactionResult);
        }
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetUserSalesDetails(TingcoDevice tingcoDevice, Hashtable<String, List<se.info24.ismOperationsPojo.TransactionResult>> TR, List<Currency> currencyList, List<GroupTranslations> gtLists, Hashtable<String, Float> userAmount, Hashtable<String, Integer> userNumber) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        Set<String> useraliaID = TR.keySet();
        for (String string : useraliaID) {
            List<TransactionResult> transactionResultList = TR.get(string);
            for (TransactionResult transactionResult : transactionResultList) {
                se.info24.devicejaxb.TransactionResult transactionResultjaxb = new se.info24.devicejaxb.TransactionResult();
                transactionResultjaxb.setFirstName(transactionResult.getFirstName());
                transactionResultjaxb.setLastName(transactionResult.getLastName());
                transactionResultjaxb.setUserAlias(transactionResult.getUserAlias());
                transactionResultjaxb.setFullName(transactionResult.getFirstName() + " " + transactionResult.getLastName() + " (" + transactionResult.getUserAlias() + ")");
                transactionResultjaxb.setUserAliasID(transactionResult.getUserAliasId());
                for (GroupTranslations groupTranslations : gtLists) {
                    if (groupTranslations.getGroups().getGroupId().equalsIgnoreCase(transactionResult.getUserGroupId())) {
                        transactionResultjaxb.setGroupName(groupTranslations.getGroupName());
                    }
                }

                transactionResultjaxb.setAmount(userAmount.get(transactionResult.getUserAliasId() + "," + transactionResult.getCurrencyId()));
                transactionResultjaxb.setCount(userNumber.get(transactionResult.getUserAliasId() + "," + transactionResult.getCurrencyId()));
                for (Currency currency : currencyList) {
                    if (currency != null) {
                        if (transactionResult.getCurrencyId() != null) {
                            if (currency.getCurrencyId() == transactionResult.getCurrencyId()) {
                                transactionResultjaxb.setCurrencyISOCharCode(currency.getCurrencyIsocharCode());
                            }
                        }
                    }
                }
                device.getContent().add(transactionResultjaxb);
            }

        }

        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildUserFavoriteDevices(TingcoDevice tingcoDevice, List<UserFavoriteDevices> ufd) {
        Devices devices = new Devices();
        int seqID = 1;
        for (UserFavoriteDevices favDev : ufd) {
            se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
            dev.setSeqNo(seqID++);
            dev.getContent().add(objectFactory.createDeviceID(favDev.getDevice().getDeviceId()));

            DeviceTypeID deviceTypeID = objectFactory.createDeviceTypeID();
            deviceTypeID.setDeviceTypeName(favDev.getDevice().getDeviceTypes().getDeviceTypeName());
            deviceTypeID.setValue(favDev.getDevice().getDeviceTypes().getDeviceTypeId());
            dev.getContent().add(deviceTypeID);

            dev.getContent().add(objectFactory.createDeviceName(favDev.getDevice().getDeviceName()));
            dev.getContent().add(objectFactory.createDeviceName2(favDev.getDevice().getDeviceName2()));

            devices.getDevice().add(dev);

        }
        tingcoDevice.setDevices(devices);
        return tingcoDevice;

    }

    public TingcoDevice buildVehicleList(TingcoDevice tingcodriver, List<Device> devilist) throws DatatypeConfigurationException {
        int seq = 0;
        Devices devices = new Devices();
        for (Device device : devilist) {
            se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
            dev.setSeqNo(++seq);
            dev.getContent().add(objectFactory.createDeviceID(device.getDeviceId()));
            dev.getContent().add(objectFactory.createDeviceName(device.getDeviceName()));
            devices.getDevice().add(dev);
        }

        tingcoDevice.setDevices(devices);
        return tingcodriver;
    }

    public TingcoDevice buildDeviceTypesByGroupId(TingcoDevice tingcoDevice, List<DeviceTypes> deviceTypesList) {

        Devices devices = new Devices();
        se.info24.devicejaxb.Device dev;
        int seqNo = 1;
        for (DeviceTypes deviceTypes : deviceTypesList) {
            dev = new se.info24.devicejaxb.Device();
            dev.setSeqNo(seqNo++);
            DeviceTypeID deviceTypeID = objectFactory.createDeviceTypeID();
            deviceTypeID.setDeviceTypeName(deviceTypes.getDeviceTypeName());
            deviceTypeID.setValue(deviceTypes.getDeviceTypeId());
            dev.getContent().add(deviceTypeID);
            devices.getDevice().add(dev);
        }

        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceLevelDetails(TingcoDevice tingcoDevice, List<DeviceDataItemScaling> ddis, Hashtable<String, String> ht, List<ProductVariantTranslations> pvt, List<DeviceStatusDataItems> deviceStatusDataItemsList, int countryId, List<Device> device, String timeZoneGMToffset) throws DatatypeConfigurationException {
        se.info24.devicejaxb.DeviceDataItemScalings ddiss = new DeviceDataItemScalings();
        se.info24.devicejaxb.DeviceDataItemScaling deviceDataItemScaling = null;
        GregorianCalendar gc = new GregorianCalendar();
        int seq = 0;
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.HOUR_OF_DAY, -24);
        for (DeviceDataItemScaling dataItemScaling : ddis) {
            deviceDataItemScaling = new se.info24.devicejaxb.DeviceDataItemScaling();
            deviceDataItemScaling.setSeqNo(seq++);
            deviceDataItemScaling.getContent().add(objectFactory.createDeviceDataItemScalingCountryId(countryId));
            boolean flag = false;
            for (DeviceStatusDataItems deviceStatusDataItems : deviceStatusDataItemsList) {
                if (deviceStatusDataItems.getId().getDeviceDataItemId().equalsIgnoreCase(dataItemScaling.getId().getDeviceDataItemId()) && deviceStatusDataItems.getId().getDeviceId().equalsIgnoreCase(dataItemScaling.getId().getDeviceId())) {
                    flag = true;
                    String str = deviceStatusDataItems.getDeviceDataValue();
                    str = str.replace(",", ".");
                    deviceDataItemScaling.getContent().add(objectFactory.createDeviceDataItemScalingFilledLevel(Float.valueOf(df.format(Double.valueOf(str)).replace(",", "."))));
                    if (dataItemScaling.getScaledMaxValue() != null) {
                        deviceDataItemScaling.getContent().add(objectFactory.createDeviceDataItemScalingFreeLevel(Float.valueOf(df.format(dataItemScaling.getScaledMaxValue().floatValue() - Float.valueOf(str)).replace(",", "."))));
                    }
                    if (dataItemScaling.getScaledMinValue() != null && dataItemScaling.getScaledMaxValue() != null) {
                        int filledPercentage = 0;
                        if ((Float.valueOf(str) > dataItemScaling.getScaledMinValue().floatValue())) {
                            filledPercentage = (int) ((Float.valueOf(str) - dataItemScaling.getScaledMinValue().floatValue()) / (dataItemScaling.getScaledMaxValue().floatValue() - dataItemScaling.getScaledMinValue().floatValue()) * 100);
                        }

                        deviceDataItemScaling.getContent().add(objectFactory.createDeviceDataItemScalingFillLevelPercent(filledPercentage));
                    }
                    gc.setTime(deviceStatusDataItems.getActiveFromDate());
                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, gc.getTime()));
                    deviceDataItemScaling.getContent().add(objectFactory.createActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc)));
                    if (deviceStatusDataItems.getActiveFromDate().after(currentDate.getTime())) {
                        deviceDataItemScaling.getContent().add(objectFactory.createDeviceDataItemScalingStatus("Active"));
                    } else {
                        deviceDataItemScaling.getContent().add(objectFactory.createDeviceDataItemScalingStatus("InActive"));
                    }
                } /*
             * else {
             * deviceDataItemScaling.getContent().add(objectFactory.createDeviceDataItemScalingFillLevelPercent(0));
            }
             */
            }
            if (!flag) {
                deviceDataItemScaling.getContent().add(objectFactory.createDeviceDataItemScalingFillLevelPercent(0));
            }
            if (!ht.isEmpty()) {
                Set<String> deviceDataItemId = ht.keySet();
                for (String string : deviceDataItemId) {
                    if (string.equalsIgnoreCase(dataItemScaling.getId().getDeviceDataItemId())) {
                        deviceDataItemScaling.getContent().add(objectFactory.createDataItemName(ht.get(string)));
                        deviceDataItemScaling.getContent().add(objectFactory.createDataItemID(dataItemScaling.getId().getDeviceDataItemId()));
                    }
                }
            }


            for (Device dev : device) {
                if (dev.getDeviceId().equalsIgnoreCase(dataItemScaling.getId().getDeviceId())) {
                    deviceDataItemScaling.getContent().add(objectFactory.createDeviceID(dev.getDeviceId()));
                    deviceDataItemScaling.getContent().add(objectFactory.createDeviceName(dev.getDeviceName()));
                }
            }

            if (dataItemScaling.getProductVariantId() != null) {
                for (ProductVariantTranslations productVariantTranslations : pvt) {
                    if (productVariantTranslations.getId().getProductVariantId().equalsIgnoreCase(dataItemScaling.getProductVariantId())) {

                        deviceDataItemScaling.getContent().add(objectFactory.createDeviceDataItemScalingProductVariantName(productVariantTranslations.getProductVariantName()));
                        deviceDataItemScaling.getContent().add(objectFactory.createDeviceDataItemScalingProductVariantID(productVariantTranslations.getId().getProductVariantId()));
                    }
                }
            }
            if (dataItemScaling.getScaledMaxValue() != null) {
                deviceDataItemScaling.getContent().add(objectFactory.createDeviceDataItemScalingScaledMaxValue(Float.valueOf(df.format(dataItemScaling.getScaledMaxValue().doubleValue()))));
            }
            if (dataItemScaling.getScaledMinValue() != null) {
                deviceDataItemScaling.getContent().add(objectFactory.createDeviceDataItemScalingScaledMinValue(Float.valueOf(df.format(dataItemScaling.getScaledMinValue().doubleValue()))));
            }
            ddiss.getDeviceDataItemScaling().add(deviceDataItemScaling);
        }
        tingcoDevice.setDeviceDataItemScalings(ddiss);
        return tingcoDevice;
    }

    public TingcoDevice buildObjectSettingTemplates(TingcoDevice tingcoDevice, List<se.info24.pojo.ObjectSettingTemplates> ostList) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.DeviceSettings deviceSettings = new se.info24.devicejaxb.DeviceSettings();
        DeviceSetting deviceSetting = new DeviceSetting();
        for (se.info24.pojo.ObjectSettingTemplates osts : ostList) {
            se.info24.devicejaxb.ObjectSettingTemplates ost = new se.info24.devicejaxb.ObjectSettingTemplates();
            ost.setObjectSettingTemplateID(osts.getObjectSettingTemplateId());
            ost.setObjectSettingName(osts.getObjectSettingName());
            if (osts.getObjectSettingDefaultValue() != null) {
                ost.setObjectSettingDefaultValue(osts.getObjectSettingDefaultValue());
            }
            if (osts.getObjectSettingDescription() != null) {
                ost.setObjectSettingDescription(osts.getObjectSettingDescription());
            }
            se.info24.devicejaxb.SettingDataType sdt = new se.info24.devicejaxb.SettingDataType();
            se.info24.pojo.SettingDataType sdtp = osts.getSettingDataType();
            sdt.setSettingDataTypeID(sdtp.getSettingDataTypeId());
            sdt.setSettingDataType(sdtp.getSettingDataType());
            ost.getSettingDataType().add(sdt);
            deviceSetting.getObjectSettingTemplates().add(ost);
        }
        deviceSettings.getDeviceSetting().add(deviceSetting);
        device.getContent().add(deviceSettings);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildContactGroupByGroup(TingcoDevice tingcoDevice, List<ContactGroups> cgList) {
        se.info24.devicejaxb.Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        for (ContactGroups contactGroups : cgList) {
            se.info24.devicejaxb.ContactGroups cg = new se.info24.devicejaxb.ContactGroups();
            cg.setContactGroupID(contactGroups.getContactGroupId());
            cg.setContactGroupName(contactGroups.getContactGroupName());
            cg.setContactGroupDescription(contactGroups.getContactGroupDescription());
            device.getContent().add(cg);
        }
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;

    }

    public TingcoDevice buildSchedulesByGroup(TingcoDevice tingcoDevice, List<Schedules> schedules) {
        se.info24.devicejaxb.Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        for (Schedules schedules1 : schedules) {
            se.info24.devicejaxb.Schedules sche = new se.info24.devicejaxb.Schedules();
            sche.setScheduleID(schedules1.getScheduleId());
            sche.setScheduleName(schedules1.getScheduleName());
            device.getContent().add(sche);
        }
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceEventTrigger(TingcoDevice tingcoDevice, List<EventItems> eventItemList, List<EventTypeTranslations> eTT, List<EventItemActions> eIA, List<EventActionSchedules> eAS, List<ContactGroups> cgList, List<Schedules> schedulesList) {
        int seq = 1;
        se.info24.devicejaxb.Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        for (EventItems eventItems : eventItemList) {
            se.info24.devicejaxb.EventItems eventitemsjaxb = new se.info24.devicejaxb.EventItems();
            eventitemsjaxb.setSeqNo(seq++);
            eventitemsjaxb.setEventItemID(eventItems.getEventItemId());
            eventitemsjaxb.setEventTypeID(eventItems.getEventTypeId());
            eventitemsjaxb.setIsEnabled(eventItems.getIsEnabled());
            for (EventTypeTranslations eventTypeTranslations : eTT) {
                if (eventItems.getEventTypeId().equalsIgnoreCase(eventTypeTranslations.getEventTypes().getEventTypeId())) {
                    se.info24.devicejaxb.EventTypeTranslation ettJaxb = new EventTypeTranslation();
                    ettJaxb.setEventTypeName(eventTypeTranslations.getEventTypeName());
                    ettJaxb.setCountryID(eventTypeTranslations.getId().getCountryId());
                    eventitemsjaxb.getEventTypeTranslation().add(ettJaxb);
                }
            }

            for (EventItemActions eventItemActions : eIA) {
                if (eventItems.getEventItemId().equalsIgnoreCase(eventItemActions.getId().getEventItemId())) {
                    se.info24.devicejaxb.EventItemActions eiajaxb = new se.info24.devicejaxb.EventItemActions();
                    eiajaxb.setEventActionScheduleID(eventItemActions.getId().getEventActionScheduleId());
                    for (EventActionSchedules eventActionSchedules : eAS) {
                        if (eventItemActions.getId().getEventActionScheduleId().equalsIgnoreCase(eventActionSchedules.getEventActionScheduleId())) {
                            se.info24.devicejaxb.EventActionSchedules easjaxb = new se.info24.devicejaxb.EventActionSchedules();
                            for (ContactGroups contactGroups : cgList) {
                                if (eventActionSchedules.getContactGroupId().equalsIgnoreCase(contactGroups.getContactGroupId())) {
                                    se.info24.devicejaxb.ContactGroups cgjaxb = new se.info24.devicejaxb.ContactGroups();
                                    cgjaxb.setContactGroupID(contactGroups.getContactGroupId());
                                    cgjaxb.setContactGroupName(contactGroups.getContactGroupName());
                                    cgjaxb.setContactGroupDescription(contactGroups.getContactGroupDescription());
                                    easjaxb.getContactGroups().add(cgjaxb);
                                }
                            }
                            for (Schedules schedules : schedulesList) {
                                if (eventActionSchedules.getScheduleId().equalsIgnoreCase(schedules.getScheduleId())) {
                                    se.info24.devicejaxb.Schedules shjaxb = new se.info24.devicejaxb.Schedules();
                                    shjaxb.setScheduleID(schedules.getScheduleId());
                                    shjaxb.setScheduleName(schedules.getScheduleName());
                                    easjaxb.getSchedules().add(shjaxb);
                                }
                            }
                            eiajaxb.getEventActionSchedules().add(easjaxb);
                        }
                    }
                    eventitemsjaxb.getEventItemActions().add(eiajaxb);
                }
            }
            device.getContent().add(eventitemsjaxb);
        }
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceConnectionStatusDetails(TingcoDevice tingcoDevice, List<DeviceStatus> deviceStatus, List<se.info24.pojo.Device> devicepojo, List<ItemConnectionStatus> ICS, String timeZoneOffset, int countryId, Session session) throws DatatypeConfigurationException {
        se.info24.devicejaxb.Devices devices = new Devices();
        se.info24.devicejaxb.Device device = null;
        int seqID = 1;
        GregorianCalendar gc = new GregorianCalendar();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (Device device1 : devicepojo) {
            device = new se.info24.devicejaxb.Device();
            device.setSeqNo(seqID++);
            device.getContent().add(objectFactory.createDeviceID(device1.getDeviceId()));
            device.getContent().add(objectFactory.createDeviceName(device1.getDeviceName()));
            device.getContent().add(objectFactory.createDeviceName2(device1.getDeviceName2()));
            GroupTranslations gt = groupDAO.getGroupTranslationsByIds(device1.getGroups().getGroupId(), countryId, session);
            GroupID groupID = objectFactory.createGroupID();
            groupID.setValue(device1.getGroups().getGroupId());
            if (gt != null) {
                groupID.setGroupName(gt.getGroupName());
            }
            device.getContent().add(groupID);
            DeviceTypeID deviceTypeID = objectFactory.createDeviceTypeID();
            deviceTypeID.setDeviceTypeName(device1.getDeviceTypes().getDeviceTypeName());
            deviceTypeID.setValue(device1.getDeviceTypes().getDeviceTypeId());
            device.getContent().add(deviceTypeID);
            boolean flag = false;
            for (ItemConnectionStatus itemConnectionStatus : ICS) {
                if (itemConnectionStatus.getItemId().equalsIgnoreCase(device1.getDeviceId())) {
                    se.info24.devicejaxb.ItemConnectionStatus ICSJaxb = new se.info24.devicejaxb.ItemConnectionStatus();
                    ICSJaxb.setConnected(String.valueOf(itemConnectionStatus.getConnected()));
                    if (itemConnectionStatus.getObjectStateCode() != null) {
                        ICSJaxb.setObjectStateCode(itemConnectionStatus.getObjectStateCode());
                    }
                    device.getContent().add(ICSJaxb);
                    flag = true;
                }
            }
            if (!flag) {
                se.info24.devicejaxb.ItemConnectionStatus ICSJaxb = new se.info24.devicejaxb.ItemConnectionStatus();
                ICSJaxb.setConnected(String.valueOf(0));
                device.getContent().add(ICSJaxb);
                flag = true;
            }

            for (DeviceStatus ds : deviceStatus) {
                if (ds.getDeviceId().equalsIgnoreCase(device1.getDeviceId())) {
                    se.info24.devicejaxb.DeviceStatus deviceStatusJaxb = objectFactory.createDeviceStatus();
                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneOffset, ds.getDataItemTime()));
                    deviceStatusJaxb.setDataItemTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    deviceStatusJaxb.setDataItemTimeTCMV3(dateFormat.format(gc.getTime()));
                    if (ds.getPosLatitude() != null) {
                        deviceStatusJaxb.setPosLatitude(String.valueOf(ds.getPosLatitude()));
                    }
                    if (ds.getPosLongitude() != null) {
                        deviceStatusJaxb.setPosLongitude(String.valueOf(ds.getPosLongitude()));
                    }
                    if (ds.getUpdatedDate() != null) {
                        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneOffset, ds.getUpdatedDate()));
                        deviceStatusJaxb.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    }
                    device.getContent().add(deviceStatusJaxb);
                }
            }
            devices.getDevice().add(device);
        }
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildDeviceConnectionStatusDetailsTCMV3(TingcoDevice tingcoDevice, List<DeviceStatus> deviceStatus, List<se.info24.pojo.Device> devicepojo, List<ItemConnectionStatus> ICS, String timeZoneOffset, Integer isConnected, int countryId, Session session) throws DatatypeConfigurationException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        float connectedDevice = 0;
        float disConnectedDevice = 0;
        int deviceIsConnected = 1;
        float sum = 0;
        if (isConnected != null) {
            se.info24.devicejaxb.Devices devices = new Devices();
            se.info24.devicejaxb.Device device = null;
            int seqID = 1;
            GregorianCalendar gc = new GregorianCalendar();
            for (Device device1 : devicepojo) {
                device = new se.info24.devicejaxb.Device();
                device.getContent().add(objectFactory.createDeviceID(device1.getDeviceId()));
                device.getContent().add(objectFactory.createDeviceName(device1.getDeviceName()));
                device.getContent().add(objectFactory.createDeviceName2(device1.getDeviceName2()));
                GroupTranslations gt = groupDAO.getGroupTranslationsByIds(device1.getGroups().getGroupId(), countryId, session);
                GroupID groupID = objectFactory.createGroupID();
                groupID.setValue(device1.getGroups().getGroupId());
                if (gt != null) {
                    groupID.setGroupName(gt.getGroupName());
                }
                device.getContent().add(groupID);
                DeviceTypeID deviceTypeID = objectFactory.createDeviceTypeID();
                deviceTypeID.setDeviceTypeName(device1.getDeviceTypes().getDeviceTypeName());
                deviceTypeID.setValue(device1.getDeviceTypes().getDeviceTypeId());
                device.getContent().add(deviceTypeID);
                boolean flag = false;

                for (ItemConnectionStatus itemConnectionStatus : ICS) {
                    if (itemConnectionStatus.getItemId().equalsIgnoreCase(device1.getDeviceId())) {
                        se.info24.devicejaxb.ItemConnectionStatus ICSJaxb = new se.info24.devicejaxb.ItemConnectionStatus();
                        ICSJaxb.setConnected(String.valueOf(itemConnectionStatus.getConnected()));
                        if (String.valueOf(itemConnectionStatus.getConnected()).equalsIgnoreCase("1")) {
                            deviceIsConnected = 1;
                            connectedDevice = connectedDevice + 1;
                            ICSJaxb.setConnectedTCMV3("Online");
                        } else {
                            deviceIsConnected = 0;
                            disConnectedDevice = disConnectedDevice + 1;
                            ICSJaxb.setConnectedTCMV3("Offline");
                        }
                        if (itemConnectionStatus.getObjectStateCode() != null) {
                            ICSJaxb.setObjectStateCode(itemConnectionStatus.getObjectStateCode());
                        } else {
                            ICSJaxb.setObjectStateCode("DISABLED");
                        }
                        device.getContent().add(ICSJaxb);
                        flag = true;
                    }
                }
                if (!flag) {
                    deviceIsConnected = 0;
                    se.info24.devicejaxb.ItemConnectionStatus ICSJaxb = new se.info24.devicejaxb.ItemConnectionStatus();
                    ICSJaxb.setConnected(String.valueOf(0));
                    ICSJaxb.setConnectedTCMV3("Offline");
                    disConnectedDevice = disConnectedDevice + 1;
                    device.getContent().add(ICSJaxb);
                    flag = true;
                }

                for (DeviceStatus ds : deviceStatus) {
                    if (ds.getDeviceId().equalsIgnoreCase(device1.getDeviceId())) {
                        se.info24.devicejaxb.DeviceStatus deviceStatusJaxb = objectFactory.createDeviceStatus();
                        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneOffset, ds.getDataItemTime()));
                        deviceStatusJaxb.setDataItemTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                        deviceStatusJaxb.setDataItemTimeTCMV3(dateFormat.format(gc.getTime()));
                        if (ds.getPosLatitude() != null) {
                            deviceStatusJaxb.setPosLatitude(String.valueOf(ds.getPosLatitude()));
                        }
                        if (ds.getPosLongitude() != null) {
                            deviceStatusJaxb.setPosLongitude(String.valueOf(ds.getPosLongitude()));
                        }
                        if (ds.getUpdatedDate() != null) {
                            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneOffset, ds.getUpdatedDate()));
                            deviceStatusJaxb.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                            deviceStatusJaxb.setUpdatedDateTCMV3(dateFormat.format(gc.getTime()));
                        }
                        device.getContent().add(deviceStatusJaxb);
                    }
                }
                if (deviceIsConnected == isConnected) {
                    device.setSeqNo(seqID++);
                    devices.getDevice().add(device);
                } else if (deviceIsConnected == isConnected) {
                    device.setSeqNo(seqID++);
                    devices.getDevice().add(device);
                }
            }
            sum = connectedDevice + disConnectedDevice;

            if (devices.getDevice().isEmpty()) {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("Devices Not Found");
                return tingcoDevice;
            }
            tingcoDevice.setDevices(devices);
            return tingcoDevice;
        } else {
            se.info24.devicejaxb.Devices devices = new Devices();
            se.info24.devicejaxb.Device device = null;
            int seqID = 1;
            GregorianCalendar gc = new GregorianCalendar();
            for (Device device1 : devicepojo) {
                device = new se.info24.devicejaxb.Device();
                device.setSeqNo(seqID++);
                device.getContent().add(objectFactory.createDeviceID(device1.getDeviceId()));
                device.getContent().add(objectFactory.createDeviceName(device1.getDeviceName()));
                device.getContent().add(objectFactory.createDeviceName2(device1.getDeviceName2()));
                GroupTranslations gt = groupDAO.getGroupTranslationsByIds(device1.getGroups().getGroupId(), countryId, session);
                GroupID groupID = objectFactory.createGroupID();
                groupID.setValue(device1.getGroups().getGroupId());
                if (gt != null) {
                    groupID.setGroupName(gt.getGroupName());
                }
                device.getContent().add(groupID);
                DeviceTypeID deviceTypeID = objectFactory.createDeviceTypeID();
                deviceTypeID.setDeviceTypeName(device1.getDeviceTypes().getDeviceTypeName());
                deviceTypeID.setValue(device1.getDeviceTypes().getDeviceTypeId());
                device.getContent().add(deviceTypeID);
                boolean flag = false;
                for (ItemConnectionStatus itemConnectionStatus : ICS) {
                    if (itemConnectionStatus.getItemId().equalsIgnoreCase(device1.getDeviceId())) {
                        se.info24.devicejaxb.ItemConnectionStatus ICSJaxb = new se.info24.devicejaxb.ItemConnectionStatus();
                        ICSJaxb.setConnected(String.valueOf(itemConnectionStatus.getConnected()));
                        if (String.valueOf(itemConnectionStatus.getConnected()).equalsIgnoreCase("1")) {
                            connectedDevice = connectedDevice + 1;
                            ICSJaxb.setConnectedTCMV3("Online");
                        } else {
                            disConnectedDevice = disConnectedDevice + 1;
                            ICSJaxb.setConnectedTCMV3("Offline");
                        }
                        if (itemConnectionStatus.getObjectStateCode() != null) {
                            ICSJaxb.setObjectStateCode(itemConnectionStatus.getObjectStateCode());
                        } else {
                            ICSJaxb.setObjectStateCode("DISABLED");
                        }
                        device.getContent().add(ICSJaxb);
                        flag = true;
                    }
                }
                if (!flag) {
                    se.info24.devicejaxb.ItemConnectionStatus ICSJaxb = new se.info24.devicejaxb.ItemConnectionStatus();
                    ICSJaxb.setConnected(String.valueOf(0));
                    ICSJaxb.setConnectedTCMV3("Offline");
                    ICSJaxb.setObjectStateCode("DISABLED");
                    disConnectedDevice = disConnectedDevice + 1;
                    device.getContent().add(ICSJaxb);
                    flag = true;
                }

                for (DeviceStatus ds : deviceStatus) {
                    if (ds.getDeviceId().equalsIgnoreCase(device1.getDeviceId())) {
                        se.info24.devicejaxb.DeviceStatus deviceStatusJaxb = objectFactory.createDeviceStatus();
                        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneOffset, ds.getDataItemTime()));
                        deviceStatusJaxb.setDataItemTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                        deviceStatusJaxb.setDataItemTimeTCMV3(dateFormat.format(gc.getTime()));
                        if (ds.getPosLatitude() != null) {
                            deviceStatusJaxb.setPosLatitude(String.valueOf(ds.getPosLatitude()));
                        }
                        if (ds.getPosLongitude() != null) {
                            deviceStatusJaxb.setPosLongitude(String.valueOf(ds.getPosLongitude()));
                        }
                        if (ds.getUpdatedDate() != null) {
                            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneOffset, ds.getUpdatedDate()));
                            deviceStatusJaxb.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                            deviceStatusJaxb.setUpdatedDateTCMV3(dateFormat.format(gc.getTime()));
                        }
                        device.getContent().add(deviceStatusJaxb);
                    }
                }
                sum = connectedDevice + disConnectedDevice;

                float ConnectedDevicesAverage = (connectedDevice / sum) * 100;
                float DisConnectedDevicesAverage = (disConnectedDevice / sum) * 100;
                device.getContent().add(objectFactory.createDeviceConnectedDevicesAverageTCMV3(Math.round(ConnectedDevicesAverage)));
                device.getContent().add(objectFactory.createDeviceDisConnectedDevicesAverageTCMV3((Math.round(DisConnectedDevicesAverage))));
                devices.getDevice().add(device);
            }
            tingcoDevice.setDevices(devices);
            return tingcoDevice;
        }
    }

    public TingcoDevice buildgetSalesbyProductVariant(TingcoDevice tingcoDevice, Hashtable<String, Integer> ht1, List<TransactionProducts> transactionProducts) {
        se.info24.devicejaxb.Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.TransactionProducts transactionProductsjaxb = null;
        Set<String> productvariantId = ht1.keySet();
        Map<Integer, List<String>> unsortMap = new HashMap<Integer, List<String>>();
        int i = 0;
        List<String> name = null;
        for (String string : productvariantId) {
            for (TransactionProducts tra : transactionProducts) {

                if (tra.getId().getProductVariantId().equalsIgnoreCase(string)) {
                    if (i < 30) {
                        if (!unsortMap.containsKey(ht1.get(string))) {
                            name = new ArrayList<String>();
                            name.add(tra.getProductVariantName());
                            unsortMap.put(ht1.get(string), name);
                        } else {
                            name = unsortMap.get(ht1.get(string));
                            name.add(tra.getProductVariantName());
                            unsortMap.put(ht1.get(string), name);
                        }
                        i++;
                        break;
                    }
                }
            }
        }

        Map<Integer, List<String>> shorted = new TreeMap<Integer, List<String>>(unsortMap);
        List<Integer> quantity = new ArrayList<Integer>(shorted.keySet());
        for (i = quantity.size() - 1; i >= 0; i--) {
            List<String> names = shorted.get(quantity.get(i));
            for (String string : names) {
                transactionProductsjaxb = new se.info24.devicejaxb.TransactionProducts();
                transactionProductsjaxb.setProductVariantName(string);
                transactionProductsjaxb.setQuantity(quantity.get(i));
                device.getContent().add(transactionProductsjaxb);
            }
        }

        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetDeviceMediaFiles(TingcoDevice tingcoDevice, List<Object> mfList, List<MediaFileTypes> mftList, List<ObjectMediaFiles> omfList, String timeZoneGMToffset, List<GroupTranslations> groupTranslationses, List<Users2> users2s) throws DatatypeConfigurationException, ParseException {
        se.info24.devicejaxb.ObjectMediaFiles omfs = new se.info24.devicejaxb.ObjectMediaFiles();
        se.info24.devicejaxb.ObjectMediaFile omf = null;
        se.info24.devicejaxb.MediaFiles mfs = null;
        se.info24.devicejaxb.MediaFileTypes mft = null;
        GregorianCalendar gc = new GregorianCalendar();
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Iterator itr = mfList.iterator(); itr.hasNext();) {
            Object[] mediaFiles = (Object[]) itr.next();
            for (int i = 0; i < mediaFiles.length; i++) {
                for (ObjectMediaFiles objectMediaFiles : omfList) {
                    if (objectMediaFiles.getId().getMediaFileId().equalsIgnoreCase(mediaFiles[i].toString())) {
                        omf = new se.info24.devicejaxb.ObjectMediaFile();
                        omf.setDisplayOrder(objectMediaFiles.getDisplayOrder());

                        mfs = new se.info24.devicejaxb.MediaFiles();
                        if (mediaFiles[i] != null) {
                            mfs.setMediaFileID(mediaFiles[i].toString());
                        }
                        if (mediaFiles[i + 1] != null) {
                            mfs.setMediaFileName(mediaFiles[i + 1].toString());
                        }
                        if (mediaFiles[i + 2] != null) {
                            mfs.setMediaFileTypeID(mediaFiles[i + 2].toString());
                        }
                        mfs.setMediaFileLength(Integer.parseInt(mediaFiles[i + 3].toString()));
                        if (mediaFiles[i + 4] != null) {
                            mfs.setMediaFileExtension(mediaFiles[i + 4].toString());
                        }
                        if (mediaFiles[i + 5] != null) {
                            gc.setTime(df1.parse(mediaFiles[i + 5].toString()));
                            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, gc.getTime()));
                            mfs.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                            mfs.setCreatedDateTCMV3(df1.format(gc.getTime()));

                        }

                        if (mediaFiles[i + 6] != null) {
                            for (Users2 u : users2s) {
                                if (u.getUserId().equalsIgnoreCase(mediaFiles[i + 6].toString())) {
                                    LastUpdatedByUserID userID = new LastUpdatedByUserID();
                                    userID.setUserFullName(u.getFirstName() + " " + u.getLastName());
                                    userID.setValue(u.getUserId());
                                    mfs.setLastUpdatedByUserID(userID);
                                    break;
                                }
                            }

                        }

                        if (mediaFiles[i + 7] != null) {
                            for (GroupTranslations gt : groupTranslationses) {
                                if (gt.getId().getGroupId().equalsIgnoreCase(mediaFiles[i + 7].toString())) {
                                    mfs.setGroupID(gt.getId().getGroupId());
                                    mfs.setGroupName(gt.getGroupName());
                                    break;
                                }
                            }

                        }

                        for (MediaFileTypes mediaFileTypes : mftList) {
                            if (mediaFiles[i + 2] != null) {
                                if (mediaFileTypes.getMediaFileTypeId().equalsIgnoreCase(mediaFiles[i + 2].toString())) {
                                    mft = new se.info24.devicejaxb.MediaFileTypes();
                                    mft.setMediaFileTypeID(mediaFiles[i + 2].toString());
                                    mft.setTypeName(mediaFileTypes.getTypeName());
                                    if (mediaFileTypes.getIconUrl() != null) {
                                        mft.setIconURL(mediaFileTypes.getIconUrl());
                                    }
                                    mfs.getMediaFileTypes().add(mft);
                                }
                            }

                        }
                        omf.getMediaFiles().add(mfs);
                        omfs.getObjectMediaFile().add(omf);
                    }

                }
                i += 6;
            }
        }


        tingcoDevice.setObjectMediaFiles(omfs);
        return tingcoDevice;
    }

    public TingcoDevice buildGetMediaFileTypes(TingcoDevice tingcoDevice, List<MediaFileTypes> mftList) {
        se.info24.devicejaxb.ObjectMediaFiles omfs = new se.info24.devicejaxb.ObjectMediaFiles();
        se.info24.devicejaxb.ObjectMediaFile omf = new se.info24.devicejaxb.ObjectMediaFile();
        se.info24.devicejaxb.MediaFiles mfs = new se.info24.devicejaxb.MediaFiles();
        se.info24.devicejaxb.MediaFileTypes mft = null;
        for (MediaFileTypes mediaFileTypes : mftList) {
            mft = new se.info24.devicejaxb.MediaFileTypes();
            mft.setMediaFileTypeID(mediaFileTypes.getMediaFileTypeId());
            mft.setTypeName(mediaFileTypes.getTypeName());
            mft.setIconURL(mediaFileTypes.getIconUrl());
            mfs.getMediaFileTypes().add(mft);
        }
        omf.getMediaFiles().add(mfs);
        omfs.getObjectMediaFile().add(omf);
        tingcoDevice.setObjectMediaFiles(omfs);
        return tingcoDevice;
    }

    public TingcoDevice buildGetExistingFilesForDevice(TingcoDevice tingcoDevice, List<GroupTranslations> gtList, List<MediaFiles> mfList, List<ObjectMediaFiles> omfList) {
        se.info24.devicejaxb.ObjectMediaFiles omfs = new se.info24.devicejaxb.ObjectMediaFiles();
        se.info24.devicejaxb.ObjectMediaFile omf = new se.info24.devicejaxb.ObjectMediaFile();
        se.info24.devicejaxb.MediaFiles mfs = null;
        boolean flag = false;
        for (MediaFiles mediaFiles : mfList) {
            flag = false;
            for (ObjectMediaFiles objectMediaFiles : omfList) {
                if (mediaFiles.getMediaFileId().equalsIgnoreCase(objectMediaFiles.getId().getMediaFileId())) {
                    flag = true;
                }
            }
            if (!flag) {
                mfs = new se.info24.devicejaxb.MediaFiles();
                mfs.setMediaFileID(mediaFiles.getMediaFileId());
                mfs.setMediaFileName(mediaFiles.getMediaFileName());
                mfs.setGroupID(mediaFiles.getGroupId());
                for (GroupTranslations gt : gtList) {
                    if (gt.getId().getGroupId().equalsIgnoreCase(mediaFiles.getGroupId())) {
                        mfs.setGroupName(gt.getGroupName());
                    }
                }
                omf.getMediaFiles().add(mfs);
            }

        }

        omfs.getObjectMediaFile().add(omf);
        tingcoDevice.setObjectMediaFiles(omfs);
        return tingcoDevice;
    }

    public TingcoDevice buildGetDeviceCommandScheduleDetails(TingcoDevice tingcoDevice, List<DeviceCommandSchedules> dcsList, List<DeviceTypeCommandTranslations> dtct, String timeZoneGMToffset) throws DatatypeConfigurationException {
        se.info24.devicejaxb.Devices devices = new Devices();
        DateFormat df1 = new SimpleDateFormat("HH:mm");
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.DeviceCommandSchedules dcsJaxb = null;
        se.info24.devicejaxb.DeviceTypeCommandTranslations dtctJaxb = null;
        GregorianCalendar gc = new GregorianCalendar();
        for (DeviceTypeCommandTranslations deviceTypeCommandTranslations : dtct) {
            for (DeviceCommandSchedules dcs : dcsList) {
                if (deviceTypeCommandTranslations.getId().getDeviceTypeCommandId().equalsIgnoreCase(dcs.getDeviceTypeCommands().getDeviceTypeCommandId())) {
                    dcsJaxb = new se.info24.devicejaxb.DeviceCommandSchedules();
                    dcsJaxb.setDeviceCommandScheduleID(dcs.getDeviceCommandScheduleId());
                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, dcs.getSendTime()));

                    dcsJaxb.setSendTime(df1.format(gc.getTime()));

                    dcsJaxb.setSendMonday(dcs.getSendMonday());
                    dcsJaxb.setSendTuesday(dcs.getSendTuesday());
                    dcsJaxb.setSendWednesday(dcs.getSendWednesday());
                    dcsJaxb.setSendThursday(dcs.getSendThursday());
                    dcsJaxb.setSendFriday(dcs.getSendFriday());
                    dcsJaxb.setSendSaturday(dcs.getSendSaturday());
                    dcsJaxb.setSendSunday(dcs.getSendSunday());

                    if (dcs.getSendMonday() == 1) {
                        dcsJaxb.setSendMondayTCMV3("Yes");
                    } else {
                        dcsJaxb.setSendMondayTCMV3("No");
                    }
                    if (dcs.getSendTuesday() == 1) {
                        dcsJaxb.setSendTuesdayTCMV3("Yes");
                    } else {
                        dcsJaxb.setSendTuesdayTCMV3("No");
                    }

                    if (dcs.getSendWednesday() == 1) {
                        dcsJaxb.setSendWednesdayTCMV3("Yes");
                    } else {
                        dcsJaxb.setSendWednesdayTCMV3("No");
                    }
                    if (dcs.getSendThursday() == 1) {
                        dcsJaxb.setSendThursdayTCMV3("Yes");
                    } else {
                        dcsJaxb.setSendThursdayTCMV3("No");
                    }

                    if (dcs.getSendFriday() == 1) {
                        dcsJaxb.setSendFridayTCMV3("Yes");
                    } else {
                        dcsJaxb.setSendFridayTCMV3("No");
                    }
                    if (dcs.getSendSaturday() == 1) {
                        dcsJaxb.setSendSaturdayTCMV3("Yes");
                    } else {
                        dcsJaxb.setSendSaturdayTCMV3("No");
                    }
                    if (dcs.getSendSunday() == 1) {
                        dcsJaxb.setSendSundayTCMV3("Yes");
                    } else {
                        dcsJaxb.setSendSundayTCMV3("No");
                    }


                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, dcs.getActiveFromDate()));
                    dcsJaxb.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) + "");
                    dcsJaxb.setActiveFromDateTCMV3(df2.format(gc.getTime()));
                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, dcs.getActiveToDate()));
                    dcsJaxb.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) + "");

                    dcsJaxb.setActiveToDateTCMV3(df2.format(gc.getTime()));

                    dcsJaxb.setIsEnabled(dcs.getIsEnabled());

                    if (dcs.getLastSent() != null) {
                        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, dcs.getLastSent()));
                        dcsJaxb.setLastSent(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    }

                    dtctJaxb = new se.info24.devicejaxb.DeviceTypeCommandTranslations();
                    dtctJaxb.setDeviceTypecommandName(deviceTypeCommandTranslations.getCommandName());
                    dcsJaxb.getDeviceTypeCommandTranslations().add(dtctJaxb);
                    dev.getContent().add(dcsJaxb);

                }

            }

        }


        devices.getDevice().add(dev);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetDeviceTypeCommands(TingcoDevice tingcoDevice, List<DeviceTypeCommands> dtcList, List<DeviceTypeCommandTranslations> dtct) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.DeviceTypeCommands deviceTypeCommandsJaxb = null;
        se.info24.devicejaxb.DeviceTypeCommandTranslations dtctJaxb = null;

        for (DeviceTypeCommandTranslations deviceTypeCommandTranslations : dtct) {
            for (DeviceTypeCommands dtc : dtcList) {
                if (deviceTypeCommandTranslations.getId().getDeviceTypeCommandId().equalsIgnoreCase(dtc.getDeviceTypeCommandId())) {

                    deviceTypeCommandsJaxb = new se.info24.devicejaxb.DeviceTypeCommands();
                    deviceTypeCommandsJaxb.setDeviceDataItemID(dtc.getDeviceDataItemId());
                    deviceTypeCommandsJaxb.setDataItemValue(dtc.getDataItemValue());
                    deviceTypeCommandsJaxb.setFlagUserCanSetValue(dtc.getFlagUserCanSetValue());
                    deviceTypeCommandsJaxb.setDisplayOrder(dtc.getDisplayOrder());

                    dtctJaxb = new se.info24.devicejaxb.DeviceTypeCommandTranslations();
                    dtctJaxb.setDeviceTypeCommandID(deviceTypeCommandTranslations.getId().getDeviceTypeCommandId());
                    dtctJaxb.setDeviceTypecommandName(deviceTypeCommandTranslations.getCommandName());
                    dtctJaxb.setCommandButtonText(deviceTypeCommandTranslations.getCommandButtonText());
                    deviceTypeCommandsJaxb.getDeviceTypeCommandTranslations().add(dtctJaxb);

                    dev.getContent().add(deviceTypeCommandsJaxb);
                }
            }
        }
//        dev.getContent().add(dcsJaxb);
        devices.getDevice().add(dev);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public se.info24.devicejaxbPost.TingcoDevice buildTingcoDeviceTemplatePost() throws DatatypeConfigurationException {
        se.info24.devicejaxbPost.ObjectFactory objectFactoryPost = new se.info24.devicejaxbPost.ObjectFactory();
        se.info24.devicejaxbPost.TingcoDevice tingcoDevicePost = objectFactoryPost.createTingcoDevice();
        tingcoDevicePost.setCreateRef("Info24");
        tingcoDevicePost.setMsgVer(new BigDecimal("1.0"));
        tingcoDevicePost.setRegionalUnits("Metric");
        tingcoDevicePost.setTimeZone("UTC");

        tingcoDevicePost.setMsgID(UUID.randomUUID().toString());
        tingcoDevicePost.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        se.info24.devicejaxbPost.MsgStatus msgStatus = new se.info24.devicejaxbPost.MsgStatus();
        msgStatus.setResponseCode(0);
        msgStatus.setResponseText("OK");

        tingcoDevicePost.setMsgStatus(msgStatus);
        return tingcoDevicePost;
    }

    public se.info24.devicejaxbPost.TingcoDevice buildDeviceCountReport(List<Device> deviceList, List<GroupTranslations> gtList, se.info24.devicejaxbPost.TingcoDevice tingcoDevice, String groupBy, Session session) {
        se.info24.devicejaxbPost.Devices devices = new se.info24.devicejaxbPost.Devices();
        int seqNo = 1;
        Hashtable<String, Integer> deviceCount = new Hashtable<String, Integer>();
        boolean flag = false;
        if (groupBy == null) {
            for (se.info24.pojo.Device dev : deviceList) {
                if (!deviceCount.containsKey(dev.getGroups().getGroupId())) {
                    deviceCount.put(dev.getGroups().getGroupId(), 1);
                } else {
                    int incremented = deviceCount.get(dev.getGroups().getGroupId()) + 1;
                    deviceCount.put(dev.getGroups().getGroupId(), incremented);
                }
            }
            flag = true;
        } else {
            if (groupBy.equalsIgnoreCase("DeviceType")) {
                for (se.info24.pojo.Device dev : deviceList) {
                    if (!deviceCount.containsKey(dev.getDeviceTypes().getDeviceTypeId())) {
                        deviceCount.put(dev.getDeviceTypes().getDeviceTypeId(), 1);
                    } else {
                        int incremented = deviceCount.get(dev.getDeviceTypes().getDeviceTypeId()) + 1;
                        deviceCount.put(dev.getDeviceTypes().getDeviceTypeId(), incremented);
                    }
                }
                List<DeviceTypes> deviceTypeses = deviceDAO.getDeviceTypes(deviceCount.keySet(), session);
                for (DeviceTypes deviceTypes : deviceTypeses) {
                    for (String key : deviceCount.keySet()) {
                        if (deviceTypes.getDeviceTypeId().equalsIgnoreCase(key)) {
                            se.info24.devicejaxbPost.Device device = new se.info24.devicejaxbPost.Device();
                            device.setSeqNo(seqNo++);
                            device.setGroupBy(deviceTypes.getDeviceTypeName());
                            device.setNumberOfDevices(deviceCount.get(key));
                            devices.getDevice().add(device);
                            break;
                        }
                    }
                }


            } else if (groupBy.equalsIgnoreCase("Country")) {
                for (se.info24.pojo.Device dev : deviceList) {
                    if (dev.getAddresses() != null) {
                        if (!deviceCount.containsKey(String.valueOf(dev.getAddresses().getCountry().getCountryId()))) {
                            deviceCount.put(String.valueOf(dev.getAddresses().getCountry().getCountryId()), 1);
                        } else {
                            int incremented = deviceCount.get(String.valueOf(dev.getAddresses().getCountry().getCountryId())) + 1;
                            deviceCount.put(String.valueOf(dev.getAddresses().getCountry().getCountryId()), incremented);
                        }
                    }
                }
                List<Integer> CountryID = new ArrayList<Integer>();
                for (String key : deviceCount.keySet()) {
                    CountryID.add(Integer.valueOf(key));
                }
                if (!CountryID.isEmpty()) {
                    List<Country> countrys = session.getNamedQuery("getCountryByIdList").setParameterList("countryId", CountryID).list();
                    for (Country country : countrys) {
                        for (String key : deviceCount.keySet()) {
                            if (country.getCountryId() == Integer.valueOf(key)) {
                                se.info24.devicejaxbPost.Device device = new se.info24.devicejaxbPost.Device();
                                device.setSeqNo(seqNo++);
                                device.setGroupBy(country.getCountryName());
                                device.setNumberOfDevices(deviceCount.get(key));
                                devices.getDevice().add(device);
                                break;
                            }
                        }
                    }
                }
            } else if (groupBy.equalsIgnoreCase("Organization")) {
                for (se.info24.pojo.Device dev : deviceList) {
                    if (!deviceCount.containsKey(dev.getGroups().getGroupId())) {
                        deviceCount.put(dev.getGroups().getGroupId(), 1);
                    } else {
                        int incremented = deviceCount.get(dev.getGroups().getGroupId()) + 1;
                        deviceCount.put(dev.getGroups().getGroupId(), incremented);
                    }
                }
            }
            flag = true;
        }
        if (flag) {
            for (GroupTranslations groupTranslations : gtList) {
                for (String key : deviceCount.keySet()) {
                    if (key.equalsIgnoreCase(groupTranslations.getId().getGroupId())) {
                        se.info24.devicejaxbPost.Device device = new se.info24.devicejaxbPost.Device();
                        device.setSeqNo(seqNo++);
                        se.info24.devicejaxbPost.GroupID groupID = new se.info24.devicejaxbPost.GroupID();
                        device.setGroupBy(groupTranslations.getGroupName());
                        groupID.setGroupName(groupTranslations.getGroupName());
                        groupID.setValue(key);
                        device.setGroupID(groupID);
                        device.setNumberOfDevices(deviceCount.get(key));
                        devices.getDevice().add(device);
                        break;
                    }
                }
            }
        }
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetDeviceCommandScheduleDetailsByDeviceCommandScheduleID(TingcoDevice tingcoDevice, DeviceCommandSchedules get, List<DeviceTypeCommandTranslations> dtctList, String timeZoneGMToffset) throws DatatypeConfigurationException {

        se.info24.devicejaxb.Devices devices = new Devices();
        DateFormat df1 = new SimpleDateFormat("HH:mm:ss");
        se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.DeviceCommandSchedules dcsJaxb = null;
        se.info24.devicejaxb.DeviceTypeCommandTranslations dtctJaxb = null;
        GregorianCalendar gc = new GregorianCalendar();
        dcsJaxb = new se.info24.devicejaxb.DeviceCommandSchedules();
        dcsJaxb.setDeviceCommandScheduleID(get.getDeviceCommandScheduleId());
        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, get.getSendTime()));

        dcsJaxb.setSendTime(df1.format(gc.getTime()));
        dcsJaxb.setSendMonday(get.getSendMonday());
        dcsJaxb.setSendTuesday(get.getSendTuesday());
        dcsJaxb.setSendWednesday(get.getSendWednesday());
        dcsJaxb.setSendThursday(get.getSendThursday());
        dcsJaxb.setSendFriday(get.getSendFriday());
        dcsJaxb.setSendSaturday(get.getSendSaturday());
        dcsJaxb.setSendSunday(get.getSendSunday());

        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, get.getActiveFromDate()));
        dcsJaxb.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) + "");

        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, get.getActiveToDate()));
        dcsJaxb.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) + "");
        dcsJaxb.setIsEnabled(get.getIsEnabled());

//        if (get.getLastSent() != null) {
//            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, get.getLastSent()));
//            dcsJaxb.setLastSent(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
//        }
        for (DeviceTypeCommandTranslations deviceTypeCommandTranslations : dtctList) {
            if (get.getDeviceTypeCommands().getDeviceTypeCommandId().equalsIgnoreCase(deviceTypeCommandTranslations.getId().getDeviceTypeCommandId())) {
                dtctJaxb = new se.info24.devicejaxb.DeviceTypeCommandTranslations();
                dtctJaxb.setDeviceTypecommandName(deviceTypeCommandTranslations.getCommandName());
                dcsJaxb.getDeviceTypeCommandTranslations().add(dtctJaxb);
                dev.getContent().add(dcsJaxb);
            }
        }
        devices.getDevice().add(dev);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildEventDetailsForUsageLog(List<EventLog> eventLogs, List<EventTypeTranslations> eventTypeTranslationses, TingcoDevice tingcoDevice, String timeZoneGMToffset, List<EventDetails> eventDetailses) throws DatatypeConfigurationException {

        if (eventLogs.isEmpty()) {
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("No Data Found");
            return tingcoDevice;
        }
        se.info24.devicejaxb.Devices devices = new Devices();
        se.info24.devicejaxb.Device deviceJaxb = new se.info24.devicejaxb.Device();
        GregorianCalendar gc = new GregorianCalendar();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int seqNo = 1;
        for (EventLog eventLog : eventLogs) {
            EventDetail eventDetail = new EventDetail();
            eventDetail.setSeqNo(seqNo++);
            eventDetail.setEventID(eventLog.getEventId());
            eventDetail.setEventTypeID(eventLog.getEventTypeId());
            for (EventTypeTranslations ett : eventTypeTranslationses) {
                if (ett.getId().getEventTypeId().equalsIgnoreCase(eventLog.getEventTypeId())) {
                    eventDetail.setEventTypeDescription(ett.getEventTypeDescription());
                    eventDetail.setType(ett.getEventTypeName());
                    break;
                }
            }

            for (EventDetails eventDetails : eventDetailses) {
                if (eventDetails.getId().getEventId().equalsIgnoreCase(eventLog.getEventId())) {
                    se.info24.devicejaxb.EventLog eventLogjaxb = new se.info24.devicejaxb.EventLog();
                    if (eventDetails.getId().getName().equalsIgnoreCase("ErrorCode")) {
                        eventLogjaxb.setError(eventDetails.getData());
                    } else if (eventDetails.getId().getName().equalsIgnoreCase("VendorErrorCode")) {
                        eventLogjaxb.setErrorCode(eventDetails.getData());
                    }
                    eventDetail.getEventLog().add(eventLogjaxb);
                }
            }


            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, eventLog.getEventTime()));
            eventDetail.setEventTime(dateFormat.format(gc.getTime()));
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, eventLog.getCreatedDate()));
            eventDetail.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            eventDetail.setCreatedDateTCMV3(dateFormat.format(gc.getTime()));
            deviceJaxb.getContent().add(eventDetail);
        }
        devices.getDevice().add(deviceJaxb);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetEventTypes(TingcoDevice tingcoDevice, List<EventTypeTranslations> eventTypeTranslationses) {
        se.info24.devicejaxb.Devices devices = new Devices();
        se.info24.devicejaxb.Device deviceJaxb = new se.info24.devicejaxb.Device();
        int seqNo = 1;
        for (EventTypeTranslations ett : eventTypeTranslationses) {
            EventDetail eventDetail = new EventDetail();
            eventDetail.setSeqNo(seqNo++);
            eventDetail.setEventTypeID(ett.getId().getEventTypeId());
            eventDetail.setEventTypeDescription(ett.getEventTypeDescription());
            eventDetail.setType(ett.getEventTypeName());
            deviceJaxb.getContent().add(eventDetail);
        }
        devices.getDevice().add(deviceJaxb);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetDeviceManufacturesList(TingcoDevice tingcoDevice, List<DeviceManufacturers> dmList) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device dev = null;
        int seqNo = 1;
        Users2 user = null;

        for (DeviceManufacturers manfacurer : dmList) {
            dev = new se.info24.devicejaxb.Device();
            dev.setSeqNo(seqNo++);

            DeviceTypeManufacturerID dtManfacId = objectFactory.createDeviceTypeManufacturerID();
            dtManfacId.setDeviceTypeManufacturerName(manfacurer.getDeviceManufacturerName());
            dtManfacId.setValue(manfacurer.getDeviceManufacturerId());
            dev.getContent().add(dtManfacId);
            devices.getDevice().add(dev);
        }

        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetProductVariantsForDeviceReport(TingcoDevice tingcoDevice, List<RecurringPurchases> recurringPurchaseses, List<UserAlias> userAliases, List<ProductVariantTranslations> productVariantTranslationses, String timeZoneGMToffset) throws DatatypeConfigurationException {
        Devices devices = new Devices();
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd");
        se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
        int seqNo = 1;
        GregorianCalendar gc = null;
        se.info24.devicejaxb.RecurrenceTypes recurrenceTypes = new se.info24.devicejaxb.RecurrenceTypes();
        for (ProductVariantTranslations productVariantTranslations : productVariantTranslationses) {
            for (RecurringPurchases recurringPurchasesPojo : recurringPurchaseses) {
                if (recurringPurchasesPojo.getProductVariants().getProductVariantId().equalsIgnoreCase(productVariantTranslations.getId().getProductVariantId())) {
                    se.info24.devicejaxb.RecurringPurchases recurringPurchases = new se.info24.devicejaxb.RecurringPurchases();
                    recurringPurchases.setRecurringPurchaseID(recurringPurchasesPojo.getRecurringPurchaseId());
                    recurringPurchases.setQuantity(recurringPurchasesPojo.getQuantity());
                    gc = new GregorianCalendar();
                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, recurringPurchasesPojo.getActiveFromDate()));
                    recurringPurchases.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    recurringPurchases.setActiveFromDateTCMV3(lFormat.format(gc.getTime()));
                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, recurringPurchasesPojo.getActiveToDate()));
                    recurringPurchases.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    recurringPurchases.setActiveToDateTCMV3(lFormat.format(gc.getTime()));
                    recurringPurchases.setProductVariantName(productVariantTranslations.getProductVariantName());

                    for (UserAlias userAlias : userAliases) {
                        if (recurringPurchasesPojo.getUserAliasId().equalsIgnoreCase(userAlias.getUserAliasId())) {
                            recurringPurchases.setUserAlias(userAlias.getUserAlias());
                            recurringPurchases.setFirstName(userAlias.getFirstName());
                            recurringPurchases.setLastName(userAlias.getLastName());
                            recurringPurchases.setUserAliasTCMV3(userAlias.getUserAlias() + "(" + userAlias.getFirstName() + " " + userAlias.getLastName() + ")");
                        }
                    }
                    recurrenceTypes.getRecurringPurchases().add(recurringPurchases);
                }

            }

        }
        dev.getContent().add(recurrenceTypes);
        devices.getDevice().add(dev);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetDataItemsForDeviceType(TingcoDevice tingcoDevice, List<DeviceDataitemTranslations> deviceDataitemTranslationses) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();

        for (DeviceDataitemTranslations ddit : deviceDataitemTranslationses) {

            se.info24.devicejaxb.DeviceDataItems deviceDataItems = new se.info24.devicejaxb.DeviceDataItems();
            se.info24.devicejaxb.DeviceDataitemTranslations deviceDataItemTrans = new se.info24.devicejaxb.DeviceDataitemTranslations();
            DeviceDataitemTranslation deviceDataItemTran = new DeviceDataitemTranslation();
            deviceDataItemTran.setDeviceDataItemID(ddit.getId().getDeviceDataItemId());
            deviceDataItemTran.setDataItemName(ddit.getDataItemName());
            deviceDataItemTrans.getDeviceDataitemTranslation().add(deviceDataItemTran);
            deviceDataItems.setDeviceDataitemTranslations(deviceDataItemTrans);
            device.getContent().add(deviceDataItems);
        }
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetConnectorsList(TingcoDevice tingcoDevice, List<Connectors> connectorsLinkedToDevice) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();

        if (connectorsLinkedToDevice.isEmpty()) {
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
            return tingcoDevice;
        }

        se.info24.devicejaxb.Connectors connectorsJaxb = new se.info24.devicejaxb.Connectors();
        for (Connectors connector : connectorsLinkedToDevice) {
            se.info24.devicejaxb.Connector connectorJaxb = new Connector();
            connectorJaxb.setConnectorID(connector.getConnectorId());
            connectorJaxb.setConnectorName(connector.getConnectorName());
            connectorsJaxb.getConnector().add(connectorJaxb);
        }
        device.getContent().add(connectorsJaxb);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetDeviceDataItemsList(TingcoDevice tingcoDevice, List<DeviceDataitemTranslations> deviceDataItemTranslationsList) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();

        if (deviceDataItemTranslationsList.isEmpty()) {
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
            return tingcoDevice;
        }
        int seqNo = 1;

        for (DeviceDataitemTranslations dataitemTranslations : deviceDataItemTranslationsList) {
            se.info24.devicejaxb.DeviceDataItems deviceDataItemsJaxb = new se.info24.devicejaxb.DeviceDataItems();
            se.info24.devicejaxb.DeviceDataitemTranslations dataitemTranslationssJaxb = new se.info24.devicejaxb.DeviceDataitemTranslations();
            se.info24.devicejaxb.DeviceDataitemTranslation dataitemTranslationJaxb = new DeviceDataitemTranslation();
            dataitemTranslationJaxb.setDeviceDataItemID(dataitemTranslations.getId().getDeviceDataItemId());
            dataitemTranslationJaxb.setDataItemName(dataitemTranslations.getDataItemName());
            dataitemTranslationssJaxb.getDeviceDataitemTranslation().add(dataitemTranslationJaxb);
            deviceDataItemsJaxb.setDeviceDataitemTranslations(dataitemTranslationssJaxb);
            deviceDataItemsJaxb.setSeqNo(seqNo++);
            device.getContent().add(deviceDataItemsJaxb);
        }
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetConnectorTypes(TingcoDevice tingcoDevice, List<ConnectorTypes> connectorsTypesList) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();

        if (connectorsTypesList.isEmpty()) {
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("No Data Found");
            return tingcoDevice;
        }
        se.info24.devicejaxb.Connectors connectorsJaxb = new se.info24.devicejaxb.Connectors();
        se.info24.devicejaxb.Connector connectorJaxb = new Connector();
        for (ConnectorTypes connectorType : connectorsTypesList) {
            se.info24.devicejaxb.ConnectorTypes connectorTypeJaxb = new se.info24.devicejaxb.ConnectorTypes();
            connectorTypeJaxb.setConnectorTypeID(connectorType.getConnectorTypeId());
            connectorTypeJaxb.setObjectCode(connectorType.getObjectCode());
            connectorTypeJaxb.setConnectorTypeName(connectorType.getConnectorTypeName());
            connectorJaxb.getConnectorTypes().add(connectorTypeJaxb);
        }
        connectorsJaxb.getConnector().add(connectorJaxb);
        device.getContent().add(connectorsJaxb);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetConnectorModes(TingcoDevice tingcoDevice, List<ConnectorModes> connectorsModesList) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();

        if (connectorsModesList.isEmpty()) {
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("No Data Found");
            return tingcoDevice;
        }
        se.info24.devicejaxb.Connectors connectorsJaxb = new se.info24.devicejaxb.Connectors();
        se.info24.devicejaxb.Connector connectorJaxb = new Connector();
        for (ConnectorModes connectorMode : connectorsModesList) {
            se.info24.devicejaxb.ConnectorModes connectorModeJaxb = new se.info24.devicejaxb.ConnectorModes();
            connectorModeJaxb.setConnectorModeID(connectorMode.getConnectorModeId());
            connectorModeJaxb.setObjectCode(connectorMode.getObjectCode());
            connectorModeJaxb.setConnectorModeName(connectorMode.getConnectorModeName());
            connectorJaxb.getConnectorModes().add(connectorModeJaxb);
        }
        connectorsJaxb.getConnector().add(connectorJaxb);
        device.getContent().add(connectorsJaxb);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetConnectorVoltages(TingcoDevice tingcoDevice, List<ConnectorVoltages> connectorVoltagesList) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();

        if (connectorVoltagesList.isEmpty()) {
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("No Data Found");
            return tingcoDevice;
        }
        se.info24.devicejaxb.Connectors connectorsJaxb = new se.info24.devicejaxb.Connectors();
        se.info24.devicejaxb.Connector connectorJaxb = new Connector();
        for (ConnectorVoltages connectorVoltage : connectorVoltagesList) {
            se.info24.devicejaxb.ConnectorVoltages connectorVoltageJaxb = new se.info24.devicejaxb.ConnectorVoltages();
            connectorVoltageJaxb.setConnectorVoltageID(connectorVoltage.getConnectorVoltageId());
            connectorVoltageJaxb.setObjectCode(connectorVoltage.getObjectCode());
            connectorVoltageJaxb.setConnectorVoltageName(connectorVoltage.getConnectorVoltageName());
            connectorJaxb.getConnectorVoltages().add(connectorVoltageJaxb);
        }
        connectorsJaxb.getConnector().add(connectorJaxb);
        device.getContent().add(connectorsJaxb);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetConnectorCurrents(TingcoDevice tingcoDevice, List<ConnectorCurrents> connectorCurrentsList) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();

        if (connectorCurrentsList.isEmpty()) {
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("No Data Found");
            return tingcoDevice;
        }
        se.info24.devicejaxb.Connectors connectorsJaxb = new se.info24.devicejaxb.Connectors();
        se.info24.devicejaxb.Connector connectorJaxb = new Connector();
        for (ConnectorCurrents connectorCurrent : connectorCurrentsList) {
            se.info24.devicejaxb.ConnectorCurrents connectorCurrentsJaxb = new se.info24.devicejaxb.ConnectorCurrents();
            connectorCurrentsJaxb.setConnectorCurrentID(connectorCurrent.getConnectorCurrentId());
            connectorCurrentsJaxb.setObjectCode(connectorCurrent.getObjectCode());
            connectorCurrentsJaxb.setConnectorCurrentName(connectorCurrent.getConnectorCurrentName());
            connectorJaxb.getConnectorCurrents().add(connectorCurrentsJaxb);
        }
        connectorsJaxb.getConnector().add(connectorJaxb);
        device.getContent().add(connectorsJaxb);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetConnectorACDC(TingcoDevice tingcoDevice, List<ConnectorAcdc> connectorAcdcList) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();

        if (connectorAcdcList.isEmpty()) {
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("No Data Found");
            return tingcoDevice;
        }
        se.info24.devicejaxb.Connectors connectorsJaxb = new se.info24.devicejaxb.Connectors();
        se.info24.devicejaxb.Connector connectorJaxb = new Connector();
        for (ConnectorAcdc connectorAcdc : connectorAcdcList) {
            se.info24.devicejaxb.ConnectorACDC connectorAcDcJaxb = new se.info24.devicejaxb.ConnectorACDC();
            connectorAcDcJaxb.setConnectorACDCID(connectorAcdc.getConnectorAcdcid());
            connectorAcDcJaxb.setObjectCode(connectorAcdc.getObjectCode());
            connectorAcDcJaxb.setConnectorACDCName(connectorAcdc.getConnectorAcdcname());
            connectorJaxb.getConnectorACDC().add(connectorAcDcJaxb);
        }
        connectorsJaxb.getConnector().add(connectorJaxb);
        device.getContent().add(connectorsJaxb);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetLoadBalanceList(TingcoDevice tingcoDevice, List<LoadBalance> loadBalanceList) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();

        if (loadBalanceList.isEmpty()) {
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("No Data Found");
            return tingcoDevice;
        }
        se.info24.devicejaxb.Connectors connectorsJaxb = new se.info24.devicejaxb.Connectors();
        se.info24.devicejaxb.Connector connectorJaxb = new Connector();
        for (LoadBalance loadBalance : loadBalanceList) {
            se.info24.devicejaxb.LoadBalance loadBalanceJaxb = new se.info24.devicejaxb.LoadBalance();
            loadBalanceJaxb.setLoadBalanceID(loadBalance.getLoadBalanceId());
            loadBalanceJaxb.setLoadBalanceName(loadBalance.getLoadBalanceName());
            connectorJaxb.getLoadBalance().add(loadBalanceJaxb);
        }
        connectorsJaxb.getConnector().add(connectorJaxb);
        device.getContent().add(connectorsJaxb);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetConnectorDetailsById(TingcoDevice tingcoDevice, Connectors connector, int CountryID, Object loadBalanceObject, DeviceDataitemTranslations dataitemTranslations) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.Connectors connectorsJaxb = new se.info24.devicejaxb.Connectors();
        se.info24.devicejaxb.Connector connectorJaxb = new Connector();
        connectorJaxb.setConnectorID(connector.getConnectorId());
        connectorJaxb.setConnectorName(connector.getConnectorName());
        connectorJaxb.setIsEnabled(connector.getIsEnabled());
        connectorJaxb.setIsVisibleInAPI(connector.getIsVisibleInApi());
        if (connector.getProductVariants() != null) {
            for (Object object : connector.getProductVariants().getProductVariantTranslationses()) {
                ProductVariantTranslations pvt = (ProductVariantTranslations) object;
                if (pvt.getId().getCountryId() == CountryID) {
                    connectorJaxb.setProductVariantName(pvt.getProductVariantName());
                }
            }
        }
        if (connector.getConnectorDescription() != null) {
            connectorJaxb.setConnectorDescription(connector.getConnectorDescription());
        }
        if (connector.getDeviceDataItems() != null) {
            connectorJaxb.setDeviceDataItemID(connector.getDeviceDataItems().getDeviceDataItemId());
        }
        if (connector.getConnectorTypes() != null) {
            se.info24.devicejaxb.ConnectorTypes connectorTypesJaxb = new se.info24.devicejaxb.ConnectorTypes();
            connectorTypesJaxb.setConnectorTypeID(connector.getConnectorTypes().getConnectorTypeId());
            connectorTypesJaxb.setConnectorTypeName(connector.getConnectorTypes().getConnectorTypeName());
            connectorJaxb.getConnectorTypes().add(connectorTypesJaxb);
        }
        if (connector.getConnectorModes() != null) {
            se.info24.devicejaxb.ConnectorModes connectorModesJaxb = new se.info24.devicejaxb.ConnectorModes();
            connectorModesJaxb.setConnectorModeID(connector.getConnectorModes().getConnectorModeId());
            connectorModesJaxb.setConnectorModeName(connector.getConnectorModes().getConnectorModeName());
            connectorJaxb.getConnectorModes().add(connectorModesJaxb);
        }
        if (connector.getConnectorVoltages() != null) {
            se.info24.devicejaxb.ConnectorVoltages connectorVoltagesJaxb = new se.info24.devicejaxb.ConnectorVoltages();
            connectorVoltagesJaxb.setConnectorVoltageID(connector.getConnectorVoltages().getConnectorVoltageId());
            connectorVoltagesJaxb.setConnectorVoltageName(connector.getConnectorVoltages().getConnectorVoltageName());
            connectorJaxb.getConnectorVoltages().add(connectorVoltagesJaxb);
        }
        if (connector.getConnectorCurrents() != null) {
            se.info24.devicejaxb.ConnectorCurrents connectorCurrentsJaxb = new se.info24.devicejaxb.ConnectorCurrents();
            connectorCurrentsJaxb.setConnectorCurrentID(connector.getConnectorCurrents().getConnectorCurrentId());
            connectorCurrentsJaxb.setConnectorCurrentName(connector.getConnectorCurrents().getConnectorCurrentName());
            connectorJaxb.getConnectorCurrents().add(connectorCurrentsJaxb);
        }
        if (connector.getConnectorAcdc() != null) {
            se.info24.devicejaxb.ConnectorACDC connectorAcdcJaxb = new se.info24.devicejaxb.ConnectorACDC();
            connectorAcdcJaxb.setConnectorACDCID(connector.getConnectorAcdc().getConnectorAcdcid());
            connectorAcdcJaxb.setConnectorACDCName(connector.getConnectorAcdc().getConnectorAcdcname());
            connectorJaxb.getConnectorACDC().add(connectorAcdcJaxb);
        }
        if (loadBalanceObject != null) {
            LoadBalance loadBalance = (LoadBalance) loadBalanceObject;
            se.info24.devicejaxb.LoadBalance loadBalanceJaxb = new se.info24.devicejaxb.LoadBalance();
            loadBalanceJaxb.setLoadBalanceID(loadBalance.getLoadBalanceId());
            loadBalanceJaxb.setLoadBalanceName(loadBalance.getLoadBalanceName());
            connectorJaxb.getLoadBalance().add(loadBalanceJaxb);
        }
        if (connector.getProductVariants() != null) {
            connectorJaxb.setProductVariantID(connector.getProductVariants().getProductVariantId());
        }

        connectorJaxb.setSortOrder(connector.getSortOrder());

        if (dataitemTranslations != null) {
            se.info24.devicejaxb.DeviceDataitemTranslation dataitemTranslationJaxb = new DeviceDataitemTranslation();
            dataitemTranslationJaxb.setDeviceDataItemID(dataitemTranslations.getId().getDeviceDataItemId());
            dataitemTranslationJaxb.setDataItemName(dataitemTranslations.getDataItemName());
            connectorJaxb.getDeviceDataitemTranslation().add(dataitemTranslationJaxb);
        }
        if (connector.getPublicComment1() != null) {
            connectorJaxb.setPublicComment1(connector.getPublicComment1());
        }
        if (connector.getPublicComment2() != null) {
            connectorJaxb.setPublicComment2(connector.getPublicComment2());
        }
        if (connector.getInternalComment() != null) {
            connectorJaxb.setInternalComment(connector.getInternalComment());
        }
        if (connector.getConnectorImageUrl() != null) {
            connectorJaxb.setConnectorImageURL(connector.getConnectorImageUrl());
        }
        if (connector.getSearchTags() != null) {
            connectorJaxb.setSearchTags(connector.getSearchTags());
        }

        connectorsJaxb.getConnector().add(connectorJaxb);
        device.getContent().add(connectorsJaxb);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetDeviceTypeChannels(TingcoDevice tingcoDevice, List<DeviceTypeChannels> deviceTypeChannelsList, List<Channels> channelsList) {
        if (channelsList.isEmpty()) {
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("No Data Found");
            return tingcoDevice;
        }
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        for (Channels channel : channelsList) {
            for (DeviceTypeChannels dtc : deviceTypeChannelsList) {
                if (channel.getChannelId().equalsIgnoreCase(dtc.getId().getChannelId())) {
                    se.info24.devicejaxb.DeviceTypeChannels deviceTypeChannelJaxb = new se.info24.devicejaxb.DeviceTypeChannels();
                    se.info24.devicejaxb.Channels channelJaxb = new se.info24.devicejaxb.Channels();
                    deviceTypeChannelJaxb.setChannelID(dtc.getId().getChannelId());
                    deviceTypeChannelJaxb.setChannelDirection(dtc.getId().getChannelDirection());
                    channelJaxb.setChannelName(channel.getChannelName());
                    if (dtc.getChannelTag() != null) {
                        deviceTypeChannelJaxb.setChannelTag(dtc.getChannelTag());
                    }
                    if (channel.getChannelData() != null) {
                        channelJaxb.setChannelData(channel.getChannelData());
                    }
                    deviceTypeChannelJaxb.getChannels().add(channelJaxb);
                    device.getContent().add(deviceTypeChannelJaxb);
                    break;
                }
            }
        }
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetAllDeviceDataItem(TingcoDevice tingcoDevice, List<DeviceDataItems> allDeviceDataItems) {
        if (allDeviceDataItems.isEmpty()) {
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("No Data Found");
            return tingcoDevice;
        }
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        int seqNo = 1;
        for (DeviceDataItems ddi : allDeviceDataItems) {
            se.info24.devicejaxb.DeviceDataItems dataItemsJaxb = new se.info24.devicejaxb.DeviceDataItems();
            dataItemsJaxb.setSeqNo(seqNo++);
            dataItemsJaxb.setDeviceDataItemID(ddi.getDeviceDataItemId());
            dataItemsJaxb.setDeviceDataFieldName(ddi.getDeviceDataFieldName());
            dataItemsJaxb.setDeviceDataGroupName(ddi.getDeviceDataGroupName());
            dataItemsJaxb.setUnit(ddi.getUnit());
            device.getContent().add(dataItemsJaxb);
        }
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetDevcieDataItembyDeviceDataItemID(TingcoDevice tingcoDevice, DeviceDataItems dataItems, List<Country> countries, List<DeviceDataitemTranslations> dataitemTranslationses) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.DeviceDataItems dataItemsJaxb = new se.info24.devicejaxb.DeviceDataItems();
        dataItemsJaxb.setDeviceDataItemID(dataItems.getDeviceDataItemId());
        dataItemsJaxb.setDeviceDataFieldName(dataItems.getDeviceDataFieldName());
        dataItemsJaxb.setDeviceDataGroupName(dataItems.getDeviceDataGroupName());
        dataItemsJaxb.setUnit(dataItems.getUnit());
        se.info24.devicejaxb.DeviceDataitemTranslations dataitemTranslationsJaxb = new se.info24.devicejaxb.DeviceDataitemTranslations();
        int seqNo = 1;
        for (Country country : countries) {
            se.info24.devicejaxb.DeviceDataitemTranslation dataitemTranslationJaxb = new DeviceDataitemTranslation();
            dataitemTranslationJaxb.setSeqNo(seqNo++);
            CountryID countryID = new CountryID();
            for (DeviceDataitemTranslations ddt : dataitemTranslationses) {
                if (ddt.getId().getCountryId() == country.getCountryId()) {
                    dataitemTranslationJaxb.setDataItemName(ddt.getDataItemName());
                    break;
                }
            }
            countryID.setCountryName(country.getCountryName());
            countryID.setValue(country.getCountryId());
            dataitemTranslationJaxb.setCountryID(countryID);
            dataitemTranslationsJaxb.getDeviceDataitemTranslation().add(dataitemTranslationJaxb);
        }
        dataItemsJaxb.setDeviceDataitemTranslations(dataitemTranslationsJaxb);
        device.getContent().add(dataItemsJaxb);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetAllDeviceTypeCommands(TingcoDevice tingcoDevice, List<DeviceTypeCommands> deviceTypeCommandses, List<DeviceTypeCommandTranslations> dtcts, List<DeviceTypes> dts) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        for (DeviceTypeCommandTranslations dtct : dtcts) {
            for (DeviceTypeCommands dtc : deviceTypeCommandses) {
                if (dtct.getId().getDeviceTypeCommandId().equalsIgnoreCase(dtc.getDeviceTypeCommandId())) {
                    se.info24.devicejaxb.DeviceTypeCommands dtcJaxb = new se.info24.devicejaxb.DeviceTypeCommands();
                    dtcJaxb.setDeviceTypeCommandID(dtc.getDeviceTypeCommandId());
                    for (DeviceTypes deviceTypes : dts) {
                        if (deviceTypes.getDeviceTypeId().equalsIgnoreCase(dtc.getDeviceTypeId())) {
                            se.info24.devicejaxb.DeviceTypeCommandTranslations commandTranslationsJaxb = new se.info24.devicejaxb.DeviceTypeCommandTranslations();
                            commandTranslationsJaxb.setDeviceTypecommandName(dtct.getCommandName() + "(" + deviceTypes.getDeviceTypeName() + ")");
                            dtcJaxb.getDeviceTypeCommandTranslations().add(commandTranslationsJaxb);
                            break;
                        }
                    }
                    device.getContent().add(dtcJaxb);
                }
            }

        }

        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetUserRoleDeviceTypeCommandDetails(TingcoDevice tingcoDevice, List<DeviceTypeCommandTranslations> commandTranslationses, List<PermissionTranslations> permissionTranslationses, List<DeviceTypes> deviceTypeses, List<UserRoleObjectPermissions2> userRoleObjectPermissions2s, List<DeviceTypeCommands> deviceTypeCommandses) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();

        for (DeviceTypeCommandTranslations dtct : commandTranslationses) {
            for (UserRoleObjectPermissions2 urop : userRoleObjectPermissions2s) {
                if (urop.getId().getObjectId().equalsIgnoreCase(dtct.getId().getDeviceTypeCommandId())) {

                    se.info24.devicejaxb.UserRoleObjectPermissions2 uropJaxb = new se.info24.devicejaxb.UserRoleObjectPermissions2();

                    se.info24.devicejaxb.DeviceTypeCommandTranslations dtctJaxb = new se.info24.devicejaxb.DeviceTypeCommandTranslations();
                    dtctJaxb.setDeviceTypeCommandID(dtct.getId().getDeviceTypeCommandId());
                    dtctJaxb.setDeviceTypecommandName(dtct.getCommandName());

                    for (PermissionTranslations pt : permissionTranslationses) {
                        if (pt.getId().getPermissionId().equalsIgnoreCase(urop.getId().getPermissionId())) {
                            se.info24.devicejaxb.PermissionTranslations ptJaxb = new se.info24.devicejaxb.PermissionTranslations();
                            ptJaxb.setPermissionID(pt.getId().getPermissionId());
                            ptJaxb.setPermissionName(pt.getPermissionName());
                            uropJaxb.getPermissionTranslations().add(ptJaxb);
                            break;
                        }
                    }
                    for (DeviceTypeCommands dtc : deviceTypeCommandses) {
                        if (dtc.getDeviceTypeCommandId().equalsIgnoreCase(urop.getId().getObjectId())) {
                            for (DeviceTypes deviceTypes : deviceTypeses) {
                                if (deviceTypes.getDeviceTypeId().equalsIgnoreCase(dtc.getDeviceTypeId())) {
                                    DeviceTypeID deviceTypeID = new DeviceTypeID();
                                    deviceTypeID.setDeviceTypeName(deviceTypes.getDeviceTypeName());
                                    deviceTypeID.setValue(deviceTypes.getDeviceTypeId());
                                    uropJaxb.setDeviceTypeID(deviceTypeID);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    uropJaxb.getDeviceTypeCommandTranslations().add(dtctJaxb);
                    device.getContent().add(uropJaxb);
                }
            }
        }
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetMediaFilesDetails(TingcoDevice tingcoDevice, String timeZoneGMToffset, List<MediaFiles> filesOrderedByName, List<GroupTranslations> groupTranslationses, List<Users2> users2List, List<MediaFileTypes> fileTypeses) {
        se.info24.devicejaxb.ObjectMediaFiles objectMediaFilesJaxb = new se.info24.devicejaxb.ObjectMediaFiles();
        ObjectMediaFile objectMediaFileJaxb = new ObjectMediaFile();
        int seqNo = 1;
        GregorianCalendar gc = new GregorianCalendar();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (MediaFiles mf : filesOrderedByName) {
            se.info24.devicejaxb.MediaFiles mediafilesJaxb = new se.info24.devicejaxb.MediaFiles();
            mediafilesJaxb.setSeqNo(seqNo++);
            mediafilesJaxb.setMediaFileID(mf.getMediaFileId());
            mediafilesJaxb.setMediaFileName(mf.getMediaFileName());
            mediafilesJaxb.setMediaFileExtension(mf.getMediaFileExtension());
            if (mf.getMediaFileDescription() != null) {
                mediafilesJaxb.setMediaFileDescription(mf.getMediaFileDescription());
            }
            if (mf.getMediaFileTypeId() != null) {
                for (MediaFileTypes mft : fileTypeses) {
                    if (mf.getMediaFileTypeId().equalsIgnoreCase(mft.getMediaFileTypeId())) {
                        se.info24.devicejaxb.MediaFileTypes mediaFileTypesJaxb = new se.info24.devicejaxb.MediaFileTypes();
                        mediaFileTypesJaxb.setMediaFileTypeID(mft.getMediaFileTypeId());
                        mediaFileTypesJaxb.setTypeName(mft.getTypeName());
                        mediafilesJaxb.getMediaFileTypes().add(mediaFileTypesJaxb);
                        break;
                    }
                }
            }
//            if (mf.getMediaFileBlob() != null) {
//                byte[] bytes = mf.getMediaFileBlob();
//                StringBuffer sb = new StringBuffer();
//                for (byte b : bytes) {
//                    sb.append(toBinary(b));
//                }
//                mediafilesJaxb.setMediaFileBlob(sb.toString());
//            }
            for (GroupTranslations gt : groupTranslationses) {
                if (gt.getId().getGroupId().equalsIgnoreCase(mf.getGroupId())) {
                    mediafilesJaxb.setGroupID(gt.getId().getGroupId());
                    mediafilesJaxb.setGroupName(gt.getGroupName());
                    break;
                }
            }
            if (mf.getLastUpdatedByUserId() != null) {
                for (Users2 u : users2List) {
                    if (u.getUserId().equalsIgnoreCase(mf.getLastUpdatedByUserId())) {
                        LastUpdatedByUserID userID = new LastUpdatedByUserID();
                        userID.setUserFullName(u.getFirstName() + " " + u.getLastName());
                        userID.setValue(u.getUserId());
                        for (GroupTranslations gt : groupTranslationses) {
                            if (gt.getId().getGroupId().equalsIgnoreCase(u.getGroupId())) {
                                mediafilesJaxb.setUserGroupID(gt.getId().getGroupId());
                                mediafilesJaxb.setUserGroupName(gt.getGroupName());
                                break;
                            }
                        }
                        mediafilesJaxb.setLastUpdatedByUserID(userID);
                        break;
                    }
                }
            }
            if (mf.getUpdatedDate() != null) {
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, mf.getUpdatedDate()));
                mediafilesJaxb.setUpdatedDate(dateFormat.format(gc.getTime()));
            }
            objectMediaFileJaxb.getMediaFiles().add(mediafilesJaxb);
        }
        objectMediaFilesJaxb.getObjectMediaFile().add(objectMediaFileJaxb);
        tingcoDevice.setObjectMediaFiles(objectMediaFilesJaxb);
        return tingcoDevice;
    }

    public TingcoDevice buildGetBillingCategoriesByDeviceType(TingcoDevice tingcoDevice, List<BillingCategoryTranslations> billingCategoryTranslationses) {
        if (billingCategoryTranslationses.isEmpty()) {
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("No Data Found");
            return tingcoDevice;
        }
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.BillingCategories bcJaxb = new se.info24.devicejaxb.BillingCategories();
        for (BillingCategoryTranslations bct : billingCategoryTranslationses) {
            se.info24.devicejaxb.BillingCategoryTranslations bctJaxb = new se.info24.devicejaxb.BillingCategoryTranslations();
            bctJaxb.setBillingCategoryID(bct.getId().getBillingCategoryId());
            bctJaxb.setCategoryName(bct.getCategoryName());
            bcJaxb.getBillingCategoryTranslations().add(bctJaxb);
        }
        device.getContent().add(bcJaxb);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetBillingCategoriesByDeviceType(TingcoDevice tingcoDevice, List<BillingCategoryTranslations> billingCategoryTranslationses, List<BillingCategories> billingCategoriesesFiltered, List<GroupTranslations> groupTranslationses) {
        if (billingCategoryTranslationses.isEmpty()) {
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("No Data Found");
            return tingcoDevice;
        }
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();

        for (BillingCategoryTranslations bct : billingCategoryTranslationses) {
            se.info24.devicejaxb.BillingCategories bcJaxb = new se.info24.devicejaxb.BillingCategories();
            se.info24.devicejaxb.BillingCategoryTranslations bctJaxb = new se.info24.devicejaxb.BillingCategoryTranslations();
            bctJaxb.setBillingCategoryID(bct.getId().getBillingCategoryId());
            bctJaxb.setCategoryName(bct.getCategoryName());
            //
            for (BillingCategories bc : billingCategoriesesFiltered) {
                if (bc.getBillingCategoryId().equalsIgnoreCase(bct.getId().getBillingCategoryId())) {
                    for (GroupTranslations gt : groupTranslationses) {
                        if (gt.getId().getGroupId().equalsIgnoreCase(bc.getGroupId())) {
                            GroupID groupID = new GroupID();
                            groupID.setValue(gt.getId().getGroupId());
                            groupID.setGroupName(gt.getGroupName());
                            bcJaxb.setGroupID(groupID);
                            break;
                        }
                    }
                    break;
                }
            }
            bcJaxb.getBillingCategoryTranslations().add(bctJaxb);
            device.getContent().add(bcJaxb);
        }

        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetAllEventTypeDetailsByGroupId(TingcoDevice tingcoDevice, List<EventTypeTranslations> eventTypeTranslationses) {
        if (eventTypeTranslationses.isEmpty()) {
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("No Data Found");
            return tingcoDevice;
        }
        Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        EventTypes eventTypesJaxb = new EventTypes();
        int seqNo = 1;
        for (EventTypeTranslations ett : eventTypeTranslationses) {
            EventTypeTranslation ettJaxb = new EventTypeTranslation();
            ettJaxb.setSeqNo(seqNo++);
            ettJaxb.setEventTypeID(ett.getId().getEventTypeId());
            ettJaxb.setEventTypeName(ett.getEventTypeName());
            eventTypesJaxb.getEventTypeTranslation().add(ettJaxb);
        }
        device.getContent().add(eventTypesJaxb);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetTransactionReceiptDetails(TingcoDevice tingcoDevice, List<TransactionProducts> tp, TransactionResult tr, Currency currency, GroupTranslations gt, Country country, se.info24.pojo.Device devicePojo, Session session) {
        Devices devices = new Devices();
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DecimalFormat dformat = new DecimalFormat("#.##");
//        dformat.setMaximumFractionDigits(2);
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.TransactionResult transactionResult = new se.info24.devicejaxb.TransactionResult();
        transactionResult.setTransactionID(tr.getTransactionId());
//        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, tr.getTransactionStartTime()));
        transactionResult.setTransactionStartTimev3(df1.format(tr.getTransactionStartTime()));
        transactionResult.setDeviceName(devicePojo.getDeviceName());
        if (gt != null) {
            transactionResult.setGroupName(gt.getGroupName());
        }
        transactionResult.setCountryId(country.getCountryId());
        if(country.getCountryId() == 1 || country.getCountryId() == 4){
            transactionResult.setVATPercent("25%");
            transactionResult.setVATAmount(Float.valueOf(dformat.format(tr.getAmount().multiply(new BigDecimal("0.2"))).replace(",", "."))+"");
        }else if(country.getCountryId() == 5){
            transactionResult.setVATPercent("24%");
            transactionResult.setVATAmount(Float.valueOf(dformat.format(tr.getAmount().multiply(new BigDecimal("0.1935"))).replace(",", "."))+"");
        }else{
            transactionResult.setVATPercent("0%");
            transactionResult.setVATAmount("0");
        }
        transactionResult.setLanguage(country.getLanguage());
        transactionResult.setLanguageCode(country.getLanguageCode());
        transactionResult.setAmount(Float.valueOf(dformat.format(tr.getAmount()).replace(",", ".")));
        transactionResult.setCurrencyISOCharCode(currency.getCurrencyIsocharCode());
        for (TransactionProducts transactionProduct : tp) {
            se.info24.devicejaxb.TransactionProducts transactionProducts = new se.info24.devicejaxb.TransactionProducts();
            ProductsDAO productDao = new ProductsDAO();
            ProductVariantTranslations productVariantTranslations = productDao.getProductVariantTranslationsByIds(transactionProduct.getId().getProductVariantId(), 6, session);
            if (productVariantTranslations != null) {
                transactionProducts.setProductVariantName(productVariantTranslations.getProductVariantName());
            }

            transactionProducts.setQuantity(transactionProduct.getQuantity());
            transactionProducts.setQuantityUnit(transactionProduct.getQuantityUnit());
            float ppu = ((float) transactionProduct.getPpu()) / 100;
            transactionProducts.setPPU(dformat.format(ppu).replace(",", "."));
            transactionResult.getTransactionProducts().add(transactionProducts);
        }
        device.getContent().add(transactionResult);
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetDeviceStateWidgetDetails(TingcoDevice tingcoDevice, Hashtable<String, Integer> ht, int size) {
        se.info24.devicejaxb.Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();
        for (String string : ht.keySet()) {
            se.info24.devicejaxb.ItemConnectionStatus ICSJaxb = new se.info24.devicejaxb.ItemConnectionStatus();
            ICSJaxb.setObjectStateCode(string);
            float objectCodepersent = ht.get(string) * 100 / size;
            ICSJaxb.setConnectedTCMV3(objectCodepersent + "");
            device.getContent().add(ICSJaxb);
        }
        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetChargePointStatusWidgetDetails(TingcoDevice tingcoDevice, List<Device> deviceList, List<Connectors> connectorses, List<DeviceActivationSchedules> deviceActivationScheduleses, String timeZoneGMToffset, List<ItemConnectionStatus> ICS, List<DeviceStatusDataItems> deviceStatusDataItemses) {
        int seq = 0;
        Devices devices = new Devices();
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        GregorianCalendar gc = new GregorianCalendar();
        try {
            se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
            dev.setSeqNo(++seq);
            dev.getContent().add(objectFactory.createDeviceID(connectorses.get(0).getDeviceId()));
            se.info24.devicejaxb.Connectors connectorsJaxb = new se.info24.devicejaxb.Connectors();
            se.info24.devicejaxb.Connector connectorJaxb = null;
            boolean flag = false;
            ChargePointStatusWidgetDetails cpswd = null;

            for (Connectors connectors : connectorses) {
                connectorJaxb = new se.info24.devicejaxb.Connector();
                connectorJaxb.setConnectorID(connectors.getConnectorId());
                connectorJaxb.setConnectorName(connectors.getConnectorName());
                flag = false;
                for (Device device : deviceList) {
                    if (connectors.getDeviceId().equalsIgnoreCase(device.getDeviceId())) {
                        connectorJaxb.setDeviceID(connectors.getDeviceId());
                        connectorJaxb.setDeviceName(device.getDeviceName());
                        connectorJaxb.setDeviceTypeName(device.getDeviceTypes().getDeviceTypeName());
                        break;
                    }
                }
                for (ItemConnectionStatus itemConnectionStatus : ICS) {
                    if (connectors.getDeviceId().equalsIgnoreCase(itemConnectionStatus.getItemId())) {
                        flag = true;
                        cpswd = new ChargePointStatusWidgetDetails();
                        cpswd.setConnected(itemConnectionStatus.getConnected() + "");
                        cpswd.setObjectStateCode(itemConnectionStatus.getObjectStateCode());
                        break;
                    }

                }
                for (DeviceStatusDataItems deviceStatusDataItems : deviceStatusDataItemses) {
                    if (connectors.getDeviceId().equalsIgnoreCase(deviceStatusDataItems.getDeviceDataValue())) {
                        if (!flag) {
                            cpswd = new ChargePointStatusWidgetDetails();
                            flag = true;
                        }
                        cpswd.setDeviceDataValue("1");
                        break;
                    }
                }
                for (DeviceActivationSchedules deviceActivationSchedules : deviceActivationScheduleses) {
                    if (connectors.getDeviceId().equalsIgnoreCase(deviceActivationSchedules.getDeviceId())) {
                        if (!flag) {
                            cpswd = new ChargePointStatusWidgetDetails();
                            flag = true;
                        }
                        cpswd.setUserAlias(deviceActivationSchedules.getUserAlias());
                        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, deviceActivationSchedules.getStartTime()));
                        cpswd.setStartTime(lFormat.format(gc.getTime()));
                        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, deviceActivationSchedules.getStopTime()));
                        cpswd.setStopTime(lFormat.format(gc.getTime()));
                        break;
                    }
                }
                if (flag) {
                    connectorJaxb.getChargePointStatusWidgetDetails().add(cpswd);
                }
                connectorsJaxb.getConnector().add(connectorJaxb);
            }
            dev.getContent().add(connectorsJaxb);
            devices.getDevice().add(dev);
            tingcoDevice.setDevices(devices);
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
//        }
        return tingcoDevice;
    }

    public se.info24.devicejaxb.Device buildExportxsl(List<Connectors> connectorses, List<DeviceStatusDataItems> deviceStatusList, List<DeviceDataItems> ddiList, int countryId, Session mSession) {
        se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
        try {
            dev.getContent().add(objectFactory.createDeviceID(connectorses.get(0).getDeviceId()));
            se.info24.devicejaxb.Connectors connectorsJaxb = new se.info24.devicejaxb.Connectors();
            se.info24.devicejaxb.Connector connectorJaxb = null;
            for (DeviceDataItems ddi : ddiList) {
                for (Connectors connectorse : connectorses) {
                    if (connectorse.getDeviceDataItems().getDeviceDataItemId().equalsIgnoreCase(ddi.getDeviceDataItemId())) {
                        connectorJaxb = new se.info24.devicejaxb.Connector();
                        connectorJaxb.setDeviceID(connectorse.getDeviceId());
                        connectorJaxb.setConnectorID(connectorse.getConnectorId());
                        connectorJaxb.setConnectorName(connectorse.getConnectorName());
                        connectorJaxb.setConnectorModeCode(connectorse.getConnectorModes().getConnectorModeName());
                        connectorJaxb.setConnectorTypeCode(connectorse.getConnectorTypes().getConnectorTypeName());
                        connectorJaxb.setConnectorCurrentID(connectorse.getConnectorCurrents().getConnectorCurrentId());
                        connectorJaxb.setConnectorCurrentCode(connectorse.getConnectorCurrents().getConnectorCurrentName());
                        connectorJaxb.setConnectorVoltageID(connectorse.getConnectorVoltages().getConnectorVoltageId());
                        connectorJaxb.setConnectorVoltageCode(connectorse.getConnectorVoltages().getConnectorVoltageName());
                        for (Object object : connectorse.getProductVariants().getProductVariantTranslationses()) {
                            ProductVariantTranslations productVariantTranslations = (ProductVariantTranslations) object;
                            if (productVariantTranslations.getId().getCountryId() == countryId) {
                                connectorJaxb.setProductVariantName(productVariantTranslations.getProductVariantName());
                                break;
                            }
                        }
                        connectorJaxb.setIsEnabled(connectorse.getIsEnabled());
                        if (connectorse.getInternalComment() != null) {
                            connectorJaxb.setInternalComment(connectorse.getInternalComment());
                        }

                        connectorJaxb.setDeviceDataItemID(ddi.getDeviceDataItemId());
                        connectorJaxb.setDeviceDataItemName(ddi.getDeviceDataFieldName());
                        for (DeviceStatusDataItems ds : deviceStatusList) {
                            if (ddi.getDeviceDataItemId().equalsIgnoreCase(ds.getId().getDeviceDataItemId())) {
                                if (ds.getDeviceDataValue().equalsIgnoreCase("0") || ds.getDeviceDataValue().equalsIgnoreCase("1")) {
                                    connectorJaxb.setDeviceDataItemValue(ds.getDeviceDataValue());
                                    break;
                                }
                            }
                        }
                        connectorsJaxb.getConnector().add(connectorJaxb);
                    }
                }
            }
            dev.getContent().add(connectorsJaxb);
            return dev;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return dev;
        }
    }

    public TingcoDevice buildGetDeviceTypeCommandDetailsById(TingcoDevice tingcoDevice, DeviceTypeCommands deviceTypeCommands, DeviceTypeCommandTranslations deviceTypeCommandTranslationses) {
        se.info24.devicejaxb.Devices devices = new Devices();
        se.info24.devicejaxb.Device device = new se.info24.devicejaxb.Device();

        se.info24.devicejaxb.DeviceTypeCommands dtcJaxb = new se.info24.devicejaxb.DeviceTypeCommands();
        dtcJaxb.setObjectCode(deviceTypeCommands.getObjectCode());
        dtcJaxb.setDeviceDataItemID(deviceTypeCommands.getDeviceDataItemId());
        if (deviceTypeCommands.getDataItemValue() != null) {
            dtcJaxb.setDataItemValue(deviceTypeCommands.getDataItemValue());
        }
        if (deviceTypeCommands.getDisplayOrder() != null) {
            dtcJaxb.setDisplayOrder(deviceTypeCommands.getDisplayOrder());
        }
        if (deviceTypeCommands.getFlagIsNumericValue() != null) {
            dtcJaxb.setFlagIsNumericValue(deviceTypeCommands.getFlagIsNumericValue());
        }
        if (deviceTypeCommands.getFlagIsVisible() != null) {
            dtcJaxb.setFlagIsVisible(deviceTypeCommands.getFlagIsVisible());
        }
        if (deviceTypeCommands.getFlagUserCanSetValue() != null) {
            dtcJaxb.setFlagUserCanSetValue(deviceTypeCommands.getFlagUserCanSetValue());
        }
        if (deviceTypeCommands.getFlagValueCanBeNull() != null) {
            dtcJaxb.setFlagValueCanBeNull(deviceTypeCommands.getFlagValueCanBeNull());
        }
        if (deviceTypeCommands.getMaxCharsInValue() != null) {
            dtcJaxb.setMaxCharsInValue(deviceTypeCommands.getMaxCharsInValue());
        }
        if (deviceTypeCommands.getMaxNumericValue() != null) {
            dtcJaxb.setMaxNumericValue(deviceTypeCommands.getMaxNumericValue().floatValue());
        }
        if (deviceTypeCommands.getMinNumericValue() != null) {
            dtcJaxb.setMinNumericValue(deviceTypeCommands.getMinNumericValue().floatValue());
        }

        se.info24.devicejaxb.DeviceTypeCommandTranslations dtctJaxb = new se.info24.devicejaxb.DeviceTypeCommandTranslations();
        dtctJaxb.setDeviceTypecommandName(deviceTypeCommandTranslationses.getCommandName());
        dtctJaxb.setCommandButtonText(deviceTypeCommandTranslationses.getCommandButtonText());
        if (deviceTypeCommandTranslationses.getCommandDescription() != null) {
            dtctJaxb.setCommandDescription(deviceTypeCommandTranslationses.getCommandDescription());
        }

        dtcJaxb.getDeviceTypeCommandTranslations().add(dtctJaxb);

        device.getContent().add(dtcJaxb);

        devices.getDevice().add(device);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }

    public TingcoDevice buildGetDeviceTypeCommandsByDeviceType(TingcoDevice tingcoDevice, List<DeviceTypeCommands> dtcList, List<DeviceTypeCommandTranslations> dtct, List<DeviceDataitemTranslations> deviceDataitemTranslationses) {
        Devices devices = new Devices();
        se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
        se.info24.devicejaxb.DeviceTypeCommands deviceTypeCommandsJaxb = null;
        se.info24.devicejaxb.DeviceTypeCommandTranslations dtctJaxb = null;
        for (DeviceTypeCommandTranslations deviceTypeCommandTranslations : dtct) {
            for (DeviceTypeCommands dtc : dtcList) {
                if (deviceTypeCommandTranslations.getId().getDeviceTypeCommandId().equalsIgnoreCase(dtc.getDeviceTypeCommandId())) {
                    deviceTypeCommandsJaxb = new se.info24.devicejaxb.DeviceTypeCommands();
                    for (DeviceDataitemTranslations deviceDataitemTranslations : deviceDataitemTranslationses) {
                        if (deviceDataitemTranslations.getId().getDeviceDataItemId().equalsIgnoreCase(dtc.getDeviceDataItemId())) {
                            deviceTypeCommandsJaxb.setDataItemName(deviceDataitemTranslations.getDataItemName());
                        }
                    }
                    deviceTypeCommandsJaxb.setDeviceDataItemID(dtc.getDeviceDataItemId());
                    deviceTypeCommandsJaxb.setDataItemValue(dtc.getDataItemValue());
                    dtctJaxb = new se.info24.devicejaxb.DeviceTypeCommandTranslations();
                    dtctJaxb.setDeviceTypeCommandID(deviceTypeCommandTranslations.getId().getDeviceTypeCommandId());
                    dtctJaxb.setDeviceTypecommandName(deviceTypeCommandTranslations.getCommandName());
                    dtctJaxb.setCommandButtonText(deviceTypeCommandTranslations.getCommandButtonText());
                    deviceTypeCommandsJaxb.getDeviceTypeCommandTranslations().add(dtctJaxb);
                    dev.getContent().add(deviceTypeCommandsJaxb);
                }
            }
        }
        devices.getDevice().add(dev);
        tingcoDevice.setDevices(devices);
        return tingcoDevice;
    }
}
