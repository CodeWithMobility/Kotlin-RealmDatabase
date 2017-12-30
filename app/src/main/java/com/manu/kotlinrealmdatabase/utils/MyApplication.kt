package com.manu.kotlinrealmdatabase.utils

import android.app.Application

/**
 * Created by manu on 12/28/2017.
 */




import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by Manu on 11/6/2017.
 */

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initRealm()
    }

    private fun initRealm() {
        Realm.init(this)
        val configuration = RealmConfiguration.Builder()
                .name("myDatabase.realm")
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(configuration)
    }
}