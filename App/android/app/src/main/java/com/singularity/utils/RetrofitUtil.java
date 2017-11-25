package com.singularity.utils;

import com.singularity.conf.APIConf;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author architect.bian
 * @date 2017-11-25 3:12 PM
 */

public class RetrofitUtil {

    private static Retrofit defaultRetrofit = null;

    public static Retrofit getDefault() {
        if (defaultRetrofit == null) {
            defaultRetrofit = getRetrofit(APIConf.hostDefault);
        }
        return defaultRetrofit;
    }

    public static Retrofit getRetrofit(String url) {
        return new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
