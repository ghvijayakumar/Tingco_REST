/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.jaxbUtil;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Session;
import se.info24.device.DeviceDAO;
import se.info24.group.GroupDAO;
import se.info24.groupsjaxb.AccessLogs;
import se.info24.groupsjaxb.Address;
import se.info24.groupsjaxb.ApplicationSolution;
import se.info24.groupsjaxb.ApplicationSolutions;
import se.info24.groupsjaxb.ContactTypes;
import se.info24.groupsjaxb.Contacts;
import se.info24.groupsjaxb.CountryID;
import se.info24.groupsjaxb.DeliveryAccount;
import se.info24.groupsjaxb.DeliveryAccounts;
import se.info24.groupsjaxb.DeviceGroupID;
import se.info24.groupsjaxb.EventTypeID;
import se.info24.groupsjaxb.FAQ;
import se.info24.groupsjaxb.FAQs;
import se.info24.groupsjaxb.Group;
import se.info24.groupsjaxb.GroupApplicationPackage;
import se.info24.groupsjaxb.GroupDescription;
import se.info24.groupsjaxb.GroupDescriptions;
import se.info24.groupsjaxb.GroupName;
import se.info24.groupsjaxb.GroupNames;
import se.info24.groupsjaxb.Groups;
import se.info24.groupsjaxb.LastUpdatedByUserID;
import se.info24.groupsjaxb.MsgStatus;
import se.info24.groupsjaxb.ObjectFactory;
import se.info24.groupsjaxb.ObjectGroups;
import se.info24.groupsjaxb.TingcoGroups;
import se.info24.groupsjaxb.UserGroupID;
import se.info24.ismOperationsPojo.AccessLog;
import se.info24.pojo.Addresses;
import se.info24.pojo.ApplicationPackageTranslations;
import se.info24.pojo.ApplicationSolutionTranslations;
import se.info24.pojo.ContactFieldTranslations;
import se.info24.pojo.ContactGroups;
import se.info24.pojo.ContactTypeDefaultFields;
import se.info24.pojo.ContactTypeDetails;
import se.info24.pojo.ContactTypeTranslations;
import se.info24.pojo.ContactTypesInContacts;
import se.info24.pojo.CostCenters;
import se.info24.pojo.Country;
import se.info24.pojo.EventTypeTranslations;
import se.info24.pojo.Faq;
import se.info24.pojo.GroupAlias;
import se.info24.pojo.GroupDefaultAgreement;
import se.info24.pojo.GroupDefaultUserAlias;
import se.info24.pojo.GroupLimitPackages;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.GroupTrustsId;
import se.info24.pojo.GroupTypeTranslations;
import se.info24.pojo.ObjectGroupTranslations;
import se.info24.pojo.Ogmcontainers;
import se.info24.pojo.Ogmdevice;
import se.info24.pojo.Schedules;
import se.info24.pojo.ServiceClientLogins;
import se.info24.pojo.UserAlias;
import se.info24.pojo.UserFavoriteGroups;
import se.info24.pojo.Users2;
import se.info24.restUtil.RestUtilDAO;
import se.info24.tcp.TingcoCustomerDAO;
import se.info24.util.TCMUtil;

/**
 *
 * @author Sekhar
 */
public class TingcoGroupXML {

    GroupDAO groupDAO = new GroupDAO();
    DeviceDAO deviceDAO = new DeviceDAO();

//    public TingcoGroups buildAccessLogDetails(TingcoGroups tingcoGroups, List<AccessLog> accessLogList, String timeZoneGMToffset, int countryId, Session session) throws DatatypeConfigurationException {
//        GregorianCalendar gc = new GregorianCalendar();
//        Groups groups = new Groups();
//        AccessLogs accessLogs = new AccessLogs();
//        for (AccessLog access : accessLogList) {
//            se.info24.groupsjaxb.AccessLog accessLog = new se.info24.groupsjaxb.AccessLog();
//            accessLog.setAccessLogRowID(access.getAccessLogRowId());
//            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, access.getAccessTime()));
//            accessLog.setAccessTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
//            accessLog.setDeviceName(access.getDeviceName());
//            accessLog.setFirstName(access.getFirstName());
//            accessLog.setLastName(access.getLastName());
//            accessLog.setUserAlias(access.getUserAlias());
//            EventTypeTranslations eventTypeTrans = deviceDAO.getEventTypeTranslations(access.getEventTypeId(), countryId, session);
//            if (eventTypeTrans != null) {
//                se.info24.groupsjaxb.EventTypeID eventId = new EventTypeID();
//                eventId.setEventTypeName(eventTypeTrans.getEventTypeName());
//                accessLog.setEventTypeID(eventId);
//            }
//            accessLogs.getAccessLog().add(accessLog);
//        }
//        groups.getAccessLogs().add(accessLogs);
//        tingcoGroups.setGroups(groups);
//        return tingcoGroups;
//    }
    public TingcoGroups buildAccessLogDetails(TingcoGroups tingcoGroups, List<Object> accessLogList, String timeZoneGMToffset, int countryId, Session session) throws DatatypeConfigurationException, ParseException {
        GregorianCalendar gc = new GregorianCalendar();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Groups groups = new Groups();
        AccessLogs accessLogs = new AccessLogs();
        se.info24.groupsjaxb.AccessLog accessLog = null;
        for (Iterator itr = accessLogList.iterator(); itr.hasNext();) {
            accessLog = new se.info24.groupsjaxb.AccessLog();
            Object[] row = (Object[]) itr.next();
            String fullName = "";
            for (int i = 0; i < row.length; i++) {
                if (i % 2 == 0) {
                    if (row[i] != null) {
                        accessLog.setAccessLogRowID(row[i].toString());
                    }
                    if (row[i + 1] != null) {
                        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, dateFormat.parse(row[i + 1].toString())));
                        accessLog.setAccessTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, dateFormat2.parse(row[i + 1].toString())));
                        accessLog.setAccessTimeTCMV3(dateFormat2.format(gc.getTime()));
                    }
                    if (row[i + 2] != null) {
                        accessLog.setDeviceName(row[i + 2].toString());
                    }
                    if (row[i + 3] != null) {
                        accessLog.setFirstName(row[i + 3].toString());
                        fullName = fullName + row[i + 3].toString();
                    }
                    if (row[i + 4] != null) {
                        accessLog.setLastName(row[i + 4].toString());
                        fullName = fullName + " " + row[i + 4].toString();
                    }
                    if (row[i + 5] != null) {
                        accessLog.setUserAlias(row[i + 5].toString());
                        fullName = fullName + " (" + row[i + 5].toString() + ")";
                    }
                    if (row[i + 6] != null) {
                        se.info24.groupsjaxb.EventTypeID eventId = new EventTypeID();
                        eventId.setEventTypeName(row[i + 6].toString());
                        accessLog.setEventTypeID(eventId);
                    }
                    i += 6;
                }
            }
            if (!fullName.equalsIgnoreCase("")) {
                accessLog.setFullName(fullName);
            }
            accessLogs.getAccessLog().add(accessLog);
        }
        groups.getAccessLogs().add(accessLogs);
        tingcoGroups.setGroups(groups);
        return tingcoGroups;
    }

    public TingcoGroups buildAccessLogMoreInformation(TingcoGroups tingcoGroups, AccessLog accesslog, String userName, String DeviceName, String eventTypeName, String timezoneOffset) throws DatatypeConfigurationException {
        GregorianCalendar gc = new GregorianCalendar();
        Groups groups = new Groups();
        AccessLogs accessLogs = new AccessLogs();
        DecimalFormat decimalFormat = new DecimalFormat("0.000000");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        decimalFormat.setMinimumFractionDigits(6);
        se.info24.groupsjaxb.AccessLog access = new se.info24.groupsjaxb.AccessLog();
        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timezoneOffset, accesslog.getAccessTime()));
        access.setAccessTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
        access.setAccessTimeTCMV3(dateFormat.format(gc.getTime()));
        access.setDeviceName(accesslog.getDeviceName());
        access.setFirstName(accesslog.getFirstName());
        access.setLastName(accesslog.getLastName());
        access.setUserAlias(accesslog.getUserAlias());
        if (eventTypeName != null) {
            se.info24.groupsjaxb.EventTypeID eventid = new EventTypeID();
            eventid.setEventTypeName(eventTypeName);
            eventid.setValue(accesslog.getEventTypeId());
            access.setEventTypeID(eventid);
        }
        if (DeviceName != null) {
            DeviceGroupID deviceId = new DeviceGroupID();
            deviceId.setDeviceGroupName(DeviceName);
            deviceId.setValue(accesslog.getDeviceGroupId());
            access.setDeviceGroupID(deviceId);
        }
        if (userName != null) {
            UserGroupID userId = new UserGroupID();
            userId.setUserGroupName(userName);
            userId.setValue(accesslog.getUserGroupId());
            access.setUserGroupID(userId);
        }
        if (accesslog.getResource() != null) {
            access.setResource(accesslog.getResource());
        }
        if (accesslog.getUserAliasTypeName() != null) {
            access.setUserAliasTypeName(accesslog.getUserAliasTypeName());
        }
        if (accesslog.getPosLatitude() != null) {
            access.setPosLatitude(decimalFormat.format(Double.valueOf(accesslog.getPosLatitude().replace(",", "."))));
        }
        if (accesslog.getPosLongitude() != null) {
            access.setPosLongitude(decimalFormat.format(Double.valueOf(accesslog.getPosLongitude().replace(",", "."))));
        }
        if (accesslog.getPosAltitude() != null) {
            access.setPosAltitude((int) Float.parseFloat(accesslog.getPosAltitude()));
        }

        if (accesslog.getLocation() != null) {
            access.setLocation(accesslog.getLocation());
        }
        accessLogs.getAccessLog().add(access);
        groups.getAccessLogs().add(accessLogs);
        tingcoGroups.setGroups(groups);
        return tingcoGroups;
    }

    public TingcoGroups buildEventTypes(TingcoGroups tingcoGroups, List<EventTypeTranslations> ettList) {
        se.info24.groupsjaxb.EventTypess eventTypess = new se.info24.groupsjaxb.EventTypess();
        se.info24.groupsjaxb.EventTypes eventType = new se.info24.groupsjaxb.EventTypes();
        se.info24.groupsjaxb.EventTypeTranslations eventTypeTranslationsjaxb = null;
        for (EventTypeTranslations eventTypeTranslations : ettList) {
            eventTypeTranslationsjaxb = new se.info24.groupsjaxb.EventTypeTranslations();
            eventTypeTranslationsjaxb.setEventTypeID(eventTypeTranslations.getId().getEventTypeId());
            eventTypeTranslationsjaxb.setCountryID(eventTypeTranslations.getId().getCountryId());
            eventTypeTranslationsjaxb.setEventTypeName(eventTypeTranslations.getEventTypeName());
            eventType.getEventTypeTranslations().add(eventTypeTranslationsjaxb);
        }
        eventTypess.getEventTypes().add(eventType);
        tingcoGroups.setEventTypess(eventTypess);
        return tingcoGroups;
    }

    public TingcoGroups buildGetContactDetails(TingcoGroups tingcoGroups, se.info24.pojo.Contacts contact, GroupTranslations groupTrans, List<ContactTypeTranslations> contactTypeTrans, Integer countryId, Session session) {
        Groups groups = new Groups();
        Group group = new Group();
        Contacts contacts = new Contacts();
        contacts.setContactID(contact.getContactId());
        contacts.setContactName(contact.getContactName());
        contacts.setContactDescription(contact.getContactDescription());
        contacts.setGroupID(contact.getGroupId());
        contacts.setGroupName(groupTrans.getGroupName());
        for (ContactTypeTranslations ctt : contactTypeTrans) {
            ContactTypes contactTypes = new ContactTypes();
            se.info24.groupsjaxb.ContactTypeTranslations contactTypeTranslations = new se.info24.groupsjaxb.ContactTypeTranslations();
            contactTypeTranslations.setContactTypeID(ctt.getId().getContactTypeId());
            contactTypeTranslations.setContactTypeName(ctt.getContactTypeName());
            contactTypes.getContactTypeTranslations().add(contactTypeTranslations);

            List<ContactTypeDefaultFields> ctdfList = groupDAO.getContactTypeDefaultFieldsByContactTypeId(ctt.getId().getContactTypeId(), session);
            for (ContactTypeDefaultFields ctdf : ctdfList) {
                ContactFieldTranslations contactFieldTrans = groupDAO.getcontactFieldTranslationsByIds(ctdf.getId().getContactTypeFieldId(), countryId, session);
                ContactTypesInContacts ctic = groupDAO.getContactTypesInContactsByIds(contact.getContactId(), ctt.getId().getContactTypeId(), session);
                se.info24.groupsjaxb.ContactTypeDetails contactTypeDetails = new se.info24.groupsjaxb.ContactTypeDetails();
                if (ctic != null) {
                    List<ContactTypeDetails> ctdList = groupDAO.getcontactTypeDetailsByIds(ctic.getContactTypeInCoId(), ctdf.getId().getContactTypeFieldId(), session);
                    if (!ctdList.isEmpty()) {
                        contactTypeDetails.setContactDetailID(ctdList.get(0).getContactDetailId());
                        contactTypeDetails.setContactFieldValue(ctdList.get(0).getContactFieldValue());
                    }
                }
                contactTypeDetails.setContactTypeFieldID(contactFieldTrans.getId().getContactTypeFieldId());
                contactTypeDetails.setContactFieldName(contactFieldTrans.getContactFieldName());
                contactTypes.getContactTypeDetails().add(contactTypeDetails);
            }
            contacts.getContactTypes().add(contactTypes);
        }
        group.getContacts().add(contacts);
        groups.getGroup().add(group);
        tingcoGroups.setGroups(groups);

        return tingcoGroups;
    }

    public TingcoGroups buildGetContactGroupsForContact(TingcoGroups tingcoGroups, List<ContactGroups> ctGroups, Integer countryId, Session session) {
        Groups groups = new Groups();
        Group group = new Group();
        Contacts contacts = new Contacts();
        for (ContactGroups cg : ctGroups) {
            se.info24.groupsjaxb.ContactGroups contactGroups = new se.info24.groupsjaxb.ContactGroups();
            contactGroups.setContactGroupID(cg.getContactGroupId());
            contactGroups.setContactGroupName(cg.getContactGroupName());
            se.info24.pojo.GroupTranslations grouptrans = groupDAO.getGroupTranslationsByIds(cg.getGroupId(), countryId, session);
            contactGroups.setGroupID(cg.getGroupId());
            contactGroups.setGroupName(grouptrans.getGroupName());
            contacts.getContactGroups().add(contactGroups);
        }
        group.getContacts().add(contacts);
        groups.getGroup().add(group);
        tingcoGroups.setGroups(groups);
        return tingcoGroups;
    }

    public TingcoGroups buildGetContactTypeDetails(TingcoGroups tingcoGroups, List<ContactTypeTranslations> contactTypeTrans, Integer countryId, Session session) {
        Groups groups = new Groups();
        Group group = new Group();
        Contacts contacts = new Contacts();
        for (ContactTypeTranslations ctt : contactTypeTrans) {
            ContactTypes contactTypes = new ContactTypes();
            se.info24.groupsjaxb.ContactTypeTranslations contactTypeTranslations = new se.info24.groupsjaxb.ContactTypeTranslations();
            contactTypeTranslations.setContactTypeID(ctt.getId().getContactTypeId());
            contactTypeTranslations.setContactTypeName(ctt.getContactTypeName());
            contactTypes.getContactTypeTranslations().add(contactTypeTranslations);

            List<ContactTypeDefaultFields> ctdfList = groupDAO.getContactTypeDefaultFieldsByContactTypeId(ctt.getId().getContactTypeId(), session);
            for (ContactTypeDefaultFields ctdf : ctdfList) {
                ContactFieldTranslations contactFieldTrans = groupDAO.getcontactFieldTranslationsByIds(ctdf.getId().getContactTypeFieldId(), countryId, session);
                se.info24.groupsjaxb.ContactTypeDetails ctft = new se.info24.groupsjaxb.ContactTypeDetails();
                ctft.setContactTypeFieldID(contactFieldTrans.getId().getContactTypeFieldId());
                ctft.setContactFieldName(contactFieldTrans.getContactFieldName());
                contactTypes.getContactTypeDetails().add(ctft);
            }
            contacts.getContactTypes().add(contactTypes);
        }
        group.getContacts().add(contacts);
        groups.getGroup().add(group);
        tingcoGroups.setGroups(groups);
        return tingcoGroups;
    }

    public TingcoGroups buildGetContacts(TingcoGroups tingcoGroups, List<se.info24.pojo.Contacts> contactsList, int countryId, Session session) {
        Groups groups = new Groups();
        Group group = new Group();
        for (se.info24.pojo.Contacts c : contactsList) {
            Contacts contacts = new Contacts();
            contacts.setContactID(c.getContactId());
            contacts.setContactName(c.getContactName());
            if (c.getGroupId() != null) {
                GroupTranslations gt = groupDAO.getGroupTranslationsByIds(c.getGroupId(), countryId, session);
                contacts.setGroupID(c.getGroupId());
                contacts.setGroupName(gt.getGroupName());
            }
            group.getContacts().add(contacts);
        }
        groups.getGroup().add(group);
        tingcoGroups.setGroups(groups);
        return tingcoGroups;
    }

    public TingcoGroups buildGetGroupAliasForOrganization(TingcoGroups tingcoGroups, List<GroupAlias> groupAliasList) {
        se.info24.groupsjaxb.Groups groups = new se.info24.groupsjaxb.Groups();
        se.info24.groupsjaxb.Group group = new se.info24.groupsjaxb.Group();
        group.setSeqNo(1);
        se.info24.groupsjaxb.GroupAlias ga = null;
        for (GroupAlias groupAlias : groupAliasList) {
            ga = new se.info24.groupsjaxb.GroupAlias();
            ga.setGroupID(groupAlias.getGroups().getGroupId());
            ga.setGroupAlias(groupAlias.getGroupAlias());
            group.getGroupAlias().add(ga);
        }
        groups.getGroup().add(group);
        tingcoGroups.setGroups(groups);
        return tingcoGroups;
    }

    public TingcoGroups buildGetPaymentSettingForOrganization(TingcoGroups tingcoGroups, GroupDefaultUserAlias get, UserAlias ua, CostCenters costCenters) {
        se.info24.groupsjaxb.Groups groups = new se.info24.groupsjaxb.Groups();
        se.info24.groupsjaxb.Group group = new se.info24.groupsjaxb.Group();
        group.setSeqNo(1);
        se.info24.groupsjaxb.GroupDefaultUserAlias groupDefaultUserAlias = new se.info24.groupsjaxb.GroupDefaultUserAlias();
        if (get.getCompanyPaymentEnabled() != null) {
            groupDefaultUserAlias.setCompanyPaymentEnabled(get.getCompanyPaymentEnabled());
        }
        groupDefaultUserAlias.setCostCenterID(get.getCostCenterId());
        if (costCenters != null) {
            groupDefaultUserAlias.setCostCenterName(costCenters.getCostCenterName());
        }

        se.info24.groupsjaxb.UserAlias userAlias = new se.info24.groupsjaxb.UserAlias();
        if (ua.getFirstName() != null) {
            userAlias.setFirstName(ua.getFirstName());
        }
        if (ua.getLastName() != null) {
            userAlias.setLastName(ua.getLastName());

        }
        userAlias.setUserAliasId(ua.getUserAliasId());
        userAlias.setUserAlias(ua.getUserAlias());
        groupDefaultUserAlias.setUserAlias(userAlias);
        group.setGroupDefaultUserAlias(groupDefaultUserAlias);
        groups.getGroup().add(group);
        tingcoGroups.setGroups(groups);
        return tingcoGroups;
    }

    public TingcoGroups buildGetTrustedGroupsList(TingcoGroups tingcoGroups, List<GroupTranslations> gt, HashMap<String, GroupTrusts> hm, int countryId, Session session) {
        Groups groups = new Groups();
        TingcoCustomerDAO customerDAO = new TingcoCustomerDAO();
//        GroupTranslations gts;
        int seqNo = 1;
        for (GroupTranslations groupTranslations : gt) {
            for (String s : hm.keySet()) {
                if (s.equalsIgnoreCase(groupTranslations.getId().getGroupId())) {
                    Group group = new Group();
                    group.setSeqNo(seqNo++);
                    group.setGroupID(s);
                    GroupNames groupnames = new GroupNames();
                    GroupName groupname = new GroupName();
                    groupname.setValue(groupTranslations.getGroupName());
                    groupnames.getGroupName().add(groupname);
                    group.setGroupNames(groupnames);
                    group.setUserRoleID(hm.get(s).getId().getUserRoleId());
                    group.setUserRoleName(customerDAO.getUserRoleByUserRoleID(session, hm.get(s).getId().getUserRoleId()).getUserRoleName());
                    groups.getGroup().add(group);
                }

            }
        }
        tingcoGroups.setGroups(groups);
        return tingcoGroups;
    }

    public TingcoGroups buildGroupTrusts(TingcoGroups tingcoGroups, List<GroupTranslations> groupTransList) {
        int seqId = 1;
        Groups groups = new Groups();
        for (GroupTranslations gt : groupTransList) {
            Group group = new Group();
            group.setSeqNo(seqId++);
            group.setGroupID(gt.getId().getGroupId());
            GroupNames gns = new GroupNames();
            GroupName gn = new GroupName();
            gn.setValue(gt.getGroupName());
//            group.setgroup(groupTrans.getGroupTypeName());
            gns.getGroupName().add(gn);
            group.setGroupNames(gns);
            groups.getGroup().add(group);
        }
        tingcoGroups.setGroups(groups);
        return tingcoGroups;
    }

    public TingcoGroups buildObjectGroups(TingcoGroups tingcoGroups, List<Ogmdevice> ogmdeviceList, List<ObjectGroupTranslations> ogtransList, List<se.info24.pojo.ObjectGroups> ogList, Integer countryId, boolean ogmDeviceLinked, Session session) {
        Groups groups = new Groups();
        for (ObjectGroupTranslations ogt : ogtransList) {
            for (Ogmdevice ogmd : ogmdeviceList) {
                if (ogt.getId().getObjectGroupId().equalsIgnoreCase(ogmd.getObjectGroupId())) {
                    Group group = new Group();
                    ObjectGroups objectGroups = new ObjectGroups();
                    se.info24.groupsjaxb.ObjectGroupTranslations objectGroupTrans = new se.info24.groupsjaxb.ObjectGroupTranslations();
                    objectGroupTrans.setObjectGroupID(ogt.getId().getObjectGroupId());
                    objectGroupTrans.setObjectGroupName(ogt.getObjectGroupName());
                    if (ogmDeviceLinked) {
                        if (ogmd.getSchedules() != null) {
                            TCMUtil.loger(getClass().getName(), "ScheduleName" + ogmd.getSchedules().getScheduleName(), "Info");
                            se.info24.groupsjaxb.Schedules sch = new se.info24.groupsjaxb.Schedules();
                            sch.setScheduleName(ogmd.getSchedules().getScheduleName());
                            group.setSchedules(sch);
                        }
                    }
                    objectGroups.getObjectGroupTranslations().add(objectGroupTrans);
                    for (se.info24.pojo.ObjectGroups og : ogList) {
                        if (og.getObjectGroupId().equalsIgnoreCase(ogt.getId().getObjectGroupId())) {
                            GroupTranslations gt = groupDAO.getGroupTranslationsByIds(og.getGroupId(), countryId, session);
                            GroupNames gns = new GroupNames();
                            GroupName gn = new GroupName();
                            gn.setValue(gt.getGroupName());
                            gns.getGroupName().add(gn);
                            group.setGroupNames(gns);
                        }
                    }
                    group.getObjectGroups().add(objectGroups);
                    groups.getGroup().add(group);
                }
            }
        }
        tingcoGroups.setGroups(groups);
        return tingcoGroups;
    }

    public TingcoGroups buildObjectGroupsLinkedToOGMContainers(TingcoGroups tingcoGroups, List<Ogmcontainers> ogmContainersList, List<ObjectGroupTranslations> ogtransList, List<se.info24.pojo.ObjectGroups> ogList, Integer countryId, Session session) {
        Groups groups = new Groups();
        int seqNo = 0;
        for (ObjectGroupTranslations ogt : ogtransList) {
            for (Ogmcontainers ogmc : ogmContainersList) {
//                if (ogt.getId().getObjectGroupId().equalsIgnoreCase(ogmc.getObjectGroups().getObjectGroupId())) {
                if (ogt.getId().getObjectGroupId().equalsIgnoreCase(ogmc.getObjectGroupId())) {
                    Group group = new Group();
                    group.setSeqNo(seqNo++);
                    ObjectGroups objectGroups = new ObjectGroups();
                    se.info24.groupsjaxb.ObjectGroupTranslations objectGroupTrans = new se.info24.groupsjaxb.ObjectGroupTranslations();
                    objectGroupTrans.setObjectGroupID(ogt.getId().getObjectGroupId());
                    objectGroupTrans.setObjectGroupName(ogt.getObjectGroupName());
                    if (ogmc.getScheduleId() != null) {
                        Schedules schedules = deviceDAO.getSchedulesByID(ogmc.getScheduleId(), session);
                        if (schedules != null) {
                            se.info24.groupsjaxb.Schedules sch = new se.info24.groupsjaxb.Schedules();
                            sch.setScheduleName(schedules.getScheduleName());
                            group.setSchedules(sch);
                        }
                    }
                    objectGroups.getObjectGroupTranslations().add(objectGroupTrans);
                    for (se.info24.pojo.ObjectGroups og : ogList) {
                        if (og.getObjectGroupId().equalsIgnoreCase(ogt.getId().getObjectGroupId())) {
                            GroupTranslations gt = groupDAO.getGroupTranslationsByIds(og.getGroupId(), countryId, session);
                            GroupNames gns = new GroupNames();
                            GroupName gn = new GroupName();
                            gn.setValue(gt.getGroupName());
                            gns.getGroupName().add(gn);
                            group.setGroupNames(gns);
                        }
                    }
                    group.getObjectGroups().add(objectGroups);
                    groups.getGroup().add(group);
                }
            }
        }
        tingcoGroups.setGroups(groups);
        return tingcoGroups;
    }

    public TingcoGroups buildObjectGroups(TingcoGroups tingcoGroups, Set<se.info24.pojo.ObjectGroups> objectGroupsList, List<ObjectGroupTranslations> ogtransList, int countryId, boolean ogmDevicelinked, Session session) {
        Groups groups = new Groups();
        int seqNo = 0;
        for (ObjectGroupTranslations ogt : ogtransList) {
            for (se.info24.pojo.ObjectGroups og : objectGroupsList) {
                if (og.getObjectGroupId().equalsIgnoreCase(ogt.getId().getObjectGroupId())) {
                    Group group = new Group();
                    group.setSeqNo(seqNo++);
                    ObjectGroups objectGroups = new ObjectGroups();
                    se.info24.groupsjaxb.ObjectGroupTranslations objectGroupTrans = new se.info24.groupsjaxb.ObjectGroupTranslations();
                    objectGroupTrans.setObjectGroupID(og.getObjectGroupId());
                    objectGroupTrans.setObjectGroupName(ogt.getObjectGroupName());
                    if (ogmDevicelinked) {
                        Schedules schedules = deviceDAO.getSchedulesByGroupid(og.getGroupId(), session);
                        if (schedules != null) {
                            se.info24.groupsjaxb.Schedules sch = new se.info24.groupsjaxb.Schedules();
                            sch.setScheduleName(schedules.getScheduleName());
                            group.setSchedules(sch);
                        }
                    }
                    objectGroups.getObjectGroupTranslations().add(objectGroupTrans);
                    GroupTranslations gt = groupDAO.getGroupTranslationsByIds(og.getGroupId(), countryId, session);
                    GroupNames gns = new GroupNames();
                    GroupName gn = new GroupName();
                    gn.setValue(gt.getGroupName());
                    gns.getGroupName().add(gn);
                    group.setGroupNames(gns);
                    group.getObjectGroups().add(objectGroups);
                    groups.getGroup().add(group);
                }
            }
        }
        tingcoGroups.setGroups(groups);
        return tingcoGroups;
    }

    public TingcoGroups buildServiceClientLogins(TingcoGroups tingcoGroups, ServiceClientLogins scl, int countryId, String timeZoneGMToffset) {
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DeliveryAccounts deliveryAccounts = new DeliveryAccounts();
        DeliveryAccount deliveryAccount = new DeliveryAccount();
        se.info24.groupsjaxb.ServiceClientLogins serviceClientLogins = new se.info24.groupsjaxb.ServiceClientLogins();
        serviceClientLogins.setServiceClientLoginID(scl.getServiceClientLoginId());
        serviceClientLogins.setServiceClientLoginName(scl.getServiceClientLoginName());
        if (scl.getServiceClientLoginDescription() != null) {
            serviceClientLogins.setServiceClientLoginDescription(scl.getServiceClientLoginDescription());
        }
        serviceClientLogins.setGroupID(scl.getGroups().getGroupId());
        Set<GroupTranslations> groupTransList = scl.getGroups().getGroupTranslationses();
        for (GroupTranslations gt : groupTransList) {
            if (gt.getId().getCountryId() == countryId) {
                serviceClientLogins.setGroupName(gt.getGroupName());
                break;
            }
        }
        if (scl.getPassword() != null) {
            serviceClientLogins.setPassword(scl.getPassword());
        }
        if (scl.getProtocol() != null) {
            serviceClientLogins.setProtocol(scl.getProtocol());
        }
        if (scl.getProtocolVersion() != null) {
            serviceClientLogins.setProtocolVersion(scl.getProtocolVersion() + "");
        }
        if (scl.getIsEnabled() != null) {
            serviceClientLogins.setIsEnabled(scl.getIsEnabled() + "");
        }
        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, scl.getActiveFromDate()));
        serviceClientLogins.setActiveFromDateTCMV3(dateFormat.format(gc.getTime()));
        if (scl.getActiveToDate() != null) {
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, scl.getActiveToDate()));
            serviceClientLogins.setActiveToDateTCMV3(dateFormat.format(gc.getTime()));
        }
        deliveryAccount.getServiceClientLogins().add(serviceClientLogins);
        deliveryAccounts.getDeliveryAccount().add(deliveryAccount);
        tingcoGroups.setDeliveryAccounts(deliveryAccounts);
        return tingcoGroups;
    }

    public TingcoGroups buildTingcoGroupDetails(TingcoGroups tingcoGroups, se.info24.pojo.Groups gs, GroupTranslations groupTrans, GroupTypeTranslations groupTypeTrans, GroupDefaultAgreement groupDefaultAgreementList, Addresses addresses, List<ApplicationSolutionTranslations> astList, GroupLimitPackages grouplimitpackageslist, List<ApplicationPackageTranslations> aptsList, List<ApplicationPackageTranslations> aptList, int countryid, List<ApplicationSolutionTranslations> astsList) {
        se.info24.groupsjaxb.Groups groups = new se.info24.groupsjaxb.Groups();
        int seqNo = 1;
        if (gs != null) {
            Group group = new Group();
            group.setSeqNo(seqNo);
            group.setGroupID(gs.getGroupId());
            Set<GroupTranslations> gttsSet = gs.getGroupTranslationses();
            for (GroupTranslations gts : gttsSet) {
                GroupNames gns = new GroupNames();
                GroupName gn = new GroupName();
                GroupDescriptions gds = new GroupDescriptions();
                GroupDescription gd = new GroupDescription();
                if (gts.getId().getCountryId() == Integer.valueOf(countryid)) {
                    gn.setValue(gts.getGroupName());
                    gns.getGroupName().add(gn);
                    group.setGroupNames(gns);
                    if (gts.getGroupDescription() != null) {
                        gd.setValue(gts.getGroupDescription());
                        gds.getGroupDescription().add(gd);
                        group.setGroupDescriptions(gds);
                    }
                }

            }
            if (gs.getGroupParentId() != null) {
                if (gs.getGroupParentId().equalsIgnoreCase(groupTrans.getId().getGroupId())) {
                    group.setGroupParentID(gs.getGroupParentId());
                    group.setGroupParentName(groupTrans.getGroupName());
                }
            }
            if (groupTypeTrans != null) {
                if (gs.getGroupTypeId().equalsIgnoreCase(groupTypeTrans.getId().getGroupTypeId())) {
                    group.setGroupTypeID(gs.getGroupTypeId());
                    group.setGroupTypeName(groupTypeTrans.getGroupTypeName());
                }
            }
            if (gs.getOrganizationNumber() != null) {
                group.setOrganizationNumber(gs.getOrganizationNumber());
            }
            if (grouplimitpackageslist != null) {
                group.setGroupLimitPackageID(grouplimitpackageslist.getGroupLimitPackageId());
                group.setPackageName(grouplimitpackageslist.getPackageName());
            }

            se.info24.groupsjaxb.GroupVisibleApplicationSolutions gvas = new se.info24.groupsjaxb.GroupVisibleApplicationSolutions();
            ApplicationSolutions apss = new ApplicationSolutions();
            for (ApplicationSolutionTranslations ast : astList) {
                if (ast.getId().getCountryId() == Integer.valueOf(countryid)) {
                    ApplicationSolution as = new ApplicationSolution();
                    as.setApplicationSolutionID(ast.getId().getApplicationSolutionId());
                    as.setApplicationSolutionName(ast.getApplicationSolutionName());
                    apss.getApplicationSolution().add(as);
                }
            }
            gvas.getApplicationSolutions().add(apss);
            group.getGroupVisibleApplicationSolutions().add(gvas);

            if (groupDefaultAgreementList != null) {
                group.setAgreementID(groupDefaultAgreementList.getAgreements().getAgreementId());
                group.setAgreementName(groupDefaultAgreementList.getAgreements().getAgreementName());
            }

            se.info24.groupsjaxb.GroupApplicationPackages gaps = new se.info24.groupsjaxb.GroupApplicationPackages();
            int seqNumber = 1;
            for (ApplicationSolutionTranslations astrans : astsList) {
                if (astrans.getId().getCountryId() == Integer.valueOf(countryid)) {
                    for (ApplicationPackageTranslations ap : aptList) {
                        GroupApplicationPackage gap = new GroupApplicationPackage();
                        if (ap.getApplicationPackages().getApplicationSolutions().getApplicationSolutionId().equalsIgnoreCase(astrans.getId().getApplicationSolutionId())) {
                            if (ap.getId().getCountryId() == Integer.valueOf(countryid)) {
                                gap.setSeqNo(seqNumber++);
                                gap.setApplicationPackageID(ap.getId().getApplicationPackageId());
                                gap.setApplicationPackageName(ap.getApplicationPackageName());
                                gap.setApplicationSolutionID(astrans.getId().getApplicationSolutionId());
                                gap.setApplicationSolutionName(astrans.getApplicationSolutionName());
                                gaps.getGroupApplicationPackage().add(gap);
                            }
                        }
                    }
                }
            }

            group.getGroupApplicationPackages().add(gaps);

            se.info24.groupsjaxb.GroupVisibleApplicationPackages gvaps = new se.info24.groupsjaxb.GroupVisibleApplicationPackages();
            se.info24.groupsjaxb.ApplicationPackages aps = new se.info24.groupsjaxb.ApplicationPackages();
            for (ApplicationPackageTranslations apt : aptsList) {
                se.info24.groupsjaxb.ApplicationPackage ape = new se.info24.groupsjaxb.ApplicationPackage();
                if (apt.getId().getCountryId() == Integer.valueOf(countryid)) {
                    ape.setApplicationPackageID(apt.getId().getApplicationPackageId());
                    ape.setApplicationPackageName(apt.getApplicationPackageName());
                    aps.getApplicationPackage().add(ape);
                }
            }

            gvaps.getApplicationPackages().add(aps);
            group.getGroupVisibleApplicationPackages().add(gvaps);

            if (addresses != null) {
                Address address = new Address();
                address.setAddressID(addresses.getAddressId());
                address.setAddressStreet(addresses.getAddressStreet());
                address.setAddressStreet2(addresses.getAddressStreet2());
                address.setAddressSuite(addresses.getAddressSuite());
                address.setAddressZip(addresses.getAddressZip());
                address.setAddressCity(addresses.getAddressCity());
                address.setAddressRegion(addresses.getAddressRegion());
                address.setAddressDesc(addresses.getAddressDescription());
                Country country = addresses.getCountry();
                address.setCountryID(country.getCountryId());
                address.setCountryName(country.getCountryName());
                group.setAddress(address);
            }

            groups.getGroup().add(group);
        }
        tingcoGroups.setGroups(groups);
        return tingcoGroups;
    }

    /**
     * An Utility Method to create TingcoApplication XML Template.
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    public TingcoGroups buildTingcoGroupTemplate() throws DatatypeConfigurationException {
        ObjectFactory objectFactory = new ObjectFactory();
        TingcoGroups groupXML = objectFactory.createTingcoGroups();
        groupXML.setCreateRef("Info24");
        groupXML.setMsgVer(new BigDecimal("1.0"));
        groupXML.setRegionalUnits("Metric");
        groupXML.setTimeZone("UTC");

        groupXML.setMsgID(UUID.randomUUID().toString());
        groupXML.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        MsgStatus msgStatus = new MsgStatus();
        msgStatus.setResponseCode(0);
        msgStatus.setResponseText("OK");

        groupXML.setMsgStatus(msgStatus);

        return groupXML;
    }

    public TingcoGroups buildCostCentreByID(TingcoGroups tingcoGroups, CostCenters costCenter, String timezoneOffset) throws DatatypeConfigurationException {
        GregorianCalendar gc = new GregorianCalendar();
        se.info24.groupsjaxb.Groups groups = new se.info24.groupsjaxb.Groups();
        se.info24.groupsjaxb.Group group = new se.info24.groupsjaxb.Group();
        group.setSeqNo(1);
        se.info24.groupsjaxb.CostCenters cc = new se.info24.groupsjaxb.CostCenters();
        cc.setCostCenterID(costCenter.getCostCenterId());
        cc.setCostCenterName(costCenter.getCostCenterName());
        cc.setCostCenterDescription(costCenter.getCostCenterDescription());
        cc.setCostCenterNumber(costCenter.getCostCenterNumber());
        cc.setGroupID(costCenter.getGroupId());
        cc.setDisplayInWebShop(costCenter.getDisplayInWebShop());
        cc.setDisplayOrder(costCenter.getDisplayOrder());
        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timezoneOffset, costCenter.getActiveFromDate()));
        cc.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timezoneOffset, costCenter.getActiveToDate()));
        cc.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
        group.getCostCenters().add(cc);
        groups.getGroup().add(group);
        tingcoGroups.setGroups(groups);
        return tingcoGroups;
    }

    public TingcoGroups buildTingcoGroupTypes(TingcoGroups tingcoGroups, List<GroupTypeTranslations> groupTypeTransList) {
        int seqId = 1;
        Groups groups = new Groups();

        for (GroupTypeTranslations groupTrans : groupTypeTransList) {
            Group group = new Group();
            group.setSeqNo(seqId++);
            group.setGroupTypeID(groupTrans.getId().getGroupTypeId());
            group.setGroupTypeName(groupTrans.getGroupTypeName());
            groups.getGroup().add(group);
        }
        tingcoGroups.setGroups(groups);
        return tingcoGroups;
    }

    public TingcoGroups buildGroupTrust(TingcoGroups tingcoGroups, GroupDAO groupDAO, List<GroupTrusts> groupTrustList, List<se.info24.pojo.Groups> groupList, Session session, String groupId, int countryId, String domainId, String groupName) {
        /**************** dont delete the below commented code if not necessary to delete *****************************/
        int seqId = 1;
        Groups groups = new Groups();
        boolean flag = true;
        for (GroupTrusts groupTrust : groupTrustList) {
            if (groupTrust.getId().getGroupId().equalsIgnoreCase(groupId)) {
                flag = false;
                break;
            }
        }
        if (flag) {
            GroupTrusts ownGroupTrust = new GroupTrusts();
            ownGroupTrust.setId(new GroupTrustsId(groupId, groupId, "")); // Adding dummy GroupTrust record to groupTrustList, for getting own group info in result.
            groupTrustList.add(ownGroupTrust);
        }
        Group group = null;
//        boolean groupNameflag;
        for (GroupTrusts groupTrust : groupTrustList) {
//            groupNameflag = false;

            for (se.info24.pojo.Groups groupPojo : groupList) {

                if (groupPojo.getGroupId().equalsIgnoreCase(groupTrust.getId().getGroupId()) && domainId.equalsIgnoreCase(groupPojo.getDomainId())) {

                    GroupNames gns = new GroupNames();
                    Set<GroupTranslations> groupTrans = groupPojo.getGroupTranslationses();
                    for (GroupTranslations gt : groupTrans) {
                        /**************** dont delete the below commented code if not necessary to delete *****************************/
//                        if (groupName != null) {
//                            if (gt.getCountry().getCountryId() == countryId && gt.getGroupName().toUpperCase().startsWith(groupName.toUpperCase())) {
//                                groupNameflag = true;
//                                GroupName gn = new GroupName();
//                                gn.setLanguage(gt.getCountry().getLanguageCode());
//                                gn.setValue(gt.getGroupName());
//                                gns.getGroupName().add(gn);
//                                break;
//                            }
                        // } else {
                        if (gt.getCountry().getCountryId() == countryId) {
                            GroupName gn = new GroupName();
                            gn.setLanguage(gt.getCountry().getLanguageCode());
                            gn.setValue(gt.getGroupName());
                            gns.getGroupName().add(gn);

                            group = new Group();
                            group.setSeqNo(seqId++);
                            group.setGroupID(groupPojo.getGroupId());
                            group.setGroupNames(gns);
                            groups.getGroup().add(group);
                            break;
                        }
                    // }
                    }
                    /**************** dont delete the below commented code if not necessary to delete *****************************/
//                    if (groupName != null && groupNameflag) {
//                        group = new Group();
//                        group.setSeqNo(seqId++);
//                        group.setGroupID(groupPojo.getGroupId());
//                        group.setGroupNames(gns);
//                        groups.getGroup().add(group);
//                    } else if (groupName == null) {
//                        group = new Group();
//                        group.setSeqNo(seqId++);
//                        group.setGroupID(groupPojo.getGroupId());
//                        group.setGroupNames(gns);
//                        groups.getGroup().add(group);
//                    }
                    if (groupPojo.getGroupTypeId() != null) {
                        GroupTypeTranslations gtt = groupDAO.getGroupTypeTranslationsByGroupTypeId(groupPojo.getGroupTypeId(), countryId, session);
                        if (gtt != null) {
                            group.setGroupTypeID(gtt.getId().getGroupTypeId());
                            group.setGroupTypeName(gtt.getGroupTypeName());
                        }
                    }

                }
            }

        }

        tingcoGroups.setGroups(groups);
        return tingcoGroups;

    }

    public TingcoGroups buildGroupInfo(TingcoGroups tingcoGroups, Set<GroupTranslations> groupTrans, int countryId) {
        int seqId = 1;
        Groups groups = new Groups();
        Group group = new Group();
        group.setSeqNo(seqId++);
        GroupNames gns = new GroupNames();
        for (GroupTranslations gt : groupTrans) {
            if (gt.getCountry().getCountryId() == countryId) {
                GroupName gn = new GroupName();
                gn.setLanguage(gt.getCountry().getLanguageCode());
                gn.setValue(gt.getGroupName());
                group.setGroupID(gt.getId().getGroupId());
                gns.getGroupName().add(gn);
                break;
            }
        }
        group.setGroupNames(gns);
        groups.getGroup().add(group);
        tingcoGroups.setGroups(groups);
        return tingcoGroups;
    }

    public TingcoGroups buildFavoriteGroups(TingcoGroups tingcoGroups, List<UserFavoriteGroups> favGroups, int countryID) {
        Groups grps = new Groups();
        int seqId = 1;
        GroupNames names = null;
        for (UserFavoriteGroups ufg : favGroups) {
            Group g = new Group();
            g.setSeqNo(seqId++);
            g.setGroupID(ufg.getId().getGroupId());
            g.setGroupParentID(ufg.getGroup().getGroupParentId());
            Set<GroupTranslations> gt = ufg.getGroup().getGroupTranslationses();
            for (GroupTranslations trans : gt) {
                if (trans.getCountry().getCountryId() == countryID) {
                    names = new GroupNames();
                    GroupName gn = new GroupName();
                    gn.setLanguage(trans.getCountry().getLanguage());
                    gn.setValue(trans.getGroupName());
                    names.getGroupName().add(gn);
                    g.setGroupNames(names);
                    break;
                }
            }
            grps.getGroup().add(g);
        }
        tingcoGroups.setGroups(grps);
        return tingcoGroups;
    }

    public TingcoGroups buildgetCostCenters(TingcoGroups tingcoGroups, List<CostCenters> costCentersesList, String timezoneOffset) throws DatatypeConfigurationException {
        GregorianCalendar gc = new GregorianCalendar();
        se.info24.groupsjaxb.Groups groups = new se.info24.groupsjaxb.Groups();
        se.info24.groupsjaxb.Group group = new se.info24.groupsjaxb.Group();
        group.setSeqNo(1);
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd");
        se.info24.groupsjaxb.CostCenters cc = null;
        for (CostCenters costCenter : costCentersesList) {
            cc = new se.info24.groupsjaxb.CostCenters();
            cc.setCostCenterID(costCenter.getCostCenterId());
            cc.setCostCenterName(costCenter.getCostCenterName());
            if (timezoneOffset != null) {
                cc.setCostCenterDescription(costCenter.getCostCenterDescription());
                cc.setCostCenterNumber(costCenter.getCostCenterNumber());
                cc.setGroupID(costCenter.getGroupId());
                cc.setDisplayInWebShop(costCenter.getDisplayInWebShop());
                cc.setDisplayOrder(costCenter.getDisplayOrder());
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timezoneOffset, costCenter.getActiveFromDate()));
                cc.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                cc.setActiveFromDateTCMV3(lFormat.format(gc.getTime()));
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timezoneOffset, costCenter.getActiveToDate()));
                cc.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                cc.setActiveToDateTCMV3(lFormat.format(gc.getTime()));
            }
            group.getCostCenters().add(cc);
        }

        groups.getGroup().add(group);
        tingcoGroups.setGroups(groups);
        return tingcoGroups;
    }

    public TingcoGroups buileObjectGroups(TingcoGroups tingcoGroups, List<ObjectGroupTranslations> objectGroupTranslationsList) {
        Groups groups = new Groups();
        Group group = new Group();
        int seqNo = 1;
        group.setSeqNo(seqNo++);
        for (ObjectGroupTranslations ogs : objectGroupTranslationsList) {
            ObjectGroups objectGroups = new ObjectGroups();
//            objectGroups.setObjectGroupID(ogs.getId().getObjectGroupId());
            se.info24.groupsjaxb.ObjectGroupTranslations ogts = new se.info24.groupsjaxb.ObjectGroupTranslations();
            ogts.setObjectGroupID(ogs.getId().getObjectGroupId());
            ogts.setCountryID(ogs.getId().getCountryId());
            ogts.setObjectGroupName(ogs.getObjectGroupName());
            objectGroups.getObjectGroupTranslations().add(ogts);
            group.getObjectGroups().add(objectGroups);
        }
        groups.getGroup().add(group);
        tingcoGroups.setGroups(groups);
        return tingcoGroups;
    }

    public TingcoGroups buildTingcoGroupList(TingcoGroups tingcoGroups, List<se.info24.pojo.Groups> groupsList, List<GroupTypeTranslations> groupTypeTransList, List<Users2> usersList, List<GroupTranslations> groupTranslationsList, List<GroupTranslations> groupTranslationsList1, String timeZoneGMToffset) throws DatatypeConfigurationException {
        se.info24.groupsjaxb.Groups groups = new se.info24.groupsjaxb.Groups();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int seqNo = 1;
        for (GroupTranslations gts : groupTranslationsList1) {
            for (se.info24.pojo.Groups gs : groupsList) {
                if (gts.getId().getGroupId().equalsIgnoreCase(gs.getGroupId())) {
                    Group group = new Group();
                    group.setSeqNo(seqNo++);
                    group.setGroupID(gs.getGroupId());
                    if (gs.getOrganizationNumber() != null) {
                        group.setOrganizationNumber(gs.getOrganizationNumber());
                    }
                    GregorianCalendar gc = new GregorianCalendar();
                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, gs.getCreatedDate()));
                    group.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    group.setCreatedDateTCMV3(dateFormat.format(gc.getTime()));
                    GroupNames gns = new GroupNames();
                    GroupName gn = new GroupName();
                    gn.setValue(gts.getGroupName());
                    gns.getGroupName().add(gn);
                    group.setGroupNames(gns);
                    for (GroupTranslations gtrans : groupTranslationsList) {
                        if (gs.getGroupParentId() != null) {
                            if (gs.getGroupParentId().equalsIgnoreCase(gtrans.getId().getGroupId())) {
                                group.setGroupParentID(gs.getGroupParentId());
                                group.setGroupParentName(gtrans.getGroupName());
                            }
                        }
                    }
                    for (GroupTypeTranslations gtts : groupTypeTransList) {
                        if (gs.getGroupTypeId() != null) {
                            group.setGroupTypeID(gs.getGroupTypeId());
                            if (gtts.getId().getGroupTypeId().equalsIgnoreCase(gs.getGroupTypeId())) {
                                if (gtts.getGroupTypeName() != null) {
                                    group.setGroupTypeName(gtts.getGroupTypeName());
                                }
                            }
                        }
                    }
                    for (Users2 us : usersList) {
                        if (gs.getUserId() != null) {
                            if (gs.getUserId().equalsIgnoreCase(us.getUserId())) {
                                if (us.getFirstName() != null && us.getLastName() != null) {
                                    LastUpdatedByUserID luid = new LastUpdatedByUserID();
                                    luid.setValue(gs.getUserId());
                                    luid.setUserFullName(us.getFirstName() + " " + us.getLastName());
                                    group.setLastUpdatedByUserID(luid);
                                }
                            }
                        }
                    }
                    groups.getGroup().add(group);
                }
            }
        }
        tingcoGroups.setGroups(groups);
        return tingcoGroups;
    }

    public TingcoGroups buildXmlObjectGroups(TingcoGroups tingcoGroups, List<ObjectGroupTranslations> ogtList, List<GroupTranslations> groupTransList, List<se.info24.pojo.ObjectGroups> objectGroupList) {
        Groups groups = new Groups();
        Group group = new Group();
        List<ObjectGroups> objectGroupJaxbList = new ArrayList<ObjectGroups>();

        for (ObjectGroupTranslations ogt : ogtList) {
            for (se.info24.pojo.ObjectGroups og : objectGroupList) {
                String objectGroupId = og.getObjectGroupId();
                String groupId = og.getGroupId();
                ObjectGroups objectGroupJaxb = new ObjectGroups();
                if (ogt.getId().getObjectGroupId().equalsIgnoreCase(objectGroupId)) {
                    se.info24.groupsjaxb.ObjectGroupTranslations ogTrans = new se.info24.groupsjaxb.ObjectGroupTranslations();
                    ogTrans.setCountryID(ogt.getId().getCountryId());
                    ogTrans.setObjectGroupID(ogt.getId().getObjectGroupId());
                    ogTrans.setObjectGroupName(ogt.getObjectGroupName());
                    if (ogt.getObjectGroupDescription() != null) {
                        ogTrans.setObjectGroupDescription(ogt.getObjectGroupDescription());
                    }
                    for (GroupTranslations gt : groupTransList) {
                        if (gt.getGroups().getGroupId().equalsIgnoreCase(groupId)) {
                            objectGroupJaxb.setGroupId(gt.getId().getGroupId());
                            objectGroupJaxb.setGroupName(gt.getGroupName());
                            break;
                        }
                    }
                    objectGroupJaxb.getObjectGroupTranslations().add(ogTrans);
                    objectGroupJaxbList.add(objectGroupJaxb);
                    break;
                }
            }
        }
        group.getObjectGroups().addAll(objectGroupJaxbList);
        groups.getGroup().add(group);
        tingcoGroups.setGroups(groups);
        return tingcoGroups;
    }

    public TingcoGroups buildDeliveryAccountsList(TingcoGroups tingcoGroups, List<ServiceClientLogins> serviceClientLoginsList, List<GroupTranslations> groupTranslationsList, String timeZoneGMToffset) throws DatatypeConfigurationException {
        boolean dataAdded = false;
        GregorianCalendar gc = new GregorianCalendar();
        DeliveryAccounts deliveryAccounts = new DeliveryAccounts();
        DeliveryAccount deliveryAccount = new DeliveryAccount();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        for (ServiceClientLogins scl : serviceClientLoginsList) {
            String groupId = scl.getGroupId();
            for (GroupTranslations gt : groupTranslationsList) {
                if (gt.getId().getGroupId().equalsIgnoreCase(groupId)) {
                    se.info24.groupsjaxb.ServiceClientLogins clientLogins = new se.info24.groupsjaxb.ServiceClientLogins();
                    clientLogins.setServiceClientLoginID(scl.getServiceClientLoginId());
                    if (scl.getServiceClientLoginName() != null) {
                        clientLogins.setServiceClientLoginName(scl.getServiceClientLoginName());
                    }
                    clientLogins.setClientLogin(scl.getClientLogin());
                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, scl.getActiveFromDate()));
                    clientLogins.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    clientLogins.setActiveFromDateTCMV3(dateFormat.format(gc.getTime()));
                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, scl.getActiveToDate()));
                    clientLogins.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    clientLogins.setActiveToDateTCMV3(dateFormat.format(gc.getTime()));
                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, scl.getCreatedDate()));
                    clientLogins.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    clientLogins.setCreatedDateTCMv3(dateFormat2.format(gc.getTime()));
                    clientLogins.setGroupID(groupId);
                    clientLogins.setGroupName(gt.getGroupName());
                    deliveryAccount.getServiceClientLogins().add(clientLogins);
                    dataAdded = true;
                    break;
                }
            }
        }
        if (dataAdded) {
            deliveryAccounts.getDeliveryAccount().add(deliveryAccount);
            tingcoGroups.setDeliveryAccounts(deliveryAccounts);
        } else {
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("No Data Found");
            return tingcoGroups;
        }
        return tingcoGroups;
    }

    public TingcoGroups buildDeliveryAccountList(TingcoGroups tingcoGroups, List<ServiceClientLogins> serviceClientLoginsList, GroupTranslations groupTranslationse, String timeZoneGMToffset) throws DatatypeConfigurationException {
        boolean dataAdded = false;
        GregorianCalendar gc = new GregorianCalendar();
        DeliveryAccounts deliveryAccounts = new DeliveryAccounts();
        DeliveryAccount deliveryAccount = new DeliveryAccount();
        for (ServiceClientLogins scl : serviceClientLoginsList) {
            if (scl.getGroupId().equalsIgnoreCase(groupTranslationse.getId().getGroupId())) {
                se.info24.groupsjaxb.ServiceClientLogins clientLogins = new se.info24.groupsjaxb.ServiceClientLogins();
                clientLogins.setServiceClientLoginID(scl.getServiceClientLoginId());
                if (scl.getServiceClientLoginName() != null) {
                    clientLogins.setServiceClientLoginName(scl.getServiceClientLoginName());
                }
                clientLogins.setClientLogin(scl.getClientLogin());
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, scl.getActiveFromDate()));
                clientLogins.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, scl.getActiveToDate()));
                clientLogins.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, scl.getCreatedDate()));
                clientLogins.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                clientLogins.setGroupID(scl.getGroupId());
                clientLogins.setGroupName(groupTranslationse.getGroupName());
                deliveryAccount.getServiceClientLogins().add(clientLogins);
                dataAdded = true;
            }
        }
        if (dataAdded) {
            deliveryAccounts.getDeliveryAccount().add(deliveryAccount);
            tingcoGroups.setDeliveryAccounts(deliveryAccounts);
        } else {
            tingcoGroups.getMsgStatus().setResponseCode(-1);
            tingcoGroups.getMsgStatus().setResponseText("No Data Found");
            return tingcoGroups;
        }
        return tingcoGroups;
    }

    public TingcoGroups buildgetContactDetailsForPopup(TingcoGroups tingcoGroups, List<se.info24.pojo.Contacts> contactList) {
        Groups groups = new Groups();
        Group group = new Group();
        for (se.info24.pojo.Contacts contact : contactList) {
            Contacts contacts = new Contacts();
            contacts.setContactID(contact.getContactId());
            contacts.setContactName(contact.getContactName());
            group.getContacts().add(contacts);
        }
        groups.getGroup().add(group);
        tingcoGroups.setGroups(groups);

        return tingcoGroups;
    }
    
    public TingcoGroups buildGetFAQDetailsByFAQID(TingcoGroups tingcoGroups, Faq faq, Country country) {
        FAQs fAQsJaxb = new FAQs();
        FAQ faqJaxb = new FAQ();
        faqJaxb.setFAQID(faq.getFaqid());
        faqJaxb.setQuestion(faq.getQuestion());
        faqJaxb.setAnswer(faq.getAnswer());
        if (faq.getCategory() != null) {
            faqJaxb.setCategory(faq.getCategory());
        }
        if (faq.getTags() != null) {
            faqJaxb.setTags(faq.getTags());
        }
        faq.setGroupId(faq.getGroupId());
        CountryID countryID = new CountryID();
        countryID.setCountryName(country.getCountryName());
        countryID.setValue(country.getCountryId());
        faqJaxb.setCountryID(countryID);
        fAQsJaxb.getFAQ().add(faqJaxb);
        tingcoGroups.setFAQs(fAQsJaxb);
        return tingcoGroups;
    }

    public TingcoGroups buildGetFAQDetails(TingcoGroups tingcoGroups, List<Object> result) {
        FAQs fAQsJaxb = new FAQs();
        int seqNo = 1;
        for (Iterator itr = result.iterator(); itr.hasNext();) {
            Object[] row = (Object[]) itr.next();
            FAQ faqJaxb = new FAQ();
            faqJaxb.setSeqNo(seqNo++);
            se.info24.groupsjaxb.GroupTranslations gtJaxb = new se.info24.groupsjaxb.GroupTranslations();
            for (int i = 0; i < row.length; i++) {
                if (i % 2 == 0) {
                    if (row[i] != null) {
                        faqJaxb.setFAQID(row[i].toString());
                    }
                    if (row[i + 1] != null) {
                        gtJaxb.setGroupID(row[i + 1].toString());
                    }
                    if (row[i + 2] != null) {
                        faqJaxb.setQuestion(row[i + 2].toString());
                    }
                    if (row[i + 3] != null) {
                        faqJaxb.setAnswer(row[i + 3].toString());
                    }
                    if (row[i + 4] != null) {
                        gtJaxb.setGroupName(row[i + 4].toString());
                    }
                    i += 4;
                }
                faqJaxb.getGroupTranslations().add(gtJaxb);
                fAQsJaxb.getFAQ().add(faqJaxb);
            }
        }
        tingcoGroups.setFAQs(fAQsJaxb);
        return tingcoGroups;
    }
}