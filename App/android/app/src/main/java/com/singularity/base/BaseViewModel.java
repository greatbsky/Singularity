package com.singularity.base;

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

}
