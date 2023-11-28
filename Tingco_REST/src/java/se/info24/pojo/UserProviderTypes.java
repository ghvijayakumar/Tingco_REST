package se.info24.pojo;
// Generated Feb 27, 2013 2:19:10 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * UserProviderTypes generated by hbm2java
 */
public class UserProviderTypes  implements java.io.Serializable {


     private String userId;
     private ProviderTypes providerTypes;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public UserProviderTypes() {
    }

	
    public UserProviderTypes(String userId, ProviderTypes providerTypes) {
        this.userId = userId;
        this.providerTypes = providerTypes;
    }
    public UserProviderTypes(String userId, ProviderTypes providerTypes, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.userId = userId;
       this.providerTypes = providerTypes;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public ProviderTypes getProviderTypes() {
        return this.providerTypes;
    }
    
    public void setProviderTypes(ProviderTypes providerTypes) {
        this.providerTypes = providerTypes;
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

