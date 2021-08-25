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

    fun getExchangeRateData(): MutableLiveData<Double?> = exchangeRate

    fun getExchangeRate(
        baseCurrency: Currency, resultCurrency: Currency, accessKey: String
    ) {
        val exchangeRate = mainRepository.getExchangeRate(
            baseCurrency, resultCurrency, accessKey
        )
        setExchangeRateData(exchangeRate)
    }

    private fun setExchangeRateData(exchangeRate: Double?) {
        this.exchangeRate.postValue(exchangeRate)
    }
}