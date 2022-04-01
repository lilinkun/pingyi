package com.communication.lib_http.httpdata.contact

import com.communication.lib_http.httpdata.me.DeptBean

/**
 * Created by LG
 * on 2022/4/1  17:14
 * Description：
 */
data class ContactUserBean(
    val userName : String,
    val postJob : String,
    val phoneNumber : String,
    val dept : DeptBean
)
