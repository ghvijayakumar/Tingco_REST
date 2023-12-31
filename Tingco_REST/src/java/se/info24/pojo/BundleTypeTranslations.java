package se.info24.pojo;
// Generated Apr 28, 2014 4:47:22 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * BundleTypeTranslations generated by hbm2java
 */
public class BundleTypeTranslations  implements java.io.Serializable {


     private BundleTypeTranslationsId id;
     private Country country;
     private BundleTypes bundleTypes;
     private String bundleTypeName;
     private String bundleTypeDescription;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public BundleTypeTranslations() {
    }

	
    public BundleTypeTranslations(BundleTypeTranslationsId id, Country country, BundleTypes bundleTypes, String bundleTypeName, String lastUpdatedByUserId) {
        this.id = id;
        this.country = country;
        this.bundleTypes = bundleTypes;
        this.bundleTypeName = bundleTypeName;
        this.lastUpdatedByUserId = lastUpdatedByUserId;
    }
    public BundleTypeTranslations(BundleTypeTranslationsId id, Country country, BundleTypes bundleTypes, String bundleTypeName, String bundleTypeDescription, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.country = country;
       this.bundleTypes = bundleTypes;
       this.bundleTypeName = bundleTypeName;
       this.bundleTypeDescription = bundleTypeDescription;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public BundleTypeTranslationsId getId() {
        return this.id;
    }
    
    public void setId(BundleTypeTranslationsId id) {
        this.id = id;
    }
    public Country getCountry() {
        return this.country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }
    public BundleTypes getBundleTypes() {
        return this.bundleTypes;
    }
    
    public void setBundleTypes(BundleTypes bundleTypes) {
        this.bundleTypes = bundleTypes;
    }
    public String getBundleTypeName() {
        return this.bundleTypeName;
    }
    
    public void setBundleTypeName(String bundleTypeName) {
        this.bundleTypeName = bundleTypeName;
    }
    public String getBundleTypeDescription() {
        return this.bundleTypeDescription;
    }
    
    public void setBundleTypeDescription(String bundleTypeDescription) {
        this.bundleTypeDescription = bundleTypeDescription;
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


