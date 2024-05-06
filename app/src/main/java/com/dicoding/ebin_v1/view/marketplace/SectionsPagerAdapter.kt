package com.dicoding.ebin_v1.view.marketplace

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
//        val fragment = RequestsFragment()
//
//        fragment.arguments = Bundle().apply {
//            putInt(RequestsFragment.ARG_POSITION, position+1)
//        }
//
//        return fragment
        return when (position) {
            0 -> {
                RequestsFragment().apply {
                    arguments = Bundle().apply {
                        putInt(RequestsFragment.ARG_POSITION, 1)
                    }
                }
            }
            1 -> {
                MyRequestFragment().apply {
                    arguments = Bundle().apply {
                        putInt(MyRequestFragment.ARG_POSITION, 2)
                    }
                }
            }
            else -> throw IllegalStateException("Unexpected position: $position")
        }
    }

}