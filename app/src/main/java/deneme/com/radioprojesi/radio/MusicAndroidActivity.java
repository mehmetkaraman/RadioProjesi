package deneme.com.radioprojesi.radio;

import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import deneme.com.radioprojesi.R;

public class MusicAndroidActivity extends Activity {

	static MediaPlayer mPlayer;
	ProgressDialog pd;
	Button buttonPlay;
	Button buttonStop;
	//String url = "http://streaming.radionomy.com/Radio-Mozart?lang=tr-TR%2ctr%3bq%3d0.8%2cen-US%3bq%3d0.6%2cen%3bq%3d0.4"; //test icin
	String url = "http://streaming.radionomy.com/kekik"; //test icin
	//http://streaming.radionomy.com/Eskici?lang=tr-TR%2ctr%3bq%3d0.8%2cen-US%3bq%3d0.6%2cen%3bq%3d0.4, Eskici radyo
	//http://streaming.radionomy.com/kekik?lang=tr-TR%2ctr%3bq%3d0.8%2cen-US%3bq%3d0.6%2cen%3bq%3d0.4  Kekik Radyo
	//"http://streaming.radionomy.com/indiefresh?lang=tr-TR%2ctr%3bq%3d0.8%2cen-US%3bq%3d0.6%2cen%3bq%3d0.4";
	//http://tunein.com/radio/Power-FM-1000-s14259/
	 Intent intent ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		
		  intent = getIntent();
		  
		  TextView text = (TextView) findViewById(R.id.textViewSarki) ;
		  text.setText(intent.getStringExtra("isim"));
		
		buttonPlay = (Button) findViewById(R.id.play);
		buttonPlay.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mPlayer = new MediaPlayer();
				
				mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				try {
					//mPlayer.setDataSource(intent.getStringExtra("url"));
					//mPlayer.setDataSource(getApplicationContext(), Uri.parse(url));
					mPlayer.setDataSource(url);
				} catch (IllegalArgumentException e) {
					Toast.makeText(getApplicationContext(), "Illegal-You might not set the URI correctly!", Toast.LENGTH_LONG).show();
				} catch (SecurityException e) {
					Toast.makeText(getApplicationContext(), "Security-You might not set the URI correctly!", Toast.LENGTH_LONG).show();
				} catch (IllegalStateException e) {
					Toast.makeText(getApplicationContext(), "IllegalState-You might not set the URI correctly!", Toast.LENGTH_LONG).show();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					mPlayer.prepare();
				} catch (IllegalStateException e) {
					Toast.makeText(getApplicationContext(), "prepareIllegal-You might not set the URI correctly!", Toast.LENGTH_LONG).show();
				} catch (IOException e) {
					Toast.makeText(getApplicationContext(), "prepare IO-You might not set the URI correctly!", Toast.LENGTH_LONG).show();
				}
				mPlayer.start();
			}
		});
		
		buttonStop = (Button) findViewById(R.id.stop);
		buttonStop.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mPlayer!=null && mPlayer.isPlaying()){
					mPlayer.stop();
					
				}
			}
		});
	}
	public void onPrepared(MediaPlayer mp) {
	       Log.i("StreamAudioDemo", "prepare finished");
	       pd.setMessage("Playing.....");
	       mp.start();
	  }
	protected void onDestroy() {
		super.onDestroy();
		// TODO Auto-generated method stub
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}

}
