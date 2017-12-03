package com.singularity.service;

import android.databinding.ObservableField;
import android.util.Log;

import com.singularity.api.UserAPI;
import xyz.xysc.core.base.BaseSO;
import com.singularity.entity.User;
import com.singularity.global.G;

import xyz.xysc.core.global.Global;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author architect.bian
 * @date 2017-11-23 6:54 PM
 */

public class UserSO extends BaseSO {

    private static UserAPI userAPI = newAPI(UserAPI.class);

    public static void get(String uid, final ObservableField<User> user) {
        userAPI.get("uid0000001").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    public void accept(User u) {
                        user.set(u);
                    }
                });

//        userAPI.create("uid0000001").enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                //处理请求成功
//                Log.e("OkHttp", "处理成功请求 response = " + response.body().toString());
//                user.set(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                //处理请求失败
//                Log.e("OkHttp", "处理失败请求");
//            }
//        });

//        try {
//            User u = userAPI.create("uid0000001").execute().body();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static long add(String lucy) {
        User user = new User(lucy);
        G.getDaoSession().getUserDao().insert(user);
        Log.d("dao", "Inserted new user, ID: " + user.getId());
        return user.getId();
    }

    public static User get(Long id) {
        return G.getDaoSession().getUserDao().load(id);
    }
}
