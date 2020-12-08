package newpac

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import newpac.R


class MissedTrashActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var trashcanNumber : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_missed_trash)

        val button = findViewById<Button>(R.id.submit)


        button.setOnClickListener{
            val complaints = findViewById<EditText>(R.id.complaints)
            val datePicker = findViewById<DatePicker>(R.id.simpleDatePicker)
            val day = datePicker.dayOfMonth
            val month = datePicker.month
            val year = datePicker.year

            database = FirebaseDatabase.getInstance().getReference()

            val user = FirebaseAuth.getInstance().currentUser

            val myRef = database.child(user!!.uid).child("trashcanNumber")

            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    trashcanNumber = dataSnapshot.getValue(String::class.java).toString()
                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })

            database.child(user!!.uid).child("Missed Trash").setValue("${day}, ${month}, ${year}")

            database.child(user!!.uid).child("Complaints").setValue(complaints.text.toString())

            Toast.makeText(this, "Submitted!", Toast.LENGTH_LONG).show()
            complaints.setText("")
        }
    }

    companion object {
        val TAG = "TrashMaster Project"
    }
}