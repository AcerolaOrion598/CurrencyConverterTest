package com.example.currencyconvertertest.model.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = ExchangeRateTable.tableName,
    primaryKeys =[
        ExchangeRateTable.baseCurrencyCodeFieldName,
        ExchangeRateTable.finalCurrencyCodeFieldName
    ]
)
data class ExchangeRateTable(
    @ColumnInfo(name = baseCurrencyCodeFieldName)
    val baseCurrency: String,

    @ColumnInfo(name = finalCurrencyCodeFieldName)
    val finalCurrencyCode: String,

    @ColumnInfo(name = rateFieldName)
    val rate: Double
) {
    companion object {
        const val tableName = "exchange_rate_table"
        const val baseCurrencyCodeFieldName = "base_currency_code"
        const val finalCurrencyCodeFieldName = "final_currency_code"
        const val rateFieldName = "rate"
    }
}