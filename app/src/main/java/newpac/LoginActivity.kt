package newpac

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private var userEmail: EditText? = null
    private var userPassword: EditText? = null
    private var loginB: Button? = null
    private var mAuth: FirebaseAuth? = null // Use com.google.firebase library

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance() // Use com.google.firebase library
        userEmail = findViewById(R.id.email)
        userPassword = findViewById(R.id.password)
        loginB = findViewById(R.id.login_button)

        loginB!!.setOnClickListener { loginUser() }
    }

    private fun loginUser() {
        val email: String = userEmail!!.text.toString()
        val password: String = userPassword!!.text.toString()

        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Login successful!", Toast.LENGTH_LONG)
                        .show()

                   val intent = Intent(this, DashboardActivity::class.java)
                   startActivity(intent)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Login failed! Please try again later",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

    }

}