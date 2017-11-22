package com.singularity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    /**
     * 初始化视图
     */
    protected abstract void initView();

    protected void showToast(CharSequence text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    protected void showToast(CharSequence text, int duration) {
        Toast.makeText(this, text, duration).show();
    }
}
