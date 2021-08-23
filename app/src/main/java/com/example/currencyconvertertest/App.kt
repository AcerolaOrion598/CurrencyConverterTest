package com.example.currencyconvertertest

import android.app.Application
import com.example.currencyconvertertest.di.AppComponent
import com.example.currencyconvertertest.di.AppModule
import com.example.currencyconvertertest.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}