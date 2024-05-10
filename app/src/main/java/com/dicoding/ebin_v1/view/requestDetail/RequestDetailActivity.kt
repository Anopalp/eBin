package com.dicoding.ebin_v1.view.requestDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.entity.Requests
import com.dicoding.ebin_v1.databinding.ActivityRequestDetailBinding

class RequestDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRequestDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailRequest = intent.getParcelableExtra(KEY_DETAIL) as Requests?
        setDetailData(detailRequest!!)

        setAction()
    }

    private fun setAction() {
        binding.mtDetailRequestToolBar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setDetailData(detailRequest: Requests) {
        binding.apply {
            val title = "${detailRequest.amount} ${detailRequest.type}"
            mtDetailRequestToolBar.title = title
            detailRequestItemRequesterPlaceholder.text = detailRequest.owner
            detailRequestItemDescriptionPlaceholder.text = detailRequest.detail
            detailRequestItemAddressPlaceholder.text = detailRequest.address
            detailRequestItemRewardPlaceholder.text = detailRequest.reward.toString()
            detailRequestItemDatePlaceholder.text = detailRequest.requestStart
        }
    }

    companion object {
        const val KEY_DETAIL = "key-detail"
    }
}