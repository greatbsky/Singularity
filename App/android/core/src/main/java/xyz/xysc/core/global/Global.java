package xyz.xysc.core.global;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface.*;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import xyz.xysc.core.R;
import xyz.xysc.core.api.APIFactory;
import xyz.xysc.core.base.BaseGlobal;

/**
 * @author architect.bian
 * @date 2017-11-25 7:15 PM
 */

public class Global extends BaseGlobal {

    /*
    处理全局的存储
     */
    public static Store store;

    protected static final String dbName = "global"; //存数据的名称，不一定是数据库名
    public static boolean shouldExit = false; //是否应该退出

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

    /**
     * 延迟重置shouldExit
     */
    public static void shouldExitInvalideDelay() {
        setTimeout(new Runnable() {
            @Override
            public void run() {
                shouldExit = false;
            }
        }, 2000);
        // do sth...example post logs to server
    }

    public static void setTimeout(Runnable runnable, long delayMillis) {
        new Handler().postDelayed(runnable, delayMillis);
    }
}
