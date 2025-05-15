package com.example.personalityquiz

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val message = intent.getStringExtra("EXTRA_MESSAGE")
        val resultText = findViewById<TextView>(R.id.resultTextView)
        resultText.text = "Wynik: $day"
    }

    val checkbox1 = intent.getBooleanExtra("checkbox1", false)
    val checkbox2 = intent.getBooleanExtra("checkbox2", false)
    val checkbox3 = intent.getBooleanExtra("checkbox3", false)

    val spinnerItem = intent.getStringExtra("spinnerItem")

    val chronometerTime = intent.getLongExtra("chronometerTime", 0L)

    val seekValue = intent.getIntExtra("seekValue", 0)

    val radioSelection = intent.getStringExtra("radioSelection")

    val day = intent.getIntExtra("dateDay", 1)
    val month = intent.getIntExtra("dateMonth", 0)
    val year = intent.getIntExtra("dateYear", 2025)

    val minute = intent.getIntExtra("minuteTime", 0)
    val hour = intent.getIntExtra("hourTime", 0)

}
