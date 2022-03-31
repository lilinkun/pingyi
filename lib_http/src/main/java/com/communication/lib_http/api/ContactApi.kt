package com.communication.lib_http.api

import com.communication.lib_http.base.BaseModel
import com.communication.lib_http.httpdata.contact.ContactBean
import retrofit2.http.GET

/**
 * Created by LG
 * on 2022/3/17  16:18
 * Description：
 */
interface ContactApi {

    @GET("$SERVER_BASE_URL/system/address/dept/appTreeSelect")
    suspend fun getContact() : BaseModel<ContactBean<Any>>


}