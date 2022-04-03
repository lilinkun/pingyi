package com.communication.lib_http.httpdata.home

/**
 * Created by LG
 * on 2022/4/2  10:36
 * Description：
 */
data class HomeFlowBean(
    val cumulativeTraffic : Int,
    val historyLowestVehicle : Int,
    val historyHighestVehicle : Int
)