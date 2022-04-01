package com.communication.pingyi.ui.home

import androidx.lifecycle.MutableLiveData
import com.communication.lib_core.tools.EVENTBUS_TOKEN_INVALID
import com.communication.lib_http.base.NetResult
import com.communication.lib_http.exception.ApiResultCode.INTERNAL_SERVER_ERROR
import com.communication.lib_http.httpdata.home.AppsItem
import com.communication.lib_http.httpdata.home.HomeItem
import com.communication.pingyi.base.BaseViewModel
import com.communication.pingyi.ext.pyLog
import com.jeremyliao.liveeventbus.LiveEventBus

/**
 * Created by LG
 * on 2022/3/24  15:09
 * Description：
 */
class AppsViewModel(private val repository: HomeAppsRepository) : BaseViewModel(){

    val appsLiveData = MutableLiveData<HomeItem<MutableList<AppsItem>>>()

    fun getHomeAppsList(){
        launch {
            isLoading.postValue(true)
            val result = repository.getHomeAppsList()

            if (result is NetResult.Success){
                result.data?.let {

                    if(it.size > 0) {
                        appsLiveData.postValue(it[0])
                    }

                }
            }else if (result is NetResult.Error){
                if (result.exception.errorCode == INTERNAL_SERVER_ERROR){
                    LiveEventBus.get(EVENTBUS_TOKEN_INVALID).post(true)
                }
            }

            isLoading.postValue(false)


        }
    }
}