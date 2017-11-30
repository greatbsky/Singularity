package xyz.xysc.core.global;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import xyz.xysc.core.api.APIFactory;

/**
 * @author architect.bian
 * @date 2017-11-25 7:15 PM
 */

public class Global {

    public static Context appContext = null;
    public static Store store;

    protected static final String dbName = "global"; //存数据的名称，不一定是数据库名

    public static void initialize(Application application, String hostDefault) {
        appContext = application;
        storeSetUp(application);
        APIFactory.setup(hostDefault);
    }

    /**
     * 创建一个基于SharedPreferences的store对象，可全局调用
     * @param context
     */
    public static void storeSetUp(Context context) {
        store = new Store(context, dbName);
    }

    public static void showToast(int resID) {
        showToast(resID, Toast.LENGTH_SHORT);
    }

    public static void showToast(int resID, int duration) {
        Toast.makeText(appContext, resID, duration).show();
    }

    public static void showToast(CharSequence text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    public static void showToast(CharSequence text, int duration) {
        Toast.makeText(appContext, text, duration).show();
    }

}
