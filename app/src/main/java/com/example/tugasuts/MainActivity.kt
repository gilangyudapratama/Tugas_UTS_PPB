package com.example.tugasuts

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Deklarasi variabel untuk elemen UI
    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etNamaDepan: EditText
    private lateinit var etNamaBelakang: EditText
    private lateinit var etPassword: EditText
    private lateinit var etUlangPassword: EditText
    private lateinit var btnKirim: Button
    private lateinit var btnBatal: Button
    private lateinit var tvPesan: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Menerapkan padding untuk system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initializeViews()
        setupListeners()

        // Atur pesan awal
        tvPesan.text = "Mohon isi semua data pendaftaran."
        tvPesan.setTextColor(resources.getColor(android.R.color.holo_green_dark))
    }

    private fun initializeViews() {
        // Mendapatkan referensi menggunakan ID yang ada di activity_main.xml
        etUsername = findViewById(R.id.etUsername)
        etEmail = findViewById(R.id.etEmail)
        etNamaDepan = findViewById(R.id.etNamaDepan)
        etNamaBelakang = findViewById(R.id.etNamaBelakang)
        etPassword = findViewById(R.id.etPassword)
        etUlangPassword = findViewById(R.id.etUlangPassword)
        btnKirim = findViewById(R.id.btnKirim)
        btnBatal = findViewById(R.id.btnBatal)
        tvPesan = findViewById(R.id.tvPesan)
    }

    private fun setupListeners() {
        btnKirim.setOnClickListener {
            handleRegistration()
        }
        btnBatal.setOnClickListener {
            clearFields()
            tvPesan.text = "Semua field dikosongkan."
            tvPesan.setTextColor(resources.getColor(android.R.color.holo_red_dark))
        }
    }

    private fun handleRegistration() {

        // Mengambil teks dari field input
        val username = etUsername.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val namaDepan = etNamaDepan.text.toString().trim()
        val namaBelakang = etNamaBelakang.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val ulangPassword = etUlangPassword.text.toString().trim()

        // 4a. Validasi: Memastikan SEMUA FIELD terisi (Tidak boleh kosong)
        if (username.isEmpty() || email.isEmpty() || namaDepan.isEmpty() || namaBelakang.isEmpty() || password.isEmpty() || ulangPassword.isEmpty()) {

            Toast.makeText(this, "Input Data Masih Kosong", Toast.LENGTH_LONG).show()

            tvPesan.text = "Gagal: Mohon lengkapi semua field!"
            tvPesan.setTextColor(resources.getColor(android.R.color.holo_red_dark))

            return // Menghentikan proses jika ada yang kosong
        }

        // 4b. Validasi: Password dan Konfirmasi Password harus sama
        if (password != ulangPassword) {
            tvPesan.text = "Gagal: Password dan Konfirmasi Password tidak cocok!"
            tvPesan.setTextColor(resources.getColor(android.R.color.holo_red_dark))
            return // Menghentikan proses jika password tidak sama
        }

        // 5. NOTIFIKASI SUKSES (Nama Depan dan Nama Belakang digabung)
        val namaLengkap = "$namaDepan $namaBelakang"
        val message = "Pendaftaran Sukses!\nSelamat datang, $namaLengkap"

        Toast.makeText(this, message, Toast.LENGTH_LONG).show() // Menampilkan Notifikasi

        // Menampilkan pesan sukses di TextView
        tvPesan.text = "Pendaftaran Berhasil! Nama Anda: $namaLengkap"
        tvPesan.setTextColor(resources.getColor(android.R.color.holo_green_dark))

        // Mengosongkan field setelah sukses
        clearFields()
    }

    private fun clearFields() {
        etUsername.setText("")
        etEmail.setText("")
        etNamaDepan.setText("")
        etNamaBelakang.setText("")
        etPassword.setText("")
        etUlangPassword.setText("")
    }
}