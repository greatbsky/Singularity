package com.singularity.conf;

import android.Manifest;

/**
 * App本身的配置
 * @author architect.bian
 * @date 2017-11-23 5:11 PM
 */
public class AppConf {

    /*
    APP需要的所有动态权限
     */
    public static String[] allPermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.PROCESS_OUTGOING_CALLS,
            Manifest.permission.CALL_PHONE,
//            Manifest.permission.READ_PHONE_NUMBERS,
//            Manifest.permission.ANSWER_PHONE_CALLS, //Android O
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.CAMERA,
    };
}
