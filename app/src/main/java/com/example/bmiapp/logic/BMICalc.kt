package com.example.bmiapp.logic

interface BMICalc {

    var height : Int
    var weight : Int
    val MIN_HEIGHT : Int
    val MAX_HEIGHT : Int
    val MIN_WEIGHT : Int
    val MAX_WEIGHT : Int
    val heightUnit : String
    val weightUnit : String

    companion object {
        val KG_LB_CONVERTER = 2.2046
        val CM_IN_CONVERTER = 0.39370
        val BMI_BOUNDS = arrayOf(18.5, 24.9, 29.9, 34.9)
        val BMI_COLORS = arrayOf(
            0xff99bbff.toInt(), 0xff00e6ac.toInt(), 0xffffff00.toInt(), 0xffff9933.toInt(), 0xffe60000.toInt())
    }

    fun countBMI(): Double {
        val bmi : Double = weight*10000/(height*height).toDouble()
        return bmi
    }

    fun isHeightTooSmall() : Boolean
    fun isHeightTooBig() : Boolean
    fun isWeightTooSmall() : Boolean
    fun isWeightTooBig() : Boolean

    fun isHeightValid() : Boolean {
        return !( isHeightTooSmall() || isHeightTooBig() )
    }

    fun isWeightValid() : Boolean {
        return !( isWeightTooSmall() || isWeightTooBig() )
    }

    fun areAllDataValid() : Boolean {
        return ( isHeightValid() && isWeightValid() )
    }
}