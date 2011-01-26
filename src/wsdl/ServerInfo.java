
package wsdl;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="userInfo" type="{http://www.ipd.uni-karlsruhe.de/wsdl/types}UserInfo"/>
 *         &lt;element name="languageInfos" type="{http://www.ipd.uni-karlsruhe.de/wsdl/types}LanguageInfo" maxOccurs="unbounded"/>
 *         &lt;element name="countryLanguages" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="submissions" type="{http://www.ipd.uni-karlsruhe.de/wsdl/types}Submission" maxOccurs="unbounded" minOccurs="0"/>
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
    "userInfo",
    "languageInfos",
    "countryLanguages",
    "submissions"
})
@XmlRootElement(name = "ServerInfo")
public class ServerInfo {

    @XmlElement(required = true)
    protected UserInfo userInfo;
    @XmlElement(required = true)
    protected List<LanguageInfo> languageInfos;
    @XmlElement(required = true)
    protected List<String> countryLanguages;
    protected List<Submission> submissions;

    /**
     * Gets the value of the userInfo property.
     * 
     * @return
     *     possible object is
     *     {@link UserInfo }
     *     
     */
    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * Sets the value of the userInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserInfo }
     *     
     */
    public void setUserInfo(UserInfo value) {
        this.userInfo = value;
    }

    /**
     * Gets the value of the languageInfos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the languageInfos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLanguageInfos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LanguageInfo }
     * 
     * 
     */
    public List<LanguageInfo> getLanguageInfos() {
        if (languageInfos == null) {
            languageInfos = new ArrayList<LanguageInfo>();
        }
        return this.languageInfos;
    }

    /**
     * Gets the value of the countryLanguages property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the countryLanguages property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCountryLanguages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCountryLanguages() {
        if (countryLanguages == null) {
            countryLanguages = new ArrayList<String>();
        }
        return this.countryLanguages;
    }

    /**
     * Gets the value of the submissions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the submissions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubmissions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Submission }
     * 
     * 
     */
    public List<Submission> getSubmissions() {
        if (submissions == null) {
            submissions = new ArrayList<Submission>();
        }
        return this.submissions;
    }

}
