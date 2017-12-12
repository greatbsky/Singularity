package xyz.xysc.core.utils;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import xyz.xysc.core.global.GlideApp;
import xyz.xysc.core.global.GlideRequest;
import xyz.xysc.core.global.Global;

/**
 * @author architect.bian
 * @date 2017-11-26 6:45 PM
 */

public class ImgUtil {

    public static void show(ImageView view, String url) {
        Log.d("Glide", url);
        GlideApp.with(Global.appContext).load(url).into(view);
    }

    public static void showCircle(ImageView view, String url) {
        Log.d("Glide", url);
        GlideApp.with(Global.appContext).load(url).centerCrop().circleCrop().into(view);
    }

    public static void showRoundCorner(ImageView view, String url, int radius) {
        Log.d("Glide", url);
        GlideRequest<Drawable> request = GlideApp.with(Global.appContext).load(url).centerCrop();
        if (radius > 0) {
            request = request.transform(new RoundedCorners(radius));
        }
        request.into(view);
    }

    public static void fresh(ImageView view, String url) {
        GlideApp.with(Global.appContext).load(url)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE).centerCrop().into(view);
    }


}
