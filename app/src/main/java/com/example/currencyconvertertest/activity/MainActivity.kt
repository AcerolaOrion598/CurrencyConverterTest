package com.example.currencyconvertertest.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconvertertest.R
import com.example.currencyconvertertest.view_model.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var baseCurrencyEd: EditText
    private var currentExchangeRate: Double? = null
    private var finalCurrency: Currency? = null
    private var rateValueTV: TextView? = null
    private var resultSumTv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        baseCurrencyEd = findViewById(R.id.base_currency_ed)
        rateValueTV = findViewById(R.id.rate_value_tv)
        resultSumTv = findViewById(R.id.result_sum_tv)

        baseCurrencyEd.addTextChangedListener {
            printResult()
        }

        setupCurrencySpinner()

        mainViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(
            MainViewModel::class.java
        )
        mainViewModel.getExchangeRate().observe(this, {
            currentExchangeRate = it
            rateValueTV?.text = it?.toString() ?: ""
            printResult()
        })
    }

    private fun setupCurrencySpinner() {
        val codeArray = resources.getStringArray(R.array.currencyCodes)
        val currencyList = mutableListOf<Currency>()

        codeArray.forEach {
            currencyList.add(Currency.getInstance(it))
        }

        val currencySpinner = findViewById<AppCompatSpinner>(R.id.currency_spinner)
        currencySpinner.adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, currencyList
        )
        currencySpinner.setSelection(0)
        currencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                parent?.let {
                    val selectedCurrency = it.selectedItem as Currency
                    finalCurrency = selectedCurrency
                    rateValueTV?.text = ""
                    resultSumTv?.text = ""
                    GlobalScope.launch(Dispatchers.IO) {
                        mainViewModel.requestExchangeRate(
                            Currency.getInstance("EUR"), selectedCurrency,
                            getString(R.string.access_key)
                        )
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun printResult() {
        finalCurrency?.let {
            val baseCurrencyStr = baseCurrencyEd.text.toString()

            val result = when {
                baseCurrencyStr.isEmpty() -> ""
                currentExchangeRate == null -> getString(R.string.something_went_wrong)
                else ->
                    try {
                        "${it.symbol} ${String.format("%.2f", baseCurrencyStr.toDouble() * currentExchangeRate!!)}"
                    } catch (e: NumberFormatException) {
                        getString(R.string.unacceptable_characters)
                    }
            }

            resultSumTv?.text = result
        }
    }
}