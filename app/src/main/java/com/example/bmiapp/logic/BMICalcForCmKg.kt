package com.example.bmiapp.logic

class BMICalcForCmKg() : BMICalc {
    override var height : Int = 0
    override var weight : Int = 0
    override val MIN_HEIGHT = 148
    override val MAX_HEIGHT = 250
    override val MIN_WEIGHT = 40
    override val MAX_WEIGHT = 300
    override val heightUnit = "cm"
    override val weightUnit = "kg"

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