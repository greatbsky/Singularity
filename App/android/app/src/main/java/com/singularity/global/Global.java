package com.singularity.global;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.singularity.entity.DaoMaster;
import com.singularity.entity.DaoSession;

import org.greenrobot.greendao.AbstractDaoSession;

/**
 * @author architect.bian
 * @date 2017-11-25 7:15 PM
 */

public class Global {

    private static DaoSession daoSession = null;

    public static Context appContext = null;

    public static synchronized void daoSetUp(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "mydb", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        appContext = context;
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }


    public static void showToast(CharSequence text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    public static void showToast(CharSequence text, int duration) {
        Toast.makeText(appContext, text, duration).show();
    }


}
