package com.singularity.activity;

import android.databinding.DataBindingUtil;

import com.singularity.R;
import com.singularity.base.BaseActivity;
import com.singularity.databinding.ActivityMainBinding;
import com.singularity.viewmodel.UserModel;

public class MainActivity extends BaseActivity {

    @Override
    protected void initView() {

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setVm(new UserModel(this));

    }

}
