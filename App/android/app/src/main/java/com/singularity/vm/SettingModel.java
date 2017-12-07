package com.singularity.vm;

import android.databinding.ObservableField;
import android.view.View;

import com.singularity.activity.SettingActivity;
import com.singularity.activity.WebViewActivity;

import xyz.xysc.databinding.base.BaseViewModel;

/**
 * @author architect.bian
 * @date 2017-11-29 4:24 PM
 */

public class SettingModel extends BaseViewModel {

    private final SettingActivity activity;
    public ObservableField<Integer> progress = new ObservableField<>();

    public SettingModel(SettingActivity activity) {
        super();
        this.activity = activity;
    }

    public void click(View v) {
        //do sth...
    }

    public void showAboutus(View v) {
        WebViewActivity.start(activity, "http://www.baidu.com");
    }

    public void checkUpdate(View v) {
        activity.test();
    }
}
