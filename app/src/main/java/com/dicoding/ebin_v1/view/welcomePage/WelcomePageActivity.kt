package com.dicoding.ebin_v1.view.welcomePage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.databinding.ActivityWelcomePageBinding
import com.dicoding.ebin_v1.view.login.LoginActivity
import com.dicoding.ebin_v1.view.signup.SignupActivity

class WelcomePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAction()
    }

    private fun setAction() {
        binding.btnWelcomePageSignupButton.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        binding.btnWelcomePageLoginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}