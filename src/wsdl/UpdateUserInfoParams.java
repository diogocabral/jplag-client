
package wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="newPassword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="newEmailSecond" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="newHomepage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "newPassword",
    "newEmailSecond",
    "newHomepage"
})
@XmlRootElement(name = "updateUserInfoParams")
public class UpdateUserInfoParams {

    @XmlElement(required = true, nillable = true)
    protected String newPassword;
    @XmlElement(required = true, nillable = true)
    protected String newEmailSecond;
    @XmlElement(required = true, nillable = true)
    protected String newHomepage;

    /**
     * Gets the value of the newPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Sets the value of the newPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewPassword(String value) {
        this.newPassword = value;
    }

    /**
     * Gets the value of the newEmailSecond property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewEmailSecond() {
        return newEmailSecond;
    }

    /**
     * Sets the value of the newEmailSecond property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewEmailSecond(String value) {
        this.newEmailSecond = value;
    }

    /**
     * Gets the value of the newHomepage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewHomepage() {
        return newHomepage;
    }

    /**
     * Sets the value of the newHomepage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewHomepage(String value) {
        this.newHomepage = value;
    }

}
