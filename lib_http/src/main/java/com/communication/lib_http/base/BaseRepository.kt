package com.communication.lib_http.base

import com.communication.lib_http.exception.DealException
import com.communication.lib_http.exception.ResultException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

/**
 * Created by LG
 * on 2022/2/28  17:08
 * Description：
 */
open class BaseRepository {

    suspend fun <T : Any> callRequest(
        call: suspend () -> NetResult<T>
    ): NetResult<T> {
        return try {
            call()
        } catch (e: Exception) {
            e.printStackTrace()
            NetResult.Error(DealException.handlerException(e))
        }
    }


    suspend fun <T : Any> handleResponse(
        response : BaseModel<T>,
        successBlock : (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock : (suspend CoroutineScope.() -> Unit)? = null
    ):NetResult<T> {

        return coroutineScope {

            if (response.code == 200 || response.code == 0){
                successBlock?.let { it() }
                NetResult.Success(response.data)
            }else{
                errorBlock?.let { it() }
                NetResult.Error(
                    ResultException(
                        response.code.toString(),
                        response.message
                    )
                )
            }

        }

    }

    suspend fun <T : Any> handleResponse1(
        response : TokenModel<T>,
        successBlock : (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock : (suspend CoroutineScope.() -> Unit)? = null
    ):NetResult<T> {

        return coroutineScope {

            if (response.code == 200 || response.code == 0){
                successBlock?.let { it() }
                NetResult.Success(response.token)
            }else{
                errorBlock?.let { it() }
                NetResult.Error(
                    ResultException(
                        response.code.toString(),
                        response.msg
                    )
                )
            }

        }

    }


}