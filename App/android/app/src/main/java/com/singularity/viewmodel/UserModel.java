package com.singularity.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;

import com.singularity.base.BaseViewModel;
import com.singularity.entity.User;
import com.singularity.service.UserSO;

/**
 * @author architect.bian
 * @date 2017-11-23 1:25 PM
 */

public class UserModel extends BaseViewModel {

    public final ObservableField<User> user = new ObservableField<>();
    private UserSO userSO = new UserSO();

    public UserModel(Context context) {
        super(context);
    }

    public UserModel(Context context, String name, String desc) {
        super(context);
//        user = new User(name, desc);
    }

    public void getUser() {
        userSO.get("uid0000001", user);
    }

    public void changeName() {
//        user.setName("New Name");
        user.get().setName("New Name");
    }

    public void click() {
        showToast("U click me");
    }
}
