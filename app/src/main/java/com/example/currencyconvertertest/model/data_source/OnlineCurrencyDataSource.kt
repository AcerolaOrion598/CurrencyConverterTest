package com.example.currencyconvertertest.model.data_source

import com.example.currencyconvertertest.App
import com.example.currencyconvertertest.model.database.AppDatabase
import com.example.currencyconvertertest.model.database.entity.ExchangeRateTable
import com.example.currencyconvertertest.model.network.NetworkInterface
import com.example.currencyconvertertest.model.network.OnlineServerException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import javax.inject.Inject

@Suppress("ProtectedInFinal")
class OnlineCurrencyDataSource(private val accessKey: String) : ICurrencyDataSource {

    @Inject
    protected lateinit var networkInterface: NetworkInterface

    @Inject
    protected lateinit var appDatabase: AppDatabase

    init {
        App.appComponent.inject(this)
    }

    override fun requestExchangeRate(
        baseCurrency: Currency,
        finalCurrency: Currency
    ): Double? {
        val response = networkInterface.getExchangeRate(
            baseCurrency.currencyCode, finalCurrency.currencyCode, accessKey
        ).execute()
        if (response.isSuccessful) {
            val rawResponse = response.body()?.string()
            rawResponse?.let {
                return try {
                    val exchangeRate = JSONObject(it).getJSONObject("rates").getDouble(
                        finalCurrency.currencyCode
                    )
                    GlobalScope.launch(Dispatchers.IO) {
                        storeExchangeRate(baseCurrency, finalCurrency, exchangeRate)
                    }
                    exchangeRate
                } catch (e: JSONException) {
                    null
                }
            }
        }
        throw OnlineServerException()
    }

    private fun storeExchangeRate(
        baseCurrency: Currency, finalCurrency: Currency, exchangeRate: Double
    ) {
        val table = ExchangeRateTable(
            baseCurrency.currencyCode, finalCurrency.currencyCode, exchangeRate
        )
        appDatabase.exchangeRateDao().insert(table)
    }
}