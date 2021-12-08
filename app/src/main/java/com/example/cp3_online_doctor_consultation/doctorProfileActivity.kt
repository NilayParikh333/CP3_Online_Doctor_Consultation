package com.example.cp3_online_doctor_consultation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.razorpay.PaymentResultListener
import kotlinx.android.synthetic.main.activity_doctor_profile.*

class doctorProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_profile)

        Toast.makeText(this, intent.extras!!.getString("DOC_ID"), Toast.LENGTH_LONG).show()
        Toast.makeText(this, intent.extras!!.getString("DOC_TYPE"), Toast.LENGTH_LONG).show()

        val fireDB = FirebaseFirestore.getInstance()

        btnPayment.setOnClickListener{
            Intent(this,PaymentActivity::class.java).also {
                startActivity(it)
            }
        }

        fireDB.collection(intent.extras!!.getString("DOC_TYPE").toString()).document(intent.extras!!.getString("DOC_ID").toString()).get().addOnSuccessListener {
            Log.d("DOCTOR_DATA", it.toString())
            doctor1Name.text = it.get("doc_name").toString()
            doctor1Experience.text = it.get("doc_experience").toString() + " Years of Experience"
            doctor1Rating.text = it.get("doc_rating").toString()
            Glide.with(this).load(it.get("doc_image")).into(imgDoctor1)
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to load doctor data", Toast.LENGTH_SHORT).show()
        }
    }
}