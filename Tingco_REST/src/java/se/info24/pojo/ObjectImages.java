package se.info24.pojo;
// Generated Sep 4, 2012 12:18:29 PM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.util.Date;

/**
 * ObjectImages generated by hbm2java
 */
public class ObjectImages  implements java.io.Serializable {


     private String objectId;
     private String objectIconUrl;
     private String objectLogoImageUrl;
     private String objectImageSmallUrl;
     private String objectImageLargeUrl;
     private String objectPowerByImageUrl;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public ObjectImages() {
    }

	
    public ObjectImages(String objectId) {
        this.objectId = objectId;
    }
    public ObjectImages(String objectId, String objectIconUrl, String objectLogoImageUrl, String objectImageSmallUrl, String objectImageLargeUrl, String objectPowerByImageUrl, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.objectId = objectId;
       this.objectIconUrl = objectIconUrl;
       this.objectLogoImageUrl = objectLogoImageUrl;
       this.objectImageSmallUrl = objectImageSmallUrl;
       this.objectImageLargeUrl = objectImageLargeUrl;
       this.objectPowerByImageUrl = objectPowerByImageUrl;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public String getObjectId() {
        return this.objectId;
    }
    
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    public String getObjectIconUrl() {
        return this.objectIconUrl;
    }
    
    public void setObjectIconUrl(String objectIconUrl) {
        this.objectIconUrl = objectIconUrl;
    }
    public String getObjectLogoImageUrl() {
        return this.objectLogoImageUrl;
    }
    
    public void setObjectLogoImageUrl(String objectLogoImageUrl) {
        this.objectLogoImageUrl = objectLogoImageUrl;
    }
    public String getObjectImageSmallUrl() {
        return this.objectImageSmallUrl;
    }
    
    public void setObjectImageSmallUrl(String objectImageSmallUrl) {
        this.objectImageSmallUrl = objectImageSmallUrl;
    }
    public String getObjectImageLargeUrl() {
        return this.objectImageLargeUrl;
    }
    
    public void setObjectImageLargeUrl(String objectImageLargeUrl) {
        this.objectImageLargeUrl = objectImageLargeUrl;
    }
    public String getObjectPowerByImageUrl() {
        return this.objectPowerByImageUrl;
    }
    
    public void setObjectPowerByImageUrl(String objectPowerByImageUrl) {
        this.objectPowerByImageUrl = objectPowerByImageUrl;
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

