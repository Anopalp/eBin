package com.dicoding.ebin_v1.view.editRequest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.response.GetAllRequestResponseItem
import com.dicoding.ebin_v1.databinding.ActivityEditRequestBinding

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

    companion object {
        const val KEY_DETAIL = "key-detail"
    }
}