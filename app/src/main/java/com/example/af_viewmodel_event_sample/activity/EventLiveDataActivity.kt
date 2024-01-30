package com.example.af_viewmodel_event_sample.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.af_viewmodel_event_sample.R
import com.example.af_viewmodel_event_sample.databinding.ActivityEventLiveDataBinding
import com.example.af_viewmodel_event_sample.extensions.dataBinding
import com.example.af_viewmodel_event_sample.viewModels.EventLiveDataViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

class EventLiveDataActivity : BaseActivity() {
    private val binding by dataBinding<ActivityEventLiveDataBinding>(R.layout.activity_event_live_data)
    private val viewModel by viewModels<EventLiveDataViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }

    override fun initUi() {
        binding.button.setOnClickListener {
            viewModel.setData()
        }
    }

    override fun initObserve() {
        lifecycleScope.launch {
            viewModel.someData.observe(this@EventLiveDataActivity) {
                startActivity(Intent(this@EventLiveDataActivity, TestActivity::class.java))
                Timber.d("observed data: ${it.getContentIfNotHandled()}")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Timber.i("current data: ${viewModel.someData.value?.getContentIfNotHandled()}")
        Timber.i("peek data: ${viewModel.someData.value?.peekContent()}")
    }
}