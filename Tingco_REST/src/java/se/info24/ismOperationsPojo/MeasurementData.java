package se.info24.ismOperationsPojo;
// Generated May 30, 2012 2:45:58 PM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;

/**
 * MeasurementData generated by hbm2java
 */
public class MeasurementData  implements java.io.Serializable {


     private MeasurementDataId id;
//     private String measurementTypeId;
     private Date dataItemTime;
     private String objectId;
     private String groupId;
     private BigDecimal measurementValue;
     private BigDecimal measurementMinValue;
     private BigDecimal measurementMaxValue;
     private BigDecimal measurementStandardDeviation;
     private Short year;
     private Short month;
     private Short day;
     private Short hour;
     private Date createdDate;
     private Date updatedDate;

    public MeasurementData() {
    }

	
    public MeasurementData(MeasurementDataId id, Date dataItemTime, String objectId, BigDecimal measurementValue) {
        this.id = id;
        this.dataItemTime = dataItemTime;
        this.objectId = objectId;
        this.measurementValue = measurementValue;
    }
    public MeasurementData(MeasurementDataId id, Date dataItemTime, String objectId, String groupId, BigDecimal measurementValue, BigDecimal measurementMinValue, BigDecimal measurementMaxValue, BigDecimal measurementStandardDeviation, Short year, Short month, Short day, Short hour, Date createdDate, Date updatedDate) {
       this.id = id;
       this.dataItemTime = dataItemTime;
       this.objectId = objectId;
       this.groupId = groupId;
       this.measurementValue = measurementValue;
       this.measurementMinValue = measurementMinValue;
       this.measurementMaxValue = measurementMaxValue;
       this.measurementStandardDeviation = measurementStandardDeviation;
       this.year = year;
       this.month = month;
       this.day = day;
       this.hour = hour;
       this.createdDate = createdDate;
       this.updatedDate = updatedDate;
    }

//    public MeasurementData(ResultSet rs) throws SQLException {
//       this.measurementTypeId = rs.getString("MeasurementTypeId");
//       this.dataItemTime = rs.getDate("DataItemTime");
//       this.measurementValue = rs.getBigDecimal("MeasurementValue");
//    }
   
    public MeasurementDataId getId() {
        return this.id;
    }

//    public String getMeasurementTypeId() {
//        return measurementTypeId;
//    }
//
//    public void setMeasurementTypeId(String measurementTypeId) {
//        this.measurementTypeId = measurementTypeId;
//    }
    
    public void setId(MeasurementDataId id) {
        this.id = id;
    }
    public Date getDataItemTime() {
        return this.dataItemTime;
    }
    
    public void setDataItemTime(Date dataItemTime) {
        this.dataItemTime = dataItemTime;
    }
    public String getObjectId() {
        return this.objectId;
    }
    
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    public String getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public BigDecimal getMeasurementValue() {
        return this.measurementValue;
    }
    
    public void setMeasurementValue(BigDecimal measurementValue) {
        this.measurementValue = measurementValue;
    }
    public BigDecimal getMeasurementMinValue() {
        return this.measurementMinValue;
    }
    
    public void setMeasurementMinValue(BigDecimal measurementMinValue) {
        this.measurementMinValue = measurementMinValue;
    }
    public BigDecimal getMeasurementMaxValue() {
        return this.measurementMaxValue;
    }
    
    public void setMeasurementMaxValue(BigDecimal measurementMaxValue) {
        this.measurementMaxValue = measurementMaxValue;
    }
    public BigDecimal getMeasurementStandardDeviation() {
        return this.measurementStandardDeviation;
    }
    
    public void setMeasurementStandardDeviation(BigDecimal measurementStandardDeviation) {
        this.measurementStandardDeviation = measurementStandardDeviation;
    }
    public Short getYear() {
        return this.year;
    }
    
    public void setYear(Short year) {
        this.year = year;
    }
    public Short getMonth() {
        return this.month;
    }
    
    public void setMonth(Short month) {
        this.month = month;
    }
    public Short getDay() {
        return this.day;
    }
    
    public void setDay(Short day) {
        this.day = day;
    }
    public Short getHour() {
        return this.hour;
    }
    
    public void setHour(Short hour) {
        this.hour = hour;
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


