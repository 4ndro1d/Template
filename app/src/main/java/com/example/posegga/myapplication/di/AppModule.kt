package com.example.posegga.myapplication.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.bumptech.glide.Glide
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideResources(context: Context): Resources = context.resources

    @Provides
    @Singleton
    fun provideGlide(context: Context) = Glide.with(context)
}