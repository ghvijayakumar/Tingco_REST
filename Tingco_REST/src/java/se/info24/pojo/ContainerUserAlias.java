package se.info24.pojo;
// Generated Jun 9, 2014 12:42:53 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ContainerUserAlias generated by hbm2java
 */
public class ContainerUserAlias  implements java.io.Serializable {


     private ContainerUserAliasId id;
     private Containers containers;
     private String userAlias;
     private Date checkInDate;
     private Date checkinInDateDevice;
     private String checkInDeviceId;
     private String checkinDeviceName;
     private String checkinDeviceGroupId;
     private String checkinDeviceTypeId;
     private String checkinDeviceAgreementId;
     private String checkInServiceId;
     private String visitingHost;
     private String visitingHostUserId;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public ContainerUserAlias() {
    }


    public ContainerUserAlias(ContainerUserAliasId id, Containers containers) {
        this.id = id;
        this.containers = containers;
    }
    public ContainerUserAlias(ContainerUserAliasId id, Containers containers, String userAlias, Date checkInDate, Date checkinInDateDevice, String checkInDeviceId, String checkinDeviceName, String checkinDeviceGroupId, String checkinDeviceTypeId, String checkinDeviceAgreementId, String checkInServiceId, String visitingHost, String visitingHostUserId, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.id = id;
       this.containers = containers;
       this.userAlias = userAlias;
       this.checkInDate = checkInDate;
       this.checkinInDateDevice = checkinInDateDevice;
       this.checkInDeviceId = checkInDeviceId;
       this.checkinDeviceName = checkinDeviceName;
       this.checkinDeviceGroupId = checkinDeviceGroupId;
       this.checkinDeviceTypeId = checkinDeviceTypeId;
       this.checkinDeviceAgreementId = checkinDeviceAgreementId;
       this.checkInServiceId = checkInServiceId;
       this.visitingHost = visitingHost;
       this.visitingHostUserId = visitingHostUserId;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }

    public ContainerUserAliasId getId() {
        return this.id;
    }

    public void setId(ContainerUserAliasId id) {
        this.id = id;
    }
    public Containers getContainers() {
        return this.containers;
    }

    public void setContainers(Containers containers) {
        this.containers = containers;
    }
    public String getUserAlias() {
        return this.userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }
    public Date getCheckInDate() {
        return this.checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }
    public Date getCheckinInDateDevice() {
        return this.checkinInDateDevice;
    }

    public void setCheckinInDateDevice(Date checkinInDateDevice) {
        this.checkinInDateDevice = checkinInDateDevice;
    }
    public String getCheckInDeviceId() {
        return this.checkInDeviceId;
    }

    public void setCheckInDeviceId(String checkInDeviceId) {
        this.checkInDeviceId = checkInDeviceId;
    }
    public String getCheckinDeviceName() {
        return this.checkinDeviceName;
    }

    public void setCheckinDeviceName(String checkinDeviceName) {
        this.checkinDeviceName = checkinDeviceName;
    }
    public String getCheckinDeviceGroupId() {
        return this.checkinDeviceGroupId;
    }

    public void setCheckinDeviceGroupId(String checkinDeviceGroupId) {
        this.checkinDeviceGroupId = checkinDeviceGroupId;
    }
    public String getCheckinDeviceTypeId() {
        return this.checkinDeviceTypeId;
    }

    public void setCheckinDeviceTypeId(String checkinDeviceTypeId) {
        this.checkinDeviceTypeId = checkinDeviceTypeId;
    }
    public String getCheckinDeviceAgreementId() {
        return this.checkinDeviceAgreementId;
    }

    public void setCheckinDeviceAgreementId(String checkinDeviceAgreementId) {
        this.checkinDeviceAgreementId = checkinDeviceAgreementId;
    }
    public String getCheckInServiceId() {
        return this.checkInServiceId;
    }

    public void setCheckInServiceId(String checkInServiceId) {
        this.checkInServiceId = checkInServiceId;
    }
    public String getVisitingHost() {
        return this.visitingHost;
    }

    public void setVisitingHost(String visitingHost) {
        this.visitingHost = visitingHost;
    }
    public String getVisitingHostUserId() {
        return this.visitingHostUserId;
    }

    public void setVisitingHostUserId(String visitingHostUserId) {
        this.visitingHostUserId = visitingHostUserId;
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


