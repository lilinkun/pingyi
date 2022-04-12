package com.communication.pingyi.ui.message

import androidx.lifecycle.MutableLiveData
import com.communication.lib_http.base.NetResult
import com.communication.lib_http.httpdata.message.MessageBean
import com.communication.pingyi.base.BaseViewModel

/**
 * Created by LG
 * on 2022/3/29  11:22
 * Description：
 */
class MessageViewModel(private val repo : MessageRepository) : BaseViewModel() {

    val messageList = MutableLiveData<MutableList<MessageBean>>()

    fun getMessageList(){
        launch {
            isLoading.postValue(true)
            val result = repo.getMessageList()

            if (result is NetResult.Success){

                result.data?.let {

                    messageList.postValue(result.data)

                }

            }else if (result is NetResult.Error){

            }
            isLoading.postValue(false)


        }
    }

    fun readOnlyMessage(id : String){
        launch {
            isLoading.postValue(true)
            val result = repo.readOnlyMessage(id)

            if (result is NetResult.Success){

                result.data?.let {


                }

            }else if (result is NetResult.Error){

            }
            isLoading.postValue(false)
        }
    }


    fun readAllMessage(){
        launch {
            isLoading.postValue(true)
            val result = repo.readAllMessage()

            if (result is NetResult.Success){

                result.data?.let {


                }

            }else if (result is NetResult.Error){

            }
            isLoading.postValue(false)
        }
    }


}