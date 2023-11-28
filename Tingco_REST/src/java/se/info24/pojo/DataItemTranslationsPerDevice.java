package se.info24.pojo;
// Generated Oct 5, 2012 12:34:20 PM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.util.Date;

/**
 * DataItemTranslationsPerDevice generated by hbm2java
 */
public class DataItemTranslationsPerDevice  implements java.io.Serializable {


     private DataItemTranslationsPerDeviceId id;
     private String dataItemName;
     private String dataItemDescription;
     private String unit;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public DataItemTranslationsPerDevice() {
    }

	
    public DataItemTranslationsPerDevice(DataItemTranslationsPerDeviceId id, String dataItemName) {
        this.id = id;
        this.dataItemName = dataItemName;
    }
    public DataItemTranslationsPerDevice(DataItemTranslationsPerDeviceId id, String dataItemName, String dataItemDescription, String unit, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.dataItemName = dataItemName;
       this.dataItemDescription = dataItemDescription;
       this.unit = unit;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public DataItemTranslationsPerDeviceId getId() {
        return this.id;
    }
    
    public void setId(DataItemTranslationsPerDeviceId id) {
        this.id = id;
    }
    public String getDataItemName() {
        return this.dataItemName;
    }
    
    public void setDataItemName(String dataItemName) {
        this.dataItemName = dataItemName;
    }
    public String getDataItemDescription() {
        return this.dataItemDescription;
    }
    
    public void setDataItemDescription(String dataItemDescription) {
        this.dataItemDescription = dataItemDescription;
    }
    public String getUnit() {
        return this.unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
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


