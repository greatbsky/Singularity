package com.singularity.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.singularity.R;
import com.singularity.base.BaseActivity;
import com.singularity.databinding.ActivityDynamicBinding;
import com.singularity.global.G;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import xyz.xysc.core.utils.ActivityUtil;
import xyz.xysc.core.utils.AppUtil;
import xyz.xysc.core.utils.FileUtil;
import xyz.xysc.core.utils.UriUtil;

public class DynamicActivity extends BaseActivity {

    private ActivityDynamicBinding binding;
    private Uri imageUri;
    private Uri videoUri;
    private int rcTakePhoto = 0;
    private int rcChoosePhoto = 1;
    private int rcVideoRecord = 2;

    @Override
    protected void initView(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dynamic);
        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordVideo();
            }
        });
        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                if (getPermissionsToRequest(permissions).length == 0) {
                    openAlbum();
                } else {
                    requestPermissionsHandler(rcPerms, false, permissions);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == rcTakePhoto && resultCode == RESULT_OK) {
            Bitmap bitmap = null;
            bitmap = UriUtil.getBitmap(this, imageUri);
            binding.picture.setImageBitmap(bitmap);
            binding.btn1.setText(imageUri.toString());
        } else if (requestCode == rcVideoRecord && resultCode == RESULT_OK) {
            binding.video.setVideoPath(data.getData().toString());
            binding.video.start();
        } else if (requestCode == rcChoosePhoto && resultCode == RESULT_OK) {
            displayImage(UriUtil.getImagePath(this, data.getData()));
        }
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitImage = BitmapFactory.decodeFile(imagePath);
            binding.picture.setImageBitmap(bitImage);
        } else {
            G.showToast(R.string.err_get_img);
        }
    }

    @Override
    protected void onRequestingPermissions() {
    }

    @Override
    protected void onGrantedPermissions(int requestCode, String[] permissions, int[] grantResults) {
        openAlbum();
    }

    /*----------------------------------------自定义方法----------------------------------------*/

    private void takePhoto() {
        File out = FileUtil.getExternalCacheFile(this, "out.jpg");
        if (out != null) {
            if (Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.M) {
                imageUri = FileProvider.getUriForFile(this, "com.singularity.fileprovider", out);
            } else {
                imageUri = Uri.fromFile(out);
            }
            ActivityUtil.startCamera(this, imageUri, rcTakePhoto);
        }
    }

    private void recordVideo() {
        File out = FileUtil.getExternalCacheFile(this, "out.mp4");
        if (out != null) {
            if (Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.M) {
                videoUri = FileProvider.getUriForFile(this, "com.singularity.fileprovider", out);
            } else {
                videoUri = Uri.fromFile(out);
            }
            ActivityUtil.startVideoRecord(this, videoUri, rcVideoRecord);
        }
    }


    private void openAlbum() {
        ActivityUtil.startAlbum(this, rcChoosePhoto);
    }

    public static void start(Activity activity) {
        ActivityUtil.start(activity, null, DynamicActivity.class);
    }

}
