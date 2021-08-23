package com.example.currencyconvertertest.model.data_source

import java.util.*

class OfflineCurrencyDataSource : ICurrencyDataSource {
    override fun requestExchangeRate(
        baseCurrency: Currency,
        finalCurrency: Currency,
        accessKey: String
    ): Double? {
        return 2.0
    }
}