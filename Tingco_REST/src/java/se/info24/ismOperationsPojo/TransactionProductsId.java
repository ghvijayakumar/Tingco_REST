package se.info24.ismOperationsPojo;
// Generated Dec 26, 2012 5:25:15 PM by Hibernate Tools 3.2.1.GA



/**
 * TransactionProductsId generated by hbm2java
 */
public class TransactionProductsId  implements java.io.Serializable {


     private String transactionId;
     private String productVariantId;

    public TransactionProductsId() {
    }

    public TransactionProductsId(String transactionId, String productVariantId) {
       this.transactionId = transactionId;
       this.productVariantId = productVariantId;
    }
   
    public String getTransactionId() {
        return this.transactionId;
    }
    
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public String getProductVariantId() {
        return this.productVariantId;
    }
    
    public void setProductVariantId(String productVariantId) {
        this.productVariantId = productVariantId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TransactionProductsId) ) return false;
		 TransactionProductsId castOther = ( TransactionProductsId ) other; 
         
		 return ( (this.getTransactionId()==castOther.getTransactionId()) || ( this.getTransactionId()!=null && castOther.getTransactionId()!=null && this.getTransactionId().equals(castOther.getTransactionId()) ) )
 && ( (this.getProductVariantId()==castOther.getProductVariantId()) || ( this.getProductVariantId()!=null && castOther.getProductVariantId()!=null && this.getProductVariantId().equals(castOther.getProductVariantId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTransactionId() == null ? 0 : this.getTransactionId().hashCode() );
         result = 37 * result + ( getProductVariantId() == null ? 0 : this.getProductVariantId().hashCode() );
         return result;
   }   


}


