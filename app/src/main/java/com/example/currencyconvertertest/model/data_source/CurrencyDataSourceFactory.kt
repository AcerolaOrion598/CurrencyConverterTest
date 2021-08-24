package com.example.currencyconvertertest.model.data_source

import com.example.currencyconvertertest.utils.Utils

class CurrencyDataSourceFactory {
    companion object {
        private var onlineInstance: OnlineCurrencyDataSource? = null
        private var offlineInstance: OfflineCurrencyDataSource? = null

        fun getInstance(accessKey: String): ICurrencyDataSource {
            return if (Utils.isServerAvailable()) {
                if (onlineInstance == null)
                    onlineInstance = OnlineCurrencyDataSource(accessKey)
                onlineInstance!!
            } else {
                if (offlineInstance == null)
                    offlineInstance = OfflineCurrencyDataSource()
                offlineInstance!!
            }
        }
    }
}