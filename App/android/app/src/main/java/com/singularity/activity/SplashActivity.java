package com.singularity.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.singularity.*;
import com.singularity.base.BaseActivity;
import com.singularity.databinding.ActivitySplashBinding;
import com.singularity.global.GlideApp;
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
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }


}
