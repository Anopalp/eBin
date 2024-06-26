package com.dicoding.ebin_v1.view.transactionHistory

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.entity.Requests
import com.dicoding.ebin_v1.data.entity.Transaction
import com.dicoding.ebin_v1.data.response.GetAllUsersResponseItem
import com.dicoding.ebin_v1.databinding.FragmentRequestHistoryBinding

class RequestHistoryFragment : Fragment() {

    private lateinit var binding: FragmentRequestHistoryBinding
    private lateinit var detailAccount: GetAllUsersResponseItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRequestHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            detailAccount = it.getParcelable(TransactionHistoryActivity.KEY_DETAIL)!!
            Log.d("DETAIL ACCOUNT", detailAccount.toString())
        }

        val requests = detailAccount.request

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvRequestHistoryList.layoutManager = layoutManager

        val historyAdapter = RequestHistoryAdapter()
        historyAdapter.submitList(requests)
        binding.rvRequestHistoryList.adapter = historyAdapter
    }

    companion object {
        val dummyHistory: List<Requests> = arrayListOf(
            Requests("Luthfi", 30, "gram botol plastik", 20, "Sunday, 11 May 2020", "Saturday, 20 April 2023"),
            Requests("Luthfi", 30, "gram botol plastik", 20, "Sunday, 11 May 2020", "Saturday, 20 April 2023")
        )
    }
}