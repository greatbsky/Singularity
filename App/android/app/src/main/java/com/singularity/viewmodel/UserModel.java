package com.singularity.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;

import com.singularity.base.BaseViewModel;
import com.singularity.entity.User;
import com.singularity.event.Events;
import com.singularity.service.UserSO;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author architect.bian
 * @date 2017-11-23 1:25 PM
 */

public class UserModel extends BaseViewModel {

    public final ObservableField<User> user = new ObservableField<>();
    public final ObservableField<String> daotxt = new ObservableField<>();
    public final ObservableField<String> notifyContent = new ObservableField<>();

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

    public void click(View view) {
        showToast("U click me");
    }

    public void notifyHandler() {
        EventBus.getDefault().post(new Events.NotifyEvent("title...", "contents...."));
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void notifyEventHandler(Events.NotifyEvent event) {
        notifyContent.set(event.content);
    }

    public void userAdd() {
        daotxt.set(String.valueOf(userSO.add("lucy")));
    }

    public void userGet() {
        User u = userSO.get(Long.parseLong(daotxt.get()));
        daotxt.set(u.getName());
    }
}
