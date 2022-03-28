package com.communication.pingyi.ui.me.me

import com.communication.lib_http.api.MeApi
import com.communication.lib_http.base.BaseRepository
import com.communication.lib_http.base.NetResult

/**
 * Created by LG
 * on 2022/3/17  16:35
 * Description：
 */
class MeRepository(private val meApi : MeApi) : BaseRepository() {

    suspend fun login() : NetResult<String>{
        return callRequest { handleResponse(meApi.logout()) }
    }


}