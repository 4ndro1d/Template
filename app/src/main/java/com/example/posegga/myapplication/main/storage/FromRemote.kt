package com.example.posegga.myapplication.main.storage

import com.example.posegga.myapplication.main.model.Quote
import com.example.posegga.myapplication.main.net.QuoteApi
import io.reactivex.Observable
import retrofit2.Retrofit

class FromRemote(private val retrofit: Retrofit) {

    fun getQuote(): Observable<Quote> =
            retrofit.create(QuoteApi::class.java).getQuote()
}