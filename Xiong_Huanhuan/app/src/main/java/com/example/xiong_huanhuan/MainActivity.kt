package com.example.xiong_huanhuan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*

const val FIRST_MSG = "FIRST_MSG"
const val SECOND_MSG = "SECOND_MSG"
const val THIRD_MSG = "THIRD_MSG"
const val FOURTH_MSG = "FOURTH_MSG"
class MainActivity : AppCompatActivity() {
    private val bookList = ArrayList<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBook()
        val adapter = BookAdapter(this,R.layout.book_item,bookList)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            val book = bookList[position]
            Toast.makeText(this,book.bookname,Toast.LENGTH_SHORT).show()

            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra(FIRST_MSG,bookname.text.toString())
            intent.putExtra(SECOND_MSG,author.text.toString())
            intent.putExtra(THIRD_MSG,publishing.text.toString())
            intent.putExtra(FOURTH_MSG,price.text.toString())
            startActivity(intent)
        }
    }

    private fun initBook(){
        repeat(1){
            bookList.add(Book("傲慢与偏见","奥斯汀","人民教育出版社","34"))
            bookList.add(Book("巴黎圣母院","雨果","人民文学出版社","29"))
            bookList.add(Book("老人与海","海明威","中国社会科学出版社","40"))
            bookList.add(Book("童年","高尔基","人民教育出版社","42"))
            bookList.add(Book("茶花女","小仲马","中国社会科学出版社","33"))
            bookList.add(Book("罗密欧与朱丽叶","莎士比亚","中国社会科学出版社","36"))
        }
        bookList.sortBy { it.price}
    }
}