package com.singularity.viewmodel;

import android.view.View;

import com.singularity.activity.SettingActivity;
import xyz.xysc.databinding.base.BaseViewModel;

/**
 * @author architect.bian
 * @date 2017-11-29 4:24 PM
 */

public class SettingModel extends BaseViewModel {

    private final SettingActivity activity;

    public SettingModel(SettingActivity activity) {
        super();
        this.activity = activity;
    }

    public void click(View v) {
        //do sth...
    }
}
