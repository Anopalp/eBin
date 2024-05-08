package com.dicoding.ebin_v1.view.marketplace

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                RequestsFragment()
            }
            1 -> {
                MyRequestFragment()
            }
            else -> throw IllegalStateException("Unexpected position: $position")
        }
    }

}