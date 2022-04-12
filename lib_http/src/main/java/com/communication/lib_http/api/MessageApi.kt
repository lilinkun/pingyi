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

    @GET("http://192.168.40.94:8080/operation/app/appMessage/getInfoList")
    suspend fun getMessageList() : BaseModel<MutableList<MessageBean>>

    @GET("http://192.168.40.94:8080/operation/app/appMessage/getInfoByIdt")
    suspend fun readOnlyMessage(
        @Query("id") id : String
    ) : BaseModel<Any>

    @GET("http://192.168.40.94:8080/operation/app/appMessage/getInfoByUserId")
    suspend fun readAllMessage() : BaseModel<Any>

}