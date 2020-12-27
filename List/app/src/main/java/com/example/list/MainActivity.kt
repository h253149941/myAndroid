package com.example.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val data = listOf("教务处","文学院","法学院","外国语学院","历史文化与旅游学院","教育科学学" +
            "院","教育科学学院","数学与软件科学学院","物理与电子工程学院","化学与材料科学学院","生命科" +
            "学学院","地理与资源科学学院","计算机科学学院","经济与管理学院","体育学院","政治教育学院",
            "教师教育与心理学院","工学院","商学院")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            textView.text = data[position]
        }
    }
}