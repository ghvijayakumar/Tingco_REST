package se.info24.ismOperationsPojo;
// Generated Jun 16, 2014 3:27:50 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ObjectLog generated by hbm2java
 */
public class ObjectLog  implements java.io.Serializable {

     private String objectId;
     private String message;
     private String messageCode;
     private Date createdDate;

    public ObjectLog() {
    }

	
    public ObjectLog(String objectId, String message) {
        this.objectId = objectId;
        this.message = message;
    }
    public ObjectLog(String objectId, String message, String messageCode, Date createdDate) {
       this.objectId = objectId;
       this.message = message;
       this.messageCode = messageCode;
       this.createdDate = createdDate;
    }
   
    public String getObjectId() {
        return this.objectId;
    }
    
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessageCode() {
        return this.messageCode;
    }
    
    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }




}


