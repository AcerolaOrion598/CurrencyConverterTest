package com.example.currencyconvertertest.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.currencyconvertertest.App
import com.example.currencyconvertertest.model.MainRepository
import javax.inject.Inject

@Suppress("ProtectedInFinal")
class MainViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    protected lateinit var mainRepository: MainRepository

    init {
        App.appComponent.inject(this)
    }

    private var exchangeRate: MutableLiveData<Double> = MutableLiveData()

    fun getExchangeRate(): MutableLiveData<Double> = exchangeRate

    fun setExchangeRate(exchangeRate: Double) {
        this.exchangeRate.postValue(exchangeRate)
    }

    fun test(): String {
        return mainRepository.test()
    }
}