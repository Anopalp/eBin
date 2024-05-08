package com.dicoding.ebin_v1.view.transactionHistory

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HistorySectionPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                TrashStationHistoryFragment()
            }
            1 -> {
                RequestHistoryFragment()
            }
            else -> throw IllegalStateException("Unexpected position: $position")
        }
    }

}