package com.singularity.activity.video;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.singularity.R;
import com.singularity.databinding.FragmentVideoBinding;
import com.singularity.viewmodel.VideoModel;

import xyz.xysc.core.base.BaseFragment;

/**
 * @author architect.bian
 * @date 2017-12-03 4:55 PM
 */
public class VideoFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentVideoBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_video, container, false);
        binding.setVm(new VideoModel(this.getActivity()));
        return binding.getRoot();
    }
}
