package xyz.xysc.core.utils;

import android.content.Context;
import android.location.LocationManager;

import java.util.List;

/**
 * @author architect.bian
 * @date 2017-12-12 3:42 PM
 */

public class GPSUtil {

    /**
     * 检测GPS是否打开
     *
     * @param context 上下文
     * @return 结果
     */
    public static boolean isGpsEnabled(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> accessibleProviders = lm.getProviders(true);
        for (String name : accessibleProviders) {
            if ("gps".equals(name)) {
                return true;
            }
        }
        return false;
    }
}
