package com.singularity.base;

import android.support.multidex.MultiDexApplication;

import com.singularity.global.G;

/**
 * @author architect.bian
 * @date 2017-11-25 6:13 PM
 */

public class BaseApplication extends MultiDexApplication {

//    public RefWatcher watcher;

    @Override
    public void onCreate() {
        super.onCreate();

        G.onCreate(this);

//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return;
//        }
//        watcher = LeakCanary.install(this); //TODO Release版本要去掉leak
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        G.onTerminate();
    }
}
