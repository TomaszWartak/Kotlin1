package com.example.bmiapp.app

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.bmiapp.R
import com.example.bmiapp.logic.BMICalc
import com.example.bmiapp.logic.BMICalcForCmKg
import com.example.bmiapp.logic.BMICalcForInchesPounds

class MainActivity : AppCompatActivity() {

    var bmiCalc : BMICalc? = null
    var startHintColor : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countBmiButton : Button = findViewById(R.id.countBmiButton)
        countBmiButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View?){
                showBmi()
            }
        })

        var euUsMeasuresSwitch : Switch = findViewById(R.id.euUsMeasuresSwitch)
        euUsMeasuresSwitch.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View?){
                setHints()
            }
        })
        val heightEditText : EditText = findViewById(R.id.heightEditText)
        startHintColor = heightEditText.currentHintTextColor
        setBmiCalc()
    }

    fun setHints() {
        setBmiCalc()

        val heightEditText : EditText = findViewById(R.id.heightEditText)
        heightEditText.hint = "["+bmiCalc?.heightUnit+"]"

        val weightEditText : EditText = findViewById(R.id.weightEditText)
        weightEditText.hint = "["+bmiCalc?.weightUnit+"]"

        val usMeasureLabel : TextView = findViewById(R.id.usMeasureLabel)

        var euUsMeasuresSwitch : Switch = findViewById(R.id.euUsMeasuresSwitch)
        if (euUsMeasuresSwitch.isChecked) {
            heightEditText.setHintTextColor(Color.RED)
            weightEditText.setHintTextColor(Color.RED)
            usMeasureLabel.setTextColor(Color.RED)
        } else {
            heightEditText.setHintTextColor(startHintColor)
            weightEditText.setHintTextColor(startHintColor)
            usMeasureLabel.setTextColor(startHintColor)
        }
    }

    fun getBmiColor(bmiValue : Double) : Int {
        var bmiColor : Int = 0
        when (bmiValue) {
            in 0.0..BMICalc.BMI_BOUNDS[0] -> bmiColor = BMICalc.BMI_COLORS[0]
            in BMICalc.BMI_BOUNDS[0]..BMICalc.BMI_BOUNDS[1] -> bmiColor = BMICalc.BMI_COLORS[1]
            in BMICalc.BMI_BOUNDS[1]..BMICalc.BMI_BOUNDS[2] -> bmiColor = BMICalc.BMI_COLORS[2]
            in BMICalc.BMI_BOUNDS[2]..BMICalc.BMI_BOUNDS[3] -> bmiColor = BMICalc.BMI_COLORS[3]
            in BMICalc.BMI_BOUNDS[3]..10000.0 -> bmiColor = BMICalc.BMI_COLORS[4]
        }
        return bmiColor
    }

    fun showMessage( message : String ) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    fun chceckHeight( bmiCalc: BMICalc ) : BMICalc {
        val heightEditText : EditText = findViewById(R.id.heightEditText)
        try {
            bmiCalc.height = heightEditText.text.toString().toInt()
            if (bmiCalc.isHeightTooSmall()) {
                showMessage(
                    getString(R.string.height_too_small_message)+" "+
                    bmiCalc.MIN_HEIGHT+" "+
                    bmiCalc.heightUnit)
            } else {
                if (bmiCalc.isHeightTooBig()) {
                    showMessage(
                        getString(
                            R.string.height_too_big_message)+" "+
                            bmiCalc.MAX_HEIGHT+" "+
                            bmiCalc.heightUnit)
                }
            }
        } catch (ex : NumberFormatException) {
            showMessage(getString(R.string.height_incorrect_value))
        }
        return bmiCalc
    }

    fun chceckWeight( bmiCalc: BMICalc ) : BMICalc {
        val weightEditText : EditText = findViewById(R.id.weightEditText)
        try {
            bmiCalc.weight = weightEditText.text.toString().toInt()
            if (bmiCalc.isWeightTooSmall()) {
                showMessage(
                    getString(
                        R.string.weight_too_small_message)+" "+
                        bmiCalc.MIN_WEIGHT+" "+
                        bmiCalc.weightUnit)
            } else {
                if (bmiCalc.isWeightTooBig()) {
                    showMessage(
                        getString(
                            R.string.weight_too_big_message)+" "+
                            bmiCalc.MAX_WEIGHT+" "+
                            bmiCalc.weightUnit)
                }
            }
        } catch (ex : NumberFormatException) {
            showMessage(getString(R.string.weight_incorrect_value))
        }
        return bmiCalc
    }

    fun setBmiCalc() {
        var euUsMeasuresSwitch : Switch = findViewById(R.id.euUsMeasuresSwitch)
        if (euUsMeasuresSwitch.isChecked) {
            bmiCalc = BMICalcForInchesPounds()
        } else {
            bmiCalc = BMICalcForCmKg()
        }
        if (chceckHeight(bmiCalc!!).isHeightValid()) {
            bmiCalc = chceckWeight(bmiCalc!!)
        }
    }

    fun showBmi()  {
        setBmiCalc()
        if (bmiCalc!!.areAllDataValid()) {
            val bmiValue = bmiCalc?.countBMI()
            val bmiValueLabel : TextView = findViewById(R.id.bmiValueLabel)
            bmiValueLabel.setBackgroundColor(getBmiColor(bmiValue!!))
            bmiValueLabel.text = "%.2f".format(bmiValue)
        }
    }

}
