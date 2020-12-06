package com.example.trashmaster.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.trashmaster.R

class HomeActivity : AppCompatActivity() {

    private var registerBtn: Button? = null
    private var loginBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerBtn = findViewById(R.id.register)
        loginBtn = findViewById(R.id.login)

        registerBtn!!.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
           startActivity(intent)
        }

        loginBtn!!.setOnClickListener {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        }
    }


}
