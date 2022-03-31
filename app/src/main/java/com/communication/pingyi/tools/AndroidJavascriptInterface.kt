package com.communication.pingyi.tools

import android.app.Activity
import android.content.Intent
import android.webkit.JavascriptInterface
import com.communication.lib_http.base.MMKVTool
import com.communication.pingyi.activity.LoginActivity
import com.communication.pingyi.activity.WebviewActivity

/**
 * Created by LG
 * on 2022/3/31  17:22
 * Description：
 */
class AndroidJavascriptInterface(webviewActivity: Activity?) {
    private val webviewActivity: Activity?
    @JavascriptInterface
    fun goToLogin() {
        if (webviewActivity != null) {
            MMKVTool.clearAll()
            val intent = Intent(webviewActivity, LoginActivity::class.java)
            webviewActivity.startActivity(intent)
            ActivityUtil.finishAll()
        }
    }

    @JavascriptInterface
    fun goBack() {
        webviewActivity?.runOnUiThread {
            //                    WebView webView = webviewActivity.getWebView();
            //
            //                    if (webView != null) {
            webviewActivity.setResult(Activity.RESULT_OK)
            webviewActivity.finish()
            //                    }
        }
    }

    @JavascriptInterface
    fun getToken(): String {
        return MMKVTool.getToken()
    }

    @JavascriptInterface
    fun JumpDetail(url: String?) {
        val intent = Intent(webviewActivity, WebviewActivity::class.java)
        intent.putExtra("url", url)
        webviewActivity!!.startActivity(intent)
    }

    init {
        this.webviewActivity = webviewActivity
    }
}
