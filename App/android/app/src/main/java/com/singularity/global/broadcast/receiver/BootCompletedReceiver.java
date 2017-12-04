package com.singularity.global.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.singularity.activity.MainActivity;
import com.singularity.activity.SplashActivity;
import com.singularity.global.G;

import xyz.xysc.core.utils.ActivityUtil;

public class BootCompletedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        G.showToast("启动完成！");
        ActivityUtil.start(context, MainActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK);
    }
}
