package se.info24.pojo;
// Generated Jul 5, 2013 12:26:18 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ContactTypes generated by hbm2java
 */
public class ContactTypes  implements java.io.Serializable {


     private String contactTypeId;
     private String userId;
     private Date createdDate;
     private Date updatedDate;

    public ContactTypes() {
    }

	
    public ContactTypes(String contactTypeId) {
        this.contactTypeId = contactTypeId;
    }
    public ContactTypes(String contactTypeId, String userId, Date createdDate, Date updatedDate) {
       this.contactTypeId = contactTypeId;
       this.userId = userId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public String getContactTypeId() {
        return this.contactTypeId;
    }
    
    public void setContactTypeId(String contactTypeId) {
        this.contactTypeId = contactTypeId;
    }
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
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


