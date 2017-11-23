package com.singularity.entity;

import android.databinding.Bindable;

import com.singularity.BR;
import com.singularity.base.BaseEntity;

/**
 * @author architect.bian
 * @date 2017-11-22 6:05 PM
 */

public class User extends BaseEntity {

    private String name;
    private String desc;

    public User(String name, String desc) {
        this.name = name;
        this.desc = desc;
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