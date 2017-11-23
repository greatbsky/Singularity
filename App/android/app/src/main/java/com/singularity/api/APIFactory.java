package com.singularity.api;

import com.singularity.conf.APIConf;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author architect.bian
 * @date 2017-11-23 5:27 PM
 */

public class APIFactory {

    private static Retrofit defaultRetrofit = null;

    public static synchronized Retrofit getRetrofit() {
        if (defaultRetrofit == null) {
            defaultRetrofit = getRetrofit(APIConf.host);
        }
        return defaultRetrofit;
    }

    public static Retrofit getRetrofit(String url) {
        return new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static <T> T get(Class<T> clazz) {
        return getRetrofit().create(clazz);
    }

}
