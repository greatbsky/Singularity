package com.singularity.activity.my;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.singularity.R;
import com.singularity.base.BaseActivity;
import com.singularity.conf.KeyConsts;
import com.singularity.databinding.ActivityNotifyDetailBinding;
import com.singularity.viewmodel.NotifyModel;

public class NotifyDetailActivity extends BaseActivity {

    public static int rcShowNotify = 0;

    protected NotifyModel viewModel = new NotifyModel(this);

    @Override
    protected void initView(Bundle savedInstanceState) {
        ActivityNotifyDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_notify_detail);
        binding.setVm(viewModel);
        Intent i = getIntent();
        long id = i.getLongExtra(KeyConsts.id, -1);
        if (id > 0) {
            viewModel.show(id);
        }
    }

    /*----------------------------------------自定义方法----------------------------------------*/


}
