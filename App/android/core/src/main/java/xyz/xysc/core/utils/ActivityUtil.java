package xyz.xysc.core.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Objects;

import xyz.xysc.core.R;

/**
 * @author architect.bian
 * @date 2017-11-22 6:16 PM
 */
public class ActivityUtil {

    /*----------------------------------------各种Intent启动----------------------------------------*/

    public static void startTel(Activity activity, String telNum) {
        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("tel:" + telNum));
        activity.startActivity(i);
    }

    /**
     * 进入系统拍照
     *
     * @param activity
     * @param imageUri 照片输出路径 Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/image.jpg"))
     */
    public static void startCamera(Activity activity, Uri imageUri, int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 进入系统拍照 (输出为Bitmap)<br>
     * <p>
     * 获得输出
     * 在 @<code>onActivityResult</code>中<br>
     * 通过@<code>Bitmap bitmap = (Bitmap)intent.data.getExtras().get("data")</code>获取<br>
     * <p>
     * Tips: 返回的Bitmap并非原图的Bitmap而是经过压缩的Bitmap
     */
    public static void startCamera(Activity activity, int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(intent, requestCode);
        }
    }

    public static void startVideoRecord(Activity activity, Uri imageUri, int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(intent, requestCode);
        }
    }

    public static void startAlbum(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startChooseVideo(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startChooseFile(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startBrowser(Activity activity, String url) {
        Uri uri = Uri.parse(url);
        startUri(activity, uri);
    }

    public static void startUri(Activity activity, Uri uri) {
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        activity.startActivity(i);
    }

    public static void startSMS(Activity activity, String body) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.putExtra("sms_body", body);
        activity.startActivity(i);
    }

    public static void startAPK(Activity activity, File file) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.fromFile(file), FileUtil.getMIMEType(file));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(i);
    }

    public static void startAPKUninstall(Activity activity, String ssp) {
        Uri uninstallUri = Uri.fromParts("package", ssp, null);
        Intent i = new Intent(Intent.ACTION_DELETE);
        activity.startActivity(i);
    }

    public static void startImgChooserForResult(Activity activity, int requestCode) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        Intent chooserIntent = Intent.createChooser(galleryIntent, activity.getResources().getString(R.string.pick_image));
        activity.startActivityForResult(chooserIntent, requestCode);
    }

    /**
     * 进入系统裁剪
     *
     * @param inputUri  需裁剪的图片路径 Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/image.jpg")
     * @param outputUri 裁剪后图片路径 Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/image_cut.jpg")
     * @param width     裁剪后宽度(px)
     * @param height    裁剪后高度(px)
     */
    public static void startActivityForImageCut(Activity activity, int requestCode, Uri inputUri, Uri outputUri, int width, int height) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(inputUri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true); // 去黑边
        intent.putExtra("scaleUpIfNeeded", true); // 去黑边
        // aspectX aspectY 裁剪框宽高比例
        intent.putExtra("aspectX", width); // 输出是X方向的比例
        intent.putExtra("aspectY", height);
        // outputX outputY 输出图片宽高，切忌不要再改动下列数字，会卡死
        intent.putExtra("outputX", width); // 输出X方向的像素
        intent.putExtra("outputY", height);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        intent.putExtra("return-data", false); // 设置为不返回数据
        activity.startActivityForResult(intent, requestCode);
    }

    /*----------------------------------------启动某个activity----------------------------------------*/

    /**
     * 启动某个activity
     *
     * @param context
     * @param clzTarget
     */
    public static void start(Context context, Class<?> clzTarget) {
        start(context, null, clzTarget, -1);
    }

    /**
     * @param context
     * @param clzTarget
     * @param flags     > 0
     */
    public static void start(Context context, Class<?> clzTarget, int flags) {
        start(context, null, clzTarget, flags);
    }

    public static void start(Context context, Bundle bundle, Class<?> clzTarget) {
        start(context, bundle, clzTarget, -1);
    }

    public static void start(Context context, Bundle bundle, Class<?> clzTarget, int flags) {
        Intent i = new Intent(context, clzTarget);
        if (bundle != null) {
            i.putExtras(bundle);
        }
        if (flags > 0) {
            i.setFlags(flags);
        }
        context.startActivity(i);
    }

    public static void startForResult(Activity activity, Class<?> clzTarget, int requestCode) {
        startForResult(activity, clzTarget, requestCode);
    }

    public static void startForResult(Activity activity, Bundle bundle, Class<?> clzTarget, int requestCode) {
        Intent i = new Intent(activity, clzTarget);
        if (bundle != null) {
            i.putExtras(bundle);
        }
        activity.startActivityForResult(i, requestCode);
    }

    /*----------------------------------------Fragment操作----------------------------------------*/

    /**
     * 添加一个fragment到containerID
     *
     * @param activity
     * @param fragment
     * @param containerId
     */
    public static void replaceFragment(@NonNull FragmentActivity activity, @NonNull Fragment fragment, int containerId) {
        Objects.requireNonNull(activity);
        Objects.requireNonNull(fragment);
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
//        transaction.addToBackStack(null);
        transaction.replace(containerId, fragment);
        transaction.commit();
    }

    /**
     * 使用ID获取Fragment对象
     *
     * @param activity
     * @param id
     * @return
     */
    public static Fragment getFragment(@NonNull FragmentActivity activity, @IdRes int id) {
        return activity.getSupportFragmentManager().findFragmentById(id);
    }

}
