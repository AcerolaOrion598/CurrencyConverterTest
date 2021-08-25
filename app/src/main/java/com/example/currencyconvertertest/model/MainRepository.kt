package com.example.currencyconvertertest.model

import com.example.currencyconvertertest.model.data_source.CurrencyDataSourceFactory
import com.example.currencyconvertertest.model.network.OnlineServerException
import java.util.*

class MainRepository {
    fun getExchangeRate(
        baseCurrency: Currency, finalCurrency: Currency, accessKey: String
    ): Double? {
        return try {
            requestExchangeRate(baseCurrency, finalCurrency, accessKey = accessKey)
        } catch (e: OnlineServerException) {
            requestExchangeRate(baseCurrency, finalCurrency, onlineServerError = true)
        }
    }

    private fun requestExchangeRate(
        baseCurrency: Currency,
        finalCurrency: Currency,
        accessKey: String = "",
        onlineServerError: Boolean = false
    ): Double? {
        return CurrencyDataSourceFactory.getInstance(
            accessKey, onlineServerError
        ).requestExchangeRate(baseCurrency, finalCurrency)
    }
}