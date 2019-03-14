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
    var bmiLabelBackgroundColor : Int = 0

    var heightEditText: EditText? = null
    var weightEditText: EditText? = null
    var countBmiButton : Button? = null
    var euUsMeasuresSwitch: Switch? = null
    var usMeasureLabel : TextView? = null
    var bmiValueLabel : TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        setBmiCalc()
    }

    fun initUI() {
        heightEditText = findViewById(R.id.heightEditText)
        // todo kolejność przechodzenia fokusa
        /*
        heightEditText!!.nextFocusDownId = R.id.weightEditText
        heightEditText!!.nextFocusRightId = R.id.weightEditText
        heightEditText!!.nextFocusUpId = R.id.weightEditText
        heightEditText!!.nextFocusLeftId = R.id.weightEditText
        */
        weightEditText = findViewById(R.id.weightEditText)
        countBmiButton = findViewById(R.id.countBmiButton)
        countBmiButton!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                showBmi()
            }
        })
        euUsMeasuresSwitch = findViewById(R.id.euUsMeasuresSwitch)
        euUsMeasuresSwitch!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                setBmiCalc()
                setHints()
            }
        })
        usMeasureLabel = findViewById(R.id.usMeasureLabel)
        bmiValueLabel = findViewById(R.id.bmiValueLabel)
        startHintColor = heightEditText!!.currentHintTextColor
    }

    fun showBmi() {
        setBmiCalc()
        if (chceckHeight(bmiCalc!!).isHeightValid()) {
            bmiCalc = chceckWeight(bmiCalc!!)
        }
        if (bmiCalc!!.areAllDataValid()) {
            val bmiValue = bmiCalc?.countBMI()
            bmiValueLabel = findViewById(R.id.bmiValueLabel)
            bmiLabelBackgroundColor = getBmiColor(bmiValue!!)
            bmiValueLabel!!.setBackgroundColor(bmiLabelBackgroundColor)
            bmiValueLabel!!.text = "%.2f".format(bmiValue)
        }
    }

    fun setHints() {
        //setBmiCalc()

        //val heightEditText: EditText = findViewById(R.id.heightEditText)
        heightEditText!!.hint = "[" + bmiCalc?.heightUnit + "]"

        //val weightEditText: EditText = findViewById(R.id.weightEditText)
        weightEditText!!.hint = "[" + bmiCalc?.weightUnit + "]"

        //val usMeasureLabel: TextView = findViewById(R.id.usMeasureLabel)

        //var euUsMeasuresSwitch: Switch = findViewById(R.id.euUsMeasuresSwitch)

        if (euUsMeasuresSwitch!!.isChecked) {
            heightEditText!!.setHintTextColor(Color.RED)
            weightEditText!!.setHintTextColor(Color.RED)
            usMeasureLabel!!.setTextColor(Color.RED)
        } else {
            heightEditText!!.setHintTextColor(startHintColor)
            weightEditText!!.setHintTextColor(startHintColor)
            usMeasureLabel!!.setTextColor(startHintColor)
        }
    }

    fun setBmiCalc() {
        //var euUsMeasuresSwitch: Switch = findViewById(R.id.euUsMeasuresSwitch)
        if (euUsMeasuresSwitch!!.isChecked) {
            bmiCalc = BMICalcForInchesPounds()
        } else {
            bmiCalc = BMICalcForCmKg()
        }
        /*
        if (chceckHeight(bmiCalc!!).isHeightValid()) {
            bmiCalc = chceckWeight(bmiCalc!!)
        }
        */
    }

    fun chceckHeight(bmiCalc: BMICalc): BMICalc {
        //val heightEditText: EditText = findViewById(R.id.heightEditText)
        try {
            bmiCalc.height = heightEditText!!.text.toString().toInt()
            if (bmiCalc.isHeightTooSmall()) {
                showMessage(
                    getString(R.string.height_too_small_message) + " " +
                            bmiCalc.MIN_HEIGHT + " " +
                            bmiCalc.heightUnit
                )
            } else {
                if (bmiCalc.isHeightTooBig()) {
                    showMessage(
                        getString(
                            R.string.height_too_big_message
                        ) + " " +
                                bmiCalc.MAX_HEIGHT + " " +
                                bmiCalc.heightUnit
                    )
                }
            }
        } catch (ex: NumberFormatException) {
            showMessage(getString(R.string.height_incorrect_value)+" ["+bmiCalc.heightUnit+"]")
        }
        return bmiCalc
    }

    fun chceckWeight(bmiCalc: BMICalc): BMICalc {
        //val weightEditText: EditText = findViewById(R.id.weightEditText)
        try {
            bmiCalc.weight = weightEditText!!.text.toString().toInt()
            if (bmiCalc.isWeightTooSmall()) {
                showMessage(
                    getString(
                        R.string.weight_too_small_message
                    ) + " " +
                            bmiCalc.MIN_WEIGHT + " " +
                            bmiCalc.weightUnit
                )
            } else {
                if (bmiCalc.isWeightTooBig()) {
                    showMessage(
                        getString(
                            R.string.weight_too_big_message
                        ) + " " +
                                bmiCalc.MAX_WEIGHT + " " +
                                bmiCalc.weightUnit
                    )
                }
            }
        } catch (ex: NumberFormatException) {
            showMessage(
                getString(R.string.weight_incorrect_value)+" ["+bmiCalc.weightUnit+"]")
        }
        return bmiCalc
    }

    fun getBmiColor(bmiValue: Double): Int {
        var bmiColor: Int = 0
        when (bmiValue) {
            in 0.0..BMICalc.BMI_BOUNDS[0] -> bmiColor = BMICalc.BMI_COLORS[0]
            in BMICalc.BMI_BOUNDS[0]..BMICalc.BMI_BOUNDS[1] -> bmiColor = BMICalc.BMI_COLORS[1]
            in BMICalc.BMI_BOUNDS[1]..BMICalc.BMI_BOUNDS[2] -> bmiColor = BMICalc.BMI_COLORS[2]
            in BMICalc.BMI_BOUNDS[2]..BMICalc.BMI_BOUNDS[3] -> bmiColor = BMICalc.BMI_COLORS[3]
            in BMICalc.BMI_BOUNDS[3]..10000.0 -> bmiColor = BMICalc.BMI_COLORS[4]
        }
        return bmiColor
    }

    fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveHeightEditTextValues(outState)
        saveWeightEditText(outState)
        saveEuUsMeasuresSwitch(outState)
        saveUsMeasureLabel(outState)
        saveBmiValueLabel(outState)
    }

    fun saveHeightEditTextValues(outState: Bundle) {
        outState.putString("heightEditTextText", heightEditText!!.text.toString() )
        outState.putString("heightEditTextHint", heightEditText!!.hint.toString() )
        outState.putInt("heightEditTextHintColor", heightEditText!!.currentHintTextColor )
    }

    fun saveWeightEditText(outState: Bundle) {
        outState.putString("weightEditTextText", weightEditText!!.text.toString() )
        outState.putString("weightEditTextHint", weightEditText!!.hint.toString() )
        outState.putInt("weightEditTextHintColor", weightEditText!!.currentHintTextColor )
    }

    fun saveEuUsMeasuresSwitch(outState: Bundle) {
        outState.putBoolean("euUsMeasuresSwitchIsChecked", euUsMeasuresSwitch!!.isChecked )
    }

    fun saveUsMeasureLabel(outState: Bundle) {
        outState.putInt("usMeasureLabelTextColor", usMeasureLabel!!.currentTextColor )
    }

    fun saveBmiValueLabel(outState: Bundle) {
        outState.putString("bmiValueLabelText", bmiValueLabel!!.text.toString())
        outState.putInt("bmiValueLabelBackgroundColor", bmiLabelBackgroundColor )
    }

    override fun onRestoreInstanceState( savedInstanceState : Bundle) {
        restoreHeightEditTextValues(savedInstanceState)
        restoreWeightEditText(savedInstanceState)
        restoreEuUsMeasuresSwitch(savedInstanceState)
        restoreUsMeasureLabel(savedInstanceState)
        restoreBmiValueLabel(savedInstanceState)
    }

    fun restoreHeightEditTextValues(savedInstanceState : Bundle) {
        heightEditText!!.setText(savedInstanceState.getString("heightEditTextText" ))
        heightEditText!!.hint = savedInstanceState.getString("heightEditTextHint")
        heightEditText!!.setHintTextColor(savedInstanceState.getInt("heightEditTextHintColor" ))
    }

    fun restoreWeightEditText(savedInstanceState: Bundle) {
        weightEditText!!.setText(savedInstanceState.getString("weightEditTextText" ))
        weightEditText!!.hint = savedInstanceState.getString("weightEditTextHint")
        weightEditText!!.setHintTextColor(savedInstanceState.getInt("weightEditTextHintColor" ))
    }

    fun restoreEuUsMeasuresSwitch(savedInstanceState: Bundle) {
        euUsMeasuresSwitch!!.isChecked = savedInstanceState.getBoolean("euUsMeasuresSwitchIsChecked")
    }

    fun restoreUsMeasureLabel(savedInstanceState: Bundle) {
        usMeasureLabel!!.setTextColor(savedInstanceState.getInt("usMeasureLabelTextColor"  ))
    }

    fun restoreBmiValueLabel(savedInstanceState: Bundle) {
        bmiValueLabel!!.text = savedInstanceState.getCharSequence("bmiValueLabelText")
        bmiValueLabel!!.setBackgroundColor(savedInstanceState.getInt("bmiValueLabelBackgroundColor"))
    }

}
