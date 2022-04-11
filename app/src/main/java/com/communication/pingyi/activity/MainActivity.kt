package com.communication.pingyi.activity

import android.content.IntentFilter
import android.os.Bundle
import com.communication.pingyi.R
import com.communication.pingyi.base.BaseActivity
import com.communication.pingyi.jpush.PushReceiver
import com.communication.pingyi.tools.ActivityUtil
import com.jeremyliao.liveeventbus.LiveEventBus

class MainActivity : BaseActivity() {
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

    }
    private val reciver: PushReceiver = PushReceiver()


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(reciver)
    }
}