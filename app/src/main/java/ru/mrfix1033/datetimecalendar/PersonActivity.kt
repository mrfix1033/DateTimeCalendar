package ru.mrfix1033.datetimecalendar

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date

class PersonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_person)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val person = intent.getParcelableExtra<Person>("person")!!
        title = getString(R.string.app_name)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        findViewById<ImageView>(R.id.imageView).setImageURI(person.image)
        findViewById<TextView>(R.id.textViewSurnameAndName).setText("${person.surname} ${person.name}")
        findViewById<TextView>(R.id.textViewPhone).setText(person.phone)
        findViewById<TextView>(R.id.textViewAgeAndDaysToBirth).setText(
            "Возраст: ${(Date().time - person.birthMillis) / 31536000000}; " +
                    "Дней до ДР: ${(31536000000 - (Date().time - person.birthMillis) % 31536000000) / 86400000}"
        )
    }
}