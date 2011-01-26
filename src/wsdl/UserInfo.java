
package wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for UserInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="leftSubmissionSlots" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="expires" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emailSecond" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="homepage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserInfo", propOrder = {
    "leftSubmissionSlots",
    "expires",
    "email",
    "emailSecond",
    "homepage"
})
public class UserInfo {

    protected int leftSubmissionSlots;
    @XmlElement(required = true, nillable = true)
    protected XMLGregorianCalendar expires;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true, nillable = true)
    protected String emailSecond;
    @XmlElement(required = true, nillable = true)
    protected String homepage;

    /**
     * Gets the value of the leftSubmissionSlots property.
     * 
     */
    public int getLeftSubmissionSlots() {
        return leftSubmissionSlots;
    }

    /**
     * Sets the value of the leftSubmissionSlots property.
     * 
     */
    public void setLeftSubmissionSlots(int value) {
        this.leftSubmissionSlots = value;
    }

    /**
     * Gets the value of the expires property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpires() {
        return expires;
    }

    /**
     * Sets the value of the expires property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpires(XMLGregorianCalendar value) {
        this.expires = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the emailSecond property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailSecond() {
        return emailSecond;
    }

    /**
     * Sets the value of the emailSecond property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailSecond(String value) {
        this.emailSecond = value;
    }

    /**
     * Gets the value of the homepage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomepage() {
        return homepage;
    }

    /**
     * Sets the value of the homepage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomepage(String value) {
        this.homepage = value;
    }

}
