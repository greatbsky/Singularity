package com.singularity.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.singularity.R;

import xyz.xysc.core.base.BaseActivity;
import xyz.xysc.core.utils.ActivityUtil;

import com.singularity.databinding.ActivitySettingBinding;
import com.singularity.vm.SettingModel;

public class SettingActivity extends BaseActivity {

    protected SettingModel viewModel = new SettingModel(this);
    protected ActivitySettingBinding binding;

    @Override
    protected void initView(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        binding.setVm(viewModel);
        binding.include.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(binding.include.toolbar);
    }

    /*----------------------------------------自定义方法----------------------------------------*/

    public static void start(Activity activity) {
        ActivityUtil.start(activity, SettingActivity.class);
    }

    public void test() {
        binding.include.progress.setProgress(10);
//        testAsyncTask();
//        testHandlerSendMsg();
    }

    private void testAsyncTask() {

        new AsyncTask<Void, Integer, Boolean>() {

            @Override
            protected void onPreExecute() {
                ((ProgressBar) ((FrameLayout) findViewById(R.id.include)).findViewById(R.id.progress)).setVisibility(View.VISIBLE);
            }

            protected void onPostExecute(Boolean result) {
                if (result) {
                    //success
                } else {
                    //failed
                }
            }

            protected void onProgressUpdate(Integer... values) {
                ((ProgressBar) ((FrameLayout) findViewById(R.id.include)).findViewById(R.id.progress)).setProgress(values[0]);
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                int p = 0;
                while (true) {
                    p++;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    publishProgress(p);
                    if (p > 99) break;
                }
                return true;
            }
        }.execute();
    }
    public void testHandlerSendMsg() {
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                ((TextView)findViewById(R.id.copyright)).setText(msg.obj.toString());
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.obj = "hi world!";
                handler.sendMessage(msg);
            }
        }).start();
    }
}
