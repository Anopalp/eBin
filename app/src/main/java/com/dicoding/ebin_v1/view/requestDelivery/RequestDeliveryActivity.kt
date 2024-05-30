package com.dicoding.ebin_v1.view.requestDelivery

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.databinding.ActivityRequestDeliveryBinding

class RequestDeliveryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRequestDeliveryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestDeliveryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAction()
    }

    private fun setAction() {
        binding.mtDeliveryToolBar.setNavigationOnClickListener {
            finish()
        }
    }
}