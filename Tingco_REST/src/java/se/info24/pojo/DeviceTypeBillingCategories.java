package se.info24.pojo;
// Generated Jul 8, 2014 4:03:07 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * DeviceTypeBillingCategories generated by hbm2java
 */
public class DeviceTypeBillingCategories  implements java.io.Serializable {


     private DeviceTypeBillingCategoriesId id;
     private DeviceTypes deviceTypes;
     private BillingCategories billingCategories;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public DeviceTypeBillingCategories() {
    }

	
    public DeviceTypeBillingCategories(DeviceTypeBillingCategoriesId id, DeviceTypes deviceTypes, BillingCategories billingCategories, String lastUpdatedByUserId) {
        this.id = id;
        this.deviceTypes = deviceTypes;
        this.billingCategories = billingCategories;
        this.lastUpdatedByUserId = lastUpdatedByUserId;
    }
    public DeviceTypeBillingCategories(DeviceTypeBillingCategoriesId id, DeviceTypes deviceTypes, BillingCategories billingCategories, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.deviceTypes = deviceTypes;
       this.billingCategories = billingCategories;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public DeviceTypeBillingCategoriesId getId() {
        return this.id;
    }
    
    public void setId(DeviceTypeBillingCategoriesId id) {
        this.id = id;
    }
    public DeviceTypes getDeviceTypes() {
        return this.deviceTypes;
    }
    
    public void setDeviceTypes(DeviceTypes deviceTypes) {
        this.deviceTypes = deviceTypes;
    }
    public BillingCategories getBillingCategories() {
        return this.billingCategories;
    }
    
    public void setBillingCategories(BillingCategories billingCategories) {
        this.billingCategories = billingCategories;
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

