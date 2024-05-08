package com.dicoding.ebin_v1.view.marketplace

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.entity.Requests
import com.dicoding.ebin_v1.databinding.FragmentMyRequestBinding

class MyRequestFragment : Fragment() {

    private lateinit var binding: FragmentMyRequestBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyRequestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val requestedLayoutManager = LinearLayoutManager(requireContext())
        binding.rvMyRequestsList.layoutManager = requestedLayoutManager

        val requestTakenLayoutManager = LinearLayoutManager(requireContext())
        binding.rvRequestTaken.layoutManager = requestTakenLayoutManager

        val requestedAdapter = RequestAdapter()
        requestedAdapter.submitList(dummyRequested)
        binding.rvMyRequestsList.adapter = requestedAdapter

        val requestTakenAdapter = RequestAdapter()
        requestTakenAdapter.submitList(dummyRequestsTaken)
        binding.rvRequestTaken.adapter = requestTakenAdapter

    }

    companion object {
        val dummyRequested: List<Requests> = arrayListOf(
            Requests("sam", 10, "botol plastik", 6),
            Requests("sam", 10, "lembar kertas koran", 5)
        )

        val dummyRequestsTaken: List<Requests> = arrayListOf(
            Requests("luthfi", 25, "botol plastik", 15)
        )
    }
}