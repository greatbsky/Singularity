package xyz.xysc.databinding.adapters;

import android.databinding.BindingAdapter;
import android.support.v7.widget.Toolbar;

/**
 * @author architect.bian
 * @date 2017-12-01 11:50 AM
 */

public class ToolbarAdapter {

    @BindingAdapter(value = {"title"}, requireAll = false)
    public static void bind(Toolbar toolbar, String title){
        toolbar.setTitle(title);
    }

}
