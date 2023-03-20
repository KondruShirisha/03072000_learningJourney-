package com.example.siri_task




import android.app.DatePickerDialog

import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var goalsSwitch: Switch
    private lateinit var yesNoTextView: TextView
    private lateinit var ratingBar: RatingBar
    private lateinit var scaleSeekBar: SeekBar
    private lateinit var dateOfBirthEditText: EditText
    private lateinit var dateAndTimeEditText: EditText
    private lateinit var submitButton: Button

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        goalsSwitch = findViewById(R.id.goalsSwitch)
        yesNoTextView = findViewById(R.id.yesNoTextView)
        ratingBar = findViewById(R.id.ratingBar)
        scaleSeekBar = findViewById(R.id.scaleSeekBar)
        dateOfBirthEditText = findViewById(R.id.dateOfBirthEditText)
        //TImeDateEditText = findViewById(R.id.dateAndTimeEditText)
        submitButton = findViewById(R.id.submitButton)
        dateAndTimeEditText = findViewById(R.id.dateAndTimeEditText)


        val dateOfBirthEditText = findViewById<EditText>(R.id.dateOfBirthEditText)
        dateOfBirthEditText.setOnClickListener {
            // Create a Calendar object to store the selected date and time
            val currentDate = Calendar.getInstance()
            val year = currentDate.get(Calendar.YEAR)
            val month = currentDate.get(Calendar.MONTH)
            val day = currentDate.get(Calendar.DAY_OF_MONTH)
            val datePicker = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val selectedDate = "${dayOfMonth}/${month + 1}/${year}"
                dateOfBirthEditText.setText(selectedDate)
            }, year, month, day)
            datePicker.datePicker.maxDate = System.currentTimeMillis() - 1000
            datePicker.show()
        }




        // Set onCheckedChangeListener for goalsSwitch
        val switch = findViewById<Switch>(R.id.goalsSwitch)
        val editText = findViewById<EditText>(R.id.editTextTextMultiLine2)

        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                editText.setText("Yes")
            } else {
                editText.setText("")
            }
        }


        // Set onClickListener for submit button
        submitButton.setOnClickListener {
            // Get input values from views
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val hasGoals = goalsSwitch.isChecked
            val rating = ratingBar.rating
            val scale = scaleSeekBar.progress
            val dateOfBirth = dateOfBirthEditText.text.toString()
            val comments = dateAndTimeEditText.text.toString()


            // Validate email address format
            if (!isValidEmail(email)) {
                // Show error message
                emailEditText.error = "Enter a valid email address"
                return@setOnClickListener
            }
            if (name.isEmpty()) {
                nameEditText.error = "Name is required"
                nameEditText.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                emailEditText.error = "Email is required"
                emailEditText.requestFocus()
                return@setOnClickListener
            }
            if (dateOfBirth.isEmpty()) {
                Toast.makeText(this, "Date of birth is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Display toast message
            Toast.makeText(this, "Form Submitted", Toast.LENGTH_LONG).show()
        }



    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}