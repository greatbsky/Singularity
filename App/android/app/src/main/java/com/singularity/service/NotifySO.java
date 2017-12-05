package com.singularity.service;

import android.databinding.ObservableField;

import com.singularity.api.NotifyAPI;
import com.singularity.api.UserAPI;
import com.singularity.entity.Notify;
import com.singularity.entity.NotifyDao;
import com.singularity.entity.User;
import com.singularity.global.G;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.IOException;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xyz.xysc.core.base.BaseSO;

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
}
