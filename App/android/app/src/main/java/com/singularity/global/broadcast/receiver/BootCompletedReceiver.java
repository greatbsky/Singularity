package com.singularity.global.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.singularity.global.G;
import com.singularity.global.service.ForeverService;

public class BootCompletedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        G.showToast("启动完成！");
        ForeverService.start(context);

//        ActivityUtil.start(context, MainActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK);
//        Intent i = new Intent(context, NotifyDetailActivity.class);
//        PendingIntent pi = PendingIntent.getActivity(context, 0, i, 0);
//        AppUtil.sendNotification(activity, id, title, content, android.R.mipmap.sym_def_app_icon, intent);
    }
}
