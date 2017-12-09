package my.views;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * @author architect.bian
 * @date 2017-12-09 3:10 PM
 */

public class RecyclerView extends android.support.v7.widget.RecyclerView {

    public RecyclerView(Context context) {
        super(context);
    }

    public RecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
