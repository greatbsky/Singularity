package com.singularity.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;

import com.singularity.R;
import xyz.xysc.core.base.BaseActivity;
import com.singularity.databinding.ActivitySplashBinding;
import com.singularity.event.Events;
import com.singularity.global.G;

import xyz.xysc.core.utils.JsonUtil;
import xyz.xysc.core.utils.NetworkUtil;
import com.singularity.viewmodel.SplashModel;

import org.greenrobot.eventbus.EventBus;

public class SplashActivity extends BaseActivity {

    protected SplashModel viewModel = new SplashModel(this);
    private ActivitySplashBinding binding;
    private boolean showMain = false;

    @Override
    protected void initView(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        binding.setVm(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NetworkUtil.isConnected(this)) {
            G.showToast(R.string.no_network);
        } else {
            Events.post(new Events.InitializeDataEvent()); //触发事件加载数据
        }
        goWhere();

    }

    /*----------------------------------------自定义方法----------------------------------------*/

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
            }, 2000);
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
//            TestActivity.start(this, bundle);
            MainActivity.start(this, bundle);
            finish();
        }
    }


}
