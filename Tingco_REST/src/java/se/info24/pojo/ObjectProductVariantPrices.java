package se.info24.pojo;
// Generated Nov 20, 2013 4:26:30 PM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ObjectProductVariantPrices generated by hbm2java
 */
public class ObjectProductVariantPrices  implements java.io.Serializable {


     private ObjectProductVariantPricesId id;
     private ProductVariants productVariants;
     private Currency currency;
     private BigDecimal pricePerUnitExclVat;
     private BigDecimal vatpercent;
     private String quantityUnit;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public ObjectProductVariantPrices() {
    }

	
    public ObjectProductVariantPrices(ObjectProductVariantPricesId id, ProductVariants productVariants, Currency currency, BigDecimal pricePerUnitExclVat, BigDecimal vatpercent, String quantityUnit, String lastUpdatedByUserId) {
        this.id = id;
        this.productVariants = productVariants;
        this.currency = currency;
        this.pricePerUnitExclVat = pricePerUnitExclVat;
        this.vatpercent = vatpercent;
        this.quantityUnit = quantityUnit;
        this.lastUpdatedByUserId = lastUpdatedByUserId;
    }
    public ObjectProductVariantPrices(ObjectProductVariantPricesId id, ProductVariants productVariants, Currency currency, BigDecimal pricePerUnitExclVat, BigDecimal vatpercent, String quantityUnit, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.productVariants = productVariants;
       this.currency = currency;
       this.pricePerUnitExclVat = pricePerUnitExclVat;
       this.vatpercent = vatpercent;
       this.quantityUnit = quantityUnit;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public ObjectProductVariantPricesId getId() {
        return this.id;
    }
    
    public void setId(ObjectProductVariantPricesId id) {
        this.id = id;
    }
    public ProductVariants getProductVariants() {
        return this.productVariants;
    }
    
    public void setProductVariants(ProductVariants productVariants) {
        this.productVariants = productVariants;
    }
    public Currency getCurrency() {
        return this.currency;
    }
    
    public void setCurrency(Currency currency) {
        this.currency = currency;
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

