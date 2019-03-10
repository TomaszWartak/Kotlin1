package com.example.bmiapp.logic

class BMICalcForCmKg() : BMICalc {
    override var height : Int = 0
    override var weight : Int = 0
    private val  MIN_HEIGHT : Int = 148
    private val MAX_HEIGHT = 250
    private val MIN_WEIGHT = 40
    private val MAX_WEIGHT = 300

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