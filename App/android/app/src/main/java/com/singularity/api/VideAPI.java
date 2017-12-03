package com.singularity.api;

import com.singularity.entity.User;
import com.singularity.entity.Video;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author architect.bian
 * @date 2017-12-03 3:35 PM
 */

public interface VideAPI {

    @GET("video/{uid}")
    Flowable<Video> get(@Path("uid") String uid);
}
