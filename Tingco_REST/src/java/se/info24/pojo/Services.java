package se.info24.pojo;
// Generated Jul 16, 2012 11:28:09 AM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Services generated by hbm2java
 */
public class Services implements java.io.Serializable {

    private String serviceId;
    private Groups groups;
    private ServiceTypes serviceTypes;
    private String serviceName;
    private String serviceDescription;
    private Integer serviceEnabled;
    private Date activeFromDate;
    private String replicates;
    private String userId;
    private Date createdDate;
    private Date updatedDate;
    private Set servicesVisibleToGroups = new HashSet(0);
    private Set serviceDeviceSubscriptionses = new HashSet(0);
    private Set deviceTypeServiceses = new HashSet(0);
    private Set deviceServiceses = new HashSet(0);
    private Set supportCaseServiceLinkses = new HashSet(0);
    private Set servicesChannelses = new HashSet(0);
    private Set serviceContentCategorieses = new HashSet(0);
    private Set deviceServicesActiveBundleses = new HashSet(0);
    private Set deviceServicesAvailableBundleses = new HashSet(0);
    private Set deviceServicesAllowedBundleses = new HashSet(0);

    public Services() {
    }

    public Services(String serviceId) {
        this.serviceId = serviceId;
    }

    public Services(String serviceId, Groups groups, ServiceTypes serviceTypes, String serviceName) {
        this.serviceId = serviceId;
        this.groups = groups;
        this.serviceTypes = serviceTypes;
        this.serviceName = serviceName;
    }

    public Services(String serviceId, Groups groups, ServiceTypes serviceTypes, String serviceName, String serviceDescription, Integer serviceEnabled, Date activeFromDate, String replicates, String userId, Date createdDate, Date updatedDate, Set servicesVisibleToGroups, Set serviceDeviceSubscriptionses, Set deviceTypeServiceses, Set deviceServiceses, Set supportCaseServiceLinkses, Set servicesChannelses, Set serviceContentCategorieses, Set deviceServicesActiveBundleses, Set deviceServicesAvailableBundleses, Set deviceServicesAllowedBundleses) {
        this.serviceId = serviceId;
        this.groups = groups;
        this.serviceTypes = serviceTypes;
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.serviceEnabled = serviceEnabled;
        this.activeFromDate = activeFromDate;
        this.replicates = replicates;
        this.userId = userId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.servicesVisibleToGroups = servicesVisibleToGroups;
        this.serviceDeviceSubscriptionses = serviceDeviceSubscriptionses;
        this.deviceTypeServiceses = deviceTypeServiceses;
        this.deviceServiceses = deviceServiceses;
        this.supportCaseServiceLinkses = supportCaseServiceLinkses;
        this.servicesChannelses = servicesChannelses;
        this.serviceContentCategorieses = serviceContentCategorieses;
        this.deviceServicesActiveBundleses = deviceServicesActiveBundleses;
        this.deviceServicesAvailableBundleses = deviceServicesAvailableBundleses;
        this.deviceServicesAllowedBundleses = deviceServicesAllowedBundleses;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Groups getGroups() {
        return this.groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public ServiceTypes getServiceTypes() {
        return this.serviceTypes;
    }

    public void setServiceTypes(ServiceTypes serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return this.serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public Integer getServiceEnabled() {
        return this.serviceEnabled;
    }

    public void setServiceEnabled(Integer serviceEnabled) {
        this.serviceEnabled = serviceEnabled;
    }

    public Date getActiveFromDate() {
        return this.activeFromDate;
    }

    public void setActiveFromDate(Date activeFromDate) {
        this.activeFromDate = activeFromDate;
    }

    public String getReplicates() {
        return this.replicates;
    }

    public void setReplicates(String replicates) {
        this.replicates = replicates;
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

    public Set getServicesVisibleToGroups() {
        return this.servicesVisibleToGroups;
    }

    public void setServicesVisibleToGroups(Set servicesVisibleToGroups) {
        this.servicesVisibleToGroups = servicesVisibleToGroups;
    }

    public Set getServiceDeviceSubscriptionses() {
        return this.serviceDeviceSubscriptionses;
    }

    public void setServiceDeviceSubscriptionses(Set serviceDeviceSubscriptionses) {
        this.serviceDeviceSubscriptionses = serviceDeviceSubscriptionses;
    }

    public Set getDeviceTypeServiceses() {
        return this.deviceTypeServiceses;
    }

    public void setDeviceTypeServiceses(Set deviceTypeServiceses) {
        this.deviceTypeServiceses = deviceTypeServiceses;
    }

    public Set getDeviceServiceses() {
        return deviceServiceses;
    }

    public void setDeviceServiceses(Set deviceServiceses) {
        this.deviceServiceses = deviceServiceses;
    }

    public Set getSupportCaseServiceLinkses() {
        return this.supportCaseServiceLinkses;
    }

    public void setSupportCaseServiceLinkses(Set supportCaseServiceLinkses) {
        this.supportCaseServiceLinkses = supportCaseServiceLinkses;
    }

    public Set getServicesChannelses() {
        return this.servicesChannelses;
    }

    public void setServicesChannelses(Set servicesChannelses) {
        this.servicesChannelses = servicesChannelses;
    }

    public Set getServiceContentCategorieses() {
        return this.serviceContentCategorieses;
    }

    public void setServiceContentCategorieses(Set serviceContentCategorieses) {
        this.serviceContentCategorieses = serviceContentCategorieses;
    }

    public Set getDeviceServicesActiveBundleses() {
        return this.deviceServicesActiveBundleses;
    }

    public void setDeviceServicesActiveBundleses(Set deviceServicesActiveBundleses) {
        this.deviceServicesActiveBundleses = deviceServicesActiveBundleses;
    }
    public Set getDeviceServicesAvailableBundleses() {
        return this.deviceServicesAvailableBundleses;
    }

    public void setDeviceServicesAvailableBundleses(Set deviceServicesAvailableBundleses) {
        this.deviceServicesAvailableBundleses = deviceServicesAvailableBundleses;
    }
    public Set getDeviceServicesAllowedBundleses() {
        return this.deviceServicesAllowedBundleses;
    }

    public void setDeviceServicesAllowedBundleses(Set deviceServicesAllowedBundleses) {
        this.deviceServicesAllowedBundleses = deviceServicesAllowedBundleses;
    }
}


