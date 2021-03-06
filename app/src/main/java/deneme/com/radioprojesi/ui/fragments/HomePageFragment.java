package deneme.com.radioprojesi.ui.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import deneme.com.radioprojesi.HomeActivty;
import deneme.com.radioprojesi.R;
import deneme.com.radioprojesi.radio.ListViewCustomAdapter;
import deneme.com.radioprojesi.radio.MusicAndroidActivity;
import deneme.com.radioprojesi.radio.Radyo;
import deneme.com.radioprojesi.radio.SAXXMLParser;


/**
 * Created by zer0day on 13.3.2016.
 */
public class HomePageFragment extends Fragment{


    private View myFragmentView;
    ListView lview3;
    ListViewCustomAdapter adapter;


    static final String AUDIO_PATH =
            "http://88.191.22.108:19090/listen.pls";
    private MediaPlayer mediaPlayer;
    private int playbackPosition=0;

    private static int icons[] = {R.drawable.istanbulfm, R.drawable.bodrumfm,R.drawable.cilekfm,R.drawable.radyo35,R.drawable.powerturk,
            R.drawable.powerturktaptaze,R.drawable.powersmoothjazz,R.drawable.powerlove,R.drawable.powerturkakustik,R.drawable.acikradyo,R.drawable.dinamofm,
            R.drawable.hitradyo,R.drawable.powerfm,R.drawable.powergreek,R.drawable.poweritaly,R.drawable.powerminimax,R.drawable.powerturkrock,
            R.drawable.powerxl,R.drawable.powersalsa,R.drawable.powerdance};
    ArrayList<String> isimListesi = new ArrayList<String>() ;
    ArrayList<String> urlListesi = new ArrayList<String>() ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        testSaxFonksiyonu() ;
        myFragmentView = inflater.inflate(R.layout.activity_main_two, container, false);
        lview3 = (ListView) myFragmentView.findViewById(R.id.listView4);
        adapter = new ListViewCustomAdapter(getActivity(), isimListesi, urlListesi, icons);
        lview3.setAdapter(adapter);
        lview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {




                Toast.makeText(getActivity(), "Radyo biraz geç açılacak bi on saniye bekleyiniz.", Toast.LENGTH_LONG).show();
                // getting values from selected ListItem
                String name = ((TextView) view.findViewById(R.id.name))
                        .getText().toString();
                String url = ((TextView) view.findViewById(R.id.url))
                        .getText().toString();
                ImageView gelenImage = (ImageView) view.findViewById(R.id.imageView);
                TextView txt = (TextView) getActivity().findViewById(R.id.barText);
                txt.setText("Çalan Radyo:  " + name);
                TextView txt2 = (TextView) getActivity().findViewById(R.id.barText2);
                txt2.setText("Çalan Url:  " + url);
                ImageView imageView = (ImageView) getActivity().findViewById(R.id.barImage);
                imageView.setImageDrawable(gelenImage.getDrawable());

                //Radyo Çal çok zaman aldığı için şimdilik kapatıyorum
                ((HomeActivty) getActivity()).radyoCal();


                Snackbar.make(getView(), "Çalan Radyo:  " + name, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();



            }
        });





        return myFragmentView ;
    }

    public void testSaxFonksiyonu(){
        List<Radyo> radyolar = null ;
        try {
            radyolar = SAXXMLParser.parse(getActivity().getAssets().open("radyo.xml")) ;
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
