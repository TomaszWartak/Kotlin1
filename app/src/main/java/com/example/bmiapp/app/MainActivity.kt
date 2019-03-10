package com.example.bmiapp.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.bmiapp.R
import com.example.bmiapp.logic.BMICalc
import com.example.bmiapp.logic.BMICalcForCmKg
import com.example.bmiapp.logic.BMICalcForFeetPounds

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countBmiButton : Button = findViewById(R.id.countBmiButton)
        countBmiButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View?){
                showBmi();

            }
        });

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

    fun showMessage( messageId : Int ) {
        Toast.makeText(applicationContext, messageId, Toast.LENGTH_SHORT).show()
    }

    fun chceckHeight( bmiCalc: BMICalc ) : BMICalc {
        val heightEditText : EditText = findViewById(R.id.heightEditText)
        try {
            bmiCalc.height = heightEditText.text.toString().toInt()
            if (bmiCalc.isHeightTooSmall()) {
                showMessage(R.string.height_too_small_message)
            } else {
                if (bmiCalc.isHeightTooBig()) {
                    showMessage(R.string.height_too_big_message)
                }
            }
        } catch (ex : NumberFormatException) {
            showMessage(R.string.height_incorrect_value)
        }
        return bmiCalc
    }

    fun chceckWeight( bmiCalc: BMICalc ) : BMICalc {
        val weightEditText : EditText = findViewById(R.id.weightEditText)
        try {
            bmiCalc.weight = weightEditText.text.toString().toInt()
            if (bmiCalc.isWeightTooSmall()) {
                showMessage(R.string.weight_too_small_message)
            } else {
                if (bmiCalc.isWeightTooBig()) {
                    showMessage(R.string.weight_too_big_message)
                }
            }
        } catch (ex : NumberFormatException) {
            showMessage(R.string.weight_incorrect_value)
        }
        return bmiCalc
    }

    fun getBmiCalc() : BMICalc? {
        var bmiCalc : BMICalc? = null
        if (true) {
            bmiCalc = BMICalcForCmKg()
        } else {
            bmiCalc = BMICalcForFeetPounds()
        }
        if (chceckHeight(bmiCalc).isHeightValid()) {
            bmiCalc = chceckWeight(bmiCalc)
        }
        return bmiCalc
    }

    fun showBmi()  {
        val bmiCalc : BMICalc? = getBmiCalc()
        if (bmiCalc!!.areAllDataValid()) {
            val bmiValue = bmiCalc?.countBMI()
            val bmiValueLabel : TextView = findViewById(R.id.bmiValueLabel)
            bmiValueLabel.setBackgroundColor(getBmiColor(bmiValue))
            bmiValueLabel.text = "%.2f".format(bmiValue)
        }
    }

}
