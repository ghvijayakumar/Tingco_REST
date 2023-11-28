package se.info24.pojo;
// Generated Nov 28, 2012 11:17:04 AM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * EventTypes generated by hbm2java
 */
public class EventTypes implements java.io.Serializable {

    private String eventTypeId;
    private String userId;
    private Date createdDate;
    private Date updatedDate;
    private Set eventTypesInGroups = new HashSet(0);
    private Set eventTypesInTypes = new HashSet(0);
    private Set eventTypeTranslations = new HashSet(0);

    public EventTypes() {
    }

    public EventTypes(String eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public EventTypes(String eventTypeId, String userId, Date createdDate, Date updatedDate, Set eventTypesInGroups, Set eventTypesInTypes, Set eventTypeTranslations) {
        this.eventTypeId = eventTypeId;
        this.userId = userId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.eventTypesInGroups = eventTypesInGroups;
        this.eventTypesInTypes = eventTypesInTypes;
        this.eventTypeTranslations = eventTypeTranslations;
    }

    public String getEventTypeId() {
        return this.eventTypeId;
    }

    public void setEventTypeId(String eventTypeId) {
        this.eventTypeId = eventTypeId;
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

    public Set getEventTypesInGroups() {
        return eventTypesInGroups;
    }

    public void setEventTypesInGroups(Set eventTypesInGroups) {
        this.eventTypesInGroups = eventTypesInGroups;
    }

    public Set getEventTypeTranslations() {
        return eventTypeTranslations;
    }

    public void setEventTypeTranslations(Set eventTypeTranslations) {
        this.eventTypeTranslations = eventTypeTranslations;
    }

    public Set getEventTypesInTypes() {
        return eventTypesInTypes;
    }

    public void setEventTypesInTypes(Set eventTypesInTypes) {
        this.eventTypesInTypes = eventTypesInTypes;
    }
}


