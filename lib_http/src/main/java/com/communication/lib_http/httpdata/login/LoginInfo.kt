package com.communication.lib_http.httpdata.login

data class LoginInfo(
    val code : String,
    val username : String,
    val password : String,
    val clientType : String,
    val uuid : String,
)