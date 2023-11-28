package se.info24.pojo;
// Generated Jan 8, 2013 3:53:44 PM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ProductVariantPrices generated by hbm2java
 */
public class ProductVariantPrices  implements java.io.Serializable {


     private ProductVariantPricesId id;
     private ProductVariants productVariants;
     private Country country;
     private BigDecimal pricePerUnitExclVat;
     private BigDecimal vatpercent;
     private String quantityUnit;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public ProductVariantPrices() {
    }

	
    public ProductVariantPrices(ProductVariantPricesId id, ProductVariants productVariants, Country country, BigDecimal pricePerUnitExclVat, BigDecimal vatpercent, String quantityUnit) {
        this.id = id;
        this.productVariants = productVariants;
        this.country = country;
        this.pricePerUnitExclVat = pricePerUnitExclVat;
        this.vatpercent = vatpercent;
        this.quantityUnit = quantityUnit;
    }
    public ProductVariantPrices(ProductVariantPricesId id, ProductVariants productVariants, Country country, BigDecimal pricePerUnitExclVat, BigDecimal vatpercent, String quantityUnit, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.productVariants = productVariants;
       this.country = country;
       this.pricePerUnitExclVat = pricePerUnitExclVat;
       this.vatpercent = vatpercent;
       this.quantityUnit = quantityUnit;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public ProductVariantPricesId getId() {
        return this.id;
    }
    
    public void setId(ProductVariantPricesId id) {
        this.id = id;
    }
    public ProductVariants getProductVariants() {
        return this.productVariants;
    }
    
    public void setProductVariants(ProductVariants productVariants) {
        this.productVariants = productVariants;
    }
    public Country getCountry() {
        return this.country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }
    public BigDecimal getPricePerUnitExclVat() {
        return this.pricePerUnitExclVat;
    }
    
    public void setPricePerUnitExclVat(BigDecimal pricePerUnitExclVat) {
        this.pricePerUnitExclVat = pricePerUnitExclVat;
    }
    public BigDecimal getVatpercent() {
        return this.vatpercent;
    }
    
    public void setVatpercent(BigDecimal vatpercent) {
        this.vatpercent = vatpercent;
    }
    public String getQuantityUnit() {
        return this.quantityUnit;
    }
    
    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
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


