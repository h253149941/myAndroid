package com.example.test_6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    val fragment1 = BlankFragment1()
    val fragment2 = BlankFragment2()
    val fragment3 = ChatFragment()
    val fragment4 = GameFragment()
    val myReceiver = MyReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentFilter = IntentFilter(MyMessage)
        registerReceiver(myReceiver,intentFilter)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, fragment1)
                .commit()
        }

        val bottomNV = findViewById<BottomNavigationView>(R.id.bottomNV)
        bottomNV.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.blank1 -> supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout,fragment1)
                    .commit()
                R.id.blank2 -> supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout,fragment2)
                    .commit()
                R.id.blank3 -> supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout,fragment3)
                    .commit()
                R.id.blank4 -> supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout,fragment4)
                    .commit()
            }
            true
        }

//
//        button1.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                .show(fragment1)
//                .hide(fragment2)
//                .commit()
//            printAllFragments()
//        }
//        button2.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                .show(fragment2)
//                .hide(fragment1)
//                .commit()
//            printAllFragments()
//        }
    }
    fun printAllFragments() {
        supportFragmentManager.fragments.forEach {
            Log.d("Fragment",it.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        unregisterReceiver(myReceiver)
    }
}

class MyReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d("Receiver","receive message")
    }

}
