package com.example.projectflask

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectflask.databinding.ActivityLoginBinding
import com.example.projectflask.response_auth.LoginRequest
import com.example.projectflask.response_auth.ResponseLogin
import com.example.projectflask.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Response
import androidx.core.content.edit

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("MY_APP", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("IS_LOGIN", false)

        if (isLogin) {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            // Lakukan validasi dan autentikasi di sini

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email dan password wajib diisi", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

            val request = LoginRequest(email, password)

            ApiConfig.getApiService().loginUser(request).enqueue(object : retrofit2.Callback<ResponseLogin>{
                override fun onResponse(
                    call: Call<ResponseLogin?>,
                    response: Response<ResponseLogin?>
                ) {
                    if (response.isSuccessful) {
                        val res = response.body()
                        if (res != null && res.status) {
                            Toast.makeText(this@LoginActivity, res.message, Toast.LENGTH_SHORT)
                                .show()

                            // ambil token
                            val token = res.token
                            Log.d("LOGIN", "Response: ${response.body()}")

                            val sharedPref = getSharedPreferences("MY_APP", MODE_PRIVATE)
                            sharedPref.edit {

                                val username = res.username
                                putString("USERNAME", username)
                                putString("TOKEN", token)
                                putBoolean("IS_LOGIN", true)
                                apply()

                            }

                            //Pindah ke MainActivity
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.putExtra("TOKEN", token)
                            startActivity(intent)
                            finish()

                        }else {
                            Toast.makeText(
                                this@LoginActivity,
                                res?.message ?: "Login gagal",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        } else {
                            Toast.makeText(this@LoginActivity, "Response error : ${response.code()}",
                                Toast.LENGTH_SHORT).show()
                        }
                    }

                override fun onFailure(
                    call: Call<ResponseLogin?>,
                    t: Throwable
                ) {
                    Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }

            })
        }

        binding.tvDaftar.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}