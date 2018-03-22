package com.example.posegga.myapplication.main

import com.example.posegga.myapplication.main.presenter.MainPresenter
import com.example.posegga.myapplication.main.storage.FromRemote
import com.example.posegga.myapplication.main.storage.Repository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    @Singleton
    fun provideModel(fromRemote: FromRemote): MVP.Model = Repository(fromRemote)

    @Provides
    fun providePresenter(model: MVP.Model): MVP.Presenter = MainPresenter(model)

    @Provides
    fun provideFromRemote(retrofit: Retrofit) = FromRemote(retrofit)
}