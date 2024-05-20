package com.dicoding.ebin_v1.view.marketplace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.entity.Requests
import com.dicoding.ebin_v1.data.response.GetAllRequestResponseItem
import com.dicoding.ebin_v1.databinding.ActivityMarketplaceBinding
import com.dicoding.ebin_v1.view.addRequest.AddRequestActivity
import com.dicoding.ebin_v1.view.welcomePage.WelcomePageActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MarketplaceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMarketplaceBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var marketplaceViewModel: MarketplaceViewModel
    private lateinit var requestsList: List<GetAllRequestResponseItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketplaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        checkSession(auth)

        marketplaceViewModel = ViewModelProvider(this).get(MarketplaceViewModel::class.java)

        marketplaceViewModel.getAllRequests()
        marketplaceViewModel.response.observe(this) { response ->
            requestsList = response
            val sectionsPagerAdapter = SectionsPagerAdapter(this, requestsList)
            val viewPager: ViewPager2 = binding.pagerMarketplace
            viewPager.adapter = sectionsPagerAdapter
            val tabs: TabLayout = binding.tlMarketplaceTabLayout

            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }

        setAction()
    }

    private fun setListAdapter(requests: List<GetAllRequestResponseItem>) {

    }

    private fun checkSession(auth: FirebaseAuth) {
        val firebaseUser = auth.currentUser

        if (firebaseUser == null) {
            startActivity(Intent(this, WelcomePageActivity::class.java))
            finish()
        }
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