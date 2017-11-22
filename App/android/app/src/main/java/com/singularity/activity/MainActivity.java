package com.singularity.activity;

import android.databinding.DataBindingUtil;

import com.singularity.R;
import com.singularity.base.BaseActivity;
import com.singularity.databinding.ActivityMainBinding;
import com.singularity.entity.User;

public class MainActivity extends BaseActivity {

    @Override
    protected void initView() {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setUser(new User("XX", "我是XX"));
        binding.setAction(this);
    }

    public void click() {
        showToast("U click me");
    }
}
