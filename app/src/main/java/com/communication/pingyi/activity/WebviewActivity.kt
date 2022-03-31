package com.communication.pingyi.activity

import android.app.Activity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import com.communication.pingyi.R
import com.communication.pingyi.base.BaseActivity
import com.communication.pingyi.tools.AndroidJavascriptInterface

/**
 * Created by LG
 * on 2022/3/10  11:40
 * Description：
 */
class WebviewActivity : BaseActivity() {

    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_webview)

        val url = "http://localhost:8088/"

        webView = findViewById(R.id.wv)

        webView.apply{

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



            addJavascriptInterface(AndroidJavascriptInterface(this@WebviewActivity), "Android")

            loadUrl(url)

        }


    }

}