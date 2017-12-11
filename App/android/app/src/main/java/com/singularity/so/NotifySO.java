package com.singularity.so;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.react.HeadlessJsTaskService;
import com.singularity.R;
import com.singularity.activity.my.NotifyDetailActivity;
import com.singularity.api.NotifyAPI;
import com.singularity.conf.KeyConsts;
import com.singularity.entity.Notify;
import com.singularity.entity.NotifyDao;
import com.singularity.global.G;
import com.singularity.global.service.ForeverService;
import com.singularity.rn.MyTaskService;
import com.singularity.rn.RNBridge;
import com.singularity.utils.BundleBuilder;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.IOException;
import java.util.List;

import xyz.xysc.core.base.BaseSO;
import xyz.xysc.core.utils.AppUtil;

/**
 * @author architect.bian
 * @date 2017-12-05 5:05 PM
 */

public class NotifySO extends BaseSO {

    private static NotifyAPI api = newAPI(NotifyAPI.class);

    public static List<Notify> getList(long userid) {
        try {
            List<Notify> list = api.get(userid, System.currentTimeMillis()).execute().body();
            if (list != null && list.size() > 0) {
                for (Notify item : list) {
                    Notify n = getByUid(item.getUid());
                    if (n != null) {
                        item.setId(n.getId());
                    }
                    G.getDaoSession().getNotifyDao().save(item);
                }
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Notify getByUid(String uid) {
        QueryBuilder<Notify> builder = G.getDaoSession().getNotifyDao().queryBuilder()
                .where(NotifyDao.Properties.Uid.eq(uid));
        if (builder.count() > 0) {
            return builder.list().get(0);
        }
        return null;
    }

    public static Notify get(long id) {
        return G.getDaoSession().getNotifyDao().load(id);
    }


    public static void delete(long id) {
        G.getDaoSession().getNotifyDao().queryBuilder()
                .where(NotifyDao.Properties.Id.eq(id))
                .buildDelete().executeDeleteWithoutDetachingEntities();
    }

    public static void add() {
        Notify n = new Notify();
//        n.setId(11L);
        n.setTitle("title...");
        n.setContent("content");
        G.getDaoSession().getNotifyDao().save(n);
    }

    public static void notifyInThread(final Context context, final long userId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NotifySO.notify(userId, context);
                try {
                    Thread.currentThread().join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void notify(long userId, Context context) {
        List<Notify> notifyList = NotifySO.getList(userId);
        if (notifyList != null) {
            for (Notify n : notifyList) {
                Intent i = new Intent(context, NotifyDetailActivity.class);
                i.putExtra(KeyConsts.id, n.getId());
                PendingIntent pi = PendingIntent.getActivity(context, 0, i, 0);
                AppUtil.sendNotification(context, (int) (long) n.getId(), n.getTitle(), n.getContent(), R.mipmap.ic_launcher, pi);

//                RNBridge.notifyCallbackHandler(n.getTitle(), n.getContent(), n.getCreateTime());
                MyTaskService.start(context, new BundleBuilder("k", "v").build());
            }
        }
    }
}
