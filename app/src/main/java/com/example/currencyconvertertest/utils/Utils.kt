package com.example.currencyconvertertest.utils

class Utils {
    companion object {
        fun isServerAvailable(): Boolean {
            val command = "ping -c 1 api.exchangeratesapi.io"
            return Runtime.getRuntime().exec(command).waitFor() == 0
        }
    }
}