package com.singularity.rn.modules;

import android.os.Bundle;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.singularity.global.service.ForeverService;
import com.singularity.rn.RNBridge;
import com.singularity.rn.base.BaseModule;

/**
 * @author architect.bian
 * @date 2017-12-11 2:01 PM
 */
public class NotifyModule extends BaseModule {

    public NotifyModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "NotifyModule";
    }

    public void start(Callback callback) {
        recall(callback);
        ForeverService.start(this.getReactApplicationContext());
    }

    @ReactMethod
    public void start() {
        ForeverService.start(this.getReactApplicationContext());
    }

    @ReactMethod
    public void startSync(Promise promise) {
        assign(promise);
        ForeverService.start(this.getReactApplicationContext());
    }

    private void assign(Promise promise) {
        RNBridge.notifyPromise = promise;
    }

    @ReactMethod
    public void recall(Callback callback) {
        RNBridge.notifyCallback = callback;
    }
}
