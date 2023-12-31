package se.info24.pojo;
// Generated Dec 6, 2010 12:53:09 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DeviceTypes generated by hbm2java
 */
public class DeviceTypes implements java.io.Serializable {

    private String deviceTypeId;
    private DeviceManufacturers deviceManufacturers;
    private String deviceTypeName;
    private String deviceTypeDescription;
    private String userId;
    private Date createdDate;
    private Date updatedDate;
//     private Set biosInformations = new HashSet(0);
    private Set deviceDetailsTemplates = new HashSet(0);
    private Set devices = new HashSet(0);
    private Set deviceTypeDataitemses = new HashSet(0);
    private Set deviceSettingsPackageses = new HashSet(0);
    private Set deviceTypeServiceses = new HashSet(0);
    private Set deviceTypeContentAttributeses = new HashSet(0);
    private Set deviceTypeDefaultCategories = new HashSet(0);
    private Set deviceTypeBillingCategorieses = new HashSet(0);
    private Set deviceTypesInServiceses = new HashSet(0);
    private Set deviceTypesInGroupses = new HashSet(0);
    private Set deviceTypeChannelses = new HashSet(0);
    private Set deviceTypeCommandses = new HashSet(0);
    private Set deviceTypeMeasurementTypeses = new HashSet(0);

    public DeviceTypes() {
    }

    public DeviceTypes(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public DeviceTypes(String deviceTypeId, DeviceManufacturers deviceManufacturers, String deviceTypeName) {
        this.deviceTypeId = deviceTypeId;
        // this.deviceManufacturers = deviceManufacturers;
        this.deviceTypeName = deviceTypeName;
    }
//    public DeviceTypes(String deviceTypeId, DeviceManufacturers deviceManufacturers, String deviceTypeName, String deviceTypeDescription, String userId, Date createdDate, Date updatedDate, Set biosInformations, Set deviceDetailsTemplates, Set devices, Set deviceTypeDataitemses, Set deviceSettingsPackageses, Set deviceTypeServiceses, Set deviceTypeContentAttributeses, Set deviceTypeDefaultCategories, Set deviceTypeBillingCategorieses, Set deviceTypesInServiceses) {
//        this.deviceTypeId = deviceTypeId;
//        this.deviceManufacturers = deviceManufacturers;
//        this.deviceTypeName = deviceTypeName;
//        this.deviceTypeDescription = deviceTypeDescription;
//        this.userId = userId;
//        this.createdDate = createdDate;
//        this.updatedDate = updatedDate;
//        this.biosInformations = biosInformations;
//        this.deviceDetailsTemplates = deviceDetailsTemplates;
//        this.devices = devices;
//        this.deviceTypeDataitemses = deviceTypeDataitemses;
//        this.deviceSettingsPackageses = deviceSettingsPackageses;
//        this.deviceTypeServiceses = deviceTypeServiceses;
//        this.deviceTypeContentAttributeses = deviceTypeContentAttributeses;
//        this.deviceTypeDefaultCategories = deviceTypeDefaultCategories;
//        this.deviceTypeBillingCategorieses = deviceTypeBillingCategorieses;
//        this.deviceTypesInServiceses = deviceTypesInServiceses;
//    }

    public String getDeviceTypeId() {
        return this.deviceTypeId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public DeviceManufacturers getDeviceManufacturers() {
        return this.deviceManufacturers;
    }

    public void setDeviceManufacturers(DeviceManufacturers deviceManufacturers) {
        this.deviceManufacturers = deviceManufacturers;
    }

    public String getDeviceTypeName() {
        return this.deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public String getDeviceTypeDescription() {
        return this.deviceTypeDescription;
    }

    public void setDeviceTypeDescription(String deviceTypeDescription) {
        this.deviceTypeDescription = deviceTypeDescription;
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
//    public Set getBiosInformations() {
//        return this.biosInformations;
//    }
//
//    public void setBiosInformations(Set biosInformations) {
//        this.biosInformations = biosInformations;
//    }

    public Set getDeviceDetailsTemplates() {
        return this.deviceDetailsTemplates;
    }

    public void setDeviceDetailsTemplates(Set deviceDetailsTemplates) {
        this.deviceDetailsTemplates = deviceDetailsTemplates;
    }

    public Set getDevices() {
        return this.devices;
    }

    public void setDevices(Set devices) {
        this.devices = devices;
    }

    public Set getDeviceTypeDataitemses() {
        return this.deviceTypeDataitemses;
    }

    public void setDeviceTypeDataitemses(Set deviceTypeDataitemses) {
        this.deviceTypeDataitemses = deviceTypeDataitemses;
    }

    public Set getDeviceSettingsPackageses() {
        return this.deviceSettingsPackageses;
    }

    public void setDeviceSettingsPackageses(Set deviceSettingsPackageses) {
        this.deviceSettingsPackageses = deviceSettingsPackageses;
    }

    public Set getDeviceTypeServiceses() {
        return this.deviceTypeServiceses;
    }

    public void setDeviceTypeServiceses(Set deviceTypeServiceses) {
        this.deviceTypeServiceses = deviceTypeServiceses;
    }

    public Set getDeviceTypeContentAttributeses() {
        return this.deviceTypeContentAttributeses;
    }

    public void setDeviceTypeContentAttributeses(Set deviceTypeContentAttributeses) {
        this.deviceTypeContentAttributeses = deviceTypeContentAttributeses;
    }

    public Set getDeviceTypeDefaultCategories() {
        return this.deviceTypeDefaultCategories;
    }

    public void setDeviceTypeDefaultCategories(Set deviceTypeDefaultCategories) {
        this.deviceTypeDefaultCategories = deviceTypeDefaultCategories;
    }

    public Set getDeviceTypeBillingCategorieses() {
        return this.deviceTypeBillingCategorieses;
    }

    public void setDeviceTypeBillingCategorieses(Set deviceTypeBillingCategorieses) {
        this.deviceTypeBillingCategorieses = deviceTypeBillingCategorieses;
    }

    public Set getDeviceTypesInServiceses() {
        return this.deviceTypesInServiceses;
    }

    public void setDeviceTypesInServiceses(Set deviceTypesInServiceses) {
        this.deviceTypesInServiceses = deviceTypesInServiceses;
    }

    public Set getDeviceTypeChannelses() {
        return deviceTypeChannelses;
    }

    public void setDeviceTypeChannelses(Set deviceTypeChannelses) {
        this.deviceTypeChannelses = deviceTypeChannelses;
    }

    public Set getDeviceTypeCommandses() {
        return deviceTypeCommandses;
    }

    public void setDeviceTypeCommandses(Set deviceTypeCommandses) {
        this.deviceTypeCommandses = deviceTypeCommandses;
    }

    public Set getDeviceTypeMeasurementTypeses() {
        return deviceTypeMeasurementTypeses;
    }

    public void setDeviceTypeMeasurementTypeses(Set deviceTypeMeasurementTypeses) {
        this.deviceTypeMeasurementTypeses = deviceTypeMeasurementTypeses;
    }

    public Set getDeviceTypesInGroupses() {
        return deviceTypesInGroupses;
    }

    public void setDeviceTypesInGroupses(Set deviceTypesInGroupses) {
        this.deviceTypesInGroupses = deviceTypesInGroupses;
    }
}


