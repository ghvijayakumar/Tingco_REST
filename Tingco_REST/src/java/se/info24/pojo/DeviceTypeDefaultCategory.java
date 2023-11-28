package se.info24.pojo;
// Generated Sep 25, 2013 1:29:15 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * DeviceTypeDefaultCategory generated by hbm2java
 */
public class DeviceTypeDefaultCategory  implements java.io.Serializable {


     private String deviceTypeId;
     private ContainerTypes containerTypes;
     private DeviceTypes deviceTypes;
     private ContentCategories contentCategories;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public DeviceTypeDefaultCategory() {
    }

	
    public DeviceTypeDefaultCategory(String deviceTypeId, ContainerTypes containerTypes, DeviceTypes deviceTypes, ContentCategories contentCategories) {
        this.deviceTypeId = deviceTypeId;
        this.containerTypes = containerTypes;
        this.deviceTypes = deviceTypes;
        this.contentCategories = contentCategories;
    }
    public DeviceTypeDefaultCategory(String deviceTypeId, ContainerTypes containerTypes, DeviceTypes deviceTypes, ContentCategories contentCategories, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.deviceTypeId = deviceTypeId;
       this.containerTypes = containerTypes;
       this.deviceTypes = deviceTypes;
       this.contentCategories = contentCategories;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public String getDeviceTypeId() {
        return this.deviceTypeId;
    }
    
    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }
    public ContainerTypes getContainerTypes() {
        return this.containerTypes;
    }
    
    public void setContainerTypes(ContainerTypes containerTypes) {
        this.containerTypes = containerTypes;
    }
    public DeviceTypes getDeviceTypes() {
        return this.deviceTypes;
    }
    
    public void setDeviceTypes(DeviceTypes deviceTypes) {
        this.deviceTypes = deviceTypes;
    }
    public ContentCategories getContentCategories() {
        return this.contentCategories;
    }
    
    public void setContentCategories(ContentCategories contentCategories) {
        this.contentCategories = contentCategories;
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

