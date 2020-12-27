package com.example.xiong_huanhuan

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class BookAdapter(activity: Activity,val resourceId: Int,data: List<Book>):
    ArrayAdapter<Book>(activity,resourceId,data){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view=LayoutInflater.from(context).inflate(resourceId,parent,false)
        val bookName:TextView = view.findViewById(R.id.bookname)
        val author:TextView=view.findViewById(R.id.author)
        val publishing:TextView=view.findViewById(R.id.publishing)
        val price:TextView=view.findViewById(R.id.price)
        val book=getItem(position)
        if(book != null){
            bookName.text = book.bookname
            author.text = book.author
            publishing.text = book.pulishing
            price.text = book.price
        }
        return view
    }
}