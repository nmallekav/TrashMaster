package newpac

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class RegistrationActivity : AppCompatActivity() {

    // Looked at code from firebase lab
    private var mEmail: EditText? = null
    private var mPassword: EditText? = null
    private var registrationB: Button? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        mAuth = FirebaseAuth.getInstance() // Use com.google.firebase library
        mEmail = findViewById(R.id.email)
        mPassword = findViewById(R.id.password)
        registrationB = findViewById(R.id.register)

        registrationB!!.setOnClickListener { register() }
    }

    private fun register() {
        val email: String = mEmail!!.text.toString()
        val password: String = mPassword!!.text.toString()

        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Registration successful!", Toast.LENGTH_LONG).show()

                    val intent = Intent(this@RegistrationActivity, LoginActivity::class.java)
                    intent.putExtra("username", email)
                    startActivity(intent)

                } else {
                    Toast.makeText(applicationContext, "Registration failed!", Toast.LENGTH_LONG).show()
                    val e = task.exception
                    Log.e("newpac.LoginActivity", "Failed Registration", e);
                }
            }
    }
}