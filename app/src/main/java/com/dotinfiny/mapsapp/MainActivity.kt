package com.dotinfiny.mapsapp

import android.annotation.SuppressLint
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity(),OnMapReadyCallback {

    lateinit var myMap:GoogleMap

    lateinit var mFusedLocationProviderClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //permission will be granted
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION),100)


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        button.setOnClickListener {
            mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

            mFusedLocationProviderClient.lastLocation.addOnSuccessListener {loc:Location? ->

                val latLng = LatLng(loc!!.latitude,loc.longitude)

                myMap.addMarker(MarkerOptions().position(latLng).title("Found Location"))

                myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,100f))

            }
        }


//        button.setOnClickListener {
//            val latLng = LatLng(24.944796314820035, 67.04499089537165)
//
//            val markerOption:MarkerOptions =  MarkerOptions().position(latLng).title("ABC")
//
//            myMap.addMarker(markerOption)
//            myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,12.0f))
//        }
    }

    override fun onMapReady(gMap: GoogleMap) {
        myMap = gMap
        myMap.setOnMarkerClickListener {marker->
            marker.showInfoWindow()
            true
        }

    }
}