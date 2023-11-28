package se.info24.pojo;
// Generated Aug 29, 2012 2:11:04 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Device generated by hbm2java
 */
public class Device implements java.io.Serializable, Comparable<Device> {

    private String deviceId;
    private Agreements agreements;
    private Groups groups;
    private DeviceTypes deviceTypes;
    private DeviceSettingsPackages deviceSettingsPackages;
    private Company company;
    private Addresses addresses;
    private String deviceName;
    private String deviceDescription;
    private String deviceName2;
    private String groupId;
    private String deviceTypeId;
    private Integer deviceEnabled;
    private Integer invoiceDevice;
    private Date activeFromDate;
    private Date installedDate;
    private String userId;
    private Date createdDate;
    private Date updatedDate;
    private String assetId;
    private String shortDescription;
    private Set deviceSettingses = new HashSet(0);
    // private Set biosDeviceMembershipses = new HashSet(0);
    // private Set deviceOperationsMembers = new HashSet(0);
    private Set deviceAgreementMembershipses = new HashSet(0);
//     private Set networkSubscriptionses = new HashSet(0);
//     private Set serviceDeviceSubscriptionses = new HashSet(0);
    private Set deviceDetailses = new HashSet(0);
    private Set serviceDeviceSubscriptionses = new HashSet(0);
    private Set productVariantDeviceses = new HashSet(0);
//    private Set objectFieldData = new HashSet(0);
    private Set containerDevices = new HashSet(0);
    private Set supportCaseDeviceLinkses = new HashSet(0);
    private Set networkSubscriptionses = new HashSet(0);
    private Set deviceCommandScheduleses = new HashSet(0);
    private Set commandParseTargetses = new HashSet(0);
    private Set deviceServiceses = new HashSet(0);
    private Set deviceServicesAllowedBundleses = new HashSet(0);
    private Set deviceServicesAvailableBundleses = new HashSet(0);
    private Set deviceServicesActiveBundleses = new HashSet(0);
    private Set deviceBillingCategorieses = new HashSet(0);
    private Set deviceLinkses = new HashSet(0);
    private Set buildingDeviceses = new HashSet(0);

    public Device() {
    }

    public Device(String deviceId) {
        this.deviceId = deviceId;
    }

    public Device(String deviceId, Groups groups, DeviceTypes deviceTypes, String deviceName, Date activeFromDate) {
        this.deviceId = deviceId;
        this.groups = groups;
        this.deviceTypes = deviceTypes;
        this.deviceName = deviceName;
        this.activeFromDate = activeFromDate;
    }

    public Device(String deviceId, Agreements agreements, Groups groups, DeviceTypes deviceTypes, DeviceSettingsPackages deviceSettingsPackages, Company company, Addresses addresses, String deviceName, String deviceDescription, String deviceName2, Integer deviceEnabled, Integer invoiceDevice, Date activeFromDate, Date installedDate, String userId, Date createdDate, Date updatedDate, String assetId, String shortDescription, Set serviceDeviceSubscriptionses, Set productVariantDeviceses, Set objectFieldData, Set containerDevices, Set supportCaseDeviceLinkses, Set networkSubscriptionses, Set deviceCommandScheduleses, Set commandParseTargetses, Set deviceServiceses, Set deviceServicesAllowedBundleses, Set deviceServicesAvailableBundleses, Set deviceServicesActiveBundleses, Set deviceBillingCategorieses, Set deviceLinkses, Set buildingDeviceses) {
        this.deviceId = deviceId;
        this.agreements = agreements;
        this.groups = groups;
        this.deviceTypes = deviceTypes;
        this.deviceSettingsPackages = deviceSettingsPackages;
        this.company = company;
        this.addresses = addresses;
        this.deviceName = deviceName;
        this.deviceDescription = deviceDescription;
        this.deviceName2 = deviceName2;
        this.deviceEnabled = deviceEnabled;
        this.invoiceDevice = invoiceDevice;
        this.activeFromDate = activeFromDate;
        this.installedDate = installedDate;
        this.userId = userId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.assetId = assetId;
        this.shortDescription = shortDescription;
        this.serviceDeviceSubscriptionses = serviceDeviceSubscriptionses;
        this.productVariantDeviceses = productVariantDeviceses;
//        this.objectFieldData = objectFieldData;
        this.containerDevices = containerDevices;
        this.supportCaseDeviceLinkses = supportCaseDeviceLinkses;
        this.networkSubscriptionses = networkSubscriptionses;
        this.deviceCommandScheduleses = deviceCommandScheduleses;
        this.commandParseTargetses = commandParseTargetses;
        this.deviceServiceses = deviceServiceses;
        this.deviceServicesAllowedBundleses = deviceServicesAllowedBundleses;
        this.deviceServicesAvailableBundleses = deviceServicesAvailableBundleses;
        this.deviceServicesActiveBundleses = deviceServicesActiveBundleses;
        this.deviceBillingCategorieses = deviceBillingCategorieses;
        this.deviceLinkses = deviceLinkses;
        this.buildingDeviceses = buildingDeviceses;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Agreements getAgreements() {
        return this.agreements;
    }

    public void setAgreements(Agreements agreements) {
        this.agreements = agreements;
    }

    public Groups getGroups() {
        return this.groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public DeviceTypes getDeviceTypes() {
        return this.deviceTypes;
    }

    public void setDeviceTypes(DeviceTypes deviceTypes) {
        this.deviceTypes = deviceTypes;
    }

    public DeviceSettingsPackages getDeviceSettingsPackages() {
        return this.deviceSettingsPackages;
    }

    public void setDeviceSettingsPackages(DeviceSettingsPackages deviceSettingsPackages) {
        this.deviceSettingsPackages = deviceSettingsPackages;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Addresses getAddresses() {
        return this.addresses;
    }

    public void setAddresses(Addresses addresses) {
        this.addresses = addresses;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceDescription() {
        return this.deviceDescription;
    }

    public void setDeviceDescription(String deviceDescription) {
        this.deviceDescription = deviceDescription;
    }

    public String getDeviceName2() {
        return this.deviceName2;
    }

    public void setDeviceName2(String deviceName2) {
        this.deviceName2 = deviceName2;
    }

    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getDeviceEnabled() {
        return this.deviceEnabled;
    }

    public void setDeviceEnabled(Integer deviceEnabled) {
        this.deviceEnabled = deviceEnabled;
    }

    public Integer getInvoiceDevice() {
        return this.invoiceDevice;
    }

    public void setInvoiceDevice(Integer invoiceDevice) {
        this.invoiceDevice = invoiceDevice;
    }

    public Date getActiveFromDate() {
        return this.activeFromDate;
    }

    public void setActiveFromDate(Date activeFromDate) {
        this.activeFromDate = activeFromDate;
    }

    public Date getInstalledDate() {
        return this.installedDate;
    }

    public void setInstalledDate(Date installedDate) {
        this.installedDate = installedDate;
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

    public String getAssetId() {
        return this.assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Set getDeviceSettingses() {
        return this.deviceSettingses;
    }

    public void setDeviceSettingses(Set deviceSettingses) {
        this.deviceSettingses = deviceSettingses;
    }
//    public Set getBiosDeviceMembershipses() {
//        return this.biosDeviceMembershipses;
//    }
//
//    public void setBiosDeviceMembershipses(Set biosDeviceMembershipses) {
//        this.biosDeviceMembershipses = biosDeviceMembershipses;
//    }
//    public Set getDeviceOperationsMembers() {
//        return this.deviceOperationsMembers;
//    }
//
//    public void setDeviceOperationsMembers(Set deviceOperationsMembers) {
//        this.deviceOperationsMembers = deviceOperationsMembers;
//    }

    public Set getDeviceAgreementMembershipses() {
        return this.deviceAgreementMembershipses;
    }

    public void setDeviceAgreementMembershipses(Set deviceAgreementMembershipses) {
        this.deviceAgreementMembershipses = deviceAgreementMembershipses;
    }
//    public Set getNetworkSubscriptionses() {
//        return this.networkSubscriptionses;
//    }
//
//    public void setNetworkSubscriptionses(Set networkSubscriptionses) {
//        this.networkSubscriptionses = networkSubscriptionses;
//    }
//    public Set getServiceDeviceSubscriptionses() {
//        return this.serviceDeviceSubscriptionses;
//    }
//
//    public void setServiceDeviceSubscriptionses(Set serviceDeviceSubscriptionses) {
//        this.serviceDeviceSubscriptionses = serviceDeviceSubscriptionses;
//    }

    public Set getDeviceDetailses() {
        return this.deviceDetailses;
    }

    public void setDeviceDetailses(Set deviceDetailses) {
        this.deviceDetailses = deviceDetailses;
    }

    public Set getServiceDeviceSubscriptionses() {
        return this.serviceDeviceSubscriptionses;
    }

    public void setServiceDeviceSubscriptionses(Set serviceDeviceSubscriptionses) {
        this.serviceDeviceSubscriptionses = serviceDeviceSubscriptionses;
    }

    public Set getproductVariantDeviceses() {
        return this.productVariantDeviceses;
    }

    public void setproductVariantDeviceses(Set productVariantDeviceses) {
        this.productVariantDeviceses = productVariantDeviceses;
    }

//    public Set getObjectFieldData() {
//        return this.objectFieldData;
//    }
//
//    public void setObjectFieldData(Set objectFieldData) {
//        this.objectFieldData = objectFieldData;
//    }
    public Set getContainerDevices() {
        return this.containerDevices;
    }

    public void setContainerDevices(Set containerDevices) {
        this.containerDevices = containerDevices;
    }

    public Set getSupportCaseDeviceLinkses() {
        return this.supportCaseDeviceLinkses;
    }

    public void setSupportCaseDeviceLinkses(Set supportCaseDeviceLinkses) {
        this.supportCaseDeviceLinkses = supportCaseDeviceLinkses;
    }

    public Set getNetworkSubscriptionses() {
        return this.networkSubscriptionses;
    }

    public void setNetworkSubscriptionses(Set networkSubscriptionses) {
        this.networkSubscriptionses = networkSubscriptionses;
    }

    public Set getDeviceCommandScheduleses() {
        return this.deviceCommandScheduleses;
    }

    public void setDeviceCommandScheduleses(Set deviceCommandScheduleses) {
        this.deviceCommandScheduleses = deviceCommandScheduleses;
    }

    public Set getCommandParseTargetses() {
        return this.commandParseTargetses;
    }

    public void setCommandParseTargetses(Set commandParseTargetses) {
        this.commandParseTargetses = commandParseTargetses;
    }

    public Set getDeviceServiceses() {
        return deviceServiceses;
    }

    public void setDeviceServiceses(Set deviceServiceses) {
        this.deviceServiceses = deviceServiceses;
    }

    public Set getProductVariantDeviceses() {
        return productVariantDeviceses;
    }

    public void setProductVariantDeviceses(Set productVariantDeviceses) {
        this.productVariantDeviceses = productVariantDeviceses;
    }

    public Set getDeviceServicesAllowedBundleses() {
        return this.deviceServicesAllowedBundleses;
    }

    public void setDeviceServicesAllowedBundleses(Set deviceServicesAllowedBundleses) {
        this.deviceServicesAllowedBundleses = deviceServicesAllowedBundleses;
    }
    public Set getDeviceServicesAvailableBundleses() {
        return this.deviceServicesAvailableBundleses;
    }

    public void setDeviceServicesAvailableBundleses(Set deviceServicesAvailableBundleses) {
        this.deviceServicesAvailableBundleses = deviceServicesAvailableBundleses;
    }
    public Set getDeviceServicesActiveBundleses() {
        return this.deviceServicesActiveBundleses;
    }

    public void setDeviceServicesActiveBundleses(Set deviceServicesActiveBundleses) {
        this.deviceServicesActiveBundleses = deviceServicesActiveBundleses;
    }

     public Set getDeviceBillingCategorieses() {
        return this.deviceBillingCategorieses;
    }

    public void setDeviceBillingCategorieses(Set deviceBillingCategorieses) {
        this.deviceBillingCategorieses = deviceBillingCategorieses;
    }

    public Set getDeviceLinkses() {
        return this.deviceLinkses;
    }

    public void setDeviceLinkses(Set deviceLinkses) {
        this.deviceLinkses = deviceLinkses;
    }

     public Set getBuildingDeviceses() {
        return this.buildingDeviceses;
    }

    public void setBuildingDeviceses(Set buildingDeviceses) {
        this.buildingDeviceses = buildingDeviceses;
    }

    public int compareTo(Device o) {
        return deviceName.compareToIgnoreCase(o.getDeviceName());
    }
}

