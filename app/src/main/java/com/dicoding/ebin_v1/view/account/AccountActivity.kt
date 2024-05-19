package com.dicoding.ebin_v1.view.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.entity.User
import com.dicoding.ebin_v1.data.entity.UserDetail
import com.dicoding.ebin_v1.data.response.GetAllUsersResponseItem
import com.dicoding.ebin_v1.data.retrofit.ApiConfig
import com.dicoding.ebin_v1.databinding.ActivityAccountBinding
import com.dicoding.ebin_v1.view.editAccount.EditAccountActivity
import com.dicoding.ebin_v1.view.homepage.HomepageActivity
import com.dicoding.ebin_v1.view.qrCode.QRCodeActivity
import com.dicoding.ebin_v1.view.transactionHistory.TransactionHistoryActivity
import com.dicoding.ebin_v1.view.welcomePage.WelcomePageActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var userList: List<GetAllUsersResponseItem>
    private lateinit var currentUserData: GetAllUsersResponseItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        showLoading(true)
        setContentView(binding.root)


        auth = Firebase.auth
        checkSession(auth)

        setAction()
        getUser()

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
                    intent.putExtra(EditAccountActivity.KEY_DETAIL, currentUserData)
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
            val intent = Intent(this, TransactionHistoryActivity::class.java)
            intent.putExtra(TransactionHistoryActivity.KEY_DETAIL, currentUserData)
            startActivity(intent)
        }
    }

    private fun getUser() {
        try {
            val client = ApiConfig.getApiService().getAllUsers()
            client.enqueue(object : Callback<List<GetAllUsersResponseItem>> {
                override fun onResponse(
                    call: Call<List<GetAllUsersResponseItem>>,
                    response: Response<List<GetAllUsersResponseItem>>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            userList = responseBody
                            Log.d("GET USER", userList.toString())
                            searchUser()
                        } else {
                            Log.e("GET USER", "onFailure: ${response.message()}")
                        }
                    }
                }

                override fun onFailure(call: Call<List<GetAllUsersResponseItem>>, t: Throwable) {
                    Log.e("API ERROR", "onFailure: ${t.message}")
                }
            })
        } catch (e: Exception) {
            Log.e("API ERROR", "onFailure: ${e.message}")
        }
    }

    private fun searchUser() {
        try {
            val currentUser = auth.currentUser!!.email

            currentUserData = userList.find { it.email == currentUser }!!
            Log.d("SETTING DATA", currentUserData.toString())

            setUserData()
        } catch (e: Exception) {
            Log.e("SETTING DATA", "onFailure: ${e.message}")
        }
    }

    private fun setUserData() {
        binding.apply {
            txtAccountPointPlaceholder.text = currentUserData.point.toString()
            txtAccountUsernamePlaceholder.text = currentUserData.username
            txtAccountFullNamePlaceholder.text = currentUserData.fullname
            txtAccountPhonePlaceholder.text = currentUserData.phone.toString()
            txtAccountEmailPlaceholder.text = currentUserData.email
            txtAccountAddressPlaceholder.text = currentUserData.address
        }
        showLoading(false)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.svDetailContainer.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.svDetailContainer.visibility = View.VISIBLE
        }
    }

    companion object {
        val user: User = User(70, "sam", "Samuel Luthfi Alghifari", "+62 823-9120-8990", "samuel@testmail.com", "Jl. Let. Jend. Purn. Dr. (HC) Mashudi No.1, Sayang, Kec. Jatinangor, Kabupaten Sumedang, Jawa Barat 45363")
    }
}