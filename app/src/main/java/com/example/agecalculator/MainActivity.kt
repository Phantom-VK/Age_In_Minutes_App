package com.example.agecalculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.agecalculator.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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
    private fun datePicker(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
       { _, selectedYear, selectedMonth, selectedDay ->


           val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"

           //Here capitalization of mm yy and dd is important because it changes values
           val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

           val theDate = sdf.parse(selectedDate)

           theDate?.let {

               val selectedDateInMinutes = theDate.time / 60000

               val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

               currentDate?.let {
                   val currentDateInMinutes = currentDate.time / 60000

                   val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                   binding.inMinutes.text = "$differenceInMinutes minutes"
               }



           }


       },
            year,
            month,
            day
            )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()




    }


}