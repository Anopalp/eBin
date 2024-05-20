package com.dicoding.ebin_v1.view.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.entity.UserDetail
import com.dicoding.ebin_v1.data.response.SignupResponse
import com.dicoding.ebin_v1.data.retrofit.ApiConfig
import com.dicoding.ebin_v1.databinding.ActivitySignupBinding
import com.dicoding.ebin_v1.view.homepage.HomepageActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        if (isEmailValid() &&
            isPasswordValid() &&
            isFullnameValid() &&
            isUsernameValid() &&
            isAddressValid() &&
            isPhoneValid()) {
            processSignup()
        }
    }

    private fun isPhoneValid(): Boolean {
        var retVal = true

        if (binding.txtInputEditTextPhoneNumber.text!!.isEmpty()) {
            toastText("Phone number must be fill")
            retVal = false
        }

        return retVal
    }

    private fun isAddressValid(): Boolean {
        var retVal = true

        if (binding.txtInputEditTextAddress.text!!.isEmpty()) {
            toastText("Address must be fill")
            retVal = false
        }

        return retVal
    }

    private fun isUsernameValid(): Boolean {
        var retVal = true

        if (binding.txtInputEditTextUsername.text!!.isEmpty()) {
            toastText("Username must be fill")
            retVal = false
        }

        return retVal
    }

    private fun isFullnameValid(): Boolean {
        var retVal = true

        if (binding.txtInputEditTextFullName.text!!.isEmpty()) {
            toastText("Full name must be fill")
            retVal = false
        }

        return retVal
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

    private fun processSignup() {
        try {
            showLoading(true)
            val email = binding.txtInputEditTextEmail.text.toString()
            val password = binding.txtInputEditTextPassword.text.toString()

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d("SignInActivity", "SUCCESS")
                        val user = firebaseAuth.currentUser
                        postUser(user)
                        updateUI(user)
                    } else {
                        Log.e("SignInActivity", "FAIL", task.exception)
                        Toast.makeText(this, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        } catch (e: Exception) {
            toastText(e.message.toString())
            Log.e("SignInActivity", e.message.toString())
        } finally {
            showLoading(false)
        }
    }

    private fun postUser(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            try {
                val id = currentUser.uid
                val username = binding.txtInputEditTextUsername.text.toString()
                val fullname = binding.txtInputEditTextFullName.text.toString()
                val phone = binding.txtInputEditTextPhoneNumber.text.toString()
                val email = binding.txtInputEditTextEmail.text.toString()
                val address = binding.txtInputEditTextAddress.text.toString()
                val userDetail = UserDetail(id, username, fullname, phone, email, address)
                val client = ApiConfig
                    .getApiService()
                    .postUser(userDetail)
                client.enqueue(object : Callback<SignupResponse> {
                    override fun onResponse(
                        call: Call<SignupResponse>,
                        response: Response<SignupResponse>
                    ) {
                        Log.d("ACCOUNT POST HEADERS", response.headers().toString())
                        Log.d("ACCOUNT POST BODY", response.body().toString())
                        Log.d("ACCOUNT POST RAW", response.raw().toString())
                        if (response.isSuccessful) {
                            Log.d("ACCOUNT POST 1", "Success: ${response.body()}")
                        } else {
                            Log.e("ACCOUNT POST 1", "Failed: ${response.errorBody()?.string()}")
                        }
                    }

                    override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                        Log.e("ACCOUNT POST", "Failed: ${t.message}")
                    }

                })
            } catch (e: Exception) {
                Log.e("ACCOUNT POST 2", e.message.toString())
            }
        }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            startActivity(Intent(this, HomepageActivity::class.java))
            finish()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.clSignupContainer.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.clSignupContainer.visibility = View.VISIBLE
        }
    }
}