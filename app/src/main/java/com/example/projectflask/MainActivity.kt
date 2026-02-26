package com.example.projectflask

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectflask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("MY_APP", MODE_PRIVATE)
        val username = sharedPref.getString("USERNAME", "User")

        binding.tvHaloUser.text = "Halo $username"


        binding.btnLogout.setOnClickListener {

            val editor = sharedPref.edit()
            editor.clear()
            editor.apply()

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}