package se.info24.pojo;
// Generated May 18, 2011 3:54:17 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ContentCategories generated by hbm2java
 */
public class ContentCategories implements java.io.Serializable {

    private String contentCategoryId;
    private String contentCategoryParentId;
    private Integer displayOrder;
    private String userId;
    private Date createdDate;
    private Date updatedDate;
    private Set contentCategoryTranslationses = new HashSet(0);
    private Set contentCategoryAttributeses = new HashSet(0);
    private Set deviceTypeDefaultCategories = new HashSet(0);
    private Set supportCaseContentLinkses = new HashSet(0);
    private Set contentCategoriesInGroupses = new HashSet(0);
    private Set serviceContentCategorieses = new HashSet(0);

    public ContentCategories() {
    }

    public ContentCategories(String contentCategoryId) {
        this.contentCategoryId = contentCategoryId;
    }

    public ContentCategories(String contentCategoryId, String contentCategoryParentId, Integer displayOrder, String userId, Date createdDate, Date updatedDate, Set contentCategoryTranslationses, Set contentCategoryAttributeses, Set deviceTypeDefaultCategories, Set supportCaseContentLinkses, Set contentCategoriesInGroupses, Set serviceContentCategorieses) {
        this.contentCategoryId = contentCategoryId;
        this.contentCategoryParentId = contentCategoryParentId;
        this.displayOrder = displayOrder;
        this.userId = userId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.contentCategoryTranslationses = contentCategoryTranslationses;
        this.contentCategoryAttributeses = contentCategoryAttributeses;
        this.deviceTypeDefaultCategories = deviceTypeDefaultCategories;
        this.supportCaseContentLinkses = supportCaseContentLinkses;
        this.contentCategoriesInGroupses = contentCategoriesInGroupses;
        this.serviceContentCategorieses = serviceContentCategorieses;
    }

    public String getContentCategoryId() {
        return this.contentCategoryId;
    }

    public void setContentCategoryId(String contentCategoryId) {
        this.contentCategoryId = contentCategoryId;
    }

    public String getContentCategoryParentId() {
        return this.contentCategoryParentId;
    }

    public void setContentCategoryParentId(String contentCategoryParentId) {
        this.contentCategoryParentId = contentCategoryParentId;
    }

    public Integer getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Set getContentCategoryTranslationses() {
        return this.contentCategoryTranslationses;
    }

    public void setContentCategoryTranslationses(Set contentCategoryTranslationses) {
        this.contentCategoryTranslationses = contentCategoryTranslationses;
    }

    public Set getContentCategoryAttributeses() {
        return this.contentCategoryAttributeses;
    }

    public void setContentCategoryAttributeses(Set contentCategoryAttributeses) {
        this.contentCategoryAttributeses = contentCategoryAttributeses;
    }

    public Set getDeviceTypeDefaultCategories() {
        return this.deviceTypeDefaultCategories;
    }

    public void setDeviceTypeDefaultCategories(Set deviceTypeDefaultCategories) {
        this.deviceTypeDefaultCategories = deviceTypeDefaultCategories;
    }

    public Set getSupportCaseContentLinkses() {
        return this.supportCaseContentLinkses;
    }

    public void setSupportCaseContentLinkses(Set supportCaseContentLinkses) {
        this.supportCaseContentLinkses = supportCaseContentLinkses;
    }

    public Set getContentCategoriesInGroupses() {
        return this.contentCategoriesInGroupses;
    }

    public void setContentCategoriesInGroupses(Set contentCategoriesInGroupses) {
        this.contentCategoriesInGroupses = contentCategoriesInGroupses;
    }

    public Set getServiceContentCategorieses() {
        return this.serviceContentCategorieses;
    }

    public void setServiceContentCategorieses(Set serviceContentCategorieses) {
        this.serviceContentCategorieses = serviceContentCategorieses;
    }
}

