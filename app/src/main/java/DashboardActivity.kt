import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.trashmaster.R
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {
    private var mGpsTracker: Button? = null
    private var mReportSlolen: Button? = null
    private var mMissedTrash: Button? = null
    private var mReportTrash: Button? = null
    private var mSchedulePickup: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        mGpsTracker = findViewById(R.id.gps_tracker)
    }

}