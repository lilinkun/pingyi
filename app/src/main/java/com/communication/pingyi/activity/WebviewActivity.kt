package com.communication.pingyi.activity

import android.app.Activity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import com.communication.pingyi.R

/**
 * Created by LG
 * on 2022/3/10  11:40
 * Description：
 */
class WebviewActivity : Activity() {

    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_webview)

        val url = "http://www.baidu.com"

        webView = findViewById(R.id.wv)

        webView.run{

            settings.defaultTextEncodingName = "utf-8"
            settings.allowFileAccess = true
            settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS;
            settings.supportZoom()
            settings.builtInZoomControls = true
            settings.displayZoomControls = false
            settings.useWideViewPort = true
            settings.supportMultipleWindows()
            settings.setAppCacheEnabled(true)
            settings.domStorageEnabled = true
            settings.javaScriptEnabled = true
            settings.setGeolocationEnabled(true)
            settings.setAppCachePath(context.cacheDir.absolutePath)
            loadUrl(url)

        }


    }

}