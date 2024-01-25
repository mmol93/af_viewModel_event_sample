package com.example.af_viewmodel_event_sample.extensions

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlin.reflect.KProperty

class ActivityBindingDelegate<T : ViewDataBinding>(
    private val activity: AppCompatActivity,
    private val layoutId: Int
) {
    private var binding: T? = null

    operator fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        binding?.let { return it }

        val bindingResult = DataBindingUtil.setContentView<T>(activity, layoutId)
        binding = bindingResult
        return bindingResult
    }

    operator fun setValue(thisRef: AppCompatActivity, property: KProperty<*>, value: T) {
        binding = value
    }
}

fun <T : ViewDataBinding> AppCompatActivity.dataBinding(@LayoutRes layoutId: Int) =
    ActivityBindingDelegate<T>(this, layoutId)
