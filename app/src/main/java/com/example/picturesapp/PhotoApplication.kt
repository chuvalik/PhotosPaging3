package com.example.picturesapp

import android.app.Application
import com.example.picturesapp.feature_search_photo.di.appModule
import com.example.picturesapp.feature_search_photo.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PhotoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PhotoApplication)
            modules(dataModule, appModule)
        }
    }
}