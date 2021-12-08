package com.example.cp3_online_doctor_consultation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cp3_online_doctor_consultation.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var databaseReference : DatabaseReference? = null
    var database : FirebaseDatabase? = null
//    var noWhiteSpace = "\\A\\w{4,20}\\z"
//    lateinit var et_signupEmail: EditText
//    lateinit var et_signupName: EditText
//    lateinit var et_signupNumber: EditText
//    lateinit var et_signupPassword: EditText


    // [END declare_auth]

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("Dashboard")


        btnSignup.setOnClickListener{
            val fieldValidation = validate();
            if(fieldValidation == true){
                auth.createUserWithEmailAndPassword(et_signupEmail.text.toString(), et_signupPassword.text.toString())
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            val currentUser = auth.currentUser
                            val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                            currentUserDb?.child("Name")?.setValue(et_signupName.text.toString())
                            currentUserDb?.child("Number")?.setValue(et_signupNumber.text.toString())

                            Toast.makeText(this@SignupActivity, "Registration Successful ", Toast.LENGTH_SHORT).show()
                            Intent(this@SignupActivity, LoginActivity::class.java).also {
                                startActivity(it)
                            }
                            finish()
                        }
                        else{
                            Toast.makeText(this@SignupActivity, "Registration failed , please try again!!", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else{
                Toast.makeText(this, "Please check all fields", Toast.LENGTH_LONG).show();
            }


        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        btn_cntWithGoogle.setOnClickListener{
            signIn()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun validate(): Boolean {
        if (et_signupEmail.text.toString().isEmpty()) {
            et_signupEmail.error = "Email Should not be Empty"
            return false
        }
        if (et_signupName.text.toString().isEmpty()) {
            et_signupName.error = "Name Should not be Empty"
            return false
        }
        if (et_signupNumber.text.toString().isEmpty()) {
            et_signupNumber.error = "Number Should not be Empty"
            return false
        }
        if (et_signupPassword.text.toString().isEmpty()) {
            et_signupPassword.error = "Password Should not be Empty"
            return false
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun register(){

    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
//                        val user = auth.currentUser
//                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        updateUI()
                    }
                }
    }

    private fun updateUI() {

    }
    private fun reload() {

    }

    companion object {
        const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}