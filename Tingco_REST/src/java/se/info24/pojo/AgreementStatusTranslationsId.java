package se.info24.pojo;
// Generated Oct 9, 2013 2:32:09 PM by Hibernate Tools 3.2.1.GA



/**
 * AgreementStatusTranslationsId generated by hbm2java
 */
public class AgreementStatusTranslationsId  implements java.io.Serializable {


     private String agreementStatusId;
     private int countryId;

    public AgreementStatusTranslationsId() {
    }

    public AgreementStatusTranslationsId(String agreementStatusId, int countryId) {
       this.agreementStatusId = agreementStatusId;
       this.countryId = countryId;
    }
   
    public String getAgreementStatusId() {
        return this.agreementStatusId;
    }
    
    public void setAgreementStatusId(String agreementStatusId) {
        this.agreementStatusId = agreementStatusId;
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
		 if ( !(other instanceof AgreementStatusTranslationsId) ) return false;
		 AgreementStatusTranslationsId castOther = ( AgreementStatusTranslationsId ) other; 
         
		 return ( (this.getAgreementStatusId()==castOther.getAgreementStatusId()) || ( this.getAgreementStatusId()!=null && castOther.getAgreementStatusId()!=null && this.getAgreementStatusId().equals(castOther.getAgreementStatusId()) ) )
 && (this.getCountryId()==castOther.getCountryId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getAgreementStatusId() == null ? 0 : this.getAgreementStatusId().hashCode() );
         result = 37 * result + this.getCountryId();
         return result;
   }   


}

