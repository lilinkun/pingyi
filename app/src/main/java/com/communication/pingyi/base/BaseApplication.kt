package com.communication.pingyi.base

import android.app.Application
import android.content.ContextWrapper
import com.communication.lib_http.base.MMKVTool.initializeMMKV
import com.communication.pingyi.di.allModule
import com.communication.pingyi.ext.initTimber
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by LG
 * on 2022/3/4  15:30
 * Description：
 */
open class BaseApplication : Application(){


    override fun onCreate() {
        super.onCreate()
        mApplication = this
        initTimber()
        initKoin()

        initMMKV()

    }


    private fun initKoin() {

        startKoin {

            androidContext(this@BaseApplication)
            modules(allModule)

        }

    }

    private fun initMMKV(){
        initializeMMKV(this)
    }

}

lateinit var mApplication: Application

object AppContext : ContextWrapper(mApplication)