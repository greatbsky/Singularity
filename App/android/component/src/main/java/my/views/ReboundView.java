package my.views;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

import my.R;

/**
 * @author architect.bian
 * @date 2017-11-29 7:50 PM
 */

public class ReboundView extends ScrollView {

    private float startY;
    private View contentView;
    private Rect originalRect = new Rect();
    private float moveFactor = 0.5f;
    private long animTime = 300;

    public ReboundView(Context context) {
        super(context);
    }

    public ReboundView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        LayoutInflater.from(context).inflate(R.layout.xxxx, this);
    }

    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            contentView = getChildAt(0);
        }
        super.onFinishInflate();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (contentView != null) {
            originalRect.set(contentView.getLeft(), contentView.getTop(), contentView.getRight(), contentView.getBottom());
        }
    }

    /**
     * 在触摸事件中, 处理上拉和下拉的逻辑
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int offset = (int)((e.getY() - startY) * moveFactor);
                contentView.layout(originalRect.left, originalRect.top + offset, originalRect.right, originalRect.bottom);
                break;
            case MotionEvent.ACTION_UP:
                // 开启动画
                TranslateAnimation anim = new TranslateAnimation(0, 0, contentView.getTop(), originalRect.top);
                anim.setDuration(animTime);

                contentView.startAnimation(anim);

                // 设置回到正常的布局位置
                contentView.layout(originalRect.left, originalRect.top, originalRect.right, originalRect.bottom);
                break;
        }
//        this.invalidate();
//        this.requestLayout();

//        this.getRootView().invalidate();
        this.getParent().requestLayout();
        return super.dispatchTouchEvent(e);
    }
}
