package se.info24.pojo;
// Generated May 7, 2013 10:56:51 AM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ContainerTypes generated by hbm2java
 */
public class ContainerTypes implements java.io.Serializable {

    private String containerTypeId;
    private String containerTypeName;
    private String containerTypeDescription;
    private String objectCode;
    private String lastUpdatedByUserId;
    private Date createdDate;
    private Date updatedDate;
    private Set containerses = new HashSet(0);
    private Set deviceTypeDefaultCategories = new HashSet(0);

    public ContainerTypes() {
    }

    public ContainerTypes(String containerTypeId, String containerTypeName, String objectCode) {
        this.containerTypeId = containerTypeId;
        this.containerTypeName = containerTypeName;
        this.objectCode = objectCode;
    }

    public ContainerTypes(String containerTypeId, String containerTypeName, String containerTypeDescription, String objectCode, String lastUpdatedByUserId, Date createdDate, Date updatedDate, Set containerses, Set deviceTypeDefaultCategories) {
        this.containerTypeId = containerTypeId;
        this.containerTypeName = containerTypeName;
        this.containerTypeDescription = containerTypeDescription;
        this.objectCode = objectCode;
        this.lastUpdatedByUserId = lastUpdatedByUserId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.containerses = containerses;
        this.deviceTypeDefaultCategories = deviceTypeDefaultCategories;
    }

    public String getContainerTypeId() {
        return this.containerTypeId;
    }

    public void setContainerTypeId(String containerTypeId) {
        this.containerTypeId = containerTypeId;
    }

    public String getContainerTypeName() {
        return this.containerTypeName;
    }

    public void setContainerTypeName(String containerTypeName) {
        this.containerTypeName = containerTypeName;
    }

    public String getContainerTypeDescription() {
        return this.containerTypeDescription;
    }

    public void setContainerTypeDescription(String containerTypeDescription) {
        this.containerTypeDescription = containerTypeDescription;
    }

    public String getObjectCode() {
        return this.objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
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

    public Set getContainerses() {
        return this.containerses;
    }

    public void setContainerses(Set containerses) {
        this.containerses = containerses;
    }

    public Set getDeviceTypeDefaultCategories() {
        return this.deviceTypeDefaultCategories;
    }

    public void setDeviceTypeDefaultCategories(Set deviceTypeDefaultCategories) {
        this.deviceTypeDefaultCategories = deviceTypeDefaultCategories;
    }
}


