/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.tcp;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Session;
import se.info24.device.DeviceDAO;
import se.info24.ismOperationsPojo.TransactionProducts;
import se.info24.ismOperationsPojo.TransactionResult;
import se.info24.pojo.Addresses;
import se.info24.pojo.Country;
import se.info24.pojo.Currency;
import se.info24.pojo.ProviderTypeTranslations;
import se.info24.pojo.ProviderTypes;
import se.info24.pojo.UserProviderTypeReferences;
import se.info24.pojo.Users2;
import se.info24.restUtil.RestUtilDAO;
import se.info24.tcpjaxb.ObjectFactory;
import se.info24.tcpjaxb.ProviderTypeTranslation;
import se.info24.tcpjaxb.TingcoCustomer;
import se.info24.tcpjaxb.Transactions;
import se.info24.util.TCMUtil;

/**
 *
 * @author Ravikant
 */
public class TingcoCustomerXML {

    TingcoCustomer customer = new TingcoCustomer();

    public TingcoCustomer buildUserTemplate() {
        try {
            ObjectFactory objectFactory = new ObjectFactory();
            customer = objectFactory.createTingcoCustomer();
            customer.setCreateRef("Info24");
            customer.setMsgVer(new BigDecimal("1.0"));
            customer.setRegionalUnits("Metric");
            customer.setTimeZone("UTC");

            customer.setMsgID(UUID.randomUUID().toString());
            customer.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

            se.info24.tcpjaxb.MsgStatus msgStatus = new se.info24.tcpjaxb.MsgStatus();
            msgStatus.setResponseCode(0);
            msgStatus.setResponseText("OK");

            customer.setMsgStatus(msgStatus);

        } catch (Exception e) {
            TCMUtil.exceptionLog(getClass().getName(), e);
        }
        return customer;
    }

    public TingcoCustomer buildgetProviderTypes(TingcoCustomer tingcoCustomer, List<ProviderTypeTranslations> PTT, List<ProviderTypes> PT) {
        se.info24.tcpjaxb.ProviderTypeTranslations providerTypeTranslations = new se.info24.tcpjaxb.ProviderTypeTranslations();

        for (ProviderTypeTranslations ProviderTypeTranslations : PTT) {
            for (ProviderTypes providerTypes : PT) {
                if (ProviderTypeTranslations.getId().getProviderTypeId().equalsIgnoreCase(providerTypes.getProviderTypeId())) {
                    se.info24.tcpjaxb.ProviderTypeTranslation providerTypeTranslation = new ProviderTypeTranslation();
                    providerTypeTranslation.setProviderTypeID(ProviderTypeTranslations.getId().getProviderTypeId());
                    providerTypeTranslation.setProviderTypeName(ProviderTypeTranslations.getProviderTypeName());
                    providerTypeTranslations.getProviderTypeTranslation().add(providerTypeTranslation);
                }
            }
        }
        tingcoCustomer.setProviderTypeTranslations(providerTypeTranslations);
        return tingcoCustomer;
    }

    public TingcoCustomer buildGetUserProfileForUserId(TingcoCustomer tingcoCustomer, Addresses address, Users2 users) {

        se.info24.tcpjaxb.Users usersjaxb = new se.info24.tcpjaxb.Users();
        se.info24.tcpjaxb.User user = new se.info24.tcpjaxb.User();
        if (address != null) {
            se.info24.tcpjaxb.Address addressJaxb = new se.info24.tcpjaxb.Address();
            Country country = address.getCountry();
            addressJaxb.setAddressID(address.getAddressId());
            addressJaxb.setCountryID(country.getCountryId());
            if (address.getAddressStreet() != null && address.getAddressStreet().length() > 0) {
                addressJaxb.setAddressStreet(address.getAddressStreet());
            }

            if (address.getAddressZip() != null && address.getAddressZip().length() > 0) {
                addressJaxb.setAddressZip(address.getAddressZip());
            }
            if (address.getAddressCity() != null && address.getAddressCity().length() > 0) {
                addressJaxb.setAddressCity(address.getAddressCity());
            }
            user.setAddress(addressJaxb);
        }

        user.setFirstName(users.getFirstName());
        user.setLastName(users.getLastName());
        user.setUserEmail(users.getUserEmail());
        user.setUserMobilePhone(users.getUserMobilePhone());
        usersjaxb.getUser().add(user);
        tingcoCustomer.setUsers(usersjaxb);
        return tingcoCustomer;
    }

    TingcoCustomer buildTransactionResults(TingcoCustomer customer, List<TransactionResult> transactionResultList, String timeZoneGMToffset, Session ismOperationsession, Session session) throws DatatypeConfigurationException {
        RestUtilDAO utilDAO = new RestUtilDAO();
        DeviceDAO deviceDAO = new DeviceDAO();
        Transactions transactions = new Transactions();
        for (TransactionResult tr : transactionResultList) {
            se.info24.tcpjaxb.TransactionResult transResults = new se.info24.tcpjaxb.TransactionResult();
            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
            transResults.setTransactionID(tr.getTransactionId());
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, tr.getTransactionStartTime()));
            transResults.setTransactionStartTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            transResults.setAmount(tr.getAmount());
            transResults.setDeviceName(tr.getDeviceName() == null ? "" : tr.getDeviceName());
            transResults.setIsPurchase(tr.getIsPurchase() == null ? 0 : tr.getIsPurchase());
            transResults.setIsInvoiced(tr.getIsInvoiced() == null ? 0 : tr.getIsInvoiced());
            transResults.setIsPaid(tr.getIsPaid() == null ? 0 : tr.getIsPaid());
            transResults.setIsPayment(tr.getIsPayment() == null ? 0 : tr.getIsPayment());
            if (tr.getIsPaid() == 1) {
                transResults.setStatus("Paid");
            } else if (tr.getIsInvoiced() == 1 && tr.getIsPaid() == 0) {
                transResults.setStatus("Invoice");
            } else if (tr.getIsPurchase() == 1 && tr.getIsInvoiced() == 0 && tr.getIsPaid() == 0) {
                transResults.setStatus("Bought");
            } else {
                transResults.setStatus("Bought");
            }
            if (tr.getCurrencyId() != null) {
                Currency currency = utilDAO.getCurrencyById(String.valueOf(tr.getCurrencyId()), session);
                if (currency != null) {
                    se.info24.tcpjaxb.Currency currencyJaxb = new se.info24.tcpjaxb.Currency();
                    transResults.setCurrencyID(currency.getCurrencyId());
                    currencyJaxb.setCurrencyISOCharCode(currency.getCurrencyIsocharCode());
                    currencyJaxb.setCurrencyName(currency.getCurrencyName());
                    transResults.getCurrency().add(currencyJaxb);
                }
            }

            List<TransactionProducts> transactionProductsList = deviceDAO.getTransactionProducts(tr.getTransactionId(), ismOperationsession);
            if (!transactionProductsList.isEmpty()) {
                transResults.setNumberOfProduct(String.valueOf(transactionProductsList.size()));
            }
            transactions.getTransactionResult().add(transResults);
        }
        customer.setTransactions(transactions);
        return customer;
    }

    public TingcoCustomer buildGetObjectCodeForProviderType(TingcoCustomer customer, ProviderTypes pt, UserProviderTypeReferences uptr) {
        se.info24.tcpjaxb.ProviderTypes providerTypes = new se.info24.tcpjaxb.ProviderTypes();
        se.info24.tcpjaxb.ProviderType providerType = new se.info24.tcpjaxb.ProviderType();
        providerType.setProviderTypeID(pt.getProviderTypeId());
        providerType.setObjectCode(pt.getObjectCode());
        se.info24.tcpjaxb.UserProviderTypeReferences userProviderTypeReferences = new se.info24.tcpjaxb.UserProviderTypeReferences();
        if (uptr != null) {
            userProviderTypeReferences.setUserProviderReference1(uptr.getUserProviderReference1());
            userProviderTypeReferences.setStatus("1");
        } else {
            userProviderTypeReferences.setStatus("0");
        }
        providerType.setUserProviderTypeReferences(userProviderTypeReferences);
        providerTypes.getProviderType().add(providerType);
        customer.setProviderTypes(providerTypes);
        return customer;
    }
}
