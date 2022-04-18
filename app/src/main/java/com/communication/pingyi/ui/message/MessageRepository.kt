package com.communication.pingyi.ui.message

import com.communication.lib_http.api.MessageApi
import com.communication.lib_http.base.BaseModel
import com.communication.lib_http.base.BaseRepository
import com.communication.lib_http.base.NetResult
import com.communication.lib_http.httpdata.message.MessageBean

/**
 * Created by LG
 * on 2022/3/29  11:25
 * Description：
 */
class MessageRepository(private val mApi : MessageApi) : BaseRepository() {

    suspend fun getMessageList() : NetResult<MutableList<MessageBean>>{
        return callRequest { handleResponse(mApi.getMessageList()) }
    }

    suspend fun readOnlyMessage(id : String) : NetResult<String>{
        return callRequest { handleResponse(mApi.readOnlyMessage(id)) }
    }


    suspend fun readAllMessage(userId : String) : NetResult<MessageBean>{
        return callRequest { handleResponse(mApi.readAllMessage(userId)) }
    }

}