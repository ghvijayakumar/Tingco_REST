package se.info24.pojo;
// Generated Dec 5, 2013 11:21:41 AM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.util.Date;

/**
 * AgreementRoleTypeTranslations generated by hbm2java
 */
public class AgreementRoleTypeTranslations  implements java.io.Serializable {


     private AgreementRoleTypeTranslationsId id;
     private AgreementRolesTypes agreementRolesTypes;
     private String roleTypeName;
     private String roleTypeDescription;
     private Date createdDate;
     private Date updatedDate;

    public AgreementRoleTypeTranslations() {
    }

	
    public AgreementRoleTypeTranslations(AgreementRoleTypeTranslationsId id, AgreementRolesTypes agreementRolesTypes, String roleTypeName) {
        this.id = id;
        this.agreementRolesTypes = agreementRolesTypes;
        this.roleTypeName = roleTypeName;
    }
    public AgreementRoleTypeTranslations(AgreementRoleTypeTranslationsId id, AgreementRolesTypes agreementRolesTypes, String roleTypeName, String roleTypeDescription, Date createdDate, Date updatedDate) {
       this.id = id;
       this.agreementRolesTypes = agreementRolesTypes;
       this.roleTypeName = roleTypeName;
       this.roleTypeDescription = roleTypeDescription;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public AgreementRoleTypeTranslationsId getId() {
        return this.id;
    }
    
    public void setId(AgreementRoleTypeTranslationsId id) {
        this.id = id;
    }
    public AgreementRolesTypes getAgreementRolesTypes() {
        return this.agreementRolesTypes;
    }
    
    public void setAgreementRolesTypes(AgreementRolesTypes agreementRolesTypes) {
        this.agreementRolesTypes = agreementRolesTypes;
    }
    public String getRoleTypeName() {
        return this.roleTypeName;
    }
    
    public void setRoleTypeName(String roleTypeName) {
        this.roleTypeName = roleTypeName;
    }
    public String getRoleTypeDescription() {
        return this.roleTypeDescription;
    }
    
    public void setRoleTypeDescription(String roleTypeDescription) {
        this.roleTypeDescription = roleTypeDescription;
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

