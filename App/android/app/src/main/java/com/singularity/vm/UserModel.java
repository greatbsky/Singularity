package com.singularity.vm;

import android.Manifest;
import android.content.DialogInterface;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.singularity.R;
import com.singularity.activity.DynamicActivity;
import com.singularity.activity.LoginActivity;
import com.singularity.activity.SettingActivity;
import com.singularity.activity.TestActivity;
import com.singularity.entity.User;
import com.singularity.event.Events;
import com.singularity.global.G;
import com.singularity.global.service.DownLoadService;
import com.singularity.global.service.ForeverService;
import com.singularity.so.UserSO;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import xyz.xysc.core.global.ActivityHistory;
import xyz.xysc.core.utils.ActivityUtil;
import xyz.xysc.databinding.base.BaseViewModel;

/**
 * @author architect.bian
 * @date 2017-11-23 1:25 PM
 */

public class UserModel extends BaseViewModel {

    public final ObservableField<User> user = new ObservableField<>();
    public final ObservableField<String> daotxt = new ObservableField<>();
    public final ObservableField<String> notifyContent = new ObservableField<>();
    public final ObservableField<String> imgSrc = new ObservableField<>("http://www.imgup.bid/images/2017/10/29/4232422e8314489c0bb668a9b6fd1be9.jpg");
    public final ObservableField<Integer> progress = new ObservableField<>();
    public final ObservableField<File> file = new ObservableField<>();

    private TestActivity activity = null;
    public DownLoadService.Binder binder;

    public UserModel(TestActivity activity) {
        this.activity = activity;
    }

    public void getUser() {
        UserSO.get("uid0000001", user);
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
        daotxt.set(String.valueOf(UserSO.add("lucy")));
        imgSrc.set("https://n.sinaimg.cn/ent/4_img/upload/1f0ce517/20171024/0uxJ-fymzzpw0580729.jpg");
    }

    public void userGet() {
        User u = UserSO.get(Long.parseLong(daotxt.get()));
        if (!daotxt.get().equals(u.getName())) {
            daotxt.set(u.getName());
        }
    }

    public void goSetting(View v) {
        SettingActivity.start(activity);
    }

    public void alert(View view) {
        G.alert(activity, R.string.new_msgs, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e("Alert", "onClick: " + which);
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e("Alert", "onClick: " + which);
            }
        });
    }

    public void relogin(View v) {
        ActivityHistory.finishAll();
        ActivityUtil.start(this.activity, LoginActivity.class);
    }

    public void startService(View v) {
        ForeverService.start(activity);
    }

    public void takePhoto(View v) {
        DynamicActivity.start(activity);
    }

    public void startDownload(View v) {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        activity.runWithPermissions(new Runnable() {
            @Override
            public void run() {
                binder.start("http://gdown.baidu.com/data/wisegame/fc5895c68ec02729/weibo_3516.apk", progress, file);
                progress.addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {
                        Log.d("onPropertyChanged", "sender:" + ((ObservableField<Integer>)sender).get());
                        if (((ObservableField<Integer>)sender).get() == 100) {
                            ActivityUtil.startAPK(activity, file.get());
                        }
                    }
                });
            }
        }, permissions);
    }

    public void stopDownload(View v) {
        binder.stop();
    }

    public void cancelDownload(View v) {
        binder.cancel();
    }
}
