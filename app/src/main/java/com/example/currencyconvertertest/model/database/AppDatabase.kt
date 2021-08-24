package com.example.currencyconvertertest.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.currencyconvertertest.model.database.dao.ExchangeRateDao
import com.example.currencyconvertertest.model.database.entity.ExchangeRateTable

@Database(entities = [ExchangeRateTable::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exchangeRateDao(): ExchangeRateDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        @kotlin.jvm.JvmStatic
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context, AppDatabase::class.java, "currency_converter_test_db"
            ).build()
        }
    }
}