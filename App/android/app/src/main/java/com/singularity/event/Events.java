package com.singularity.event;

import xyz.xysc.core.base.BaseEvent;

/**
 * @author architect.bian
 * @date 2017-11-25 3:59 PM
 */

public class Events {

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
}
