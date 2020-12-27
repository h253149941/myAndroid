package com.example.test_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_third.*


class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        confirm.setOnClickListener {
            val intent = Intent()
            intent.putExtra(SECOND_MSG,editText.text.toString())
            setResult(1,intent)
            finish()
        }
    }
}