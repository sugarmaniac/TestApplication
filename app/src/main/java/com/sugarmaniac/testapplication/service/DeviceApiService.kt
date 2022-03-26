package com.sugarmaniac.testapplication.service

import com.sugarmaniac.testapplication.model.DevicesModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class DeviceApiService {

    private val BASE_URL = "https://veramobile.mios.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(DeviceApi::class.java)

    fun getDataService(): Single<DevicesModel> {
        return api.getData()
    }
}