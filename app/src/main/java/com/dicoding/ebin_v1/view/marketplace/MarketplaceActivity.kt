package com.dicoding.ebin_v1.view.marketplace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.entity.Requests
import com.dicoding.ebin_v1.databinding.ActivityMarketplaceBinding
import com.dicoding.ebin_v1.view.addRequest.AddRequestActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MarketplaceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMarketplaceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketplaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = binding.pagerMarketplace
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tlMarketplaceTabLayout

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        setAction()
    }

    private fun setAction() {
        binding.mtEditMarketplaceToolBar.setNavigationOnClickListener {
            finish()
        }

        binding.btnMarketplaceAddRequest.setOnClickListener {
            startActivity(Intent(this, AddRequestActivity::class.java))
        }
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.all_request,
            R.string.my_requests
        )
    }
}