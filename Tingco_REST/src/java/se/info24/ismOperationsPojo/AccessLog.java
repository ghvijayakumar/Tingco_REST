package se.info24.ismOperationsPojo;
// Generated Apr 9, 2013 11:22:51 AM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * AccessLog generated by hbm2java
 */
public class AccessLog  implements java.io.Serializable {


     private String accessLogRowId;
     private Date accessTime;
     private String deviceId;
     private String deviceGroupId;
     private String deviceName;
     private String resource;
     private String userAliasId;
     private String userAlias;
     private String firstName;
     private String lastName;
     private String userId;
     private String userGroupId;
     private String userAliasTypeId;
     private String userAliasTypeName;
     private String eventTypeId;
     private String eventSubject;
     private String posLatitude;
     private String posLongitude;
     private String posAltitude;
     private Integer posHeading;
     private String coordinateSystemId;
     private String location;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public AccessLog() {
    }

	
    public AccessLog(String accessLogRowId, Date accessTime, String deviceId, String deviceGroupId, String deviceName, String userAlias, String userGroupId, String userAliasTypeId, String eventTypeId, String eventSubject) {
        this.accessLogRowId = accessLogRowId;
        this.accessTime = accessTime;
        this.deviceId = deviceId;
        this.deviceGroupId = deviceGroupId;
        this.deviceName = deviceName;
        this.userAlias = userAlias;
        this.userGroupId = userGroupId;
        this.userAliasTypeId = userAliasTypeId;
        this.eventTypeId = eventTypeId;
        this.eventSubject = eventSubject;
    }
    public AccessLog(String accessLogRowId, Date accessTime, String deviceId, String deviceGroupId, String deviceName, String resource, String userAliasId, String userAlias, String firstName, String lastName, String userId, String userGroupId, String userAliasTypeId, String userAliasTypeName, String eventTypeId, String eventSubject, String posLatitude, String posLongitude, String posAltitude, Integer posHeading, String coordinateSystemId, String location, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.accessLogRowId = accessLogRowId;
       this.accessTime = accessTime;
       this.deviceId = deviceId;
       this.deviceGroupId = deviceGroupId;
       this.deviceName = deviceName;
       this.resource = resource;
       this.userAliasId = userAliasId;
       this.userAlias = userAlias;
       this.firstName = firstName;
       this.lastName = lastName;
       this.userId = userId;
       this.userGroupId = userGroupId;
       this.userAliasTypeId = userAliasTypeId;
       this.userAliasTypeName = userAliasTypeName;
       this.eventTypeId = eventTypeId;
       this.eventSubject = eventSubject;
       this.posLatitude = posLatitude;
       this.posLongitude = posLongitude;
       this.posAltitude = posAltitude;
       this.posHeading = posHeading;
       this.coordinateSystemId = coordinateSystemId;
       this.location = location;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public String getAccessLogRowId() {
        return this.accessLogRowId;
    }
    
    public void setAccessLogRowId(String accessLogRowId) {
        this.accessLogRowId = accessLogRowId;
    }
    public Date getAccessTime() {
        return this.accessTime;
    }
    
    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }
    public String getDeviceId() {
        return this.deviceId;
    }
    
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public String getDeviceGroupId() {
        return this.deviceGroupId;
    }
    
    public void setDeviceGroupId(String deviceGroupId) {
        this.deviceGroupId = deviceGroupId;
    }
    public String getDeviceName() {
        return this.deviceName;
    }
    
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    public String getResource() {
        return this.resource;
    }
    
    public void setResource(String resource) {
        this.resource = resource;
    }
    public String getUserAliasId() {
        return this.userAliasId;
    }
    
    public void setUserAliasId(String userAliasId) {
        this.userAliasId = userAliasId;
    }
    public String getUserAlias() {
        return this.userAlias;
    }
    
    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserGroupId() {
        return this.userGroupId;
    }
    
    public void setUserGroupId(String userGroupId) {
        this.userGroupId = userGroupId;
    }
    public String getUserAliasTypeId() {
        return this.userAliasTypeId;
    }
    
    public void setUserAliasTypeId(String userAliasTypeId) {
        this.userAliasTypeId = userAliasTypeId;
    }
    public String getUserAliasTypeName() {
        return this.userAliasTypeName;
    }
    
    public void setUserAliasTypeName(String userAliasTypeName) {
        this.userAliasTypeName = userAliasTypeName;
    }
    public String getEventTypeId() {
        return this.eventTypeId;
    }
    
    public void setEventTypeId(String eventTypeId) {
        this.eventTypeId = eventTypeId;
    }
    public String getEventSubject() {
        return this.eventSubject;
    }
    
    public void setEventSubject(String eventSubject) {
        this.eventSubject = eventSubject;
    }

    public String getPosAltitude() {
        return posAltitude;
    }

    public void setPosAltitude(String posAltitude) {
        this.posAltitude = posAltitude;
    }

    public String getPosLatitude() {
        return posLatitude;
    }

    public void setPosLatitude(String posLatitude) {
        this.posLatitude = posLatitude;
    }

    public String getPosLongitude() {
        return posLongitude;
    }

    public void setPosLongitude(String posLongitude) {
        this.posLongitude = posLongitude;
    }
   
    public Integer getPosHeading() {
        return this.posHeading;
    }
    
    public void setPosHeading(Integer posHeading) {
        this.posHeading = posHeading;
    }
    public String getCoordinateSystemId() {
        return this.coordinateSystemId;
    }
    
    public void setCoordinateSystemId(String coordinateSystemId) {
        this.coordinateSystemId = coordinateSystemId;
    }
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    public String getLastUpdatedByUserId() {
        return this.lastUpdatedByUserId;
    }
    
    public void setLastUpdatedByUserId(String lastUpdatedByUserId) {
        this.lastUpdatedByUserId = lastUpdatedByUserId;
    }
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public Date getUpdatedDate() {
        return this.updatedDate;
    }
    
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }




}

