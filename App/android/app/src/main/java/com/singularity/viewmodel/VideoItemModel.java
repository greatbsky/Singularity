package com.singularity.viewmodel;

import android.view.View;

import com.singularity.activity.MainActivity;
import com.singularity.global.G;

import xyz.xysc.databinding.base.BaseViewModel;

/**
 * @author architect.bian
 * @date 2017-12-03 5:09 PM
 */

public class VideoItemModel extends BaseViewModel {

    private final MainActivity activity;

    public VideoItemModel(MainActivity activity) {
        super();
        this.activity = activity;
    }

    public void alertTitle(View v, String title) {
        G.alert(this.activity, title, null, null);
    }
}
