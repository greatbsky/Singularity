package com.singularity.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;

import com.singularity.R;
import com.singularity.conf.AppConf;
import com.singularity.global.G;

import java.util.ArrayList;
import java.util.List;

/**
 * @author architect.bian
 * @date 2017-11-30 8:05 PM
 */

public class BaseActivity extends xyz.xysc.core.base.BaseActivity {

    private int showRationaleCount = 0; //弹窗引导设置权限显示次数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ((BaseApplication)getApplication()).watcher.watch(this);
    }

    /*----------------------------------------自定义方法----------------------------------------*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                // 判断用户是否点击了不再提醒。(检测该权限是否还可以申请)
                if (shouldShowRequestPermissionRationale(permissions[0])) {
                    // 用户还是想用我的 APP 的提示用户去应用设置界面手动开启权限
                    if (showRationaleCount == 0) { //仅请求一次
                        requestPermissionsHandler(requestCode, true, getPermissionsToRequest(AppConf.allPermissions));
                    }
                } else {
                    showDialogToAppSettting();
                }
            } else {
                onGrantedPermissions(requestCode, permissions, grantResults);
            }
        }
    }

    /**
     * 请求权限
     *
     * @param showRationale
     * @param requestPerms
     */
    protected void requestPermissionsHandler(final int requestCode, boolean showRationale, final String[] requestPerms) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //系统大于23 时，才有必要去判断权限是否获取
            if (requestPerms.length > 0) {
                if (showRationale) {
                    showRationaleCount++;
                    G.createAlertBuilder(this, R.string.request_permission_title, R.string.request_permission_message)
                            .setPositiveButton(R.string.setting_permission, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(requestPerms, requestCode);
                                    }
                                }
                            }).show();
                } else {
                    requestPermissions(requestPerms, requestCode);
                }
                onRequestingPermissions();
            }
        }
    }

    @NonNull
    protected String[] getPermissionsToRequest(String[] permissions) {
        final List<String> requestPerms = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String p : permissions) {
                int i = checkSelfPermission(p);
                if (i != PackageManager.PERMISSION_GRANTED) {
                    requestPerms.add(p);
                }
            }
        }
        return requestPerms.toArray(new String[]{});
    }

    /**
     * 去设置界面设置权限
     */
    protected void showDialogToAppSettting() {
        G.createAlertBuilder(this, R.string.request_permission_title, R.string.setting_permission_to_appsetting)
                .setPositiveButton(R.string.goto_setting, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivityForResult(intent, rcSetting);
                        }
                    }
                }).show();
    }

    /**
     * 正在请求权限
     */
    protected void onRequestingPermissions() {
    }

    /**
     * 已获得权限
     */
    protected void onGrantedPermissions(int requestCode, String[] permissions, int[] grantResults) {
    }

}
