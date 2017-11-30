package com.singularity.activity;

import android.databinding.DataBindingUtil;

import com.singularity.R;
import xyz.xysc.core.base.BaseActivity;
import com.singularity.databinding.ActivitySettingBinding;
import com.singularity.viewmodel.SettingModel;

public class SettingActivity extends BaseActivity {

    protected SettingModel viewModel = new SettingModel(this);

    @Override
    protected void initView() {
        ActivitySettingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        binding.setVm(viewModel);
        binding.include.toolbar.setTitle(R.string.setting);
        binding.include.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(binding.include.toolbar);
    }

}
