package com.singularity.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.singularity.R;
import xyz.xysc.core.base.BaseActivity;
import xyz.xysc.core.utils.ActivityUtil;

import com.singularity.databinding.ActivityTestBinding;
import com.singularity.viewmodel.UserModel;

public class TestActivity extends BaseActivity {

    protected UserModel viewModel = new UserModel(this);

    @Override
    protected void initView() {
        ActivityTestBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        binding.setVm(viewModel);
    }

    @Override
    protected Object getListener() {
        return viewModel;
    }

    public static void start(Activity activity, Bundle bundle) {
        ActivityUtil.start(activity, bundle, TestActivity.class);
    }

    public static void startForResult(Activity activity, Bundle bundle, int requestCode) {
        ActivityUtil.startForResult(activity, bundle, TestActivity.class, requestCode);
    }
}
