package com.example.posegga.myapplication.main.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.example.posegga.myapplication.App
import com.example.posegga.myapplication.R
import com.example.posegga.myapplication.main.MVP
import com.example.posegga.myapplication.main.model.Quote
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MVP.View {

    private val tvTitle by lazy { findViewById<TextView>(R.id.tv_title) }
    private val btNew by lazy { findViewById<Button>(R.id.bt_new) }

    @Inject
    lateinit var presenter: MVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).component.inject(this)

        btNew.setOnClickListener {
            presenter.loadQuote()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
        presenter.loadQuote()
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }

    override fun setQuote(quote: Quote) {
        tvTitle.text = quote.text
    }
}
