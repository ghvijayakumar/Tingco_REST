/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.jaxbUtil;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Session;
import se.info24.device.DeviceDAO;
import se.info24.group.GroupDAO;
import se.info24.pojo.ChargeTypeTranslations;
import se.info24.pojo.ContentCategoryTranslations;
import se.info24.pojo.Currency;
import se.info24.pojo.Device;
import se.info24.pojo.DeviceTypes;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTypeTranslations;
import se.info24.pojo.PricePlanItems;
import se.info24.pojo.PricePlanTypeTranslations;
import se.info24.pojo.PricePlanVersions;
import se.info24.pojo.PricePlans;
import se.info24.pojo.ProductCategoryTranslations;
import se.info24.pojo.ProductTranslations;
import se.info24.pojo.ProductVariantTranslations;
import se.info24.pojo.ServiceTypes;
import se.info24.pojo.Services;
import se.info24.pojo.UserAliasTypes;
import se.info24.pojo.UserTypeTranslations2;
import se.info24.pojo.Users2;
import se.info24.pojo.ValidationDataType;
import se.info24.pojo.ValidationExpressions;
import se.info24.pojo.WeekdayTranslations;
import se.info24.priceplans.PricePlanDAO;
import se.info24.priceplansjaxb.ChargeTypes;
import se.info24.priceplansjaxb.MsgStatus;
import se.info24.priceplansjaxb.ObjectFactory;
import se.info24.priceplansjaxb.PricePlan;
import se.info24.priceplansjaxb.PricePlanItemTypeTranslations;
import se.info24.priceplansjaxb.PricePlanItemTypes;
import se.info24.priceplansjaxb.PricePlanTypes;
import se.info24.priceplansjaxb.ProductCategories;
import se.info24.priceplansjaxb.Products;
import se.info24.priceplansjaxb.TingcoPricePlans;
import se.info24.priceplansjaxb.ValidationDataTypes;
import se.info24.priceplansjaxb.ValidationValue;
import se.info24.products.ProductsDAO;
import se.info24.reports.ReportDAO;
import se.info24.restUtil.RestUtilDAO;
import se.info24.service.ServiceDAO;
import se.info24.user.UserDAO;
import se.info24.util.TCMUtil;

/**
 *
 * @author Vijayakumar
 */
public class TingcoPricePlansXML {

    private TingcoPricePlans tingcoPricePlans;
    ObjectFactory objectFactory;
    PricePlanDAO dao = new PricePlanDAO();
    GroupDAO groupDAO = new GroupDAO();
    ProductsDAO productsDAO = new ProductsDAO();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public TingcoPricePlansXML() {
        objectFactory = new ObjectFactory();
    }

    public TingcoPricePlans buildTingcoPricePlanItemTypes(TingcoPricePlans tingcoPricePlans, List<se.info24.pojo.PricePlanItemTypeTranslations> ppitList) {
        se.info24.priceplansjaxb.PricePlans pricePlans = new se.info24.priceplansjaxb.PricePlans();
        PricePlan priceplan = new PricePlan();
        se.info24.priceplansjaxb.PricePlanVersions ppv = new se.info24.priceplansjaxb.PricePlanVersions();
        se.info24.priceplansjaxb.PricePlanItems ppitems = new se.info24.priceplansjaxb.PricePlanItems();
        for (se.info24.pojo.PricePlanItemTypeTranslations ppitts : ppitList) {
            PricePlanItemTypes ppit = new PricePlanItemTypes();
            PricePlanItemTypeTranslations ppitt = new PricePlanItemTypeTranslations();
            ppitt.setPricePlanItemTypeID(ppitts.getId().getPricePlanItemTypeId());
            ppitt.setCountryID(ppitts.getId().getCountryId());
            ppitt.setPricePlanItemTypeName(ppitts.getPricePlanItemTypeName());
            ppit.getPricePlanItemTypeTranslations().add(ppitt);
            ppitems.getPricePlanItemTypes().add(ppit);
        }
        ppv.getPricePlanItems().add(ppitems);
        priceplan.getPricePlanVersions().add(ppv);
        pricePlans.getPricePlan().add(priceplan);
        tingcoPricePlans.setPricePlans(pricePlans);
        return tingcoPricePlans;
    }

    public TingcoPricePlans buildTingcoPricePlanTypeTranslations(TingcoPricePlans tingcoPricePlans, List<PricePlanTypeTranslations> pricePlanTypeTranslationsList) {
        se.info24.priceplansjaxb.PricePlans pricePlans = new se.info24.priceplansjaxb.PricePlans();
        se.info24.priceplansjaxb.PricePlan pricePlan = new se.info24.priceplansjaxb.PricePlan();
        for (PricePlanTypeTranslations pptt : pricePlanTypeTranslationsList) {
            PricePlanTypes ppt = new PricePlanTypes();
            se.info24.priceplansjaxb.PricePlanTypeTranslations ppttrans = new se.info24.priceplansjaxb.PricePlanTypeTranslations();
            ppt.setPricePlanTypeID(pptt.getId().getPricePlanTypeId());
            ppttrans.setPricePlanTypeName(pptt.getPricePlanTypeName());
            ppt.getPricePlanTypeTranslations().add(ppttrans);
            pricePlan.getPricePlanTypes().add(ppt);
        }
        pricePlans.getPricePlan().add(pricePlan);
        tingcoPricePlans.setPricePlans(pricePlans);
        return tingcoPricePlans;
    }

    public TingcoPricePlans buildTingcoPricePlanItems(TingcoPricePlans tingcoPricePlans, List<PricePlanItems> pricePlanItemsList, int countryId, Session session, String timeZoneGMToffset) throws ParseException {
        se.info24.priceplansjaxb.PricePlans pricePlans = new se.info24.priceplansjaxb.PricePlans();
        int seqNo = 0;
        for (PricePlanItems ppi : pricePlanItemsList) {
            PricePlan pp = new PricePlan();
            pp.setSeqNo(++seqNo);
            se.info24.priceplansjaxb.PricePlanVersions pricePlanVersions = new se.info24.priceplansjaxb.PricePlanVersions();
            se.info24.priceplansjaxb.PricePlanItems pricePlanItems = new se.info24.priceplansjaxb.PricePlanItems();
            pricePlanItems.setPricePlanItemID(ppi.getPricePlanItemId());
            if (ppi.getPricePlanItemParentId() != null) {
                pricePlanItems.setPricePlanItemParentID(ppi.getPricePlanItemParentId());
            }
            pricePlanItems.setItemName(ppi.getItemName());
            pricePlanItems.setPricePlanItemTypeID(ppi.getPricePlanItemTypes().getPricePlanItemTypeId());
            se.info24.pojo.PricePlanItemTypes ppity = new se.info24.pojo.PricePlanItemTypes();
            ppity.setPricePlanItemTypeCode(ppi.getPricePlanItemTypes().getPricePlanItemTypeId());
            pricePlanItems.setChargeTypeID(ppi.getChargeTypeId());
            pricePlanItems.setValidationDataTypeID(ppi.getValidationDataType().getValidationDataTypeId());
            pricePlanItems.setValidationExpressionID(ppi.getValidationExpressions().getValidationExpressionId());
            pricePlanItems.setValidationExpression(ppi.getValidationExpression());
            pricePlanItems.setValidationDataType(ppi.getValidationDataType_1());
            ValidationValue validationValue = new ValidationValue();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            GregorianCalendar gc = new GregorianCalendar();
            if (TCMUtil.isValidUUID(ppi.getValidationValue())) {
                if (ppi.getValidationDataType_1().equalsIgnoreCase("DAYOFWEEK")) {
                    validationValue.setID(ppi.getValidationValue());
                    ReportDAO reportDAO = new ReportDAO();

                    WeekdayTranslations weekDayTranslations = reportDAO.getWeekDaysById(countryId, ppi.getValidationValue(), session);
                    if (weekDayTranslations != null) {
                        validationValue.setValue(weekDayTranslations.getWeekdayName());
                    }
                } else if (ppi.getValidationDataType_1().equalsIgnoreCase("USERALIASTYPE")) {
                    validationValue.setID(ppi.getValidationValue());
                    UserDAO userDAO = new UserDAO();
                    Object uat = userDAO.getUserAliasTypesByID(session, ppi.getValidationValue());
                    if (uat != null) {
                        UserAliasTypes userAliasTypes = (UserAliasTypes) uat;
                        validationValue.setValue(userAliasTypes.getUserAliasTypeName());
                    }
                } else if (ppi.getValidationDataType_1().equalsIgnoreCase("PRODUCT")) {
                    validationValue.setID(ppi.getValidationValue());
                    ProductTranslations productTranslations = productsDAO.getProductTranslationsById(ppi.getValidationValue(), countryId, session);
                    if (productTranslations != null) {
                        validationValue.setValue(productTranslations.getProductName());
                    }
                } else if (ppi.getValidationDataType_1().equalsIgnoreCase("PRODUCTCATEGORY")) {
                    validationValue.setID(ppi.getValidationValue());
                    ProductCategoryTranslations productCategoryTranslations = productsDAO.getProductCategoryTranslationsByID(ppi.getValidationValue(), countryId, session);
                    if (productCategoryTranslations != null) {
                        validationValue.setValue(productCategoryTranslations.getProductCategoryName());
                    }
                } else if (ppi.getValidationDataType_1().equalsIgnoreCase("DEVICETYPE")) {
                    validationValue.setID(ppi.getValidationValue());
                    DeviceDAO deviceDAO = new DeviceDAO();
                    Object dt = deviceDAO.getDeviceTypesById(ppi.getValidationValue(), session);
                    if (dt != null) {
                        DeviceTypes deviceTypes = (DeviceTypes) dt;
                        validationValue.setValue(deviceTypes.getDeviceTypeName());
                    }
                } else if (ppi.getValidationDataType_1().equalsIgnoreCase("SERVICETYPE")) {
                    validationValue.setID(ppi.getValidationValue());
                    ServiceDAO serviceDAO = new ServiceDAO();
                    ServiceTypes serviceTypes = serviceDAO.getServiceTypesById(ppi.getValidationValue(), session);
                    if (serviceTypes != null) {
                        validationValue.setValue(serviceTypes.getServiceTypeName());
                    }
                } else if (ppi.getValidationDataType_1().equalsIgnoreCase("SERVICE")) {
                    validationValue.setID(ppi.getValidationValue());
                    ServiceDAO serviceDAO = new ServiceDAO();
                    Object obj = serviceDAO.getServicesbyServiceId(session, ppi.getValidationValue());
                    if (obj != null) {
                        Services services = (Services) obj;
                        validationValue.setValue(services.getServiceName());
                    }
                } else if (ppi.getValidationDataType_1().equalsIgnoreCase("DEVICE")) {
                    validationValue.setID(ppi.getValidationValue());
                    DeviceDAO deviceDAO = new DeviceDAO();
                    Device device = deviceDAO.getDeviceById(ppi.getValidationValue(), session);
                    if (device != null) {
                        validationValue.setValue(device.getDeviceName());
                    }
                } else if (ppi.getValidationDataType_1().equalsIgnoreCase("ORGANIZATIONTYPE")) {
                    validationValue.setID(ppi.getValidationValue());
                    GroupTypeTranslations groupTypeTranslations = groupDAO.getGroupTypeTranslationsByGroupTypeId(ppi.getValidationValue(), countryId, session);
                    if (groupTypeTranslations != null) {
                        validationValue.setValue(groupTypeTranslations.getGroupTypeName());
                    }
                } else if (ppi.getValidationDataType_1().equalsIgnoreCase("ORGANIZATIONUNIT") || ppi.getValidationDataType_1().equalsIgnoreCase("DEVICEORGANIZATION") || ppi.getValidationDataType_1().equalsIgnoreCase("USERORGANIZATION") ) {
                    validationValue.setID(ppi.getValidationValue());
                    GroupTranslations groupTranslations = groupDAO.getGroupTranslationsByIds(ppi.getValidationValue(), countryId, session);
                    if (groupTranslations != null) {
                        validationValue.setValue(groupTranslations.getGroupName());
                    }
                } else if (ppi.getValidationDataType_1().equalsIgnoreCase("USERTYPE")) {
                    validationValue.setID(ppi.getValidationValue());
                    UserDAO userDAO = new UserDAO();
                    UserTypeTranslations2 userTypeTrans2 = userDAO.getUserTypeTranslations2ById(ppi.getValidationValue(), countryId, session);
                    if (userTypeTrans2 != null) {
                        validationValue.setValue(userTypeTrans2.getUserTypeName());
                    }
                } else if (ppi.getValidationDataType_1().equalsIgnoreCase("CONTENTCATEGORY")) {
                    validationValue.setID(ppi.getValidationValue());
                    ContentCategoryTranslations contentCategoryTrans = productsDAO.getContentCategoryTranslations(ppi.getValidationValue(), countryId, session);
                    if (contentCategoryTrans != null) {
                        validationValue.setValue(contentCategoryTrans.getContentCategoryName());
                    }
                } else if (ppi.getValidationDataType_1().equalsIgnoreCase("USER")) {
                    validationValue.setID(ppi.getValidationValue());
                    UserDAO userDAO = new UserDAO();
                    Users2 users2 = userDAO.getUserById(ppi.getValidationValue(), session);
                    if (users2 != null) {
                        validationValue.setValue(users2.getFirstName() + " " + users2.getLastName());
                    }
                } else if (ppi.getValidationDataType_1().equalsIgnoreCase("PRODUCTVARIANT")) {
                    validationValue.setID(ppi.getValidationValue());
                    ProductVariantTranslations productVariantTrans = productsDAO.getProductVariantTranslationsByIds(ppi.getValidationValue(), countryId, session);
                    if (productVariantTrans != null) {
                        validationValue.setValue(productVariantTrans.getProductVariantName());
                    }
                }
            } else if (ppi.getValidationDataType_1().equalsIgnoreCase("DATE")) {
                gc.setTime(df.parse(ppi.getValidationValue()));
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, gc.getTime()));
                String str = df.format(gc.getTime());
                validationValue.setID(str);
                validationValue.setValue(str.substring(0, str.lastIndexOf("-") + 3)); //fetching only the date part from dateTime
            } else {
//                gc.setTime(df.parse(ppi.getValidationValue()));
//                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, gc.getTime()));
//                String str = df.format(gc.getTime());


                validationValue.setID(ppi.getValidationDataType_1()); //If none of the below IF conditions satisfied, this will be the validation value
                validationValue.setValue(ppi.getValidationValue()); //If none of the below IF conditions satisfied, this will be the validation value
            }
            pricePlanItems.setValidationValue(validationValue);

            if (ppi.getItemAmount() != null) {
                pricePlanItems.setItemAmount(ppi.getItemAmount().floatValue());
            }
            if (ppi.getItemPercent() != null) {
                pricePlanItems.setItemPercent(ppi.getItemPercent().floatValue());
            }
            List<se.info24.pojo.PricePlanItemTypeTranslations> ppittList = dao.getPricePlanItemTypeTranslations(session, ppi.getPricePlanItemTypes().getPricePlanItemTypeId(), countryId);
            for (se.info24.pojo.PricePlanItemTypeTranslations ppttt : ppittList) {
                PricePlanItemTypeTranslations ppityt = new PricePlanItemTypeTranslations();
                ppityt.setPricePlanItemTypeName(ppttt.getPricePlanItemTypeName());
                PricePlanItemTypes ppit = new PricePlanItemTypes();
                ppit.getPricePlanItemTypeTranslations().add(ppityt);
                pricePlanItems.getPricePlanItemTypes().add(ppit);
                ChargeTypeTranslations chargeTypeTranslations = dao.getChargeTypeTranslations(session, ppi.getChargeTypeId(), countryId);
                if (chargeTypeTranslations != null) {
                    String chargeTypeName = null;
                    chargeTypeName = chargeTypeTranslations.getChargeTypeName();
                    pricePlanItems.setChargeTypeName(chargeTypeName);
                }
                pricePlanVersions.getPricePlanItems().add(pricePlanItems);
                pp.getPricePlanVersions().add(pricePlanVersions);
                pricePlans.getPricePlan().add(pp);
            }
        }
        tingcoPricePlans.setPricePlans(pricePlans);
        return tingcoPricePlans;
    }

    public TingcoPricePlans buildTingcoPricePlans(TingcoPricePlans tingcoPricePlans, List<PricePlans> pricePlanList) {
        se.info24.priceplansjaxb.PricePlans pricePlans = new se.info24.priceplansjaxb.PricePlans();
        int seqNo = 0;
        for (PricePlans pp : pricePlanList) {
            PricePlan p = new PricePlan();
            p.setPricePlanID(pp.getPricePlanId());
            p.setPricePlanName(pp.getPricePlanName());
            p.setSeqNo(++seqNo);
            pricePlans.getPricePlan().add(p);
        }
        tingcoPricePlans.setPricePlans(pricePlans);
        return tingcoPricePlans;
    }

    public TingcoPricePlans buildTingcoPricePlansDetails(TingcoPricePlans tingcoPricePlans, String timeZoneGMToffset, PricePlans pp) throws DatatypeConfigurationException {
        se.info24.priceplansjaxb.PricePlans pricePlans = new se.info24.priceplansjaxb.PricePlans();
        GregorianCalendar gc = new GregorianCalendar();
        int seqNo = 0;
        PricePlan p = new PricePlan();
        p.setPricePlanName(pp.getPricePlanName());
        p.setPricePlanDescription(pp.getPricePlanDescription());
        p.setGroupID(pp.getGroupId());
        p.setPricePlanTypeID(pp.getPricePlanTypes().getPricePlanTypeId());
        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, pp.getActiveFromDate()));
        p.setActiveFromDate(dateFormat.format(gc.getTime()));
        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, pp.getActiveToDate()));
        p.setActiveToDate(dateFormat.format(gc.getTime()));
        p.setSeqNo(++seqNo);
        pricePlans.getPricePlan().add(p);
        tingcoPricePlans.setPricePlans(pricePlans);
        return tingcoPricePlans;
    }

    public TingcoPricePlans buildTingcoPricePlanVersions(TingcoPricePlans tingcoPricePlans, List<PricePlanVersions> pricePlanVersionsList, Session session, String timeZoneGMToffset) throws DatatypeConfigurationException {
        se.info24.priceplansjaxb.PricePlans pricePlans = new se.info24.priceplansjaxb.PricePlans();
        se.info24.priceplansjaxb.PricePlan pricePlan = new se.info24.priceplansjaxb.PricePlan();
        RestUtilDAO restutildao = new RestUtilDAO();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (PricePlanVersions ppv : pricePlanVersionsList) {
            se.info24.priceplansjaxb.PricePlanVersions ppver = new se.info24.priceplansjaxb.PricePlanVersions();
            ppver.setPricePlanVersionID(ppv.getPricePlanVersionId());
            ppver.setVersionName(ppv.getVersionName());
            ppver.setVersionDescription(ppv.getVersionDescription());
            GregorianCalendar gc = new GregorianCalendar();
//            gc.setTime(ppv.getActiveFromDate());
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, ppv.getActiveFromDate()));
            ppver.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            ppver.setActiveFromDateTCMV3(df.format(gc.getTime()));
//            gc.setTime(ppv.getActiveToDate());
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, ppv.getActiveToDate()));
            ppver.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
             ppver.setActiveToDateTCMV3(df.format(gc.getTime()));
            ppver.setPriority(ppv.getPriority());
            ppver.setIsEnabled(ppv.getIsEnabled());
            Currency currency = restutildao.getCurrencyById(String.valueOf(ppv.getCurrencyId()), session);
            if (currency != null) {
                ppver.setCurrencyISOCharCode(currency.getCurrencyIsocharCode());
                ppver.setCurrencyID(currency.getCurrencyId());
            }
            pricePlan.getPricePlanVersions().add(ppver);
        }
        pricePlans.getPricePlan().add(pricePlan);
        tingcoPricePlans.setPricePlans(pricePlans);
        return tingcoPricePlans;
    }

    public TingcoPricePlans buildTingcoPricePlansTemplate() throws DatatypeConfigurationException {
        tingcoPricePlans = objectFactory.createTingcoPricePlans();
        tingcoPricePlans.setCreateRef("Info24");
        tingcoPricePlans.setMsgVer(new BigDecimal("1.0"));
        tingcoPricePlans.setRegionalUnits("Metric");
        tingcoPricePlans.setTimeZone("UTC");

        tingcoPricePlans.setMsgID(UUID.randomUUID().toString());
        tingcoPricePlans.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        MsgStatus msgStatus = new MsgStatus();
        msgStatus.setResponseCode(0);
        msgStatus.setResponseText("OK");

        tingcoPricePlans.setMsgStatus(msgStatus);
        return tingcoPricePlans;
    }

    public TingcoPricePlans buildTingcoProductCategoryTranslations(TingcoPricePlans tingcoPricePlans, List<ProductCategoryTranslations> productCategoryTranslationsList) {
        se.info24.priceplansjaxb.PricePlans pricePlans = new se.info24.priceplansjaxb.PricePlans();
        se.info24.priceplansjaxb.PricePlan priceplan = new se.info24.priceplansjaxb.PricePlan();
        ProductCategories productCategories = new ProductCategories();
        for (ProductCategoryTranslations pct : productCategoryTranslationsList) {
            se.info24.priceplansjaxb.ProductCategoryTranslations pctrans = new se.info24.priceplansjaxb.ProductCategoryTranslations();
            pctrans.setProductCategoryID(pct.getId().getProductCategoryId());
            pctrans.setCountryID(pct.getId().getCountryId());
            pctrans.setProductCategoryName(pct.getProductCategoryName());
            productCategories.getProductCategoryTranslations().add(pctrans);
        }
        priceplan.getProductCategories().add(productCategories);
        pricePlans.getPricePlan().add(priceplan);
        tingcoPricePlans.setPricePlans(pricePlans);
        return tingcoPricePlans;
    }

    public TingcoPricePlans buildTingcoProductTranslations(TingcoPricePlans tingcoPricePlans, List<ProductTranslations> productTranslationsList, String countryId) {
        se.info24.priceplansjaxb.PricePlans pricePlans = new se.info24.priceplansjaxb.PricePlans();
        se.info24.priceplansjaxb.PricePlan priceplan = new se.info24.priceplansjaxb.PricePlan();
        Products products = new Products();
        for (ProductTranslations pt : productTranslationsList) {
            se.info24.priceplansjaxb.ProductTranslations ptrans = new se.info24.priceplansjaxb.ProductTranslations();
            ptrans.setProductID(pt.getId().getProductId());
//            ptrans.setCountryID(pt.getId().getCountryId());
            if (!countryId.equals("")) {
                ptrans.setProductName(pt.getProductName());
            }
            products.getProductTranslations().add(ptrans);
        }
        priceplan.getProducts().add(products);
        pricePlans.getPricePlan().add(priceplan);
        tingcoPricePlans.setPricePlans(pricePlans);
        return tingcoPricePlans;
    }

    public TingcoPricePlans buildTingcoProductVariantTranslations(TingcoPricePlans tingcoPricePlans, List<ProductVariantTranslations> productVariantTranslationsList) {
        se.info24.priceplansjaxb.PricePlans pricePlans = new se.info24.priceplansjaxb.PricePlans();
        se.info24.priceplansjaxb.PricePlan priceplan = new se.info24.priceplansjaxb.PricePlan();
        se.info24.priceplansjaxb.ProductVariants productVariants = new se.info24.priceplansjaxb.ProductVariants();
        for (ProductVariantTranslations pvt : productVariantTranslationsList) {
            se.info24.priceplansjaxb.ProductVariantTranslations pvtrans = new se.info24.priceplansjaxb.ProductVariantTranslations();
            pvtrans.setProductVariantID(pvt.getId().getProductVariantId());
            pvtrans.setProductVariantName(pvt.getProductVariantName());
            pvtrans.setCountryID(pvt.getId().getCountryId());
            productVariants.getProductVariantTranslations().add(pvtrans);
        }
        priceplan.getProductVariants().add(productVariants);
        pricePlans.getPricePlan().add(priceplan);
        tingcoPricePlans.setPricePlans(pricePlans);
        return tingcoPricePlans;
    }

    public TingcoPricePlans buildTingcoProducts(TingcoPricePlans tingcoPricePlans, Set<String> productIdsList) {
        se.info24.priceplansjaxb.PricePlans pricePlans = new se.info24.priceplansjaxb.PricePlans();
        se.info24.priceplansjaxb.PricePlan priceplan = new se.info24.priceplansjaxb.PricePlan();
        Products products = new Products();
        for (String s : productIdsList) {
            se.info24.priceplansjaxb.ProductTranslations ptrans = new se.info24.priceplansjaxb.ProductTranslations();
            ptrans.setProductID(s);
            products.getProductTranslations().add(ptrans);
        }
        priceplan.getProducts().add(products);
        pricePlans.getPricePlan().add(priceplan);
        tingcoPricePlans.setPricePlans(pricePlans);
        return tingcoPricePlans;
    }

    public TingcoPricePlans buildTingcoUserAliasTypes(TingcoPricePlans tingcoPricePlans, List<UserAliasTypes> userAliasTypesList) {
        se.info24.priceplansjaxb.PricePlans pricePlans = new se.info24.priceplansjaxb.PricePlans();
        se.info24.priceplansjaxb.PricePlan priceplan = new se.info24.priceplansjaxb.PricePlan();
        for (UserAliasTypes uat : userAliasTypesList) {
            se.info24.priceplansjaxb.UserAliasTypes userAliasTypes = new se.info24.priceplansjaxb.UserAliasTypes();
            userAliasTypes.setUserAliasTypeID(uat.getUserAliasTypeId());
            userAliasTypes.setUserAliasTypeName(uat.getUserAliasTypeName());
            priceplan.getUserAliasTypes().add(userAliasTypes);
        }
        pricePlans.getPricePlan().add(priceplan);
        tingcoPricePlans.setPricePlans(pricePlans);
        return tingcoPricePlans;
    }

    public TingcoPricePlans buildTingcoValidationDataType(TingcoPricePlans tingcoPricePlans, List<ValidationDataType> validationDataTypeList) {
        se.info24.priceplansjaxb.PricePlans pricePlans = new se.info24.priceplansjaxb.PricePlans();
        se.info24.priceplansjaxb.PricePlan priceplan = new se.info24.priceplansjaxb.PricePlan();
        se.info24.priceplansjaxb.PricePlanVersions ppv = new se.info24.priceplansjaxb.PricePlanVersions();
        se.info24.priceplansjaxb.PricePlanItems ppitems = new se.info24.priceplansjaxb.PricePlanItems();
        for (ValidationDataType vdt : validationDataTypeList) {
            ValidationDataTypes vdts = new ValidationDataTypes();
            vdts.setValidationDataTypeID(vdt.getValidationDataTypeId());
            vdts.setValidationDataType(vdt.getValidationDataType());
            ppitems.getValidationDataTypes().add(vdts);
        }
        ppv.getPricePlanItems().add(ppitems);
        priceplan.getPricePlanVersions().add(ppv);
        pricePlans.getPricePlan().add(priceplan);
        tingcoPricePlans.setPricePlans(pricePlans);
        return tingcoPricePlans;
    }

    public TingcoPricePlans buildTingcoValidationExpressions(TingcoPricePlans tingcoPricePlans, List<ValidationExpressions> validationExpressionsList) {
        se.info24.priceplansjaxb.PricePlans pricePlans = new se.info24.priceplansjaxb.PricePlans();
        se.info24.priceplansjaxb.PricePlan priceplan = new se.info24.priceplansjaxb.PricePlan();
        se.info24.priceplansjaxb.PricePlanVersions ppv = new se.info24.priceplansjaxb.PricePlanVersions();
        se.info24.priceplansjaxb.PricePlanItems ppitems = new se.info24.priceplansjaxb.PricePlanItems();
        for (ValidationExpressions ve : validationExpressionsList) {
            se.info24.priceplansjaxb.ValidationExpressions ves = new se.info24.priceplansjaxb.ValidationExpressions();
            ves.setValidationExpressionID(ve.getValidationExpressionId());
            ves.setValidationExpression(ve.getValidationExpression());
            ppitems.getValidationExpressions().add(ves);
        }
        ppv.getPricePlanItems().add(ppitems);
        priceplan.getPricePlanVersions().add(ppv);
        pricePlans.getPricePlan().add(priceplan);
        tingcoPricePlans.setPricePlans(pricePlans);
        return tingcoPricePlans;
    }

    public TingcoPricePlans buildTingcoChargeTypeTranslations(TingcoPricePlans tingcoPricePlans, List<ChargeTypeTranslations> chargeTypeTranslationsList) {
        se.info24.priceplansjaxb.PricePlans pricePlans = new se.info24.priceplansjaxb.PricePlans();
        se.info24.priceplansjaxb.PricePlan priceplan = new se.info24.priceplansjaxb.PricePlan();
        ChargeTypes ct = new ChargeTypes();
        for (ChargeTypeTranslations ctt : chargeTypeTranslationsList) {
            se.info24.priceplansjaxb.ChargeTypeTranslations cttrans = new se.info24.priceplansjaxb.ChargeTypeTranslations();
            cttrans.setChargeTypeID(ctt.getId().getChargeTypeId());
            cttrans.setCountryID(ctt.getId().getCountryId());
            cttrans.setChargeTypeName(ctt.getChargeTypeName());
            ct.getChargeTypeTranslations().add(cttrans);
        }
        priceplan.getChargeTypes().add(ct);
        pricePlans.getPricePlan().add(priceplan);
        tingcoPricePlans.setPricePlans(pricePlans);
        return tingcoPricePlans;
    }

    public TingcoPricePlans buildgetPricePlans(TingcoPricePlans tingcoPricePlans, List<PricePlans> pricePlanList, List<GroupTranslations> groupTranslationsList) {
        se.info24.priceplansjaxb.PricePlans pricePlans = new se.info24.priceplansjaxb.PricePlans();
        se.info24.priceplansjaxb.PricePlan priceplan = null;
        for (PricePlans pplan : pricePlanList) {
            String pricePlanName = null;
            priceplan = new se.info24.priceplansjaxb.PricePlan();
            priceplan.setPricePlanID(pplan.getPricePlanId());
            priceplan.setPricePlanName(pplan.getPricePlanName());
            pricePlanName = pplan.getPricePlanName();
            priceplan.setGroupID(pplan.getGroupId());
            for (GroupTranslations groupTranslations : groupTranslationsList) {
                if(groupTranslations.getGroups().getGroupId().equalsIgnoreCase(pplan.getGroupId())){
                    priceplan.setGroupName(groupTranslations.getGroupName());
                    pricePlanName = pricePlanName +" ("+groupTranslations.getGroupName()+")";
                }
            }
            priceplan.setPricePlanNameTCMV3(pricePlanName);
            pricePlans.getPricePlan().add(priceplan);
        }
        
        tingcoPricePlans.setPricePlans(pricePlans);
        return tingcoPricePlans;
    }

    public TingcoPricePlans buildTingcoPricePlansBySearchString(TingcoPricePlans tingcoPricePlans, List<PricePlans> pricePlanList,List<GroupTranslations> groupTranslationses) {
        if (pricePlanList.isEmpty()) {
            tingcoPricePlans.getMsgStatus().setResponseCode(-1);
            tingcoPricePlans.getMsgStatus().setResponseText("No Data found");
            return tingcoPricePlans;
        }
//        Collections.sort(pricePlanList, new Comparator<se.info24.pojo.PricePlans>() {
//            public int compare(se.info24.pojo.PricePlans o1, se.info24.pojo.PricePlans o2) {
//                return o1.getPricePlanName().compareToIgnoreCase(o2.getPricePlanName());
//            }
//        });
        se.info24.priceplansjaxb.PricePlans pricePlans = new se.info24.priceplansjaxb.PricePlans();
        int seqNo = 0;
        for (PricePlans pp : pricePlanList) {
            PricePlan p = new PricePlan();
            p.setPricePlanID(pp.getPricePlanId());
            p.setPricePlanName(pp.getPricePlanName());
            for(GroupTranslations gt : groupTranslationses){
                if(gt.getId().getGroupId().equalsIgnoreCase(pp.getGroupId())){
                    p.setGroupName(gt.getGroupName());
                    p.setGroupID(gt.getId().getGroupId());
                    break;
                }
            }
            p.setSeqNo(++seqNo);
            pricePlans.getPricePlan().add(p);
        }
        tingcoPricePlans.setPricePlans(pricePlans);
        return tingcoPricePlans;
    }
    
    public TingcoPricePlans buildGetPricePlansList(TingcoPricePlans tingcoPricePlans, List<PricePlans> pricePlanList, List<GroupTranslations> groupTranslationses, List<PricePlanTypeTranslations> pricePlanTypeTranslationses, String timeZoneGMToffset) throws DatatypeConfigurationException {
//        
//        Collections.sort(pricePlanList, new Comparator<se.info24.pojo.PricePlans>() {
//            public int compare(se.info24.pojo.PricePlans o1, se.info24.pojo.PricePlans o2) {
//                return o1.getPricePlanName().compareToIgnoreCase(o2.getPricePlanName());
//            }
//        });
        se.info24.priceplansjaxb.PricePlans pricePlans = new se.info24.priceplansjaxb.PricePlans();
        int seqNo = 0;
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        for (PricePlans pp : pricePlanList) {
            PricePlan p = new PricePlan();
            p.setPricePlanID(pp.getPricePlanId());
            p.setPricePlanName(pp.getPricePlanName());
            GregorianCalendar gc = new GregorianCalendar();
            if(pp.getActiveFromDate() != null){
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, pp.getActiveFromDate()));
                p.setActiveFromDate(dateFormat.format(gc.getTime()));
                p.setActiveFromDateTCMV3(dateFormat1.format(gc.getTime()));
            }
            if(pp.getActiveToDate() != null){
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, pp.getActiveToDate()));
                p.setActiveToDate(dateFormat.format(gc.getTime()));
                p.setActiveToDateTCMV3(dateFormat1.format(gc.getTime()));
            }
            for(GroupTranslations gt : groupTranslationses){
                if(gt.getId().getGroupId().equalsIgnoreCase(pp.getGroupId())){
                    p.setGroupName(gt.getGroupName());
                    p.setGroupID(gt.getId().getGroupId());
                    break;
                }
            }
            for(PricePlanTypeTranslations pptt : pricePlanTypeTranslationses){
                if(pp.getPricePlanTypes() != null && pp.getPricePlanTypes().getPricePlanTypeId().equalsIgnoreCase(pptt.getId().getPricePlanTypeId())){
                    PricePlanTypes pricePlanTypes = new PricePlanTypes();
                    se.info24.priceplansjaxb.PricePlanTypeTranslations ppttJaxb = new se.info24.priceplansjaxb.PricePlanTypeTranslations();
                    ppttJaxb.setPricePlanTypeID(pptt.getId().getPricePlanTypeId());
                    ppttJaxb.setPricePlanTypeName(pptt.getPricePlanTypeName());
                    pricePlanTypes.getPricePlanTypeTranslations().add(ppttJaxb);
                    p.getPricePlanTypes().add(pricePlanTypes);
                }
            }
            p.setSeqNo(++seqNo);
            pricePlans.getPricePlan().add(p);
        }
        tingcoPricePlans.setPricePlans(pricePlans);
        return tingcoPricePlans;
    }

    public TingcoPricePlans buildPricePlanDetailsById(TingcoPricePlans tingcoPricePlans, String timeZoneGMToffset, PricePlans pricePlans, GroupTranslations groupTranslations, Object pptt) throws DatatypeConfigurationException {
        se.info24.priceplansjaxb.PricePlans pricePlansJaxb = new se.info24.priceplansjaxb.PricePlans();
        GregorianCalendar gc = new GregorianCalendar();
        int seqNo = 0;
        PricePlan p = new PricePlan();
        p.setPricePlanName(pricePlans.getPricePlanName());
        p.setPricePlanDescription(pricePlans.getPricePlanDescription());
        p.setGroupID(pricePlans.getGroupId());
        p.setIsEnabled(pricePlans.getIsEnabled());
        if(groupTranslations!=null){
            p.setGroupName(groupTranslations.getGroupName());
        }
        p.setPricePlanTypeID(pricePlans.getPricePlanTypes().getPricePlanTypeId());
        if(pptt!=null){
            PricePlanTypeTranslations ppttObj = (PricePlanTypeTranslations) pptt;
            PricePlanTypes planTypes = new PricePlanTypes();
            se.info24.priceplansjaxb.PricePlanTypeTranslations planTypeTranslationsJaxb = new se.info24.priceplansjaxb.PricePlanTypeTranslations();
            planTypeTranslationsJaxb.setPricePlanTypeName(ppttObj.getPricePlanTypeName());
            planTypes.getPricePlanTypeTranslations().add(planTypeTranslationsJaxb);
            p.getPricePlanTypes().add(planTypes);
        }
        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, pricePlans.getActiveFromDate()));
        p.setActiveFromDate(dateFormat.format(gc.getTime()));
        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, pricePlans.getActiveToDate()));
        p.setActiveToDate(dateFormat.format(gc.getTime()));
        p.setSeqNo(++seqNo);
        pricePlansJaxb.getPricePlan().add(p);
        tingcoPricePlans.setPricePlans(pricePlansJaxb);
        return tingcoPricePlans;
    }

    public TingcoPricePlans buildVersionsByPriceId(TingcoPricePlans tingcoPricePlans, List<PricePlanVersions> pricePlanVersionsList, List<Currency> currencys) {
        se.info24.priceplansjaxb.PricePlans pricePlans = new se.info24.priceplansjaxb.PricePlans();
        se.info24.priceplansjaxb.PricePlan pricePlan = new se.info24.priceplansjaxb.PricePlan();
        for (PricePlanVersions ppv : pricePlanVersionsList) {
            String versionName = null;
            se.info24.priceplansjaxb.PricePlanVersions ppverJaxb = new se.info24.priceplansjaxb.PricePlanVersions();
            ppverJaxb.setPricePlanVersionID(ppv.getPricePlanVersionId());
            ppverJaxb.setVersionName(ppv.getVersionName());
            versionName = ppv.getVersionName();
            for(Currency currency : currencys){
                if(currency.getCurrencyId()==ppv.getCurrencyId()){
                    ppverJaxb.setCurrencyISOCharCode(currency.getCurrencyIsocharCode());
                    versionName = versionName+ " ("+currency.getCurrencyIsocharCode()+")";
                    break;
                }                
            }
            
             ppverJaxb.setVersionNameTCMV3(versionName);
            pricePlan.getPricePlanVersions().add(ppverJaxb);
        }
        pricePlans.getPricePlan().add(pricePlan);
        tingcoPricePlans.setPricePlans(pricePlans);
        return tingcoPricePlans;
    }

}
