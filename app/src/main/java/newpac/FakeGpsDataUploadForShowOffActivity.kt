package newpac

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import newpac.R
import com.google.android.gms.maps.GoogleMap
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class FakeGpsDataUploadForShowOffActivity : AppCompatActivity() {
    private lateinit var mMap: GoogleMap
    private var timer:Timer?=null
    private var task:TimerTask?=null
    private var lat = 38.984144
    private var lng = -76.971150

    private var openMap:Button?=null
    private var uploadFakeData:Button?=null
    private var stopUploadFakeGpsData:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feake_gps_data_upload_for_show_off)
        openMap=findViewById(R.id.ShowGarbageTruckOnMapButton)
        uploadFakeData=findViewById(R.id.uploadFakeDataButton)
        stopUploadFakeGpsData=findViewById(R.id.stopUploadFakeDataButton)
        uploadFakeData!!.setOnClickListener {
            Toast.makeText(this, "Fake GPS data begin to upload", Toast.LENGTH_LONG)
                .show()
            timer=Timer()
            task=MyTimerTask(lat,lng)
            timer!!.schedule(task!!,Date(),1000.toLong())
        }

        openMap!!.setOnClickListener {
            var intent = Intent(this,GarbageTruckGPSMarkOnMap::class.java)
            startActivity(intent)
        }
        stopUploadFakeGpsData!!.setOnClickListener {
            task!!.cancel()
            timer!!.cancel()
            Toast.makeText(this, "Stoped", Toast.LENGTH_LONG)
                .show()
        }
    }
    class MyTimerTask(var lat:Double,var lng:Double ) : TimerTask() {
        private var myRef = FirebaseDatabase.getInstance().getReference().child("GarbageTruckLatLng") // Use com.google.firebase library
        override fun run() {
            lat=lat+0.00005
            myRef.setValue(lat.toString()+','+lng.toString())
        }
    }

}