package se.info24.ismOperationsPojo;
// Generated Aug 19, 2013 4:49:18 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * ContentItemStatistics generated by hbm2java
 */
public class ContentItemStatistics  implements java.io.Serializable {


     private String contentItemId;
     private Integer countLikes;
     private Integer countDislikes;
     private Long hits;
     private Date createdDate;
     private Date updatedDat;

    public ContentItemStatistics() {
    }

	
    public ContentItemStatistics(String contentItemId) {
        this.contentItemId = contentItemId;
    }
    public ContentItemStatistics(String contentItemId, Integer countLikes, Integer countDislikes, Long hits, Date createdDate, Date updatedDat) {
       this.contentItemId = contentItemId;
       this.countLikes = countLikes;
       this.countDislikes = countDislikes;
       this.hits = hits;
       this.createdDate = createdDate;
       this.updatedDat = updatedDat;
    }
   
    public String getContentItemId() {
        return this.contentItemId;
    }
    
    public void setContentItemId(String contentItemId) {
        this.contentItemId = contentItemId;
    }
    public Integer getCountLikes() {
        return this.countLikes;
    }
    
    public void setCountLikes(Integer countLikes) {
        this.countLikes = countLikes;
    }
    public Integer getCountDislikes() {
        return this.countDislikes;
    }
    
    public void setCountDislikes(Integer countDislikes) {
        this.countDislikes = countDislikes;
    }
    public Long getHits() {
        return this.hits;
    }
    
    public void setHits(Long hits) {
        this.hits = hits;
    }
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public Date getUpdatedDat() {
        return this.updatedDat;
    }
    
    public void setUpdatedDat(Date updatedDat) {
        this.updatedDat = updatedDat;
    }




}


