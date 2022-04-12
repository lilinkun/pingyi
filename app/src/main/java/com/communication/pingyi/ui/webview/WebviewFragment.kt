package com.communication.pingyi.ui.webview

import android.content.Intent
import android.provider.Settings
import androidx.core.content.ContentProviderCompat
import com.communication.lib_core.PyAppDialog
import com.communication.lib_core.tools.GPSUtils
import com.communication.pingyi.R
import com.communication.pingyi.base.BaseFragment
import com.communication.pingyi.databinding.FragmentWebviewBinding
import com.communication.pingyi.ext.pyToast
import com.communication.pingyi.tools.AndroidJavascriptInterface
import com.tencent.smtt.sdk.WebSettings

/**
 * Created by LG
 * on 2022/4/11  15:59
 * Description：
 */
class WebviewFragment : BaseFragment<FragmentWebviewBinding>() {


    override fun getLayoutResId(): Int = R.layout.fragment_webview

    override fun initView() {
//        val url = "http://www.baidu.com"
        val url = "http://192.168.1.204:8088/#/"

        binding.webView.apply {


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



                addJavascriptInterface(AndroidJavascriptInterface(this@WebviewFragment), "Android")

                loadUrl(url)
        }

        if(!GPSUtils.isOPen(requireContext())){
            showGPSDialog()
        }

    }

    override fun observeViewModels() {
    }


    private fun showGPSDialog(requestCode: Int = 888) {
        PyAppDialog(requireContext())
            .setSingle(false)
            .canCancel(true)
            .setTitle(getString(R.string.location_title))
            .setMessage(getString(R.string.location_message))
            .setNegative(getString(R.string.location_cancel))
            .setPositive(getString(R.string.location_ok))
            .setPositiveCallBack {
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivityForResult(intent, requestCode)
            }
            .show()
    }

    /*override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 888){
            pyToast("asda")
        }

    }*/


}