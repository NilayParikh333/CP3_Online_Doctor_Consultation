package com.example.cp3_online_doctor_consultation.AdapterClass

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cp3_online_doctor_consultation.R
import com.example.cp3_online_doctor_consultation.doctorProfileActivity
import com.example.cp3_online_doctor_consultation.utils.CellClickListener
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.android.synthetic.main.recycler_view.view.*

class consultRecyclerAdapter(
        var context: Context,
        var doctorInfos : List<DocumentSnapshot>,
        private val cellClickListener: CellClickListener
        ) : RecyclerView.Adapter<consultRecyclerAdapter.doctorInfoViewHolder>(){

    inner class doctorInfoViewHolder(itemView: View): RecyclerView.ViewHolder (itemView) {
        val doctor_url: ImageView
            get() {
                TODO()
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): doctorInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view,parent,false)
        return doctorInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: doctorInfoViewHolder, position: Int) {
        holder.itemView.apply {
            doctor1Name.text = doctorInfos[position].get("doc_name").toString()
            doctor1Experience.text = doctorInfos[position].get("doc_experience").toString()
            doctor1Rating.text = doctorInfos[position].get("doc_review").toString()

            Glide.with(this).load(doctorInfos[position].get("doc_image")).into(imgDoctor1)

            cvAvailableDoctors.setOnClickListener {
                cellClickListener.onCellClickListener(doctorInfos[position].id.toString())
            }

//            CLdoctorInfo.setOnClickListener {
//                Intent(this@consultRecyclerAdapter, doctorProfile::class.java).also {
//                    startActivity(it as Context)
//                }
//            }
        }
    }


    override fun getItemCount(): Int {
        return doctorInfos.size
    }
}