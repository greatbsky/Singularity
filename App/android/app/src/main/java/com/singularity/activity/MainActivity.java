package com.singularity.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.singularity.R;
import com.singularity.activity.video.VideoFragment;
import com.singularity.base.BaseActivity;
import com.singularity.databinding.ActivityMainBinding;
import com.singularity.vm.MainModel;

import xyz.xysc.core.utils.ActivityUtil;

public class MainActivity extends BaseActivity {

    protected MainModel viewModel = new MainModel(this);

    @Override
    protected void initView(Bundle savedInstanceState) {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setVm(viewModel);

        ActivityUtil.replaceFragment(this, new VideoFragment(), R.id.container);
    }

    /*----------------------------------------自定义方法----------------------------------------*/

    public static void start(Activity activity, Bundle bundle) {
        ActivityUtil.start(activity, bundle, MainActivity.class);
    }

    public static void startForResult(Activity activity, Bundle bundle, int requestCode) {
        ActivityUtil.startForResult(activity, bundle, MainActivity.class, requestCode);
    }
}
