package com.example.projectflask

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectflask.databinding.ActivityRegisterBinding
import com.example.projectflask.response_auth.RegisterRequest
import com.example.projectflask.response_auth.ResponseRegister
import com.example.projectflask.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDaftar.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            // Lakukan validasi dan autentikasi di sini
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Data wajib diisi", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
            }

            val request = RegisterRequest(username,email, password)

            ApiConfig.getApiService().registerUser(request).enqueue(object : retrofit2.Callback<ResponseRegister> {
                override fun onResponse(
                    call: Call<ResponseRegister>,
                    response: Response<ResponseRegister>
                ) {
                    Log.d("REGISTER_DEBUG", "Code: ${response.code()}")
                    Log.d("REGISTER_DEBUG", "Body: ${response.body()}")
                    if (response.isSuccessful && response.body()?.status == true) {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Register berhasil",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@RegisterActivity, "Register gagal", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(
                    call: Call<ResponseRegister?>,
                    t: Throwable
                ) {
                    Toast.makeText(this@RegisterActivity, "Error: ${t.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }

        binding.tvMasuk.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
}