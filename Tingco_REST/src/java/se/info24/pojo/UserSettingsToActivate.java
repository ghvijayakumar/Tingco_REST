package se.info24.pojo;
// Generated Jan 11, 2011 1:59:16 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * UserSettingsToActivate generated by hbm2java
 */
public class UserSettingsToActivate  implements java.io.Serializable {


     private String userSettingsId;
     private String userId;
     private String settingsName;
     private String settingsValue;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public UserSettingsToActivate() {
    }

	
    public UserSettingsToActivate(String userSettingsId, String userId, String settingsName, String settingsValue) {
        this.userSettingsId = userSettingsId;
        this.userId = userId;
        this.settingsName = settingsName;
        this.settingsValue = settingsValue;
    }
    public UserSettingsToActivate(String userSettingsId, String userId, String settingsName, String settingsValue, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.userSettingsId = userSettingsId;
       this.userId = userId;
       this.settingsName = settingsName;
       this.settingsValue = settingsValue;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public String getUserSettingsId() {
        return this.userSettingsId;
    }
    
    public void setUserSettingsId(String userSettingsId) {
        this.userSettingsId = userSettingsId;
    }
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getSettingsName() {
        return this.settingsName;
    }
    
    public void setSettingsName(String settingsName) {
        this.settingsName = settingsName;
    }
    public String getSettingsValue() {
        return this.settingsValue;
    }
    
    public void setSettingsValue(String settingsValue) {
        this.settingsValue = settingsValue;
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


