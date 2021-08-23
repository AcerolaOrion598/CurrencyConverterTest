package com.example.currencyconvertertest.di

import com.example.currencyconvertertest.model.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    @Singleton
    fun provideMainRepository(): MainRepository = MainRepository()
}