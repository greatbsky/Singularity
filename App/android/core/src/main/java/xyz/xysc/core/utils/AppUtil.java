package xyz.xysc.core.utils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.NotificationCompat;

import java.util.HashMap;
import java.util.Map;

/**
 * @author architect.bian
 * @date 2017-11-30 11:44 AM
 */

public class AppUtil {

    /**
     * 验证是否有权限
     *
     * @param context
     * @param permission
     * @return
     */
    public static boolean checkPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 读取联系人
     *
     * @param context
     * @return
     */
    public static Map<String, String> readContacts(Context context) {
        Map<String, String> map = new HashMap<>();
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                map.put(name, number);
            }
        }
        return map;
    }


    /**
     * 发送通知
     *
     * @param context
     * @param id
     * @param title
     * @param content
     * @param icon
     */
    public static void sendNotification(Context context, int id, String title, String content, int icon, PendingIntent intent) {
        sendNotification(context, id, title, content, icon, intent, NotificationCompat.PRIORITY_DEFAULT,false);
    }

    /**
     * 发送通知
     *
     * @param context
     * @param id
     * @param title
     * @param content
     * @param icon
     */
    public static void sendNotification(Context context, int id, String title, String content, int icon, PendingIntent intent, int priority, boolean vibrate) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = getNotificationBuilder(context, title, content, icon, intent, -1, priority, vibrate);
        manager.notify(id, builder.build());
    }

    /**
     * 获取Notification
     * @param context
     * @param title
     * @param icon
     * @param progress
     * @param intent
     * @return
     */
    public Notification getNotification(Context context, String title, String content, int icon, int progress, PendingIntent intent) {
        NotificationCompat.Builder builder = getNotificationBuilder(context, title, content, icon, intent, progress, NotificationCompat.PRIORITY_DEFAULT, false);
        return builder.build();
    }
    /**
     * 获取NotificationCompat.Builder
     * @param context
     * @param title
     * @param content
     * @param icon
     * @param intent
     * @param progress
     * @param priority
     * @param vibrate
     * @return
     */
    public static NotificationCompat.Builder getNotificationBuilder(Context context, String title, String content, int icon, PendingIntent intent,int progress, int priority, boolean vibrate) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(icon)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), icon))
                .setContentIntent(intent)
                .setPriority(priority)
                .setAutoCancel(true);
        if (progress >= 0) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        if (content != null && content.length() > 0) {
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(content));
        }
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        if (vibrate) {
            builder.setVibrate(new long[]{0, 1000, 1000, 1000});
        }
        return builder;
    }

}
