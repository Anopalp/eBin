package com.dicoding.ebin_v1.view.qrCode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.databinding.ActivityQrcodeBinding

class QRCodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQrcodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrcodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAction()
    }

    private fun setAction() {
        binding.mtQrCodeToolBar.setNavigationOnClickListener {
            finish()
        }
    }
}