package com.example.af_viewmodel_event_sample.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.af_viewmodel_event_sample.R
import com.example.af_viewmodel_event_sample.databinding.ActivityStateSealedBinding
import com.example.af_viewmodel_event_sample.extensions.dataBinding
import com.example.af_viewmodel_event_sample.viewModels.StateFlowEventViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

class SharedSealedActivity : BaseActivity() {
    private val binding by dataBinding<ActivityStateSealedBinding>(R.layout.activity_state_sealed)
    private val viewModel by viewModels<StateFlowEventViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        initObserve()
    }

    override fun initUi() {
        binding.first.setOnClickListener {
            viewModel.setFirstData()
        }
        binding.second.setOnClickListener {
            viewModel.setSecondData()
        }
        binding.third.setOnClickListener {
            viewModel.setThirdData()
            startActivity(Intent(this, TestActivity::class.java))
        }
        binding.delay.setOnClickListener {
            viewModel.setDelayedData()
        }
    }

    private fun initObserve() {
        lifecycleScope.launch {
            viewModel.eventFlow.collect {
                when (it) {
                    is StateFlowEventViewModel.SharedFlowEvent.FirstData -> {
                        Timber.d("observed first data: $it")
                    }

                    is StateFlowEventViewModel.SharedFlowEvent.SecondData -> {
                        Timber.d("observed second data: $it")
                    }

                    is StateFlowEventViewModel.SharedFlowEvent.ThirdData -> {
                        Timber.d("observed third data: $it")
                    }

                    is StateFlowEventViewModel.SharedFlowEvent.DelayedData -> {
                        Timber.d("observed delayed data: $it")
                    }
                }
            }
        }
    }
}