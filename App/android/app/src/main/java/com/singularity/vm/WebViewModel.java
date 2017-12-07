package com.singularity.vm;

import android.app.Activity;
import android.databinding.Bindable;
import android.support.v4.app.FragmentActivity;

import com.singularity.entity.Video;
import com.singularity.so.VideoSO;

import java.util.List;

import xyz.xysc.databinding.base.BaseViewModel;

/**
 * @author architect.bian
 * @date 2017-12-03 2:15 PM
 */
public class WebViewModel extends BaseViewModel {


    private final Activity activity;

    public WebViewModel(Activity activity) {
        this.activity = activity;
    }

}
