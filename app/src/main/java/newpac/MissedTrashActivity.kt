package newpac

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
            val date = findViewById<EditText>(R.id.date)
            val complaints = findViewById<EditText>(R.id.complaints)
            database = FirebaseDatabase.getInstance().getReference()
       database.child("trashcanNumber").child("Missed Trash").child("Date").setValue(date.text.toString())
            database.child("trashcanNumber").child("Missed Trash").child("Complaints").setValue(complaints.text.toString())
            Toast.makeText(this, "Submitted!", Toast.LENGTH_LONG).show()
            date.setText("")
            complaints.setText("")
        }
    }
}