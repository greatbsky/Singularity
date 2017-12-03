package com.singularity.api;

import com.singularity.entity.User;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author architect.bian
 * @date 2017-12-03 3:39 PM
 */
public interface RootAPI {

    @GET("api/initialize")
    Flowable<Map> initialize();
}
