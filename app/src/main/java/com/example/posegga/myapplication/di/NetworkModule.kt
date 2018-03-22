package com.example.posegga.myapplication.di

import com.example.posegga.myapplication.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Singleton
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, @BaseUrl baseUrl: String): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

    /*
     * Dagger expects a java.util.Set<okhttp3.Interceptor>, not ? extends okhttp3.Interceptor.
     * The problem is that you're using Kotlin and Kotlin will by default translate
     * a Set<X> into a Set<? extends X>
     * https://github.com/google/dagger/issues/668
     */
    @Provides
    fun provideHttpClient(@Interceptors interceptors: Set<@JvmSuppressWildcards okhttp3.Interceptor>): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        for (interceptor in interceptors) {
            builder.addInterceptor(interceptor)
        }
        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(StethoInterceptor())
        }
        return builder.build()
    }

    @Provides
    @Interceptors
    @IntoSet
    fun provideHttpLoggingInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        return logging
    }

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = "http://www.randomtext.me/"

    @Qualifier
    @Retention
    internal annotation class BaseUrl

    @Qualifier
    @Retention
    internal annotation class Interceptors
}
