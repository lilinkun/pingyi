package com.communication.pingyi.activity

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import com.communication.lib_core.tools.EVENTBUS_LOGIN_FAIL
import com.communication.lib_core.tools.EVENTBUS_TOKEN_INVALID
import com.communication.lib_core.tools.Utils
import com.communication.lib_http.api.mBaseModel
import com.communication.lib_http.base.MMKVTool
import com.communication.lib_http.httpdata.login.LoginInfo
import com.communication.pingyi.R
import com.communication.pingyi.base.BaseActivity
import com.communication.pingyi.ext.pyToast
import com.communication.pingyi.jpush.PushReceiver
import com.communication.pingyi.tools.ActivityUtil
import com.communication.pingyi.ui.login.account.LoginViewModel
import com.jeremyliao.liveeventbus.LiveEventBus
import kotlinx.coroutines.NonCancellable.isActive
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    val mLoginViewModel : LoginViewModel by viewModel<LoginViewModel>()
    lateinit var mLoginInfo : LoginInfo;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityUtil.addActivity(this)
        registerReceiver(reciver, IntentFilter("com.jiguang.demo.message"))

        intent?.apply {
            extras?.getString("message")?.let {
                LiveEventBus.get("message",Boolean::class.java).post(true)
            }
        }

        LiveEventBus.get(EVENTBUS_LOGIN_FAIL,Boolean::class.java).observe(this,{
            MMKVTool.saveReconnect(true)
        })


        LiveEventBus.get(
            EVENTBUS_TOKEN_INVALID,
            String::class.java
        ).observe(this,{

                    mBaseModel == null

                    val reconnect = MMKVTool.getReconnect()

                    if (reconnect) {

                        if (!it.contains("解析错误")) {
                            pyToast(it)
                        }
                        val name = MMKVTool.getUsername()
                        MMKVTool.clearAll()
                        val intent = Intent(this, LoginActivity::class.java)
                        MMKVTool.saveUsername(name)
                        intent.putExtra("name", name)
                        startActivity(intent)
                        finish()

                    }else{


                        val currentVersionCode =  Utils.getVersionCode(this).toString()
                        mLoginInfo = LoginInfo(
                            MMKVTool.getBrand(), MMKVTool.getUsername(),MMKVTool.getPassword(),
                            MMKVTool.getDeviceId(), currentVersionCode)
                        mLoginViewModel.createOrGetAuthorization(mLoginInfo)
                    }

        })

    }
    private val reciver: PushReceiver = PushReceiver()


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(reciver)
    }
}