package com.dicoding.ebin_v1.view.marketplace

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.entity.Requests
import com.dicoding.ebin_v1.data.response.GetAllRequestResponseItem
import com.dicoding.ebin_v1.databinding.FragmentMyRequestBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MyRequestFragment : Fragment() {

    private lateinit var binding: FragmentMyRequestBinding
    private lateinit var requests: List<GetAllRequestResponseItem>
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyRequestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        val requestedLayoutManager = LinearLayoutManager(requireContext())
        binding.rvMyRequestsList.layoutManager = requestedLayoutManager

        val requestTakenLayoutManager = LinearLayoutManager(requireContext())
        binding.rvRequestTaken.layoutManager = requestTakenLayoutManager

        arguments?.let {
            requests = it.getParcelableArrayList<GetAllRequestResponseItem>("REQUEST_LIST") ?: emptyList()

            val requestedList = requests.filter { it.receiverID?.id ==  auth.uid}
            val requestedAdapter = RequestAdapter()
            requestedAdapter.submitList(requestedList)
            binding.rvMyRequestsList.adapter = requestedAdapter

            val requestTakenList = requests.filter { it.senderID?.id == auth.uid }
            val requestTakenAdapter = RequestAdapter()
            requestTakenAdapter.submitList(requestTakenList)
            binding.rvRequestTaken.adapter = requestTakenAdapter
        }



//        val requestTakenAdapter = RequestAdapter()
//        requestTakenAdapter.submitList(dummyRequestsTaken)
//        binding.rvRequestTaken.adapter = requestTakenAdapter

    }

    companion object {
        val dummyRequested: List<Requests> = arrayListOf(
            Requests("sam", 10, "botol plastik", 6, "Saturday, 13 April 2024 ", null, "Saya membutuhkan 25 botol plastik untuk tugas sekolah. Secepatnya ya. Terima kasih", "Jl. Let. Jend. Purn. Dr. (HC) Mashudi No.1, Sayang, Kec. Jatinangor, Kabupaten Sumedang, Jawa Barat 45363"),
            Requests("sam", 10, "lembar kertas koran", 5, "Saturday, 13 April 2024 ", null, "Saya membutuhkan 25 botol plastik untuk tugas sekolah. Secepatnya ya. Terima kasih", "Jl. Let. Jend. Purn. Dr. (HC) Mashudi No.1, Sayang, Kec. Jatinangor, Kabupaten Sumedang, Jawa Barat 45363")
        )

        val dummyRequestsTaken: List<Requests> = arrayListOf(
            Requests("luthfi", 25, "botol plastik", 15, "Saturday, 13 April 2024 ", null, "Saya membutuhkan 25 botol plastik untuk tugas sekolah. Secepatnya ya. Terima kasih", "Jl. Let. Jend. Purn. Dr. (HC) Mashudi No.1, Sayang, Kec. Jatinangor, Kabupaten Sumedang, Jawa Barat 45363")
        )
    }
}