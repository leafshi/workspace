
package com.digiwin.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.digiwin.webservice package. 
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

    private final static QName _RegisterUserResponse_QNAME = new QName("http://webservice.digiwin.com/", "registerUserResponse");
    private final static QName _DeactivateUser_QNAME = new QName("http://webservice.digiwin.com/", "deactivateUser");
    private final static QName _ModifyUser_QNAME = new QName("http://webservice.digiwin.com/", "modifyUser");
    private final static QName _RegisterUser_QNAME = new QName("http://webservice.digiwin.com/", "registerUser");
    private final static QName _ValidateUserLogin_QNAME = new QName("http://webservice.digiwin.com/", "validateUserLogin");
    private final static QName _ActivateUser_QNAME = new QName("http://webservice.digiwin.com/", "activateUser");
    private final static QName _ModifyUserResponse_QNAME = new QName("http://webservice.digiwin.com/", "modifyUserResponse");
    private final static QName _ActivateUserResponse_QNAME = new QName("http://webservice.digiwin.com/", "activateUserResponse");
    private final static QName _DeactivateUserResponse_QNAME = new QName("http://webservice.digiwin.com/", "deactivateUserResponse");
    private final static QName _ValidateUserLoginResponse_QNAME = new QName("http://webservice.digiwin.com/", "validateUserLoginResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.digiwin.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ValidateUserLoginResponse }
     * 
     */
    public ValidateUserLoginResponse createValidateUserLoginResponse() {
        return new ValidateUserLoginResponse();
    }

    /**
     * Create an instance of {@link DeactivateUserResponse }
     * 
     */
    public DeactivateUserResponse createDeactivateUserResponse() {
        return new DeactivateUserResponse();
    }

    /**
     * Create an instance of {@link ActivateUserResponse }
     * 
     */
    public ActivateUserResponse createActivateUserResponse() {
        return new ActivateUserResponse();
    }

    /**
     * Create an instance of {@link ModifyUserResponse }
     * 
     */
    public ModifyUserResponse createModifyUserResponse() {
        return new ModifyUserResponse();
    }

    /**
     * Create an instance of {@link ActivateUser }
     * 
     */
    public ActivateUser createActivateUser() {
        return new ActivateUser();
    }

    /**
     * Create an instance of {@link ValidateUserLogin }
     * 
     */
    public ValidateUserLogin createValidateUserLogin() {
        return new ValidateUserLogin();
    }

    /**
     * Create an instance of {@link RegisterUser }
     * 
     */
    public RegisterUser createRegisterUser() {
        return new RegisterUser();
    }

    /**
     * Create an instance of {@link ModifyUser }
     * 
     */
    public ModifyUser createModifyUser() {
        return new ModifyUser();
    }

    /**
     * Create an instance of {@link DeactivateUser }
     * 
     */
    public DeactivateUser createDeactivateUser() {
        return new DeactivateUser();
    }

    /**
     * Create an instance of {@link RegisterUserResponse }
     * 
     */
    public RegisterUserResponse createRegisterUserResponse() {
        return new RegisterUserResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.digiwin.com/", name = "registerUserResponse")
    public JAXBElement<RegisterUserResponse> createRegisterUserResponse(RegisterUserResponse value) {
        return new JAXBElement<RegisterUserResponse>(_RegisterUserResponse_QNAME, RegisterUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeactivateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.digiwin.com/", name = "deactivateUser")
    public JAXBElement<DeactivateUser> createDeactivateUser(DeactivateUser value) {
        return new JAXBElement<DeactivateUser>(_DeactivateUser_QNAME, DeactivateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.digiwin.com/", name = "modifyUser")
    public JAXBElement<ModifyUser> createModifyUser(ModifyUser value) {
        return new JAXBElement<ModifyUser>(_ModifyUser_QNAME, ModifyUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.digiwin.com/", name = "registerUser")
    public JAXBElement<RegisterUser> createRegisterUser(RegisterUser value) {
        return new JAXBElement<RegisterUser>(_RegisterUser_QNAME, RegisterUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateUserLogin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.digiwin.com/", name = "validateUserLogin")
    public JAXBElement<ValidateUserLogin> createValidateUserLogin(ValidateUserLogin value) {
        return new JAXBElement<ValidateUserLogin>(_ValidateUserLogin_QNAME, ValidateUserLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActivateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.digiwin.com/", name = "activateUser")
    public JAXBElement<ActivateUser> createActivateUser(ActivateUser value) {
        return new JAXBElement<ActivateUser>(_ActivateUser_QNAME, ActivateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.digiwin.com/", name = "modifyUserResponse")
    public JAXBElement<ModifyUserResponse> createModifyUserResponse(ModifyUserResponse value) {
        return new JAXBElement<ModifyUserResponse>(_ModifyUserResponse_QNAME, ModifyUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActivateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.digiwin.com/", name = "activateUserResponse")
    public JAXBElement<ActivateUserResponse> createActivateUserResponse(ActivateUserResponse value) {
        return new JAXBElement<ActivateUserResponse>(_ActivateUserResponse_QNAME, ActivateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeactivateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.digiwin.com/", name = "deactivateUserResponse")
    public JAXBElement<DeactivateUserResponse> createDeactivateUserResponse(DeactivateUserResponse value) {
        return new JAXBElement<DeactivateUserResponse>(_DeactivateUserResponse_QNAME, DeactivateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateUserLoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.digiwin.com/", name = "validateUserLoginResponse")
    public JAXBElement<ValidateUserLoginResponse> createValidateUserLoginResponse(ValidateUserLoginResponse value) {
        return new JAXBElement<ValidateUserLoginResponse>(_ValidateUserLoginResponse_QNAME, ValidateUserLoginResponse.class, null, value);
    }

}
