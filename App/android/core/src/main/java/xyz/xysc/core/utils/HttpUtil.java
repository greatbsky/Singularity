package xyz.xysc.core.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author architect.bian
 * @date 2017-11-23 7:37 PM
 */

public class HttpUtil {

    public static final MediaType JsonType = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient client = null;

    private OkHttpClient getClient() {
        if (client == null) {
            client = new OkHttpClient();
        }
        return client;
    }

    /**
     * 通过url及参数发送get请求
     *
     * @param url
     * @return
     */
    public Response get(String url) {
        Request request = new Request.Builder().url(url).build();

        Response response = null;
        try {
            response = getClient().newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 发送Post请求
     *
     * @param url
     * @param json
     * @return
     */
    public Response post(String url, String json) {
        RequestBody body = RequestBody.create(JsonType, json);
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 发送get异步请求
     *
     * @param url
     * @param cbk
     */
    public void get(String url, Callback cbk) {
        Request request = new Request.Builder().url(url).build();
        getClient().newCall(request).enqueue(cbk);
    }

    /**
     * 发送post异步请求
     *
     * @param url
     * @param json
     * @param cbk
     */
    public void post(String url, String json, Callback cbk) {
        RequestBody body = RequestBody.create(JsonType, json);
        Request request = new Request.Builder().url(url).post(body).build();
        getClient().newCall(request).enqueue(cbk);
    }

    public void upload(String url, byte[] fileContent, Callback cbk) {
        RequestBody body = RequestBody.create(MediaType.parse("application/octet-stream"), fileContent);
        Request request = new Request.Builder().url(url).post(body).build();
        getClient().newCall(request).enqueue(cbk);
    }

    /**
     * 异步上传表单
     *
     * @param url
     * @param parts
     * @param cbk
     */
    public void upload(String url, List<MultipartBody.Part> parts, Callback cbk) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (int i = 0; i < parts.size(); i++) {
            builder.addPart(parts.get(i)); //addFormDataPart("image", "logo-square.png", RequestBody.create(MEDIA_TYPE_PNG, file))
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        getClient().newCall(request).enqueue(cbk);
    }


    /**
     * 下載文件到某个路径
     * @param url
     * @param path
     */
    public void download(String url, final String path) {
        Request request = new Request.Builder().url(url).build();
        getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();
                    File file = new File(path);
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                } catch (IOException e) {
                } finally {
                    try {
                        if (is != null) is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null) fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }
}
