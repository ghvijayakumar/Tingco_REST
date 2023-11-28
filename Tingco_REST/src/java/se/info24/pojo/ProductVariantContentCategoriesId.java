package se.info24.pojo;
// Generated Sep 18, 2012 3:08:02 PM by Hibernate Tools 3.2.1.GA



/**
 * ProductVariantContentCategoriesId generated by hbm2java
 */
public class ProductVariantContentCategoriesId  implements java.io.Serializable {


     private String productVariantId;
     private String contentCategoryId;

    public ProductVariantContentCategoriesId() {
    }

    public ProductVariantContentCategoriesId(String productVariantId, String contentCategoryId) {
       this.productVariantId = productVariantId;
       this.contentCategoryId = contentCategoryId;
    }
   
    public String getProductVariantId() {
        return this.productVariantId;
    }
    
    public void setProductVariantId(String productVariantId) {
        this.productVariantId = productVariantId;
    }
    public String getContentCategoryId() {
        return this.contentCategoryId;
    }
    
    public void setContentCategoryId(String contentCategoryId) {
        this.contentCategoryId = contentCategoryId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ProductVariantContentCategoriesId) ) return false;
		 ProductVariantContentCategoriesId castOther = ( ProductVariantContentCategoriesId ) other; 
         
		 return ( (this.getProductVariantId()==castOther.getProductVariantId()) || ( this.getProductVariantId()!=null && castOther.getProductVariantId()!=null && this.getProductVariantId().equals(castOther.getProductVariantId()) ) )
 && ( (this.getContentCategoryId()==castOther.getContentCategoryId()) || ( this.getContentCategoryId()!=null && castOther.getContentCategoryId()!=null && this.getContentCategoryId().equals(castOther.getContentCategoryId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getProductVariantId() == null ? 0 : this.getProductVariantId().hashCode() );
         result = 37 * result + ( getContentCategoryId() == null ? 0 : this.getContentCategoryId().hashCode() );
         return result;
   }   


}


