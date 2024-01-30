package com.example.af_viewmodel_event_sample.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.af_viewmodel_event_sample.R
import com.example.af_viewmodel_event_sample.databinding.ActivitySingleLiveDataBinding
import com.example.af_viewmodel_event_sample.extensions.dataBinding
import com.example.af_viewmodel_event_sample.viewModels.SingleViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

class SingleLiveDataActivity : BaseActivity() {
    private val binding by dataBinding<ActivitySingleLiveDataBinding>(R.layout.activity_single_live_data)
    private val viewModel by viewModels<SingleViewModel>()
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
        val intent = Intent(this, TestActivity::class.java)
        lifecycleScope.launch {
            viewModel.someData.observe(this@SingleLiveDataActivity) {
                startActivity(intent)
                Timber.d("observed data: $it")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Timber.i("current data: ${viewModel.someData.getNotHandledValue()}")
        Timber.i("peek data: ${viewModel.someData.getValue()}")
    }
}