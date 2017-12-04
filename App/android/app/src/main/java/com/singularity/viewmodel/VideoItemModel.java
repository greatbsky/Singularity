package com.singularity.viewmodel;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.singularity.R;
import com.singularity.global.G;

import xyz.xysc.core.utils.ActivityUtil;
import xyz.xysc.databinding.base.BaseViewModel;

/**
 * @author architect.bian
 * @date 2017-12-03 5:09 PM
 */

public class VideoItemModel extends BaseViewModel {

    private final FragmentActivity activity;

    public VideoItemModel(FragmentActivity activity) {
        super();
        this.activity = activity;
    }

    public void alertTitle(View v, String title) {
        G.alert(this.activity, title, null, null);
    }
}
