package se.info24.pojo;
// Generated Mar 12, 2013 9:52:10 AM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.util.Date;

/**
 * UserProviderTypeReferences generated by hbm2java
 */
public class UserProviderTypeReferences implements java.io.Serializable {


     private UserProviderTypeReferencesId id;
     //private Users2 users2;
//     private ProviderTypes providerTypes;
     private String userProviderReference1;
     private String userProviderReference2;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public UserProviderTypeReferences() {
    }

	
    public UserProviderTypeReferences(UserProviderTypeReferencesId id, Users2 users2, ProviderTypes providerTypes, String userProviderReference1) {
        this.id = id;
        //this.users2 = users2;
//        this.providerTypes = providerTypes;
        this.userProviderReference1 = userProviderReference1;
    }
    public UserProviderTypeReferences(UserProviderTypeReferencesId id, Users2 users2, ProviderTypes providerTypes, String userProviderReference1, String userProviderReference2, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       //this.users2 = users2;
//       this.providerTypes = providerTypes;
       this.userProviderReference1 = userProviderReference1;
       this.userProviderReference2 = userProviderReference2;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public UserProviderTypeReferencesId getId() {
        return this.id;
    }
    
    public void setId(UserProviderTypeReferencesId id) {
        this.id = id;
    }
//    public Users2 getUsers2() {
//        return this.users2;
//    }
//
//    public void setUsers2(Users2 users2) {
//        this.users2 = users2;
//    }
//    public ProviderTypes getProviderTypes() {
//        return this.providerTypes;
//    }
//
//    public void setProviderTypes(ProviderTypes providerTypes) {
//        this.providerTypes = providerTypes;
//    }
    public String getUserProviderReference1() {
        return this.userProviderReference1;
    }
    
    public void setUserProviderReference1(String userProviderReference1) {
        this.userProviderReference1 = userProviderReference1;
    }
    public String getUserProviderReference2() {
        return this.userProviderReference2;
    }
    
    public void setUserProviderReference2(String userProviderReference2) {
        this.userProviderReference2 = userProviderReference2;
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


