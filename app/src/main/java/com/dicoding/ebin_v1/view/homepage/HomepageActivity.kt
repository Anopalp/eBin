package com.dicoding.ebin_v1.view.homepage

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.entity.TrashStation

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.dicoding.ebin_v1.databinding.ActivityHomepageBinding
import com.dicoding.ebin_v1.view.account.AccountActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior

class HomepageActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityHomepageBinding

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvTrashStationList.layoutManager = layoutManager

        val adapter = TrashStationAdapter()
        adapter.submitList(dummyTrashStation)
        binding.rvTrashStationList.adapter = adapter

        binding.svSearchView.setupWithSearchBar(binding.sbSearchBar)

        bottomSheetBehavior = BottomSheetBehavior.from(binding.hpBottomSheet)

        bottomSheetBehavior.apply {
            peekHeight = 300
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) { }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                adjustButtonConstraints(slideOffset)
            }

        })

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        setAction()

    }

    private fun setAction() {
        binding.btnAccountPage.setOnClickListener {
            startActivity(Intent(this, AccountActivity::class.java))
        }
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isCompassEnabled = true

        getMyLocation()

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getMyLocation()
            }
        }
    private fun getMyLocation() {
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
        } else {
            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun adjustButtonConstraints(slideOffset: Float) {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.hpBottomSheet)
        val peekHeight = bottomSheetBehavior.peekHeight

        val currentHeight = peekHeight + (1250 - peekHeight) * slideOffset

        val marginAboveBottomSheet = 10
        val buttonBottomMargin = currentHeight.toInt() + marginAboveBottomSheet

        val btnRequest = binding.btnRequestPage
        val btnAccount = binding.btnAccountPage

        val paramsRequest = btnRequest.layoutParams as ConstraintLayout.LayoutParams
        val paramsAccount = btnAccount.layoutParams as ConstraintLayout.LayoutParams

        paramsRequest.bottomMargin = buttonBottomMargin
        paramsAccount.bottomMargin = buttonBottomMargin

        btnRequest.layoutParams = paramsRequest
        btnAccount.layoutParams = paramsAccount
    }

    companion object {
        val dummyTrashStation: List<TrashStation> = arrayListOf(
            TrashStation("A", "Luthfi", 75.0,"Jl. Ganesa 7-11, Lb. Siliwangi, Kecamatan Coblong, Kota Bandung, Jawa Barat 40132", "8.00 - 16.00",-6.89329641160456, 107.61119012530456),
            TrashStation("B", "Sam", 60.0, "Jl. Dipati Ukur 33-35, Lebakgede, Kecamatan Coblong, Kota Bandung, Jawa Barat 40132","8.00 - 16.00",-6.895194656448975, 107.61721577749637),
            TrashStation("C", "Ibnu", 100.0, "Jl. Sulanjana 26-8, Tamansari, Kec. Bandung Wetan, Kota Bandung, Jawa Barat 40116","8.00 - 16.00",-6.899749771726763, 107.61129215349176)
        )
    }
}