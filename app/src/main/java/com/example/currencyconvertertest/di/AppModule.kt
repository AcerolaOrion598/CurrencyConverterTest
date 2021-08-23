package com.example.currencyconvertertest.di

import android.content.Context
import com.example.currencyconvertertest.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    fun provideContext(): Context = app
}