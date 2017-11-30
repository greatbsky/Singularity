package com.singularity.entity;

import android.databinding.Bindable;

import com.singularity.BR;
import xyz.xysc.databinding.base.BaseEntity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Transient;

/**
 * @author architect.bian
 * @date 2017-11-22 6:05 PM
 */
@Entity
public class User extends BaseEntity {

    @NotNull
//    @Unique //没起作用
    private String name;
    @Transient
    private String desc;

    @Id(autoincrement = true)
    private Long id;

    @Generated(hash = 586692638)
    public User() {
    }

    @Generated(hash = 969448858)
    public User(@NotNull String name, Long id) {
        this.name = name;
        this.id = id;
    }

    @Keep
    public User(String name) {
        this.name = name;
    }

    public User(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}