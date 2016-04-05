package com.technicaltask.app;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TechnicalTaskApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration configuration = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
