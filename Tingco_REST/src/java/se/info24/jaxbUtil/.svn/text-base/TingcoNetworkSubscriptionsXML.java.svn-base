/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.jaxbUtil;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Session;
import se.info24.group.GroupDAO;
import se.info24.networkjaxb.AgreementID;
import se.info24.networkjaxb.ContactGroup;
import se.info24.networkjaxb.GroupID;
import se.info24.networkjaxb.NetworkSettings;
import se.info24.networkjaxb.NetworkSubscription;
import se.info24.networkjaxb.NetworkSubscriptionStatus;
import se.info24.networkjaxb.NetworkTypes;
import se.info24.networkjaxb.TingcoNetworkSubscriptions;
import se.info24.pojo.ContactGroups;
import se.info24.pojo.CostCenters;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTranslationsId;
import se.info24.pojo.NetworkSettingsTemplate;
import se.info24.pojo.NetworkSubscriptionSettings;
import se.info24.pojo.NetworkSubscriptionStatuses;
import se.info24.pojo.NetworkSubscriptions;
import se.info24.pojo.NetworkSubscriptionsPendingDelete;
import se.info24.pojo.Networks;
import se.info24.pojo.ObjectCostCenters;
import se.info24.restUtil.RestUtilDAO;
import se.info24.util.TCMUtil;

/**
 *
 * @author Ravikant
 */
public class TingcoNetworkSubscriptionsXML {

    public TingcoNetworkSubscriptions buildGetNetworkSubscriptionDetails(TingcoNetworkSubscriptions tingcoNetworkSubscriptions, NetworkSubscriptions ns, GroupTranslations gt, String timeZoneGMToffset, NetworkSubscriptionsPendingDelete nspd, CostCenters cc) throws DatatypeConfigurationException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        se.info24.networkjaxb.NetworkSubscriptions netSubs = new se.info24.networkjaxb.NetworkSubscriptions();
        se.info24.networkjaxb.NetworkSubscription netSub = new NetworkSubscription();
        netSub.setNetworkSubscriptionID(ns.getNetworkSubscriptionId());
        GregorianCalendar gc = new GregorianCalendar();
        netSub.setNetworkSubscriptionName(ns.getNetworkSubscriptionName());
        if (ns.getSubscriptionNumber() != null) {
            netSub.setSubscriptionNumber(ns.getSubscriptionNumber());
        }
        GroupID id = new GroupID();
        id.setGroupName(gt.getGroupName());
        id.setValue(gt.getId().getGroupId());
        netSub.setGroupID(id);

        AgreementID aid = new AgreementID();
        aid.setAgreementName(ns.getAgreements().getAgreementName());
        aid.setValue(ns.getAgreements().getAgreementId());
        netSub.setAgreementID(aid);
        if (ns.getNetworkSubscriptionDescription() != null) {
            netSub.setNetworkSubscriptionDescription(ns.getNetworkSubscriptionDescription());
        }
        if (ns.getIpv4() != null) {
            netSub.setIPV4(ns.getIpv4());
        }
        if (ns.getIpv6() != null) {
            netSub.setIPV6(ns.getIpv6());
        }
        if (ns.getSubscriptionUser() != null) {
            netSub.setSubscriptionUser(ns.getSubscriptionUser());
        }
        if (ns.getSubscriptionPassword() != null) {
            netSub.setSubscriptionPassword(ns.getSubscriptionPassword());
        }
        if (ns.getSubscriptionEnabled() != null) {
            netSub.setSubscriptionEnabled(ns.getSubscriptionEnabled());
        }

        if (ns.getApn() != null) {
            netSub.setAPN(ns.getApn());
        }

        if (ns.getIccid() != null) {
            netSub.setICCID(ns.getIccid());
        }

        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, ns.getActiveFromDate()));
        netSub.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc).toString());
        netSub.setActiveFromDateTCMV3(dateFormat.format(gc.getTime()));
        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, ns.getActiveToDate()));
        netSub.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc).toString());
        netSub.setActiveToDateTCMV3(dateFormat.format(gc.getTime()));
        if (nspd != null) {
            se.info24.networkjaxb.NetworkSubscriptionsPendingDelete nspdjax = new se.info24.networkjaxb.NetworkSubscriptionsPendingDelete();
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, nspd.getDeleteSubscriptionAfterThisDate()));
            nspdjax.setDeleteSubscriptionAfterThisDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            netSub.getNetworkSubscriptionsPendingDelete().add(nspdjax);
        }
        if (cc != null) {
            netSub.setCostCenterID(cc.getCostCenterId());
            netSub.setCostCenterName(cc.getCostCenterName());
            netSub.setCostCenterNumber(cc.getCostCenterNumber() + "");
        }
        se.info24.networkjaxb.Networks network = new se.info24.networkjaxb.Networks();
        network.setNetworkID(ns.getNetworks().getNetworkId());
        network.setNetworkName(ns.getNetworks().getNetworkName());
        netSub.getNetworks().add(network);
        if (ns.getNetworkTypes() != null) {
            se.info24.networkjaxb.NetworkTypes nt = new NetworkTypes();
            nt.setNetworkTypeID(ns.getNetworkTypes().getNetworkTypeId());
            nt.setNetworkTypeName(ns.getNetworkTypes().getNetworkTypeName());
            netSub.getNetworkTypes().add(nt);
        }
        if (ns.getNetworkSubscriptionStatuses() != null) {
            se.info24.networkjaxb.NetworkSubscriptionStatus nsss = new se.info24.networkjaxb.NetworkSubscriptionStatus();
            nsss.setNetworkSubscriptionStatusID(ns.getNetworkSubscriptionStatuses().getNetworkSubscriptionStatusId());
            nsss.setStatusName(ns.getNetworkSubscriptionStatuses().getStatusName());
            netSub.getNetworkSubscriptionStatus().add(nsss);

        }
        netSubs.getNetworkSubscription().add(netSub);
        tingcoNetworkSubscriptions.setNetworkSubscriptions(netSubs);
        return tingcoNetworkSubscriptions;
    }

    public TingcoNetworkSubscriptions buildGetContactGroups(TingcoNetworkSubscriptions tingcoNetworkSubscriptions, List<ContactGroups> cgList, List<GroupTranslations> gtList) {
        se.info24.networkjaxb.ContactGroups cgs = new se.info24.networkjaxb.ContactGroups();
        se.info24.networkjaxb.ContactGroup cg = null;
        for (ContactGroups contactGroups : cgList) {
            cg = new ContactGroup();
            cg.setContactGroupID(contactGroups.getContactGroupId());
            cg.setContactGroupName(contactGroups.getContactGroupName());
            cg.setContactGroupDescription(contactGroups.getContactGroupDescription());
            for (GroupTranslations groupTranslations : gtList) {
                if (groupTranslations.getId().getGroupId().equalsIgnoreCase(contactGroups.getGroupId())) {
                    GroupID id = new GroupID();
                    id.setGroupName(groupTranslations.getGroupName());
                    id.setValue(groupTranslations.getId().getGroupId());
                    cg.setGroupID(id);
                }
            }
            cgs.getContactGroup().add(cg);
        }

        tingcoNetworkSubscriptions.setContactGroups(cgs);
        return tingcoNetworkSubscriptions;
    }

    public TingcoNetworkSubscriptions buildGetNetworkSettingTemplates(TingcoNetworkSubscriptions tingcoNetworkSubscriptions, List<NetworkSettingsTemplate> networkSettingsTemplateList, List<NetworkSubscriptionSettings> NetworkSubscriptionSettingsList) {
        se.info24.networkjaxb.NetworkSettings networkSettings = new NetworkSettings();
        se.info24.networkjaxb.NetworkSettingsTemplate nst = null;
        boolean flag = false;
        for (NetworkSettingsTemplate NetworkSettingsTemplate : networkSettingsTemplateList) {
            flag = false;
            for (NetworkSubscriptionSettings networkSubscriptionSettings : NetworkSubscriptionSettingsList) {
                if (networkSubscriptionSettings.getNetworkSubscriptionSettingName().equalsIgnoreCase(NetworkSettingsTemplate.getNetworkSettingName())) {
                    flag = true;
                }
            }
            if (!flag) {
                nst = new se.info24.networkjaxb.NetworkSettingsTemplate();
                nst.setNetworkSettingTemplateID(NetworkSettingsTemplate.getNetworkSettingTemplateId());
                nst.setNetworkSettingName(NetworkSettingsTemplate.getNetworkSettingName());
                nst.setNetworkSettingValue(NetworkSettingsTemplate.getNetworkSettingValue());
                networkSettings.getNetworkSettingsTemplate().add(nst);
            }

        }

        tingcoNetworkSubscriptions.setNetworkSettings(networkSettings);
        return tingcoNetworkSubscriptions;
    }

    public TingcoNetworkSubscriptions buildGetNetworkStatuses(TingcoNetworkSubscriptions tingcoNetworkSubscriptions, List<NetworkSubscriptionStatuses> networkSubscriptionStatusesList) {
        se.info24.networkjaxb.NetworkSubscriptionStatuses netSubStatuses = new se.info24.networkjaxb.NetworkSubscriptionStatuses();
        se.info24.networkjaxb.NetworkSubscriptionStatus netSubStatus = null;
        for (NetworkSubscriptionStatuses networkSubscriptionStatuses : networkSubscriptionStatusesList) {
            netSubStatus = new NetworkSubscriptionStatus();
            netSubStatus.setNetworkSubscriptionStatusID(networkSubscriptionStatuses.getNetworkSubscriptionStatusId());
            netSubStatus.setStatusName(networkSubscriptionStatuses.getStatusName());
            netSubStatuses.getNetworkSubscriptionStatus().add(netSubStatus);
        }
        tingcoNetworkSubscriptions.setNetworkSubscriptionStatuses(netSubStatuses);
        return tingcoNetworkSubscriptions;
    }

    public TingcoNetworkSubscriptions buildGetNetworkOperators(TingcoNetworkSubscriptions tingcoNetworkSubscriptions, List<Networks> networkList) {
        se.info24.networkjaxb.NetworkSubscriptions networksubs = new se.info24.networkjaxb.NetworkSubscriptions();
        se.info24.networkjaxb.NetworkSubscription networksub = new NetworkSubscription();
        se.info24.networkjaxb.Networks network = null;
        for (Networks networks : networkList) {
            network = new se.info24.networkjaxb.Networks();
            network.setNetworkID(networks.getNetworkId());
            network.setNetworkName(networks.getNetworkName());
            networksub.getNetworks().add(network);
        }
        networksubs.getNetworkSubscription().add(networksub);
        tingcoNetworkSubscriptions.setNetworkSubscriptions(networksubs);
        return tingcoNetworkSubscriptions;
    }

    public TingcoNetworkSubscriptions buildGetNetworkSubscriptionSettings(TingcoNetworkSubscriptions tingcoNetworkSubscriptions, List<NetworkSubscriptionSettings> networkSubscriptionSettingsList) {
        se.info24.networkjaxb.NetworkSettings networkSettings = new NetworkSettings();
        se.info24.networkjaxb.NetworkSubscriptionSettings nss = null;
        for (NetworkSubscriptionSettings networkSubscriptionSettings : networkSubscriptionSettingsList) {
            nss = new se.info24.networkjaxb.NetworkSubscriptionSettings();
            nss.setNetworkSubscriptionSettingID(networkSubscriptionSettings.getNetworkSubscriptionSettingId());
            nss.setNetworkSubscriptionSettingName(networkSubscriptionSettings.getNetworkSubscriptionSettingName());
            nss.setNetworkSubscriptionSettingValue(networkSubscriptionSettings.getNetworkSubscriptionSettingValue());
            networkSettings.getNetworkSubscriptionSettings().add(nss);
        }
        tingcoNetworkSubscriptions.setNetworkSettings(networkSettings);
        return tingcoNetworkSubscriptions;
    }

    public TingcoNetworkSubscriptions buildGetNetworkSubscriptions(TingcoNetworkSubscriptions tingcoNetworkSubscriptions, List<GroupTranslations> gtList, List<NetworkSubscriptions> networkSubsList) {
        se.info24.networkjaxb.NetworkSubscriptions networksubs = new se.info24.networkjaxb.NetworkSubscriptions();
        se.info24.networkjaxb.NetworkSubscription networksub = null;
        for (NetworkSubscriptions networkSubscriptions : networkSubsList) {
            networksub = new NetworkSubscription();
            networksub.setNetworkSubscriptionID(networkSubscriptions.getNetworkSubscriptionId());
            if (networkSubscriptions.getSubscriptionNumber() != null) {
                networksub.setSubscriptionNumber(networkSubscriptions.getSubscriptionNumber());
            }
            if (networkSubscriptions.getNetworkSubscriptionName() != null) {
                networksub.setNetworkSubscriptionName(networkSubscriptions.getNetworkSubscriptionName());
            }
            for (GroupTranslations gt : gtList) {
                if (networkSubscriptions.getGroups().getGroupId().equalsIgnoreCase(gt.getId().getGroupId())) {
                    GroupID id = new GroupID();
                    id.setGroupName(gt.getGroupName());
                    id.setValue(gt.getId().getGroupId());
                    networksub.setGroupID(id);
                }
            }
            networksubs.getNetworkSubscription().add(networksub);
        }
        tingcoNetworkSubscriptions.setNetworkSubscriptions(networksubs);
        return tingcoNetworkSubscriptions;
    }

    public TingcoNetworkSubscriptions buildNonLinkedNetworkSubscriptionForDevice(Session session, TingcoNetworkSubscriptions tingcoNetworkSubscriptions, List<NetworkSubscriptions> networkSubsList, int countryId) {
        se.info24.networkjaxb.NetworkSubscriptions networksubs = new se.info24.networkjaxb.NetworkSubscriptions();
        se.info24.networkjaxb.NetworkSubscription networksub = null;
        for (NetworkSubscriptions networkSubscriptions : networkSubsList) {
            networksub = new NetworkSubscription();
            networksub.setNetworkSubscriptionID(networkSubscriptions.getNetworkSubscriptionId());
            if (networkSubscriptions.getSubscriptionNumber() != null) {
                networksub.setSubscriptionNumber(networkSubscriptions.getSubscriptionNumber());
            }
            if (networkSubscriptions.getNetworkSubscriptionName() != null) {
                networksub.setNetworkSubscriptionName(networkSubscriptions.getNetworkSubscriptionName());
            }
            GroupTranslations gt = (GroupTranslations) session.get(GroupTranslations.class, new GroupTranslationsId(networkSubscriptions.getGroupId(), countryId));
            GroupID id = new GroupID();
            id.setGroupName(gt.getGroupName());
            id.setValue(gt.getId().getGroupId());
            networksub.setGroupID(id);
            networksubs.getNetworkSubscription().add(networksub);
        }
        tingcoNetworkSubscriptions.setNetworkSubscriptions(networksubs);
        return tingcoNetworkSubscriptions;
    }

    public TingcoNetworkSubscriptions buildGetNetworkSubscriptionsBySearchCriteria(TingcoNetworkSubscriptions tingcoNetworkSubscriptions, List<NetworkSubscriptions> networkSubscriptionsList, int countryId, String loginGroupId, Session session) throws DatatypeConfigurationException {
        se.info24.networkjaxb.NetworkSubscriptions networkSubscriptions = new se.info24.networkjaxb.NetworkSubscriptions();
        se.info24.networkjaxb.NetworkSubscription networkSubscription = null;
        Set<GroupTranslations> gtSet = new HashSet<GroupTranslations>();
        GroupID groupID;
        AgreementID agreementID;
        int seqNo = 1;
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (NetworkSubscriptions ns : networkSubscriptionsList) {
            networkSubscription = new NetworkSubscription();
            networkSubscription.setSeqNo(seqNo);
            networkSubscription.setNetworkSubscriptionID(ns.getNetworkSubscriptionId());
            if (ns.getSubscriptionNumber() != null) {
                networkSubscription.setSubscriptionNumber(ns.getSubscriptionNumber());
            }
            if (ns.getIpv4() != null) {
                networkSubscription.setIPV4(ns.getIpv4());
            }
            if (ns.getIccid() != null) {
                networkSubscription.setICCID(ns.getIccid());
            }
            if (ns.getCreatedDate() != null) {
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTime(ns.getCreatedDate());
                networkSubscription.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                networkSubscription.setCreatedDateTCMV3(lFormat.format(gc.getTime()));
            }
            if (ns.getDevice() != null) {
                networkSubscription.setDeviceID(ns.getDevice().getDeviceName());
            }
            groupID = new GroupID();
            groupID.setValue(ns.getGroups().getGroupId());
            gtSet = ns.getGroups().getGroupTranslationses();
            for (GroupTranslations gt : gtSet) {
                if (gt.getCountry().getCountryId() == countryId) {
                    groupID.setGroupName(gt.getGroupName());
                    break;
                }
            }
            networkSubscription.setGroupID(groupID);
            agreementID = new AgreementID();
            agreementID.setAgreementName(ns.getAgreements().getAgreementName());
            networkSubscription.setAgreementID(agreementID);

            networkSubscription.setNetworkID(ns.getNetworks().getNetworkName());
            List<ObjectCostCenters> objectCostCenterses = session.getNamedQuery("getObjectCostCentersByGroupIdandObjectId").setString("objectId", ns.getNetworkSubscriptionId()).setString("groupId", loginGroupId).list();
            if (!objectCostCenterses.isEmpty()) {
                GroupDAO groupDAO = new GroupDAO();
                CostCenters costCenters = groupDAO.getCostCenterById(objectCostCenterses.get(0).getCostCenterId(), session);
                networkSubscription.setCostCenterID(ns.getCostCenterId());
                if (costCenters != null) {
                    networkSubscription.setCostCenterName(costCenters.getCostCenterName());
                }
            }
            networkSubscriptions.getNetworkSubscription().add(networkSubscription);
            seqNo++;
        }
        tingcoNetworkSubscriptions.setNetworkSubscriptions(networkSubscriptions);

        return tingcoNetworkSubscriptions;
    }

    public TingcoNetworkSubscriptions buildTingcoNetworkSubscriptionsTemplate() throws DatatypeConfigurationException {
        se.info24.networkjaxb.ObjectFactory objectFactorys = new se.info24.networkjaxb.ObjectFactory();
        TingcoNetworkSubscriptions tingcoNetworkSubscriptions = objectFactorys.createTingcoNetworkSubscriptions();
        tingcoNetworkSubscriptions.setCreateRef("Info24");
        tingcoNetworkSubscriptions.setMsgVer(new BigDecimal("1.0"));
        tingcoNetworkSubscriptions.setRegionalUnits("Metric");
        tingcoNetworkSubscriptions.setTimeZone("UTC");

        tingcoNetworkSubscriptions.setMsgID(UUID.randomUUID().toString());
        tingcoNetworkSubscriptions.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        se.info24.networkjaxb.MsgStatus msgStatus = new se.info24.networkjaxb.MsgStatus();
        msgStatus.setResponseCode(0);
        msgStatus.setResponseText("OK");

        tingcoNetworkSubscriptions.setMsgStatus(msgStatus);
        return tingcoNetworkSubscriptions;
    }

    public TingcoNetworkSubscriptions buildGetAllNetworkSubscriptions(TingcoNetworkSubscriptions tingcoNetworkSubscriptions, List<NetworkSubscriptions> nsList) {
        se.info24.networkjaxb.NetworkSubscriptions networksubs = new se.info24.networkjaxb.NetworkSubscriptions();
        int seqNo = 1;
        for (NetworkSubscriptions networkSubscriptions : nsList) {
            se.info24.networkjaxb.NetworkSubscription networksub = new NetworkSubscription();
            networksub.setSeqNo(seqNo++);
            networksub.setNetworkSubscriptionID(networkSubscriptions.getNetworkSubscriptionId());
            if (networkSubscriptions.getNetworkSubscriptionName() != null) {
                StringBuffer buffer = new StringBuffer(networkSubscriptions.getNetworkSubscriptionName());
                buffer.append("(").append(networkSubscriptions.getSubscriptionNumber() != null ? networkSubscriptions.getSubscriptionNumber() : "").append(")");
                networksub.setNetworkSubscriptionName(buffer.toString());
            }
            networksubs.getNetworkSubscription().add(networksub);
        }
        tingcoNetworkSubscriptions.setNetworkSubscriptions(networksubs);
        return tingcoNetworkSubscriptions;
    }

    public TingcoNetworkSubscriptions buildGetNetworkSubscriptionDetailsoptional(TingcoNetworkSubscriptions tingcoNetworkSubscriptions, List<NetworkSubscriptions> networkSubscriptions, List<GroupTranslations> gts, String timeZoneGMToffset, NetworkSubscriptionsPendingDelete nspd, List<CostCenters> costCenterses) throws DatatypeConfigurationException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        se.info24.networkjaxb.NetworkSubscriptions netSubs = new se.info24.networkjaxb.NetworkSubscriptions();
        for (NetworkSubscriptions ns : networkSubscriptions) {
            se.info24.networkjaxb.NetworkSubscription netSub = new NetworkSubscription();
            netSub.setNetworkSubscriptionID(ns.getNetworkSubscriptionId());
            GregorianCalendar gc = new GregorianCalendar();
            netSub.setNetworkSubscriptionName(ns.getNetworkSubscriptionName());
            if (ns.getSubscriptionNumber() != null) {
                netSub.setSubscriptionNumber(ns.getSubscriptionNumber());
            }
            for (GroupTranslations gt : gts) {
                if (gt.getId().getGroupId().equalsIgnoreCase(ns.getGroups().getGroupId())) {
                    GroupID id = new GroupID();
                    id.setGroupName(gt.getGroupName());
                    id.setValue(gt.getId().getGroupId());
                    netSub.setGroupID(id);
                }
            }


            AgreementID aid = new AgreementID();
            aid.setAgreementName(ns.getAgreements().getAgreementName());
            aid.setValue(ns.getAgreements().getAgreementId());
            netSub.setAgreementID(aid);
            if (ns.getNetworkSubscriptionDescription() != null) {
                netSub.setNetworkSubscriptionDescription(ns.getNetworkSubscriptionDescription());
            }
            if (ns.getIpv4() != null) {
                netSub.setIPV4(ns.getIpv4());
            }
            if (ns.getIpv6() != null) {
                netSub.setIPV6(ns.getIpv6());
            }
            if (ns.getSubscriptionUser() != null) {
                netSub.setSubscriptionUser(ns.getSubscriptionUser());
            }
            if (ns.getSubscriptionPassword() != null) {
                netSub.setSubscriptionPassword(ns.getSubscriptionPassword());
            }
            if (ns.getSubscriptionEnabled() != null) {
                netSub.setSubscriptionEnabled(ns.getSubscriptionEnabled());
            }

            if (ns.getApn() != null) {
                netSub.setAPN(ns.getApn());
            }

            if (ns.getIccid() != null) {
                netSub.setICCID(ns.getIccid());
            }
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, ns.getActiveFromDate()));
            netSub.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc).toString());
            netSub.setActiveFromDateTCMV3(dateFormat.format(gc.getTime()));
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, ns.getActiveToDate()));
            netSub.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc).toString());
            netSub.setActiveToDateTCMV3(dateFormat.format(gc.getTime()));
            if (nspd != null) {
                se.info24.networkjaxb.NetworkSubscriptionsPendingDelete nspdjax = new se.info24.networkjaxb.NetworkSubscriptionsPendingDelete();
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, nspd.getDeleteSubscriptionAfterThisDate()));
                nspdjax.setDeleteSubscriptionAfterThisDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                netSub.getNetworkSubscriptionsPendingDelete().add(nspdjax);
            }
            for (CostCenters costCenters : costCenterses) {
                if (ns.getCostCenterId() != null) {
                    if (ns.getCostCenterId().equalsIgnoreCase(costCenters.getCostCenterId())) {
                        netSub.setCostCenterID(costCenters.getCostCenterId());
                        netSub.setCostCenterName(costCenters.getCostCenterName());
                        netSub.setCostCenterNumber(costCenters.getCostCenterNumber() + "");
                    }
                }
            }

            se.info24.networkjaxb.Networks network = new se.info24.networkjaxb.Networks();
            network.setNetworkID(ns.getNetworks().getNetworkId());
            network.setNetworkName(ns.getNetworks().getNetworkName());
            netSub.getNetworks().add(network);
            if (ns.getNetworkTypes() != null) {
                se.info24.networkjaxb.NetworkTypes nt = new NetworkTypes();
                nt.setNetworkTypeID(ns.getNetworkTypes().getNetworkTypeId());
                nt.setNetworkTypeName(ns.getNetworkTypes().getNetworkTypeName());
                netSub.getNetworkTypes().add(nt);
            }
            if (ns.getNetworkSubscriptionStatuses() != null) {
                se.info24.networkjaxb.NetworkSubscriptionStatus nsss = new se.info24.networkjaxb.NetworkSubscriptionStatus();
                nsss.setNetworkSubscriptionStatusID(ns.getNetworkSubscriptionStatuses().getNetworkSubscriptionStatusId());
                nsss.setStatusName(ns.getNetworkSubscriptionStatuses().getStatusName());
                netSub.getNetworkSubscriptionStatus().add(nsss);

            }
            netSubs.getNetworkSubscription().add(netSub);
        }
        tingcoNetworkSubscriptions.setNetworkSubscriptions(netSubs);
        return tingcoNetworkSubscriptions;
    }
}
