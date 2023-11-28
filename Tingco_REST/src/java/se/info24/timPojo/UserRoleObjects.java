package se.info24.timPojo;
// Generated Dec 5, 2013 3:09:03 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * UserRoleObjects generated by hbm2java
 */
public class UserRoleObjects  implements java.io.Serializable {


     private UserRoleObjectsId id;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public UserRoleObjects() {
    }

	
    public UserRoleObjects(UserRoleObjectsId id) {
        this.id = id;
    }
    public UserRoleObjects(UserRoleObjectsId id, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public UserRoleObjectsId getId() {
        return this.id;
    }
    
    public void setId(UserRoleObjectsId id) {
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


