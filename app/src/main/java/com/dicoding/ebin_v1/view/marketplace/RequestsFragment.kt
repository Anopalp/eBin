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
import com.dicoding.ebin_v1.databinding.FragmentRequestsBinding

class RequestsFragment : Fragment() {

    private lateinit var binding: FragmentRequestsBinding
    private lateinit var requests: List<GetAllRequestResponseItem>

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

        arguments?.let {
            requests = it.getParcelableArrayList<GetAllRequestResponseItem>("REQUEST_LIST") ?: emptyList()
            val adapter = RequestAdapter()
            adapter.submitList(requests)
            binding.rvAllRequestsLists.adapter = adapter
        }


    }

    companion object {
        val dummyRequests: List<Requests> = arrayListOf(
            Requests("luthfi", 100, "botol plastik", 60, "Saturday, 13 April 2024 ", null, "Saya membutuhkan 25 botol plastik untuk tugas sekolah. Secepatnya ya. Terima kasih", "Jl. Let. Jend. Purn. Dr. (HC) Mashudi No.1, Sayang, Kec. Jatinangor, Kabupaten Sumedang, Jawa Barat 45363"),
            Requests("alief", 50, "gram kertas koran", 50, "Saturday, 13 April 2024 ", null, "Saya membutuhkan 25 botol plastik untuk tugas sekolah. Secepatnya ya. Terima kasih", "Jl. Let. Jend. Purn. Dr. (HC) Mashudi No.1, Sayang, Kec. Jatinangor, Kabupaten Sumedang, Jawa Barat 45363"),
            Requests("alief",120, "lembar kertas HVS", 30,"Saturday, 13 April 2024 ", null, "Saya membutuhkan 25 botol plastik untuk tugas sekolah. Secepatnya ya. Terima kasih", "Jl. Let. Jend. Purn. Dr. (HC) Mashudi No.1, Sayang, Kec. Jatinangor, Kabupaten Sumedang, Jawa Barat 45363"),
            Requests("luthfi", 25, "botol plastik", 15,"Saturday, 13 April 2024 ", null, "Saya membutuhkan 25 botol plastik untuk tugas sekolah. Secepatnya ya. Terima kasih", "Jl. Let. Jend. Purn. Dr. (HC) Mashudi No.1, Sayang, Kec. Jatinangor, Kabupaten Sumedang, Jawa Barat 45363"),
            Requests("sam", 25, "botol plastik", 6,"Saturday, 13 April 2024 ", null, "Saya membutuhkan 25 botol plastik untuk tugas sekolah. Secepatnya ya. Terima kasih", "Jl. Let. Jend. Purn. Dr. (HC) Mashudi No.1, Sayang, Kec. Jatinangor, Kabupaten Sumedang, Jawa Barat 45363"),
            Requests("sam", 10, "lembar kertas koran", 5,"Saturday, 13 April 2024 ", null, "Saya membutuhkan 25 botol plastik untuk tugas sekolah. Secepatnya ya. Terima kasih", "Jl. Let. Jend. Purn. Dr. (HC) Mashudi No.1, Sayang, Kec. Jatinangor, Kabupaten Sumedang, Jawa Barat 45363")
        )
    }
}