package com.singularity.global.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.StrictMode;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.singularity.R;
import com.singularity.activity.my.NotifyDetailActivity;
import com.singularity.conf.KeyConsts;
import com.singularity.entity.Notify;
import com.singularity.global.G;
import com.singularity.so.NotifySO;

import java.util.List;

import xyz.xysc.core.utils.AppUtil;

public class ForeverService extends Service {

    private static ForeverService runningContext = null;

    public void onCreate() {
        super.onCreate();
        runningContext = this;

//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        doExecute();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
        runningContext = null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /*----------------------------------------自定义方法----------------------------------------*/
    private void doExecute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("ForeverService", String.valueOf(System.currentTimeMillis()));
                List<Notify> notifyList = NotifySO.getList(3659859);
                if (notifyList != null) {
                    for (Notify n : notifyList) {
                        Intent i = new Intent(runningContext, NotifyDetailActivity.class);
                        i.putExtra(KeyConsts.id, n.getId());
                        PendingIntent pi = PendingIntent.getActivity(runningContext, 0, i, 0);
                        AppUtil.sendNotification(runningContext, (int) (long) n.getId(), n.getTitle(), n.getContent(), R.mipmap.ic_launcher, pi);
                    }
                }
                try {
                    Thread.currentThread().join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        AppUtil.alarm(this, ForeverService.class, 6 * 1000);

    }

    private void doExecute2() {
        Runnable handler = new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("ForeverService", String.valueOf(System.currentTimeMillis()));
                        List<Notify> notifyList = NotifySO.getList(3659859);
                        if (notifyList != null) {
                            for (Notify n : notifyList) {
                                Intent i = new Intent(runningContext, NotifyDetailActivity.class);
                                i.putExtra(KeyConsts.id, n.getId());
                                PendingIntent pi = PendingIntent.getActivity(runningContext, 0, i, 0);
                                AppUtil.sendNotification(runningContext, (int) (long) n.getId(), n.getTitle(), n.getContent(), R.mipmap.ic_launcher, pi);
                            }
                        }
                        try {
                            Thread.currentThread().join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                G.setTimeout(this, 5000);
            }
        };
        handler.run();
    }

    /**
     * 运行唯一的service
     *
     * @param context
     */
    public static synchronized void start(Context context) {
        if (runningContext == null) {
            context.startService(new Intent(context, ForeverService.class));
        }
    }
}
