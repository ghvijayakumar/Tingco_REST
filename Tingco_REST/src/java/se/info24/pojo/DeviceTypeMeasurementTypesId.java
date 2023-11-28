package se.info24.pojo;
// Generated Nov 16, 2012 12:12:11 PM by Hibernate Tools 3.2.1.GA



/**
 * DeviceTypeMeasurementTypesId generated by hbm2java
 */
public class DeviceTypeMeasurementTypesId  implements java.io.Serializable {


     private String deviceTypeId;
     private String measurementTypeId;

    public DeviceTypeMeasurementTypesId() {
    }

    public DeviceTypeMeasurementTypesId(String deviceTypeId, String measurementTypeId) {
       this.deviceTypeId = deviceTypeId;
       this.measurementTypeId = measurementTypeId;
    }
   
    public String getDeviceTypeId() {
        return this.deviceTypeId;
    }
    
    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }
    public String getMeasurementTypeId() {
        return this.measurementTypeId;
    }
    
    public void setMeasurementTypeId(String measurementTypeId) {
        this.measurementTypeId = measurementTypeId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DeviceTypeMeasurementTypesId) ) return false;
		 DeviceTypeMeasurementTypesId castOther = ( DeviceTypeMeasurementTypesId ) other; 
         
		 return ( (this.getDeviceTypeId()==castOther.getDeviceTypeId()) || ( this.getDeviceTypeId()!=null && castOther.getDeviceTypeId()!=null && this.getDeviceTypeId().equals(castOther.getDeviceTypeId()) ) )
 && ( (this.getMeasurementTypeId()==castOther.getMeasurementTypeId()) || ( this.getMeasurementTypeId()!=null && castOther.getMeasurementTypeId()!=null && this.getMeasurementTypeId().equals(castOther.getMeasurementTypeId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDeviceTypeId() == null ? 0 : this.getDeviceTypeId().hashCode() );
         result = 37 * result + ( getMeasurementTypeId() == null ? 0 : this.getMeasurementTypeId().hashCode() );
         return result;
   }   


}

