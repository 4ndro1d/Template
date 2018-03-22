package com.example.posegga.myapplication.di

import com.example.posegga.myapplication.App
import com.example.posegga.myapplication.main.MainModule
import com.example.posegga.myapplication.main.view.MainActivity
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, MainModule::class])
interface AppComponent : AndroidInjector<App> {

    fun inject(mainActivity: MainActivity)
}