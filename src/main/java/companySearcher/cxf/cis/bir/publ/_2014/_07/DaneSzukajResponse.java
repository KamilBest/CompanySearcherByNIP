
package companySearcher.cxf.cis.bir.publ._2014._07;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DaneSzukajResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "daneSzukajResult"
})
@XmlRootElement(name = "DaneSzukajResponse")
public class DaneSzukajResponse {

    @XmlElementRef(name = "DaneSzukajResult", namespace = "http://CIS/BIR/PUBL/2014/07", type = JAXBElement.class, required = false)
    protected JAXBElement<String> daneSzukajResult;

    /**
     * Gets the value of the daneSzukajResult property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getDaneSzukajResult() {
        return daneSzukajResult;
    }

    /**
     * Sets the value of the daneSzukajResult property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setDaneSzukajResult(JAXBElement<String> value) {
        this.daneSzukajResult = value;
    }

}
