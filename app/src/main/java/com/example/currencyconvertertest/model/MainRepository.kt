package com.example.currencyconvertertest.model

import com.example.currencyconvertertest.model.data_source.CurrencyDataSourceFactory
import java.util.*

class MainRepository {
    fun requestExchangeRate(
        baseCurrency: Currency, finalCurrency: Currency, accessKey: String
    ): Double? {
        val currencyDataSource = CurrencyDataSourceFactory.getInstance(accessKey)
        return currencyDataSource.requestExchangeRate(baseCurrency, finalCurrency)
    }
}