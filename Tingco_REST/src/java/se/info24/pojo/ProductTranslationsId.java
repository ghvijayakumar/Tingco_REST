package se.info24.pojo;
// Generated Jul 13, 2012 12:50:36 PM by Hibernate Tools 3.2.1.GA



/**
 * ProductTranslationsId generated by hbm2java
 */
public class ProductTranslationsId  implements java.io.Serializable {


     private String productId;
     private int countryId;

    public ProductTranslationsId() {
    }

    public ProductTranslationsId(String productId, int countryId) {
       this.productId = productId;
       this.countryId = countryId;
    }
   
    public String getProductId() {
        return this.productId;
    }
    
    public void setProductId(String productId) {
        this.productId = productId;
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
		 if ( !(other instanceof ProductTranslationsId) ) return false;
		 ProductTranslationsId castOther = ( ProductTranslationsId ) other; 
         
		 return ( (this.getProductId()==castOther.getProductId()) || ( this.getProductId()!=null && castOther.getProductId()!=null && this.getProductId().equals(castOther.getProductId()) ) )
 && (this.getCountryId()==castOther.getCountryId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getProductId() == null ? 0 : this.getProductId().hashCode() );
         result = 37 * result + this.getCountryId();
         return result;
   }   


}


