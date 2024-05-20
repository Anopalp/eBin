package com.dicoding.ebin_v1.view.marketplace

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.ebin_v1.data.response.GetAllRequestResponseItem
import java.util.ArrayList

class SectionsPagerAdapter(activity: AppCompatActivity, private val requests: List<GetAllRequestResponseItem>) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment =  when (position) {
            0 -> RequestsFragment()
            1 -> MyRequestFragment()
            else -> throw IllegalStateException("Unexpected position: $position")
        }
        fragment.arguments = Bundle().apply {
            putParcelableArrayList("REQUEST_LIST", ArrayList(requests))
        }

        return fragment
    }
}