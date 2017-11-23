package com.singularity.api;

import com.singularity.entity.User;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

/**
 * @author architect.bian
 * @date 2017-11-23 4:56 PM
 */

public interface UserAPI {

    @GET("user/{uid}")
    Call<User> get(@Path("uid") String uid);

    /*
    @Multipart
    @POST("register")
    Call<User> registerUser(@PartMap Map<String, RequestBody> params, @Part("password") RequestBody password);

    RequestBody photo1 = RequestBody.create(MediaType.parse("image/png"), "f1");
    RequestBody photo2 = RequestBody.create(MediaType.parse("image/png"), "f2");
    Map<String, RequestBody> photos = new HashMap<>();
    photos.put("\"对应的key1\"; filename=\"文件名1.png\"", photo1);
    photos.put("\"对应的key2\"; filename=\"文件名2.png\"", photo2);
    photos.put("username",  RequestBody.create(MediaType.parse("text/plain"), "二傻子"));
    Call<User> call = UserAPI.registerUser(photos, RequestBody.create(null, "123456"));
    */

}
