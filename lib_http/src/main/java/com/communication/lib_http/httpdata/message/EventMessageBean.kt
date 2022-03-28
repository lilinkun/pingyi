package com.communication.lib_http.httpdata.message

/**
 * Created by LG
 * on 2022/3/28  18:01
 * Description：
 */
data class EventMessageBean(
    val isRead : Boolean,
    val createTime : String,
    val eventContent : String,
    val eventId : String,
)
