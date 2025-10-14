package com.example.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class NotificationHelper {

    public static final String CHANNEL_ID_BIGTEXT = "Bigtext-channel";
    public static final String CHANNEL_ID_PICTURE = "Picture-channel";
    public static final String CHANNEL_ID_INBOX = "Inbox-channel";

    public static final String CHANNEL_NAME = "Kanal Powiadomien";

    public static void createNotificationChannels(Context context){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            NotificationChannel channelLow = new NotificationChannel(CHANNEL_ID_BIGTEXT, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
            NotificationChannel channelDefault = new NotificationChannel(CHANNEL_ID_PICTURE, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationChannel channelHigh = new NotificationChannel(CHANNEL_ID_INBOX, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

            if(notificationManager != null){
                notificationManager.createNotificationChannel(channelLow);
                notificationManager.createNotificationChannel(channelDefault);
                notificationManager.createNotificationChannel(channelHigh);
            }
        }
    }


    public static void sendNotification(int NOTIFICATION_ID, String CHANNEL_ID, AppCompatActivity activity, String title, String message, int styleType, int LargeIconResID){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(activity, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 100);
                return;
            }
        }

        NotificationManager notificationManager= (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder= new NotificationCompat.Builder(activity, CHANNEL_ID)
                .setSmallIcon(R.drawable.kosc)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        switch (styleType){
            case 1:
                builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
                break;
            case 2:
                if(LargeIconResID != 0){
                    Bitmap largeIcon = BitmapFactory.decodeResource(activity.getResources(), LargeIconResID);
                    builder.setLargeIcon(largeIcon);
                    builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(largeIcon).setBigContentTitle(title));
                }
                break;
            case 3:
                NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
                inboxStyle.addLine(message);
                inboxStyle.addLine("Dodatkowa linia");
                builder.setStyle(inboxStyle);
                break;
        }
        notificationManager.notify(NOTIFICATION_ID,builder.build());
    }
}