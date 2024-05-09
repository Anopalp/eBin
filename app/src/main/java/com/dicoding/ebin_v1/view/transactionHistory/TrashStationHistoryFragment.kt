package com.dicoding.ebin_v1.view.transactionHistory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.entity.Transaction
import com.dicoding.ebin_v1.databinding.FragmentTrashStationHistoryBinding

class TrashStationHistoryFragment : Fragment() {

    private lateinit var binding: FragmentTrashStationHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrashStationHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvTrashStationHistoryList.layoutManager = layoutManager

        val historyAdapter = TrashStationHistoryAdapter()
        historyAdapter.submitList(dummyHistory)
        binding.rvTrashStationHistoryList.adapter = historyAdapter

    }

    companion object {
        val dummyHistory: List<Transaction> = arrayListOf(
            Transaction("Friday, 5 April 2024", 30, 100, "gram of paper", "A"),
            Transaction("Tuesday, 2 April 2024", 10, 50, "gram of paper", "C"),
            Transaction("Saturday, 30 March 2024", 10, 40, "gram of paper", "A")
        )
    }
}