package com.example.haberaksuygulamas

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.io.File

class DetailActivity : AppCompatActivity() {

    var nestedScroolView: NestedScrollView? = null
    var newsImage: ImageView? = null
    var newsTitle: TextView? = null
    var newsContent: TextView? = null
    var newsAuthor: TextView? = null
    var newsPublishedAt: TextView? = null
    var viewSourceButton: Button? = null
    var shareButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var newsDetails=intent.extras?.get("newsDetails") as NewsInfo

        nestedScroolView = findViewById(R.id.scroolview)
        newsImage = findViewById(R.id.newsImage)
        newsTitle = findViewById(R.id.newsTitle)
        newsContent = findViewById(R.id.newsContent)
        newsAuthor = findViewById(R.id.newsAuthor)
        newsPublishedAt = findViewById(R.id.newsPublishedAt)
        viewSourceButton = findViewById(R.id.viewSourceButton)
        shareButton = findViewById(R.id.shareButton)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        Glide.with(this).load(newsDetails.imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(newsImage)

        newsTitle?.setText(newsDetails.title)
        newsContent?.setText(newsDetails.content)
        newsAuthor?.setText(newsDetails.author)
        newsPublishedAt?.setText(newsDetails.publishedAt.subSequence(0,10))

        viewSourceButton?.setOnClickListener(View.OnClickListener {

            var intent = Intent(this@DetailActivity, WebViewActivity::class.java)
            intent.putExtra("newsUrl", newsDetails.url)
            startActivity(intent)
        })

        shareButton?.setOnClickListener(View.OnClickListener {

            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            startActivity(shareIntent)

        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}