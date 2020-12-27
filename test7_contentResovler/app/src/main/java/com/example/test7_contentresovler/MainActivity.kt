package com.example.test7_contentresovler

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var adapter: MyRecyclerViewAdapter
    lateinit var db: SQLiteDatabase
    lateinit var cursor: Cursor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val openSqLiteHelper = MyOpenSqLiteHelper(this,1)
        db = openSqLiteHelper.writableDatabase
        val cursor = db.query(TABLE_NAME,null,null,null,null,null,null)
        adapter = MyRecyclerViewAdapter(cursor)
        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        button_add.setOnClickListener {
            val name = editTextNumber_name.text.toString()
            val age = editTextNumber_age.text.toString()
            val gender = radioButton_male.isChecked
            val contentValues = ContentValues().apply {
                put("name",name)
                put("age",age)
                put("gender",gender)
            }
            db.insert(TABLE_NAME,null,contentValues)
            reloadAllData()
        }
        button_delete.setOnClickListener {
            val name = editTextNumber_name.text.toString()
            db.delete(TABLE_NAME,"name = ?", arrayOf(name))
            reloadAllData()
        }
        button_update.setOnClickListener {
            val name = editTextNumber_name.text.toString()
            val age = editTextNumber_age.text.toString()
            val gender = radioButton_male.isChecked
            val contentValues = ContentValues().apply {
                put("name",name)
                put("age",age)
                put("gender",gender)
            }
            db.update(TABLE_NAME,contentValues,"name = ?", arrayOf(name))
            reloadAllData()
        }

        button_query.setOnClickListener {
            val name = editTextNumber_name.text.toString()
            val cursor = db.query(TABLE_NAME,null,"name like '%$name%'", null,null,null,null)
            adapter.swapCursor(cursor)
        }
    }

    private fun reloadAllData() {
        cursor = db.query(TABLE_NAME, null, null, null, null, null, null)
        adapter.swapCursor(cursor)
    }

}

class MyRecyclerViewAdapter(var cursor: Cursor): RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {
    fun swapCursor(newCursor: Cursor){
        if (cursor == newCursor) return
        cursor.close()
        cursor = newCursor
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView_Name: TextView
        val textView_Age: TextView
        val textView_Gender: TextView
        init {
            textView_Name = view.findViewById(R.id.textView_Name)
            textView_Age = view.findViewById(R.id.textView_Age)
            textView_Gender = view.findViewById(R.id.textView_Gender)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cursor.moveToPosition(position)
        holder.textView_Name.text = cursor.getString(cursor.getColumnIndex("name"))
        holder.textView_Age.text = cursor.getString(cursor.getColumnIndex("age"))
        holder.textView_Gender.text = if (cursor.getInt(cursor.getColumnIndex("gender")) == 1) "男" else "女"
    }

    override fun getItemCount(): Int {
        return cursor.count
    }
}