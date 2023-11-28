package se.info24.pojo;
// Generated Oct 17, 2012 5:59:40 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ContactTypesInGroups generated by hbm2java
 */
public class ContactTypesInGroups  implements java.io.Serializable {


     private ContactTypesInGroupsId id;
     private String userId;
     private Date createdDate;
     private Date updatedDate;

    public ContactTypesInGroups() {
    }

	
    public ContactTypesInGroups(ContactTypesInGroupsId id) {
        this.id = id;
    }
    public ContactTypesInGroups(ContactTypesInGroupsId id, String userId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.userId = userId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public ContactTypesInGroupsId getId() {
        return this.id;
    }
    
    public void setId(ContactTypesInGroupsId id) {
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


