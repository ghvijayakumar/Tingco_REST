/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.jaxbUtil;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Session;
import se.info24.ismOperationsPojo.ObjectLog;
import se.info24.pojo.Country;
import se.info24.pojo.CountryPhoneCodes;
import se.info24.pojo.CountryStates;
import se.info24.pojo.Currency;
import se.info24.pojo.GroupWeekdays;
import se.info24.pojo.Ismnews;
import se.info24.pojo.NetworkTypes;
import se.info24.pojo.NewsCategoryTranslations;
import se.info24.pojo.TimeZones;
import se.info24.pojo.Users2;
import se.info24.pojo.WeekdayTranslations;
import se.info24.restUtil.RestUtilDAO;
import se.info24.util.TCMUtil;
import se.info24.utiljaxb.CountryID;
import se.info24.utiljaxb.GroupID;
import se.info24.utiljaxb.Languages;
import se.info24.utiljaxb.LastUpdatedByUserID;
import se.info24.utiljaxb.MsgStatus;
import se.info24.utiljaxb.News;
import se.info24.utiljaxb.NewsCategories;
import se.info24.utiljaxb.ObjectFactory;
import se.info24.utiljaxb.ObjectLogs;
import se.info24.utiljaxb.Timezones;
import se.info24.utiljaxb.TingcoUtils;
import se.info24.utiljaxb.WeekDayTranslations;

/**
 *
 * @author Sekhar
 */
public class TingcoUtilsXML {

    ObjectFactory objectFactory;
    RestUtilDAO dao = new RestUtilDAO();

    public TingcoUtilsXML() {
        objectFactory = new ObjectFactory();
    }

    public TingcoUtils buildCountryDetails(TingcoUtils tingcoUtils, Country country) {
        se.info24.utiljaxb.Country coun = new se.info24.utiljaxb.Country();
        se.info24.utiljaxb.CountryID cid = new se.info24.utiljaxb.CountryID();
        if (country.getCountryName() != null) {
            cid.setCountryName(country.getCountryName());
        }
        cid.setValue(country.getCountryId());
        coun.setCountryID(cid);
        if (country.getCurrency() != null) {
            coun.setCurrencyID(String.valueOf(country.getCurrency().getCurrencyId()));
        }
        if (country.getCountryDescription() != null) {
            coun.setCountryDescription(country.getCountryDescription());
        }
        if (country.getLanguageCode() != null) {
            coun.setLanguageCode(country.getLanguageCode());
        }
        if (country.getLanguage() != null) {
            coun.setLanguage(country.getLanguage());
        }
        if (country.getDisplayOrder() != null) {
            coun.setDisplayOrder(country.getDisplayOrder());
        }
        if (country.getFunctionId() != null) {
            coun.setFunctionID(country.getFunctionId());
        }
        if (country.getTimeZoneId() != null) {
            coun.setTimeZoneID(country.getTimeZoneId());
        }
        tingcoUtils.getCountry().add(coun);
        return tingcoUtils;
    }

    public TingcoUtils buildCountryPhoneCodes(TingcoUtils tingcoUtils, List<CountryPhoneCodes> phoneCodesList) throws DatatypeConfigurationException {

        for (CountryPhoneCodes cpc : phoneCodesList) {
            se.info24.utiljaxb.CountryPhoneCodes countryPhoneCodes = new se.info24.utiljaxb.CountryPhoneCodes();
            countryPhoneCodes.setCountryCallingCode(cpc.getCountryCallingCode());
            countryPhoneCodes.setCountryCallingCodeID(cpc.getCountryCallingCodeId());
            CountryID id = new CountryID();

            id.setCountryName(cpc.getCountryName());
            if (cpc.getCountryId() != null) {
                id.setValue(cpc.getCountryId());
            }
            countryPhoneCodes.setCountryID(id);
            LastUpdatedByUserID userid = new LastUpdatedByUserID();
            userid.setValue(cpc.getLastUpdatedByUserId());
            countryPhoneCodes.setLastUpdatedByUserID(userid);
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(cpc.getCreatedDate());
            countryPhoneCodes.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            gc.setTime(cpc.getCreatedDate());
            countryPhoneCodes.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            tingcoUtils.getCountryPhoneCodes().add(countryPhoneCodes);
        }

        return tingcoUtils;
    }

    public TingcoUtils buildObjectLogs(TingcoUtils tingcoUtils, List<ObjectLog> objectLogsList, String timeZoneGMToffset) {
        ObjectLogs objectLogs = new ObjectLogs();
        GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (ObjectLog ol : objectLogsList) {
            se.info24.utiljaxb.ObjectLog objectLog = new se.info24.utiljaxb.ObjectLog();
            objectLog.setObjectId(ol.getObjectId());
            objectLog.setMessage(ol.getMessage());
            objectLog.setMessageCode(ol.getMessageCode());
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, ol.getCreatedDate()));
            objectLog.setCreatedDate(lFormat.format(gc.getTime()));
            objectLogs.getObjectLog().add(objectLog);
        }
        tingcoUtils.setObjectLogs(objectLogs);
        return tingcoUtils;
    }

    public TingcoUtils buildObjectLogsByObjectId(TingcoUtils tingcoUtils, ObjectLog ol) {
        ObjectLogs objectLogs = new ObjectLogs();
        se.info24.utiljaxb.ObjectLog objectLog = new se.info24.utiljaxb.ObjectLog();
        objectLog.setObjectId(ol.getObjectId());
        objectLog.setMessage(ol.getMessage());
        objectLog.setMessageCode(ol.getMessageCode());
        objectLogs.getObjectLog().add(objectLog);
        tingcoUtils.setObjectLogs(objectLogs);
        return tingcoUtils;
    }

    public TingcoUtils buildTingcoUtilsTemplate() throws DatatypeConfigurationException {
        TingcoUtils tingcoUtils = objectFactory.createTingcoUtils();
        tingcoUtils.setCreateRef("Info24");
        tingcoUtils.setMsgVer(new BigDecimal("1.0"));
        tingcoUtils.setRegionalUnits("Metric");
        tingcoUtils.setTimeZone("UTC");

        tingcoUtils.setMsgID(UUID.randomUUID().toString());
        tingcoUtils.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        MsgStatus msgStatus = new MsgStatus();
        msgStatus.setResponseCode(0);
        msgStatus.setResponseText("OK");

        tingcoUtils.setMsgStatus(msgStatus);
        return tingcoUtils;
    }

    public TingcoUtils buildAllLanguages(TingcoUtils tingcoUtils, List<Country> countryList, String isLoacalized) {
        for (Country country : countryList) {
            if (isLoacalized != null) {
                if (country.getIsLocalized() == Integer.parseInt(isLoacalized)) {
                    Languages lang = new Languages();
                    se.info24.utiljaxb.CountryID cid = objectFactory.createCountryID();
                    cid.setCountryName(country.getCountryName());
                    cid.setValue(country.getCountryId());
                    if (country.getCurrency() != null) {
                        se.info24.utiljaxb.Currency currency = new se.info24.utiljaxb.Currency();
                        currency.setCurrencyID(String.valueOf(country.getCurrency().getCurrencyId()));
                        if (country.getCurrency().getCurrencyName() != null) {
                            currency.setCurrencyName(country.getCurrency().getCurrencyName());
                        }
                        if (country.getCurrency().getCurrencyIsocharCode() != null) {
                            currency.setCurrencyISOCharCode(country.getCurrency().getCurrencyIsocharCode());
                        }
                        if (country.getCurrency().getCurrencyIsocode() != null) {
                            currency.setCurrencyISOCode(country.getCurrency().getCurrencyIsocode());
                        }
                        lang.setCurrency(currency);
                    }

                    lang.setCountryID(cid);
                    lang.setLanguage(country.getLanguage());
                    lang.setLanguageCode(country.getLanguageCode());

                    tingcoUtils.getLanguages().add(lang);
                }
            } else {
                Languages lang = new Languages();
                se.info24.utiljaxb.CountryID cid = objectFactory.createCountryID();
                cid.setCountryName(country.getCountryName());
                cid.setValue(country.getCountryId());
                if (country.getCurrency() != null) {
                    se.info24.utiljaxb.Currency currency = new se.info24.utiljaxb.Currency();
                    currency.setCurrencyID(String.valueOf(country.getCurrency().getCurrencyId()));
                    if (country.getCurrency().getCurrencyName() != null) {
                        currency.setCurrencyName(country.getCurrency().getCurrencyName());
                    }
                    if (country.getCurrency().getCurrencyIsocharCode() != null) {
                        currency.setCurrencyISOCharCode(country.getCurrency().getCurrencyIsocharCode());
                    }
                    if (country.getCurrency().getCurrencyIsocode() != null) {
                        currency.setCurrencyISOCode(country.getCurrency().getCurrencyIsocode());
                    }
                    lang.setCurrency(currency);
                }

                lang.setCountryID(cid);
                lang.setLanguage(country.getLanguage());
                lang.setLanguageCode(country.getLanguageCode());

                tingcoUtils.getLanguages().add(lang);
            }


        }
        return tingcoUtils;
    }

    public TingcoUtils buildAllTimeZones(TingcoUtils tingcoUtils, List<TimeZones> timeZoneList) {
        for (TimeZones timeZone : timeZoneList) {
            Timezones timezones = new Timezones();
            timezones.setTimezoneID(timeZone.getTimeZoneId());
            timezones.setTimezoneName(timeZone.getTimeZoneName());
            timezones.setTimeZoneDescription(timeZone.getTimeZoneDescription());
            timezones.setTimeZoneGMTOffset(timeZone.getTimeZoneGmtoffset());
            timezones.setDaylightSavingStartTime(timeZone.getDaylightSavingStartTime());
            timezones.setDaylightSavingEndTime(timeZone.getDaylightSavingEndTime());
            timezones.setDaylightSavingOffset(timeZone.getDaylightSavingOffset());
            timezones.setDaylightSavingStartRule(timeZone.getDaylightSavingStartRule());
            timezones.setDaylightSavingStopRule(timeZone.getDaylightSavingStopRule());
            tingcoUtils.getTimezones().add(timezones);
        }
        return tingcoUtils;
    }

    public TingcoUtils buildCurrency(TingcoUtils tingcoUtil, List<Currency> currencyList, HashMap<String, Users2> userMap, List<Country> countryList) {

        se.info24.utiljaxb.Currency cur;
        int seqNo = 1;
        Users2 user = null;
        CountryID countryid = null;
        for (Currency currency : currencyList) {
            cur = new se.info24.utiljaxb.Currency();
            cur.setSeqNo(seqNo++);

            cur.setCurrencyID(String.valueOf(currency.getCurrencyId()));
            cur.setCurrencyISOCharCode(currency.getCurrencyIsocharCode());
            cur.setCurrencyISOCode(currency.getCurrencyIsocode());
            cur.setCurrencyName(currency.getCurrencyName());
            cur.setCurrencyDescription(currency.getCurrencyDescription());
            for (Country country : countryList) {
                if (country.getCurrency() != null) {
                    if (currency.getCurrencyId() == country.getCurrency().getCurrencyId()) {
                        countryid = new CountryID();
                        countryid.setCountryName(country.getCountryName());
                        countryid.setValue(country.getCountryId());
                        cur.getCountryID().add(countryid);
                    }
                }
            }
            if (userMap.get(currency.getUserId()) != null) {
                user = userMap.get(currency.getUserId());

            }

            if (user != null) {
                LastUpdatedByUserID updatedUser = objectFactory.createLastUpdatedByUserID();
                updatedUser.setUserFullName(user.getFirstName() + " " + user.getLastName());
                updatedUser.setValue(user.getUserId());
                cur.setLastUpdatedByUserID(updatedUser);
            }
            tingcoUtil.getCurrency().add(cur);
        }
        return tingcoUtil;
    }

    public TingcoUtils buildCurrency(TingcoUtils tingcoUtil, List<Currency> currencyList, HashMap<String, Users2> userMap) {

        se.info24.utiljaxb.Currency cur;
        int seqNo = 1;
        Users2 user = null;
        CountryID countryid = null;
        for (Currency currency : currencyList) {
            cur = new se.info24.utiljaxb.Currency();
            cur.setSeqNo(seqNo++);

            cur.setCurrencyID(String.valueOf(currency.getCurrencyId()));
            cur.setCurrencyISOCharCode(currency.getCurrencyIsocharCode());
            cur.setCurrencyISOCode(currency.getCurrencyIsocode());
            cur.setCurrencyName(currency.getCurrencyName());
            cur.setCurrencyDescription(currency.getCurrencyDescription());

            if (userMap.get(currency.getUserId()) != null) {
                user = userMap.get(currency.getUserId());

            }

            if (user != null) {
                LastUpdatedByUserID updatedUser = objectFactory.createLastUpdatedByUserID();
                updatedUser.setUserFullName(user.getFirstName() + " " + user.getLastName());
                updatedUser.setValue(user.getUserId());
                cur.setLastUpdatedByUserID(updatedUser);
            }
            tingcoUtil.getCurrency().add(cur);
        }
        return tingcoUtil;
    }

    public TingcoUtils buildNetworkTypes(TingcoUtils tingcoUtils, List<NetworkTypes> networktypesList, HashMap<String, Users2> userMap) {
        se.info24.utiljaxb.NetworkTypes nwtypes;
        int seqNo = 1;
        Users2 user = null;
        for (NetworkTypes nwt : networktypesList) {
            nwtypes = new se.info24.utiljaxb.NetworkTypes();
            nwtypes.setSeqNo(seqNo++);

            nwtypes.setNetworkTypeID(nwt.getNetworkTypeId());
            nwtypes.setNetworkTypeName(nwt.getNetworkTypeName());
            if (nwt.getNetworkTypeDescription() != null) {
                nwtypes.setNetworkTypeDescription(nwt.getNetworkTypeDescription());

            }
            if (userMap.get(nwt.getUserId()) != null) {
                user = userMap.get(nwt.getUserId());
            }

            if (user != null) {
                LastUpdatedByUserID updatedUser = objectFactory.createLastUpdatedByUserID();
                updatedUser.setUserFullName(user.getFirstName() + " " + user.getLastName());
                updatedUser.setValue(user.getUserId());
                nwtypes.setLastUpdatedByUserID(updatedUser);
            }
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(nwt.getCreatedDate());
            try {
                nwtypes.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                gc.setTime(nwt.getUpdatedDate());
                nwtypes.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            } catch (DatatypeConfigurationException ex) {
                TCMUtil.exceptionLog(getClass().getName(), ex);
            }
            tingcoUtils.getNetworkTypes().add(nwtypes);
        }
        return tingcoUtils;
    }

    public TingcoUtils buildNews(TingcoUtils tingcoUtils, List<Ismnews> newsList, HashMap<String, Users2> userMap) {
        News news = null;
        Users2 user = null;
        GregorianCalendar gc = new GregorianCalendar();
        for (Ismnews nw : newsList) {
            news = new News();

            if (nw.getBody() != null) {
                news.setBody(nw.getBody());
            }
            news.setSubject(nw.getSubject());
            news.setNewsID(nw.getNewsId());
            if (userMap.get(nw.getUserId()) != null) {
                user = userMap.get(nw.getUserId());
            }

            if (user != null) {
                LastUpdatedByUserID updatedUser = objectFactory.createLastUpdatedByUserID();
                updatedUser.setUserFullName(user.getFirstName() + " " + user.getLastName());
                updatedUser.setValue(user.getUserId());
                news.setLastUpdatedByUserID(updatedUser);
            }
            gc.setTime(nw.getCreatedDate());
            try {
                news.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                gc.setTime(nw.getUpdatedDate());
                news.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
            } catch (DatatypeConfigurationException ex) {
                TCMUtil.exceptionLog(getClass().getName(), ex);
            }
            tingcoUtils.getNews().add(news);
        }
        return tingcoUtils;
    }

    public TingcoUtils buildCountryStates(TingcoUtils tingcoUtils, List<CountryStates> csList) {
        se.info24.utiljaxb.CountryStates cs = null;
        for (CountryStates cState : csList) {
            cs = new se.info24.utiljaxb.CountryStates();
            cs.setCountryStateID(cState.getCountryStateId());
            cs.setStateName(cState.getStateName());
            if (cState.getStateDescription() != null) {
                cs.setStateDesc(cState.getStateDescription());
            }
            if (cState.getStateShortCode() != null) {
                cs.setStateShortCode(cState.getStateShortCode());
            }
            tingcoUtils.getCountryStates().add(cs);
        }
        return tingcoUtils;
    }

    public TingcoUtils buildWeekDays(TingcoUtils tingcoUtils, List<WeekdayTranslations> weekDaysList, Session session, String groupId) {
        if (!weekDaysList.isEmpty()) {
            for (WeekdayTranslations wdt : weekDaysList) {
                GroupWeekdays gwd = dao.getGroupWeekDays(wdt.getId().getWeekdayId(), groupId, session);
                WeekDayTranslations wd = new WeekDayTranslations();
                if (gwd != null) {
                    wd.setWeekdayValue(gwd.getWeekdayValue());
                    wd.setSortOrder(gwd.getSortOrder());
                }
                wd.setWeekdayID(wdt.getId().getWeekdayId());
                wd.setWeekdayName(wdt.getWeekdayName());
                tingcoUtils.getWeekDayTranslations().add(wd);
            }
        }
        return tingcoUtils;
    }

    public TingcoUtils buildGetNewsCategoryList(TingcoUtils tingcoUtils, List<NewsCategoryTranslations> newsCategoryTranslationses) {
        if (newsCategoryTranslationses.isEmpty()) {
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("No Data Found");
            return tingcoUtils;
        }
        News newsJaxb = new News();
        NewsCategories ncJaxb = new NewsCategories();
        for (NewsCategoryTranslations nct : newsCategoryTranslationses) {
            se.info24.utiljaxb.NewsCategoryTranslations nctJaxb = new se.info24.utiljaxb.NewsCategoryTranslations();
            nctJaxb.setNewsCategoryID(nct.getId().getNewsCategoryId());
            nctJaxb.setCategoryName(nct.getCategoryName());
            ncJaxb.getNewsCategoryTranslations().add(nctJaxb);
        }
        newsJaxb.getNewsCategories().add(ncJaxb);
        tingcoUtils.getNews().add(newsJaxb);
        return tingcoUtils;
    }

    public TingcoUtils buildGetNewsXML(TingcoUtils tingcoUtils, List<se.info24.pojo.News> newses, List<NewsCategoryTranslations> categoryTranslationses, String timeZoneGMToffset, List<Users2> users, String loggedInUserGroupID) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar gc = new GregorianCalendar();
        for (se.info24.pojo.News n : newses) {
            for (NewsCategoryTranslations nct : categoryTranslationses) {
                if (n.getNewsCategories().getNewsCategoryId().equalsIgnoreCase(nct.getId().getNewsCategoryId())) {
                    News newsJaxb = new News();
                    NewsCategories ncJaxb = new NewsCategories();
                    se.info24.utiljaxb.NewsCategoryTranslations nctJaxb = new se.info24.utiljaxb.NewsCategoryTranslations();

                    newsJaxb.setNewsIDTCMV3(n.getNewsId());
                    newsJaxb.setSubject(n.getSubject());
                    if (n.getBody() != null) {
                        newsJaxb.setBody(n.getBody());
                    }
                    StringBuffer categoryName = new StringBuffer();
                    for (Users2 users2 : users) {
                        if (users2.getUserId().equalsIgnoreCase(n.getLastUpdatedByUserId())) {
                            categoryName = new StringBuffer(users2.getFirstName() + " " + users2.getLastName()).append(" / ");
                        }
                    }
                    if (n.getCreatedDate() != null) {
                        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, n.getCreatedDate()));
                        categoryName.append(df.format(gc.getTime())).append(" / ");
                    }
                    categoryName.append(nct.getCategoryName());
                    nctJaxb.setCategoryName(categoryName.toString());

                    if (n.getGroupId().equalsIgnoreCase(loggedInUserGroupID)) {
                        newsJaxb.setIsEnabled(new BigDecimal("1"));
                    } else {
                        newsJaxb.setIsEnabled(new BigDecimal("0"));
                    }
                    ncJaxb.getNewsCategoryTranslations().add(nctJaxb);
                    newsJaxb.getNewsCategories().add(ncJaxb);
                    tingcoUtils.getNews().add(newsJaxb);
                    break;
                }
            }
        }
        return tingcoUtils;
    }
}
