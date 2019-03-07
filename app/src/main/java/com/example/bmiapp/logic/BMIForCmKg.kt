package com.example.bmiapp.logic

class BMIForCmKg(var mass : Int, var height : Int) : BMI {
    override fun countBMI(): Double {
        val bmi : Int = mass/(height*height)*10000
        return bmi.toDouble()
    }
    // można też
//    override fun countBMI() = mass/(height*height)*10000
}