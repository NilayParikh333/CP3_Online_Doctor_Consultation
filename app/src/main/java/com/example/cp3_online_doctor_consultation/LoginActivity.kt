package com.example.cp3_online_doctor_consultation

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.cp3_online_doctor_consultation.DashboardActivity
import com.example.cp3_online_doctor_consultation.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        title = "Login"
//        val TAG = "LoginActivity"

        auth = FirebaseAuth.getInstance()
//        signIn(et_loginEmail.text.toString(),et_loginPassword.text.toString())

        // Check if already logged in
        val currentUser = auth.currentUser
        if(currentUser!=null){
            Intent(this, DashboardActivity::class.java).also {
                startActivity(it)
            }
        }

        tvSignup.setOnClickListener {
            Intent(this@LoginActivity, SignupActivity::class.java).also {
                startActivity(it)
            }
        }

        btnLogin.setOnClickListener {
            if (TextUtils.isEmpty(et_loginEmail.text.toString())) {
                et_loginEmail.setError("Please enter your Email")
                return@setOnClickListener
            }
            else if (TextUtils.isEmpty(et_loginPassword.text.toString())){
                et_loginPassword.setError("Please enter your Password")
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(et_loginEmail.text.toString(),et_loginPassword.text.toString())
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        Intent(this@LoginActivity, DashboardActivity::class.java).also {
                            startActivity(it)
                        }
                    }
                    else{
                        Toast.makeText(this@LoginActivity,"Registration failed , please try again!!", Toast.LENGTH_SHORT).show()

                    }
                }.addOnFailureListener{
                    Toast.makeText(this@LoginActivity,"Registration failed 2", Toast.LENGTH_SHORT).show()
                    Log.d("Login Check", it.message.toString())
                }
        }

        tvForgotPassword.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Forgot Password")
            val view = layoutInflater.inflate(R.layout.dialog_forgot_password,null)
            val username = view.findViewById<EditText>(R.id.et_userName)
            builder.setView(view)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener{ _, _ ->
                forgotPassword(username)
            })
            builder.setNegativeButton("Cancel", DialogInterface.OnClickListener{ _, _ -> })
            builder.show()
        }
    }

    private fun forgotPassword(username : EditText){
        if(username.text.toString().isEmpty()){
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()){
            return
        }
        FirebaseAuth.getInstance().sendPasswordResetEmail(username.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this@LoginActivity,"Email sent.", Toast.LENGTH_SHORT).show()
                    }
                }
    }

    private fun login() {

    }
}