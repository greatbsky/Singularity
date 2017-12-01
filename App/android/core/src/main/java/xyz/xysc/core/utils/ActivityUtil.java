package xyz.xysc.core.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Objects;

/**
 * @author architect.bian
 * @date 2017-11-22 6:16 PM
 */
public class ActivityUtil {


    public static void start(Activity activity, Bundle bundle, Class<?> clzTarget) {
        Intent i = new Intent(activity, clzTarget);
        if (bundle != null) {
            i.putExtras(bundle);
        }
        activity.startActivity(i);
    }

    public static void startForResult(Activity activity, Bundle bundle, Class<?> clzTarget, int requestCode) {
        Intent i = new Intent(activity, clzTarget);
        if (bundle != null) {
            i.putExtras(bundle);
        }
        activity.startActivityForResult(i, requestCode);
    }

    /**
     * The {@code fragment} is added to the container view with daotxt {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     *
     */
    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId) {
        Objects.requireNonNull(fragmentManager);
        Objects.requireNonNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    /**
     * The {@code fragment} is added to the container view with daotxt {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     *
     */
    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, String tag) {
        Objects.requireNonNull(fragmentManager);
        Objects.requireNonNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(fragment, tag);
        transaction.commit();
    }

}
