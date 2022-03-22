package com.communication.pingyi.ui.me

import com.communication.lib_core.tools.EVENTBUS_LOGOUT_SUCCESS
import com.communication.lib_core.tools.EVENTBUS_TOAST_STRING
import com.communication.lib_http.base.NetResult
import com.communication.pingyi.base.BaseViewModel
import com.jeremyliao.liveeventbus.LiveEventBus

/**
 * Created by LG
 * on 2022/3/17  11:36
 * Description：
 */
class MeViewModel(private val repo : MeRepository) : BaseViewModel(){

    fun logout(){
        launch {

            isLoading.postValue(true)
            val result = repo.login()
            if (result is NetResult.Success){
                LiveEventBus.get(EVENTBUS_LOGOUT_SUCCESS).post(true)
            }else if (result is NetResult.Error){
                LiveEventBus.get(EVENTBUS_TOAST_STRING).post(result.exception.message)
            }

            isLoading.postValue(false)

        }
    }

}