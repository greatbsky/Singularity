package xyz.xysc.core.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import java.io.FileNotFoundException;

/**
 * @author architect.bian
 * @date 2017-12-01 5:57 PM
 */
public class UriUtil {

    public static String getImagePath(Context context, Uri uri) {
        String path = null;
        Log.d("UriUtil:", "" + uri);
        if (DocumentsContract.isDocumentUri(context, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            Log.d("UriUtil", "getDocumentId(uri) :" + docId);
            Log.d("UriUtil", "uri.getAuthority() :" + uri.getAuthority());
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                path = queryImg(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri u = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                path = queryImg(context, u, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            path = queryImg(context, uri, null);
        }
        return path;
    }

//    public static String getVideoPath(Context context, Uri uri) {
//        String path = null;
//        Log.d("UriUtil:", "" + uri);
//        if (DocumentsContract.isDocumentUri(context, uri)) {
//            String docId = DocumentsContract.getDocumentId(uri);
//            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
//                String id = docId.split(":")[1];
//                String selection = MediaStore.Video.Media._ID + "=" + id;
//                path = queryVideo(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
//            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
//                Uri u = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
//                path = queryVideo(context, u, null);
//            }
//        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
//            path = queryVideo(context, uri, null);
//        } else {
//            path = uri.toString();
//        }
//        return path;
//    }

    /**
     * 返回图片的路径
     * @param context
     * @param uri
     * @param selection
     * @return
     */
    public static String queryImg(Context context, Uri uri, String selection) {
        return query(context, uri, selection, MediaStore.Images.Media.DATA);
    }

    /**
     * 返回视频的路径
     * @param context
     * @param uri
     * @param selection
     * @return
     */
    public static String queryVideo(Context context, Uri uri, String selection) {
        return query(context, uri, selection, MediaStore.Video.Media.DATA);
    }

    public static String query(Context context, Uri uri, String selection, String field) {
        String path = null;
        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null); //内容提供器
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(field)); //获取路径
            }
        }
        cursor.close();
        return path;
    }

    public static Bitmap getBitmap(Context context, Uri uri) {
        try {
            return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
