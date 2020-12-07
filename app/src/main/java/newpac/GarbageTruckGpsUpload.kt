package newpac

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class GarbageTruckGpsUpload : AppCompatActivity() {
    private var currentLocation: Location?=null
    private val mMinTime: Long = 5000
    private val mMinDistance = 1000.0f
    private var provider:String?=null
    private  var mLocationManager: LocationManager?=null
    private  var locationListener: LocationListener? =null
    private var timer:Timer?=null
    private var task: TimerTask?=null
    var firebase = FirebaseDatabase.getInstance()
    var myref = firebase.getReference().child("GarbageTruckLatLng")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_garbage_truck_gps_upload)
        timer=Timer()
        task= MyTimerTask()
        timer!!.schedule(task!!,Date(),1000.toLong())

    }
    class MyTimerTask() : TimerTask() {
        private var myRef = FirebaseDatabase.getInstance().getReference().child("GarbageTruckLatLng")
        override fun run() {

        }
    }
}