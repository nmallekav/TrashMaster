package newpac

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class SchedulePickupActivity : AppCompatActivity() {
    private var submitButton: Button?=null
    private var useCurrentButton:Button? =null
    private var date:EditText?=null
    private var time:EditText?=null
    private var location:EditText?=null
    private var country:EditText?=null
    private var state:EditText?=null
    private var city:EditText?=null
    private var postalcode:EditText?=null
    private var typeRadioGroup: RadioGroup?=null
    private var trashRadioButton:RadioButton?=null
    private var recycleRadioButton: RadioButton?=null
    private var currentLocation: Location?=null
    private val mMinTime: Long = 5000
    private val mMinDistance = 1000.0f
    private var provider:String?=null
    private  var mLocationManager: LocationManager?=null
    private  var locationListener: LocationListener? =null

    var firebase = FirebaseDatabase.getInstance()
    var myref = firebase.getReference()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_pickup)
        submitButton =  findViewById(R.id.submitButton) as Button
        useCurrentButton = findViewById(R.id.useCurrentLocation) as Button
        date = findViewById(R.id.editTextDate) as EditText
        time = findViewById(R.id.editTextTime) as EditText
        location = findViewById(R.id.editTextLocation) as EditText
        country = findViewById(R.id.editTextCountry) as EditText
        state = findViewById(R.id.editTextState) as EditText
        city = findViewById(R.id.editTextCity) as EditText
        postalcode = findViewById(R.id.editTextPostalCode) as EditText
        typeRadioGroup = findViewById(R.id.TypeRadioGroup) as RadioGroup
        trashRadioButton=findViewById(R.id.TrashRadioButton) as RadioButton
        recycleRadioButton = findViewById(R.id.RecycleRadioButton) as RadioButton
        typeRadioGroup!!.check(R.id.TrashRadioButton)
        var firebase = FirebaseAuth.getInstance()
        var user = firebase.currentUser
        useCurrentButton!!.setOnClickListener{
            mLocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationListener=makeLocationListener()
            if (null != mLocationManager) {
                Log.i(TAG, "Couldn't find the LocationManager")
                // Return a LocationListener
            }
            if (checkSelfPermission(ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(ACCESS_FINE_LOCATION),
                    REQUEST_FINE_LOC_PERM_ONCREATE
                )
            }else {
                if (null != mLocationManager!!.getProvider(LocationManager.NETWORK_PROVIDER)) {
                    currentLocation = mLocationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                    sHasNetwork = true
                    provider=LocationManager.NETWORK_PROVIDER

                }else if (null != mLocationManager!!.getProvider(LocationManager.GPS_PROVIDER)) {
                    currentLocation = mLocationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    provider=LocationManager.GPS_PROVIDER
                }
                mLocationManager!!.requestLocationUpdates(provider!!, mMinTime, mMinDistance, locationListener!!)
                getaddress(currentLocation!!)
            }

        }
        submitButton!!.setOnClickListener {
            if(date!!.text.toString() != null&&
                date!!.text.toString().length>0&&
                time!!.text.toString()!= null&&
                time!!.text.toString().length>0&&
                location!!.text.toString() != null&&
                location!!.text.toString().length>0&&
                country!!.text.toString() != null&&
                country!!.text.toString().length>0&&
                state!!.text.toString() != null&&
                state!!.text.toString().length>0&&
                city!!.text.toString() != null&&
                city!!.text.toString().length>0&&
                postalcode!!.text.toString() != null&&
                postalcode!!.text.toString().length>0&&
                (trashRadioButton!!.isChecked||recycleRadioButton!!.isChecked)
                ){

                myref.child(user!!.uid).child("Location").setValue("scheduled pick up in "+date!!.text.toString()+" "+
                        time!!.text.toString()+" at "+
                        location!!.text.toString()+", "+
                        country!!.text.toString()+", "+
                        state!!.text.toString()+", "+
                        city!!.text.toString()+", "+
                        postalcode!!.text.toString()+", the trash type is "+
                        if(recycleRadioButton!!.isChecked){
                            "trashtype recycle"
                        }else{
                            "trashtype trash"
                        })
                Toast.makeText(this, "scheduled pick up in "+date!!.text.toString()+" "+
                        time!!.text.toString()+" at "+
                        location!!.text.toString()+", "+
                        country!!.text.toString()+", "+
                        state!!.text.toString()+", "+
                        city!!.text.toString()+", "+
                        postalcode!!.text.toString()+", the trash type is "+
                        if(recycleRadioButton!!.isChecked){
                            "trashtype recycle"
                        }else{
                            "trashtype trash"
                        }, Toast.LENGTH_LONG)
                    .show()
                finish()
            }  else{
                Toast.makeText(this, "Not completed the information", Toast.LENGTH_LONG)
                    .show()
            }
        }





    }
    private fun makeLocationListener(): LocationListener {

        return object : LocationListener {
            // Called back when location changes
            override fun onLocationChanged(location: Location) {
                Log.i(TAG, "Received new location$location")

                // Determine whether new location is better than current best estimate
                currentLocation=location
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
                /* Not implemented */
            }

            override fun onProviderEnabled(provider: String) { /* Not implemented */
            }

            override fun onProviderDisabled(provider: String) { /* Not implemented */
            }
        }
    }
    private fun getaddress(inputlocation:Location){

            var longitude=inputlocation.longitude
            var latitude=inputlocation.latitude
            var geocoder = Geocoder(this, Locale.getDefault())
            var addresses= geocoder.getFromLocation(latitude,longitude,1)

            val address = addresses[0].getAddressLine(0)
            val address2 = addresses[0].getAddressLine(1)
            val cityformbutton = addresses[0].locality
            val stateformbutton = addresses[0].adminArea
            val countryformbutton = addresses[0].countryName
            val postalCodeformbutton = addresses[0].postalCode
            val knownName = addresses[0].featureName
            location!!.setText(address+
                    if(address2!=null){
                        address2
                    }else{
                        ""
                    })
            country!!.setText(countryformbutton)
            city!!.setText(cityformbutton)
            state!!.setText(stateformbutton)
            postalcode!!.setText(postalCodeformbutton)
    }

    companion object {
        private const val TAG = "Trash Master schedule pick up"
        private const val REQUEST_FINE_LOC_PERM_ONCREATE = 200



        // False if you don't have network access
        @JvmField
        var sHasNetwork = true
        const val MY_PERMISSIONS_LOCATION = 4
    }
}