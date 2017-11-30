package com.singularity.viewmodel;

import android.view.View;

import com.singularity.activity.SplashActivity;
import xyz.xysc.databinding.base.BaseViewModel;

/**
 * @author architect.bian
 * @date 2017-11-28 4:48 PM
 */

public class SplashModel extends BaseViewModel {

    private SplashActivity activity = null;

    public SplashModel(SplashActivity activity) {
        this.activity = activity;
    }
    
    public void btnSkip(View v) {
        activity.goMain();
    }

}
