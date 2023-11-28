package se.info24.pojo;
// Generated May 2, 2011 10:43:33 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * DomainSettings generated by hbm2java
 */
public class DomainSettings  implements java.io.Serializable {


     private String domainSettingId;
     private String domainSettingParentId;
     private String domainId;
     private String domainSettingName;
     private String domainSettingValue;
     private String settingDataTypeId;
     private Date activeFromDate;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public DomainSettings() {
    }

	
    public DomainSettings(String domainSettingId, String domainId, String domainSettingName, String domainSettingValue, String settingDataTypeId) {
        this.domainSettingId = domainSettingId;
        this.domainId = domainId;
        this.domainSettingName = domainSettingName;
        this.domainSettingValue = domainSettingValue;
        this.settingDataTypeId = settingDataTypeId;
    }
    public DomainSettings(String domainSettingId, String domainSettingParentId, String domainId, String domainSettingName, String domainSettingValue, String settingDataTypeId, Date activeFromDate, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.domainSettingId = domainSettingId;
       this.domainSettingParentId = domainSettingParentId;
       this.domainId = domainId;
       this.domainSettingName = domainSettingName;
       this.domainSettingValue = domainSettingValue;
       this.settingDataTypeId = settingDataTypeId;
       this.activeFromDate = activeFromDate;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public String getDomainSettingId() {
        return this.domainSettingId;
    }
    
    public void setDomainSettingId(String domainSettingId) {
        this.domainSettingId = domainSettingId;
    }
    public String getDomainSettingParentId() {
        return this.domainSettingParentId;
    }
    
    public void setDomainSettingParentId(String domainSettingParentId) {
        this.domainSettingParentId = domainSettingParentId;
    }
    public String getDomainId() {
        return this.domainId;
    }
    
    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }
    public String getDomainSettingName() {
        return this.domainSettingName;
    }
    
    public void setDomainSettingName(String domainSettingName) {
        this.domainSettingName = domainSettingName;
    }
    public String getDomainSettingValue() {
        return this.domainSettingValue;
    }
    
    public void setDomainSettingValue(String domainSettingValue) {
        this.domainSettingValue = domainSettingValue;
    }
    public String getSettingDataTypeId() {
        return this.settingDataTypeId;
    }
    
    public void setSettingDataTypeId(String settingDataTypeId) {
        this.settingDataTypeId = settingDataTypeId;
    }
    public Date getActiveFromDate() {
        return this.activeFromDate;
    }
    
    public void setActiveFromDate(Date activeFromDate) {
        this.activeFromDate = activeFromDate;
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

