package com.singularity.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.IBinder;

import com.singularity.R;
import com.singularity.base.BaseActivity;
import com.singularity.databinding.ActivityTestBinding;
import com.singularity.global.service.DownLoadService;
import com.singularity.vm.UserModel;

import xyz.xysc.core.utils.ActivityUtil;

public class TestActivity extends BaseActivity {

    private UserModel viewModel = new UserModel(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DownLoadService.bind(this, new ServiceConnection() {

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                DownLoadService.Binder binder = (DownLoadService.Binder) service;
                viewModel.binder = binder;
            }

        }, BIND_AUTO_CREATE);
    }

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
