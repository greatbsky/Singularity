package com.singularity.global;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.singularity.entity.DaoMaster;
import com.singularity.entity.DaoSession;

import org.greenrobot.greendao.AbstractDaoSession;

/**
 * @author architect.bian
 * @date 2017-11-25 7:15 PM
 */

public class Global {

    private static DaoSession daoSession = null;

    public static synchronized void daoSetUp(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "mydb", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

}
