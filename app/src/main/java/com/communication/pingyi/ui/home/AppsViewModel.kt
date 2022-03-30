package com.communication.pingyi.ui.home

import androidx.lifecycle.MutableLiveData
import com.communication.lib_http.base.NetResult
import com.communication.lib_http.httpdata.home.AppsItem
import com.communication.lib_http.httpdata.home.HomeItem
import com.communication.pingyi.base.BaseViewModel

/**
 * Created by LG
 * on 2022/3/24  15:09
 * Description：
 */
class AppsViewModel(private val repository: HomeAppsRepository) : BaseViewModel(){

    val appsLiveData = MutableLiveData<HomeItem<MutableList<AppsItem>>>()

    fun getHomeAppsList(){
        launch {

            val result = repository.getHomeAppsList()

            if (result is NetResult.Success){
                result.data?.let {

                    appsLiveData.postValue(it[0])

                }
            }else if (result is NetResult.Error){

            }


        }
    }
}