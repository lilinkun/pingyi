package com.communication.lib_http.httpdata.contact

/**
 * Created by LG
 * on 2022/3/31  9:34
 * Description：
 */
data class ContactBean<out T>(val trees : MutableList<ContactItem>,val users : T)
