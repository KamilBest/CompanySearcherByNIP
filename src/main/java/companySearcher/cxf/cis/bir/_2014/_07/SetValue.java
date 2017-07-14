
package companySearcher.cxf.cis.bir._2014._07;

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
 *         &lt;element name="pNazwaParametru" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pWartoscParametru" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "pNazwaParametru",
        "pWartoscParametru"
})
@XmlRootElement(name = "SetValue")
public class SetValue {

    @XmlElementRef(name = "pNazwaParametru", namespace = "http://CIS/BIR/2014/07", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pNazwaParametru;
    @XmlElementRef(name = "pWartoscParametru", namespace = "http://CIS/BIR/2014/07", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pWartoscParametru;

    /**
     * Gets the value of the pNazwaParametru property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getPNazwaParametru() {
        return pNazwaParametru;
    }

    /**
     * Sets the value of the pNazwaParametru property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setPNazwaParametru(JAXBElement<String> value) {
        this.pNazwaParametru = value;
    }

    /**
     * Gets the value of the pWartoscParametru property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getPWartoscParametru() {
        return pWartoscParametru;
    }

    /**
     * Sets the value of the pWartoscParametru property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setPWartoscParametru(JAXBElement<String> value) {
        this.pWartoscParametru = value;
    }

}
