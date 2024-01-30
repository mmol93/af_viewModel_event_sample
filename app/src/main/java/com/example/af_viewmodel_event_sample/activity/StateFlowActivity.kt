package com.example.af_viewmodel_event_sample.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.af_viewmodel_event_sample.R
import com.example.af_viewmodel_event_sample.databinding.ActivityStateFlowBinding
import com.example.af_viewmodel_event_sample.extensions.dataBinding
import com.example.af_viewmodel_event_sample.viewModels.StateFlowViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

class StateFlowActivity : BaseActivity() {
    private val binding by dataBinding<ActivityStateFlowBinding>(R.layout.activity_state_flow)
    private val viewModel by viewModels<StateFlowViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            initObserve()
        }
    }

    override fun initUi() {
        binding.button.setOnClickListener {
            viewModel.setData()
            startActivity(Intent(this, TestActivity::class.java))
        }
    }

    private suspend fun initObserve() {
        viewModel.someData.collect {
            Timber.d("observed data: $it")
        }
    }

    override fun onResume() {
        super.onResume()
        Timber.i("current data: ${viewModel.someData.value}")
    }
}