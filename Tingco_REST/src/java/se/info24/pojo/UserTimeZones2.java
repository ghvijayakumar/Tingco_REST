package se.info24.pojo;
// Generated Jan 4, 2011 12:12:38 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * UserTimeZones2 generated by hbm2java
 */
public class UserTimeZones2  implements java.io.Serializable {


     private String userId;
     private String timeZoneId;
     private Integer useDaylightSaving;
     private Date createdDate;
     private Date updatedDate;

    public UserTimeZones2() {
    }

	
    public UserTimeZones2(String userId, String timeZoneId) {
        this.userId = userId;
        this.timeZoneId = timeZoneId;
    }
    public UserTimeZones2(String userId, String timeZoneId, Integer useDaylightSaving, Date createdDate, Date updatedDate) {
       this.userId = userId;
       this.timeZoneId = timeZoneId;
       this.useDaylightSaving = useDaylightSaving;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getTimeZoneId() {
        return this.timeZoneId;
    }
    
    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }
    public Integer getUseDaylightSaving() {
        return this.useDaylightSaving;
    }
    
    public void setUseDaylightSaving(Integer useDaylightSaving) {
        this.useDaylightSaving = useDaylightSaving;
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

