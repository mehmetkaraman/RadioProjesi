package deneme.com.radioprojesi;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;

import deneme.com.radioprojesi.helpers.Myloger;
import deneme.com.radioprojesi.ui.fragments.HomePageFragment;
import deneme.com.radioprojesi.ui.fragments.MyFavoriFragment;


public class HomeActivty extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Activity activity;
    static MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activty);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Myloger.logLifeCycle(getClass().getSimpleName());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showFirstFragment();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Şimdilik böyle kalmasın radyo Çalıyor ;)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void showFirstFragment(){
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction ft=fragmentManager.beginTransaction();
        HomePageFragment homePageFragment=new HomePageFragment();
        ft.replace(R.id.home_activity_linearlayout_inside_drawer,homePageFragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        int count=getFragmentManager().getBackStackEntryCount();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if(count==0){

            super.onBackPressed();
        }
        else{
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home_activty, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            System.exit(1);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        int id = item.getItemId();

        if (id == R.id.nav_homapage) {
            HomePageFragment homePageFragment=new HomePageFragment();
            ft.replace(R.id.home_activity_linearlayout_inside_drawer,homePageFragment);
            ft.addToBackStack(homePageFragment.getClass().getSimpleName());
            ft.commitAllowingStateLoss();


        } else if (id == R.id.nav_favorits) {
            MyFavoriFragment myFavoriFragment =new MyFavoriFragment();
            ft.replace(R.id.home_activity_linearlayout_inside_drawer, myFavoriFragment);
            ft.addToBackStack(myFavoriFragment.getClass().getSimpleName());
            ft.commitAllowingStateLoss();


        } else if (id == R.id.nav_sleepTime) {

        } else if (id == R.id.nav_contact) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    ////////BEDO////////Burdan sonraki kodları ben ekledim deneme amaçlı
    public void radyoCal(){

        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.stop();

        }

        mPlayer = new MediaPlayer();

        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            //mPlayer.setDataSource(intent.getStringExtra("url"));
            //mPlayer.setDataSource(getApplicationContext(), Uri.parse(url));
            mPlayer.setDataSource("http://streaming.radionomy.com/kekik");
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




}
