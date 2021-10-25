package com.dotinfiny.mapsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),OnMapReadyCallback {

    lateinit var myMap:GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        button.setOnClickListener {
            val latLng = LatLng(24.944796314820035, 67.04499089537165)

            val markerOption:MarkerOptions =  MarkerOptions().position(latLng).title("ABC")

            myMap.addMarker(markerOption)
            myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,12.0f))
        }
    }

    override fun onMapReady(gMap: GoogleMap) {
        myMap = gMap
        myMap.setOnMarkerClickListener {marker->
            marker.showInfoWindow()
            true
        }

    }
}