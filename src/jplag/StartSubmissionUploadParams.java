
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
 *         &lt;element name="submissionParams" type="{http://www.ipd.uni-karlsruhe.de/jplag/types}Option"/>
 *         &lt;element name="filesize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
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
    "submissionParams",
    "filesize",
    "data"
})
@XmlRootElement(name = "startSubmissionUploadParams")
public class StartSubmissionUploadParams {

    @XmlElement(required = true)
    protected Option submissionParams;
    protected int filesize;
    @XmlElement(required = true)
    protected byte[] data;

    /**
     * Gets the value of the submissionParams property.
     * 
     * @return
     *     possible object is
     *     {@link Option }
     *     
     */
    public Option getSubmissionParams() {
        return submissionParams;
    }

    /**
     * Sets the value of the submissionParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link Option }
     *     
     */
    public void setSubmissionParams(Option value) {
        this.submissionParams = value;
    }

    /**
     * Gets the value of the filesize property.
     * 
     */
    public int getFilesize() {
        return filesize;
    }

    /**
     * Sets the value of the filesize property.
     * 
     */
    public void setFilesize(int value) {
        this.filesize = value;
    }

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setData(byte[] value) {
        this.data = ((byte[]) value);
    }

}
