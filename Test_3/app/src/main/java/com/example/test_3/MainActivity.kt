package com.example.test_3

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_four.*
import kotlinx.android.synthetic.main.activity_main.*

const val FIRST_MSG = "FIRST_MSG"
const val SECOND_MSG = "SECOND_MSG"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        first.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra(FIRST_MSG,editText.text.toString())
            startActivity(intent)
        }

        second.setOnClickListener {
            val intent = Intent(this,ThirdActivity::class.java)
            startActivityForResult(intent,0)
        }

        third.setOnClickListener {
            val intent = Intent(this,FourActivity::class.java)
            startActivity(intent)
        }

        fourth.setOnClickListener {
            val intent = Intent(this,FifthActivity::class.java)
            startActivity(intent)
        }
    }
    //回调
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0) {//请求判断
            if (resultCode == 1) {
                textView.text = data?.getStringExtra(SECOND_MSG)//?为判空判断
            }
        }
    }
}