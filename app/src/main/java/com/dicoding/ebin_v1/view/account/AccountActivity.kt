package com.dicoding.ebin_v1.view.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.entity.User
import com.dicoding.ebin_v1.databinding.ActivityAccountBinding
import com.dicoding.ebin_v1.view.editAccount.EditAccountActivity
import com.dicoding.ebin_v1.view.homepage.HomepageActivity
import com.dicoding.ebin_v1.view.qrCode.QRCodeActivity
import com.dicoding.ebin_v1.view.transactionHistory.TransactionHistoryActivity
import com.dicoding.ebin_v1.view.welcomePage.WelcomePageActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        checkSession(auth)

        setAction()
    }

    private fun checkSession(auth: FirebaseAuth) {
        val firebaseUser = auth.currentUser

        if (firebaseUser == null) {
            startActivity(Intent(this, WelcomePageActivity::class.java))
            finish()
        }
    }

    private fun setAction() {
        binding.mtAccountToolBar.setNavigationOnClickListener {
            finish()
        }

        binding.mtAccountToolBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_edit -> {
                    val intent = Intent(this, EditAccountActivity::class.java)
                    intent.putExtra(EditAccountActivity.KEY_DETAIL, user)
                    startActivity(intent)
                    true
                }
                R.id.action_logout -> {
                    auth.signOut()
                    startActivity(Intent(this, HomepageActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }

        binding.btnAccountQrCode.setOnClickListener {
            startActivity(Intent(this, QRCodeActivity::class.java))
        }

        binding.btnAccountShowTransactionHistory.setOnClickListener {
            startActivity(Intent(this, TransactionHistoryActivity::class.java))
        }
    }

    companion object {
        val user: User = User(70, "sam", "Samuel Luthfi Alghifari", "+62 823-9120-8990", "samuel@testmail.com", "Jl. Let. Jend. Purn. Dr. (HC) Mashudi No.1, Sayang, Kec. Jatinangor, Kabupaten Sumedang, Jawa Barat 45363")
    }
}