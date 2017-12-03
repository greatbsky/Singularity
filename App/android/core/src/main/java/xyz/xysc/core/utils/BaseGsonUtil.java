package xyz.xysc.core.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author architect.bian
 * @date 2017-12-03 4:17 PM
 */

public abstract class BaseGsonUtil {

    protected static Gson engine;

    protected static Gson getEngine() {
        if (engine == null) {
            engine = new GsonBuilder().create();
        }
        return engine;
    }
}
