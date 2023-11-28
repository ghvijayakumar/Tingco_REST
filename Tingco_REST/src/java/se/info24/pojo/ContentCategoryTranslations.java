package se.info24.pojo;
// Generated May 18, 2011 3:54:17 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ContentCategoryTranslations generated by hbm2java
 */
public class ContentCategoryTranslations  implements java.io.Serializable {


     private ContentCategoryTranslationsId id;
     private Country country;
     private ContentCategories contentCategories;
     private String contentCategoryName;
     private String contentCategoryDescription;
     private String userId;
     private Date createdDate;
     private Date updatedDate;

    public ContentCategoryTranslations() {
    }

	
    public ContentCategoryTranslations(ContentCategoryTranslationsId id, Country country, ContentCategories contentCategories, String contentCategoryName) {
        this.id = id;
        this.country = country;
        this.contentCategories = contentCategories;
        this.contentCategoryName = contentCategoryName;
    }
    public ContentCategoryTranslations(ContentCategoryTranslationsId id, Country country, ContentCategories contentCategories, String contentCategoryName, String contentCategoryDescription, String userId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.country = country;
       this.contentCategories = contentCategories;
       this.contentCategoryName = contentCategoryName;
       this.contentCategoryDescription = contentCategoryDescription;
       this.userId = userId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public ContentCategoryTranslationsId getId() {
        return this.id;
    }
    
    public void setId(ContentCategoryTranslationsId id) {
        this.id = id;
    }
    public Country getCountry() {
        return this.country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }
    public ContentCategories getContentCategories() {
        return this.contentCategories;
    }
    
    public void setContentCategories(ContentCategories contentCategories) {
        this.contentCategories = contentCategories;
    }
    public String getContentCategoryName() {
        return this.contentCategoryName;
    }
    
    public void setContentCategoryName(String contentCategoryName) {
        this.contentCategoryName = contentCategoryName;
    }
    public String getContentCategoryDescription() {
        return this.contentCategoryDescription;
    }
    
    public void setContentCategoryDescription(String contentCategoryDescription) {
        this.contentCategoryDescription = contentCategoryDescription;
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


