package com.communication.pingyi.ui.message

import com.communication.lib_http.base.NetResult
import com.communication.pingyi.base.BaseViewModel

/**
 * Created by LG
 * on 2022/3/29  11:22
 * Description：
 */
class MessageViewModel(private val repo : MessageRepository) : BaseViewModel() {

    fun getMessage(){
        launch {
            /*val result = repo.getMessage()

            if (result is NetResult.Success){

            }else if (result is NetResult.Error){

            }*/


        }
    }


}