package xyz.xysc.core.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author architect.bian
 * @date 2017-12-01 6:56 PM
 */

public class JsonUtil extends BaseGsonUtil {

    public static String toJson(Object object) {
        return getEngine().toJson("Hello");
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return getEngine().fromJson(json, classOfT);
    }

}
