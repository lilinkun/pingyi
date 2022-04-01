package com.communication.pingyi.ui.contact.contact

import androidx.lifecycle.MutableLiveData
import com.communication.lib_http.base.NetResult
import com.communication.lib_http.httpdata.contact.ContactBean
import com.communication.lib_http.httpdata.contact.ContactItem
import com.communication.lib_http.httpdata.contact.ContactUserBean
import com.communication.pingyi.base.BaseViewModel

/**
 * Created by LG
 * on 2022/3/17  15:53
 * Description：
 */
class ContactViewModel(private val repos : ContactRepository) : BaseViewModel(){

    var org_list = MutableLiveData<MutableList<ContactItem>>()

    val org_user = MutableLiveData<ContactBean>()

    fun getContactList(){

        launch {
            val result = repos.getContact()
            if (result is NetResult.Success){
                result.data?.let {
                   org_list.postValue(it.trees)
                }
            }else if(result is NetResult.Error){
            }
        }
    }

    fun getContactUser(id : String){

        launch {
            val result = repos.getContact(id)
            if (result is NetResult.Success){
                result.data?.let {
                    org_user.postValue(it)
                }
            }else if(result is NetResult.Error){
            }
        }
    }


}

