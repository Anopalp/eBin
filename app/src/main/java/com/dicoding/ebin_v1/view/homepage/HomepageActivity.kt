package com.dicoding.ebin_v1.view.homepage

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.ebin_v1.R
import com.dicoding.ebin_v1.data.entity.TrashStation
import com.dicoding.ebin_v1.data.response.TrashStationsResponse
import com.dicoding.ebin_v1.data.response.TrashStationsResponseItem
import com.dicoding.ebin_v1.data.retrofit.ApiConfig
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.dicoding.ebin_v1.databinding.ActivityHomepageBinding
import com.dicoding.ebin_v1.view.account.AccountActivity
import com.dicoding.ebin_v1.view.marketplace.MarketplaceActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomepageActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityHomepageBinding
    private lateinit var homepageViewModel: HomepageViewModel
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    private val adapter = TrashStationAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvTrashStationList.layoutManager = layoutManager

        homepageViewModel = ViewModelProvider(this).get(HomepageViewModel::class.java)

        binding.svSearchView.setupWithSearchBar(binding.sbSearchBar)

        binding.svSearchView
            .editText
            .setOnEditorActionListener { _, actionId, _ ->
                binding.svSearchView.hide()
                val searchText = binding.svSearchView.text.toString()
                if (searchText != "") {
                    homepageViewModel.getTrashStation(searchText)
                }
                false
            }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

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

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        setAction()

        homepageViewModel.getAllTrashStations()

        homepageViewModel.response.observe(this) { response ->
            if (response.isEmpty()) {
                Toast.makeText(this, "No Data", Toast.LENGTH_LONG).show()
            }
            setListAdapter(response)
        }
    }

    private fun setListAdapter(trashStations: List<TrashStationsResponseItem>) {
        adapter.submitList(trashStations)
        binding.rvTrashStationList.adapter = adapter
    }

    private fun setAction() {
        binding.btnAccountPage.setOnClickListener {
            startActivity(Intent(this, AccountActivity::class.java))
        }

        binding.btnRequestPage.setOnClickListener {
            startActivity(Intent(this, MarketplaceActivity::class.java))
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isCompassEnabled = true

        getMyLocation()
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

            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    val currentLocation = LatLng(location.latitude, location.longitude)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f))

                    homepageViewModel.response.observe(this) { response ->
                        response.forEach { station ->
                            var pinStation = LatLng(station.location!!.latitude!!.toDouble(), station.location!!.longitude!!.toDouble())
                            val distance = FloatArray(1)
                            Location.distanceBetween(
                                location.latitude, location.longitude,
                                pinStation.latitude, pinStation.longitude,
                                distance
                            )
                            mMap.addMarker(MarkerOptions().position(pinStation).title("Trash Station ${station.id} - Distance ${distance[0]} m"))
                        }
                    }
                }
            }
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

    override fun onResume() {
        super.onResume()
        homepageViewModel.getAllTrashStations()
    }

    companion object {
        val dummyTrashStation: List<TrashStation> = arrayListOf(
            TrashStation("A", "Luthfi", 75.0,"Jl. Ganesa 7-11, Lb. Siliwangi, Kecamatan Coblong, Kota Bandung, Jawa Barat 40132", "8.00 - 16.00",-6.89329641160456, 107.61119012530456),
            TrashStation("B", "Sam", 60.0, "Jl. Dipati Ukur 33-35, Lebakgede, Kecamatan Coblong, Kota Bandung, Jawa Barat 40132","8.00 - 16.00",-6.895194656448975, 107.61721577749637),
            TrashStation("C", "Ibnu", 100.0, "Jl. Sulanjana 26-8, Tamansari, Kec. Bandung Wetan, Kota Bandung, Jawa Barat 40116","8.00 - 16.00",-6.899749771726763, 107.61129215349176)
        )
    }
}