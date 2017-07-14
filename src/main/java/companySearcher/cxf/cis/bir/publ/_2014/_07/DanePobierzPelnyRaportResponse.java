
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
 *         &lt;element name="DanePobierzPelnyRaportResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "danePobierzPelnyRaportResult"
})
@XmlRootElement(name = "DanePobierzPelnyRaportResponse")
public class DanePobierzPelnyRaportResponse {

    @XmlElementRef(name = "DanePobierzPelnyRaportResult", namespace = "http://CIS/BIR/PUBL/2014/07", type = JAXBElement.class, required = false)
    protected JAXBElement<String> danePobierzPelnyRaportResult;

    /**
     * Gets the value of the danePobierzPelnyRaportResult property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getDanePobierzPelnyRaportResult() {
        return danePobierzPelnyRaportResult;
    }

    /**
     * Sets the value of the danePobierzPelnyRaportResult property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setDanePobierzPelnyRaportResult(JAXBElement<String> value) {
        this.danePobierzPelnyRaportResult = value;
    }

}
