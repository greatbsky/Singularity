package xyz.xysc.core.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author architect.bian
 * @date 2017-11-25 3:12 PM
 */

public class RetrofitUtil {

    public static Retrofit getRetrofit(String url) {
        return new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
