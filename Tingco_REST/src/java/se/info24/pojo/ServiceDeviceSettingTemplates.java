package se.info24.pojo;
// Generated Oct 30, 2012 12:10:18 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ServiceDeviceSettingTemplates generated by hbm2java
 */
public class ServiceDeviceSettingTemplates  implements java.io.Serializable {


     private ServiceDeviceSettingTemplatesId id;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public ServiceDeviceSettingTemplates() {
    }

	
    public ServiceDeviceSettingTemplates(ServiceDeviceSettingTemplatesId id) {
        this.id = id;
    }
    public ServiceDeviceSettingTemplates(ServiceDeviceSettingTemplatesId id, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public ServiceDeviceSettingTemplatesId getId() {
        return this.id;
    }
    
    public void setId(ServiceDeviceSettingTemplatesId id) {
        this.id = id;
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


