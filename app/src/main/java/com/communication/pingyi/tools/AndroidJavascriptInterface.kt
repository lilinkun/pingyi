package com.communication.pingyi.tools

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.webkit.JavascriptInterface
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.communication.lib_http.base.MMKVTool
import com.communication.pingyi.activity.LoginActivity
import com.communication.pingyi.activity.WebviewActivity
import com.communication.pingyi.ext.pyToast

/**
 * Created by LG
 * on 2022/3/31  17:22
 * Description：
 */
class AndroidJavascriptInterface(webviewActivity: Fragment?) {
    lateinit var webviewActivity: Fragment


    init {
        if (webviewActivity != null) {
            this.webviewActivity = webviewActivity
        }
    }

    @JavascriptInterface
    fun goToLogin() {
        if (webviewActivity != null) {
            MMKVTool.clearAll()
            val intent = Intent(webviewActivity.context, LoginActivity::class.java)
            webviewActivity.startActivity(intent)
            ActivityUtil.finishAll()
        }
    }

    @JavascriptInterface
    fun goBack() {

        webviewActivity?.findNavController()?.navigateUp()

        /*webviewActivity?.runOnUiThread {
            //                    WebView webView = webviewActivity.getWebView();
            //
            //                    if (webView != null) {
            webviewActivity.setResult(Activity.RESULT_OK)
            webviewActivity.finish()
            //                    }
        }*/
    }

    @JavascriptInterface
    fun getToken(): String {
        pyToast(MMKVTool.getToken())
        return MMKVTool.getToken()
    }

}

