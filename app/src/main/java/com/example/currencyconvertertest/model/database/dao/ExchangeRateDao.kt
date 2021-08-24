package com.example.currencyconvertertest.model.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currencyconvertertest.model.database.entity.ExchangeRateTable

@Dao
interface ExchangeRateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exchangeRateTable: ExchangeRateTable)

    @Query(
        "select ${ExchangeRateTable.rateFieldName} " +
        "from ${ExchangeRateTable.tableName} where 1 = 1 " +
        "and ${ExchangeRateTable.baseCurrencyCodeFieldName} = :baseCurrencyCode " +
        "and ${ExchangeRateTable.finalCurrencyCodeFieldName} = :finalCurrencyCode"
    )
    fun requestExchangeRate(baseCurrencyCode: String, finalCurrencyCode: String): Double?
}