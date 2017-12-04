package xyz.xysc.core.global;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author architect.bian
 * @date 2017-12-04 3:14 PM
 */

public class ActivityHistory {

    private static List<Activity> history = new ArrayList<>();

    public static int getCount() {
        return history.size();
    }

    public static void add(Activity activity) {
        history.add(activity);
    }

    public static void remove(Activity activity) {
        history.remove(activity);
    }

    public static void finishAll() {
        for (Activity item : history) {
            if (!item.isFinishing()) {
                item.finish();
            }
        }
    }
}
