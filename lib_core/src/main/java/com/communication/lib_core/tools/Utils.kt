package com.communication.lib_core.tools

import android.content.Context

object Utils {
    fun getVersionName(context: Context): String {
        return context.packageManager.getPackageInfo(context.packageName, 0).versionName
    }

    fun getVersionCode(context: Context): Long {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            context.packageManager.getPackageInfo(context.packageName, 0).longVersionCode
        } else {
            context.packageManager.getPackageInfo(context.packageName, 0).versionCode.toLong()
        }
    }
}