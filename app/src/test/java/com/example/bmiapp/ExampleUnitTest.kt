package com.example.bmiapp

import com.example.bmiapp.logic.BMICalcForCmKg
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun for_valid_weight_and_height_return_bmi() {
        val bmi = BMICalcForCmKg(185,76)
           assertEquals(22.21, bmi.countBMI(), 0.01)
    }

    @Test
    fun weight_is_valid() {
        val bmi = BMICalcForCmKg(185,410)
        assertTrue(bmi.isWeightValid())
    }

    @Test
    fun height_is_valid() {
        val bmi = BMICalcForCmKg(185,410)
        assertTrue(bmi.isHeightValid())
    }
}
