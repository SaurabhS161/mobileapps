package com.example.petsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class DetailsActivity : AppCompatActivity() {
    private lateinit var webView : WebView
    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        url = intent.extras?.getString("URL").toString()

        webView = findViewById(R.id.webview)
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)
    }
}