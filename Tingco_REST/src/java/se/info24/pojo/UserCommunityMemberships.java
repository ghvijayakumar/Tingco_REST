package se.info24.pojo;
// Generated Dec 2, 2010 3:59:27 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * UserCommunityMemberships generated by hbm2java
 */
public class UserCommunityMemberships  implements java.io.Serializable {


     private UserCommunityMembershipsId id;
     private Integer isAdmin;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public UserCommunityMemberships() {
    }

	
    public UserCommunityMemberships(UserCommunityMembershipsId id) {
        this.id = id;
    }
    public UserCommunityMemberships(UserCommunityMembershipsId id, Integer isAdmin, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.isAdmin = isAdmin;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public UserCommunityMembershipsId getId() {
        return this.id;
    }
    
    public void setId(UserCommunityMembershipsId id) {
        this.id = id;
    }
    public Integer getIsAdmin() {
        return this.isAdmin;
    }
    
    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
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


