package com.singularity.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.singularity.R;
import xyz.xysc.core.base.BaseActivity;
import com.singularity.databinding.ActivitySplashBinding;
import com.singularity.global.G;

import xyz.xysc.core.utils.JsonUtil;
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
        goWhere();

    }

    /**
     * 跳转
     */
    private void goWhere() {
        Intent intent = getIntent();
        if (intent.getData() == null) { //正常启动
            binding.getRoot().postDelayed(new Runnable() {
                @Override
                public void run() {
                    goMain();
                }
            }, 3000);
        } else {
            Uri uri = intent.getData();
            goMain(uri);
        }

    }

    public synchronized void goMain() {
        goMain(null);
    }

    public synchronized void goMain(Uri uri) { //TODO do sth with uri
        if (!showMain) {
            showMain = true;
            Bundle bundle = new Bundle();
            bundle.putString("data", JsonUtil.toJson(uri));
            TestActivity.start(this, bundle);
            finish();
        }
    }


}
