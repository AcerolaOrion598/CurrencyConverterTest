package com.example.currencyconvertertest.model

import com.example.currencyconvertertest.model.data_source.CurrencyDataSourceFactory
import com.example.currencyconvertertest.model.network.OnlineServerException
import java.util.*

class MainRepository {
    fun requestExchangeRate(
        baseCurrency: Currency, finalCurrency: Currency, accessKey: String
    ): Double? {
        return try {
            CurrencyDataSourceFactory.getInstance(accessKey = accessKey).requestExchangeRate(
                baseCurrency, finalCurrency
            )
        } catch (e: OnlineServerException) {
            CurrencyDataSourceFactory.getInstance(onlineServerError =  true).requestExchangeRate(
                baseCurrency, finalCurrency
            )
        }
    }
}