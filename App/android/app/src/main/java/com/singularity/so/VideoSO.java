package com.singularity.so;

import com.singularity.entity.Video;
import com.singularity.global.G;

import java.util.List;

import xyz.xysc.core.base.BaseSO;

/**
 * @author architect.bian
 * @date 2017-12-03 3:33 PM
 */

public class VideoSO extends BaseSO {

    public List<Video> getList() {
        return G.getDaoSession().getVideoDao().loadAll();
    }
}
