package xyz.xysc.core.utils;

import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import static android.view.ViewGroup.LayoutParams;
import static android.view.View.MeasureSpec;

/**
 * @author architect.bian
 * @date 2017-11-28 12:02 PM
 */

public class MeasureUtil {

    /**
     * 对view进行测量
     * @param view
     * @return 返回 Point x 宽度 y 高度
     */
    public static Point measureView(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p == null) {
            p = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        }
        int childHeightSpec;
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        if (p.height > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(p.height, MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        view.measure(childWidthSpec, childHeightSpec);
        return new Point(view.getMeasuredWidth(), view.getMeasuredHeight());
    }
}
