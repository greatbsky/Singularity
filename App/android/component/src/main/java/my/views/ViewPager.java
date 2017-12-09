package my.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.support.design.widget.*;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.support.design.widget.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author architect.bian
 * @date 2017-12-09 3:17 PM
 */

public class ViewPager extends android.support.v4.view.ViewPager {

    private static final String TAG = "ViewPager";
    private View[] views;

    public ViewPager(Context context) {
        super(context);
    }

    public ViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        views = getChildren();
        if (views.length > 0) {
            bindAdapter();
        }
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    /*----------------------------------------自定义方法----------------------------------------*/

    private void bindAdapter() {
        this.removeAllViews();
        this.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return views.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(views[position]);
                return views[position];
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                Log.e(TAG, "destroyItem: " + ViewPager.this.getChildCount() );
                container.removeView((View) object);
            }

        });
    }

    private View[] getChildren() {
        View[] children = new View[this.getChildCount()];
        for (int i = 0, j = this.getChildCount(); i < j; i++) {
            children[i] = this.getChildAt(i);
        }
        return children;
    }

    public void correlate(final TabLayout tabs) {
        tabs.setOnTabSelectedListener(new android.support.design.widget.TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(android.support.design.widget.TabLayout.Tab tab) {
                ViewPager.this.setCurrentItem(tabs.getSelectedTabPosition());
            }

            @Override
            public void onTabUnselected(android.support.design.widget.TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(android.support.design.widget.TabLayout.Tab tab) {

            }

        });
        ViewPager.this.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabs.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
