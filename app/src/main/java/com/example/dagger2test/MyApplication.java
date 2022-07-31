package com.example.dagger2test;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.example.dagger2test.data.model.DataManager;
import com.example.dagger2test.di.module.ActivityModule;
import com.example.dagger2test.di.module.ApplicationComponent;
import com.example.dagger2test.di.module.ApplicationModule;
import com.example.dagger2test.di.module.DaggerApplicationComponent;

import javax.inject.Inject;

public class MyApplication extends Application {

    ApplicationComponent applicationComponent;
    public static Activity mActivity;

    @Inject
    DataManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        initApplicationComponent();
    }

    private void initApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        applicationComponent.inject(this);
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel_name";
            String description = "channel_description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("noti_test", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
}
}
