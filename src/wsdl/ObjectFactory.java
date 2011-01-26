
package wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the wsdl package.
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

    private final static QName _ContinueResultDownloadDummyInt_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "continueResultDownloadDummyInt");
    private final static QName _GetStatusRequestString_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "getStatusRequestString");
    private final static QName _ContinueSubmissionUploadDummyInt_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "continueSubmissionUploadDummyInt");
    private final static QName _CompareSourceResponseString_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "compareSourceResponseString");
    private final static QName _StartSubmissionUploadResponseString_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "startSubmissionUploadResponseString");
    private final static QName _GetAccountRequestsLenOnlyBoolean_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "getAccountRequestsLenOnlyBoolean");
    private final static QName _RequestAccountBoolean_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "requestAccountBoolean");
    private final static QName _FinishAccountRequestDummyInt_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "finishAccountRequestDummyInt");
    private final static QName _NotifyDevelopersDummyInt_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "notifyDevelopersDummyInt");
    private final static QName _SetMailTemplateDummyInt_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "setMailTemplateDummyInt");
    private final static QName _ERequestData_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "eRequestData");
    private final static QName _GetMailTemplatesInt_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "getMailTemplatesInt");
    private final static QName _GetResultRequestString_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "getResultRequestString");
    private final static QName _ContinueSubmissionUploadData_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "continueSubmissionUploadData");
    private final static QName _StartResultDownloadRequestString_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "startResultDownloadRequestString");
    private final static QName _UpdateUserInfoDummyInt_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "updateUserInfoDummyInt");
    private final static QName _CancelSubmissionDummyInt_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "cancelSubmissionDummyInt");
    private final static QName _SetUserDataUsernameN_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "setUserDataUsernameN");
    private final static QName _CancelSubmissionRequestString_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "cancelSubmissionRequestString");
    private final static QName _EOption_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "eOption");
    private final static QName _GetUserDataArrayDummyInt_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "getUserDataArrayDummyInt");
    private final static QName _SetUserDataStringN_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "setUserDataStringN");
    private final static QName _ExtendAccountString_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "extendAccountString");
    private final static QName _ContinueResultDownloadData_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "continueResultDownloadData");
    private final static QName _SetDeveloperStateDummyInt_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "setDeveloperStateDummyInt");
    private final static QName _ExtendAccountDummyInt_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "extendAccountDummyInt");
    private final static QName _SetUserDataDummyInt_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "setUserDataDummyInt");
    private final static QName _EUserData_QNAME = new QName("http://www.ipd.uni-karlsruhe.de/jplag/types", "eUserData");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MailTemplateArray }
     * 
     */
    public MailTemplateArray createMailTemplateArray() {
        return new MailTemplateArray();
    }

    /**
     * Create an instance of {@link Option }
     * 
     */
    public Option createOption() {
        return new Option();
    }

    /**
     * Create an instance of {@link UserData }
     * 
     */
    public UserData createUserData() {
        return new UserData();
    }

    /**
     * Create an instance of {@link MailTemplate }
     * 
     */
    public MailTemplate createMailTemplate() {
        return new MailTemplate();
    }

    /**
     * Create an instance of {@link ServerInfo }
     * 
     */
    public ServerInfo createServerInfo() {
        return new ServerInfo();
    }

    /**
     * Create an instance of {@link Submission }
     * 
     */
    public Submission createSubmission() {
        return new Submission();
    }

    /**
     * Create an instance of {@link NotifyDevelopersParams }
     * 
     */
    public NotifyDevelopersParams createNotifyDevelopersParams() {
        return new NotifyDevelopersParams();
    }

    /**
     * Create an instance of {@link Access }
     * 
     */
    public Access createAccess() {
        return new Access();
    }

    /**
     * Create an instance of {@link StartSubmissionParams }
     * 
     */
    public StartSubmissionParams createStartSubmissionParams() {
        return new StartSubmissionParams();
    }

    /**
     * Create an instance of {@link SetDeveloperStateParams }
     * 
     */
    public SetDeveloperStateParams createSetDeveloperStateParams() {
        return new SetDeveloperStateParams();
    }

    /**
     * Create an instance of {@link JPlagException }
     * 
     */
    public JPlagException createJPlagException() {
        return new JPlagException();
    }

    /**
     * Create an instance of {@link UpdateUserInfoParams }
     * 
     */
    public UpdateUserInfoParams createUpdateUserInfoParams() {
        return new UpdateUserInfoParams();
    }

    /**
     * Create an instance of {@link RequestDataArray }
     * 
     */
    public RequestDataArray createRequestDataArray() {
        return new RequestDataArray();
    }

    /**
     * Create an instance of {@link UserInfo }
     * 
     */
    public UserInfo createUserInfo() {
        return new UserInfo();
    }

    /**
     * Create an instance of {@link LanguageInfo }
     * 
     */
    public LanguageInfo createLanguageInfo() {
        return new LanguageInfo();
    }

    /**
     * Create an instance of {@link SetUserDataParams }
     * 
     */
    public SetUserDataParams createSetUserDataParams() {
        return new SetUserDataParams();
    }

    /**
     * Create an instance of {@link RequestData }
     * 
     */
    public RequestData createRequestData() {
        return new RequestData();
    }

    /**
     * Create an instance of {@link StartSubmissionUploadParams }
     * 
     */
    public StartSubmissionUploadParams createStartSubmissionUploadParams() {
        return new StartSubmissionUploadParams();
    }

    /**
     * Create an instance of {@link UserDataArray }
     * 
     */
    public UserDataArray createUserDataArray() {
        return new UserDataArray();
    }

    /**
     * Create an instance of {@link StartResultDownloadData }
     * 
     */
    public StartResultDownloadData createStartResultDownloadData() {
        return new StartResultDownloadData();
    }

    /**
     * Create an instance of {@link Status }
     * 
     */
    public Status createStatus() {
        return new Status();
    }

    /**
     * Create an instance of {@link SetMailTemplateParams }
     * 
     */
    public SetMailTemplateParams createSetMailTemplateParams() {
        return new SetMailTemplateParams();
    }

    /**
     * Create an instance of {@link FinishRequestData }
     * 
     */
    public FinishRequestData createFinishRequestData() {
        return new FinishRequestData();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "continueResultDownloadDummyInt")
    public JAXBElement<Integer> createContinueResultDownloadDummyInt(Integer value) {
        return new JAXBElement<Integer>(_ContinueResultDownloadDummyInt_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "getStatusRequestString")
    public JAXBElement<String> createGetStatusRequestString(String value) {
        return new JAXBElement<String>(_GetStatusRequestString_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "continueSubmissionUploadDummyInt")
    public JAXBElement<Integer> createContinueSubmissionUploadDummyInt(Integer value) {
        return new JAXBElement<Integer>(_ContinueSubmissionUploadDummyInt_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "compareSourceResponseString")
    public JAXBElement<String> createCompareSourceResponseString(String value) {
        return new JAXBElement<String>(_CompareSourceResponseString_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "startSubmissionUploadResponseString")
    public JAXBElement<String> createStartSubmissionUploadResponseString(String value) {
        return new JAXBElement<String>(_StartSubmissionUploadResponseString_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "getAccountRequestsLenOnlyBoolean")
    public JAXBElement<Boolean> createGetAccountRequestsLenOnlyBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_GetAccountRequestsLenOnlyBoolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "requestAccountBoolean")
    public JAXBElement<Boolean> createRequestAccountBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_RequestAccountBoolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "finishAccountRequestDummyInt")
    public JAXBElement<Integer> createFinishAccountRequestDummyInt(Integer value) {
        return new JAXBElement<Integer>(_FinishAccountRequestDummyInt_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "notifyDevelopersDummyInt")
    public JAXBElement<Integer> createNotifyDevelopersDummyInt(Integer value) {
        return new JAXBElement<Integer>(_NotifyDevelopersDummyInt_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "setMailTemplateDummyInt")
    public JAXBElement<Integer> createSetMailTemplateDummyInt(Integer value) {
        return new JAXBElement<Integer>(_SetMailTemplateDummyInt_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "eRequestData")
    public JAXBElement<RequestData> createERequestData(RequestData value) {
        return new JAXBElement<RequestData>(_ERequestData_QNAME, RequestData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "getMailTemplatesInt")
    public JAXBElement<Integer> createGetMailTemplatesInt(Integer value) {
        return new JAXBElement<Integer>(_GetMailTemplatesInt_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "getResultRequestString")
    public JAXBElement<String> createGetResultRequestString(String value) {
        return new JAXBElement<String>(_GetResultRequestString_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "continueSubmissionUploadData")
    public JAXBElement<byte[]> createContinueSubmissionUploadData(byte[] value) {
        return new JAXBElement<byte[]>(_ContinueSubmissionUploadData_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "startResultDownloadRequestString")
    public JAXBElement<String> createStartResultDownloadRequestString(String value) {
        return new JAXBElement<String>(_StartResultDownloadRequestString_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "updateUserInfoDummyInt")
    public JAXBElement<Integer> createUpdateUserInfoDummyInt(Integer value) {
        return new JAXBElement<Integer>(_UpdateUserInfoDummyInt_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "cancelSubmissionDummyInt")
    public JAXBElement<Integer> createCancelSubmissionDummyInt(Integer value) {
        return new JAXBElement<Integer>(_CancelSubmissionDummyInt_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "setUserDataUsernameN")
    public JAXBElement<String> createSetUserDataUsernameN(String value) {
        return new JAXBElement<String>(_SetUserDataUsernameN_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "cancelSubmissionRequestString")
    public JAXBElement<String> createCancelSubmissionRequestString(String value) {
        return new JAXBElement<String>(_CancelSubmissionRequestString_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Option }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "eOption")
    public JAXBElement<Option> createEOption(Option value) {
        return new JAXBElement<Option>(_EOption_QNAME, Option.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "getUserDataArrayDummyInt")
    public JAXBElement<Integer> createGetUserDataArrayDummyInt(Integer value) {
        return new JAXBElement<Integer>(_GetUserDataArrayDummyInt_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "setUserDataStringN")
    public JAXBElement<String> createSetUserDataStringN(String value) {
        return new JAXBElement<String>(_SetUserDataStringN_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "extendAccountString")
    public JAXBElement<String> createExtendAccountString(String value) {
        return new JAXBElement<String>(_ExtendAccountString_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "continueResultDownloadData")
    public JAXBElement<byte[]> createContinueResultDownloadData(byte[] value) {
        return new JAXBElement<byte[]>(_ContinueResultDownloadData_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "setDeveloperStateDummyInt")
    public JAXBElement<Integer> createSetDeveloperStateDummyInt(Integer value) {
        return new JAXBElement<Integer>(_SetDeveloperStateDummyInt_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "extendAccountDummyInt")
    public JAXBElement<Integer> createExtendAccountDummyInt(Integer value) {
        return new JAXBElement<Integer>(_ExtendAccountDummyInt_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "setUserDataDummyInt")
    public JAXBElement<Integer> createSetUserDataDummyInt(Integer value) {
        return new JAXBElement<Integer>(_SetUserDataDummyInt_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ipd.uni-karlsruhe.de/jplag/types", name = "eUserData")
    public JAXBElement<UserData> createEUserData(UserData value) {
        return new JAXBElement<UserData>(_EUserData_QNAME, UserData.class, null, value);
    }

}
