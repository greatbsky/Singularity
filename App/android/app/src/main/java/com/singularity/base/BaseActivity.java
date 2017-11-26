package com.singularity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.singularity.viewmodel.UserModel;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerEventBus();
        initView();
        ((BaseApplication)getApplication()).watcher.watch(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterEventBus();
    }

    /**
     * 注册监听到eventbus
     */
    private void registerEventBus() {
        if (getListener() != null) {
            EventBus.getDefault().unregister(getListener());
        }
    }

    /**
     * 取消注册
     */
    private void unregisterEventBus() {
        if (getListener() != null) {
            EventBus.getDefault().unregister(getListener());
        }
    }

    /**
     * EventBus的Listener
     * @return
     */
    protected Object getListener() {
        return null;
    }

    /**
     * 初始化视图
     */
    protected void initView() {

    }

}
