package com.communication.lib_http.api

import android.util.Log
import com.communication.lib_http.BuildConfig
import com.communication.lib_http.interceptor.AuthorizationInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by LG
 * on 2022/3/4  9:53
 * Description：
 */

const val SERVER_BASE_URL = "http://192.168.1.236:8098"
//const val SERVER_BASE_URL = "http://192.168.40.99:8004"

const val TIME_OUT = 15L


val httpModule = module {
    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor { message ->
            Log.w(
                "httpLoggingInterceptor", message
            )
        }
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        httpLoggingInterceptor
    }

    single {

        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(AuthorizationInterceptor())
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build()


    }
    single<Retrofit>() {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .baseUrl(SERVER_BASE_URL)
            .build()
    }

    single<LoginApi> {
        get<Retrofit>().create(LoginApi::class.java)
    }

    single<MeApi> {
        get<Retrofit>().create(MeApi::class.java)
    }
}