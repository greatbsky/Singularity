package com.singularity.global.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.ObservableField;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.singularity.R;
import com.singularity.global.G;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import xyz.xysc.core.utils.FileUtil;

public class DownLoadService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    /*----------------------------------------自定义方法----------------------------------------*/

    /**
     * 运行唯一的service
     *
     * @param context
     */
    public static synchronized void bind(Context context, ServiceConnection conn, int flags) {
        context.bindService(new Intent(context, DownLoadService.class), conn, flags);
    }

    /*----------------------------------------内部类----------------------------------------*/

    public class Binder extends android.os.Binder {

        private Task task;

        public void start(String url, ObservableField<Integer> progress, ObservableField<File> file) {
            if (url != null) {
                task = new Task(url, progress, file);
                task.execute();
            }
        }

        public void stop() {
            task.state = 0;
        }

        public void cancel() {
            task.state = -1;
            task.doInBackground();
        }
    }

    class Task extends AsyncTask<Void, Integer, Integer> {

        private final String url;
        private final ObservableField<Integer> progress;
        private final ObservableField<File> fileDownload;
        public int state = 1; // 0 stop, 1 start, 2 finished, -1 cancel, -2 error

        public Task(String url, ObservableField<Integer> progress, ObservableField<File> file) {
            this.url = url;
            this.progress = progress;
            this.fileDownload = file;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            InputStream is = null;
            RandomAccessFile savedFile = null;
            File file = null;
            try {
                long downloadedLength = 0; // 记录已下载的文件长度
                String fileName = this.url.substring(this.url.lastIndexOf("/"));
                String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                file = new File(directory + fileName);
                fileDownload.set(file);
                if (file.exists()) {
                    downloadedLength = file.length();
                    if (state == -1) { //取消
                        this.progress.set(0);
                        file.delete();
                    }
                }
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        // 断点下载，指定从哪个字节开始下载
                        .addHeader("RANGE", "bytes=" + downloadedLength + "-")
                        .url(this.url)
                        .build();
                Response response = client.newCall(request).execute();
                if (response != null) {
                    is = response.body().byteStream();
                    long contentLength = response.body().contentLength();
                    savedFile = new RandomAccessFile(file, "rw");
                    savedFile.seek(downloadedLength); // 跳过已下载的字节
                    byte[] b = new byte[1024];
                    int total = 0;
                    int len;
                    while ((len = is.read(b)) != -1) {
                        if (state == -1) { //取消
                            return state;
                        } else if (state == 0) { //停止
                            return state;
                        } else {
                            total += len;
                            savedFile.write(b, 0, len);
                            // 计算已下载的百分比
                            int progress = (int) ((total + downloadedLength) * 100 / contentLength);
                            publishProgress(progress);
                        }
                    }
                    response.body().close();
                    return state = 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (savedFile != null) {
                        savedFile.close();
                    }
                    if (state == -1 && file != null) {
                        file.delete();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return state = -2;
        }

        @Override
        protected void onPreExecute() {
            //发送下载通知
        }

        protected void onPostExecute(Boolean result) {
            if (result) {
                //发送成功通知
            } else {
                //发送失败通知
            }
        }

        protected void onProgressUpdate(Integer... values) {
            this.progress.set(values[0]);
        }
    }

}
