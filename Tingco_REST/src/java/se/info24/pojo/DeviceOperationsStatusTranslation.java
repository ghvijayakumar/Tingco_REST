package se.info24.pojo;
// Generated Dec 6, 2010 12:53:09 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * DeviceOperationsStatusTranslation generated by hbm2java
 */
public class DeviceOperationsStatusTranslation  implements java.io.Serializable {


     private DeviceOperationsStatusTranslationId id;
     private Country country;
     private DeviceOperationsStatus deviceOperationsStatus;
     private String deviceOperationsName;
     private String deviceOperationsDescription;
     private String userId;
     private Date createdDate;
     private Date updatedDate;

    public DeviceOperationsStatusTranslation() {
    }

	
    public DeviceOperationsStatusTranslation(DeviceOperationsStatusTranslationId id, Country country, DeviceOperationsStatus deviceOperationsStatus) {
        this.id = id;
        this.country = country;
        this.deviceOperationsStatus = deviceOperationsStatus;
    }
    public DeviceOperationsStatusTranslation(DeviceOperationsStatusTranslationId id, Country country, DeviceOperationsStatus deviceOperationsStatus, String deviceOperationsName, String deviceOperationsDescription, String userId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.country = country;
       this.deviceOperationsStatus = deviceOperationsStatus;
       this.deviceOperationsName = deviceOperationsName;
       this.deviceOperationsDescription = deviceOperationsDescription;
       this.userId = userId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public DeviceOperationsStatusTranslationId getId() {
        return this.id;
    }
    
    public void setId(DeviceOperationsStatusTranslationId id) {
        this.id = id;
    }
    public Country getCountry() {
        return this.country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }
    public DeviceOperationsStatus getDeviceOperationsStatus() {
        return this.deviceOperationsStatus;
    }
    
    public void setDeviceOperationsStatus(DeviceOperationsStatus deviceOperationsStatus) {
        this.deviceOperationsStatus = deviceOperationsStatus;
    }
    public String getDeviceOperationsName() {
        return this.deviceOperationsName;
    }
    
    public void setDeviceOperationsName(String deviceOperationsName) {
        this.deviceOperationsName = deviceOperationsName;
    }
    public String getDeviceOperationsDescription() {
        return this.deviceOperationsDescription;
    }
    
    public void setDeviceOperationsDescription(String deviceOperationsDescription) {
        this.deviceOperationsDescription = deviceOperationsDescription;
    }
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
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


