package com.communication.pingyi.ui.contact

import com.communication.lib_http.api.ContactApi
import com.communication.lib_http.base.BaseRepository
import com.communication.lib_http.base.NetResult
import com.communication.lib_http.httpdata.contact.ContactItem
import com.communication.lib_http.httpdata.home.AppsItem

/**
 * Created by LG
 * on 2022/3/17  16:07
 * Description：
 */
class ContactRepository(private val contactApi : ContactApi) : BaseRepository() {


    suspend fun getContact() : NetResult<MutableList<ContactItem>> {
        return callRequest { handleResponse(contactApi.getContact()) }
    }

}