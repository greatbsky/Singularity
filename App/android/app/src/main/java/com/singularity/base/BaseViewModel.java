package com.singularity.base;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.singularity.api.APIFactory;
import com.singularity.utils.ImageUtil;

/**
 * @author architect.bian
 * @date 2017-11-23 1:43 PM
 */

public class BaseViewModel extends BaseObservable {

    protected Context context;

    public BaseViewModel(Context context) {
        this.context = context;
    }

    @BindingAdapter({"app:url"})
    public static void loadImage (ImageView view, String url) {
        ImageUtil.show(view, url);
    }

    /**
     * activity释放时调用此方法释放其引用
     */
    public void destroy() {
        this.context = null;
    }
}
