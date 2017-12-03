package xyz.xysc.core.base;

import android.app.Activity;
import android.app.Application;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import xyz.xysc.core.R;

/**
 * @author architect.bian
 * @date 2017-12-03 6:50 PM
 */

public abstract class BaseGlobal {

    public static Application appContext = null;

    public static void showToast(int resID) {
        showToast(resID, Toast.LENGTH_SHORT);
    }

    public static void showToastLong(int resID) {
        showToast(resID, Toast.LENGTH_LONG);
    }

    public static void showToast(CharSequence text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    public static void showToastLong(CharSequence text) {
        showToast(text, Toast.LENGTH_LONG);
    }

    public static void showToast(int resID, int duration) {
        Toast.makeText(appContext, resID, duration).show();
    }

    public static void showToast(CharSequence text, int duration) {
        Toast.makeText(appContext, text, duration).show();
    }

    public static void alert(Activity activity, int title, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        alert(activity, title, -1, positiveListener, negativeListener);
    }

    public static void alert(Activity activity, String title, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        alert(activity, title, -1, positiveListener, negativeListener);
    }

    public static void alert(Activity activity, int title, int message, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = createAlertBuilder(activity, positiveListener, negativeListener);
        if (title > 0) {
            builder.setTitle(title);
        }
        if (message > 0) {
            builder.setMessage(message);
        }
        builder.show();
    }

    public static void alert(Activity activity, String title, int message, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = createAlertBuilder(activity, positiveListener, negativeListener);
        if (title != null) {
            builder.setTitle(title);
        }
        if (message > 0) {
            builder.setMessage(message);
        }
        builder.show();
    }

    private static AlertDialog.Builder createAlertBuilder(Activity activity, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (positiveListener != null || negativeListener != null) {
            builder.setCancelable(false);
        } else {
            builder.setCancelable(true);
        }
        if (positiveListener != null) {
            builder.setPositiveButton(R.string.positive, positiveListener);
        }
        if (negativeListener != null) {
            builder.setNegativeButton(R.string.cancel, negativeListener);
        }
        return builder;
    }

}
