package xyz.xysc.databinding.base;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;

/**
 * @author architect.bian
 * @date 2017-11-23 1:43 PM
 */
public class BaseViewModel extends BaseObservable {

    protected void startActivity(Activity activity, Class<?>  clz) {
        activity.startActivity(new Intent(activity, clz));
    }

    protected void startActivityForResult(Activity activity, Class<?> clz, int requestCode) {
        activity.startActivityForResult(new Intent(activity, clz), requestCode);
    }
}
