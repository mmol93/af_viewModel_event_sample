package com.example.af_viewmodel_event_sample.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.af_viewmodel_event_sample.R
import com.example.af_viewmodel_event_sample.databinding.ActivityMainBinding
import com.example.af_viewmodel_event_sample.extensions.dataBinding

class MainActivity : BaseActivity() {
    private val binding by dataBinding<ActivityMainBinding>(R.layout.activity_main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }

    override fun initUi() {
        binding.liveDataButton.setOnClickListener {
            startActivity(Intent(this, LiveDataActivity::class.java))
        }

        binding.liveEventDataButton.setOnClickListener {
            startActivity(Intent(this, EventLiveDataActivity::class.java))
        }

        binding.singleLiveDataButton.setOnClickListener {
            startActivity(Intent(this, SingleLiveDataActivity::class.java))
        }

        binding.stateFlowButton.setOnClickListener {
            startActivity(Intent(this, StateFlowActivity::class.java))
        }

        binding.sharedSealedButton.setOnClickListener {
            startActivity(Intent(this, SharedSealedActivity::class.java))
        }
    }
}