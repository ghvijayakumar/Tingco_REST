/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.jaxbUtil;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Session;
import se.info24.appjaxb.Agreement;
import se.info24.appjaxb.AgreementDetails;
import se.info24.appjaxb.AgreementItemTypeID;
import se.info24.appjaxb.AgreementItems;
import se.info24.appjaxb.AgreementParentID;
import se.info24.appjaxb.AgreementRolesTypes;
import se.info24.appjaxb.AgreementStatusTranslations;
import se.info24.appjaxb.AgreementTypeTranslations;
import se.info24.appjaxb.Application;
import se.info24.appjaxb.ApplicationDescription;
import se.info24.appjaxb.ApplicationDescriptions;
import se.info24.appjaxb.ApplicationModuleNames;
import se.info24.appjaxb.ApplicationModules;
import se.info24.appjaxb.ApplicationName;
import se.info24.appjaxb.ApplicationNames;
import se.info24.appjaxb.ApplicationPackage;
import se.info24.appjaxb.ApplicationPackageNames;
import se.info24.appjaxb.ApplicationPackages;
import se.info24.appjaxb.ApplicationSetting;
import se.info24.appjaxb.ApplicationSolution;
import se.info24.appjaxb.ApplicationSolutionNames;
import se.info24.appjaxb.ApplicationSolutions;
import se.info24.appjaxb.ApplicationTypeID;
import se.info24.appjaxb.GroupID;
import se.info24.appjaxb.LastUpdatedByUserID;
import se.info24.appjaxb.ModuleName;
import se.info24.appjaxb.MsgStatus;
import se.info24.appjaxb.ObjectFactory;
import se.info24.appjaxb.PackageName;
import se.info24.appjaxb.SolutionName;
import se.info24.appjaxb.TingcoApplications;
import se.info24.group.GroupDAO;
import se.info24.ismOperationsPojo.AgreementBillingData;
import se.info24.pojo.AgreementItemTypeTranslations;
import se.info24.pojo.AgreementRoleTypeTranslations;
import se.info24.pojo.AgreementRoles;
import se.info24.pojo.Agreements;
import se.info24.pojo.ApplicationEmailTemplates;
import se.info24.pojo.ApplicationModuleTranslations;
import se.info24.pojo.ApplicationPackageTranslations;
import se.info24.pojo.ApplicationSettings;
import se.info24.pojo.ApplicationSolutionTranslations;
import se.info24.pojo.ApplicationTranslations;
import se.info24.pojo.ApplicationTypeTranslations;
import se.info24.pojo.Applications;
import se.info24.pojo.Country;
import se.info24.pojo.GroupApplicationPackages;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupVisibleApplicationModules;
import se.info24.pojo.GroupVisibleApplicationPackages;
import se.info24.pojo.GroupVisibleApplicationSolutions;
import se.info24.pojo.ProductVariantTranslations;
import se.info24.pojo.Users2;
import se.info24.restUtil.RestUtilDAO;
import se.info24.user.UserDAO;

/**
 *
 * @author Sekhar
 */
public class TingcoApplicationXML {

    private TingcoApplications applicationXML;
    GroupDAO groupDAO;
    UserDAO userDAO;

    public TingcoApplicationXML() {
        groupDAO = new GroupDAO();
        userDAO = new UserDAO();
    }

    public TingcoApplications buildAllApplicationModule(TingcoApplications tingcoApps, List<ApplicationModuleTranslations> transList) {
        ApplicationSolutions appSolutions = new ApplicationSolutions();
        ApplicationSolution appSolution = new ApplicationSolution();
        ApplicationPackages appPackages = new ApplicationPackages();
        int seqId = 1;
        for (ApplicationModuleTranslations amt : transList) {
            ApplicationPackage pkg = new ApplicationPackage();
            pkg.setSeqNo(seqId++);
            se.info24.appjaxb.ApplicationModules am = new se.info24.appjaxb.ApplicationModules();
            am.setApplicationModuleID(amt.getId().getApplicationModuleId());
            ApplicationModuleNames name = new ApplicationModuleNames();
            ModuleName mn = new ModuleName();
            mn.setValue(amt.getApplicationModuleName());
            name.getModuleName().add(mn);
            am.setApplicationModuleNames(name);
            pkg.getApplicationModules().add(am);
            appPackages.getApplicationPackage().add(pkg);
        }
        appSolution.getApplicationPackages().add(appPackages);
        appSolutions.getApplicationSolution().add(appSolution);
        tingcoApps.getApplicationSolutions().add(appSolutions);
        return tingcoApps;
    }

    public void buildAllApplicationModules(TingcoApplications tingcoApps, Hashtable<String, ArrayList<ApplicationModuleTranslations>> ht, Hashtable<String, se.info24.pojo.ApplicationPackages> packages, Hashtable<String, ApplicationPackageTranslations> pkgTrans, String applicationsolutionid) {

        ApplicationSolutions as = new ApplicationSolutions();
        ApplicationSolution solution = new ApplicationSolution();
        ApplicationPackages appPackages = new ApplicationPackages();
        int seq = 0;
        for (String key : ht.keySet()) {
            ApplicationPackage pkg = new ApplicationPackage();
            pkg.setSeqNo(seq++);
            se.info24.pojo.ApplicationPackages ap = packages.get(key);

            pkg.setApplicationPackageID(ap.getApplicationPackageId());
            pkg.setDisplayOrder(ap.getDisplayOrder());
            ApplicationPackageNames apn = new ApplicationPackageNames();
            ApplicationPackageTranslations apt = pkgTrans.get(key);
            apn.setPackageDesc(apt.getApplicationPackageDescrption());
            PackageName pn = new PackageName();
            pn.setValue(apt.getApplicationPackageName());

            for (ApplicationModuleTranslations amt : ht.get(key)) {
                ApplicationModules modules = new ApplicationModules();
                ApplicationModuleNames names = new ApplicationModuleNames();
                modules.setApplicationModuleID(amt.getApplicationModules().getApplicationModuleId());
                ModuleName mn = new ModuleName();
                mn.setValue(amt.getApplicationModuleName());
                names.getModuleName().add(mn);
                names.setModuleDesc(amt.getApplicationModuleDescription());
                modules.setApplicationModuleNames(names);
                pkg.getApplicationModules().add(modules);
            }
            apn.getPackageName().add(pn);
            pkg.setApplicationPackageNames(apn);
            appPackages.getApplicationPackage().add(pkg);
        }

        solution.getApplicationPackages().add(appPackages);
        as.getApplicationSolution().add(solution);
        tingcoApps.getApplicationSolutions().add(as);
    }

    /**
     * An Utility Method to create TingcoApplication XML Template.
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    public TingcoApplications buildTingcoAPITemplate() throws DatatypeConfigurationException {
        ObjectFactory objectFactory = new ObjectFactory();
        applicationXML = objectFactory.createTingcoApplications();
        applicationXML.setCreateRef("Info24");
        applicationXML.setMsgVer(new BigDecimal("1.0"));
        applicationXML.setRegionalUnits("Metric");
        applicationXML.setTimeZone("UTC");
        applicationXML.setMsgID(UUID.randomUUID().toString());
        applicationXML.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));
        MsgStatus msgStatus = new MsgStatus();
        msgStatus.setResponseCode(0);
        msgStatus.setResponseText("OK");
        applicationXML.setMsgStatus(msgStatus);
        return applicationXML;
    }

    public TingcoApplications buildTingcoAgreementRoleTypes(TingcoApplications tingcoApps, List<AgreementRoleTypeTranslations> arttList) {
        ApplicationSolutions apps = new ApplicationSolutions();
        ApplicationSolution appSolution = new ApplicationSolution();
        ApplicationPackages pkgs = new ApplicationPackages();
        ApplicationPackage pkg = new ApplicationPackage();
        Agreement agreement = new Agreement();
        se.info24.appjaxb.AgreementRoles agreementRoles = new se.info24.appjaxb.AgreementRoles();
        AgreementRolesTypes agreementRolesTypes = new AgreementRolesTypes();
        for (AgreementRoleTypeTranslations artt : arttList) {
            se.info24.appjaxb.AgreementRoleTypeTranslations agreementRoleTypeTrans = new se.info24.appjaxb.AgreementRoleTypeTranslations();
            agreementRoleTypeTrans.setAgreementRoleTypeID(artt.getId().getAgreementRoleTypeId());
            agreementRoleTypeTrans.setRoleTypeName(artt.getRoleTypeName());
            agreementRolesTypes.getAgreementRoleTypeTranslations().add(agreementRoleTypeTrans);
        }
        agreementRoles.setAgreementRolesTypes(agreementRolesTypes);
        agreement.getAgreementRoles().add(agreementRoles);
        pkg.getAgreement().add(agreement);
        pkgs.getApplicationPackage().add(pkg);
        appSolution.getApplicationPackages().add(pkgs);
        apps.getApplicationSolution().add(appSolution);
        tingcoApps.getApplicationSolutions().add(apps);
        return tingcoApps;
    }

    public TingcoApplications buildTingcoAgreements(TingcoApplications tingcoApps, List<Agreements> agreementsList) {
        ApplicationSolutions appSolutions = new ApplicationSolutions();
        ApplicationSolution appSolution = new ApplicationSolution();
        ApplicationPackages appPackages = new ApplicationPackages();
        ApplicationPackage appPackage = new ApplicationPackage();
        for (Agreements ag : agreementsList) {
            Agreement agreement = new Agreement();
            agreement.setAgreementID(ag.getAgreementId());
            agreement.setAgreementName(ag.getAgreementName());
            appPackage.getAgreement().add(agreement);
        }
        appPackages.getApplicationPackage().add(appPackage);
        appSolution.getApplicationPackages().add(appPackages);
        appSolutions.getApplicationSolution().add(appSolution);
        tingcoApps.getApplicationSolutions().add(appSolutions);
        return tingcoApps;
    }

    public TingcoApplications buildTingcoAgreementRolesDetails(TingcoApplications tingcoApps, List<AgreementRoles> agreementRolesList, int countryId, String userId, String timeZoneGMToffset, Session session) {
        try {
            ApplicationSolutions apps = new ApplicationSolutions();
            ApplicationSolution appSolution = new ApplicationSolution();
            ApplicationPackages pkgs = new ApplicationPackages();
            ApplicationPackage pkg = new ApplicationPackage();
            Agreement agreement = new Agreement();
            GregorianCalendar gc = new GregorianCalendar();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Set<AgreementRoleTypeTranslations> arttList = new HashSet<AgreementRoleTypeTranslations>();
            Users2 users2;
            for (AgreementRoles agr : agreementRolesList) {
                se.info24.appjaxb.AgreementRoles agreementRoles = new se.info24.appjaxb.AgreementRoles();
                agreementRoles.setObjectID(agr.getId().getObjectId());
                agreementRoles.setObjectName(agr.getObjectName());
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, agr.getActiveFromDate()));
                agreementRoles.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                agreementRoles.setActiveFromDateTCMv3(dateFormat.format(gc.getTime()));
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, agr.getActiveToDate()));
                agreementRoles.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                agreementRoles.setActiveToDateTCMV3(dateFormat.format(gc.getTime()));
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, agr.getUpdatedDate()));
                agreementRoles.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                agreementRoles.setUpdatedDateTCMV3(dateFormat2.format(gc.getTime()));
                arttList = agr.getAgreementRolesTypes().getAgreementRoleTypeTranslationses();
                for (AgreementRoleTypeTranslations artt : arttList) {
                    if ((agr.getAgreementRolesTypes().getAgreementRoleTypeId().equalsIgnoreCase(artt.getId().getAgreementRoleTypeId())) && (artt.getId().getCountryId() == countryId)) {
                        AgreementRolesTypes agreeementRoleTypes = new AgreementRolesTypes();
                        se.info24.appjaxb.AgreementRoleTypeTranslations agreementRoleTypeTrans = new se.info24.appjaxb.AgreementRoleTypeTranslations();
                        agreementRoleTypeTrans.setRoleTypeName(artt.getRoleTypeName());
                        agreeementRoleTypes.getAgreementRoleTypeTranslations().add(agreementRoleTypeTrans);
                        agreementRoles.setAgreementRolesTypes(agreeementRoleTypes);
                        break;
                    }
                }
                users2 = userDAO.getUserById(userId, session);
                agreementRoles.setLastUpdatedByUser(users2.getFirstName() + " " + users2.getLastName());
                agreement.getAgreementRoles().add(agreementRoles);
            }
            pkg.getAgreement().add(agreement);
            pkgs.getApplicationPackage().add(pkg);
            appSolution.getApplicationPackages().add(pkgs);
            apps.getApplicationSolution().add(appSolution);
            tingcoApps.getApplicationSolutions().add(apps);
            return tingcoApps;
        } catch (Exception ex) {
            return tingcoApps;
        }
    }

    public TingcoApplications buildTingcoAllApplicationModules(TingcoApplications tingcoApps, List<GroupVisibleApplicationModules> gvamList, List<ApplicationModuleTranslations> amtList, int countryId, List<se.info24.pojo.ApplicationModules> appModulesList) {
        ApplicationSolutions apps = new ApplicationSolutions();
        ApplicationSolution appSolution = new ApplicationSolution();
        ApplicationPackages pkgs = new ApplicationPackages();
        ApplicationPackage pkg = new ApplicationPackage();
        for (GroupVisibleApplicationModules gvam : gvamList) {
            for (ApplicationModuleTranslations amt : amtList) {
                if (gvam.getApplicationModules().getApplicationModuleId().equalsIgnoreCase(amt.getId().getApplicationModuleId()) && amt.getId().getCountryId() == countryId) {
                    se.info24.appjaxb.ApplicationModules am = new se.info24.appjaxb.ApplicationModules();
                    am.setApplicationModuleID(amt.getId().getApplicationModuleId());
                    ApplicationModuleNames name = new ApplicationModuleNames();
                    ModuleName mn = new ModuleName();
                    mn.setValue(amt.getApplicationModuleName());
                    am.setApplicationModuleDescription(amt.getApplicationModuleDescription());
                    name.getModuleName().add(mn);
                    am.setApplicationModuleNames(name);
                    for (se.info24.pojo.ApplicationModules applicationModules : appModulesList) {
                        if (applicationModules.getApplicationModuleId().equalsIgnoreCase(amt.getId().getApplicationModuleId())) {
                            am.setDisplayOrder(applicationModules.getDisplayOrder());
                        }
                    }

                    pkg.getApplicationModules().add(am);

                }
            }
        }
        pkgs.getApplicationPackage().add(pkg);
        appSolution.getApplicationPackages().add(pkgs);
        apps.getApplicationSolution().add(appSolution);
        tingcoApps.getApplicationSolutions().add(apps);
        return tingcoApps;
    }

    public TingcoApplications buildTingcoApplicationModules(TingcoApplications tingcoApps, List<ApplicationModuleTranslations> translations, int countryId) {
        ApplicationSolutions appSolutions = new ApplicationSolutions();
        ApplicationSolution appSolution = new ApplicationSolution();
        ApplicationPackages appPackages = new ApplicationPackages();
        int seqId = 1;
        for (ApplicationModuleTranslations amt : translations) {
            ApplicationPackage pkg = new ApplicationPackage();
            pkg.setSeqNo(seqId++);
            se.info24.appjaxb.ApplicationModules am = new se.info24.appjaxb.ApplicationModules();
            am.setApplicationModuleID(amt.getId().getApplicationModuleId());
            ApplicationModuleNames name = new ApplicationModuleNames();
            ModuleName mn = new ModuleName();
            mn.setValue(amt.getApplicationModuleName());
            name.getModuleName().add(mn);
            am.setApplicationModuleNames(name);
            pkg.getApplicationModules().add(am);
            appPackages.getApplicationPackage().add(pkg);
        }

        appSolution.getApplicationPackages().add(appPackages);
        appSolutions.getApplicationSolution().add(appSolution);
        tingcoApps.getApplicationSolutions().add(appSolutions);
        return tingcoApps;
    }

    public TingcoApplications buildTingcoApplicationSolutions(TingcoApplications tingcoApps, List<se.info24.pojo.ApplicationSolutions> applicationSolutionsList, List<GroupVisibleApplicationSolutions> groupVisibleappSolutionsList, List<ApplicationSolutionTranslations> appSolutionTransList, List<se.info24.pojo.ApplicationPackages> appPackagesList,
            List<GroupApplicationPackages> groupAppPackagesList, List<ApplicationPackageTranslations> appPackageTransList, List<Agreements> agreementsList, int countryId) throws DatatypeConfigurationException {
        ApplicationSolutions appSolutions = new ApplicationSolutions();
        for (se.info24.pojo.ApplicationSolutions ast : applicationSolutionsList) {
            for (GroupVisibleApplicationSolutions gvap : groupVisibleappSolutionsList) {
                if (gvap.getApplicationSolutions().getApplicationSolutionId().equalsIgnoreCase(ast.getApplicationSolutionId())) {
                    ApplicationSolution appSolution = new ApplicationSolution();
                    for (ApplicationSolutionTranslations astt : appSolutionTransList) {
                        if (ast.getApplicationSolutionId().equalsIgnoreCase(astt.getId().getApplicationSolutionId())) {
                            appSolution.setApplicationSolutionID(ast.getApplicationSolutionId());
                            appSolution.setDisplayOrder(ast.getDisplayOrder());
                            ApplicationSolutionNames name = new ApplicationSolutionNames();
                            SolutionName sn = new SolutionName();
                            sn.setValue(astt.getApplicationSolutionName());
                            name.getSolutionName().add(sn);
                            appSolution.setApplicationSolutionNames(name);
                            if (astt.getApplicationSolutionDescription() != null) {
                                appSolution.setApplicationSolutionDescription(astt.getApplicationSolutionDescription());
                            }
                        }
                    }
                    ApplicationPackages appPackages = new ApplicationPackages();
                    int seqId = 1;
                    for (GroupApplicationPackages gap : groupAppPackagesList) {
                        for (se.info24.pojo.ApplicationPackages ap : appPackagesList) {
                            if (gap.getApplicationPackages().getApplicationPackageId().equalsIgnoreCase(ap.getApplicationPackageId()) && ast.getApplicationSolutionId().equalsIgnoreCase(ap.getApplicationSolutions().getApplicationSolutionId())) {
                                Set<ApplicationPackageTranslations> aptransList = ap.getApplicationPackageTranslationses();
                                for (ApplicationPackageTranslations apt : aptransList) {
                                    if (gap.getApplicationPackages().getApplicationPackageId().equalsIgnoreCase(ap.getApplicationPackageId()) && apt.getId().getCountryId() == countryId) {
                                        ApplicationPackage appPackage = new ApplicationPackage();
                                        appPackage.setSeqNo(seqId++);
                                        appPackage.setApplicationPackageID(apt.getId().getApplicationPackageId());
                                        ApplicationPackageNames appPackagename = new ApplicationPackageNames();
                                        PackageName pn = new PackageName();
                                        pn.setValue((String) apt.getApplicationPackageName());
                                        appPackagename.getPackageName().add(pn);
                                        appPackage.setApplicationPackageNames(appPackagename);


                                        for (Agreements ag : agreementsList) {
                                            if (apt.getId().getApplicationPackageId().equalsIgnoreCase(gap.getId().getApplicationPackageId()) && gap.getAgreements().getAgreementId().equalsIgnoreCase(ag.getAgreementId())) {
                                                Agreement agreement = new Agreement();
                                                agreement.setAgreementID(ag.getAgreementId());
                                                agreement.setAgreementName(ag.getAgreementName());
                                                appPackage.getAgreement().add(agreement);
                                                appPackage.setIsEnabled(gap.getIsEnabled());
                                                appPackage.setIsTrial(gap.getIsTrial());
                                                appPackage.setIsPendingDelete(gap.getIsPendingDelete());

                                                GregorianCalendar gc = new GregorianCalendar();
                                                gc.setTime(gap.getActiveFromDate());
                                                appPackage.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                                                gc.setTime(gap.getActiveToDate());
                                                appPackage.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                                                if (gap.getDeleteAfterThisDate() != null) {
                                                    gc.setTime(gap.getDeleteAfterThisDate());
                                                    appPackage.setDeleteAfterThisDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));
                                                }

                                            }
                                        }
                                        appPackages.getApplicationPackage().add(appPackage);
                                    }
                                }
                            }
                        }
                    }
                    appSolution.getApplicationPackages().add(appPackages);
                    appSolutions.getApplicationSolution().add(appSolution);
                }
            }
        }
        tingcoApps.getApplicationSolutions().add(appSolutions);
        return tingcoApps;
    }

    public TingcoApplications buildTingcoApplicationXML(TingcoApplications appXML, Applications application, int userCountryId) throws DatatypeConfigurationException {
        Application app = new Application();
        ApplicationDescriptions appDescriptions = new ApplicationDescriptions();
        app.setSeqNo(1);
        ApplicationTypeID appTypeID = new ApplicationTypeID();
        String value = null;
        Iterator it;
        for (it = application.getApplicationTypes().getApplicationTypeTranslationses().iterator(); it.hasNext();) {
            ApplicationTypeTranslations appTypeTrans = (ApplicationTypeTranslations) it.next();
            if (userCountryId == appTypeTrans.getCountry().getCountryId()) {
                value = appTypeTrans.getApplicationTypeName();
                break;
            }
        }
        appTypeID.setApplicationTypeName(value);
        appTypeID.setValue(application.getApplicationTypes().getApplicationTypeId());
        app.setApplicationTypeID(appTypeID);
        app.setIsEnabled(application.getIsEnabled());
        for (it = application.getApplicationTranslationses().iterator(); it.hasNext();) {
            ApplicationTranslations appTrans = (ApplicationTranslations) it.next();
            if (userCountryId == appTrans.getCountry().getCountryId()) {
                ApplicationName appName = new ApplicationName();
                appName.setLanguage(appTrans.getCountry().getLanguageCode());
                appName.setValue(appTrans.getApplicationName());
                ApplicationNames appNames = new ApplicationNames();
                appNames.getApplicationName().add(appName);
                app.setApplicationNames(appNames);
                if (appTrans.getApplicationDescription() != null) {
                    ApplicationDescription appDesc = new ApplicationDescription();
                    appDesc.setLanguage(appTrans.getCountry().getLanguageCode());
                    appDesc.setValue(appTrans.getApplicationDescription());
                    appDescriptions.getApplicationDescription().add(appDesc);

                }
            }
        }
        app.setApplicationDescriptions(appDescriptions);

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(application.getActiveFromDate());
        app.setActiveFromDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
        cal.setTime(application.getActiveToDate());
        app.setActiveToDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
        if (application.getApplicationLogoUrl() != null) {
            app.setApplicationLogoURL(application.getApplicationLogoUrl());
        }
        app.setApplicationVersion(application.getApplicationVersion());
        LastUpdatedByUserID lastUserId = new LastUpdatedByUserID();
        lastUserId.setUserFullName(application.getLastUpdatedByUserId()); // Change to User Name.
        lastUserId.setValue(application.getLastUpdatedByUserId());
        app.setLastUpdatedByUserID(lastUserId);
        cal.setTime(application.getCreatedDate());
        app.setCreatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
        cal.setTime(application.getUpdatedDate());
        app.setUpdatedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));

        se.info24.appjaxb.ApplicationSettings appSS = new se.info24.appjaxb.ApplicationSettings();
        ApplicationSetting as = null;
        byte asSeqId = 1;
        for (it = application.getApplicationSettingses().iterator(); it.hasNext();) {
            ApplicationSettings ass = (ApplicationSettings) it.next();
            as = new ApplicationSetting();
            as.setSeqNo(asSeqId++);
            as.setApplicationSettingID(ass.getApplicationSettingId());
            as.setApplicationSettingName(ass.getApplicationSettingName());
            as.setApplicationSettingValue(ass.getApplicationSettingValue());
            if (ass.getApplicationSettingParentId() != null) {
                as.setApplicationSettingParentID(ass.getApplicationSettingParentId());
            }
            lastUserId = new LastUpdatedByUserID();
            lastUserId.setUserFullName(ass.getLastUpdatedByUserId()); // Change to User Name.
            lastUserId.setValue(ass.getLastUpdatedByUserId());
            as.setLastUpdatedByUserID(lastUserId);
            appSS.getApplicationSetting().add(as);
        }
        app.setApplicationSettings(appSS);
        // TODO
        // Need to add code to create XML data for Application Permissions.
        se.info24.appjaxb.Applications apps = new se.info24.appjaxb.Applications();
        apps.getApplication().add(app);
        appXML.setApplications(apps);
        return appXML;
    }

    public TingcoApplications buildAllApplicationsXML(TingcoApplications appXML, List<ApplicationTranslations> appTranList) {

        if (appTranList == null) {
            appXML.getMsgStatus().setResponseCode(-1);
            appXML.getMsgStatus().setResponseText("Error occured");
            return appXML;
        }
        if (appTranList.isEmpty()) {
            appXML.getMsgStatus().setResponseCode(-1);
            appXML.getMsgStatus().setResponseText("Data Not Found");
            return appXML;
        }
        int seqId = 1;
        se.info24.appjaxb.Applications apps = new se.info24.appjaxb.Applications();
        for (ApplicationTranslations appTrans : appTranList) {
            Application app = new Application();
            app.setSeqNo(seqId++);
            app.setApplicationID(appTrans.getApplications().getApplicationId());
            ApplicationName appName = new ApplicationName();
            appName.setLanguage(appTrans.getCountry().getLanguageCode());
            appName.setValue(appTrans.getApplicationName());
            ApplicationNames appNames = new ApplicationNames();
            appNames.getApplicationName().add(appName);
            app.setApplicationNames(appNames);
            apps.getApplication().add(app);
        }
        appXML.setApplications(apps);
        return appXML;
    }

//    public TingcoApplications buildTingcoApplicationPackages(TingcoApplications tingcoApps, List<GroupVisibleApplicationPackages> groupVisibleAppPackagesList, List<ApplicationPackageTranslations> appPackageTransList, int countryId, List<ApplicationPackages> applicationPackagesList) {
    public TingcoApplications buildTingcoApplicationPackages(TingcoApplications tingcoApps, List<GroupVisibleApplicationPackages> groupVisibleAppPackagesList, List<ApplicationPackageTranslations> appPackageTransList, int countryId, List<se.info24.pojo.ApplicationPackages> applicationPackagesList) {
        ApplicationSolutions appSolutions = new ApplicationSolutions();
        ApplicationSolution appSolution = new ApplicationSolution();
        ApplicationPackages appPackages = new ApplicationPackages();
        int id = 1;
        for (GroupVisibleApplicationPackages gvap : groupVisibleAppPackagesList) {
            for (ApplicationPackageTranslations apt : appPackageTransList) {
                if (gvap.getApplicationPackages().getApplicationPackageId().equalsIgnoreCase(apt.getId().getApplicationPackageId()) && apt.getId().getCountryId() == countryId) {
                    ApplicationPackage pkg = new ApplicationPackage();
                    pkg.setSeqNo(id++);
                    pkg.setApplicationPackageID(apt.getId().getApplicationPackageId());
                    for (se.info24.pojo.ApplicationPackages applicationPackages : applicationPackagesList) {
                        if (applicationPackages.getApplicationPackageId().equalsIgnoreCase(apt.getId().getApplicationPackageId())) {
                            pkg.setApplicationSolutionID(applicationPackages.getApplicationSolutions().getApplicationSolutionId());
                        }
                    }
                    ApplicationPackageNames name = new ApplicationPackageNames();
                    PackageName pn = new PackageName();
                    pn.setValue((String) apt.getApplicationPackageName());
                    name.getPackageName().add(pn);
                    pkg.setApplicationPackageNames(name);
                    appPackages.getApplicationPackage().add(pkg);
                    break;
                }
            }
        }
        appSolution.getApplicationPackages().add(appPackages);
        appSolutions.getApplicationSolution().add(appSolution);

        tingcoApps.getApplicationSolutions().add(appSolutions);
        return tingcoApps;
    }

    public TingcoApplications buildAllApplicationsSolutionXML(TingcoApplications tingcoApps, List<ApplicationSolutionTranslations> astList) {
        ApplicationSolutions appSolutions = new ApplicationSolutions();
        for (ApplicationSolutionTranslations ast : astList) {
            ApplicationSolution appSolution = new ApplicationSolution();
            appSolution.setApplicationSolutionID(ast.getId().getApplicationSolutionId());
            SolutionName sn = new SolutionName();
            sn.setValue(ast.getApplicationSolutionName());
            ApplicationSolutionNames asn = new ApplicationSolutionNames();
            asn.getSolutionName().add(sn);
            appSolution.setApplicationSolutionNames(asn);
            appSolutions.getApplicationSolution().add(appSolution);
        }
        tingcoApps.getApplicationSolutions().add(appSolutions);
        return tingcoApps;

    }

    public void buildAllApplicationModules(TingcoApplications tincoApps, List<ApplicationPackageTranslations> apt, ApplicationModuleTranslations amt, String applicationsolutionid) {
        ApplicationSolutions as = new ApplicationSolutions();
        ApplicationSolution solution = new ApplicationSolution();
        ApplicationPackages packages = new ApplicationPackages();
        ApplicationPackage pkg = new ApplicationPackage();

        ApplicationPackageNames apn = new ApplicationPackageNames();
        apn.setPackageDesc(apt.get(0).getApplicationPackageDescrption());
        PackageName pn = new PackageName();
        pn.setValue(apt.get(0).getApplicationPackageName());

        apn.getPackageName().add(pn);


        ApplicationModules modules = new ApplicationModules();
        ApplicationModuleNames names = new ApplicationModuleNames();
        ModuleName mn = new ModuleName();
        mn.setValue(amt.getApplicationModuleName());
        names.setModuleDesc(amt.getApplicationModuleDescription());
        modules.setApplicationModuleID(amt.getApplicationModules().getApplicationModuleId());
        modules.setApplicationModuleNames(names);

        pkg.getApplicationModules().add(modules);

        pkg.setApplicationPackageNames(apn);

        packages.getApplicationPackage().add(pkg);

        solution.getApplicationPackages().add(packages);

        as.getApplicationSolution().add(solution);

        tincoApps.getApplicationSolutions().add(as);

    }

    public TingcoApplications buildAgreementInfoDetails(TingcoApplications tingcoApps, Agreements agreements, GroupTranslations groupTranslations,
            se.info24.pojo.AgreementTypeTranslations agreementTypeTranslations, se.info24.pojo.AgreementStatusTranslations agreementStatusTranslations, String timeZoneGMToffset) throws DatatypeConfigurationException {
        GregorianCalendar gc = new GregorianCalendar();
        AgreementDetails agreementDetails = new AgreementDetails();
        Agreement agreement = new Agreement();
        GroupID groupID = new GroupID();
        AgreementParentID agreementParentID = new AgreementParentID();
        if (agreements.getAgreementOwnerGroupId() != null) {
            groupID.setValue(agreements.getAgreementOwnerGroupId());
            if (groupTranslations != null) {
                groupID.setGroupName(groupTranslations.getGroupName());
            }
            agreement.setGroupID(groupID);
        }

        if (agreements.getAgreements() != null) {
            agreementParentID.setAgreementName(agreements.getAgreements().getAgreementName());
            agreementParentID.setValue(agreements.getAgreements().getAgreementId());
            agreement.setAgreementParentID(agreementParentID);
        }

        agreement.setAgreementID(agreements.getAgreementId());
        agreement.setAgreementName(agreements.getAgreementName());
        if (agreements.getAgreementDescription() != null) {
            agreement.setAgreementDesc(agreements.getAgreementDescription());
        }
        if (agreements.getAgreementNumber() != null) {
            agreement.setAgreementNumber(agreements.getAgreementNumber());
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (agreements.getStartDate() != null) {
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, agreements.getStartDate()));
            agreement.setStartDate(dateFormat.format(gc.getTime()));
        }
        if (agreements.getEndDate() != null) {
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, agreements.getEndDate()));
            agreement.setEndDate(dateFormat.format(gc.getTime()));
        }
        if (agreementStatusTranslations != null) {
            AgreementStatusTranslations agreementStatusTranslationsJaxb = new AgreementStatusTranslations();
            agreementStatusTranslationsJaxb.setAgreementStatusID(agreementStatusTranslations.getId().getAgreementStatusId());
            agreementStatusTranslationsJaxb.setAgreementStatusName(agreementStatusTranslations.getAgreementStatusName());
            agreement.getAgreementStatusTranslations().add(agreementStatusTranslationsJaxb);
        }
        if (agreementTypeTranslations != null) {
            AgreementTypeTranslations att = new AgreementTypeTranslations();
            att.setAgreementTypeID(agreementTypeTranslations.getId().getAgreementTypeId());
            att.setAgreementTypeName(agreementTypeTranslations.getAgreementTypeName());
            agreement.getAgreementTypeTranslations().add(att);
        }
        agreementDetails.getAgreement().add(agreement);
        tingcoApps.setAgreementDetails(agreementDetails);
        return tingcoApps;
    }

    public TingcoApplications buildAgreementTypes(TingcoApplications tingcoApps, List<se.info24.pojo.AgreementTypeTranslations> agreementTypeTranslationses) {
        if (agreementTypeTranslationses.isEmpty()) {
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Data Not Found");
            return tingcoApps;
        }
        AgreementDetails agreementDetails = new AgreementDetails();
        Agreement agreement = new Agreement();

        Collections.sort(agreementTypeTranslationses, new Comparator<se.info24.pojo.AgreementTypeTranslations>() {

            public int compare(se.info24.pojo.AgreementTypeTranslations o1, se.info24.pojo.AgreementTypeTranslations o2) {
                return o1.getAgreementTypeName().compareToIgnoreCase(o2.getAgreementTypeName());
            }
        });

        for (se.info24.pojo.AgreementTypeTranslations att : agreementTypeTranslationses) {
            AgreementTypeTranslations agreementTypeTranslations = new AgreementTypeTranslations();
            agreementTypeTranslations.setAgreementTypeID(att.getId().getAgreementTypeId());
            agreementTypeTranslations.setAgreementTypeName(att.getAgreementTypeName());
            agreement.getAgreementTypeTranslations().add(agreementTypeTranslations);
        }
        agreementDetails.getAgreement().add(agreement);
        tingcoApps.setAgreementDetails(agreementDetails);
        return tingcoApps;
    }

    public TingcoApplications buildAgreementStatus(TingcoApplications tingcoApps, List<se.info24.pojo.AgreementStatusTranslations> agreementStatusTranslationses) {
        if (agreementStatusTranslationses.isEmpty()) {
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("Data Not Found");
            return tingcoApps;
        }
        AgreementDetails agreementDetails = new AgreementDetails();
        Agreement agreement = new Agreement();

        Collections.sort(agreementStatusTranslationses, new Comparator<se.info24.pojo.AgreementStatusTranslations>() {

            public int compare(se.info24.pojo.AgreementStatusTranslations o1, se.info24.pojo.AgreementStatusTranslations o2) {
                return o1.getAgreementStatusName().compareToIgnoreCase(o2.getAgreementStatusName());
            }
        });

        for (se.info24.pojo.AgreementStatusTranslations ast : agreementStatusTranslationses) {
            AgreementStatusTranslations agreementStatusTranslations = new AgreementStatusTranslations();
            agreementStatusTranslations.setAgreementStatusID(ast.getId().getAgreementStatusId());
            agreementStatusTranslations.setAgreementStatusName(ast.getAgreementStatusName());
            agreement.getAgreementStatusTranslations().add(agreementStatusTranslations);
        }
        agreementDetails.getAgreement().add(agreement);
        tingcoApps.setAgreementDetails(agreementDetails);
        return tingcoApps;
    }

//    public TingcoApplications buildGetAgreementDetails(TingcoApplications tingcoApps, List<Agreements> agreementses, List<GroupTranslations> groupTranslationses, List<se.info24.pojo.AgreementStatusTranslations> agreementStatusTranslationses, String timeZoneGMToffset) {
//        if (agreementses.isEmpty()) {
//            tingcoApps.getMsgStatus().setResponseCode(-1);
//            tingcoApps.getMsgStatus().setResponseText("No Data Found");
//            return tingcoApps;
//        }
//        GregorianCalendar gc = new GregorianCalendar();
//        AgreementDetails agreementDetails = new AgreementDetails();
//        for (se.info24.pojo.Agreements agreements : agreementses) {
//            Agreement agreement = new Agreement();
//            agreement.setAgreementID(agreements.getAgreementId());
//            agreement.setAgreementName(agreements.getAgreementName());
//            if (agreements.getAgreementNumber() != null) {
//                agreement.setAgreementNumber(agreements.getAgreementNumber());
//            }
//            for (GroupTranslations gt : groupTranslationses) {
//                if (gt.getId().getGroupId().equalsIgnoreCase(agreements.getAgreementOwnerGroupId())) {
//                    GroupID groupID = new GroupID();
//                    groupID.setGroupName(gt.getGroupName());
//                    groupID.setValue(gt.getId().getGroupId());
//                    agreement.setGroupID(groupID);
//                    break;
//                }
//            }
//
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            if (agreements.getStartDate() != null) {
//                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, agreements.getStartDate()));
//                agreement.setStartDate(dateFormat.format(gc.getTime()));
//            }
//            if (agreements.getEndDate() != null) {
//                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, agreements.getEndDate()));
//                agreement.setEndDate(dateFormat.format(gc.getTime()));
//            }
//            for (se.info24.pojo.AgreementStatusTranslations ast : agreementStatusTranslationses) {
//                if (agreements.getAgreementStatuses() != null) {
//                    if (ast.getId().getAgreementStatusId().equalsIgnoreCase(agreements.getAgreementStatuses().getAgreementStatusId())) {
//                        if (ast.getAgreementStatusName() != null) {
//                            AgreementStatusTranslations statusTranslation = new AgreementStatusTranslations();
//                            statusTranslation.setAgreementStatusName(ast.getAgreementStatusName());
//                            agreement.getAgreementStatusTranslations().add(statusTranslation);
//                        }
//                        break;
//                    }
//                }
//            }
//            agreementDetails.getAgreement().add(agreement);
////            break;
////                }
////            }
//        }
//        tingcoApps.setAgreementDetails(agreementDetails);
//        return tingcoApps;
//    }
    public TingcoApplications buildGetAgreementDetails(TingcoApplications tingcoApps, List<Agreements> agreementses, int countryId, List<se.info24.pojo.AgreementStatusTranslations> agreementStatusTranslationses, String timeZoneGMToffset, Session session) {
        GregorianCalendar gc = new GregorianCalendar();
        AgreementDetails agreementDetails = new AgreementDetails();
        GroupTranslations gt = null;
        for (se.info24.pojo.Agreements agreements : agreementses) {
            Agreement agreement = new Agreement();
            agreement.setAgreementID(agreements.getAgreementId());
            agreement.setAgreementName(agreements.getAgreementName());
            if (agreements.getAgreementNumber() != null) {
                agreement.setAgreementNumber(agreements.getAgreementNumber());
            }
//            for (GroupTranslations gt : groupTranslationses) {
//                System.out.println("inner '"+gt.getId().getGroupId());
//                if (gt.getId().getGroupId().equalsIgnoreCase(agreements.getAgreementOwnerGroupId())) {
            gt = groupDAO.getGroupTranslationsByIds(agreements.getAgreementOwnerGroupId(), countryId, session);
            GroupID groupID = new GroupID();
            groupID.setGroupName(gt.getGroupName());
            groupID.setValue(gt.getId().getGroupId());
            agreement.setGroupID(groupID);
//                }

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            if (agreements.getStartDate() != null) {
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, agreements.getStartDate()));
                agreement.setStartDate(dateFormat.format(gc.getTime()));
                agreement.setStartDateTCMV3(dateFormat2.format(gc.getTime()));
            }
            if (agreements.getEndDate() != null) {
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, agreements.getEndDate()));
                agreement.setEndDate(dateFormat.format(gc.getTime()));
                agreement.setEndDateTCMV3(dateFormat2.format(gc.getTime()));
            }
            for (se.info24.pojo.AgreementStatusTranslations ast : agreementStatusTranslationses) {
                if (agreements.getAgreementStatuses() != null) {
                    if (ast.getId().getAgreementStatusId().equalsIgnoreCase(agreements.getAgreementStatuses().getAgreementStatusId())) {
                        if (ast.getAgreementStatusName() != null) {
                            AgreementStatusTranslations statusTranslation = new AgreementStatusTranslations();
                            statusTranslation.setAgreementStatusName(ast.getAgreementStatusName());
                            agreement.getAgreementStatusTranslations().add(statusTranslation);
                        }
                        break;
                    }
                }
            }
            agreementDetails.getAgreement().add(agreement);
//                break;
//                }
//            }
        }
        tingcoApps.setAgreementDetails(agreementDetails);
        return tingcoApps;
    }

    public TingcoApplications buildAgreementDetailsForPopup(TingcoApplications tingcoApps, List<Agreements> agreementses, List<GroupTranslations> groupTranslationses, String timeZoneGMToffset) {

//        Collections.sort(agreementses, new Comparator<se.info24.pojo.Agreements>() {
//            public int compare(se.info24.pojo.Agreements o1, se.info24.pojo.Agreements o2) {
//                return o1.getAgreementName().compareToIgnoreCase(o2.getAgreementName());
//            }
//        });

        AgreementDetails agreementDetails = new AgreementDetails();
        for (se.info24.pojo.Agreements agreements : agreementses) {
            Agreement agreement = new Agreement();
            agreement.setAgreementID(agreements.getAgreementId());
            agreement.setAgreementName(agreements.getAgreementName());
            if (agreements.getAgreementNumber() != null) {
                agreement.setAgreementNumber(agreements.getAgreementNumber());
            }

            GroupID groupID = new GroupID();
            for (GroupTranslations gt : groupTranslationses) {
                if (gt.getId().getGroupId().equalsIgnoreCase(agreements.getAgreementOwnerGroupId())) {
                    groupID.setGroupName(gt.getGroupName());
                    groupID.setValue(gt.getId().getGroupId());
                    agreement.setGroupID(groupID);
                }
            }
            agreementDetails.getAgreement().add(agreement);
        }
        tingcoApps.setAgreementDetails(agreementDetails);
        return tingcoApps;
    }

    public TingcoApplications buildGetAgreementItemTypes(TingcoApplications tingcoApps, List<AgreementItemTypeTranslations> aitts) {
        if (aitts.isEmpty()) {
            tingcoApps.getMsgStatus().setResponseCode(-1);
            tingcoApps.getMsgStatus().setResponseText("No Data Found");
            return tingcoApps;
        }
        AgreementDetails agreementDetails = new AgreementDetails();
        Agreement agreement = new Agreement();
        for (AgreementItemTypeTranslations aitt : aitts) {
            AgreementItemTypeID agreementItemTypeID = new AgreementItemTypeID();
            agreementItemTypeID.setAgreementItemTypeName(aitt.getAgreementItemTypeName());
            agreementItemTypeID.setValue(aitt.getId().getAgreementItemTypeId());
            agreement.getAgreementItemTypeID().add(agreementItemTypeID);
        }
        agreementDetails.getAgreement().add(agreement);
        tingcoApps.setAgreementDetails(agreementDetails);
        return tingcoApps;
    }

    public TingcoApplications buildGetMaxSectionNuember(TingcoApplications tingcoApps, int itemSectionNumber, String agreementId) {
        AgreementDetails agreementDetails = new AgreementDetails();
        Agreement agreement = new Agreement();
        AgreementItems agreementItemsJaxb = new AgreementItems();
        agreementItemsJaxb.setAgreementID(agreementId);
        agreementItemsJaxb.setItemSectionNumber(itemSectionNumber);
        agreement.getAgreementItems().add(agreementItemsJaxb);
        agreementDetails.getAgreement().add(agreement);
        tingcoApps.setAgreementDetails(agreementDetails);
        return tingcoApps;
    }

    public TingcoApplications buildGetAgreementItemById(TingcoApplications tingcoApps, se.info24.pojo.AgreementItems agreementItems, ProductVariantTranslations productVariantTranslations, Users2 users2, AgreementItemTypeTranslations agreementItemTypeTranslations, se.info24.pojo.AgreementStatusTranslations agreementStatusTranslations, String timeZoneGMToffset) throws DatatypeConfigurationException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        AgreementDetails agreementDetails = new AgreementDetails();
        Agreement agreement = new Agreement();
        int seqNo = 1;
        AgreementItems agreementItemsJaxb = new AgreementItems();
        agreementItemsJaxb.setSeqNo(seqNo);
        agreementItemsJaxb.setAgreementItemID(agreementItems.getAgreementItemId());
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, agreementItems.getActiveFromDate()));
        agreementItemsJaxb.setActiveFromDate(dateFormat.format(gc.getTime()));
        if (agreementItems.getActiveToDate() != null) {
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, agreementItems.getActiveToDate()));
            agreementItemsJaxb.setActiveToDate(dateFormat.format(gc.getTime()));
        }
        if (agreementItems.getUpdatedDate() != null) {
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, agreementItems.getUpdatedDate()));
            agreementItemsJaxb.setUpdatedDate(dateFormat.format(gc.getTime()));
        }
        agreementItemsJaxb.setItemName(agreementItems.getItemName());
        agreementItemsJaxb.setItemText(agreementItems.getItemText());
        agreementItemsJaxb.setItemSectionNumber(agreementItems.getItemSectionNumber());

        if (agreementItemTypeTranslations != null) {
            se.info24.appjaxb.AgreementItemTypeTranslations aittJaxb = new se.info24.appjaxb.AgreementItemTypeTranslations();
            aittJaxb.setAgreementItemTypeID(agreementItemTypeTranslations.getId().getAgreementItemTypeId());
            aittJaxb.setAgreementItemTypeName(agreementItemTypeTranslations.getAgreementItemTypeName());
            agreementItemsJaxb.getAgreementItemTypeTranslations().add(aittJaxb);
        }

        if (agreementStatusTranslations != null) {
            AgreementStatusTranslations agreementStatusTranslationsJaxb = new AgreementStatusTranslations();
            agreementStatusTranslationsJaxb.setAgreementStatusID(agreementStatusTranslations.getId().getAgreementStatusId());
            agreementStatusTranslationsJaxb.setAgreementStatusName(agreementStatusTranslations.getAgreementStatusName());
            agreementItemsJaxb.getAgreementStatusTranslations().add(agreementStatusTranslationsJaxb);
        }

        if (productVariantTranslations != null) {
            agreementItemsJaxb.setProductVariantID(productVariantTranslations.getId().getProductVariantId());
            agreementItemsJaxb.setProductVariantName(productVariantTranslations.getProductVariantName());
        }

        if (users2 != null) {
            agreementItemsJaxb.setUpdatedBy(users2.getFirstName() + " " + users2.getLastName());
        }

        if (agreementItems.getPricePlans() != null) {
            agreementItemsJaxb.setPricePlanID(agreementItems.getPricePlans().getPricePlanId());
            agreementItemsJaxb.setPricePlanName(agreementItems.getPricePlans().getPricePlanName());
        }
        agreement.getAgreementItems().add(agreementItemsJaxb);
        agreementDetails.getAgreement().add(agreement);
        tingcoApps.setAgreementDetails(agreementDetails);
        return tingcoApps;
    }

    public TingcoApplications buildGetAgreementItemsDetailsByAgreementID(TingcoApplications tingcoApps, List<se.info24.pojo.AgreementItems> agreementItemsesOrderByISN, List<ProductVariantTranslations> productVariantTranslationsList, List<Users2> users2List, List<AgreementItemTypeTranslations> agreementItemTypeTranslationsList, List<se.info24.pojo.AgreementStatusTranslations> agreementStatusTranslationsList, String timeZoneGMToffset) throws DatatypeConfigurationException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        AgreementDetails agreementDetails = new AgreementDetails();
        Agreement agreement = new Agreement();
        int seqNo = 1;

        for (se.info24.pojo.AgreementItems agreementItems : agreementItemsesOrderByISN) {
            AgreementItems agreementItemsJaxb = new AgreementItems();
            agreementItemsJaxb.setSeqNo(seqNo++);
            agreementItemsJaxb.setAgreementItemID(agreementItems.getAgreementItemId());
            if (agreementItems.getAgreementItemParentId() != null) {
                agreementItemsJaxb.setAgreementItemParentID(agreementItems.getAgreementItemParentId());
            }
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, agreementItems.getActiveFromDate()));
            agreementItemsJaxb.setActiveFromDate(dateFormat.format(gc.getTime()));
            agreementItemsJaxb.setActiveFromDateTCMV3(dateFormat2.format(gc.getTime()));
            if (agreementItems.getActiveToDate() != null) {
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, agreementItems.getActiveToDate()));
                agreementItemsJaxb.setActiveToDate(dateFormat.format(gc.getTime()));
                agreementItemsJaxb.setActiveToDateTCMV3(dateFormat2.format(gc.getTime()));
            }
            if (agreementItems.getUpdatedDate() != null) {
                gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, agreementItems.getUpdatedDate()));
                agreementItemsJaxb.setUpdatedDate(dateFormat.format(gc.getTime()));

            }
            agreementItemsJaxb.setItemName(agreementItems.getItemName());
            agreementItemsJaxb.setItemText(agreementItems.getItemText());
            agreementItemsJaxb.setItemSectionNumber(agreementItems.getItemSectionNumber());

            if (agreementItems.getAgreementItemTypes() != null && !agreementItemTypeTranslationsList.isEmpty()) {
                for (AgreementItemTypeTranslations aitt : agreementItemTypeTranslationsList) {
                    if (aitt.getId().getAgreementItemTypeId().equalsIgnoreCase(agreementItems.getAgreementItemTypes().getAgreementItemTypeId())) {
                        se.info24.appjaxb.AgreementItemTypeTranslations aittJaxb = new se.info24.appjaxb.AgreementItemTypeTranslations();
                        aittJaxb.setAgreementItemTypeID(aitt.getId().getAgreementItemTypeId());
                        aittJaxb.setAgreementItemTypeName(aitt.getAgreementItemTypeName());
                        agreementItemsJaxb.getAgreementItemTypeTranslations().add(aittJaxb);
                        break;
                    }
                }
            }
            if (agreementItems.getAgreementStatuses() != null && !agreementStatusTranslationsList.isEmpty()) {
                for (se.info24.pojo.AgreementStatusTranslations ast : agreementStatusTranslationsList) {
                    if (ast.getId().getAgreementStatusId().equalsIgnoreCase(agreementItems.getAgreementStatuses().getAgreementStatusId())) {
                        AgreementStatusTranslations agreementStatusTranslationsJaxb = new AgreementStatusTranslations();
                        agreementStatusTranslationsJaxb.setAgreementStatusID(ast.getId().getAgreementStatusId());
                        agreementStatusTranslationsJaxb.setAgreementStatusName(ast.getAgreementStatusName());
                        agreementItemsJaxb.getAgreementStatusTranslations().add(agreementStatusTranslationsJaxb);
                        break;
                    }
                }
            }
            if (agreementItems.getObjectId() != null && !productVariantTranslationsList.isEmpty()) {
                for (ProductVariantTranslations pvt : productVariantTranslationsList) {
                    if (pvt.getId().getProductVariantId().equalsIgnoreCase(agreementItems.getObjectId())) {
                        agreementItemsJaxb.setProductVariantID(pvt.getId().getProductVariantId());
                        agreementItemsJaxb.setProductVariantName(pvt.getProductVariantName());
                        break;
                    }
                }
            }
            if (agreementItems.getLastUpdatedByUserId() != null && !users2List.isEmpty()) {
                for (Users2 users2 : users2List) {
                    if (users2.getUserId().equalsIgnoreCase(agreementItems.getLastUpdatedByUserId())) {
                        agreementItemsJaxb.setUpdatedBy(users2.getFirstName() + " " + users2.getLastName());
                        break;
                    }
                }
            }
            if (agreementItems.getPricePlans() != null) {
                agreementItemsJaxb.setPricePlanID(agreementItems.getPricePlans().getPricePlanId());
                agreementItemsJaxb.setPricePlanName(agreementItems.getPricePlans().getPricePlanName());
            }
            agreementItemsJaxb.setAgreementID(agreementItems.getAgreements().getAgreementId());
            agreement.getAgreementItems().add(agreementItemsJaxb);
        }

        agreementDetails.getAgreement().add(agreement);
        tingcoApps.setAgreementDetails(agreementDetails);
        return tingcoApps;
    }

    public TingcoApplications buildGetAgreementBillingDetails(TingcoApplications tingcoApps, List<AgreementBillingData> agreementBillingDatas, Agreements agg) {
        AgreementDetails agreementDetails = new AgreementDetails();
        Agreement agreement = new Agreement();
        int seqNo = 1;
        boolean flag = false;
        se.info24.appjaxb.AgreementBillingData agreementBillingDataJaxb = null;
        if (agg.getAgreementNumber() != null) {
            agreement.setAgreementName(agg.getAgreementName() + " / " + agg.getAgreementNumber());
        } else {
            agreement.setAgreementName(agg.getAgreementName() + " / -");
        }
        for (AgreementBillingData agreementBillingData : agreementBillingDatas) {
            flag = true;
            agreementBillingDataJaxb = new se.info24.appjaxb.AgreementBillingData();

            agreementBillingDataJaxb.setSKU(agreementBillingData.getSku());
            agreementBillingDataJaxb.setCategoryName(agreementBillingData.getCategoryName());
            agreementBillingDataJaxb.setNumberOfObjects(agreementBillingData.getNumberOfObjects() + "");
            agreementBillingDataJaxb.setNumberOfNewObjects(agreementBillingData.getNumberOfNewObjects() + "");
            agreement.getAgreementBillingData().add(agreementBillingDataJaxb);
        }
        if (!flag) {
            agreementBillingDataJaxb = new se.info24.appjaxb.AgreementBillingData();
            agreementBillingDataJaxb.setAgreementName(agg.getAgreementName() + " / " + agg.getAgreementNumber());
            agreement.getAgreementBillingData().add(agreementBillingDataJaxb);
        }
        agreementDetails.getAgreement().add(agreement);
        tingcoApps.setAgreementDetails(agreementDetails);
        return tingcoApps;
    }

    public TingcoApplications buildGetApplicationEmailTemplatesByID(TingcoApplications tingcoApps, ApplicationEmailTemplates applicationEmailTemplatesPojo) {
        int seqId = 1;
        se.info24.appjaxb.Applications apps = new se.info24.appjaxb.Applications();
        Application app = new Application();
        se.info24.appjaxb.ApplicationEmailTemplates applicationEmailTemplates = new se.info24.appjaxb.ApplicationEmailTemplates();
        applicationEmailTemplates.setApplicationEmailTemplateID(applicationEmailTemplatesPojo.getApplicationEmailTemplateId());
        applicationEmailTemplates.setApplicationID(applicationEmailTemplatesPojo.getApplications().getApplicationId());
        applicationEmailTemplates.setCountryID(applicationEmailTemplatesPojo.getCountryId() + "");
        applicationEmailTemplates.setObjectCode(applicationEmailTemplatesPojo.getObjectCode());
        applicationEmailTemplates.setEmailSubject(applicationEmailTemplatesPojo.getEmailSubject());
        applicationEmailTemplates.setEmailBody(applicationEmailTemplatesPojo.getEmailBody());
        applicationEmailTemplates.setEmailSignature(applicationEmailTemplatesPojo.getEmailSignature());
        app.setApplicationEmailTemplates(applicationEmailTemplates);
        apps.getApplication().add(app);
        tingcoApps.setApplications(apps);
        return tingcoApps;
    }

    public TingcoApplications buildGetApplicationEmailTemplatesDetails(TingcoApplications tingcoApps, List<ApplicationEmailTemplates> applicationEmailTemplatesPojos, List<Country> countrys, List<ApplicationTranslations> applicationTranslationses) {
        int seqId = 1;
        se.info24.appjaxb.Applications apps = new se.info24.appjaxb.Applications();
        for (ApplicationEmailTemplates applicationEmailTemplatesPojo : applicationEmailTemplatesPojos) {
            Application app = new Application();
            se.info24.appjaxb.ApplicationEmailTemplates applicationEmailTemplates = new se.info24.appjaxb.ApplicationEmailTemplates();
            applicationEmailTemplates.setApplicationEmailTemplateID(applicationEmailTemplatesPojo.getApplicationEmailTemplateId());
            for (ApplicationTranslations applicationTranslations : applicationTranslationses) {
                if (applicationTranslations.getId().getApplicationId().equalsIgnoreCase(applicationEmailTemplatesPojo.getApplications().getApplicationId())) {
                    applicationEmailTemplates.setApplicationID(applicationTranslations.getApplicationName());
                }
            }
            for (Country c : countrys) {
                if (c.getCountryId() == applicationEmailTemplatesPojo.getCountryId()) {
                    applicationEmailTemplates.setCountryID(c.getLanguage());
                }
            }
            applicationEmailTemplates.setObjectCode(applicationEmailTemplatesPojo.getObjectCode());
            app.setApplicationEmailTemplates(applicationEmailTemplates);
            apps.getApplication().add(app);
        }

        tingcoApps.setApplications(apps);
        return tingcoApps;
    }
}
