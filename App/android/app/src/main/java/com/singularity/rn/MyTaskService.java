package com.singularity.rn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.react.HeadlessJsTaskService;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;

import javax.annotation.Nullable;

import xyz.xysc.core.utils.AppUtil;

/**
 * @author architect.bian
 * @date 2017-12-11 9:16 PM
 */
public class MyTaskService extends HeadlessJsTaskService {

    @Override
    protected @Nullable HeadlessJsTaskConfig getTaskConfig(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            return new HeadlessJsTaskConfig("MyTask", Arguments.fromBundle(extras), 5000,false);
        }
        return null;
    }

    /**
     * 后台发送数据到JS端
     * @param context
     * @param bundle
     */
    public static void start(Context context, Bundle bundle) {
        if (!AppUtil.isAppOnForeground(context)) {
            Intent service = new Intent(context, MyTaskService.class);
            service.putExtras(bundle);
            context.startService(service);
            HeadlessJsTaskService.acquireWakeLockNow(context);
        }
    }
}