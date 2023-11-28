/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ravikant
 */
public class ObjectCostCenters implements Serializable{

    private ObjectCostCentersId id;
    private String costCenterId;
    private String lastUpdatedByUserId;
    private Date createdDate;
    private Date updatedDate;
     public ObjectCostCenters(){}
    public ObjectCostCenters(ObjectCostCentersId id, String costCenterId, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
        this.id = id;
        this.costCenterId = costCenterId;
        this.lastUpdatedByUserId = lastUpdatedByUserId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }



    public String getCostCenterId() {
        return costCenterId;
    }

    public void setCostCenterId(String costCenterId) {
        this.costCenterId = costCenterId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public ObjectCostCentersId getId() {
        return id;
    }

    public void setId(ObjectCostCentersId id) {
        this.id = id;
    }

    public String getLastUpdatedByUserId() {
        return lastUpdatedByUserId;
    }

    public void setLastUpdatedByUserId(String lastUpdatedByUserId) {
        this.lastUpdatedByUserId = lastUpdatedByUserId;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }


}
