package com.example.currencyconvertertest.activity

import android.os.Bundle
import android.view.View
import android.widget.*
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
    private var currentExchangeRate: Double = 0.0
    private var currentCurrency: Currency? = null
    private var resultSumTv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        baseCurrencyEd = findViewById(R.id.base_currency_ed)
        resultSumTv = findViewById(R.id.result_sum_tv)

        baseCurrencyEd.addTextChangedListener {
            printResult()
        }

        setupCurrencySpinner()

        mainViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(
            MainViewModel::class.java
        )
        mainViewModel.getExchangeRate().observe(this, { exchangeRate ->
            currentExchangeRate = exchangeRate
            printResult()
        })
        mainViewModel.setExchangeRate(2.0)
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
                    currentCurrency = it.selectedItem as Currency
                    resultSumTv?.text = ""
                    GlobalScope.launch(Dispatchers.IO) {
                        Thread.sleep(1000)
                        mainViewModel.setExchangeRate(3.0)
                    }
                    Toast.makeText(this@MainActivity, mainViewModel.test(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun printResult() {
        currentCurrency?.let {
            val baseCurrencyStr = baseCurrencyEd.text.toString()
            val result = if (baseCurrencyStr.isNotEmpty())
                try {
                    "${it.symbol} ${baseCurrencyStr.toDouble() * currentExchangeRate}"
                } catch (e: NumberFormatException) {
                    getString(R.string.unacceptable_characters)
                }
            else
                ""

            resultSumTv?.text = result
        }
    }
}