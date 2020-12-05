package com.example.trashmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class testActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        var intent = Intent(this,SchedulePickupActivity::class.java)
        startActivity(intent)
    }
}