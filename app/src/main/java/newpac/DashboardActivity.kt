package newpac

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {
    private var mGpsTracker: Button? = null
    private var mReportStolen: Button? = null
    private var mMissedTrash: Button? = null
    private var mReportTrash: Button? = null
    private var mSchedulePickup: Button? = null
    private var mAddTrashcan: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        mSchedulePickup = findViewById(R.id.schedule_pickup)
        mSchedulePickup!!.setOnClickListener {schedulePickupMethod()}

        mReportStolen = findViewById(R.id.report_stolen)
        mReportStolen!!.setOnClickListener {reportStolenMethod()}

        mMissedTrash = findViewById(R.id.missed_trash)
        mMissedTrash!!.setOnClickListener {missedTrashMethod()}

        mReportTrash = findViewById(R.id.report_trash)
        mReportTrash!!.setOnClickListener {reportTrashMethod()}

        mGpsTracker = findViewById(R.id.gps_tracker)
        mGpsTracker!!.setOnClickListener {gpsTrackerMethod()}

       mAddTrashcan = findViewById(R.id.add_trashcan_number)
       mAddTrashcan!!.setOnClickListener{addTrashcanNumber()}
    }


    private fun schedulePickupMethod() {
        val intent = Intent(this@DashboardActivity,SchedulePickupActivity::class.java)
        startActivity(intent)
    }

    private fun reportStolenMethod() {
        val intent = Intent(this@DashboardActivity, ReportStolenActivity::class.java)
        startActivity(intent)
    }

    private fun missedTrashMethod() {
        val intent = Intent(this@DashboardActivity, MissedTrashActivity::class.java)
        startActivity(intent)
    }

    private fun reportTrashMethod() {
        val intent = Intent(this@DashboardActivity, ReportTrashActivity::class.java)
        startActivity(intent)
    }

    private fun gpsTrackerMethod() {
        val intent = Intent(this@DashboardActivity, FakeGpsDataUploadForShowOffActivity::class.java)
        startActivity(intent)
    }

    private fun addTrashcanNumber() {
        val intent = Intent(this@DashboardActivity, AddTrashCanActivity::class.java)
        startActivity(intent)
    }
}