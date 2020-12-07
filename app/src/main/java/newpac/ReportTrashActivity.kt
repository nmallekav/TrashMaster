package newpac

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File


class ReportTrashActivity : AppCompatActivity() {
    lateinit var photoURI: Uri
    private lateinit var storage: StorageReference
    private lateinit var mLocationManager: LocationManager
    private var location: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        val submitButton = findViewById<Button>(R.id.submit)

        submitButton.setOnClickListener{
            //open camera activity and take photo
            capturePhoto()
            getLocationUpdates()
            reportTrash()
        }
    }

    //https://developer.android.com/training/camera/photobasics
    private fun capturePhoto() {
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            // Create the File where the photo should go
            val photoFile  = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "photo.jpg")

            photoURI = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoFile)

            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            startActivityForResult(intent,
                REQUEST_CODE
            )
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i(TAG, "onActivityResult")
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE && data != null){
             reportTrash()
            Toast.makeText(this, "Submitted!", Toast.LENGTH_LONG).show()
        }
    }

    //store photo, current location and current time in database
    //with "Report trash" as the primary key and current time as secondary key
    fun reportTrash(){
    storage = FirebaseStorage.getInstance().reference
        storage.child(location.toString()).putFile(photoURI)
    }

    private fun getLocationUpdates(){
        Log.i(TAG, "getLocationUpdates")
        try {
            mLocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)


        } catch (e: SecurityException) {
            Log.d(TAG, e.localizedMessage)
        }
    }


    companion object {
        val REQUEST_CODE = 200
        val TAG = "TrashMaster Project"
    }

}


