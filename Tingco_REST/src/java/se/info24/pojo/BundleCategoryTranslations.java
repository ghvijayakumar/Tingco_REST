package se.info24.pojo;
// Generated Apr 28, 2014 4:47:22 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * BundleCategoryTranslations generated by hbm2java
 */
public class BundleCategoryTranslations  implements java.io.Serializable {


     private BundleCategoryTranslationsId id;
     private BundleCategories bundleCategories;
     private Country country;
     private String bundleCategoryName;
     private String bundleCategoryDescription;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public BundleCategoryTranslations() {
    }

	
    public BundleCategoryTranslations(BundleCategoryTranslationsId id, BundleCategories bundleCategories, Country country, String bundleCategoryName, String lastUpdatedByUserId) {
        this.id = id;
        this.bundleCategories = bundleCategories;
        this.country = country;
        this.bundleCategoryName = bundleCategoryName;
        this.lastUpdatedByUserId = lastUpdatedByUserId;
    }
    public BundleCategoryTranslations(BundleCategoryTranslationsId id, BundleCategories bundleCategories, Country country, String bundleCategoryName, String bundleCategoryDescription, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.bundleCategories = bundleCategories;
       this.country = country;
       this.bundleCategoryName = bundleCategoryName;
       this.bundleCategoryDescription = bundleCategoryDescription;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public BundleCategoryTranslationsId getId() {
        return this.id;
    }
    
    public void setId(BundleCategoryTranslationsId id) {
        this.id = id;
    }
    public BundleCategories getBundleCategories() {
        return this.bundleCategories;
    }
    
    public void setBundleCategories(BundleCategories bundleCategories) {
        this.bundleCategories = bundleCategories;
    }
    public Country getCountry() {
        return this.country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }
    public String getBundleCategoryName() {
        return this.bundleCategoryName;
    }
    
    public void setBundleCategoryName(String bundleCategoryName) {
        this.bundleCategoryName = bundleCategoryName;
    }
    public String getBundleCategoryDescription() {
        return this.bundleCategoryDescription;
    }
    
    public void setBundleCategoryDescription(String bundleCategoryDescription) {
        this.bundleCategoryDescription = bundleCategoryDescription;
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


