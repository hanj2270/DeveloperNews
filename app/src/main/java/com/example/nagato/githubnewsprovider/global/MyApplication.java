package com.example.nagato.githubnewsprovider.global;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by nagato hanj
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder(this)
                .schemaVersion(2)
                .deleteRealmIfMigrationNeeded()
                .build());
    }
}