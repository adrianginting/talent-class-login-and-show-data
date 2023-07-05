package com.adrian.talentclassloginandshowdata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var name: TextView
    private lateinit var email: TextView
    private lateinit var major: TextView
    private lateinit var semester: TextView
    private lateinit var buttonback: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.tvName)
        email = findViewById(R.id.tvEmail)
        major = findViewById(R.id.tvMajor)
        semester = findViewById(R.id.tvSemester)
        buttonback = findViewById(R.id.buttonBack)

        // Menerima Intent yang dikirim dari Activity asal
        val intent = intent

        // Mendapatkan Bundle dari Intent
        val bundle = intent.extras

        // Mengecek apakah Bundle tidak null
        if (bundle != null) {
            // Mendapatkan data dari Bundle
            name.text = bundle.getString("name")
            email.text = bundle.getString("email")
            major.text = bundle.getString("major")
            semester.text = bundle.getString("semester")
        }

        // button kembali ke activity sebelumnya
        buttonback.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}