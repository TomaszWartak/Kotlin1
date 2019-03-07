package com.example.bmiapp.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.bmiapp.R
import com.example.bmiapp.logic.BMI
import com.example.bmiapp.logic.BMIForCmKg
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var bmi : BMIForCmKg = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bmi = BMIForCmKg()
            //.countBMI()
        val countBmiButton : Button = findViewById(R.id.countBmiButton)
        countBmiButton.setOnClickListener(View.OnClickListener {

        })
    }
}
