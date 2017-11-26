package com.singularity.global;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.module.AppGlideModule;

/**
 * @author architect.bian
 * @date 2017-11-26 6:18 PM
 */
@GlideModule
public class MyAppGlideModule extends AppGlideModule {

    public MyAppGlideModule() {
        super();
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);
        builder.setDiskCache(new ExternalPreferredCacheDiskCacheFactory(context));
//        builder.setLogLevel(Log.DEBUG);
    }
}
