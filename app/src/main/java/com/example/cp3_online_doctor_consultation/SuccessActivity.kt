package com.example.cp3_online_doctor_consultation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_success.*


class SuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        meetingLink.setOnClickListener {
            val url = "https://webexapis.com/v1/authorize?client_id=C0a4670c0a21976c65359a0543706847ec1de24eb2da2e099ac5ce7ad1c996525&response_type=code&redirect_uri=https%3A%2F%2Fmeetingsapac37.webex.com%2Fmeet%2Fpr1662207464&scope=spark%3Aall%20spark%3Akms%20meeting%3Acontrols_write%20meeting%3Aparticipants_read%20meeting%3Acontrols_read%20meeting%3Apreferences_write%20spark%3Acalls_read%20meeting%3Apreferences_read%20meeting%3Aparticipants_write%20spark%3Adevices_write%20spark%3Adevices_read%20meeting%3Aschedules_write&state=set_state_here"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}