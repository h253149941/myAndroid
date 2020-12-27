package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainLinearLayout = findViewById<LinearLayout>(R.id.LinearLayout)

        var index = 1

        button.setOnClickListener{
            val textView = TextView(this)
            textView.text = resources.getString(R.string.text) + index.toString()
            index++
            mainLinearLayout.addView(textView)
        }

        val expert = ProgramExpert()

        button3.setOnClickListener{
            textView2.text = expert.getLanguage(spinner.selectedItem.toString())
        }
    }
}