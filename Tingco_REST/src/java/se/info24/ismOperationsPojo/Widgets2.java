package se.info24.ismOperationsPojo;
// Generated Aug 6, 2014 11:26:13 AM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;

/**
 * Widgets2 generated by hbm2java
 */
public class Widgets2  implements java.io.Serializable {


     private String widgetId;
     private String groupId;
     private String widgetName;
     private String widgetDescription;
     private String widgetTypeId;
     private String widgetTemplateId;
     private String widgetUrl;
     private Integer isEnabled;
     private Integer reloadTimer;
     private Date activeFromDate;
     private Date activeToDate;
     private String apimethod;
     private String apiuser;
     private String apipassword;
     private String objectId;
     private String externalUrl;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Integer width;
     private Integer height;
     private String imageUrl;
     private Date updatedDate;
     private String deviceDateItemId;
     private Integer numberOfDecimals;
     private String unit;
     private String timeZone;
     private BigDecimal minValue;
     private BigDecimal maxValue;
     private BigDecimal warningLowValue;
     private BigDecimal warningHighValue;

    public Widgets2() {
    }

	
    public Widgets2(String widgetId, String widgetName, String widgetUrl, String lastUpdatedByUserId) {
        this.widgetId = widgetId;
        this.widgetName = widgetName;
        this.widgetUrl = widgetUrl;
        this.lastUpdatedByUserId = lastUpdatedByUserId;
    }
    public Widgets2(String widgetId, String groupId, String widgetName, String widgetDescription, String widgetTypeId, String widgetTemplateId, String widgetUrl, Integer isEnabled, Integer reloadTimer, Date activeFromDate, Date activeToDate, String apimethod, String apiuser, String apipassword, String objectId, String externalUrl, String lastUpdatedByUserId, Date createdDate, Integer width, Integer height, String imageUrl, Date updatedDate, String deviceDateItemId, Integer numberOfDecimals, String unit, String timeZone, BigDecimal minValue, BigDecimal maxValue, BigDecimal warningLowValue, BigDecimal warningHighValue) {
       this.widgetId = widgetId;
       this.groupId = groupId;
       this.widgetName = widgetName;
       this.widgetDescription = widgetDescription;
       this.widgetTypeId = widgetTypeId;
       this.widgetTemplateId = widgetTemplateId;
       this.widgetUrl = widgetUrl;
       this.isEnabled = isEnabled;
       this.reloadTimer = reloadTimer;
       this.activeFromDate = activeFromDate;
       this.activeToDate = activeToDate;
       this.apimethod = apimethod;
       this.apiuser = apiuser;
       this.apipassword = apipassword;
       this.objectId = objectId;
       this.externalUrl = externalUrl;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.width = width;
       this.height = height;
       this.imageUrl = imageUrl;
       this.updatedDate = updatedDate;
       this.deviceDateItemId = deviceDateItemId;
       this.numberOfDecimals = numberOfDecimals;
       this.unit = unit;
       this.timeZone = timeZone;
       this.minValue = minValue;
       this.maxValue = maxValue;
       this.warningLowValue = warningLowValue;
       this.warningHighValue = warningHighValue;
    }
   
    public String getWidgetId() {
        return this.widgetId;
    }
    
    public void setWidgetId(String widgetId) {
        this.widgetId = widgetId;
    }
    public String getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public String getWidgetName() {
        return this.widgetName;
    }
    
    public void setWidgetName(String widgetName) {
        this.widgetName = widgetName;
    }
    public String getWidgetDescription() {
        return this.widgetDescription;
    }
    
    public void setWidgetDescription(String widgetDescription) {
        this.widgetDescription = widgetDescription;
    }
    public String getWidgetTypeId() {
        return this.widgetTypeId;
    }
    
    public void setWidgetTypeId(String widgetTypeId) {
        this.widgetTypeId = widgetTypeId;
    }
    public String getWidgetTemplateId() {
        return this.widgetTemplateId;
    }
    
    public void setWidgetTemplateId(String widgetTemplateId) {
        this.widgetTemplateId = widgetTemplateId;
    }
    public String getWidgetUrl() {
        return this.widgetUrl;
    }
    
    public void setWidgetUrl(String widgetUrl) {
        this.widgetUrl = widgetUrl;
    }
    public Integer getIsEnabled() {
        return this.isEnabled;
    }
    
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }
    public Integer getReloadTimer() {
        return this.reloadTimer;
    }
    
    public void setReloadTimer(Integer reloadTimer) {
        this.reloadTimer = reloadTimer;
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
    public String getApimethod() {
        return this.apimethod;
    }
    
    public void setApimethod(String apimethod) {
        this.apimethod = apimethod;
    }
    public String getApiuser() {
        return this.apiuser;
    }
    
    public void setApiuser(String apiuser) {
        this.apiuser = apiuser;
    }
    public String getApipassword() {
        return this.apipassword;
    }
    
    public void setApipassword(String apipassword) {
        this.apipassword = apipassword;
    }
    public String getObjectId() {
        return this.objectId;
    }
    
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    public String getExternalUrl() {
        return this.externalUrl;
    }
    
    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
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
    public Integer getWidth() {
        return this.width;
    }
    
    public void setWidth(Integer width) {
        this.width = width;
    }
    public Integer getHeight() {
        return this.height;
    }
    
    public void setHeight(Integer height) {
        this.height = height;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public Date getUpdatedDate() {
        return this.updatedDate;
    }
    
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
    public String getDeviceDateItemId() {
        return this.deviceDateItemId;
    }
    
    public void setDeviceDateItemId(String deviceDateItemId) {
        this.deviceDateItemId = deviceDateItemId;
    }
    public Integer getNumberOfDecimals() {
        return this.numberOfDecimals;
    }
    
    public void setNumberOfDecimals(Integer numberOfDecimals) {
        this.numberOfDecimals = numberOfDecimals;
    }
    public String getUnit() {
        return this.unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getTimeZone() {
        return this.timeZone;
    }
    
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
    public BigDecimal getMinValue() {
        return this.minValue;
    }
    
    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }
    public BigDecimal getMaxValue() {
        return this.maxValue;
    }
    
    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }
    public BigDecimal getWarningLowValue() {
        return this.warningLowValue;
    }
    
    public void setWarningLowValue(BigDecimal warningLowValue) {
        this.warningLowValue = warningLowValue;
    }
    public BigDecimal getWarningHighValue() {
        return this.warningHighValue;
    }
    
    public void setWarningHighValue(BigDecimal warningHighValue) {
        this.warningHighValue = warningHighValue;
    }




}


