package com.example.personalityquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Chronometer
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var chronometer: Chronometer
    private var running = false

    lateinit var timerTextView: TextView
    lateinit var mainLayout: LinearLayout

    lateinit var editText: TextView

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

        val editText = findViewById<EditText>(R.id.message_textview)
        val quizEnd = findViewById<Button>(R.id.buttonQuizEnd)

        quizEnd.setOnClickListener {
            val message = editText.text.toString()
            val intent = Intent(this, SummaryActivity::class.java)
            intent.putExtra("EXTRA_MESSAGE", message)
            startActivity(intent)
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
                timerTextView.text = "Pozostało $secondsLeft sekund"
            }

            override fun onFinish() {
                timerTextView.text = "Koniec czasu"
            }
        }

        countDownTimer.start()

    }
}
