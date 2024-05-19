package com.dicoding.ebin_v1.view.transactionHistory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.response.GetAllUsersResponseItem
import com.dicoding.ebin_v1.databinding.ActivityTransactionHistoryBinding
import com.dicoding.ebin_v1.view.editAccount.EditAccountActivity
import com.dicoding.ebin_v1.view.marketplace.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TransactionHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionHistoryBinding
    private lateinit var detailAccount: GetAllUsersResponseItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailAccount = (intent.getParcelableExtra(EditAccountActivity.KEY_DETAIL) as GetAllUsersResponseItem?)!!

        val sectionsPagerAdapter = HistorySectionPagerAdapter(this, detailAccount)
        val viewPager: ViewPager2 = binding.pagerHistory
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tlHistoryTabLayout

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        setAction()
    }

    private fun setAction() {
        binding.mtHistoryToolBar.setNavigationOnClickListener {
            finish()
        }
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.trash_station,
            R.string.requests
        )

        const val KEY_DETAIL = "key-detail"
    }
}