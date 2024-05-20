package com.dicoding.ebin_v1.view.requestDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.entity.Requests
import com.dicoding.ebin_v1.data.response.GetAllRequestResponseItem
import com.dicoding.ebin_v1.databinding.ActivityRequestDetailBinding
import java.util.Date
import java.util.concurrent.TimeUnit

class RequestDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRequestDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailRequest = intent.getParcelableExtra(KEY_DETAIL) as GetAllRequestResponseItem?
        setDetailData(detailRequest!!)

        setAction()
    }

    private fun setAction() {
        binding.mtDetailRequestToolBar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setDetailData(detailRequest: GetAllRequestResponseItem) {
        val seconds = detailRequest.start?.seconds as Long
        val nanoseconds = detailRequest.start.seconds as Long
        val milliseconds = TimeUnit.SECONDS.toMillis(seconds) + TimeUnit.NANOSECONDS.toMillis(nanoseconds)
        val date = Date(milliseconds)
        Log.d("DATE", date.toString())
        binding.apply {
            mtDetailRequestToolBar.title = detailRequest.title
            detailRequestItemRequesterPlaceholder.text = detailRequest.senderID
            detailRequestItemDescriptionPlaceholder.text = detailRequest.description
            detailRequestItemAddressPlaceholder.text = detailRequest.address
            detailRequestItemRewardPlaceholder.text = detailRequest.reward.toString()
            detailRequestItemDatePlaceholder.text = date.toString()
        }
    }

    companion object {
        const val KEY_DETAIL = "key-detail"
    }
}