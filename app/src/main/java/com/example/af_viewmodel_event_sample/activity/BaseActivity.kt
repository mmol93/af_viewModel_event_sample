package com.example.af_viewmodel_event_sample.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        initObserve()
    }

    abstract fun initUi()
    abstract fun initObserve()
}