//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.15 at 05:34:39 PM IST 
//


package se.info24.usersjaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the se.info24.usersjaxb package. 
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

    private final static QName _UserMobilePhone_QNAME = new QName("", "UserMobilePhone");
    private final static QName _UserRoleName_QNAME = new QName("", "UserRoleName");
    private final static QName _ResponseText_QNAME = new QName("", "ResponseText");
    private final static QName _LoginName_QNAME = new QName("", "LoginName");
    private final static QName _SystemRole_QNAME = new QName("", "SystemRole");
    private final static QName _NickName_QNAME = new QName("", "NickName");
    private final static QName _ChangePwdRequired_QNAME = new QName("", "ChangePwdRequired");
    private final static QName _DomainID_QNAME = new QName("", "DomainID");
    private final static QName _UserID_QNAME = new QName("", "UserID");
    private final static QName _IsLockedOut_QNAME = new QName("", "IsLockedOut");
    private final static QName _UseDayLightSaving_QNAME = new QName("", "UseDayLightSaving");
    private final static QName _LastName_QNAME = new QName("", "LastName");
    private final static QName _AuthenticationToken_QNAME = new QName("", "AuthenticationToken");
    private final static QName _LastLoginDate_QNAME = new QName("", "LastLoginDate");
    private final static QName _FirstName_QNAME = new QName("", "FirstName");
    private final static QName _PermissionDesc_QNAME = new QName("", "PermissionDesc");
    private final static QName _PermissionName_QNAME = new QName("", "PermissionName");
    private final static QName _UserRoleID_QNAME = new QName("", "UserRoleID");
    private final static QName _MsgTimeStamp_QNAME = new QName("", "MsgTimeStamp");
    private final static QName _TimeZoneID_QNAME = new QName("", "TimeZoneID");
    private final static QName _UserStatusID_QNAME = new QName("", "UserStatusID");
    private final static QName _UserSettingID_QNAME = new QName("", "UserSettingID");
    private final static QName _LockedOutDate_QNAME = new QName("", "LockedOutDate");
    private final static QName _MsgID_QNAME = new QName("", "MsgID");
    private final static QName _UserRoleDesc_QNAME = new QName("", "UserRoleDesc");
    private final static QName _ResponseCode_QNAME = new QName("", "ResponseCode");
    private final static QName _UserEmail_QNAME = new QName("", "UserEmail");
    private final static QName _ApplicationID_QNAME = new QName("", "ApplicationID");
    private final static QName _UserSettingName_QNAME = new QName("", "UserSettingName");
    private final static QName _TimeZoneName_QNAME = new QName("", "TimeZoneName");
    private final static QName _ActiveFromDate_QNAME = new QName("", "ActiveFromDate");
    private final static QName _PermissionID_QNAME = new QName("", "PermissionID");
    private final static QName _UserSettingValue_QNAME = new QName("", "UserSettingValue");
    private final static QName _UpdatedDate_QNAME = new QName("", "UpdatedDate");
    private final static QName _ActiveToDate_QNAME = new QName("", "ActiveToDate");
    private final static QName _CreatedDate_QNAME = new QName("", "CreatedDate");
    private final static QName _UserSmallImageURL_QNAME = new QName("", "UserSmallImageURL");
    private final static QName _UserRoleParentID_QNAME = new QName("", "UserRoleParentID");
    private final static QName _UserStatusText_QNAME = new QName("", "UserStatusText");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: se.info24.usersjaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UserStatuses }
     * 
     */
    public UserStatuses createUserStatuses() {
        return new UserStatuses();
    }

    /**
     * Create an instance of {@link UserSettings }
     * 
     */
    public UserSettings createUserSettings() {
        return new UserSettings();
    }

    /**
     * Create an instance of {@link UserTimeZones2 }
     * 
     */
    public UserTimeZones2 createUserTimeZones2() {
        return new UserTimeZones2();
    }

    /**
     * Create an instance of {@link CountryID }
     * 
     */
    public CountryID createCountryID() {
        return new CountryID();
    }

    /**
     * Create an instance of {@link GroupID }
     * 
     */
    public GroupID createGroupID() {
        return new GroupID();
    }

    /**
     * Create an instance of {@link DeviceID }
     * 
     */
    public DeviceID createDeviceID() {
        return new DeviceID();
    }

    /**
     * Create an instance of {@link Operation }
     * 
     */
    public Operation createOperation() {
        return new Operation();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link UserRole }
     * 
     */
    public UserRole createUserRole() {
        return new UserRole();
    }

    /**
     * Create an instance of {@link Function }
     * 
     */
    public Function createFunction() {
        return new Function();
    }

    /**
     * Create an instance of {@link UserSession }
     * 
     */
    public UserSession createUserSession() {
        return new UserSession();
    }

    /**
     * Create an instance of {@link UserAliasOrder }
     * 
     */
    public UserAliasOrder createUserAliasOrder() {
        return new UserAliasOrder();
    }

    /**
     * Create an instance of {@link MsgStatus }
     * 
     */
    public MsgStatus createMsgStatus() {
        return new MsgStatus();
    }

    /**
     * Create an instance of {@link Country }
     * 
     */
    public Country createCountry() {
        return new Country();
    }

    /**
     * Create an instance of {@link UserTimeZones }
     * 
     */
    public UserTimeZones createUserTimeZones() {
        return new UserTimeZones();
    }

    /**
     * Create an instance of {@link ApplicationModules }
     * 
     */
    public ApplicationModules createApplicationModules() {
        return new ApplicationModules();
    }

    /**
     * Create an instance of {@link UserAliases }
     * 
     */
    public UserAliases createUserAliases() {
        return new UserAliases();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link UserSetting }
     * 
     */
    public UserSetting createUserSetting() {
        return new UserSetting();
    }

    /**
     * Create an instance of {@link UserTimeZone }
     * 
     */
    public UserTimeZone createUserTimeZone() {
        return new UserTimeZone();
    }

    /**
     * Create an instance of {@link UserTypes }
     * 
     */
    public UserTypes createUserTypes() {
        return new UserTypes();
    }

    /**
     * Create an instance of {@link UserTypeID }
     * 
     */
    public UserTypeID createUserTypeID() {
        return new UserTypeID();
    }

    /**
     * Create an instance of {@link UserAliasID }
     * 
     */
    public UserAliasID createUserAliasID() {
        return new UserAliasID();
    }

    /**
     * Create an instance of {@link AddressTypeTranslations }
     * 
     */
    public AddressTypeTranslations createAddressTypeTranslations() {
        return new AddressTypeTranslations();
    }

    /**
     * Create an instance of {@link UserAliasOrders }
     * 
     */
    public UserAliasOrders createUserAliasOrders() {
        return new UserAliasOrders();
    }

    /**
     * Create an instance of {@link Permission }
     * 
     */
    public Permission createPermission() {
        return new Permission();
    }

    /**
     * Create an instance of {@link FunctionAreas }
     * 
     */
    public FunctionAreas createFunctionAreas() {
        return new FunctionAreas();
    }

    /**
     * Create an instance of {@link ObjectTags }
     * 
     */
    public ObjectTags createObjectTags() {
        return new ObjectTags();
    }

    /**
     * Create an instance of {@link FraudLogs }
     * 
     */
    public FraudLogs createFraudLogs() {
        return new FraudLogs();
    }

    /**
     * Create an instance of {@link ApplicationModule }
     * 
     */
    public ApplicationModule createApplicationModule() {
        return new ApplicationModule();
    }

    /**
     * Create an instance of {@link UserStatus }
     * 
     */
    public UserStatus createUserStatus() {
        return new UserStatus();
    }

    /**
     * Create an instance of {@link UserRoles }
     * 
     */
    public UserRoles createUserRoles() {
        return new UserRoles();
    }

    /**
     * Create an instance of {@link ObjectGroupTranslations }
     * 
     */
    public ObjectGroupTranslations createObjectGroupTranslations() {
        return new ObjectGroupTranslations();
    }

    /**
     * Create an instance of {@link UserLog }
     * 
     */
    public UserLog createUserLog() {
        return new UserLog();
    }

    /**
     * Create an instance of {@link Currency }
     * 
     */
    public Currency createCurrency() {
        return new Currency();
    }

    /**
     * Create an instance of {@link Operations }
     * 
     */
    public Operations createOperations() {
        return new Operations();
    }

    /**
     * Create an instance of {@link Addresses }
     * 
     */
    public Addresses createAddresses() {
        return new Addresses();
    }

    /**
     * Create an instance of {@link UserAliasDetails }
     * 
     */
    public UserAliasDetails createUserAliasDetails() {
        return new UserAliasDetails();
    }

    /**
     * Create an instance of {@link Permissions }
     * 
     */
    public Permissions createPermissions() {
        return new Permissions();
    }

    /**
     * Create an instance of {@link Users }
     * 
     */
    public Users createUsers() {
        return new Users();
    }

    /**
     * Create an instance of {@link FraudLog }
     * 
     */
    public FraudLog createFraudLog() {
        return new FraudLog();
    }

    /**
     * Create an instance of {@link OGMUserAlias }
     * 
     */
    public OGMUserAlias createOGMUserAlias() {
        return new OGMUserAlias();
    }

    /**
     * Create an instance of {@link AddressType }
     * 
     */
    public AddressType createAddressType() {
        return new AddressType();
    }

    /**
     * Create an instance of {@link UserGroupMemberships }
     * 
     */
    public UserGroupMemberships createUserGroupMemberships() {
        return new UserGroupMemberships();
    }

    /**
     * Create an instance of {@link LastUpdatedByUserID }
     * 
     */
    public LastUpdatedByUserID createLastUpdatedByUserID() {
        return new LastUpdatedByUserID();
    }

    /**
     * Create an instance of {@link EventDatas }
     * 
     */
    public EventDatas createEventDatas() {
        return new EventDatas();
    }

    /**
     * Create an instance of {@link TingcoUsers }
     * 
     */
    public TingcoUsers createTingcoUsers() {
        return new TingcoUsers();
    }

    /**
     * Create an instance of {@link BlackList }
     * 
     */
    public BlackList createBlackList() {
        return new BlackList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "UserMobilePhone")
    public JAXBElement<String> createUserMobilePhone(String value) {
        return new JAXBElement<String>(_UserMobilePhone_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "UserRoleName")
    public JAXBElement<String> createUserRoleName(String value) {
        return new JAXBElement<String>(_UserRoleName_QNAME, String.class, null, value);
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
    @XmlElementDecl(namespace = "", name = "LoginName")
    public JAXBElement<String> createLoginName(String value) {
        return new JAXBElement<String>(_LoginName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "SystemRole")
    public JAXBElement<Integer> createSystemRole(Integer value) {
        return new JAXBElement<Integer>(_SystemRole_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "NickName")
    public JAXBElement<String> createNickName(String value) {
        return new JAXBElement<String>(_NickName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ChangePwdRequired")
    public JAXBElement<Integer> createChangePwdRequired(Integer value) {
        return new JAXBElement<Integer>(_ChangePwdRequired_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "DomainID")
    public JAXBElement<String> createDomainID(String value) {
        return new JAXBElement<String>(_DomainID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "UserID")
    public JAXBElement<String> createUserID(String value) {
        return new JAXBElement<String>(_UserID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "IsLockedOut")
    public JAXBElement<Integer> createIsLockedOut(Integer value) {
        return new JAXBElement<Integer>(_IsLockedOut_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "UseDayLightSaving")
    public JAXBElement<Integer> createUseDayLightSaving(Integer value) {
        return new JAXBElement<Integer>(_UseDayLightSaving_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "LastName")
    public JAXBElement<String> createLastName(String value) {
        return new JAXBElement<String>(_LastName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "AuthenticationToken")
    public JAXBElement<String> createAuthenticationToken(String value) {
        return new JAXBElement<String>(_AuthenticationToken_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "LastLoginDate")
    public JAXBElement<XMLGregorianCalendar> createLastLoginDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_LastLoginDate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "FirstName")
    public JAXBElement<String> createFirstName(String value) {
        return new JAXBElement<String>(_FirstName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "PermissionDesc")
    public JAXBElement<String> createPermissionDesc(String value) {
        return new JAXBElement<String>(_PermissionDesc_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "PermissionName")
    public JAXBElement<String> createPermissionName(String value) {
        return new JAXBElement<String>(_PermissionName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "UserRoleID")
    public JAXBElement<String> createUserRoleID(String value) {
        return new JAXBElement<String>(_UserRoleID_QNAME, String.class, null, value);
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
    @XmlElementDecl(namespace = "", name = "TimeZoneID")
    public JAXBElement<String> createTimeZoneID(String value) {
        return new JAXBElement<String>(_TimeZoneID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "UserStatusID")
    public JAXBElement<String> createUserStatusID(String value) {
        return new JAXBElement<String>(_UserStatusID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "UserSettingID")
    public JAXBElement<String> createUserSettingID(String value) {
        return new JAXBElement<String>(_UserSettingID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "LockedOutDate")
    public JAXBElement<XMLGregorianCalendar> createLockedOutDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_LockedOutDate_QNAME, XMLGregorianCalendar.class, null, value);
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
    @XmlElementDecl(namespace = "", name = "UserRoleDesc")
    public JAXBElement<String> createUserRoleDesc(String value) {
        return new JAXBElement<String>(_UserRoleDesc_QNAME, String.class, null, value);
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
    @XmlElementDecl(namespace = "", name = "UserEmail")
    public JAXBElement<String> createUserEmail(String value) {
        return new JAXBElement<String>(_UserEmail_QNAME, String.class, null, value);
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
    @XmlElementDecl(namespace = "", name = "UserSettingName")
    public JAXBElement<String> createUserSettingName(String value) {
        return new JAXBElement<String>(_UserSettingName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "TimeZoneName")
    public JAXBElement<String> createTimeZoneName(String value) {
        return new JAXBElement<String>(_TimeZoneName_QNAME, String.class, null, value);
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
    @XmlElementDecl(namespace = "", name = "PermissionID")
    public JAXBElement<String> createPermissionID(String value) {
        return new JAXBElement<String>(_PermissionID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "UserSettingValue")
    public JAXBElement<String> createUserSettingValue(String value) {
        return new JAXBElement<String>(_UserSettingValue_QNAME, String.class, null, value);
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
    @XmlElementDecl(namespace = "", name = "ActiveToDate")
    public JAXBElement<XMLGregorianCalendar> createActiveToDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ActiveToDate_QNAME, XMLGregorianCalendar.class, null, value);
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
    @XmlElementDecl(namespace = "", name = "UserSmallImageURL")
    public JAXBElement<String> createUserSmallImageURL(String value) {
        return new JAXBElement<String>(_UserSmallImageURL_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "UserRoleParentID")
    public JAXBElement<String> createUserRoleParentID(String value) {
        return new JAXBElement<String>(_UserRoleParentID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "UserStatusText")
    public JAXBElement<String> createUserStatusText(String value) {
        return new JAXBElement<String>(_UserStatusText_QNAME, String.class, null, value);
    }

}
