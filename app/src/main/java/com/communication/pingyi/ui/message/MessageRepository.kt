package com.communication.pingyi.ui.message

import com.communication.lib_http.api.MessageApi
import com.communication.lib_http.base.BaseModel
import com.communication.lib_http.base.BaseRepository
import com.communication.lib_http.base.NetResult

/**
 * Created by LG
 * on 2022/3/29  11:25
 * Description：
 */
class MessageRepository(private val mApi : MessageApi) : BaseRepository() {

    suspend fun getMessage() : NetResult<String>{
        return callRequest { handleResponse(mApi.getMessage()) }
    }

}