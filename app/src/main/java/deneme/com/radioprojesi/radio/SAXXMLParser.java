package deneme.com.radioprojesi.radio;

import java.io.InputStream;
import java.util.List;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.util.Log;
 
public class SAXXMLParser {
    public static List<Radyo> parse(InputStream is) {
        List<Radyo> radyolar = null;
        try {
            // create a XMLReader from SAXParser
            XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser()
                    .getXMLReader();
            // create a SAXXMLHandler
            SAXXMLHandler saxHandler = new SAXXMLHandler();
            // store handler in XMLReader
            xmlReader.setContentHandler(saxHandler);
            // the process starts
            xmlReader.parse(new InputSource(is));
            // get the `Radyo list`
            radyolar = saxHandler.getRadyolar();
            System.out.println("test durak");
 
        } catch (Exception ex) {
            Log.d("XML", "SAXXMLParser: parse() failed");
        }
 
        // return Radyo list
        return radyolar;
    }
}