package newpac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class ReportStolenListActivity : AppCompatActivity() {
    private var submit:Button?=null
    private var stolenLocation:EditText?=null
    private var stolenDate:EditText?=null
    var firebase = FirebaseDatabase.getInstance()
    var myRef = firebase.getReference().child("Reported stolen")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_stolen_list)
        submit=findViewById(R.id.stolenSubmitButton)
        stolenDate=findViewById(R.id.StoleneditTextDate)
        stolenLocation=findViewById(R.id.stolenLocationEditText)

        submit!!.setOnClickListener {
            if (stolenDate!!.text.toString()!=null&&stolenLocation!!.text.toString()!=null){
                myRef.setValue(stolenDate!!.text.toString()+"---"+stolenLocation!!.text.toString())
                Toast.makeText(this, "Stolen report submited", Toast.LENGTH_LONG)
                    .show()
                finish()
            }else{
                Toast.makeText(this, "you need to complete the list first", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}