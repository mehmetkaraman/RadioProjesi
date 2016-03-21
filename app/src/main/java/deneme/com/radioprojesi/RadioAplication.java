package deneme.com.radioprojesi;

import android.app.Application;

import deneme.com.radioprojesi.Constans.SettingEnables;
import deneme.com.radioprojesi.helpers.Myloger;


/**
 * Created by zer0day on 13.3.2016.
 */
public class RadioAplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        initMyloger(SettingEnables.ENABLE_APPLICATION_LOGS,SettingEnables.ENABLE_APPLICATION_LIFE_CYCLE_LOGS);
    }

    private void initMyloger(boolean enableLogs, boolean enableLifeCycleLogs){
        Myloger.init(enableLifeCycleLogs, enableLogs);
    }

}
