package se.info24.pojo;
// Generated Sep 18, 2012 3:08:02 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ProductVariantContentCategories generated by hbm2java
 */
public class ProductVariantContentCategories  implements java.io.Serializable {


     private ProductVariantContentCategoriesId id;
     private String userId;
     private Date createdDate;
     private Date updatedDate;

    public ProductVariantContentCategories() {
    }

	
    public ProductVariantContentCategories(ProductVariantContentCategoriesId id) {
        this.id = id;
    }
    public ProductVariantContentCategories(ProductVariantContentCategoriesId id, String userId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.userId = userId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public ProductVariantContentCategoriesId getId() {
        return this.id;
    }
    
    public void setId(ProductVariantContentCategoriesId id) {
        this.id = id;
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


