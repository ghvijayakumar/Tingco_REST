package se.info24.pojo;
// Generated Jul 3, 2013 5:37:47 PM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.util.Date;

/**
 * MediaFileTypes generated by hbm2java
 */
public class MediaFileTypes  implements java.io.Serializable {


     private String mediaFileTypeId;
     private String typeName;
     private String typeDescription;
     private String objectCode;
     private String iconUrl;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public MediaFileTypes() {
    }

	
    public MediaFileTypes(String mediaFileTypeId, String typeName, String objectCode) {
        this.mediaFileTypeId = mediaFileTypeId;
        this.typeName = typeName;
        this.objectCode = objectCode;
    }
    public MediaFileTypes(String mediaFileTypeId, String typeName, String typeDescription, String objectCode, String iconUrl, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.mediaFileTypeId = mediaFileTypeId;
       this.typeName = typeName;
       this.typeDescription = typeDescription;
       this.objectCode = objectCode;
       this.iconUrl = iconUrl;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public String getMediaFileTypeId() {
        return this.mediaFileTypeId;
    }
    
    public void setMediaFileTypeId(String mediaFileTypeId) {
        this.mediaFileTypeId = mediaFileTypeId;
    }
    public String getTypeName() {
        return this.typeName;
    }
    
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getTypeDescription() {
        return this.typeDescription;
    }
    
    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }
    public String getObjectCode() {
        return this.objectCode;
    }
    
    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }
    public String getIconUrl() {
        return this.iconUrl;
    }
    
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
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


