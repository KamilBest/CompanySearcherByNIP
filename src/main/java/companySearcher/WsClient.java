package companySearcher;

import companySearcher.cxf.cis.bir.publ._2014._07.IUslugaBIRzewnPubl;
import companySearcher.cxf.cis.bir.publ._2014._07.datacontract.ObjectFactory;
import companySearcher.cxf.cis.bir.publ._2014._07.datacontract.ParametryWyszukiwania;
import companySearcher.cxf.org.tempuri.UslugaBIRzewnPubl;

import javax.xml.bind.JAXBElement;
import javax.xml.ws.soap.AddressingFeature;

/**
 * Created by Kamil Best on 14.07.2017.
 */
public class WsClient {
    public static void main(String[] args) {

        UslugaBIRzewnPubl service = new UslugaBIRzewnPubl();
        service.setHandlerResolver(new SoapHandlerResolver()); //<- doklejanie SID'a do HTTP HEADER
        String statusUslugi = "StatusUslugi";
        IUslugaBIRzewnPubl port = service.getE3(new AddressingFeature());

        String result = port.getValue(statusUslugi);
////// logowanie, jezeli sesja wygasla, badx logowanie pierwszy raz
        if ((SoapMessageHandler.sessionCookie.equals("")) || (!result.equals("1"))) {
            String sid = port.zaloguj("abcde12345abcde12345");
            SoapMessageHandler.sessionCookie = sid;
        }
// przykład wyszukiwania po NIPie

        ObjectFactory objectFactory = new ObjectFactory();
        JAXBElement<String> nipParam = objectFactory.createParametryWyszukiwaniaNip("1234567890");
        ParametryWyszukiwania parametryWyszukiwania = new ParametryWyszukiwania();
        parametryWyszukiwania.setNip(nipParam);
        String raport = port.daneSzukaj(parametryWyszukiwania);
        System.out.println(raport);
    }
}
