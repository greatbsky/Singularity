package com.singularity.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import com.singularity.activity.MainActivity;
import com.singularity.base.BaseViewModel;
import com.singularity.entity.User;
import com.singularity.event.Events;
import com.singularity.global.G;
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
    public final ObservableField<String> imgSrc = new ObservableField<>("http://www.imgup.bid/images/2017/10/29/4232422e8314489c0bb668a9b6fd1be9.jpg");

    private MainActivity activity = null;

    private UserSO userSO = new UserSO();

    public UserModel(MainActivity activity) {
        this.activity = activity;
    }

    public void getUser() {
        userSO.get("uid0000001", user);
    }

    public void changeName() {
//        user.setName("New Name");
        user.get().setName("New Name");
    }

    public void click(View view) {
        G.showToast("U click me");
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
        imgSrc.set("https://n.sinaimg.cn/ent/4_img/upload/1f0ce517/20171024/0uxJ-fymzzpw0580729.jpg");
    }

    public void userGet() {
        User u = userSO.get(Long.parseLong(daotxt.get()));
        daotxt.set(u.getName());
    }

}
