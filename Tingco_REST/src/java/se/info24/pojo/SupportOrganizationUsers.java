package se.info24.pojo;
// Generated Jun 5, 2013 11:23:30 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * SupportOrganizationUsers generated by hbm2java
 */
public class SupportOrganizationUsers  implements java.io.Serializable {


     private SupportOrganizationUsersId id;
     private Integer isManager;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public SupportOrganizationUsers() {
    }

	
    public SupportOrganizationUsers(SupportOrganizationUsersId id, String lastUpdatedByUserId) {
        this.id = id;
        this.lastUpdatedByUserId = lastUpdatedByUserId;
    }
    public SupportOrganizationUsers(SupportOrganizationUsersId id, Integer isManager, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.isManager = isManager;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public SupportOrganizationUsersId getId() {
        return this.id;
    }
    
    public void setId(SupportOrganizationUsersId id) {
        this.id = id;
    }
    public Integer getIsManager() {
        return this.isManager;
    }
    
    public void setIsManager(Integer isManager) {
        this.isManager = isManager;
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


