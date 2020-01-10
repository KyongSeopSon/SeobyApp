package com.aos.seobyapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingInterface extends FirebaseMessagingService {

    private static final String TAG = "MainActivity";

    @Override
    public void onNewToken(String _token) {
        super.onNewToken(_token);
        Log.e("Firebase", "FirebaseInstanceIDService : " + _token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage != null) {
            if ((remoteMessage.getData() != null) || (remoteMessage.getNotification() != null)) {
                sendNotification(remoteMessage);
            }
        }

    }

    private void sendNotification(RemoteMessage remoteMessage) {
        String title = "";
        String message = "";

        if ((remoteMessage.getData() != null) && (remoteMessage.getData().size() > 0)) {
            title = remoteMessage.getData().get("title");
            message = remoteMessage.getData().get("body");
        } else if (remoteMessage.getNotification() != null) {
            title = remoteMessage.getNotification().getTitle();
            message = remoteMessage.getNotification().getBody();
        }

        if (message != "") {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                String channel = "dsc_alarm";
                String channel_nm = "DSC 푸시알림";

                NotificationManager notichannel = (android.app.NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationChannel channelMessage = new NotificationChannel(channel, channel_nm,
                        android.app.NotificationManager.IMPORTANCE_DEFAULT);
                channelMessage.setDescription("DSC 푸시알림 채널");
                channelMessage.enableLights(true);
                channelMessage.enableVibration(true);
                channelMessage.setShowBadge(false);
                channelMessage.setVibrationPattern(new long[]{100, 200, 100, 200});
                notichannel.createNotificationChannel(channelMessage);

                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(this, channel)
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentTitle(title)
                                .setContentText(message)
                                .setChannelId(channel)
                                .setAutoCancel(true)
                                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(9999, notificationBuilder.build());

            } else {
                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(this, "")
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentTitle(title)
                                .setContentText(message)
                                .setAutoCancel(true)
                                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(9999, notificationBuilder.build());

            }

        }
    }
}
