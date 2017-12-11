package com.singularity.rn.modules;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.singularity.rn.ActivityListener;
import com.singularity.rn.LifecycleListener;
import com.singularity.rn.RNBridge;
import com.singularity.rn.base.BaseModule;

import java.util.HashMap;
import java.util.Map;

/**
 * @author architect.bian
 * @date 2017-12-11 12:42 PM
 */
public class RNBridgeModule extends BaseModule {

    public RNBridgeModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RNBridgeModule";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        return constants;
    }

    @ReactMethod
    public void init() {
        RNBridge.applicationContext = this.getReactApplicationContext();
        RNBridge.activity = this.getCurrentActivity();
        RNBridge.applicationContext.addActivityEventListener(new ActivityListener());
        RNBridge.applicationContext.addLifecycleEventListener(new LifecycleListener());
    }
}
