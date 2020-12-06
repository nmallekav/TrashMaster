import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trashmaster.R
import com.google.firebase.database.FirebaseDatabase

class GarbageTruckGpsUpload : AppCompatActivity() {
    private var currentLocation: Location?=null
    private val mMinTime: Long = 5000
    private val mMinDistance = 1000.0f
    private var provider:String?=null
    private  var mLocationManager: LocationManager?=null
    private  var locationListener: LocationListener? =null
    var firebase = FirebaseDatabase.getInstance()
    var myref = firebase.getReference().child("GarbageTruckLatLng")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_garbage_truck_gps_upload)

    }
}