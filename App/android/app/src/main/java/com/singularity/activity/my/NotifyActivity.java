package com.singularity.activity.my;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.singularity.R;
import com.singularity.base.BaseActivity;
import com.singularity.databinding.ActivityNotifyDetailBinding;
import com.singularity.vm.NotifyModel;

public class NotifyActivity extends BaseActivity {

    protected NotifyModel viewModel = new NotifyModel(this);

    @Override
    protected void initView(Bundle savedInstanceState) {
        ActivityNotifyDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_notify);
        binding.setVm(viewModel);
    }

    /*----------------------------------------自定义方法----------------------------------------*/

}
