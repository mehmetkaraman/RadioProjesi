package deneme.com.radioprojesi.helpers;

import android.util.Log;

/**
 * Created by zer0day on 13.3.2016.
 */
public class Myloger {


    private  static final String TAG="UYGULAMA";


    private static boolean enableLifeCycleLogss=false;
    private static boolean enableLogss=false;


    private static final String METHOD_CALLED="METHOD_CALLED";

    private static final String IN_CLASS="IN CLASS : ";
    private static final String IN_METHOD="IN METHOD :";
    private static final String MESSAGE=", MESSAGE : ";

    public static void init(boolean enableLifeCycleLogs, boolean enableLogs ){

        Log.i(TAG, "MyLoger initialized wtih enableLifeCycleLogs  " + enableLifeCycleLogs + ", enableLogs " + enableLogs);

        enableLifeCycleLogss=enableLifeCycleLogs;
        enableLogss=enableLogs;

    }


    private static String getMethodName(){
        StackTraceElement[] stackTrace=Thread.currentThread().getStackTrace();
        return stackTrace[4].getMethodName();


    }
    private static String getClassName(){

        StackTraceElement[] stackTraceElements=Thread.currentThread().getStackTrace();
        String className=stackTraceElements[4].getClassName();
        int lastIndexofPoint=className.lastIndexOf("");
        className=className.substring(lastIndexofPoint+1,className.length());
        return className;
    }

    public static void logLifeCycle(String className){
        if(enableLogss && enableLifeCycleLogss){
            Log.i(TAG, IN_CLASS + className + IN_METHOD + getMethodName() + MESSAGE + METHOD_CALLED);
        }

    }

    public static void info(String message){
        if(enableLogss){
            Log.i(TAG, IN_CLASS+getClassName()+IN_METHOD+getMethodName()+MESSAGE + message);
        }
    }

    public static void debug(String message){
        if(enableLogss){
            Log.d(TAG, IN_CLASS+getClassName()+IN_METHOD+getMethodName()+MESSAGE + message);
        }
    }

    public static void warn(String message){
        if(enableLogss){
            Log.w(TAG, IN_CLASS+getClassName()+IN_METHOD+getMethodName()+MESSAGE + message);
        }
    }

    public static void error(String message){
        if(enableLogss){
            Log.e(TAG, IN_CLASS+getClassName()+IN_METHOD+getMethodName()+MESSAGE + message);
        }
    }



}
