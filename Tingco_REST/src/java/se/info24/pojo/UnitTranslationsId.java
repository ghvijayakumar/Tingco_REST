package se.info24.pojo;
// Generated Sep 24, 2013 1:11:02 PM by Hibernate Tools 3.2.1.GA



/**
 * UnitTranslationsId generated by hbm2java
 */
public class UnitTranslationsId  implements java.io.Serializable {


     private String unitId;
     private int countryId;

    public UnitTranslationsId() {
    }

    public UnitTranslationsId(String unitId, int countryId) {
       this.unitId = unitId;
       this.countryId = countryId;
    }
   
    public String getUnitId() {
        return this.unitId;
    }
    
    public void setUnitId(String unitId) {
        this.unitId = unitId;
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
		 if ( !(other instanceof UnitTranslationsId) ) return false;
		 UnitTranslationsId castOther = ( UnitTranslationsId ) other; 
         
		 return ( (this.getUnitId()==castOther.getUnitId()) || ( this.getUnitId()!=null && castOther.getUnitId()!=null && this.getUnitId().equals(castOther.getUnitId()) ) )
 && (this.getCountryId()==castOther.getCountryId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUnitId() == null ? 0 : this.getUnitId().hashCode() );
         result = 37 * result + this.getCountryId();
         return result;
   }   


}


