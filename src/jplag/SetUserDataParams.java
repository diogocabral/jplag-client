
package jplag;

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
 *         &lt;element name="userdata" type="{http://www.ipd.uni-karlsruhe.de/jplag/types}UserData"/>
 *         &lt;element name="oldUsername" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "userdata",
    "oldUsername"
})
@XmlRootElement(name = "setUserDataParams")
public class SetUserDataParams {

    @XmlElement(required = true)
    protected UserData userdata;
    @XmlElement(required = true, nillable = true)
    protected String oldUsername;

    /**
     * Gets the value of the userdata property.
     * 
     * @return
     *     possible object is
     *     {@link UserData }
     *     
     */
    public UserData getUserdata() {
        return userdata;
    }

    /**
     * Sets the value of the userdata property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserData }
     *     
     */
    public void setUserdata(UserData value) {
        this.userdata = value;
    }

    /**
     * Gets the value of the oldUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldUsername() {
        return oldUsername;
    }

    /**
     * Sets the value of the oldUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldUsername(String value) {
        this.oldUsername = value;
    }

}
