package com.singularity.global.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BatteryReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BATTERY_OKAY.equals(intent.getAction())) {
//            Toast.makeText(context, "电量已恢复，可以使用!", Toast.LENGTH_LONG).show();
        }
        if (Intent.ACTION_BATTERY_LOW.equals(intent.getAction())) {
//            Toast.makeText(context, "电量过低，请尽快充电！", Toast.LENGTH_LONG).show();
        }
        if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
            //TODO 充电时下载安装包，上传设备数据
//            Bundle bundle = intent.getExtras();
//            // 获取当前电量
//            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
//            // 获取总电量
//            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            //float batteryPct = level / (float)scale;

        }

    }
}
