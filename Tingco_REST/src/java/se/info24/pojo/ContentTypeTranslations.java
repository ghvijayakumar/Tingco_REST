package se.info24.pojo;
// Generated Feb 23, 2011 12:47:44 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ContentTypeTranslations generated by hbm2java
 */
public class ContentTypeTranslations  implements java.io.Serializable {


     private ContentTypeTranslationsId id;
     private Country country;
     private ContentTypes contentTypes;
     private String contentTypeName;
     private String contentTypeDescription;
     private String userId;
     private Date createdDate;
     private Date updatedDate;

    public ContentTypeTranslations() {
    }

	
    public ContentTypeTranslations(ContentTypeTranslationsId id, Country country, ContentTypes contentTypes) {
        this.id = id;
        this.country = country;
        this.contentTypes = contentTypes;
    }
    public ContentTypeTranslations(ContentTypeTranslationsId id, Country country, ContentTypes contentTypes, String contentTypeName, String contentTypeDescription, String userId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.country = country;
       this.contentTypes = contentTypes;
       this.contentTypeName = contentTypeName;
       this.contentTypeDescription = contentTypeDescription;
       this.userId = userId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public ContentTypeTranslationsId getId() {
        return this.id;
    }
    
    public void setId(ContentTypeTranslationsId id) {
        this.id = id;
    }
    public Country getCountry() {
        return this.country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }
    public ContentTypes getContentTypes() {
        return this.contentTypes;
    }
    
    public void setContentTypes(ContentTypes contentTypes) {
        this.contentTypes = contentTypes;
    }
    public String getContentTypeName() {
        return this.contentTypeName;
    }
    
    public void setContentTypeName(String contentTypeName) {
        this.contentTypeName = contentTypeName;
    }
    public String getContentTypeDescription() {
        return this.contentTypeDescription;
    }
    
    public void setContentTypeDescription(String contentTypeDescription) {
        this.contentTypeDescription = contentTypeDescription;
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


