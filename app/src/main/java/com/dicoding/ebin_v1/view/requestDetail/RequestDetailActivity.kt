package com.dicoding.ebin_v1.view.requestDetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.entity.Requests
import com.dicoding.ebin_v1.data.response.GetAllRequestResponseItem
import com.dicoding.ebin_v1.data.response.GetAllUsersResponseItem
import com.dicoding.ebin_v1.data.retrofit.ApiConfig
import com.dicoding.ebin_v1.databinding.ActivityRequestDetailBinding
import com.dicoding.ebin_v1.view.editRequest.EditRequestActivity
import com.dicoding.ebin_v1.view.welcomePage.WelcomePageActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Date
import java.util.concurrent.TimeUnit

class RequestDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRequestDetailBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var requestDetailViewModel: RequestDetailViewModel
    private lateinit var detailRequest: GetAllRequestResponseItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        checkSession(auth)

        requestDetailViewModel = ViewModelProvider(this).get(RequestDetailViewModel::class.java)

        detailRequest = (intent.getParcelableExtra(KEY_DETAIL) as GetAllRequestResponseItem?)!!
        setDetailData(detailRequest)

        setButtonDisplay(detailRequest)
        setDetailActionDescription(detailRequest)
        setAction(detailRequest)

        requestDetailViewModel.requestTaken.observe(this) {
            if (it) {
                binding.btnDetailRequestTakeRequest.visibility = View.GONE
                binding.btnDetailRequestRequestDelivery.visibility = View.VISIBLE
                binding.clRequestTakenDescription.visibility = View.VISIBLE
            }
        }
    }

    private fun setDetailActionDescription(detailRequest: GetAllRequestResponseItem) {
        if ((detailRequest.receiverID!!.id == auth.currentUser!!.uid) && (detailRequest.status == "taken")) {
            binding.btnDetailRequestTakeRequest.visibility = View.GONE
            binding.btnDetailRequestRequestDelivery.visibility = View.VISIBLE
            binding.clRequestTakenDescription.visibility = View.VISIBLE
        }
    }

    private fun setButtonDisplay(detailRequest: GetAllRequestResponseItem) {
        if (detailRequest.receiverID!!.id == auth.currentUser!!.uid) {
            binding.btnDetailRequestEditRequest.visibility = View.VISIBLE
            binding.btnDetailRequestTakeRequest.visibility = View.GONE
        } else {
            binding.btnDetailRequestEditRequest.visibility = View.GONE
            binding.btnDetailRequestTakeRequest.visibility = View.VISIBLE
        }
    }

    private fun checkSession(auth: FirebaseAuth) {
        val firebaseUser = auth.currentUser

        if (firebaseUser == null) {
            startActivity(Intent(this, WelcomePageActivity::class.java))
            finish()
        }
    }

    private fun setAction(detailRequest: GetAllRequestResponseItem) {
        binding.mtDetailRequestToolBar.setNavigationOnClickListener {
            finish()
        }

        binding.btnDetailRequestEditRequest.setOnClickListener {
            val intent = Intent(this, EditRequestActivity::class.java)
            intent.putExtra(EditRequestActivity.KEY_DETAIL, detailRequest)
            startActivity(intent)
        }

        binding.btnDetailRequestTakeRequest.setOnClickListener {
            val senderId = auth.uid
            Log.d("DEBUG", "reqId: ${detailRequest.id}\nsenderId: ${senderId}")
            requestDetailViewModel.updateStatus(detailRequest.id!! , senderId!!, "taken")
        }
    }

    private fun setDetailData(detailRequest: GetAllRequestResponseItem) {
        binding.apply {
            mtDetailRequestToolBar.title = detailRequest.title
            detailRequestItemRequesterPlaceholder.text = detailRequest.receiverID!!.username
            detailRequestItemDescriptionPlaceholder.text = detailRequest.description
            detailRequestItemAddressPlaceholder.text = detailRequest.address
            detailRequestItemRewardPlaceholder.text = detailRequest.reward.toString()
            detailRequestItemDatePlaceholder.text = detailRequest.start
        }
    }

    companion object {
        const val KEY_DETAIL = "key-detail"
    }
}