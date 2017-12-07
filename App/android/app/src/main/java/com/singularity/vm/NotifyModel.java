package com.singularity.vm;

import android.app.Activity;
import android.databinding.ObservableField;

import com.singularity.activity.TestActivity;
import com.singularity.entity.Notify;
import com.singularity.so.NotifySO;

import xyz.xysc.databinding.base.BaseViewModel;

/**
 * @author architect.bian
 * @date 2017-12-05 5:49 PM
 */
public class NotifyModel extends BaseViewModel {

    public ObservableField<Notify> notify = new ObservableField<>();

    private final Activity activity;

    public NotifyModel(Activity activity) {
        super();
        this.activity = activity;
    }

    public void show(long id) {
        Notify n = NotifySO.get(id);
        notify.set(n);
    }

    public void goMain() {
        TestActivity.start(activity, null);
        activity.finish();
    }
}
