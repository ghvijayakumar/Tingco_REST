package se.info24.pojo;
// Generated Jan 23, 2013 10:58:16 AM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Fields generated by hbm2java
 */
public class Fields  implements java.io.Serializable {


     private String fieldId;
     private Lists lists;
     private FieldTypes fieldTypes;
     private FieldCategories fieldCategories;
     private String objectCode;
     private String validationRegEx;
     private int length;
     private Integer decimals;
     private String defaultValue;
     private BigDecimal minValue;
     private BigDecimal maxValue;
     private String lastUpdatedByUserId;
     private Date createdDate;
     private Date updatedDate;
     private Set objectFieldDatas = new HashSet(0);
     private Set fieldTranslationses = new HashSet(0);
     private Set objectTypeFieldses = new HashSet(0);

    public Fields() {
    }

	
    public Fields(String fieldId, FieldTypes fieldTypes, FieldCategories fieldCategories, String objectCode, int length) {
        this.fieldId = fieldId;
        this.fieldTypes = fieldTypes;
        this.fieldCategories = fieldCategories;
        this.objectCode = objectCode;
        this.length = length;
    }
    public Fields(String fieldId, Lists lists, FieldTypes fieldTypes, FieldCategories fieldCategories, String objectCode, String validationRegEx, int length, Integer decimals, String defaultValue, BigDecimal minValue, BigDecimal maxValue, String lastUpdatedByUserId, Date createdDate, Date updatedDate, Set objectFieldDatas, Set fieldTranslationses, Set objectTypeFieldses) {
       this.fieldId = fieldId;
       this.lists = lists;
       this.fieldTypes = fieldTypes;
       this.fieldCategories = fieldCategories;
       this.objectCode = objectCode;
       this.validationRegEx = validationRegEx;
       this.length = length;
       this.decimals = decimals;
       this.defaultValue = defaultValue;
       this.minValue = minValue;
       this.maxValue = maxValue;
       this.lastUpdatedByUserId = lastUpdatedByUserId;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
       this.objectFieldDatas = objectFieldDatas;
       this.fieldTranslationses = fieldTranslationses;
       this.objectTypeFieldses = objectTypeFieldses;
    }
   
    public String getFieldId() {
        return this.fieldId;
    }
    
    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }
    public Lists getLists() {
        return this.lists;
    }
    
    public void setLists(Lists lists) {
        this.lists = lists;
    }
    public FieldTypes getFieldTypes() {
        return this.fieldTypes;
    }
    
    public void setFieldTypes(FieldTypes fieldTypes) {
        this.fieldTypes = fieldTypes;
    }
    public FieldCategories getFieldCategories() {
        return this.fieldCategories;
    }
    
    public void setFieldCategories(FieldCategories fieldCategories) {
        this.fieldCategories = fieldCategories;
    }
    public String getObjectCode() {
        return this.objectCode;
    }
    
    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }
    public String getValidationRegEx() {
        return this.validationRegEx;
    }
    
    public void setValidationRegEx(String validationRegEx) {
        this.validationRegEx = validationRegEx;
    }
    public int getLength() {
        return this.length;
    }
    
    public void setLength(int length) {
        this.length = length;
    }
    public Integer getDecimals() {
        return this.decimals;
    }
    
    public void setDecimals(Integer decimals) {
        this.decimals = decimals;
    }
    public String getDefaultValue() {
        return this.defaultValue;
    }
    
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
    public BigDecimal getMinValue() {
        return this.minValue;
    }
    
    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }
    public BigDecimal getMaxValue() {
        return this.maxValue;
    }
    
    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
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
    public Set getObjectFieldDatas() {
        return this.objectFieldDatas;
    }
    
    public void setObjectFieldDatas(Set objectFieldDatas) {
        this.objectFieldDatas = objectFieldDatas;
    }
    public Set getFieldTranslationses() {
        return this.fieldTranslationses;
    }
    
    public void setFieldTranslationses(Set fieldTranslationses) {
        this.fieldTranslationses = fieldTranslationses;
    }
    public Set getObjectTypeFieldses() {
        return this.objectTypeFieldses;
    }
    
    public void setObjectTypeFieldses(Set objectTypeFieldses) {
        this.objectTypeFieldses = objectTypeFieldses;
    }




}


