package com.example.currencyconvertertest.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.currencyconvertertest.App
import com.example.currencyconvertertest.model.MainRepository
import java.util.*
import javax.inject.Inject

@Suppress("ProtectedInFinal")
class MainViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    protected lateinit var mainRepository: MainRepository

    init {
        App.appComponent.inject(this)
    }

    private var exchangeRate: MutableLiveData<Double?> = MutableLiveData()

    fun getExchangeRate(): MutableLiveData<Double?> = exchangeRate

    fun requestExchangeRate(
        baseCurrency: Currency, resultCurrency: Currency, accessKey: String
    ) {
        val exchangeRate = mainRepository.requestExchangeRate(
            baseCurrency, resultCurrency, accessKey
        )
        setExchangeRate(exchangeRate)
    }

    private fun setExchangeRate(exchangeRate: Double?) {
        this.exchangeRate.postValue(exchangeRate)
    }
}