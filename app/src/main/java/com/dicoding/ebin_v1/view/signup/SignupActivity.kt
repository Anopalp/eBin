package com.dicoding.ebin_v1.view.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.databinding.ActivitySignupBinding
import com.dicoding.ebin_v1.view.homepage.HomepageActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private val firebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAction()
    }

    private fun setAction() {
        binding.mtSignupToolBar.setNavigationOnClickListener {
            finish()
        }

        binding.btnSignUp.setOnClickListener {
            signup()
        }
    }

    private fun signup() {
        binding.apply {
            if (txtInputEditTextEmail.text!!.isNotEmpty() &&
                txtInputEditTextPassword.text!!.isNotEmpty() &&
                txtInputEditTextFullName.text!!.isNotEmpty() &&
                txtInputEditTextUsername.text!!.isNotEmpty() &&
                txtInputEditTextAddress.text!!.isNotEmpty() &&
                txtInputEditTextPhoneNumber.text!!.isNotEmpty()) {
                processSignup()
            } else {
                Toast.makeText(this@SignupActivity, "LENGKAPI DATA", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun processSignup() {
        val email = binding.txtInputEditTextEmail.text.toString()
        val password = binding.txtInputEditTextPassword.text.toString()

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("SignInActivity", "SUCCESS")
                    val user = firebaseAuth.currentUser
                    updateUI(user)
                } else {
                    Log.e("SignInActivity", "FAIL", task.exception)
                    Toast.makeText(this, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            startActivity(Intent(this, HomepageActivity::class.java))
            finish()
        }
    }
}