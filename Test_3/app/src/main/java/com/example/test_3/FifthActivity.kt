package com.example.test_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_fifth.*

class FifthActivity : AppCompatActivity() {

    lateinit var viewModel: WatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth)
        Log.d("life cycle","onCreate")

        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(WatchViewModel::class.java)
        viewModel.seconds.observe(this, Observer {
            val hours = it/3600
            val minutes = (it%3600)/60
            val secs = it%60
            textView_timer.text = String.format("%02d:%02d:%02d",hours,minutes,secs)
        })

        button_start.setOnClickListener {
            viewModel.start()
        }
        button_stop.setOnClickListener {
            viewModel.stop()
        }
        button_restart.setOnClickListener {
            viewModel.restart()
        }
        }





}