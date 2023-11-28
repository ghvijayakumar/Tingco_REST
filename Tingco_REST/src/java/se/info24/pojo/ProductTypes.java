package se.info24.pojo;
// Generated Sep 4, 2012 12:18:29 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ProductTypes generated by hbm2java
 */
public class ProductTypes implements java.io.Serializable {

    private String productTypeId;
    private String productTypeName;
    private String productTypeDescription;
    private String userId;
    private Date createdDate;
    private Date updatedDate;
    private Set productses = new HashSet(0);

    public ProductTypes() {
    }

    public ProductTypes(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public ProductTypes(String productTypeId, String productTypeName) {
        this.productTypeId = productTypeId;
        this.productTypeName = productTypeName;
    }

    public ProductTypes(String productTypeId, String productTypeName, String productTypeDescription, String userId, Date createdDate, Date updatedDate, Set productses) {
        this.productTypeId = productTypeId;
        this.productTypeName = productTypeName;
        this.productTypeDescription = productTypeDescription;
        this.userId = userId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.productses = productses;
    }

    public String getProductTypeId() {
        return this.productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return this.productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getProductTypeDescription() {
        return this.productTypeDescription;
    }

    public void setProductTypeDescription(String productTypeDescription) {
        this.productTypeDescription = productTypeDescription;
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

    public Set getProductses() {
        return this.productses;
    }

    public void setProductses(Set productses) {
        this.productses = productses;
    }
}

