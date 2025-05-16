package com.example.personalityquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var chronometer: Chronometer
    private var running = false

    lateinit var timerTextView: TextView
    lateinit var mainLayout: LinearLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinner = findViewById<Spinner>(R.id.spinner)
        val opcje = listOf("Czerwony", "Zielony", "Niebieski")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcje)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        chronometer = findViewById(R.id.chronometer)
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()
        running = true

        timerTextView = findViewById(R.id.timerCounter)
        mainLayout = findViewById(R.id.main)

        val countDownTimer: CountDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                timerTextView.text = "Pozostało $secondsLeft sekund..."
            }

            override fun onFinish() {
                timerTextView.text = "Koniec czasu"
            }
        }
        countDownTimer.start()

        val quizEnd = findViewById<Button>(R.id.buttonQuizEnd)
        quizEnd.setOnClickListener {

            val intent = Intent(this, SummaryActivity::class.java)

            intent.putExtra("checkbox1", findViewById<CheckBox>(R.id.checkBox1).isChecked)
            intent.putExtra("checkbox2", findViewById<CheckBox>(R.id.checkBox2).isChecked)
            intent.putExtra("checkbox3", findViewById<CheckBox>(R.id.checkBox3).isChecked)

            val selectedSpinnerItem = spinner.selectedItem.toString()
            intent.putExtra("spinnerItem", selectedSpinnerItem)

            val elapsedMillis = SystemClock.elapsedRealtime() - chronometer.base
            val elapsedSeconds = elapsedMillis / 1000
            intent.putExtra("chronometerTime", elapsedSeconds)

            val seekValue = findViewById<SeekBar>(R.id.seekBar).progress
            intent.putExtra("seekValue", seekValue)

            val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
            val selectedRadioId = radioGroup.checkedRadioButtonId
            val selectedRadioText = if (selectedRadioId != -1) {
                findViewById<RadioButton>(selectedRadioId).text.toString()
            } else {
                "Nie wybrano"
            }
            intent.putExtra("radioSelection", selectedRadioText)

            val datePicker = findViewById<DatePicker>(R.id.datePicker)
            intent.putExtra("dateDay", datePicker.dayOfMonth)
            intent.putExtra("dateMonth", datePicker.month)
            intent.putExtra("dateYear", datePicker.year)

            val timePicker = findViewById<TimePicker>(R.id.timePicker)
            intent.putExtra("minuteTime", timePicker.minute)
            intent.putExtra("hourTime", timePicker.hour)

            startActivity(intent)
        }
    }
}
