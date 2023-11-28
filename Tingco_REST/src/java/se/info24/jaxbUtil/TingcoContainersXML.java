/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.jaxbUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import se.info24.application.ApplicationDAO;
import se.info24.containers.ContainerDAO;
import se.info24.containersjaxb.Address;
import se.info24.containersjaxb.Color;
import se.info24.containersjaxb.Container;
import se.info24.containersjaxb.Containers;
import se.info24.containersjaxb.ContainerData;
import se.info24.containersjaxb.ContainerUserAlias;
import se.info24.containersjaxb.Fields;
import se.info24.containersjaxb.FillLevel;
import se.info24.containersjaxb.GroupID;
import se.info24.containersjaxb.MsgStatus;
import se.info24.containersjaxb.ObjectContactTypes;
import se.info24.containersjaxb.ObjectFactory;
import se.info24.containersjaxb.ProductVariants;
import se.info24.containersjaxb.Service;
import se.info24.containersjaxb.TingcoContainers;
import se.info24.containersjaxb.Unit;
import se.info24.device.DeviceDAO;
import se.info24.group.GroupDAO;
import se.info24.pojo.Agreements;
import se.info24.pojo.ContainerDevices;
import se.info24.pojo.ContainerShapes;
import se.info24.pojo.ContainerTypes;
import se.info24.pojo.CostCenters;
import se.info24.pojo.Device;
import se.info24.pojo.DeviceDataitemTranslations;
import se.info24.pojo.DeviceTypes;
import se.info24.pojo.EventTypeTranslations;
import se.info24.pojo.FieldTranslations;
import se.info24.pojo.FieldTypes;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.ListValues;
import se.info24.pojo.ObjectContactMemberships;
import se.info24.pojo.ObjectContactTypeTranslations;
import se.info24.pojo.ObjectContacts;
import se.info24.pojo.ObjectCostCenters;
import se.info24.pojo.ObjectStateCodeTranslations;
import se.info24.pojo.ObjectTypeFields;
import se.info24.pojo.ProductVariantTranslations;
import se.info24.pojo.SensorTypes;
import se.info24.pojo.Services;
import se.info24.pojo.SupportCaseDeviceLinks;
import se.info24.pojo.SupportCaseStatuses;
import se.info24.pojo.UnitTranslations;
import se.info24.pojo.UserAlias;
import se.info24.products.ProductsDAO;
import se.info24.restUtil.RestUtilDAO;

/**
 *
 * @author Vijayakumar
 */
public class TingcoContainersXML {

    TingcoContainers containersXML;
    ContainerDAO containerDAO = new ContainerDAO();
    GroupDAO groupDAO = new GroupDAO();
    ProductsDAO productsDAO = new ProductsDAO();
    DeviceDAO deviceDAO = new DeviceDAO();

    public TingcoContainers buildContainerData(TingcoContainers tingcoContainers, se.info24.pojo.Containers cont, List<se.info24.ismOperationsPojo.ContainerData> containerDataList, Session session, String timeZoneGMToffset) throws DatatypeConfigurationException {
        Collections.reverse(containerDataList);
//        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        Containers containers = new Containers();
        Container container = new Container();
        ContainerData containerData = new se.info24.containersjaxb.ContainerData();
        container.setContainerMaxLevel(cont.getContainerMaxLevel().setScale(0, RoundingMode.HALF_UP));
        if (cont.getWarningLowValue() != null) {
            container.setWarningLowValue(cont.getWarningLowValue().setScale(0, RoundingMode.HALF_UP));
        }
        if (cont.getAlarmLowValue() != null) {
            container.setAlarmLowValue(cont.getAlarmLowValue().setScale(0, RoundingMode.HALF_UP));
        }
        if (cont.getProductVariantUnit() != null) {
            container.setProductVariantUnit(cont.getProductVariantUnit());
        }
//        int dataId = 1;
        for (se.info24.ismOperationsPojo.ContainerData cd : containerDataList) {
            FillLevel fillLevel = new FillLevel();
//            fillLevel.setID(dataId);
//            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, cd.getDataItemTime()));
            fillLevel.setDataItemTime(String.valueOf(RestUtilDAO.convertGMTtoUserLocalTimeInLongFormat(timeZoneGMToffset, cd.getDataItemTime())));
            fillLevel.setValue(String.valueOf(cd.getFillLevel().setScale(0, RoundingMode.HALF_UP)));
            containerData.getFillLevel().add(fillLevel);
//            dataId++;
        }
        container.getContainerData().add(containerData);
        containers.getContainer().add(container);
        tingcoContainers.setContainers(containers);
        return tingcoContainers;
    }

    public TingcoContainers buildContainerLevelMonitorDetails(List<se.info24.pojo.Containers> listContainers, int countryID, Session session, TingcoContainers tingcoContainers, String timeZoneGMToffset) throws DatatypeConfigurationException {
        Containers containersJaxb = new Containers();
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<Container> containerJaxbList = new ArrayList<Container>();
        int seqId = 1;
        GregorianCalendar gc = new GregorianCalendar();
        for (se.info24.pojo.Containers containers : listContainers) {
            Container containerJaxb = new Container();
            containerJaxb.setSeqNo(seqId++);
            containerJaxb.setContainerID(containers.getContainerId());
            if (containers.getContainerName() != null) {
                containerJaxb.setContainerName(containers.getContainerName());
            }
            if (containers.getContainerNumber() != null) {
                containerJaxb.setContainerNumber(containers.getContainerNumber());
            }
            if (containers.getCurrentFillLevel() != null) {
                containerJaxb.setCurrentFillLevel(containers.getCurrentFillLevel().setScale(0, RoundingMode.HALF_UP));
            }
            if (containers.getContainerFullLevel() != null) {
                containerJaxb.setContainerFullLevel(containers.getContainerFullLevel().setScale(0, RoundingMode.HALF_UP));
            }
            if (containers.getCurrentFillLevelPercent() != null) {
                if (containers.getCurrentFillLevelPercent() > 100) {
                    containerJaxb.setCurrentFillLevelPercent(new BigInteger("100"));
                    containerJaxb.setCurrentFillLevelPercentTCMV3("100%");
                } else {
                    containerJaxb.setCurrentFillLevelPercent(BigInteger.valueOf(containers.getCurrentFillLevelPercent()));
                    containerJaxb.setCurrentFillLevelPercentTCMV3(containers.getCurrentFillLevelPercent() + "%");
                }
//                containerJaxb.setCurrentFillLevelPercent(containers.getCurrentFillLevelPercent() > 100 ? 100 : BigInteger.valueOf(containers.getCurrentFillLevelPercent()));
            } else {
                containerJaxb.setCurrentFillLevelPercentTCMV3("0%");
            }
            if (containers.getCurrentFreeLevel() != null) {
                containerJaxb.setCurrentFreeLevel(containers.getCurrentFreeLevel().setScale(0, RoundingMode.HALF_UP));
            }
            if (containers.getCurrentFreeLevelPercent() != null) {
                containerJaxb.setCurrentFreeLevelPercent(containers.getCurrentFreeLevelPercent());
            }
            if (containers.getContainerMaxLevel() != null) {
                containerJaxb.setContainerMaxLevel(containers.getContainerMaxLevel().setScale(0, RoundingMode.HALF_UP));
            }
            if (containers.getContainerMinLevel() != null) {
                containerJaxb.setContainerMinLevel(containers.getContainerMinLevel().setScale(0, RoundingMode.HALF_UP));
            }
            if (containers.getSalesNumber() != null) {
                containerJaxb.setSalesNumber(containers.getSalesNumber());
            }
            if (containers.getDepot() != null) {
                containerJaxb.setDepot(containers.getDepot());
            }
            if (containers.getProductVariantUnit() != null) {
                containerJaxb.setProductVariantUnit(containers.getProductVariantUnit());
            }

            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, containers.getUpdatedDate()));
            containerJaxb.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            containerJaxb.setUpdatedDateTCMV3(lFormat.format(gc.getTime()));

            List<GroupTranslations> gtList = groupDAO.getGroupTranslationbyGroupId(containers.getGroups().getGroupId(), session);
            for (GroupTranslations gt : gtList) {
                if (countryID == gt.getCountry().getCountryId()) {
                    GroupID gID = new GroupID();
                    gID.setGroupName(gt.getGroupName());
                    gID.setValue(containers.getGroups().getGroupId());
                    containerJaxb.setGroupID(gID);
                    break;
                }
            }

            ProductVariantTranslations pvTrans = productsDAO.getProductVariantTranslationsByIds(containers.getProductVariantId(), countryID, session);
            if (pvTrans != null) {
                se.info24.containersjaxb.ProductVariantTranslations pvtJaxb = new se.info24.containersjaxb.ProductVariantTranslations();
                pvtJaxb.setProductVariantID(pvTrans.getProductVariants().getProductVariantId());
                pvtJaxb.setProductVariantName(pvTrans.getProductVariantName());
                containerJaxb.getProductVariantTranslations().add(pvtJaxb);
            }

            if (containers.getAddresses() != null) {
                Address add = new Address();
                add.setAddressID(containers.getAddresses().getAddressId());
                add.setAddressCity(containers.getAddresses().getAddressCity());
                containerJaxb.getAddress().add(add);
            }

            List<ContainerDevices> listContainerDevices = containerDAO.getContainerDevicesByContainerId(containers.getContainerId(), session);

            if (!listContainerDevices.isEmpty()) {
                List<String> deviceIDList = new ArrayList<String>();

                for (ContainerDevices cd : listContainerDevices) {
                    deviceIDList.add(cd.getDevice().getDeviceId());
                }
                List<SupportCaseDeviceLinks> scdlList = containerDAO.getSupportCaseDeviceLinksByDeviceID(deviceIDList, session);

                for (SupportCaseDeviceLinks scdl : scdlList) {
                    SupportCaseStatuses supportCaseStatuses = containerDAO.getSupportCaseStatusesById(scdl.getId().getSupportCaseId(), session);
                    if (supportCaseStatuses != null) {
                        se.info24.containersjaxb.SupportCaseStatuses supportJaxb = new se.info24.containersjaxb.SupportCaseStatuses();
                        supportJaxb.setSortOrder(supportCaseStatuses.getSortOrder());
                        supportJaxb.setIsClosed(supportCaseStatuses.getIsClosed());
                        containerJaxb.getSupportCaseStatuses().add(supportJaxb);
                    }
                }
            }
            //Status Color
            Date updatedDate = containers.getUpdatedDate();
            Date now = new Date();

            long diff = now.getTime() - updatedDate.getTime();

            long diffInDays = diff / (24 * 60 * 60 * 1000);
            if (diffInDays > 1) {
                containerJaxb.setStatusColor(Color.RED);
            } else {
                containerJaxb.setStatusColor(Color.GREEN);
            }

            containerJaxb.setLevelPercentColor(Color.GREEN);
            //FillLevelPercentColor
            if (containers.getCurrentFillLevel() != null && containers.getWarningLowValue() != null) {
                if (containers.getCurrentFillLevel().floatValue() < containers.getWarningLowValue().floatValue()) {
                    containerJaxb.setLevelPercentColor(Color.ORANGE);
                }
            }
            if (containers.getCurrentFillLevel() != null && containers.getAlarmLowValue() != null) {
                if (containers.getCurrentFillLevel().floatValue() < containers.getAlarmLowValue().floatValue()) {
                    containerJaxb.setLevelPercentColor(Color.RED);
                }
            }
            containerJaxbList.add(containerJaxb);
        }
        containersJaxb.getContainer().addAll(containerJaxbList);
        tingcoContainers.setContainers(containersJaxb);
        return tingcoContainers;
    }

    public TingcoContainers buildContainerListReport(TingcoContainers tingcoContainers, List<se.info24.pojo.Containers> containerList, int countryID, Session session) {
        Containers containersJaxb = new Containers();
        List<Container> containerJaxbList = new ArrayList<Container>();
        for (se.info24.pojo.Containers containers : containerList) {
            Container containerJaxb = new Container();
            containerJaxb.setContainerID(containers.getContainerId());
            if (containers.getContainerName() != null) {
                containerJaxb.setContainerName(containers.getContainerName());
            }
            if (containers.getContainerNumber() != null) {
                containerJaxb.setContainerNumber(containers.getContainerNumber());
            }

            GroupTranslations gt = groupDAO.getGroupTranslationsByIds(containers.getGroups().getGroupId(), countryID, session);
            GroupID gID = new GroupID();
            gID.setGroupName(gt.getGroupName());
            gID.setValue(containers.getGroups().getGroupId());
            containerJaxb.setGroupID(gID);

            if (containers.getAgreements() != null) {
                ApplicationDAO applicationDAO = new ApplicationDAO();
                Agreements agreements = (Agreements) applicationDAO.getAgreementsById(session, containers.getAgreements().getAgreementId());
                containerJaxb.setAgreementID(containers.getAgreements().getAgreementId());
                containerJaxb.setAgreementName(agreements.getAgreementName());
            }

            if (containers.getCostCenters() != null) {
                CostCenters costCenters = groupDAO.getCostCenterById(containers.getCostCenters().getCostCenterId(), session);
                containerJaxb.setCostCenterID(containers.getCostCenters().getCostCenterId());
                containerJaxb.setCostCenterName(costCenters.getCostCenterName());
            }

            List<ContainerDevices> cdlist = containerDAO.getContainerDevicesByContainerId(containers.getContainerId(), session);
            if (!cdlist.isEmpty()) {
                Device device = deviceDAO.getDeviceById(cdlist.get(0).getDevice().getDeviceId(), session);
                se.info24.containersjaxb.ContainerDevices containerDevices = new se.info24.containersjaxb.ContainerDevices();
                containerDevices.setDeviceID(device.getDeviceId());
                containerDevices.setDeviceName(device.getDeviceName());

                DeviceTypes deviceTypes = (DeviceTypes) deviceDAO.getDeviceTypesById(device.getDeviceTypes().getDeviceTypeId(), session);
                containerDevices.setDeviceTypeID(deviceTypes.getDeviceTypeId());
                containerDevices.setDeviceTypeName(deviceTypes.getDeviceTypeName());
                containerJaxb.getContainerDevices().add(containerDevices);
            }
            containerJaxbList.add(containerJaxb);
        }

        containersJaxb.getContainer().addAll(containerJaxbList);
        tingcoContainers.setContainers(containersJaxb);
        return tingcoContainers;
    }

    public TingcoContainers buildContainerListReport1(TingcoContainers tingcoContainers, List<Object> containersList, int countryID, Session session) {
        Containers containersJaxb = new Containers();
        List<Container> containerJaxbList = new ArrayList<Container>();
        for (Object result : containersList) {
            Map map = (Map) result;
            se.info24.pojo.Containers containers = (se.info24.pojo.Containers) map.get(Criteria.ROOT_ALIAS);
            Device device = (Device) map.get("d");
            DeviceTypes deviceTypes = (DeviceTypes) map.get("dt");
            GroupTranslations gt = (GroupTranslations) map.get("gt");
            Agreements agreements = (Agreements) map.get("a");
            CostCenters costCenters = (CostCenters) map.get("cc");

            Container containerJaxb = new Container();
            containerJaxb.setContainerID(containers.getContainerId());
            if (containers.getContainerName() != null) {
                containerJaxb.setContainerName(containers.getContainerName());
            }
            if (containers.getContainerNumber() != null) {
                containerJaxb.setContainerNumber(containers.getContainerNumber());
            }

//            GroupTranslations gt = groupDAO.getGroupTranslationsByIds(containers.getGroupId(), countryID, session);
//            GroupID gID = new GroupID();
//            gID.setGroupName(gt.getGroupName());
//            gID.setValue(containers.getGroupId());
//            containerJaxb.setGroupID(gID);
//
//            if (containers.getAgreements() != null) {
//                ApplicationDAO applicationDAO = new ApplicationDAO();
//                Agreements agreements = (Agreements) applicationDAO.getAgreementsById(session, containers.getAgreements().getAgreementId());
//                containerJaxb.setAgreementID(containers.getAgreements().getAgreementId());
//                containerJaxb.setAgreementName(agreements.getAgreementName());
//            }
//
//            if(containers.getCostCenters() != null) {
//                CostCenters costCenters = groupDAO.getCostCenterById(containers.getCostCenters().getCostCenterId(), session);
//                containerJaxb.setCostCenterID(containers.getCostCenters().getCostCenterId());
//                containerJaxb.setCostCenterName(costCenters.getCostCenterName());
//            }
//
//            List<ContainerDevices> cdlist = containerDAO.getContainerDevicesByContainerId(containers.getContainerId(), session);
//            if(!cdlist.isEmpty()) {
//            Device device = deviceDAO.getDeviceById(cdlist.get(0).getDevice().getDeviceId(), session);
//            se.info24.containersjaxb.ContainerDevices containerDevices = new se.info24.containersjaxb.ContainerDevices();
//            containerDevices.setDeviceID(device.getDeviceId());
//            containerDevices.setDeviceName(device.getDeviceName());
//
//            DeviceTypes deviceTypes = deviceDAO.getDeviceTypesById(device.getDeviceTypes().getDeviceTypeId(), session);
//            containerDevices.setDeviceTypeID(deviceTypes.getDeviceTypeId());
//            containerDevices.setDeviceTypeName(deviceTypes.getDeviceTypeName());
//            containerJaxb.getContainerDevices().add(containerDevices);
//            }
            containerJaxbList.add(containerJaxb);
        }

        containersJaxb.getContainer().addAll(containerJaxbList);
        tingcoContainers.setContainers(containersJaxb);
        return tingcoContainers;
    }

    public TingcoContainers buildContainerShapes(TingcoContainers tingcoContainers, List<ContainerShapes> containerShapesList) {
        Containers containers = new Containers();
        Container container = new Container();
        for (ContainerShapes cs : containerShapesList) {
            se.info24.containersjaxb.ContainerShapes containerShapes = new se.info24.containersjaxb.ContainerShapes();
            containerShapes.setContainerShapeID(cs.getContainerShapeId());
            containerShapes.setContainerShapeName(cs.getContainerShapeName());
            container.getContainerShapes().add(containerShapes);
        }
        containers.getContainer().add(container);
        tingcoContainers.setContainers(containers);
        return tingcoContainers;
    }

    public TingcoContainers buildContainerTypes(TingcoContainers tingcoContainers, List<ContainerTypes> containerTypesList) {
        Containers containers = new Containers();
        Container container = new Container();
        for (ContainerTypes ct : containerTypesList) {
            se.info24.containersjaxb.ContainerTypes containerTypes = new se.info24.containersjaxb.ContainerTypes();
            containerTypes.setContainerTypeID(ct.getContainerTypeId());
            containerTypes.setContainerTypeName(ct.getContainerTypeName());
            container.getContainerTypes().add(containerTypes);
        }
        containers.getContainer().add(container);
        tingcoContainers.setContainers(containers);
        return tingcoContainers;
    }

    public TingcoContainers buildContainers(TingcoContainers tingcoContainers, se.info24.pojo.Containers con) {
        Containers containers = new Containers();
        Container container = new Container();
        container.setContainerID(con.getContainerId());
        containers.getContainer().add(container);
        tingcoContainers.setContainers(containers);
        return tingcoContainers;
    }

    public TingcoContainers buildContainers(TingcoContainers tingcoContainers, se.info24.pojo.Containers cont, int countryId, Session session, String timeZoneGMToffset, int totalfilling, int averagefilling, int numberoffillings, int totalconsumption, int averageconsumption, List<Services> service) throws DatatypeConfigurationException {
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Containers containers = new Containers();
        Container container = new Container();

        Fields fieldsjaxb = getfields(cont.getContainerTypes().getContainerTypeId(), cont.getContainerId(), session, countryId);
        if (fieldsjaxb.getField() != null || fieldsjaxb.getField().size() > 0) {
            container.getFields().add(fieldsjaxb);
        }
        container.setContainerID(cont.getContainerId());
        container.setContainerName(cont.getContainerName());
        if (cont.getContainerNumber() != null) {
            container.setContainerNumber(cont.getContainerNumber());
        }
        if (cont.getCurrentFillLevelPercent() != null) {
            container.setCurrentFillLevelPercent(BigInteger.valueOf(cont.getCurrentFillLevelPercent()));
        }
        if (cont.getCurrentFillLevel() != null) {
            container.setCurrentFillLevel(cont.getCurrentFillLevel().setScale(0, RoundingMode.HALF_UP));
        }
        if (cont.getProductVariantUnit() != null) {
            container.setProductVariantUnit(cont.getProductVariantUnit());
        }


        container.setContainerMinLevel(cont.getContainerMinLevel().setScale(0, RoundingMode.HALF_UP));
        container.setContainerMaxLevel(cont.getContainerMaxLevel().setScale(0, RoundingMode.HALF_UP));

        if (cont.getAlarmLowValue() != null) {
            container.setAlarmLowValue(cont.getAlarmLowValue().setScale(0, RoundingMode.HALF_UP));
        }
        if (cont.getWarningLowValue() != null) {
            container.setWarningLowValue(cont.getWarningLowValue().setScale(0, RoundingMode.HALF_UP));
        }
        if (cont.getWarningHighValue() != null) {
            container.setWarningHighValue(cont.getWarningHighValue());
        }
        if (cont.getAlarmHighValue() != null) {
            container.setAlarmHighValue(cont.getAlarmHighValue());
        }
        if (cont.getOrderMoreBelowThisValue() != null) {
            container.setOrderMoreBelowThisValue(cont.getOrderMoreBelowThisValue());
        }

        if (cont.getUpdatedDate() != null) {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, cont.getUpdatedDate()));
            container.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            container.setUpdatedDateTCMV3(lFormat.format(gc.getTime()));
        }

        GroupTranslations gt = groupDAO.getGroupTranslationsByIds(cont.getGroups().getGroupId(), countryId, session);
        if (gt != null) {
            GroupID groupId = new GroupID();
            groupId.setGroupName(gt.getGroupName());
            groupId.setValue(cont.getGroups().getGroupId());
            container.setGroupID(groupId);
        }

        if (cont.getProductVariantId() != null) {
            ProductVariantTranslations pvt = productsDAO.getProductVariantTranslationsByIds(cont.getProductVariantId(), countryId, session);
            if (pvt != null) {
                se.info24.containersjaxb.ProductVariantTranslations prodVariantTrans = new se.info24.containersjaxb.ProductVariantTranslations();
                prodVariantTrans.setProductVariantID(cont.getProductVariantId());
                prodVariantTrans.setProductVariantName(pvt.getProductVariantName());
                container.getProductVariantTranslations().add(prodVariantTrans);
            }
        }
        container.setTotalFilling(totalfilling + "");
        container.setAverageContainerFilling(averagefilling + "");
        container.setNumberOfFillings(numberoffillings + "");
        container.setTotalConsumption(totalconsumption + "");
        container.setAverageConsumption(averageconsumption + "");
        //update for tcmv3
        if (cont.getSalesNumber() != null) {
            container.setSalesNumber(cont.getSalesNumber());

        }
        if (cont.getDepot() != null) {
            container.setDepot(cont.getDepot());
        }

        if (cont.getAddresses() != null) {
            Address add = new Address();
            if (cont.getAddresses().getAddressStreet() != null) {
                add.setAddressStreet(cont.getAddresses().getAddressStreet());
            }
            if (cont.getAddresses().getAddressSuite() != null) {
                add.setAddressSuite(cont.getAddresses().getAddressSuite());
            }
            if (cont.getAddresses().getAddressZip() != null) {
                add.setAddressZip(cont.getAddresses().getAddressZip());
            }
            if (cont.getAddresses().getAddressCity() != null) {
                add.setAddressCity(cont.getAddresses().getAddressCity());
            }
            if (cont.getAddresses().getCountry().getCountryName() != null) {

                add.setCountryName(cont.getAddresses().getCountry().getCountryName());
            }
            container.getAddress().add(add);
        }
        List<ObjectContactMemberships> objectContactMembershipses = deviceDAO.getObjectContactMemberships(cont.getContainerId(), session);
        if (!objectContactMembershipses.isEmpty()) {
            List<String> objectContactId = new ArrayList<String>();
            for (ObjectContactMemberships objectContactMemberships : objectContactMembershipses) {
                objectContactId.add(objectContactMemberships.getId().getObjectContactId());
            }
            List<ObjectContacts> objectContactses = deviceDAO.getObjectContacts(objectContactId, session);
            for (ObjectContacts objectContacts : objectContactses) {
                se.info24.containersjaxb.ObjectContacts oc = new se.info24.containersjaxb.ObjectContacts();
                oc.setContactFirstName(objectContacts.getContactFirstName());
                oc.setContactLastName(objectContacts.getContactLastName());
                if (objectContacts.getContactOrganizationName() != null) {
                    oc.setContactOrganizationName(objectContacts.getContactOrganizationName());

                }
                oc.setObjectContactID(objectContacts.getObjectContactId());
                container.getObjectContacts().add(oc);
            }
        }
        if (!service.isEmpty()) {
            se.info24.containersjaxb.Services services = new se.info24.containersjaxb.Services();
            Service servicejaxb = null;
            for (Services servic : service) {
                servicejaxb = new Service();
                servicejaxb.setServiceName(servic.getServiceName());
                services.getService().add(servicejaxb);
            }
            container.setServices(services);
        }

        List<ContainerDevices> containerDeviceses = deviceDAO.getContainerDevicesByContainerIdOrderBy(cont.getContainerId(), session);
        for (ContainerDevices containerDevices : containerDeviceses) {
            se.info24.containersjaxb.ContainerDevices containerdeviceJaxb = new se.info24.containersjaxb.ContainerDevices();
            containerdeviceJaxb.setDeviceID(containerDevices.getDevice().getDeviceId());
            Fields fieldsjaxbDevice = getfields(containerDevices.getDevice().getDeviceTypes().getDeviceTypeId(), containerDevices.getDevice().getDeviceId(), session, countryId);
            if (fieldsjaxbDevice.getField() != null || fieldsjaxbDevice.getField().size() > 0) {
                containerdeviceJaxb.getFields().add(fieldsjaxbDevice);
            }
            container.getContainerDevices().add(containerdeviceJaxb);
        }
        if (!containerDeviceses.isEmpty()) {
            Device device = deviceDAO.getDeviceById(containerDeviceses.get(0).getId().getDeviceId(), session);
            container.setDeviceID(device.getDeviceId());
            container.setDeviceName(device.getDeviceName());

        }
        containers.getContainer().add(container);
        tingcoContainers.setContainers(containers);
        return tingcoContainers;
    }

    Fields getfields(String objectTypeId, String objectId, Session session, int countryId) {
        Fields fieldssJaxb = new Fields();
        List<ObjectTypeFields> objectTypeFieldList = deviceDAO.getObjectTypeFieldsByObjectID(session, objectTypeId);
        List<String> fieldIds = new ArrayList<String>();
        for (ObjectTypeFields otf : objectTypeFieldList) {
            se.info24.pojo.Fields fields = otf.getFields();
            fieldIds.add(fields.getFieldId());
        }
        if (fieldIds.isEmpty()) {
            return fieldssJaxb;
        }
        List<FieldTranslations> fieldTransList = deviceDAO.getFieldTranslationsByIdList(session, fieldIds, countryId + "");

        for (FieldTranslations ft : fieldTransList) {
            se.info24.containersjaxb.Field fieldsJaxb = new se.info24.containersjaxb.Field();
            se.info24.containersjaxb.FieldTranslations fieldsTransJaxb = new se.info24.containersjaxb.FieldTranslations();
            se.info24.containersjaxb.FieldTypes fieldsTypesJaxb = new se.info24.containersjaxb.FieldTypes();

            se.info24.pojo.Fields field = ft.getFields();
            se.info24.pojo.ObjectFieldData objectFieldData = deviceDAO.getObjectFieldDataByID(session, objectId, field.getFieldId());

            fieldsJaxb.setFieldID(field.getFieldId());

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
            FieldTypes fieldTypes = field.getFieldTypes();
            fieldsTypesJaxb.setFieldTypeID(fieldTypes.getFieldTypeId());
            fieldsTypesJaxb.setFieldTypeCode(fieldTypes.getFieldTypeCode());


            fieldsJaxb.getFieldTranslations().add(fieldsTransJaxb);
            fieldsJaxb.getFieldTypes().add(fieldsTypesJaxb);
            fieldssJaxb.getField().add(fieldsJaxb);
        }
        return fieldssJaxb;
    }

    public TingcoContainers buildGetAllSensorTypes(TingcoContainers tingcoContainers, List<SensorTypes> sensorTypesList) {
        Containers containers = new Containers();
        Container container = new Container();
        se.info24.containersjaxb.ContainerDevices containerDevices = new se.info24.containersjaxb.ContainerDevices();
        for (SensorTypes st : sensorTypesList) {
            se.info24.containersjaxb.SensorTypes sensorTypes = new se.info24.containersjaxb.SensorTypes();
            sensorTypes.setSensorTypeID(st.getSensorTypeId());
            sensorTypes.setSensorTypeName(st.getSensorTypeName());
            containerDevices.getSensorTypes().add(sensorTypes);
        }
        container.getContainerDevices().add(containerDevices);
        containers.getContainer().add(container);
        tingcoContainers.setContainers(containers);
        return tingcoContainers;
    }

    public TingcoContainers buildGetAllUnits(TingcoContainers tingcoContainers, List<UnitTranslations> unitTransList) {
        Containers containers = new Containers();
        Container container = new Container();
        Unit unit = new Unit();
        for (UnitTranslations uts : unitTransList) {
            se.info24.containersjaxb.UnitTranslations unitTrans = new se.info24.containersjaxb.UnitTranslations();
            unitTrans.setUnitID(uts.getId().getUnitId());
            unitTrans.setUnitName(uts.getUnitName());
            unit.getUnitTranslations().add(unitTrans);
        }
        container.getUnit().add(unit);
        containers.getContainer().add(container);
        tingcoContainers.setContainers(containers);
        return tingcoContainers;
    }

    public TingcoContainers buildTingcoContainersTemplate() throws DatatypeConfigurationException {
        ObjectFactory objectFactory = new ObjectFactory();
        containersXML = objectFactory.createTingcoContainers();
        containersXML.setCreateRef("Info24");
        containersXML.setMsgVer(new BigDecimal("1.0"));
        containersXML.setRegionalUnits("Metric");
        containersXML.setTimeZone("UTC");

        containersXML.setMsgID(UUID.randomUUID().toString());
        containersXML.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        MsgStatus msgStatus = new MsgStatus();
        msgStatus.setResponseCode(0);
        msgStatus.setResponseText("OK");

        containersXML.setMsgStatus(msgStatus);

        return containersXML;
    }

    public TingcoContainers buildGetObjectContacts(TingcoContainers tingcoContainers, List<ObjectContacts> ObjectContactList) {

        Containers containers = new Containers();
        Container container = new Container();
        se.info24.containersjaxb.ObjectContacts ocs = null;
        for (ObjectContacts objectContacts : ObjectContactList) {
            ocs = new se.info24.containersjaxb.ObjectContacts();
            ocs.setObjectContactID(objectContacts.getObjectContactId());
            ocs.setContactFirstName(objectContacts.getContactFirstName());
            ocs.setContactLastName(objectContacts.getContactLastName());
            ocs.setContactOrganizationName(objectContacts.getContactOrganizationName());
            container.getObjectContacts().add(ocs);
        }
        containers.getContainer().add(container);
        tingcoContainers.setContainers(containers);
        return tingcoContainers;
    }

    public TingcoContainers buildGetObjectContactTypes(TingcoContainers tingcoContainers, List<ObjectContactTypeTranslations> octtList) {
        Containers containers = new Containers();
        Container container = new Container();
        se.info24.containersjaxb.ObjectContacts ocs = new se.info24.containersjaxb.ObjectContacts();
        se.info24.containersjaxb.ObjectContactTypes oct = new se.info24.containersjaxb.ObjectContactTypes();
        se.info24.containersjaxb.ObjectContactTypeTranslations octt = null;
        for (ObjectContactTypeTranslations objectContactTypeTranslations : octtList) {
            octt = new se.info24.containersjaxb.ObjectContactTypeTranslations();
            if (objectContactTypeTranslations.getObjectContactTypes() != null) {
                octt.setObjectContactTypeID(objectContactTypeTranslations.getId().getObjectContactTypeId());
            }
            octt.setObjectContactTypeName(objectContactTypeTranslations.getObjectContactTypeName());
            oct.getObjectContactTypeTranslations().add(octt);

        }
        ocs.setObjectContactTypes(oct);
        container.getObjectContacts().add(ocs);
        containers.getContainer().add(container);
        tingcoContainers.setContainers(containers);
        return tingcoContainers;
    }

    public TingcoContainers buildGetObjectContactInfo(TingcoContainers tingcoContainers, ObjectContacts oc, List<ObjectContactTypeTranslations> octt) {
        Containers containers = new Containers();
        Container container = new Container();
        se.info24.containersjaxb.ObjectContacts ocs = new se.info24.containersjaxb.ObjectContacts();
        ocs.setObjectContactID(oc.getObjectContactId());
        ocs.setContactFirstName(oc.getContactFirstName());
        ocs.setContactLastName(oc.getContactLastName());
        if (oc.getContactOrganizationName() != null) {
            ocs.setContactOrganizationName(oc.getContactOrganizationName());
        }
        if (oc.getContactMobilePhone() != null) {
            ocs.setContactMobilePhone(oc.getContactMobilePhone());
        }
        if (oc.getContactEmail() != null) {
            ocs.setContactEmail(oc.getContactEmail());
        }
        if (!octt.isEmpty()) {
            ObjectContactTypes oct = new ObjectContactTypes();
            se.info24.containersjaxb.ObjectContactTypeTranslations octtJax = new se.info24.containersjaxb.ObjectContactTypeTranslations();
            octtJax.setObjectContactTypeName(octt.get(0).getObjectContactTypeName());
            oct.getObjectContactTypeTranslations().add(octtJax);
            ocs.setObjectContactTypes(oct);
        }
        ocs.setObjectContactTypeID(oc.getObjectContactTypes().getObjectContactTypeId());
        container.getObjectContacts().add(ocs);
        containers.getContainer().add(container);
        tingcoContainers.setContainers(containers);
        return tingcoContainers;
    }

    public TingcoContainers buildGetContainerNotificationDetails(TingcoContainers tingcoContainers, se.info24.pojo.Containers cs, List<EventTypeTranslations> eventTypeTranslationses, Session session) {
        Containers containers = new Containers();
        Container container = new Container();
        container.setContainerID(cs.getContainerId());
        if (cs.getWarningLowValue() != null) {
            container.setWarningLowValue(cs.getWarningLowValue());
        }
        if (cs.getWarningHighValue() != null) {
            container.setWarningHighValue(cs.getWarningHighValue());
        }

        if (cs.getWarningLowEventTypeId() != null) {
            container.setWarningLowEventTypeID(cs.getWarningLowEventTypeId());
            for (EventTypeTranslations eventTypeTranslations : eventTypeTranslationses) {
                if (eventTypeTranslations.getId().getEventTypeId().equalsIgnoreCase(cs.getWarningLowEventTypeId())) {
                    container.setWarningLowEventTypeName(eventTypeTranslations.getEventTypeName());
                }
            }
        }

        if (cs.getWarningHighEventTypeId() != null) {
            container.setWarningHighEventTypeID(cs.getWarningHighEventTypeId());
            for (EventTypeTranslations eventTypeTranslations : eventTypeTranslationses) {
                if (eventTypeTranslations.getId().getEventTypeId().equalsIgnoreCase(cs.getWarningHighEventTypeId())) {
                    container.setWarningHighEventTypeName(eventTypeTranslations.getEventTypeName());
                }
            }
        }

        if (cs.getAlarmLowValue() != null) {
            container.setAlarmLowValue(cs.getAlarmLowValue());
        }

        if (cs.getAlarmHighValue() != null) {
            container.setAlarmHighValue(cs.getAlarmHighValue());
        }

        if (cs.getAlarmLowEventTypeId() != null) {
            container.setAlarmLowEventTypeID(cs.getAlarmLowEventTypeId());
            for (EventTypeTranslations eventTypeTranslations : eventTypeTranslationses) {
                if (eventTypeTranslations.getId().getEventTypeId().equalsIgnoreCase(cs.getAlarmLowEventTypeId())) {
                    container.setAlarmLowEventTypeName(eventTypeTranslations.getEventTypeName());
                }
            }
        }

        if (cs.getAlarmHighEventTypeId() != null) {
            container.setAlarmHighEventTypeID(cs.getAlarmHighEventTypeId());
            for (EventTypeTranslations eventTypeTranslations : eventTypeTranslationses) {
                if (eventTypeTranslations.getId().getEventTypeId().equalsIgnoreCase(cs.getAlarmHighEventTypeId())) {
                    container.setAlarmHighEventTypename(eventTypeTranslations.getEventTypeName());
                }
            }
        }

        if (cs.getOrderMoreBelowThisValue() != null) {
            container.setOrderMoreBelowThisValue(cs.getOrderMoreBelowThisValue());
        }
        if (cs.getOrderMoreEventTypeId() != null) {
            container.setOrderMoreEventTypeID(cs.getOrderMoreEventTypeId());
            for (EventTypeTranslations eventTypeTranslations : eventTypeTranslationses) {
                if (eventTypeTranslations.getId().getEventTypeId().equalsIgnoreCase(cs.getOrderMoreEventTypeId())) {
                    container.setOrderMoreEventTypeName(eventTypeTranslations.getEventTypeName());
                }
            }
        }
        if (cs.getFlagSetValueToMaxIfAboveMax() != null) {
            container.setFlagSetValueToMaxIfAboveMax(BigInteger.valueOf(cs.getFlagSetValueToMaxIfAboveMax()));
        }
        if (cs.getFlagSetValueToMinIfBelowMin() != null) {
            container.setFlagSetValueToMinIfBelowMin(BigInteger.valueOf(cs.getFlagSetValueToMinIfBelowMin().intValue()));
        }
        if (cs.getEmptyPublicText() != null) {
            container.setEmptyPublicText(cs.getEmptyPublicText());
        }

        if (cs.getFullPublicText() != null) {
            container.setFullPublicText(cs.getFullPublicText());
        }
        if (cs.getEventSendingDeviceId() != null) {
            container.setEventSendingDeviceID(cs.getEventSendingDeviceId());
            DeviceDAO deviceDao = new DeviceDAO();
            Device device = deviceDao.getDeviceById(cs.getEventSendingDeviceId(), session);
            if (device != null) {
                container.setEventSendingDeviceIDName(device.getDeviceName());
            }
        }

        containers.getContainer().add(container);
        tingcoContainers.setContainers(containers);
        return tingcoContainers;
    }

    public TingcoContainers buildGetContainers(TingcoContainers tingcoContainers, List<GroupTranslations> grouptransList, List<se.info24.pojo.Containers> containerList) {
        Containers containers = new Containers();
        List<Container> containerlist = new ArrayList<Container>();
        for (se.info24.pojo.Containers containers1 : containerList) {
            Container container = new Container();
            container.setContainerID(containers1.getContainerId());
            container.setContainerName(containers1.getContainerName());
            container.setContainerNumber(containers1.getContainerNumber());
            for (se.info24.pojo.GroupTranslations gt : grouptransList) {
                if (gt.getId().getGroupId().equalsIgnoreCase(containers1.getGroups().getGroupId())) {
                    GroupID id = new GroupID();
                    id.setGroupName(gt.getGroupName());
                    id.setValue(containers1.getGroups().getGroupId());
                    container.setGroupID(id);
                }
            }
            containerlist.add(container);
        }
        containers.getContainer().addAll(containerlist);
        tingcoContainers.setContainers(containers);
        return tingcoContainers;
    }

    public TingcoContainers buildGetDevicesForContainer(TingcoContainers tingcoContainers, List<Device> deviceList) {
        Containers containers = new Containers();
        Container container = new Container();
        se.info24.containersjaxb.ContainerDevices cd = null;
        for (Device device : deviceList) {
            cd = new se.info24.containersjaxb.ContainerDevices();
            cd.setDeviceID(device.getDeviceId());
            cd.setDeviceName(device.getDeviceName());
            if (device.getDeviceName2() != null) {
                cd.setDeviceNameTCMV3(device.getDeviceName() + " (" + device.getDeviceName2() + ")");
            } else {
                cd.setDeviceNameTCMV3(device.getDeviceName());
            }
            container.getContainerDevices().add(cd);
        }
        containers.getContainer().add(container);
        tingcoContainers.setContainers(containers);
        return tingcoContainers;
    }

    public TingcoContainers buildGetContainerScalingDetails(TingcoContainers tingcoContainers, ContainerDevices containerDevice, DeviceDataitemTranslations deviceDataitemTranslations, SensorTypes st) {
        Containers containers = new Containers();
        Container container = new Container();
        se.info24.containersjaxb.ContainerDevices cd = new se.info24.containersjaxb.ContainerDevices();
        cd.setDeviceDataItemID(containerDevice.getDeviceDataItemId());
        if (deviceDataitemTranslations != null) {
            cd.setDeviceDataItemName(deviceDataitemTranslations.getDataItemName());
        }

        cd.setFillDirection(containerDevice.getFillDirection());
        if (containerDevice.getRawMinValue() != null) {
            cd.setRawMinValue(containerDevice.getRawMinValue());
        }
        if (containerDevice.getRawMaxValue() != null) {
            cd.setRawMaxValue(containerDevice.getRawMaxValue());
        }
        if (containerDevice.getScaledMinValue() != null) {
            cd.setScaledMinValue(containerDevice.getScaledMinValue());
        }
        if (containerDevice.getScaledMaxValue() != null) {
            cd.setScaledMaxValue(containerDevice.getScaledMaxValue());
        }
        cd.setFlagScaleValue(containerDevice.getFlagScaleValue());
        if (containerDevice.getScalingOperand() != null) {
            cd.setScalingOperand(containerDevice.getScalingOperand());
        }
        if (containerDevice.getScalingFactor() != null) {
            cd.setScalingFactor(containerDevice.getScalingFactor());
        }
        cd.setIsEnabled(containerDevice.getIsEnabled());

        se.info24.containersjaxb.SensorTypes sensorTypes = new se.info24.containersjaxb.SensorTypes();
        sensorTypes.setSensorTypeID(containerDevice.getSensorTypeId());
        if (st != null) {
            sensorTypes.setSensorTypeName(st.getSensorTypeName());
        }
        cd.getSensorTypes().add(sensorTypes);

        container.getContainerDevices().add(cd);
        containers.getContainer().add(container);
        tingcoContainers.setContainers(containers);
        return tingcoContainers;
    }

    public TingcoContainers buildGetContainerInfo(TingcoContainers tingcoContainers, se.info24.pojo.Containers con, GroupTranslations gt, ProductVariantTranslations pvt, List<ObjectContacts> objectContactList, ObjectStateCodeTranslations osct, List<ObjectContactTypeTranslations> octt, String timeZoneGMToffset, List<ObjectCostCenters> costCenterses) throws DatatypeConfigurationException {
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuffer sb = new StringBuffer();
        Containers containers = new Containers();
        Container container = new Container();
        container.setContainerID(con.getContainerId());
        container.setContainerName(con.getContainerName());
        container.setContainerTypeID(con.getContainerTypes().getContainerTypeId());
        container.setContainerTypeName(con.getContainerTypes().getContainerTypeName());
        container.setContainerShapeID(con.getContainerShapes().getContainerShapeId());
        container.setContainerShapeName(con.getContainerShapes().getContainerShapeName());
        GregorianCalendar gc = new GregorianCalendar();
        if (con.getContainerNumber() != null) {
            container.setContainerNumber(con.getContainerNumber());
        }
        if (con.getAssetId() != null) {
            container.setAssetID(con.getAssetId());
        }
        GroupID id = new GroupID();
        id.setGroupName(gt.getGroupName());
        id.setValue(con.getGroups().getGroupId());
        container.setGroupID(id);
        if (con.getAgreements() != null) {
            container.setAgreementID(con.getAgreements().getAgreementId());
            container.setAgreementName(con.getAgreements().getAgreementName());
        }
        if (con.getCostCenters() != null) {
            if (costCenterses.get(0).getCostCenterId().equalsIgnoreCase(con.getCostCenters().getCostCenterId())) {
                container.setCostCenterID(con.getCostCenters().getCostCenterId());
                container.setCostCenterName(con.getCostCenters().getCostCenterName());
            }
        }

        if (con.getContainerDescription() != null) {
            container.setContainerDescription(con.getContainerDescription());
        }
        if (con.getSchedules() != null) {
            container.setOpenScheduleID(con.getSchedules().getScheduleId());
            container.setScheduleName(con.getSchedules().getScheduleName());
        }
        if (con.getIsOpen() != null) {
            container.setIsOpen(con.getIsOpen());
        }
        if (con.getObjectStateCodeId() != null) {
            if (osct != null) {
                container.setStateCodeName(osct.getStateCodeName());
            }
            container.setObjectStateCodeID(con.getObjectStateCodeId());
        }
        container.setIsEnabled(con.getIsEnabled());
        if (con.getIsMobileContainer() != null) {
            container.setIsMobileContainer(con.getIsMobileContainer());
        }
        if (con.getProductVariantUnit() != null) {
            container.setProductVariantUnit(con.getProductVariantUnit());
        }
        if (con.getProductVariantId() != null) {
            container.setProductVariantID(con.getProductVariantId());
        }
        if (pvt != null) {
            se.info24.containersjaxb.ProductVariantTranslations pvts = new se.info24.containersjaxb.ProductVariantTranslations();
            pvts.setProductVariantID(con.getProductVariantId());
            pvts.setProductVariantName(pvt.getProductVariantName());
            container.getProductVariantTranslations().add(pvts);

            if (pvt.getProductVariants() != null) {
                se.info24.containersjaxb.ProductVariants pv = new ProductVariants();
                pv.setProductVariantSKU(pvt.getProductVariants().getProductVariantSKU());
                container.getProductVariants().add(pv);
            }

        }
        if (con.getCurrentFillLevel() != null) {
            container.setCurrentFillLevel(con.getCurrentFillLevel());
        }
        if (con.getContainerMinLevel() != null) {
            container.setContainerMinLevel(con.getContainerMinLevel());
        }
        if (con.getContainerMaxLevel() != null) {
            container.setContainerMaxLevel(con.getContainerMaxLevel());
        }

        if (con.getContainerEmptyLevel() != null) {
            container.setContainerEmptyLevel(con.getContainerEmptyLevel());
        }
        if (con.getContainerFullLevel() != null) {
            container.setContainerFullLevel(con.getContainerFullLevel());
        }
        if (con.getContainerLength() != null) {
            container.setContainerLength(con.getContainerLength());
        }
        if (con.getContainerWidth() != null) {
            container.setContainerWidth(con.getContainerWidth());
        }
        if (con.getContainerHeight() != null) {
            container.setContainerHeight(con.getContainerHeight());
        }
        if (con.getCurrentFillLevelPercent() != null) {
            container.setCurrentFillLevelPercent(BigInteger.valueOf(con.getCurrentFillLevelPercent()));
        }
        if (con.getCurrentFreeLevel() != null) {
            container.setCurrentFreeLevel(con.getCurrentFreeLevel());
        }

        if (con.getCurrentFreeLevelPercent() != null) {
            container.setCurrentFreeLevelPercent(con.getCurrentFreeLevelPercent());
        }
        if (con.getSalesNumber() != null) {
            container.setSalesNumber(con.getSalesNumber());
        }
        if (con.getDepot() != null) {
            container.setDepot(con.getDepot());
        }
        if (con.getActiveFromDate() != null) {
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, con.getActiveFromDate()));
            container.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) + "");
            container.setActiveFromDateTCMV3(lFormat.format(gc.getTime()));
        }
        if (con.getActiveToDate() != null) {
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, con.getActiveToDate()));
            container.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) + "");
            container.setActiveToDateTCMV3(lFormat.format(gc.getTime()));
        }
        if (con.getUpdatedDate() != null) {
//            SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, con.getUpdatedDate()));
            container.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            container.setUpdatedDateTCMV3(lFormat.format(gc.getTime()));
        }
        if (con.getAddresses() != null) {
            Address add = new Address();
            if (con.getAddresses().getAddressStreet() != null) {
                add.setAddressStreet(con.getAddresses().getAddressStreet());
            }
            if (con.getAddresses().getAddressSuite() != null) {
                add.setAddressSuite(con.getAddresses().getAddressSuite());
            }
            if (con.getAddresses().getAddressZip() != null) {
                add.setAddressZip(con.getAddresses().getAddressZip());
            }
            if (con.getAddresses().getAddressCity() != null) {
                add.setAddressCity(con.getAddresses().getAddressCity());
            }
            add.setCountryID(con.getAddresses().getCountry().getCountryId());
            add.setCountryName(con.getAddresses().getCountry().getCountryName());
            container.getAddress().add(add);
        }

        for (ObjectContacts objectContacts : objectContactList) {
            se.info24.containersjaxb.ObjectContacts oc = new se.info24.containersjaxb.ObjectContacts();
            oc.setObjectContactID(objectContacts.getObjectContactId());
            if (objectContacts.getContactFirstName() != null) {
                oc.setContactFirstName(objectContacts.getContactFirstName());
                sb.append(objectContacts.getContactFirstName() + " ");
            }
            if (objectContacts.getContactLastName() != null) {
                oc.setContactLastName(objectContacts.getContactLastName());
                sb.append(objectContacts.getContactLastName() + " ");
            }
            if (objectContacts.getContactOrganizationName() != null) {
                oc.setContactOrganizationName(objectContacts.getContactOrganizationName());
            }
            if (objectContacts.getObjectContactTypes() != null) {
                oc.setObjectContactTypeID(objectContacts.getObjectContactTypes().getObjectContactTypeId());
                for (ObjectContactTypeTranslations objectContactTypeTranslations : octt) {
                    if (objectContactTypeTranslations.getId().getObjectContactTypeId().equalsIgnoreCase(objectContacts.getObjectContactTypes().getObjectContactTypeId())) {
                        ObjectContactTypes oct = new ObjectContactTypes();
                        se.info24.containersjaxb.ObjectContactTypeTranslations octtJax = new se.info24.containersjaxb.ObjectContactTypeTranslations();
                        octtJax.setObjectContactTypeName(objectContactTypeTranslations.getObjectContactTypeName());
                        oct.getObjectContactTypeTranslations().add(octtJax);
                        oc.setObjectContactTypes(oct);
                    }
                }
            }
            if (objectContacts.getContactMobilePhone() != null) {
                oc.setContactMobilePhone(objectContacts.getContactMobilePhone());
                sb.append("(" + objectContacts.getContactMobilePhone() + ")");
            }

            oc.setContactDisplayTCMV3(sb.toString());

            if (objectContacts.getContactEmail() != null) {
                oc.setContactEmail(objectContacts.getContactEmail());
            }
            container.getObjectContacts().add(oc);
        }

        containers.getContainer().add(container);
        tingcoContainers.setContainers(containers);

        return tingcoContainers;
    }

    public TingcoContainers buildGetNonLinkedDevicesForContainer(TingcoContainers tingcoContainers, List<se.info24.pojo.Device> devices, List<GroupTranslations> groupTranslationList) {
        Containers containers = new Containers();
        Container container = new Container();
        for (se.info24.pojo.Device device : devices) {
            se.info24.containersjaxb.ContainerDevices cd = new se.info24.containersjaxb.ContainerDevices();
            cd.setDeviceID(device.getDeviceId());
            cd.setDeviceName(device.getDeviceName());
            cd.setDeviceName2(device.getDeviceName2());
            for (GroupTranslations groupTranslations : groupTranslationList) {
                if (groupTranslations.getId().getGroupId().equalsIgnoreCase(device.getGroups().getGroupId())) {
                    GroupID id = new GroupID();
                    id.setGroupName(groupTranslations.getGroupName());
                    cd.setDeviceNameTCMV3(device.getDeviceName() + " (" + groupTranslations.getGroupName() + ") ");
                    id.setValue(groupTranslations.getId().getGroupId());
                    cd.setGroupID(id);
                    break;
                }
            }
            container.getContainerDevices().add(cd);
        }
        containers.getContainer().add(container);
        tingcoContainers.setContainers(containers);
        return tingcoContainers;
    }

    public TingcoContainers buildGetContainerDetails(TingcoContainers tingcoContainers, se.info24.pojo.Containers containerPojo, List<ObjectContacts> objectContactses,
            GroupTranslations gt, ProductVariantTranslations pvt, se.info24.pojo.ProductVariants pv, Session session,
            List<ContainerDevices> containerDevicesList, List<ContainerDevices> cd, int countryId) {
        Containers containers = new Containers();
        Container container = new Container();
        container.setContainerName(containerPojo.getContainerName());
        container.setContainerTypeName(containerPojo.getContainerTypes().getContainerTypeName());
        container.setContainerTypeID(containerPojo.getContainerTypes().getContainerTypeId());
        container.setContainerShapeName(containerPojo.getContainerShapes().getContainerShapeName());
        container.setContainerShapeID(containerPojo.getContainerShapes().getContainerShapeId());
        container.setContainerID(containerPojo.getContainerNumber());

        if (containerPojo.getProductVariantId() != null) {
            container.setProductVariantID(containerPojo.getProductVariantId());
        }
        if (containerPojo.getAssetId() != null) {
            container.setAssetID(containerPojo.getAssetId());
        }

        if (gt != null) {
            GroupID id = new GroupID();
            id.setValue(gt.getId().getGroupId());
            id.setGroupName(gt.getGroupName());
            container.setGroupID(id);
        }
        container.setAgreementName(containerPojo.getAgreements().getAgreementName());
        container.setAgreementID(containerPojo.getAgreements().getAgreementId());
        if (containerPojo.getContainerDescription() != null) {
            container.setContainerDescription(containerPojo.getContainerDescription());
        }
        if (pvt != null) {
            String pvId = pvt.getProductVariantName();
            if (pv.getProductVariantSKU() != null) {
                pvId = pvId + "(" + pv.getProductVariantSKU() + ")";
            }
            container.setProductVariantName(pvId);
        }
        if (containerPojo.getProductVariantUnit() != null) {
            container.setProductVariantUnit(containerPojo.getProductVariantUnit());
        }
        if (containerPojo.getContainerMinLevel() != null) {
            container.setContainerMinLevel(containerPojo.getContainerMinLevel());
        }
        if (containerPojo.getContainerMaxLevel() != null) {
            container.setContainerMaxLevel(containerPojo.getContainerMaxLevel());
        }
        if (containerPojo.getContainerEmptyLevel() != null) {
            container.setContainerEmptyLevel(containerPojo.getContainerEmptyLevel());
        }
        if (containerPojo.getContainerFullLevel() != null) {
            container.setContainerFullLevel(containerPojo.getContainerFullLevel());
        }
        if (containerPojo.getContainerLength() != null) {
            container.setContainerLength(containerPojo.getContainerLength());
        }
        if (containerPojo.getContainerWidth() != null) {
            container.setContainerWidth(containerPojo.getContainerWidth());
        }
        if (containerPojo.getContainerHeight() != null) {
            container.setContainerHeight(containerPojo.getContainerHeight());
        }
        if (containerPojo.getSalesNumber() != null) {
            container.setSalesNumber(containerPojo.getSalesNumber());
        }
        if (containerPojo.getDepot() != null) {
            container.setDepot(containerPojo.getDepot());
        }
        if (containerPojo.getWarningLowValue() != null) {
            container.setWarningLowValue(containerPojo.getWarningLowValue());
        }
        if (containerPojo.getWarningHighValue() != null) {
            container.setWarningHighValue(containerPojo.getWarningHighValue());
        }
        if (containerPojo.getWarningHighEventTypeId() != null) {
            container.setWarningHighEventTypeID(containerPojo.getWarningHighEventTypeId());
        }
        if (containerPojo.getWarningLowEventTypeId() != null) {
            container.setWarningLowEventTypeID(containerPojo.getWarningLowEventTypeId());
        }
        if (containerPojo.getAlarmLowValue() != null) {
            container.setAlarmLowValue(containerPojo.getAlarmLowValue());
        }
        if (containerPojo.getAlarmHighValue() != null) {
            container.setAlarmHighValue(containerPojo.getAlarmHighValue());
        }

        if (containerPojo.getAlarmLowEventTypeId() != null) {
            container.setAlarmLowEventTypeID(containerPojo.getAlarmLowEventTypeId());
        }
        if (containerPojo.getAlarmHighEventTypeId() != null) {
            container.setAlarmHighEventTypeID(containerPojo.getAlarmHighEventTypeId());
        }
        if (containerPojo.getOrderMoreBelowThisValue() != null) {
            container.setOrderMoreBelowThisValue(containerPojo.getOrderMoreBelowThisValue());
        }
        if (containerPojo.getOrderMoreEventTypeId() != null) {
            container.setOrderMoreEventTypeID(containerPojo.getOrderMoreEventTypeId());
        }

        if (!containerDevicesList.isEmpty()) {
            container.setDeviceID(containerDevicesList.get(0).getId().getDeviceId());
        }

        if (containerPojo.getAddresses() != null) {
            Address add = new Address();

            add.setAddressID(containerPojo.getAddresses().getAddressId());
            if (containerPojo.getAddresses().getAddressStreet() != null) {
                add.setAddressStreet(containerPojo.getAddresses().getAddressStreet());
            }
            if (containerPojo.getAddresses().getAddressSuite() != null) {
                add.setAddressSuite(containerPojo.getAddresses().getAddressSuite());
            }
            if (containerPojo.getAddresses().getAddressZip() != null) {
                add.setAddressZip(containerPojo.getAddresses().getAddressZip());
            }
            if (containerPojo.getAddresses().getAddressCity() != null) {
                add.setAddressCity(containerPojo.getAddresses().getAddressCity());
            }
            add.setCountryID(containerPojo.getAddresses().getCountry().getCountryId());
            add.setCountryName(containerPojo.getAddresses().getCountry().getCountryName());
            container.getAddress().add(add);
        }
        se.info24.containersjaxb.ObjectContacts ocjax = null;
        for (ObjectContacts oc : objectContactses) {
            ocjax = new se.info24.containersjaxb.ObjectContacts();
            if (oc.getContactFirstName() != null) {
                ocjax.setContactFirstName(oc.getContactFirstName());
            }
            if (oc.getContactLastName() != null) {
                ocjax.setContactLastName(oc.getContactLastName());
            }
            if (oc.getContactOrganizationName() != null) {
                ocjax.setContactOrganizationName(oc.getContactOrganizationName());
            }
            if (oc.getContactMobilePhone() != null) {
                ocjax.setContactMobilePhone(oc.getContactMobilePhone());
            }
            ocjax.setObjectContactID(oc.getObjectContactId());
            container.getObjectContacts().add(ocjax);

        }
        if (!containerDevicesList.isEmpty()) {
            List<ObjectTypeFields> objectTypeFieldList = deviceDAO.getObjectTypeFieldsByObjectID(session, cd.get(0).getDevice().getDeviceTypes().getDeviceTypeId());
            List<String> fieldIds = new ArrayList<String>();
            for (ObjectTypeFields otf : objectTypeFieldList) {
                se.info24.pojo.Fields fields = otf.getFields();
                fieldIds.add(fields.getFieldId());
            }
            List<FieldTranslations> fieldTransList = new ArrayList();
            if (!fieldIds.isEmpty()) {
                fieldTransList = deviceDAO.getFieldTranslationsByIdList(session, fieldIds, String.valueOf(countryId));
            }
            if (!fieldTransList.isEmpty()) {
                for (FieldTranslations ft : fieldTransList) {
                    se.info24.containersjaxb.ContainerDevices cdj = new se.info24.containersjaxb.ContainerDevices();
                    se.info24.containersjaxb.Fields fieldssJaxb = new se.info24.containersjaxb.Fields();
                    se.info24.containersjaxb.Field fieldsJaxb = new se.info24.containersjaxb.Field();
                    se.info24.containersjaxb.FieldTranslations fieldsTransJaxb = new se.info24.containersjaxb.FieldTranslations();
                    se.info24.containersjaxb.FieldTypes fieldsTypesJaxb = new se.info24.containersjaxb.FieldTypes();

                    se.info24.pojo.Fields field = ft.getFields();
                    se.info24.pojo.ObjectFieldData objectFieldData = deviceDAO.getObjectFieldDataByID(session, containerDevicesList.get(0).getDevice().getDeviceId(), field.getFieldId());

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
                                se.info24.containersjaxb.ListValues listValueJaxb = new se.info24.containersjaxb.ListValues();
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
                    fieldssJaxb.getField().add(fieldsJaxb);
                    cdj.getFields().add(fieldssJaxb);
                    container.getContainerDevices().add(cdj);
                }
            }
        }
        containers.getContainer().add(container);
        tingcoContainers.setContainers(containers);
        return tingcoContainers;
    }

    public TingcoContainers buildVisitorsList(TingcoContainers tingcoContainers, List<Object> visitorsList, String timeZoneGMToffset, List<UserAlias> userAliases) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        GregorianCalendar gc = new GregorianCalendar();
        Containers containers = new Containers();
        Container container = new Container();
        boolean flag = false;
        for (UserAlias userAlias : userAliases) {
            flag = false;
            for (Iterator itr = visitorsList.iterator(); itr.hasNext();) {
                Object[] row = (Object[]) itr.next();
                for (int i = 0; i < row.length; i++) {
                    if (i % 2 == 0) {
                        if (row[i] != null) {
                            if (row[i].toString().equalsIgnoreCase(userAlias.getUserAliasId())) {
                                ContainerUserAlias containerUserAlias = new ContainerUserAlias();
                                flag = true;
                                if (row[i] != null) {
                                    if (flag) {
                                        containerUserAlias.setUserAliasID(row[i].toString());
                                    }
                                }

                                if (row[i + 1] != null) {
                                    if (flag) {
                                        containerUserAlias.setUserAliasFirstName(row[i + 1].toString());
                                    }
                                }
                                if (row[i + 2] != null) {
                                    if (flag) {
                                        containerUserAlias.setUserAliasLastName(row[i + 2].toString());

                                    }
                                }
                                if (row[i + 3] != null) {
                                    if (flag) {
                                        containerUserAlias.setUserAliasUserMobilePhone(row[i + 3].toString());

                                    }
                                }
                                if (row[i + 4] != null) {
                                    if (flag) {
                                        containerUserAlias.setUserAlias(row[i + 4].toString());
                                    }
                                }
                                if (row[i + 5] != null) {
                                    if (flag) {
                                        containerUserAlias.setContainerName(row[i + 5].toString());

                                    }
                                }
                                if (row[i + 6] != null) {
                                    if (flag) {
                                        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, dateFormat.parse(row[i + 6].toString())));
                                        containerUserAlias.setCheckInDate(dateFormat.format(gc.getTime()));
                                    }

                                }
                                if (row[i + 7] != null) {
                                    if (flag) {
                                        containerUserAlias.setContainerID(row[i + 7].toString());
                                    }
                                }

                                if (row[i + 8] != null) {
                                    if (flag) {
                                        containerUserAlias.setCheckInDeviceID(row[i + 8].toString());
                                    }
                                }
                                container.getContainerUserAlias().add(containerUserAlias);
                            }
                        }
                        i += 8;
                    }

                }
            }
        }

        containers.getContainer().add(container);
        tingcoContainers.setContainers(containers);

        return tingcoContainers;
    }

    public TingcoContainers builtGetDatatItemsForContainer(TingcoContainers tingcoContainers, List<DeviceDataitemTranslations> DeviceDataitemTranslations, List<ContainerDevices> containerDeviceses) {
        Containers containers = new Containers();
        Container container = new Container();

        se.info24.containersjaxb.ContainerDevices cdJax = null;
        for (ContainerDevices containerDevices : containerDeviceses) {
            cdJax = new se.info24.containersjaxb.ContainerDevices();
            for (DeviceDataitemTranslations ddit : DeviceDataitemTranslations) {
                if (ddit.getId().getDeviceDataItemId().equalsIgnoreCase(containerDevices.getDeviceDataItemId())) {
                    cdJax.setDeviceDataItemName(ddit.getDataItemName());
                    cdJax.setDeviceDataItemID(ddit.getId().getDeviceDataItemId());
                    if (containerDevices.getRawMinValue() != null) {
                        cdJax.setRawMinValue(containerDevices.getRawMinValue());
                    }
                    if (containerDevices.getRawMaxValue() != null) {
                        cdJax.setRawMaxValue(containerDevices.getRawMaxValue());
                    }
                    if (containerDevices.getScaledMinValue() != null) {
                        cdJax.setScaledMinValue(containerDevices.getScaledMinValue());
                    }
                    if (containerDevices.getScaledMaxValue() != null) {
                        cdJax.setScaledMaxValue(containerDevices.getScaledMaxValue());
                    }
                    cdJax.setFlagScaleValue(0);
                    if (containerDevices.getScalingOperand() != null) {
                        cdJax.setScalingOperand(containerDevices.getScalingOperand());
                    }
                    if (containerDevices.getScalingFactor() != null) {
                        cdJax.setScalingFactor(containerDevices.getScalingFactor());
                    }
                    cdJax.setDeviceID(containerDevices.getDevice().getDeviceId());
                    if (containerDevices.getSensorTypeId() != null) {
                        cdJax.setSensorTypeID(containerDevices.getSensorTypeId());
                    }
                    container.getContainerDevices().add(cdJax);
                }
            }
        }
        containers.getContainer().add(container);
        tingcoContainers.setContainers(containers);
        return tingcoContainers;
    }

    public TingcoContainers buildGetContainersByProductVariantID(TingcoContainers tingcoContainers, List<se.info24.pojo.Containers> containersList) {
        if (containersList.isEmpty()) {
            tingcoContainers.getMsgStatus().setResponseCode(-1);
            tingcoContainers.getMsgStatus().setResponseText("No Data Found");
            return tingcoContainers;
        }
        Containers containers = new Containers();
        for (se.info24.pojo.Containers c : containersList) {
            Container containerJaxb = new Container();
            containerJaxb.setContainerID(c.getContainerId());
            containerJaxb.setContainerName(c.getContainerName());
            if (c.getCurrentFillLevel() != null) {
                containerJaxb.setCurrentFillLevel(c.getCurrentFillLevel());
            }
            if (c.getProductVariantUnit() != null) {
                containerJaxb.setProductVariantUnit(c.getProductVariantUnit());
            }
            containers.getContainer().add(containerJaxb);
        }
        tingcoContainers.setContainers(containers);
        return tingcoContainers;
    }
}

