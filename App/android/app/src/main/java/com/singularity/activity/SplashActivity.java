package com.singularity.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.singularity.R;

import xyz.xysc.core.base.BaseActivity;

import com.singularity.conf.AppConf;
import com.singularity.databinding.ActivitySplashBinding;
import com.singularity.event.Events;
import com.singularity.global.G;

import xyz.xysc.core.utils.JsonUtil;
import xyz.xysc.core.utils.NetworkUtil;

import com.singularity.viewmodel.SplashModel;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends BaseActivity {

    protected SplashModel viewModel = new SplashModel(this);
    private ActivitySplashBinding binding;
    private boolean showMain = false;
    private int requestCodePerms = 1;
    private int requestCodeSetting = 2;
    private int showRationaleCount = 0; //弹窗引导设置权限显示次数
    private boolean requestingPermissions = false;

    @Override
    protected void initView(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        binding.setVm(viewModel);

        requestPermissionsHandler(false); //TODO 第一次安装时执行
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NetworkUtil.isConnected(this)) {
            G.showToast(R.string.no_network);
        } else {
            Events.post(new Events.InitializeDataEvent()); //触发事件加载数据
        }
        if (!this.requestingPermissions) {
            goWhere();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (requestCode == requestCodePerms) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    // 判断用户是否点击了不再提醒。(检测该权限是否还可以申请)
                    if (shouldShowRequestPermissionRationale(permissions[0])) {
                        // 用户还是想用我的 APP 的提示用户去应用设置界面手动开启权限
                        if (showRationaleCount == 0) {
                            requestPermissionsHandler(true);
                        }
                    } else {
                        //showDialogToAppSettting();
                    }
                } else {
                    this.requestingPermissions = false;
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == requestCodeSetting) {
            final List<String> requestPerms = getPermissionsToRequest();
            if (requestPerms.size() > 0) {
//                finish();
            }
        }
    }

    /*----------------------------------------自定义方法----------------------------------------*/

    /**
     * 跳转
     */
    private void goWhere() {
        Intent intent = getIntent();
        if (intent.getData() == null) { //正常启动
            binding.getRoot().postDelayed(new Runnable() {
                @Override
                public void run() {
                    goMain();
                }
            }, 2000);
        } else {
            Uri uri = intent.getData();
            goMain(uri);
        }

    }

    public synchronized void goMain() {
        goMain(null);
    }

    private void requestPermissionsHandler(boolean showRationale) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //系统大于23 时，才有必要去判断权限是否获取
            final List<String> requestPerms = getPermissionsToRequest();
            if (requestPerms.size() > 0) {
                if (showRationale) {
                    showRationaleCount++;
                    G.createAlertBuilder(this, R.string.request_permission_title, R.string.request_permission_message)
                            .setPositiveButton(R.string.setting_permission, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(requestPerms.toArray(new String[0]), requestCodePerms);
                                    }
                                }
                            }).show();
                } else {
                    requestPermissions(requestPerms.toArray(new String[0]), requestCodePerms);
                }
                this.requestingPermissions = true;
            }
        }
    }

    @NonNull
    private List<String> getPermissionsToRequest() {
        final List<String> requestPerms = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String p : AppConf.allPermissions) {
                int i = checkSelfPermission(p);
                if (i != PackageManager.PERMISSION_GRANTED) {
                    requestPerms.add(p);
                }
            }
        }
        return requestPerms;
    }

    private void showDialogToAppSettting() {
        G.createAlertBuilder(this, R.string.request_permission_title, R.string.setting_permission_to_appsetting)
                .setPositiveButton(R.string.goto_setting, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivityForResult(intent, requestCodeSetting);
                        }
                    }
                }).show();
    }

    public synchronized void goMain(Uri uri) { //TODO do sth with uri
        if (!showMain) {
            showMain = true;
            Bundle bundle = new Bundle();
            bundle.putString("data", JsonUtil.toJson(uri));
            TestActivity.start(this, bundle);
//            MainActivity.start(this, bundle);
            finish();
        }
    }


}
