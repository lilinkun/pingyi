package com.communication.pingyi.ui.contact

import com.communication.lib_http.base.NetResult
import com.communication.pingyi.base.BaseViewModel
import com.communication.pingyi.ext.pyLog
import org.koin.core.KoinApplication.Companion.logger

/**
 * Created by LG
 * on 2022/3/17  15:53
 * Description：
 */
class ContactViewModel(private val repos : ContactRepository) : BaseViewModel(){

    fun getContactList(){

        launch {


            val result = repos.getContact()

            if (result is NetResult.Success){

                result.data?.let { pyLog(it[0].label) }

            }else if(result is NetResult.Error){

            }


        }



    }


}

