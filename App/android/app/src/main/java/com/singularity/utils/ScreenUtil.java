package com.singularity.utils;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author architect.bian
 * @date 2017-11-30 12:25 PM
 */

public class ScreenUtil {

    public enum EScreenDensity {
        XXHDPI,    //超高分辨率    1080×1920
        XHDPI,    //超高分辨率    720×1280
        HDPI,    //高分辨率         480×800
        MDPI,    //中分辨率         320×480
    }

    public static EScreenDensity getDisply(Context context) {
        EScreenDensity eScreenDensity;
        //初始化屏幕密度
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        int densityDpi = dm.densityDpi;

        if (densityDpi <= 160) {
            eScreenDensity = EScreenDensity.MDPI;
        } else if (densityDpi <= 240) {
            eScreenDensity = EScreenDensity.HDPI;
        } else if (densityDpi < 400) {
            eScreenDensity = EScreenDensity.XHDPI;
        } else {
            eScreenDensity = EScreenDensity.XXHDPI;
        }
        return eScreenDensity;
    }

    /**
     * dp转px
     */
    public static int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getDisplayMetrics());
    }

    /**
     * sp转px
     */
    public static int sp2px(float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, getDisplayMetrics());
    }

    /**
     * px转dp
     */
    public static float px2dp(float pxVal) {
        final float scale = getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     */
    public static float px2sp(float pxVal) {
        return (pxVal / getDisplayMetrics().scaledDensity);
    }


    /**
     * 获取屏幕像素宽度的 ratio 百分比
     *
     * @return 百分比
     */
    public static int proportion(float ratio) {
        if (ratio > 1) {
            return (int) (ratio + 0.5f);
        }
        return (int) (getDisplayMetrics().widthPixels * ratio + 0.5f);
    }

    /**
     * 获取屏幕像素宽度的 ratio 百分比（offset 屏幕像素偏移量）
     *
     * @param offset 用于布局没有占满屏幕（带边框），
     *               那么 offset = 0 - 边框
     * @return proportion
     */
    public static int proportion(float ratio, int offset) {
        if (ratio > 1) {
            return (int) (ratio + 0.5f + offset);
        }
        return (int) ((getDisplayMetrics().widthPixels + offset) * ratio + 0.5f);
    }

    /**
     * 获取 DisplayMetrics
     */
    public static DisplayMetrics getDisplayMetrics() {
        return Resources.getSystem().getDisplayMetrics();
    }

    /**
     * 获取屏幕像素宽度
     *
     * @return 像素宽度
     */
    public static int getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕像素高度
     *
     * @return 像素高度
     */
    public static int getScreenHeight() {
        return getDisplayMetrics().heightPixels;
    }

    public static int getActionBarSize(Context context) {
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return 0;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /**
     * 当前是否是横屏
     *
     * @param context context
     * @return boolean
     */
    public static final boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * 当前是否是竖屏
     *
     * @param context context
     * @return boolean
     */
    public static final boolean isPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    /**
     * 调整窗口的透明度  1.0f,0.5f 变暗
     *
     * @param from    from>=0&&from<=1.0f
     * @param to      to>=0&&to<=1.0f
     * @param context 当前的activity
     */
    public static void changeBGAlpha(final float from, final float to, Activity context) {
        final Window window = context.getWindow();
        ValueAnimator animator = ValueAnimator.ofFloat(from, to);
        animator.setDuration(500);
        animator.addUpdateListener(
                new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        WindowManager.LayoutParams attrs = window.getAttributes();
                        attrs.alpha = (Float) animation.getAnimatedValue();
                        window.setAttributes(attrs);
                    }
                });
        animator.start();
    }

    /**
     * 判断是否开启了自动亮度调节
     *
     * @param activity
     * @return
     */
    public static boolean isAutoBrightness(Activity activity) {
        try {
            int flag = Settings.System.getInt(activity.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE);
            return flag == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 关闭亮度自动调节
     *
     * @param activity
     */
    public static void stopAutoBrightness(Activity activity) {
        Settings.System.putInt(activity.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
    }

    /**
     * 开启亮度自动调节
     *
     * @param activity
     */
    public static void startAutoBrightness(Activity activity) {
        Settings.System.putInt(activity.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);

    }

    /**
     * 获得当前屏幕亮度值
     *
     * @return 0~100
     */
    public static int getScreenBrightness(Context context) {
        return (int) (getScreenBrightnessInt255(context) / 255.0F * 100);
    }

    /**
     * 获得当前屏幕亮度值
     *
     * @return 0~255
     */
    public static int getScreenBrightnessInt255(Context context) {
        int screenBrightness = 255;
        try {
            screenBrightness = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return screenBrightness;
    }

    /**
     * 设置当前屏幕亮度值
     *
     * @param context
     * @param paramInt 0~255
     */
    public static void saveScreenBrightnessInt255(Context context, int paramInt) {
        if (paramInt <= 5) {
            paramInt = 5;
        }
        try {
            Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, paramInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置当前屏幕亮度值
     *
     * @param context
     * @param paramInt 0~100
     */
    public static void saveScreenBrightnessInt100(Context context, int paramInt) {
        saveScreenBrightnessInt255(context, (int) (paramInt / 100.0F * 255));
    }

    /**
     * 设置当前屏幕亮度值
     *
     * @param context
     * @param paramFloat 0~100
     */
    public static void saveScreenBrightness(Context context, float paramFloat) {
        saveScreenBrightnessInt255(context, (int) (paramFloat / 100.0F * 255));
    }

    /**
     * 设置Activity的亮度
     *
     * @param activity
     * @param paramInt
     */
    public static void setScreenBrightness(Activity activity, int paramInt) {
        if (paramInt <= 5) {
            paramInt = 5;
        }
        Window localWindow = activity.getWindow();
        WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
        float f = paramInt / 100.0F;
        localLayoutParams.screenBrightness = f;
        localWindow.setAttributes(localLayoutParams);
    }
}
