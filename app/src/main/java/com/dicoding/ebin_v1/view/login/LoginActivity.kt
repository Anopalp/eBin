package com.dicoding.ebin_v1.view.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import com.dicoding.ebin_v1.view.homepage.HomepageActivity
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        setAction()
    }

    private fun setAction() {
        binding.mtLoginToolBar.setNavigationOnClickListener {
            finish()
        }

        binding.btnLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        if (isEmailValid() && isPasswordValid()) {
            processLogin()
        }
    }

    private fun isPasswordValid(): Boolean {
        var retVal = true

        if (binding.txtInputEditTextPassword.text!!.isEmpty()) {
            toastText("Password must be fill")
            retVal = false
        }

        if (binding.txtInputEditTextPassword.text!!.length < 8) {
            toastText("Password must be consist of 8 characters")
            retVal = false
        }

        return retVal
    }

    private fun isEmailValid(): Boolean {
        var retVal = true
        if (binding.txtInputEditTextEmail.text!!.isEmpty()) {
            toastText("Email must be fill")
            retVal = false
        }

        if (!validateEmail(binding.txtInputEditTextEmail.text.toString())) {
            toastText("False email format")
            retVal = false
        }

        return retVal
    }

    private fun validateEmail(email: String): Boolean {
        val emailRegex = "^[a-zA-Z0-9._-]+@[a-zA-Z]+\\.[a-zA-Z]{2,}\$".toRegex()
        return emailRegex.matches(email)
    }

    private fun toastText(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    private fun processLogin() {
        try {
            showLoading(true)
            val email = binding.txtInputEditTextEmail.text.toString()
            val password = binding.txtInputEditTextPassword.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    startActivity(Intent(this, HomepageActivity::class.java))
                    Log.d("LoginActivity", "SUCCESS")
                    finish()
                }
                .addOnFailureListener { error ->
                    toastText(error.message.toString())
                    Log.e("LoginActivity", "FAIL", error)
                }
        } catch (e: Exception) {
            toastText(e.message.toString())
            Log.e("LoginActivity", e.message.toString())
        } finally {
            showLoading(false)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.clLoginContainer.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.clLoginContainer.visibility = View.VISIBLE
        }
    }
}