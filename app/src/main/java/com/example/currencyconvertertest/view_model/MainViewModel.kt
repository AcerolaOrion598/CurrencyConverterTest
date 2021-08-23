package com.example.currencyconvertertest.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var exchangeRate: MutableLiveData<Double> = MutableLiveData()

    fun getExchangeRate(): MutableLiveData<Double> = exchangeRate

    fun setExchangeRate(exchangeRate: Double) {
        this.exchangeRate.postValue(exchangeRate)
    }
}