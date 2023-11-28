package se.info24.pojo;
// Generated Dec 2, 2010 3:59:27 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SystemMessageStatuses generated by hbm2java
 */
public class SystemMessageStatuses  implements java.io.Serializable {


     private String systemMessageStatusId;
     private String statusName;
     private String statusDescription;
     private String statusIconImageUrl;
     private String statusColourCode;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;
     private Set systemMessageses = new HashSet(0);

    public SystemMessageStatuses() {
    }

	
    public SystemMessageStatuses(String systemMessageStatusId, String statusName) {
        this.systemMessageStatusId = systemMessageStatusId;
        this.statusName = statusName;
    }
    public SystemMessageStatuses(String systemMessageStatusId, String statusName, String statusDescription, String statusIconImageUrl, String statusColourCode, String lastUpdatedByUserId, Date createdDate, Date updatedDate, Set systemMessageses) {
       this.systemMessageStatusId = systemMessageStatusId;
       this.statusName = statusName;
       this.statusDescription = statusDescription;
       this.statusIconImageUrl = statusIconImageUrl;
       this.statusColourCode = statusColourCode;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
       this.systemMessageses = systemMessageses;
    }
   
    public String getSystemMessageStatusId() {
        return this.systemMessageStatusId;
    }
    
    public void setSystemMessageStatusId(String systemMessageStatusId) {
        this.systemMessageStatusId = systemMessageStatusId;
    }
    public String getStatusName() {
        return this.statusName;
    }
    
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    public String getStatusDescription() {
        return this.statusDescription;
    }
    
    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
    public String getStatusIconImageUrl() {
        return this.statusIconImageUrl;
    }
    
    public void setStatusIconImageUrl(String statusIconImageUrl) {
        this.statusIconImageUrl = statusIconImageUrl;
    }
    public String getStatusColourCode() {
        return this.statusColourCode;
    }
    
    public void setStatusColourCode(String statusColourCode) {
        this.statusColourCode = statusColourCode;
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
    public Set getSystemMessageses() {
        return this.systemMessageses;
    }
    
    public void setSystemMessageses(Set systemMessageses) {
        this.systemMessageses = systemMessageses;
    }




}


