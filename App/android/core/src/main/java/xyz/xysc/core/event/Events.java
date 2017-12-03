package xyz.xysc.core.event;

import org.greenrobot.eventbus.EventBus;

import xyz.xysc.core.base.BaseEvent;

/**
 * @author architect.bian
 * @date 2017-12-03 3:09 PM
 */

public class Events {

    /**
     * 注册监听到eventbus
     */
    public static void registerEventBus(Object listener) {
        if (listener != null) {
            EventBus.getDefault().register(listener);
        }
    }

    /**
     * 取消注册
     * @param listener
     */
    public static void unregisterEventBus(Object listener) {
        if (listener != null) {
            EventBus.getDefault().unregister(listener);
        }
    }

    /**
     * 触发事件
     * @param event
     */
    public static void post(Object event) {
        EventBus.getDefault().post(event);
    }

    /**
     * 通知事件
     */
    public static class NotifyEvent extends BaseEvent {
        public String title;
        public String content;

        public NotifyEvent(String title, String content) {
            this.title = title;
            this.content = content;
        }
    }

    /**
     * 打开APP第一次加载初始数据事件
     */
    public static class InitializeDataEvent extends BaseEvent {}
}
