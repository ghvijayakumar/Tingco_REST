package se.info24.pojo;
// Generated Apr 28, 2014 5:45:13 PM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;

/**
 * LoadBalance generated by hbm2java
 */
public class LoadBalance  implements java.io.Serializable {


     private String loadBalanceId;
     private String loadBalanceName;
     private String loadBalanceDescription;
     private String groupId;
     private BigDecimal maxCurrentTotal;
     private BigDecimal maxCurrentL1;
     private BigDecimal maxCurrentL2;
     private BigDecimal maxCurrentL3;
     private Integer isPhaseL1;
     private Integer isPhaseL2;
     private Integer isPhaseL3;
     private BigDecimal currentTotal;
     private BigDecimal currentL1;
     private BigDecimal currentL2;
     private BigDecimal currentL3;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;

    public LoadBalance() {
    }

	
    public LoadBalance(String loadBalanceId, String loadBalanceName, String groupId, String lastUpdatedByUserId) {
        this.loadBalanceId = loadBalanceId;
        this.loadBalanceName = loadBalanceName;
        this.groupId = groupId;
        this.lastUpdatedByUserId = lastUpdatedByUserId;
    }
    public LoadBalance(String loadBalanceId, String loadBalanceName, String loadBalanceDescription, String groupId, BigDecimal maxCurrentTotal, BigDecimal maxCurrentL1, BigDecimal maxCurrentL2, BigDecimal maxCurrentL3, Integer isPhaseL1, Integer isPhaseL2, Integer isPhaseL3, BigDecimal currentTotal, BigDecimal currentL1, BigDecimal currentL2, BigDecimal currentL3, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
       this.loadBalanceId = loadBalanceId;
       this.loadBalanceName = loadBalanceName;
       this.loadBalanceDescription = loadBalanceDescription;
       this.groupId = groupId;
       this.maxCurrentTotal = maxCurrentTotal;
       this.maxCurrentL1 = maxCurrentL1;
       this.maxCurrentL2 = maxCurrentL2;
       this.maxCurrentL3 = maxCurrentL3;
       this.isPhaseL1 = isPhaseL1;
       this.isPhaseL2 = isPhaseL2;
       this.isPhaseL3 = isPhaseL3;
       this.currentTotal = currentTotal;
       this.currentL1 = currentL1;
       this.currentL2 = currentL2;
       this.currentL3 = currentL3;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }
   
    public String getLoadBalanceId() {
        return this.loadBalanceId;
    }
    
    public void setLoadBalanceId(String loadBalanceId) {
        this.loadBalanceId = loadBalanceId;
    }
    public String getLoadBalanceName() {
        return this.loadBalanceName;
    }
    
    public void setLoadBalanceName(String loadBalanceName) {
        this.loadBalanceName = loadBalanceName;
    }
    public String getLoadBalanceDescription() {
        return this.loadBalanceDescription;
    }
    
    public void setLoadBalanceDescription(String loadBalanceDescription) {
        this.loadBalanceDescription = loadBalanceDescription;
    }
    public String getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public BigDecimal getMaxCurrentTotal() {
        return this.maxCurrentTotal;
    }
    
    public void setMaxCurrentTotal(BigDecimal maxCurrentTotal) {
        this.maxCurrentTotal = maxCurrentTotal;
    }
    public BigDecimal getMaxCurrentL1() {
        return this.maxCurrentL1;
    }
    
    public void setMaxCurrentL1(BigDecimal maxCurrentL1) {
        this.maxCurrentL1 = maxCurrentL1;
    }
    public BigDecimal getMaxCurrentL2() {
        return this.maxCurrentL2;
    }
    
    public void setMaxCurrentL2(BigDecimal maxCurrentL2) {
        this.maxCurrentL2 = maxCurrentL2;
    }
    public BigDecimal getMaxCurrentL3() {
        return this.maxCurrentL3;
    }
    
    public void setMaxCurrentL3(BigDecimal maxCurrentL3) {
        this.maxCurrentL3 = maxCurrentL3;
    }
    public Integer getIsPhaseL1() {
        return this.isPhaseL1;
    }
    
    public void setIsPhaseL1(Integer isPhaseL1) {
        this.isPhaseL1 = isPhaseL1;
    }
    public Integer getIsPhaseL2() {
        return this.isPhaseL2;
    }
    
    public void setIsPhaseL2(Integer isPhaseL2) {
        this.isPhaseL2 = isPhaseL2;
    }
    public Integer getIsPhaseL3() {
        return this.isPhaseL3;
    }
    
    public void setIsPhaseL3(Integer isPhaseL3) {
        this.isPhaseL3 = isPhaseL3;
    }
    public BigDecimal getCurrentTotal() {
        return this.currentTotal;
    }
    
    public void setCurrentTotal(BigDecimal currentTotal) {
        this.currentTotal = currentTotal;
    }
    public BigDecimal getCurrentL1() {
        return this.currentL1;
    }
    
    public void setCurrentL1(BigDecimal currentL1) {
        this.currentL1 = currentL1;
    }
    public BigDecimal getCurrentL2() {
        return this.currentL2;
    }
    
    public void setCurrentL2(BigDecimal currentL2) {
        this.currentL2 = currentL2;
    }
    public BigDecimal getCurrentL3() {
        return this.currentL3;
    }
    
    public void setCurrentL3(BigDecimal currentL3) {
        this.currentL3 = currentL3;
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

