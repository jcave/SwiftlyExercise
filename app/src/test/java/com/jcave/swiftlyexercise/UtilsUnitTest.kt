package com.jcave.swiftlyexercise

import com.jcave.swiftlyexercise.utils.Utils
import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsUnitTest {

    @Test
    fun test_formatCurrency() {
        val price1 = Utils.formatCurrency(2.0f)
        assertEquals("$2.00", price1)

        val price2 = Utils.formatCurrency(2.234f)
        assertEquals("$2.23", price2)
    }

}