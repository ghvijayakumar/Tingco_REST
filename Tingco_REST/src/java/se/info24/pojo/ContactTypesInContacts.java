package se.info24.pojo;
// Generated Jul 5, 2013 12:26:18 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ContactTypesInContacts generated by hbm2java
 */
public class ContactTypesInContacts implements java.io.Serializable {

    private String contactTypeInCoId;
    private String contactId;
    private String contactTypeId;
    private String userId;
    private Date createdDate;
    private Date updatedDate;
    private Set contactTypeDetailses = new HashSet(0);

    public ContactTypesInContacts() {
    }

    public ContactTypesInContacts(String contactTypeInCoId) {
        this.contactTypeInCoId = contactTypeInCoId;
    }

    public ContactTypesInContacts(String contactTypeInCoId, String contactId, String contactTypeId) {
        this.contactTypeInCoId = contactTypeInCoId;
        this.contactId = contactId;
        this.contactTypeId = contactTypeId;
    }

    public ContactTypesInContacts(String contactTypeInCoId, String contactId, String contactTypeId, String userId, Date createdDate, Date updatedDate, Set contactTypeDetailses) {
        this.contactTypeInCoId = contactTypeInCoId;
        this.contactId = contactId;
        this.contactTypeId = contactTypeId;
        this.userId = userId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.contactTypeDetailses = contactTypeDetailses;
    }

    public String getContactTypeInCoId() {
        return this.contactTypeInCoId;
    }

    public void setContactTypeInCoId(String contactTypeInCoId) {
        this.contactTypeInCoId = contactTypeInCoId;
    }

    public String getContactId() {
        return this.contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getContactTypeId() {
        return this.contactTypeId;
    }

    public void setContactTypeId(String contactTypeId) {
        this.contactTypeId = contactTypeId;
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

    public Set getcontactTypeDetailses() {
        return this.contactTypeDetailses;
    }

    public void setcontactTypeDetailses(Set contactTypeDetailses) {
        this.contactTypeDetailses = contactTypeDetailses;
    }
}


