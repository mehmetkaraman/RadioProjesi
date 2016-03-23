package deneme.com.radioprojesi;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

public class Test extends Activity
{
    static final String AUDIO_PATH =
      "http://icecast2.rte.ie/radio1";
    private MediaPlayer mediaPlayer;
    private int playbackPosition=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        try {
            playAudio(AUDIO_PATH);
            //playLocalAudio();
            //playLocalAudio_UsingDescriptor();
        } catch (Exception e) {
            e.printStackTrace();

        }


    }


    private void playAudio(String url) throws Exception
    {
        killMediaPlayer();

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(url);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    private void playLocalAudio() throws Exception
    {
        mediaPlayer = MediaPlayer.create(this, R.raw.test);
        mediaPlayer.start();
    }

    private void playLocalAudio_UsingDescriptor() throws Exception {

        AssetFileDescriptor fileDesc = getResources().openRawResourceFd(
            R.raw.test);
        if (fileDesc != null) {

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(fileDesc.getFileDescriptor(), fileDesc
                    .getStartOffset(), fileDesc.getLength());

            fileDesc.close();

            mediaPlayer.prepare();
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }

    private void killMediaPlayer() {
        if(mediaPlayer!=null) {
            try {
                mediaPlayer.release();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}