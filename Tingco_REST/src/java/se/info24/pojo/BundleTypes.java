package se.info24.pojo;
// Generated Apr 28, 2014 4:47:22 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BundleTypes generated by hbm2java
 */
public class BundleTypes  implements java.io.Serializable {


     private String bundleTypeId;
     private String objectCode;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;
     private Set bundleTypeTranslationses = new HashSet(0);

    public BundleTypes() {
    }

	
    public BundleTypes(String bundleTypeId, String objectCode) {
        this.bundleTypeId = bundleTypeId;
        this.objectCode = objectCode;
    }
    public BundleTypes(String bundleTypeId, String objectCode, String lastUpdatedByUserId, Date createdDate, Date updatedDate, Set bundleTypeTranslationses) {
       this.bundleTypeId = bundleTypeId;
       this.objectCode = objectCode;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
       this.bundleTypeTranslationses = bundleTypeTranslationses;
    }
   
    public String getBundleTypeId() {
        return this.bundleTypeId;
    }
    
    public void setBundleTypeId(String bundleTypeId) {
        this.bundleTypeId = bundleTypeId;
    }
    public String getObjectCode() {
        return this.objectCode;
    }
    
    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
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
    public Set getBundleTypeTranslationses() {
        return this.bundleTypeTranslationses;
    }
    
    public void setBundleTypeTranslationses(Set bundleTypeTranslationses) {
        this.bundleTypeTranslationses = bundleTypeTranslationses;
    }




}

