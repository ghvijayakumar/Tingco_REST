package se.info24.pojo;
// Generated Dec 6, 2010 12:53:09 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * DeviceOperationsMember generated by hbm2java
 */
public class DeviceOperationsMember  implements java.io.Serializable {


     private String deviceOperationsMemberId;
     private DeviceOperationsStatus deviceOperationsStatus;
     private Device device;
     private Date activeFromDate;
     private String userId;
     private Date createdDate;
     private Date updatedDate;

    public DeviceOperationsMember() {
    }

	
    public DeviceOperationsMember(String deviceOperationsMemberId, DeviceOperationsStatus deviceOperationsStatus, Device device, Date activeFromDate) {
        this.deviceOperationsMemberId = deviceOperationsMemberId;
        this.deviceOperationsStatus = deviceOperationsStatus;
        this.device = device;
        this.activeFromDate = activeFromDate;
    }
    public DeviceOperationsMember(String deviceOperationsMemberId, DeviceOperationsStatus deviceOperationsStatus, Device device, Date activeFromDate, String userId, Date createdDate, Date updatedDate) {
       this.deviceOperationsMemberId = deviceOperationsMemberId;
       this.deviceOperationsStatus = deviceOperationsStatus;
       this.device = device;
       this.activeFromDate = activeFromDate;
       this.userId = userId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public String getDeviceOperationsMemberId() {
        return this.deviceOperationsMemberId;
    }
    
    public void setDeviceOperationsMemberId(String deviceOperationsMemberId) {
        this.deviceOperationsMemberId = deviceOperationsMemberId;
    }
    public DeviceOperationsStatus getDeviceOperationsStatus() {
        return this.deviceOperationsStatus;
    }
    
    public void setDeviceOperationsStatus(DeviceOperationsStatus deviceOperationsStatus) {
        this.deviceOperationsStatus = deviceOperationsStatus;
    }
    public Device getDevice() {
        return this.device;
    }
    
    public void setDevice(Device device) {
        this.device = device;
    }
    public Date getActiveFromDate() {
        return this.activeFromDate;
    }
    
    public void setActiveFromDate(Date activeFromDate) {
        this.activeFromDate = activeFromDate;
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


