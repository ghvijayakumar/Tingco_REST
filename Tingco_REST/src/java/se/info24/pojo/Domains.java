package se.info24.pojo;
// Generated May 2, 2011 10:43:33 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Domains generated by hbm2java
 */
public class Domains  implements java.io.Serializable {


     private String domainId;
     private String domainName;
     private String domainDescription;
     private String domainOwnerGroupId;
     private String domainUrl;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;
     private Set users2s = new HashSet(0);

    public Domains() {
    }

	
    public Domains(String domainId, String domainName, String domainOwnerGroupId) {
        this.domainId = domainId;
        this.domainName = domainName;
        this.domainOwnerGroupId = domainOwnerGroupId;
    }
    public Domains(String domainId, String domainName, String domainDescription, String domainOwnerGroupId, String domainUrl, String lastUpdatedByUserId, Date createdDate, Date updatedDate, Set users2s) {
       this.domainId = domainId;
       this.domainName = domainName;
       this.domainDescription = domainDescription;
       this.domainOwnerGroupId = domainOwnerGroupId;
       this.domainUrl = domainUrl;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
       this.users2s = users2s;
    }
   
    public String getDomainId() {
        return this.domainId;
    }
    
    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }
    public String getDomainName() {
        return this.domainName;
    }
    
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
    public String getDomainDescription() {
        return this.domainDescription;
    }
    
    public void setDomainDescription(String domainDescription) {
        this.domainDescription = domainDescription;
    }
    public String getDomainOwnerGroupId() {
        return this.domainOwnerGroupId;
    }
    
    public void setDomainOwnerGroupId(String domainOwnerGroupId) {
        this.domainOwnerGroupId = domainOwnerGroupId;
    }
    public String getDomainUrl() {
        return this.domainUrl;
    }
    
    public void setDomainUrl(String domainUrl) {
        this.domainUrl = domainUrl;
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
    public Set getUsers2s() {
        return this.users2s;
    }
    
    public void setUsers2s(Set users2s) {
        this.users2s = users2s;
    }




}

