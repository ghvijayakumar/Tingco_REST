//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.23 at 11:03:52 AM IST 
//


package se.info24.appjaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the se.info24.appjaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ApplicationSettingParentID_QNAME = new QName("", "ApplicationSettingParentID");
    private final static QName _Operation_QNAME = new QName("", "Operation");
    private final static QName _MsgTimeStamp_QNAME = new QName("", "MsgTimeStamp");
    private final static QName _ResponseText_QNAME = new QName("", "ResponseText");
    private final static QName _ApplicationVersion_QNAME = new QName("", "ApplicationVersion");
    private final static QName _MsgID_QNAME = new QName("", "MsgID");
    private final static QName _ApplicationSettingID_QNAME = new QName("", "ApplicationSettingID");
    private final static QName _ResponseCode_QNAME = new QName("", "ResponseCode");
    private final static QName _SystemMessageID_QNAME = new QName("", "SystemMessageID");
    private final static QName _ApplicationID_QNAME = new QName("", "ApplicationID");
    private final static QName _ApplicationSettingValue_QNAME = new QName("", "ApplicationSettingValue");
    private final static QName _ActiveFromDate_QNAME = new QName("", "ActiveFromDate");
    private final static QName _ApplicationSettingName_QNAME = new QName("", "ApplicationSettingName");
    private final static QName _UpdatedDate_QNAME = new QName("", "UpdatedDate");
    private final static QName _DeleteAfterThisDate_QNAME = new QName("", "DeleteAfterThisDate");
    private final static QName _ActiveToDate_QNAME = new QName("", "ActiveToDate");
    private final static QName _IsPendingDelete_QNAME = new QName("", "IsPendingDelete");
    private final static QName _CreatedDate_QNAME = new QName("", "CreatedDate");
    private final static QName _SystemMessageText_QNAME = new QName("", "SystemMessageText");
    private final static QName _IsEnabled_QNAME = new QName("", "IsEnabled");
    private final static QName _ApplicationLogoURL_QNAME = new QName("", "ApplicationLogoURL");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: se.info24.appjaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GroupMemberships }
     * 
     */
    public GroupMemberships createGroupMemberships() {
        return new GroupMemberships();
    }

    /**
     * Create an instance of {@link AgreementItems }
     * 
     */
    public AgreementItems createAgreementItems() {
        return new AgreementItems();
    }

    /**
     * Create an instance of {@link ApplicationSolution }
     * 
     */
    public ApplicationSolution createApplicationSolution() {
        return new ApplicationSolution();
    }

    /**
     * Create an instance of {@link Applications }
     * 
     */
    public Applications createApplications() {
        return new Applications();
    }

    /**
     * Create an instance of {@link AgreementTypeTranslations }
     * 
     */
    public AgreementTypeTranslations createAgreementTypeTranslations() {
        return new AgreementTypeTranslations();
    }

    /**
     * Create an instance of {@link MsgStatus }
     * 
     */
    public MsgStatus createMsgStatus() {
        return new MsgStatus();
    }

    /**
     * Create an instance of {@link ApplicationSolutionNames }
     * 
     */
    public ApplicationSolutionNames createApplicationSolutionNames() {
        return new ApplicationSolutionNames();
    }

    /**
     * Create an instance of {@link Agreement }
     * 
     */
    public Agreement createAgreement() {
        return new Agreement();
    }

    /**
     * Create an instance of {@link ApplicationTypeID }
     * 
     */
    public ApplicationTypeID createApplicationTypeID() {
        return new ApplicationTypeID();
    }

    /**
     * Create an instance of {@link ApplicationPackage }
     * 
     */
    public ApplicationPackage createApplicationPackage() {
        return new ApplicationPackage();
    }

    /**
     * Create an instance of {@link ApplicationModules }
     * 
     */
    public ApplicationModules createApplicationModules() {
        return new ApplicationModules();
    }

    /**
     * Create an instance of {@link AgreementItemTypeTranslations }
     * 
     */
    public AgreementItemTypeTranslations createAgreementItemTypeTranslations() {
        return new AgreementItemTypeTranslations();
    }

    /**
     * Create an instance of {@link AgreementRoleTypeTranslations }
     * 
     */
    public AgreementRoleTypeTranslations createAgreementRoleTypeTranslations() {
        return new AgreementRoleTypeTranslations();
    }

    /**
     * Create an instance of {@link ApplicationSolutions }
     * 
     */
    public ApplicationSolutions createApplicationSolutions() {
        return new ApplicationSolutions();
    }

    /**
     * Create an instance of {@link AgreementItemTypeID }
     * 
     */
    public AgreementItemTypeID createAgreementItemTypeID() {
        return new AgreementItemTypeID();
    }

    /**
     * Create an instance of {@link ApplicationDescriptions }
     * 
     */
    public ApplicationDescriptions createApplicationDescriptions() {
        return new ApplicationDescriptions();
    }

    /**
     * Create an instance of {@link ApplicationNames }
     * 
     */
    public ApplicationNames createApplicationNames() {
        return new ApplicationNames();
    }

    /**
     * Create an instance of {@link GroupID }
     * 
     */
    public GroupID createGroupID() {
        return new GroupID();
    }

    /**
     * Create an instance of {@link ApplicationDescription }
     * 
     */
    public ApplicationDescription createApplicationDescription() {
        return new ApplicationDescription();
    }

    /**
     * Create an instance of {@link UserPermissions }
     * 
     */
    public UserPermissions createUserPermissions() {
        return new UserPermissions();
    }

    /**
     * Create an instance of {@link ApplicationName }
     * 
     */
    public ApplicationName createApplicationName() {
        return new ApplicationName();
    }

    /**
     * Create an instance of {@link SystemMessages }
     * 
     */
    public SystemMessages createSystemMessages() {
        return new SystemMessages();
    }

    /**
     * Create an instance of {@link AgreementRolesTypes }
     * 
     */
    public AgreementRolesTypes createAgreementRolesTypes() {
        return new AgreementRolesTypes();
    }

    /**
     * Create an instance of {@link LastUpdatedByUserID }
     * 
     */
    public LastUpdatedByUserID createLastUpdatedByUserID() {
        return new LastUpdatedByUserID();
    }

    /**
     * Create an instance of {@link ApplicationSetting }
     * 
     */
    public ApplicationSetting createApplicationSetting() {
        return new ApplicationSetting();
    }

    /**
     * Create an instance of {@link PackageName }
     * 
     */
    public PackageName createPackageName() {
        return new PackageName();
    }

    /**
     * Create an instance of {@link AgreementRoles }
     * 
     */
    public AgreementRoles createAgreementRoles() {
        return new AgreementRoles();
    }

    /**
     * Create an instance of {@link ApplicationModuleNames }
     * 
     */
    public ApplicationModuleNames createApplicationModuleNames() {
        return new ApplicationModuleNames();
    }

    /**
     * Create an instance of {@link ApplicationPackageNames }
     * 
     */
    public ApplicationPackageNames createApplicationPackageNames() {
        return new ApplicationPackageNames();
    }

    /**
     * Create an instance of {@link ApplicationSettings }
     * 
     */
    public ApplicationSettings createApplicationSettings() {
        return new ApplicationSettings();
    }

    /**
     * Create an instance of {@link ApplicationPackages }
     * 
     */
    public ApplicationPackages createApplicationPackages() {
        return new ApplicationPackages();
    }

    /**
     * Create an instance of {@link Application }
     * 
     */
    public Application createApplication() {
        return new Application();
    }

    /**
     * Create an instance of {@link SolutionName }
     * 
     */
    public SolutionName createSolutionName() {
        return new SolutionName();
    }

    /**
     * Create an instance of {@link SystemMessage }
     * 
     */
    public SystemMessage createSystemMessage() {
        return new SystemMessage();
    }

    /**
     * Create an instance of {@link AgreementStatusTranslations }
     * 
     */
    public AgreementStatusTranslations createAgreementStatusTranslations() {
        return new AgreementStatusTranslations();
    }

    /**
     * Create an instance of {@link ModuleName }
     * 
     */
    public ModuleName createModuleName() {
        return new ModuleName();
    }

    /**
     * Create an instance of {@link AgreementDetails }
     * 
     */
    public AgreementDetails createAgreementDetails() {
        return new AgreementDetails();
    }

    /**
     * Create an instance of {@link TingcoApplications }
     * 
     */
    public TingcoApplications createTingcoApplications() {
        return new TingcoApplications();
    }

    /**
     * Create an instance of {@link AgreementParentID }
     * 
     */
    public AgreementParentID createAgreementParentID() {
        return new AgreementParentID();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ApplicationSettingParentID")
    public JAXBElement<String> createApplicationSettingParentID(String value) {
        return new JAXBElement<String>(_ApplicationSettingParentID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Operation")
    public JAXBElement<String> createOperation(String value) {
        return new JAXBElement<String>(_Operation_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "MsgTimeStamp")
    public JAXBElement<XMLGregorianCalendar> createMsgTimeStamp(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_MsgTimeStamp_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ResponseText")
    public JAXBElement<String> createResponseText(String value) {
        return new JAXBElement<String>(_ResponseText_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ApplicationVersion")
    public JAXBElement<String> createApplicationVersion(String value) {
        return new JAXBElement<String>(_ApplicationVersion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "MsgID")
    public JAXBElement<String> createMsgID(String value) {
        return new JAXBElement<String>(_MsgID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ApplicationSettingID")
    public JAXBElement<String> createApplicationSettingID(String value) {
        return new JAXBElement<String>(_ApplicationSettingID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ResponseCode")
    public JAXBElement<Integer> createResponseCode(Integer value) {
        return new JAXBElement<Integer>(_ResponseCode_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "SystemMessageID")
    public JAXBElement<String> createSystemMessageID(String value) {
        return new JAXBElement<String>(_SystemMessageID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ApplicationID")
    public JAXBElement<String> createApplicationID(String value) {
        return new JAXBElement<String>(_ApplicationID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ApplicationSettingValue")
    public JAXBElement<String> createApplicationSettingValue(String value) {
        return new JAXBElement<String>(_ApplicationSettingValue_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ActiveFromDate")
    public JAXBElement<XMLGregorianCalendar> createActiveFromDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ActiveFromDate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ApplicationSettingName")
    public JAXBElement<String> createApplicationSettingName(String value) {
        return new JAXBElement<String>(_ApplicationSettingName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "UpdatedDate")
    public JAXBElement<XMLGregorianCalendar> createUpdatedDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_UpdatedDate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "DeleteAfterThisDate")
    public JAXBElement<XMLGregorianCalendar> createDeleteAfterThisDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DeleteAfterThisDate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ActiveToDate")
    public JAXBElement<XMLGregorianCalendar> createActiveToDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ActiveToDate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "IsPendingDelete")
    public JAXBElement<Integer> createIsPendingDelete(Integer value) {
        return new JAXBElement<Integer>(_IsPendingDelete_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "CreatedDate")
    public JAXBElement<XMLGregorianCalendar> createCreatedDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_CreatedDate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "SystemMessageText")
    public JAXBElement<String> createSystemMessageText(String value) {
        return new JAXBElement<String>(_SystemMessageText_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "IsEnabled")
    public JAXBElement<Integer> createIsEnabled(Integer value) {
        return new JAXBElement<Integer>(_IsEnabled_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ApplicationLogoURL")
    public JAXBElement<String> createApplicationLogoURL(String value) {
        return new JAXBElement<String>(_ApplicationLogoURL_QNAME, String.class, null, value);
    }

}