package se.info24.pojo;
// Generated Jul 5, 2013 12:26:18 PM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.util.Date;

/**
 * ContactTypeTranslations generated by hbm2java
 */
public class ContactTypeTranslations  implements java.io.Serializable {


     private ContactTypeTranslationsId id;
     private String contactTypeName;
     private String contactTypeDescription;
     private String userId;
     private Date createdDate;
     private Date updatedDate;

    public ContactTypeTranslations() {
    }

	
    public ContactTypeTranslations(ContactTypeTranslationsId id, String contactTypeName) {
        this.id = id;
        this.contactTypeName = contactTypeName;
    }
    public ContactTypeTranslations(ContactTypeTranslationsId id, String contactTypeName, String contactTypeDescription, String userId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.contactTypeName = contactTypeName;
       this.contactTypeDescription = contactTypeDescription;
       this.userId = userId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public ContactTypeTranslationsId getId() {
        return this.id;
    }
    
    public void setId(ContactTypeTranslationsId id) {
        this.id = id;
    }
    public String getContactTypeName() {
        return this.contactTypeName;
    }
    
    public void setContactTypeName(String contactTypeName) {
        this.contactTypeName = contactTypeName;
    }
    public String getContactTypeDescription() {
        return this.contactTypeDescription;
    }
    
    public void setContactTypeDescription(String contactTypeDescription) {
        this.contactTypeDescription = contactTypeDescription;
    }
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
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


