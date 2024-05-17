package com.dicoding.ebin_v1.view.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        }
    }

    private fun login() {
        if (binding.txtInputEditTextEmail.text!!.isNotEmpty() && binding.txtInputEditTextPassword.text!!.isNotEmpty()) {
            processLogin()
        } else {
            Toast.makeText(this, "LENGKAPI DATA", Toast.LENGTH_LONG).show()
        }
    }

    private fun processLogin() {
        val email = binding.txtInputEditTextEmail.text.toString()
        val password = binding.txtInputEditTextPassword.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                startActivity(Intent(this, HomepageActivity::class.java))
                Log.d("LoginActivity", "SUCCESS")
                finish()
            }
            .addOnFailureListener { error ->
                Log.e("LoginActivity", "FAIL", error)
            }
    }
}