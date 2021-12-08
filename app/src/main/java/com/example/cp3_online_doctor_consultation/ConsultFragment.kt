package com.example.cp3_online_doctor_consultation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cp3_online_doctor_consultation.AdapterClass.consultRecyclerAdapter
import com.example.cp3_online_doctor_consultation.AdapterClass.doctorInfo
import com.example.cp3_online_doctor_consultation.utils.CellClickListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_consult.*
import kotlinx.android.synthetic.main.fragment_demo.*
import kotlinx.android.synthetic.main.fragment_disease.*
import kotlinx.android.synthetic.main.recycler_view.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConsultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConsultFragment : Fragment(), CellClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null
    lateinit var firebaseFirestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString("name")

            rvAvailableDoctors.setOnClickListener {
//                val doctorProfileActivity = doctorProfileActivity()
//                requireActivity().supportFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.containerFrame, doctorProfileActivity, "doctorprofileFrag")
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .setReorderingAllowed(true)
//                    .addToBackStack("doctorprofile")
//                    .commit()
//                Intent(this,doctorProfileActivity::class.java).also {
//                    startActivity(it)
//                }
            }

        }
        this.tag.toString()
        Toast.makeText(requireActivity().applicationContext, this.tag.toString(), Toast.LENGTH_SHORT).show()
        firebaseFirestore = FirebaseFirestore.getInstance()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consult, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ConsultFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ConsultFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        btnPayment.setOnClickListener {
//            val doctorProfileFragment = doctorProfileFragment()
//            requireActivity().supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.containerFrame, doctorProfileFragment, "doctorprofileFrag")
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                .setReorderingAllowed(true)
//                .addToBackStack("doctorprofile")
//                .commit()
//        }

        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.title = this.tag.toString().capitalize(Locale.ROOT)

        Log.d("Tag", this.tag.toString())

        val doctorInfoList = mutableListOf(
                doctorInfo("Dr Sushmita Sharma","10 years","4.1"),
                doctorInfo("Dr Devi Shetty","20 years","4.9"),
                doctorInfo("Dr Nishita Parikh","6 years","4.1"),
                doctorInfo("Dr Parimal Desai","9 years","4.4"),
                doctorInfo("Dr Vivek Patel","12 years","3.7"),
                doctorInfo("Dr Navin Reddy","12 years","3.7")
        )


        //Network Call
        firebaseFirestore.collection(this.tag.toString()).get().addOnSuccessListener {
            Log.d("Consult Fragment Data", it.documents.toString())
            it.documents[0].id
            val adapter = consultRecyclerAdapter(requireActivity().applicationContext, it.documents, this)
            rvAvailableDoctors.adapter = adapter
            rvAvailableDoctors.layoutManager = LinearLayoutManager(activity)

//            doctorName.text = currentDoc.get("doc_name").toString()
//            doctorExp.text = currentDoc.get("doc_experience").toString()
//            doctorRating.text = currentDoc.get("doc_review").toString()
        }
    }

    override fun onCellClickListener(docID: String) {
        Toast.makeText(requireActivity().applicationContext, "CLicked", Toast.LENGTH_LONG).show()
        val profileIntent = Intent(requireActivity().applicationContext, doctorProfileActivity::class.java)
        profileIntent.putExtra("DOC_ID", docID)
        profileIntent.putExtra("DOC_TYPE", this.tag.toString())
        startActivity(profileIntent)
    }
}