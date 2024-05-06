package com.dicoding.ebin_v1.view.marketplace

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.ebin_v1.R
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
    }

    companion object {
        const val ARG_POSITION = "arg_position"
    }
}