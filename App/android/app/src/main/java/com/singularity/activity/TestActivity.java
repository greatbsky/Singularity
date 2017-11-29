package com.singularity.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;

import com.singularity.R;
import com.singularity.base.BaseActivity;
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
}
