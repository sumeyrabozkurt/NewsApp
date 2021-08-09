package com.example.haberaksuygulamas

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.annotation.RequiresApi

class WebViewActivity : AppCompatActivity() {

    var webView: WebView? = null

    var newsUrl = ""

    @SuppressLint("RestrictedApi")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        newsUrl = intent.extras?.get("newsUrl") as String

        webView = findViewById(R.id.webView)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true);
        actionBar?.setDisplayShowHomeEnabled(true);

        linkToNewsPage()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun linkToNewsPage() {
        webView?.webViewClient = WebViewClient()

        webView?.apply {
            loadUrl(newsUrl)
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}