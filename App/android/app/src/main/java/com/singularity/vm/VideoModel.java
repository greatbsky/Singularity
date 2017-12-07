package com.singularity.vm;

import android.databinding.Bindable;
import android.support.v4.app.FragmentActivity;

import com.singularity.entity.Video;
import com.singularity.so.VideoSO;

import java.util.List;

import xyz.xysc.databinding.base.BaseViewModel;

/**
 * @author architect.bian
 * @date 2017-12-03 2:15 PM
 */
public class VideoModel extends BaseViewModel {

    private FragmentActivity activity;
    private VideoSO videoSO = new VideoSO();

    public VideoItemModel videoVM;

    public VideoModel(FragmentActivity activity) {
        this.activity = activity;
        videoVM = new VideoItemModel(activity);
    }

    @Bindable
    public List<Video> getList() {
        return videoSO.getList();
    }

}
