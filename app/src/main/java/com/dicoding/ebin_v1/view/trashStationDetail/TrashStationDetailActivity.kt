package com.dicoding.ebin_v1.view.trashStationDetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.ebin_v1.data.entity.TrashStation
import com.dicoding.ebin_v1.databinding.ActivityTrashStationDetailBinding
import com.dicoding.ebin_v1.view.qrCode.QRCodeActivity

class TrashStationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrashStationDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrashStationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailTrashStation = intent.getParcelableExtra(KEY_DETAIL) as TrashStation?

        setDetailData(detailTrashStation!!)

        setAction()
    }

    private fun setAction() {
        binding.mtDetailToolBar.setNavigationOnClickListener {
            finish()
        }

        binding.btnDetailShowQrCode.setOnClickListener {
            startActivity(Intent(this, QRCodeActivity::class.java))
        }
    }

    private fun setDetailData(detailTrashStation: TrashStation) {
        binding.apply {
            mtDetailToolBar.title = "Trash Station " + detailTrashStation.trashStationId
            txtDetailAddressDescription.text = detailTrashStation.address
            txtDetailDistanceDescription.text = detailTrashStation.distance.toString()
            txtDetailOpenHoursDescription.text = detailTrashStation.openHours
            txtDetailCapacityDescription.text = detailTrashStation.capacity.toString()
        }
    }

    companion object {
        const val KEY_DETAIL = "key-detail"
    }
}