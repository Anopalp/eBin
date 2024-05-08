package com.dicoding.ebin_v1.view.addRequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.databinding.ActivityAddRequestBinding

class AddRequestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRequestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAction()
    }

    private fun setAction() {
        binding.mtAddRequestToolBar.setNavigationOnClickListener {
            finish()
        }
    }
}