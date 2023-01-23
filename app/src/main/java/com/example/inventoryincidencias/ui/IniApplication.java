package com.example.inventoryincidencias.ui;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.example.inventoryincidencias.R;

import java.util.concurrent.Executor;

/**
 * Esta clase se ejecuta antes que cualquier Activity, y es accesible desde todos los Activitys del proyecto.
 */
public class IniApplication extends Application {

    public static final String CHANNEL_ID = "345678";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    /**
     * En este método se debe comprobar que la API del dispositivo es +26 porque la clase NotificationChannel
     * no se encuentra en la librería de soporte.
     */
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
