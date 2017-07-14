
package companySearcher.cxf.cis.bir.publ._2014._07;

import companySearcher.cxf.cis.bir.publ._2014._07.datacontract.ParametryWyszukiwania;

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
 *         &lt;element name="pParametryWyszukiwania" type="{http://CIS/BIR/PUBL/2014/07/DataContract}ParametryWyszukiwania" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "pParametryWyszukiwania"
})
@XmlRootElement(name = "DaneSzukaj")
public class DaneSzukaj {

    @XmlElementRef(name = "pParametryWyszukiwania", namespace = "http://CIS/BIR/PUBL/2014/07", type = JAXBElement.class, required = false)
    protected JAXBElement<ParametryWyszukiwania> pParametryWyszukiwania;

    /**
     * Gets the value of the pParametryWyszukiwania property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link ParametryWyszukiwania }{@code >}
     */
    public JAXBElement<ParametryWyszukiwania> getPParametryWyszukiwania() {
        return pParametryWyszukiwania;
    }

    /**
     * Sets the value of the pParametryWyszukiwania property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link ParametryWyszukiwania }{@code >}
     */
    public void setPParametryWyszukiwania(JAXBElement<ParametryWyszukiwania> value) {
        this.pParametryWyszukiwania = value;
    }

}
