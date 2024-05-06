package com.dicoding.ebin_v1.view.editAccount

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.entity.User
import com.dicoding.ebin_v1.databinding.ActivityEditAccountBinding

class EditAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailAccount = intent.getParcelableExtra(KEY_DETAIL) as User?

        setDetailAccount(detailAccount!!)

        setAction()
    }

    private fun setDetailAccount(detailAccount: User) {
        binding.txtInputEditTextUsername.setText(detailAccount.username)
        binding.txtInputEditTextFullName.setText(detailAccount.fullName)
        binding.txtInputEditTextPhone.setText(detailAccount.phone)
        binding.txtInputEditTextEmail.setText(detailAccount.email)
        binding.txtInputEditTextAddress.setText(detailAccount.address)
    }

    private fun setAction() {
        binding.mtEditAccountToolBar.setNavigationOnClickListener {
            finish()
        }
    }

    companion object {
        const val KEY_DETAIL = "key-detail"
    }
}