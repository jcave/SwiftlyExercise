package com.jcave.swiftlyexercise.utils

import java.text.NumberFormat
import java.util.*

object Utils {

    fun formatCurrency(price: Float): String {
        val priceFormat: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        return priceFormat.format(price)
    }

}