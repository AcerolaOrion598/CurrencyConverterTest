package com.example.currencyconvertertest.di

import com.example.currencyconvertertest.model.MainRepository
import com.example.currencyconvertertest.model.network.NetworkInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    @Singleton
    fun provideMainRepository(): MainRepository = MainRepository()

    @Provides
    @Singleton
    fun provideNetworkInterface(): NetworkInterface = Retrofit
        .Builder()
        .baseUrl("http://api.exchangeratesapi.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NetworkInterface::class.java)
}