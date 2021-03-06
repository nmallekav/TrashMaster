package newpac

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ReportStolenActivity : AppCompatActivity() {
    private var reportStolen: Button?=null
    private var truckShipment: Button?=null
    var firebase = FirebaseDatabase.getInstance()
    var auth = FirebaseAuth.getInstance() // use FirebaseAuth library
    var user = auth.currentUser


    var myRef = firebase.getReference().child(user!!.uid).child("Reported stolen")  // Use FireBase Database DatabaseReference
    var myRef1 = firebase.getReference().child(user!!.uid).child("shiped")  // Use FireBase Database DatabaseReference
    private var shiped:String?=null
    private var stolenReport:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_stolen)
        reportStolen=findViewById(R.id.ReportStolenButton)
        truckShipment=findViewById(R.id.truckShipment)
        reportStolen!!.setOnClickListener {
            var intent1= Intent(this, ReportStolenListActivity::class.java)
            startActivity(intent1)
        }
        truckShipment!!.setOnClickListener { // Use com.google.firebase library
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                     stolenReport=dataSnapshot.getValue(String::class.java)
                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
            myRef1.addValueEventListener(object : ValueEventListener { // Use com.google.firebase library
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    shiped=dataSnapshot.getValue(String::class.java)
                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
            if(stolenReport==null){
                Toast.makeText(this, "Not report stolen yet", Toast.LENGTH_LONG)
                    .show()
            }else if(shiped==null) {
                Toast.makeText(this, "Not ship yet", Toast.LENGTH_LONG)
                    .show()
            }else{
                var intent2= Intent(this, ShipmentTrucker::class.java)
                startActivity(intent2)
            }
        }
    }

}