package com.example.tr

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.MenuPopupWindow
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import java.util.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() , LocationListener {

    private lateinit var locationTextView: TextView
    private lateinit var locationManager: LocationManager
    private var currlocation = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        getLocationPermissionGranted()

        locationTextView = findViewById(R.id.location)
        val dropDownListView = findViewById<AutoCompleteTextView>(R.id.dropdown_menu)
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_dropdown_item_1line,
            arrayListOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"))
        dropDownListView.setAdapter(adapter)

        checkIfLocationIsEnabledOrNot()
        getRecentLocation()

        val desk = findViewById<TextView>(R.id.desk)
        desk.setOnClickListener {
            startActivity(Intent(this,MainActivity2::class.java)
                .putExtra("LOCATION",currlocation))
        }


    }

    private fun getRecentLocation() {

        if(ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER
            ,500,5.0f,this, null)

        }
    }

    private fun checkIfLocationIsEnabledOrNot() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var gpsEnabled = false
        var networkEnabled = false

        try {
            gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        }
        catch (e : Exception){
            e.printStackTrace()
        }

        try {
            networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        }
        catch (e : Exception){
            e.printStackTrace()
        }

        if(!gpsEnabled and !networkEnabled){
            AlertDialog.Builder(this)
                .setTitle("Enable GPS service")
                .setCancelable(false)
                .setPositiveButton("Enable",DialogInterface.OnClickListener{_,_ ->
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))

                })
                .setNegativeButton("Cancel",null)
                .show()
        }
    }

    private fun getLocationPermissionGranted(){
        if(ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            ){
            ActivityCompat.requestPermissions(this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),1000)
        }
    }


    override fun onLocationChanged(location: Location) {
        val geocoder = Geocoder(this, Locale.getDefault())
        val list = geocoder.getFromLocation(location.latitude,location.longitude,1)
        locationTextView.text = list.get(0).locality
        currlocation = list.get(0).locality + " " + list.get(0).adminArea
    }

}