
package jplag;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Option complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Option">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="language" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comparisonMode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="minimumMatchLength" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="suffixes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="readSubdirs" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="pathToFiles" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="basecodeDir" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="storeMatches" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="clustertype" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="countryLang" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="originalDir" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Option", propOrder = {
    "language",
    "comparisonMode",
    "minimumMatchLength",
    "suffixes",
    "readSubdirs",
    "pathToFiles",
    "basecodeDir",
    "storeMatches",
    "clustertype",
    "countryLang",
    "title",
    "originalDir"
})
public class Option {

    @XmlElement(required = true)
    protected String language;
    @XmlElement(defaultValue = "0")
    protected Integer comparisonMode;
    protected int minimumMatchLength;
    protected List<String> suffixes;
    protected boolean readSubdirs;
    @XmlElement(required = true, nillable = true)
    protected String pathToFiles;
    @XmlElement(required = true, nillable = true)
    protected String basecodeDir;
    @XmlElement(required = true, nillable = true)
    protected String storeMatches;
    @XmlElement(required = true, nillable = true)
    protected String clustertype;
    @XmlElement(required = true)
    protected String countryLang;
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String originalDir;

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the comparisonMode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getComparisonMode() {
        return comparisonMode;
    }

    /**
     * Sets the value of the comparisonMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setComparisonMode(Integer value) {
        this.comparisonMode = value;
    }

    /**
     * Gets the value of the minimumMatchLength property.
     * 
     */
    public int getMinimumMatchLength() {
        return minimumMatchLength;
    }

    /**
     * Sets the value of the minimumMatchLength property.
     * 
     */
    public void setMinimumMatchLength(int value) {
        this.minimumMatchLength = value;
    }

    /**
     * Gets the value of the suffixes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the suffixes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSuffixes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSuffixes() {
        if (suffixes == null) {
            suffixes = new ArrayList<String>();
        }
        return this.suffixes;
    }

    /**
     * Gets the value of the readSubdirs property.
     * 
     */
    public boolean isReadSubdirs() {
        return readSubdirs;
    }

    /**
     * Sets the value of the readSubdirs property.
     * 
     */
    public void setReadSubdirs(boolean value) {
        this.readSubdirs = value;
    }

    /**
     * Gets the value of the pathToFiles property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPathToFiles() {
        return pathToFiles;
    }

    /**
     * Sets the value of the pathToFiles property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPathToFiles(String value) {
        this.pathToFiles = value;
    }

    /**
     * Gets the value of the basecodeDir property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBasecodeDir() {
        return basecodeDir;
    }

    /**
     * Sets the value of the basecodeDir property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBasecodeDir(String value) {
        this.basecodeDir = value;
    }

    /**
     * Gets the value of the storeMatches property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoreMatches() {
        return storeMatches;
    }

    /**
     * Sets the value of the storeMatches property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoreMatches(String value) {
        this.storeMatches = value;
    }

    /**
     * Gets the value of the clustertype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClustertype() {
        return clustertype;
    }

    /**
     * Sets the value of the clustertype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClustertype(String value) {
        this.clustertype = value;
    }

    /**
     * Gets the value of the countryLang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryLang() {
        return countryLang;
    }

    /**
     * Sets the value of the countryLang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryLang(String value) {
        this.countryLang = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the originalDir property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginalDir() {
        return originalDir;
    }

    /**
     * Sets the value of the originalDir property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginalDir(String value) {
        this.originalDir = value;
    }

}
