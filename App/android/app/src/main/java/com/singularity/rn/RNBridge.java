package com.singularity.rn;

import android.app.Activity;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

/**
 * @author architect.bian
 * @date 2017-12-11 2:34 PM
 */

public class RNBridge {

    public static ReactApplicationContext applicationContext;
    public static Activity activity;

    public static Callback notifyCallback;
    public static Promise notifyPromise;

    public static void notifyCallbackHandler(String title, String content, Long createTime) {
        if (notifyCallback != null) {
            notifyCallback.invoke(title, content, String.valueOf(createTime));
            notifyCallback = null;
        }
        if (notifyPromise != null) {
            WritableMap map = Arguments.createMap();
            map.putString("title", title);
            map.putString("content", content);
            map.putString("createTime", String.valueOf(createTime));
            notifyPromise.resolve(map);
            notifyPromise = null;
        }
        WritableMap map = Arguments.createMap();
        map.putString("title", title);
        map.putString("content", content);
        map.putString("createTime", String.valueOf(createTime));
        emit(E.Notify, map);
    }

    /**
     * 发射事件到JS端
     * @param event
     * @param map
     */
    public static void emit(String event, WritableMap map) {
        applicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(event, map);
    }
}
