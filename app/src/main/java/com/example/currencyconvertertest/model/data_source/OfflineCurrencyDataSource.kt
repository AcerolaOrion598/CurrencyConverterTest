package com.example.currencyconvertertest.model.data_source

import com.example.currencyconvertertest.App
import com.example.currencyconvertertest.model.database.AppDatabase
import java.util.*
import javax.inject.Inject

@Suppress("ProtectedInFinal")
class OfflineCurrencyDataSource : ICurrencyDataSource {

    @Inject
    protected lateinit var appDatabase: AppDatabase

    init {
        App.appComponent.inject(this)
    }

    override fun requestExchangeRate(
        baseCurrency: Currency,
        finalCurrency: Currency
    ): Double? {
        return appDatabase.exchangeRateDao().requestExchangeRate(
            baseCurrency.currencyCode, finalCurrency.currencyCode
        )
    }
}