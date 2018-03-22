package com.example.posegga.myapplication.main.net

import com.example.posegga.myapplication.main.model.Quote
import io.reactivex.Observable
import retrofit2.http.GET

interface QuoteApi {

    @GET("api")
    fun getQuote(): Observable<Quote>

}