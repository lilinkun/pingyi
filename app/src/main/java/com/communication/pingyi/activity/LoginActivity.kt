package com.communication.pingyi.activity

import android.os.Bundle
import com.communication.pingyi.R
import com.communication.pingyi.base.BaseActivity
import com.jeremyliao.liveeventbus.LiveEventBus

/**
 * Created by LG
 * on 2022/3/14  11:22
 * Description：
 */
class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val name = intent.getStringExtra("name")
        name?.let {

            LiveEventBus.get("name").post(it)

        }
    }

    override fun onBackPressed() {
        finish()
    }

}