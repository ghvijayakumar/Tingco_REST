package se.info24.ismOperationsPojo;
// Generated Sep 30, 2014 10:49:31 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ContentItemCommands generated by hbm2java
 */
public class ContentItemCommands  implements java.io.Serializable {


     private String contentItemCommandId;
     private String groupId;
     private String contentItemId;
     private String commandId;
     private String commandName;
     private String commandButtonText;
     private String commandDescription;
     private Integer isEnabled;
     private Integer isPublicCommand;
     private Integer isPinCodeRequired;
     private Integer isSendEmail;
     private Integer isSendSms;
     private Integer isCreateSupportCase;
     private Integer isControlDevice;
     private Integer isLogInWorklog;
     private Integer isSendEmailToDeviceContacts;
     private Integer isSendSmstoDeviceContacts;
     private Integer isSendEvent;
     private Integer isLinkToTim;
     private Date activeFromDate;
     private Date activeToDate;
     private String pinCode;
     private String description;
     private Integer displayOrder;
     private String deviceId;
     private String deviceDataItemId;
     private String deviceDataItemValue;
     private String deviceTypeCommandId;
     private String sendEmailTo;
     private String sendSmsto;
     private String supportOrganizationId;
     private String eventTypeId;
     private String linkToPageUrl;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public ContentItemCommands() {
    }

	
    public ContentItemCommands(String contentItemCommandId, String groupId, String contentItemId, String commandName, String commandButtonText) {
        this.contentItemCommandId = contentItemCommandId;
        this.groupId = groupId;
        this.contentItemId = contentItemId;
        this.commandName = commandName;
        this.commandButtonText = commandButtonText;
    }
    public ContentItemCommands(String contentItemCommandId, String groupId, String contentItemId, String commandId, String commandName, String commandButtonText, String commandDescription, Integer isEnabled, Integer isPublicCommand, Integer isPinCodeRequired, Integer isSendEmail, Integer isSendSms, Integer isCreateSupportCase, Integer isControlDevice, Integer isLogInWorklog, Integer isSendEmailToDeviceContacts, Integer isSendSmstoDeviceContacts, Integer isSendEvent, Integer isLinkToTim, Date activeFromDate, Date activeToDate, String pinCode, String description, Integer displayOrder, String deviceId, String deviceDataItemId, String deviceDataItemValue, String deviceTypeCommandId, String sendEmailTo, String sendSmsto, String supportOrganizationId, String eventTypeId, String linkToPageUrl, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.contentItemCommandId = contentItemCommandId;
       this.groupId = groupId;
       this.contentItemId = contentItemId;
       this.commandId = commandId;
       this.commandName = commandName;
       this.commandButtonText = commandButtonText;
       this.commandDescription = commandDescription;
       this.isEnabled = isEnabled;
       this.isPublicCommand = isPublicCommand;
       this.isPinCodeRequired = isPinCodeRequired;
       this.isSendEmail = isSendEmail;
       this.isSendSms = isSendSms;
       this.isCreateSupportCase = isCreateSupportCase;
       this.isControlDevice = isControlDevice;
       this.isLogInWorklog = isLogInWorklog;
       this.isSendEmailToDeviceContacts = isSendEmailToDeviceContacts;
       this.isSendSmstoDeviceContacts = isSendSmstoDeviceContacts;
       this.isSendEvent = isSendEvent;
       this.isLinkToTim = isLinkToTim;
       this.activeFromDate = activeFromDate;
       this.activeToDate = activeToDate;
       this.pinCode = pinCode;
       this.description = description;
       this.displayOrder = displayOrder;
       this.deviceId = deviceId;
       this.deviceDataItemId = deviceDataItemId;
       this.deviceDataItemValue = deviceDataItemValue;
       this.deviceTypeCommandId = deviceTypeCommandId;
       this.sendEmailTo = sendEmailTo;
       this.sendSmsto = sendSmsto;
       this.supportOrganizationId = supportOrganizationId;
       this.eventTypeId = eventTypeId;
       this.linkToPageUrl = linkToPageUrl;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public String getContentItemCommandId() {
        return this.contentItemCommandId;
    }
    
    public void setContentItemCommandId(String contentItemCommandId) {
        this.contentItemCommandId = contentItemCommandId;
    }
    public String getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public String getContentItemId() {
        return this.contentItemId;
    }
    
    public void setContentItemId(String contentItemId) {
        this.contentItemId = contentItemId;
    }
    public String getCommandId() {
        return this.commandId;
    }
    
    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }
    public String getCommandName() {
        return this.commandName;
    }
    
    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }
    public String getCommandButtonText() {
        return this.commandButtonText;
    }
    
    public void setCommandButtonText(String commandButtonText) {
        this.commandButtonText = commandButtonText;
    }
    public String getCommandDescription() {
        return this.commandDescription;
    }
    
    public void setCommandDescription(String commandDescription) {
        this.commandDescription = commandDescription;
    }
    public Integer getIsEnabled() {
        return this.isEnabled;
    }
    
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }
    public Integer getIsPublicCommand() {
        return this.isPublicCommand;
    }
    
    public void setIsPublicCommand(Integer isPublicCommand) {
        this.isPublicCommand = isPublicCommand;
    }
    public Integer getIsPinCodeRequired() {
        return this.isPinCodeRequired;
    }
    
    public void setIsPinCodeRequired(Integer isPinCodeRequired) {
        this.isPinCodeRequired = isPinCodeRequired;
    }
    public Integer getIsSendEmail() {
        return this.isSendEmail;
    }
    
    public void setIsSendEmail(Integer isSendEmail) {
        this.isSendEmail = isSendEmail;
    }
    public Integer getIsSendSms() {
        return this.isSendSms;
    }
    
    public void setIsSendSms(Integer isSendSms) {
        this.isSendSms = isSendSms;
    }
    public Integer getIsCreateSupportCase() {
        return this.isCreateSupportCase;
    }
    
    public void setIsCreateSupportCase(Integer isCreateSupportCase) {
        this.isCreateSupportCase = isCreateSupportCase;
    }
    public Integer getIsControlDevice() {
        return this.isControlDevice;
    }
    
    public void setIsControlDevice(Integer isControlDevice) {
        this.isControlDevice = isControlDevice;
    }
    public Integer getIsLogInWorklog() {
        return this.isLogInWorklog;
    }
    
    public void setIsLogInWorklog(Integer isLogInWorklog) {
        this.isLogInWorklog = isLogInWorklog;
    }
    public Integer getIsSendEmailToDeviceContacts() {
        return this.isSendEmailToDeviceContacts;
    }
    
    public void setIsSendEmailToDeviceContacts(Integer isSendEmailToDeviceContacts) {
        this.isSendEmailToDeviceContacts = isSendEmailToDeviceContacts;
    }
    public Integer getIsSendSmstoDeviceContacts() {
        return this.isSendSmstoDeviceContacts;
    }
    
    public void setIsSendSmstoDeviceContacts(Integer isSendSmstoDeviceContacts) {
        this.isSendSmstoDeviceContacts = isSendSmstoDeviceContacts;
    }
    public Integer getIsSendEvent() {
        return this.isSendEvent;
    }
    
    public void setIsSendEvent(Integer isSendEvent) {
        this.isSendEvent = isSendEvent;
    }
    public Integer getIsLinkToTim() {
        return this.isLinkToTim;
    }
    
    public void setIsLinkToTim(Integer isLinkToTim) {
        this.isLinkToTim = isLinkToTim;
    }
    public Date getActiveFromDate() {
        return this.activeFromDate;
    }
    
    public void setActiveFromDate(Date activeFromDate) {
        this.activeFromDate = activeFromDate;
    }
    public Date getActiveToDate() {
        return this.activeToDate;
    }
    
    public void setActiveToDate(Date activeToDate) {
        this.activeToDate = activeToDate;
    }
    public String getPinCode() {
        return this.pinCode;
    }
    
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getDisplayOrder() {
        return this.displayOrder;
    }
    
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
    public String getDeviceId() {
        return this.deviceId;
    }
    
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public String getDeviceDataItemId() {
        return this.deviceDataItemId;
    }
    
    public void setDeviceDataItemId(String deviceDataItemId) {
        this.deviceDataItemId = deviceDataItemId;
    }
    public String getDeviceDataItemValue() {
        return this.deviceDataItemValue;
    }
    
    public void setDeviceDataItemValue(String deviceDataItemValue) {
        this.deviceDataItemValue = deviceDataItemValue;
    }
    public String getDeviceTypeCommandId() {
        return this.deviceTypeCommandId;
    }
    
    public void setDeviceTypeCommandId(String deviceTypeCommandId) {
        this.deviceTypeCommandId = deviceTypeCommandId;
    }
    public String getSendEmailTo() {
        return this.sendEmailTo;
    }
    
    public void setSendEmailTo(String sendEmailTo) {
        this.sendEmailTo = sendEmailTo;
    }
    public String getSendSmsto() {
        return this.sendSmsto;
    }
    
    public void setSendSmsto(String sendSmsto) {
        this.sendSmsto = sendSmsto;
    }
    public String getSupportOrganizationId() {
        return this.supportOrganizationId;
    }
    
    public void setSupportOrganizationId(String supportOrganizationId) {
        this.supportOrganizationId = supportOrganizationId;
    }
    public String getEventTypeId() {
        return this.eventTypeId;
    }
    
    public void setEventTypeId(String eventTypeId) {
        this.eventTypeId = eventTypeId;
    }
    public String getLinkToPageUrl() {
        return this.linkToPageUrl;
    }
    
    public void setLinkToPageUrl(String linkToPageUrl) {
        this.linkToPageUrl = linkToPageUrl;
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




}

