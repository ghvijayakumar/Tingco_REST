package se.info24.pojo;
// Generated Jun 26, 2012 3:24:36 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * PricePlanVersions generated by hbm2java
 */
public class PricePlanVersions  implements java.io.Serializable {


     private String pricePlanVersionId;
     private PricePlans pricePlans;
     private String versionName;
     private String versionDescription;
     private Date activeFromDate;
     private Date activeToDate;
     private int priority;
     private int currencyId;
     private Integer isEnabled;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public PricePlanVersions() {
    }

	
    public PricePlanVersions(String pricePlanVersionId, PricePlans pricePlans, String versionName, Date activeFromDate, Date activeToDate, int priority, int currencyId, String lastUpdatedByUserId) {
        this.pricePlanVersionId = pricePlanVersionId;
        this.pricePlans = pricePlans;
        this.versionName = versionName;
        this.activeFromDate = activeFromDate;
        this.activeToDate = activeToDate;
        this.priority = priority;
        this.currencyId = currencyId;
        this.lastUpdatedByUserId = lastUpdatedByUserId;
    }
    public PricePlanVersions(String pricePlanVersionId, PricePlans pricePlans, String versionName, String versionDescription, Date activeFromDate, Date activeToDate, int priority, int currencyId, Integer isEnabled, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.pricePlanVersionId = pricePlanVersionId;
       this.pricePlans = pricePlans;
       this.versionName = versionName;
       this.versionDescription = versionDescription;
       this.activeFromDate = activeFromDate;
       this.activeToDate = activeToDate;
       this.priority = priority;
       this.currencyId = currencyId;
       this.isEnabled = isEnabled;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public String getPricePlanVersionId() {
        return this.pricePlanVersionId;
    }
    
    public void setPricePlanVersionId(String pricePlanVersionId) {
        this.pricePlanVersionId = pricePlanVersionId;
    }
    public PricePlans getPricePlans() {
        return this.pricePlans;
    }
    
    public void setPricePlans(PricePlans pricePlans) {
        this.pricePlans = pricePlans;
    }
    public String getVersionName() {
        return this.versionName;
    }
    
    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
    public String getVersionDescription() {
        return this.versionDescription;
    }
    
    public void setVersionDescription(String versionDescription) {
        this.versionDescription = versionDescription;
    }
    public Date getActiveFromDate() {
        return this.activeFromDate;
    }
    
    public void setActiveFromDate(Date activeFromDate) {
        this.activeFromDate = activeFromDate;
    }
    public Date getActiveToDate() {
        return this.activeToDate;
    }
    
    public void setActiveToDate(Date activeToDate) {
        this.activeToDate = activeToDate;
    }
    public int getPriority() {
        return this.priority;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public int getCurrencyId() {
        return this.currencyId;
    }
    
    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }
    public Integer getIsEnabled() {
        return this.isEnabled;
    }
    
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
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


