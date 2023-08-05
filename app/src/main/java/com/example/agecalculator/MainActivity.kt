package com.example.agecalculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.agecalculator.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Locale
import kotlin.time.Duration.Companion.days

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {

            datePicker()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun datePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDay)
                val currentDate = LocalDate.now()
                val age = Period.between(selectedDate, currentDate)

                // Code for age in Days
                val ageInDays = ChronoUnit.DAYS.between(selectedDate, currentDate)
                binding.inDays.text = "$ageInDays"

                // Code for age in Years
                val ageInYears = age.years
                binding.inYears.text = "$ageInYears"

                // Code for age in months
                val ageInMonths = age.toTotalMonths()
                binding.inMonths.text = "$ageInMonths"

                // Code for age in minutes
                val selectedDateInMinutes = selectedDate.toEpochDay() * 24 * 60
                val currentDateInMinutes = LocalDate.now().toEpochDay() * 24 * 60
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                binding.inMinutes.text = "$differenceInMinutes minutes"
            },
            year,
            month,
            day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }



}