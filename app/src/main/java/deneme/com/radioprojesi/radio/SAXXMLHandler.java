package deneme.com.radioprojesi.radio;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

 
public class SAXXMLHandler extends DefaultHandler {
 
    private List<Radyo> radyolar;
    private String tempVal;
    private Radyo tempRadyo;
 
    public SAXXMLHandler() {
        radyolar = new ArrayList<Radyo>();
    }
 
    public List<Radyo> getRadyolar() {
        return radyolar;
    }
 
    // Event Handlers
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        // reset
        tempVal = "";
        if (qName.equalsIgnoreCase("istasyon")) {
            // create a new instance of radyo
        	tempRadyo = new Radyo();
        }
    }
 
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        tempVal = new String(ch, start, length);
    }
 
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (qName.equalsIgnoreCase("istasyon")) {
            // add it to the list
            radyolar.add(tempRadyo);
        } else if (qName.equalsIgnoreCase("id")) {
        	tempRadyo.setId(tempVal);
        } else if (qName.equalsIgnoreCase("isim")) {
        	tempRadyo.setIsim(tempVal);
        } else if (qName.equalsIgnoreCase("url")) {
        	tempRadyo.setUrl(tempVal);
        } else if (qName.equalsIgnoreCase("resim")) {
        	tempRadyo.setResim(tempVal);
       
        }
    }
}