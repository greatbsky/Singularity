package com.singularity.service;

import android.databinding.ObservableField;
import android.util.Log;

import com.singularity.api.UserAPI;
import com.singularity.base.BaseSO;
import com.singularity.entity.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author architect.bian
 * @date 2017-11-23 6:54 PM
 */

public class UserSO extends BaseSO {

    private UserAPI userAPI = getAPI(UserAPI.class);

    public void get(String uid, final ObservableField<User> user) {
        userAPI.get("uid0000001").enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //处理请求成功
                Log.e("OkHttp", "处理成功请求 response = " + response.body().toString());
                user.set(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //处理请求失败
                Log.e("OkHttp", "处理失败请求");
            }
        });

//        try {
//            User u = userAPI.get("uid0000001").execute().body();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
