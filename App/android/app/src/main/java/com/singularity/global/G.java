package com.singularity.global;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.singularity.base.BaseApplication;
import com.singularity.conf.APIConf;
import com.singularity.entity.DaoMaster;
import com.singularity.entity.DaoSession;

import xyz.xysc.core.api.APIFactory;
import xyz.xysc.core.global.Global;

/**
 * @author architect.bian
 * @date 2017-11-26 8:37 PM
 */
public class G extends Global {

    private static DaoSession daoSession = null;

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    public static synchronized void daoSetUp(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, dbName, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static void initialize(Application application) {
        Global.initialize(application, APIConf.hostDefault);
        G.daoSetUp(application);
    }
}
