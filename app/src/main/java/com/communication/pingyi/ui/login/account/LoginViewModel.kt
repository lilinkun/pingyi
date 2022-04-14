package com.communication.pingyi.ui.login.account

import com.communication.lib_core.tools.EVENTBUS_LOGIN_SUCCESS
import com.communication.lib_core.tools.EVENTBUS_TOAST_STRING
import com.communication.lib_http.base.MMKVTool
import com.communication.lib_http.base.NetResult
import com.communication.lib_http.httpdata.login.LoginInfo
import com.communication.pingyi.base.BaseViewModel
import com.communication.pingyi.ext.pyToast
import com.jeremyliao.liveeventbus.LiveEventBus

/**
 * Created by LG
 * on 2022/3/15  8:56
 * Description：
 */
class LoginViewModel(private val repository : LoginRepository) : BaseViewModel() {

    fun createOrGetAuthorization(loginInfo : LoginInfo){
        launch {
            isLoading.postValue(true)
            val result = repository.login(loginInfo)

            if (result is NetResult.Success){
                result.data?.let {

                    if (it != null){
                        MMKVTool.saveToken(it.access_token ?: "")
                        MMKVTool.saveUsername(loginInfo.username)
                        MMKVTool.savePassword(loginInfo.password)
                        LiveEventBus.get(EVENTBUS_LOGIN_SUCCESS).post(true)
                    }

                }
            }else if (result is NetResult.Error){
                LiveEventBus.get(EVENTBUS_TOAST_STRING).post(result.exception.message)
            }


            isLoading.postValue(false)

        }
    }



}