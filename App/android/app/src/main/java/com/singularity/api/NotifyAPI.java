package com.singularity.api;

import com.singularity.entity.Notify;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author architect.bian
 * @date 2017-12-05 5:05 PM
 */
public interface NotifyAPI {

    @GET("notify/{userid}/{lastTime}")
    Call<List<Notify>> get(@Path("userid") long userid, @Path("lastTime") long lastTime); //取lastTime之后的Notify

}
