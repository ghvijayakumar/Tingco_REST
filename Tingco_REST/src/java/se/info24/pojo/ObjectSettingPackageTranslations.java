package se.info24.pojo;
// Generated Oct 30, 2012 10:12:25 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ObjectSettingPackageTranslations generated by hbm2java
 */
public class ObjectSettingPackageTranslations  implements java.io.Serializable {


     private ObjectSettingPackageTranslationsId id;
     private ObjectSettingPackages objectSettingPackages;
     private String packageName;
     private String packageDescription;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public ObjectSettingPackageTranslations() {
    }

	
    public ObjectSettingPackageTranslations(ObjectSettingPackageTranslationsId id, ObjectSettingPackages objectSettingPackages, String packageName) {
        this.id = id;
        this.objectSettingPackages = objectSettingPackages;
        this.packageName = packageName;
    }
    public ObjectSettingPackageTranslations(ObjectSettingPackageTranslationsId id, ObjectSettingPackages objectSettingPackages, String packageName, String packageDescription, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.objectSettingPackages = objectSettingPackages;
       this.packageName = packageName;
       this.packageDescription = packageDescription;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public ObjectSettingPackageTranslationsId getId() {
        return this.id;
    }
    
    public void setId(ObjectSettingPackageTranslationsId id) {
        this.id = id;
    }
    public ObjectSettingPackages getObjectSettingPackages() {
        return this.objectSettingPackages;
    }
    
    public void setObjectSettingPackages(ObjectSettingPackages objectSettingPackages) {
        this.objectSettingPackages = objectSettingPackages;
    }
    public String getPackageName() {
        return this.packageName;
    }
    
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    public String getPackageDescription() {
        return this.packageDescription;
    }
    
    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
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

