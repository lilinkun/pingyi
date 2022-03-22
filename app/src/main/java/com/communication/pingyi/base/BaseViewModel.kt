package com.communication.pingyi.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by LG
 * on 2022/3/14  18:01
 * Description：
 */
open class BaseViewModel : ViewModel(){

    val isLoading = MutableLiveData<Boolean>()

    fun launch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.Default) {
            block()
        }
    }


}