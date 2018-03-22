package com.example.posegga.myapplication.main.storage

import com.example.posegga.myapplication.main.MVP
import com.example.posegga.myapplication.main.model.Quote
import io.reactivex.Observable

class Repository(private val fromRemote: FromRemote) : MVP.Model {

    override fun getQuote(): Observable<Quote> =
            fromRemote.getQuote()
}
