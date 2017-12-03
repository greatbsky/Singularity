package xyz.xysc.core.utils;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import xyz.xysc.core.global.GlideApp;
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

    public static void fresh(ImageView view, String url) {
        GlideApp.with(Global.appContext).load(url)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE).centerCrop().into(view);
    }


}
