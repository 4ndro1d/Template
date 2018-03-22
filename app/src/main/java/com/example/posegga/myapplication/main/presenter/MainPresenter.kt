package com.example.posegga.myapplication.main.presenter

import com.example.posegga.myapplication.main.MVP
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainPresenter(val model: MVP.Model) : MVP.Presenter {

    lateinit var view: MVP.View

    private val compositeDisposable = CompositeDisposable()

    override fun attach(view: MVP.View) {
        this.view = view
    }

    override fun loadQuote() {
        compositeDisposable.add(
                model.getQuote()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(view::setQuote, Timber::e))
    }

    override fun detach() {
        compositeDisposable.clear()
    }
}