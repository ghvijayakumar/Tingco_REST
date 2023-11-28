package se.info24.pojo;
// Generated Aug 13, 2012 2:28:58 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AgreementRolesTypes generated by hbm2java
 */
public class AgreementRolesTypes implements java.io.Serializable {

    private String agreementRoleTypeId;
    private String objectCode;
    private String lastUpdatedByUserId;
    private Date createdDate;
    private Date updatedDate;
    private Set agreementRoleses = new HashSet(0);
    private Set agreementRoleTypeTranslationses = new HashSet(0);

    public AgreementRolesTypes() {
    }

    public AgreementRolesTypes(String agreementRoleTypeId) {
        this.agreementRoleTypeId = agreementRoleTypeId;
    }

    public AgreementRolesTypes(String agreementRoleTypeId, String objectCode) {
        this.agreementRoleTypeId = agreementRoleTypeId;
        this.objectCode = objectCode;
    }

    public AgreementRolesTypes(String agreementRoleTypeId, String objectCode, String lastUpdatedByUserId, Date createdDate, Date updatedDate, Set agreementRoleses, Set agreementRoleTypeTranslationses) {
        this.agreementRoleTypeId = agreementRoleTypeId;
        this.objectCode = objectCode;
        this.lastUpdatedByUserId = lastUpdatedByUserId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.agreementRoleses = agreementRoleses;
        this.agreementRoleTypeTranslationses = agreementRoleTypeTranslationses;
    }

    public String getAgreementRoleTypeId() {
        return this.agreementRoleTypeId;
    }

    public void setAgreementRoleTypeId(String agreementRoleTypeId) {
        this.agreementRoleTypeId = agreementRoleTypeId;
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

    public Set getAgreementRoleses() {
        return this.agreementRoleses;
    }

    public void setAgreementRoleses(Set agreementRoleses) {
        this.agreementRoleses = agreementRoleses;
    }

    public Set getAgreementRoleTypeTranslationses() {
        return this.agreementRoleTypeTranslationses;
    }

    public void setAgreementRoleTypeTranslationses(Set agreementRoleTypeTranslationses) {
        this.agreementRoleTypeTranslationses = agreementRoleTypeTranslationses;
    }
}


