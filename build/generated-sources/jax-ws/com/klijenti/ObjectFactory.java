
package com.klijenti;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.klijenti package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Klijent_QNAME = new QName("http://www.klijenti.com", "Klijent");
    private final static QName _VratiKlijente_QNAME = new QName("http://www.klijenti.com", "vratiKlijente");
    private final static QName _VratiKlijenteResponse_QNAME = new QName("http://www.klijenti.com", "vratiKlijenteResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.klijenti
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Klijent }
     * 
     */
    public Klijent createKlijent() {
        return new Klijent();
    }

    /**
     * Create an instance of {@link VratiKlijente }
     * 
     */
    public VratiKlijente createVratiKlijente() {
        return new VratiKlijente();
    }

    /**
     * Create an instance of {@link VratiKlijenteResponse }
     * 
     */
    public VratiKlijenteResponse createVratiKlijenteResponse() {
        return new VratiKlijenteResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Klijent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.klijenti.com", name = "Klijent")
    public JAXBElement<Klijent> createKlijent(Klijent value) {
        return new JAXBElement<Klijent>(_Klijent_QNAME, Klijent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VratiKlijente }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.klijenti.com", name = "vratiKlijente")
    public JAXBElement<VratiKlijente> createVratiKlijente(VratiKlijente value) {
        return new JAXBElement<VratiKlijente>(_VratiKlijente_QNAME, VratiKlijente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VratiKlijenteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.klijenti.com", name = "vratiKlijenteResponse")
    public JAXBElement<VratiKlijenteResponse> createVratiKlijenteResponse(VratiKlijenteResponse value) {
        return new JAXBElement<VratiKlijenteResponse>(_VratiKlijenteResponse_QNAME, VratiKlijenteResponse.class, null, value);
    }

}
