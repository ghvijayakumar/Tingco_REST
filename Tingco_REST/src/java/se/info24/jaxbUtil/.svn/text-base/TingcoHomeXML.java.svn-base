/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.jaxbUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Session;
import se.info24.containers.ContainerDAO;
import se.info24.device.DeviceDAO;
import se.info24.devicejaxb.TingcoDevice;
import se.info24.group.GroupDAO;
import se.info24.home.HomeDAO;
import se.info24.homejaxb.AgreementID;
import se.info24.homejaxb.Building;
import se.info24.homejaxb.BuildingParentID;
import se.info24.homejaxb.BuildingTypes;
import se.info24.homejaxb.ContainerID;
import se.info24.homejaxb.CountryID;
import se.info24.homejaxb.DeviceID;
import se.info24.homejaxb.EnergyMeterDeviceID;
import se.info24.homejaxb.GasMeterDeviceID;
import se.info24.homejaxb.GroupID;
import se.info24.homejaxb.HeatMeterDeviceID;
import se.info24.homejaxb.MsgStatus;
import se.info24.homejaxb.ObjectFactory;
import se.info24.homejaxb.OwnerGroupID;
import se.info24.homejaxb.Room;
import se.info24.homejaxb.TingcoHome;
import se.info24.homejaxb.UsedByGroupID;
import se.info24.homejaxb.WaterMeterDeviceID;
import se.info24.homejaxb.Zone;
import se.info24.objectpojo.ServiceStatusDetails;
import se.info24.pojo.BuildingDevices;
import se.info24.pojo.BuildingTypeTranslations;
import se.info24.pojo.Buildings;
import se.info24.pojo.Containers;
import se.info24.pojo.Device;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTranslationsId;
import se.info24.pojo.RoomTypeTranslations;
import se.info24.pojo.Rooms;
import se.info24.pojo.ZoneRooms;
import se.info24.pojo.Zones;

/**
 *
 * @author Sumit
 */
public class TingcoHomeXML {

    public TingcoHome buildAllBuildingTypeTranslations(TingcoHome tingcoHome, List<BuildingTypeTranslations> btTransList) {
        se.info24.homejaxb.Buildings buildings = new se.info24.homejaxb.Buildings();
        Building building = new Building();
        for (BuildingTypeTranslations btt : btTransList) {
            BuildingTypes buildingTypes = new BuildingTypes();
            buildingTypes.setIconURL(btt.getBuildingTypes().getIconUrl());
            se.info24.homejaxb.BuildingTypeTranslations btTrans = new se.info24.homejaxb.BuildingTypeTranslations();
            btTrans.setBuildingTypeID(btt.getId().getBuildingTypeId());
            btTrans.setBuildingTypeName(btt.getBuildingTypeName());
            buildingTypes.getBuildingTypeTranslations().add(btTrans);
            building.getBuildingTypes().add(buildingTypes);
        }
        buildings.getBuilding().add(building);
        tingcoHome.setBuildings(buildings);
        return tingcoHome;
    }

    public TingcoHome buildBuildingDevicesDetails(TingcoHome tingcoHome, List<ServiceStatusDetails> buildingDevicesList) {
        se.info24.homejaxb.Buildings buildings = new se.info24.homejaxb.Buildings();
        for (ServiceStatusDetails ssd : buildingDevicesList) {
            Building building = new Building();
            DeviceID deviceID = new DeviceID();
            deviceID.setValue(ssd.getDeviceId());
            deviceID.setDeviceName(ssd.getDeviceName());
            building.setDeviceID(deviceID);
            OwnerGroupID ownerGroupID = new OwnerGroupID();
            ownerGroupID.setValue(ssd.getGroupId());
            ownerGroupID.setOwnerGroupName(ssd.getGroupName());
            building.setOwnerGroupID(ownerGroupID);
            Room room = new Room();
            room.setRoomID(ssd.getRoomId());
            room.setRoomName(ssd.getRoomName());
            building.getRoom().add(room);
            buildings.getBuilding().add(building);
        }
        tingcoHome.setBuildings(buildings);
        return tingcoHome;
    }

    public TingcoHome buildBuildingsDetails(TingcoHome tingcoHome, List<Buildings> buildingsList, List<GroupTranslations> groupTransList) {
        se.info24.homejaxb.Buildings buildingsJaxb = new se.info24.homejaxb.Buildings();
        for (Buildings b : buildingsList) {
            for (GroupTranslations gt : groupTransList) {
                if (b.getOwnerGroupId().equalsIgnoreCase(gt.getId().getGroupId())) {
                    Building buildingJaxb = new Building();
                    buildingJaxb.setBuildingID(b.getBuildingId());
                    buildingJaxb.setBuildingName(b.getBuildingName() + " (" + gt.getGroupName() + ") ");
                    OwnerGroupID ownerGroupId = new OwnerGroupID();
                    ownerGroupId.setValue(gt.getId().getGroupId());
                    ownerGroupId.setOwnerGroupName(gt.getGroupName());
                    buildingJaxb.setOwnerGroupID(ownerGroupId);
                    buildingsJaxb.getBuilding().add(buildingJaxb);
                }
            }
        }
        tingcoHome.setBuildings(buildingsJaxb);
        return tingcoHome;
    }

    public TingcoHome buildRoomDetails(TingcoHome tingcoHome, Rooms rooms, int countryId, Session session) {
        se.info24.homejaxb.Buildings buildings = new se.info24.homejaxb.Buildings();
        Building building = new Building();
        Room room = new Room();
        room.setRoomName(rooms.getRoomName());
        if (rooms.getRoomDescription() != null) {
            room.setRoomDescription(rooms.getRoomDescription());
        }
        if (rooms.getRoomNumber() != null) {
            room.setRoomNumber(rooms.getRoomNumber());
        }
        se.info24.homejaxb.RoomTypes roomTypes = new se.info24.homejaxb.RoomTypes();
        se.info24.homejaxb.RoomTypeTranslations roomTypeTranslations = new se.info24.homejaxb.RoomTypeTranslations();
        roomTypeTranslations.setRoomTypeID(rooms.getRoomTypes().getRoomTypeId());
        Set<se.info24.pojo.RoomTypeTranslations> rttList = rooms.getRoomTypes().getRoomTypeTranslationses();
        for (se.info24.pojo.RoomTypeTranslations rtt : rttList) {
            if (rtt.getId().getCountryId() == countryId) {
                roomTypeTranslations.setRoomTypeName(rtt.getRoomTypeName());
                break;
            }
        }

        roomTypes.getRoomTypeTranslations().add(roomTypeTranslations);
        room.getRoomTypes().add(roomTypes);

        building.setBuildingID(rooms.getBuildings().getBuildingId());
        building.setBuildingName(rooms.getBuildings().getBuildingName());

        Object gtObject = session.get(GroupTranslations.class, new GroupTranslationsId(rooms.getOwnerGroupId(), countryId));
        if (gtObject != null) {
            GroupTranslations gt = (GroupTranslations) gtObject;
            OwnerGroupID ownerGroupId = new OwnerGroupID();
            ownerGroupId.setValue(rooms.getOwnerGroupId());
            ownerGroupId.setOwnerGroupName(gt.getGroupName());
            room.setOwnerGroupID(ownerGroupId);
        }

        if (rooms.getUsedByGroupId() != null) {
            gtObject = session.get(GroupTranslations.class, new GroupTranslationsId(rooms.getUsedByGroupId(), countryId));
            if (gtObject != null) {
                GroupTranslations gt = (GroupTranslations) gtObject;
                UsedByGroupID usedByGroupId = new UsedByGroupID();
                usedByGroupId.setValue(rooms.getUsedByGroupId());
                usedByGroupId.setUsedByGroupName(gt.getGroupName());
                room.setUsedByGroupID(usedByGroupId);
            }
        }

        AgreementID agreementId = new AgreementID();
        agreementId.setAgreementName(rooms.getAgreements().getAgreementName());
        agreementId.setValue(rooms.getAgreements().getAgreementId());
        room.setAgreementID(agreementId);

        if (rooms.getFloorLevel() != null) {
            room.setFloorLevel(rooms.getFloorLevel());
        }

        if (rooms.getArea() != null) {
            room.setArea(rooms.getArea());
        }

        if (rooms.getAreaUnit() != null) {
            room.setAreaUnit(rooms.getAreaUnit());
        }

        if (rooms.getVolume() != null) {
            room.setVolume(rooms.getVolume());
        }

        if (rooms.getVolumeUnit() != null) {
            room.setVolumeUnit(rooms.getVolumeUnit());
        }

        if (rooms.getRating() != null) {
            room.setRating(new BigInteger(String.valueOf(rooms.getRating())));
        }

        if (rooms.getIsNonSmoking() != null) {
            room.setIsNonSmoking(rooms.getIsNonSmoking());
        }

        if (rooms.getIsPublicAccess() != null) {
            room.setIsPublicAccess(rooms.getIsPublicAccess());
        }

        if (rooms.getIsDoNotDisturb() != null) {
            room.setIsDoNotDisturb(rooms.getIsDoNotDisturb());
        }

        if (rooms.getLayoutImageUrl() != null) {
            room.setLayoutImageURL(rooms.getLayoutImageUrl());
        }

        if (rooms.getContainerId() != null) {
            Object containerObject = session.get(Containers.class, rooms.getContainerId());
            if (containerObject != null) {
                ContainerID containerId = new ContainerID();
                containerId.setValue(rooms.getContainerId());
                Containers containers = (Containers) containerObject;
                containerId.setContainerName(containers.getContainerName());
                room.setContainerID(containerId);
            }
        }

        Set<ZoneRooms> zoneRoomsSet = rooms.getZoneRoomses();
        if (!zoneRoomsSet.isEmpty()) {
            for (ZoneRooms zr : zoneRoomsSet) {
                Zone zone = new Zone();
                zone.setZoneID(zr.getZones().getZoneId());
                zone.setZoneName(zr.getZones().getZoneName());
                room.getZone().add(zone);
            }
        }

        building.getRoom().add(room);
        buildings.getBuilding().add(building);
        tingcoHome.setBuildings(buildings);
        return tingcoHome;
    }

    public TingcoHome buildRoomsByBuildingId(TingcoHome tingcoHome, List<Rooms> roomsList) {
        se.info24.homejaxb.Buildings buildings = new se.info24.homejaxb.Buildings();
        Building building = new Building();
        for (Rooms rooms : roomsList) {
            Room room = new Room();
            room.setRoomID(rooms.getRoomId());
            room.setRoomName(rooms.getRoomName());
            building.getRoom().add(room);
        }
        buildings.getBuilding().add(building);
        tingcoHome.setBuildings(buildings);
        return tingcoHome;
    }

    public TingcoHome buildRoomsDetails(TingcoHome tingcoHome, List<Rooms> roomsList) {
        se.info24.homejaxb.Buildings buildingsJaxb = new se.info24.homejaxb.Buildings();
        Building building = new Building();
        for (Rooms r : roomsList) {
            Room roomJaxb = new Room();
            roomJaxb.setRoomID(r.getRoomId());
            roomJaxb.setRoomName(r.getRoomName() + " (" + r.getBuildings().getBuildingName() + ") ");
            building.getRoom().add(roomJaxb);
        }
        buildingsJaxb.getBuilding().add(building);
        tingcoHome.setBuildings(buildingsJaxb);
        return tingcoHome;
    }

    public TingcoHome buildTingcoHomeTemplate() throws DatatypeConfigurationException {
        ObjectFactory objectFactory = new ObjectFactory();
        TingcoHome homeXML = objectFactory.createTingcoHome();
        homeXML.setCreateRef("Info24");
        homeXML.setMsgVer(new BigDecimal("1.0"));
        homeXML.setRegionalUnits("Metric");
        homeXML.setTimeZone("UTC");

        homeXML.setMsgID(UUID.randomUUID().toString());
        homeXML.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        MsgStatus msgStatus = new MsgStatus();
        msgStatus.setResponseCode(0);
        msgStatus.setResponseText("OK");

        homeXML.setMsgStatus(msgStatus);

        return homeXML;
    }

    public TingcoHome buildGetBuildingByID(TingcoHome tingcoHome, Buildings buildings, int countryId, Session session) {
        se.info24.homejaxb.Buildings buildingsJaxb = new se.info24.homejaxb.Buildings();

        Building buildingJaxb = new Building();
        HomeDAO homeDAO = new HomeDAO();
        buildingJaxb.setBuildingID(buildings.getBuildingId());
        if (buildings.getBuildingParentId() != null) {
            BuildingParentID buildingParentID = new BuildingParentID();
            buildingParentID.setValue(buildings.getBuildingParentId());

            Object object = homeDAO.getBuildingsById(buildings.getBuildingParentId(), session);
            if (object != null) {
                Buildings parentBuildings = (Buildings) object;
                buildingParentID.setBuildingParentName(parentBuildings.getBuildingName());
            }
            buildingJaxb.setBuildingParentID(buildingParentID);
        }
        buildingJaxb.setBuildingName(buildings.getBuildingName());
        if (buildings.getBuildingDescription() != null) {
            buildingJaxb.setBuildingDescription(buildings.getBuildingDescription());
        }
        if (buildings.getBuildingTypes() != null) {
            BuildingTypeTranslations btt = homeDAO.getBuildingTypeTranslationsById(buildings.getBuildingTypes().getBuildingTypeId(), countryId, session);
            if (btt != null) {
                BuildingTypes buildingTypeJaxb = new BuildingTypes();
                se.info24.homejaxb.BuildingTypeTranslations bttJaxb = new se.info24.homejaxb.BuildingTypeTranslations();
                bttJaxb.setBuildingTypeID(btt.getId().getBuildingTypeId());
                bttJaxb.setBuildingTypeName(btt.getBuildingTypeName());
                buildingTypeJaxb.getBuildingTypeTranslations().add(bttJaxb);
                buildingJaxb.getBuildingTypes().add(buildingTypeJaxb);
            }
        }

        GroupDAO groupDAO = new GroupDAO();
        OwnerGroupID ownerGroupID = new OwnerGroupID();
        ownerGroupID.setValue(buildings.getOwnerGroupId());
        GroupTranslations gt = groupDAO.getGroupTranslationsByIds(buildings.getOwnerGroupId(), countryId, session);
        ownerGroupID.setOwnerGroupName(gt.getGroupName());
        buildingJaxb.setOwnerGroupID(ownerGroupID);

        if (buildings.getUsedByGroupId() != null) {
            UsedByGroupID usedByGroupID = new UsedByGroupID();
            GroupTranslations usedByGroup = groupDAO.getGroupTranslationsByIds(buildings.getUsedByGroupId(), countryId, session);
            usedByGroupID.setValue(usedByGroup.getId().getGroupId());
            usedByGroupID.setUsedByGroupName(usedByGroup.getGroupName());
            buildingJaxb.setUsedByGroupID(usedByGroupID);
        }

        AgreementID agreementID = new AgreementID();
        agreementID.setValue(buildings.getAgreements().getAgreementId());
        agreementID.setAgreementName(buildings.getAgreements().getAgreementName());
        buildingJaxb.setAgreementID(agreementID);

        if (buildings.getContainerId() != null) {
            ContainerDAO containerDAO = new ContainerDAO();
            Containers containers = containerDAO.getContainersByContainerId(buildings.getContainerId(), session);
            if (containers != null) {
                ContainerID containerID = new ContainerID();
                containerID.setValue(containers.getContainerId());
                containerID.setContainerName(containers.getContainerName());
                buildingJaxb.setContainerID(containerID);
            }
        }

        if (buildings.getIsPetsAllowed() != null) {
            buildingJaxb.setIsPetsAllowed(buildings.getIsPetsAllowed() + "");
        }
        if (buildings.getUserCanChangeClimatePricePref() != null) {
            buildingJaxb.setUserCanChangeClimatePricePref(buildings.getUserCanChangeClimatePricePref() + "");
        }


        if (buildings.getPropertyCode() != null) {
            buildingJaxb.setPropertyCode(buildings.getPropertyCode());
        }
        if (buildings.getBuildingNumber() != null) {
            buildingJaxb.setBuildingNumber(buildings.getBuildingNumber());
        }
        if (buildings.getBuildingCode() != null) {
            buildingJaxb.setBuildingCode(buildings.getBuildingCode());
        }
        if (buildings.getShareOfParent() != null) {
            buildingJaxb.setShareOfParent(buildings.getShareOfParent());
        }
        if (buildings.getFloorLevel() != null) {
            buildingJaxb.setFloorLevel(buildings.getFloorLevel());
        }
        if (buildings.getArea() != null) {
            buildingJaxb.setArea(buildings.getArea());
        }
        if (buildings.getAreaUnit() != null) {
            buildingJaxb.setAreaUnit(buildings.getAreaUnit());
        }
        if (buildings.getVolume() != null) {
            buildingJaxb.setVolume(buildings.getVolume());
        }
        if (buildings.getVolumeUnit() != null) {
            buildingJaxb.setVolumeUnit(buildings.getVolumeUnit());
        }
        if (buildings.getRating() != null) {
            buildingJaxb.setRating(buildings.getRating());
        }
        if (buildings.getIsInEmergencyMode() != null) {
            buildingJaxb.setIsInEmergencyMode(buildings.getIsInEmergencyMode());
        }
        if (buildings.getIsNonSmoking() != null) {
            buildingJaxb.setIsNonSmoking(buildings.getIsNonSmoking());
        }
        if (buildings.getIsOpen() != null) {
            buildingJaxb.setIsOpen(buildings.getIsOpen());
        }
        if (buildings.getIsPublicAccess() != null) {
            buildingJaxb.setIsPublicAccess(buildings.getIsPublicAccess());
        }
        if (buildings.getStreet1() != null) {
            buildingJaxb.setStreet1(buildings.getStreet1());
        }
        if (buildings.getStreet2() != null) {
            buildingJaxb.setStreet2(buildings.getStreet2());
        }
        if (buildings.getZipCode() != null) {
            buildingJaxb.setZipCode(buildings.getZipCode());
        }
        if (buildings.getCity() != null) {
            buildingJaxb.setCity(buildings.getCity());
        }
        if (buildings.getState() != null) {
            buildingJaxb.setState(buildings.getState());
        }
        if (buildings.getRegion() != null) {
            buildingJaxb.setRegion(buildings.getRegion());
        }
        if (buildings.getCountry() != null) {
            CountryID countryID = new CountryID();
            countryID.setValue(String.valueOf(buildings.getCountry().getCountryId()));
            countryID.setCountryName(buildings.getCountry().getCountryName());
            buildingJaxb.setCountryID(countryID);
        }
        if (buildings.getLatitude() != null) {
            buildingJaxb.setLatitude(buildings.getLatitude());
        }
        if (buildings.getLongitude() != null) {
            buildingJaxb.setLongitude(buildings.getLongitude());
        }
        if (buildings.getAltitude() != null) {
            buildingJaxb.setAltitude(buildings.getAltitude());
        }
        if (buildings.getLayoutImageUrl() != null) {
            buildingJaxb.setLayoutImageURL(buildings.getLayoutImageUrl());
        }
        if (buildings.getWebsiteUrl() != null) {
            buildingJaxb.setWebsiteURL(buildings.getWebsiteUrl());
        }
        if (buildings.getClimatePricePreference() != null) {
            buildingJaxb.setClimatePricePreference(buildings.getClimatePricePreference());
        }
        DeviceDAO deviceDAO = new DeviceDAO();
        Object deviceObject = null;
        Device device = null;
        if (buildings.getEnergyMeterDeviceId() != null) {
            deviceObject = deviceDAO.getDeviceByDeviceId(buildings.getEnergyMeterDeviceId(), session);
            if (deviceObject != null) {
                device = (Device) deviceObject;
                EnergyMeterDeviceID energyMeterDeviceID = new EnergyMeterDeviceID();
                energyMeterDeviceID.setValue(device.getDeviceId());
                energyMeterDeviceID.setEnergyMeterDeviceName(device.getDeviceName());
                buildingJaxb.setEnergyMeterDeviceID(energyMeterDeviceID);
            }
        }

        if (buildings.getWaterMeterDeviceId() != null) {
            deviceObject = deviceDAO.getDeviceByDeviceId(buildings.getWaterMeterDeviceId(), session);
            if (deviceObject != null) {
                device = (Device) deviceObject;
                WaterMeterDeviceID waterMeterDeviceID = new WaterMeterDeviceID();
                waterMeterDeviceID.setValue(device.getDeviceId());
                waterMeterDeviceID.setWaterMeterDeviceName(device.getDeviceName());
                buildingJaxb.setWaterMeterDeviceID(waterMeterDeviceID);
            }
        }
        if (buildings.getHeatMeterDeviceId() != null) {
            deviceObject = deviceDAO.getDeviceByDeviceId(buildings.getHeatMeterDeviceId(), session);
            if (deviceObject != null) {
                device = (Device) deviceObject;
                HeatMeterDeviceID heatMeterDeviceID = new HeatMeterDeviceID();
                heatMeterDeviceID.setValue(device.getDeviceId());
                heatMeterDeviceID.setHeatMeterDeviceName(device.getDeviceName());
                buildingJaxb.setHeatMeterDeviceID(heatMeterDeviceID);
            }
        }
        if (buildings.getGasMeterDeviceId() != null) {
            deviceObject = deviceDAO.getDeviceByDeviceId(buildings.getGasMeterDeviceId(), session);
            if (deviceObject != null) {
                device = (Device) deviceObject;
                GasMeterDeviceID gasMeterDeviceID = new GasMeterDeviceID();
                gasMeterDeviceID.setValue(device.getDeviceId());
                gasMeterDeviceID.setGasMeterDeviceName(device.getDeviceName());
                buildingJaxb.setGasMeterDeviceID(gasMeterDeviceID);
            }
        }
        buildingsJaxb.getBuilding().add(buildingJaxb);
        tingcoHome.setBuildings(buildingsJaxb);
        return tingcoHome;
    }

    public TingcoHome buildGetAllRoomTypes(TingcoHome tingcoHome, List<RoomTypeTranslations> roomTypeTranslationses) {
        se.info24.homejaxb.Buildings buildingsJaxb = new se.info24.homejaxb.Buildings();
        Building buildingJaxb = new Building();
        int seqNo = 1;
        for (RoomTypeTranslations rtt : roomTypeTranslationses) {
            se.info24.homejaxb.Room roomJaxb = new Room();
            se.info24.homejaxb.RoomTypes roomTypesJaxb = new se.info24.homejaxb.RoomTypes();
            se.info24.homejaxb.RoomTypeTranslations roomTypeTranslationsJaxb = new se.info24.homejaxb.RoomTypeTranslations();

            roomTypesJaxb.setRoomTypeID(rtt.getId().getRoomTypeId());
            if (rtt.getRoomTypes().getIconUrl() != null) {
                roomTypesJaxb.setIconURL(rtt.getRoomTypes().getIconUrl());
            }
            roomTypeTranslationsJaxb.setRoomTypeName(rtt.getRoomTypeName());
            roomJaxb.setSeqNo(seqNo++);
            roomTypesJaxb.getRoomTypeTranslations().add(roomTypeTranslationsJaxb);
            roomJaxb.getRoomTypes().add(roomTypesJaxb);
            buildingJaxb.getRoom().add(roomJaxb);
        }
        buildingsJaxb.getBuilding().add(buildingJaxb);
        tingcoHome.setBuildings(buildingsJaxb);
        return tingcoHome;
    }

    public TingcoHome buildZonesDetails(TingcoHome tingcoHome, List<Zones> zonesList) {
        se.info24.homejaxb.Zones zones = new se.info24.homejaxb.Zones();
        for (Zones z : zonesList) {
            Zone zone = new Zone();
            zone.setZoneID(z.getZoneId());
            zone.setZoneName(z.getZoneName());
            zones.getZone().add(zone);
        }
        tingcoHome.setZones(zones);
        return tingcoHome;
    }

    public TingcoHome buildGetZoneDetailsByID(TingcoHome tingcoHome, Zones zonePOJO, GroupTranslations gt) {
        se.info24.homejaxb.Zones zones = new se.info24.homejaxb.Zones();
        Zone zone = new Zone();
        zone.setZoneID(zonePOJO.getZoneId());
        zone.setZoneName(zonePOJO.getZoneName());
        if (gt != null) {
            GroupID id = new GroupID();
            id.setGroupName(gt.getGroupName());
            id.setValue(zonePOJO.getGroupId());
            zone.setGroupID(id);
        }
        if (zonePOJO.getZoneDescription() != null) {
            zone.setZoneDescription(zonePOJO.getZoneDescription());
        }
        if (zonePOJO.getIconUrl() != null) {
            zone.setIconURL(zonePOJO.getIconUrl());
        }
        zones.getZone().add(zone);
        tingcoHome.setZones(zones);
        return tingcoHome;
    }

    public TingcoHome buildGetZoneDetailsByRoomId(TingcoHome tingcoHome, List<ZoneRooms> zoneRoomses, Set<String> zoneId) {
        se.info24.homejaxb.Zones zones = new se.info24.homejaxb.Zones();
        for (String id : zoneId) {
            for (ZoneRooms z : zoneRoomses) {
                if (z.getZones().getZoneId().equalsIgnoreCase(id)) {
                    Zone zone = new Zone();
                    zone.setZoneID(z.getZones().getZoneId());
                    zone.setZoneName(z.getZones().getZoneName());
                    zones.getZone().add(zone);
                }
            }
        }
        tingcoHome.setZones(zones);
        return tingcoHome;
    }
}
