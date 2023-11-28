package se.info24.pojo;
// Generated Sep 4, 2012 12:18:29 PM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.util.Date;

/**
 * ProductAttributes generated by hbm2java
 */
public class ProductAttributes  implements java.io.Serializable {


     private String productAttributeId;
     private Products products;
     private String attributeName;
     private String attributeValue;
     private String userId;
     private Date createdDate;
     private Date updateddate;

    public ProductAttributes() {
    }

	
    public ProductAttributes(String productAttributeId, Products products, String attributeName) {
        this.productAttributeId = productAttributeId;
        this.products = products;
        this.attributeName = attributeName;
    }
    public ProductAttributes(String productAttributeId, Products products, String attributeName, String attributeValue, String userId, Date createdDate, Date updateddate) {
       this.productAttributeId = productAttributeId;
       this.products = products;
       this.attributeName = attributeName;
       this.attributeValue = attributeValue;
       this.userId = userId;
       this.createdDate = createdDate;
       this.updateddate = updateddate;
    }
   
    public String getProductAttributeId() {
        return this.productAttributeId;
    }
    
    public void setProductAttributeId(String productAttributeId) {
        this.productAttributeId = productAttributeId;
    }
    public Products getProducts() {
        return this.products;
    }
    
    public void setProducts(Products products) {
        this.products = products;
    }
    public String getAttributeName() {
        return this.attributeName;
    }
    
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
    public String getAttributeValue() {
        return this.attributeValue;
    }
    
    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
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
    public Date getUpdateddate() {
        return this.updateddate;
    }
    
    public void setUpdateddate(Date updateddate) {
        this.updateddate = updateddate;
    }




}


