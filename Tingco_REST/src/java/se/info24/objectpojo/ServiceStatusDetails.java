/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.info24.objectpojo;

import java.util.Date;

/**
 *
 * @author Vijayakumar
 */
public class ServiceStatusDetails {
    private String serviceId;
    private String serviceName;
    private String deviceId;
    private String deviceName;
    private String deviceTypeId;
    private String deviceTypeName;
    private String groupId;
    private String groupName;
    private String roomId;
    private String roomName;
    private Integer isOnline;
    private String objectStateCode;
    private Date isOnlineChangedDate;
    private Date objectStateChangedDate;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    public Date getIsOnlineChangedDate() {
        return isOnlineChangedDate;
    }

    public void setIsOnlineChangedDate(Date isOnlineChangedDate) {
        this.isOnlineChangedDate = isOnlineChangedDate;
    }

    public Date getObjectStateChangedDate() {
        return objectStateChangedDate;
    }

    public void setObjectStateChangedDate(Date objectStateChangedDate) {
        this.objectStateChangedDate = objectStateChangedDate;
    }

    public String getObjectStateCode() {
        return objectStateCode;
    }

    public void setObjectStateCode(String objectStateCode) {
        this.objectStateCode = objectStateCode;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

}
