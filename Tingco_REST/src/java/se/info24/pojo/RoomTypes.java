package se.info24.pojo;
// Generated Aug 21, 2014 12:56:43 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * RoomTypes generated by hbm2java
 */
public class RoomTypes  implements java.io.Serializable {


     private String roomTypeId;
     private String objectCode;
     private String iconUrl;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;
     private Set roomses = new HashSet(0);
     private Set roomTypeTranslationses = new HashSet(0);

    public RoomTypes() {
    }

    public RoomTypes(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

	
    public RoomTypes(String roomTypeId, String objectCode, String lastUpdatedByUserId) {
        this.roomTypeId = roomTypeId;
        this.objectCode = objectCode;
        this.lastUpdatedByUserId = lastUpdatedByUserId;
    }
    public RoomTypes(String roomTypeId, String objectCode, String iconUrl, String lastUpdatedByUserId, Date createdDate, Date updatedDate, Set roomses, Set roomTypeTranslationses) {
       this.roomTypeId = roomTypeId;
       this.objectCode = objectCode;
       this.iconUrl = iconUrl;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
       this.roomses = roomses;
       this.roomTypeTranslationses = roomTypeTranslationses;
    }
   
    public String getRoomTypeId() {
        return this.roomTypeId;
    }
    
    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
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
    public Set getRoomses() {
        return this.roomses;
    }
    
    public void setRoomses(Set roomses) {
        this.roomses = roomses;
    }
    public Set getRoomTypeTranslationses() {
        return this.roomTypeTranslationses;
    }
    
    public void setRoomTypeTranslationses(Set roomTypeTranslationses) {
        this.roomTypeTranslationses = roomTypeTranslationses;
    }




}


