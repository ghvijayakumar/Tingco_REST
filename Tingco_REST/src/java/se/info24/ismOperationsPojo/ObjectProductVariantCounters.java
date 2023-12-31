package se.info24.ismOperationsPojo;
// Generated Nov 20, 2013 4:26:30 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;

/**
 * ObjectProductVariantCounters generated by hbm2java
 */
public class ObjectProductVariantCounters implements java.io.Serializable {

    private String objectId;
    private String productVariantId;
    private int countSinceLastUpdate;
    private int countTotal;
    private String lastUpdatedByUserId;
    private Date createdDate;
    private Date updatedDate;
    private int isDeleted;

    public ObjectProductVariantCounters() {
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getCountSinceLastUpdate() {
        return this.countSinceLastUpdate;
    }

    public void setCountSinceLastUpdate(int countSinceLastUpdate) {
        this.countSinceLastUpdate = countSinceLastUpdate;
    }

    public int getCountTotal() {
        return this.countTotal;
    }

    public void setCountTotal(int countTotal) {
        this.countTotal = countTotal;
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

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getProductVariantId() {
        return productVariantId;
    }

    public void setProductVariantId(String productVariantId) {
        this.productVariantId = productVariantId;
    }
}


