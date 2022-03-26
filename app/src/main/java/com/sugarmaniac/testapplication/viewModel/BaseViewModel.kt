package com.sugarmaniac.testapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sugarmaniac.testapplication.model.DevicesModel
import com.sugarmaniac.testapplication.service.DeviceApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class BaseViewModel: ViewModel() {
    private val deviceApiService = DeviceApiService()
    private val disposable = CompositeDisposable()

    val deviceData = MutableLiveData<DevicesModel>()

    fun fetchData(){
        disposable.add(
            deviceApiService.getDataService()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<DevicesModel>(){
                    override fun onSuccess(t: DevicesModel) {
                        deviceData.value
                    }

                    override fun onError(e: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
        )
    }

}