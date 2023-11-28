package se.info24.pojo;
// Generated Jun 26, 2012 3:24:36 PM by Hibernate Tools 3.2.1.GA

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PricePlanItems generated by hbm2java
 */
public class PricePlanItems implements java.io.Serializable {

    private String pricePlanItemId;
    private ValidationExpressions validationExpressions;
    private PricePlanItemTypes pricePlanItemTypes;
    private ValidationDataType validationDataType;
    private String pricePlanItemParentId;
    private String pricePlanVersionId;
    private String itemName;
    private String itemDescription;
    private String chargeTypeId;
    private String validationDataType_1;
    private String validationExpression;
    private String validationValue;
    private BigDecimal itemAmount;
    private BigDecimal itemPercent;
    private Integer isEnabled;
    private String lastUpdatedByUserId;
    private Date createdDate;
    private Date updatedDate;
    private Integer priority;
    private Set pricePlanItemses = new HashSet(0);

    public PricePlanItems() {
    }

    public PricePlanItems(String pricePlanItemId, ValidationExpressions validationExpressions, PricePlanItemTypes pricePlanItemTypes, ValidationDataType validationDataType, String pricePlanVersionId, String itemName, String chargeTypeId, String validationDataType_1, String validationExpression, String validationValue, String lastUpdatedByUserId) {
        this.pricePlanItemId = pricePlanItemId;
        this.validationExpressions = validationExpressions;
        this.pricePlanItemTypes = pricePlanItemTypes;
        this.validationDataType = validationDataType;
        this.pricePlanVersionId = pricePlanVersionId;
        this.itemName = itemName;
        this.chargeTypeId = chargeTypeId;
        this.validationDataType_1 = validationDataType_1;
        this.validationExpression = validationExpression;
        this.validationValue = validationValue;
        this.lastUpdatedByUserId = lastUpdatedByUserId;
    }

    public PricePlanItems(String pricePlanItemId, ValidationExpressions validationExpressions, PricePlanItemTypes pricePlanItemTypes, ValidationDataType validationDataType, String pricePlanItemParentId, String pricePlanVersionId, String itemName, String itemDescription, String chargeTypeId, String validationDataType_1, String validationExpression, String validationValue, BigDecimal itemAmount, BigDecimal itemPercent, Integer isEnabled, String lastUpdatedByUserId, Date createdDate, Date updatedDate) {
        this.pricePlanItemId = pricePlanItemId;
        this.validationExpressions = validationExpressions;
        this.pricePlanItemTypes = pricePlanItemTypes;
        this.validationDataType = validationDataType;
        this.pricePlanItemParentId = pricePlanItemParentId;
        this.pricePlanVersionId = pricePlanVersionId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.chargeTypeId = chargeTypeId;
        this.validationDataType_1 = validationDataType_1;
        this.validationExpression = validationExpression;
        this.validationValue = validationValue;
        this.itemAmount = itemAmount;
        this.itemPercent = itemPercent;
        this.isEnabled = isEnabled;
        this.lastUpdatedByUserId = lastUpdatedByUserId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public String getPricePlanItemId() {
        return this.pricePlanItemId;
    }

    public void setPricePlanItemId(String pricePlanItemId) {
        this.pricePlanItemId = pricePlanItemId;
    }

    public ValidationExpressions getValidationExpressions() {
        return this.validationExpressions;
    }

    public void setValidationExpressions(ValidationExpressions validationExpressions) {
        this.validationExpressions = validationExpressions;
    }

    public PricePlanItemTypes getPricePlanItemTypes() {
        return this.pricePlanItemTypes;
    }

    public void setPricePlanItemTypes(PricePlanItemTypes pricePlanItemTypes) {
        this.pricePlanItemTypes = pricePlanItemTypes;
    }

    public ValidationDataType getValidationDataType() {
        return this.validationDataType;
    }

    public void setValidationDataType(ValidationDataType validationDataType) {
        this.validationDataType = validationDataType;
    }

    public String getPricePlanItemParentId() {
        return this.pricePlanItemParentId;
    }

    public void setPricePlanItemParentId(String pricePlanItemParentId) {
        this.pricePlanItemParentId = pricePlanItemParentId;
    }

    public String getPricePlanVersionId() {
        return this.pricePlanVersionId;
    }

    public void setPricePlanVersionId(String pricePlanVersionId) {
        this.pricePlanVersionId = pricePlanVersionId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return this.itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getChargeTypeId() {
        return this.chargeTypeId;
    }

    public void setChargeTypeId(String chargeTypeId) {
        this.chargeTypeId = chargeTypeId;
    }

    public String getValidationDataType_1() {
        return this.validationDataType_1;
    }

    public void setValidationDataType_1(String validationDataType_1) {
        this.validationDataType_1 = validationDataType_1;
    }

    public String getValidationExpression() {
        return this.validationExpression;
    }

    public void setValidationExpression(String validationExpression) {
        this.validationExpression = validationExpression;
    }

    public String getValidationValue() {
        return this.validationValue;
    }

    public void setValidationValue(String validationValue) {
        this.validationValue = validationValue;
    }

    public BigDecimal getItemAmount() {
        return this.itemAmount;
    }

    public void setItemAmount(BigDecimal itemAmount) {
        this.itemAmount = itemAmount;
    }

    public BigDecimal getItemPercent() {
        return this.itemPercent;
    }

    public void setItemPercent(BigDecimal itemPercent) {
        this.itemPercent = itemPercent;
    }

    public Integer getIsEnabled() {
        return this.isEnabled;
    }

    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Set getPricePlanItemses() {
        return pricePlanItemses;
    }

    public void setPricePlanItemses(Set pricePlanItemses) {
        this.pricePlanItemses = pricePlanItemses;
    }
}

