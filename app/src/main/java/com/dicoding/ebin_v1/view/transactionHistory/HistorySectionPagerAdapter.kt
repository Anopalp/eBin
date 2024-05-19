package com.dicoding.ebin_v1.view.transactionHistory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.ebin_v1.data.response.GetAllUsersResponseItem

class HistorySectionPagerAdapter(activity: AppCompatActivity, private val detailAccount: GetAllUsersResponseItem) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment =  when (position) {
            0 -> TrashStationHistoryFragment()
            1 -> RequestHistoryFragment()
            else -> throw IllegalStateException("Unexpected position: $position")
        }
        fragment.arguments = Bundle().apply {
            putParcelable(TransactionHistoryActivity.KEY_DETAIL, detailAccount)
        }

        return fragment
    }

}