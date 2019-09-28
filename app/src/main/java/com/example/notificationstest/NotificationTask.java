package com.example.notificationstest;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import static com.example.notificationstest.R.drawable.dream;

public class NotificationTask {
    public static final String CHANNEL_ID ="vn.edu.csc.notificationapp" ;
    public static final String CHANNEL_NAME = "notificationapp";

    public static void createNotificationChannel(Context context) {
        /* 1 - check OS API level >= 26 */
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // 2 - Create notification channel include necessary info
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel =
                    new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription("Reminders");
            channel.setShowBadge(true);
            // 3 – Create notification channel using NotificationManager
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.createNotificationChannel(channel);
        }
    }

    public static void createSampleNotification(Context context, int notificationId, int icon, String title, String message) {

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.dream);

        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 1 – Create notification with channel id include necessary info
            builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(icon)
                    .setContentTitle(title)
                    .setContentText(message);

        } else {
            // 2 – create notification in OS API level < 26 case.
            builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(icon)
                    .setContentTitle(title)
                    .setContentText(message);
        }
        // 3 (step 4) – send notification to NotificationManager and show
        NotificationManager mNotificationManager =
                (NotificationManager)
                        context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notificationId, builder.build());
    }

    public static void createSampleNotificationToActivity(Context context, int notificationId, int icon, String title, String message, String data) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.dream);

        NotificationCompat.Builder builder;
        Intent intent = new Intent(context, Main2Activity.class);
        intent.putExtra("data", data);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                                                        10,
                                                                intent,
                                                                PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 1 – Create notification with channel id include necessary info
            builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(icon)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigPictureStyle()
                            .bigPicture(bitmap))
                    .setContentIntent(pendingIntent);
        } else {
            // 2 – create notification in OS API level < 26 case.
            builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(icon)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigPictureStyle()
                            .bigPicture(bitmap))
                    .setContentIntent(pendingIntent);
        }
        // 3 (step 4) – send notification to NotificationManager and show
        NotificationManager mNotificationManager =
                (NotificationManager)
                        context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notificationId, builder.build());
    }

}
