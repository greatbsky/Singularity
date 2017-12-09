package my.views;

import android.content.Context;
import android.util.AttributeSet;
import android.support.v4.view.ViewPager;

/**
 * @author architect.bian
 * @date 2017-12-09 3:23 PM
 */

public class TabLayout extends android.support.design.widget.TabLayout {

    public TabLayout(Context context) {
        super(context);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*----------------------------------------自定义方法----------------------------------------*/

    /**
     * 关联viewpager
     * @param pages
     */
    public void correlate(final ViewPager pages) {
        TabLayout.this.setOnTabSelectedListener(new android.support.design.widget.TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(android.support.design.widget.TabLayout.Tab tab) {
                pages.setCurrentItem(TabLayout.this.getSelectedTabPosition());
            }

            @Override
            public void onTabUnselected(android.support.design.widget.TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(android.support.design.widget.TabLayout.Tab tab) {

            }

        });
        pages.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                TabLayout.this.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
