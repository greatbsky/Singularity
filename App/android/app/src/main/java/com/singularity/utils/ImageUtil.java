package com.singularity.utils;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.singularity.global.G;
import com.singularity.global.GlideApp;

/**
 * @author architect.bian
 * @date 2017-11-26 6:45 PM
 */

public class ImageUtil {

    public static void show(ImageView view, String url) {
        Log.d("Glide", url);
        GlideApp.with(G.appContext).load(url).into(view);
    }

    public static void fresh(ImageView view, String url) {
        GlideApp.with(G.appContext).load(url)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(view);
    }
}
