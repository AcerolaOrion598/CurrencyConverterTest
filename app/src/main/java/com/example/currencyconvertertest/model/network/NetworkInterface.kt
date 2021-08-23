package com.example.currencyconvertertest.model.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkInterface {

    @GET("v1/latest")
    fun getExchangeRate(
        @Query("base") baseCurrencyCode: String,
        @Query("symbols") finalCurrenciesCodes: String,
        @Query("access_key") accessKey: String
    ): Call<ResponseBody>
}