package se.info24.pojo;
// Generated Oct 30, 2012 12:10:18 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ObjectSettingPackageMemberships generated by hbm2java
 */
public class ObjectSettingPackageMemberships  implements java.io.Serializable {


     private ObjectSettingPackageMembershipsId id;
     private ObjectSettingPackages objectSettingPackages;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public ObjectSettingPackageMemberships() {
    }

	
    public ObjectSettingPackageMemberships(ObjectSettingPackageMembershipsId id, ObjectSettingPackages objectSettingPackages) {
        this.id = id;
        this.objectSettingPackages = objectSettingPackages;
    }
    public ObjectSettingPackageMemberships(ObjectSettingPackageMembershipsId id, ObjectSettingPackages objectSettingPackages, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.objectSettingPackages = objectSettingPackages;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public ObjectSettingPackageMembershipsId getId() {
        return this.id;
    }
    
    public void setId(ObjectSettingPackageMembershipsId id) {
        this.id = id;
    }
    public ObjectSettingPackages getObjectSettingPackages() {
        return this.objectSettingPackages;
    }
    
    public void setObjectSettingPackages(ObjectSettingPackages objectSettingPackages) {
        this.objectSettingPackages = objectSettingPackages;
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


