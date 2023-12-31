package se.info24.pojo;
// Generated Aug 24, 2012 2:26:58 PM by Hibernate Tools 3.2.1.GA



/**
 * DeviceTypeCommandTranslationsId generated by hbm2java
 */
public class DeviceTypeCommandTranslationsId  implements java.io.Serializable {


     private String deviceTypeCommandId;
     private int countryId;

    public DeviceTypeCommandTranslationsId() {
    }

    public DeviceTypeCommandTranslationsId(String deviceTypeCommandId, int countryId) {
       this.deviceTypeCommandId = deviceTypeCommandId;
       this.countryId = countryId;
    }
   
    public String getDeviceTypeCommandId() {
        return this.deviceTypeCommandId;
    }
    
    public void setDeviceTypeCommandId(String deviceTypeCommandId) {
        this.deviceTypeCommandId = deviceTypeCommandId;
    }
    public int getCountryId() {
        return this.countryId;
    }
    
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DeviceTypeCommandTranslationsId) ) return false;
		 DeviceTypeCommandTranslationsId castOther = ( DeviceTypeCommandTranslationsId ) other; 
         
		 return ( (this.getDeviceTypeCommandId()==castOther.getDeviceTypeCommandId()) || ( this.getDeviceTypeCommandId()!=null && castOther.getDeviceTypeCommandId()!=null && this.getDeviceTypeCommandId().equals(castOther.getDeviceTypeCommandId()) ) )
 && (this.getCountryId()==castOther.getCountryId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDeviceTypeCommandId() == null ? 0 : this.getDeviceTypeCommandId().hashCode() );
         result = 37 * result + this.getCountryId();
         return result;
   }   


}


