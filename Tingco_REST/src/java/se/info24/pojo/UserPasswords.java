package se.info24.pojo;
// Generated May 2, 2011 12:21:54 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * UserPasswords generated by hbm2java
 */
public class UserPasswords  implements java.io.Serializable {


     private String id;
     private String userId;
     private String domainId;
     private String password;
     private Date createdDate;

    public UserPasswords() {
    }

	
    public UserPasswords(String id, String userId, String domainId) {
        this.id = id;
        this.userId = userId;
        this.domainId = domainId;
    }
    public UserPasswords(String id, String userId, String domainId, String password, Date createdDate) {
       this.id = id;
       this.userId = userId;
       this.domainId = domainId;
       this.password = password;
       this.createdDate = createdDate;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getDomainId() {
        return this.domainId;
    }
    
    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }




}

