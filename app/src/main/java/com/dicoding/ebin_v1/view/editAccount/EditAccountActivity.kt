package com.dicoding.ebin_v1.view.editAccount

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.entity.User
import com.dicoding.ebin_v1.data.entity.UserDetail
import com.dicoding.ebin_v1.data.response.GetAllUsersResponseItem
import com.dicoding.ebin_v1.data.response.SignupResponse
import com.dicoding.ebin_v1.data.retrofit.ApiConfig
import com.dicoding.ebin_v1.databinding.ActivityEditAccountBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditAccountBinding
    private lateinit var detailAccount: GetAllUsersResponseItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailAccount = (intent.getParcelableExtra(KEY_DETAIL) as GetAllUsersResponseItem?)!!

        setDetailAccount(detailAccount)

        setAction()
    }

    private fun setDetailAccount(detailAccount: GetAllUsersResponseItem) {
        binding.txtInputEditTextUsername.setText(detailAccount.username)
        binding.txtInputEditTextFullName.setText(detailAccount.fullname)
        binding.txtInputEditTextPhone.setText(detailAccount.phone.toString())
        binding.txtInputEditTextEmail.setText(detailAccount.email)
        binding.txtInputEditTextAddress.setText(detailAccount.address)
    }

    private fun setAction() {
        binding.mtEditAccountToolBar.setNavigationOnClickListener {
            finish()
        }

        binding.btnEditAccountEdit.setOnClickListener {
            editAccount()
        }
    }

    private fun editAccount() {
        try {
            val id = detailAccount.id!!
            val username = binding.txtInputEditTextUsername.text.toString()
            val fullname = binding.txtInputEditTextFullName.text.toString()
            val phone = binding.txtInputEditTextPhone.text.toString()
            val email = binding.txtInputEditTextEmail.text.toString()
            val address = binding.txtInputEditTextAddress.text.toString()

            val userDetail = UserDetail(id, username, fullname, phone, email, address)
            val client = ApiConfig
                .getApiService()
                .editUser(id, userDetail)
            client.enqueue(object : Callback<SignupResponse> {
                override fun onResponse(
                    call: Call<SignupResponse>,
                    response: Response<SignupResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.d("ACCOUNT PUT 1", "Success: ${response.body()}")
                        finish()
                    } else {
                        Log.e("ACCOUNT PUT 1", "Failed: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                    Log.e("ACCOUNT PUT", "Failed: ${t.message}")
                }

            })
        } catch (e: Exception) {
            Log.e("ACCOUNT PUT 2", e.message.toString())
        }
    }

    companion object {
        const val KEY_DETAIL = "key-detail"
    }
}