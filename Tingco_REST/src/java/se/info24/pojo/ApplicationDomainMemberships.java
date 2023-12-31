package se.info24.pojo;
// Generated May 2, 2011 10:43:33 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ApplicationDomainMemberships generated by hbm2java
 */
public class ApplicationDomainMemberships  implements java.io.Serializable {


     private ApplicationDomainMembershipsId id;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public ApplicationDomainMemberships() {
    }

	
    public ApplicationDomainMemberships(ApplicationDomainMembershipsId id) {
        this.id = id;
    }
    public ApplicationDomainMemberships(ApplicationDomainMembershipsId id, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public ApplicationDomainMembershipsId getId() {
        return this.id;
    }
    
    public void setId(ApplicationDomainMembershipsId id) {
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


