package xyz.xysc.core.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Objects;

/**
 * @author architect.bian
 * @date 2017-11-22 6:16 PM
 */
public class ActivityUtil {

    /**
     * 启动某个activity
     * @param context
     * @param clzTarget
     */
    public static void start(Context context, Class<?> clzTarget) {
        start(context, null, clzTarget, -1);
    }

    /**
     *
     * @param context
     * @param clzTarget
     * @param flags > 0
     */
    public static void start(Context context, Class<?> clzTarget, int flags) {
        start(context, null, clzTarget, flags);
    }

    public static void start(Context context, Bundle bundle, Class<?> clzTarget) {
        start(context, bundle, clzTarget, -1);
    }

    public static void start(Context context, Bundle bundle, Class<?> clzTarget, int flags) {
        Intent i = new Intent(context, clzTarget);
        if (bundle != null) {
            i.putExtras(bundle);
        }
        if (flags > 0) {
            i.setFlags(flags);
        }
        context.startActivity(i);
    }

    public static void startForResult(Activity activity, Class<?> clzTarget, int requestCode) {
        startForResult(activity, clzTarget, requestCode);
    }

    public static void startForResult(Activity activity, Bundle bundle, Class<?> clzTarget, int requestCode) {
        Intent i = new Intent(activity, clzTarget);
        if (bundle != null) {
            i.putExtras(bundle);
        }
        activity.startActivityForResult(i, requestCode);
    }

    public static void startTel(Context context, String telNum) {
        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("tel:" + telNum));
        context.startActivity(i);
    }

    /**
     * 添加一个fragment到containerID
     * @param activity
     * @param fragment
     * @param containerId
     */
    public static void replaceFragment(@NonNull FragmentActivity activity, @NonNull Fragment fragment, int containerId) {
        Objects.requireNonNull(activity);
        Objects.requireNonNull(fragment);
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
//        transaction.addToBackStack(null);
        transaction.replace(containerId, fragment);
        transaction.commit();
    }

    /**
     * 使用ID获取Fragment对象
     * @param activity
     * @param id
     * @return
     */
    public static Fragment getFragment(@NonNull FragmentActivity activity, @IdRes int id) {
        return activity.getSupportFragmentManager().findFragmentById(id);
    }

    public static FileInputStream getInputStream(Context context, String name) {
        try {
            return context.openFileInput(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static FileOutputStream getOutputStream(Context context, String name, int mode) {
        try {
            return context.openFileOutput(name, mode);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用于获取/data/data/<package name>/cache目录
     * @param context
     * @return
     */
    public static File getCacheDir(Context context) {
        return context.getCacheDir();
    }

    /**
     * 用于获取/data/data/<package name>/files目录
     * @param context
     * @return
     */
    public static File getFilesDir(Context context) {
        return context.getFilesDir();
    }
}
