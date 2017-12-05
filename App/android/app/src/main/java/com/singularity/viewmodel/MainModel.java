package com.singularity.viewmodel;

import android.databinding.Bindable;

import com.singularity.activity.MainActivity;
import com.singularity.entity.Video;
import com.singularity.service.VideoSO;

import java.util.List;

import xyz.xysc.databinding.base.BaseViewModel;

/**
 * @author architect.bian
 * @date 2017-12-03 2:15 PM
 */
public class MainModel extends BaseViewModel {

    private MainActivity activity;
    private VideoSO videoSO = new VideoSO();

    public VideoItemModel videoVM;

    public MainModel(MainActivity activity) {
        this.activity = activity;
        videoVM = new VideoItemModel(activity);
    }

    /*----------------------------------------自定义方法----------------------------------------*/

    @Bindable
    public List<Video> getList() {
        return videoSO.getList();
    }

}
