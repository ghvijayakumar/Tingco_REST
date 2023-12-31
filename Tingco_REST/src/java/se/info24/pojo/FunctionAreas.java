package se.info24.pojo;
// Generated Nov 2, 2011 2:39:53 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * FunctionAreas generated by hbm2java
 */
public class FunctionAreas implements java.io.Serializable {

    private String functionAreaId;
    private String functionAreaParentId;
    private String functionAreaTechName;
    private String lastUpdatedByUserId;
    private Date createdDate;
    private Date updatedDate;
    private Set functionAreaTranslationses = new HashSet(0);
    private Set agreementItemses = new HashSet(0);

    public FunctionAreas() {
    }

    public FunctionAreas(String functionAreaId, String functionAreaTechName) {
        this.functionAreaId = functionAreaId;
        this.functionAreaTechName = functionAreaTechName;
    }

    public FunctionAreas(String functionAreaId, String functionAreaParentId, String functionAreaTechName, String lastUpdatedByUserId, Date createdDate, Date updatedDate, Set functionAreaTranslationses, Set agreementItemses) {
        this.functionAreaId = functionAreaId;
        this.functionAreaParentId = functionAreaParentId;
        this.functionAreaTechName = functionAreaTechName;
        this.lastUpdatedByUserId = lastUpdatedByUserId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.functionAreaTranslationses = functionAreaTranslationses;
        this.agreementItemses = agreementItemses;
    }

    public String getFunctionAreaId() {
        return this.functionAreaId;
    }

    public void setFunctionAreaId(String functionAreaId) {
        this.functionAreaId = functionAreaId;
    }

    public String getFunctionAreaParentId() {
        return this.functionAreaParentId;
    }

    public void setFunctionAreaParentId(String functionAreaParentId) {
        this.functionAreaParentId = functionAreaParentId;
    }

    public String getFunctionAreaTechName() {
        return this.functionAreaTechName;
    }

    public void setFunctionAreaTechName(String functionAreaTechName) {
        this.functionAreaTechName = functionAreaTechName;
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

    public Set getFunctionAreaTranslationses() {
        return this.functionAreaTranslationses;
    }

    public void setFunctionAreaTranslationses(Set functionAreaTranslationses) {
        this.functionAreaTranslationses = functionAreaTranslationses;
    }

    public Set getAgreementItemses() {
        return this.agreementItemses;
    }

    public void setAgreementItemses(Set agreementItemses) {
        this.agreementItemses = agreementItemses;
    }
}


