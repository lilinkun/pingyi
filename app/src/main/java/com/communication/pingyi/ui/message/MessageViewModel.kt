package com.communication.pingyi.ui.message

import androidx.lifecycle.MutableLiveData
import com.communication.lib_http.base.NetResult
import com.communication.lib_http.httpdata.message.MessageBean
import com.communication.pingyi.base.BaseViewModel
import com.communication.pingyi.ext.pyToast

/**
 * Created by LG
 * on 2022/3/29  11:22
 * Description：
 */
class MessageViewModel(private val repo : MessageRepository) : BaseViewModel() {

    val messageList = MutableLiveData<MutableList<MessageBean>>()
    val messageError = MutableLiveData<String>()

    fun getMessageList(){
        launch {
            isLoading.postValue(true)
            val result = repo.getMessageList()

            if (result is NetResult.Success){

                result.data?.let {

                    messageList.postValue(result.data)

                }

            }else if (result is NetResult.Error){
                result.exception?.let {
                    if(!it.msg.contains("解析错误")) {
                        messageError.postValue(it.msg)
                    }
                }
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
                    getMessageList()
                }

            }else if (result is NetResult.Error){
                result.exception?.let {
                    messageError.postValue(it.msg)
                }
            }
            isLoading.postValue(false)
        }
    }


    fun readAllMessage(userId : String){
        launch {
            isLoading.postValue(true)
            val result = repo.readAllMessage(userId)

            if (result is NetResult.Success){

                result.data?.let {
                    getMessageList()
                }

            }else if (result is NetResult.Error){
                result.exception?.let {
                    messageError.postValue(it.msg)
                }
            }
            isLoading.postValue(false)
        }
    }


}