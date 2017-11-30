package com.singularity.base;

import android.os.Bundle;

/**
 * @author architect.bian
 * @date 2017-11-30 8:05 PM
 */

public class BaseActivity extends xyz.xysc.core.base.BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseApplication)getApplication()).watcher.watch(this);
    }
}
