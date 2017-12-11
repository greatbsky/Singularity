package com.singularity.rn.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintContextWrapper;
import android.util.AttributeSet;
import android.view.View;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.singularity.rn.E;

/**
 * @author architect.bian
 * @date 2017-12-11 7:18 PM
 */
public class ImageDemo extends android.support.v7.widget.AppCompatImageView {

    public ImageDemo(Context context) {
        this(context, null);
    }

    public ImageDemo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setListener();
    }

    private void setListener() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                WritableMap event = Arguments.createMap();
                event.putString("message", "You click me");
                ReactContext reactContext = (ReactContext) ((TintContextWrapper) getContext()).getBaseContext();
                reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), E.Click, event);
            }
        });
    }


}
