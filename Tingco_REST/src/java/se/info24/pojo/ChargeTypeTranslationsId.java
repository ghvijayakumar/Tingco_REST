package se.info24.pojo;
// Generated Jun 26, 2012 3:24:36 PM by Hibernate Tools 3.2.1.GA



/**
 * ChargeTypeTranslationsId generated by hbm2java
 */
public class ChargeTypeTranslationsId  implements java.io.Serializable {


     private String chargeTypeId;
     private int countryId;

    public ChargeTypeTranslationsId() {
    }

    public ChargeTypeTranslationsId(String chargeTypeId, int countryId) {
       this.chargeTypeId = chargeTypeId;
       this.countryId = countryId;
    }
   
    public String getChargeTypeId() {
        return this.chargeTypeId;
    }
    
    public void setChargeTypeId(String chargeTypeId) {
        this.chargeTypeId = chargeTypeId;
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
		 if ( !(other instanceof ChargeTypeTranslationsId) ) return false;
		 ChargeTypeTranslationsId castOther = ( ChargeTypeTranslationsId ) other; 
         
		 return ( (this.getChargeTypeId()==castOther.getChargeTypeId()) || ( this.getChargeTypeId()!=null && castOther.getChargeTypeId()!=null && this.getChargeTypeId().equals(castOther.getChargeTypeId()) ) )
 && (this.getCountryId()==castOther.getCountryId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getChargeTypeId() == null ? 0 : this.getChargeTypeId().hashCode() );
         result = 37 * result + this.getCountryId();
         return result;
   }   


}


