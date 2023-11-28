package se.info24.pojo;
// Generated Dec 6, 2010 12:53:09 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DeviceDataItems generated by hbm2java
 */
public class DeviceDataItems implements java.io.Serializable {

    private String deviceDataItemId;
    private String deviceDataFieldName;
    private String deviceDataGroupName;
    private String unit;
    private String userId;
    private Date createdDate;
    private Date updatedDate;
     private Set deviceDataItemScaling = new HashSet(0);
    private Set deviceDataitemTranslationses = new HashSet(0);
    private Set deviceTypeDataitemses = new HashSet(0);
    private Set userFavoriteDataItemses = new HashSet(0);
    private Set connectorses = new HashSet(0);

    public DeviceDataItems() {
    }

    public DeviceDataItems(String deviceDataItemId) {
        this.deviceDataItemId = deviceDataItemId;
    }

    public DeviceDataItems(String deviceDataItemId, String deviceDataFieldName, String deviceDataGroupName) {
        this.deviceDataItemId = deviceDataItemId;
        this.deviceDataFieldName = deviceDataFieldName;
        this.deviceDataGroupName = deviceDataGroupName;
    }

    public DeviceDataItems(String deviceDataItemId, String deviceDataFieldName, String deviceDataGroupName, String unit, String userId, Date createdDate, Date updatedDate, Set deviceDataitemTranslationses, Set deviceTypeDataitemses, Set userFavoriteDataItemses, Set connectorses) {
        this.deviceDataItemId = deviceDataItemId;
        this.deviceDataFieldName = deviceDataFieldName;
        this.deviceDataGroupName = deviceDataGroupName;
        this.unit = unit;
        this.userId = userId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deviceDataitemTranslationses = deviceDataitemTranslationses;
        this.deviceTypeDataitemses = deviceTypeDataitemses;
        this.userFavoriteDataItemses = userFavoriteDataItemses;
        this.connectorses = connectorses;
    }

    public Set getDeviceDataItemScaling() {
        return deviceDataItemScaling;
    }

    public void setDeviceDataItemScaling(Set deviceDataItemScaling) {
        this.deviceDataItemScaling = deviceDataItemScaling;
    }

    public String getDeviceDataItemId() {
        return this.deviceDataItemId;
    }

    public void setDeviceDataItemId(String deviceDataItemId) {
        this.deviceDataItemId = deviceDataItemId;
    }

    public String getDeviceDataFieldName() {
        return this.deviceDataFieldName;
    }

    public void setDeviceDataFieldName(String deviceDataFieldName) {
        this.deviceDataFieldName = deviceDataFieldName;
    }

    public String getDeviceDataGroupName() {
        return this.deviceDataGroupName;
    }

    public void setDeviceDataGroupName(String deviceDataGroupName) {
        this.deviceDataGroupName = deviceDataGroupName;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public Set getDeviceDataitemTranslationses() {
        return this.deviceDataitemTranslationses;
    }

    public void setDeviceDataitemTranslationses(Set deviceDataitemTranslationses) {
        this.deviceDataitemTranslationses = deviceDataitemTranslationses;
    }

    public Set getDeviceTypeDataitemses() {
        return this.deviceTypeDataitemses;
    }

    public void setDeviceTypeDataitemses(Set deviceTypeDataitemses) {
        this.deviceTypeDataitemses = deviceTypeDataitemses;
    }

    public Set getUserFavoriteDataItemses() {
        return this.userFavoriteDataItemses;
    }

    public void setUserFavoriteDataItemses(Set userFavoriteDataItemses) {
        this.userFavoriteDataItemses = userFavoriteDataItemses;
    }

    public Set getConnectorses() {
        return this.connectorses;
    }

    public void setConnectorses(Set connectorses) {
        this.connectorses = connectorses;
    }
}

