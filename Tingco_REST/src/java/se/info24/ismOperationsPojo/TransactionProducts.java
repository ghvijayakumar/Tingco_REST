package se.info24.ismOperationsPojo;
// Generated Dec 26, 2012 5:25:15 PM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * TransactionProducts generated by hbm2java
 */
public class TransactionProducts  implements java.io.Serializable {


     private TransactionProductsId id;
     private String productVariantSku;
     private String productVariantName;
     private String type;
     private String chargeType;
     private String transactionType;
     private Integer ppu;
     private BigDecimal vat;
     private Integer quantity;
     private Integer bayNumber;
     private String quantityUnit;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updateDate;
     private Long amountInclVat;
     private Long amountExclVat;
     private Long amountVat;

    public TransactionProducts() {
    }

	
    public TransactionProducts(TransactionProductsId id) {
        this.id = id;
    }
    public TransactionProducts(TransactionProductsId id, String productVariantSku, String productVariantName, String type, String chargeType, String transactionType, Integer ppu, BigDecimal vat, Integer quantity, Integer bayNumber, String quantityUnit, String lastUpdatedByUserId, Date createdDate, Date updateDate, Long amountInclVat, Long amountExclVat, Long amountVat) {
       this.id = id;
       this.productVariantSku = productVariantSku;
       this.productVariantName = productVariantName;
       this.type = type;
       this.chargeType = chargeType;
       this.transactionType = transactionType;
       this.ppu = ppu;
       this.vat = vat;
       this.quantity = quantity;
       this.bayNumber = bayNumber;
       this.quantityUnit = quantityUnit;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updateDate = updateDate;
       this.amountInclVat = amountInclVat;
       this.amountExclVat = amountExclVat;
       this.amountVat = amountVat;
    }

    public Long getAmountExclVat() {
        return amountExclVat;
    }

    public void setAmountExclVat(Long amountExclVat) {
        this.amountExclVat = amountExclVat;
    }

    public Long getAmountInclVat() {
        return amountInclVat;
    }

    public void setAmountInclVat(Long amountInclVat) {
        this.amountInclVat = amountInclVat;
    }

    public Long getAmountVat() {
        return amountVat;
    }

    public void setAmountVat(Long amountVat) {
        this.amountVat = amountVat;
    }

    
    public TransactionProductsId getId() {
        return this.id;
    }
    
    public void setId(TransactionProductsId id) {
        this.id = id;
    }
    public String getProductVariantSku() {
        return this.productVariantSku;
    }
    
    public void setProductVariantSku(String productVariantSku) {
        this.productVariantSku = productVariantSku;
    }
    public String getProductVariantName() {
        return this.productVariantName;
    }
    
    public void setProductVariantName(String productVariantName) {
        this.productVariantName = productVariantName;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public String getChargeType() {
        return this.chargeType;
    }
    
    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }
    public String getTransactionType() {
        return this.transactionType;
    }
    
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public Integer getPpu() {
        return this.ppu;
    }
    
    public void setPpu(Integer ppu) {
        this.ppu = ppu;
    }
    public BigDecimal getVat() {
        return this.vat;
    }
    
    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }
    public Integer getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Integer getBayNumber() {
        return this.bayNumber;
    }
    
    public void setBayNumber(Integer bayNumber) {
        this.bayNumber = bayNumber;
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
    public Date getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }




}


