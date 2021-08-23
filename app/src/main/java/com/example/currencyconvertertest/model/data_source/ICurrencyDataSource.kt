package com.example.currencyconvertertest.model.data_source

import java.util.*

interface ICurrencyDataSource {
    fun requestExchangeRate(
        baseCurrency: Currency, finalCurrency: Currency, accessKey: String
    ): Double?
}