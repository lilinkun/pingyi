package com.communication.pingyi.ui.contact

import androidx.lifecycle.MutableLiveData
import com.communication.lib_http.base.NetResult
import com.communication.lib_http.httpdata.contact.ContactBean
import com.communication.lib_http.httpdata.contact.ContactItem
import com.communication.pingyi.base.BaseViewModel
import com.communication.pingyi.ext.pyLog
import org.koin.core.KoinApplication.Companion.logger

/**
 * Created by LG
 * on 2022/3/17  15:53
 * Description：
 */
class ContactViewModel(private val repos : ContactRepository) : BaseViewModel(){

    val org_list = MutableLiveData<MutableList<ContactItem>>()

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


}

