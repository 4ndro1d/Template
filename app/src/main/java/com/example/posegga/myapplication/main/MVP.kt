package com.example.posegga.myapplication.main

import com.example.posegga.myapplication.main.model.Quote
import io.reactivex.Observable

interface MVP {

    interface View {

        fun setQuote(quote: Quote)

    }

    interface Presenter {

        fun attach(view: View)

        fun loadQuote()

        fun detach()

    }

    interface Model {

        fun getQuote(): Observable<Quote>

    }
}