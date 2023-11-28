package se.info24.pojo;
// Generated Aug 21, 2014 12:56:43 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * BuildingTypeTranslations generated by hbm2java
 */
public class BuildingTypeTranslations  implements java.io.Serializable {


     private BuildingTypeTranslationsId id;
     private Country country;
     private BuildingTypes buildingTypes;
     private String buildingTypeName;
     private String buildingTypeDescription;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public BuildingTypeTranslations() {
    }

	
    public BuildingTypeTranslations(BuildingTypeTranslationsId id, Country country, BuildingTypes buildingTypes, String buildingTypeName, String lastUpdatedByUserId) {
        this.id = id;
        this.country = country;
        this.buildingTypes = buildingTypes;
        this.buildingTypeName = buildingTypeName;
        this.lastUpdatedByUserId = lastUpdatedByUserId;
    }
    public BuildingTypeTranslations(BuildingTypeTranslationsId id, Country country, BuildingTypes buildingTypes, String buildingTypeName, String buildingTypeDescription, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.country = country;
       this.buildingTypes = buildingTypes;
       this.buildingTypeName = buildingTypeName;
       this.buildingTypeDescription = buildingTypeDescription;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public BuildingTypeTranslationsId getId() {
        return this.id;
    }
    
    public void setId(BuildingTypeTranslationsId id) {
        this.id = id;
    }
    public Country getCountry() {
        return this.country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }
    public BuildingTypes getBuildingTypes() {
        return this.buildingTypes;
    }
    
    public void setBuildingTypes(BuildingTypes buildingTypes) {
        this.buildingTypes = buildingTypes;
    }
    public String getBuildingTypeName() {
        return this.buildingTypeName;
    }
    
    public void setBuildingTypeName(String buildingTypeName) {
        this.buildingTypeName = buildingTypeName;
    }
    public String getBuildingTypeDescription() {
        return this.buildingTypeDescription;
    }
    
    public void setBuildingTypeDescription(String buildingTypeDescription) {
        this.buildingTypeDescription = buildingTypeDescription;
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

