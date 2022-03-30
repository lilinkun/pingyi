package com.communication.lib_http.httpdata.me

/**
 * Created by LG
 * on 2022/3/30  17:40
 * Description： 用户信息
 */
data class PersonInfoBean(
    val dept : DeptBean,
    val roles : RolesBean,
    val phonenumber : String,
    val userName : String
)
