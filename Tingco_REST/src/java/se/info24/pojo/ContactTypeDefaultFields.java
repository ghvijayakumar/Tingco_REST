package se.info24.pojo;
// Generated Jul 5, 2013 12:26:18 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ContactTypeDefaultFields generated by hbm2java
 */
public class ContactTypeDefaultFields  implements java.io.Serializable {


     private ContactTypeDefaultFieldsId id;
     private String userId;
     private Date createdDate;
     private Date updatedDate;

    public ContactTypeDefaultFields() {
    }

	
    public ContactTypeDefaultFields(ContactTypeDefaultFieldsId id) {
        this.id = id;
    }
    public ContactTypeDefaultFields(ContactTypeDefaultFieldsId id, String userId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.userId = userId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public ContactTypeDefaultFieldsId getId() {
        return this.id;
    }
    
    public void setId(ContactTypeDefaultFieldsId id) {
        this.id = id;
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


