package xyz.xysc.core.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.StringDef;
import android.support.v4.content.ContextCompat;

/**
 * @author architect.bian
 * @date 2017-11-30 11:44 AM
 */

public class AppUtil {

    /**
     * 验证是否有权限
     * @param context
     * @param permission
     * @return
     */
    public static boolean checkPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

}
