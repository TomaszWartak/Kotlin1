package com.example.bmiapp.logic

class BMICalcForFeetPounds () : BMICalc {

    override var height : Int = 0
    override var weight : Int = 0
    private val MIN_HEIGHT = 326.28
    private val MAX_HEIGHT = 551.16
    private val MIN_WEIGHT = 88.18
    private val MAX_WEIGHT = 661.39

    constructor(height : Int, weight : Int) : this() {
        this.height = height
        this.weight = weight
    }

    override fun isHeightTooSmall() : Boolean {
        return height<MIN_HEIGHT
    }

    override fun isHeightTooBig(): Boolean {
        return height > MAX_HEIGHT
    }

    override fun isWeightTooSmall(): Boolean {
        return weight<MIN_WEIGHT
    }

    override fun isWeightTooBig(): Boolean {
        return weight>MAX_WEIGHT
    }

}