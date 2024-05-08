package com.dicoding.ebin_v1.view.marketplace

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.entity.Requests
import com.dicoding.ebin_v1.databinding.FragmentRequestsBinding

class RequestsFragment : Fragment() {

    private lateinit var binding: FragmentRequestsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRequestsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvAllRequestsLists.layoutManager  = layoutManager

        val adapter = RequestAdapter()
        adapter.submitList(dummyRequests)
        binding.rvAllRequestsLists.adapter = adapter
    }

    companion object {
        const val ARG_POSITION = "arg_position"

        val dummyRequests: List<Requests> = arrayListOf(
            Requests("luthfi", 100, "botol plastik", 60),
            Requests("alief", 50, "gram kertas koran", 50),
            Requests("alief",120, "lembar kertas HVS", 30),
            Requests("luthfi", 25, "botol plastik", 15),
            Requests("sam", 25, "botol plastik", 6),
            Requests("sam", 10, "lembar kertas koran", 5)
        )
    }
}