package com.example.posegga.myapplication

import android.app.Application
import com.example.posegga.myapplication.di.AppComponent
import com.example.posegga.myapplication.di.AppModule
import com.example.posegga.myapplication.di.DaggerAppComponent
import timber.log.Timber

class App : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()

        Timber.plant(Timber.DebugTree())
    }

}