package xyz.xysc.databinding.adapters;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import xyz.xysc.core.utils.ImgUtil;

/**
 * @author architect.bian
 * @date 2017-11-30 4:36 PM
 */
public class ImageViewAdapter {

    @BindingAdapter(value = {"url"}, requireAll = false)
    public static void loadImage (ImageView view, String url) {
        ImgUtil.show(view, url);
    }
}
