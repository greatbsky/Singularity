package com.singularity.event;

import android.util.Log;

import com.singularity.so.MainSO;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 全局事件处理类
 * @author architect.bian
 * @date 2017-12-03 3:04 PM
 */
public class EventsHandler {

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void initializeDataEventHandler(Events.InitializeDataEvent event) {
        Log.e("EventsHandler", "begin to load the onCreate data ...");
        MainSO.initialize();
    }
}
