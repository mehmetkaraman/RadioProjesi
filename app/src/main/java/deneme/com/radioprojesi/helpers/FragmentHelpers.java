package deneme.com.radioprojesi.helpers;

import android.app.Activity;
import android.app.Fragment;

/**
 * Created by zer0day on 14.3.2016.
 */
public class FragmentHelpers {




    public static String getActiveFragmentTag(Activity activity){
        if(activity.getFragmentManager().getBackStackEntryCount() ==0){
            return null;
        }else{
            return activity.getFragmentManager().getBackStackEntryAt(activity.getFragmentManager().getBackStackEntryCount()-1).getName();
        }
    }


    public static Fragment getActiveFragment(Activity activity){
        return activity.getFragmentManager().findFragmentByTag(getActiveFragmentTag(activity));
    }


    public static boolean isEqual(Fragment fragment1 , Fragment fragment2){

        if(fragment1==null || fragment2==null){
            return false;
        }
        if(fragment1.getClass().getSimpleName().equals(fragment2.getClass().getSimpleName())){
            return true;
        }
        else {
            return false;
        }
    }
}
