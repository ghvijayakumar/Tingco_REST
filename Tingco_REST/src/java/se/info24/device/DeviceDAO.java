/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.device;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.jms.Connection;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.apache.commons.codec.binary.Base64;
import org.apache.xmlbeans.XmlOptions;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import se.info24.devicejaxb.Devices;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.devicejaxbPost.DeviceAddress;
import se.info24.devicejaxbPost.DeviceCountReport;
import se.info24.devicejaxbPost.DeviceSetting;
import se.info24.devicejaxbPost.EventTypeTranslation;
import se.info24.devicejaxbPost.ObjectUsageRecords;
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
import se.info24.ismOperationsPojo.TransactionProducts;
import se.info24.ismOperationsPojo.TransactionResult;
import se.info24.pojo.AddressType;
import se.info24.pojo.Addresses;
import se.info24.pojo.Agreements;
import se.info24.pojo.BillingCategories;
import se.info24.pojo.BillingCategoryTranslations;
import se.info24.pojo.Channels;
import se.info24.pojo.CommandParseTargets;
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
import se.info24.pojo.DataItemTranslationsPerDevice;
import se.info24.pojo.Device;
import se.info24.pojo.DeviceBillingCategories;
import se.info24.pojo.DeviceCommandSchedules;
import se.info24.pojo.DeviceDataItemScaling;
import se.info24.pojo.DeviceDataItems;
import se.info24.pojo.DeviceDataitemTranslations;
import se.info24.pojo.DeviceLinks;
import se.info24.pojo.DeviceManufacturers;
import se.info24.pojo.DeviceOperationsMember;
import se.info24.pojo.DeviceOperationsStatus;
import se.info24.pojo.DeviceOperationsStatusTranslation;
import se.info24.pojo.DevicePendingDelete;
import se.info24.pojo.DeviceSettings;
import se.info24.pojo.DeviceSettingsPackages;
import se.info24.pojo.DeviceTypeBillingCategories;
import se.info24.pojo.DeviceTypeChannels;
import se.info24.pojo.DeviceTypeCommandTranslations;
import se.info24.pojo.DeviceTypeCommands;
import se.info24.pojo.DeviceTypeDataitems;
import se.info24.pojo.DeviceTypes;
import se.info24.pojo.DeviceTypesInGroups;
import se.info24.pojo.EventActionSchedules;
import se.info24.pojo.EventItemActions;
import se.info24.pojo.EventItems;
import se.info24.pojo.EventTypeTranslations;
import se.info24.pojo.EventTypesInGroups;
import se.info24.pojo.EventTypesInTypes;
import se.info24.pojo.FieldTranslations;
import se.info24.pojo.GroupObjectSettingPackages;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.ListValues;
import se.info24.pojo.LoadBalance;
import se.info24.pojo.MediaFileTypes;
import se.info24.pojo.ObjectContactMemberships;
import se.info24.pojo.ObjectContactMembershipsId;
import se.info24.pojo.ObjectContacts;
import se.info24.pojo.ObjectFieldData;
import se.info24.pojo.ObjectGroupTranslations;
import se.info24.pojo.ObjectGroups;
import se.info24.pojo.ObjectMediaFiles;
import se.info24.pojo.ObjectSettingPackageMemberships;
import se.info24.pojo.ObjectSettingPackageMembershipsId;
import se.info24.pojo.ObjectSettingPackageTemplates;
import se.info24.pojo.ObjectSettingPackageTranslations;
import se.info24.pojo.ObjectSettingPackages;
import se.info24.pojo.ObjectSettingTemplates;
import se.info24.pojo.ObjectStateCodeTranslations;
import se.info24.pojo.ObjectStateCodes;
import se.info24.pojo.ObjectStateControl;
import se.info24.pojo.ObjectTypeFields;
import se.info24.pojo.ObjectTypeSettingTemplates;
import se.info24.objectpojo.ObjectUsageSummaryReport;
import se.info24.pojo.Ogmdevice;
import se.info24.pojo.PermissionTranslations;
import se.info24.pojo.ProductVariantDevices;
import se.info24.pojo.ProductVariantTranslations;
import se.info24.pojo.RecurrenceTypes;
import se.info24.pojo.RecurringPurchases;
import se.info24.pojo.Schedules;
import se.info24.pojo.ServiceClientLogins;
import se.info24.pojo.ServiceDeviceSettingTemplates;
import se.info24.pojo.ServiceDeviceSettings;
import se.info24.pojo.ServiceDeviceSubscriptions;
import se.info24.pojo.Services;
import se.info24.pojo.SettingDataType;
import se.info24.pojo.UserAlias;
import se.info24.pojo.UserFavoriteDataItems;
import se.info24.pojo.UserFavoriteDevices;
import se.info24.pojo.UserFavoriteDevicesId;
import se.info24.pojo.UserRoleObjectPermissions2;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.Users2;
import se.info24.protocols.em.Detail;
import se.info24.protocols.em.Details;
import se.info24.protocols.em.EventMessage;
import se.info24.protocols.em.EventMessageDocument;
import se.info24.restUtil.RestUtilDAO;
import se.info24.service.ServiceDAO;
import se.info24.user.UserDAO;
import se.info24.util.HibernateUtil;
import se.info24.util.TCMUtil;
import se.info24.util.TingcoConstants;

/**
 *
 * @author Sridhar Dasari | Sekhar Babu
 */
public class DeviceDAO {

    public List<Device> getDeviceByDeviceAndGroupIDList(Set<String> groupIdSet, Object objectId, Session session) {
        return session.getNamedQuery("getDeviceByDeviceGroupID").setParameterList("groupId", groupIdSet).setParameter("deviceId", objectId).list();
    }

    public List<ContainerDevices> getContainerDevicesByDeviceId(String deviceId, Session session) {
        return session.getNamedQuery("getContainerDevicesByDeviceId").setParameter("deviceId", deviceId).list();
    }

    public Channels getChannels(String channelId, Session session) {
        return (Channels) session.getNamedQuery("getChannelById").setString("channelId", channelId).uniqueResult();
    }

    public List<Object> getChargePointStatusDetails(se.info24.devicejaxbPost.Device deviceXML, Session session, String objectTags) {
        List<Object> deviceList = new ArrayList<Object>();
        StringBuffer queryString = new StringBuffer();
        if (deviceXML.getGroupID().getGroupName() != null) {
            queryString.append("select distinct d.deviceId as deviceId, d.deviceName as deviceName, d.deviceName2 as deviceName2, d.groupId as groupId,d.deviceTypeId as deviceTypeId, d.assetId  from Device as d inner join Groups as g on d.groupId " +
                    "= g.groupId and g.groupid in (" + TCMUtil.getGroupTrust(deviceXML.getGroupID().getValue()) + ") ");

            if (TCMUtil.isValidUUID(deviceXML.getGroupID().getGroupName())) {
                queryString.append("inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.groupId = '" + deviceXML.getGroupID().getGroupName() + "' ");
            } else {
                queryString.append("inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.groupName like '%" + deviceXML.getGroupID().getGroupName() + "%' ");
            }

        } else {
            queryString.append("select distinct d.deviceId as deviceId, d.deviceName as deviceName, d.deviceName2 as deviceName2, d.groupId as groupId,d.deviceTypeId as deviceTypeId, d.assetId  from Device as d inner join Groups as g on d.groupId " +
                    "= g.groupId and g.groupid in(" + TCMUtil.getGroupTrust(deviceXML.getGroupID().getValue()) + ") ");
        }

        if (deviceXML.getOptionalCountryID() != null) {
            queryString.append("inner join Addresses as addr on d.addressId = addr.addressId inner join country as cou on addr.countryId = cou.countryId ");
            if (deviceXML.getOptionalCountryID().matches("[0-9]+")) {
                queryString.append(" and cou.countryId = " + Integer.valueOf(deviceXML.getOptionalCountryID()) + " ");
            } else {
                queryString.append(" and cou.countryName like '%" + deviceXML.getOptionalCountryID() + "%' ");
            }
        }

        if (deviceXML.getObjectGroupName() != null) {
            queryString.append(" inner join Ogmdevice as ogm on d.deviceId = ogm.deviceId inner join ObjectGroups as og on ogm.objectGroupId = og.objectGroupId " +
                    " inner join ObjectGroupTranslations as ogt on og.objectGroupId = ogt.objectGroupId and ogt.countryId = " + deviceXML.getCountryID().getValue() + " ");
            if (TCMUtil.isValidUUID(deviceXML.getObjectGroupName())) {
                queryString.append("and ogt.objectGroupId = '" + deviceXML.getObjectGroupName() + "' ");
            } else {
                queryString.append("and ogt.objectGroupName like '%" + deviceXML.getObjectGroupName() + "%' ");
            }
        }

        if (deviceXML.getDeviceName() != null) {
            if (TCMUtil.isValidUUID(deviceXML.getDeviceName())) {
                queryString.append(" and d.deviceId = '" + deviceXML.getDeviceName() + "' ");
            } else {
                queryString.append(" and (d.deviceName like '%" + deviceXML.getDeviceName() + "%' or d.deviceName2 like '%" + deviceXML.getDeviceName() + "%') ");
            }
        }
        if (deviceXML.getAssetID() != null) {
            queryString.append(" and d.assetId = '" + deviceXML.getAssetID() + "' ");
        }

        if (deviceXML.getDeviceTypeID() != null) {
            queryString.append(" inner join deviceTypes as dt on d.deviceTypeId = dt.deviceTypeId ");
            if (TCMUtil.isValidUUID(deviceXML.getDeviceTypeID().getValue())) {
                queryString.append(" and dt.deviceTypeId = '" + deviceXML.getDeviceTypeID().getValue() + "' ");
            } else {
                queryString.append(" and dt.deviceTypeName like '%" + deviceXML.getDeviceTypeID().getValue() + "%' ");
            }
        }
        if (objectTags != null) {
            queryString.append(" and d.deviceId in (Select ObjectID from ISMOperations.dbo.ObjectUsageRecords where ObjectID in (select ObjectID from ObjectTags where SearchTags like '%" + objectTags + "%'))");
        }

        queryString.append(" order by deviceName asc ");
        SQLQuery query = session.createSQLQuery(queryString.toString());
        TCMUtil.loger(getClass().getName(), queryString.toString(), "Info");
        query.addScalar("deviceId", Hibernate.STRING);
        query.addScalar("deviceName", Hibernate.STRING);
        query.addScalar("deviceName2", Hibernate.STRING);
        query.addScalar("groupId", Hibernate.STRING);
        query.addScalar("deviceTypeId", Hibernate.STRING);
        query.addScalar("assetId", Hibernate.STRING);

        deviceList = query.list();

        return deviceList;
    }

    // Device CRUD Methods.
    public Device getDeviceById(String deviceId, Session session) {
        return (Device) session.getNamedQuery("getDeviceById").setString("deviceId", deviceId).uniqueResult();
    }

    public Object getDeviceByDeviceId(String deviceId, Session session) {
        return session.getNamedQuery("getDeviceById").setString("deviceId", deviceId).uniqueResult();
    }

    public List<Device> getDeviceByDeviceTypeId(String deviceTypeId, Session session) {
        return session.getNamedQuery("getDeviceByDeviceTypeId").setString("deviceTypeId", deviceTypeId).list();
    }

    public List<Device> getDeviceByAssetId(String assetId, Session session) {
        return session.getNamedQuery("getDeviceByAssetId").setString("assetId", assetId).list();
    }

    public Device getDeviceByDeviceTypeIdNDeviceName2(String deviceTypeId, String deviceName2, Session session) {
        Device device = null;
        try {
            device = (Device) session.getNamedQuery("getDeviceByDeviceTypeIdNDeviceName2").setString("deviceTypeId", deviceTypeId).setString("deviceName2", deviceName2).list().get(0);
            return device;
        } catch (IndexOutOfBoundsException ae) {
            return null;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public Device getDeviceByGroupIDNDeviceName(String groupId, String deviceName, Session session) {
        Device device = null;
        try {
            device = (Device) session.getNamedQuery("getDeviceByGroupIDNDeviceName").setString("groupId", groupId).setString("deviceName", deviceName).list().get(0);
            return device;
        } catch (IndexOutOfBoundsException ae) {
            return null;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public List<Device> getAllDevices(Session session) {
        try {
            return session.getNamedQuery("getAllDevices").list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public List<String> getDeviceDataItemIdsList(List<DeviceStatusDataItems> deviceList) {
        List<String> deviceDataItemIDList = new ArrayList<String>();
        for (DeviceStatusDataItems dsdi : deviceList) {
            deviceDataItemIDList.add(dsdi.getId().getDeviceDataItemId());
        }
        return deviceDataItemIDList;
    }

    public Set<String> getDeviceDataItemDeviceIdsList(List<DeviceStatusDataItems> deviceList) {
        Set<String> DeviceDataItemDeviceIdsList = new HashSet<String>();
        for (DeviceStatusDataItems dsdi : deviceList) {
            DeviceDataItemDeviceIdsList.add(dsdi.getDeviceId());
        }
        return DeviceDataItemDeviceIdsList;
    }

    public List<Device> getDeviceDetailsByUserId(String userId, Session session) {
        List<Users2> users2List = session.getNamedQuery("getUsers2ById").setString("userID", userId).list();
        List<Device> deviceList = new ArrayList<Device>();
        for (Users2 u : users2List) {
            if (u.getGroupId() != null) {
                List<GroupTrusts> gtList = session.getNamedQuery("getGroupTrustsByGroupId").setString("groupId", u.getGroupId()).list();
                ArrayList<String> groupIdList = new ArrayList();
                groupIdList.add(u.getGroupId());
                for (GroupTrusts gt : gtList) {
                    groupIdList.add(gt.getId().getItrustGroupId());
                }
                List<List<String>> splitList = TCMUtil.splitStringList(groupIdList, 2000);
                for (List<String> list : splitList) {
                    List<Device> deviceListTemp = session.getNamedQuery("getDeviceDetailsByGroupId").setParameterList("groupId", list).list();
                    deviceList.addAll(deviceListTemp);
                }

            }
        }
        return deviceList;
    }

    public List<DeviceTypeChannels> getDeviceTypeChannels(String deviceTypeId, Session session) {
        return session.getNamedQuery("getDeviceTypeChannels").setString("deviceTypeId", deviceTypeId).list();
    }

    public List<DeviceTypeChannels> getDeviceTypeChannelsBydeviceTypeId(String deviceTypeId, Session session) {
        return session.getNamedQuery("getDeviceTypeChannelsBydeviceTypeId").setString("deviceTypeId", deviceTypeId).list();
    }

    public List<ObjectUsageSummaryReport> getObjectUsageErrorReport(se.info24.devicejaxbPost.Device deviceXML, String groupingBy, Session session) {
        List<ObjectUsageSummaryReport> deviceList = new ArrayList<ObjectUsageSummaryReport>();
        StringBuffer queryString = new StringBuffer();
        ObjectUsageRecords objectUsageRecordsjaxb = deviceXML.getObjectUsageRecords().get(0);
        if (groupingBy.equalsIgnoreCase("Country")) {
            queryString.append("SELECT cou.countryName as groupedBy,");
        } else if (groupingBy.equalsIgnoreCase("Organization")) {
            queryString.append("SELECT gts.groupName as groupedBy,");
        } else if (groupingBy.equalsIgnoreCase("Device")) {
            queryString.append("SELECT d.deviceName as groupedBy,");
        } else if (groupingBy.equalsIgnoreCase("UserKey")) {
            queryString.append("SELECT case when ua.firstName is null then '' else ua.firstName end as firstName, case when ua.lastName is null then '' else ua.lastName end as lastName,");
        }

        queryString.append(" count(our.usagerecordid) as usageSessions, " +
                //                "count(our.usedbyuseralias) as userss, "+
                "sum(case when datediff(ss, our.usagestarttime, our.usagestoptime) > 121 and convert(Decimal(16,2), our.usagevolume) > 0.1 then 1 else 0 end) as 'successfulSessions' " +
                "from ismoperations.dbo.objectusagerecords as our inner join device as d on our.objectId = d.deviceId inner join Groups as g on d.groupId " +
                "= g.groupId and g.groupid in (" + TCMUtil.getGroupTrust(deviceXML.getGroupID().getValue()) + ") " +
                "inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.countryId = " + deviceXML.getCountryID().getValue() + " " +
                "inner join Addresses as addr on d.addressId = addr.addressId inner join country as cou on addr.countryId = cou.countryId " +
                "inner join userAlias as ua on our.usedByUserAlias = ua.userAlias ");
        if (objectUsageRecordsjaxb.getUsageTypeID() != null) {
            queryString.append(" and our.usageTypeId = '" + objectUsageRecordsjaxb.getUsageTypeID() + "'");
        }

        if (objectUsageRecordsjaxb.getUsageStartTimeTCMV3() != null && objectUsageRecordsjaxb.getUsageStopTimeTCMV3() != null) {
            queryString.append("and our.usageStartTime between '" + objectUsageRecordsjaxb.getUsageStartTimeTCMV3() + "' and '" + objectUsageRecordsjaxb.getUsageStopTimeTCMV3() + "' ");
        } else if (objectUsageRecordsjaxb.getUsageStartTimeTCMV3() != null) {
            queryString.append("and our.usageStartTime between '" + objectUsageRecordsjaxb.getUsageStartTimeTCMV3() + "' and getutcdate() ");
        } else if (objectUsageRecordsjaxb.getUsageStopTimeTCMV3() != null) {
            queryString.append("and our.usageStopTime between getutcdate() and '" + objectUsageRecordsjaxb.getUsageStopTimeTCMV3() + "' ");
        }
        if (deviceXML.getGroupID().getGroupName() != null) {
            if (TCMUtil.isValidUUID(deviceXML.getGroupID().getGroupName())) {
                queryString.append("and gts.groupId = '" + deviceXML.getGroupID().getGroupName() + "' ");
            } else {
                queryString.append("and gts.groupName like '%" + deviceXML.getGroupID().getGroupName() + "%' ");
            }
        }

        if (deviceXML.getOptionalCountryID() != null) {
            if (deviceXML.getOptionalCountryID().matches("[0-9]+")) {
                queryString.append(" and cou.countryId = " + Integer.valueOf(deviceXML.getOptionalCountryID()) + " ");
            } else {
                queryString.append(" and cou.countryName like '%" + deviceXML.getOptionalCountryID() + "%' ");
            }
        }

        if (deviceXML.getObjectGroupName() != null) {
            queryString.append(" inner join Ogmdevice as ogm on d.deviceId = ogm.deviceId inner join ObjectGroups as og on ogm.objectGroupId = og.objectGroupId " +
                    " inner join ObjectGroupTranslations as ogt on og.objectGroupId = ogt.objectGroupId and ogt.countryId = " + deviceXML.getCountryID().getValue() + " ");
            if (TCMUtil.isValidUUID(deviceXML.getObjectGroupName())) {
                queryString.append("and ogt.objectGroupId = '" + deviceXML.getObjectGroupName() + "' ");
            } else {
                queryString.append("and ogt.objectGroupName like '%" + deviceXML.getObjectGroupName() + "%' ");
            }
        }

        if (deviceXML.getDeviceName() != null) {
            if (TCMUtil.isValidUUID(deviceXML.getDeviceName())) {
                queryString.append(" and d.deviceId = '" + deviceXML.getDeviceName() + "' ");
            } else {
                queryString.append(" and (d.deviceName like '%" + deviceXML.getDeviceName() + "%' or d.deviceName2 like '%" + deviceXML.getDeviceName() + "%') ");
            }
        }
        if (deviceXML.getAssetID() != null) {
            queryString.append(" and d.assetId = '" + deviceXML.getAssetID() + "' ");
        }

        if (objectUsageRecordsjaxb.getUsedByUserAlias() != null) {
            queryString.append(" and our.usedByUserAlias = '" + objectUsageRecordsjaxb.getUsedByUserAlias() + "' ");
        }

        if (deviceXML.getSearchTags() != null) {
            queryString.append("inner join ObjectTags as ot on ot.objectId = our.objectId and ot.searchTags like '%" + deviceXML.getSearchTags() + "%' ");
        }

        if (groupingBy.equalsIgnoreCase("Country")) {
            queryString.append("group by cou.countryName ");
        } else if (groupingBy.equalsIgnoreCase("Organization")) {
            queryString.append("group by gts.groupName ");
        } else if (groupingBy.equalsIgnoreCase("Device")) {
            queryString.append("group by d.deviceName ");
        } else if (groupingBy.equalsIgnoreCase("UserKey")) {
            queryString.append("group by ua.firstName, ua.lastName ");
        }
        queryString.append(" order by max(our.createdDate) desc");
        SQLQuery query = session.createSQLQuery(queryString.toString());

        if (groupingBy.equalsIgnoreCase("UserKey")) {
            query.addScalar("firstName", Hibernate.STRING);
            query.addScalar("lastName", Hibernate.STRING);
        } else {
            query.addScalar("groupedBy", Hibernate.STRING);
        }
        query.addScalar("usageSessions", Hibernate.STRING);
        query.addScalar("successfulSessions", Hibernate.STRING);
        query.setResultTransformer(Transformers.aliasToBean(ObjectUsageSummaryReport.class));
        deviceList = query.setMaxResults(2000).list();
        return deviceList;
    }

    public List<ObjectUsageSummaryReport> getObjectUsageSummaryReport(se.info24.devicejaxbPost.Device deviceXML, String groupingBy, Session session) {
        List<ObjectUsageSummaryReport> deviceList = new ArrayList<ObjectUsageSummaryReport>();
        StringBuffer queryString = new StringBuffer();
        ObjectUsageRecords objectUsageRecordsjaxb = deviceXML.getObjectUsageRecords().get(0);
        if (groupingBy.equalsIgnoreCase("Country")) {
            queryString.append("SELECT cou.countryName as groupedBy,");
        } else if (groupingBy.equalsIgnoreCase("Organization")) {
            queryString.append("SELECT gts.groupName as groupedBy,");
        } else if (groupingBy.equalsIgnoreCase("Device")) {
            queryString.append("SELECT d.deviceName as groupedBy,");
        } else if (groupingBy.equalsIgnoreCase("UserKey")) {
            queryString.append("SELECT case when ua.firstName is null then '' else ua.firstName end as firstName, case when ua.lastName is null then '' else ua.lastName end as lastName,");
        }

        queryString.append(" count(distinct our.usedbyuseralias) as uniqueUsers, count(our.usagerecordid) as usageSessions, sum(DATEDIFF(mi, our.usagestarttime, our.usagestoptime)) as totalUsageTime, " +
                "avg(DATEDIFF(mi, our.usagestarttime, our.usagestoptime)) as averageUsageTime, sum(convert(Decimal(16,2), our.usagevolume)) as volume " +
                "from ismoperations.dbo.objectusagerecords as our inner join device as d on our.objectId = d.deviceId inner join Groups as g on d.groupId " +
                "= g.groupId and g.groupid in (" + TCMUtil.getGroupTrust(deviceXML.getGroupID().getValue()) + ") " +
                "inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.countryId = " + deviceXML.getCountryID().getValue() + " " +
                "inner join Addresses as addr on d.addressId = addr.addressId inner join country as cou on addr.countryId = cou.countryId " +
                "inner join userAlias as ua on our.usedByUserAlias = ua.userAlias " +
                "and our.usageTypeId = '" + objectUsageRecordsjaxb.getUsageTypeID() + "' ");

        if (objectUsageRecordsjaxb.getUsageStartTimeTCMV3() != null && objectUsageRecordsjaxb.getUsageStopTimeTCMV3() != null) {
            queryString.append("and our.usageStartTime between '" + objectUsageRecordsjaxb.getUsageStartTimeTCMV3() + "' and '" + objectUsageRecordsjaxb.getUsageStopTimeTCMV3() + "' ");
        } else if (objectUsageRecordsjaxb.getUsageStartTimeTCMV3() != null) {
            queryString.append("and our.usageStartTime between '" + objectUsageRecordsjaxb.getUsageStartTimeTCMV3() + "' and getutcdate() ");
        } else if (objectUsageRecordsjaxb.getUsageStopTimeTCMV3() != null) {
            queryString.append("and our.usageStopTime between getutcdate() and '" + objectUsageRecordsjaxb.getUsageStopTimeTCMV3() + "' ");
        }
        if (deviceXML.getGroupID().getGroupName() != null) {
            if (TCMUtil.isValidUUID(deviceXML.getGroupID().getGroupName())) {
                queryString.append("and gts.groupId = '" + deviceXML.getGroupID().getGroupName() + "' ");
            } else {
                queryString.append("and gts.groupName like '%" + deviceXML.getGroupID().getGroupName() + "%' ");
            }
        }

        if (deviceXML.getOptionalCountryID() != null) {
            if (deviceXML.getOptionalCountryID().matches("[0-9]+")) {
                queryString.append(" and cou.countryId = " + Integer.valueOf(deviceXML.getOptionalCountryID()) + " ");
            } else {
                queryString.append(" and cou.countryName like '%" + deviceXML.getOptionalCountryID() + "%' ");
            }
        }

        if (deviceXML.getObjectGroupName() != null) {
            queryString.append(" inner join Ogmdevice as ogm on d.deviceId = ogm.deviceId inner join ObjectGroups as og on ogm.objectGroupId = og.objectGroupId " +
                    " inner join ObjectGroupTranslations as ogt on og.objectGroupId = ogt.objectGroupId and ogt.countryId = " + deviceXML.getCountryID().getValue() + " ");
            if (TCMUtil.isValidUUID(deviceXML.getObjectGroupName())) {
                queryString.append("and ogt.objectGroupId = '" + deviceXML.getObjectGroupName() + "' ");
            } else {
                queryString.append("and ogt.objectGroupName like '%" + deviceXML.getObjectGroupName() + "%' ");
            }
        }

        if (deviceXML.getDeviceName() != null) {
            if (TCMUtil.isValidUUID(deviceXML.getDeviceName())) {
                queryString.append(" and d.deviceId = '" + deviceXML.getDeviceName() + "' ");
            } else {
                queryString.append(" and (d.deviceName like '%" + deviceXML.getDeviceName() + "%' or d.deviceName2 like '%" + deviceXML.getDeviceName() + "%') ");
            }
        }
        if (deviceXML.getAssetID() != null) {
            queryString.append(" and d.assetId = '" + deviceXML.getAssetID() + "' ");
        }

        if (objectUsageRecordsjaxb.getUsedByUserAlias() != null) {
            queryString.append(" and our.usedByUserAlias = '" + objectUsageRecordsjaxb.getUsedByUserAlias() + "' ");
        }

        if (deviceXML.getSearchTags() != null) {
            queryString.append("inner join ObjectTags as ot on ot.objectId = our.objectId and ot.searchTags like '%" + deviceXML.getSearchTags() + "%' ");
        }

        if (groupingBy.equalsIgnoreCase("Country")) {
            queryString.append("group by cou.countryName ");
        } else if (groupingBy.equalsIgnoreCase("Organization")) {
            queryString.append("group by gts.groupName ");
        } else if (groupingBy.equalsIgnoreCase("Device")) {
            queryString.append("group by d.deviceName ");
        } else if (groupingBy.equalsIgnoreCase("UserKey")) {
            queryString.append("group by ua.firstName, ua.lastName ");
        }
        queryString.append(" order by max(our.createdDate) desc");
        SQLQuery query = session.createSQLQuery(queryString.toString());

        if (groupingBy.equalsIgnoreCase("UserKey")) {
            query.addScalar("firstName", Hibernate.STRING);
            query.addScalar("lastName", Hibernate.STRING);
        } else {
            query.addScalar("groupedBy", Hibernate.STRING);
        }
        query.addScalar("uniqueUsers", Hibernate.STRING);
        query.addScalar("usageSessions", Hibernate.STRING);
        query.addScalar("totalUsageTime", Hibernate.STRING);
        query.addScalar("averageUsageTime", Hibernate.STRING);
        query.addScalar("volume", Hibernate.STRING);
        query.setResultTransformer(Transformers.aliasToBean(ObjectUsageSummaryReport.class));
        deviceList = query.setMaxResults(2000).list();
        return deviceList;
    }

    public boolean saveDevice(Device device, Session session) {
        Transaction tx = null;
        boolean result = false;
        try {
            if (device != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(device);
                tx.commit();
                session.flush();
                session.clear();
                result = true;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx != null) {
                tx.rollback();
            }
            result = false;
        }
        return result;
    }

    // DeviceTypes CRUD methods.
    public boolean saveDeviceTypes(DeviceTypes deviceTypes, Session session) {
        Transaction tx = null;
        try {
            if (deviceTypes != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(deviceTypes);
                tx.commit();
                return true;
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return false;
    }

    public List<DeviceTypesInGroups> getDeviceTypesInGroupsByGroupIds(Set<String> groupIdsList, Session session) {
        return session.getNamedQuery("getDeviceTypesInGroupsByGroupIds").setParameterList("groupId", groupIdsList).list();
    }

    public List<DeviceTypes> getDeviceTypes(Set<String> deviceTypeIds, Session session) {
        return session.getNamedQuery("getDeviceTypesByIdsList").setParameterList("deviceTypeId", deviceTypeIds).setMaxResults(200).list();
    }

    public List<DeviceTypes> getAllDeviceTypes(Set<String> deviceTypeIds, Session session) {
        return session.getNamedQuery("getDeviceTypesByIdsList").setParameterList("deviceTypeId", deviceTypeIds).list();
    }

    public Set<String> getDeviceTypeIds(List<DeviceTypesInGroups> dtigList) {
        Set<String> deviceTypeIds = new HashSet<String>();
        for (DeviceTypesInGroups dtig : dtigList) {
            deviceTypeIds.add(dtig.getId().getDeviceTypeId());
        }
        return deviceTypeIds;
    }

    public Object getDeviceTypesById(String deviceTypeId, Session session) {
        try {
            return session.getNamedQuery("getDeviceTypesById").setString("deviceTypeId", deviceTypeId).uniqueResult();
        } catch (IndexOutOfBoundsException ae) {
            return null;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public boolean removeDeviceType(DeviceTypes deviceTypes, Session session) {
        Transaction tx = null;
        try {
            if (deviceTypes != null) {
                tx = session.beginTransaction();
                session.delete(deviceTypes);
                tx.commit();
                return true;
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return false;
    }

    public List<DeviceTypes> getAllDeviceTypes(Session session) {
        return session.getNamedQuery("getAllDeviceTypes").list();
    }

    // DeviceManfacturers CRUD Methods
    public boolean saveDeviceManfacturer(DeviceManufacturers deviceManufacturers, Session session) {
        Transaction tx = null;
        try {
            if (deviceManufacturers != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(deviceManufacturers);
                tx.commit();
                return true;
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return false;
    }

    public DeviceManufacturers getDeviceManufacturersById(String deviceManufacturerID, Session session) {
        try {
            return (DeviceManufacturers) session.getNamedQuery("getDeviceManufacturersById").setString("deviceManufacturerID", deviceManufacturerID).list().get(0);
        } catch (IndexOutOfBoundsException ae) {
            return null;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public List<DeviceManufacturers> getDeviceManufacturersByIds(List<String> deviceManufacturerID, int maxResult, Session session) {
        try {
            return session.getNamedQuery("getDeviceManufacturersByIds").setParameterList("deviceManufacturerID", deviceManufacturerID).setMaxResults(maxResult).list();
        } catch (IndexOutOfBoundsException ae) {
            return null;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public List<DeviceManufacturers> getAllDeviceManufacturers(Session session, int maxresult) {
        try {
            if (maxresult == 0) {
                return session.getNamedQuery("getAllDeviceManufacturers").list();
            } else {
                return session.getNamedQuery("getAllDeviceManufacturers").setMaxResults(maxresult).list();
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public boolean removeDeviceManufacturers(DeviceManufacturers deviceManufacturers, Session session) {
        Transaction tx = null;
        try {
            if (deviceManufacturers != null) {
                tx = session.beginTransaction();
                session.delete(deviceManufacturers);
                tx.commit();
                return true;
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return false;
    }

    public DeviceStatus getDeviceStatusByDeviceId(String deviceId, Session session) {
        try {
            return (DeviceStatus) session.getNamedQuery("getDeviceStatusByDeviceId").setString("deviceId", deviceId).uniqueResult();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public List<DeviceStatus> getDeviceStatusByGroupId(String groupId, Session session) {
        try {
            return session.getNamedQuery("getDeviceStatusByGroupId").setString("groupId", groupId).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public DeviceStatus getDeviceStatus(String deviceId, String groupId, Session session) {
        try {
            return (DeviceStatus) session.getNamedQuery("getDeviceStatus").setString("deviceId", deviceId).setString("groupId", groupId).uniqueResult();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public List<DeviceDataitemTranslations> getAllDeviceDataitemTranslations(int countryId, Session session) {
        try {
            return session.getNamedQuery("getAllDeviceDataitemTranslationsById").setInteger("countryId", countryId).setMaxResults(1000).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public List<DeviceStatusDataItems> getDeviceStatusDataItemsByDeviceID(String deviceId, Session session) {
        try {
            return session.getNamedQuery("getDeviceStatusDataItemsByDeviceID").setString("deviceId", deviceId).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public List<DeviceStatusDataItems> getDeviceStatusDataItemsByDeviceIDs(Set<String> deviceId, Session session) {
        try {
            return session.getNamedQuery("getDeviceStatusDataItemsByDeviceIDs").setParameterList("deviceId", deviceId).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public List<DeviceSettingsPackages> getDeviceSettingsPackagesByDeviceTypeId(String deviceTypeId, Session session) {
        try {
            return session.getNamedQuery("getDeviceSettingsPackagesByDeviceTypeId").setString("deviceTypeId", deviceTypeId).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public boolean addNewDeviceAttribute(DeviceDataItems ddi, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(ddi);
            tx.commit();
            return true;

        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    public boolean deleteDeviceAttribute(String id, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            DeviceDataItems ddi = (DeviceDataItems) session.getNamedQuery("getDeviceDataItemsById").setString("id", id).list().get(0);
            session.delete(ddi);
            tx.commit();
            return true;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    public boolean updateDeviceAttribute(String id, String dataFieldName, String dataGroupName, String unit, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            GregorianCalendar gc = new GregorianCalendar();
            DeviceDataItems ddi = (DeviceDataItems) session.getNamedQuery("getDeviceDataItemsById").setString("id", id).list().get(0);
            ddi.setDeviceDataFieldName(dataFieldName);
            ddi.setDeviceDataGroupName(dataGroupName);
            if (unit != null) {
                ddi.setUnit(unit);
            }
            ddi.setUpdatedDate(gc.getTime());
            session.saveOrUpdate(ddi);
            tx.commit();
            return true;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }


    //CRUD Methods for DeviceTypeDataItems.
    public boolean saveDeviceTypeDataItems(DeviceTypeDataitems deviceTypeDataitems, Session session) {
        Transaction tx = null;
        try {
            if (deviceTypeDataitems != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(deviceTypeDataitems);
                tx.commit();
                return true;
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return false;
    }

    public DeviceTypeDataitems getDeviceTypeDataItems(String deviceTypeId, String deviceDataItemId, Session session) {
        try {
            return (DeviceTypeDataitems) session.getNamedQuery("getDeviceTypeDataItemsById").setString("deviceTypeId", deviceTypeId).setString("deviceDataItemId", deviceDataItemId).list().get(0);
        } catch (IndexOutOfBoundsException ae) {
            return null;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public boolean removeDeviceTypeDataitems(DeviceTypeDataitems deviceTypeDataitems, Session session) {
        Transaction tx = null;
        try {
            if (deviceTypeDataitems != null) {
                tx = session.beginTransaction();
                session.delete(deviceTypeDataitems);
                tx.commit();
                return true;
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return false;
    }

    public List<DeviceTypeDataitems> getDeviceTypeDataItemsByDeviceTypeID(String deviceTypeId, Session session) {
        try {
            return session.getNamedQuery("getDeviceTypeDataItemsByDeviceTypeID").setString("deviceTypeId", deviceTypeId).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    // CRUD Methods for DeviceMessages table.
    public DeviceMessages getDeviceMessagesById(String deviceMessageID, Session session) {
        try {
            return (DeviceMessages) session.getNamedQuery("getDeviceMessagesById").setString("deviceMessageID", deviceMessageID).list().get(0);
        } catch (IndexOutOfBoundsException ae) {
            return null;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public boolean removeDeviceMessages(DeviceMessages deviceMessages, Session session) {
        Transaction tx = null;
        try {
            if (deviceMessages != null) {
                tx = session.beginTransaction();
                session.delete(deviceMessages);
                tx.commit();
                return true;
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return false;
    }

    public List<DeviceMessages> getAllDeviceMessages(Session session) {
        try {
            return session.getNamedQuery("getAllDeviceMessages").list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public List<DeviceMessages> getAllDeviceMessagesByQry(String qry, Session session) {
        try {
            return session.createQuery(qry).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public boolean saveDeviceMessages(DeviceMessages deviceMessages, Session session) {
        Transaction tx = null;
        try {
            if (deviceMessages != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(deviceMessages);
                tx.commit();
                return true;
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return false;
    }

    // CRUD Methods For DeviceHistory.
    public List<DeviceHistory> getAllDeviceHistoryByQry(String qry, Session session) {
        try {
            return session.createQuery(qry).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public List<DeviceHistoryDataItems> getDeviceHistoryDataItemsById(String deviceID, Session session) {
        try {
            return session.getNamedQuery("getDeviceHistoryDataItemsById").setString("deviceID", deviceID).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    //CRUD Methods for DeviceTypesInGroups.
    public List<DeviceTypesInGroups> getDeviceTypesInGroupsByGroupId(String groupId, Session session) {
        try {
            return session.getNamedQuery("getDeviceTypesInGroupsByGroupId").setString("groupId", groupId).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public boolean addFavoriteDevices(String userId, List<se.info24.devicejaxbPost.Device> devices, Session session) {
        UserFavoriteDevices userFav = null;
        UserFavoriteDevicesId userFavDev = null;
        GregorianCalendar gc = new GregorianCalendar();
        Transaction tx = null;
        try {
            for (se.info24.devicejaxbPost.Device data : devices) {
                userFav = new UserFavoriteDevices();
                userFavDev = new UserFavoriteDevicesId();
                userFavDev.setDeviceId(data.getDeviceID());
                userFavDev.setUserId(userId);
                userFav.setCreatedDate(gc.getTime());
                userFav.setUpdatedDate(gc.getTime());
                userFav.setId(userFavDev);
                tx = session.beginTransaction();
                session.save(userFav);
                tx.commit();
            }
            return true;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    public boolean deleteFavoriteDevicesByDeviceId(String userId, String deviceId, Session session) {
        Transaction tx = null;
        try {
            Query query = session.getNamedQuery("deleteFavoriteDevicesByDeviceId").setString("userid", userId).setString("deviceid", deviceId);
            query.executeUpdate();
            tx = session.beginTransaction();
            tx.commit();
            return true;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    public boolean deleteFavoriteDevicesByUserId(String userId, Session session) {
        Transaction tx = null;
        try {
            Query query = session.getNamedQuery("deleteFavoriteDevicesByUserId").setString("userid", userId);
            query.executeUpdate();
            tx = session.beginTransaction();
            tx.commit();
            return true;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    public List<UserFavoriteDevices> getUserFavoriteDevices(String userId, Session session) {
        List<UserFavoriteDevices> favDevices = session.getNamedQuery("getUserFavoriteDevicesById").setString("userId", userId).list();
        return favDevices;
    }

    public List<Device> getDeviceDetails(int countryId, Set<String> groupIdList, String searchField, int maxResult, Session session) {
        List<Device> device = null;
        if (TCMUtil.isValidUUID(searchField)) {
            Device dev = getDeviceById(searchField, session);
            device.add(dev);
        } else {
            if (maxResult == 0) {
                device = session.getNamedQuery("getDeviceBySearchdeviceName").setParameterList("groupId", groupIdList).setString("searchField", searchField).list();
            } else {
                device = session.getNamedQuery("getDeviceBySearchdeviceName").setParameterList("groupId", groupIdList).setString("searchField", searchField).setMaxResults(maxResult).list();
            }
        }
        return device;
    }

    public List<Device> getDeviceConnectionStatus(String groupId, String deviceId, Session session) {
        GroupDAO groupDAO = new GroupDAO();
        List<Device> deviceList = new ArrayList<Device>();
        String dev = null;
        if (deviceId != null && TCMUtil.isValidUUID(deviceId)) {
            dev = deviceId;
        }
        List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
        ArrayList<String> groupIdList = new ArrayList<String>();
        groupIdList.add(groupId);
        for (GroupTrusts gtr : groupTrustsList) {
            groupIdList.add(gtr.getId().getGroupId());
        }
        List<List<String>> splitList = TCMUtil.splitStringList(groupIdList, 2000);
        for (List<String> list : splitList) {
            if (groupId != null && deviceId != null) {
                List<Device> deviceListtemp = session.getNamedQuery("getDeviceDetails").setParameterList("groupId", list).setString("deviceId", dev).setString("searchField", "%" + deviceId + "%").list();
                deviceList.addAll(deviceListtemp);
            }
            if (groupId != null && deviceId == null) {
                List<Device> deviceListtemp = session.getNamedQuery("getDeviceDetailsByGroupId").setParameterList("groupId", list).list();
                deviceList.addAll(deviceListtemp);
            }
        }


        if (groupId == null && deviceId != null) {
            deviceList = getDeviceByDeviceIdAndDeviceNames(deviceId, session);
        }

        return deviceList;
    }

    public List<Device> getDeviceByDeviceIdAndDeviceNames(String deviceId, Session session) {
        String dev = null;
        if (deviceId != null && TCMUtil.isValidUUID(deviceId)) {
            dev = deviceId;
        }
        return session.getNamedQuery("getDeviceDetailsBySearchField").setString("deviceId", dev).setString("searchField", "%" + deviceId + "%").list();
    }

    public boolean updateDeviceActivationSchedules(String deviceDataItemId, String deviceId, Session session, Session ismsession) {
        Transaction tx = null;
        List<DeviceDataItems> ddiList = ismsession.getNamedQuery("getDeviceDataItemsByItemId").setString("id", deviceDataItemId).list();
        DeviceStatusDataItems dsdi = (DeviceStatusDataItems) getDeviceStatusDataItemsBydeviceDataItemId(session, deviceId, deviceDataItemId);

        try {
            if (!ddiList.isEmpty()) {
                for (DeviceDataItems ddi : ddiList) {
                    List<DeviceActivationSchedules> dasList = session.getNamedQuery("getDeviceActivationSchedules").setString("dataItem", ddi.getDeviceDataFieldName()).setString("deviceId", deviceId).list();
                    if (!dasList.isEmpty()) {
                        for (DeviceActivationSchedules das : dasList) {
                            das.setDataItemValue("0");
                            GregorianCalendar gc = new GregorianCalendar();
                            das.setStopTime(gc.getTime());
                            das.setUpdatedDate(gc.getTime());
                            tx = session.beginTransaction();
                            session.saveOrUpdate(das);

                            if (dsdi != null) {
                                dsdi.setDeviceDataValue("0");
                                session.saveOrUpdate(dsdi);
                            }
                            tx.commit();
                        }
                    }
                }
            }
            return true;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
        }
        return false;
    }

    public boolean addDeviceActivationSchedules(DeviceActivationSchedules das, Session session, String deviceDataItemId, String deviceId) {
        DeviceStatusDataItems dsdi = (DeviceStatusDataItems) getDeviceStatusDataItemsBydeviceDataItemId(session, deviceId, deviceDataItemId);
        Transaction tx = null;
        try {
            if (das != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(das);
                if (dsdi != null) {
                    dsdi.setDeviceDataValue("1");
                    session.saveOrUpdate(dsdi);
                }
                tx.commit();
                return true;
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return false;
    }

    public boolean deleteDevicePending(Session session, String deviceId, String userId) {
        boolean result = false;
        try {
            DevicePendingDelete devicePendingDelete = new DevicePendingDelete();
            devicePendingDelete.setDeviceId(deviceId);
            Calendar cal = Calendar.getInstance();
//            cal.add(Calendar.DATE, 31);
            cal.add(Calendar.DATE, 60);//feature ID :- 773 the tingco API currently set the pending delete date (DeleteDeviceAfterThisDate) to DateTime(now)+ 30 days (In the DevicePendingDelete table). Please change this so the DeleteDeviceAfterThisDate date is set to DateTime(now)+60 days instead.
            devicePendingDelete.setDeleteDeviceAfterThisDate(cal.getTime());
            devicePendingDelete.setLastUpdatedByUserId(userId);
            GregorianCalendar gc = new GregorianCalendar();
            devicePendingDelete.setCreatedDate(gc.getTime());
            devicePendingDelete.setUpdatedDate(gc.getTime());
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(devicePendingDelete);
            tx.commit();
            result = true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            result = false;
        }
        return result;
    }

    boolean addDeviceSetting(ObjectSettingTemplates ost, String deviceId, String settingValue, String deviceSettingParentId, String userId, Session session) {
        boolean result = false;
        Transaction tx = session.beginTransaction();
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        try {
            DeviceSettings ds = getDeviceSettingsByIds(deviceId, ost.getObjectSettingName(), session);
            settingValue = TCMUtil.convertHexToString(settingValue);
            if (ds != null) {
                ds.setDeviceSettingValue(settingValue);
                ds.setActiveFromDate(gc.getTime());
                ds.setUserId(userId);
                ds.setUpdatedDate(gc.getTime());
                ds.setObjectSettingTemplateId(ost.getObjectSettingTemplateId());
                session.saveOrUpdate(ds);
                result = true;
            // saveDeviceSettings(ds, session);
            //updateDeviceSettings(ds, settingValue, userId, session);
            } else {
                ds = new DeviceSettings();
                ds.setDeviceSettingId(UUID.randomUUID().toString());
                if (deviceSettingParentId != null) {
                    DeviceSettings devSettings = new DeviceSettings();
                    devSettings.setDeviceSettingId(deviceSettingParentId);
                    ds.setDeviceSettings(devSettings);
                }
                Device device = new Device();
                device.setDeviceId(deviceId);
                ds.setDevice(device);
                ds.setDeviceSettingName(ost.getObjectSettingName());
                ds.setDeviceSettingValue(settingValue);
                SettingDataType sdt = new SettingDataType();
                sdt.setSettingDataTypeId(ost.getSettingDataType().getSettingDataTypeId());
                ds.setSettingDataType(sdt);
                ds.setActiveFromDate(gc.getTime());
                ds.setUserId(userId);
                ds.setCreatedDate(gc.getTime());
                ds.setUpdatedDate(gc.getTime());
                ds.setObjectSettingTemplateId(ost.getObjectSettingTemplateId());
                session.saveOrUpdate(ds);
                tx.commit();
                tx = session.beginTransaction();
                result = traverseDeviceSettings(deviceId, ost, userId, 1, session);
            }
            if (result) {
                tx.commit();
            }
            return true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    public List<BillingCategories> getBillingCategorieses(Session session, String billingCategoryId) {
        return session.getNamedQuery("getBillingCategories").setString("billingCategoryId", billingCategoryId).list();
    }

    boolean addDeviceWizard(String deviceId, Device device, Session session, se.info24.devicejaxbPost.Device deviceXML, String userId, GregorianCalendar gc) {
        ServiceDAO serviceDAO = new ServiceDAO();

        List<DeviceOperationsMember> domList = getdeviceOperationsMember(session, device.getDeviceId());
        if (!domList.isEmpty()) {
            for (DeviceOperationsMember dom : domList) {
                DeviceOperationsMember deviceOperationMember = new DeviceOperationsMember();
                deviceOperationMember.setDeviceOperationsMemberId(UUID.randomUUID().toString());
                Device d = new Device();
                d.setDeviceId(deviceId);
                deviceOperationMember.setDevice(d);
                deviceOperationMember.setDeviceOperationsStatus(new DeviceOperationsStatus(dom.getDeviceOperationsStatus().getDeviceOperationsStatusId()));
                deviceOperationMember.setActiveFromDate(gc.getTime());
                deviceOperationMember.setUserId(userId);
                deviceOperationMember.setCreatedDate(gc.getTime());
                deviceOperationMember.setUpdatedDate(gc.getTime());
                saveDeviceOperationsMember(deviceOperationMember, session);
            }
        }
        session.flush();
        session.clear();
        List<ObjectSettingPackageMemberships> ospmList = getObjectSettingPackageMemberships(session, device.getDeviceId());
        if (!ospmList.isEmpty()) {
            for (ObjectSettingPackageMemberships ospme : ospmList) {
                ObjectSettingPackageMemberships ospm = new ObjectSettingPackageMemberships();
                ospm.setId(new ObjectSettingPackageMembershipsId(ospme.getId().getObjectSettingPackageId(), deviceId));
                ospm.setLastUpdatedByUserId(userId);
                ospm.setCreatedDate(gc.getTime());
                ospm.setUpdatedDate(gc.getTime());
                saveObjectSettingPackageMemberships(ospm, session);
            }
        }
        session.flush();
        session.clear();
        List<ServiceDeviceSubscriptions> sdsList = serviceDAO.getServiceDeviceSubscriptionsByDeviceId(session, device.getDeviceId());
        String sdsId = null;
        if (!sdsList.isEmpty()) {
            for (ServiceDeviceSubscriptions sdsub : sdsList) {
                ServiceDeviceSubscriptions sds = new ServiceDeviceSubscriptions();
                sdsId = UUID.randomUUID().toString();
                sds.setServiceDeviceSubscriptionId(sdsId);
                Services services = new Services();
                services.setServiceId(sdsub.getServices().getServiceId());
                sds.setServices(services);
                Device dev = new Device();
                dev.setDeviceId(deviceId);
                sds.setDevice(dev);
                if (sdsub.getAgreements() != null) {
                    sds.setAgreements(new Agreements(sdsub.getAgreements().getAgreementId()));
                }
                if (sdsub.getServiceClientLogins() != null) {
                    ServiceClientLogins scl = new ServiceClientLogins();
                    scl.setServiceClientLoginId(sdsub.getServiceClientLogins().getServiceClientLoginId());
                    sds.setServiceClientLogins(scl);
                }
                sds.setSubscriptionEnabled(sdsub.getSubscriptionEnabled());
                if (sdsub.getServiceSubscriptionAclid() != null) {
                    sds.setServiceSubscriptionAclid(sdsub.getServiceSubscriptionAclid());
                }
                sds.setUserId(userId);
                sds.setCreatedDate(gc.getTime());
                sds.setUpdatedDate(gc.getTime());
                saveServiceDeviceSubscriptions(sds, session);
                session.flush();
                session.clear();
                List<ServiceDeviceSettings> serviceDeviceSettingsList = getServiceDeviceSettingsBySubscriptionId(session, sdsub.getServiceDeviceSubscriptionId());
                if (!serviceDeviceSettingsList.isEmpty()) {
                    for (ServiceDeviceSettings sdse : serviceDeviceSettingsList) {
                        ServiceDeviceSettings sdss = new ServiceDeviceSettings();
                        sdss.setServiceDeviceSettingId(UUID.randomUUID().toString());
                        if (sdse.getServiceDeviceSettingParentID() != null) {
                            sdss.setServiceDeviceSettingParentID(sdse.getServiceDeviceSettingParentID());
                        }

                        ServiceDeviceSubscriptions sdsu = new ServiceDeviceSubscriptions();
                        sdsu.setServiceDeviceSubscriptionId(sdsId);
                        sdss.setServiceDeviceSubscriptions(sdsu);
                        sdss.setServiceDeviceSettingName(sdse.getServiceDeviceSettingName());
                        sdss.setServiceDeviceSettingValue(sdse.getServiceDeviceSettingValue());
                        if (sdse.getSettingDataTypeId() != null) {
                            sdss.setSettingDataTypeId(sdse.getSettingDataTypeId());
                        } else {
                            sdss.setSettingDataTypeId(null);
                        }
                        sdss.setActiveFromDate(gc.getTime());
                        sdss.setUserId(userId);
                        sdss.setCreatedDate(gc.getTime());
                        sdss.setUpdatedDate(gc.getTime());
                        if (sdse.getObjectSettingTemplateId() != null) {
                            sdss.setObjectSettingTemplateId(sdse.getObjectSettingTemplateId());
                        }

                        saveServiceDeviceSettings(session, sdss);
                    }
                }
            }
        }

        if (deviceXML.getDeviceSettings() != null) {
            List<DeviceSetting> deviceSettingList = deviceXML.getDeviceSettings().getDeviceSetting();
            for (se.info24.devicejaxbPost.DeviceSetting ds : deviceSettingList) {
                DeviceSettings deviceSettings = getDeviceSettings(deviceId, ds.getDeviceSettingID(), session);
                if (deviceSettings == null) {
                    deviceSettings = new DeviceSettings();
                    deviceSettings.setDeviceSettingId(ds.getDeviceSettingID());
                    if (ds.getDeviceSettingParentID() != null) {
                        DeviceSettings dses = new DeviceSettings();
                        dses.setDeviceSettingId(ds.getDeviceSettingParentID());
                        deviceSettings.setDeviceSettings(dses);
                    }
                    se.info24.pojo.Device de = new se.info24.pojo.Device();
                    de.setDeviceId(deviceId);
                    deviceSettings.setDevice(de);
                    deviceSettings.setDeviceSettingName(ds.getDeviceSettingName());
                    deviceSettings.setDeviceSettingValue(ds.getDeviceSettingValue());
                    if (ds.getObjectSettingTemplates() != null) {
                        if (ds.getObjectSettingTemplates().getObjectSettingTemplateID() != null && !ds.getObjectSettingTemplates().getObjectSettingTemplateID().equalsIgnoreCase("")) {
                            deviceSettings.setObjectSettingTemplateId(ds.getObjectSettingTemplates().getObjectSettingTemplateID());
                        }
                    }
                    if (!ds.getObjectSettingTemplates().getSettingDataType().isEmpty()) {
                        if (!ds.getObjectSettingTemplates().getSettingDataType().get(0).getSettingDataTypeID().equalsIgnoreCase("")) {
                            SettingDataType sdt = new SettingDataType();
                            sdt.setSettingDataTypeId(ds.getObjectSettingTemplates().getSettingDataType().get(0).getSettingDataTypeID());
                            deviceSettings.setSettingDataType(sdt);
                        }
                    }
                    gc.add(GregorianCalendar.SECOND, 1);
                    deviceSettings.setActiveFromDate(gc.getTime());
                    deviceSettings.setUserId(userId);
                    deviceSettings.setCreatedDate(gc.getTime());
                    deviceSettings.setUpdatedDate(gc.getTime());
                    session.saveOrUpdate(deviceSettings);
                    for (DeviceSetting dss : deviceSettingList) {
                        if (ds.getDeviceSettingID().equalsIgnoreCase(dss.getDeviceSettingParentID())) {
                            deviceSettings = new DeviceSettings();
                            deviceSettings.setDeviceSettingId(dss.getDeviceSettingID());
                            if (dss.getDeviceSettingParentID() != null) {
                                DeviceSettings dses = new DeviceSettings();
                                dses.setDeviceSettingId(dss.getDeviceSettingParentID());
                                deviceSettings.setDeviceSettings(dses);
                            }
                            de = new se.info24.pojo.Device();
                            de.setDeviceId(deviceId);
                            deviceSettings.setDevice(de);
                            deviceSettings.setDeviceSettingName(dss.getDeviceSettingName());
                            deviceSettings.setDeviceSettingValue(dss.getDeviceSettingValue());
                            if (dss.getObjectSettingTemplates() != null) {
                                if (ds.getObjectSettingTemplates().getObjectSettingTemplateID() != null && !ds.getObjectSettingTemplates().getObjectSettingTemplateID().equalsIgnoreCase("")) {
                                    deviceSettings.setObjectSettingTemplateId(dss.getObjectSettingTemplates().getObjectSettingTemplateID());
                                }
                            }
                            if (!dss.getObjectSettingTemplates().getSettingDataType().isEmpty()) {
                                if (!dss.getObjectSettingTemplates().getSettingDataType().get(0).getSettingDataTypeID().equalsIgnoreCase("")) {
                                    SettingDataType sdt = new SettingDataType();
                                    sdt.setSettingDataTypeId(dss.getObjectSettingTemplates().getSettingDataType().get(0).getSettingDataTypeID());
                                    deviceSettings.setSettingDataType(sdt);
                                }
                            }
                            deviceSettings.setActiveFromDate(gc.getTime());
                            deviceSettings.setUserId(userId);
                            deviceSettings.setCreatedDate(gc.getTime());
                            deviceSettings.setUpdatedDate(gc.getTime());
                            session.saveOrUpdate(deviceSettings);
                        }
                    }
                }
            }
        }
        return true;
    }

    boolean addNewDevice(Device device, List<se.info24.pojo.Services> servicesList, Session session, se.info24.devicejaxbPost.Device deviceXML, String userId) {
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        String objectSettingPackageId = null;
        Transaction tx = session.beginTransaction();
        ServiceClientLogins serviceClientLogin = null;
        try {
            if (deviceXML.getObjectSettingPackageId() != null) {
                objectSettingPackageId = deviceXML.getObjectSettingPackageId();
            }

            if (deviceXML.getServiceClientLogins() != null) {
                serviceClientLogin = (ServiceClientLogins) getServiceClientLoginsById(deviceXML.getServiceClientLogins().getServiceClientLoginID(), session);
            }

            //Adding DeviceSettings
            if (objectSettingPackageId != null) {
                List<se.info24.pojo.ObjectSettingPackageTemplates> settingPackTempList = getObjectSettingPackageTemplatesByPackageId(objectSettingPackageId, session);
                if (!settingPackTempList.isEmpty()) {
                    for (se.info24.pojo.ObjectSettingPackageTemplates ospt : settingPackTempList) {
                        ObjectSettingTemplates os = getObjectSettingTemplates(ospt.getId().getObjectSettingTemplateId(), session);
                        if (os != null) {
                            String deviceSettingId = UUID.randomUUID().toString();
                            if (insertDeviceSettings(device, userId, os, deviceSettingId, null, session)) {
                                traverseDeviceSetting(device, userId, os, deviceSettingId, session);
                            }
                        }
                    }
                }
            }

            //Insert ObjectSettingPackageMemberships
            if (objectSettingPackageId != null) {
                se.info24.pojo.ObjectSettingPackageMemberships ospm = new ObjectSettingPackageMemberships();
                ospm.setId(new se.info24.pojo.ObjectSettingPackageMembershipsId(objectSettingPackageId, device.getDeviceId()));
                ospm.setLastUpdatedByUserId(userId);
                ospm.setUpdatedDate(gc.getTime());
                ospm.setCreatedDate(gc.getTime());
                session.saveOrUpdate(ospm);
            }

            //Insert ServiceDeviceSubscriptions
            List<se.info24.pojo.ServiceDeviceSubscriptions> sdsList = new ArrayList<ServiceDeviceSubscriptions>();
            if (!servicesList.isEmpty()) {
                for (se.info24.pojo.Services service : servicesList) {
                    se.info24.pojo.ServiceDeviceSubscriptions sds = new ServiceDeviceSubscriptions();
                    sds.setServiceDeviceSubscriptionId(UUID.randomUUID().toString());
                    Services services = new Services();
                    services.setServiceId(service.getServiceId());
                    sds.setServices(services);
                    sds.setDevice(device);
                    sds.setAgreements(device.getAgreements());
                    sds.setServiceClientLogins(serviceClientLogin);
                    sds.setSubscriptionEnabled(1);
                    sds.setUserId(userId);
                    sds.setCreatedDate(gc.getTime());
                    sds.setUpdatedDate(gc.getTime());
                    session.saveOrUpdate(sds);
                    sdsList.add(sds);
                }
            }
            for (se.info24.pojo.ServiceDeviceSubscriptions subService : sdsList) {
                List<ServiceDeviceSettingTemplates> sdst1 = session.getNamedQuery("getServiceDeviceSettingTemplatesbyIds").setString("serviceId", subService.getServices().getServiceId()).setString("DeviceTypeId", device.getDeviceTypes().getDeviceTypeId()).list();
                if (!sdst1.isEmpty()) {
                    for (ServiceDeviceSettingTemplates sdst : sdst1) {
                        ObjectSettingTemplates os = getObjectSettingTemplates(sdst.getId().getObjectSettingTemplateId(), session);
                        if (os != null) {
                            String serviceDeviceSettingId = UUID.randomUUID().toString();
                            String serviceDeviceSettingParentId = null;
                            if (insertServiceDeviceSettings(device, subService, userId, os, serviceDeviceSettingId, serviceDeviceSettingParentId, session)) {
                                traverse(device, subService, userId, os, serviceDeviceSettingId, session);
                            }
                        }
                    }
                }
            }

            //Insert DeviceStatus
            if (deviceXML.getDeviceStatus() != null) {
                saveDeviceStatus(device, deviceXML);
            }

            //Insert DeviceAddresses
            if (deviceXML.getDeviceAddress() != null) {
                DeviceAddress devAddress = deviceXML.getDeviceAddress();
                Addresses address = new Addresses();
                address.setAddressId(UUID.randomUUID().toString());
                AddressType addressType = new AddressType();
                addressType.setAddressTypeId(1); //Hardcoded as per .NET team instruction
                address.setAddressType(addressType);
                address.setAddressStreet(devAddress.getAddressStreet());
                address.setAddressSuite(devAddress.getAddressSuite());
                address.setAddressZip(devAddress.getAddressZip());
                address.setAddressCity(devAddress.getAddressCity());
                address.setCountry(new Country(devAddress.getCountryID().getValue()));
                address.setUserId(userId);
                address.setCreatedDate(gc.getTime());
                address.setUpdatedDate(gc.getTime());
                device.setAddresses(address);
                session.saveOrUpdate(address);
            }

            if (deviceXML.getDeviceOperationsStatusID() != null) {
                DeviceOperationsMember dom = new DeviceOperationsMember();
                dom.setDeviceOperationsMemberId(UUID.randomUUID().toString());
                dom.setDevice(device);
                dom.setDeviceOperationsStatus(new DeviceOperationsStatus(deviceXML.getDeviceOperationsStatusID()));
                dom.setActiveFromDate(gc.getTime());
                dom.setUserId(userId);
                dom.setCreatedDate(gc.getTime());
                dom.setUpdatedDate(gc.getTime());
                session.saveOrUpdate(dom);
            }

            if (device != null) {
                if (!tx.wasCommitted()) {
                    tx.commit();
                }
                return true;
            }

        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return false;
    }

    public boolean deleteDeviceSettingsList(List<DeviceSettings> deviceSettingsList, Session session) {
        Transaction tx = session.beginTransaction();
        try {
            for (DeviceSettings ds : deviceSettingsList) {
                session.delete(ds);
            }
            tx.commit();
            return true;
        } catch (Exception ex) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return false;
        }
    }

    boolean deleteProductVariantDevices(ProductVariantDevices productVariantDevices, Session session) {
        try {
            session.delete(productVariantDevices);
            return true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return false;
        }
    }

    boolean deleteTransactionProducts(List<TransactionProducts> transactionProductsList, Session ismOperationsSession) {
        try {
            for (TransactionProducts tr : transactionProductsList) {
                ismOperationsSession.delete(tr);
            }
            return true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return false;
        }
    }

    boolean deleteTransactionProducts(TransactionProducts transactionProducts, Session ismOperationsSession) {
        try {
            ismOperationsSession.delete(transactionProducts);
            return true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return false;
        }
    }

    boolean deleteTransactionResultAndTransactionProducts(TransactionResult transactionResult, List<TransactionProducts> transProductsList, Session ismOperationsSession) {
        try {
            List<TransactionResult> transactionResultList = getTransactionResultbyParentId(transactionResult.getTransactionId(), ismOperationsSession);
            if (!transactionResultList.isEmpty()) {
                for (TransactionResult tr : transactionResultList) {
                    List<TransactionProducts> transactionProductsList = getTransactionProducts(tr.getTransactionId(), ismOperationsSession);
                    if (!transactionProductsList.isEmpty()) {
                        for (TransactionProducts tp : transactionProductsList) {
                            ismOperationsSession.delete(tp);
                        }
                    }
                    deleteTransactionResultAndTransactionProducts(tr, transProductsList, ismOperationsSession);
                    ismOperationsSession.delete(tr);
                }
            }
            if (!transProductsList.isEmpty()) {
                for (TransactionProducts tp : transProductsList) {
                    ismOperationsSession.delete(tp);
                }
            }
            ismOperationsSession.delete(transactionResult);
            return true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return false;
        }
    }

    List<CommandParseTargets> getCommandParseTargets(String commandId, String deviceId, Session session) {
        return session.getNamedQuery("getCommandParseTargetsByCommandIdAndDeviceId").setString("commandId", commandId).setString("deviceId", deviceId).list();
    }

    Object getCommandParseTargetsByIds(String commandId, String targetString, String deviceId, Session session) {
        return session.getNamedQuery("getCommandParseTargetsByIds").setString("commandId", commandId).setString("targetString", targetString).setString("deviceId", deviceId).uniqueResult();
    }

    List<CommandTranslations> getCommandTranslations(int countryId, String commandTypeId, Session session) {
        Criteria criteria = session.createCriteria(CommandTranslations.class, "ct");
        criteria.createAlias("ct.commands", "c", CriteriaSpecification.INNER_JOIN);
        criteria.add(Restrictions.eq("c.commandTypes.commandTypeId", commandTypeId));
        criteria.add(Restrictions.eq("ct.id.countryId", countryId));
        criteria.addOrder(Order.asc("ct.commandName"));
        return criteria.setMaxResults(200).list();
    }

    List<Object> getCommanddetailsByDeviceId(String deviceId, int countryId, Session session) {
        SQLQuery query = session.createSQLQuery("SELECT cpt.commandid as commandid, cpt.targetstring as targetstring, ct.commandname as commandname, " +
                "cpt.devicetypecommandid as devicetypecommandid, dtct.commandname as dtctcommandname FROM CommandParseTargets as cpt INNER JOIN " +
                "CommandTranslations as ct ON cpt.commandid=ct.commandid INNER JOIN DeviceTypeCommandTranslations as dtct ON cpt.devicetypecommandid = dtct.devicetypecommandid " +
                "AND ct.countryid=" + countryId + " AND dtct.countryid=" + countryId + " AND cpt.deviceid='" + deviceId + "' ORDER BY ct.commandname ");
        query.addScalar("commandid", Hibernate.STRING);
        query.addScalar("targetstring", Hibernate.STRING);
        query.addScalar("commandname", Hibernate.STRING);
        query.addScalar("devicetypecommandid", Hibernate.STRING);
        query.addScalar("dtctcommandname", Hibernate.STRING);
        return query.setMaxResults(200).list();
    }

    Object getConnectorsByDeviceIdAndDeviceDataItemId(String deviceId, String deviceDataItemId, Session session) {
        return session.getNamedQuery("getConnectorsByDeviceIdAndDeviceDataItemId").setString("deviceId", deviceId).setString("deviceDataItemId", deviceDataItemId).uniqueResult();
    }

    List<DeviceDataitemTranslations> getDeviceDataItemTranslationsByCriteria(String userId, int countryId, Session session) {
        Criteria criteria = session.createCriteria(DeviceDataitemTranslations.class, "ddit");
        criteria.createAlias("ddit.deviceDataItems", "ddi", CriteriaSpecification.INNER_JOIN);
        criteria.createCriteria("ddi.userFavoriteDataItemses", "ufdi", CriteriaSpecification.INNER_JOIN);
        criteria.add(Restrictions.eq("ufdi.id.userId", userId));
        criteria.add(Restrictions.eq("ddit.id.countryId", countryId));
        criteria.addOrder(Order.asc("ddit.dataItemName"));
        return criteria.setMaxResults(100).list();
    }

    List<Object> getDeviceDetailsBySearchCriteria(se.info24.devicejaxbPost.Device deviceXML, TingcoDevice tingcoDevice, String loggedinuserId, Session session, String objectTags) {
        List<Object> deviceList = new ArrayList<Object>();
        UserDAO userDAO = new UserDAO();
        Users2 users2 = userDAO.getUserById(loggedinuserId, session);
        StringBuffer queryString = new StringBuffer();
        queryString.append("select d.deviceId as deviceId from Device as d inner join Groups as g on d.groupId " +
                "= g.groupId and g.groupid in(" + TCMUtil.getGroupTrust(users2.getGroupId()) + ") ");
        if (deviceXML.getGroupID() != null) {
            if (TCMUtil.isValidUUID(deviceXML.getGroupID().getGroupName())) {
                queryString.append("inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.groupId = '" + deviceXML.getGroupID().getGroupName() + "' ");
            } else {
                queryString.append("inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.groupName like '%" + deviceXML.getGroupID().getGroupName() + "%' ");
            }

        }

        if (deviceXML.getOptionalCountryID() != null) {
            queryString.append("inner join Addresses as addr on d.addressId = addr.addressId inner join country as cou on addr.countryId = cou.countryId ");
            if (deviceXML.getOptionalCountryID().matches("[0-9]+")) {
                queryString.append(" and cou.countryId = " + Integer.valueOf(deviceXML.getOptionalCountryID()) + " ");
            } else {
                queryString.append(" and cou.countryName like '%" + deviceXML.getOptionalCountryID() + "%' ");
            }
        }

        if (deviceXML.getObjectGroupName() != null) {
            queryString.append(" inner join Ogmdevice as ogm on d.deviceId = ogm.deviceId inner join ObjectGroups as og on ogm.objectGroupId = og.objectGroupId " +
                    " inner join ObjectGroupTranslations as ogt on og.objectGroupId = ogt.objectGroupId and ogt.countryId = " + deviceXML.getCountryID().getValue() + " ");
            if (TCMUtil.isValidUUID(deviceXML.getObjectGroupName())) {
                queryString.append("and ogt.objectGroupId = '" + deviceXML.getObjectGroupName() + "' ");
            } else {
                queryString.append("and ogt.objectGroupName like '%" + deviceXML.getObjectGroupName() + "%' ");
            }
        }

        if (deviceXML.getDeviceName() != null) {
            if (TCMUtil.isValidUUID(deviceXML.getDeviceName())) {
                queryString.append(" and d.deviceId = '" + deviceXML.getDeviceName() + "' ");
            } else {
                queryString.append(" and (d.deviceName like '%" + deviceXML.getDeviceName() + "%' or d.deviceName2 like '%" + deviceXML.getDeviceName() + "%') ");
            }
        }
        if (objectTags != null) {
            queryString.append(" and d.deviceId in (Select ObjectID from ISMOperations.dbo.ObjectUsageRecords where ObjectID in (select ObjectID from ObjectTags where SearchTags like '%" + objectTags + "%'))");
        }
        if (deviceXML.getAssetID() != null) {
            queryString.append(" and d.assetId = '" + deviceXML.getAssetID() + "' ");
        }
        if (deviceXML.getDeviceTypeID() != null) {
            queryString.append(" inner join deviceTypes as dt on d.deviceTypeId = dt.deviceTypeId ");
            if (TCMUtil.isValidUUID(deviceXML.getDeviceTypeID().getValue())) {
                queryString.append(" and dt.deviceTypeId = '" + deviceXML.getDeviceTypeID().getValue() + "' ");
            } else {
                queryString.append(" and dt.deviceTypeName like '%" + deviceXML.getDeviceTypeID().getValue() + "%' ");
            }

            if (deviceXML.getFields() != null) {
                if (!deviceXML.getFields().isEmpty()) {
                    queryString.append(" and d.deviceId in (select ObjectID from ObjectFieldData where FieldID = '" + deviceXML.getFields().get(0).getFieldID() + "' and FieldValue like '%" + deviceXML.getFields().get(0).getFieldValue() + "%')");
                }
            }
            if (deviceXML.getDeviceSettings() != null) {
                if (!deviceXML.getDeviceSettings().getDeviceSetting().isEmpty()) {
                    queryString.append(" and d.deviceId in (select DeviceID from DeviceSettings where DeviceSettingID = '" + deviceXML.getDeviceSettings().getDeviceSetting().get(0).getDeviceSettingID() + "' and DeviceSettingValue like '%" + deviceXML.getDeviceSettings().getDeviceSetting().get(0).getDeviceSettingName() + "%')");
                }
            }
        }
        queryString.append(" group by d.deviceId order by min(d.createdDate) desc ");
        TCMUtil.loger(getClass().getName(), queryString.toString(), "Info");
        SQLQuery query = session.createSQLQuery(queryString.toString());
        query.addScalar("deviceId", Hibernate.STRING);
        deviceList = query.list();
        return deviceList;
    }

    List<String> getDeviceForDeviceLocationHistory(se.info24.devicejaxbPost.Device deviceXML, TingcoDevice tingcoDevice, String loggedinuserId, Session session) {
        UserDAO userDAO = new UserDAO();
        Users2 users2 = userDAO.getUserById(loggedinuserId, session);
        StringBuffer queryString = new StringBuffer();

        queryString.append("select dh.deviceId as deviceId from ISMOperations.dbo.DeviceHistory as dh inner join groups as g on dh.groupId = g.groupId and g.groupId in (" + TCMUtil.getGroupTrust(users2.getGroupId()) + ") " +
                " inner join device as d on dh.deviceId = d.deviceId ");

        if (deviceXML.getGroupID() != null) {
            if (TCMUtil.isValidUUID(deviceXML.getGroupID().getGroupName())) {
                queryString.append(" inner join groupTranslations as gts on g.groupId = gts.groupId and gts.groupId = '" + deviceXML.getGroupID().getGroupName() + "' and gts.countryId = " + deviceXML.getCountryID().getValue() + " ");
            } else {
                queryString.append(" inner join groupTranslations as gts on g.groupId = gts.groupId and gts.groupName like '%" + deviceXML.getGroupID().getGroupName() + "%' and gts.countryId = " + deviceXML.getCountryID().getValue() + " ");
            }
        }

        if (deviceXML.getObjectGroupName() != null) {
            queryString.append(" inner join Ogmdevice as ogm on d.deviceId = ogm.deviceId inner join ObjectGroups as og on ogm.objectGroupId = og.objectGroupId " +
                    " inner join ObjectGroupTranslations as ogt on og.objectGroupId = ogt.objectGroupId and ogt.countryId = " + deviceXML.getCountryID().getValue() + " ");
            if (TCMUtil.isValidUUID(deviceXML.getObjectGroupName())) {
                queryString.append("and ogt.objectGroupId = '" + deviceXML.getObjectGroupName() + "' ");
            } else {
                queryString.append("and ogt.objectGroupName like '%" + deviceXML.getObjectGroupName() + "%' ");
            }
        }

        if (deviceXML.getDeviceName() != null) {
            if (TCMUtil.isValidUUID(deviceXML.getDeviceName())) {
                queryString.append(" and d.deviceId = '" + deviceXML.getDeviceName() + "' ");
            } else {
                queryString.append(" and (d.deviceName like '%" + deviceXML.getDeviceName() + "%') ");
            }
        }

        queryString.append(" order by dh.createdDate desc ");

        SQLQuery query = session.createSQLQuery(queryString.toString());
        query.addScalar("deviceId", Hibernate.STRING);

        return query.setMaxResults(200).list();
    }

    List<DeviceHistory> getDeviceHistory(String deviceId, String fromDate, String toDate, Session ismOperationsession, String timeZoneGMToffset) throws ParseException {
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        gc.setTime(lFormat.parse(fromDate));
        gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
        String gc_diff_time = lFormat.format(gc.getTime());
        gc.setTime(lFormat.parse(toDate));
        gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
        String gc_time = lFormat.format(gc.getTime());
        return ismOperationsession.getNamedQuery("getDeviceHistoryByDeviceIdAnddataItemTime").setString("deviceId", deviceId).setString("fromDate", gc_diff_time).setString("toDate", gc_time).setMaxResults(500).list();
    }

    List<DeviceLinks> getDeviceLinksByITrustDeviceId(String deviceId, Session session) {
        return session.getNamedQuery("getDeviceLinksByiTrustDeviceId").setString("itrustDeviceId", deviceId).setMaxResults(200).list();
    }

    List<DeviceStatusDataItems> getDeviceStatusDataItems(Set<String> groupIDSet, String userId, Session session, Session ismOperationSession) {
        List<String> deviceDataItemIds = getUserFavoriteDataItemsByCriteria(userId, session);
        if (deviceDataItemIds.isEmpty()) {
            List<String> deviceIdsList = getDeviceIdsList(groupIDSet, session);
            Hashtable<Integer, List<String>> ht = new Hashtable<Integer, List<String>>();
            int counterKey = 1;
            while (deviceIdsList.size() > 2000) {
                List<String> subList = new ArrayList<String>(deviceIdsList.subList(0, 2000));
                deviceIdsList.removeAll(subList);
                ht.put(counterKey++, subList);
            }
            List<DeviceStatusDataItems> list = new ArrayList<DeviceStatusDataItems>();
            if (ht.size() > 0) {
                for (Integer key : ht.keySet()) {
                    list = getDeviceStatusDataItemsByDeviceIds(ht.get(key), ismOperationSession);
                    list.addAll(list);
                }
            }
            if (!deviceIdsList.isEmpty()) {
                list = getDeviceStatusDataItemsByDeviceIds(deviceIdsList, ismOperationSession);
                list.addAll(list);
            }

            Collections.sort(list, new Comparator<DeviceStatusDataItems>() {

                public int compare(DeviceStatusDataItems o1, DeviceStatusDataItems o2) {
                    return o2.getActiveFromDate().compareTo(o1.getActiveFromDate());
                }
            });
            if (list.size() > 200) {
                return list.subList(0, 200);
            } else {
                return list;
            }

        } else {
            return getDeviceStatusDataItemsByDeviceDataItemIds(deviceDataItemIds, ismOperationSession);
        }
    }

    List<DeviceStatusDataItems> getDeviceStatusDataItemsByDeviceIds(List<String> deviceIdsList, Session ismOperationSession) {
        return ismOperationSession.getNamedQuery("getDeviceStatusDataItemsByDataItemId").setParameterList("deviceDataItemId", deviceIdsList).list();
    }

    List<String> getDeviceIdsList(Set<String> groupIDSet, Session session) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Device.class);
        detachedCriteria.add(Restrictions.in("groups.groupId", groupIDSet));
        detachedCriteria.setProjection(Projections.property("deviceId"));
        return detachedCriteria.getExecutableCriteria(session).list();
    }

    List<DeviceStatusDataItems> getDeviceStatusDataItemsByDeviceDataItemIds(List<String> deviceDataItemIdsList, Session ismOperationSession) {
        return ismOperationSession.getNamedQuery("getDeviceStatusDataItemsByDataItemId").setParameterList("deviceDataItemId", deviceDataItemIdsList).setMaxResults(200).list();
    }

    List<DeviceStatusDataItems> getDeviceStatusDataItemsBySearchCriteria(se.info24.devicejaxbPost.Device deviceXML, Session session) {
        StringBuffer queryString = new StringBuffer();
        queryString.append("select dsdi.deviceId as DeviceId, dsdi.deviceDataItemId as deviceDataItemId , dsdi.deviceDataValue as deviceDataValue, dsdi.unit as unit, dsdi.activeFromDate as activeFromDate from ISMOperations.dbo.DeviceStatusDataItems as dsdi inner join device as d on " +
                " dsdi.deviceId = d.deviceId inner join groups as g on d.groupId = g.groupId and g.groupId in(" + TCMUtil.getGroupTrust(deviceXML.getGroupID().getValue()) + ") ");

        if (deviceXML.getGroupID().getGroupName() != null) {
            if (TCMUtil.isValidUUID(deviceXML.getGroupID().getGroupName())) {
                queryString.append(" inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.groupId = '" + deviceXML.getGroupID().getGroupName() + "' and gts.countryid = '" + deviceXML.getCountryID().getValue() + "' ");
            } else {
                queryString.append(" inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.groupName like '%" + deviceXML.getGroupID().getGroupName() + "%' and gts.countryid = '" + deviceXML.getCountryID().getValue() + "' ");
            }
        }

        if (deviceXML.getObjectGroupName() != null) {
            queryString.append(" inner join Ogmdevice as ogm on d.deviceId = ogm.deviceId inner join ObjectGroups as og on ogm.objectGroupId = og.objectGroupId " +
                    " inner join ObjectGroupTranslations as ogt on og.objectGroupId = ogt.objectGroupId and ogt.countryId = " + deviceXML.getCountryID().getValue() + " ");
            if (TCMUtil.isValidUUID(deviceXML.getObjectGroupName())) {
                queryString.append("and ogt.objectGroupId = '" + deviceXML.getObjectGroupName() + "' ");
            } else {
                queryString.append("and ogt.objectGroupName like '%" + deviceXML.getObjectGroupName() + "%' ");
            }
        }

        if (deviceXML.getDeviceName() != null) {
            if (TCMUtil.isValidUUID(deviceXML.getDeviceName())) {
                queryString.append(" and d.deviceId = '" + deviceXML.getDeviceName() + "' ");
            } else {
                queryString.append(" and (d.deviceName like '%" + deviceXML.getDeviceName() + "%' or d.deviceName2 like '%" + deviceXML.getDeviceName() + "%') ");
            }
        }

        if (deviceXML.getAssetID() != null) {
            queryString.append(" and d.assetId = '" + deviceXML.getAssetID() + "' ");
        }

        if (deviceXML.getDeviceDataItems() != null && !deviceXML.getDeviceDataItems().isEmpty()) {
            queryString.append(" and (");
            boolean flag = false;
            for (String str : deviceXML.getDeviceDataItems().get(0).getDeviceDataItemID()) {
                if (flag) {
                    queryString.append(" or ");
                }
                queryString.append(" dsdi.deviceDataItemId = '" + str + "'");
                flag = true;
            }
            queryString.append(")");
//            queryString.append(" and dsdi.deviceDataItemId in(:deviceDataItemList) ");
        }

        queryString.append(" order by dsdi.activeFromDate desc");
        SQLQuery sqlQuery = session.createSQLQuery(queryString.toString());

//        if (deviceXML.getDeviceDataItems() != null && !deviceXML.getDeviceDataItems().isEmpty()) {
//            TCMUtil.loger(getClass().getName(), deviceXML.getDeviceDataItems().get(0).getDeviceDataItemID().get(0), "Info");
//            for (String str : deviceXML.getDeviceDataItems().get(0).getDeviceDataItemID()) {
//
//            }
//            sqlQuery.setParameter("deviceDataItemList", deviceXML.getDeviceDataItems().get(0).getDeviceDataItemID());
//        }
        sqlQuery.addScalar("DeviceId", Hibernate.STRING);
        sqlQuery.addScalar("deviceDataItemId", Hibernate.STRING);
        sqlQuery.addScalar("deviceDataValue", Hibernate.STRING);
        sqlQuery.addScalar("unit", Hibernate.STRING);
        sqlQuery.addScalar("activeFromDate", Hibernate.DATE);

        sqlQuery.setResultTransformer(Transformers.aliasToBean(DeviceStatusDataItems.class));
        return sqlQuery.setMaxResults(200).list();
    }

    List<DeviceTypes> getDeviceTypeDetails(String deviceTypeName, String deviceManufacturerName, Session session) {
        StringBuffer queryString = new StringBuffer();
        queryString.append("from DeviceTypes as dt where dt.deviceTypeId is not null ");
        if (deviceTypeName != null) {
            if (TCMUtil.isValidUUID(deviceTypeName)) {
                queryString.append(" and dt.deviceTypeId = '" + deviceTypeName + "' ");
            } else {
                queryString.append(" and dt.deviceTypeName like '%" + deviceTypeName + "%' ");
            }
        }
        if (deviceManufacturerName != null) {
            if (TCMUtil.isValidUUID(deviceManufacturerName)) {
                queryString.append(" and dt.deviceManufacturers.deviceManufacturerId = '" + deviceManufacturerName + "' ");
            } else {
                queryString.append(" and dt.deviceManufacturers.deviceManufacturerName like '%" + deviceManufacturerName + "%' ");
            }
        }
        queryString.append("order by dt.deviceTypeName asc");

        Query query = session.createQuery(queryString.toString());
        return query.list();
    }

    List<DeviceTypes> getDeviceTypesByIdsAndDeviceTypeName(Set<String> deviceTypeIds, String searchString, Session session) {
        return session.getNamedQuery("getDeviceTypesByIdsListAndDeviceTypeName").setParameterList("deviceTypeId", deviceTypeIds).setString("deviceTypeName", searchString).setMaxResults(200).list();
    }

    Ogmdevice getOGMDeviceByDeviceIdAndObjectGroupId(String deviceId, String objectGroupId, Session session) {
        List<Ogmdevice> ogmDeviceList = session.getNamedQuery("getOgmdeviceByDeviceIdAndObjectGroupId").setString("deviceId", deviceId).setString("objectGroupId", objectGroupId).list();
        return !ogmDeviceList.isEmpty() ? ogmDeviceList.get(0) : null;
    }

    List<ObjectContacts> getObjectContactsByGroupId(Set<String> groupIdsList, Session session) {
        List<ObjectContacts> objectContacts = new ArrayList<ObjectContacts>();
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ObjectContacts.class, "oc");
        detachedCriteria.add(Restrictions.in("oc.groupId", groupIdsList));
        detachedCriteria.addOrder(Order.desc("oc.createdDate"));
        detachedCriteria.setProjection(Projections.property("oc.objectContactId"));
        List<String> objectContactIds = detachedCriteria.getExecutableCriteria(session).setMaxResults(200).list();
        if (!objectContactIds.isEmpty()) {
            Criteria criteria = session.createCriteria(ObjectContacts.class, "oc");
            criteria.add(Restrictions.in("oc.objectContactId", objectContactIds));
            criteria.addOrder(Order.asc("oc.contactFirstName"));
            objectContacts = criteria.list();
        }
        return objectContacts;
    }

    ObjectMediaFiles getObjectMediaFilesByIds(String deviceId, String mediaFileId, Session session) {
        return (ObjectMediaFiles) session.getNamedQuery("getObjectMediaFilesByIds").setString("objectId", deviceId).setString("mediaFileId", mediaFileId).uniqueResult();
    }

    List<Object> getObjectUsageRecordsForUsageLog(se.info24.devicejaxbPost.Device deviceXML, TingcoDevice tingcoDevice, Session ismOperationsSession, Session session, String timeZoneGMToffset) throws ParseException {
        List<Object> objectUsageRecordsList = new ArrayList<Object>();
        String timePeriod = deviceXML.getEventDatas().getTimePeriod();
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        String gc_time = lFormat.format(gc.getTime());
        GregorianCalendar gc_diff = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        String gc_diff_time = null;
        if (timePeriod != null) {
            if (timePeriod.equalsIgnoreCase("hour")) {
                gc_diff.add(GregorianCalendar.HOUR, -1);
                gc_diff_time = lFormat.format(gc_diff.getTime());
            } else if (timePeriod.equalsIgnoreCase("day")) {
                gc_diff.add(GregorianCalendar.DATE, -1);
                gc_diff_time = lFormat.format(gc_diff.getTime());
            } else if (timePeriod.equalsIgnoreCase("week")) {
                gc_diff.add(GregorianCalendar.DATE, -7);
                gc_diff_time = lFormat.format(gc_diff.getTime());
            } else if (timePeriod.equalsIgnoreCase("month")) {
                gc_diff.add(GregorianCalendar.MONTH, -1);
                gc_diff_time = lFormat.format(gc_diff.getTime());
            }
        } else if (deviceXML.getEventDatas().getFromDate() != null && deviceXML.getEventDatas().getToDate() != null) {

            gc_diff.setTime(lFormat.parse(deviceXML.getEventDatas().getFromDate()));
            gc_diff.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc_diff.getTime()));
            gc_diff_time = lFormat.format(gc_diff.getTime());

            gc.setTime(lFormat.parse(deviceXML.getEventDatas().getToDate()));
            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
            gc_time = lFormat.format(gc.getTime());
        }

        StringBuffer queryString = new StringBuffer();

        if (deviceXML.getGroupID().getGroupName() != null) {

            queryString.append("select distinct our.UsageRecordId as usageRecordId, our.ObjectId as objectId, our.groupid, our.UsageStartTime as usageStartTime, " +
                    "our.UsageStopTime as usageStopTime, our.DataItemName as dataItemName, our.UsedByUserAlias as usedByUserAlias, our.UsageVolume as usageVolume, " +
                    "our.UsageUnitId as usageUnitId from ISMOperations.dbo.ObjectUsageRecords as our inner join Groups as g on our.groupId = g.groupId " +
                    "and g.groupid in(" + TCMUtil.getGroupTrust(deviceXML.getGroupID().getValue()) + ") ");
            if (deviceXML.getGroupID().getGroupName() != null) {
                if (TCMUtil.isValidUUID(deviceXML.getGroupID().getGroupName())) {
                    queryString.append("inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.groupId = '" + deviceXML.getGroupID().getGroupName() + "' and gts.countryId = " + deviceXML.getCountryID().getValue() + "  ");
                } else {
                    queryString.append("inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.groupName like '%" + deviceXML.getGroupID().getGroupName() + "%' and gts.countryId = " + deviceXML.getCountryID().getValue() + " ");
                }
            }

        } else {

            queryString.append("select distinct our.UsageRecordId as usageRecordId, our.ObjectId as objectId, our.groupid, our.UsageStartTime as usageStartTime, " +
                    "our.UsageStopTime as usageStopTime, our.DataItemName as dataItemName, our.UsedByUserAlias as usedByUserAlias, our.UsageVolume as usageVolume, " +
                    "our.UsageUnitId as usageUnitId from ISMOperations.dbo.ObjectUsageRecords as our inner join Groups as g on our.groupId = g.groupId " +
                    "and g.groupid in(" + TCMUtil.getGroupTrust(deviceXML.getGroupID().getValue()) + ") ");
        }
//        }
        if (deviceXML.getDeviceName() != null) {
            if (TCMUtil.isValidUUID(deviceXML.getDeviceName())) {
                queryString.append("and our.ObjectId = '" + deviceXML.getDeviceName() + "' ");
            } else {
                queryString.append("inner join device as d on  our.ObjectId = d.deviceId and (d.deviceName like '%" + deviceXML.getDeviceName() + "%' or d.deviceName2 like '%" + deviceXML.getDeviceName() + "%') ");
            }
        }
        if (deviceXML.getLastUpdatedByUserID() != null) {
            if (deviceXML.getLastUpdatedByUserID().getUserFullName() != null) {
                queryString.append(" and (our.UsedByUserName like '%" + deviceXML.getLastUpdatedByUserID().getUserFullName() + "%' or our.UsedByUserAlias like '%" + deviceXML.getLastUpdatedByUserID().getUserFullName() + "%') ");

            }
        }

        if (deviceXML.getAssetID() != null) {
            queryString.append("inner join device as de on our.objectId = de.deviceId and de.assetId = '" + deviceXML.getAssetID() + "' ");
        }

        if (deviceXML.getOptionalCountryID() != null) {
            if (deviceXML.getOptionalCountryID().matches("[0-9]+")) {
                queryString.append("inner join device as dev on our.objectId = dev.deviceId inner join addresses as addr on dev.addressId = addr.addressId and addr.countryId = " + deviceXML.getOptionalCountryID() + " ");
            } else {
                queryString.append("inner join device as dev on our.objectId = dev.deviceId inner join addresses as addr on dev.addressId = addr.addressId inner join Country as c on c.CountryID = addr.CountryID and c.CountryName like '%" + deviceXML.getOptionalCountryID() + "%'");
            }
        }

        if (deviceXML.getObjectGroupName() != null) {
            queryString.append(" inner join Ogmdevice as ogm on our.objectId = ogm.deviceId inner join ObjectGroups as og on ogm.objectGroupId = og.objectGroupId " +
                    " inner join ObjectGroupTranslations as ogt on og.objectGroupId = ogt.objectGroupId and ogt.countryId = " + deviceXML.getCountryID().getValue() + " ");
            if (TCMUtil.isValidUUID(deviceXML.getObjectGroupName())) {
                queryString.append("and ogt.objectGroupId = '" + deviceXML.getObjectGroupName() + "' ");
            } else {
                queryString.append("and ogt.objectGroupName like '%" + deviceXML.getObjectGroupName() + "%' ");
            }
        }
        if (timePeriod != null) {
            if (timePeriod.equalsIgnoreCase("100")) {
                queryString.append(" order by our.usageStartTime desc");
                SQLQuery query = session.createSQLQuery(queryString.toString());
                query.addScalar("usageRecordId", Hibernate.STRING);
                query.addScalar("objectId", Hibernate.STRING);
                query.addScalar("usageStartTime", Hibernate.STRING);
                query.addScalar("usageStopTime", Hibernate.STRING);
                query.addScalar("dataItemName", Hibernate.STRING);
                query.addScalar("usedByUserAlias", Hibernate.STRING);
                query.addScalar("usageVolume", Hibernate.STRING);
                query.addScalar("usageUnitId", Hibernate.STRING);
                objectUsageRecordsList = query.setMaxResults(100).list();

            } else if (timePeriod.equalsIgnoreCase("5")) {
                queryString.append(" order by our.usageStartTime desc");
                SQLQuery query = session.createSQLQuery(queryString.toString());
                query.addScalar("usageRecordId", Hibernate.STRING);
                query.addScalar("objectId", Hibernate.STRING);
                query.addScalar("usageStartTime", Hibernate.STRING);
                query.addScalar("usageStopTime", Hibernate.STRING);
                query.addScalar("dataItemName", Hibernate.STRING);
                query.addScalar("usedByUserAlias", Hibernate.STRING);
                query.addScalar("usageVolume", Hibernate.STRING);
                query.addScalar("usageUnitId", Hibernate.STRING);
                objectUsageRecordsList = query.setMaxResults(5).list();

            } else {
                queryString.append(" and our.usageStartTime between '" + gc_diff_time + "' and '" + gc_time + "' order by our.usageStartTime desc");
                SQLQuery query = session.createSQLQuery(queryString.toString());
                query.addScalar("usageRecordId", Hibernate.STRING);
                query.addScalar("objectId", Hibernate.STRING);
                query.addScalar("usageStartTime", Hibernate.STRING);
                query.addScalar("usageStopTime", Hibernate.STRING);
                query.addScalar("dataItemName", Hibernate.STRING);
                query.addScalar("usedByUserAlias", Hibernate.STRING);
                query.addScalar("usageVolume", Hibernate.STRING);
                query.addScalar("usageUnitId", Hibernate.STRING);
                objectUsageRecordsList = query.list();
            }
        } else {
            queryString.append(" and our.usageStartTime between '" + gc_diff_time + "' and '" + gc_time + "' order by our.usageStartTime desc");
            SQLQuery query = session.createSQLQuery(queryString.toString());
            query.addScalar("usageRecordId", Hibernate.STRING);
            query.addScalar("objectId", Hibernate.STRING);
            query.addScalar("usageStartTime", Hibernate.STRING);
            query.addScalar("usageStopTime", Hibernate.STRING);
            query.addScalar("dataItemName", Hibernate.STRING);
            query.addScalar("usedByUserAlias", Hibernate.STRING);
            query.addScalar("usageVolume", Hibernate.STRING);
            query.addScalar("usageUnitId", Hibernate.STRING);
            objectUsageRecordsList = query.list();
        }
        return objectUsageRecordsList;
    }

    public List<TransactionProducts> getTransactionProducts(String transactionId, Session ismOperationsSession) {
        return ismOperationsSession.getNamedQuery("getTransactionProductsByTransactionId").setString("transactionId", transactionId).list();
    }

    List<RecurrenceTypes> getRecurrenceTypesByObjectCodes(List<String> objectCodeList, Session session) {
        return session.getNamedQuery("getRecurrenceTypesByobjectCode").setParameterList("objectCode", objectCodeList).list();
    }

    List<Object> getSalesTransactionReport(se.info24.devicejaxbPost.Device deviceXML, TingcoDevice tingcoDevice, Session session) {
        List<Object> transactionResultList = new ArrayList<Object>();
        StringBuffer queryString = new StringBuffer();
        queryString.append("select distinct trs.transactionid as transactionId, x.countryname as countryName, gts.groupname as groupName, x.organizationnumber as organizationNumber, ofds.fieldvalue as fieldValue, x.devicename as  deviceName, x.transactionstarttime as transactionStartTime, trs.useralias as userAlias, tps.ppu/100 as ppu, " +//tp.ppu / 100
                "tps.vat as vat, cus.currencyisocharcode as currencyISOCharCode from ISMOperations.dbo.transactionresult as trs inner join (" +
                "select c.countryname , g.organizationnumber, tr.devicename, tr.transactionstarttime from ISMOperations.dbo.transactionresult as tr inner join ISMOperations.dbo.transactionproducts as tp on tr.transactionid=tp.transactionid  " +
                "inner join device as d on tr.deviceid=d.deviceid inner join addresses as ad on d.addressid = ad.addressid inner join country as c on ad.countryid=c.countryid " +//+deviceXML.getCountryID().getValue()+" "+
                "inner join groups as g on tr.devicegroupid=g.groupid inner join grouptranslations as gt on g.groupid=gt.groupid inner join objectfielddata as ofd " +
                "on tr.devicegroupid=ofd.objectid inner join fields as f on ofd.fieldid=f.fieldid and f.objectcode='VATNUMBER' inner join currency as cu on tr.currencyid = cu.currencyid " +
                " and tr.transactionparentid is null and tr.ispurchase=1 and tr.deviceGroupId in(" + TCMUtil.getGroupTrust(deviceXML.getGroupID().getValue()) + ") " +
                " and tr.transactionstarttime between '" + deviceXML.getEventDatas().getFromDate() + "' and '" + deviceXML.getEventDatas().getToDate() + "' ");

        if (deviceXML.getAssetID() != null) {
            queryString.append(" and d.assetId = '" + deviceXML.getAssetID() + "' ");
        }
        if (deviceXML.getOptionalCountryID() != null) {
            if (deviceXML.getOptionalCountryID().matches("[0-9]+")) {
                queryString.append("and c.countryId = " + deviceXML.getOptionalCountryID() + " ");
            } else {
                queryString.append(" and c.countryName like '%" + deviceXML.getOptionalCountryID() + "%' ");
            }
        }

        if (deviceXML.getDeviceAgreementID() != null) {
            queryString.append("and d.agreementId = '" + deviceXML.getDeviceAgreementID() + "' ");
        }
        queryString.append("  group by c.countryname , g.organizationnumber, tr.devicename, tr.transactionstarttime) x on");
        queryString.append(" x.devicename = trs.devicename and trs.transactionstarttime = x.transactionstarttime inner join groups as gs on trs.devicegroupid=gs.groupid inner join grouptranslations as gts on gs.groupid=gts.groupid inner join objectfielddata as ofds on trs.devicegroupid=ofds.objectid inner join fields as fs on ofds.fieldid=fs.fieldid and fs.objectcode='VATNUMBER' ");
        queryString.append("  inner join currency as cus on trs.currencyid = cus.currencyid inner join ISMOperations.dbo.transactionproducts as tps on trs.transactionid=tps.transactionid ");


        if (deviceXML.getGroupID().getGroupName() != null) {
            if (TCMUtil.isValidUUID(deviceXML.getGroupID().getGroupName())) {
                queryString.append("inner join GroupTranslations as gtss on gs.groupId = gtss.groupId and gtss.groupId = '" + deviceXML.getGroupID().getGroupName() + "' ");
            } else {
                queryString.append("inner join GroupTranslations as gtss on gs.groupId = gtss.groupId and gtss.groupName like '%" + deviceXML.getGroupID().getGroupName() + "%' ");
            }
        }

        if (deviceXML.getDeviceName() != null) {
            if (TCMUtil.isValidUUID(deviceXML.getDeviceName())) {
                queryString.append(" and trs.deviceId = '" + deviceXML.getDeviceName() + "' ");
            } else {
                queryString.append(" and trs.deviceName like '%" + deviceXML.getDeviceName() + "%' ");
            }
        }

        if (deviceXML.getObjectGroupName() != null) {
            queryString.append(" inner join Ogmdevice as ogm on trs.deviceId = ogm.deviceId inner join ObjectGroups as og on ogm.objectGroupId = og.objectGroupId " +
                    " inner join ObjectGroupTranslations as ogt on og.objectGroupId = ogt.objectGroupId and ogt.countryId = " + deviceXML.getCountryID().getValue() + " ");
            if (TCMUtil.isValidUUID(deviceXML.getObjectGroupName())) {
                queryString.append("and ogt.objectGroupId = '" + deviceXML.getObjectGroupName() + "' ");
            } else {
                queryString.append("and ogt.objectGroupName like '%" + deviceXML.getObjectGroupName() + "%' ");
            }
        }



        if (!deviceXML.getRecurrenceTypes().isEmpty()) {
            if (deviceXML.getRecurrenceTypes().get(0).getRecurringPurchases().get(0).getUserAliasID() != null) {
                queryString.append(" and trs.useralias like '%" + deviceXML.getRecurrenceTypes().get(0).getRecurringPurchases().get(0).getUserAliasID() + "%' ");
            }
        }

        if (!deviceXML.getRecurrenceTypes().isEmpty()) {
            if (deviceXML.getRecurrenceTypes().get(0).getRecurringPurchases().get(0).getProductVariantID() != null) {
                if (TCMUtil.isValidUUID(deviceXML.getRecurrenceTypes().get(0).getRecurringPurchases().get(0).getProductVariantID())) {
                    queryString.append(" and tps.productVariantId = '" + deviceXML.getRecurrenceTypes().get(0).getRecurringPurchases().get(0).getProductVariantID() + "' ");
                } else {
                    queryString.append(" and tps.productVariantName like '%" + deviceXML.getRecurrenceTypes().get(0).getRecurringPurchases().get(0).getProductVariantID() + "%' ");
                }
            }
        }


//        queryString.append("  group by tr.transactionid , c.countryname , gt.groupname, g.organizationnumber, ofd.fieldvalue, tr.devicename, tr.transactionstarttime , tr.useralias, tp.amount, tp.vat, cu.currencyisocharcode, tp.productVariantId ");


        queryString.append(" order by x.countryname, gts.groupname, x.devicename, x.transactionstarttime ");

        SQLQuery query = session.createSQLQuery(queryString.toString());
        System.out.println(queryString.toString());
        query.addScalar("transactionId", Hibernate.STRING);
        query.addScalar("countryName", Hibernate.STRING);
        query.addScalar("groupName", Hibernate.STRING);
        query.addScalar("organizationNumber", Hibernate.STRING);
        query.addScalar("fieldValue", Hibernate.STRING);
        query.addScalar("deviceName", Hibernate.STRING);
        query.addScalar("transactionStartTime", Hibernate.STRING);
        query.addScalar("userAlias", Hibernate.STRING);
        query.addScalar("ppu", Hibernate.STRING);
        query.addScalar("vat", Hibernate.STRING);
        query.addScalar("currencyISOCharCode", Hibernate.STRING);

        transactionResultList = query.list();
        return transactionResultList;
    }

    TransactionProducts getTransactionProductsByIds(String transactionId, String productVariantId, Session ismOperationsSession) {
        return (TransactionProducts) ismOperationsSession.getNamedQuery("getTransactionProductsByIds").setString("transactionId", transactionId).setString("productVariantId", productVariantId).uniqueResult();
    }

    TransactionResult getTransactionResult(String transactionId, Session ismOperationsSession) {
        return (TransactionResult) ismOperationsSession.getNamedQuery("getTransactionResultById").setString("transactionId", transactionId).uniqueResult();
    }

    List<Object> getTransactionResultForPurchaseTransaction(se.info24.devicejaxbPost.Device deviceXML, TingcoDevice tingcoDevice, Session session, String maxresult) {
        List<Object> transactionResultList = new ArrayList<Object>();
        StringBuffer queryString = new StringBuffer();
        String timePeriod = deviceXML.getEventDatas().getTimePeriod();
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS ");
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        String gc_time = lFormat.format(gc.getTime());
        GregorianCalendar gc_diff = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        String gc_diff_time = null;
        if (timePeriod != null) {
            if (timePeriod.equalsIgnoreCase("hour")) {
                gc_diff.add(GregorianCalendar.HOUR, -1);
                gc_diff_time = lFormat.format(gc_diff.getTime());
            } else if (timePeriod.equalsIgnoreCase("day")) {
                gc_diff.add(GregorianCalendar.DATE, -1);
                gc_diff_time = lFormat.format(gc_diff.getTime());
            } else if (timePeriod.equalsIgnoreCase("week")) {
                gc_diff.add(GregorianCalendar.DATE, -7);
                gc_diff_time = lFormat.format(gc_diff.getTime());
            } else if (timePeriod.equalsIgnoreCase("month")) {
                gc_diff.add(GregorianCalendar.MONTH, -1);
                gc_diff_time = lFormat.format(gc_diff.getTime());
            }
        } else if (deviceXML.getEventDatas().getFromDate() != null && deviceXML.getEventDatas().getToDate() != null) {
            gc_diff_time = deviceXML.getEventDatas().getFromDate();
            gc_time = deviceXML.getEventDatas().getToDate();
        }
        queryString.append("select tr.transactionId as transactionId, tr.transactionStartTime as transactionStartTime, tr.deviceName as deviceName, tr.firstName as firstName, " +
                "tr.lastName as lastName, tr.userAlias as userAlias, tr.amount as amount, tr.providerName as providerName, tr.currencyId as currencyId " +
                " from ISMOperations.dbo.TransactionResult as tr inner join Groups as g on tr.deviceGroupId " +
                "= g.groupId and tr.deviceGroupId in (" + TCMUtil.getGroupTrust(deviceXML.getGroupID().getValue()) + ") ");
        if (deviceXML.getGroupID().getGroupName() != null) {
            if (TCMUtil.isValidUUID(deviceXML.getGroupID().getGroupName())) {
                queryString.append("inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.groupId = '" + deviceXML.getGroupID().getGroupName() + "' and gts.countryId = " + deviceXML.getCountryID().getValue() + " ");
            } else {
                queryString.append("inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.groupName like '%" + deviceXML.getGroupID().getGroupName() + "%' and gts.countryId = " + deviceXML.getCountryID().getValue() + " ");
            }
        }


        if (deviceXML.getDeviceName() != null) {
            if (TCMUtil.isValidUUID(deviceXML.getDeviceName())) {
                queryString.append(" and tr.deviceId = '" + deviceXML.getDeviceName() + "' ");
            } else {
                queryString.append(" and tr.deviceName like '%" + deviceXML.getDeviceName() + "%' ");
            }
        }
        if (deviceXML.getLastUpdatedByUserID() != null) {
            if (deviceXML.getLastUpdatedByUserID().getUserFullName() != null) {
                if (TCMUtil.isValidUUID(deviceXML.getLastUpdatedByUserID().getUserFullName())) {
                    queryString.append(" and tr.UserAliasID = '" + deviceXML.getLastUpdatedByUserID().getUserFullName() + "'");
                } else {
                    queryString.append(" and (tr.firstName like '%" + deviceXML.getLastUpdatedByUserID().getUserFullName() + "%' or tr.lastName like '%" + deviceXML.getLastUpdatedByUserID().getUserFullName() + "%' or tr.userAlias like '%" + deviceXML.getLastUpdatedByUserID().getUserFullName() + "%') ");

                }
            }
        }

        if (deviceXML.getTransactionResult() != null) {
            se.info24.devicejaxbPost.TransactionResult transactionResult = deviceXML.getTransactionResult();
//            if (transactionResult.getIsAllStatuses() != 1 || transactionResult.getIsAllStatuses() == null) {
            if (transactionResult.getIsAllStatuses() != 1) {
                if (transactionResult.getIsPurchase() != null) {
                    queryString.append(" and tr.isPurchase = " + transactionResult.getIsPurchase() + " ");
                }
                if (transactionResult.getIsInvoice() != null) {
                    queryString.append(" and tr.isInvoiced = " + transactionResult.getIsInvoice() + " ");
                }
                if (transactionResult.getIsPaid() != null) {
                    queryString.append(" and tr.isPaid = " + transactionResult.getIsPaid() + " ");
                }
                if (transactionResult.getIsPayment() != null) {
                    queryString.append(" and tr.isPayment = " + transactionResult.getIsPayment() + " ");
                }
            }

        }
        if (deviceXML.getTransactionResult() != null) {
            if (deviceXML.getTransactionResult().getTransactionID() != null) {
                queryString.append(" and tr.TransactionID = '" + deviceXML.getTransactionResult().getTransactionID() + "' ");
            }
        }
        if (deviceXML.getOptionalCountryID() != null) {
            if (deviceXML.getOptionalCountryID().matches("[0-9]+")) {
                queryString.append(" and tr.DeviceID in(select DeviceID from Device where AddressID in (select AddressID from Addresses where CountryID = " + deviceXML.getOptionalCountryID() + "))");
            } else {
                queryString.append(" and tr.DeviceID in(select DeviceID from Device where AddressID in (select AddressID from Addresses where CountryID in (select CountryID from Country where CountryName like '%" + deviceXML.getOptionalCountryID() + "%')))");
            }
        }
        if (timePeriod != null) {
            if (timePeriod.equalsIgnoreCase("100")) {
                queryString.append(" order by tr.createdDate desc");
                SQLQuery query = session.createSQLQuery(queryString.toString());
                System.out.println(queryString.toString());
                query.addScalar("transactionId", Hibernate.STRING);
                query.addScalar("transactionStartTime", Hibernate.STRING);
                query.addScalar("deviceName", Hibernate.STRING);
                query.addScalar("firstName", Hibernate.STRING);
                query.addScalar("lastName", Hibernate.STRING);
                query.addScalar("userAlias", Hibernate.STRING);
                query.addScalar("amount", Hibernate.STRING);
                query.addScalar("providerName", Hibernate.STRING);
                query.addScalar("currencyId", Hibernate.STRING);
                transactionResultList = query.setMaxResults(100).list();
            } else {
                queryString.append(" and tr.transactionStartTime between '" + gc_diff_time + "' and '" + gc_time + "' order by tr.createdDate desc");
                SQLQuery query = session.createSQLQuery(queryString.toString());
                System.out.println(queryString.toString());
                query.addScalar("transactionId", Hibernate.STRING);
                query.addScalar("transactionStartTime", Hibernate.STRING);
                query.addScalar("deviceName", Hibernate.STRING);
                query.addScalar("firstName", Hibernate.STRING);
                query.addScalar("lastName", Hibernate.STRING);
                query.addScalar("userAlias", Hibernate.STRING);
                query.addScalar("amount", Hibernate.STRING);
                query.addScalar("providerName", Hibernate.STRING);
                query.addScalar("currencyId", Hibernate.STRING);
                transactionResultList = query.list();
            }
        } else {
            queryString.append(" and tr.transactionStartTime between '" + gc_diff_time + "' and '" + gc_time + "' order by tr.createdDate desc");
            SQLQuery query = session.createSQLQuery(queryString.toString());
            System.out.println(queryString.toString());
            query.addScalar("transactionId", Hibernate.STRING);
            query.addScalar("transactionStartTime", Hibernate.STRING);
            query.addScalar("deviceName", Hibernate.STRING);
            query.addScalar("firstName", Hibernate.STRING);
            query.addScalar("lastName", Hibernate.STRING);
            query.addScalar("userAlias", Hibernate.STRING);
            query.addScalar("amount", Hibernate.STRING);
            query.addScalar("providerName", Hibernate.STRING);
            query.addScalar("currencyId", Hibernate.STRING);
            transactionResultList = query.list();
        }
        return transactionResultList;
    }

    List<Object> getTransactionResultForDeviceSalesDetails(se.info24.devicejaxbPost.Device deviceXML, TingcoDevice tingcoDevice, Session ismOperationsSession, Session session) {
        List<Object> transactionResultList = new ArrayList<Object>();
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS ");
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        String gc_time = lFormat.format(gc.getTime());
        String gc_diff_time = null;
        if (deviceXML.getEventDatas().getFromDate() != null && deviceXML.getEventDatas().getToDate() != null) {
            gc_diff_time = deviceXML.getEventDatas().getFromDate();
            gc_time = deviceXML.getEventDatas().getToDate();
        }
        StringBuffer queryString = new StringBuffer();
        if (deviceXML.getGroupID().getGroupName() != null) {
            queryString.append("select tr.deviceId as deviceId, tr.deviceName as deviceName, tr.deviceGroupId as deviceGroupId, count(amount) as amountCount, sum(amount) as amountSum, tr.currencyId as currencyId from ISMOperations.dbo.TransactionResult as tr " +
                    "inner join Groups as g on tr.deviceGroupId = g.groupId " +
                    "and g.groupid in(" + TCMUtil.getGroupTrust(deviceXML.getGroupID().getValue()) + ") ");
            if (TCMUtil.isValidUUID(deviceXML.getGroupID().getGroupName())) {
                queryString.append("inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.groupId = '" + deviceXML.getGroupID().getGroupName() + "' ");
            } else {
                queryString.append("inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.groupName like '%" + deviceXML.getGroupID().getGroupName() + "%' and gts.countryId = " + deviceXML.getCountryID().getValue() + " ");
            }
        } else {
            queryString.append("select tr.deviceId as deviceId, tr.deviceName as deviceName, tr.deviceGroupId as deviceGroupId, count(amount) as amountCount, sum(amount) as amountSum, tr.currencyId as currencyId from ISMOperations.dbo.TransactionResult as tr " +
                    "inner join Groups as g on tr.deviceGroupId = g.groupId " +
                    "and g.groupid in(" + TCMUtil.getGroupTrust(deviceXML.getGroupID().getValue()) + ") ");
        }

        if (deviceXML.getObjectGroupName() != null) {
            queryString.append(" inner join Ogmdevice as ogm on tr.deviceId = ogm.deviceId inner join ObjectGroups as og on ogm.objectGroupId = og.objectGroupId " +
                    " inner join ObjectGroupTranslations as ogt on og.objectGroupId = ogt.objectGroupId and ogt.countryId = " + deviceXML.getCountryID().getValue() + " ");
            if (TCMUtil.isValidUUID(deviceXML.getObjectGroupName())) {
                queryString.append("and ogt.objectGroupId = '" + deviceXML.getObjectGroupName() + "' ");
            } else {
                queryString.append("and ogt.objectGroupName like '%" + deviceXML.getObjectGroupName() + "%' ");
            }
        }

        if (deviceXML.getDeviceName() != null) {
            if (TCMUtil.isValidUUID(deviceXML.getDeviceName())) {
                queryString.append(" and tr.deviceId = '" + deviceXML.getDeviceName() + "' ");
            } else {
                queryString.append(" and tr.deviceName like '%" + deviceXML.getDeviceName() + "%' ");
            }
        }

        queryString.append(" and tr.transactionStartTime between '" + gc_diff_time + "' and '" + gc_time + "' group by tr.deviceId, tr.deviceGroupId, tr.deviceName, tr.currencyId order by tr.deviceName ");
        SQLQuery query = session.createSQLQuery(queryString.toString());
        query.addScalar("deviceId", Hibernate.STRING);
        query.addScalar("deviceName", Hibernate.STRING);
        query.addScalar("deviceGroupId", Hibernate.STRING);
        query.addScalar("amountCount", Hibernate.STRING);
        query.addScalar("amountSum", Hibernate.STRING);
        query.addScalar("currencyId", Hibernate.STRING);
        transactionResultList = query.list();
        return transactionResultList;
    }

    List<Object> getTransactionResultForSalesByProvider(se.info24.devicejaxbPost.Device deviceXML, TingcoDevice tingcoDevice, String loggedinUserGroupId, String timeZoneGMToffset, Session ismOperationsSession, Session session) throws ParseException {
        List<Object> transactionResultList = new ArrayList<Object>();
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        String gc_time = lFormat.format(gc.getTime());
        String gc_diff_time = null;
        if (deviceXML.getEventDatas().getFromDate() != null && deviceXML.getEventDatas().getToDate() != null) {
            gc_diff_time = lFormat.format(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, lFormat.parse(deviceXML.getEventDatas().getFromDate())));
            gc_time = lFormat.format(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, lFormat.parse(deviceXML.getEventDatas().getToDate())));
        }
        StringBuffer queryString = new StringBuffer();

        if (deviceXML.getDeviceGroupName() != null) {
            queryString.append("select tr.providerId as providerId, tr.providerName as providerName, count(tr.amount) as amountCount, sum(tr.amount) as amountSum, tr.currencyId as currencyId from ISMOperations.dbo.TransactionResult as tr " +
                    "inner join groups as g on tr.deviceGroupId = g.groupId and g.groupId in(" + TCMUtil.getGroupTrust(loggedinUserGroupId) + ") and isPurchase = 1");

            if (TCMUtil.isValidUUID(deviceXML.getDeviceGroupName())) {
                queryString.append("inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.groupId = '" + deviceXML.getDeviceGroupName() + "' and gts.countryId = '" + deviceXML.getCountryID().getValue() + "' ");
            } else {
                queryString.append("inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.groupName like '%" + deviceXML.getDeviceGroupName() + "%' and gts.countryId = '" + deviceXML.getCountryID().getValue() + "' ");
            }
        } else {
            queryString.append("select tr.providerId as providerId, tr.providerName as providerName, count(tr.amount) as amountCount, sum(tr.amount) as amountSum, tr.currencyId as currencyId from ISMOperations.dbo.TransactionResult as tr " +
                    "inner join groups as g on tr.deviceGroupId = g.groupId and g.groupId in(" + TCMUtil.getGroupTrust(loggedinUserGroupId) + ") and isPurchase = 1");
        }

        if (deviceXML.getUserGroupName() != null) {
            queryString.append("inner join groups as gr on tr.userGroupId = gr.groupId and gr.groupId in(" + TCMUtil.getGroupTrust(loggedinUserGroupId) + ") ");
            if (TCMUtil.isValidUUID(deviceXML.getUserGroupName())) {
                queryString.append("inner join GroupTranslations as gtss on gr.groupId = gtss.groupId and gtss.groupId = '" + deviceXML.getUserGroupName() + "' and gtss.countryId = '" + deviceXML.getCountryID().getValue() + "' ");
            } else {
                queryString.append("inner join GroupTranslations as gtss on gr.groupId = gtss.groupId and gtss.groupName like '%" + deviceXML.getUserGroupName() + "%' and gtss.countryId = '" + deviceXML.getCountryID().getValue() + "' ");
            }
        }

        if (deviceXML.getObjectGroupName() != null) {
            queryString.append(" inner join Ogmdevice as ogm on tr.deviceId = ogm.deviceId inner join ObjectGroups as og on ogm.objectGroupId = og.objectGroupId " +
                    " inner join ObjectGroupTranslations as ogt on og.objectGroupId = ogt.objectGroupId and ogt.countryId = " + deviceXML.getCountryID().getValue() + " ");
            if (TCMUtil.isValidUUID(deviceXML.getObjectGroupName())) {
                queryString.append("and ogt.objectGroupId = '" + deviceXML.getObjectGroupName() + "' ");
            } else {
                queryString.append("and ogt.objectGroupName like '%" + deviceXML.getObjectGroupName() + "%' ");
            }
        }

        if (deviceXML.getDeviceName() != null) {
            if (TCMUtil.isValidUUID(deviceXML.getDeviceName())) {
                queryString.append(" and tr.deviceId = '" + deviceXML.getDeviceName() + "' ");
            } else {
                queryString.append(" and tr.deviceName like '%" + deviceXML.getDeviceName() + "%' ");
            }
        }

        queryString.append(" and tr.transactionStartTime between '" + gc_diff_time + "' and '" + gc_time + "' group by tr.providerId, tr.providerName, tr.currencyId order by tr.providerName ");
        SQLQuery query = session.createSQLQuery(queryString.toString());
        query.addScalar("providerId", Hibernate.STRING);
        query.addScalar("providerName", Hibernate.STRING);
        query.addScalar("amountCount", Hibernate.STRING);
        query.addScalar("amountSum", Hibernate.STRING);
        query.addScalar("currencyId", Hibernate.STRING);
        transactionResultList = query.list();
        return transactionResultList;
    }

    List<TransactionResult> getTransactionResultbyParentId(String transactionId, Session ismOperationsSession) {
        return ismOperationsSession.getNamedQuery("getTransactionResultByParentId").setString("transactionParentId", transactionId).list();
    }

    List<String> getUserFavoriteDataItemsByCriteria(String userID, Session session) {
        Criteria criteria = session.createCriteria(UserFavoriteDataItems.class, "ufdi");
        criteria.add(Restrictions.eq("ufdi.id.userId", userID));
        criteria.setProjection(Projections.property("ufdi.id.deviceDataItemId"));
        return criteria.list();
    }

    List<UserFavoriteDataItems> getUserFavoriteDataItems(String userID, Session session) {
        return session.getNamedQuery("getUserFavoriteDataItemsByUserID").setString("userId", userID).list();
    }

    public List<String> getdeviceIdsListObject(List<Object> listdevicesObject) {
        List<String> devicessSet = new ArrayList<String>();
        for (Iterator itr = listdevicesObject.iterator(); itr.hasNext();) {
            devicessSet.add(itr.next().toString());
        }
        return devicessSet;
    }

    Object getWidgets2ByWidgetId(String widgetId, Session ismOperationSession) {
        return ismOperationSession.getNamedQuery("getWidgets2ById").setString("widgetId", widgetId).uniqueResult();
    }

    List<String> getdeviceIdsList(List<Device> devicesList) {
        List<String> deviceIdsList = new ArrayList<String>();
        for (se.info24.pojo.Device device : devicesList) {
            deviceIdsList.add(device.getDeviceId());
        }
        return deviceIdsList;
    }

    String saveMediaFiles(se.info24.devicejaxbPost.MediaFiles mediaFile, String loggedinUserId, Date time, Session ismoperationsession) {
        try {
            String sha1Checksum = null;
            String md5Checksum = null;
            if (mediaFile.getMediaFileBlob() != null) {
                TCMUtil tcmUtil = new TCMUtil();
                sha1Checksum = tcmUtil.SHA1checksumCalculation(Base64.decodeBase64(mediaFile.getMediaFileBlob().getBytes()));
                md5Checksum = tcmUtil.MD5checksumCalculation(Base64.decodeBase64(mediaFile.getMediaFileBlob().getBytes()));
            }
            String mediaFileId = UUID.randomUUID().toString();
            MediaFiles mediaFiles = new MediaFiles();
            mediaFiles.setMediaFileId(mediaFileId);
            mediaFiles.setMediaFileName(mediaFile.getMediaFileName());
            mediaFiles.setMediaFileDescription(mediaFile.getMediaFileDescription());
            mediaFiles.setGroupId(mediaFile.getGroupID());
            if (mediaFile.getMediaFileURL() != null) {
                mediaFiles.setMediaFileUrl(mediaFile.getMediaFileURL());
            } else {
                mediaFiles.setMediaFileUrl(null);
            }
            if (mediaFile.getMediaFileBlob() != null) {
                mediaFiles.setMediaFileBlob(Base64.decodeBase64(mediaFile.getMediaFileBlob().getBytes()));
            } else {
                mediaFiles.setMediaFileBlob(null);
            }
            mediaFiles.setMediaFileLength(mediaFile.getMediaFileLength());
            mediaFiles.setMediaFileExtension(mediaFile.getMediaFileExtension());
            if (mediaFile.getMediaFileCountryID() != null) {
                mediaFiles.setMediaFileCountryId(mediaFile.getMediaFileCountryID());
            } else {
                mediaFiles.setMediaFileCountryId(null);
            }
            if (mediaFile.getMediaFileTypeID() != null) {
                mediaFiles.setMediaFileTypeId(mediaFile.getMediaFileTypeID());
            } else {
                mediaFiles.setMediaFileTypeId(null);
            }
            mediaFiles.setChecksumSha1(sha1Checksum);
            mediaFiles.setChecksumMd5(md5Checksum);
            mediaFiles.setCopyright(null);
            mediaFiles.setVersion(1);
            mediaFiles.setPosLatitude(null);
            mediaFiles.setPosLongitude(null);
            mediaFiles.setPosAltitude(null);
            mediaFiles.setPosHeading(null);
            mediaFiles.setTags(null);
            mediaFiles.setLastUpdatedByUserId(loggedinUserId);
            mediaFiles.setCreatedDate(time);
            mediaFiles.setUpdatedDate(time);
            ismoperationsession.save(mediaFiles);

            return mediaFileId;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return "Error occured";
        }
    }

    boolean saveProductVariantDevices(ProductVariantDevices productVariantDevices, Session session) {
        try {
            session.saveOrUpdate(productVariantDevices);
            return true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return false;
        }
    }

    List<String> getEventTypeIdList(se.info24.devicejaxbPost.Device device, Session session) {
        List<String> eventTypeIdList = new ArrayList<String>();
        if (device.getEventTypes() != null) {
            List<EventTypeTranslation> ett = device.getEventTypes().getEventTypeTranslation();
            for (EventTypeTranslation ettr : ett) {
                eventTypeIdList.add(ettr.getEventTypeID());
            }
        } else {
            List<EventTypesInGroups> etigList = getEventTypesInGroups(device.getGroupID().getValue(), session);
            eventTypeIdList = getEventTypeIdList(etigList);
        }
        return eventTypeIdList;
    }

    TingcoDevice buildGetDeviceEventLog(se.info24.devicejaxbPost.Device device, List<String> eventTypeIdList, TingcoDevice tingcoDevice, Session session, Session ismOperationsSession) throws DatatypeConfigurationException {
        try {
            SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            GregorianCalendar gc = new GregorianCalendar();
            UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(device.getLastUpdatedByUserID().getValue(), session);
            if (userTimeZones2 != null) {
                String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                List<EventLog> eventLogList = getEventLogbyIdsList(eventTypeIdList, device.getDeviceID(), device.getEventDatas().getTimePeriod(), device.getEventDatas().getFromDate(), device.getEventDatas().getToDate(), timeZoneGMToffset, ismOperationsSession);

                if (eventLogList.isEmpty()) {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("Data Not Found");
                    return tingcoDevice;
                }
                List<String> eventIdList = new ArrayList<String>();
                for (EventLog eventLog : eventLogList) {
                    eventIdList.add(eventLog.getEventId());
                }

                List<EventDetails> eventDetailses = ismOperationsSession.getNamedQuery("getDeviceEventLog").setParameterList("eventId", eventIdList).list();

                List<EventTypeTranslations> ettList = getEventTypeTranslations(eventTypeIdList, device.getCountryID().getValue(), session);
                if (!eventLogList.isEmpty()) {
                    Devices devices = new Devices();
                    se.info24.devicejaxb.Device dev = new se.info24.devicejaxb.Device();
                    for (EventLog el : eventLogList) {
                        se.info24.devicejaxb.EventLog eventLog = new se.info24.devicejaxb.EventLog();
                        eventLog.setEventID(el.getEventId());
                        eventLog.setEventTypeID(el.getEventTypeId());
                        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, el.getEventTime()));
                        eventLog.setEventTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                        eventLog.setEventTimeTCMV3(lFormat.format(gc.getTime()));
                        for (EventTypeTranslations ett : ettList) {
                            if (ett.getId().getEventTypeId().equalsIgnoreCase(el.getEventTypeId())) {
                                eventLog.setEventType(ett.getEventTypeName());
                                if (ett.getEventTypeDescription() != null) {
                                    eventLog.setEventTypeDescription(ett.getEventTypeDescription());
                                }
                            }
                        }
                        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, el.getCreatedDate()));
                        for (EventDetails eventDetails : eventDetailses) {
                            if (eventDetails.getId().getEventId().equalsIgnoreCase(el.getEventId())) {
                                if (eventDetails.getId().getName().equalsIgnoreCase("ErrorCode")) {
                                    eventLog.setError(eventDetails.getData());
                                } else if (eventDetails.getId().getName().equalsIgnoreCase("VendorErrorCode")) {
                                    eventLog.setErrorCode(eventDetails.getData());
                                }
                            }
                        }
//                        
                        eventLog.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                        eventLog.setCreatedDateTCMV3(lFormat.format(gc.getTime()));
                        dev.getContent().add(eventLog);
                    }
                    devices.getDevice().add(dev);
                    tingcoDevice.setDevices(devices);
                } else {
                    tingcoDevice.getMsgStatus().setResponseCode(-1);
                    tingcoDevice.getMsgStatus().setResponseText("No Records found");
                    return tingcoDevice;
                }
            } else {
                tingcoDevice.getMsgStatus().setResponseCode(-1);
                tingcoDevice.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                return tingcoDevice;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoDevice.getMsgStatus().setResponseCode(-1);
            tingcoDevice.getMsgStatus().setResponseText("Error occured");
        }
        return tingcoDevice;
    }

    boolean copyDeviceSettings(DeviceSettings dss, DeviceSettings ds, int level, Session session, UserSessions2 sessions2) {
        boolean result = false;
        try {
            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            if (ds != null) {
                List<DeviceSettings> dsList = getDeviceSettings(session, ds.getDevice().getDeviceId(), ds.getDeviceSettingId());
                if (!dsList.isEmpty()) {
                    level++;
                    for (DeviceSettings devSettings : dsList) {
                        if (level == 7) {
                            break;
                        } else {
                            DeviceSettings deviceSettings = getDeviceSettingsByIds(dss.getDevice().getDeviceId(), devSettings.getDeviceSettingName(), session);
                            if (deviceSettings != null) {
                                deviceSettings.setDeviceSettingValue(devSettings.getDeviceSettingValue());
                                deviceSettings.setActiveFromDate(gc.getTime());
                                deviceSettings.setUserId(sessions2.getUserId());
                                deviceSettings.setUpdatedDate(gc.getTime());
                                if (devSettings.getObjectSettingTemplateId() != null) {
                                    deviceSettings.setObjectSettingTemplateId(devSettings.getObjectSettingTemplateId());
                                }
                                session.saveOrUpdate(deviceSettings);
                                copyDeviceSettings(deviceSettings, devSettings, level, session, sessions2);
                            } else {
                                deviceSettings = new DeviceSettings();
                                deviceSettings.setDeviceSettingId(UUID.randomUUID().toString());
                                DeviceSettings dses = new DeviceSettings();
                                dses.setDeviceSettingId(dss.getDeviceSettingId());
                                deviceSettings.setDeviceSettings(dses);
                                se.info24.pojo.Device de = new se.info24.pojo.Device();
                                de.setDeviceId(dss.getDevice().getDeviceId());
                                deviceSettings.setDevice(de);
                                deviceSettings.setDeviceSettingName(devSettings.getDeviceSettingName());
                                deviceSettings.setDeviceSettingValue(devSettings.getDeviceSettingValue());
                                if (devSettings.getObjectSettingTemplateId() != null) {
                                    deviceSettings.setObjectSettingTemplateId(devSettings.getObjectSettingTemplateId());
                                }
                                if (devSettings.getSettingDataType().getSettingDataTypeId() != null) {
                                    SettingDataType sdt = new SettingDataType();
                                    sdt.setSettingDataTypeId(devSettings.getSettingDataType().getSettingDataTypeId());
                                    deviceSettings.setSettingDataType(sdt);
                                }
                                deviceSettings.setActiveFromDate(gc.getTime());
                                deviceSettings.setUserId(sessions2.getUserId());
                                deviceSettings.setCreatedDate(gc.getTime());
                                deviceSettings.setUpdatedDate(gc.getTime());
                                session.saveOrUpdate(deviceSettings);
                                copyDeviceSettings(deviceSettings, devSettings, level, session, sessions2);
                            }
                        }
                    }
                }
            }
            result = true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            result = false;
            return result;
        }
        return result;
    }

    void deleteDataItemTranslationsPerDevice(DataItemTranslationsPerDevice dits, Session session) {
        session.delete(dits);
    }

    boolean deleteDeviceSetting(DeviceSettings deviceSettings, Session session) {
        try {
            session.delete(deviceSettings);
            return true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return false;
        }
    }

    public EventTypeTranslations getEventTypeTranslations(String eventTypeId, int countryId, Session session) {
        return (EventTypeTranslations) session.getNamedQuery("getEventTypeTranslationsbyId").setString("eventTypeId", eventTypeId).setInteger("countryId", countryId).uniqueResult();
    }

    public List<EventTypeTranslations> getEventTypeTranslations(List<String> eventTypeIdList, int countryId, Session session) {
        return session.getNamedQuery("getEventTypeTranslationsbyIds").setParameterList("eventTypeId", eventTypeIdList).setInteger("countryId", countryId).list();
    }

    public List<EventTypeTranslations> getEventTypeTranslationsByIdsAndName(List<String> eventTypeIdList, int countryId, String searchString, Session session) {
        return session.getNamedQuery("getEventTypeTranslationsbyIdsAndName").setParameterList("eventTypeId", eventTypeIdList).setInteger("countryId", countryId).setString("searchString", searchString).list();
    }

    public List<EventTypesInGroups> getEventTypesInGroups(String groupId, Session session) {
        return session.getNamedQuery("getEventTypesInGroupsbyGroupId").setString("groupId", groupId).list();
    }

    public List<String> getEventTypeIdList(List<EventTypesInGroups> etigList) {
        List<String> eventTypeIdList = new ArrayList<String>();
        for (EventTypesInGroups etig : etigList) {
            eventTypeIdList.add(etig.getEventTypes().getEventTypeId());
        }
        return eventTypeIdList;
    }

    boolean deleteServiceDeviceSetting(ServiceDeviceSettings serviceDevicesettings, Session session) {
        Transaction tx = session.beginTransaction();
        try {
            session.delete(serviceDevicesettings);
            tx.commit();
            return true;
        } catch (Exception ex) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return false;
        }
    }

    List<DataItemTranslationsPerDevice> getDataItemTranslationsPerDevice(String deviceId, String deviceDataItemId, Session session) {
        return session.getNamedQuery("getDataItemTranslationsbyIds").setString("deviceId", deviceId).setString("deviceDataItemId", deviceDataItemId).list();
    }

    DataItemTranslationsPerDevice getDataItemTranslationsPerDevice(String deviceId, String dataItemId, int countryId, Session session) {
        return (DataItemTranslationsPerDevice) session.getNamedQuery("getDataItemTranslationsbyId").setString("deviceId", deviceId).setString("deviceDataItemId", dataItemId).setInteger("countryId", countryId).uniqueResult();
    }

    List<DataItemTranslationsPerDevice> getDataItemTranslationsPerDeviceById(String deviceId, String deviceDataItemId, Session session) {
        return session.getNamedQuery("getDataItemTranslationsPerDeviceById").setString("deviceId", deviceId).setString("deviceDataItemId", deviceDataItemId).list();
    }

    DeviceDataItemScaling getDeviceDataItemScaling(String deviceId, String dataItemId, Session session) {
        return (DeviceDataItemScaling) session.getNamedQuery("getDeviceDataItemScalingById").setString("deviceId", deviceId).setString("deviceDataItemId", dataItemId).uniqueResult();
    }

    List<DeviceDataitemTranslations> getDeviceDataItemTranslations(String deviceTypeId, int countryId, Session session) {
        Criteria criteria = session.createCriteria(DeviceDataitemTranslations.class, "d");
        criteria.createAlias("d.deviceDataItems", "i", CriteriaSpecification.INNER_JOIN);
        criteria.add(Restrictions.eq("d.id.countryId", countryId));
        criteria.createAlias("i.deviceTypeDataitemses", "t", CriteriaSpecification.INNER_JOIN);
        criteria.add(Restrictions.eq("t.deviceTypes.deviceTypeId", deviceTypeId));
        criteria.addOrder(Order.asc("d.dataItemName"));
        return criteria.list();
    }

    DeviceSettings getDeviceSettings(String deviceId, String deviceSettingId, Session session) {
        return (DeviceSettings) session.getNamedQuery("getDeviceSettingsByDeviceIdandSettingId").setString("deviceSettingId", deviceSettingId).setString("deviceId", deviceId).uniqueResult();
    }

    DeviceSettings getDeviceSettingsBySettingId(String deviceSettingID, Session session) {
        return (DeviceSettings) session.getNamedQuery("getDeviceSettingsBySettingId").setString("deviceSettingId", deviceSettingID).uniqueResult();
    }

    List<DeviceSettings> getDeviceSettingsbyDeviceId(String deviceId, Session session) {
        return session.getNamedQuery("getDeviceSettingsByDeviceId").setString("deviceId", deviceId).list();
    }

    List<EventDetails> getEventDetailsById(String eventId, Session operSession) {
        return operSession.getNamedQuery("getEventDetailsById").setString("eventId", eventId).setMaxResults(100).list();
    }

    List<EventTypeTranslations> getEventTypeTranslationsbyCriteria(String groupId, int countryId, Session session) {
        Criteria criteria = session.createCriteria(EventTypeTranslations.class, "e");
        criteria.createAlias("e.eventTypes", "et", CriteriaSpecification.INNER_JOIN);
        criteria.add(Restrictions.eq("e.id.countryId", countryId));
        criteria.createAlias("et.eventTypesInGroups", "g", CriteriaSpecification.INNER_JOIN);
        criteria.add(Restrictions.eq("g.id.groupId", groupId));
        criteria.addOrder(Order.asc("e.eventTypeName"));
        return criteria.list();
    }

    List<EventTypeTranslations> getEventTypesbyCriteria(String groupId, String deviceTypeId, int countryId, Session session) {
        Criteria criteria = session.createCriteria(EventTypeTranslations.class, "e");
        criteria.createAlias("e.eventTypes", "et", CriteriaSpecification.INNER_JOIN);
        criteria.add(Restrictions.eq("e.id.countryId", countryId));
        criteria.createAlias("et.eventTypesInGroups", "g", CriteriaSpecification.INNER_JOIN);
        criteria.add(Restrictions.eq("g.id.groupId", groupId));
        criteria.createAlias("et.eventTypesInTypes", "t", CriteriaSpecification.INNER_JOIN);
        criteria.add(Restrictions.eq("t.id.itemId", deviceTypeId));
        criteria.addOrder(Order.asc("e.eventTypeName"));
        criteria.addOrder(Order.desc("et.createdDate"));
        criteria.setMaxResults(100);
        return criteria.list();
    }

    List<ObjectSettingPackageTranslations> getObjectSettingPackageTranslations(String deviceTypeId, String groupId, int countryId, Session session) {
        Criteria criteria = session.createCriteria(ObjectSettingPackageTranslations.class, "t");
        criteria.createAlias("t.objectSettingPackages", "o", CriteriaSpecification.INNER_JOIN);
        criteria.add(Restrictions.eq("t.id.countryId", countryId));
        criteria.createAlias("o.groupObjectSettingPackageses", "g", CriteriaSpecification.INNER_JOIN);
        criteria.add(Restrictions.eq("g.id.groupId", groupId));
        criteria.add(Restrictions.eq("o.objectTypeId", deviceTypeId));
        criteria.addOrder(Order.asc("t.packageName"));
        return criteria.list();
    }

    List<ObjectSettingTemplates> getObjectSettingTemplate(List<String> objectSettingTemplateIdList, Session session) {
        return session.getNamedQuery("getObjectSettingTemplates").setParameterList("objectSettingTemplateId", objectSettingTemplateIdList).list();
    }

    ObjectSettingTemplates getObjectSettingTemplatesByName(String deviceSettingName, Session session) {
        return (ObjectSettingTemplates) session.getNamedQuery("getObjectSettingTemplatesByName").setString("objectSettingName", deviceSettingName).uniqueResult();
    }

    public ProductVariantDevices getProductVariantDevices(String deviceId, String productVariantId, Session session) {
        return (ProductVariantDevices) session.getNamedQuery("getProductVariantDevicesByIds").setString("productVariantId", productVariantId).setString("deviceId", deviceId).uniqueResult();
    }

    List<ServiceDeviceSettingTemplates> getServiceDeviceSettingTemplatesbyIds(String serviceId, String deviceTypeId, Session session) {
        return session.getNamedQuery("getServiceDeviceSettingTemplatesbyIds").setString("serviceId", serviceId).setString("DeviceTypeId", deviceTypeId).list();
    }

    ServiceDeviceSettings getServiceDeviceSettings(String serviceDeviceSettingId, Session session) {
        return (ServiceDeviceSettings) session.getNamedQuery("getServiceDeviceSettingsBySettingId").setString("serviceDeviceSettingId", serviceDeviceSettingId).uniqueResult();
    }

    List<ObjectTypeSettingTemplates> getObjectTypeSettingTemplatesByTypeId(String deviceTypeId, Session session) {
        return session.getNamedQuery("getObjectTypeSettingTemplatesByTypeId").setString("objectTypeId", deviceTypeId).list();
    }

    ServiceDeviceSettings getServiceDeviceSettingsByIds(String serviceDeviceSettingId, String objectSettingTemplateId, Session session) {
        return (ServiceDeviceSettings) session.getNamedQuery("getServiceDeviceSettingsByIds").setString("serviceDeviceSettingId", serviceDeviceSettingId).setString("objectSettingTemplateId", objectSettingTemplateId).uniqueResult();
    }

    ServiceDeviceSettings getServiceDeviceSettingsBySubscriptionIdandName(String serviceDeviceSettingName, String serviceDeviceSubscriptionId, Session session) {
        return (ServiceDeviceSettings) session.getNamedQuery("getServiceDeviceSettingsBySubscriptionIdandName").setString("serviceDeviceSettingName", serviceDeviceSettingName).setString("serviceDeviceSubscriptionId", serviceDeviceSubscriptionId).uniqueResult();
    }

    public SettingDataType getSettingDataTypeById(String settingDataTypeId, Session session) {
        return (SettingDataType) session.getNamedQuery("getSettingDataTypeById").setString("settingDataTypeId", settingDataTypeId).uniqueResult();
    }

    void insertObjectSettingTemplates(String deviceID, String objectSettingTemplateId, String deviceSettingID, Session session, UserSessions2 sessions2) {
        List<DeviceSettings> dsList = getDeviceSettings(session, deviceID, deviceSettingID);
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        if (!dsList.isEmpty()) {
            for (DeviceSettings ds : dsList) {
                ObjectSettingTemplates ost = getObjectSettingTemplatesByName(ds.getDeviceSettingName(), session);
                if (ost != null) {
                    ost.setObjectSettingTemplateParentId(objectSettingTemplateId);
                    ost.setObjectSettingDefaultValue(ds.getDeviceSettingValue());
                    ost.setUpdatedDate(gc.getTime());
                    saveObjectSettingTemplates(ost, session);
                    insertObjectSettingTemplates(deviceID, ost.getObjectSettingTemplateId(), ds.getDeviceSettingId(), session, sessions2);
                } else {
                    ost = new ObjectSettingTemplates();
                    ost.setObjectSettingTemplateId(UUID.randomUUID().toString());
                    ost.setObjectSettingTemplateParentId(objectSettingTemplateId);
                    ost.setObjectSettingName(ds.getDeviceSettingName());
                    ost.setObjectSettingDefaultValue(ds.getDeviceSettingValue());
                    ost.setObjectSettingDescription(ds.getDeviceSettingName());
                    ost.setSettingRequired(0);
                    if (ds.getSettingDataType() != null) {
                        SettingDataType sdt = new SettingDataType();
                        sdt.setSettingDataTypeId(ds.getSettingDataType().getSettingDataTypeId());
                        ost.setSettingDataType(sdt);
                    }
                    ost.setLastUpdatedByUserId(sessions2.getUserId());
                    ost.setCreatedDate(gc.getTime());
                    ost.setUpdatedDate(gc.getTime());
                    saveObjectSettingTemplates(ost, session);
                    insertObjectSettingTemplates(deviceID, ost.getObjectSettingTemplateId(), ds.getDeviceSettingId(), session, sessions2);
                }
            }
        }
    }

    void saveDataItemTranslationsPerDevice(DataItemTranslationsPerDevice ditranspd, Session session) {
        session.saveOrUpdate(ditranspd);
    }

    void saveDeviceDataItemScaling(DeviceDataItemScaling deviceDataItemScaling, Session session) {
        session.saveOrUpdate(deviceDataItemScaling);
    }

    boolean saveDeviceSettings(DeviceSettings dss, Session session) {
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(dss);
            tx.commit();
            return true;
        } catch (Exception ex) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return false;
        }
    }

    void saveGroupObjectSettingPackages(GroupObjectSettingPackages gosp, Session session) {
        session.saveOrUpdate(gosp);
    }

    boolean saveObjectSettingPackageTemplates(ObjectSettingPackageTemplates ospt, Session session) {
        session.saveOrUpdate(ospt);
        return true;
    }

    void saveObjectSettingPackageTranslations(ObjectSettingPackageTranslations osptrans, Session session) {
        session.saveOrUpdate(osptrans);
    }

    boolean saveObjectSettingPackages(ObjectSettingPackages osp, Session session) {
        session.saveOrUpdate(osp);
        return true;
    }

    boolean saveObjectSettingTemplates(ObjectSettingTemplates ost, Session session) {
        session.saveOrUpdate(ost);
        return true;
    }

    boolean saveObjectTypeSettingTemplates(ObjectTypeSettingTemplates otst, Session session) {
        session.saveOrUpdate(otst);
        return true;
    }

    boolean saveRecurringPurchases(RecurringPurchases recurringPurchases, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(recurringPurchases);
            tx.commit();
            return true;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }

    }

    boolean saveServiceDeviceSetting(ServiceDeviceSettings serviceDeviceSettings, Session session) {
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(serviceDeviceSettings);
            tx.commit();
            return true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tx.rollback();
            return false;
        }
    }

    boolean saveServiceDeviceSettings(ServiceDeviceSettings sds, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(sds);
            tx.commit();
            return true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tx.rollback();
            return false;
        }
    }

    boolean saveServiceDeviceSettings(Session session, ServiceDeviceSettings sds) {
        try {
            session.saveOrUpdate(sds);
            return true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return false;
        }
    }

    public Set<String> getDeviceIdsListObject(List<Object> deviceList) {
        Set<String> deviceIdSet = new HashSet<String>();
        for (Iterator itr = deviceList.iterator(); itr.hasNext();) {

            Object[] row = (Object[]) itr.next();
            for (int i = 0; i < row.length; i++) {
                if (i % 2 == 0) {
                    if (row[i] != null) {
                        deviceIdSet.add(row[i].toString());

                    }
                    i += 5;
                }

            }
        }
        return deviceIdSet;
    }

    public Set<String> getDeviceIdsList(List<Device> deviceList) {
        Set<String> deviceIdSet = new HashSet<String>();
        if (!deviceList.isEmpty()) {
            for (Device d : deviceList) {
                deviceIdSet.add(d.getDeviceId());
            }
        }
        return deviceIdSet;
    }

    public Set<String> getDeviceLinkIdsList(List<DeviceLinks> deviceLinksList) {
        Set<String> deviceIdSet = new HashSet<String>();
        if (!deviceLinksList.isEmpty()) {
            for (DeviceLinks d : deviceLinksList) {
                deviceIdSet.add(d.getId().getItrustDeviceId());
            }
        }
        return deviceIdSet;
    }

    public List<String> getCountryDeviceIdsList(int countryId, Session session) {
        Criteria criteria = session.createCriteria(Device.class, "d");
        criteria.createAlias("d.addresses", "add", CriteriaSpecification.INNER_JOIN);
        criteria.createAlias("add.country", "co", CriteriaSpecification.INNER_JOIN);
        if (String.valueOf(countryId).matches("[0-9]+")) {
            criteria.add(Restrictions.eq("co.countryId", countryId));
        } else {
            criteria.add(Restrictions.eq("co.countryName", countryId));
        }
        criteria.setProjection(Projections.distinct(Projections.property("d.deviceId")));
        return criteria.list();
    }

    public List<Device> getDeviceTypesDeviceIdsList(String deviceTypeId, Session session) {
        Criteria criteria = session.createCriteria(Device.class, "d");
        criteria.createAlias("d.deviceTypes", "dt", CriteriaSpecification.INNER_JOIN);
        if (TCMUtil.isValidUUID(deviceTypeId)) {
            criteria.add(Restrictions.eq("dt.deviceTypeId", deviceTypeId));
        } else {
            criteria.add(Restrictions.ilike("dt.deviceTypeName", deviceTypeId, MatchMode.ANYWHERE));
        }
        return criteria.list();
    }

    public List<String> getAgreementDeviceIdsList(String agreementId, Session session) {
        Criteria criteria = session.createCriteria(Device.class, "d");
        criteria.createAlias("d.agreements", "ag", CriteriaSpecification.INNER_JOIN);
        criteria.add(Restrictions.eq("ag.agreementId", agreementId));
        criteria.setProjection(Projections.distinct(Projections.property("d.deviceId")));
        return criteria.list();
    }

    private List<DeviceSettings> getDeviceSettings(Session session, String deviceID, String deviceSettingID) {
        return session.getNamedQuery("getDeviceSettingsByDeviceIdandSettingParentId").setString("deviceSettingParentId", deviceSettingID).setString("deviceId", deviceID).list();
    }

    public List<DeviceSettings> getDeviceSettingsById(String objectSettingTemplateId, String deviceId, Session session) {
        return session.getNamedQuery("getDeviceSettingsById").setString("objectSettingTemplateId", objectSettingTemplateId).setString("deviceId", deviceId).list();
    }

    public List<SettingDataType> getSettingDataType(Session session) {
        return session.getNamedQuery("getAllSettingDataTypes").list();
    }

    public boolean traverseDeviceSettings(String deviceId, ObjectSettingTemplates ost, String userId, int level, Session session) {
        //int level=1;
        boolean settingResult = false;
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        try {
            if (ost != null) {
                String parentId = ost.getObjectSettingTemplateId();
                List<ObjectSettingTemplates> osList = session.getNamedQuery("getObjectSettingTemplatesByParentId").setString("objectSettingTemplateParentId", parentId).list();
                if (!osList.isEmpty()) {
                    level++;
                    for (ObjectSettingTemplates osts : osList) {
                        if (level == 7) {
                            break;
                        } else {
                            List<DeviceSettings> ds = getDeviceSettingsById(osts.getObjectSettingTemplateParentId(), deviceId, session);
                            DeviceSettings dss = getDeviceSettingsByIds(deviceId, osts.getObjectSettingName(), session);

                            if (dss != null) {
                                dss.setDeviceSettingValue(osts.getObjectSettingDefaultValue());
                                dss.setActiveFromDate(gc.getTime());
                                dss.setUserId(userId);
                                dss.setUpdatedDate(gc.getTime());
                                dss.setObjectSettingTemplateId(osts.getObjectSettingTemplateId());
                                session.saveOrUpdate(dss);
                                traverseDeviceSettings(deviceId, osts, userId, level, session);
                            } else {
                                dss = new DeviceSettings();
                                dss.setDeviceSettingId(UUID.randomUUID().toString());
                                DeviceSettings devSettings = new DeviceSettings();
                                devSettings.setDeviceSettingId(ds.get(0).getDeviceSettingId());
                                dss.setDeviceSettings(devSettings);
                                Device device = new Device();
                                device.setDeviceId(deviceId);
                                dss.setDevice(device);
                                dss.setDeviceSettingName(osts.getObjectSettingName());
                                dss.setDeviceSettingValue(osts.getObjectSettingDefaultValue());
                                SettingDataType sdt = new SettingDataType();
                                sdt.setSettingDataTypeId(osts.getSettingDataType().getSettingDataTypeId());
                                dss.setSettingDataType(sdt);
                                dss.setActiveFromDate(gc.getTime());
                                dss.setUserId(userId);
                                dss.setCreatedDate(gc.getTime());
                                dss.setUpdatedDate(gc.getTime());
                                dss.setObjectSettingTemplateId(osts.getObjectSettingTemplateId());
                                session.saveOrUpdate(dss);
                                traverseDeviceSettings(deviceId, osts, userId, level, session);
                            }
                        }
                    }
                }
            }
            settingResult = true;
            return settingResult;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            settingResult = false;
            return settingResult;
        }
    }

    public List<ObjectSettingTemplates> getObjectSettingTemplatesByParentId(String objectSettingTemplateParentId, Session session) {
        return session.getNamedQuery("getObjectSettingTemplatesByParentId").setString("objectSettingTemplateParentId", objectSettingTemplateParentId).list();
    }

    public List<EventLog> getEventLogbyIdsList(List<String> eventTypeIdList, String deviceId, String timePeriod, String fromDate, String toDate, String timeZoneGMToffset, Session ismOperationsSession) throws ParseException {
        List<EventLog> eventLogList = new ArrayList<EventLog>();
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        GregorianCalendar gc = new GregorianCalendar();
        String gc_time = lFormat.format(gc.getTime());
        GregorianCalendar gc_diff = new GregorianCalendar();
        gc_diff.setTime(gc.getTime());
        if (timePeriod != null) {
            if (timePeriod.equalsIgnoreCase("100")) {
                eventLogList = ismOperationsSession.getNamedQuery("getEventLogbyEventTypeIdandSourceIdTop100").setParameterList("eventTypeId", eventTypeIdList).setString("sourceId", deviceId).setMaxResults(100).list();
            } else if (timePeriod.equalsIgnoreCase("hour")) {
                gc_diff.add(GregorianCalendar.HOUR, -1);
                long gc_diff_Millis = gc_diff.getTimeInMillis();
                Date gc_diff_lDate = new Date(gc_diff_Millis);
                String gc_diff_time = lFormat.format(gc_diff_lDate);
                eventLogList = ismOperationsSession.getNamedQuery("getEventLogbyEventTypeIdandSourceId").setParameterList("eventTypeId", eventTypeIdList).setString("sourceId", deviceId).setString("fromDate", gc_diff_time).setString("toDate", gc_time).setMaxResults(200).list();
            } else if (timePeriod.equalsIgnoreCase("day")) {
                gc_diff.add(GregorianCalendar.DATE, -1);
                long gc_diff_Millis = gc_diff.getTimeInMillis();
                Date gc_diff_lDate = new Date(gc_diff_Millis);
                String gc_diff_time = lFormat.format(gc_diff_lDate);
                eventLogList = ismOperationsSession.getNamedQuery("getEventLogbyEventTypeIdandSourceId").setParameterList("eventTypeId", eventTypeIdList).setString("sourceId", deviceId).setString("fromDate", gc_diff_time).setString("toDate", gc_time).setMaxResults(200).list();
            } else if (timePeriod.equalsIgnoreCase("week")) {
                gc_diff.add(GregorianCalendar.DATE, -7);
                long gc_diff_Millis = gc_diff.getTimeInMillis();
                Date gc_diff_lDate = new Date(gc_diff_Millis);
                String gc_diff_time = lFormat.format(gc_diff_lDate);
                eventLogList = ismOperationsSession.getNamedQuery("getEventLogbyEventTypeIdandSourceId").setParameterList("eventTypeId", eventTypeIdList).setString("sourceId", deviceId).setString("fromDate", gc_diff_time).setString("toDate", gc_time).setMaxResults(200).list();
            } else if (timePeriod.equalsIgnoreCase("month")) {
                gc_diff.add(GregorianCalendar.MONTH, -1);
                long gc_diff_Millis = gc_diff.getTimeInMillis();
                Date gc_diff_lDate = new Date(gc_diff_Millis);
                String gc_diff_time = lFormat.format(gc_diff_lDate);
                eventLogList = ismOperationsSession.getNamedQuery("getEventLogbyEventTypeIdandSourceId").setParameterList("eventTypeId", eventTypeIdList).setString("sourceId", deviceId).setString("fromDate", gc_diff_time).setString("toDate", gc_time).setMaxResults(200).list();
            }
        } else if (fromDate != null && toDate != null) {
            gc.setTime(lFormat.parse(fromDate));
            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
            String fromDateString = lFormat.format(gc.getTime());
            gc.setTime(lFormat.parse(toDate));
            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, gc.getTime()));
            String toDateString = lFormat.format(gc.getTime());
            eventLogList = ismOperationsSession.getNamedQuery("getEventLogbyEventTypeIdandSourceId").setParameterList("eventTypeId", eventTypeIdList).setString("sourceId", deviceId).setString("fromDate", fromDateString).setString("toDate", toDateString).setMaxResults(200).list();
        }
        return eventLogList;
    }

    boolean saveObjectSettingPackageMemberships(ObjectSettingPackageMemberships ospm, Session session) {
        try {
            session.saveOrUpdate(ospm);
            return true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return false;
        }
    }

    boolean saveServiceDeviceSubscriptions(ServiceDeviceSubscriptions sds, Session session) {
        try {
            session.saveOrUpdate(sds);
            return true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return false;
        }
    }

    public List<String> getObjectDeviceIdsList(Set<String> groupIdsList, String objectGroupName, int countryId, Session session) {
        GroupDAO gd = new GroupDAO();
        List<String> deviceId = new ArrayList<String>();
        List<ObjectGroups> og = gd.getObjectGroupsByGroupId(groupIdsList, session);
        List<String> objectGroupid = new ArrayList<String>();
        List<Ogmdevice> ogmdevices = new ArrayList<Ogmdevice>();
        if (!og.isEmpty()) {
            for (ObjectGroups objectGroups : og) {
                objectGroupid.add(objectGroups.getObjectGroupId());
            }

            Set<String> ObjectGroupdeviceid = new HashSet<String>();
            if (TCMUtil.isValidUUID(objectGroupName)) {
                if (objectGroupid.contains(objectGroupName)) {
                    ObjectGroupdeviceid.add(objectGroupName);
                }
            } else {
                List<ObjectGroupTranslations> ogt = getObjectGroupTranslationsByObjectGroupName(session, objectGroupid, objectGroupName);
                if (!ogt.isEmpty()) {
                    for (ObjectGroupTranslations objectGroupTranslations : ogt) {
                        ObjectGroupdeviceid.add(objectGroupTranslations.getId().getObjectGroupId());
                    }
                } else {
                    return deviceId;
                }
            }
            if (!ObjectGroupdeviceid.isEmpty()) {
                ogmdevices = getAllOgmdeviceById(session, ObjectGroupdeviceid);
            } else {
                return deviceId;
            }
        } else {
            return deviceId;
        }

        for (Ogmdevice ogmdevice : ogmdevices) {
            deviceId.add(ogmdevice.getId().getDeviceId());
        }
        return deviceId;
    }

    private boolean insertDeviceSettings(Device device, String userId, ObjectSettingTemplates template, String deviceSettingId, String deviceSettingParentId, Session session) {
        GregorianCalendar gc = new GregorianCalendar();
        try {
            DeviceSettings devSetting = new DeviceSettings();
            devSetting.setDeviceSettingId(deviceSettingId);
            if (deviceSettingParentId != null) {
                DeviceSettings des = new DeviceSettings();
                des.setDeviceSettingId(deviceSettingParentId);
                devSetting.setDeviceSettings(des);
            }
            devSetting.setDeviceSettingName(template.getObjectSettingName());
            if (template.getObjectSettingDefaultValue() != null) {
                devSetting.setDeviceSettingValue(template.getObjectSettingDefaultValue());
            } else {
                devSetting.setDeviceSettingValue("ObjectSettingDefaultValue is not Available in ObjectSettingTemplates");
            }
            devSetting.setActiveFromDate(gc.getTime());
            devSetting.setSettingDataType(template.getSettingDataType());
            devSetting.setUserId(userId);
            devSetting.setCreatedDate(gc.getTime());
            devSetting.setUpdatedDate(gc.getTime());
            devSetting.setDevice(device);
            devSetting.setObjectSettingTemplateId(template.getObjectSettingTemplateId());
            session.saveOrUpdate(devSetting);
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    private Set<String> traverseAgreementsDownwards(String agreementId, Set<String> agreementIdsList, int level, Session session) {
        List<Agreements> agreementsList = getAgreementsByAgreementParentId(agreementId, session);
        if (!agreementsList.isEmpty()) {
            level++;
            for (Agreements agreements : agreementsList) {
                if (level == 10) {
                    break;
                } else {
                    agreementIdsList.add(agreements.getAgreementId());
                    traverseAgreementsDownwards(agreements.getAgreementId(), agreementIdsList, level, session);
                }
            }
        }
        return agreementIdsList;
    }

    private boolean traverseDeviceSetting(Device device, String userId, ObjectSettingTemplates osts, String deviceSettingParentId, Session session) {
        boolean settingResult = false;
        try {
            if (osts != null) {
                String parentId = osts.getObjectSettingTemplateId();
                List<ObjectSettingTemplates> osList = getObjectSettingTemplatesByParentId(parentId, session);
                if (!osList.isEmpty()) {
                    for (ObjectSettingTemplates os1 : osList) {
                        String deviceSettingId = UUID.randomUUID().toString();
                        settingResult = insertDeviceSettings(device, userId, os1, deviceSettingId, deviceSettingParentId, session);
                        traverseDeviceSetting(device, userId, os1, deviceSettingId, session);
                    }
                }
            }
            return settingResult;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return settingResult;
        }
    }

    boolean deleteObjectContactMemberships(List<ObjectContactMemberships> objectContactMembershipsList, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (ObjectContactMemberships ocm : objectContactMembershipsList) {
                session.delete(ocm);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public Addresses getAddress(String addressId, Session session) {
        return (Addresses) session.getNamedQuery("getAddressById").setString("addressID", addressId).uniqueResult();
    }

    public List<ServiceDeviceSettings> getServiceDeviceSettingsBySubscriptionId(Session session, String serviceDeviceSubscriptionId) {
        return session.getNamedQuery("getServiceDeviceSettings").setString("serviceDeviceSubscriptionId", serviceDeviceSubscriptionId).list();
    }

    boolean deleteServiceDeviceSubscription(ServiceDeviceSubscriptions serviceDeviceSubscriptions, Session session) {
        Transaction tx = null;
        boolean result = false;
        try {
            if (serviceDeviceSubscriptions != null) {
                List<ServiceDeviceSettings> sds = getServiceDeviceSettingsBySubscriptionId(session, serviceDeviceSubscriptions.getServiceDeviceSubscriptionId());
                if (!sds.isEmpty()) {
                    for (ServiceDeviceSettings sd : sds) {
                        tx = session.beginTransaction();
                        session.delete(sd);
                        tx.commit();
                    }
                    result = true;
                }
                tx = session.beginTransaction();
                session.delete(serviceDeviceSubscriptions);
                tx.commit();
                result = true;
            }
            return result;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tx.rollback();
            return false;
        }
    }

    List<DeviceActivationSchedules> getDeviceActivationID(Session session, String deviceDataItemId, String deviceId, Session ismSession) {
        List<DeviceActivationSchedules> deviceActiveList = new ArrayList<DeviceActivationSchedules>();
        List<DeviceDataItems> ddiList = ismSession.getNamedQuery("getDeviceDataItemsByItemId").setString("id", deviceDataItemId).list();
        if (!ddiList.isEmpty()) {
            for (DeviceDataItems ddi : ddiList) {
                deviceActiveList = session.getNamedQuery("getDeviceActivationSchedulesByDeviceIdAndFieldName").setString("deviceId", deviceId).setString("fieldName", ddi.getDeviceDataFieldName()).list();
            }
        }
        return deviceActiveList;
    }

    public List<Device> getDeviceByGroupId(Set<String> groupIdList, Session session, int maxResult) {
        if (maxResult == 0) {
            return session.getNamedQuery("getDeviceByGroupID").setParameterList("groupId", groupIdList).list();
        } else {
            return session.getNamedQuery("getDeviceByGroupID").setParameterList("groupId", groupIdList).setMaxResults(maxResult).list();
        }

    }

    public List<DeviceOperationsStatusTranslation> getDeviceOperationsStatusTrans(Session session, int countryId) {
        return session.getNamedQuery("getDeviceOperationsStatusTransbyCountryId").setInteger("countryId", countryId).list();
    }

    public List<Device> getDeviceByGroupIdNDeviceName2(String groupId, String deviceName2, Session session) {
        return session.getNamedQuery("getDeviceByGroupIdNDeviceName2").setParameter("groupId", groupId).setParameter("deviceName2", deviceName2).list();
    }

    DevicePendingDelete getDevicePendingDelete(String deviceId, Session session) {
        List<DevicePendingDelete> devicePendingDeleteList = session.getNamedQuery("getDevicePendingDelete").setString("deviceId", deviceId).list();
        DevicePendingDelete devicePendingDelete = null;
        if (!devicePendingDeleteList.isEmpty()) {
            devicePendingDelete = devicePendingDeleteList.get(0);
        }
        return devicePendingDelete;
    }

    public List<DeviceTypeCommands> getDeviceTypeCommands(String deviceTypeId, Session session) {
        return session.getNamedQuery("getDeviceTypeCommandsByTypeId").setString("deviceTypeId", deviceTypeId).list();
    }

    public Object getDeviceTypeCommandsById(String deviceTypeCommandId, Session session) {
        return session.getNamedQuery("getDeviceTypeCommands").setString("deviceTypeCommandId", deviceTypeCommandId).uniqueResult();
    }

    public List<DeviceTypeCommands> getDeviceTypeCommandByIDS(Set<String> deviceTypeCommandId, Session session) {
        return session.getNamedQuery("getDeviceTypeCommandByIDS").setParameterList("deviceTypeCommandId", deviceTypeCommandId).list();
    }

    List<GroupObjectSettingPackages> getGroupObjectSettingPackages(Set<String> groupIdSet, Session session) {
        return session.getNamedQuery("getGroupObjectSettingPackagesByGroupIdList").setParameterList("groupId", groupIdSet).list();
    }

    List<ObjectContacts> getObjectContact(String groupId, int countryId, String objectId, Session session) {
        List<ObjectContactMemberships> objectContactMemList = session.getNamedQuery("getObjectContactMemberships").setString("objectId", objectId).list();
        List<String> objectContactIdList = new ArrayList<String>();
        List<ObjectContacts> objectContactsList = new ArrayList<ObjectContacts>();
        if (!objectContactMemList.isEmpty()) {
            for (ObjectContactMemberships ocm : objectContactMemList) {
                objectContactIdList.add(ocm.getId().getObjectContactId());
            }
            objectContactsList = session.getNamedQuery("getObjectContactsDetails").setParameterList("objectContactId", objectContactIdList).setString("groupId", groupId).setInteger("countryId", countryId).list();
        }
        return objectContactsList;
    }

    public List<ObjectContactMemberships> getObjectContactMemberships(String deviceId, Session session) {
        return session.getNamedQuery("getObjectContactMembershipsByObjectId").setString("objectId", deviceId).list();
    }

    public List<ObjectContactMemberships> getObjectContactMembershipsbyIdandIsDefault(String objectId, Session session) {
        return session.getNamedQuery("getObjectContactMemberships").setString("objectId", objectId).list();
    }

    public List<ContainerDevices> getContainerDevicesByContainerIdOrderBy(String containerId, Session session) {
        return session.getNamedQuery("getContainerDevicesByContainerIdOrderBy").setString("containerId", containerId).list();
    }

    public List<ObjectContacts> getObjectContacts(List<String> objectContactIdList, Session session) {
        List<ObjectContacts> objectContactList = new ArrayList<ObjectContacts>();
        if (!objectContactIdList.isEmpty()) {
            objectContactList = session.getNamedQuery("getObjectContacts").setParameterList("objectContactId", objectContactIdList).list();
        }
        return objectContactList;
    }

    List<String> getObjectSettingPackageIdList(List<GroupObjectSettingPackages> gospList) {
        List<String> objectSettingPackageIdList = new ArrayList<String>();
        for (GroupObjectSettingPackages gosp : gospList) {
            objectSettingPackageIdList.add(gosp.getId().getObjectSettingPackageId());
        }
        return objectSettingPackageIdList;
    }

    List<ObjectSettingPackageTranslations> getObjectSettingPackageTranslations(List<String> objectSettingPackageIdsList, int countryId, Session session) {
        return session.getNamedQuery("getObjectSettingPackageTranslationsById").setParameterList("objectSettingPackageId", objectSettingPackageIdsList).setInteger("countryId", countryId).list();
    }

    List<ObjectSettingPackages> getObjectSettingPackagesByIds(List<String> objectSettingPackageIdList, String deviceTypeId, Session session) {
        return session.getNamedQuery("getObjectSettingPackagesByIds").setParameterList("objectSettingPackageId", objectSettingPackageIdList).setString("objectTypeId", deviceTypeId).setMaxResults(50).list();
    }

    ServiceDeviceSubscriptions getServiceDeviceSubscriptions(String serviceDeviceSubscriptionId, Session session) {
        return (ServiceDeviceSubscriptions) session.getNamedQuery("getServiceDeviceSubscriptions").setString("serviceDeviceSubscriptionId", serviceDeviceSubscriptionId).uniqueResult();
    }

    ServiceDeviceSubscriptions getServiceDeviceSubscriptions(String serviceId, String deviceId, String serviceClientLoginId, Session session) {
        return (ServiceDeviceSubscriptions) session.getNamedQuery("getServiceDeviceSubscriptionsById").setString("serviceId", serviceId).setString("deviceId", deviceId).setString("serviceClientLoginId", serviceClientLoginId).uniqueResult();
    }

    ServiceDeviceSubscriptions getServiceDeviceSubscriptions(List<String> serviceIdsList, String deviceId, String serviceClientLoginId, String agreementId, Session session) {
        return (ServiceDeviceSubscriptions) session.getNamedQuery("getServiceDeviceSubscriptionsByIds").setParameterList("serviceId", serviceIdsList).setString("deviceId", deviceId).setString("serviceClientLoginId", serviceClientLoginId).setString("agreementId", agreementId).uniqueResult();
    }

    List<DeviceOperationsMember> getdeviceOperationsMember(Session session, String deviceId) {
        return session.getNamedQuery("getDeviceOperationsMember").setString("deviceId", deviceId).list();
    }

    List<ObjectSettingPackageMemberships> getObjectSettingPackageMemberships(Session session, String deviceId) {
        return session.getNamedQuery("getObjectSettingPackageMembershipsbyobjectId").setString("objectId", deviceId).list();
    }

    public Object getitemconnectionStatus(String deviceId, Session operationsSession) {
        return operationsSession.getNamedQuery("getItemConnectionStatusById").setString("itemId", deviceId).uniqueResult();
    }

    boolean insertObjectContactMemberships(String objectId, String objectcontactId, Session session, String UserId) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ObjectContactMemberships ocm = new ObjectContactMemberships();
            ocm.setId(new ObjectContactMembershipsId(objectId, objectcontactId));
            ocm.setIsDefaultContact(1);
            ocm.setLastUpdatedByUserId(UserId);
            GregorianCalendar gc = new GregorianCalendar();
            ocm.setCreatedDate(gc.getTime());
            ocm.setUpdatedDate(gc.getTime());
            session.saveOrUpdate(ocm);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    boolean insertServiceDeviceSubscriptions(String serviceId, String deviceId, String serviceClientLoginId, Device device, String userId, Session session) {
        Transaction tx = session.beginTransaction();
        boolean result = false;
        boolean settingsresult = false;
        ServiceDAO serviceDAO = new ServiceDAO();
        try {
            ServiceDeviceSubscriptions sds = new ServiceDeviceSubscriptions();
            String serviceDeviceSubscriptionId = UUID.randomUUID().toString();
            sds.setServiceDeviceSubscriptionId(serviceDeviceSubscriptionId);
            Object obj = serviceDAO.getServicesbyServiceId(session, serviceId);
            if (obj != null) {
                Services services = (Services) obj;
                services.setServiceId(serviceId);
                sds.setServices(services);
            } else {
                return false;
            }

            Device devices = getDeviceById(deviceId, session);
            if (devices != null) {
                devices.setDeviceId(deviceId);
                sds.setDevice(devices);
                if (device.getAgreements() != null) {
                    Agreements agreements = new Agreements();
                    agreements.setAgreementId(device.getAgreements().getAgreementId());
                    sds.setAgreements(agreements);
                }
            } else {
                return false;
            }

            Object sclObject = getServiceClientLoginsById(serviceClientLoginId, session);
            if (sclObject != null) {
                ServiceClientLogins scl = (ServiceClientLogins) sclObject;
                scl.setServiceClientLoginId(serviceClientLoginId);
                sds.setServiceClientLogins(scl);
            } else {
                return false;
            }

            sds.setSubscriptionEnabled(1);
            sds.setUserId(userId);
            GregorianCalendar gc = new GregorianCalendar();
            sds.setCreatedDate(gc.getTime());
            sds.setUpdatedDate(gc.getTime());
            session.saveOrUpdate(sds);
            result = true;
            tx.commit();
            session.flush();
            session.clear();
            tx = session.beginTransaction();
            if (result) {
                List<ServiceDeviceSettingTemplates> sdst1 = session.getNamedQuery("getServiceDeviceSettingTemplatesbyIds").setString("serviceId", serviceId).setString("DeviceTypeId", device.getDeviceTypes().getDeviceTypeId()).list();
                if (!sdst1.isEmpty()) {
                    for (ServiceDeviceSettingTemplates sdst : sdst1) {
                        ObjectSettingTemplates ost = getObjectSettingTemplates(sdst.getId().getObjectSettingTemplateId(), session);
                        if (ost != null) {
                            String serviceDeviceSettingId = UUID.randomUUID().toString();
                            if (insertServiceDeviceSettings(device, sds, userId, ost, serviceDeviceSettingId, null, session)) {
                                settingsresult = traverse(device, sds, userId, ost, serviceDeviceSettingId, session);
                            }
                        }
                        result = true;
                    }
                }
            }
            if (result && settingsresult) {
                if (!tx.wasCommitted()) {
                    tx.commit();
                }
            }
            return result;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tx.rollback();
            return result;
        }
    }

    public List<Agreements> getAgreementsByAgreementParentId(String agreementParentId, Session session) {
        return session.getNamedQuery("getAgreementsByAgreementParentId").setString("agreementParentId", agreementParentId).list();
    }

    public ObjectSettingTemplates getObjectSettingTemplates(String objectSettingTemplateId, Session session) {
        return (ObjectSettingTemplates) session.getNamedQuery("getObjectSettingTemplatesByTemplateId").setString("objectSettingTemplateId", objectSettingTemplateId).uniqueResult();
    }

    public boolean traverseUp(Device device, ServiceDeviceSubscriptions sds, String userId, ObjectSettingTemplates ost, String ObjectSettingTemplateId, Session session) {
        boolean settingResult = false;
        try {
            String serviceDeviceSettingId = UUID.randomUUID().toString();
            String serviceDeviceSettingParentId = null;
            if (ost.getObjectSettingTemplateParentId() != null) {
                ObjectSettingTemplates osts = findRoot(ost, session);
                settingResult = insertServiceDeviceSettings(device, sds, userId, osts, serviceDeviceSettingId, serviceDeviceSettingParentId, session);
            } else {
                settingResult = insertServiceDeviceSettings(device, sds, userId, ost, serviceDeviceSettingId, serviceDeviceSettingParentId, session);
            }
            return settingResult;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return settingResult;
        }
    }

    boolean insertServiceDeviceSettings(Device device, ServiceDeviceSubscriptions sds, String userId, ObjectSettingTemplates ost, String serviceDeviceSettingId, String serviceDeviceSettingParentId, Session session) {
        GregorianCalendar gc = new GregorianCalendar();
        try {
            if (ost != null) {
                ServiceDeviceSettings sdss = new ServiceDeviceSettings();
                sdss.setServiceDeviceSettingId(serviceDeviceSettingId);
                sdss.setServiceDeviceSettingParentID(serviceDeviceSettingParentId);
                ServiceDeviceSubscriptions sdes = new ServiceDeviceSubscriptions();
                sdes.setServiceDeviceSubscriptionId(sds.getServiceDeviceSubscriptionId());
                sdss.setServiceDeviceSubscriptions(sds);
                sdss.setServiceDeviceSettingName(ost.getObjectSettingName());
                if (ost.getObjectSettingDefaultValue() != null) {
                    sdss.setServiceDeviceSettingValue(ost.getObjectSettingDefaultValue());
                } else {
                    sdss.setServiceDeviceSettingValue("ServiceDeviceSettingDefaultValue is not Available in ObjectSettingTemplates");
                }
                sdss.setSettingDataTypeId(ost.getSettingDataType().getSettingDataTypeId());
                sdss.setObjectSettingTemplateId(ost.getObjectSettingTemplateId());
                sdss.setActiveFromDate(gc.getTime());
                sdss.setUserId(userId);
                sdss.setCreatedDate(gc.getTime());
                sdss.setUpdatedDate(gc.getTime());
                session.saveOrUpdate(sdss);
            }
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public boolean traverse(Device device, ServiceDeviceSubscriptions sds, String userId, ObjectSettingTemplates ost, String serviceDeviceSettingParentId, Session session) {
        boolean settingResult = false;
        try {
            if (ost != null) {
                String parentId = ost.getObjectSettingTemplateId();
                List<ObjectSettingTemplates> osList = getObjectSettingTemplatesByParentId(parentId, session);
                if (!osList.isEmpty()) {
                    for (ObjectSettingTemplates os1 : osList) {
                        String serviceDeviceSettingId = UUID.randomUUID().toString();
                        settingResult = insertServiceDeviceSettings(device, sds, userId, os1, serviceDeviceSettingId, serviceDeviceSettingParentId, session);
                        traverse(device, sds, userId, os1, serviceDeviceSettingId, session);
                    }
                }
            }
            return settingResult;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return settingResult;
        }
    }

    public ObjectSettingTemplates findRoot(ObjectSettingTemplates ostsList, Session session) {
        while (ostsList.getObjectSettingTemplateParentId() != null) {
            String templateId = ostsList.getObjectSettingTemplateParentId();
            List<ObjectSettingTemplates> osList = session.getNamedQuery("getObjectSettingTemplatesByTemplateId").setString("objectSettingTemplateId", templateId).list();
            if (!osList.isEmpty()) {
                for (ObjectSettingTemplates os1 : osList) {
                    ostsList = os1;
                }
            }
        }
        return ostsList;
    }

    boolean saveAddresses(Addresses add, Session session) {
        Transaction tx = null;
        boolean result = false;
        try {
            tx = session.beginTransaction();
            if (add != null) {
                session.saveOrUpdate(add);
                tx.commit();
                result = true;
            }
        } catch (Exception he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            result = false;
        }
        return result;
    }

    boolean saveDeviceOperationsMember(List<DeviceOperationsMember> domList, String deviceOperationsStatusId, Session session) {
        Transaction tx = session.beginTransaction();
        boolean result = false;
        try {
            if (!domList.isEmpty()) {
                for (DeviceOperationsMember dom : domList) {
                    Query query = session.createQuery("update DeviceOperationsMember set deviceOperationsStatusId = :deviceOperationsStatusId where deviceOperationsMemberId = :deviceOperationsMemberId");
                    query.setString("deviceOperationsStatusId", deviceOperationsStatusId);
                    query.setString("deviceOperationsMemberId", dom.getDeviceOperationsMemberId());
                    query.executeUpdate();
                    session.flush();
                    session.clear();
                }
            }
            tx.commit();
            result = true;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            result = false;
        }
        return result;
    }

    boolean saveDeviceOperationsMember(DeviceOperationsMember dom, Session session) {
        try {
            session.saveOrUpdate(dom);
            return true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            return false;
        }
    }

    boolean insertDeviceOperationsMember(DeviceOperationsMember dom, Session session) {
        Transaction tx = null;
        boolean result = false;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(dom);
            tx.commit();
            result = true;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            result = false;
        }
        return result;
    }

    boolean updateDeviceActivationSchedulesByActivationId(String deviceActivationId, Session session, int minutes, String userAlias, String deviceDataItemId, String deviceId) {
        Transaction tx = null;
        DeviceActivationSchedules das = (DeviceActivationSchedules) getDeviceActivationSchedulesById(deviceActivationId, session);
        DeviceStatusDataItems dsdi = (DeviceStatusDataItems) getDeviceStatusDataItemsBydeviceDataItemId(session, deviceId, deviceDataItemId);
        das.setDataItemValue("1");
        das.setUserAlias(userAlias);
        GregorianCalendar gc = new GregorianCalendar();
        das.setStartTime(gc.getTime());
        gc.add(Calendar.MINUTE, minutes);

        das.setStopTime(gc.getTime());
        das.setUpdatedDate(gc.getTime());
        tx = session.beginTransaction();
        session.saveOrUpdate(das);
        dsdi.setDeviceDataValue("1");
        session.saveOrUpdate(dsdi);
        tx.commit();
        return true;

    }

    public Object getDeviceActivationSchedulesById(String deviceActivationId, Session ismoperationSession) {
        return ismoperationSession.getNamedQuery("getDeviceActivationSchedulesByActivationId").setString("deviceActivationId", deviceActivationId).uniqueResult();
    }

    public boolean saveOrUpdateDeviceStatus(DeviceStatus ds, Session operationsSession) {
        Transaction tx = operationsSession.beginTransaction();
        try {
            operationsSession.saveOrUpdate(ds);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public boolean deleteDeviceStatus(DeviceStatus ds, Session operationsSession) {
        Transaction tx = operationsSession.beginTransaction();
        try {
            operationsSession.delete(ds);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public List<Device> getDeviceByGroup(Session session, String groupId) {
        try {
            return session.getNamedQuery("getDeviceByGroup").setString("groupId", groupId).list();
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }
    }

    public List<DeviceDataItemScaling> getDeviceDataItemScalingByDeviceId(Session session, List<String> deviceId) {
        try {
            return session.getNamedQuery("getDeviceDataItemScalingByDeviceId").setParameterList("deviceId", deviceId).list();
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }
    }

    public DeviceDataitemTranslations getDeviceDataItemTranslationsById(Session session, String deviceDataItemId, int countryId) {
        try {
            return (DeviceDataitemTranslations) session.getNamedQuery("getDeviceDataItemTranslationsById").setString("deviceDataItemId", deviceDataItemId).setInteger("countryId", countryId).uniqueResult();
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }
    }

    public List<Ogmdevice> getOgmdeviceById(Session session, String objectGroupId) {
        try {
            return session.getNamedQuery("getOgmdeviceById").setString("objectGroupId", objectGroupId).list();
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }
    }

    public List<DeviceDataItemScaling> getDeviceDataItemScalingByproductVariantId(Session session, String productVariantId) {
        try {
            return session.getNamedQuery("getDeviceDataItemScalingByproductVariantId").setString("productVariantId", productVariantId).list();
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }
    }

    public List<DeviceDataItemScaling> getAllDeviceDataItemScaling(Session session) {
        try {
            return session.getNamedQuery("getAllDeviceDataItemScaling").setMaxResults(100).list();
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }
    }

    public Object getDeviceStatusDataItemsBydeviceDataItemId(Session session, String deviceId, String deviceDataItemId) {
        try {
            return session.getNamedQuery("getDeviceStatusDataItemsBydeviceDataItemId").setString("deviceId", deviceId).setString("deviceDataItemId", deviceDataItemId).uniqueResult();
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }
    }

    public List<DeviceStatusDataItems> getDeviceStatusDataItemsBydeviceDataItemIds(Session session, String deviceId, List<String> deviceDataItemId) {
        try {
            return session.getNamedQuery("getDeviceStatusDataItemsBydeviceDataItemIds").setString("deviceId", deviceId).setParameterList("deviceDataItemId", deviceDataItemId).list();
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }
    }

    public List<DeviceDataItemScaling> getDeviceDataItemScalingByDeviceIdAndVariantId(Session session, List<String> deviceId, String productVariantId) {
        try {
            return session.getNamedQuery("getDeviceDataItemScalingByDeviceIdAndVariantId").setParameterList("deviceId", deviceId).setString("productVariantId", productVariantId).list();
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }
    }

    public List<ProductVariantTranslations> getProductVariantTranslations(Session session, List<String> productVariantIdList, int countryId, int limit) {
        if (limit == 0) {
            return session.getNamedQuery("getProductVariantTranslations").setParameterList("productVariantId", productVariantIdList).setInteger("countryId", countryId).list();
        } else {
            return session.getNamedQuery("getProductVariantTranslations").setParameterList("productVariantId", productVariantIdList).setInteger("countryId", countryId).setMaxResults(limit).list();
        }
    }

    public List<Device> getDeviceByDeviceIdsList(Session session, List<String> deviceId) {
        return session.getNamedQuery("getDeviceByListId").setParameterList("deviceId", deviceId).list();
    }

//    public List<Device> getDeviceByDeviceIdsSet(Session session, Set<String> deviceId) {
//        return session.getNamedQuery("getDeviceByListId").setParameterList("deviceId", deviceId).list();
//    }
    public List<Device> getDeviceByGroupListandDeviceIdList(Session session, Set<String> deviceId, Set<String> groupIDSet) {
        List<Device> devicesList = new ArrayList<Device>();
        List<List<String>> splitGroupIdsList = TCMUtil.split(new ArrayList<String>(groupIDSet), 1000);
        for (List<String> list : splitGroupIdsList) {
            devicesList.addAll(session.getNamedQuery("getDeviceByGroupListandDeviceIdList").setParameterList("deviceId", deviceId).setParameterList("groupId", list).list());
        }
        return devicesList;
    }

    public List<ObjectSettingPackageTemplates> getObjectSettingPackageTemplatesByPackageId(String objectSettingPackageId, Session session) {
        return session.getNamedQuery("getObjectSettingPackageTemplatesByPackageId").setString("objectSettingPackageId", objectSettingPackageId).list();
    }

    public se.info24.pojo.ObjectSettingTemplates getObjectSettingTemplatesByTemplateId(String objectSettingTemplateId, Session session) {
        try {
            List<se.info24.pojo.ObjectSettingTemplates> list = session.getNamedQuery("getObjectSettingTemplatesByTemplateId").setString("objectSettingTemplateId", objectSettingTemplateId).list();
            if (list != null && list.size() > 0) {
                return list.get(0);
            } else {
                return null;
            }
        } catch (IndexOutOfBoundsException ae) {
            return null;
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public Object getServiceClientLoginsById(String serviceClientLoginId, Session session) {
        return session.getNamedQuery("getServiceClientLoginsById").setString("serviceClientLoginId", serviceClientLoginId).uniqueResult();
    }

    public List<se.info24.pojo.ObjectSettingTemplates> getObjectSettingTemplate(Session session, String templateId, List<se.info24.pojo.ObjectSettingTemplates> list) {
        String settingTemplateId = templateId;
        if (settingTemplateId != null) {
            se.info24.pojo.ObjectSettingTemplates settingTemplate = getObjectSettingTemplatesByTemplateId(settingTemplateId, session);
            list.add(settingTemplate);
            if (settingTemplate.getObjectSettingTemplateParentId() != null) {
                getObjectSettingTemplate(session, settingTemplate.getObjectSettingTemplateParentId(), list);
            }
        }
        return list;
    }

    public List<Services> getServices(Session session, List<String> serviceIDList) {
        return session.getNamedQuery("getServicesByID").setParameterList("serviceId", serviceIDList).list();
    }

    public DeviceStatus saveDeviceStatus(Device device, se.info24.devicejaxbPost.Device deviceXML, Transaction tx) {
        Session session = HibernateUtil.getISMOperationsSessionFactory().openSession();
        try {
            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            DeviceStatus devStatus = new DeviceStatus();
            devStatus.setCoordinateSystemId("WGS84");
            devStatus.setCreatedDate(gc.getTime());
            devStatus.setDataItemId(UUID.randomUUID().toString());
            devStatus.setDataItemTime(gc.getTime());
            devStatus.setDeviceId(device.getDeviceId());
            devStatus.setGroupId(device.getGroups().getGroupId());
            devStatus.setIsEnabled(1);
            devStatus.setMsgId(UUID.randomUUID().toString());
            devStatus.setMsgTimeStamp(gc.getTime());
            devStatus.setPosDepth("0");
            devStatus.setPosDirection(0);
            devStatus.setPosLatitude("0");
            devStatus.setPosLongitude("0");
            session.saveOrUpdate(devStatus);
            return devStatus;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }
    }

    private DeviceStatus saveDeviceStatus(Device device, se.info24.devicejaxbPost.Device deviceXML) {
        Session session = HibernateUtil.getISMOperationsSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            DeviceStatus devStatus = new DeviceStatus();
            devStatus.setCoordinateSystemId("WGS84");
            devStatus.setCreatedDate(gc.getTime());
            devStatus.setDataItemId(UUID.randomUUID().toString());
            devStatus.setDataItemTime(gc.getTime());
            devStatus.setDeviceId(device.getDeviceId());
            devStatus.setGroupId(device.getGroups().getGroupId());
            devStatus.setIsEnabled(1);
            devStatus.setMsgId(UUID.randomUUID().toString());
            devStatus.setMsgTimeStamp(gc.getTime());
            if (deviceXML.getDeviceStatus().getPosDepth() != null) {
                devStatus.setPosDepth(deviceXML.getDeviceStatus().getPosDepth());
            }
            devStatus.setPosDirection(0);
            if (deviceXML.getDeviceStatus().getPosLatitude() != null) {
                devStatus.setPosLatitude(deviceXML.getDeviceStatus().getPosLatitude());
            }
            if (deviceXML.getDeviceStatus().getPosLongitude() != null) {
                devStatus.setPosLongitude(deviceXML.getDeviceStatus().getPosLongitude());
            }
            session.saveOrUpdate(devStatus);
            tx.commit();
            return devStatus;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tx.rollback();
            return null;
        }
    }

    public DeviceSettings getDeviceSettingsByIds(String deviceId, String objectSettingName, Session session) {
        return (DeviceSettings) session.getNamedQuery("getDeviceSettingsByIdandName").setString("deviceId", deviceId).setString("deviceSettingName", objectSettingName).uniqueResult();
    }

    public boolean saveEventItems(EventItems eventItems, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(eventItems);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            tx.rollback();
            return false;
        }
    }

    public List<ContactGroups> getContactGroupByGroupid(Set<String> GroupID, Session session) {
        try {
            return session.getNamedQuery("getContactGroupByGroupid").setParameterList("GroupID", GroupID).setMaxResults(200).list();
        } catch (Exception e) {
            return null;
        }
    }

    public List<EventItems> getEventItemsByID(String itemID, String eventTypeID, Session session) {
        try {
            return session.getNamedQuery("getEventItemsByID").setString("ItemID", itemID).setString("EventTypeID", eventTypeID).list();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Schedules> getSchedulesByGroupidList(List<String> groupIdsList, Session session) {
        try {
            return session.getNamedQuery("getSchedulesByGroupidList").setParameterList("GroupID", groupIdsList).list();
        } catch (Exception e) {
            return null;
        }
    }

    public Schedules getSchedulesByGroupid(String groupId, Session session) {
        List<Schedules> schedulesList = session.getNamedQuery("getSchedulesByGroupId").setString("groupId", groupId).list();
        return !schedulesList.isEmpty() ? schedulesList.get(0) : null;
    }

    public List<EventItems> getEventItemsByItemID(String itemID, Session session) {
        try {
            return session.getNamedQuery("getEventItemsByItemID").setString("ItemID", itemID).list();
        } catch (Exception e) {
            return null;
        }
    }

    public List<EventTypeTranslations> getEventTypeTranslationsbyEventTypeId(List<String> eventTypeIdList, int countryid, Session session) {
        try {
            return session.getNamedQuery("getEventTypeTranslationsbyIds").setParameterList("eventTypeId", eventTypeIdList).setInteger("countryId", countryid).list();
        } catch (Exception e) {
            return null;
        }
    }

    public List<EventItemActions> getEventItemActionsbyEventItemID(List<String> eventItemID, Session session) {
        try {
            return session.getNamedQuery("getEventItemActionsbyEventItemID").setParameterList("eventItemId", eventItemID).list();
        } catch (Exception e) {
            return null;
        }
    }

    public List<EventActionSchedules> getEventActionSchedulesbyID(List<String> eventActionScheduleID, Session session) {
        try {
            return session.getNamedQuery("getEventActionSchedulesbyID").setParameterList("eventActionScheduleId", eventActionScheduleID).list();
        } catch (Exception e) {
            return null;
        }
    }

    public List<ContactGroups> getContactGroupById(List<String> contactGroupId, Session session) {
        try {
            return session.getNamedQuery("getContactGroupById").setParameterList("contactGroupId", contactGroupId).list();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Schedules> getSchedulesByID(List<String> scheduleId, Session session) {
        try {
            return session.getNamedQuery("getSchedulesByIdList").setParameterList("scheduleId", scheduleId).list();
        } catch (Exception e) {
            return null;
        }
    }

    public Schedules getSchedulesByID(String scheduleId, Session session) {
        List<Schedules> schedulesList = session.getNamedQuery("getSchedulesById").setString("scheduleId", scheduleId).list();
        return !schedulesList.isEmpty() ? schedulesList.get(0) : null;
    }

    public boolean saveEventCondition(EventItems item, Session session) {
        Transaction tx = null;
        boolean result = false;
        try {
            if (item != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(item);
                tx.commit();
                result = true;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx != null) {
                tx.rollback();
            }
            result = false;
        }
        return result;
    }

    EventItems getEventItemsById(Session session, String eventItemId) {
        try {
            return (EventItems) session.getNamedQuery("getEventItemsById").setString("eventItemId", eventItemId).uniqueResult();
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }
    }

    public boolean deleteEventCondition(EventItems item, List<EventActionSchedules> schedules, List<EventItemActions> actions, Session session) {
        Transaction tx = null;
        try {
            if (item != null) {
                tx = session.beginTransaction();
                session.delete(item);
                for (EventActionSchedules sche : schedules) {
                    session.delete(sche);
                }
                for (EventItemActions action : actions) {
                    session.delete(action);
                }
                tx.commit();
                return true;
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return false;
    }

    EventActionSchedules getEventActionSchedulesById(Session session, String eventActionScheduleId) {
        try {
            return (EventActionSchedules) session.getNamedQuery("getEventActionSchedulesById").setString("eventActionScheduleId", eventActionScheduleId).uniqueResult();
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return null;
        }
    }

    List<EventItemActions> getEventItemActionsByScheduleId(Session session, String eventActionScheduleId) {
        return session.getNamedQuery("getEventItemActionsScheduleId").setString("eventActionScheduleId", eventActionScheduleId).list();
    }

    List<EventItemActions> getEventItemActionsByEventItemId(Session session, String eventItemId) {
        return session.getNamedQuery("getEventItemActionsByEventItemId").setString("eventItemId", eventItemId).list();
    }

    public boolean saveEventActionSchedules(EventActionSchedules item, Session session) {
        Transaction tx = null;
        boolean result = false;
        try {
            if (item != null) {
                tx = session.beginTransaction();
                session.saveOrUpdate(item);
                tx.commit();
                session.flush();
                session.clear();
                result = true;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx != null) {
                tx.rollback();
            }
            result = false;
        }
        return result;
    }

    public boolean deleteEventActionSchedules(EventActionSchedules item, Session session) {
        Transaction tx = null;
        try {
            if (item != null) {
                tx = session.beginTransaction();
                session.delete(item);
                tx.commit();
                return true;
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return false;
    }

    public boolean deleteEventItemActions(List<EventItemActions> itemList, Session session) {
        Transaction tx = null;
        try {
            if (itemList != null) {
                tx = session.beginTransaction();
                for (EventItemActions item : itemList) {
                    session.delete(item);
                }
                tx.commit();
                return true;
            }
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
        return false;
    }

    public boolean addEventNotification(EventActionSchedules itemSchedules, EventItemActions itemActions, Session session) {
        Transaction tx = null;
        boolean result = false;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(itemSchedules);
            session.saveOrUpdate(itemActions);
            tx.commit();
            session.flush();
            session.clear();
            result = true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx != null) {
                tx.rollback();
            }
            result = false;
        }
        return result;
    }

    public ObjectFieldData getObjectFieldDataByID(Session session, String objectId, String fieldId) {
        return (ObjectFieldData) session.getNamedQuery("getObjectFieldDataByID").setString("objectId", objectId).setString("fieldId", fieldId).uniqueResult();
    }

    public List<ObjectTypeFields> getObjectTypeFieldsByObjectID(Session session, String objectTypeId) {
        return session.getNamedQuery("getObjectTypeFieldsByObjectID").setString("objectTypeId", objectTypeId).list();
    }

    public List<FieldTranslations> getFieldTranslationsByIdList(Session session, List<String> fieldIds, String countryId) {
        return session.getNamedQuery("getFieldTranslationsByIdList").setParameterList("fieldId", fieldIds).setInteger("countryId", Integer.parseInt(countryId)).list();
    }

    public List<ListValues> getListValuesByListId(Session session, String listId) {
        return session.getNamedQuery("getListValuesByListId").setParameter("listId", listId).list();
    }

    public List<ObjectStateCodeTranslations> getObjectStateCodeTranslationsByCountryId(Session session, String countryId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ObjectStateCodes.class, "osd");
        detachedCriteria.addOrder(Order.desc("osd.createdDate"));
        detachedCriteria.setProjection(Projections.property("osd.objectStateCodeId"));
        List<String> objectStateCodeIdsList = detachedCriteria.getExecutableCriteria(session).setMaxResults(200).list();

        Criteria criteria = session.createCriteria(ObjectStateCodeTranslations.class, "osct");
        criteria.add(Restrictions.in("osct.id.objectStateCodeId", objectStateCodeIdsList));
        criteria.add(Restrictions.eq("osct.id.countryId", Integer.valueOf(countryId)));
        criteria.addOrder(Order.asc("osct.stateCodeName"));
        return criteria.list();
    }

    public ObjectStateControl getObjectStateControlByObjectId(Session session, String objectId) {
        return (ObjectStateControl) session.getNamedQuery("getObjectStateControlByObjectId").setString("objectId", objectId).uniqueResult();
    }

    public ObjectStateCodes getObjectStateCodesById(Session session, String objectStateCodeId) {
        return (ObjectStateCodes) session.getNamedQuery("getObjectStateCodesById").setString("objectStateCodeId", objectStateCodeId).uniqueResult();
    }

    public boolean saveUpdateObjectFieldData(Session session, ObjectFieldData ofd) {
        Transaction tx = null;
        boolean result = false;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(ofd);
            tx.commit();
            result = true;
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            if (tx != null) {
                tx.rollback();
            }
            result = false;
        }
        return result;
    }

    public List<String> getTrustedGroup(Session session, String groupId) {
        GroupDAO groupDAO = new GroupDAO();
        List<GroupTrusts> groupTrustsList = groupDAO.getGroupTrustByGroupID(groupId, session);
        List<String> groupIdList = new ArrayList<String>();
        groupIdList.add(groupId);
        for (GroupTrusts gtr : groupTrustsList) {
            groupIdList.add(gtr.getId().getGroupId());
        }
        return groupIdList;
    }

    public List<GroupTranslations> getGroupTransSearch(Session session, List<String> groupID, String groupName) {
        return session.getNamedQuery("getGroupTransSearch").setParameterList("groupID", groupID).setString("groupName", groupName).list();
    }

    public List<ObjectGroupTranslations> getObjectGroupTranslationsByObjectGroupName(Session session, List<String> objectGroupId, String searchString) {
        return session.getNamedQuery("getObjectGroupTranslationsByObjectGroupName").setParameterList("objectGroupId", objectGroupId).setString("searchString", searchString).list();
    }

    public List<Ogmdevice> getAllOgmdeviceById(Session session, Set<String> objectGroupId) {
        return session.getNamedQuery("getAllOgmdeviceById").setParameterList("objectGroupId", objectGroupId).list();
    }

    public List<Device> getDeviceBySearchdeviceName(Session session, String searchField, Set<String> groupId) {
        if (TCMUtil.isValidUUID(searchField)) {
            return session.getNamedQuery("getDeviceByDeviceGroupID").setParameterList("groupId", groupId).setString("deviceId", searchField).list();
        } else {
            List<Device> dev = session.getNamedQuery("getDeviceBySearchdeviceName").setParameterList("groupId", groupId).setString("searchField", searchField).list();
            return dev;
        }
    }

    public List<Device> getDeviceBySearchdeviceNames(Session session, String searchField, List<String> groupId) {
        return session.getNamedQuery("getDeviceBySearchdeviceNames").setParameterList("groupId", groupId).setString("searchField", searchField).list();
    }

    public List<Device> getDeviceBySearchdeviceNameonly(Session session, String searchField, Set<String> groupId) {
        return session.getNamedQuery("getDeviceBySearchdeviceNameonly").setParameterList("groupId", groupId).setString("searchField", searchField).list();
    }

    public List<ItemConnectionStatus> getItemConnectionStatusByIds(Session session, Set<String> itemId, int connected) {
        return session.getNamedQuery("getItemConnectionStatusByIds").setParameterList("itemId", itemId).setInteger("connected", connected).list();
    }

    public List<DeviceStatus> getDeviceStatusByDeviceIdsTop200(Session session, Set<String> device) {
        return session.getNamedQuery("getDeviceStatusByDeviceIds").setParameterList("deviceId", device).list();
    }

    public List<DeviceStatus> getDeviceStatusByDeviceIds(Session session, Set<String> device) {
        return session.getNamedQuery("getDeviceStatusByDeviceIds").setParameterList("deviceId", device).setMaxResults(200).list();
    }

    public List<ItemConnectionStatus> getItemConnectionStatusByItemIds(Session session, Set<String> itemId) {
        return session.getNamedQuery("getItemConnectionStatusByItemIds").setParameterList("itemId", itemId).list();
    }

    public List<Device> getDeviceDetailsByGroupId(Session session, List<String> groupId) {
        return session.getNamedQuery("getDeviceDetailsByGroupId").setParameterList("groupId", groupId).list();
    }

    public List<DeviceDataItems> getDeviceDataItemsByIdList(Session session, List<String> deviceDataItemIDList, int maxResult) {
        return session.getNamedQuery("getDeviceDataItemsByIdList").setParameterList("id", deviceDataItemIDList).setMaxResults(maxResult).list();
    }

    public List<DeviceDataItems> getDeviceDataItemsList(Session session, List<String> deviceDataItemIDList, int maxResult) {
        if (maxResult == 0) {
            return session.getNamedQuery("getDeviceDataItemsList").setParameterList("id", deviceDataItemIDList).list();
        } else {
            return session.getNamedQuery("getDeviceDataItemsList").setParameterList("id", deviceDataItemIDList).setMaxResults(maxResult).list();
        }
    }

    public List<GroupTranslations> getGroupTransSearchByGroupName(Session session, List<String> groupId, String searchString, int countryID) {
        return session.getNamedQuery("getGroupTransSearchByGroupName").setParameterList("groupID", groupId).setString("groupName", searchString).setInteger("countryID", countryID).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringGroupName(Session session, List<String> userGroupIdSet, String gc_start_time, String gc_stop_time) {
        return session.getNamedQuery("getTransactionResultBySearchStringGroupName").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setParameterList("searchString", userGroupIdSet).setMaxResults(200).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringDeviceName(Session session, String deviceName, String gc_start_time, String gc_stop_time, List<String> userGroupId) {
        return session.getNamedQuery("getTransactionResultBySearchStringDeviceName").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setString("searchString", deviceName).setParameterList("userGroupId", userGroupId).setMaxResults(200).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringDeviceID(Session session, String deviceName, String gc_start_time, String gc_stop_time, List<String> userGroupId) {
        return session.getNamedQuery("getTransactionResultBySearchStringDeviceID").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setString("searchString", deviceName).setParameterList("userGroupId", userGroupId).setMaxResults(200).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringUserGroupNameUserDetail(Session session, String userName, List<String> userGroupIdSet, String gc_start_time, String gc_stop_time) {
        return session.getNamedQuery("getTransactionResultBySearchStringUserGroupNameUserDetail").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setString("searchString", userName).setParameterList("userGroupId", userGroupIdSet).setMaxResults(200).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringUserGroupNameUserDetailID(Session session, String userName, List<String> userGroupIdSet, String gc_start_time, String gc_stop_time) {
        return session.getNamedQuery("getTransactionResultBySearchStringUserGroupNameUserDetailID").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setString("searchString", userName).setParameterList("userGroupId", userGroupIdSet).setMaxResults(200).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringUserGroupName(Session session, String userName, List<String> userGroupIdSet, String gc_start_time, String gc_stop_time) {
        return session.getNamedQuery("getTransactionResultBySearchStringUserGroupName").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setString("searchString", userName).setParameterList("userGroupId", userGroupIdSet).setMaxResults(200).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringDeviceGroupName(Session session, String deviceName, List<String> userGroupIdSet, String gc_start_time, String gc_stop_time) {
        return session.getNamedQuery("getTransactionResultBySearchStringDeviceGroupName").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setString("searchString", deviceName).setParameterList("userGroupId", userGroupIdSet).setMaxResults(200).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringDeviceIDGroupName(Session session, String deviceName, List<String> userGroupIdSet, String gc_start_time, String gc_stop_time) {
        return session.getNamedQuery("getTransactionResultBySearchStringDeviceIDGroupName").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setString("searchString", deviceName).setParameterList("userGroupId", userGroupIdSet).setMaxResults(200).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringUserDeviceName(Session session, String deviceName, String userName, String gc_start_time, String gc_stop_time, List<String> userGroupIdSet) {
        return session.getNamedQuery("getTransactionResultBySearchStringUserDeviceName").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setString("searchString", userName).setString("deviceName", deviceName).setParameterList("userGroupId", userGroupIdSet).setMaxResults(200).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringUserDeviceNameUserDetail(Session session, String deviceName, String userName, String gc_start_time, String gc_stop_time, List<String> userGroupIdSet) {
        return session.getNamedQuery("getTransactionResultBySearchStringUserDeviceNameUserDetail").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setString("searchString", userName).setString("deviceName", deviceName).setParameterList("userGroupId", userGroupIdSet).setMaxResults(200).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringUserDeviceNameUserDetailID(Session session, String deviceName, String userName, String gc_start_time, String gc_stop_time, List<String> userGroupIdSet) {
        return session.getNamedQuery("getTransactionResultBySearchStringUserDeviceName").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setString("searchString", userName).setString("deviceName", deviceName).setParameterList("userGroupId", userGroupIdSet).setMaxResults(200).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringUserDeviceIDName(Session session, String deviceName, String userName, String gc_start_time, String gc_stop_time, List<String> userGroupIdSet) {
        return session.getNamedQuery("getTransactionResultBySearchStringUserDeviceIDName").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setString("searchString", userName).setString("deviceName", deviceName).setParameterList("userGroupId", userGroupIdSet).setMaxResults(200).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringUserDeviceIDNameUserDetail(Session session, String deviceName, String userName, String gc_start_time, String gc_stop_time, List<String> userGroupIdSet) {
        return session.getNamedQuery("getTransactionResultBySearchStringUserDeviceIDNameUserDetail").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setString("searchString", userName).setString("deviceName", deviceName).setParameterList("userGroupId", userGroupIdSet).setMaxResults(200).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringUserDeviceIDNameUserDetailID(Session session, String deviceName, String userName, String gc_start_time, String gc_stop_time, List<String> userGroupIdSet) {
        return session.getNamedQuery("getTransactionResultBySearchStringUserDeviceIDNameUserDetailID").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setString("searchString", userName).setString("deviceName", deviceName).setParameterList("userGroupId", userGroupIdSet).setMaxResults(200).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringUserDeviceGroupName(Session session, String deviceName, String userName, List<String> userGroupIdSet, String gc_start_time, String gc_stop_time) {
        return session.getNamedQuery("getTransactionResultBySearchStringUserDeviceGroupName").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setString("searchString", userName).setString("deviceName", deviceName).setParameterList("userGroupId", userGroupIdSet).setMaxResults(200).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringUserDeviceGroupNameUserDetail(Session session, String deviceName, String userName, List<String> userGroupIdSet, String gc_start_time, String gc_stop_time) {
        return session.getNamedQuery("getTransactionResultBySearchStringUserDeviceGroupNameUserDetail").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setString("searchString", userName).setString("deviceName", deviceName).setParameterList("userGroupId", userGroupIdSet).setMaxResults(200).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringUserDeviceGroupNameUserDetailID(Session session, String deviceName, String userName, List<String> userGroupIdSet, String gc_start_time, String gc_stop_time) {
        return session.getNamedQuery("getTransactionResultBySearchStringUserDeviceGroupNameUserDetailID").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setString("searchString", userName).setString("deviceName", deviceName).setParameterList("userGroupId", userGroupIdSet).setMaxResults(200).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringUserDeviceIDGroupName(Session session, String deviceName, String userName, List<String> userGroupIdSet, String gc_start_time, String gc_stop_time) {
        return session.getNamedQuery("getTransactionResultBySearchStringUserDeviceIDGroupName").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setString("searchString", userName).setString("deviceName", deviceName).setParameterList("userGroupId", userGroupIdSet).setMaxResults(200).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringUserDeviceIDGroupNameUserDetail(Session session, String deviceName, String userName, List<String> userGroupIdSet, String gc_start_time, String gc_stop_time) {
        return session.getNamedQuery("getTransactionResultBySearchStringUserDeviceIDGroupNameUserDetail").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setString("searchString", userName).setString("deviceName", deviceName).setParameterList("userGroupId", userGroupIdSet).setMaxResults(200).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringUserDeviceIDGroupNameUserDetailID(Session session, String deviceName, String userName, List<String> userGroupIdSet, String gc_start_time, String gc_stop_time) {
        return session.getNamedQuery("getTransactionResultBySearchStringUserDeviceIDGroupNameUserDetailID").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setString("searchString", userName).setString("deviceName", deviceName).setParameterList("userGroupId", userGroupIdSet).setMaxResults(200).list();
    }

    public List<Currency> getCurrencyByCurrencyId(Session session, Set<Integer> currencyId) {
        return session.getNamedQuery("getCurrencyByCurrencyId").setParameterList("currencyId", currencyId).list();
    }

    public List<GroupTranslations> getGroupTranslationsById(Set<String> groupIdSet, int countryid, Session session) {
        try {
            return session.getNamedQuery("getGroupTranslationByGrpIDandCountryId").setParameterList("groupID", groupIdSet).setInteger("countryID", countryid).list();
        } catch (HibernateException he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            return null;
        }
    }

    public List<ObjectGroupTranslations> getObjectGroupTranslationsByObjectGroupNames(Session session, String searchString) {
        return session.getNamedQuery("getObjectGroupTranslationsByObjectGroupNames").setString("searchString", searchString).list();
    }

    public List<ObjectGroups> getObjectGroupsByGroupIdsandObjectGroupIds(Session session, Set<String> objectGroupId, List<String> groupidset) {
        return session.getNamedQuery("getObjectGroupsByGroupIdsandObjectGroupIds").setParameterList("groupId", groupidset).setParameterList("objectGroupId", objectGroupId).list();
    }

    public List<ObjectGroups> getObjectGroupsByGroupIdsandNotObjectGroupIds(Session session, Set<String> groupidset, List<String> objectGroupIdList) {
        return session.getNamedQuery("getObjectGroupsByGroupIdsandNotObjectGroupIds").setParameterList("groupId", groupidset).setParameterList("objectGroupId", objectGroupIdList).setMaxResults(200).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringObjectGroupName(Session session, Set<String> deviceId, String gc_start_time, String gc_stop_time) {
        return session.getNamedQuery("getTransactionResultBySearchStringObjectGroupName").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setParameterList("deviceId", deviceId).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringGroup(Session session, List<String> userGroupIdSet, String gc_start_time, String gc_stop_time) {
        return session.getNamedQuery("getTransactionResultBySearchStringGroup").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setParameterList("searchString", userGroupIdSet).list();
    }

    public List<TransactionResult> getTransactionResultBySearchStringDevice(Session session, List<String> deviceName, String gc_start_time, String gc_stop_time, List<String> userGroupId) {
        return session.getNamedQuery("getTransactionResultBySearchStringDevice").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setParameterList("searchString", deviceName).setParameterList("userGroupId", userGroupId).list();
    }

    public List<TransactionResult> getTransactionResultBySearchDeviceName(Session session, String deviceName, String gc_start_time, String gc_stop_time, List<String> userGroupId) {
        return session.getNamedQuery("getTransactionResultBySearchDeviceName").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setString("searchString", deviceName).setParameterList("userGroupId", userGroupId).list();
    }

    public List<TransactionResult> getTransactionResultBySearchDeviceNameID(Session session, String gc_start_time, String gc_stop_time, String searchString, Set<String> deviceid, List<String> groupidset) {
        return session.getNamedQuery("getTransactionResultBySearchDeviceNameID").setString("fromDate", gc_start_time).setString("toDate", gc_stop_time).setString("searchString", searchString).setParameterList("deviceId", deviceid).setParameterList("userGroupId", groupidset).list();
    }

    public List<TransactionProducts> getTransactionProductsByTransactionIds(Session session, Set<String> transactionId) {
        return session.getNamedQuery("getTransactionProductsByTransactionIds").setParameterList("transactionId", transactionId).list();
    }

    public List<ObjectMediaFiles> getObjectMediaFilesByobjectId(Session session, String objectId, List<String> mediaFileId) {
        return session.getNamedQuery("getObjectMediaFilesByobjectId").setString("objectId", objectId).setParameterList("mediaFileId", mediaFileId).list();
    }

    public List<String> getMediaFiles(Session sessionopr) {
        SQLQuery query = sessionopr.createSQLQuery("select mediaFileId from MediaFiles").addScalar("mediaFileId", Hibernate.STRING);

        return query.setMaxResults(200).list();
    }

    public List<Object> getMediaFilesByIds(Session sessionopr, List<String> mediaFileId) {
        SQLQuery query = sessionopr.createSQLQuery("select mediaFileId,mediaFileName,mediaFileTypeId,mediaFileLength,mediaFileExtension,createdDate,lastUpdatedByUserId,groupId from MediaFiles where mediaFileId in (:mediaFileId)order by createdDate desc");
        query.addScalar("mediaFileId", Hibernate.STRING).addScalar("mediaFileName", Hibernate.STRING).addScalar("mediaFileTypeId", Hibernate.STRING).addScalar("mediaFileLength", Hibernate.STRING).addScalar("mediaFileExtension", Hibernate.STRING).addScalar("createdDate", Hibernate.STRING).addScalar("lastUpdatedByUserId", Hibernate.STRING).addScalar("groupId", Hibernate.STRING);

        query.setParameterList("mediaFileId", mediaFileId);
        return query.list();
    }

    public List<Object> getMediaFilesByMediaFileIDs(Session sessionopr, List<String> mediaFileId) {
        SQLQuery query = sessionopr.createSQLQuery("select mediaFileId,mediaFileName,mediaFileTypeId,mediaFileLength,mediaFileExtension,createdDate,updatedDate,mediaFileDescription,lastUpdatedByUserId,groupId from MediaFiles where mediaFileId in (:mediaFileId)order by mediaFileName");
        query.addScalar("mediaFileId", Hibernate.STRING).addScalar("mediaFileName", Hibernate.STRING).addScalar("mediaFileTypeId", Hibernate.STRING).addScalar("mediaFileLength", Hibernate.STRING).addScalar("mediaFileExtension", Hibernate.STRING).addScalar("createdDate", Hibernate.STRING).addScalar("updatedDate", Hibernate.STRING).addScalar("mediaFileDescription", Hibernate.STRING).addScalar("lastUpdatedByUserId", Hibernate.STRING).addScalar("groupId", Hibernate.STRING);

        query.setParameterList("mediaFileId", mediaFileId);
        query.setMaxResults(200);
        return query.list();
    }

    public List<Object> getMediaFilesByGivenGroupIDs(Session sessionopr, List<String> groupIds) {
        SQLQuery query = sessionopr.createSQLQuery("select mediaFileId,mediaFileName,mediaFileTypeId,mediaFileLength,mediaFileExtension,createdDate,updatedDate,mediaFileDescription,lastUpdatedByUserId,groupId from MediaFiles where groupId in (:groupId)order by mediaFileName");
        query.addScalar("mediaFileId", Hibernate.STRING).addScalar("mediaFileName", Hibernate.STRING).addScalar("mediaFileTypeId", Hibernate.STRING).addScalar("mediaFileLength", Hibernate.STRING).addScalar("mediaFileExtension", Hibernate.STRING).addScalar("createdDate", Hibernate.STRING).addScalar("updatedDate", Hibernate.STRING).addScalar("mediaFileDescription", Hibernate.STRING).addScalar("lastUpdatedByUserId", Hibernate.STRING).addScalar("groupId", Hibernate.STRING);
        query.setParameterList("groupId", groupIds);
        return query.list();
    }

    public List<MediaFileTypes> getMediaFileTypesByIds(Session session, List<String> mediaFileTypeId) {
        return session.getNamedQuery("getMediaFileTypesByIds").setParameterList("mediaFileTypeId", mediaFileTypeId).list();
    }

    public List<MediaFileTypes> getMediaFileTypes(Session session) {
        return session.getNamedQuery("getMediaFileTypes").setMaxResults(50).list();
    }

    public List<MediaFiles> getMediaFilesById(Session sessionopr, List<String> mediaFileId) {
        return sessionopr.getNamedQuery("getMediaFilesById").setParameterList("mediaFileId", mediaFileId).list();
    }

    public List<MediaFiles> getMediaFilesByIdWithMaxResult(Session sessionopr, List<String> mediaFileId, int maxResult) {
        return sessionopr.getNamedQuery("getMediaFilesById").setParameterList("mediaFileId", mediaFileId).setMaxResults(maxResult).list();
    }

    public Object getMediaFilesByMediaFileId(Session ismoperationssession, String mediaFileId) {
        SQLQuery query = ismoperationssession.createSQLQuery("select mediaFileId, mediaFileName, mediaFileBlob, mediaFileExtension from MediaFiles where mediaFileId = :mediaFileId");
        query.addScalar("mediaFileId", Hibernate.STRING);
        query.addScalar("mediaFileName", Hibernate.STRING);
        query.addScalar("mediaFileBlob", Hibernate.BLOB);
        query.addScalar("mediaFileExtension", Hibernate.STRING);

        query.setString("mediaFileId", mediaFileId);
        return query.uniqueResult();
    }

    public List<MediaFiles> getMediaFilesByGroupId(Session sessionopr, List<String> groupId) {
        return sessionopr.getNamedQuery("getMediaFilesByGroupId").setParameterList("groupId", groupId).setMaxResults(200).list();
    }

    public List<MediaFiles> getMediaFilesByGroupIDs(Session sessionopr, List<String> groupId) {
        return sessionopr.getNamedQuery("getMediaFilesByGroupId").setParameterList("groupId", groupId).list();
    }

    public List<MediaFiles> getMediaFilesByGroupIdandFileName(Session sessionopr, List<String> groupId, String searchField) {
        return sessionopr.getNamedQuery("getMediaFilesByGroupIdandFileName").setParameterList("groupId", groupId).setString("searchField", searchField).setMaxResults(200).list();
    }

    public List<MediaFiles> getMediaFilesByGroupIdandId(Session sessionopr, List<String> groupId, String mediaFileId) {
        return sessionopr.getNamedQuery("getMediaFilesByGroupIdandId").setParameterList("groupId", groupId).setString("mediaFileId", mediaFileId).setMaxResults(200).list();
    }

    public List<ObjectMediaFiles> getObjectMediaFilesByNotobjectId(Session session, String objectId) {
        return session.getNamedQuery("getObjectMediaFilesByNotobjectId").setString("objectId", objectId).list();
    }

    public DeviceTypesInGroups getDeviceTypesInGroupsById(Session session, String groupId, String deviceTypeId) {
        return (DeviceTypesInGroups) session.getNamedQuery("getDeviceTypesInGroupsById").setParameter("groupId", groupId).setParameter("deviceTypeId", deviceTypeId).uniqueResult();
    }

    public List<DeviceCommandSchedules> getDeviceCommandSchedulesByDeviceId(Session session, String deviceId, int maxElement) {
        return session.getNamedQuery("getDeviceCommandSchedulesByDeviceId").setParameter("deviceId", deviceId).setMaxResults(maxElement).list();
    }

    public List<DeviceTypeCommandTranslations> getDeviceTypeCommandTranslations(Session session, List<String> deviceTypeCommandId, int countryId) {
        return session.getNamedQuery("getDeviceTypeCommandTranslations").setParameterList("deviceTypeCommandId", deviceTypeCommandId).setInteger("countryId", countryId).list();
    }

    public List<DeviceTypeCommandTranslations> getDeviceTypeCommandTranslationsOrderBycommandButtonText(Session session, List<String> deviceTypeCommandId, int countryId) {
        return session.getNamedQuery("getDeviceTypeCommandTranslationsOrderBycommandButtonText").setParameterList("deviceTypeCommandId", deviceTypeCommandId).setInteger("countryId", countryId).setMaxResults(200).list();
    }

    public List<DeviceTypeCommands> getDeviceTypeCommandsByType(Session session, String deviceTypeId) {
        return session.getNamedQuery("getDeviceTypeCommandsByType").setParameter("deviceTypeId", deviceTypeId).setMaxResults(200).list();
    }

    public boolean AddDeviceCommandSchedule(DeviceCommandSchedules dcs, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(dcs);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public List<DeviceCommandSchedules> getDeviceCommandSchedulesById(Session session, String deviceCommandScheduleId) {
        return session.getNamedQuery("getDeviceCommandSchedulesById").setParameter("deviceCommandScheduleId", deviceCommandScheduleId).list();
    }

    public boolean deleteDeviceCommandSchedule(DeviceCommandSchedules get, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(get);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public List<Object> getDevicesCountBySearch(Session session, String agreementId, String groupName, String objectGroupName, String deviceName, DeviceCountReport deviceCountReport) {
        StringBuffer queryString = new StringBuffer();
        List<Object> object = new ArrayList<Object>();
        //The below Common Table Expression is written to take the parent child agreementId's upto 10 downstream levels
        queryString.append(";with  CTE as ( select  agreementid, 1 as level from agreements where agreementid = '" + agreementId + "' " +
                "union all select child.agreementid, level + 1 from agreements child join CTE parent on child.agreementparentid = parent.agreementid " +
                "and level < 5) ");
        queryString.append("select d.deviceId as deviceId from Device as d inner join groups as g on d.groupId = g.groupId and g.groupId in(" + TCMUtil.getGroupTrust(deviceCountReport.getLoggedInUserGroupID()) + ") ");
        try {
            if (groupName != null) {
                if (TCMUtil.isValidUUID(groupName)) {
                    queryString.append(" inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.groupId = '" + groupName + "' ");
                } else {
                    queryString.append(" inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.GroupName like '%" + groupName + "%' and gts.countryId = " + deviceCountReport.getCountryID() + " ");
                }
            }

            if (deviceName != null) {
                if (TCMUtil.isValidUUID(deviceName)) {
                    queryString.append(" and d.deviceId = '" + deviceName + "' ");
                } else {
                    queryString.append(" and (d.deviceName like '%" + deviceName + "%' or d.deviceName2 like '%" + deviceName + "%') ");
                }
            }

            if (agreementId != null) {
                //below query is to add the 10 level parent child agreementIds to the main query
                queryString.append(" and d.agreementId in(select agreementid from CTE where level < 5) ");
            }

            if (objectGroupName != null) {
                queryString.append(" inner join Ogmdevice as ogm on d.deviceId = ogm.deviceId inner join ObjectGroups as og on ogm.objectGroupId = og.objectGroupId " +
                        " inner join ObjectGroupTranslations as ogt on og.objectGroupId = ogt.objectGroupId and ogt.countryId = " + deviceCountReport.getCountryID() + " ");
                if (TCMUtil.isValidUUID(objectGroupName)) {
                    queryString.append("and ogt.objectGroupId = '" + objectGroupName + "' ");
                } else {
                    queryString.append("and ogt.objectGroupName like '%" + objectGroupName + "%' ");
                }
            }
            if (deviceCountReport.getDeviceType() != null) {
                if (TCMUtil.isValidUUID(deviceCountReport.getDeviceType())) {
                    queryString.append(" and d.DeviceTypeID = '" + deviceCountReport.getDeviceType() + "' ");
                } else {
                    queryString.append(" and d.DeviceTypeID in(select dt.DeviceTypeID from DeviceTypes as dt where dt.DeviceTypeName like '%" + deviceCountReport.getDeviceType() + "%' ");
                }
            }

            if (deviceCountReport.getOptionalCountryID() != null) {
                TCMUtil.loger(getClass().getName(), "IF" + deviceCountReport.getOptionalCountryID(), "INFO");
                queryString.append("inner join Addresses as addr on d.addressId = addr.addressId inner join country as cou on addr.countryId = cou.countryId ");
                if (deviceCountReport.getOptionalCountryID().matches("[0-9]+")) {
                    queryString.append(" and cou.countryId = " + Integer.valueOf(deviceCountReport.getOptionalCountryID()) + " ");
                } else {
                    queryString.append(" and cou.countryName like '%" + deviceCountReport.getOptionalCountryID() + "%' ");
                }
            }

            queryString.append(" order by d.deviceName asc");
            System.out.println(queryString.toString());

            SQLQuery query = session.createSQLQuery(queryString.toString());



            query.addScalar("deviceId", Hibernate.STRING);
//            query.addScalar("groupId", Hibernate.STRING);
//            query.addScalar("deviceName", Hibernate.STRING);
//            query.addScalar("deviceName2", Hibernate.STRING);
//            query.addScalar("deviceTypeId", Hibernate.STRING);
//            query.addScalar("assetId", Hibernate.STRING);
//            query.setResultTransformer(Transformers.aliasToBean(Device.class));
            return query.list();
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        }
        return object;
    }

    public EventMessageDocument createEM(Device device, Services service, String Subject, String body, String userKey, int minute, String outlet, String sentBy, String groupName, String commandValue) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        EventMessageDocument emd = EventMessageDocument.Factory.newInstance();
        EventMessage em = emd.addNewEventMessage();
        em.setEventID(UUID.randomUUID().toString().toUpperCase());
        em.setEventTypeID("80b58d09-bd08-4085-9ba9-5cd3d45df41a".toUpperCase());
        em.setEventTypeName("Command Event");
        em.setEventTime(cal);
        em.setEventClosedTime(cal);
        em.setSubject(Subject);
        em.setBody(body);
        em.setActive((short) 1);
        Details details = em.addNewDetails();
        Detail detail = details.addNewDetail();
        detail.setDataType(se.info24.protocols.em.DataType.STRING);
        detail.setName("Command ");
        detail.setStringValue(body);

        if (userKey != null) {
            detail = details.addNewDetail();
            detail.setDataType(se.info24.protocols.em.DataType.STRING);
            detail.setName("User key");
            detail.setStringValue(userKey);
        }

        if (minute > 0) {
            detail = details.addNewDetail();
            detail.setDataType(se.info24.protocols.em.DataType.INT);
            detail.setName("Charge time ");
            detail.setStringValue(minute + "");
        }

        if (outlet != null) {
            detail = details.addNewDetail();
            detail.setDataType(se.info24.protocols.em.DataType.STRING);
            detail.setName("Outlet");
            detail.setStringValue(outlet);
        }

        if (commandValue != null) {
            detail = details.addNewDetail();
            detail.setDataType(se.info24.protocols.em.DataType.STRING);
            detail.setName("CommandValue ");
            detail.setStringValue(commandValue);
        }
        detail = details.addNewDetail();
        detail.setDataType(se.info24.protocols.em.DataType.STRING);
        detail.setName("Sent By");
        detail.setStringValue(sentBy);

        detail = details.addNewDetail();
        detail.setDataType(se.info24.protocols.em.DataType.STRING);
        detail.setName("Organization");
        detail.setStringValue(groupName);

        em.setPriority((short) 0);

        em.setSourceTypeID(device.getDeviceTypes().getDeviceTypeId());
        em.setSourceTypeName(device.getDeviceTypes().getDeviceTypeName());
        em.setSourceID(device.getDeviceId());
        em.setSourceName(device.getDeviceName());
        em.setTargetTypeID(service.getServiceTypes().getServiceTypeId());
        em.setTargetTypeName(device.getDeviceTypes().getDeviceTypeName());
        em.setTargetID(TingcoConstants.getServiceID());
        em.setTargetName(service.getServiceTypes().getServiceTypeName());
        return emd;
    }

    public void sendEventMessage(EventMessageDocument emd, String topicName) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            XmlOptions options = new XmlOptions();
            options.setSavePrettyPrint();
            emd.save(baos, options);
            Connection connection = null;
            connection = (Connection) TingcoConstants.getGenericPool().borrowObject();
            javax.jms.Session pubSession = connection.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
            javax.jms.Topic topic = pubSession.createTopic(topicName);
            javax.jms.MessageProducer publisher = pubSession.createProducer(topic);
            connection.start();
            progress.message.jclient.XMLMessage xMsg = ((progress.message.jclient.Session) pubSession).createXMLMessage();
            xMsg.setText(baos.toString());
            publisher.send(xMsg);
            pubSession.close();
            TingcoConstants.getGenericPool().returnObject(connection);
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }

    }

    public List<EventTypesInGroups> getEventTypesInGroupsbyGroupIDs(Set<String> groupIdsList, Session session) {
        return session.getNamedQuery("getEventTypesInGroupsbyGroupIDs").setParameterList("groupId", groupIdsList).list();
    }

    public List<EventLog> getEventLogbyEventTypeIdandEventTime(List<String> eventTypeIdList, String fromTime, String toTime, Session session, int maxResult, String deviceId) {
        return session.getNamedQuery("getEventLogbyEventTypeIdandEventTime").setParameterList("eventTypeId", eventTypeIdList).setString("fromDate", fromTime).setString("toDate", toTime).setString("sourceId", deviceId).setMaxResults(maxResult).list();
    }

    public List<EventTypesInGroups> getEventTypesInGroupsbyGroupIdandeventTypeId(Session session, String eventTypeId, String groupId) {
        return session.getNamedQuery("getEventTypesInGroupsbyGroupIdandeventTypeId").setString("groupId", groupId).setString("eventTypeId", eventTypeId).list();
    }

    public boolean insertEventTypeForGroup(Session session, EventTypesInGroups eventTypesInGroups) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(eventTypesInGroups);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public boolean deleteEventTypeForGroup(Session session, EventTypesInGroups get) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(get);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public boolean saveServiceClientLogins(Session session, ServiceClientLogins serviceClientLogin) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(serviceClientLogin);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public boolean addConnectorToDevice(Session session, Connectors connector) {
        try {
            session.saveOrUpdate(connector);
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public List<DeviceManufacturers> getAllDeviceManufacturersBySearchString(Session session, String searchString) {
        return session.getNamedQuery("getAllDeviceManufacturersBySearchString").setString("searchString", searchString).setMaxResults(200).list();
    }

    public List<Connectors> getConnectorsLinkedToDevice(String deviceId, Session session) {
        return session.getNamedQuery("getConnectorsByDeviceId").setString("deviceId", deviceId).list();
    }

    public List<Connectors> getConnectorsLinkedToDevices(Set<String> deviceId, Session session) {
        return session.getNamedQuery("getConnectorsByDeviceIds").setParameterList("deviceId", deviceId).list();
    }

    public boolean addDeviceTypes(DeviceTypesInGroups deviceTypesInGroups, DeviceTypes deviceTypes, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(deviceTypes);
            session.saveOrUpdate(deviceTypesInGroups);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public List<RecurringPurchases> getRecurringPurchasesByID(Session session, String recurringPurchasesID) {
        return session.getNamedQuery("getRecurringPurchasesByID").setString("recurringPurchaseId", recurringPurchasesID).list();
    }

    public boolean deleteRecurringPurchaseByID(Session session, RecurringPurchases get) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(get);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }

    public List<RecurringPurchases> getRecurringPurchasesByDeviceId(Session session, String deviceId) {
        return session.getNamedQuery("getRecurringPurchasesByDeviceId").setString("deviceId", deviceId).list();
    }

    public List<UserAlias> getUserAliasDetailsByUserAliasIDs(Session session, List<String> userAliasId) {
        return session.getNamedQuery("getUserAliasDetailsByUserAliasIDs").setParameterList("userAliasId", userAliasId).list();
    }

    List<Connectors> getConnectorsByDeviceIdSortedByConnectorName(String deviceId, Session session) {
        return session.getNamedQuery("getConnectorsByDeviceIdSortedByConnectorName").setString("deviceId", deviceId).list();
    }

    List<ConnectorTypes> getAllConnectorsTypesOrderByConnectorTypeName(Session session) {
        return session.getNamedQuery("getAllConnectorsTypesOrderByConnectorTypeName").list();
    }

    List<ConnectorModes> getAllConnectorsModesOrderByConnectorModeName(Session session) {
        return session.getNamedQuery("getAllConnectorsModesOrderByConnectorModeName").list();
    }

    List<ConnectorVoltages> getAllConnectorVoltagesOrderByConnectorVoltageName(Session session) {
        return session.getNamedQuery("getAllConnectorVoltagesOrderByConnectorVoltageName").list();
    }

    List<ConnectorCurrents> getAllConnectorCurrentsOrderByConnectorCurrentName(Session session) {
        return session.getNamedQuery("getAllConnectorCurrentsOrderByConnectorCurrentName").list();
    }

    List<ConnectorAcdc> getAllConnectorAcdcOrderByConnectorACDCName(Session session) {
        return session.getNamedQuery("getAllConnectorAcdcOrderByConnectorACDCName").list();
    }

    List<LoadBalance> getAllLoadBalanceOrderByLoadBalanceName(Session session) {
        return session.getNamedQuery("getAllLoadBalanceOrderByLoadBalanceName").list();
    }

    Object getConnectorsByConnectorID(String connectorId, Session session) {
        return session.getNamedQuery("getConnectorsByConnectorID").setString("connectorId", connectorId).uniqueResult();
    }

    void deleteConnector(Connectors connectors, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            tx.begin();
            session.delete(connectors);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    Object getAllLoadBalanceByLoadBalanceID(String loadBalanceId, Session session) {
        return session.getNamedQuery("getAllLoadBalanceByLoadBalanceID").setString("loadBalanceId", loadBalanceId).uniqueResult();
    }

    public List<DeviceDataitemTranslations> getDeviceDataItemTranslationsByDataItemIdorderBydataItemName(List<String> deviceDataItemID, Session session) {
        return session.getNamedQuery("getDeviceDataItemTranslationsByDataItemIdorderBydataItemName").setParameterList("deviceDataItemId", deviceDataItemID).list();
    }

    Object getDeviceTypeChannelsByID(Session session, String deviceTypeId, String channelid, int channelDirection) {
        return session.getNamedQuery("getDeviceTypeChannelsByID").setString("deviceTypeId", deviceTypeId).setString("channelId", channelid).setInteger("channelDirection", channelDirection).uniqueResult();
    }

    void deleteDeviceTypeChannels(Session session, DeviceTypeChannels deviceTypeChannels) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            tx.begin();
            session.delete(deviceTypeChannels);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    void saveOrUpdateDeviceTypeChannels(Session session, DeviceTypeChannels deviceTypeChannels) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
//            tx.begin();
            session.saveOrUpdate(deviceTypeChannels);
            tx.commit();
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    List<DeviceDataItems> getAllDeviceDataItems(Session session) {
        return session.getNamedQuery("getAllDeviceDataItems").setMaxResults(2000).list();
    }

    void saveDeviceTypeDataitems(DeviceTypeDataitems deviceTypeDataitems, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            tx.begin();
            session.saveOrUpdate(deviceTypeDataitems);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    void deleteDeviceTypeDataitems(DeviceTypeDataitems deviceTypeDataitems, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            tx.begin();
            session.delete(deviceTypeDataitems);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public Object getDeviceDataItemsByItemId(String deviceDataItemId, Session session) {
        return session.getNamedQuery("getDeviceDataItemsByItemId").setString("id", deviceDataItemId).uniqueResult();
    }

    List<DeviceDataitemTranslations> getDeviceDataItemTranslationsByDataItemId(String deviceDataItemID, Session session) {
        return session.getNamedQuery("getDeviceDataItemTranslationsByDataItemId").setString("deviceDataItemId", deviceDataItemID).list();
    }

    List<DeviceTypeCommands> getAllDeviceTypeCommands(Session session) {
        return session.getNamedQuery("getAllDeviceTypeCommands").list();
    }

    public List<UserRoleObjectPermissions2> getUserRoleObjectPermissions2ByUserRoleId(Session session, String userRoleID) {
        return session.getNamedQuery("getUserRoleObjectPermissions2ByUserId").setString("userRoleID", userRoleID).list();
    }

    public List<PermissionTranslations> getPermissionTranslationsByIdsAndCountry(Session session, List<String> permissionid, int countryId) {
        return session.getNamedQuery("getPermissionTranslationsByIdsAndCountry").setParameterList("permissionId", permissionid).setInteger("countryID", countryId).list();
    }

    List<ObjectMediaFiles> getObjectMediaFilesByMediaFileID(Session session, String mediaFileId) {
        return session.getNamedQuery("getObjectMediaFilesByMediaFileID").setString("mediaFileId", mediaFileId).list();
    }

    Object getMediaFilesByMediaFileID(Session session, String mediaFileID) {
        return session.getNamedQuery("getMediaFilesByMediaFileID").setString("mediaFileId", mediaFileID).uniqueResult();
    }

    List<Object> filterMediaFiles(se.info24.devicejaxbPost.MediaFiles mediaFilesXml, int countryId, Set<String> groupTrustedIDSet, String loggedInUserGroupID, Session session) {
        StringBuffer queryString = new StringBuffer();

        queryString.append("Select mf.mediaFileId from MediaFiles mf ");
        queryString.append(" inner join ISM.dbo.groups as g on mf.groupId = g.groupId and g.groupId in (" + TCMUtil.getGroupTrust(loggedInUserGroupID) + ")");
        if (mediaFilesXml.getGroupName() != null || mediaFilesXml.getGroupID() != null) {
            if (mediaFilesXml.getGroupID() != null) {
                queryString.append(" inner join ISM.dbo.GroupTranslations as gts on mf.groupId = gts.groupId and gts.groupId ='" + mediaFilesXml.getGroupID() + "' and gts.countryId = " + countryId + " ");
            } else {
                queryString.append(" inner join ISM.dbo.GroupTranslations as gts on mf.groupId = gts.groupId and gts.groupName like '%" + mediaFilesXml.getGroupName() + "%' and gts.countryId = " + countryId + " ");
            }
        }
        if (mediaFilesXml.getLastUpdatedByUserID() != null) {
            if (mediaFilesXml.getLastUpdatedByUserID().getValue() != null && TCMUtil.isValidUUID(mediaFilesXml.getLastUpdatedByUserID().getValue())) {
                queryString.append(" inner join ISM.dbo.Users2 as u2 on mf.lastUpdatedByUserId = u2.userId and u2.userId ='" + mediaFilesXml.getLastUpdatedByUserID().getValue() + "'");
            }
            if (mediaFilesXml.getLastUpdatedByUserID().getUserFullName() != null) {
                queryString.append(" inner join ISM.dbo.Users2 as u2 on mf.lastUpdatedByUserId = u2.userId and u2.firstName like '%" + mediaFilesXml.getLastUpdatedByUserID().getUserFullName() + "%' or u2.lastName like '%" + mediaFilesXml.getLastUpdatedByUserID().getUserFullName() + "%' or (u2.firstName+' '+u2.lastName) like '%" + mediaFilesXml.getLastUpdatedByUserID().getUserFullName() + "%' ");
            }
        }
        if (mediaFilesXml.getObjectTags() != null) {
            if (mediaFilesXml.getObjectTags().getObjectID() != null && TCMUtil.isValidUUID(mediaFilesXml.getObjectTags().getObjectID())) {
                queryString.append(" inner join ISM.dbo.ObjectTags as ots on mf.mediaFileId = ots.objectId and ots.objectId = ='" + mediaFilesXml.getObjectTags().getObjectID() + "'");
            }
            if (mediaFilesXml.getObjectTags().getSearchTags() != null) {
                queryString.append(" inner join ISM.dbo.ObjectTags as ots on mf.mediaFileId = ots.objectId and ots.searchTags like '%" + mediaFilesXml.getObjectTags().getSearchTags() + "%'");
            }
        }
        if (mediaFilesXml.getMediaFileTypeID() != null) {
            queryString.append(" and mf.mediaFileTypeId ='" + mediaFilesXml.getMediaFileTypeID() + "'");
        }
        if (mediaFilesXml.getMediaFileName() != null) {
            queryString.append(" and (mf.mediaFileName like '%" + mediaFilesXml.getMediaFileName() + "%' or mf.mediaFileDescription like '%" + mediaFilesXml.getMediaFileName() + "%')");
        }

        queryString.append(" order by mediaFileName");
        TCMUtil.loger(this.getClass().getName(), queryString.toString(), "info");
        SQLQuery query = session.createSQLQuery(queryString.toString());
        query.addScalar("mediaFileId", Hibernate.STRING);
        query.setMaxResults(200);
        List<Object> filteredMediaFiles = query.list();
        TCMUtil.loger(this.getClass().getName(), filteredMediaFiles.size() + "", "info");
        return filteredMediaFiles;
    }

    public List<DeviceBillingCategories> getDeviceBillingCategories(Session session, String deviceID) {
        return session.getNamedQuery("getDeviceBillingCategories").setString("deviceId", deviceID).list();
    }

    public List<BillingCategoryTranslations> getBillingCategoryTranslations(Session session, String billingCategoryId, int countryId) {
        return session.getNamedQuery("getBillingCategoryTranslations").setString("billingCategoryId", billingCategoryId).setInteger("countryId", countryId).list();
    }

    public List<DeviceTypeBillingCategories> getDeviceTypeBillingCategoriesByDeviceTypeID(String deviceTypeId, Session session) {
        return session.getNamedQuery("getDeviceTypeBillingCategoriesByDeviceTypeID").setString("deviceTypeId", deviceTypeId).list();
    }

    List<BillingCategoryTranslations> getBillingCategoryTranslationsByIDs(Set<String> billingCategoryIDs, int countryId, Session session) {
        return session.getNamedQuery("getBillingCategoryTranslationsByIDs").setParameterList("billingCategoryId", billingCategoryIDs).setInteger("countryId", countryId).list();
    }

    List<BillingCategories> getBillingCategoriesByBillingCategoryIDAndGroupID(Set<String> billingCategoryIDs, List<String> list, Session session) {
        return session.getNamedQuery("getBillingCategoriesByBillingCategoryIDAndGroupID").setParameterList("billingCategoryId", billingCategoryIDs).setParameterList("groupId", list).list();
    }

    List<BillingCategories> getBillingCategoriesByBillingCategoryID(Set<String> resultIDs, Session session, int maxResult) {
        return session.getNamedQuery("getBillingCategoriesByBillingCategoryID").setParameterList("billingCategoryId", resultIDs).setMaxResults(maxResult).list();
    }

    List<BillingCategoryTranslations> getBillingCategoryTranslationsBySearchString(Set<String> categoryIDs, int countryId, String searchString, Session session) {
        return session.getNamedQuery("getBillingCategoryTranslationsBySearchString").setParameterList("billingCategoryId", categoryIDs).setInteger("countryId", countryId).setString("categoryName", searchString).list();
    }

    List<BillingCategories> getBillingCategoriesByGroupIDs(List<String> list, Session session) {
        return session.getNamedQuery("getBillingCategoriesByGroupIDs").setParameterList("groupId", list).list();
    }

    boolean saveDeviceTypeBillingCategories(Session session, List<DeviceTypeBillingCategories> newDeviceTypeBillingCategorieses) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (DeviceTypeBillingCategories dtbc : newDeviceTypeBillingCategorieses) {
                session.saveOrUpdate(dtbc);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(this.getClass().getName(), e);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    Object getDeviceTypeBillingCategoriesByDeviceTypeIDAndBillingCategoryID(String deviceTypeId, String billingCategoryId, Session session) {
        return session.getNamedQuery("getDeviceTypeBillingCategoriesByDeviceTypeIDAndBillingCategoryID").setString("deviceTypeId", deviceTypeId).setString("billingCategoryId", billingCategoryId).uniqueResult();
    }

    boolean deleteDeviceTypeBillingCategories(DeviceTypeBillingCategories dtbc, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(dtbc);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(this.getClass().getName(), e);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    List<EventTypesInTypes> getEventTypesInTypesByDeviceTypeID(String deviceTypeId, Session session) {
        return session.getNamedQuery("getEventTypesInTypesByDeviceTypeID").setString("itemId", deviceTypeId).list();
    }

    /*Object getEventTypesInTypesByEventTypeIDAndItemID(String eventTypeId, String deviceTypeId, Session session) {
    return session.getNamedQuery("getEventTypesInTypesByEventTypeIDAndItemID").setString("eventTypeId", eventTypeId).setString("itemId", eventTypeId).uniqueResult();
    }*/
    List<EventTypesInTypes> getEventTypesInTypesByEventTypeIDAndItemID(String eventTypeId, String deviceTypeId, Session session) {
        return session.getNamedQuery("getEventTypesInTypesByEventTypeIDAndItemID").setString("eventTypeId", eventTypeId).setString("itemId", deviceTypeId).list();
    }

    boolean deleteEventTypesInTypes(List<EventTypesInTypes> eventTypesInTypeses, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (EventTypesInTypes eventTypesInTypes : eventTypesInTypeses) {
                session.delete(eventTypesInTypes);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(this.getClass().getName(), e);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    boolean saveEventTypesInTypes(EventTypesInTypes eventTypesInTypes, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(eventTypesInTypes);
            tx.commit();
            return true;
        } catch (Exception e) {
            TCMUtil.exceptionLog(this.getClass().getName(), e);
            if (tx != null) {
                tx.rollback();
            }
            return false;
        }
    }

    Object getEventTypesInTypesByEventTypeIDAndItemIDAndTargetID(String eventTypeId, String deviceTypeId, String targetId, Session session) {
        return session.getNamedQuery("getEventTypesInTypesByEventTypeIDAndItemIDAndTargetID").setString("eventTypeId", eventTypeId).setString("itemId", deviceTypeId).setString("targetId", targetId).uniqueResult();
    }

    boolean savedeviceBillingCategories(DeviceBillingCategories deviceBillingCategories, Session session) {
        Transaction tx = null;
        boolean result = false;
        try {
            tx = session.beginTransaction();
            if (deviceBillingCategories != null) {
                session.saveOrUpdate(deviceBillingCategories);
                tx.commit();
                result = true;
            }
        } catch (Exception he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            result = false;
        }
        return result;
    }

    public List<DeviceActivationSchedules> getChargePointStatusWidgetDetails(Session session, Set<String> deviceId, List<String> dataItem, List<String> dataItemGroup) {
        return session.getNamedQuery("getChargePointStatusWidgetDetails").setParameterList("deviceId", deviceId).setParameterList("dataItem", dataItem).setParameterList("dataItemGroup", dataItemGroup).list();
    }

    boolean deleteDTC(DeviceTypeCommands deviceTypeCommands, List<DeviceTypeCommandTranslations> deviceTypeCommandTranslationses, List<DeviceCommandSchedules> deviceCommandScheduleses, Session session) {
        Transaction tx = null;
        boolean result = false;
        try {
            tx = session.beginTransaction();
            for (DeviceCommandSchedules deviceCommandSchedules : deviceCommandScheduleses) {
                session.delete(deviceCommandSchedules);
            }
            for (DeviceTypeCommandTranslations dtct : deviceTypeCommandTranslationses) {
                session.delete(dtct);
            }
            session.delete(deviceTypeCommands);

            tx.commit();
            return true;
        } catch (Exception he) {
            TCMUtil.exceptionLog(getClass().getName(), he);
            if (tx != null) {
                tx.rollback();
            }
            result = false;
        }
        return result;
    }

    Object getDeviceTypeCommandTranslationsByDeviceTypeCommandIDAndCountryId(Session session, String deviceTypeCommandId, int countryId) {
        return session.getNamedQuery("getDeviceTypeCommandTranslationsByDeviceTypeCommandIDAndCountryId").setString("deviceTypeCommandId", deviceTypeCommandId).setInteger("countryId", countryId).uniqueResult();
    }
}
