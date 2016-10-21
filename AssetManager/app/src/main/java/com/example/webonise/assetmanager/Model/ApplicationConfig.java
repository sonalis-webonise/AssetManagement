package com.example.webonise.assetmanager.Model;

import android.app.Application;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;

public class ApplicationConfig extends Application implements RealmMigration {
    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration configuration=new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .migration(this)
                .schemaVersion(2)
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

    }
}
