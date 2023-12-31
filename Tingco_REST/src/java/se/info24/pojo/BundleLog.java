package se.info24.pojo;
// Generated Apr 28, 2014 4:47:22 PM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.util.Date;

/**
 * BundleLog generated by hbm2java
 */
public class BundleLog  implements java.io.Serializable {


     private String bundleId;
     private Bundle bundle;
     private String bundleVersionId;
     private String message;
     private String messageCode;
     private Date createdDate;

    public BundleLog() {
    }

	
    public BundleLog(String bundleId, Bundle bundle, String message) {
        this.bundleId = bundleId;
        this.bundle = bundle;
        this.message = message;
    }
    public BundleLog(String bundleId, Bundle bundle, String bundleVersionId, String message, String messageCode, Date createdDate) {
       this.bundleId = bundleId;
       this.bundle = bundle;
       this.bundleVersionId = bundleVersionId;
       this.message = message;
       this.messageCode = messageCode;
       this.createdDate = createdDate;
    }
   
    public String getBundleId() {
        return this.bundleId;
    }
    
    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }
    public Bundle getBundle() {
        return this.bundle;
    }
    
    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
    public String getBundleVersionId() {
        return this.bundleVersionId;
    }
    
    public void setBundleVersionId(String bundleVersionId) {
        this.bundleVersionId = bundleVersionId;
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


