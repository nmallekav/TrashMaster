package newpac

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

private var uploadbutton: Button?=null
class workerHoldActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker_hold)
        uploadbutton =findViewById(R.id.upload)

        uploadbutton!!.setOnClickListener {
            var intent =Intent(this, GarbageTruckGpsUpload::class.java)
            startActivity(intent)
        }
    }
}