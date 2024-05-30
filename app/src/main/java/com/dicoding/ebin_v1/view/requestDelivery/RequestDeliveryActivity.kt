package com.dicoding.ebin_v1.view.requestDelivery

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.response.GetAllRequestResponseItem
import com.dicoding.ebin_v1.data.response.UpdateRequestStatusResponse
import com.dicoding.ebin_v1.databinding.ActivityRequestDeliveryBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RequestDeliveryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRequestDeliveryBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var detailRequest: GetAllRequestResponseItem
    private lateinit var requestDeliveryViewModel: RequestDeliveryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestDeliveryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        requestDeliveryViewModel = ViewModelProvider(this).get(RequestDeliveryViewModel::class.java)
        detailRequest = (intent.getParcelableExtra(KEY_DETAIL) as GetAllRequestResponseItem?)!!

        setDetailRequest(detailRequest)
        setAction(detailRequest)

        requestDeliveryViewModel.response.observe(this) {
            setStatus(it!!)
        }
    }

    private fun setStatus(response: UpdateRequestStatusResponse) {
        when (response.status) {
            "on hold" -> binding.ivImageStatus.setImageResource(R.drawable.status_delivered)
            "end" -> binding.ivImageStatus.setImageResource(R.drawable.status_completed)
        }
    }

    private fun setDetailRequest(detailRequest: GetAllRequestResponseItem) {
        binding.apply {
            mtDeliveryToolBar.title = detailRequest.title
            deliveryItemRequesterPlaceholder.text = detailRequest.receiverID?.username
            deliveryItemDescriptionPlaceholder.text = detailRequest.description
            deliveryItemAddressPlaceholder.text = detailRequest.address
            deliveryItemRewardPlaceholder.text = detailRequest.reward.toString()
            deliveryItemDatePlaceholder.text = detailRequest.start

            when (detailRequest.status) {
                "delivery" -> {
                    binding.ivImageStatus.setImageResource(R.drawable.status_delivery)
                    binding.txtRequestStatusTitle.text = "The Request is On Delivery"
                }
                "on hold" -> {
                    binding.ivImageStatus.setImageResource(R.drawable.status_delivered)
                    binding.txtRequestStatusTitle.text = "The Request is Delivered"
                }
                "end" -> {
                    binding.ivImageStatus.setImageResource(R.drawable.status_completed)
                    binding.txtRequestStatusTitle.text = "The Request Has Been Completed"
                }
            }
        }
    }

    private fun setAction(detailRequest: GetAllRequestResponseItem) {
        binding.mtDeliveryToolBar.setNavigationOnClickListener {
            finish()
        }

        binding.ivImageStatus.setOnClickListener {
            val senderId = auth.uid
            if (detailRequest.status == "delivery") {
                requestDeliveryViewModel.updateStatus(detailRequest.id!!, senderId!!, "on hold")
            }
        }
    }

    companion object {
        const val KEY_DETAIL = "key-detail"
    }
}