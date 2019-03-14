package com.example.bmiapp.logic

class BMICalcForInchesPounds () : BMICalc {

    override var height : Int = 0
    override var weight : Int = 0
    override val MIN_HEIGHT = 55
    override val MAX_HEIGHT = 98
    override val MIN_WEIGHT = 88
    override val MAX_WEIGHT = 661
    override val heightUnit = "in"
    override val weightUnit = "lb"


    constructor(height : Int, weight : Int) : this() {
        this.height = height
        this.weight = weight
    }

    override fun isHeightTooSmall() = height < MIN_HEIGHT

    override fun isHeightTooBig()= height > MAX_HEIGHT

    override fun isWeightTooSmall() = weight < MIN_WEIGHT

    override fun isWeightTooBig() = weight > MAX_WEIGHT

}