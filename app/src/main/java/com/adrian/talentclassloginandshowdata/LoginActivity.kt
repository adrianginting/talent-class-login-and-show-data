package com.adrian.talentclassloginandshowdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import android.util.Patterns

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var spinnerMajor: Spinner
    private lateinit var editTextSemester: EditText
    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        spinnerMajor = findViewById(R.id.spinnerMajor)
        editTextSemester = findViewById(R.id.editTextSemester)
        buttonLogin = findViewById(R.id.buttonLogin)

        // Daftar item dropdown
        val major = arrayOf("Teknik Informatika",
                                "Sistem Informasi",
                                "Teknik Komputer",
                                "Data Science",
                                "Artificial Intelligence",
                                "Cybersecurity")

        // Membuat adapter untuk dropdown
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, major)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Menetapkan adapter ke spinner
        spinnerMajor.adapter = adapter

        // Menangani peristiwa saat item dipilih
        spinnerMajor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedLanguage = parent.getItemAtPosition(position).toString()

                if (position != 0) {
                    Toast.makeText(this@LoginActivity, "Anda memilih: $selectedLanguage", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Tidak ada item yang dipilih
            }
        }

        buttonLogin.setOnClickListener {
            // mengambil teks yang dimasukkan oleh pengguna dalam EditText dan kemudian menghapus spasi di awal dan akhir teks menggunakan trim()
            val name = editTextName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val major = spinnerMajor.selectedItem.toString().trim()
            val semester = editTextSemester.text.toString().trim()

            // Melakukan pengecekan email valid atau tidak
            val isValid = isEmailValid(email)
            if (isValid) {
                // Email valid
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(major) || TextUtils.isEmpty(semester)) {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                } else {
                    // Lakukan tindakan selanjutnya, misalnya, buka Activity baru atau proses data
                    val intent = Intent(this, MainActivity::class.java)
                    // Membuat objek Bundle
                    val bundle = Bundle()
                    // Menambahkan data ke Bundle
                    bundle.putString("name", name)
                    bundle.putString("email", email)
                    bundle.putString("major", major)
                    bundle.putString("semester", semester)
                    // Menyimpan Bundle pada Intent
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
            } else {
                // Email tidak valid
                Toast.makeText(this, "E-mail tidak valid", Toast.LENGTH_SHORT).show()
            }
        }
    }
    // Menggunakan ekspresi reguler (regular expression) atau menggunakan metode bawaan Android Patterns.EMAIL_ADDRESS
    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}