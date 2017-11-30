package com.singularity.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;

import com.singularity.R;
import xyz.xysc.core.base.BaseActivity;
import com.singularity.databinding.ActivitySplashBinding;
import com.singularity.global.G;
import xyz.xysc.core.utils.NetworkUtil;
import com.singularity.viewmodel.SplashModel;

public class SplashActivity extends BaseActivity {

    protected SplashModel viewModel = new SplashModel(this);
    private ActivitySplashBinding binding;
    private boolean showMain = false;

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        binding.setVm(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NetworkUtil.isConnected(this)) {
            G.showToast(R.string.no_network);
        }
        binding.getRoot().postDelayed(new Runnable() {
            @Override
            public void run() {
                goMain();
            }
        }, 9000);

    }

    public synchronized void goMain() {
        if (!showMain) {
            showMain = true;
            startActivity(new Intent(this, TestActivity.class));
            finish();
        }
    }


}
