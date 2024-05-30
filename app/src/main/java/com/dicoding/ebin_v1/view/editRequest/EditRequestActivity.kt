package com.dicoding.ebin_v1.view.editRequest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.entity.EditRequest
import com.dicoding.ebin_v1.data.response.EditRequestResponse
import com.dicoding.ebin_v1.data.response.GetAllRequestResponseItem
import com.dicoding.ebin_v1.data.retrofit.ApiConfig
import com.dicoding.ebin_v1.databinding.ActivityEditRequestBinding
import com.dicoding.ebin_v1.view.marketplace.MarketplaceActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditRequestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRequestBinding
    private lateinit var detailRequest: GetAllRequestResponseItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailRequest = (intent.getParcelableExtra(KEY_DETAIL) as GetAllRequestResponseItem?)!!

        setDetailRequest(detailRequest)
        setAction()
    }

    private fun setAction() {
        binding.mtEditRequestToolBar.setNavigationOnClickListener {
            finish()
        }

        binding.btnEditRequest.setOnClickListener {
            editRequest()
        }
    }

    private fun setDetailRequest(detailRequest: GetAllRequestResponseItem) {
        val title = detailRequest.title!!.split(" ")
        val number = title[0]
        val type = title[1]
        binding.txtInputEditTextEditRequestAmount.setText(number)
        binding.txtInputEditTextEditRequestType.setText(type)
        binding.txtInputEditTextEditRequestDescription.setText(detailRequest.description)
        binding.txtInputEditTextEditRequestDestination.setText(detailRequest.address)
        binding.txtInputEditTextEditRequestReward.setText(detailRequest.reward.toString())
    }

    private fun editRequest() {
        try {
            val id = detailRequest.id!!
            val amount = binding.txtInputEditTextEditRequestAmount.text.toString()
            val type = binding.txtInputEditTextEditRequestType.text.toString()
            val title = "$amount $type"
            val description = binding.txtInputEditTextEditRequestDescription.text.toString()
            val address = binding.txtInputEditTextEditRequestDestination.text.toString()
            val reward = binding.txtInputEditTextEditRequestReward.text.toString().toInt()

            val editRequestBody = EditRequest(title, description, address, reward)
            Log.d("REQUEST ENTITY", editRequestBody.toString())
            val client = ApiConfig
                .getApiService()
                .editRequest(id, editRequestBody)
            client.enqueue(object : Callback<EditRequestResponse> {
                override fun onResponse(
                    call: Call<EditRequestResponse>,
                    response: Response<EditRequestResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.d("REQUEST EDIT 1", "Success: ${response.body()}")
                        finish()
                    } else {
                        Log.e("REQUEST EDIT 1", "Failed: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<EditRequestResponse>, t: Throwable) {
                    Log.e("REQUEST EDIT", "Failed: ${t.message}")
                }

            })
        } catch (e: Exception) {
            Log.e("REQUEST EDIT 2", e.message.toString())
        }
    }

    companion object {
        const val KEY_DETAIL = "key-detail"
    }
}