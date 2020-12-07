package newpac

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddTrashCanActivity : AppCompatActivity(){
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_trashcan)

        Log.i("get", "in class")
        val trashcanNumber = findViewById<EditText>(R.id.trashcan_number)
        val button = findViewById<Button>(R.id.submit)
        database = FirebaseDatabase.getInstance().getReference()
        val user = FirebaseAuth.getInstance().currentUser

        val intent = Intent(this@AddTrashCanActivity, DashboardActivity::class.java)

        button.setOnClickListener{
            database.child(user!!.uid).child("trashcanNumber").setValue(trashcanNumber.text.toString())
            Toast.makeText(this, "Submitted!", Toast.LENGTH_LONG).show()
            trashcanNumber.setText("")
            startActivity(intent)
        }
    }

}