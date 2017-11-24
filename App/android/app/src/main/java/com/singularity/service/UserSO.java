package com.singularity.service;

import android.databinding.ObservableField;

import com.singularity.api.UserAPI;
import com.singularity.base.BaseSO;
import com.singularity.entity.User;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author architect.bian
 * @date 2017-11-23 6:54 PM
 */

public class UserSO extends BaseSO {

    private UserAPI userAPI = newAPI(UserAPI.class);

    public void get(String uid, final ObservableField<User> user) {
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
}
