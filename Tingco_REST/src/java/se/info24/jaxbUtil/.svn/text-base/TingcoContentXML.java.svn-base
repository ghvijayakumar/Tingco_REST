/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.jaxbUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Session;
import se.info24.contentjaxb.Attribute;
import se.info24.contentjaxb.Attributes;
import se.info24.contentjaxb.Geometry;
import se.info24.contentjaxb.Content;
import se.info24.contentjaxb.ContentAttribute;
import se.info24.contentjaxb.ContentGroups;
import se.info24.contentjaxb.ContentItem;
import se.info24.contentjaxb.ContentItems;
import se.info24.contentjaxb.CountryID;
import se.info24.contentjaxb.GroupID;
import se.info24.contentjaxb.MsgStatus;
import se.info24.contentjaxb.ObjectFactory;
import se.info24.contentjaxb.Point;
import se.info24.contentjaxb.TingcoContent;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.ContentItemCommands;
import se.info24.ismOperationsPojo.ContentItemFeedback;
import se.info24.ismOperationsPojo.DeviceStatus;
import se.info24.pojo.ContentAttributeTranslations;
import se.info24.pojo.ContentAttributes;
import se.info24.pojo.ContentCategories;
import se.info24.pojo.ContentCategoryTranslations;
import se.info24.pojo.ContentTypeTranslations;
import se.info24.pojo.ContentTypes;
import se.info24.pojo.Country;
import se.info24.pojo.Device;
import se.info24.pojo.DeviceTypeCommandTranslations;
import se.info24.pojo.DeviceTypeCommandTranslationsId;
import se.info24.pojo.DeviceTypeCommands;
import se.info24.pojo.DeviceTypeDefaultCategory;
import se.info24.pojo.EventTypeTranslations;
import se.info24.pojo.EventTypeTranslationsId;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTranslationsId;
import se.info24.pojo.UserFavoriteContentCategories;
import se.info24.pojo.ObjectContentCategorySubscriptions;
import se.info24.pojo.SupportOrganizations;
import se.info24.pojo.Users2;
import se.info24.products.ProductsDAO;
import se.info24.restUtil.RestUtilDAO;
import se.info24.util.TCMUtil;

/**
 *
 * @author Sekhar
 */
public class TingcoContentXML {

    private TingcoContent tingcoContent;
    ObjectFactory objectFactory;
    ProductsDAO productsDAO;
    GroupDAO groupDAO;

    public TingcoContentXML() {
        objectFactory = new ObjectFactory();
        productsDAO = new ProductsDAO();
        groupDAO = new GroupDAO();
    }

    public TingcoContent buildAllContentAttributes(TingcoContent tingcoContent, List<ContentAttributes> contentAttributesList) {
        Content content = new Content();
        ContentItems contentItems = new ContentItems();

        for (ContentAttributes ca : contentAttributesList) {
            ContentItem contentItem = new ContentItem();
            Attributes attributes = new Attributes();
            Attribute attribute = new Attribute();
            attribute.setContentAttributeId(ca.getContentAttributeId());
            attribute.setCDMFieldName(ca.getCdmfieldName());
            attribute.setCDMDataGroupName(ca.getCdmdataGroupName());
            attribute.setUnit(ca.getUnit());
            attributes.getAttribute().add(attribute);
            contentItem.setAttributes(attributes);
            contentItems.getContentItem().add(contentItem);
        }
        content.setContentItems(contentItems);
        tingcoContent.setContent(content);
        return tingcoContent;
    }

    public TingcoContent buildContentItemCommands(TingcoContent tingcoContent, ContentItemCommands cic, int countryId, String timeZoneGMToffset, Session session) {
        Content content = new Content();
        ContentItems contentItems = new ContentItems();
        ContentItem contentItem = new ContentItem();
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar gc = new GregorianCalendar();
        se.info24.contentjaxb.ContentItemCommands contentItemCommands = new se.info24.contentjaxb.ContentItemCommands();
        contentItemCommands.setContentItemCommandID(cic.getContentItemCommandId());
        contentItemCommands.setCommandName(cic.getCommandName());
        contentItemCommands.setCommandButtonText(cic.getCommandButtonText());
        if (cic.getCommandDescription() != null) {
            contentItemCommands.setCommandDescription(cic.getCommandDescription());
        }
        GroupID groupID = new GroupID();
        groupID.setValue(cic.getGroupId());
        Object object = session.get(GroupTranslations.class, new GroupTranslationsId(cic.getGroupId(), countryId));
        GroupTranslations gt = null;
        if (object != null) {
            gt = (GroupTranslations) object;
            groupID.setGroupName(gt.getGroupName());
        }
        contentItemCommands.setGroupID(groupID);
        if (cic.getDisplayOrder() != null) {
            contentItemCommands.setDisplayOrder(BigInteger.valueOf(cic.getDisplayOrder().longValue()));
        }
        if (cic.getIsEnabled() != null) {
            contentItemCommands.setIsEnabled(BigInteger.valueOf(cic.getIsEnabled().longValue()));
        }
        if (cic.getActiveFromDate() != null) {
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, cic.getActiveFromDate()));
            contentItemCommands.setActiveFromDate(lFormat.format(gc.getTime()));
        }
        if (cic.getActiveToDate() != null) {
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, cic.getActiveToDate()));
            contentItemCommands.setActiveToDate(lFormat.format(gc.getTime()));
        }
        if (cic.getIsPinCodeRequired() != null) {
            contentItemCommands.setIsPinCodeRequired(BigInteger.valueOf(cic.getIsPinCodeRequired().longValue()));
        }
        if (cic.getPinCode() != null) {
            contentItemCommands.setPinCode(cic.getPinCode());
        }
        if (cic.getIsSendEmail() != null) {
            contentItemCommands.setIsSendEmail(BigInteger.valueOf(cic.getIsSendEmail().longValue()));
        }
        if (cic.getIsSendEmailToDeviceContacts() != null) {
            contentItemCommands.setIsSendEmailToDeviceContacts(BigInteger.valueOf(cic.getIsSendEmailToDeviceContacts().longValue()));
        }
        if (cic.getSendEmailTo() != null) {
            contentItemCommands.setSendEmailTo(cic.getSendEmailTo());
        }
        if (cic.getIsSendSms() != null) {
            contentItemCommands.setIsSendSMS(BigInteger.valueOf(cic.getIsSendSms()));
        }
        if (cic.getIsSendSmstoDeviceContacts() != null) {
            contentItemCommands.setIsSendSMSToDeviceContacts(BigInteger.valueOf(cic.getIsSendSmstoDeviceContacts()));
        }
        if (cic.getSendSmsto() != null) {
            contentItemCommands.setSendSMSTo(cic.getSendSmsto());
        }
        if (cic.getIsLogInWorklog() != null) {
            contentItemCommands.setIsLogInWorklog(BigInteger.valueOf(cic.getIsLogInWorklog().longValue()));
        }
        if (cic.getIsLinkToTim() != null) {
            contentItemCommands.setIsLinkToTIM(BigInteger.valueOf(cic.getIsLinkToTim()));
        }
        if (cic.getIsCreateSupportCase() != null) {
            contentItemCommands.setIsCreateSupportCase(BigInteger.valueOf(cic.getIsCreateSupportCase().longValue()));
        }
        if (cic.getSupportOrganizationId() != null) {
            contentItemCommands.setSupportOrganizationID(cic.getSupportOrganizationId());
            object = session.get(SupportOrganizations.class, cic.getSupportOrganizationId());
            if (object != null) {
                SupportOrganizations so = (SupportOrganizations) object;
//                if(gt != null){
//                    contentItemCommands.setSupportOrganizationName(so.getOrganizationName()+" ("+gt.getGroupName() + ") ");
//                }else{
                contentItemCommands.setSupportOrganizationName(so.getOrganizationName());
//                }

            }
        }
        if (cic.getIsSendEvent() != null) {
            contentItemCommands.setIsSendEvent(BigInteger.valueOf(cic.getIsSendEvent().longValue()));
        }
        if (cic.getEventTypeId() != null) {
            contentItemCommands.setEventTypeID(cic.getEventTypeId());
            object = session.get(EventTypeTranslations.class, new EventTypeTranslationsId(cic.getEventTypeId(), countryId));
            if (object != null) {
                EventTypeTranslations ett = (EventTypeTranslations) object;
                contentItemCommands.setEventTypeName(ett.getEventTypeName());
            }
        }
        if (cic.getIsControlDevice() != null) {
            contentItemCommands.setIsControlDevice(BigInteger.valueOf(cic.getIsControlDevice().longValue()));
        }
        if (cic.getDeviceId() != null) {
            contentItemCommands.setDeviceID(cic.getDeviceId());
            object = session.get(Device.class, cic.getDeviceId());
            if (object != null) {
                Device device = (Device) object;
                contentItemCommands.setDeviceName(device.getDeviceName());
            }

        }
        if (cic.getDeviceTypeCommandId() != null) {
            contentItemCommands.setDeviceTypeCommandID(cic.getDeviceTypeCommandId());
            object = session.get(DeviceTypeCommandTranslations.class, new DeviceTypeCommandTranslationsId(cic.getDeviceTypeCommandId(), countryId));
            if (object != null) {
                DeviceTypeCommandTranslations dtct = (DeviceTypeCommandTranslations) object;
                contentItemCommands.setDeviceTypeCommandName(dtct.getCommandName());
            }
        }
        contentItem.getContentItemCommands().add(contentItemCommands);
        contentItems.getContentItem().add(contentItem);
        content.setContentItems(contentItems);
        tingcoContent.setContent(content);
        return tingcoContent;
    }

    public TingcoContent buildContentItemDetailsByContentItemId(TingcoContent tingcoContent, List<se.info24.ismOperationsPojo.ContentItems> contentItemsList, int countryId, String timeZoneGMToffset, Session session) {
        Content con = new Content();
        ContentItems cItems = new ContentItems();
        GregorianCalendar gc;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            for (se.info24.ismOperationsPojo.ContentItems ci : contentItemsList) {
                gc = new GregorianCalendar();
                ContentItem conItem = new ContentItem();
                conItem.setContentItemID(ci.getContentItemId());
                Geometry g = new Geometry();
                Point p = new Point();
                if (ci.getPosLatitude() != null) {
                    p.setPosLatitude(ci.getPosLatitude().toString());
                }
                if (ci.getPosLongitude() != null) {
                    p.setPosLongitude(ci.getPosLongitude().toString());
                }
                if (ci.getPosDepth() != null) {
                    p.setPosDepth(ci.getPosDepth().toString());
                }
                g.setPoint(p);
                conItem.setGeometry(g);
                conItem.setBody(ci.getContentItemSubject());
                conItem.setIsEnabled(ci.getIsEnabled());

                if (ci.getIsEnabled() == 1 && gc.getTime().after(ci.getStartTime()) && gc.getTime().before(ci.getStopTime()) && gc.getTime().before(ci.getExpiryTime())) {
                    conItem.setIsActive(1);
                } else {
                    conItem.setIsActive(0);
                }

                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, ci.getStartTime()));
                conItem.setStartTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                conItem.setStartTimeTCMV3(dateFormat.format(gc.getTime()));
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, ci.getStopTime()));
                conItem.setStopTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                conItem.setStopTimeTCMV3(dateFormat.format(gc.getTime()));
                if (ci.getExpiryTime() != null) {
                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, ci.getExpiryTime()));
                    conItem.setExpiryTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                    conItem.setExpiryTimeTCMV3(dateFormat.format(gc.getTime()));
                }

                conItem.setContentCategoryID(ci.getContentCategoryId());
                ContentCategoryTranslations categoryTranslations = productsDAO.getContentCategoryTranslations(ci.getContentCategoryId(), countryId, session);
                if (categoryTranslations != null) {
                    conItem.setContentCategoryName(categoryTranslations.getContentCategoryName());
                }
                conItem.setContentTypeID(ci.getContentTypeId());
                ContentTypeTranslations contentTypeTranslations = productsDAO.getContentTypeTranslations(ci.getContentTypeId(), countryId, session);
                if (contentTypeTranslations != null) {
                    conItem.setContentTypeName(contentTypeTranslations.getContentTypeName());
                }

                if (ci.getGroupId() != null) {
                    GroupID groupID = new GroupID();
                    groupID.setValue(ci.getGroupId());
                    GroupTranslations groupTranslations = groupDAO.getGroupTranslationsByIds(ci.getGroupId(), countryId, session);
                    if (groupTranslations != null) {
                        groupID.setGroupName(groupTranslations.getGroupName());
                    }
                    conItem.setGroupID(groupID);
                }
                if (ci.getSourceName() != null) {
                    conItem.setSourceName(ci.getSourceName());
                }
                if (ci.getSourceReferenceId() != null) {
                    conItem.setSourceReferenceID(ci.getSourceReferenceId());
                }
                cItems.getContentItem().add(conItem);
            }
            con.setContentItems(cItems);
            tingcoContent.setContent(con);
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        return tingcoContent;
    }

    public TingcoContent buildContentItemsWithCategoriesAndGroups(TingcoContent tingcoContent, List<se.info24.ismOperationsPojo.ContentItems> contentItemsList, int countryId, Session session) {
        Content content = new Content();
        ContentItems contentItems = new ContentItems();
        ContentCategoryTranslations cct = null;
        for (se.info24.ismOperationsPojo.ContentItems ci : contentItemsList) {
            ContentItem contentItem = new ContentItem();
            contentItem.setContentItemID(ci.getContentItemId());
            contentItem.setBody(ci.getContentItemSubject());
            cct = productsDAO.getContentCategoryTranslations(ci.getContentCategoryId(), countryId, session);
            if (cct != null) {
                contentItem.setContentCategoryID(ci.getContentCategoryId());
                contentItem.setContentCategoryName(cct.getContentCategoryName());
            }
            if (ci.getGroupId() != null) {
                GroupID groupID = new GroupID();
                groupID.setValue(ci.getGroupId());
                GroupTranslations groupTranslations = groupDAO.getGroupTranslationsByIds(ci.getGroupId(), countryId, session);
                if (groupTranslations != null) {
                    groupID.setGroupName(groupTranslations.getGroupName());
                }
                contentItem.setGroupID(groupID);
            }
            contentItems.getContentItem().add(contentItem);
        }
        content.setContentItems(contentItems);
        tingcoContent.setContent(content);
        return tingcoContent;
    }

    public TingcoContent buildTingcoContentTemplate() throws DatatypeConfigurationException {
        tingcoContent = objectFactory.createTingcoContent();
        tingcoContent.setCreateRef("Info24");
        tingcoContent.setMsgVer(new BigDecimal("1.0"));
        tingcoContent.setRegionalUnits("Metric");
        tingcoContent.setTimeZone("UTC");

        tingcoContent.setMsgID(UUID.randomUUID().toString());
        tingcoContent.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        MsgStatus msgStatus = new MsgStatus();
        msgStatus.setResponseCode(0);
        msgStatus.setResponseText("OK");

        tingcoContent.setMsgStatus(msgStatus);
        return tingcoContent;
    }

    public TingcoContent buildContentTypes(TingcoContent tingcoContent, List<ContentTypes> ctList, HashMap<String, Users2> userMap, int countryId) {
//        Contents contents = new Contents();
//        Content content;
//        int seqNo = 1;
//        Users2 user = null;
//        Set<ContentTypeTranslations> cttSet = null;
//
//        for (ContentTypes ct : ctList) {
//            content = new Content();
//            content.setSeqNo(seqNo++);
//
//            ContentType contentType = objectFactory.createContentType();
//            contentType.getContent().add(objectFactory.createContentTypeID(ct.getContentTypeId()));
//
//            cttSet = ct.getContentTypeTranslationses();
//
//            for (ContentTypeTranslations ctt : cttSet) {
//                if (ctt.getCountry().getCountryId() == countryId) {
//                    contentType.getContent().add(objectFactory.createContentTypeName(ctt.getContentTypeName()));
//                    contentType.getContent().add(objectFactory.createContentTypeDesc(ctt.getContentTypeDescription()));
//                    CountryID country = objectFactory.createCountryID();
//                    country.setCountryName(ctt.getCountry().getCountryName());
//                    country.setValue(String.valueOf(countryId));
//                    contentType.getContent().add(country);
//
//                    content.getContent().add(contentType);
//
//                    if (userMap.get(ct.getUserId()) != null) {
//                        user = userMap.get(ct.getUserId());
//                    }
//
//                    if (user != null) {
//                        LastUpdatedByUserID updatedUser = objectFactory.createLastUpdatedByUserID();
//                        updatedUser.setUserFullName(user.getFirstName() + " " + user.getLastName());
//                        updatedUser.setValue(user.getUserId());
//                        contentType.getContent().add(updatedUser);
//                    }
//                    contents.getContent().add(content);
//                }
//            }


        //  }

        // tingcoContent.setContents(contents);
        return tingcoContent;
    }

    public TingcoContent getfavoriteContentCategories(TingcoContent tingcoContent, List<UserFavoriteContentCategories> ufcg, HashMap<String, ContentCategories> contentMap, int countryID) {
        String catid = null;
        int seqNo = 1;
        Set<ContentCategoryTranslations> cctSet = null;
        ContentItems ci = new ContentItems();
        Content content = new Content();

        for (UserFavoriteContentCategories fcg : ufcg) {
            catid = fcg.getId().getContentCategoryId();
            ContentCategories conCat = contentMap.get(catid);
            cctSet = conCat.getContentCategoryTranslationses();
            ContentItem item = new ContentItem();
            item.setSeqNo(seqNo++);
            item.setContentCategoryID(catid);

            for (ContentCategoryTranslations trans : cctSet) {
                if (trans.getCountry().getCountryId() == countryID) {
                    item.setContentCategoryName(trans.getContentCategoryName());
                    break;
                }
            }
            ci.getContentItem().add(item);
        }
        content.setContentItems(ci);
        tingcoContent.setContent(content);
        return tingcoContent;

    }

    public TingcoContent buildContentItemDetails(TingcoContent tc, List<se.info24.ismOperationsPojo.ContentItems> items) {
        Content con = new Content();
        ContentItems cItems = new ContentItems();
        GregorianCalendar gc = new GregorianCalendar();
        for (se.info24.ismOperationsPojo.ContentItems ci : items) {
            try {
                ContentItem conItem = new ContentItem();
                conItem.setContentItemID(ci.getContentItemId());
                Geometry g = new Geometry();
                Point p = new Point();
                p.setPosLatitude(ci.getPosLatitude().toString());
                p.setPosLongitude(ci.getPosLongitude().toString());
                if (ci.getPosDirection() != null) {
                    p.setPosHeading(ci.getPosDirection().toString());
                }
                g.setPoint(p);
                conItem.setGeometry(g);
                conItem.setBody(ci.getContentItemSubject());

                gc.setTime(ci.getStartTime());
                conItem.setStartTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                gc.setTime(ci.getStopTime());
                conItem.setStopTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));

                conItem.setContentCategoryID(ci.getContentCategoryId());
                conItem.setSourceName(ci.getSourceName());
                conItem.setSourceReferenceID(ci.getSourceReferenceId());
                cItems.getContentItem().add(conItem);
            } catch (DatatypeConfigurationException ex) {
                TCMUtil.exceptionLog(getClass().getName(), ex);
            }
        }
        con.setContentItems(cItems);
        tc.setContent(con);
        return tc;
    }

    public TingcoContent buildContentItem(TingcoContent tc, List<se.info24.ismOperationsPojo.ContentItems> items) {
        Content con = new Content();
        ContentItems cItems = new ContentItems();
        for (se.info24.ismOperationsPojo.ContentItems ci : items) {
            ContentItem conItem = new ContentItem();
            conItem.setContentItemID(ci.getContentItemId());
            conItem.setBody(ci.getContentItemSubject());
            cItems.getContentItem().add(conItem);
        }
        con.setContentItems(cItems);
        tc.setContent(con);
        return tc;
    }

    public TingcoContent buildContentCategories(TingcoContent tingcoContent, List<ObjectContentCategorySubscriptions> oc, HashMap<String, ContentCategories> contentMap, int countryId) {
        Content content = new Content();
        ArrayList<String> categories = new ArrayList<String>();
        int seqNo = 1;
        Set<ContentCategoryTranslations> cctSet = null;
        ContentItems ci = new ContentItems();
        String catid = null;
        String prtID = null;
        for (ObjectContentCategorySubscriptions sub : oc) {
            catid = sub.getId().getContentCategoryId();
            ContentCategories conCat = contentMap.get(catid);
            prtID = conCat.getContentCategoryParentId();
            cctSet = conCat.getContentCategoryTranslationses();
            while (prtID != null && !categories.contains(catid)) {
                categories.add(catid);
                ContentItem item = new ContentItem();
                item.setSeqNo(seqNo++);

                item.setContentCategoryID(catid);
                item.setContentCategoryParentID(prtID);

                for (ContentCategoryTranslations trans : cctSet) {
                    if (trans.getCountry().getCountryId() == countryId) {
                        item.setContentCategoryName(trans.getContentCategoryName());
                        break;
                    }
                }
                catid = prtID;
                prtID = contentMap.get(catid).getContentCategoryParentId();
                cctSet = contentMap.get(catid).getContentCategoryTranslationses();
                ci.getContentItem().add(item);
            }

            if (!categories.contains(catid)) {
                categories.add(catid);
                ContentItem item = new ContentItem();
                item.setSeqNo(seqNo++);
                item.setContentCategoryID(catid);
                item.setContentCategoryParentID(prtID);

                for (ContentCategoryTranslations trans : cctSet) {
                    if (trans.getCountry().getCountryId() == countryId) {
                        item.setContentCategoryName(trans.getContentCategoryName());
                        break;
                    }
                }
                ci.getContentItem().add(item);
            }
        }
        content.setContentItems(ci);
        tingcoContent.setContent(content);
        return tingcoContent;
    }

    public TingcoContent buildContentCategoriesList(TingcoContent tingcoContent, List<ContentCategories> contentCategories, List<ContentCategoryTranslations> contentCategoryTransList, String timeZoneGMToffset) {
        Content content = new Content();
        ContentItems contentItemsjaxb = new ContentItems();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        for (ContentCategoryTranslations cct : contentCategoryTransList) {
            for (ContentCategories cc : contentCategories) {
                if (cc.getContentCategoryId().equalsIgnoreCase(cct.getId().getContentCategoryId())) {
                    ContentItem contentItemjaxb = new ContentItem();
                    contentItemjaxb.setContentCategoryID(cct.getId().getContentCategoryId());
                    contentItemjaxb.setContentCategoryName(cct.getContentCategoryName());
                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, cct.getCreatedDate()));
                    contentItemjaxb.setContentCategoryCreatedDate(dateFormat.format(gc.getTime()));
                    if (cc.getContentCategoryParentId() != null) {
                        for (ContentCategoryTranslations cctrans : contentCategoryTransList) {
                            if (cc.getContentCategoryParentId().equalsIgnoreCase(cctrans.getId().getContentCategoryId())) {
                                contentItemjaxb.setContentCategoryParentID(cctrans.getId().getContentCategoryId());
                                contentItemjaxb.setContentCategoryParentName(cctrans.getContentCategoryName());
                                break;
                            }
                        }
                    }
                    contentItemsjaxb.getContentItem().add(contentItemjaxb);
                    break;
                }
            }
        }

        content.setContentItems(contentItemsjaxb);
        tingcoContent.setContent(content);
        return tingcoContent;
    }

    public TingcoContent buildContentCategories(TingcoContent tingcoContent, List<ContentCategoryTranslations> cct) {
        Content content = new Content();
        ContentItems ci = new ContentItems();
        int seqNo = 1;
        for (ContentCategoryTranslations contentCategoryTranslations : cct) {
            ContentItem item = new ContentItem();
            item.setSeqNo(seqNo++);
            item.setContentCategoryID(contentCategoryTranslations.getId().getContentCategoryId());
            item.setContentCategoryName(contentCategoryTranslations.getContentCategoryName());
            if (contentCategoryTranslations.getContentCategoryDescription() != null) {
                item.setContentCategoryDescription(contentCategoryTranslations.getContentCategoryDescription());
            }
            ci.getContentItem().add(item);
        }
        content.setContentItems(ci);
        tingcoContent.setContent(content);
        return tingcoContent;
    }

    public TingcoContent buildContentItemsXMl(List<se.info24.ismOperationsPojo.ContentItems> list, TingcoContent tingcoContent) {
        if (list.isEmpty()) {
            tingcoContent.getMsgStatus().setResponseCode(-1);
            tingcoContent.getMsgStatus().setResponseText("No Data Found");
            return tingcoContent;
        }
        ContentItems contentItems = new ContentItems();
        Content content = new Content();
        Collections.sort(list, new Comparator<se.info24.ismOperationsPojo.ContentItems>() {

            public int compare(se.info24.ismOperationsPojo.ContentItems o1, se.info24.ismOperationsPojo.ContentItems o2) {
                return o1.getContentItemSubject().compareToIgnoreCase(o2.getContentItemSubject());
            }
        });
        int seqNo = 1;
        for (se.info24.ismOperationsPojo.ContentItems ci : list) {
            ContentItem contentItem = new ContentItem();
            contentItem.setSeqNo(seqNo++);
            contentItem.setContentItemID(ci.getContentItemId());
            contentItem.setBody(ci.getContentItemSubject());
            contentItems.getContentItem().add(contentItem);
        }
        content.setContentItems(contentItems);
        tingcoContent.setContent(content);
        return tingcoContent;
    }

    public TingcoContent buildContentItemAttributes(List<Object> objectlist, TingcoContent tingcoContent) {
        Content content = new Content();
        ContentItems contentItems = new ContentItems();
        ContentItem contentItem = new ContentItem();
        Attributes attributes = new Attributes();
        Attribute attribute = new Attribute();
        for (Iterator itr = objectlist.iterator(); itr.hasNext();) {
            Object[] row = (Object[]) itr.next();
            for (int i = 0; i < row.length; i++) {
                if (i % 2 == 0) {
                    ContentAttribute ca = new ContentAttribute();
                    ca.setContentAttributeId(row[i].toString());
                    ca.setAttributeName(row[i + 1].toString());
                    if (row[i + 2] != null) {
                        if (!row[i + 2].toString().equals("")) {
                            ca.setContentAttributeValue(row[i + 2].toString());
                        }
                    }
                    i = i + 2;
                    attribute.getContentAttribute().add(ca);
                }
            }
        }
        attributes.getAttribute().add(attribute);
        contentItem.setAttributes(attributes);
        contentItems.getContentItem().add(contentItem);
        content.setContentItems(contentItems);
        tingcoContent.setContent(content);
        return tingcoContent;
    }

    public TingcoContent buildContentGroupsbyContentItemId(TingcoContent tingcoContent, List<se.info24.ismOperationsPojo.ContentGroups> contentGroupsList) {
        if (contentGroupsList.isEmpty()) {
            tingcoContent.getMsgStatus().setResponseCode(-1);
            tingcoContent.getMsgStatus().setResponseText("Data Not Found");
            return tingcoContent;
        }
        Content content = new Content();
        ContentItems contentItems = new ContentItems();
        ContentItem contentItem = new ContentItem();
        for (se.info24.ismOperationsPojo.ContentGroups cg : contentGroupsList) {
            ContentGroups contentGroupJaxb = new ContentGroups();
            contentGroupJaxb.setContentGroupID(cg.getContentGroupId());
            contentGroupJaxb.setContentGroupName(cg.getContentGroupName());
            contentItem.getContentGroups().add(contentGroupJaxb);
        }
        contentItems.getContentItem().add(contentItem);
        content.setContentItems(contentItems);
        tingcoContent.setContent(content);
        return tingcoContent;
    }

    public TingcoContent buildGetAllContentGroups(TingcoContent tingcoContent, List<se.info24.ismOperationsPojo.ContentGroups> contentGroupsList) {
        if (contentGroupsList.isEmpty()) {
            tingcoContent.getMsgStatus().setResponseCode(-1);
            tingcoContent.getMsgStatus().setResponseText("Data Not Found");
            return tingcoContent;
        }
        Content content = new Content();
        ContentItems contentItems = new ContentItems();
        ContentItem contentItem = new ContentItem();
        Collections.sort(contentGroupsList, new Comparator<se.info24.ismOperationsPojo.ContentGroups>() {

            public int compare(se.info24.ismOperationsPojo.ContentGroups o1, se.info24.ismOperationsPojo.ContentGroups o2) {
                return o1.getContentGroupName().compareToIgnoreCase(o2.getContentGroupName());
            }
        });
        for (se.info24.ismOperationsPojo.ContentGroups cg : contentGroupsList) {
            ContentGroups contentGroupJaxb = new ContentGroups();
            contentGroupJaxb.setContentGroupID(cg.getContentGroupId());
            contentGroupJaxb.setContentGroupName(cg.getContentGroupName());
            contentItem.getContentGroups().add(contentGroupJaxb);
        }
        contentItems.getContentItem().add(contentItem);
        content.setContentItems(contentItems);
        tingcoContent.setContent(content);
        return tingcoContent;
    }

    public TingcoContent buildDetailsForAddNewContentItem(Device device, DeviceTypeDefaultCategory deviceTypeDefaultCategory, GroupTranslations groupTranslations, DeviceStatus deviceStatus) {
        Content content = new Content();
        ContentItems contentItems = new ContentItems();
        ContentItem contentItem = new ContentItem();

        contentItem.setDeviceName(device.getDeviceName());
        if (deviceTypeDefaultCategory != null) {
            contentItem.setContentCategoryID(deviceTypeDefaultCategory.getContentCategories().getContentCategoryId());
            contentItem.setContentTypeID(deviceTypeDefaultCategory.getContainerTypes().getContainerTypeId());
        }
        if (groupTranslations != null) {
            GroupID groupID = new GroupID();
            groupID.setGroupName(groupTranslations.getGroupName());
            groupID.setValue(groupTranslations.getId().getGroupId());
            contentItem.setGroupID(groupID);
        }
        if (deviceStatus != null) {
            Geometry geometry = new Geometry();
            Point point = new Point();
            if (deviceStatus.getPosLatitude() != null) {
                point.setPosLatitude(deviceStatus.getPosLatitude().toString());

            }
            if (deviceStatus.getPosLongitude() != null) {
                point.setPosLongitude(deviceStatus.getPosLongitude().toString());

            }
            if (deviceStatus.getPosDepth() != null) {
                point.setPosDepth(deviceStatus.getPosDepth().toString());

            }
            geometry.setPoint(point);
            contentItem.setGeometry(geometry);
        }
        contentItems.getContentItem().add(contentItem);
        content.setContentItems(contentItems);
        tingcoContent.setContent(content);
        return tingcoContent;
    }

    public TingcoContent buildgetAllContentTypesByCountryId(TingcoContent tingcoContent, List<ContentTypeTranslations> contentTypeTranslationsList) {
        if (contentTypeTranslationsList.isEmpty()) {
            tingcoContent.getMsgStatus().setResponseCode(-1);
            tingcoContent.getMsgStatus().setResponseText("No Data Found");
            return tingcoContent;
        }
        Content content = new Content();
        ContentItems contentItems = new ContentItems();
        int seqNo = 1;
        for (ContentTypeTranslations ctt : contentTypeTranslationsList) {
            ContentItem contentItem = new ContentItem();
            contentItem.setSeqNo(seqNo++);
            contentItem.setContentTypeID(ctt.getId().getContentTypeId());
            if (ctt.getContentTypeName() != null) {
                contentItem.setContentTypeName(ctt.getContentTypeName());
            }
            contentItems.getContentItem().add(contentItem);
        }
        content.setContentItems(contentItems);
        tingcoContent.setContent(content);
        return tingcoContent;
    }

    public TingcoContent buildAddNewContentItem(TingcoContent tingcoContent, String newContentItemId) {
        Content content = new Content();
        ContentItems contentItems = new ContentItems();
        int seqNo = 1;
        ContentItem contentItem = new ContentItem();
        contentItem.setSeqNo(seqNo++);
        contentItem.setContentItemID(newContentItemId);
        contentItems.getContentItem().add(contentItem);
        content.setContentItems(contentItems);
        tingcoContent.setContent(content);
        return tingcoContent;
    }

    public TingcoContent buildGetContentAttributebyContentAttributeID(TingcoContent tingcoContent, ContentAttributes contentAttributes, List<ContentAttributeTranslations> attributeTranslationses, List<Country> countries) {
        Content content = new Content();
        ContentItems contentItems = new ContentItems();
        ContentItem contentItem = new ContentItem();
        Attributes attributes = new Attributes();
        Attribute attribute = new Attribute();

        attribute.setCDMFieldName(contentAttributes.getCdmfieldName());
        attribute.setCDMDataGroupName(contentAttributes.getCdmdataGroupName());
        if (contentAttributes.getUnit() != null) {
            attribute.setUnit(contentAttributes.getUnit());
        }
        for (Country country : countries) {
            ContentAttribute contentAttributeJaxb = new ContentAttribute();
            contentAttributeJaxb.setCountryId(country.getCountryId());
            contentAttributeJaxb.setCountryName(country.getCountryName());
            for (ContentAttributeTranslations cat : attributeTranslationses) {
                if (country.getCountryId() == cat.getId().getCountryId()) {
                    contentAttributeJaxb.setAttributeName(cat.getAttributeName());
                    break;
                }
            }
            attribute.getContentAttribute().add(contentAttributeJaxb);
        }
        attributes.getAttribute().add(attribute);
        contentItem.setAttributes(attributes);
        contentItems.getContentItem().add(contentItem);
        content.setContentItems(contentItems);
        tingcoContent.setContent(content);
        return tingcoContent;
    }

    public TingcoContent buildContentAttributesByCountryId(TingcoContent tingcoContent, List<ContentAttributeTranslations> attributeTranslationses) {
        Content content = new Content();
        ContentItems contentItems = new ContentItems();
        ContentItem contentItem = new ContentItem();
        Attributes attributes = new Attributes();
        Attribute attribute = new Attribute();
        for (ContentAttributeTranslations cat : attributeTranslationses) {
            ContentAttribute contentAttributeJaxb = new ContentAttribute();
            contentAttributeJaxb.setContentAttributeId(cat.getId().getContentAttributeId());
            contentAttributeJaxb.setAttributeName(cat.getAttributeName());
            attribute.getContentAttribute().add(contentAttributeJaxb);
        }
        attributes.getAttribute().add(attribute);
        contentItem.setAttributes(attributes);
        contentItems.getContentItem().add(contentItem);
        content.setContentItems(contentItems);
        tingcoContent.setContent(content);
        return tingcoContent;
    }

    public TingcoContent buildGetEventTypeNameDetailsForContentItemCommand(TingcoContent tingcoContent, List<EventTypeTranslations> etts) {
        Content content = new Content();
        ContentItems contentItems = new ContentItems();
        ContentItem contentItem = new ContentItem();
        int seqNo = 1;
        for (EventTypeTranslations ett : etts) {
            se.info24.contentjaxb.ContentItemCommands cic = new se.info24.contentjaxb.ContentItemCommands();
            cic.setSeqNo(seqNo++);
            cic.setEventTypeID(ett.getId().getEventTypeId());
            cic.setEventTypeName(ett.getEventTypeName());

            contentItem.getContentItemCommands().add(cic);
        }

        contentItems.getContentItem().add(contentItem);
        content.setContentItems(contentItems);
        tingcoContent.setContent(content);
        return tingcoContent;
    }

    public TingcoContent buildGetDeviceTypeCommandsForContentItemCommand(TingcoContent tingcoContent, List<DeviceTypeCommands> deviceTypeCommandsList, List<DeviceTypeCommandTranslations> deviceTypeCommandTranslationses) {
        Content content = new Content();
        ContentItems contentItems = new ContentItems();
        ContentItem contentItem = new ContentItem();
        int seqNo = 1;
        for (DeviceTypeCommandTranslations dtct : deviceTypeCommandTranslationses) {
            se.info24.contentjaxb.ContentItemCommands cic = new se.info24.contentjaxb.ContentItemCommands();
            cic.setSeqNo(seqNo++);
            cic.setDeviceTypeCommandID(dtct.getId().getDeviceTypeCommandId());
            cic.setCommandName(dtct.getCommandName());

            contentItem.getContentItemCommands().add(cic);
        }

        contentItems.getContentItem().add(contentItem);
        content.setContentItems(contentItems);
        tingcoContent.setContent(content);
        return tingcoContent;
    }

    public TingcoContent buildGetContentItemCommandDetailsByContentItemId(TingcoContent tingcoContent, List<ContentItemCommands> cics) {
        Content content = new Content();
        ContentItems contentItems = new ContentItems();
        ContentItem contentItem = new ContentItem();
        int seqNo = 1;
        for (ContentItemCommands cic : cics) {
            se.info24.contentjaxb.ContentItemCommands cicJaxb = new se.info24.contentjaxb.ContentItemCommands();
            cicJaxb.setSeqNo(seqNo++);
            cicJaxb.setContentItemCommandID(cic.getContentItemCommandId());
            cicJaxb.setCommandName(cic.getCommandName());

            contentItem.getContentItemCommands().add(cicJaxb);
        }

        contentItems.getContentItem().add(contentItem);
        content.setContentItems(contentItems);
        tingcoContent.setContent(content);
        return tingcoContent;
    }

    public TingcoContent buildGetContentItemDetails(TingcoContent tingcoContent, List<se.info24.ismOperationsPojo.ContentItems> filteredContentItemses, List<GroupTranslations> groupTranslationses) {
        Content content = new Content();
        ContentItems contentItems = new ContentItems();

        int seqNo = 1;
        for (se.info24.ismOperationsPojo.ContentItems ci : filteredContentItemses) {
            ContentItem contentItem = new ContentItem();
            contentItem.setSeqNo(seqNo++);
            contentItem.setContentItemID(ci.getContentItemId());
            if (ci.getGroupId() != null) {
                StringBuffer buffer = new StringBuffer(ci.getContentItemSubject());
                buffer.append("(");
                for (GroupTranslations gt : groupTranslationses) {
                    if (gt.getId().getGroupId().equalsIgnoreCase(ci.getGroupId())) {
                        buffer.append(gt.getGroupName());
                        break;
                    }
                }
                buffer.append(")");
                contentItem.setBody(buffer.toString());
            } else {
                contentItem.setBody(ci.getContentItemSubject());
            }
            contentItems.getContentItem().add(contentItem);
        }

        content.setContentItems(contentItems);
        tingcoContent.setContent(content);
        return tingcoContent;
    }

    public TingcoContent buildGetContentItemStatistics(TingcoContent tingcoContent, se.info24.ismOperationsPojo.ContentItems contentItems, ContentCategoryTranslations contentCategoryTranslations, GroupTranslations gt, se.info24.ismOperationsPojo.ContentItemStatistics cis, List<ContentItemFeedback> cifs, String timeZoneGMToffset) {
        Content content = new Content();
        ContentItems contentItemsJaxb = new ContentItems();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int seqNo = 1;
        ContentItem contentItem = new ContentItem();
        contentItem.setSeqNo(seqNo);
        contentItem.setBody(contentItems.getContentItemSubject());

        if (contentCategoryTranslations != null) {
            contentItem.setContentCategoryName(contentCategoryTranslations.getContentCategoryName());
        }

        if (gt != null) {
            GroupID groupID = new GroupID();
            groupID.setValue(gt.getId().getGroupId());
            groupID.setGroupName(gt.getGroupName());
            contentItem.setGroupID(groupID);
        }

        if (cis != null) {
            se.info24.contentjaxb.ContentItemStatistics cisJaxb = new se.info24.contentjaxb.ContentItemStatistics();
            cisJaxb.setCreatedDate(df.format(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, cis.getCreatedDate())));
            cisJaxb.setUpdatedDate(df.format(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, cis.getUpdatedDat())));
            cisJaxb.setHits(cis.getHits().intValue());
            if (cis.getCountLikes() != null) {
                cisJaxb.setCountLikes(cis.getCountLikes());
                int temp = cis.getCountDislikes() == null ? 0 : cis.getCountDislikes();
                float percentage = cis.getCountLikes() * 100f / (cis.getCountLikes() + temp);
                cisJaxb.setCountLikePercentage(Math.round(percentage) + "");
            }
            if (cis.getCountDislikes() != null) {
                cisJaxb.setCountDislikes(cis.getCountDislikes());
                int temp = cis.getCountLikes() == null ? 0 : cis.getCountLikes();
                float percentage = cis.getCountDislikes() * 100f / (cis.getCountDislikes() + temp);
                cisJaxb.setCountDisLikePercentage(Math.round(percentage) + "");
            }
            contentItem.setContentItemStatistics(cisJaxb);
        }

        for (ContentItemFeedback cif : cifs) {
            se.info24.contentjaxb.ContentItemFeedback cifJaxb = new se.info24.contentjaxb.ContentItemFeedback();
            cifJaxb.setSeqNo(seqNo++);
            cifJaxb.setCreatedDate(df.format(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, cif.getCreatedDate())));
            try {
                cifJaxb.setIsLike(cif.getIsLike());
            } catch (Exception e) {
                //Do Not Set In OutputXMl
            }
            try {
                cifJaxb.setIsDisLike(cif.getIsDisLike());
            } catch (Exception e) {
                //Do Not Set In OutputXMl
            }

            if (cif.getFeedbackText() != null) {
                String chars75 = cif.getFeedbackText().length() > 75 ? cif.getFeedbackText().substring(0, 75) : cif.getFeedbackText();
                cifJaxb.setFeedbackText(chars75);
            }
            contentItem.getContentItemFeedback().add(cifJaxb);
        }
        contentItemsJaxb.getContentItem().add(contentItem);
        content.setContentItems(contentItemsJaxb);
        tingcoContent.setContent(content);
        return tingcoContent;
    }

    public TingcoContent buildGetDeviceTypeCommandTranslations(TingcoContent tingcoContent, List<DeviceTypeCommandTranslations> deviceTypeCommandTranslationses, List<Country> countrys) {
        Content content = new Content();
        ContentItems contentItems = new ContentItems();
        ContentItem contentItem = new ContentItem();
        int seqNo = 1;
        ContentItem.ContentItemCommand contentItemCommand = new ContentItem.ContentItemCommand();
        for (Country c : countrys) {
            se.info24.contentjaxb.ContentItemCommandCountry contentItemCommandCountry = objectFactory.createContentItemCommandCountry();
            contentItemCommandCountry.setCountryId(c.getCountryId() + "");
            contentItemCommandCountry.setCountryName(c.getCountryName());
            for (DeviceTypeCommandTranslations dtct : deviceTypeCommandTranslationses) {
                if (dtct.getId().getCountryId() == c.getCountryId()) {
                    contentItemCommandCountry.setCommandName(dtct.getCommandName());
                    contentItemCommandCountry.setCommandButtonText(dtct.getCommandButtonText());
                }
            }
            contentItemCommand.getContentItemCommandCountry().add(contentItemCommandCountry);
        }
        contentItem.setContentItemCommand(contentItemCommand);
        contentItems.getContentItem().add(contentItem);


        content.setContentItems(contentItems);
        tingcoContent.setContent(content);
        return tingcoContent;
    }
}