package com.communication.lib_http.api

import com.communication.lib_http.base.BaseModel
import retrofit2.http.GET

/**
 * Created by LG
 * on 2022/3/17  16:37
 * Description：
 */
interface MeApi {

    @GET("$SERVER_BASE_URL/logout")
    suspend fun logout() : BaseModel<String>

}