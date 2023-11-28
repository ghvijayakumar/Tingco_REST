package se.info24.pojo;
// Generated Jan 11, 2011 1:59:16 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * UserRoleMembershipsToActivate generated by hbm2java
 */
public class UserRoleMembershipsToActivate  implements java.io.Serializable {


     private UserRoleMembershipsToActivateId id;
     private UserRoles2 userRoles2;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public UserRoleMembershipsToActivate() {
    }

	
    public UserRoleMembershipsToActivate(UserRoleMembershipsToActivateId id, UserRoles2 userRoles2) {
        this.id = id;
        this.userRoles2 = userRoles2;
    }
    public UserRoleMembershipsToActivate(UserRoleMembershipsToActivateId id, UserRoles2 userRoles2, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.userRoles2 = userRoles2;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public UserRoleMembershipsToActivateId getId() {
        return this.id;
    }
    
    public void setId(UserRoleMembershipsToActivateId id) {
        this.id = id;
    }
    public UserRoles2 getUserRoles2() {
        return this.userRoles2;
    }
    
    public void setUserRoles2(UserRoles2 userRoles2) {
        this.userRoles2 = userRoles2;
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

