package deneme.com.radioprojesi.radio;





import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import deneme.com.radioprojesi.R;

public class MainActivity extends Activity {

	ListView lview3;
    ListViewCustomAdapter adapter;	
	
	private static int icons[] = {R.drawable.super_fm, R.drawable.kral_pop,R.drawable.power_turk1,};
	ArrayList<String> isimListesi = new ArrayList<String>() ;
	ArrayList<String> urlListesi = new ArrayList<String>() ;
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_two);


	

		 testSaxFonksiyonu() ;
			
		lview3 = (ListView) findViewById(R.id.listView4);
        adapter = new ListViewCustomAdapter(this, isimListesi, urlListesi, icons);
        lview3.setAdapter(adapter);
        lview3.setOnItemClickListener(new OnItemClickListener() {
        	
        				public void onItemClick(AdapterView<?> parent, View view,
        						int position, long id) {
        					// getting values from selected ListItem
        					String name = ((TextView) view.findViewById(R.id.name))
        							.getText().toString();
        					String url = ((TextView) view.findViewById(R.id.url))
        							.getText().toString();
        					
        	
        					Intent intent = new Intent(getApplicationContext(), MusicAndroidActivity.class) ;
        					intent.putExtra("isim", name);
        					intent.putExtra("url", url);
        					
        					startActivity(intent) ;
        	
        				}
        			});
 

	}
	
	
	public void testSaxFonksiyonu(){
		List<Radyo> radyolar = null ;
		try {
			radyolar = SAXXMLParser.parse(getAssets().open("radyo.xml")) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(Radyo radyo : radyolar ){
			isimListesi.add(radyo.getIsim()) ;
			urlListesi.add(radyo.getUrl()) ;

		}
	}
	
}