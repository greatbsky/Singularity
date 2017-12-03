package xyz.xysc.core.utils;

import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author architect.bian
 * @date 2017-12-03 4:15 PM
 */

public class MapUtil extends BaseGsonUtil {

    public static <T> T from(Object src, Type typeOfT) {
        JsonElement jsonElement = getEngine().toJsonTree(src);
        return getEngine().fromJson(jsonElement, typeOfT);
    }

    public static <T> T from(Object src, Class<T> classOfT) {
        JsonElement jsonElement = getEngine().toJsonTree(src);
        return getEngine().fromJson(jsonElement, classOfT);
    }
}
