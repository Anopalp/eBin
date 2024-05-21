package com.dicoding.ebin_v1.view.addRequest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.entity.RequestDetail
import com.dicoding.ebin_v1.data.response.PostRequestResponse
import com.dicoding.ebin_v1.data.retrofit.ApiConfig
import com.dicoding.ebin_v1.databinding.ActivityAddRequestBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddRequestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRequestBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        setAction()
    }

    private fun setAction() {
        binding.mtAddRequestToolBar.setNavigationOnClickListener {
            finish()
        }

        binding.btnAddRequest.setOnClickListener {
            if (validateInputNotEmpty() && validateType()) {
                postRequest()
            }
        }
    }

    private fun validateInputNotEmpty(): Boolean {
        var retVal = true

        if (binding.txtInputEditTextAddRequestType.text!!.isEmpty() &&
            binding.txtInputEditTextAddRequestAmount.text!!.isEmpty() &&
            binding.txtInputEditTextAddRequestDescription.text!!.isEmpty() &&
            binding.txtInputEditTextAddRequestDestination.text!!.isEmpty() &&
            binding.txtInputEditTextAddRequestReward.text!!.isEmpty()) {

            toastMessage("All of the input must me fill")
            retVal = false
        }

        return retVal
    }

    private fun validateType(): Boolean {
        var retVal = true

        Log.d("VALIDATE TYPE", binding.txtInputEditTextAddRequestType.text.toString())

        if ((binding.txtInputEditTextAddRequestType.text.toString().lowercase() != "paper") &&
            (binding.txtInputEditTextAddRequestType.text.toString().lowercase() != "plastic trash")) {
            toastMessage("Must be paper or plastic or plastic trash")
            retVal = false
        }

        return retVal
    }

    private fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun postRequest() {
        val receiverId = auth.currentUser!!.uid
        val type = binding.txtInputEditTextAddRequestType.text.toString()
        val amount = binding.txtInputEditTextAddRequestAmount.text.toString()
        val title = "$amount $type"
        val description = binding.txtInputEditTextAddRequestDescription.text.toString()
        val address = binding.txtInputEditTextAddRequestDestination.text.toString()
        val reward = binding.txtInputEditTextAddRequestReward.text.toString().toInt()

        try {
            val requestDetail = RequestDetail(receiverId, title, description, address, reward)
            val client = ApiConfig.getApiService().postRequest(requestDetail)
            client.enqueue(object : Callback<PostRequestResponse> {
                override fun onResponse(
                    call: Call<PostRequestResponse>,
                    response: Response<PostRequestResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.d("REQUEST POST 1", "Success: ${response.body()}")
                        finish()
                    } else {
                        Log.e("ACCOUNT POST 1", "Failed: ${response.errorBody()?.toString()}")
                    }
                }

                override fun onFailure(call: Call<PostRequestResponse>, t: Throwable) {
                    Log.e("ACCOUNT POST 2", "Failed: ${t.message}")
                }

            })
        } catch (e: Exception) {
            Log.e("ACCOUNT POST 3", e.message.toString())
        }
    }
}