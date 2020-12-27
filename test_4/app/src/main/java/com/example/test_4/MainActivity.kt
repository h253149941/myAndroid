package com.example.test_4

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test_4.model.CardMatchingGame
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    companion object{
        var game: CardMatchingGame = CardMatchingGame(24)
    }
    lateinit var adapter:CardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = CardAdapter(game)
        recyclerView.adapter = adapter

        val configure = resources.configuration
        if (configure.orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.layoutManager = GridLayoutManager(this,4)
        }else{
            recyclerView.layoutManager = GridLayoutManager(this,6)
        }

        adapter.setOnClickListenner {
            game.chooseCardAtIndex(it)
            updateUI()
        }
        updateUI()

        reset.setOnClickListener {
            game.reset()
            updateUI()
        }
    }
    fun updateUI(){
        adapter.notifyDataSetChanged()
        score.text = String.format("%s%d","Score",game.score)
        score.text = "Score:" + game.score
    }

}