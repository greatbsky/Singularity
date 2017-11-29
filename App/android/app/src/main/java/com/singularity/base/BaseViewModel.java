package com.singularity.base;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.singularity.utils.ImageUtil;

/**
 * @author architect.bian
 * @date 2017-11-23 1:43 PM
 */

public class BaseViewModel extends BaseObservable {

    @BindingAdapter({"app:url"})
    public static void loadImage (ImageView view, String url) {
        ImageUtil.show(view, url);
    }

    protected void startActivity(Activity activity, Class<?>  clz) {
        activity.startActivity(new Intent(activity, clz));
    }

    protected void startActivityForResult(Activity activity, Class<?> clz, int requestCode) {
        activity.startActivityForResult(new Intent(activity, clz), requestCode);
    }
}
