/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.jaxbUtil;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Session;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.TransactionProducts;
import se.info24.ismOperationsPojo.TransactionResult;
import se.info24.pojo.ChargeTypeTranslations;
import se.info24.pojo.ContentCategories;
import se.info24.pojo.ContentCategoryTranslations;
import se.info24.pojo.Country;
import se.info24.pojo.Currency;
import se.info24.pojo.Device;
import se.info24.pojo.GroupLimitPackages;
import se.info24.pojo.GroupProductVariantSubscriptions;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.ObjectImages;
import se.info24.pojo.ObjectProductVariantPrices;
import se.info24.pojo.ProductAttributes;
import se.info24.pojo.ProductCategoryTranslations;
import se.info24.pojo.ProductTranslations;
import se.info24.pojo.ProductTypes;
import se.info24.pojo.ProductVariantAttributes;
import se.info24.pojo.ProductVariantPrices;
import se.info24.pojo.ProductVariantTranslations;
import se.info24.pojo.ProductVariants;
import se.info24.pojo.Products;
import se.info24.pojo.ProductsInGroups;
import se.info24.pojo.Providers;
import se.info24.pojo.ServiceClientLogins;
import se.info24.pojo.ServiceContentSubscriptions;
import se.info24.pojo.ServiceSubscriptionAcl;
import se.info24.pojo.Users2;
import se.info24.priceplans.PricePlanDAO;
import se.info24.productsjaxb.ContentCategory;
import se.info24.productsjaxb.CurrencyID;
import se.info24.productsjaxb.MsgStatus;
import se.info24.productsjaxb.ObjectFactory;
import se.info24.productsjaxb.Product;
import se.info24.productsjaxb.ProductCategories;
import se.info24.productsjaxb.ProductVariantID;
import se.info24.productsjaxb.ServiceSubscriptionACL;
import se.info24.productsjaxb.TingcoProducts;
import se.info24.restUtil.RestUtilDAO;
import se.info24.user.CountryDAO;
import se.info24.util.TCMUtil;

/**
 *
 * @author Vijayakumar
 */
public class TingcoProductsXML {

    private TingcoProducts tingcoProducts;
    ObjectFactory objectFactory;
    GroupDAO groupDAO = new GroupDAO();

    public TingcoProductsXML() {
        objectFactory = new ObjectFactory();
    }

    public TingcoProducts buildContentCategories(TingcoProducts tingcoProducts, ContentCategories cc, List<Country> countries) {
        se.info24.productsjaxb.Products products = new se.info24.productsjaxb.Products();
        Product product = new Product();
        se.info24.productsjaxb.ContentCategories contentCategories = new se.info24.productsjaxb.ContentCategories();
        ContentCategory contentCategory = new ContentCategory();
        if (cc.getContentCategoryParentId() != null) {
            contentCategory.setContentCategoryParentID(cc.getContentCategoryParentId());
        }
        Set<ContentCategoryTranslations> cctList = cc.getContentCategoryTranslationses();
        for (Country country : countries) {
            se.info24.productsjaxb.ContentCategoryTranslations contentCategoryTrans = new se.info24.productsjaxb.ContentCategoryTranslations();
            contentCategoryTrans.setCountryID(country.getCountryId());
            contentCategoryTrans.setCountryName(country.getCountryName());
            for (ContentCategoryTranslations cct : cctList) {
                if (country.getCountryId() == cct.getId().getCountryId()) {
                    contentCategoryTrans.setContentCategoryName(cct.getContentCategoryName());
                }

            }
            contentCategory.getContentCategoryTranslations().add(contentCategoryTrans);
        }

        contentCategories.getContentCategory().add(contentCategory);
        product.getContentCategories().add(contentCategories);
        products.getProduct().add(product);
        tingcoProducts.setProducts(products);
        return tingcoProducts;
    }

    public TingcoProducts buildTingcoGroupLimitPackages(TingcoProducts tingcoProducts, List<GroupLimitPackages> groupLimitPackagesList) {
        se.info24.productsjaxb.Products products = new se.info24.productsjaxb.Products();
        Product product = new Product();
        for (GroupLimitPackages glp : groupLimitPackagesList) {
            se.info24.productsjaxb.GroupLimitPackages groupLimitPackages = new se.info24.productsjaxb.GroupLimitPackages();
            groupLimitPackages.setGroupLimitPackageID(glp.getGroupLimitPackageId());
            if (glp.getPackageName() != null) {
                groupLimitPackages.setPackageName(glp.getPackageName());
            }
            if (glp.getPackageDescription() != null) {
                groupLimitPackages.setPackageDescription(glp.getPackageDescription());
            }
            product.getGroupLimitPackages().add(groupLimitPackages);
        }
        products.getProduct().add(product);
        tingcoProducts.setProducts(products);
        return tingcoProducts;
    }

    public TingcoProducts buildTingcoProductCategoryTranslations(TingcoProducts tingcoProducts, List<ProductCategoryTranslations> productCategoryTranslationsList) {
        se.info24.productsjaxb.Products products = new se.info24.productsjaxb.Products();
        Product product = new Product();
        se.info24.productsjaxb.ProductCategories productCategories = new se.info24.productsjaxb.ProductCategories();
        for (ProductCategoryTranslations pct : productCategoryTranslationsList) {
            se.info24.productsjaxb.ProductCategoryTranslations pctrans = new se.info24.productsjaxb.ProductCategoryTranslations();
            pctrans.setProductCategoryID(pct.getId().getProductCategoryId());
            pctrans.setCountryID(pct.getId().getCountryId());
            pctrans.setProductCategoryName(pct.getProductCategoryName());
            productCategories.getProductCategoryTranslations().add(pctrans);
        }
        product.getProductCategories().add(productCategories);
        products.getProduct().add(product);
        tingcoProducts.setProducts(products);
        return tingcoProducts;
    }

    public TingcoProducts buildTingcoProductDetails(TingcoProducts tingcoProducts, List<Products> productsList, List<ProductsInGroups> productsInGroupsList, List<ProductAttributes> productAttributesList, List<ObjectImages> objectImagesList, List<ProductTranslations> productTranslationses) {
        se.info24.productsjaxb.Products products = new se.info24.productsjaxb.Products();
        for (ProductTranslations pt : productTranslationses) {
            for (Products p : productsList) {
                if (pt.getId().getProductId().equalsIgnoreCase(p.getProductId())) {
                    for (ProductsInGroups pig : productsInGroupsList) {
                        if (p.getProductId().equalsIgnoreCase(pig.getId().getProductId())) {
                            Product product = new Product();
                            se.info24.productsjaxb.ProductTranslations productTrans = new se.info24.productsjaxb.ProductTranslations();
                            productTrans.setProductID(pt.getId().getProductId());
                            productTrans.setCountryID(pt.getId().getCountryId());
                            productTrans.setProductName(pt.getProductName());
                            productTrans.setProductDescription(pt.getProductDescription());
                            product.getProductTranslations().add(productTrans);


                            for (ProductAttributes pa : productAttributesList) {
                                if ((pa.getProducts().getProductId().equalsIgnoreCase(p.getProductId())) && (pa.getAttributeName().equalsIgnoreCase("Provider") || pa.getAttributeName().equalsIgnoreCase("MoreInfoURL"))) {
                                    se.info24.productsjaxb.ProductAttributes productAttributes = new se.info24.productsjaxb.ProductAttributes();
                                    productAttributes.setAttributeName(pa.getAttributeName());
                                    productAttributes.setAttributeValue(pa.getAttributeValue());
                                    product.getProductAttributes().add(productAttributes);
                                }
                            }
                            for (ObjectImages oi : objectImagesList) {
                                if (oi.getObjectId().equalsIgnoreCase(p.getProductId())) {
                                    se.info24.productsjaxb.ObjectImages objectImages = new se.info24.productsjaxb.ObjectImages();
                                    objectImages.setObjectImageSmallURL(oi.getObjectImageSmallUrl());
                                    product.getObjectImages().add(objectImages);
                                }
                            }
                            products.getProduct().add(product);
                        }
                    }
                }
            }
        }

        tingcoProducts.setProducts(products);
        return tingcoProducts;
    }

    public TingcoProducts buildTingcoProductVariants(TingcoProducts tingcoProducts, List<ProductVariants> productVariantsList, List<ProductVariantTranslations> pvtList) {
        se.info24.productsjaxb.Products products = new se.info24.productsjaxb.Products();
        Product product = new Product();
        for (ProductVariantTranslations pvt : pvtList) {
            for (ProductVariants pv : productVariantsList) {
                if (pvt.getId().getProductVariantId().equalsIgnoreCase(pv.getProductVariantId())) {
                    se.info24.productsjaxb.ProductVariants pvs = new se.info24.productsjaxb.ProductVariants();
                    if (pv.getProductVariantSKU() != null) {
                        pvs.setProductVariantSKU(pv.getProductVariantSKU());
                        if (!pvt.getProductVariantName().trim().equalsIgnoreCase("")) {
                            pvs.setProductVariantNameTCMV3(pvt.getProductVariantName() + "(" + pv.getProductVariantSKU() + ")");
                        } else {
                            pvs.setProductVariantNameTCMV3("(" + pv.getProductVariantSKU() + ")");
                        }
                    }

                    se.info24.productsjaxb.ProductVariantTranslations pvts = new se.info24.productsjaxb.ProductVariantTranslations();
                    pvts.setProductVariantID(pvt.getId().getProductVariantId());
                    if (!pvt.getProductVariantName().trim().equalsIgnoreCase("")) {
                        pvts.setProductVariantName(pvt.getProductVariantName());

                    }
                    pvts.setProductVariantDescription(pvt.getProductVariantDescription());
                    pvs.getProductVariantTranslations().add(pvts);
                    product.getProductVariants().add(pvs);
                }
            }
        }
        products.getProduct().add(product);
        tingcoProducts.setProducts(products);
        return tingcoProducts;
    }

    public TingcoProducts buildTingcoProducts(TingcoProducts tingcoProducts, List<GroupProductVariantSubscriptions> groupProductVariantSubList,
            List<ProductVariants> productVariantsList, List<Products> productsList, List<ServiceSubscriptionAcl> serviceSubACLList,
            List<ServiceClientLogins> serviceClientLoginsList, List<ProductAttributes> productAttributesList, List<ObjectImages> objectImagesList,
            List<ServiceContentSubscriptions> serviceContentSubList, List<ContentCategoryTranslations> contentCategoryTransList, int countryId) throws DatatypeConfigurationException {
        se.info24.productsjaxb.Products products = new se.info24.productsjaxb.Products();
        int seqNo = 1;
        try {
            for (GroupProductVariantSubscriptions gpvs : groupProductVariantSubList) {
                for (ProductVariants pv : productVariantsList) {
                    if (gpvs.getId().getProductVariantId().equalsIgnoreCase(pv.getProductVariantId())) {
                        for (Products p : productsList) {
                            if (p.getProductId().equalsIgnoreCase(pv.getProducts().getProductId())) {
                                Product product = new Product();
                                product.setSeqNo(seqNo++);
                                Set<ProductTranslations> ptSet = p.getProductTranslationses();
                                for (ProductTranslations pt : ptSet) {
                                    if (pt.getId().getCountryId() == countryId) {
                                        se.info24.productsjaxb.ProductTranslations ptrans = new se.info24.productsjaxb.ProductTranslations();
                                        ptrans.setProductID(pt.getId().getProductId());
                                        ptrans.setProductName(pt.getProductName());
                                        product.getProductTranslations().add(ptrans);
                                    }
                                }
                                for (ServiceClientLogins scl : serviceClientLoginsList) {
                                    if (scl.getServiceClientLoginId().equalsIgnoreCase(gpvs.getId().getServiceClientLoginId())) {
                                        se.info24.productsjaxb.ServiceClientLogins scls = new se.info24.productsjaxb.ServiceClientLogins();
                                        scls.setServiceClientLoginID(scl.getServiceClientLoginId());
                                        scls.setServiceClientLoginName(scl.getServiceClientLoginName());
                                        scls.setServiceClientLoginDescription(scl.getServiceClientLoginDescription());
                                        scls.setClientLogin(scl.getClientLogin());
                                        product.getServiceClientLogins().add(scls);
                                    }
                                }

                                for (ServiceSubscriptionAcl ssa : serviceSubACLList) {
                                    if (ssa.getServiceSubscriptionAclid().equalsIgnoreCase(gpvs.getId().getServiceSubscriptionAclid())) {
                                        ServiceSubscriptionACL ssacl = new ServiceSubscriptionACL();
                                        GregorianCalendar gc = new GregorianCalendar();
                                        gc.setTime(ssa.getActiveToDate());
                                        ssacl.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                                        ssacl.setServiceSubscriptionACLID(ssa.getServiceSubscriptionAclid());
                                        product.getServiceSubscriptionACL().add(ssacl);
                                    }
                                }

                                for (ProductAttributes pa : productAttributesList) {
                                    if (pa.getProducts().getProductId().equalsIgnoreCase(p.getProductId())) {
                                        se.info24.productsjaxb.ProductAttributes productAttributes = new se.info24.productsjaxb.ProductAttributes();
                                        productAttributes.setAttributeValue(pa.getAttributeValue());
                                        product.getProductAttributes().add(productAttributes);
                                    }
                                }

                                for (ObjectImages oi : objectImagesList) {
                                    if (oi.getObjectId().equalsIgnoreCase(p.getProductId())) {
                                        se.info24.productsjaxb.ObjectImages objectImages = new se.info24.productsjaxb.ObjectImages();
                                        objectImages.setObjectImageSmallURL(oi.getObjectImageSmallUrl());
                                        product.getObjectImages().add(objectImages);
                                    }
                                }

                                se.info24.productsjaxb.ContentCategories cc = new se.info24.productsjaxb.ContentCategories();
                                for (ServiceContentSubscriptions scs : serviceContentSubList) {
                                    if (gpvs.getId().getServiceClientLoginId().equalsIgnoreCase(scs.getServiceClientLoginId()) && gpvs.getId().getServiceSubscriptionAclid().equalsIgnoreCase(scs.getServiceSubscriptionAclid())) {
                                        for (ContentCategoryTranslations cct : contentCategoryTransList) {
                                            if (cct.getId().getContentCategoryId().equalsIgnoreCase(scs.getContentCategoryId()) && cct.getId().getCountryId() == countryId) {
                                                ContentCategory ccg = new ContentCategory();
                                                se.info24.productsjaxb.ContentCategoryTranslations ccts = new se.info24.productsjaxb.ContentCategoryTranslations();
                                                ccts.setContentCategoryID(cct.getContentCategories().getContentCategoryId());
                                                ccts.setContentCategoryName(cct.getContentCategoryName());
                                                ccg.getContentCategoryTranslations().add(ccts);
                                                cc.getContentCategory().add(ccg);
                                            }
                                        }
                                    }
                                }
                                product.getContentCategories().add(cc);

                                se.info24.productsjaxb.ProductVariants pvs = new se.info24.productsjaxb.ProductVariants();
                                Set<ProductVariantTranslations> pvtSet = pv.getProductVariantTranslationses();
                                for (ProductVariantTranslations pvt : pvtSet) {
                                    if (pvt.getId().getCountryId() == countryId && gpvs.getId().getProductVariantId().equalsIgnoreCase(pvt.getId().getProductVariantId())) {
                                        se.info24.productsjaxb.ProductVariantTranslations pvts = new se.info24.productsjaxb.ProductVariantTranslations();
                                        pvts.setProductVariantID(pvt.getId().getProductVariantId());
                                        pvts.setProductVariantName(pvt.getProductVariantName());
                                        pvts.setProductVariantDescription(pvt.getProductVariantDescription());
                                        pvs.getProductVariantTranslations().add(pvts);
                                        product.getProductVariants().add(pvs);
                                    }
                                }
                                products.getProduct().add(product);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        }
        tingcoProducts.setProducts(products);
        return tingcoProducts;
    }

    public TingcoProducts buildTingcoProductsTemplate() throws DatatypeConfigurationException {
        tingcoProducts = objectFactory.createTingcoProducts();
        tingcoProducts.setCreateRef("Info24");
        tingcoProducts.setMsgVer(new BigDecimal("1.0"));
        tingcoProducts.setRegionalUnits("Metric");
        tingcoProducts.setTimeZone("UTC");

        tingcoProducts.setMsgID(UUID.randomUUID().toString());
        tingcoProducts.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        MsgStatus msgStatus = new MsgStatus();
        msgStatus.setResponseCode(0);
        msgStatus.setResponseText("OK");

        tingcoProducts.setMsgStatus(msgStatus);
        return tingcoProducts;
    }

    public TingcoProducts buildTingcoProductDetailByProductId(TingcoProducts tingcoProducts, ProductCategoryTranslations pct, ProductTranslations pts, List<ProductAttributes> paList) {
        se.info24.productsjaxb.Products products = new se.info24.productsjaxb.Products();
        se.info24.productsjaxb.Product product = new Product();
        se.info24.productsjaxb.ProductCategories pc = new se.info24.productsjaxb.ProductCategories();
        se.info24.productsjaxb.ProductCategoryTranslations productcategoryTrans = new se.info24.productsjaxb.ProductCategoryTranslations();
        productcategoryTrans.setProductCategoryID(pct.getId().getProductCategoryId());
        productcategoryTrans.setProductCategoryName(pct.getProductCategoryName());
        productcategoryTrans.setCountryID(pct.getId().getCountryId());
        pc.getProductCategoryTranslations().add(productcategoryTrans);
        product.getProductCategories().add(pc);

        se.info24.productsjaxb.ProductTranslations productTranslations = new se.info24.productsjaxb.ProductTranslations();
        productTranslations.setProductID(pts.getProducts().getProductId());
        productTranslations.setProductName(pts.getProductName());
        productTranslations.setProductDescription(pts.getProductDescription().toString());
        productTranslations.setCountryID(pts.getId().getCountryId());
        product.getProductTranslations().add(productTranslations);

        se.info24.productsjaxb.ProductAttributes productAttribute = null;
        for (ProductAttributes productAttributes : paList) {
            if (productAttributes.getAttributeName().equalsIgnoreCase("Provider") || productAttributes.getAttributeName().equalsIgnoreCase("LegalTermsURL")) {
                productAttribute = new se.info24.productsjaxb.ProductAttributes();
                productAttribute.setAttributeValue(productAttributes.getAttributeValue());
                productAttribute.setAttributeName(productAttributes.getAttributeName());
                product.getProductAttributes().add(productAttribute);
            }
        }
        products.getProduct().add(product);
        tingcoProducts.setProducts(products);
        return tingcoProducts;
    }

    public TingcoProducts buildTingcoProducts(TingcoProducts tingcoProducts, List<ProductTranslations> productTransList) {
        se.info24.productsjaxb.Products products = new se.info24.productsjaxb.Products();
        int seqNo = 1;
        for (se.info24.pojo.ProductTranslations pr : productTransList) {
            se.info24.productsjaxb.Product product = new Product();
            product.setSeqNo(seqNo++);
            product.setProductID(pr.getId().getProductId());
            se.info24.productsjaxb.ProductTranslations productTranslations = new se.info24.productsjaxb.ProductTranslations();
            productTranslations.setProductName(pr.getProductName());
            productTranslations.setProductDescription(pr.getProductDescription());
            productTranslations.setCountryID(pr.getId().getCountryId());
            product.getProductTranslations().add(productTranslations);
            products.getProduct().add(product);
        }
        tingcoProducts.setProducts(products);
        return tingcoProducts;
    }

    public TingcoProducts buildTingcoServiceClientLogins(TingcoProducts tingcoProducts, List<ServiceClientLogins> list) {
        se.info24.productsjaxb.Products products = new se.info24.productsjaxb.Products();
        se.info24.productsjaxb.Product product = new Product();
        int seqNo = 1;
        product.setSeqNo(seqNo);
        for (ServiceClientLogins scl : list) {
            se.info24.productsjaxb.ServiceClientLogins serviceClientLogins = new se.info24.productsjaxb.ServiceClientLogins();
            serviceClientLogins.setSeqNo(seqNo++);
            serviceClientLogins.setServiceClientLoginID(scl.getServiceClientLoginId());
            serviceClientLogins.setServiceClientLoginName(scl.getServiceClientLoginName());
            if (scl.getServiceClientLoginDescription() != null) {
                serviceClientLogins.setServiceClientLoginDescription(scl.getServiceClientLoginDescription());
            }
            product.getServiceClientLogins().add(serviceClientLogins);
        }
        products.getProduct().add(product);
        tingcoProducts.setProducts(products);
        return tingcoProducts;
    }

    public TingcoProducts buildPurchaseTransactionDetails(TingcoProducts tingcoProducts, List<TransactionProducts> transProductsList, List<TransactionResult> transResultList, String timeZoneGMToffset, Session ismSession, int countryId) throws DatatypeConfigurationException {
        se.info24.productsjaxb.Transactions transactions = new se.info24.productsjaxb.Transactions();
        RestUtilDAO utilDAO = new RestUtilDAO();
        PricePlanDAO pricePlanDAO = new PricePlanDAO();
        int seqNo = 1;
        DecimalFormat decimalFormatter = new DecimalFormat("#.##");
        if (transProductsList != null) {
            for (TransactionProducts tp : transProductsList) {
                se.info24.productsjaxb.TransactionProducts transProducts = new se.info24.productsjaxb.TransactionProducts();
                transProducts.setSeqNo(seqNo++);
                transProducts.setProductVariantID(tp.getId().getProductVariantId());
                transProducts.setProductVariantSKU(tp.getProductVariantSku());
                transProducts.setProductVariantName(tp.getProductVariantName());
                transProducts.setQuantity(tp.getQuantity() == null ? 0 : tp.getQuantity());
                transProducts.setQuantityUnit(tp.getQuantityUnit() == null ? "" : tp.getQuantityUnit());
                transProducts.setVAT(decimalFormatter.format(Float.valueOf(tp.getVat() == null ? 0 : tp.getVat().floatValue())));
                transProducts.setBayNumber(tp.getBayNumber() == null ? 0 : tp.getBayNumber());
                transProducts.setPPU(decimalFormatter.format(Float.valueOf(tp.getPpu() == null ? 0 : (float) tp.getPpu() / 100)));
                ChargeTypeTranslations chargeTrans = pricePlanDAO.getChargeTypeTranslations(ismSession, tp.getChargeType(), countryId);
                if (chargeTrans != null) {
                    se.info24.productsjaxb.ChargeTypeTranslations trans = new se.info24.productsjaxb.ChargeTypeTranslations();
                    trans.setChargeTypeName(chargeTrans.getChargeTypeName());
                    trans.setCountryID(chargeTrans.getId().getCountryId());
                    transProducts.getChargeTypeTranslations().add(trans);
                }
                transactions.getTransactionProducts().add(transProducts);
            }
        }

        seqNo = 1;
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (TransactionResult tr : transResultList) {
            se.info24.productsjaxb.TransactionResult transResults = new se.info24.productsjaxb.TransactionResult();
            transResults.setSeqNo(seqNo++);
            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
            transResults.setTransactionID(tr.getTransactionId());
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, tr.getTransactionStartTime()));
            transResults.setTransactionStartTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            transResults.setTransactionStartTimeTCMV3(dateFormat2.format(gc.getTime()));

            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, tr.getCreatedDate()));
            transResults.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            transResults.setAmount(tr.getAmount());
            transResults.setTransactionAccount(tr.getTransactionAccount() == null ? "" : tr.getTransactionAccount());
            transResults.setTransactionNote(tr.getTransactionNote() == null ? "" : tr.getTransactionNote());
            transResults.setFirstName(tr.getFirstName() == null ? "" : tr.getFirstName());
            transResults.setLastName(tr.getLastName() == null ? "" : tr.getLastName());
            transResults.setUserAliasTypeName(tr.getUserAliasTypeName() == null ? "" : tr.getUserAliasTypeName());
            transResults.setUserAlias(tr.getUserAlias() == null ? "" : tr.getUserAlias());
            transResults.setDeviceName(tr.getDeviceName() == null ? "" : tr.getDeviceName());
            transResults.setProviderName(tr.getProviderName() == null ? "" : tr.getProviderName());
            transResults.setIsPurchase(tr.getIsPurchase() == null ? 0 : tr.getIsPurchase());
            transResults.setIsInvoiced(tr.getIsInvoiced() == null ? 0 : tr.getIsInvoiced());
            transResults.setIsPaid(tr.getIsPaid() == null ? 0 : tr.getIsPaid());
            transResults.setIsPayment(tr.getIsPayment() == null ? 0 : tr.getIsPayment());
            transResults.setExternalTransactionID(tr.getExternalTransactionId() == null ? "0" : tr.getExternalTransactionId());
            Currency currency = utilDAO.getCurrencyById(String.valueOf(tr.getCurrencyId()), ismSession);
            if (currency != null) {
                se.info24.productsjaxb.Currency currencyJaxb = new se.info24.productsjaxb.Currency();
                transResults.setCurrencyID(currency.getCurrencyId());
                currencyJaxb.setCurrencyID(currency.getCurrencyId());
                currencyJaxb.setCurrencyName(currency.getCurrencyName());
                transResults.getCurrency().add(currencyJaxb);
            }
            GroupTranslations grpTrans = groupDAO.getGroupTranslationsByIds(tr.getDeviceGroupId(), countryId, ismSession);
            if (grpTrans != null) {
                if (grpTrans.getId() != null) {
                    se.info24.productsjaxb.GroupTranslations trans = new se.info24.productsjaxb.GroupTranslations();
                    trans.setCountryID(grpTrans.getId() == null ? 0 : grpTrans.getId().getCountryId());
                    trans.setGroupName(grpTrans.getGroupName() == null ? "" : grpTrans.getGroupName());
                    transResults.getGroupTranslations().add(trans);
                }
            }
            transactions.getTransactionResult().add(transResults);
        }
        tingcoProducts.setTransactions(transactions);
        return tingcoProducts;
    }

    public TingcoProducts buildDeviceEventTrigger(TingcoProducts tingcoProducts, List<String> productVariantidList, List<se.info24.pojo.ProductVariantDevices> productVariantDevicese, List<ProductVariantTranslations> productVariantTransList, String timeZoneGMToffset) throws DatatypeConfigurationException {
        se.info24.productsjaxb.Products products = new se.info24.productsjaxb.Products();
        se.info24.productsjaxb.Product product = null;
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        for (ProductVariantTranslations productVariantTranslations : productVariantTransList) {
            for (String productVariantid : productVariantidList) {
                if (productVariantTranslations.getId().getProductVariantId().equalsIgnoreCase(productVariantid)) {
                    product = new Product();
                    for (se.info24.pojo.ProductVariantDevices pvd : productVariantDevicese) {
                        if (productVariantid.equalsIgnoreCase(pvd.getId().getProductVariantId())) {
                            se.info24.productsjaxb.ProductVariantDevices pvdevice = new se.info24.productsjaxb.ProductVariantDevices();
                            pvdevice.setProductVariantID(productVariantid);
                            pvdevice.setIsEnabled(pvd.getIsEnabled());
                            pvdevice.setIsDefault(pvd.getIsDefault() + "");
                            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, pvd.getActiveFromDate()));
                            pvdevice.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                            pvdevice.setActiveFromDateTCMV3(lFormat.format(gc.getTime()));
                            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, pvd.getActiveToDate()));
                            pvdevice.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                            pvdevice.setActiveToDateTCMV3(lFormat.format(gc.getTime()));
                            pvdevice.setDisplayOrder(pvd.getDisplayOrder());
                            pvdevice.setBayNumber(pvd.getBayNumber());
                            product.getProductVariantDevices().add(pvdevice);
                        }
                    }

                    if (productVariantTranslations.getId().getProductVariantId().equalsIgnoreCase(productVariantid)) {
                        se.info24.productsjaxb.ProductVariants pv = new se.info24.productsjaxb.ProductVariants();
                        se.info24.productsjaxb.ProductVariantTranslations pvt = new se.info24.productsjaxb.ProductVariantTranslations();
                        pvt.setProductVariantName(productVariantTranslations.getProductVariantName());
                        pvt.setCountryID(productVariantTranslations.getId().getCountryId());
                        pvt.setProductVariantID(productVariantTranslations.getId().getProductVariantId());
                        pv.getProductVariantTranslations().add(pvt);
                        product.getProductVariants().add(pv);
                    }
                }

            }
            products.getProduct().add(product);
        }
        tingcoProducts.setProducts(products);
        return tingcoProducts;
    }

    public TingcoProducts buildProductVariantInformation(TingcoProducts tingcoProducts, ProductVariants variants, List<ProductVariantTranslations> pvtList, List<ProductVariantAttributes> attributesList, List<ProductVariantPrices> pricesList, Session ismSession) {
        se.info24.productsjaxb.Products products = new se.info24.productsjaxb.Products();
        se.info24.productsjaxb.Product product = new Product();

        CountryDAO countryDao = new CountryDAO();

        se.info24.productsjaxb.ProductVariants pv = new se.info24.productsjaxb.ProductVariants();
        pv.setIsDefault(variants.getIsDefault() == null ? 0 : variants.getIsDefault());
        pv.setIsEnabled(variants.getIsEnabled() == null ? 0 : variants.getIsEnabled());
        pv.setDisplayInWebShop(variants.getDisplayInWebShop() == null ? 0 : variants.getDisplayInWebShop());
        pv.setProductVariantSKU(variants.getProductVariantSKU() == null ? "" : variants.getProductVariantSKU());

        for (ProductVariantTranslations productVariantTranslations1 : pvtList) {
            se.info24.productsjaxb.ProductVariantTranslations productVariantTranslations = new se.info24.productsjaxb.ProductVariantTranslations();
            productVariantTranslations.setCountryID(productVariantTranslations1.getId().getCountryId());
            productVariantTranslations.setProductVariantID(productVariantTranslations1.getId().getProductVariantId());
            productVariantTranslations.setProductVariantName(productVariantTranslations1.getProductVariantName());
            pv.getProductVariantTranslations().add(productVariantTranslations);
        }
        for (ProductVariantAttributes attributes : attributesList) {
            se.info24.productsjaxb.ProductVariantAttributes pva = new se.info24.productsjaxb.ProductVariantAttributes();
            pva.setProductVariantAttributeID(attributes.getProductVariantAttributeId());
            pva.setAttributeName(attributes.getProductVariantAttributeId());
            if (attributes.getAttributeName() != null) {
                pva.setAttributeName(attributes.getAttributeName());
            }
            if (attributes.getAttributeValue() != null) {
                pva.setAttributeValue(attributes.getAttributeValue());
            }
            pv.getProductVariantAttributes().add(pva);
        }

        List<Country> list = countryDao.getAllCountries(ismSession);

        for (Country country : list) {
            for (ProductVariantPrices prices : pricesList) {
                if (country.getCountryId() == prices.getCountry().getCountryId()) {
                    se.info24.productsjaxb.ProductVariantPrices pvp = new se.info24.productsjaxb.ProductVariantPrices();
                    Country countryData = prices.getCountry();
                    if (countryData != null) {
                        se.info24.productsjaxb.Country countryJaxb = new se.info24.productsjaxb.Country();
                        se.info24.productsjaxb.Currency currencyJaxb = new se.info24.productsjaxb.Currency();
                        countryJaxb.setCountryID(prices.getId().getCountryId());
                        countryJaxb.setCurrencyID(countryData.getCurrency().getCurrencyId());
                        countryJaxb.setCountryName(countryData.getCountryName());
                        currencyJaxb.setCurrencyISOCharCode(countryData.getCurrency().getCurrencyIsocharCode());
                        currencyJaxb.setCurrencyID(countryData.getCurrency().getCurrencyId());
                        countryJaxb.getCurrency().add(currencyJaxb);
                        pvp.getCountry().add(countryJaxb);
                    }
                    if (prices.getPricePerUnitExclVat() != null) {
                        pvp.setPricePerUnitExclVAT(prices.getPricePerUnitExclVat());
                    }
                    if (prices.getVatpercent() != null) {
                        pvp.setVATPercent(prices.getVatpercent());
                    }
                    if (prices.getQuantityUnit() != null) {
                        pvp.setQuantityUnit(prices.getQuantityUnit());
                    }
                    pv.getProductVariantPrices().add(pvp);
                }
            }
        }



        product.getProductVariants().add(pv);
        products.getProduct().add(product);
        tingcoProducts.setProducts(products);
        return tingcoProducts;
    }

    public TingcoProducts buildGetProductVariantTranslations(TingcoProducts tingcoProducts, List<Country> countryList, List<ProductVariantTranslations> pvtList) {
        se.info24.productsjaxb.Products products = new se.info24.productsjaxb.Products();
        se.info24.productsjaxb.Product product = new Product();
        int seqNo = 1;
        product.setSeqNo(seqNo++);
        se.info24.productsjaxb.ProductVariants pv = new se.info24.productsjaxb.ProductVariants();
        boolean countryadded = false;
        for (Country country : countryList) {
            for (ProductVariantTranslations pvt : pvtList) {
                if (pvt.getId().getCountryId() == country.getCountryId()) {
                    se.info24.productsjaxb.ProductVariantTranslations pvtJaxb = new se.info24.productsjaxb.ProductVariantTranslations();
                    se.info24.productsjaxb.Country countryJaxb = new se.info24.productsjaxb.Country();
                    countryJaxb.setCountryID(country.getCountryId());
                    if (country.getCurrency() != null) {
                        countryJaxb.setCurrencyID(country.getCurrency().getCurrencyId());
                    }
                    countryJaxb.setCountryName(country.getCountryName());
                    pvtJaxb.setProductVariantName(pvt.getProductVariantName() == null ? "" : pvt.getProductVariantName());
                    pvtJaxb.setProductVariantDescription(pvt.getProductVariantDescription() == null ? "" : pvt.getProductVariantDescription());
                    pvtJaxb.setCountryID(pvt.getId().getCountryId());
                    pvtJaxb.getCountry().add(countryJaxb);
                    pv.getProductVariantTranslations().add(pvtJaxb);
                    countryadded = true;
                }
            }
            if (countryadded) {
                countryadded = false;
            } else {
                se.info24.productsjaxb.ProductVariantTranslations pvtJaxb = new se.info24.productsjaxb.ProductVariantTranslations();
                se.info24.productsjaxb.Country countryJaxb = new se.info24.productsjaxb.Country();
                countryJaxb.setCountryID(country.getCountryId());
                countryJaxb.setCountryName(country.getCountryName());
                if (country.getCurrency() != null) {
                    countryJaxb.setCurrencyID(country.getCurrency().getCurrencyId());
                }
                pvtJaxb.setCountryID(country.getCountryId());
                pvtJaxb.getCountry().add(countryJaxb);
                pv.getProductVariantTranslations().add(pvtJaxb);
            }
        }
        product.getProductVariants().add(pv);
        products.getProduct().add(product);
        tingcoProducts.setProducts(products);
        return tingcoProducts;
    }

    public TingcoProducts buildObjectProductPriceDetails(List<ObjectProductVariantPrices> objectProductVariantPriceses, List<ProductVariantTranslations> pvtList, TingcoProducts tingcoProducts) {
        se.info24.productsjaxb.Products products = new se.info24.productsjaxb.Products();
        se.info24.productsjaxb.Product product = new Product();
        int seqNo = 1;
        product.setSeqNo(seqNo);
        for (ProductVariantTranslations pvt : pvtList) {
            for (ObjectProductVariantPrices opvp : objectProductVariantPriceses) {
                if (opvp.getId().getProductVariantId().equalsIgnoreCase(pvt.getId().getProductVariantId())) {
                    se.info24.productsjaxb.ObjectProductVariantPrices opvpJaxb = new se.info24.productsjaxb.ObjectProductVariantPrices();
                    opvpJaxb.setSeqNo(seqNo++);
                    ProductVariantID productVariantID = new ProductVariantID();
                    productVariantID.setProductVariantName(pvt.getProductVariantName());
                    productVariantID.setValue(pvt.getId().getProductVariantId());
                    opvpJaxb.setProductVariantID(productVariantID);
                    opvpJaxb.setPricePerUnitExclVAT(opvp.getPricePerUnitExclVat());
                    opvpJaxb.setVATPercent(opvp.getVatpercent());
                    opvpJaxb.setQuantityUnit(opvp.getQuantityUnit());
                    CurrencyID currencyID = new CurrencyID();
                    currencyID.setValue(opvp.getId().getCurrencyId());
                    if (opvp.getCurrency().getCurrencyIsocharCode() != null) {
                        currencyID.setCurrencyISOCharCode(opvp.getCurrency().getCurrencyIsocharCode());

                    }
                    opvpJaxb.setCurrencyID(currencyID);
                    product.getObjectProductVariantPrices().add(opvpJaxb);
                    if (opvp.getProductVariants().getProductVariantSKU() != null) {
                        opvpJaxb.setProductVariantSKU(opvp.getProductVariants().getProductVariantSKU());

                    }
                    break;
                }
            }
        }
        products.getProduct().add(product);
        tingcoProducts.setProducts(products);
        return tingcoProducts;
    }

    public TingcoProducts buildGetProductVariantPrice(BigDecimal pricePerUnit, TingcoProducts tingcoProducts) {
        se.info24.productsjaxb.Products products = new se.info24.productsjaxb.Products();
        se.info24.productsjaxb.Product product = new Product();
        int seqNo = 1;
        product.setSeqNo(seqNo);
        se.info24.productsjaxb.ObjectProductVariantPrices opvpJaxb = new se.info24.productsjaxb.ObjectProductVariantPrices();
        if (pricePerUnit == null) {
            pricePerUnit = new BigDecimal("0.00");
        } else {
            opvpJaxb.setPricePerUnitExclVAT(pricePerUnit);
        }
        opvpJaxb.setSeqNo(seqNo);
        product.getObjectProductVariantPrices().add(opvpJaxb);
        products.getProduct().add(product);
        tingcoProducts.setProducts(products);
        return tingcoProducts;
    }

    public TingcoProducts buildGetCurrencyForDeviceID(Device device, TingcoProducts tingcoProducts) {
        se.info24.productsjaxb.Products products = new se.info24.productsjaxb.Products();
        se.info24.productsjaxb.Product product = new Product();
        int seqNo = 1;
        product.setSeqNo(seqNo);
        se.info24.productsjaxb.ObjectProductVariantPrices opvpJaxb = new se.info24.productsjaxb.ObjectProductVariantPrices();
        CurrencyID currencyID = new CurrencyID();
        currencyID.setValue(device.getAddresses().getCountry().getCurrency().getCurrencyId());
        if (device.getAddresses().getCountry().getCurrency().getCurrencyIsocharCode() != null) {
            currencyID.setCurrencyISOCharCode(device.getAddresses().getCountry().getCurrency().getCurrencyIsocharCode());
        }
        opvpJaxb.setCurrencyID(currencyID);
        opvpJaxb.setSeqNo(seqNo);
        product.getObjectProductVariantPrices().add(opvpJaxb);
        products.getProduct().add(product);
        tingcoProducts.setProducts(products);
        return tingcoProducts;
    }

    public TingcoProducts buildGetPaymentProviders(List<Providers> providerses, TingcoProducts tingcoProducts) {
        se.info24.productsjaxb.Products products = new se.info24.productsjaxb.Products();
        se.info24.productsjaxb.Product product = new Product();
        int seqNo = 1;
        product.setSeqNo(seqNo);
        if (providerses.isEmpty()) {
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Data Not Found");
            return tingcoProducts;
        }
        for (Providers providers : providerses) {
            se.info24.productsjaxb.Providers providerJaxb = new se.info24.productsjaxb.Providers();
            providerJaxb.setProviderID(providers.getProviderId());
            providerJaxb.setProviderName(providers.getProviderName());
            providerJaxb.setSeqNo(seqNo++);
            product.getProviders().add(providerJaxb);
        }
        products.getProduct().add(product);
        tingcoProducts.setProducts(products);
        return tingcoProducts;
    }

    public TingcoProducts buildGetProductVariantsForDeviceReport(TingcoProducts tingcoProducts, List<ProductVariantTranslations> productVariantTranslationses) {
        se.info24.productsjaxb.Products products = new se.info24.productsjaxb.Products();
        Product product = new Product();
        for (ProductVariantTranslations pvt : productVariantTranslationses) {
            se.info24.productsjaxb.ProductVariants pvs = new se.info24.productsjaxb.ProductVariants();
            se.info24.productsjaxb.ProductVariantTranslations pvts = new se.info24.productsjaxb.ProductVariantTranslations();
            pvts.setProductVariantID(pvt.getId().getProductVariantId());
            if (!pvt.getProductVariantName().trim().equalsIgnoreCase("")) {
                pvts.setProductVariantName(pvt.getProductVariantName());

            }
            pvs.getProductVariantTranslations().add(pvts);
            product.getProductVariants().add(pvs);
        }
        products.getProduct().add(product);
        tingcoProducts.setProducts(products);
        return tingcoProducts;
    }

    public TingcoProducts buildGetProductCategoryDetails(TingcoProducts tingcoProducts, List<ProductCategoryTranslations> categoryTranslationses) {
        if (categoryTranslationses.isEmpty()) {
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Data Not Found");
            return tingcoProducts;
        }
        se.info24.productsjaxb.Products products = new se.info24.productsjaxb.Products();
        Product product = new Product();
        ProductCategories categories = new ProductCategories();
        for (ProductCategoryTranslations pct : categoryTranslationses) {
            se.info24.productsjaxb.ProductCategoryTranslations pctJaxb = new se.info24.productsjaxb.ProductCategoryTranslations();
            pctJaxb.setProductCategoryID(pct.getId().getProductCategoryId());
            pctJaxb.setProductCategoryName(pct.getProductCategoryName());
            categories.getProductCategoryTranslations().add(pctJaxb);
        }
        product.getProductCategories().add(categories);
        products.getProduct().add(product);
        tingcoProducts.setProducts(products);
        return tingcoProducts;
    }

    public TingcoProducts buildGetProductTypeDetails(TingcoProducts tingcoProducts, List<ProductTypes> productTypeses) {
        if (productTypeses.isEmpty()) {
            tingcoProducts.getMsgStatus().setResponseCode(-1);
            tingcoProducts.getMsgStatus().setResponseText("Data Not Found");
            return tingcoProducts;
        }
        se.info24.productsjaxb.Products products = new se.info24.productsjaxb.Products();
        for (ProductTypes pt : productTypeses) {
            Product productJaxb = new Product();
            productJaxb.setProductTypeID(pt.getProductTypeId());
            productJaxb.setProductTypeName(pt.getProductTypeName());
            products.getProduct().add(productJaxb);
        }
        tingcoProducts.setProducts(products);
        return tingcoProducts;
    }

    /**
     * ProductID(Products), ProductName(ProductTranslations),ProductCategoryID(Products),
    ProductCategoryName(ProductCategoryTranslations),
    ProductTypeID(Products),
    ProductTypeName(ProductTypes),
    IsEnabled,
    IsDownloadProduct,
    DisplayInWebShop,
    CreatedDate (yyyy-MM-dd hh :mm:ss),
    FirstName and LastName from the UserID (Users2 table) in the Products table.
     */
    public TingcoProducts buildGetProductDetails(TingcoProducts tingcoProducts, List<Products> products, List<Users2> users, int countryId, Session session, List<ProductTranslations> productTranslationses) {
        se.info24.productsjaxb.Products productsJaxb = new se.info24.productsjaxb.Products();
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        GregorianCalendar gc = new GregorianCalendar();
        for (ProductTranslations ptPojo : productTranslationses) {
            for (Products product : products) {
                if (product.getProductId().equalsIgnoreCase(ptPojo.getId().getProductId())) {
                    Product productJaxb = new Product();
                    productJaxb.setProductID(product.getProductId());
//                    for (Object ptPojoObject : product.getProductTranslationses()) {
//                        ProductTranslations ptPojo = (ProductTranslations) ptPojoObject;
//                        if (ptPojo.getId().getCountryId() == countryId) {
                    se.info24.productsjaxb.ProductTranslations pt = new se.info24.productsjaxb.ProductTranslations();
                    pt.setProductName(ptPojo.getProductName());
                    productJaxb.getProductTranslations().add(pt);
//                        }
//                    }

                    ProductCategories productCategoriesJaxb = new ProductCategories();
                    productCategoriesJaxb.setProductCategoryID(product.getProductCategories().getProductCategoryId());

                    List<ProductCategoryTranslations> productCategoryTranslationses = session.getNamedQuery("getProductCategoryTranslationsByID").setParameter("productCategoryId", product.getProductCategories().getProductCategoryId()).setInteger("countryId", countryId).list();
                    if (!productCategoryTranslationses.isEmpty()) {
                        se.info24.productsjaxb.ProductCategoryTranslations pct = new se.info24.productsjaxb.ProductCategoryTranslations();
                        pct.setProductCategoryName(productCategoryTranslationses.get(0).getProductCategoryName());
                        productCategoriesJaxb.getProductCategoryTranslations().add(pct);

                    }
//            pct.setProductCategoryName(product.getProductCategories().get)

                    productJaxb.getProductCategories().add(productCategoriesJaxb);

                    productJaxb.setProductTypeID(product.getProductTypes().getProductTypeId());
                    productJaxb.setProductTypeName(product.getProductTypes().getProductTypeName());

                    productJaxb.setIsEnabled(product.getIsEnabled());
                    if (product.getIsEnabled() == 1) {
                        productJaxb.setIsEnabledTCMV3("Yes");
                    } else {
                        productJaxb.setIsEnabledTCMV3("No");
                    }
                    productJaxb.setIsDownloadProduct(product.getIsDownloadProduct());
                    if (product.getIsDownloadProduct() == 1) {
                        productJaxb.setIsDownloadProductTCMV3("Yes");
                    } else {
                        productJaxb.setIsDownloadProductTCMV3("No");
                    }
                    productJaxb.setDisplayInWebShop(product.getDisplayInWebShop());
                    if (product.getDisplayInWebShop() == 1) {
                        productJaxb.setDisplayInWebShopTCMV3("Yes");
                    } else {
                        productJaxb.setDisplayInWebShopTCMV3("No");
                    }
                    gc.setTime(product.getCreatedDate());
                    productJaxb.setCreatedDateTCMV3(df1.format(gc.getTime()));

                    for (Users2 users2 : users) {
                        if (users2.getUserId().equalsIgnoreCase(product.getUserId())) {
                            productJaxb.setFullName(users2.getFirstName() + " " + users2.getLastName());
                        }

                    }
                    productsJaxb.getProduct().add(productJaxb);
                }
            }

        }

        tingcoProducts.setProducts(productsJaxb);
        return tingcoProducts;
    }

    public TingcoProducts buildGetProductDetailsbyProductID(TingcoProducts tingcoProducts, Products products, ProductTranslations productTranslations, ProductCategoryTranslations productCategoryTranslations, ProductTypes productTypes) {
        se.info24.productsjaxb.Products productsJaxb = new se.info24.productsjaxb.Products();
        Product productJaxb = new Product();
        productJaxb.setProductID(products.getProductId());
        productJaxb.setIsEnabled(products.getIsEnabled());
        productJaxb.setIsDownloadProduct(products.getIsDownloadProduct());
        productJaxb.setExtendedMembershipRequired(products.getExtendedMembershipRequired());
        productJaxb.setDisplayInWebShop(products.getDisplayInWebShop());
        if (productCategoryTranslations != null) {
            ProductCategories categoriesJaxb = new ProductCategories();
            se.info24.productsjaxb.ProductCategoryTranslations categoryTranslationsJaxb = new se.info24.productsjaxb.ProductCategoryTranslations();
            categoryTranslationsJaxb.setProductCategoryID(productCategoryTranslations.getId().getProductCategoryId());
            categoryTranslationsJaxb.setProductCategoryName(productCategoryTranslations.getProductCategoryName());
            categoriesJaxb.getProductCategoryTranslations().add(categoryTranslationsJaxb);
            productJaxb.getProductCategories().add(categoriesJaxb);
        }
        if (productTypes != null) {
            productJaxb.setProductTypeID(productTypes.getProductTypeId());
            productJaxb.setProductTypeName(productTypes.getProductTypeName());
        }
        if (productTranslations != null) {
            se.info24.productsjaxb.ProductTranslations ptJaxb = new se.info24.productsjaxb.ProductTranslations();
            ptJaxb.setProductName(productTranslations.getProductName());
            productJaxb.getProductTranslations().add(ptJaxb);
        }
        productsJaxb.getProduct().add(productJaxb);
        tingcoProducts.setProducts(productsJaxb);
        return tingcoProducts;
    }
}
