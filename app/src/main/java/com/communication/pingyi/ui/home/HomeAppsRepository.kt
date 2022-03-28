package com.communication.pingyi.ui.home

import com.communication.lib_http.api.HomeApi
import com.communication.lib_http.base.BaseRepository
import com.communication.lib_http.base.NetResult
import com.communication.lib_http.httpdata.home.AppsItem

/**
 * Created by LG
 * on 2022/3/24  15:17
 * Description：
 */
class HomeAppsRepository(private val api : HomeApi) : BaseRepository(){


    suspend fun getHomeAppsList() : NetResult<MutableList<AppsItem>> {
        return callRequest { handleResponse(api.getHomeAppsList()) }
    }

}