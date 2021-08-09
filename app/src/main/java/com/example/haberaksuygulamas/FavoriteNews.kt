package com.example.haberaksuygulamas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavoriteNews : AppCompatActivity() {

    var bottom_nav: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_news)

        /*bottom_nav = findViewById(R.id.bottom_navigation2)
        bottom_nav?.selectedItemId = R.id.favoritesPage

        bottom_nav?.setOnNavigationItemReselectedListener(BottomNavigationView.OnNavigationItemReselectedListener OnNavigationItemSelectedListener@{
                item ->
            when(item.itemId){
                R.id.home -> {
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    return@OnNavigationItemSelectedListener
                }
                R.id.favoritesPage -> {
                    return@OnNavigationItemSelectedListener
                }

            }

        })*/


    }
}