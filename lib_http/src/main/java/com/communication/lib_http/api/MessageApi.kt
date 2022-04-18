package com.communication.lib_http.api

import com.communication.lib_http.base.BaseModel
import com.communication.lib_http.httpdata.message.MessageBean
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by LG
 * on 2022/3/29  14:06
 * Description：
 */
interface MessageApi {

    @GET("$SERVER_BASE_URL/operation/app/appMessage/getInfoList")
    suspend fun getMessageList() : BaseModel<MutableList<MessageBean>>

    @GET("$SERVER_BASE_URL/operation/app/appMessage/getInfoById")
    suspend fun readOnlyMessage(
        @Query("id") id : String
    ) : BaseModel<String>

    @GET("$SERVER_BASE_URL/operation/app/appMessage/getInfoByUserId")
    suspend fun readAllMessage(
        @Query("userId") userId : String
    ) : BaseModel<MessageBean>

}