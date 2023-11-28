package se.info24.pojo;
// Generated Jul 8, 2014 4:03:07 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * BillingCategoryTranslations generated by hbm2java
 */
public class BillingCategoryTranslations  implements java.io.Serializable {


     private BillingCategoryTranslationsId id;
     private Country country;
     private BillingCategories billingCategories;
     private String categoryName;
     private String categoryDescription;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public BillingCategoryTranslations() {
    }

	
    public BillingCategoryTranslations(BillingCategoryTranslationsId id, Country country, BillingCategories billingCategories, String categoryName, String lastUpdatedByUserId) {
        this.id = id;
        this.country = country;
        this.billingCategories = billingCategories;
        this.categoryName = categoryName;
        this.lastUpdatedByUserId = lastUpdatedByUserId;
    }
    public BillingCategoryTranslations(BillingCategoryTranslationsId id, Country country, BillingCategories billingCategories, String categoryName, String categoryDescription, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.country = country;
       this.billingCategories = billingCategories;
       this.categoryName = categoryName;
       this.categoryDescription = categoryDescription;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public BillingCategoryTranslationsId getId() {
        return this.id;
    }
    
    public void setId(BillingCategoryTranslationsId id) {
        this.id = id;
    }
    public Country getCountry() {
        return this.country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }
    public BillingCategories getBillingCategories() {
        return this.billingCategories;
    }
    
    public void setBillingCategories(BillingCategories billingCategories) {
        this.billingCategories = billingCategories;
    }
    public String getCategoryName() {
        return this.categoryName;
    }
    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getCategoryDescription() {
        return this.categoryDescription;
    }
    
    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
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

