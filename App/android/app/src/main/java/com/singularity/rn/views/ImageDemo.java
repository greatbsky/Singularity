package com.singularity.rn.views;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintContextWrapper;
import android.util.AttributeSet;
import android.view.View;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.singularity.rn.E;

import xyz.xysc.core.utils.ImgUtil;

/**
 * @author architect.bian
 * @date 2017-12-11 7:18 PM
 */
public class ImageDemo extends android.support.v7.widget.AppCompatImageView {

    private int borderRadius;
    private String uri;

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

    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }

    public float getBorderRadius() {
        return borderRadius;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ImgUtil.showRoundCorner(this, this.uri, this.borderRadius);
    }

}
