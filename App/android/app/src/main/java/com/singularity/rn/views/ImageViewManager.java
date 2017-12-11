package com.singularity.rn.views;

import static android.widget.ImageView.ScaleType;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.singularity.rn.E;

import java.util.Map;

import javax.annotation.Nullable;

import xyz.xysc.core.utils.ImgUtil;

/**
 * @author architect.bian
 * @date 2017-12-11 6:40 PM
 */
public class ImageViewManager extends SimpleViewManager<ImageDemo> {

    @Override
    public String getName() {
        return "ImageView";
    }

    @Override
    protected ImageDemo createViewInstance(ThemedReactContext context) {
        return new ImageDemo(context);
    }

    @ReactProp(name = "src")
    public void setSrc(ImageDemo view, ReadableArray sources) {
        if(sources != null && sources.size() != 0) {
            if(sources.size() == 1) {
                ReadableMap source = sources.getMap(0);
                String uri = source.getString("uri");
                ImgUtil.show(view, uri);
//                view.setImageURI(Uri.parse(uri));
//                view.invalidate();
            }
        }
    }

//    @ReactProp(name = "borderRadius", defaultFloat = 0f)
//    public void setBorderRadius(ImageView view, float borderRadius) {
//        view.setBorderRadius(borderRadius);
//    }

    @ReactProp(name = ViewProps.RESIZE_MODE)
    public void setResizeMode(ImageDemo view, @Nullable String resizeMode) {
//        view.setScaleType(toScaleType(resizeMode));
    }

    public static ScaleType toScaleType(@Nullable String resizeModeValue) {
        if ("matrix".equals(resizeModeValue)) {
            return ScaleType.MATRIX;
        }
        if ("fitXY".equals(resizeModeValue)) {
            return ScaleType.FIT_XY;
        }
        if ("fitStart".equals(resizeModeValue)) {
            return ScaleType.FIT_START;
        }
        if ("fitCenter".equals(resizeModeValue)) {
            return ScaleType.FIT_CENTER;
        }
        if ("fitEnd".equals(resizeModeValue)) {
            return ScaleType.FIT_END;
        }
        if ("center".equals(resizeModeValue)) {
            return ScaleType.CENTER;
        }
        if ("centerCrop".equals(resizeModeValue)) {
            return ScaleType.CENTER_CROP;
        }
        if ("centerInside".equals(resizeModeValue)) {
            return ScaleType.CENTER_INSIDE;
        }
        throw new JSApplicationIllegalArgumentException("Invalid resize mode: '" + resizeModeValue + "'");
    }

    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder().put(
                E.Click,
                MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onClick"))
                ).build();
    }
}
