package com.singularity.service;

import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.singularity.api.RootAPI;
import com.singularity.entity.Video;
import com.singularity.global.G;

import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xyz.xysc.core.base.BaseSO;
import xyz.xysc.core.utils.MapUtil;

/**
 * @author architect.bian
 * @date 2017-12-03 3:33 PM
 */

public class MainSO extends BaseSO {

    private static RootAPI rootAPI = newAPI(RootAPI.class);

    public static void initialize() {
        rootAPI.initialize()
                .subscribeOn(Schedulers.io())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedu、lers.mainThread())
                .subscribe(new Consumer<Map>() {
                    public void accept(Map m) {
                        Log.e("MainSO", "accept: " + m.toString());
                        List<Video> list = MapUtil.from(m.get("videos"), new TypeToken<List<Video>>(){}.getType());
                        G.getDaoSession().getVideoDao().deleteAll();
                        for(Video item : list) {
                            G.getDaoSession().getVideoDao().insert(item);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("MainSO", throwable.toString());
                    }
                });
    }
}
