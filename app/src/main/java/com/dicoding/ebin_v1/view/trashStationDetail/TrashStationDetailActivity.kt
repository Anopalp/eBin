package com.dicoding.ebin_v1.view.trashStationDetail

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.dicoding.ebin_v1.data.entity.TrashStation
import com.dicoding.ebin_v1.data.response.TrashStationsResponseItem
import com.dicoding.ebin_v1.databinding.ActivityTrashStationDetailBinding
import com.dicoding.ebin_v1.view.qrCode.QRCodeActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class TrashStationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrashStationDetailBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lon: Float? = null
    private var lat: Float? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrashStationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val detailTrashStation = intent.getParcelableExtra(KEY_DETAIL) as TrashStationsResponseItem?

        setDetailData(detailTrashStation!!)

        setAction(detailTrashStation)
        getMyLocation()

    }

    private fun setAction(detailTrashStation: TrashStationsResponseItem) {
        binding.mtDetailToolBar.setNavigationOnClickListener {
            finish()
        }

        binding.btnDetailShowQrCode.setOnClickListener {
            startActivity(Intent(this, QRCodeActivity::class.java))
        }

        binding.btnDetailSetNavigation.setOnClickListener {
            val source = "'$lat,$lon'"
            val destination = "'${detailTrashStation.location?.latitude},${detailTrashStation.location?.longitude}'"
            val uriString = "https://www.google.com/maps/dir/$source/$destination"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uriString))
            intent.setPackage("com.android.chrome") // Specify the package for Chrome browser
            startActivity(intent)
        }
    }

    private fun setDetailData(detailTrashStation: TrashStationsResponseItem) {
        binding.apply {
            mtDetailToolBar.title = "Trash Station " + detailTrashStation.id
            txtDetailAddressDescription.text = detailTrashStation.address
            txtDetailOpenHoursDescription.text = "${detailTrashStation.openHours?.openTime}.00 - ${detailTrashStation.openHours?.closeTime}.00"
            txtDetailCapacityDescription.text = "${detailTrashStation.capacity.toString()}%"
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        when {
            permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false -> {
                getMyLocation()
            }
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false -> {
                getMyLocation()
            }
            else -> {}
        }
    }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun getMyLocation() {
        if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION) && checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    setLocation(location)
                } else {
                    val alertDialog = AlertDialog.Builder(this).apply {
                        setTitle("Failed")
                        setMessage("Fail to get location")
                        setPositiveButton("Close") { dialog, _ ->
                            dialog.dismiss()
                        }
                        create()
                    }

                    alertDialog.show()
                }
            }
        } else {
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    private fun setLocation(location: Location) {
        lon = location.longitude.toFloat()
        lat = location.latitude.toFloat()
        Toast.makeText(this, "Your location : $lon , $lat", Toast.LENGTH_LONG).show()
    }

    companion object {
        const val KEY_DETAIL = "key-detail"
    }
}