package com.example.personalityquiz

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

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

        val message = intent.getStringExtra("EXTRA_MESSAGE")

        val resultText = findViewById<TextView>(R.id.resultTextView)

        val selectedColors = listOfNotNull(
            checkbox1.takeIf { it }?.let { "Zielony" },
            checkbox2.takeIf { it }?.let { "Niebieski" },
            checkbox3.takeIf { it }?.let { "Czerwony" }
        ).joinToString(", ")

        var opis = "null";
        if (seekValue == 0){
            opis = "Jesteś zerem";
        }else if (seekValue < 5 && seekValue > 0){
            opis = "Jesteś okej pomidorkiem";
        }else if (seekValue >= 5 && seekValue <= 7){
            opis = "Jesteś dobrym pomidorkiem";
        }else if (seekValue > 7 && seekValue < 10){
            opis = "Jesteś cudnym pomidorkiem"
        }else if (seekValue == 10){
            opis = "Jesteś idealnym pomidorkiem"
        }else{
            opis = "Jesteś cw*lem"
        }

        val resultSummary = """
        Wynik:

        1. Data: ${"%02d".format(day)}.${"%02d".format(month + 1)}.$year
        2. Godzina: ${"%02d".format(hour)}:${"%02d".format(minute)}
        3. Zaznaczona odpowiedź: $radioSelection
        4. Zaznaczone kolory: $selectedColors
        5. Wartość suwaka: $seekValue
        6. Wybrana opcja ze spinnera: $spinnerItem
        7. Czas wykonania testu: ${chronometerTime}s
        -----------------------------------------------------------------------
        Twój opis: $opis!
        """.trimIndent()

        resultText.text = resultSummary
    }
}
