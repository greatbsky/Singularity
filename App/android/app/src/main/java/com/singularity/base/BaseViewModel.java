package com.singularity.base;

import android.content.Context;
import android.databinding.BaseObservable;
import android.widget.Toast;

import com.singularity.api.APIFactory;

/**
 * @author architect.bian
 * @date 2017-11-23 1:43 PM
 */

public class BaseViewModel extends BaseObservable {

    protected final Context context;

    public BaseViewModel(Context context) {
        this.context = context;
    }

    protected void showToast(CharSequence text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    protected void showToast(CharSequence text, int duration) {
        Toast.makeText(context, text, duration).show();
    }

}
