import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.trashmaster.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class GarbageTruckGPSMarkOnMap : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var myRef = FirebaseDatabase.getInstance().getReference().child("GarbageTruckLatLng")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_garbage_truck_g_p_s_mark_on_map)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera



    }
    override fun onStart() {
        super.onStart()

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var latlng=dataSnapshot.getValue(String::class.java)
                if(latlng!=null){
                    var latAndLng =latlng!!.split(',')
                    var latandlng=LatLng(latAndLng!!.get(0).toDouble(), latAndLng!!.get(1).toDouble())
                    mMap.clear()
                    mMap.addMarker(MarkerOptions().position(latandlng).title("Garbage Truck"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latandlng))
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(20.toFloat()), 2000, null)
                }else{
                    Toast.makeText(this@GarbageTruckGPSMarkOnMap, "There is not garbage truck work now", Toast.LENGTH_LONG)
                        .show()
                    finish()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

}