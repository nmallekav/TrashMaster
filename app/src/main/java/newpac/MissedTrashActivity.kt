package newpac

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import newpac.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MissedTrashActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference

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

            val user = FirebaseAuth.getInstance().currentUser.toString()
            val trashcanNumber = database.child(user).toString()

            database.child(trashcanNumber).child("Missed Trash").child("Date")
                .setValue("${day}, ${month}, ${year}")
            Log.i(TAG, "${day}, ${month}, ${year}")
            database.child(trashcanNumber).child("Missed Trash").child("Complaints")
                .setValue(complaints.text.toString())
            Toast.makeText(this, "Submitted!", Toast.LENGTH_LONG).show()
            complaints.setText("")
        }
    }

    companion object {
        val TAG = "TrashMaster Project"
    }
}