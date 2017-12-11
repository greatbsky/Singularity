package com.singularity.rn.base;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

/**
 * @author architect.bian
 * @date 2017-12-11 3:26 PM
 */

public abstract class BaseModule extends ReactContextBaseJavaModule {

    public BaseModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public void recall(Callback callback) {

    };

}
