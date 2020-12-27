package com.example.test_3

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_fifth.*

class WatchViewModel :ViewModel(){
    private  var _seconds:MutableLiveData<Int> = MutableLiveData()
    var running = false
    val seconds:LiveData<Int> = _seconds

    init {
        runTimer()
    }

    fun start(){
        running = true
    }

    fun stop(){
        running = false
        _seconds.value=0
    }
    fun restart(){
        running = true
    }
    fun runTimer(){
        val handler = Handler()
        val runnable = object: Runnable{
            override fun run(){
                if(running){
                    val sec = _seconds.value ?:0
                    _seconds.value = sec +1
                }
                handler.postDelayed(this,1000)
            }
        }
        handler.post(runnable)
    }
}