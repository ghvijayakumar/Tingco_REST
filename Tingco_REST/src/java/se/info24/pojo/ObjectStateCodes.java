package se.info24.pojo;
// Generated Jan 23, 2013 10:58:16 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ObjectStateCodes generated by hbm2java
 */
public class ObjectStateCodes  implements java.io.Serializable {


     private String objectStateCodeId;
     private String objectStateCode;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public ObjectStateCodes() {
    }

	
    public ObjectStateCodes(String objectStateCodeId, String objectStateCode) {
        this.objectStateCodeId = objectStateCodeId;
        this.objectStateCode = objectStateCode;
    }
    public ObjectStateCodes(String objectStateCodeId, String objectStateCode, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.objectStateCodeId = objectStateCodeId;
       this.objectStateCode = objectStateCode;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public String getObjectStateCodeId() {
        return this.objectStateCodeId;
    }
    
    public void setObjectStateCodeId(String objectStateCodeId) {
        this.objectStateCodeId = objectStateCodeId;
    }
    public String getObjectStateCode() {
        return this.objectStateCode;
    }
    
    public void setObjectStateCode(String objectStateCode) {
        this.objectStateCode = objectStateCode;
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


