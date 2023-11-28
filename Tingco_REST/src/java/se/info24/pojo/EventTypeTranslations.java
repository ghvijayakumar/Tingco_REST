package se.info24.pojo;
// Generated Nov 28, 2012 11:17:04 AM by Hibernate Tools 3.2.1.GA

import java.io.Serializable;
import java.util.Date;

/**
 * EventTypeTranslations generated by hbm2java
 */
public class EventTypeTranslations implements java.io.Serializable {

    private EventTypeTranslationsId id;
    private String eventTypeName;
    private String eventTypeDescription;
    private String userId;
    private Date createdDate;
    private Date updatedDate;
    private EventTypes eventTypes;

    public EventTypeTranslations() {
    }

    public EventTypeTranslations(EventTypeTranslationsId id, String eventTypeName) {
        this.id = id;
        this.eventTypeName = eventTypeName;
    }

    public EventTypeTranslations(EventTypeTranslationsId id, String eventTypeName, String eventTypeDescription, String userId, Date createdDate, Date updatedDate, EventTypes eventTypes) {
        this.id = id;
        this.eventTypeName = eventTypeName;
        this.eventTypes = eventTypes;
        this.eventTypeDescription = eventTypeDescription;
        this.userId = userId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public EventTypeTranslationsId getId() {
        return this.id;
    }

    public void setId(EventTypeTranslationsId id) {
        this.id = id;
    }

    public String getEventTypeName() {
        return this.eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    public String getEventTypeDescription() {
        return this.eventTypeDescription;
    }

    public void setEventTypeDescription(String eventTypeDescription) {
        this.eventTypeDescription = eventTypeDescription;
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

    public EventTypes getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(EventTypes eventTypes) {
        this.eventTypes = eventTypes;
    }
}


