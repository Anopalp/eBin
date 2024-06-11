package com.dicoding.ebin_v1.view.ReqHistory

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.response.GetAllUsersResponseItem
import com.dicoding.ebin_v1.databinding.ActivityReqHistoryBinding
import com.dicoding.ebin_v1.view.editAccount.EditAccountActivity
import com.dicoding.ebin_v1.view.transactionHistory.RequestHistoryAdapter
import com.dicoding.ebin_v1.view.transactionHistory.TrashStationHistoryAdapter

class ReqHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReqHistoryBinding
    private lateinit var detailAccount: GetAllUsersResponseItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReqHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailAccount = (intent.getParcelableExtra(KEY_DETAIL) as GetAllUsersResponseItem?)!!

        val requests = detailAccount.request

//        Transaction
//        val transactions = detailAccount.transaction
//        Log.d("TRANSACTIONS", transactions.toString())

        val layoutManager = LinearLayoutManager(this)
        binding.rvReqHistoryList.layoutManager = layoutManager

//        val trashStationHistoryAdapter = TrashStationHistoryAdapter()
//        trashStationHistoryAdapter.submitList(transactions)
//        binding.rvReqHistoryList.adapter = trashStationHistoryAdapter

        val historyAdapter = RequestHistoryAdapter()
        historyAdapter.submitList(requests)
        binding.rvReqHistoryList.adapter = historyAdapter

        setAction()
    }

    private fun setAction() {
        binding.mtReqHistoryToolBar.setNavigationOnClickListener {
            finish()
        }
    }

    companion object {
        const val KEY_DETAIL = "key-detail"
    }
}