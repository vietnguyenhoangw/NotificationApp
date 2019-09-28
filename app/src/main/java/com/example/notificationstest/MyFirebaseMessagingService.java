package com.example.notificationstest;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        /* context is a service */
//        NotificationTask.createSampleNotification(this,
//                10, R.drawable.ic_launcher_background,
//                remoteMessage.getNotification().getTitle(),
//                remoteMessage.getNotification().getBody());

        NotificationTask.createSampleNotificationToActivity(this, 10,
                R.drawable.ic_launcher_foreground,
                remoteMessage.getNotification().getTitle(),
                remoteMessage.getNotification().getBody(),
                remoteMessage.getData().get("promotion"));
                /* we create key promotion */
    }
}
