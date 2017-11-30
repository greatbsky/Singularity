package xyz.xysc.core.api;

import retrofit2.Retrofit;
import xyz.xysc.core.utils.RetrofitUtil;

/**
 * @author architect.bian
 * @date 2017-11-23 5:27 PM
 */

public class APIFactory {

    private static Retrofit defaultRetrofit = null;

    public static Retrofit setup(String host) {
        if (defaultRetrofit == null) {
            defaultRetrofit = RetrofitUtil.getRetrofit(host);
        }
        return defaultRetrofit;
    }

    public static <T> T create(Class<T> clazz) {
        return defaultRetrofit.create(clazz);
    }

}
