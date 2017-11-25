package com.singularity.base;

import android.support.multidex.MultiDexApplication;

import com.singularity.global.Global;

/**
 * @author architect.bian
 * @date 2017-11-25 6:13 PM
 */

public class BaseApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Global.daoSetUp(this);
    }
}
