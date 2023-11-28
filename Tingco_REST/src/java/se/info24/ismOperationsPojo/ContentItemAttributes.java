package se.info24.ismOperationsPojo;
// Generated Aug 25, 2011 3:25:26 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ContentItemAttributes generated by hbm2java
 */
public class ContentItemAttributes  implements java.io.Serializable {


     private ContentItemAttributesId id;
     private String contentAttributeValue;
     private Date activeFromDate;
     private String language;
     private String unit;
     private String dataType;
     private String quality;
     private Double scaling;
     private String md5;
     private Integer isEncrypted;
     private Integer isEnabled;
     private Date createdDate;
     private Date updatedDate;

    public ContentItemAttributes() {
    }

	
    public ContentItemAttributes(ContentItemAttributesId id, String contentAttributeValue, Date activeFromDate) {
        this.id = id;
        this.contentAttributeValue = contentAttributeValue;
        this.activeFromDate = activeFromDate;
    }
    public ContentItemAttributes(ContentItemAttributesId id, String contentAttributeValue, Date activeFromDate, String language, String unit, String dataType, String quality, Double scaling, String md5, Integer isEncrypted, Integer isEnabled, Date createdDate, Date updatedDate) {
       this.id = id;
       this.contentAttributeValue = contentAttributeValue;
       this.activeFromDate = activeFromDate;
       this.language = language;
       this.unit = unit;
       this.dataType = dataType;
       this.quality = quality;
       this.scaling = scaling;
       this.md5 = md5;
       this.isEncrypted = isEncrypted;
       this.isEnabled = isEnabled;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public ContentItemAttributesId getId() {
        return this.id;
    }
    
    public void setId(ContentItemAttributesId id) {
        this.id = id;
    }
    public String getContentAttributeValue() {
        return this.contentAttributeValue;
    }
    
    public void setContentAttributeValue(String contentAttributeValue) {
        this.contentAttributeValue = contentAttributeValue;
    }
    public Date getActiveFromDate() {
        return this.activeFromDate;
    }
    
    public void setActiveFromDate(Date activeFromDate) {
        this.activeFromDate = activeFromDate;
    }
    public String getLanguage() {
        return this.language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getUnit() {
        return this.unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getDataType() {
        return this.dataType;
    }
    
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    public String getQuality() {
        return this.quality;
    }
    
    public void setQuality(String quality) {
        this.quality = quality;
    }
    public Double getScaling() {
        return this.scaling;
    }
    
    public void setScaling(Double scaling) {
        this.scaling = scaling;
    }
    public String getMd5() {
        return this.md5;
    }
    
    public void setMd5(String md5) {
        this.md5 = md5;
    }
    public Integer getIsEncrypted() {
        return this.isEncrypted;
    }
    
    public void setIsEncrypted(Integer isEncrypted) {
        this.isEncrypted = isEncrypted;
    }
    public Integer getIsEnabled() {
        return this.isEnabled;
    }
    
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
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


