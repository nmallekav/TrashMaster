package com.example.trashmaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.Thread.sleep

class feakeGpsDataUploadForShowOffActivity : AppCompatActivity() {
    private lateinit var mMap: GoogleMap
    private var myRef = FirebaseDatabase.getInstance().getReference().child("ShipmentTruckLatLng")
    var lat = 38.945471
    var lng = -77.344936
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feake_gps_data_upload_for_show_off)
    }
    override fun onResume() {
        super.onResume()


        while(true) {
            lat = lat + 0.00001
            lng = lng + 0.00001
            myRef.setValue(lat.toString() + "," + lng.toString())
        }
    }
}