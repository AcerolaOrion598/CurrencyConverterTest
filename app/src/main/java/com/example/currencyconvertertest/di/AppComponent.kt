package com.example.currencyconvertertest.di

import com.example.currencyconvertertest.model.MainRepository
import com.example.currencyconvertertest.view_model.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [MainModule::class, AppModule::class])
@Singleton
interface AppComponent {
    fun inject(mainViewModel: MainViewModel)
    fun inject(mainRepository: MainRepository)
}