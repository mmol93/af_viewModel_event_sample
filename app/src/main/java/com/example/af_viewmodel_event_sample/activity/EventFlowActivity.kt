package com.example.af_viewmodel_event_sample.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.af_viewmodel_event_sample.R
import com.example.af_viewmodel_event_sample.databinding.ActivityEventFlowBinding
import com.example.af_viewmodel_event_sample.extensions.dataBinding
import com.example.af_viewmodel_event_sample.extensions.repeatOnStarted
import com.example.af_viewmodel_event_sample.viewModels.EventFlowViewModel
import timber.log.Timber

class EventFlowActivity : BaseActivity() {
    private val binding by dataBinding<ActivityEventFlowBinding>(R.layout.activity_event_flow)
    private val viewModel by viewModels<EventFlowViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        initUi()
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
        // 全てのEventをrepeatOnStartedでCollectするように変更
        repeatOnStarted {
            viewModel.eventFlow.collect {
                when (it) {
                    is EventFlowViewModel.SharedFlowEvent.FirstData -> {
                        Timber.d("observed first data: ${it.number}")
                    }

                    is EventFlowViewModel.SharedFlowEvent.SecondData -> {
                        Timber.d("observed second data: ${it.text}")
                    }

                    is EventFlowViewModel.SharedFlowEvent.ThirdData -> {
                        Timber.d("observed third data: ${it.ox}")
                    }

                    is EventFlowViewModel.SharedFlowEvent.DelayedData -> {
                        Timber.d("observed delayed data: ${it.text}")
                    }
                }
            }
        }
    }
}