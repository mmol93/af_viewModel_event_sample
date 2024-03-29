package com.example.af_viewmodel_event_sample.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.af_viewmodel_event_sample.R
import com.example.af_viewmodel_event_sample.databinding.ActivityLiveDataBinding
import com.example.af_viewmodel_event_sample.extensions.dataBinding
import com.example.af_viewmodel_event_sample.viewModels.LiveDataViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

class LiveDataActivity : BaseActivity() {
    private val binding by dataBinding<ActivityLiveDataBinding>(R.layout.activity_live_data)
    private val viewModel by viewModels<LiveDataViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        initObserve()
    }

    override fun initUi() {
        binding.button.setOnClickListener {
            viewModel.setData()
        }
    }

    private fun initObserve() {
        lifecycleScope.launch {
            viewModel.someData.observe(this@LiveDataActivity) {
                startActivity(Intent(this@LiveDataActivity, TestActivity::class.java))
                Timber.d("observed data: $it")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Timber.i("current Data: ${viewModel.someData.value}")
    }
}