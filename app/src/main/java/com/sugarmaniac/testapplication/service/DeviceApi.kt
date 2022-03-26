package com.sugarmaniac.testapplication.service

import com.sugarmaniac.testapplication.model.DevicesModel
import io.reactivex.Single
import retrofit2.http.GET

interface DeviceApi {

    @GET("test_android/items.test")
    fun getData(): Single<DevicesModel>
}