package com.example.currencyconvertertest.model.data_source

import com.example.currencyconvertertest.App
import com.example.currencyconvertertest.model.network.NetworkInterface
import org.json.JSONObject
import java.util.*
import javax.inject.Inject

@Suppress("ProtectedInFinal")
class OnlineCurrencyDataSource : ICurrencyDataSource {

    @Inject
    protected lateinit var networkInterface: NetworkInterface

    init {
        App.appComponent.inject(this)
    }

    override fun requestExchangeRate(
        baseCurrency: Currency,
        finalCurrency: Currency,
        accessKey: String
    ): Double? {
        val response = networkInterface.getExchangeRate(
            baseCurrency.currencyCode, finalCurrency.currencyCode, accessKey
        ).execute()
        if (response.isSuccessful) {
            val rawResponse = response.body()?.string()
            rawResponse?.let {
                return JSONObject(it).getJSONObject("rates").getDouble(
                    finalCurrency.currencyCode
                )
            }
        }
        return null
    }
}