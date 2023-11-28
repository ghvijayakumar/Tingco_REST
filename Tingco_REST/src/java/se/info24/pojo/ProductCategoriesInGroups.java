package se.info24.pojo;
// Generated Jul 13, 2012 12:50:36 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ProductCategoriesInGroups generated by hbm2java
 */
public class ProductCategoriesInGroups  implements java.io.Serializable {


     private ProductCategoriesInGroupsId id;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public ProductCategoriesInGroups() {
    }

	
    public ProductCategoriesInGroups(ProductCategoriesInGroupsId id, String lastUpdatedByUserId) {
        this.id = id;
        this.lastUpdatedByUserId = lastUpdatedByUserId;
    }
    public ProductCategoriesInGroups(ProductCategoriesInGroupsId id, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public ProductCategoriesInGroupsId getId() {
        return this.id;
    }
    
    public void setId(ProductCategoriesInGroupsId id) {
        this.id = id;
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

