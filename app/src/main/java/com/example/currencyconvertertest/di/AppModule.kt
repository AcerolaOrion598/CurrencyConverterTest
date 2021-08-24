package com.example.currencyconvertertest.di

import android.content.Context
import com.example.currencyconvertertest.App
import com.example.currencyconvertertest.model.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase = AppDatabase.getInstance(context)
}