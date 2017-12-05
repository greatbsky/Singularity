package com.singularity.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Unique;
import org.joda.time.DateTime;

import xyz.xysc.databinding.base.BaseEntity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author architect.bian
 * @date 2017-12-05 5:06 PM
 */
@Entity
public class Notify extends BaseEntity {

    public static int NotifySystem = 1; //系统通知
    public static int NotifyMsg = 2; //消息通知
    public static int NotifyImportant = 3; //重要通知

    @Id
    private Long id;
    @Index
    private String uid;
    private long userid = 0L;
    private String title;
    private String content;
    private Long createTime = 0L;
    private int type = 0;

    @Generated(hash = 2142308492)
    public Notify(Long id, String uid, long userid, String title, String content,
            Long createTime, int type) {
        this.id = id;
        this.uid = uid;
        this.userid = userid;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.type = type;
    }

    @Generated(hash = 2135566307)
    public Notify() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
