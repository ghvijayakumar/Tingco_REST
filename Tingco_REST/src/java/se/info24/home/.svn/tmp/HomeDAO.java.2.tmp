/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.home;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import se.info24.objectpojo.ServiceStatusDetails;
import se.info24.pojo.BuildingTypeTranslations;
import se.info24.pojo.BuildingTypes;
import se.info24.pojo.Buildings;
import se.info24.pojo.Device;
import se.info24.pojo.RoomTypeTranslations;
import se.info24.pojo.Rooms;
import se.info24.pojo.ZoneRooms;
import se.info24.pojo.Zones;
import se.info24.util.TCMUtil;

/**
 *
 * @author Sumit
 */
public class HomeDAO {

    public BuildingTypeTranslations getBuildingTypeTranslationsById(String buildingTypeId, int countryId, Session session) {
        return (BuildingTypeTranslations) session.getNamedQuery("getBuildingTypeTranslationsById").setString("buildingTypeId", buildingTypeId).setInteger("countryId", countryId).uniqueResult();
    }

    public Object getBuildingsById(String buildingId, Session session) {
        return session.getNamedQuery("getBuildingsById").setString("buildingId", buildingId).uniqueResult();
    }

    List<BuildingTypeTranslations> getAllBuildingTypeTranslationsByCountryId(int countryId, Session session) {
        return session.getNamedQuery("getBuildingTypeTranslationsByCountryId").setInteger("countryId", countryId).list();
    }

    List<ServiceStatusDetails> getBuildingDevicesByBuildingIdAndGroupId(Session session, String buildingId, String groupId, int countryId) {
        StringBuffer queryString = new StringBuffer();
        queryString.append("select d.deviceId as deviceId, d.deviceName as deviceName, gt.groupId as groupId, gt.groupName as groupName, r.roomId as roomId, r.roomName as roomName from BuildingDevices " +
                " as bd inner join device as d on bd.deviceId = d.deviceId inner join groups as g on d.groupId = g.groupId inner join groupTranslations as gt on g.groupId = gt.groupId" +
                " inner join rooms as r on bd.roomId = r.roomId and bd.buildingId = '" + buildingId + "' and bd.buildingId " +
                " in (select buildingId from Buildings where OwnerGroupID in(" + TCMUtil.getGroupTrust(groupId) + ")) " +
                " and bd.deviceID in (select DeviceID from Device where GroupID" +
                " in(" + TCMUtil.getGroupTrust(groupId) + ")) and gt.countryId = " + countryId + " order by bd.createdDate desc");
        SQLQuery query = session.createSQLQuery(queryString.toString());
        query.addScalar("deviceId", Hibernate.STRING);
        query.addScalar("deviceName", Hibernate.STRING);
        query.addScalar("groupId", Hibernate.STRING);
        query.addScalar("groupName", Hibernate.STRING);
        query.addScalar("roomId", Hibernate.STRING);
        query.addScalar("roomName", Hibernate.STRING);
        query.setResultTransformer(Transformers.aliasToBean(ServiceStatusDetails.class));
        return query.setMaxResults(200).list();
    }

    Object getBuildingDevicesById(Session session, String buildingId, String deviceId) {
        return session.getNamedQuery("getBuildingDevicesByIds").setString("buildingId", buildingId).setString("deviceId", deviceId).uniqueResult();
    }

    BuildingTypes getBuildingTypesById(String buildingTypeID, Session session) {
        return (BuildingTypes) session.getNamedQuery("getBuildingTypesById").setString("buildingTypeId", buildingTypeID).uniqueResult();
    }

    List<Buildings> getBuildingsByNameAndOwnerGroupId(List<String> list, String searchString, Session session) {
        return session.getNamedQuery("getBuildingsByNameAndOwnerGroupId").setParameterList("ownerGroupId", list).setString("buildingName", searchString).list();
    }

    List<Buildings> getBuildingsByGroupIdsList(List<String> list, Session session) {
        return session.getNamedQuery("getBuildingsByOwnerGroupId").setParameterList("ownerGroupId", list).list();
    }

    Object getBuildingsByIdAndGroupIdsList(List<String> list, String buildingId, Session session) {
        return session.getNamedQuery("getBuildingsByIdAndOwnerGroupId").setParameterList("ownerGroupId", list).setString("buildingId", buildingId).uniqueResult();
    }

    List<Device> getDeviceDetailsForBuilding(String groupId, int countryId, String groupName, String deviceName, Session session) {
        StringBuffer queryString = new StringBuffer();
        queryString.append("select deviceId, deviceName from device as d inner join groups as g on d.groupId = g.groupId ");
        if (groupName == null && deviceName == null) {
            queryString.append(" and g.groupId = '" + groupId + "' ");
        } else {
            queryString.append("and g.groupid in(" + TCMUtil.getGroupTrust(groupId) + ") ");

            if (groupName != null) {
                if (TCMUtil.isValidUUID(groupName)) {
                    queryString.append("inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.countryId = " + countryId + " and gts.groupId = '" + groupName + "' ");
                } else {
                    queryString.append("inner join GroupTranslations as gts on g.groupId = gts.groupId and gts.countryId = " + countryId + " and gts.groupName like '%" + groupName + "%' ");
                }

            }

            if (deviceName != null) {
                if (TCMUtil.isValidUUID(deviceName)) {
                    queryString.append(" and d.deviceId = '" + deviceName + "' ");
                } else {
                    queryString.append(" and (d.deviceName like '%" + deviceName + "%' or d.deviceName2 like '%" + deviceName + "%') ");
                }
            }
        }
        queryString.append(" order by d.createdDate desc");
        SQLQuery query = session.createSQLQuery(queryString.toString());
        query.addScalar("deviceId", Hibernate.STRING);
        query.addScalar("deviceName", Hibernate.STRING);
        query.setResultTransformer(Transformers.aliasToBean(Device.class));
        if (groupName == null && deviceName == null) {
            return query.setMaxResults(50).list();
        } else {
            return query.list();
        }


    }

    List<RoomTypeTranslations> getRoomTypeTranslationsByCountryIds(int countryId, Session session) {
        return session.getNamedQuery("getRoomTypeTranslationsByCountryIds").setInteger("countryId", countryId).list();
    }

    List<Rooms> getRoomsByBuildingId(String buildingId, Session session) {
        return session.getNamedQuery("getRoomsByBuildingId").setString("buildingId", buildingId).setMaxResults(50).list();
    }

    List<Zones> getZonesByGroupIdsList(List<String> list, Session session) {
        return session.getNamedQuery("getZonesByGroupIDsList").setParameterList("groupId", list).list();
    }

    Object getZonesByIdAndGroupIds(List<String> groupIdsList, String searchString, Session session) {
        return session.getNamedQuery("getZonesByIdAndGroupIds").setParameterList("groupId", groupIdsList).setString("zoneId", searchString).uniqueResult();
    }

    List<Zones> getZonesByZoneNameAndGroupIds(List<String> groupIdsList, String searchString, Session session) {
        return session.getNamedQuery("getZonesByZoneNameAndGroupIds").setParameterList("groupId", groupIdsList).setString("searchString", searchString).list();
    }

    List<Rooms> getRoomsByGroupIdsList(List<String> list, Session session) {
        return session.getNamedQuery("getRoomsByGroupIDsList").setParameterList("groupId", list).list();
    }

    Object getRoomsByIdAndOwnerGroupIds(List<String> ownerGroupIdsList, String searchString, Session session) {
        return session.getNamedQuery("getRoomsByIdAndOwnerGroupIds").setParameterList("groupId", ownerGroupIdsList).setString("roomId", searchString).uniqueResult();
    }

    List<Rooms> getRoomsByRoomNameAndOwnerGroupIds(List<String> ownerGroupIdsList, String searchString, Session session) {
        return session.getNamedQuery("getRoomsByRoomNameAndOwnerGroupIds").setParameterList("groupId", ownerGroupIdsList).setString("searchString", searchString).list();
    }

    Object getZoneRoomsById(String zoneId, String roomId, Session session) {
        return session.getNamedQuery("getZoneRoomsById").setString("zoneId", zoneId).setString("roomId", roomId).uniqueResult();
    }

    public List<ZoneRooms> getZoneRoomsByRoomId(Session session, String roomId) {
        return session.getNamedQuery("getZoneRoomsByRoomId").setString("roomId", roomId).list();
    }

    boolean saveOrUpdate(Buildings newBuildings, Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(newBuildings);
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
}
