package com.example.cp3_online_doctor_consultation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import kotlinx.android.synthetic.main.activity_doctor_profile.*
import kotlinx.android.synthetic.main.activity_doctor_profile.amount_to_be_paid
import kotlinx.android.synthetic.main.activity_payment.*
import org.json.JSONObject

class PaymentActivity : AppCompatActivity(), PaymentResultListener {

    lateinit var razorpay: Button
    lateinit var payment_editText: EditText
    var amountTotal:Double=0.0
    private val TAG:String = PaymentActivity::class.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        razorpay = findViewById(R.id.payBtn)
        payment_editText = findViewById(R.id.amount_to_be_paid)

        listener()
    }

    private fun startPayment() {
        val activity: Activity = this
        val co = Checkout()

        try {
            val options = JSONObject()
            options.put("name", "Nilay Parikh")
            options.put("description", "Service Charge")
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "http://yatiyamaha.com/images/yati-logo.png")
            options.put("theme.color", "#FF3700B3");
            options.put("currency", "INR");
            val payment: String = payment_editText.getText().toString()
            var total = payment.toDouble()
            total = total * 100
            options.put("amount", total)

            val preFill = JSONObject()
            preFill.put("email", " ")
            preFill.put("contact", " ")

            options.put("prefill", preFill)
            co.open(activity, options)
        }catch (e: Exception){
            Toast.makeText(activity, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(p0: String?) {
        Intent(this@PaymentActivity,SuccessActivity::class.java).also {
            startActivity(it)
        }
        Toast.makeText(this, "Payment Successfully:", Toast.LENGTH_LONG).show()
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Intent(this@PaymentActivity,FailureActivity::class.java).also {
            startActivity(it)
        }
        Toast.makeText(this, "Error in payment: ", Toast.LENGTH_LONG).show()
    }

    private fun listener(){
        razorpay.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                if (payment_editText.getText().toString().equals("")) {
                    Toast.makeText(this@PaymentActivity, "Please fill payment", Toast.LENGTH_LONG).show()
                    return
                }
                startPayment()
            }
        })
    }
}