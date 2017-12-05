package com.singularity.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.singularity.R;
import com.singularity.databinding.ActivityTestBinding;
import com.singularity.viewmodel.UserModel;

import xyz.xysc.core.base.BaseActivity;
import xyz.xysc.core.utils.ActivityUtil;

public class TestActivity extends BaseActivity {

    protected UserModel viewModel = new UserModel(this);

    @Override
    protected void initView(Bundle savedInstanceState) {
        ActivityTestBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        binding.setVm(viewModel);
    }

    @Override
    protected Object getListener() {
        return viewModel;
    }

    /*----------------------------------------自定义方法----------------------------------------*/

    public static void start(Activity activity, Bundle bundle) {
        ActivityUtil.start(activity, bundle, TestActivity.class);
    }

    public static void startForResult(Activity activity, Bundle bundle, int requestCode) {
        ActivityUtil.startForResult(activity, bundle, TestActivity.class, requestCode);
    }
}
