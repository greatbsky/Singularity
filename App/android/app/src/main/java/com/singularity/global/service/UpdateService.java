package com.singularity.global.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.singularity.global.G;

public class UpdateService extends Service {

    private static UpdateService runningContext = null;

    public void onCreate() {
        super.onCreate();
        runningContext = this;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        doExecute();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /*----------------------------------------自定义方法----------------------------------------*/

    /**
     * 运行唯一的service
     *
     * @param context
     */
    public static synchronized void start(Context context) {
        if (runningContext == null) {
            context.startService(new Intent(context, UpdateService.class));
        }
    }

    public void doExecute() {
        G.setTimeout(new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("UpdateService", String.valueOf(System.currentTimeMillis()));

            }
        }), 5000);
    }

}
